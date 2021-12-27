

<%-- 
    Document   : store
    Created on : Oct 13, 2021, 12:05:28 PM
    Author     : ToneVn
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en" dir="ltr">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

        <title>Search</title>

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
        <style>
            li.disabled{
                pointer-events: none;
            }
            .store-pagination {
                position: relative;
                margin-top: 150px;
                float: right;
            }

            .store-pagination li {
                display: inline-block;
                width: 40px;
                height: 40px;
                line-height: 40px;
                text-align: center;
                background-color: #FFF;
                border: 1px solid #E4E7ED;
                -webkit-transition: 0.2s all;
                transition: 0.2s all;
            }

            .store-pagination li+li {
                margin-left: 5px;
            }

            .store-pagination li:hover {
                background-color: #E4E7ED;
                color: #D10024;
            }

            .store-pagination li.active {
                background-color: #D10024;
                border-color: #D10024;
                color: #FFF;
                font-weight: 500;
                cursor: default;
            }

            .store-pagination li a {
                display: block;
            }

            .store-qty {
                margin-top: 160px;
                margin-left: 30px;
                font-weight: 500;
                text-transform: uppercase;
                font-size: 14px;
            }

        </style>
    </head>
    <body>
        <jsp:include page="/common/header/header.jsp"></jsp:include>

            <!-- NAVIGATION -->
            <nav id="navigation">
                <!-- container -->
                <div class="container">
                    <!-- responsive-nav -->
                    <div id="responsive-nav">
                        <!-- NAV -->
                        <ul class="main-nav nav navbar-nav">
                            <li class="active"><a href="#">Home</a></li>
                            <li><a href="#">Hot Deals</a></li>
                            <li><a href="#">Categories</a></li>
                            <li><a href="#">Laptops</a></li>
                            <li><a href="#">Smartphones</a></li>
                            <li><a href="#">Cameras</a></li>
                            <li><a href="#">Accessories</a></li>
                        </ul>
                        <!-- /NAV -->
                    </div>
                    <!-- /responsive-nav -->
                </div>
                <!-- /container -->
            </nav>
            <!-- /NAVIGATION -->

            <!-- BREADCRUMB -->
            <div id="breadcrumb" class="section">
                <!-- container -->
                <div class="container">
                    <!-- row -->
                    <div class="row">
                        <div class="col-md-12">
                            <ul class="breadcrumb-tree">
                                <li><a href="#">Home</a></li>
                                <li class="active"><a  href="#">All Categories</a></li>
                                <li><a href="#">Accessories</a></li>
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

                        <form action="Search" method="POST">
                            <input type="text" hidden=""  id="kw" name="name" value=${name}>
                        <input type="number" name="minPrice" hidden="hidden" id="minPrice" value="${minPrice}">
                        <input type="number" name="maxPrice" hidden="hidden" id="maxPrice" value="${maxPrice}">
                        <input type="number" name="minPrice" hidden="hidden" id="currentMin" value="${from}">
                        <input type="number" name="maxPrice" hidden="hidden" id="currentMax" value="${to}">
                        <!-- ASIDE -->
                        <div id="aside" class="col-md-3">
                            <!-- aside Widget -->
                            <div class="aside">
                                <h3 class="aside-title">Categories</h3>
                                <div class="checkbox-filter">

                                    <c:forEach items="${requestScope.categories}" var="category">
                                        <div id="checkboxCat" class="input-checkbox">
                                            <input name="cbCategory" type="checkbox"
                                                   <c:forEach items="${requestScope.arrayCateID}" var="arrayCID">
                                                       <c:if test="${arrayCID==category.getCategory_id()}">
                                                           checked="checked"
                                                       </c:if>
                                                   </c:forEach>
                                                   value="${category.getCategory_id()}" id="${category.getCategory_id()}">
                                            <label for="${category.getCategory_id()}">
                                                <span></span>
                                                ${category.getCategory_name()}
                                                <small>(${category.getStatus()})</small>
                                            </label>
                                        </div>
                                    </c:forEach>
                                </div>

                            </div>
                            <!-- /aside Widget -->

                            <!-- aside Widget -->
                            <div class="aside">
                                <h3 class="aside-title">Price</h3>
                                <div class="price-filter">
                                    <div id="price-slider" ></div>
                                    <div class="input-number price-min">
                                        <input id="price-min" type="number" name="from" value="${from}" >
                                        <span class="qty-up">+</span>
                                        <span class="qty-down">-</span>

                                    </div>
                                    <span>-</span>
                                    <div class="input-number price-max">
                                        <input id="price-max" type="number" name="to" value="${to}">
                                        <span class="qty-up">+</span>
                                        <span class="qty-down">-</span>
                                    </div>
                                </div>
                            </div>
                            <!-- /aside Widget -->

                            <!-- aside Widget -->
                            <div class="aside">
                                <div class="price-filter">
                                    <button id="filter-submit" class="btn btn-success">Apply</button>
                                </div>
                            </div>
                            <!-- /aside Widget -->
                        </div>
                        <!-- /ASIDE -->
                    </form>

                    <!-- STORE -->
                    <div style="height: 1012px;" id="store" class="col-md-9 store">
                        <!-- store top filter -->
                        <div class="store-filter clearfix">
                            <div class="store-sort">
                                <label>
                                    Sort By:
                                    <select id="input-select" class="input-select">
                                        <option value="asc">Ascent Price </option>
                                        <option value="desc">Descent Price </option>
                                    </select>
                                </label>

                            </div>

                        </div>
                        <!-- /store top filter -->
                        <!-- store products -->
                        <div style="display: flex;flex-wrap: wrap;" id="row-product" class="row-product">
                            ${fail}

                            <!-- product 
                            <c:forEach items="${lp}" var="product">
                                <!--                                                       col-xs-6 col-sm-3     -->
                                <div class="col-sm-4">
                                    <div class="product">
                                        <div class="product-img">
                                            <img src="./img/product01.png" alt="">
                                            <div class="product-label">
                                                <span class="sale">-30%</span>
                                                <span class="new">NEW</span>
                                            </div>
                                        </div>
                                        <div class="product-body">
                                            <p class="product-category">${product.getCategory_id()}</p>
                                            <h3 class="product-name"><a href="Product?product_id=${product.getCategory_id()}">${product.getProduct_name()}</a></h3>
                                            <h4 class="product-price">${product.getPrice()}$</h4>
                                            <div class="product-rating">
                                                <i class="fa fa-star"></i>
                                                <i class="fa fa-star"></i>
                                                <i class="fa fa-star"></i>
                                                <i class="fa fa-star"></i>
                                                <i class="fa fa-star"></i>
                                            </div>
                                            <div class="product-btns">
                                                <button class="add-to-wishlist"><i class="fa fa-heart-o"></i><span class="tooltipp">add to wishlist</span></button>
                                                <button class="add-to-compare"><i class="fa fa-exchange"></i><span class="tooltipp">add to compare</span></button>
                                                <button class="quick-view"><i class="fa fa-eye"></i><span class="tooltipp">quick view</span></button>
                                            </div>
                                        </div>
                                        <div class="add-to-cart">
                                            <button class="add-to-cart-btn"><i class="fa fa-shopping-cart"></i> add to cart</button>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                            <!-- /product -->

                        </div>

                    </div>
                    <!-- /store products -->

                    <!-- store bottom filter -->
                    <div id="pagingnumber" class="store-filter clearfix">
                        <span class="store-qty storepage store-pagination">You are in <b>${page}</b> of ${maxPage} total page</span>
                        <ul class="store-pagination storepage">
                            <li class="page-item ${page-1>1?'':'disabled'}"  ><a class="page-link" href="" >Pre</a></li>
                                <c:if test="${page-2>0}">
                                <li class="page-item"><a class="page-link" href="">${page-2}</a></li>
                                </c:if>
                                <c:if test="${page-1>0}">
                                <li class="page-item"><a class="page-link" href="">${page-1}</a></li>
                                </c:if>
                            <li class="page-item active"><a class="page-link" href="">${page}</a></li>
                                <c:if test="${page+1<=maxPage}">
                                <li class="page-item"><a class="page-link" href="">${page+1}</a></li>
                                </c:if>
                                <c:if test="${page+2<=maxPage}">
                                <li class="page-item"><a class="page-link" href="">${page+2}</a></li>
                                </c:if>
                            <li><a class="page-link ${page+1<=maxPage?'':'disabled'}" href="">Next</a></li>
                        </ul>
                    </div>
                    <!-- /store bottom filter -->
                </div>
                <!-- /STORE -->
            </div>
            <!-- /row -->
        </div>
        <!-- /container -->
    </div>
    <!-- /SECTION -->

    <script>
		var slider = document.getElementById("price-slider");
		var priceInputMax = document.getElementById('price-max');
		var priceInputMin = document.getElementById('price-min');
		var from = parseInt(document.getElementById('minPrice').value);
		var to = parseInt(document.getElementById('maxPrice').value);
		var currentMin = parseInt(document.getElementById('currentMin').value);
		var currentMax = parseInt(document.getElementById('currentMax').value);
		if (slider) {
			noUiSlider.create(slider, {
				start: [currentMin || from, currentMax || to],
				connect: true,
				step: 1,
				range: {
					'min': from,
					'max': to
				}
			});
			slider.noUiSlider.on('update', function (values) {
				priceInputMin.value = values[0];
				priceInputMax.value = values[1];

			});
		}

		$.getJSON('js/json/data.json', function (json) {

			$(function () {
				var data = json.map(function (json) {
					return json.product_name;
				});
				$('#keyword').autocomplete({
					source: data, minLength: 3
				});
			});
		});

		$(document).ready(function () {


			function call(name, min, max, page, sort, cb) {
				$.ajax({
					type: 'POST',
					data: {action: '1', name: name, from: min, to: max, page: page, sort: sort, cbCategory: cb},
					url: 'Search',
					success: function (result) {
						var test = JSON.parse(result);
						var p1 = $('#row-product');
						var p2 = $('#pagingnumber');
						p1.replaceWith('<div style="display: flex;flex-wrap: wrap;" id="row-product" class="row-product">' + test[0] + '</div>');
						p2.replaceWith(' <div id="pagingnumber" class="store-filter clearfix">' + test[1] + '</div>');
//                        $('#row-product').html(test[0]);
//                        $('#pagingnumber').html(test[1]);
					}
				});
			}
			$(document).on('click', '#filter-submit', function (e) {
				e.preventDefault();
				var name = $('#kw').val();
				console.log(name);
				var min = $('#price-min').val();
				var max = $('#price-max').val();
				var c = Array.from($('#checkboxCat input[type=checkbox]:checked').map(function () {
					return $(this).attr('value');
				})).join(' ');
				var s = $('#input-select').val();
				call(name, min, max, 1, s, c);
			});

			$(document).on('click', '.page-link', function (e) {
				e.preventDefault();
				var name = $('#kw').val();
				console.log(name);
				var min = $('#price-min').val();
				var max = $('#price-max').val();
				var c = Array.from($('#checkboxCat input[type=checkbox]:checked').map(function () {
					return $(this).attr('value');
				})).join(' ');
				var s = $('#input-select').val();
				var p = $(this).text();
				if (p === 'Next')
					p = parseInt($('li.page-item.active').find('a').text()) + 1;
				if (p === 'Pre')
					p = parseInt($('li.page-item.active').find('a').text()) - 1;
				call(name, min, max, p, s, c);
			});
			$('#input-select').change(function (e) {
				e.preventDefault();
				var name = $('#kw').val();
				console.log(name);
				var min = $('#price-min').val();
				var max = $('#price-max').val();
				var c = Array.from($('#checkboxCat input[type=checkbox]:checked').map(function () {
					return $(this).attr('value');
				})).join(' ');
				var s = $('#input-select').val();
				call(name, min, max, 1, s, c);
			}
			);

		});

    </script>
    <!-- jQuery Plugins -->
    <script src="https://code.jquery.com/jquery-1.10.2.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <!--    <script src="js/searchjs.js"></script>-->
    <!--    <script src="js/jquery.min.js"></script>-->
    <script src="js/bootstrap.min.js"></script>
    <script src="js/slick.min.js"></script>
    <script src="js/nouislider.min.js"></script>
    <script src="js/jquery.zoom.min.js"></script>
    <script src="js/main.js"></script>
    <jsp:include page="/common/footer/footer.jsp"></jsp:include>

</body>
</html>



