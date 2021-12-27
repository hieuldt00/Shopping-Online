<%--
	Document   : EditProfileShop
	Created on : Nov 10, 2021, 12:28:17 PM
	Author 	: son
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

		<meta name="viewport" content="width=device-width, initial-scale=1">
		<script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
		<link href="https://netdna.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css" rel="stylesheet">
		<script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>

	</head>
	<body>
		<div class="container bootstrap snippets bootdey">
			<div class="row">
				<div class="profile-nav col-md-3">
					<div class="panel">
						<div class="user-heading round">
							<a href="#">
								<img src="./img/image_shop/${shop.shop_image}" alt="image_shop">
							</a>
							<h1>${shop.shop_name}</h1>
							<a class="nav-link text-white add-button" href="EditProfile"> Edit Profile</a>
						</div>

						<ul class="nav nav-pills nav-stacked">
							<li class="active"><a href="VendorStore"> <i class="fa fa-list"></i> My Product</a></li>
							<li><a href="VendorOrder"> <i class="fa fa-shopping-cart"></i> My Order </a></li>
							<li><a href="VendorOrderCancelled"> <i class="fa fa-trash"></i> Order Cancelled </a></li>
						</ul>
					</div>
				</div>
				<div class="profile-info col-md-9">
					<div class="row">
						<div class="col-12">
							<!-- Recent Order Table -->
							<p>${message}</p>
							<div class="tab-content">
								<div role="tabpanel" class="tab-pane active" id="home">
									<form action="EditProfileShop" method="post" enctype="multipart/form-data">
										<div class="form-group">
											<label for="inputName">Name Shop</label>
											<input type="text" name="nameshop" value="${shop.shop_name}" class="form-control" id="inputName" placeholder="Name" required="">
										</div>

										<div class="form-group">
											<label for="exampleInputFile">Image Shop</label>
											<input type="file" id="exampleInputFile" name="image">

										</div>

										<button type="submit" class="btn btn-default">Submit</button>
									</form>
								</div>

							</div>
						</div>
					</div>
				</div>
			</div>
		</div>     	 
	</body>
	<style type="text/css">
		body {
			color: #000;
			background: #ffffff;
			font-family: 'Open Sans', sans-serif;
			padding: 0px !important;
			margin: 0px !important;
			font-size: 13px;
			text-rendering: optimizeLegibility;
			-webkit-font-smoothing: antialiased;
			-moz-font-smoothing: antialiased;
		}

		.profile-nav, .profile-info{
			margin-top:30px;   
		}

		.profile-nav .user-heading {
			background: #f8f7f5;
			color: #000;
			border-radius: 4px 4px 0 0;
			-webkit-border-radius: 4px 4px 0 0;
			padding: 30px;
			text-align: center;
		}

		.profile-nav .user-heading.round a  {
			border-radius: 50%;
			-webkit-border-radius: 50%;
			border: 10px solid rgba(255,255,255,0.3);
			display: inline-block;
		}

		.profile-nav .user-heading a img {
			width: 112px;
			height: 112px;
			border-radius: 50%;
			-webkit-border-radius: 50%;
		}

		.profile-nav .user-heading h1 {
			font-size: 22px;
			font-weight: 300;
			margin-bottom: 5px;
		}

		.profile-nav .user-heading p {
			font-size: 12px;
		}

		.profile-nav ul {
			margin-top: 1px;
		}

		.profile-nav ul > li {
			border-bottom: 1px solid #ebeae6;
			margin-top: 0;
			line-height: 30px;
		}

		.profile-nav ul > li:last-child {
			border-bottom: none;
		}

		.profile-nav ul > li > a {
			border-radius: 0;
			-webkit-border-radius: 0;
			color: #89817f;
			border-left: 5px solid #fff;
		}

		.profile-nav ul > li > a:hover, .profile-nav ul > li > a:focus, .profile-nav ul li.active  a {
			background: #f8f7f5 !important;
			border-left: 5px solid #ff3333;
			color: #89817f !important;
		}

		.profile-nav ul > li:last-child > a:last-child {
			border-radius: 0 0 4px 4px;
			-webkit-border-radius: 0 0 4px 4px;
		}

		.profile-nav ul > li > a > i{
			font-size: 16px;
			padding-right: 10px;
			color: #bcb3aa;
		}

		.r-activity {
			margin: 6px 0 0;
			font-size: 12px;
		}


		.p-text-area, .p-text-area:focus {
			border: none;
			font-weight: 300;
			box-shadow: none;
			color: #c3c3c3;
			font-size: 16px;
		}

		.profile-info .panel-footer {
			background-color:#f8f7f5 ;
			border-top: 1px solid #e7ebee;
		}

		.profile-info .panel-footer ul li a {
			color: #7a7a7a;
		}


		.profile-activity h5 {
			font-weight: 300;
			margin-top: 0;
			color: #c3c3c3;
		}

	</style>
</html>


