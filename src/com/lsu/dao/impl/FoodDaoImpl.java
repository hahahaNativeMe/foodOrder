package com.lsu.dao.impl;

import com.lsu.bean.*;
import com.lsu.dao.FoodsDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FoodDaoImpl implements FoodsDao {
    @Override
    public int addProducts(Foods foods) {
        Connection connection = null;
        PreparedStatement pst = null;
        int i =0;
        try {
            connection = BaseDao.getConnection();
            String sql ="insert into food values(?,?,?,?,?,?,?)";
            pst = connection.prepareStatement(sql);

            pst.setString(1,foods.getId());
            pst.setString(2,foods.getName());
            pst.setDouble(3,foods.getPrice());
            pst.setString(4,foods.getCategory());
            pst.setInt(5,foods.getPnum());
            pst.setString(6,foods.getImgurl());
            pst.setString(7,foods.getDescription());

            i = pst.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            BaseDao.close(connection,pst,null);
        }
        return i;
    }

    @Override
    public int delProducts(String id) {
        Connection connection = null;
        PreparedStatement pst = null;
        int i=0;
        try {
            connection = BaseDao.getConnection();
            String sql ="delete from food where id = ?";
            pst = connection.prepareStatement(sql);
            pst.setString(1,id);
            i=pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            BaseDao.close(connection,pst,null);
        }
        return i;
    }

    @Override
    public List<Foods> queryAll() {
        return null;
    }

    @Override
    public Foods queryById(String id) {
        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet resultSet = null;
        Foods food= null;

        try {
            connection = BaseDao.getConnection();
            String sql = "SELECT *FROM food where id = ?";
            pst = connection.prepareStatement(sql);
            pst.setString(1,id);
            resultSet = pst.executeQuery();

            while (resultSet.next()){
                //String id = resultSet.getString("id");
                String category = resultSet.getString("category");
                String description = resultSet.getString("description");
                String imgurl = resultSet.getString("imgurl");
                String name = resultSet.getString("name");
                int pnum  = resultSet.getInt("pnum");
                Double price = Double.valueOf(resultSet.getFloat("price"));

                food = new Foods(id,name,price,category,pnum,imgurl,description);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            BaseDao.close(connection,pst,resultSet);
        }
        return food;
    }

    @Override
    public Foods queryById(String id, int num) {
        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet resultSet = null;
        Foods food= null;

        try {
            connection = BaseDao.getConnection();
            String sql = "SELECT *FROM food where id = ?";
            pst = connection.prepareStatement(sql);
            pst.setString(1,id);
            resultSet = pst.executeQuery();

            while (resultSet.next()){
                //String id = resultSet.getString("id");
                String category = resultSet.getString("category");
                String description = resultSet.getString("description");
                String imgurl = resultSet.getString("imgurl");
                String name = resultSet.getString("name");
                Double price = Double.valueOf(resultSet.getFloat("price"));

                food = new Foods(id,name,price,category,num,imgurl,description);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            BaseDao.close(connection,pst,resultSet);
        }
        return food;
    }

    @Override
    public void editProduct(Foods f) {
        Connection connection = null;
        PreparedStatement pst = null;
        String sql = "update food set category = ?,description = ?,imgurl =?,name = ?,pnum = ?,price =? where id = ?";
        try {
            connection = BaseDao.getConnection();
            pst = connection.prepareStatement(sql);
            pst.setString(1,f.getCategory());
            pst.setString(2,f.getDescription());
            pst.setString(3,f.getImgurl());
            pst.setString(4,f.getName());
            pst.setInt(5,f.getPnum());
            pst.setDouble(6,f.getPrice());
            pst.setString(7,f.getId());
            int i=pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            BaseDao.close(connection,pst,null);
        }
    }

    @Override
    public List<Foods> findProductByManyCondition(String id, String category, String name, String minprice, String maxprice) {
        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet resultSet = null;
        List<Foods> foods = new ArrayList<Foods>();
        try {
            connection = BaseDao.getConnection();
            String sql = "select * from food where 1=1 "; //永远成立
            if(id!=null&&id.length()>0){ //多条件查询
                sql +="and id = '"+id+"'";
            }if(category!=null&&category.length()>0){
                sql +="and category = '"+category+"'";
            }if(name!=null&&name.length()>0){
                sql +="and name like '%"+name+"%'";  //like 模糊查询
            }if(minprice!=null&&maxprice!=null&&maxprice.length()>0&&minprice.length()>0){
                sql +="and price between "+minprice+" and "+maxprice;
            }
            pst = connection.prepareStatement(sql);
            resultSet=pst.executeQuery();
            while (resultSet.next()){
                String pid = resultSet.getString("id");
                String pcategory = resultSet.getString("category");
                String pdescription = resultSet.getString("description");
                String pimgurl = resultSet.getString("imgurl");
                String pname = resultSet.getString("name");
                int ppnum  = resultSet.getInt("pnum");
                Double pprice = Double.valueOf(resultSet.getFloat("price"));
                Foods food = new Foods(pid,pname,pprice,pcategory,ppnum,pimgurl,pdescription);
                foods.add(food);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            BaseDao.close(connection,pst,resultSet);
        }
        return foods;
    }

    @Override
    public List<Foods> getWeekHotProduct() {
        return null;
    }

    @Override
    public void changeProductNum(Order order) {
        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet resultSet = null;
        try {
            connection = BaseDao.getConnection();
            String sql = "UPDATE food SET pnum = pnum-? WHERE id = ?";
            pst = connection.prepareStatement(sql);
            pst.setInt(1,+order.getNum());
            pst.setString(2,order.getFoodid());
            int i = pst.executeUpdate();//执行sql语句得到结果
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            BaseDao.close(connection,pst,resultSet);
        }
    }

    @Override
    public void updateProductNum(Order order) {
        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet resultSet = null;
        try {
            connection = BaseDao.getConnection();
            String sql = "UPDATE food SET pnum = pnum+? WHERE id = ?";
            pst = connection.prepareStatement(sql);
            pst.setInt(1,order.getNum());
            pst.setString(2,order.getFoodid());
            int i = pst.executeUpdate();//执行sql语句得到结果
            System.out.println(i+"&");
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            BaseDao.close(connection,pst,resultSet);
        }
    }


    @Override
    public List<Foods> findBookByName(String searchfield) {
        return null;
    }

    @Override
    public List<Foods> getFoodsByPage(int page, int pageSize) {
        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet resultSet = null;
        List<Foods> foods= new ArrayList<Foods>();
        try {
            connection = BaseDao.getConnection();
            String sql = "select * from  food limit  ?,?;";
            pst = connection.prepareStatement(sql);
            pst.setInt(1,(page-1)*pageSize);
            pst.setInt(2,pageSize);
            resultSet = pst.executeQuery();//执行sql语句得到结果
            while (resultSet.next()){
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                Double price = Double.valueOf(resultSet.getFloat("price"));
                int pnum  = resultSet.getInt("pnum");
                String category = resultSet.getString("category");
                String description = resultSet.getString("description");
                String imgurl = resultSet.getString("imgurl");
                Foods food = new Foods(id,name,price,category,pnum,imgurl,description);
                foods.add(food);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            BaseDao.close(connection,pst,resultSet);
        }
        return foods;
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
            String sql = "select  count(*) from food";
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
    public List<Foods> getLastFood(int num){
        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet resultSet = null;
        List<Foods> foods= new ArrayList<Foods>();
        try {
            connection = BaseDao.getConnection();
            //因为没有设时间条，发现图片名称是根据时间设的用imgurl做查询的关键词倒序查询
            String sql = "select * from food order by imgurl DESC LIMIT ?";//倒序取前8条
            pst=connection.prepareStatement(sql);
            pst.setInt(1,num);
            resultSet = pst.executeQuery();//执行sql语句得到结果
            while (resultSet.next()){
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                Double price = Double.valueOf(resultSet.getFloat("price"));
                int pnum  = resultSet.getInt("pnum");
                String category = resultSet.getString("category");
                String description = resultSet.getString("description");
                String imgurl = resultSet.getString("imgurl");

                Foods food = new Foods(id,name,price,category,pnum,imgurl,description);
                foods.add(food);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            BaseDao.close(connection,pst,resultSet);
        }
        return foods;
    }

    @Override
    public List<Foods> getFoodsByPage(int page, int pageSize, String cate) {
        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet resultSet = null;
        List<Foods> foods= new ArrayList<Foods>();
        try {
            connection = BaseDao.getConnection();
            String sql = "select * from  food WHERE category=? limit  ?,? ";
            pst = connection.prepareStatement(sql);
            pst.setString(1,cate);
            pst.setInt(2,(page-1)*pageSize);
            pst.setInt(3,pageSize);
            resultSet = pst.executeQuery();//执行sql语句得到结果
            while (resultSet.next()){
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                Double price = Double.valueOf(resultSet.getFloat("price"));
                int pnum  = resultSet.getInt("pnum");
                String category = resultSet.getString("category");
                String description = resultSet.getString("description");
                String imgurl = resultSet.getString("imgurl");

                Foods food = new Foods(id,name,price,category,pnum,imgurl,description);
                foods.add(food);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            BaseDao.close(connection,pst,resultSet);
        }
        return foods;
    }

    @Override
    public int getTotal(String cate) {
        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        int i = 0; //记录总条数
        List<User> users = new ArrayList<User>();
        try {
            connection = BaseDao.getConnection();
            String sql = "select  count(*) from food where category=?";
            pst = connection.prepareStatement(sql);
            pst.setString(1,cate);
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
}
