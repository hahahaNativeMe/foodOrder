package com.lsu.dao.impl;

import com.lsu.bean.BaseDao;
import com.lsu.bean.User;
import com.lsu.dao.UserDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserDaoImpl implements UserDao {
    @Override
    public User login(String id, String password) {
            Connection connection = null;
            PreparedStatement pst = null;
            ResultSet rs = null;
            User user =null;
            try {
                connection = BaseDao.getConnection();
                String sql = "select * from users where id=? and password=?";
                pst = connection.prepareStatement(sql);
                pst.setString(1,id);
                pst.setString(2,password);
                rs = pst.executeQuery();
                while (rs.next()){
                    String name = rs.getString("name");
                    String telephone = rs.getString("telephone");
                    String introduce = rs.getString("introduce");
                    String role = rs.getString("role");
                    int state = rs.getInt("state");
                    String registTime = rs.getString("registTime");

                    user = new User(id,name,null,telephone,introduce,role,state,registTime);

                }
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                BaseDao.close(connection,pst,rs);
            }
            return user;
    }


    @Override
    public void register(User user) {
        Connection connection = null;
        PreparedStatement pst = null;
        try {
            connection = BaseDao.getConnection();
            pst = connection.prepareStatement("insert into users values(?,?,?,?,?,?,?,?)");
            pst.setString(1,user.getId());
            pst.setString(2,user.getName());
            pst.setString(3,user.getPassword());
            pst.setString(4,user.getTelephone());
            pst.setString(5,user.getIntroduce());
            pst.setString(6,user.getRole());
            pst.setInt(7,user.getState());
            pst.setString(8,user.getRegistTime());
            pst.execute();//执行sql语句得到结果集
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            BaseDao.close(connection,pst,null);
        }

    }

    @Override
    public List<User> getAllUsers() {
        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<User> users = new ArrayList<User>();
        try {
            connection = BaseDao.getConnection();
            pst = connection.prepareStatement("select * from users");
            rs = pst.executeQuery();//执行sql语句得到结果集
            while (rs.next()){
                String id = rs.getString("id");
                String name = rs.getString("name");
                String telephone = rs.getString("telephone");
                String introduce = rs.getString("introduce");
                String role = rs.getString("role");
                int state = rs.getInt("state");
                String registTime = rs.getString("registTime");

                User user = new User(id,name,null,telephone,introduce,role,state,registTime);
                users.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            BaseDao.close(connection,pst,rs);
        }
        return users;
    }

    @Override
    public List<User> getUsersByPage(int page, int pageSize) {
        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<User> users= new ArrayList<User>();
        try {
            connection = BaseDao.getConnection();
            String sql = "select * from  users order by registTime DESC limit ?,?";
            pst = connection.prepareStatement(sql);
            pst.setInt(1,(page-1)*pageSize);
            pst.setInt(2,pageSize);
            rs = pst.executeQuery();//执行sql语句得到结果
            while (rs.next()){
                String id = rs.getString("id");
                String name = rs.getString("name");
                String telephone = rs.getString("telephone");
                String introduce = rs.getString("introduce");
                String role = rs.getString("role");
                int state = rs.getInt("state");
                String registTime = rs.getString("registTime");

                User user = new User(id,name,null,telephone,introduce,role,state,registTime);

                users.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            BaseDao.close(connection,pst,rs);
        }
        return users;
    }


    @Override
    public int getTotal() {
        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        int i = 0; //记录总条数
        List<User> users = new ArrayList<User>();
        try {
            connection = BaseDao.getConnection();
            String sql = "select  count(*) from users";
            pst = connection.prepareStatement(sql);
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
    public User findUserById(String id) {
        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        User user = null;
        String sql ="";
        try {
            connection = BaseDao.getConnection();
            sql= "select * from users where id= ?";
            pst = connection.prepareStatement(sql);
            pst.setString(1,id);
            rs = pst.executeQuery();//执行sql语句得到结果集
            while (rs.next()){
                String name = rs.getString("name");
                String telephone = rs.getString("telephone");
                String introduce = rs.getString("introduce");
                String role = rs.getString("role");
                int state = rs.getInt("state");
                String registTime = rs.getString("registTime");

                user = new User(id,name,null,telephone,introduce,role,state,registTime);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            BaseDao.close(connection,pst,rs);
        }
        return user;
    }

    @Override
    public void updateUser(User user) {
        Connection connection = null;
        PreparedStatement pst = null;
        try {
            connection = BaseDao.getConnection();
            String sql = "update users set name=?,telephone=?,registTime=? where id=?";
            pst = connection.prepareStatement(sql);
            pst.setString(1,user.getName());
            pst.setString(2,user.getTelephone());
            pst.setString(3,user.getRegistTime());
            pst.setString(4,user.getId());
            pst.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            BaseDao.close(connection,pst,null);
        }
    }

    @Override
    public void changeUserState(User user) {
        Connection connection = null;
        PreparedStatement pst = null;
        int state = 0;
        try {
            connection = BaseDao.getConnection();
            String sql = "update users set state=? where id=?";
            pst = connection.prepareStatement(sql);
            if (user.getState()==0){
                state=1;
            }else {
                state=0;
            }
            pst.setInt(1,state);
            pst.setString(2,user.getId());
            pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            BaseDao.close(connection,pst,null);
        }
    }

    @Override
    public void delTheUser(String id) {
        Connection connection = null;
        PreparedStatement pst = null;
        try {
            connection = BaseDao.getConnection();
            String sql = "DELETE FROM users WHERE id = ?";
            pst = connection.prepareStatement(sql);
            pst.setString(1,id);
            pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            BaseDao.close(connection,pst,null);
        }
    }

}
