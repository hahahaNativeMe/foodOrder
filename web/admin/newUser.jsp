<!doctype html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>网上订餐注册</title>
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
    <link rel="stylesheet" href="css/font.css">
	<link rel="stylesheet" href="css/xadmin.css">
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script src="lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="js/xadmin.js"></script>

</head>
<body class="login-bg" style="background-size:cover">
    
    <div class="login layui-anim layui-anim-up">
        <div class="message">网上订餐注册<i style="font-size: 10px">经由管理员审核后方可购物</i></div>
        <div id="darkbannerwrap"></div>
        <form method="post" class="layui-form" action="${pageContext.request.contextPath}/newUser">
            <input name="id" placeholder="账号(您的学号)"  type="text" lay-verify="required" class="layui-input" >
            <hr class="hr15">
            <input name="name" placeholder="学生名称"  type="text" lay-verify="required" class="layui-input" >
            <hr class="hr15">
            <input name="password" lay-verify="required" placeholder="密码"  type="password" class="layui-input">
            <hr class="hr15">
            <input name="telephone" placeholder="电话号码"  type="tel" lay-verify="required" class="layui-input" >
            <hr class="hr15">
            <input name="introduce" placeholder="个人简介"  type="text" lay-verify="required" class="layui-input" >
            <hr class="hr15">
            <input value="注册" lay-submit lay-filter="login" style="width:100%;" type="submit">
            <hr class="hr20" >
        </form>
    </div>
</body>
</html>