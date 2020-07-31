<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
  
  <head>
    <meta charset="UTF-8">
    <title>欢迎页面-X-admin2.0</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
    <%
      String path = request.getContextPath();
      String basePath = request.getScheme()+"://"
              +request.getServerName()+":"
              +request.getServerPort()+path+"/";
    %>
    <base href="<%=basePath%>">
    <link rel="shortcut icon" href="admin/favicon.ico" type="image/x-icon" />
    <link rel="stylesheet" href="admin/css/font.css">
    <link rel="stylesheet" href="admin/css/xadmin.css">
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script type="text/javascript" src="admin/lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="admin/js/xadmin.js"></script>
    <!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
    <!--[if lt IE 9]>
      <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
      <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <script type="text/javascript">
      function show_confirm(x)
      {
        if (confirm("您确定要删除吗？"))
        {
          window.location.href='${pageContext.request.contextPath}/noticeDel?id='+x;
        } else {
          alert("您取消了");
        }
      }
    </script>
  </head>
  
  <body>
    <div class="x-nav">
      <span class="layui-breadcrumb">
        <a href="">首页</a>
        <a href="">演示</a>
        <a>
          <cite>导航元素</cite></a>
      </span>
      <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right" href="${pageContext.request.contextPath}/ListNoticeServlet" title="刷新">
        <i class="layui-icon" style="line-height:30px">ဂ</i></a>
    </div>
    <div class="x-body">
      <xblock>
        <button class="layui-btn" onclick="x_admin_show('添加用户','${pageContext.request.contextPath}/noticeAdd')"><i class="layui-icon"></i>添加</button>
        <span class="x-right" style="line-height:40px">共有数据：${allPages}条</span>
      </xblock>
      <form action="" method="post" id="form2" name="form2">
        <table class="layui-table">
          <thead>
          <tr>
            <th>
              <div class="layui-unselect header layui-form-checkbox" lay-skin="primary"><i class="layui-icon">&#xe605;</i></div>
            </th>
            <th>公告编号</th>
            <th>公告标题</th>
            <th>详情</th>
            <th>发布时间</th>
            <th>编辑发布</th>
          </tr>
          </thead>
          <tbody>
          <c:forEach items="${notices}" var="n">
          <tr>
            <td>
              <div class="layui-unselect layui-form-checkbox" lay-skin="primary" data-id='2'><i class="layui-icon">&#xe605;</i></div>
            </td>
            <td>${n.n_id}</td>
            <td>${n.title}</td>
            <td>${n.details}</td>
            <td>${n.n_time}</td>
            <td class="td-manage">
              <a title="查看"  onclick="x_admin_show('编辑','${pageContext.request.contextPath}/showNotice?id=${n.n_id}')" href="javascript:;">
                <i class="layui-icon">&#xe63c;</i>
              </a>
              <a title="删除" onclick="show_confirm(${n.n_id})" href="javascript:;">
                <i class="layui-icon">&#xe640;</i>
              </a>
            </td>
          </tr>
          </c:forEach>
          </tbody>
        </table>
      </form>
      <div class="page">
        <div>
          <a>总共有${pageNum}页，当前是第${page}页 &nbsp</a>
          <a href="ListNoticeServlet?page=1" class="num">首页&nbsp;</a>
          <a href="ListNoticeServlet?page=${page-1}" class="num">上一页 </a>&nbsp;
          <a href="ListNoticeServlet?page=${page+1}" class="num">下一页</a>&nbsp;
          <a href="ListNoticeServlet?page=${pageNum}" class="num">尾页</a>
        </div>
      </div>
    </div>

  </body>

</html>