<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="java.text.SimpleDateFormat" %>
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
        <base href="<%=basePath%>"><link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
        <link rel="stylesheet" href="./css/font.css">
        <link rel="stylesheet" href="./css/xadmin.css">
        <% SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date = simpleDateFormat.format(new java.util.Date());
            request.setAttribute("date", date); %>
    </head>
    <body>
    <div class="x-body layui-anim layui-anim-up">
        <blockquote class="layui-elem-quote">欢迎：
            <span class="x-red">${sessionScope.user.name}</span>！当前时间:${date}</blockquote>
    </div>
    </body>
</html>