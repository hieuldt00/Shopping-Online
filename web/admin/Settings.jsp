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
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <link href="./css/categorystyle.css" rel="stylesheet" >
        <link rel="stylesheet" href="./css/owl.carousel.min.css">
        <link rel="stylesheet" href="./css/iconstyle.css">
        <link rel="stylesheet" href="./css/adduser.css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <style>
            .addCategory{
                font-size: 18px;
                text-decoration: none!important;
                cursor: pointer;
                display: block;
                position: absolute;
                top: 30px;
                right: 50px;
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
                    <a href="ShopManage" >
                        <i class='bx bx-shopping-bag' ></i>
                        <span class="links_name">Shop Manage</span>
                    </a>
                </li>
                <li>
                    <a href="UserManage" >
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
                    <a href="Settings" class="active">
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
        <section class="home-section" >
            <nav>
                <div class="sidebar-button">
                    <i class='bx bx-menu sidebarBtn'></i>
                    <span class="dashboard">Group 7</span>
                </div>
                <div class="profile-details">
                    <!--<img src="images/profile.jpg" alt="">-->
                    <span class="admin_name">${sessionScope.account.getAccount_name()}</span>

                </div>
            </nav>

            <div class="home-content">
                <div class="container" style="position: relative;background-color: #fff;border-radius: 10px;">
                    <h2 class="mb-5" style="padding-top: 10px;">Category Control Panel</h2>
                    <a href="#" class="addCategory" onclick="document.getElementById('addCategory').style.display = 'flex'" >Add new Category</a>
                    <div class="table-responsive" >

                        <table style="border-radius: 50px;" class="table table-striped custom-table">
                            <thead>
                                <tr>

                                    <th scope="col">Category ID</th>
                                    <th scope="col">Category name</th>
                                    <th scope="col">Photo</th>
                                    <th scope="col">
                                        <label class="custom-control ios-switch" style="position: relative; top: 10px;">
                                            <input type="checkbox" class="ios-switch-control-input js-ios-switch-all">
                                            <span class="ios-switch-control-indicator"></span>
                                        </label>
                                    </th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${listcategory}" var="c">
                                    <tr scope="row" class="">

                                        <td>
                                            ${c.getCategory_id()}
                                        </td>
                                        <td class="pl-0">
                                            <div class="d-flex align-items-center">

                                                <a href="#" class="name">${c.getCategory_name()}</a>
                                            </div>
                                        </td>
                                        <td>
                                            <img src="./img/image_category/${c.getCategory_image()}" width="90">
                                        </td>
                                        <td>
                                            <label class="custom-control ios-switch">
                                                <input type="checkbox" class="ios-switch-control-input" checked="">
                                                <span class="ios-switch-control-indicator"></span>
                                            </label>
                                        </td>

                                    </tr>
                                </c:forEach>


                            </tbody>
                        </table>
                    </div>
                </div>

                <div class="modall" id="addCategory">
                    <a href="" onclick="document.getElementById('addCategory').style.display = 'none'" class="close-regis"></a>
                    <div class="modal_overlayer"></div>
                    <div class="mbody">
                        <span onclick="document.getElementById('addCategory').style.display = 'none'" class="close" title="Close Modal">&times;</span>
                        <form class="modal-content" id="form-cate" action="Settings" method="post" enctype="multipart/form-data">
                            <div class="container">
                                <h1>Create Category</h1>
                                <p>Please fill in this form to create an Category.</p>
                                <hr>
                                <label for="newCategoryname"><b>Category name</b></label>
                                <input type="text" placeholder="Enter category name" name="categoryname" id="newCategoryname" required>
                                <p style="color: red;" id="checkCategory"></p>
                                <label for="file"><b>Image</b></label>
                                <input type = "file" id="file" name = "file" size = "50" multiple="">

                                <div class="clearfix">
                                    <button type="button" onclick="document.getElementById('addCategory').style.display = 'none'" class="cancelbtn">Cancel</button>
                                    <button type="submit" class="signupbtn">Create</button>
                                </div>
                            </div>
                        </form>

                    </div>

                </div>
        </section>
        <script src="./js/categoryControl.js"></script>
        <script src="./js/popper.min.js"></script>
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
        </script>

    </body>
</html>

