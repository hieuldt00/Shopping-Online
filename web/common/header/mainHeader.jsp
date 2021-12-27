<%-- Document : mainHeader Created on : Oct 13, 2021, 12:32:14 PM Author :
ToneVn --%> <%@page import="model.Category"%> <%@page
	import="java.util.ArrayList"%> <%@page import="dal.DAOCategory"%> <%@page
		contentType="text/html" pageEncoding="UTF-8"%>
		<!DOCTYPE html>
		<html>
			<head>
				<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
				<meta http-equiv="X-UA-Compatible" content="IE=edge" />
				<meta name="viewport" content="width=device-width, initial-scale=1" />
				<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

				<!-- Google font -->
				<link
					href="https://fonts.googleapis.com/css?family=Montserrat:400,500,700"
					rel="stylesheet"
					/>

				<!-- Bootstrap -->
				<link type="text/css" rel="stylesheet" href="css/bootstrap.min.css" />

				<!-- Slick -->
				<link type="text/css" rel="stylesheet" href="css/slick.css" />
				<link type="text/css" rel="stylesheet" href="css/slick-theme.css" />

				<!-- nouislider -->
				<link type="text/css" rel="stylesheet" href="css/nouislider.min.css" />

				<!-- Font Awesome Icon -->
				<link rel="stylesheet" href="css/font-awesome.min.css" />

				<!-- Custom stlylesheet -->
				<link type="text/css" rel="stylesheet" href="css/style.css" />
				<title>main header</title>
			</head>
			<body>
				<!-- MAIN HEADER -->
				<div id="header">
					<!-- container -->
					<div class="container">
						<!-- row -->
						<div class="row" style="display: flex;align-items: center">
							<!-- LOGO -->
							<div class="col-md-3">
								<div class="header-logo">
									<a href="Home" class="logo">
										<img src="./img/logo.png" alt="" />
									</a>
								</div>
							</div>
							<!-- /LOGO -->

							<!-- SEARCH BAR -->
							<div class="col-md-6">
								<div class="header-search">
									<form action="Search" method="POST">
										<input type="checkbox" hidden="hidden" name="cbCategory" />
										<input
											class="input"
											name="name"
											value="${name}"
											placeholder="What do you looking for?"
											/>
										<input hidden="hidden" name="from" />
										<input hidden="hidden" name="to" />
										<button class="search-btn">Search</button>
									</form>
								</div>
							</div>
							<!-- /SEARCH BAR -->

							<!-- ACCOUNT -->
							<div class="col-md-3 clearfix">
								<div class="header-ctn">
									<!-- Wishlist -->
									<!--								<div>
																														  <a href="#">
																																  <i class="fa fa-heart-o"></i>
																																  <span>Your Wishlist</span>
																																  <div class="qty">2</div>
																														  </a>
																												  </div>
																												   /Wishlist -->

									<!-- Cart -->
									<div class="dropdown">
										<!--                <a
														  class="dropdown-toggle"
														  data-toggle="dropdown"
														  aria-expanded="true"
														>-->
										<a href="AddToCart">
											<img src="img/shopping-cart.png">
										</a>
										<div class="cart-dropdown">
											<div class="cart-list">
											</div>

										</div>
									</div>
									<!-- /Cart -->

									<!-- Menu Toogle -->
									<div class="menu-toggle">
										<a href="#">
											<i class="fa fa-bars"></i>
											<span>Menu</span>
										</a>
									</div>
									<!-- /Menu Toogle -->
								</div>
							</div>
							<!-- /ACCOUNT -->
						</div>
						<!-- row -->
					</div>
					<!-- container -->
				</div>
				<!-- /MAIN HEADER -->
				<script src="js/jquery.min.js"></script>
				<script src="js/bootstrap.min.js"></script>
				<script src="js/slick.min.js"></script>
				<script src="js/nouislider.min.js"></script>
				<script src="js/jquery.zoom.min.js"></script>
				<script src="js/main.js"></script>
			</body>
		</html>
