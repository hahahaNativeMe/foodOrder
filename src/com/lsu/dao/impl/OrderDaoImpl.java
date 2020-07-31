package com.lsu.dao.impl;

import com.lsu.bean.*;
import com.lsu.dao.OrderDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl implements OrderDao {
    @Override
    public List<Order> findOrderByOrder(User user) {
        return null;
    }

    @Override
    public List<Order> orderList(int page, int pageSize,String studentId,int state) {
        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet resultSet = null;
        List<Order> orders= new ArrayList<Order>();
        try {
            connection = BaseDao.getConnection();
            String sql = "select * from  orders where studentid=? and state=? limit  ?,?";
            pst = connection.prepareStatement(sql);
            pst.setString(1,studentId);
            pst.setInt(2,state);
            pst.setInt(3,(page-1)*pageSize);
            pst.setInt(4,pageSize);
            resultSet = pst.executeQuery();//执行sql语句得到结果
            while (resultSet.next()){
                String id = resultSet.getString("id");
                String foodid = resultSet.getString("foodid");
                Double money = Double.valueOf(resultSet.getFloat("money"));
                int num = resultSet.getInt("num");
                int cstate= resultSet.getInt("state");
                String date = resultSet.getString("date");
                String evaluation = resultSet.getString("evaluation");
                Order order = new Order(id,studentId,foodid,money, num, cstate,date,evaluation);
                orders.add(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            BaseDao.close(connection,pst,resultSet);
        }
        return orders;
    }

    @Override
    public Order findOrderById(String id) {
        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        Order order = null;
        String sql ="";
        try {
            connection = BaseDao.getConnection();
            sql= "select * from orders where id= ?";
            pst = connection.prepareStatement(sql);
            pst.setString(1,id);
            rs = pst.executeQuery();//执行sql语句得到结果集
            while (rs.next()){
                String studentId = rs.getString("studentid");
                String foodid = rs.getString("foodid");
                Double money = Double.valueOf(rs.getFloat("money"));
                int num = rs.getInt("num");
                int state = rs.getInt("state");
                String date = rs.getString("date");
                String evaluation = rs.getString("evaluation");

                order = new Order(id,studentId,foodid,money, num, state,date,evaluation);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            BaseDao.close(connection,pst,rs);
        }
        return order;
    }

    @Override
    public List<Order> findAllOrder() {
        return null;
    }

    @Override
    public List<Order> findOrderByManyCondition(String id, String receiverName) {
        return null;
    }

    @Override
    public void delOrderById(String id) {
        Connection connection = null;
        PreparedStatement pst = null;
        try {
            connection = BaseDao.getConnection();
            String sql ="delete from orders where id = ?";
            pst = connection.prepareStatement(sql);
            pst.setString(1,id);
            int i=pst.executeUpdate();
            System.out.println(i);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            BaseDao.close(connection,pst,null);
        }
    }

    @Override
    public void addProduct(Order o) {
        Connection connection = null;
        PreparedStatement pst = null;
        int state = 0;
        try {
            connection = BaseDao.getConnection();
//            Order order = new Order(id,studentid,foodid,money,num,state,date,evaluation);
            String sql = "insert into orders values (?,?,?,?,?,?,?,?)";
            pst = connection.prepareStatement(sql);
            pst.setString(1,o.getId());
            pst.setString(2,o.getStudentid());
            pst.setString(3,o.getFoodid());
            pst.setDouble(4,o.getMoney());
            pst.setInt(5,o.getNum());
            pst.setInt(6,o.getState());
            pst.setString(7,o.getDate());
            pst.setString(8,o.getEvaluation());
            int i = pst.executeUpdate();
            System.out.println("add+"+i);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            BaseDao.close(connection,pst,null);
        }
    }

    @Override
    public void updateOrderState(String id) {

    }

    @Override
    public int getTotal(String stuId,int state) {
        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        int i = 0; //记录总条数
        List<Order> orders = new ArrayList<Order>();
        try {
            connection = BaseDao.getConnection();
            String sql = "select  count(*) from orders where studentid=? and state=?";
            pst = connection.prepareStatement(sql);
            pst.setString(1,stuId);
            pst.setInt(2,state);
            rs = pst.executeQuery();//执行sql语句得到结果集
            while (rs.next()){
                i = rs.getInt(1);//从结果集的第一行问第一列里拿值
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            BaseDao.close(connection,pst,rs);
        }
        return i;
    }

    @Override
    public void changeOrderState(Order order) {
        Connection connection = null;
        PreparedStatement pst = null;
        int state = 0;
        try {
            connection = BaseDao.getConnection();
            String sql = "update orders set state=? where id=?";
            pst = connection.prepareStatement(sql);
            if (order.getState()==0){
                state=1;
            }else {
                state=0;
            }
            pst.setInt(1,state);
            pst.setString(2,order.getId());
            pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            BaseDao.close(connection,pst,null);
        }
    }
}
