<!doctype html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>网上订餐登录</title>
	<meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
    <meta http-equiv="Cache-Control" content="no-siteapp" />
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
    <script src="./lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="./js/xadmin.js"></script>

</head>
<body class="login-bg">
    
    <div class="login layui-anim layui-anim-up">
        <div type="hidden" class="message">网上订餐登录</div>
        <div>${registerMessage}</div>
        <div id="darkbannerwrap"></div>
        <form method="post" class="layui-form" action="../login">
            <input name="username" placeholder="账号"  type="text" lay-verify="required" class="layui-input" >
            <hr class="hr15">
            <input name="password" lay-verify="required" placeholder="密码"  type="password" class="layui-input">
            <hr class="hr15">
            <input value="登录" lay-submit lay-filter="login" style="width:100%;" type="submit">
            <hr class="hr20" >
            <a href="newUser.jsp">没有账号请先注册</a>
        </form>
    </div>
</body>
</html>