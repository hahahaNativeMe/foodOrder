<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>编辑公告</title>
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
    <form class="layui-form" action="${pageContext.request.contextPath}/editNotice?id=${n.n_id}" method="post">
        <div class="layui-form-item">
            <label  class="layui-form-label">
                <span class="x-red">*</span>公告标题
            </label>
            <div class="layui-input-inline">
                <input type="text"  name="noticeTitle" required="" lay-verify="required"
                       autocomplete="off" class="layui-input" value="${n.title}">
            </div>
        </div>
        <div class="layui-form-item layui-form-text">
            <label  class="layui-form-label">
                公告详情
            </label>
            <div class="layui-input-block">
                <textarea placeholder="请输入内容"  name="details" class="layui-textarea">${n.details}</textarea>
            </div>
        </div>
        <div class="layui-form-item">
            <label  class="layui-form-label">
            </label>
            <button  class="layui-btn" lay-filter="add" lay-submit="">
                确定
            </button>
        </div>
    </form>
</div>

</body>

</html>
