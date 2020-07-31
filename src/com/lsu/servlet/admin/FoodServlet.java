package com.lsu.servlet.admin;

import com.lsu.bean.Foods;
import com.lsu.dao.FoodsDao;
import com.lsu.dao.impl.FoodDaoImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@MultipartConfig()
@WebServlet(urlPatterns= {"/ListFoodServlet","/foodAdd","/foodEdit","/showFood","/foodDel","/findFood"})
public class FoodServlet extends HttpServlet {
    FoodsDao dao = new FoodDaoImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String uri = request.getRequestURI();
        if(uri.endsWith("ListFoodServlet")){ //分页显示餐品
            ListFoodServlet(request,response);
        }else if(uri.endsWith("foodAdd")){ //添加菜品
            foodAdd(request,response);
        }else if(uri.endsWith("showFood")){//将修改的菜品信息呈现到修改页
            showFood(request,response);
        }else if(uri.endsWith("foodEdit")){ //修改的菜品
            foodEdit(request,response);
        }else if(uri.endsWith("foodDel")){ //删除菜品
            foodDel(request,response);
        }else if(uri.endsWith("findFood")){ //多条件查询
            findFood(request,response);
        }
    }

    //多条件查询
    private void findFood(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String category = request.getParameter("category");
        String minprice = request.getParameter("minprice");
        String maxprice = request.getParameter("maxprice");
        List<Foods> foods = dao.findProductByManyCondition(null,category,name,minprice,maxprice);
        request.setAttribute("foods",foods);
        request.getRequestDispatcher("admin/food-list.jsp").forward(request,response);
    }

    //删除菜品
    private void foodDel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        System.out.println(id);
        dao.delProducts(id);
        request.getRequestDispatcher("ListFoodServlet").forward(request,response);
    }

    //修改的菜品
    private void foodEdit(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String category = request.getParameter("category");
        String description = request.getParameter("description");
        String id = request.getParameter("id");

        //上传图片
        Part part = request.getPart("upload");
        String disposition = part.getHeader("content-disposition");
        System.out.println("contsnt-di:"+description);
        String ext = disposition.substring(disposition.lastIndexOf('.'),disposition.length()-1);
        String imgName = System.currentTimeMillis()+ext;   //要写入的文件名
        ServletContext context = request.getServletContext();
        String fPath = context.getRealPath("foodimg"); //得到要报存的
        part.write(fPath+"\\"+imgName);  //写入文件

        String imgurl = "foodimg"+"\\"+imgName;

        String name = request.getParameter("name");
        int pnum = Integer.parseInt(request.getParameter("pnum"));
        Double price = Double.valueOf(request.getParameter("price"));

        Foods food = new Foods(id,name,price,category,pnum,imgurl,description);
        dao.editProduct(food);
        response.sendRedirect("admin/member-edit-success.jsp");
    }

    //将修改的菜品信息呈现到修改页
    private void showFood(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        Foods food = dao.queryById(id);
        request.setAttribute("food",food);
        request.getRequestDispatcher("admin/food-edit.jsp").forward(request,response);
    }

    //添加菜品
    private void foodAdd(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String category = request.getParameter("category");
        String description = request.getParameter("description");
        String id = UUID.randomUUID().toString();

        //上传图片
        Part part = request.getPart("upload");
        String disposition = part.getHeader("content-disposition");
        String ext = disposition.substring(disposition.lastIndexOf('.'),disposition.length()-1);
        String imgName = System.currentTimeMillis()+ext;   //要写入的文件名
        ServletContext context = request.getServletContext();
        String fPath = context.getRealPath("foodimg"); //得到要报存的
        System.out.println(fPath);
        part.write(fPath+"\\"+imgName);  //写入文件
        String imgurl = "foodimg"+"\\"+imgName;

        String name = request.getParameter("name");
        int pnum = Integer.parseInt(request.getParameter("pnum"));
        Double price = Double.valueOf(request.getParameter("price"));

        Foods food = new Foods(id,name,price,category,pnum,imgurl,description);
        dao.addProducts(food);
        System.out.println(food);
        response.sendRedirect("admin/food-add.jsp");
    }

    //分页显示餐品
    private void ListFoodServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pageSize = 3; //每页显示几条记录
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
        request.setAttribute("pageNum",pageNum);
        request.setAttribute("page", currentPage);
        request.setAttribute("foods",foods);
        request.setAttribute("allPages",dao.getTotal());
        request.getRequestDispatcher("admin/food-list.jsp").forward(request,response);
    }
}
