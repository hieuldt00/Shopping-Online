<%-- 
    Document   : OrderCancelled
    Created on : Nov 8, 2021, 8:35:18 PM
    Author     : son
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
		<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
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
							<li ><a href="VendorStore"> <i class="fa fa-list"></i> My Product</a></li>
							<li class=""><a href="VendorOrder"> <i class="fa fa-shopping-cart"></i> My Order </a></li>
							<li class="active"><a href="VendorOrder"> <i class="fa fa-trash"></i> Order Cancelled </a></li>
						</ul>
					</div>
				</div>
				<div class="profile-info col-md-9">
					<div class="row">
						<div class="col-12"> 
							<!-- Recent Order Table -->
							<div class="card card-table-border-none" id="recent-orders">
								<div class="card-header justify-content-between">
									<h2>Order Cancelled</h2>

								</div>
								<div class="card-body pt-0 pb-5">
									<table class="table card-table table-responsive table-responsive-large" style="width:100%">
										<thead>
											<tr>
												<th>Order ID</th>
												<th>Customer Name</th>
												<th class="d-none d-md-table-cell">Phone</th>
												<th class="d-none d-md-table-cell">Order Date</th>
												<th class="d-none d-md-table-cell">Order Cost</th>
												<th>Status</th>
												<th></th>
											</tr>
										</thead>
										<tbody>





											<c:forEach items="${listO}" var="i">
												<tr>
													<td >${i.order_id}</td>
													<td class="d-none d-md-table-cell">
														${i.order_name}
													</td>
													<td class="d-none d-md-table-cell">${i.order_phone}</td>
													<td class="d-none d-md-table-cell">${i.dateCreate}</td>
													<td class="d-none d-md-table-cell"><fmt:formatNumber type="number" maxFractionDigits="2" value="${i.total}"/></td>
													<td >
														<c:if test="${i.status==1}">
															<span class="badge badge-success" style="background-color: greenyellow">Completed</span>

														</c:if>
														<c:if test="${i.status==-1}">
															<span class="badge badge-danger" style="background-color: red">Cancelled</span>

														</c:if>
														<c:if test="${i.status==0}">
															<span class="badge badge-warning" style="background-color: yellow">On Process</span>

														</c:if>
													</td>
													<td class="action" data-title="Action">
														<div class="">
															<ul class="list-inline justify-content-center">

																<li class="list-inline-item">
																	<a class="edit" data-toggle="tooltip" data-placement="top" title="completed" href="VendorStatus?status=1&order_id=${i.order_id}">
																		<i class="fa fa-check" style="color: red"></i>
																	</a>
																</li>
																<li class="list-inline-item">
																	<a class="delete" data-toggle="tooltip" data-placement="top" title="On Process" href="VendorStatus?status=0&order_id=${i.order_id}">
																		<img src="https://img.icons8.com/material-outlined/24/000000/process.png" style="background-image: red">
																	</a>
																</li>
																<li class="list-inline-item">
																	<a class="delete" data-toggle="tooltip" data-placement="top" title="View Detail" href="VendorOrderDetail?order_id=${i.order_id}">
																		<i class="fa fa-align-justify" style="color: red"></i>
																	</a>
																</li>

																<li class="list-inline-item">
																	<a class="delete" data-toggle="tooltip" data-placement="top" title="Delete" href="#" onclick="mess(${i.order_id})">
																		<i class="fa fa-trash" style="color: red"></i>
																	</a>
																</li>
															</ul>
														</div>
													</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
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

			<script type="text/javascript">

			</script>
	</body>
</html>

