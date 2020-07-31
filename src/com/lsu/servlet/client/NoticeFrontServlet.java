package com.lsu.servlet.client;

import com.lsu.bean.Notice;
import com.lsu.dao.NoticeDao;
import com.lsu.dao.impl.NoticeDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.lsu.servlet.client.ShopServlet.productLast;

@WebServlet(urlPatterns = {"/noticeShowFront"})
public class NoticeFrontServlet extends HttpServlet {
    NoticeDao dao = new NoticeDaoImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if(uri.endsWith("noticeShowFront")){ //分页显示公告
            noticeShowFront(request,response);
        }
    }

    //分页显示公告
    private void noticeShowFront(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pageSize = 2; //每页显示几条记录
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
        List<Notice> notices = dao.getNoticesByPage(currentPage,pageSize);
        request.setAttribute("pageNum",pageNum);
        request.setAttribute("page", currentPage);
        request.setAttribute("notices",notices);
        request.setAttribute("allPages",dao.getTotal());
        productLast(request,response);
        request.getRequestDispatcher("client/blog.jsp").forward(request,response);
    }
}
