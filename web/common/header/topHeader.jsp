<%-- Document : topHeader Created on : Oct 13, 2021, 12:17:57 PM Author : ToneVn
--%> <%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

        <title>top header</title>

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


        <style>
            body {
                font-family: 'Varela Round', sans-serif;
            }
            .modal-confirm {		
                color: #636363;
                width: 325px;
            }
            .modal-confirm .modal-content {
                padding: 20px;
                border-radius: 5px;
                border: none;
            }
            .modal-confirm .modal-header {
                border-bottom: none;   
                position: relative;
            }
            .modal-confirm h4 {
                text-align: center;
                font-size: 26px;
                margin: 30px 0 -15px;
            }
            .modal-confirm .form-control, .modal-confirm .btn {
                min-height: 40px;
                border-radius: 3px; 
            }
            .modal-confirm .close {
                position: absolute;
                top: -5px;
                right: -5px;
            }	
            .modal-confirm .modal-footer {
                border: none;
                text-align: center;
                border-radius: 5px;
                font-size: 13px;
            }	
            .modal-confirm .icon-box {
                color: #fff;		
                position: absolute;
                margin: 0 auto;
                left: 0;
                right: 0;
                top: -70px;
                width: 95px;
                height: 95px;
                border-radius: 50%;
                z-index: 9;
                background: #ef513a;
                padding: 15px;
                text-align: center;
                box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.1);
            }
            .modal-confirm .icon-box i {
                font-size: 56px;
                position: relative;
                top: 4px;
            }
            .modal-confirm.modal-dialog {
                margin-top: 80px;
            }
            .modal-confirm .btn {
                color: #fff;
                border-radius: 4px;
                background: #ef513a;
                text-decoration: none;
                transition: all 0.4s;
                line-height: normal;
                border: none;
            }
            .modal-confirm .btn:hover, .modal-confirm .btn:focus {
                background: #da2c12;
                outline: none;
            }
            .trigger-btn {
                display: inline-block;

            }
        </style>
    </head>
    <body>
        <div id="top-header">
            <div class="container">
				<ul class="header-links pull-left">
					<c:if test="${sessionScope.account==null}">
						<li><a href="Login" >Become a vendor</a></li>
						</c:if>
						<c:if test="${sessionScope.account!=null && accountShop==null}">
						<li><a href="#myModal" class="trigger-btn" data-toggle="modal">Become a vendor</a></li>
						</c:if>
						<c:if test="${sessionScope.account!=null && accountShop!=null}">
						<li><a href="VendorStore">Go to shop</a></li>
						</c:if>
				</ul>
                <div id="myModal" class="modal fade">
                    <div class="modal-dialog modal-confirm">
                        <div class="modal-content">

                            <div class="modal-body">
                                <p class="text-center">Để đăng ký bán hàng , bạn cần cung cấp một số thông tin cơ bản.</p>
                            </div>
                            <div class="modal-footer">
                                <button class="btn btn-danger btn-block" data-dismiss="modal" onclick="window.location.href = 'SignInShop'">Đăng kí</button>
                            </div>
                        </div>
                    </div>
                </div>
				<ul class="header-links pull-right">
					<c:if test="${sessionScope.account==null}">
                        <li><a href="Login" >Login</a></li>
						</c:if>
						<c:if test="${sessionScope.account!=null}">
                        <li><a href="AccountProfile" >My Profile</a></li>
						<li><a href="Logout" >Logout</a></li>
						</c:if>
                </ul>
            </div>
        </div>

    </body>
</html>
