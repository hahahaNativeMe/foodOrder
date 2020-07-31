package com.lsu.dao;

import com.lsu.bean.Foods;
import com.lsu.bean.Order;

import java.util.List;

public interface FoodsDao {
    //插入食物记录
    public int addProducts(Foods foods);
    //后台系统，根据id删除商品信息
    public int delProducts(String id);
    //查询所有商品记录
    public List<Foods> queryAll();
    //查询指定商品
    public Foods queryById(String id);
    //查询指定商品并显示订单的菜肴数量
    public Foods queryById(String id,int num);
    // 修改商品信息
    public void editProduct(Foods f);
    //多条件查询
    public List<Foods> findProductByManyCondition(String id,
                                                     String category, String name, String minprice, String maxprice);
    //前台，获取本周热销菜品
    public List<Foods> getWeekHotProduct();
    // 生成订单时，将菜品数量减少
    public void changeProductNum(Order order);
    //删除订单时，修改菜品数量
    public void updateProductNum(Order order);
    //前台，用于搜索框根据书名来模糊查询相应的菜品
    public List<Foods> findBookByName(String searchfield);
    //分页查询
    public List<Foods> getFoodsByPage(int page, int pageSize);
    //获取总页数
    public int getTotal();
    //查找前8个新菜品
    public List<Foods> getLastFood(int num);
    //分页查询变化的sql
    public List<Foods> getFoodsByPage(int page, int pageSize,String cate);
    //获取不同种类总页数
    public int getTotal(String cate);
}
