<%-- 
    Document   : Category
    Created on : Oct 1, 2021, 6:45:30 AM
    Author     : ToneVn
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Category</title>
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
							<ul class="breadcrumb-tree">							
								<h3 class="breadcrumb-header">Category</h3>
								<li><a href="Home">Home</a></li>
								<li class="active">Category</li>
							</ul>
						</div>
					</div>
					<!-- /row -->
				</div>
				<!-- /container -->
			</div>
			<!-- /BREADCRUMB -->
			<!-- SECTION -->
			<div class="section">
				<!-- container -->
				<div class="container">
					<!-- row -->
					<div class="row">

						<!--section title -->
						<div class="col-md-12">
							<div class="section-title">
							<c:if test="${requestScope.categories != null}">
								<h3 class="title">All Product</h3>
							</c:if>
							<c:if test="${requestScope.category != null}">
								<h3 class="title">${requestScope.category.category_name} Product</h3>
							</c:if>
						</div>
					</div>
				</div>
				<!--/section title -->
				<%--<c:choose>--%>
				<%--<c:when test="${requestScope.products != null}">--%>						
				<!-- Products tab & slick -->
				<div class="col-md-12">
					<div class="row">
						<c:forEach items="${requestScope.products}" var="products">

							<!-- product -->
							<div class="product col-md-3">
								<div class="product-img">
									<img style="width: 270px;height: 270px" src="./img/image_product_thumbnail/${products.image_thumbnail}" alt="image">													
								</div>
								<div class="product-body">
									<p class="product-category"><a href="Category?category_id=${products.category_id}">${products.category_id}</a></p>
									<h3 class="product-name"><a href="Product?product_id=${products.product_id}">${products.product_name}</a></h3>
									<h4 class="product-price"><fmt:formatNumber type="number" maxFractionDigits="2" value="${products.price}" /> VND</h4>
									<div class="product-rating">
										<i class="fa fa-star"></i>
										<i class="fa fa-star"></i>
										<i class="fa fa-star"></i>
										<i class="fa fa-star"></i>
										<i class="fa fa-star"></i>
									</div>
								</div>
								<form action="AddToCart" method="POST">
									<div class="add-to-cart">
										<button class="add-to-cart-btn" type="submit" name="btn_addToCart" value="${products.product_id}"><i class="fa fa-shopping-cart"></i> add to cart</button>
									</div>
								</form>
							</div>
							<!-- /product -->
						</c:forEach>
					</div>
				</div>
				<!-- Products tab & slick -->
				<%--</c:when>--%>
				<%--<c:otherwise>--%>
				<!--There is no product of this category-->
				<%--</c:otherwise>--%>
				<%--</c:choose>--%>
			</div>
			<!-- /row -->
		</div>
		<!-- /container -->
	</div>
	<jsp:include page="/common/footer/footer.jsp"></jsp:include>
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/slick.min.js"></script>
	<script src="js/nouislider.min.js"></script>
	<script src="js/jquery.zoom.min.js"></script>
	<script src="js/main.js"></script>
</body>
</html>
