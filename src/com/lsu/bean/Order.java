package com.lsu.bean;

//学生才可以看到自己的订单
public class Order {
    private String id; // 订单编号
    private String studentid; //学生学号
    private String foodid; //菜肴编号
    private double money; // 饭菜总价
    private int num; //订单数量
    private int state; //支付状态
    private String date; // 下单时间
    private String evaluation; //评价

    public Order() {
    }

    public Order(String id, String studentid, String foodid, double money, int num, int state, String date, String evaluation) {
        this.id = id;
        this.studentid = studentid;
        this.foodid = foodid;
        this.money = money;
        this.num = num;
        this.state = state;
        this.date = date;
        this.evaluation = evaluation;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", studentid='" + studentid + '\'' +
                ", foodid='" + foodid + '\'' +
                ", money=" + money +
                ", num=" + num +
                ", state=" + state +
                ", date='" + date + '\'' +
                ", evaluation='" + evaluation + '\'' +
                '}';
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStudentid() {
        return studentid;
    }

    public void setStudentid(String studentid) {
        this.studentid = studentid;
    }

    public String getFoodid() {
        return foodid;
    }

    public void setFoodid(String foodid) {
        this.foodid = foodid;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(String evaluation) {
        this.evaluation = evaluation;
    }
}
