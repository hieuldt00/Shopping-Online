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
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <style>
            img{
                background: #FFF;
            }
            a{
                text-decoration: none;
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

            .emp-profile{
                padding: 3%;
                margin-top: 3%;
                margin-bottom: 3%;
                border-radius: 0.5rem;
                background: #fff;
            }
            .profile-img{
                text-align: center;
            }
            .profile-img img{
                width: 70%;
                height: 100%;
            }
            .profile-img .file {
                position: relative;
                overflow: hidden;
                margin-top: -20%;
                width: 70%;
                border: none;
                border-radius: 0;
                font-size: 15px;
                background: #212529b8;
            }
            .profile-img .file input {
                position: absolute;
                opacity: 0;
                right: 0;
                top: 0;
            }
            .profile-head h5{
                color: #333;
            }
            .profile-head h6{
                color: #0062cc;
            }
            .profile-edit-btn{
                border: none;
                border-radius: 1.5rem;
                width: 70%;
                padding: 2%;
                font-weight: 600;
                color: #6c757d;
                cursor: pointer;
            }
            .proile-rating{
                font-size: 12px;
                color: #818182;
                margin-top: 5%;
            }
            .proile-rating span{
                color: #495057;
                font-size: 15px;
                font-weight: 600;
            }
            .profile-head .nav-tabs{
                margin-bottom:5%;
            }
            .profile-head .nav-tabs .nav-link{
                font-weight:600;
                border: none;
            }
            .profile-head .nav-tabs .nav-link.active{
                border: none;
                border-bottom:2px solid #0062cc;
            }
            .profile-work{
                padding: 14%;
                margin-top: -15%;
            }
            .profile-work p{
                font-size: 12px;
                color: #818182;
                font-weight: 600;
                margin-top: 10%;
            }
            .profile-work a{
                text-decoration: none;
                color: #495057;
                font-weight: 600;
                font-size: 14px;
            }
            .profile-work ul{
                list-style: none;
            }
            .profile-tab label{
                font-weight: 600;
            }
            .profile-tab p{
                font-weight: 600;
                color: #0062cc;
            }
            input{
                width: 300px;
                font-weight: 600;
                color: #0062cc;
                outline: none;
                border: none;
            }
            .inputActive{
                color:#333;
                font-weight: 300;
                width: 300px;
                outline: none;
                border: none;
                border-bottom: 0.5px solid #0DB8DE;
            }
            input[type=checkbox] + label {
                width: 10px;
                color: #ccc;
                font-style: italic;
            } 
            input[type=checkbox]:checked + label {
                width: 10px;
                color: #f00;
                font-style: normal;
            } 
            .btn .btn-warning{
                position: absolute;
                right: 0;
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
                <li>
                    <a href="#">
                        <i class='bx bx-pie-chart-alt-2' ></i>
                        <span class="links_name">Analytics</span>
                    </a>
                </li>

                <li>
                    <a href="#">
                        <i class='bx bx-book-alt' ></i>
                        <span class="links_name">Sale Manage</span>
                    </a>
                </li>
                <li>
                    <a href="#">
                        <i class='bx bx-user' ></i>
                        <span class="links_name">Team</span>
                    </a>
                </li>

                <li>
                    <a href="Settings">
                        <i class='bx bx-cog' ></i>
                        <span class="links_name">Settings</span>
                    </a>
                </li>
                <li class="log_out">
                    <a href="#">
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
                <div class="profile-details">
                    <!--<img src="images/profile.jpg" alt="">-->
                    <span class="admin_name">${sessionScope.account.getAccount_name()}</span>
                    
                </div>
            </nav>

            <div class="home-content">

                <div class="sales-boxes">
                    <div style="width: 100%;" class="recent-sales box">
                        <div style="text-align: center;" class="title">User detail</div>
                        <div  class="sales-details">
                            <div class="container emp-profile">

                                <div class="row">
                                    <div class="col-md-4">
                                        <div class="profile-img">
                                            <img src="img/image_user/${user.getAccount_image()}" alt=""/>

                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="profile-head">

                                            
                                            <ul class="nav nav-tabs" id="myTab" role="tablist">
                                                <li class="nav-item">
                                                    <a class="nav-link active" id="home-tab" data-toggle="tab" href="#home" role="tab" aria-controls="home" aria-selected="true">About</a>
                                                </li>
                                                <li class="nav-item">
                                                    <a class="nav-link " id="profile-tab" data-toggle="tab" href="#profile" role="tab" aria-controls="profile" aria-selected="false">Edit Profile</a>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                    
                                </div>
                                <div class="row">
                                    <div class="col-md-4">
                                        <div class="profile-work">
                                        

                                        </div>
                                    </div>
                                    <div class="col-md-8">
                                        <div class="tab-content profile-tab" id="myTabContent">
                                            <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
                                                <div class="row">
                                                    <div class="col-md-6">
                                                        <label>User Id</label>
                                                    </div>
                                                    <div class="col-md-6">
                                                        <input  type="text" value="${user.getAccount_id()}" readonly="">

                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="col-md-6">
                                                        <label>Name</label>
                                                    </div>
                                                    <div class="col-md-6">
                                                        <input type="text" value="${user.getAccount_name()}" readonly="">

                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="col-md-6">
                                                        <label>Email</label>
                                                    </div>
                                                    <div class="col-md-6">
                                                        <input type="text" value="${user.getEmail()}" readonly="">

                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="col-md-6">
                                                        <label>Phone</label>
                                                    </div>
                                                    <div class="col-md-6">
                                                        <input type="text" value="${user.getAccount_phone()}" readonly="">

                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="col-md-6">
                                                        <label>Profession</label>
                                                    </div>
                                                    <div class="col-md-6">
                                                        <p>Web Developer and Designer</p>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="tab-pane fade" id="profile" role="tabpanel" aria-labelledby="profile-tab">
                                                <div class="row">
                                                    <div class="">
                                                        <label></label><br>
                                                    </div>
                                                    <form id="form-roles" action="ViewUserProfile" method="post">
                                                        <input type="text" name="userid" value="${user.getAccount_id()}" hidden="hidden">
                                                        <div>
                                                            <div class="">
                                                                <c:forEach items="${listrole}" var="r">
                                                                    <input type="checkbox" name="roles" id="${r.getRolename()}"  
                                                                           <c:forEach items="${selectedRole}" var="sr">
                                                                               <c:if test="${r.getRoleid()==sr}">
                                                                                   checked="checked"
                                                                               </c:if>
                                                                           </c:forEach>
                                                                           value="${r.getRoleid()}">
                                                                    <label for="${r.getRolename()}">${r.getRolename()}</label><br>
                                                                </c:forEach>
                                                                <hr>

                                                            </div>
                                                            <div class="row">
                                                                <div class="col-md-6">
                                                                    <button type="submit" class="btn btn-warning">Save</button>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </form>
                                                </div>


                                            </div>
                                        </div>
                                    </div>
                                </div>

                            </div>

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
                                };
            let navlink = document.querySelectorAll(".nav-link");
            let home = document.querySelector("#home");
            let profile = document.querySelector("#profile");

            for (var item of navlink) {
                item.onclick = function () {
                    navlink[0].classList.toggle("active");
                    navlink[1].classList.toggle("active");
                    home.classList.toggle("show");
                    home.classList.toggle("active");
                    profile.classList.toggle("active");
                    profile.classList.toggle("show");
                };
            }
            
            
            let formroles = document.getElementById('form-roles');

            if (formroles) {
                formroles.onsubmit = function (event) {
                    event.preventDefault();
                    let roles = document.querySelectorAll('input[name="roles"]:checked');
                    console.log(roles);
                    if (roles.length === 0) {
                        alert('Please choose role for this user !!');
                    }else{
                         formroles.submit();
                    }
                }
            }
        </script>

    </body>
</html>

