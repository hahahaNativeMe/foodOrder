<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: loverybutter
  Date: 2020/6/7
  Time: 18:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>1111<!DOCTYPE html>
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
                        +request.getServerPort()+path+"/"+"admin/";
            %>
            <base href="<%=basePath%>">
            <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
            <link rel="stylesheet" href="./css/font.css">
            <link rel="stylesheet" href="./css/xadmin.css">
            <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
            <script type="text/javascript" src="./lib/layui/layui.js" charset="utf-8"></script>
            <script type="text/javascript" src="./js/xadmin.js"></script>
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
                        window.location.href='${pageContext.request.contextPath}/delOrder?id='+x;
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
</div>
<div class="x-body">
    <form action="${pageContext.request.contextPath}/cart" method="post" enctype="multipart/form-data">
    <xblock>
        <div style="height: 30px;">
        <span class="x-right" style="line-height:40px ;height: 40px;">共有数据：${allPages} 条</span>
        </div>
    </xblock>
    <table class="layui-table">
        <thead>
        <tr>
            <th>
                <div class="layui-unselect header layui-form-checkbox" lay-skin="primary"><i class="layui-icon">&#xe605;</i></div>
            </th>
            <th>订单编号</th>
            <th>订单日期</th>
            <th>订单数量</th>
            <th>支付状态</th>
            <th>订单详情</th>
        </tr>
        </thead>
        <tbody>

            <c:forEach items="${orders}" var="order">
                    <tr>
                        <td>
                            <div class="layui-unselect layui-form-checkbox" lay-skin="primary" data-id='2'><i class="layui-icon">&#xe605;</i></div>
                        </td>
                        <td>${order.id}</td>
                        <td>${order.date}</td>
                        <td>${order.num}</td>
                        <td>未支付</td>
                        <td class="td-manage">
                            <a onclick="member_stop(this,'10001')" href="${pageContext.request.contextPath}/passOder?id=${order.id}"  title="支付">
                                <i class="layui-icon">&#xe601;</i>
                            </a>
                            <a onclick="x_admin_show('详情','${pageContext.request.contextPath}/showOrderFood?id=${order.id}',600,400)" title="查看详情" href="javascript:;">
                                <i class="layui-icon">&#xe631;</i>
                            </a>
                            <a title="删除"  onclick="show_confirm('${order.id}')">
                                <i class="layui-icon">&#xe640;</i>
                            </a>
                        </td>
                    </tr>
                </c:forEach>
        </tbody>
    </table>
    <div class="page">
        <div>
            <a>总共有${pageNum }页，当前是第${page }页 &nbsp</a>
            <a href="${pageContext.request.contextPath}/cart?page=1&stuId=${stuId}" class="num">首页&nbsp;</a>
            <a href="${pageContext.request.contextPath}/cart?stuId=${stuId}&page=${page-1}" class="num">上一页 </a>&nbsp;
            <a href="${pageContext.request.contextPath}/cart?page=${page+1}&stuId=${stuId}" class="num">下一页</a>&nbsp;
            <a href="${pageContext.request.contextPath}/cart?stuId=${stuId}&page=${pageNum}" class="num">尾页</a>
        </div>
    </div>
    </form>
</div>

</body>

</html>
