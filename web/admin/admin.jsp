
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!-- Designined by CodingLab | www.youtube.com/codinglabyt -->
<html lang="en" dir="ltr">
    <head>
        <meta charset="UTF-8">
        <!--<title> Responsiive Admin Dashboard | CodingLab </title>-->
        <link rel="stylesheet" href="./css/adminpage.css">
        <link rel="stylesheet" href="../css/adminpage.css">
        <!-- Boxicons CDN Link -->
        <link href='https://unpkg.com/boxicons@2.0.7/css/boxicons.min.css' rel='stylesheet'>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/3.6.0/chart.min.js"></script> 
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/chartjs-plugin-datalabels@2.0.0/dist/chartjs-plugin-datalabels.min.js"></script><meta name="viewport" content="width=device-width, initial-scale=1.0">
        <style>
            table {
                border-collapse: collapse;
                width: 100%;
            }

            th, td {
                padding: 8px;
                text-align: left;
                border-bottom: 0.5px solid #ddd;
            }
            canvas{
                margin: auto;
            }
        </style>
    </head>
    <body>
        <div class="sidebar">
            <div class="logo-details">
                <i class='bx bxl-c-plus-plus'></i>
                <span class="logo_name">7 Admin</span>
            </div>
            <ul class="nav-links">
                <li>
                    <a href="#" class="active">
                        <i class='bx bx-grid-alt' ></i>
                        <span class="links_name">Dashboard</span>
                    </a>
                </li>
                <li>
                    <a href="ShopManage">
                        <i class='bx bx-shopping-bag' ></i>
                        <span class="links_name">Shop Manage</span>
                    </a>
                </li>
                <li>
                    <a href="UserManage">
                        <i class='bx bx-user-circle' ></i>
                        <span class="links_name">User Manage</span>
                    </a>
                </li>
<!--                <li>
                    <a href="#">
                        <i class='bx bx-pie-chart-alt-2' ></i>
                        <span class="links_name">Analytics</span>
                    </a>
                </li>-->

                <li>
                    <a href="Contact">
                        <i class='bx bx-book-alt' ></i>
                        <span class="links_name">Contact</span>
                    </a>
                </li>
