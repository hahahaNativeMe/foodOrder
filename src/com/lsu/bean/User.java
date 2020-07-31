package com.lsu.bean;

import java.util.Date;

public class User {
    private String id; // 用户编号
    private String name; // 用户姓名
    private String password; // 用户密码
    private String telephone; // 用户联系电话
    private String introduce; // 用户介绍
    private String role; // 用户角色 admin是管理员
    private int state; // 用户状态 申请通过1，未通过-1，为查看0
    private  String registTime;// 注册时间

    public User() {
    }

    public User(String id, String name, String password, String telephone, String introduce, String role, int state, String registTime) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.telephone = telephone;
        this.introduce = introduce;
        this.role = role;
        this.state = state;
        this.registTime = registTime;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", telephone='" + telephone + '\'' +
                ", introduce='" + introduce + '\'' +
                ", role='" + role + '\'' +
                ", state=" + state +
                ", registTime=" + registTime +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getRegistTime() {
        return registTime;
    }

    public void setRegistTime(String registTime) {
        this.registTime = registTime;
    }
}
