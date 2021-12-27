<%-- 
    Document   : Home
    Created on : Sep 30, 2021, 11:43:40 PM
    Author     : ToneVn
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
    </head>
    <body>
		<jsp:include page="/common/header/header.jsp"></jsp:include>
			<!-- SECTION -->
			<div class="section">
				<!-- container -->
				<div class="container">
					<!-- row -->
					<div class="row">
						<!--section title -->
						<div class="col-md-12">
							<div class="section-title">
								<h3 class="title">Category</h3>
							</div>
						</div>
						<!--/section title -->

					<c:forEach items="${requestScope.categories}" var="categories">

						<div class="col-md-2 col-xs-6">
							<div class="shop">
								<div class="shop-img">
									<img src="./img/image_category/${categories.category_image}" alt="">
								</div>
								<div class="shop-body">
									<!--<h3>${categories.category_name}<br>Collection</h3>-->
									<h4>${categories.category_name}</h4>
									<a href="Category?category_id=${categories.category_id}" class="cta-btn"><i class="fa fa-arrow-circle-right"></i></a>
								</div>
							</div>
						</div>
						<!-- /shop -->	
					</c:forEach>

				</div>
				<!-- /row -->
			</div>
			<!-- /container -->
		</div>
		<!-- /SECTION -->


		<!-- SECTION -->
		<div class="section">
			<!-- container -->
			<div class="container">
				<!-- row -->
				<div class="row">

					<!--section title -->
					<div class="col-md-12">
						<div class="section-title">
							<h3 class="title">Product</h3>
							<!--							<div class="section-nav">
															<ul class="section-tab-nav tab-nav">
																<li class="active"><a data-toggle="tab" href="#tab2">Laptops</a></li>
																<li><a data-toggle="tab" href="#tab2">Smartphones</a></li>
																<li><a data-toggle="tab" href="#tab2">Cameras</a></li>
																<li><a data-toggle="tab" href="#tab2">Accessories</a></li>
															</ul>
														</div>-->
						</div>
					</div>
					<!--/section title -->

					<!-- Products tab & slick -->
					<div class="col-md-12">
						<div class="row">
							<div class="products-tabs">
								<!-- tab -->
								<div class="row" style="height: 100%">
									<c:forEach items="${requestScope.products}" var="product">
										<!-- product -->
										<div class="product col-md-3" style="margin-top: 55px">
											<div class="product-img">
												<a href="Product?product_id=${product.product_id}">
													<img style="width: 270px;height: 270px"  src="./img/image_product_thumbnail/${product.image_thumbnail}" alt="image">
												</a>
												<!--<div class="product-label">
													<span class="sale">-30%</span>
													<span class="new">NEW</span>
												</div>-->
											</div>
											<div class="product-body">
												<p class="product-category"><a href="Category?category_id=${product.category_id}">${product.category_id}</a></p>
												<h3 class="product-name"><a href="Product?product_id=${product.product_id}">${product.product_name}</a></h3>
												<h4 class="product-price"><fmt:formatNumber type="number" maxFractionDigits="2" value="${product.price}" /> VND</h4>
												
												<div class="product-rating">
													<i class="fa fa-star"></i>
													<i class="fa fa-star"></i>
													<i class="fa fa-star"></i>
													<i class="fa fa-star"></i>
													<i class="fa fa-star"></i>
												</div>
												<!--													<div class="product-btns">
																										<button class="add-to-wishlist"><i class="fa fa-heart-o"></i><span class="tooltipp">add to wishlist</span></button>
																										<button class="add-to-compare"><i class="fa fa-exchange"></i><span class="tooltipp">add to compare</span></button>
																										<button class="quick-view"><i class="fa fa-eye"></i><span class="tooltipp">quick view</span></button>
																									</div>-->
											</div>
											<form action="AddToCart" method="POST">
												<div class="add-to-cart">
													<button class="add-to-cart-btn" type="submit" name="btn_addToCart" value="${product.product_id}"><i class="fa fa-shopping-cart"></i> add to cart</button>
												</div>
											</form>
										</div>
										<!-- /product -->
									</c:forEach>


								</div>
								<!--<div id="slick-nav-1" class="products-slick-nav"></div>-->
							</div>
						</div>
					</div>
					<!-- Products tab & slick -->
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
