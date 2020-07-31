<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
  </head>
  
  <body>
    <div class="x-body">
        <form class="layui-form" method="post" action="${pageContext.request.contextPath}/editUser?id=${u.id}">
          <div class="layui-form-item">
              <label  class="layui-form-label">
                  <span class="x-red">*</span>学号
              </label>
              <div class="layui-input-inline">
                  <input type="text"  name="number" class="layui-input" value="${u.id}">
              </div>
          </div>

          <div class="layui-form-item">
              <label class="layui-form-label">
                  <span class="x-red">*</span>用户名
              </label>
              <div class="layui-input-inline">
                  <input type="text"  name="userName" class="layui-input" value="${u.name}">
              </div>
          </div>

          <div class="layui-form-item">
                <label class="layui-form-label">
                    <span class="x-red">*</span>电话
                </label>
                <div class="layui-input-inline">
                    <input type="text" name="telephone"class="layui-input" value="${u.telephone}">
                </div>
          </div>
         <div class="layui-form-item">
            <label class="layui-form-label">
                <span class="x-red">*</span>加入时间
            </label>
            <div class="layui-input-inline" >
                <input type="text" name="registTime"  class="layui-input" value="${u.registTime}">
            </div>
         </div>

        <div class="layui-form-item">
            <label  class="layui-form-label">
            </label>
            <button  class="layui-btn" lay-filter="add" lay-submit="">
                <a href="${pageContext.request.contextPath}/editUser?id=${u.id}">确定</a>
            </button>
        </div>
      </form>
    </div>
  </body>
</html>