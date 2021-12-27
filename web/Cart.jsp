<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

		<title>Blank</title>

		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
		<!-- Google font -->
		<link href="https://fonts.googleapis.com/css?family=Montserrat:400,500,700" rel="stylesheet">

		<!-- Bootstrap -->
		<link type="text/css" rel="stylesheet" href="css/bootstrap.min.css"/>

		<!-- Slick -->
		<link type="text/css" rel="stylesheet" href="css/slick.css"/>
		<link type="text/css" rel="stylesheet" href="css/slick-theme.css"/>

		<!-- nouislider -->
		<link type="text/css" rel="stylesheet" href="css/nouislider.min.css"/>

		<!-- Font Awesome Icon -->
		<link rel="stylesheet" href="css/font-awesome.min.css">

		<!-- Custom stlylesheet -->
		<link type="text/css" rel="stylesheet" href="css/style.css"/>

		<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
		<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
		<!--[if lt IE 9]>
		  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
		  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
		<![endif]-->

    </head>
	<body>
		<jsp:include page="/common/header/header.jsp"></jsp:include>
			<!-- BREADCRUMB -->
			<div id="breadcrumb" class="section">
				<!-- container -->
				<div class="container">
					<!-- row -->
					<div class="row">
						<div class="col-md-12">
							<h3 class="breadcrumb-header">Cart</h3>
							<ul class="breadcrumb-tree">
								<li><a href="Home">Home</a></li>
								<li class="active">Cart</li>
							</ul>
						</div>
					</div>
					<!-- /row -->
				</div>
				<!-- /container -->
			</div>
			<!-- /BREADCRUMB -->
			<!-- SECTION -->
			<!--			<div class="section">
							 container 
							<div class="container">
								 row 
								<div class="row">
								</div>
								 /row 
							</div>
							 /container 
						</div>-->
			<!-- /SECTION -->

		<c:if test="${requestScope.message != null}">
			<div style="text-align:center">
				<hr>
				<span style="font-size: 20px; color: red;width: 100%;">
					${requestScope.message}
				</span>
				<hr>
			</div>
		</c:if>
		<div class="container">
			<div class="col-auto">
				<table class="table table-responsive">
					<thead>
						<tr>
							<!--<th scope="col">#</th>-->
							<th scope="col">ShopID</th>
							<th scope="col">ProductID</th>
							<th scope="col">Product name</th>								
							<th scope="col" style="text-align: right">Price</th>
							<th scope="col" style="width: 140px">Quantity</th>
							<th scope="col" style="text-align: right">Total</th>
							<th scope="col">Remove</th>
						</tr>
					</thead>
					<tbody>
					<form action="UpdateCart" method="POST">
						<c:set var="totalPrice" value="0" scope="page"/>
						<!-- start test-->
						<c:forEach items="${requestScope.htbShopCart}" var="shopCart">
							<tr>
								<td rowspan="${shopCart.value.size()}">
									${shopCart.key}
								</td>
								<c:forEach items="${shopCart.value}" var="v" varStatus="loop">										
									<td>${v.getProduct().getProduct_id()}</td>
									<td><a href="Product?product_id=${v.getProduct().getProduct_id()}">${v.getProduct().getProduct_name()}</a></td>
								<input  type="text" name="product_id" value="${v.getProduct().getProduct_id()}" hidden/>
								<td style="text-align: right"><fmt:formatNumber type="number" maxFractionDigits="2" value="${v.getProduct().getPrice()}"/></td>
								<td style="width: 140px"><input type="number" value="${v.getQuantity()}" name="quantity" style="width: 133.38px"/></td>
								<td style="text-align: right"><fmt:formatNumber type="number" maxFractionDigits="2" value="${v.getProduct().getPrice() * v.getQuantity()}"/></td>
								<c:set var="totalPrice" value="${totalPrice + v.getProduct().getPrice() * v.getQuantity()}" scope="page"/>
								<td>
									<a href="RemoveCart?Action=Remove&product_id=${v.getProduct().getProduct_id()}">Remove</a>
								</td>

								</tr>
							</c:forEach>
						</c:forEach>
						<!--end test-->
						<tr>
							<td colspan="4"></td>
							<td><span style="font-size: 15px;line-height: 28px;margin-left: 5px;color: #ee4d2d;">TOTAL: (vnd)</span></td>
							<td><span style="font-size: 18px;line-height: 28px;margin-left: 5px;color: #ee4d2d;"><fmt:formatNumber type="number" maxFractionDigits="2" value="${totalPrice}" /></span></td>
							<td style="width: 150px">
								<a href="RemoveCart?Action=RemoveAll">Remove All</a>
							</td>
						</tr>
						<tr>
							<td colspan="4"></td>
							<td>
								<input type="submit" value="Update Quantity"/>
							</td>
							<td></td>
							<td><a href="CheckOut">CheckOut</a></td>
						</tr>
					</form>
					</tbody>
				</table>


				<!--//////////////////////////////////////////////////////////////////////////////////// -->
				<!--//////////////////////////////////////////////////////////////////////////////////// -->
				<!--//////////////////////////////////////////////////////////////////////////////////// -->
				<!--//////////////////////////////////////////////////////////////////////////////////// -->
				<!--//////////////////////////////////////////////////////////////////////////////////// -->							



			</div>
		</div>

		<jsp:include page="/common/footer/footer.jsp"></jsp:include>

		<!-- jQuery Plugins -->
		<script src="js/jquery.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
		<script src="js/slick.min.js"></script>
		<script src="js/nouislider.min.js"></script>
		<script src="js/jquery.zoom.min.js"></script>
		<script src="js/main.js"></script>

	</body>
</html>
