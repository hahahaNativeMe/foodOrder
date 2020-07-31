package com.lsu.servlet.client;

import com.lsu.bean.Foods;
import com.lsu.bean.Notice;
import com.lsu.bean.Order;
import com.lsu.bean.User;
import com.lsu.dao.FoodsDao;
import com.lsu.dao.OrderDao;
import com.lsu.dao.impl.FoodDaoImpl;
import com.lsu.dao.impl.OrderDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

@WebServlet(urlPatterns = {"/cart","/passOder","/showOrderFood","/delOrder","/historyOrder","/delHisOrder","/showAddCart","/buyFood"})
public class OrderServlet extends HttpServlet {
    OrderDao dao = new OrderDaoImpl();
    FoodsDao fdao = new FoodDaoImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String uri = request.getRequestURI();
        if(uri.endsWith("cart")){ //分页显示购物车
            cart(request,response);
        }else if(uri.endsWith("passOder")){ //将支付状态进行更改
            passOrder(request,response);
        }else if(uri.endsWith("showOrderFood")){ //显示订单上的菜肴此时的菜肴数量是订单上的数量
            showOrderFood(request,response);
        }else if(uri.endsWith("delOrder")){ //取消订单
            delOrder(request,response);
        }else if(uri.endsWith("historyOrder")){ //分页显示历史订单
            historyOrder(request,response);
        }else if(uri.endsWith("delHisOrder")){ //删除历史订单
            delHisOrder(request,response);
        }else if(uri.endsWith("showAddCart")){ //将菜肴细节显示到购买页
            showAddCart(request,response);
        }else if(uri.endsWith("buyFood")){ //购买食物加入购物车
            buyFood(request,response);
        }
    }

    //购买食物加入购物车
    private void buyFood(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String foodid = request.getParameter("id");//得到是菜品id
        Foods food = fdao.queryById(foodid);
        String id = UUID.randomUUID().toString();
        String studentid = request.getParameter("stuId");
        String num = request.getParameter("amount");
        System.out.println(num);
        Double money = food.getPrice()*Integer.parseInt(num);
        int state = 0;//未支付默认0
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = simpleDateFormat.format(new java.util.Date());
        String evaluation = "";
        System.out.println(id+"#"+studentid+"#"+foodid+"#"+money+"#"+num+"#"+state+"#"+date);
        Order order = new Order(id,studentid,foodid,money,Integer.parseInt(num),state,date,evaluation);
        System.out.println(order);
        fdao.changeProductNum(order);  // 生成订单时，将菜品数量减少
        dao.addProduct(order);
        request.getRequestDispatcher("showAddCart?id="+foodid).forward(request,response);
    }

    //将菜肴细节显示到购买页
    private void showAddCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String foodid = request.getParameter("id");//得到是菜品id
        Foods food = fdao.queryById(foodid);
        request.setAttribute("food",food);
        request.getRequestDispatcher("client/buy-food.jsp").forward(request,response);
    }

    //删除历史订单
    private void delHisOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        Order order = dao.findOrderById(id);
        System.out.println(id+order);
        dao.delOrderById(id); //历史订单已近支付过不用修改对应食物的数量
        request.getRequestDispatcher("historyOrder?stuId="+order.getStudentid()).forward(request,response);
    }

    //分页显示历史订单
    private void historyOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String stuId = request.getParameter("stuId");
        int state = 1;
        System.out.println(stuId);
        int pageSize = 5; //每页显示几条记录
        int pageNum = (dao.getTotal(stuId,state)-1)/pageSize +1; //总页数
        int currentPage = 1 ; //当前页数
        if(request.getParameter("page")!=null){ //最开始page没有返回值
            currentPage = Integer.parseInt(request.getParameter("page"));
        }
        if(currentPage-1<1){  //使页面不超界
            currentPage =1;
        }else if(currentPage+1>pageNum) {
            currentPage=pageNum;
        }
        List<Order> orders = dao.orderList(currentPage,pageSize,stuId,state);
        request.setAttribute("pageNum",pageNum);
        request.setAttribute("page", currentPage);
        request.setAttribute("orders",orders);
        request.setAttribute("allPages",dao.getTotal(stuId,state));
        request.setAttribute("stuId",stuId);
        request.getRequestDispatcher("admin/order-list.jsp").forward(request,response);
    }

    //取消订单
    private void delOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        Order order = dao.findOrderById(id);
        fdao.updateProductNum(order);//在删除改语句前先修改菜肴的数量
        dao.delOrderById(id);
        request.getRequestDispatcher("cart?stuId="+order.getStudentid()).forward(request,response);
    }

    //显示订单上的菜肴此时的菜肴数量是订单上的数量
    private void showOrderFood(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        Order order = dao.findOrderById(id);
        Foods food = fdao.queryById(order.getFoodid(),order.getNum());
        request.setAttribute("food",food);
        request.getRequestDispatcher("admin/order-food.jsp").forward(request,response);
    }

    //将支付状态进行更改
    private void passOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        Order order = dao.findOrderById(id);
        dao.changeOrderState(order);
        request.getRequestDispatcher("cart?stuId="+order.getStudentid()).forward(request,response);
    }

    //分页显示购物车
    private void cart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String stuId = request.getParameter("stuId");
        int state = 0;
        int pageSize = 5; //每页显示几条记录
        int pageNum = (dao.getTotal(stuId,state)-1)/pageSize +1; //总页数
        int currentPage = 1 ; //当前页数
        if(request.getParameter("page")!=null){ //最开始page没有返回值
            currentPage = Integer.parseInt(request.getParameter("page"));
        }
        if(currentPage-1<1){  //使页面不超界
            currentPage =1;
        }else if(currentPage+1>pageNum) {
            currentPage=pageNum;
        }
        List<Order> orders = dao.orderList(currentPage,pageSize,stuId,state);
        request.setAttribute("pageNum",pageNum);
        request.setAttribute("page", currentPage);
        request.setAttribute("orders",orders);
        request.setAttribute("allPages",dao.getTotal(stuId,state));
        request.setAttribute("stuId",stuId);
        request.getRequestDispatcher("admin/cart-list.jsp").forward(request,response);
    }
}
