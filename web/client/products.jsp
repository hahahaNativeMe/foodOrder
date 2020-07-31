<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>网上订餐v_1.0</title>
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
	<%
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"
				+request.getServerName()+":"
				+request.getServerPort()+path+"/";
	%>
	<base href="<%=basePath%>">
	<!-- Fonts -->
	<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700' rel='stylesheet' type='text/css'>
	<link href='http://fonts.googleapis.com/css?family=Yanone+Kaffeesatz:400,700' rel='stylesheet' type='text/css'>

	<!-- Css -->
	<link rel="stylesheet" href="client/css/nivo-slider.css" type="text/css" />
	<link rel="stylesheet" href="client/css/owl.carousel.css">
	<link rel="stylesheet" href="client/css/bootstrap.min.css">
	<link rel="stylesheet" href="client/css/font-awesome.min.css">
	<link rel="stylesheet" href="client/css/style.css">
	<link rel="stylesheet" href="client/css/responsive.css">

	<!-- jS -->
	<script src="client/js/jquery.min.js" type="text/javascript"></script>
	<script src="client/js/bootstrap.min.js" type="text/javascript"></script>
	<script src="client/js/jquery.nivo.slider.js" type="text/javascript"></script>
	<script src="client/js/owl.carousel.min.js" type="text/javascript"></script>
	<script src="client/js/jquery.nicescroll.js"></script>
	<script src="client/js/jquery.scrollUp.min.js"></script>
	<script src="client/js/main.js" type="text/javascript"></script>


</head>
<body>


<!-- TOP HEADER Start
    ================================================== -->

<section id="top">
	<div class="container">
		<div class="row">
			<div class="col-md-7">
				<p class="contact-action">如果有任何问题，请拨打这个号码: <strong>+652730</strong></p>
			</div>
			<div class="col-md-3 clearfix">
				<ul class="login-cart">
					<li>
						<a data-toggle="modal" data-target="#myModal" href="#">
							<c:choose>
								<c:when test="${sessionScope.user==null}">
									登录
								</c:when>
								<c:otherwise>
									${sessionScope.user.name}
								</c:otherwise>
							</c:choose>
						</a>
					</li>
					<li>
						<div class="cart dropdown">
							<a  href="${pageContext.request.contextPath}/admin/index1.jsp">购物车</a>
						</div>
					</li>
				</ul>
			</div>
			<div class="col-md-2">
				<div class="search-box">
					<div class="input-group">
						<input placeholder="Search Here" type="text" class="form-control">
						<span class="input-group-btn">
					        	<button class="btn btn-default" type="button"></button>
					      	</span>
					</div><!-- /.input-group -->
				</div><!-- /.search-box -->
			</div>
		</div> <!-- End Of /.row -->
	</div>	<!-- End Of /.Container -->


	<!-- MODAL Start
    	================================================== -->

	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">Introduce Yourself</h4>
				</div>
				<div class="modal-body clearfix">

					<form action="#" method="post" id="create-account_form" class="std">
						<fieldset>
							<h3>创建你的账户</h3>
							<div class="form_content clearfix">
								<h4>输入您的学号以创建帐户。</h4>
								<p style="color: #000000">创建用户后需要管理员审核后方可购物</p>
								<p class="submit">
									<button class="btn btn-primary"><a href="${pageContext.request.contextPath}/admin/newUser.jsp" style="color: #ffffff">创建账户</a></button>
								</p>
							</div>
						</fieldset>
					</form>
					<form action="" method="post" id="login_form" class="std">
						<fieldset>
							<h3>已经注册了？</h3>
							<div class="form_content clearfix">
								<h6>请登录 </h6>
								<p class="submit">
									<button class="btn btn-success"><a style="color: #ffffff" href="${pageContext.request.contextPath}/admin/login.jsp">登录</a></button>
								</p>
							</div>
						</fieldset>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>
