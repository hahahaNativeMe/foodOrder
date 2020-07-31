package com.lsu.dao;

import com.lsu.bean.Notice;

import java.util.List;

public interface NoticeDao {
    //查询所有公告
    public List<Notice> getAllNotices();
    //分页查询
    public List<Notice> getNoticesByPage(int page,int pageSize);
    //添加公告
    public int addNotice(Notice n);
    //按ID查询公告
    public Notice findNoticeById(String n_id);
    //更新公告
    public int updateNotice(Notice n);
    //删除公告
    public int deleteNotice(String n_id);
    //查询最新公告
    public Notice getRecentNotice();
    //获取总页数
    public int getTotal();
}
