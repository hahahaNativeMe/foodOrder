package com.lsu.dao;

import com.lsu.bean.User;

import java.util.List;

public interface UserDao {
    //登录
    public User login(String username, String password);
    //注册用户
    public void register(User user);
    //得到全部的用户
    public List<User> getAllUsers();
    //分页查询
    public List<User> getUsersByPage(int page,int pageSize);
    //获取总页数
    public int getTotal();
    //通过id号查询用户
    public User findUserById(String id);
    //更新用户
    public void updateUser(User user);
    //修改用户状态
    public void changeUserState(User user);
    //删除用户
    public void delTheUser(String id);
}
