package com.lsu.dao;

import com.lsu.bean.Order;
import com.lsu.bean.User;

import java.util.List;

public interface OrderDao {
    //按照用户ID查找订单
    public List<Order> findOrderByOrder(final User user);
    //分页显示订单
    public List<Order> orderList(int page, int pageSize,String studentId,int sate);
    // 按ID查找订单
    public Order findOrderById(String id) ;
    // 查找 所有订单
    public List<Order> findAllOrder();
    // 多条件查询订单
    public List<Order> findOrderByManyCondition(String id, String receiverName);
    //删除订单
    public void delOrderById(String id);
    // 生成订单
    public void addProduct(Order o);
    // 更新订单状态
    public void updateOrderState(String id);
    //得到中页数
    public int getTotal(String stuId,int state);
    //修改用户状态
    public void changeOrderState(Order order);
}
