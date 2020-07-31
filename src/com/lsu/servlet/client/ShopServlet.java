package com.lsu.servlet.client;

import com.lsu.bean.Foods;
import com.lsu.dao.FoodsDao;
import com.lsu.dao.impl.FoodDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/showShop","/productLast","/sortProduct","/showDetails"})
public class ShopServlet extends HttpServlet {
    static FoodsDao dao = new FoodDaoImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri =request.getRequestURI();
        //整个商店都要有分页
        if(uri.endsWith("showShop")){ //显示商店初始页面即，将全部菜品显示出来
            showShop(request,response);
        }else if(uri.endsWith("productLast")){ //商品页的最新商品
            productLast(request,response);
        }else if(uri.endsWith("sortProduct")){ //根据不通种类选择显示不同菜品
            sortProduct(request,response);
        }else if(uri.endsWith("showDetails")){ //将商品细节显示到商品细节页上面
            System.out.println("hello");
            showDetails(request,response);
        }
    }

    //将商品细节显示到商品细节页上面
    private void showDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        Foods food = dao.queryById(id);
        request.setAttribute("food",food);
        productLast(request,response);
        System.out.println(id+"#"+food+"*");
        request.getRequestDispatcher("client/single-product.jsp").forward(request,response);
    }

    //商品页的最新商品
    public static void productLast(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Foods> newFoods = dao.getLastFood(3);
        request.setAttribute("newFoods",newFoods);
    }

    //根据不通种类选择显示不同菜品
    private void sortProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String category = request.getParameter("cate");
        int pageSize = 6; //每页显示几条记录
        int pageNum = (dao.getTotal(category)-1)/pageSize +1; //总页数
        int currentPage = 1 ; //当前页数
        if(request.getParameter("page")!=null){ //最开始page没有返回值
            currentPage = Integer.parseInt(request.getParameter("page"));
        }
        if(currentPage-1<1){  //使页面不超界
            currentPage =1;
        }else if(currentPage+1>pageNum) {
            currentPage=pageNum;
        }
        List<Foods> foods = dao.getFoodsByPage(currentPage,pageSize,category);
        productLast(request,response);
        request.setAttribute("pageNum",pageNum);
        request.setAttribute("page", currentPage);
        request.setAttribute("foods",foods);
        request.setAttribute("allPages",dao.getTotal());
        request.setAttribute("category",category);
        request.setAttribute("foodType",category+"类");
        request.getRequestDispatcher("client/products2.jsp").forward(request,response);

    }



    //将全部商品显示出来
    private void showShop(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pageSize = 6; //每页显示几条记录
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
        List<Foods> foods = dao.getFoodsByPage(currentPage,pageSize);
        productLast(request,response);
        request.setAttribute("pageNum",pageNum);
        request.setAttribute("page", currentPage);
        request.setAttribute("foods",foods);
        request.setAttribute("allPages",dao.getTotal());
        request.setAttribute("foodType","全部菜品");
        request.getRequestDispatcher("client/products.jsp").forward(request,response);
    }
}
