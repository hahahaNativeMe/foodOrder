<%--
  Created by IntelliJ IDEA.
  User: loverybutter
  Date: 2020/6/10
  Time: 22:31
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>菜品列表</title>
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
                window.location.href='${pageContext.request.contextPath}/foodDel?id='+x;
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
    <a class="layui-btn layui-btn-small" onclick="Push" style="line-height:1.6em;margin-top:3px;float:right" href="${pageContext.request.contextPath}/ListFoodServlet" title="刷新">
        <i class="layui-icon" style="line-height:30px">ဂ</i></a>
</div>
<div class="x-body">
    <div class="layui-row">
        <form class="layui-form layui-col-md12 x-so" method="post" action="${pageContext.request.contextPath}/findFood" enctype = "multipart/form-data">
            <table>
                <tr>
                    <td style="padding-right: 50px;">
                        <input type="text" name="name"  placeholder="请输入菜品名称" autocomplete="off" class="layui-input">
                    </td>
                    <td style="padding-right: 150px;">
                        <div class="layui-input-inline">
                            <select name="category" id="category">
                                <option value="" selected="selected">--请选择菜品种类--</option>
                                <option value="荤菜">荤菜</option>
                                <option value="素菜">素菜</option>
                                <option value="主食">主食</option>
                                <option value="套餐">套餐</option>
                            </select>
                        </div>
                    </td>
                    <td>
                        <div class="layui-input-inline">
                            <input type="text" name="minprice" size="10" value="" class="layui-input"/>-
                            <input type="text" name="maxprice" size="10" value="" class="layui-input"/>
                        </div>
                    </td>
                    <td>
                        <button class="layui-btn"  lay-submit="" lay-filter="sreach"><a href="${pageContext.request.contextPath}/findFood"><i class="layui-icon">&#xe615;</i></a></button>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <xblock>
        <button class="layui-btn" onclick="x_admin_show('添加菜品','admin/food-add.jsp')"><i class="layui-icon"></i>添加</button>
        <span  class="x-right" style="line-height:40px;">共有数据：${allPages} 条</span>
    </xblock>
    <form action="${pageContext.request.contextPath}/ListFoodServlet" method="post" id="form1" name="form1" enctype = "multipart/form-data">
        <table class="layui-table">
            <thead>
            <tr>
                <th>
                    <div class="layui-unselect header layui-form-checkbox" lay-skin="primary"><i class="layui-icon">&#xe605;</i></div>
                </th>
                <th>菜品编号</th>
                <th>菜名</th>
                <th>价格</th>
                <th>菜品种类</th>
                <th>菜品数量</th>
                <th>菜品图片</th>
                <th>菜品简介</th>
                <th>菜品管理</th>

            </tr>
            </thead>
            <tbody>
            <c:forEach items="${foods}" var="f">
                <tr>
                    <td>
                        <div class="layui-unselect layui-form-checkbox" lay-skin="primary" data-id='2'><i class="layui-icon">&#xe605;</i></div>
                    </td>
                    <td>${f.id}</td>
                    <td>${f.name}</td>
                    <td>￥${f.price}</td>
                    <td>${f.category}</td>
                    <td>${f.pnum}</td>
                    <td><img src="${f.imgurl}" width="50" height="50"></td>
                    <td>${f.description}</td>
                    <td class="td-manage">
                        <a onclick="x_admin_show('修改','${pageContext.request.contextPath}/showFood?id=${f.id}',600,400)" title="修改" href="javascript:;">
                            <i class="layui-icon">&#xe631;</i>
                        </a>
                        <a title="删除"  onclick="show_confirm('${f.id}')">
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
            <a href="ListFoodServlet?page=1" class="num">首页&nbsp;</a>
            <a href="ListFoodServlet?page=${page-1}" class="num">上一页 </a>&nbsp;
            <a href="ListFoodServlet?page=${page+1}" class="num">下一页</a>&nbsp;
            <a href="ListFoodServlet?page=${pageNum}" class="num">尾页</a>
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
