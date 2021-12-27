<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!-- Designined by CodingLab | www.youtube.com/codinglabyt -->
<html lang="en" dir="ltr">
    <head>
        <meta charset="UTF-8">
        <!--<title> Responsiive Admin Dashboard | CodingLab </title>-->
        <link rel="stylesheet" href="./css/adminpage.css">
        <link rel="stylesheet" href="./css/adduser.css">
        <!-- Boxicons CDN Link -->
        <link href='https://unpkg.com/boxicons@2.0.7/css/boxicons.min.css' rel='stylesheet'>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <style>
            img{
                background: #FFF;
            }

            table {
                border-collapse: collapse;
                width: 100%;
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
            .adduser{
                text-decoration: none!important;
                cursor: pointer;
                display: block;
                position: absolute;
                top: 17%;
                right: 40px;
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
                    <a href="UserManage" class="active">
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
                    <form action="UserManage" method="post">

                        <input type="text" hidden="hidden" name="status" value="" >
                        <input type="text"hidden="hidden" name="gender" value="">
                        <input type="text" name="keyword" value="${keyword}" placeholder="Search user...">
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
                        <div class="title">Recent User</div>


                        <div style="display: flex;">

                            <div style="width: 20%" class="top-sales box">
                                <div class="title">Filter</div>
                                <form action="UserManage" method="post">
                                    <input  type="text" hidden="hidden" value="${keyword}" name="keyword">


                                    <p>Role</p>
                                    <c:forEach items="${listrole}" var="r">
                                        <input type="checkbox" name="roles"  
                                               <c:forEach items="${selectedRole}" var="sr">
                                                   <c:if test="${r.getRoleid()==sr}">
                                                       checked="checked"
                                                   </c:if>
                                               </c:forEach>
                                               value="${r.getRoleid()}">${r.getRolename()} <br>
                                    </c:forEach> 
                                    <hr>
                                    <p>Gender</p>

                                    <input type="radio" name="gender" value="" checked > All
                                    <br>
                                    <c:choose>
                                        <c:when test="${gender==1}">
                                            <input type="radio" name="gender" value="1"  checked > Male
                                            <br>
                                        </c:when>
                                        <c:otherwise>
                                            <input type="radio" name="gender" value="1" >  Male
                                            <br>
                                        </c:otherwise>

                                    </c:choose>
                                    <c:choose>
                                        <c:when test="${gender==0}">
                                            <input type="radio" name="gender" value="0"  checked>Female
                                            <hr>
                                        </c:when>
                                        <c:otherwise>
                                            <input type="radio" name="gender" value="0" >  Female
                                            <hr>
                                        </c:otherwise>
                                    </c:choose>



                                    <p>Status</p>
                                    <input type="radio" name="status" value="" checked>  All
                                    <br>

                                    <c:choose>
                                        <c:when test="${status==1}">
                                            <input type="radio" name="status" value="1" checked>  Active
                                            <br>
                                        </c:when>
                                        <c:otherwise>
                                            <input type="radio" name="status" value="1" >  Active
                                            <br>
                                        </c:otherwise>

                                    </c:choose>
                                    <c:choose>
                                        <c:when test="${status==0}">
                                            <input type="radio" name="status" value="0" checked>  Inactive
                                            <hr>
                                        </c:when>
                                        <c:otherwise>
                                            <input type="radio" name="status" value="0" >  Inactive
                                            <hr>
                                        </c:otherwise>
                                    </c:choose>


                                    <button type="submit" class="btn btn-primary">apply</button>

                                </form>
                                <form action="UserManage" method="post">
                                    <input  type="text" hidden="hidden" value="${keyword}" name="keyword">
                                    <input type="text" name="gender" value="" hidden="hidden">
                                    <input type="text" name="status" value="" hidden="hidden">
                                    <button style="margin-left: 100px;margin-top: -62px;" type="submit" class="btn btn-success">clear</button>
                                </form>

                            </div>
                            <div  class="sales-details">
                                <c:choose>
                                    <c:when test="${listuser.isEmpty()}">
                                        <p>Not found !</p>
                                    </c:when>
                                    <c:otherwise>
                                        <table>
                                            <tr>
                                                <th>User ID</th>
                                                <th>Fullname</th>
                                                <th>Gender</th>
                                                <th>Email</th>
                                                <th>Phone number</th>
                                                <th>Status</th>


                                            </tr>

                                            <c:forEach var="s" items="${listuser}">
                                                <tr>
                                                    <td>${s.getAccount_id()}</td>
                                                    <td><a style="text-decoration: none;" href="ViewUserProfile?userid=${s.getAccount_id()}">${s.getAccount_name()}</a></td>

                                                    <td> <img style="background-color: #FFF" width="15px" height="15px" src=${s.isGender()? "img/image_active/male.png":"img/image_active/female.png"} > </td>
                                                    <td>${s.getEmail()}</td>
                                                    <td>${s.getAccount_phone()}</td>
                                                    <c:if test="${s.getStatus() == 1}">
                                                        <td><img width="10px" height="10px" src="img/image_active/1.png"></td>
                                                        <td> <form action="UserManage" method="post">
                                                                <c:forEach items="${listrole}" var="r">
                                                                    <input type="checkbox" hidden="hidden" name="roles"  
                                                                           <c:forEach items="${selectedRole}" var="sr">
                                                                               <c:if test="${r.getRoleid()==sr}">
                                                                                   checked="checked"
                                                                               </c:if>
                                                                           </c:forEach>
                                                                           value="${r.getRoleid()}">
                                                                </c:forEach>
                                                                <input type="text" hidden="hidden" name="status" value="${status}" >
                                                                <input type="text"hidden="hidden" name="gender" value="${gender}"> 
                                                                <input  type="text" hidden="hidden" value="${keyword}" name="keyword">
                                                                <input  type="text" hidden="hidden" value="true" name="ban">
                                                                <input  type="text" hidden="hidden" value="${s.getAccount_id()}" name="userid">
                                                                <input class="ban" type="submit" value="ban" />
                                                            </form>
                                                        </td> 
                                                    </c:if>
                                                    <c:if test="${s.getStatus() == 0}">
                                                        <td> <img width="10px" height="10px" src="img/image_active/0.png"></td>
                                                        <td> <form action="UserManage" method="post">
                                                                <c:forEach items="${listrole}" var="r">
                                                                    <input type="checkbox" hidden="hidden" name="roles"  
                                                                           <c:forEach items="${selectedRole}" var="sr">
                                                                               <c:if test="${r.getRoleid()==sr}">
                                                                                   checked="checked"
                                                                               </c:if>
                                                                           </c:forEach>
                                                                           value="${r.getRoleid()}">
                                                                </c:forEach>
                                                                <input type="text" hidden="hidden" name="status" value="${status}" >
                                                                <input type="text"hidden="hidden" name="gender" value="${gender}">
                                                                <input  type="text" hidden="hidden" value="${keyword}" name="keyword">
                                                                <input  type="text" hidden="hidden" value="false" name="ban">
                                                                <input  type="text" hidden="hidden" value="${s.getAccount_id()}" name="userid">
                                                                <input class="act" type="submit" value="active" />
                                                            </form></td> 
                                                        </c:if>
                                                </tr>
                                            </c:forEach>

                                        </table>
                                    </c:otherwise>
                                </c:choose>


                            </div>


                        </div>
                        <a href="#" class="adduser" onclick="document.getElementById('addUser').style.display = 'flex'" >Add new User</a>
                    </div>

                </div>
            </div>

        </section>
        <div class="modall" id="addUser">
            <a href="" onclick="document.getElementById('addUser').style.display = 'none'" class="close-regis"></a>
            <div class="modal_overlayer"></div>
            <div class="mbody">
                <span onclick="document.getElementById('addUser').style.display = 'none'" class="close" title="Close Modal">&times;</span>
                <form class="modal-content" id="form-register" action="AddUser" method="post">
                    <div class="container">
                        <h1>Sign Up</h1>
                        <p>Please fill in this form to create an account.</p>
                        <hr>
                        <label for="psw"><b>Username</b></label>
                        <input type="text" placeholder="Enter Username" name="username" id="newUsername" required>
                        <p style="color: red;" id="checkUsername"></p>
                        <label for="email"><b>Email</b></label>
                        <input type="text" placeholder="Enter Email" id="email" name="email" required>
                        <p style="color: red;" id="error-massage"></p>


                        <label>
                            <input type="checkbox" checked="checked" name="remember" style="margin-bottom:15px"> Remember me
                        </label>

                        <p>By creating an account you agree to our <a href="#" style="color:dodgerblue">Terms & Privacy</a>.</p>

                        <div class="clearfix">
                            <button type="button" onclick="document.getElementById('addUser').style.display = 'none'" class="cancelbtn">Cancel</button>
                            <button type="submit" class="signupbtn">Sign Up</button>
                        </div>
                    </div>
                </form>
            </div>

        </div>
        <script src="js/Validators.js"></script>
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
                                let check = false;
                                $(document).ready(function () {
                                    $('#newUsername').keyup(function () {
                                        var username = $('#newUsername').val();
                                        $.ajax({
                                            type: 'POST',
                                            data: {action: 'checkun', username: username},
                                            url: 'CheckUsername',
                                            success: function (result) {
                                                if (result === '')
                                                    check = true;
                                                else {
                                                    check = false;
                                                }
                                                $('#checkUsername').html(result);
                                            }
                                        });
                                    });
                                });
                                ValidatorRegister();
        </script>

    </body>
</html>



