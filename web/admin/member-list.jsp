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
            window.location.href='${pageContext.request.contextPath}/delUser?id='+x;
          } else {
            alert("您取消了");
          }
      }
    </script>
  </head>

  <body class="layui-anim layui-anim-up">
    <div class="x-nav">
      <span class="layui-breadcrumb">
        <a href="">首页${href}</a>
        <a href="">演示</a>
        <a>
          <cite>导航元素</cite></a>
      </span>
      <a class="layui-btn layui-btn-small" onclick="Push" style="line-height:1.6em;margin-top:3px;float:right" href="${pageContext.request.contextPath}/ListUserServlet" title="刷新">
        <i class="layui-icon" style="line-height:30px">ဂ</i></a>
    </div>
    <div class="x-body">
      <div class="layui-row">
        <form class="layui-form layui-col-md12 x-so" method="post" action="${pageContext.request.contextPath}/findUserById">
          <input type="text" name="userId"  placeholder="请输入用户账号" autocomplete="off" class="layui-input">
          <button class="layui-btn"  lay-submit="" lay-filter="sreach"><i class="layui-icon">&#xe615;</i></button>
        </form>
      </div>
      <xblock>
        <div style="height: 30px"><span  class="x-right" style="line-height:40px;">共有数据：${allPages} 条</span></div>
<%--        <button class="layui-btn layui-btn-danger" onclick="delAll()"><i class="layui-icon"></i>批量删除</button>--%>

      </xblock>
      <form action="" method="post" id="form1" name="form1">
        <table class="layui-table">
          <thead>
          <tr>
            <th>
              <div class="layui-unselect header layui-form-checkbox" lay-skin="primary"><i class="layui-icon">&#xe605;</i></div>
            </th>
            <th>学号</th>
            <th>用户名</th>
            <th>电话</th>
            <th>自我简介</th>
            <th>加入时间</th>
            <th>状态</th>
            <th>用户管理</th>

          </tr>
          </thead>
          <tbody>
            <c:forEach items="${users}" var="u">
            <tr>
              <td>
                <div class="layui-unselect layui-form-checkbox" lay-skin="primary" data-id='2'><i class="layui-icon">&#xe605;</i></div>
              </td>
              <td>${u.id}</td>
              <td>${u.name}</td>
              <td>${u.telephone}</td>
              <td>${u.introduce}</td>
              <td>${u.registTime}</td>
              <td class="td-status">
                <span class="layui-btn layui-btn-normal layui-btn-mini">
                  <c:choose>
                    <c:when test="${u.state ==0}">
                      未通过
                    </c:when>
                    <c:otherwise>
                      已通过
                    </c:otherwise>
                  </c:choose>
                </span></td>
              <td class="td-manage">
                <a onclick="member_stop(this,'10001')" href="${pageContext.request.contextPath}/passUser?id=${u.id}"  title="启用">
                  <i class="layui-icon">&#xe601;</i>
                </a>
                <a onclick="x_admin_show('修改','${pageContext.request.contextPath}/findEditUser?id=${u.id}',600,400)" title="修改密码" href="javascript:;">
                  <i class="layui-icon">&#xe631;</i>
                </a>


                <a title="删除"  onclick="show_confirm(${u.id})">
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
          <a>总共有${pageNum }页，当前是第${page }页 &nbsp</a>
          <a href="ListUserServlet?page=1" class="num">首页&nbsp;</a>
          <a href="ListUserServlet?page=${page-1}" class="num">上一页 </a>&nbsp;
          <a href="ListUserServlet?page=${page+1}" class="num">下一页</a>&nbsp;
          <a href="ListUserServlet?page=${pageNum}" class="num">尾页</a>
        </div>
      </div>


    </div>
    <script>
      layui.use('laydate', function(){
        var laydate = layui.laydate;

        //执行一个laydate实例
        laydate.render({
          elem: '#start' //指定元素
        });

        //执行一个laydate实例
        laydate.render({
          elem: '#end' //指定元素
        });
      });
    </script>







    <%--<script>var _hmt = _hmt || []; (function() {--%>
    <%--    var hm = document.createElement("script");--%>
    <%--    hm.src = "https://hm.baidu.com/hm.js?b393d153aeb26b46e9431fabaf0f6190";--%>
    <%--    var s = document.getElementsByTagName("script")[0];--%>
    <%--    s.parentNode.insertBefore(hm, s);--%>
    <%--  })();</script>--%>
  </body>

</html>