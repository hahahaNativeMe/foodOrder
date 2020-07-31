package com.lsu.servlet.client;

import com.lsu.bean.User;
import com.lsu.dao.UserDao;
import com.lsu.dao.impl.UserDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(urlPatterns = {"/newUser"})
public class NewUserServlet extends HttpServlet {
    UserDao dao = new UserDaoImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String uri = request.getRequestURI();
        if(uri.endsWith("newUser")){ //注册用户并将用户信息加入数据库中
            newUser(request,response);
        }
    }

    //注册用户并将用户信息加入数据库中
    private void newUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String name= request.getParameter("name");
        String password= request.getParameter("password");
        String telephone= request.getParameter("telephone");
        String introduce= request.getParameter("introduce");
        String registTime = request.getParameter("registTime");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String time = simpleDateFormat.format(new java.util.Date());
        User user = new User(id,name,password,telephone,introduce,"student",0,time);
        dao.register(user); //添加一条数据库
        request.getRequestDispatcher("showIndex").forward(request,response);
    }
}
