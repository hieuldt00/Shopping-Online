<%-- 
    Document   : SignInToBecomeVendor
    Created on : Oct 27, 2021, 11:17:23 PM
    Author     : son
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Bootstrap Simple Contact Form</title>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round|Open+Sans">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
        <style>
            body {
                color: #566787;
                background: #f5f5f5;
                font-family: "Open Sans", sans-serif;
            }
            .contact-form {
                padding: 50px;
                margin: 30px 0;
            }
            .contact-form h1 {
                text-transform: uppercase;
                margin: 0 0 15px;
            }
            .contact-form .form-control, .contact-form .btn  {
                min-height: 38px;
                border-radius: 2px;
            }
            .contact-form .btn-primary {
                min-width: 150px;
                background: #299be4;
                border: none;
            }
            .contact-form .btn-primary:hover {
                background: #1c8cd7; 
            }
            .contact-form label {
                opacity: 0.9;
            }
            .contact-form textarea {
                resize: vertical;
            }
            .hint-text {
                font-size: 15px;
                padding-bottom: 15px;
                opacity: 0.8;
            }
            .bs-example {
                margin: 20px;
            }
            .contact-modal   {
                min-height: 38px;
                border-radius: 1px;
                outline: none;
                border-radius: 2px;
                transition: all 0.5s;
            }
            .contact-modal .form-group {
                border-radius: 1px;
            }
        </style>
    </head>
    <body>
        <div class="container-xl">
            <div class="row">
                <div class="col-md-8 mx-auto">
                    <div class="contact-form contact-modal" >
                        <h1>Thông tin cửa hàng</h1>
                        <p style="background-color: red">${message}</p>
                           <p>${message2}</p>
                        <form action="SignInShop" method="post">

                                 
                            <div class="form-group">
                                <label for="inputFirstName"> Tên Shop</label>
                                <input type="text" class="form-control" name="nameShop" required>
                            </div>


                            <div class="form-group">
                                <label for="inputLastName">Email</label>
                                <c:if test="${Email==null}">
                                <input type="text" class="form-control" name="Email" required>
                                </c:if>
                                <c:if test="${Email !=null}">
                                <input type="text" class="form-control" name="Email" value="${Email}"  disabled>
                                </c:if>
                            </div>
                            <div class="form-group">
                                <label for="inputEmail">Số điện thoại</label>
                               <c:if test="${Sdt==null}">
                                <input type="text" class="form-control" name="Sdt"  required>
                                </c:if>
                                <c:if test="${Sdt !=null}">
                                <input type="text" class="form-control" name="Sdt"  value="${Sdt}"  disabled>
                                </c:if>
                            </div>

                            <input type="submit" class="btn btn-primary" value="Lưu" style="background-color: red">
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
