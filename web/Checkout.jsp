<%-- 
    Document   : checkout
    Created on : Oct 13, 2021, 12:02:19 PM
    Author     : ToneVn
--%>

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

		<title>Checkout</title>

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
							<h3 class="breadcrumb-header">Checkout</h3>
							<ul class="breadcrumb-tree">
								<li><a href="Home">Home</a></li>
								<li class="active">Checkout</li>
							</ul>
						</div>
					</div>
					<!-- /row -->
				</div>
				<!-- /container -->
			</div>
			<!-- /BREADCRUMB -->

			<div class="container">
				<div class="col-auto">
					<table class="table table-responsive">
						<thead>
							<tr>
								<!--<th scope="col">#</th>-->
								<th scope="col">ShopID</th>
								<!--<th scope="col">Product ID</th>-->
								<th scope="col">Product name</th>								
								<th scope="col" style="text-align: right">Price</th>
								<th scope="col" style="text-align: right">Quantity</th>
								<th scope="col" style="text-align: right">Total</th>
							</tr>
						</thead>
						<tbody>


						<c:set var="totalPrice" value="0" scope="page"/>
						<c:forEach items="${requestScope.htbShopCart}" var="shopCart">
							<tr>
								<td rowspan="${shopCart.value.size()}">
									${shopCart.key}
								</td>
								<c:forEach items="${shopCart.value}" var="v" varStatus="status">										


									<td><a href="Product?product_id=${v.getProduct().getProduct_id()}">${v.getProduct().getProduct_name()}</a></td>
									<td style="text-align: right"><fmt:formatNumber type="number" maxFractionDigits="2" value="${v.getProduct().getPrice()}"/></td>
									<td style="text-align: right">${v.getQuantity()}</td>
									<td style="text-align: right"><fmt:formatNumber type="number" maxFractionDigits="2" value="${v.getProduct().getPrice() * v.getQuantity()}"/></td>
									<c:set var="totalPrice" value="${totalPrice + v.getProduct().getPrice() * v.getQuantity()}" scope="page"/>
								</tr>
							</c:forEach>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>


		<!-- SECTION -->
		<div class="section">
			<!-- container -->
			<div class="container">
				<!-- row -->
				<div class="row">

					<form action="CheckOut" method="POST">
						<div class="col-md-7">
							<!-- Billing Details -->
							<div class="section-title text-center">
								<h3 class="title">Customer shipping information</h3>
							</div>
							<div class="billing-details">
								<c:set var="account" value="${sessionScope.account}" scope="page"/>
								<div class="form-group">
									<div class="order-col">
										<div><strong>Customer name</strong></div>
									</div>
									<input style="border: 1px solid black;margin-top: 5px" class="input" type="text" name="order_name" value="${account.getAccount_name()}">
								</div>
								<div class="form-group">
									<div class="order-col">
										<div><strong>Customer phone</strong></div>
									</div>
									<input style="border: 1px solid black;margin-top: 5px" class="input" type="text" name="order_phone" value="${account.getAccount_phone()}">
								</div>
								<div class="form-group">
									<div class="order-col">
										<div><strong>Customer address</strong></div>
									</div>
									<input style="border: 1px solid black;margin-top: 5px" class="input" type="text" name="order_address" value="${account.getAccount_address()}">
								</div>							
							</div>
							<!-- /Billing Details -->

						</div>

						<!-- Order Details -->
						<div class="col-md-5 order-details">
							<div class="section-title text-center">
								<h3 class="title">Your Order</h3>
							</div>
							<div class="order-summary">
								<div class="order-col">
									<div><strong>PRODUCT</strong></div>
									<div><strong>TOTAL</strong></div>
								</div>
								<div class="order-products">
									<c:forEach items="${requestScope.htbShopCart}" var="shopCart">
										<c:forEach items="${shopCart.value}" var="v" varStatus="status">	
											<div class="order-col">
												<div><strong>${v.getQuantity()}</strong> x ${v.getProduct().getProduct_name()}</div>
												<div> <strong><fmt:formatNumber type="number" maxFractionDigits="2" value="${v.getProduct().getPrice()}"/></strong></div>
											</div>
										</c:forEach>
									</c:forEach>
								</div>
								<div class="order-col">
									<div><strong>TOTAL</strong></div>
									<div><strong class="order-total"> <fmt:formatNumber type="number" maxFractionDigits="2" value="${requestScope.total}"/></strong></div>
								</div>
							</div>
							<input style="width: 100%" type="submit" value="Place Order" class="primary-btn"/>
							<!--<a href="#" class="primary-btn order-submit">Place order</a>-->
						</div>
						<input type="hidden" value="${requestScope.htbShopCart}" name="htbShopCart"/>
					</form>

					<!-- /Order Details -->
				</div>
				<!-- /row -->
			</div>
			<!-- /container -->
		</div>
		<!-- /SECTION -->


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