<!--                <li>
                    <a href="#">
                        <i class='bx bx-user' ></i>
                        <span class="links_name">Team</span>
                    </a>
                </li>-->

                <li>
                    <a href="Settings">
                        <i class='bx bx-cog' ></i>
                        <span class="links_name">Settings</span>
                    </a>
                </li>
                <li class="log_out">
                    <a href="AdminLogout">
                        <i class='bx bx-log-out'></i>
                        <span class="links_name">Log out</span>
                    </a>
                </li>
            </ul>
        </div>
        <section class="home-section">
            <nav>
                <div class="sidebar-button">
                    <i class='bx bx-menu sidebarBtn'></i>
                    <span class="dashboard">Group 7</span>
                </div>
                <div class="search-box">

                </div>
                <div class="profile-details">
                    <!--<img src="images/profile.jpg" alt="">-->
                    <span class="admin_name">${sessionScope.account.getAccount_name()}</span>
                    <i class='bx bx-chevron-down' ></i>
                </div>
            </nav>

            <div class="home-content">
                <div class="overview-boxes">
                    <div class="box">
                        <div class="right-side">
                            <div class="box-topic">Total Users</div>
                            <div class="number">${totaluser}</div>
                            <div class="indicator">
                                <i class='bx bx-up-arrow-alt'></i>
                                <span class="text">Up from last week</span>
                            </div>
                        </div>
                        <i class='bx bx-cart-alt cart'></i>
                    </div>
                    <div class="box">
                        <div class="right-side">
                            <div class="box-topic">Total Stores</div>
                            <div class="number">${totalshop}</div>
                            <div class="indicator">
                                <i class='bx bx-up-arrow-alt'></i>
                                <span class="text">Up from last week</span>
                            </div>
                        </div>
                        <i class='bx bxs-cart-add cart two' ></i>
                    </div>
                    <div class="box">
                        <div class="right-side">
                            <div class="box-topic">Total Order</div>
                            <div class="number">$${revenue}</div>
                            <div class="indicator">
                                <i class='bx bx-up-arrow-alt'></i>
                                <span class="text">Up from last week</span>
                            </div>
                        </div>
                        <i class='bx bx-cart cart three' ></i>
                    </div>
                    <!--                    <div class="box">
                                            <div class="right-side">
                                                <div class="box-topic">Total Return</div>
                                                <div class="number">11,086</div>
                                                <div class="indicator">
                                                    <i class='bx bx-down-arrow-alt down'></i>
                                                    <span class="text">Down From Today</span>
                                                </div>
                                            </div>
                                            <i class='bx bxs-cart-download cart four' ></i>
                                        </div>-->
                </div>

                <div  class="sales-boxes">
                    <div style="position: relative;" class="recent-sales box">

                        <form action="Analysis" method="post" class="form-year">
                            <input type="text" hidden="" name="startdate" value=${startdate}>
                            <input type="text" hidden="" name="enddate" value=${enddate}>
                            <select style="position: absolute;
                                    top:10px;right: 20%;outline: none;border-radius: 3px;"  name="year" id="year"> 
                                <c:forEach var="i" items="${year}">
                                    <option value="${i}" <c:if test="${i==selectedYear}">selected="selected"</c:if>>${i}</option>  
                                </c:forEach>
                            </select>
                        </form>
                        <div  class="sales-details">

                            <canvas id="myChart" style="width:100%;max-width:700px"></canvas>
                        </div>
                        <!--                        <div class="button">
                                                    <a href="#">See All</a>
                                                </div>-->
                    </div>
                    <div class="top-sales box">
                        <div class="title">Top Selling Shop</div>
                        <ul class="top-sales-details">
                            <c:forEach var="s" items="${topshop}">
                                <li>
                                    <a href="#">
                                        <!--<img src="images/sunglasses.jpg" alt="">-->
                                        <span class="product">${s.getShop_name()}</span>
                                    </a>
                                    <span class="price">${s.getStatus()}</span>
                                </li>
                            </c:forEach>

                            <input type="text" id="revenueData" hidden="hidden" value=${revenueData}> 
                        </ul>

                    </div>
                </div>
                <div style="margin-top: 20px;" class="sales-boxes">
                    <div class="recent-sales box">
                        <form action="Analysis" method="post" class="form-date">
                            <input type="text" hidden="" name="year" value=${selectedYear}>
                            <input type="date" id="start" name="startdate"
                                   min="2020-01-01" max="2023-12-31" value=${startdate}>
                            <input type="date" id="end"  name="enddate"
                                   min="2020-01-01" max="2023-12-31" value=${enddate}>
                        </form>
                        <canvas id="myChartbyCategories" style="width:100%;max-width:700px"></canvas>
                    </div>
                    <div class="top-sales box">
                        <div class="title">Best Seller</div>
                        <ul class="top-sales-details">
                            <c:forEach var="p" items="${bestseller}">
                                <li>
                                    <a href="Product?product_id=${p.getProduct_id()}">
                                        <!--<img src="images/sunglasses.jpg" alt="">-->
                                        <span class="product">${p.getProduct_name()}</span>
                                    </a>
										<span class="price"><fmt:formatNumber type="number" maxFractionDigits="2" value="${p.getPrice()}"/></span>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                    <input type="text" id="revenueData" hidden="hidden" value=${revenueData}> 
                    <input type="text" id="revenueDataCategories" hidden="hidden" value=${revenueDataCategories}> 
                </div> 
        </section>

        <script>


			let sidebar = document.querySelector(".sidebar");
			let sidebarBtn = document.querySelector(".sidebarBtn");
			sidebarBtn.onclick = function () {
				sidebar.classList.toggle("active");
				if (sidebar.classList.contains("active")) {
					sidebarBtn.classList.replace("bx-menu", "bx-menu-alt-right");
				} else
					sidebarBtn.classList.replace("bx-menu-alt-right", "bx-menu");
			};

			let data1 = document.getElementById('revenueData').value;
			let data2 = document.getElementById('revenueDataCategories').value;
			console.log(data1);
			chartmonth(data1);
			chartcategories(data2);
			function chartmonth(parameters) {
				let dataSQL = JSON.parse(parameters);
				var l1 = dataSQL.map(function (data) {
					return data.month;
				});
				var v1 = dataSQL.map(function (data) {
					return parseInt(data.total);
				});
				var chart = document.getElementById('myChart').getContext('2d');

				var chart2 = new Chart(chart, {
					type: "line",
					data: {
						labels: l1,
						datasets: [{
								fill: true,
								lineTension: 0.5,
								backgroundColor: "rgba(255,196,63,0.5)",
								borderColor: "rgba(255,196,63,0.2)",
								data: v1,
								label: 'Revenue of year($)',
								pointBackgroundColor: "rgba(66,150,108,0.8)",
								pointRadius: 5,
								pointHoverRadius: 7,
								pointHoverBorderColor: "rgb(251,85,51)"
							}]
					},
					options: {
						legend: {display: false},
						scales: {
							yAxes: [{ticks: {min: Math.min(...v1) - 100, max: Math.max(...v1) + 100}}]
						}
					}
				});
			}


			function chartcategories(parameters) {
				let dataCategories = JSON.parse(parameters);

				function get_random_color2()
				{
					var r = function () {
						return Math.floor(Math.random() * 256);
					};
					return "rgb(" + r() + "," + r() + "," + r() + ",0.6" + ")";
				}

				let l2 = dataCategories.map(function (cate) {
					return cate.category_name;
				});
				let v2 = dataCategories.map(function (cate) {
					return cate.total;
				});
//            let barColor = Array.from(Array(l2.length), (x, index) => get_random_color2());
//            console.log(barColor);
				var chartCategories = document.getElementById('myChartbyCategories').getContext('2d');

				var chart2 = new Chart(chartCategories, {
					type: "bar",
					data: {
						labels: l2,
						datasets: [{
								label: 'Revenue by categories($)',
								backgroundColor: 'rgb(30,152,228)',
								data: v2,
								stack: 'combined',
								datalabels: {
									anchor: 'end',
									align: 'top'
								}
							}]
					},
					plugins: [ChartDataLabels],
					options: {
						legend: {display: false},
						title: {
							display: true,
							text: "Revenue by categories"
						},
						scales: {
							yAxes: [{ticks: {min: 0, max: Math.max(...v2) + 500}}]
						}

					}
				});
			}

			$(document).ready(function () {
				$('#end').change(function () {
					var start = $('#start').val();
					var end = $('#end').val();
					$.ajax({
						type: 'POST',
						data: {
							action: 'chart2',
							start: start,
							end: end
						},
						url: "CheckUsername",
						success: function (data) {
							$('#myChartbyCategories').replaceWith('<canvas id="myChartbyCategories" style="width:100%;max-width:700px"></canvas>')
							chartcategories(data);
						}
					});
				});
				$('#year').change(function () {
					var year = $('#year').val();
					$.ajax({
						type: 'POST',
						data: {
							action: 'chart1',
							year: year
						},
						url: "CheckUsername",
						success: function (data) {
							$('#myChart').replaceWith('<canvas id="myChart" style="width:100%;max-width:700px"></canvas>')
							chartmonth(data);
						}
					});
				});
			});
        </script>

    </body>
</html>


