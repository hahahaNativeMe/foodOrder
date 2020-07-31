package com.lsu.servlet.admin;

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
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {"/ListUserServlet","/findUserById","/findEditUser","/editUser","/passUser","/delUser"})
public class UserServlet extends HttpServlet {
    UserDao dao = new UserDaoImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String uri = request.getRequestURI();
        if(uri.endsWith("ListUserServlet")){ //分页显示所有用户
            queryAll(request,response);
        }else if(uri.endsWith("findUserById")) { //通过id号查询用户
            findById(request,response);
        }else if(uri.endsWith("findEditUser")){  //将要修改的内容显示到修改页
            findEditUser(request,response);
        }else if(uri.endsWith("editUser")){ //修改用户信息
            editUser(request,response);
        }else if(uri.endsWith("passUser")){ //修改用户的状态
            passUser(request,response);
        }else if(uri.endsWith("delUser")){ //删除用户
            delUser(request,response);
        }

    }

    //删除用户
    private void delUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        dao.delTheUser(id);
        System.out.println("hello!"+id);
        request.getRequestDispatcher("ListUserServlet").forward(request,response);
    }

    //修改用户的状态
    private void passUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        User user = dao.findUserById(id);
        dao.changeUserState(user);
        request.getRequestDispatcher("ListUserServlet").forward(request,response);
    }

    //修改用户信息
    private void editUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        User user = dao.findUserById(id);
        String name= request.getParameter("userName");
        String telephone= request.getParameter("telephone");
        String registTime = request.getParameter("registTime");
        System.out.println(name+telephone+registTime);
        User user1 = new User(user.getId(),name,user.getPassword(),telephone,user.getIntroduce(),user.getRole(),user.getState(),registTime);
        dao.updateUser(user1);
        request.getRequestDispatcher("admin/member-edit-success.jsp").forward(request,response);
    }

    //将要修改的内容显示到修改页
    private void findEditUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        User user = dao.findUserById(id);
        request.setAttribute("u",user);
        request.getRequestDispatcher("admin/member-edit.jsp").forward(request,response);
    }

    //通过id号查询用户
    private void findById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("userId");
        User user = dao.findUserById(id);
        List<User> users =new ArrayList<User>();
        users.add(user);
        request.setAttribute("users",users);
        request.getRequestDispatcher("admin/member-list.jsp").forward(request,response);

    }

    //分页显示所有用户
    private void queryAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pageSize = 5; //每页显示几条记录
        int pageNum = (dao.getTotal()-1)/pageSize +1; //总页数
        int currentPage = 1 ; //当前页数
        if(request.getParameter("page")!=null){ //最开始page没有返回值
            currentPage = Integer.parseInt(request.getParameter("page"));
        }
        if(currentPage-1<1){  //使页面不超界
            currentPage =1;
        }else if(currentPage+1>pageNum) {
            currentPage=pageNum;
        }
        List<User> users = dao.getUsersByPage(currentPage,pageSize);
        request.setAttribute("pageNum",pageNum);
        request.setAttribute("page", currentPage);
        request.setAttribute("users",users);
        request.setAttribute("allPages",dao.getTotal());
        request.getRequestDispatcher("admin/member-list.jsp").forward(request,response);
    }
}
