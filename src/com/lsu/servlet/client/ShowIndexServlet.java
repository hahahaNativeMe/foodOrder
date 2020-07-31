package com.lsu.servlet.client;

import com.lsu.bean.Foods;
import com.lsu.dao.FoodsDao;
import com.lsu.dao.impl.FoodDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@MultipartConfig()
@WebServlet(urlPatterns = {"/showIndex"})
public class ShowIndexServlet extends HttpServlet {
    FoodsDao dao = new FoodDaoImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.endsWith("showIndex")){ //使首页的新菜品是动态的
            showIndex(request,response);
        }
    }

    private void showIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Foods> foods = dao.getLastFood(8);
        request.setAttribute("foods",foods);
        request.getRequestDispatcher("client/index.jsp").forward(request,response);
    }
}
