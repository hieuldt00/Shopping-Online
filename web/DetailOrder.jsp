<%-- 
    Document   : DetailOrder
    Created on : Oct 30, 2021, 8:38:07 PM
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
        <link rel="stylesheet" href="css/font-awesome.min.css">
        <link rel="stylesheet" href="css/all.css">
        <link rel="stylesheet" href="webfonts">
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
							<a class=""  href="EditProfileShop.jsp"> Edit Profile</a>
                        </div>

                        <ul class="nav nav-pills nav-stacked">
                            <li ><a href="VendorStore"> <i class="fa fa-list"></i> My Product</a></li>
                            <li class="active"><a href="VendorOrder"> <i class="fa fa-shopping-cart"></i> My Order </a></li>
							<li><a href="VendorOrderCancelled"> <i class="fa fa-trash"></i> Order Cancelled </a></li>
                        </ul>
                    </div>
                </div>
                <div class="profile-info col-md-9">
                    <div class="row">
                        <div class="col-12"> 
                            <!-- Recent Order Table -->
                            <div class="card card-table-border-none" id="recent-orders">
                                <div class="card-header justify-content-between">
                                    <h2>Detail Orders</h2>

                                </div>
                                <div class="main-body">

                                    <div class="row gutters-sm">

                                        <div class="col-md-8">
                                            <div class="card mb-3">
                                                <div class="card-body">
                                                    <div class="row">
                                                        <div class="col-sm-3">
                                                            <h6 class="mb-0">Name</h6>
                                                        </div>
                                                        <div class="col-sm-9 text-secondary">
                                                            ${order.order_name}
                                                        </div>
                                                    </div>
                                                    <hr>                 
                                                    <hr>
                                                    <div class="row">
                                                        <div class="col-sm-3">
                                                            <h6 class="mb-0">Phone</h6>
                                                        </div>
                                                        <div class="col-sm-9 text-secondary">
                                                            ${order.order_phone}
                                                        </div>
                                                    </div>
                                                    <hr>

                                                    <hr>
                                                    <div class="row">
                                                        <div class="col-sm-3">
                                                            <h6 class="mb-0">Address</h6>
                                                        </div>
                                                        <div class="col-sm-9 text-secondary">
                                                            ${order.order_address}
                                                        </div>
                                                    </div>
                                                    <hr>

                                                </div>
                                            </div>





                                        </div>
                                    </div>

                                </div>
                                <div class="card-body pt-0 pb-5">
                                    <table class="table card-table table-responsive table-responsive-large" style="width:100%">
                                        <thead>
                                            <tr>
                                                <th>Product id</th>
                                                <th>Order Id</th>
                                                <th class="d-none d-md-table-cell">Quantity</th>
                                                <th class="d-none d-md-table-cell">Price</th>
                                                <th class="d-none d-md-table-cell">Total</th>

                                                <th></th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${listOD}" var="i">
                                                <tr>
                                                    <td><a href="Product?product_id=${i.product.product_id}" style="font-size: 18px">${i.product.product_name}</a></td>
                                                    <td>
                                                        ${i.order_id}
                                                    </td>
                                                    <td class="d-none d-md-table-cell">${i.quantity}</td>
                                                    <td class="d-none d-md-table-cell"><fmt:formatNumber type="number" maxFractionDigits="2" value="${i.price}"/></td>
                                                    <td class="d-none d-md-table-cell"><fmt:formatNumber type="number" maxFractionDigits="2" value="${i.price*i.quantity}"/></td>
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