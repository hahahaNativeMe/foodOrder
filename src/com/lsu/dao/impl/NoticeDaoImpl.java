package com.lsu.dao.impl;

import com.lsu.bean.BaseDao;
import com.lsu.bean.Notice;
import com.lsu.dao.NoticeDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class NoticeDaoImpl implements NoticeDao {
    @Override
    public List<Notice> getAllNotices() {
        return null;
    }

    @Override
    public List<Notice> getNoticesByPage(int page, int pageSize) {
        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<Notice> notices = new ArrayList<Notice>();
        try {
            connection = BaseDao.getConnection();
            String sql = "select * from  notice order by n_time DESC limit ?,?";
            pst = connection.prepareStatement(sql);
            pst.setInt(1,(page-1)*pageSize);
            pst.setInt(2,pageSize);
            rs = pst.executeQuery();//执行sql语句得到结果
            while (rs.next()){
                int n_id = rs.getInt("n_id");
                String title = rs.getString("title");
                String details = rs.getString("details");
                String n_time = rs.getString("n_time");
                Notice notice = new Notice(n_id,title,details,n_time);
                notices.add(notice);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            BaseDao.close(connection,pst,rs);
        }
        return notices;
    }

    @Override
    public int addNotice(Notice n) {
        Connection connection =null;
        PreparedStatement pst = null;
        int i = 0;
        try {
            connection= BaseDao.getConnection();
            String sql = "insert into notice(title,details,n_time) values(?,?,?)";
            pst = connection.prepareStatement(sql);
            pst.setString(1,n.getTitle());
            pst.setString(2,n.getDetails());
            pst.setString(3,n.getN_time());
            i =pst.executeUpdate(); //执行

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            BaseDao.close(connection,pst,null);
        }
        return i;
    }

    @Override
    public Notice findNoticeById(String n_id) {
        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        Notice notice = null;
        try {
            connection = BaseDao.getConnection();
            String sql= "select * from notice where n_id= ?";
            pst = connection.prepareStatement(sql);
            pst.setString(1,n_id);
            System.out.println(n_id);
            rs = pst.executeQuery();//执行sql语句得到结果集
            while (rs.next()){
                int id = rs.getInt("n_id");
                String title = rs.getString("title");
                String details = rs.getString("details");
                String n_time = rs.getString("n_time");
                notice = new Notice(id,title,details,n_time);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            BaseDao.close(connection,pst,rs);
        }
        return notice;
    }

    @Override
    public int updateNotice(Notice n) {
        Connection connection = null;
        PreparedStatement pst = null;
        int i = 0;
        try {
            connection = BaseDao.getConnection();
            String sql = "update  notice set title=?,details=?,n_time=? where n_id=?";
            pst = connection.prepareStatement(sql);
            pst.setString(1,n.getTitle());
            pst.setString(2,n.getDetails());
            pst.setString(3,n.getN_time());
            pst.setInt(4,n.getN_id());
            i = pst.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            BaseDao.close(connection,pst,null);
        }
        return i;
    }

    @Override
    public int deleteNotice(String n_id) {
        Connection connection = null;
        PreparedStatement pst = null;
        int i = 0;
        try {
            connection = BaseDao.getConnection();
            String sql = "delete from notice where n_id=?";
            pst = connection.prepareStatement(sql);
            pst.setString(1,n_id);
            i = pst.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            BaseDao.close(connection,pst,null);
        }
        return i;
    }

    @Override
    public Notice getRecentNotice() {
        return null;
    }

    @Override
    public int getTotal() {
        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        int i = 0; //记录总条数
        List<Notice> notices = new ArrayList<Notice>();
        try {
            connection = BaseDao.getConnection();
            String sql = "select  count(*) from notice";
            pst = connection.prepareStatement(sql);
            rs = pst.executeQuery();//执行sql语句得到结果集
            while (rs.next()){
                i = rs.getInt(1);//从结果集的第一行问第一列里拿值
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            BaseDao.close(connection,pst,rs);
        }
        return i;
    }
}