</section>  <!-- End of /Section -->

	<!-- LOGO Start
    ================================================== -->
	
	<header>
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<a href="#">
						<img src="client/images/logo.png" alt="logo">
					</a>
				</div>	<!-- End of /.col-md-12 -->
			</div>	<!-- End of /.row -->
		</div>	<!-- End of /.container -->
	</header> <!-- End of /Header -->

	


	<!-- MENU Start
    ================================================== -->

	<nav class="navbar navbar-default">
		<div class="container">
		    <div class="navbar-header">
		      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
		        <span class="sr-only">Toggle navigation</span>
		        <span class="icon-bar"></span>
		        <span class="icon-bar"></span>
		        <span class="icon-bar"></span>
		      </button>
		    </div> <!-- End of /.navbar-header -->

		    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
		      	<ul class="nav navbar-nav nav-main">
		        	<li ><a href="${pageContext.request.contextPath}/showIndex">首页</a></li>
					<li class="active"><a href="showShop">商店</a></li>
					<li><a href="${pageContext.request.contextPath}/noticeShowFront">公告</a></li>
		        </ul> <!-- End of /.nav-main -->
		    </div>	<!-- /.navbar-collapse -->
		</div>	<!-- /.container-fluid -->
	</nav>	<!-- End of /.nav -->




	<section id="topic-header">
		<div class="container">
			<div class="row">
				<div class="col-md-4">
					<h1>商店</h1>
					<p>A Bunch Of Products</p>
				</div>	<!-- End of /.col-md-4 -->
				<div class="col-md-8 hidden-xs">
					<ol class="breadcrumb pull-right">
					  	<li><a href="#">首页</a></li>
					  	<li class="active">商店</li>
					</ol>
				</div>	<!-- End of /.col-md-8 -->
			</div>	<!-- End of /.row -->
		</div>	<!-- End of /.container -->
	</section>	<!-- End of /#Topic-header -->



	<!-- PRODUCTS Start
    ================================================== -->

	<section id="shop">
		<div class="container">
			<fo  rm action="${pageContext.request.contextPath}/showShop" name="shop" method="post" enctype="multipart/form-data">

			<div class="row">
					<div class="col-md-9">
						<div class="products-heading">
							<h2>${foodType}</h2>
						</div>	<!-- End of /.Products-heading -->
						<div class="product-grid">
							<ul>
								<c:forEach items="${foods}" var="f">
									<li>
										<div class="products">
											<a href="${pageContext.request.contextPath}/showDetails?id=${f.id}">
												<img src="${f.imgurl}" alt="" width="220" height="220">
											</a>
											<a href="#">
												<h4>${f.name}</h4>
											</a>
											<p class="price">￥${f.price}</p>
											<div >
												<a class="view-link shutter" href="${pageContext.request.contextPath}/showAddCart?id=${f.id}">加入购物车</a>
											</div>
										</div>	<!-- End of /.products -->
									</li>
								</c:forEach>
							</ul>
						</div>	<!-- End of /.products-grid -->
						<div class="pagination-bottom">
							<ul class="pagination">
								<li><a>总共有${pageNum }页，当前是第${page }页</a></li>
								<li><a href="showShop?page=1">首页</a></li>
								<li><a href="showShop?page=${page-1}">上一页</a></li>
								<li><a href="showShop?page=${page+1}">下一页</a></li>
								<li><a href="showShop?page=${pageNum}">尾页</a></li>
							</ul>	<!-- End of /.pagination -->
						</div>
					<!-- Pagination -->


				</div>	<!-- End of /.col-md-9 -->
				<div class="col-md-3">
					<div class="blog-sidebar">
						<div class="block">
							<h4>其他菜系</h4>
							<div class="list-group">
								<a href="${pageContext.request.contextPath}/sortProduct?cate=荤菜" class="list-group-item">
									荤菜
								</a>
								<a href="${pageContext.request.contextPath}/sortProduct?cate=素菜" class="list-group-item">
									素菜
								</a>
								<a href="${pageContext.request.contextPath}/sortProduct?cate=套餐" class="list-group-item">
									套餐
								</a>
								<a href="${pageContext.request.contextPath}/sortProduct?cate=主食" class="list-group-item">
									主食
								</a>
							</div>
						</div>
						<div class="block">
							<img src="client/images/food-ad.png" alt="">
						</div>
						<div class="block">
							<h4>最新菜品</h4>
							<ul class="media-list">
								<c:forEach items="${newFoods}" var="f">
							 	<li class="media">
							    	<a class="pull-left" href="${pageContext.request.contextPath}/showDetails?id=${f.id}">
							      		<img class="media-object" src="${pageContext.request.contextPath}/${f.imgurl}" alt="...">
							    	</a>
							    	<div class="media-body">
							      		<a href="${pageContext.request.contextPath}/showDetails?id=${f.id}" class="media-heading">${f.name}</a>
							    	</div>
							  	</li>
								</c:forEach>
							  </ul>
						</div>

						<div class="block">
							<h4>食品标签</h4>
							<div class="tag-link">
								<a href="">美味</a>
								<a href="">健康</a>
								<a href="">绿色</a>
								<a href="">便捷</a>
								<a href="">满足</a>
							</div>
						</div>
				</div>	<!-- End of /.col-md-3 -->

		</div>	<!-- End of /.row -->

		</div>	<!-- End of /.container -->
			</form>

		</div>
	</section>	<!-- End of Section -->


	


	





