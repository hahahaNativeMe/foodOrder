package com.lsu.servlet.admin;

import com.lsu.bean.Notice;
import com.lsu.bean.User;
import com.lsu.dao.NoticeDao;
import com.lsu.dao.impl.NoticeDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

@WebServlet(urlPatterns = {"/ListNoticeServlet","/noticeAdd","/showNotice","/editNotice","/noticeDel"})
public class NoticeServlet extends HttpServlet {
    NoticeDao dao = new NoticeDaoImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String uri = request.getRequestURI();
        if(uri.endsWith("ListNoticeServlet")){ //分页显示公告
            ListNoticeServlet(request,response);
        }else if (uri.endsWith("noticeAdd")){ //添加公告
            noticeAdd(request,response);
        }else if(uri.endsWith("showNotice")){ //将notice显示到修改页面上
            showNotice(request,response);
        }else if(uri.endsWith("editNotice")){ //修改notice
            esitNotice(request,response);
        }else if(uri.endsWith("noticeDel")){ //删除公告
            noticeDel(request,response);
        }
    }

    //删除公告
    private void noticeDel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String n_id = request.getParameter("id");
        int i =dao.deleteNotice(n_id);
        request.getRequestDispatcher("ListNoticeServlet").forward(request,response);
    }

    //修改notice
    private void esitNotice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int n_id = Integer.parseInt(request.getParameter("id"));//n_id在页面隐藏域
        String title = request.getParameter("noticeTitle");
        String details = request.getParameter("details");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String n_time = simpleDateFormat.format(new java.util.Date());
        Notice notice = new Notice(n_id,title,details,n_time);
        dao.updateNotice(notice);
        request.getRequestDispatcher("admin/notice-edit-success.jsp").forward(request,response);
    }

    //将notice显示到修改页面上
    private void showNotice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String n_id = request.getParameter("id");
        Notice notice = dao.findNoticeById(n_id);//获取到公告对象
        request.setAttribute("n",notice);
        request.getRequestDispatcher("admin/notice-view.jsp").forward(request,response);
    }

    //添加公告
    private void noticeAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("noticeTitle");
        String details = request.getParameter("details");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String n_time = simpleDateFormat.format(new java.util.Date());
        Notice notice = new Notice(0,title,details,n_time);
        dao.addNotice(notice);
        request.getRequestDispatcher("admin/notice-add.jsp").forward(request,response);
    }

    //分页显示公告
    private void ListNoticeServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        List<Notice> notices = dao.getNoticesByPage(currentPage,pageSize);
        request.setAttribute("pageNum",pageNum);
        request.setAttribute("page", currentPage);
        request.setAttribute("notices",notices);
        request.setAttribute("allPages",dao.getTotal());
        request.getRequestDispatcher("admin/notice-list.jsp").forward(request,response);
    }
}
