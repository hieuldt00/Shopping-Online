<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!-- Designined by CodingLab | www.youtube.com/codinglabyt -->
<html lang="en" dir="ltr">
    <head>
        <meta charset="UTF-8">
        <!--<title> Responsiive Admin Dashboard | CodingLab </title>-->
        <link rel="stylesheet" href="./css/adminpage.css">
        <!-- Boxicons CDN Link -->
        <link href='https://unpkg.com/boxicons@2.0.7/css/boxicons.min.css' rel='stylesheet'>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <style>
            table {
                border-collapse: collapse;
                width: 100%;
            }
            a{
                text-decoration: none;
            }
            th, td {
                padding: 8px;
                text-align: left;
                border-bottom: 0.5px solid #ddd;
            }
            .ban{
                cursor: pointer;
                width: 40px;
                background-color: red;
                border: none;
            }
            .act{
                cursor: pointer;
                width: 40px;
                background-color: greenyellow;
                border: none;
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
                    <a href="Analysis" >
                        <i class='bx bx-grid-alt' ></i>
                        <span class="links_name">Dashboard</span>
                    </a>
                </li>
                <li>
                    <a href="ShopManage" class="active">
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
                    <form action="ShopManage" method="post">

                        <input type="text" name="keyword" value="${keyword}" placeholder="Search shop...">

                        <button type="submit" class="bx-search"><i class='bx bx-search'></i></button>

                    </form>
                </div>
                <div class="profile-details">
                    <!--<img src="images/profile.jpg" alt="">-->
                    <span class="admin_name">${sessionScope.account.getAccount_name()}</span>
                    <i class='bx bx-chevron-down' ></i>
                </div>
            </nav>

            <div class="home-content">

                <div class="sales-boxes">
                    <div style="width: 100%;" class="recent-sales box">
                        <div class="title">Recent Shop</div>
                        <div class="sales-details">
                            <c:choose>
                                <c:when test="${listshop.isEmpty()}">
                                    <p>Not found !</p>
                                </c:when>
                                <c:otherwise>
                                    <table>
                                        <tr>
                                            <th>Shop ID</th>
                                            <th>Shop name</th>
                                            <th>status</th>
                                            <th></th>

                                        </tr>
                                        <c:forEach var="s" items="${listshop}">
                                            <tr>
                                                <td>${s.getShop_id()}</td>
                                                <td><a href="#" >${s.getShop_name()}</a></td>

                                                <c:if test="${s.getStatus() == 1}">
                                                    <td><img width="10px" height="10px" src="img/image_active/1.png"></td>
                                                    <td> <form action="ShopManage" method="post">
                                                            <input  type="text" hidden="hidden" value="${keyword}" name="keyword">
                                                            <input  type="text" hidden="hidden" value="true" name="ban">
                                                            <input  type="text" hidden="hidden" value="${s.getShop_id()}" name="shopid">
                                                            <input class="ban" type="submit" value="ban" />
                                                        </form>
                                                    </td> 
                                                </c:if>
                                                <c:if test="${s.getStatus() == 0}">
                                                    <td> <img width="10px" height="10px" src="img/image_active/0.png"></td>
                                                    <td> <form action="ShopManage" method="post">
                                                            <input  type="text" hidden="hidden" value="${keyword}" name="keyword">
                                                            <input  type="text" hidden="hidden" value="false" name="ban">
                                                            <input  type="text" hidden="hidden" value="${s.getShop_id()}" name="shopid">
                                                            <input class="act" type="submit" value="active" />
                                                        </form></td> 
                                                    </c:if>


                                            </tr>
                                        </c:forEach>

                                    </table>
                                </c:otherwise></c:choose>
                        </div>

                    </div>

                </div>
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
			}
        </script>

    </body>
</html>