<!-- FOOTER Start
    ================================================== -->

	<footer>
		<div class="container">
			<div class="row">
				<div class="col-md-4">
					<div class="block clearfix">
						<a href="#">
							<img src="client/images/footer-logo.png" alt="">
						</a>
						<p>
							Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s
						</p>
						<h4 class="connect-heading">联系我们</h4>
					</div>	<!-- End Of /.block -->
				</div> <!-- End Of /.Col-md-4 -->
				<div class="col-md-4">
					<div class="block">
						<h4>联系方式</h4>
						<p ><span>Food Address：</span>No. 1, Xueyuan road, Baiyun street, Liandu district, Lishui city, Zhejiang province</p>
						<p><span>Phone:</span> (+86) 6 5 2 7 3 0</p>

						<p><span>Mobile:</span> (+86) 17858922557</p>

						<p class="mail">Eamil: <span>749935037@qq.com</span></p>
					</div>	<!-- End Of /.block -->
				</div> <!-- End Of Col-md-3 -->
				<div class="col-md-4">
					<div class="block">
						<h4>即将推出的新品</h4>
						<div class="media">
						  	<a class="pull-left" href="#">
						    	<img class="media-object" src="client/images/product-item.jpg" alt="...">
						  	</a>
						  	<a class="pull-left" href="#">
						    	<img class="media-object" src="client/images/product-item.jpg" alt="...">
						  	</a>
						  	<a class="pull-left" href="#">
						    	<img class="media-object" src="client/images/product-item.jpg" alt="...">
						  	</a>
						  	<a class="pull-left" href="#">
						    	<img class="media-object" src="client/images/product-item.jpg" alt="...">
						  	</a>
						  	<a class="pull-left" href="#">
						    	<img class="media-object" src="client/images/product-item.jpg" alt="...">
						  	</a>
						  	<a class="pull-left" href="#">
						    	<img class="media-object" src="client/images/product-item.jpg" alt="...">
						  	</a>
						</div>	<!-- End Of /.media -->
					</div>	<!-- End Of /.block -->
				</div> <!-- End Of Col-md-3 -->
			</div> <!-- End Of /.row -->
		</div> <!-- End Of /.Container -->
		


	<!-- FOOTER-BOTTOM Start
    ================================================== -->

		<div class="footer-bottom">
			<div class="container">
				<div class="row">
					<div class="col-md-12">
						<ul class="cash-out pull-left">
							<li>
								<a href="#">
									<img src="client/images/American-Express.png" alt="">
								</a>
							</li>
							<li>
								<a href="#">
									<img src="client/images/PayPal.png" alt="">
								</a>
							</li>
							<li>
								<a href="#">
									<img src="client/images/Maestro.png" alt="">
								</a>
							</li>
							<li>
								<a href="#">
									<img src="client/images/Visa.png" alt="">
								</a>
							</li>
							<li>
								<a href="#">
									<img src="client/images/Visa-Electron.png" alt="">
								</a>
							</li>
						</ul>
						<p class="copyright-text pull-right">Food Code @2020 Designed by <a href="https://user.qzone.qq.com/749935037/main">team</a> All Rights Reserved</p>
					</div>	<!-- End Of /.col-md-12 -->	
				</div>	<!-- End Of /.row -->	
			</div>	<!-- End Of /.container -->	
		</div>	<!-- End Of /.footer-bottom -->
	</footer> <!-- End Of Footer -->
	
	<a id="back-top" href="#"></a>
</body>
</html>