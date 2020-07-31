<%--
  Created by IntelliJ IDEA.
  User: loverybutter
  Date: 2020/6/12
  Time: 10:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改页面</title>
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
    <form class="layui-form" action="${pageContext.request.contextPath}/foodEdit?id=${food.id}" method="post" enctype="multipart/form-data">
        <table>
            <tr>
                <div class="layui-form-item">
                    <label  class="layui-form-label">
                        <span class="x-red">*</span>菜品名称
                    </label>
                    <div class="layui-input-inline">
                        <input type="text"  name="name" required="" lay-verify="required"
                               autocomplete="off" class="layui-input" value="${food.name}">
                    </div>
                </div>
            </tr>
            <tr>
                <div class="layui-form-item">
                    <label  class="layui-form-label">
                        <span class="x-red">*</span>菜品价格
                    </label>
                    <div class="layui-input-inline">
                        <input type="text"  name="price" required="" lay-verify="required"
                               autocomplete="off" class="layui-input" value="${food.price*food.pnum}">
                    </div>
                </div>
            </tr>
            <tr>
                <div class="layui-form-item">
                    <label  class="layui-form-label">
                        <span class="x-red">*</span>菜品种类
                    </label>
                    <div class="layui-input-inline">
                        <input type="text"  name="category" required="" lay-verify="required"
                               autocomplete="off" class="layui-input" value="${food.category}">
                    </div>
                </div>
            </tr>
            <tr>
                <div class="layui-form-item">
                    <label  class="layui-form-label">
                        <span class="x-red">*</span>菜品数量
                    </label>
                    <div class="layui-input-inline">
                        <input type="text"  name="pnum" required="" lay-verify="required"
                               autocomplete="off" class="layui-input" value="${food.pnum}">
                    </div>
                </div>
            </tr>

            <tr>
                <div >
                    <label  class="layui-form-label">
                        <span class="x-red">*</span>菜品图片
                    </label>
                    <img src="${food.imgurl}" width="40" href="40">
                </div>
            </tr>
            <tr>
                <div class="layui-form-item layui-form-text">
                    <label  class="layui-form-label">
                        菜品详情
                    </label>
                    <div class="layui-input-block">
                        <textarea placeholder="请输入内容"  name="description" class="layui-textarea">${food.description}</textarea>
                    </div>
                </div>
            </tr>
        </table>
    </form>
</div>

</body>

</html>

