<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<!--  This file has been downloaded from bootdey.com @bootdey on twitter -->
		<!--  All snippets are MIT license http://bootdey.com/license -->

		<meta name="viewport" content="width=device-width, initial-scale=1">
		<script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
		<link href="https://netdna.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css" rel="stylesheet">
		<script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
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
								<h3 class="breadcrumb-header">My Profile</h3>
								<li><a href="Home">Home</a></li>
								<li class="active">My Profile</li>
							</ul>
						</div>
					</div>
					<!-- /row -->
				</div>
				<!-- /container -->
			</div>
			<!-- /BREADCRUMB -->
			<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
			<div class="container bootstrap snippets bootdey">
				<div class="row">
					<div class="profile-nav col-md-3">
						<div class="panel">
							<div class="user-heading round">
								<a href="#">
									<img src="img/image_account/${accountprofile.account_image}" alt="">    
							</a>
							<h1>${sessionScope.account.account_name}</h1>

						</div>

						<ul class="nav nav-pills nav-stacked">
							<li class="active"><a href="AccountProfile"> <i class="fa fa-list"></i> My Profile</a></li>
							<li ><a href="Edit"> <i class="fa fa-list"></i> Edit My Profile</a></li>
							<li><a href="Order"> <i class="fa fa-shopping-cart"></i> My Order </a></li>
							<li ><a href="ChangePassword"> <i class="fa fa-list"></i> Change Password </a></li>

						</ul>
					</div>
				</div>
				<div class="profile-info col-md-9">
					<div class="widget dashboard-container my-adslist">
						<div class="row mt-2">
							<div class="col-md-12"><label class="labels" style="width: 120px">Name: </label><span style="font-size: 15px">${accountprofile.account_name}</span></div>
							<div class="col-md-6" ><input type="hidden" class="form-control" placeholder=" name" name="account_id" value="${accountprofile.account_id}" readonly></div>
						</div>
						<div class="row mt-3">
							<div class="col-md-12"><label class="labels" style="width: 120px">Phone Number: </label><span style="font-size: 15px">${accountprofile.account_phone}</span></div>
							<div class="col-md-12"><label class="labels" style="width: 120px">Address: </label><span style="font-size: 15px">${accountprofile.account_address}</span></div>
							<div class="col-md-12"><label class="labels" style="width: 120px">Email: </label><span style="font-size: 15px">${accountprofile.email}</span></div>
							<div class="col-md-12"><label class="labels" style="width: 120px">Date of birth: </label><span style="font-size: 15px">${accountprofile.dob}</span></div>
							<div class="col-md-12"><label class="labels" style="width: 120px">Gender:</label>
								<c:if test="${accountprofile.gender == true}">
									<span style="font-size: 15px">Male</span>
								</c:if>
								<c:if test="${accountprofile.gender == false}">
									<span style="font-size: 15px">Female</span><
								</c:if>
							</div>
						</div>
					</div>
				</div>
			</div>

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
				@media (max-width: 480px) {
					.nav-pills .nav-link text-white add-button {
						font-size: 12px;
					}
				}
				.nav-link {
					display: block;
					padding: .5rem 1rem;
				}
				.add-button {
					background: #ff3333;
					color: #fff;
					padding: 8px 20px !important;
					display: inline-block;
				}
				.profile-nav .user-heading {
					background: #f8f7f5;
					color: #000;
					border-radius: 4px 4px 0 0;
					-webkit-border-radius: 4px 4px 0 0;
					padding: 30px;
					text-align: center;
				}
				.navbar-nav .nav-link {
					padding-right: 0;
					padding-left: 0;
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
				.text-white {
					color: #fff !important;
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

			<script type="text/javascript">

			</script>
	</body>
</html>