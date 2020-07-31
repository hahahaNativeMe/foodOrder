package com.lsu.servlet;

import com.lsu.bean.User;
import com.lsu.dao.UserDao;
import com.lsu.dao.impl.UserDaoImpl;
import sun.plugin.dom.core.Element;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    UserDao dao = new UserDaoImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        User user = new User("","","","","","",0,"");
        String username = request.getParameter("username");//获得用户账号
        String password = request.getParameter("password");//获得密码
        user = dao.login(username,password);
        HttpSession session = request.getSession();
        session.setAttribute("user",user); //将uesr存入会话中
        if(user == null && !username.equals("账号")){
            //用户不存在，跳回登录界面
            request.setAttribute("registerMessage","用户名或密码错误");
            request.getRequestDispatcher("admin/login.jsp").forward(request,response);
        }else if(user.getState() == 0){
            //用户的状态的未通过的情况
            request.setAttribute("registerMessage","您未通过审核，不能登入");
            request.getRequestDispatcher("admin/login.jsp").forward(request,response);
        }else {
            //用户存在
            if(user.getRole().equals("admin")){
                response.sendRedirect("admin/index.jsp"); //admin跳转后台页
            }else {
                response.sendRedirect(request.getContextPath()+"/showIndex");//普通用户跳转餐馆首页
            }
        }
    }
}
