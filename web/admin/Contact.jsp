<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- Designined by CodingLab | www.youtube.com/codinglabyt -->
<html lang="en" dir="ltr">
    <head>
        <meta charset="UTF-8">
        <!--<title> Responsiive Admin Dashboard | CodingLab </title>-->
        <link rel="stylesheet" href="css/adminpage.css">
        <!-- Boxicons CDN Link -->
        <link href='https://unpkg.com/boxicons@2.0.7/css/boxicons.min.css' rel='stylesheet'>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <link href="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/css/bootstrap-combined.no-icons.min.css" rel="stylesheet">
        <link href="//netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.css" rel="stylesheet">
        <link rel="stylesheet" href="../css/owl.carousel.min.css">
        <link rel="stylesheet" href="../css/iconstyle.css">
        <link rel="stylesheet" href="./css/ContactStyle.css">
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.1/css/all.min.css" integrity="sha512-9my9Mb2+0YO+I4PUCSwUYO7sEK21Y0STBAiFEYoWtd2VzLEZZ4QARDrZ30hdM1GlioHJ8o8cWQiy8IAb1hy/Hg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <style>
            .list-group-item{
                background-color: #1abc9c;
                color: white;

            }
            .list-group{
                outline: none;
                border: none;
            }
            .icon-remove:hover{
                color: red;
            }
            input[type="text"]{
                height: 35px;
            }
            input[type="email"]{
                height: 35px;
            }
            .autocom-box li{
                position: relative;
                z-index: 1000;
                list-style: none;
                padding: 8px 12px;
                width: 100%;
                cursor: default;
                border-radius: 3px;
            }
            .autocom-box li:hover{
                background: #f2f2f2;
            }
			.containers {
				position: relative;
				width: 100%;
				min-height: 100vh;
				padding: 2rem;
				background-color: #fafafa;
				overflow: hidden;
				display: flex;
				align-items: center;
				justify-content: center;
			}

			.form {
				width: 100%;
				max-width: 820px;
				background-color: #fff;
				border-radius: 10px;
				box-shadow: 0 0 20px 1px rgba(0, 0, 0, 0.1);
				z-index: 4;
				overflow: hidden;
				display: grid;
				grid-template-columns: repeat(2, 1fr);
			}

			.contact-form {
				background-color: #1abc9c;
				position: relative;
			}

			.circle {
				border-radius: 50%;
				background: linear-gradient(135deg, transparent 20%, #149279);
				position: absolute;
			}

			.circle.one {
				width: 130px;
				height: 130px;
				top: 130px;
				right: -40px;
			}

			.circle.two {
				width: 80px;
				height: 80px;
				top: 10px;
				right: 30px;
			}

			.contact-form:before {
				content: "";
				position: absolute;
				width: 26px;
				height: 26px;
				background-color: #1abc9c;
				transform: rotate(45deg);
				top: 50px;
				left: -13px;
			}

			form {
				padding: 2.3rem 2.2rem;
				z-index: 10;
				overflow: hidden;
				position: relative;
			}

			.title {
				color: #fff;
				font-weight: 500;
				font-size: 1.5rem;
				line-height: 1;
				margin-bottom: 0.7rem;
			}

			.input-container {
				z-index: 1;
				position: relative;
				margin: 1rem 0;
			}

			.input {
				width: 100%;
				outline: none;
				border: 2px solid #fafafa;
				background: none;
				padding: 0.6rem 1.2rem;
				color: #fff;
				font-weight: 500;
				font-size: 0.95rem;
				letter-spacing: 0.5px;
				border-radius: 25px;
				transition: 0.3s;
			}

			textarea.input {
				padding: 0.8rem 1.2rem;
				min-height: 150px;
				border-radius: 22px;
				resize: none;
				overflow-y: auto;
			}

			.input-container label {
				position: absolute;
				top: 50%;
				left: 15px;
				transform: translateY(-50%);
				padding: 0 0.4rem;
				color: #fafafa;
				font-size: 0.9rem;
				font-weight: 400;
				pointer-events: none;
				z-index: 2;
				transition: 0.5s;
			}

			.input-container.textarea label {
				top: 1rem;
				transform: translateY(0);
			}

			.btn {
				padding: 0.6rem 1.3rem;
				background-color: #fff;
				border: 2px solid #fafafa;
				font-size: 0.95rem;
				color: #1abc9c;
				line-height: 1;
				border-radius: 25px;
				outline: none;
				cursor: pointer;
				transition: 0.3s;
				margin: 0;
			}

			.btn:hover {
				background-color: transparent;
				color: #fff;
			}

			.input-container span {
				position: absolute;
				top: 0;
				left: 25px;
				transform: translateY(-50%);
				font-size: 0.8rem;
				padding: 0 0.4rem;
				color: transparent;
				pointer-events: none;
				z-index: 3;
			}

			.input-container span:before,
			.input-container span:after {
				content: "";
				position: absolute;
				width: 10%;
				opacity: 0;
				transition: 0.3s;
				height: 5px;
				background-color: #1abc9c;
				top: 50%;
				transform: translateY(-50%);
			}

			.input-container span:before {
				left: 50%;
			}

			.input-container span:after {
				right: 50%;
			}

			.input-container.focus label {
				top: 0;
				transform: translateY(-50%);
				left: 25px;
				font-size: 0.8rem;
			}

			.input-container.focus span:before,
			.input-container.focus span:after {
				width: 50%;
				opacity: 1;
			}

			.contact-info {
				padding: 2.3rem 2.2rem;
				position: relative;
			}

			.contact-info .title {
				color: #1abc9c;
			}

			.text {
				color: #333;
				margin: 1.5rem 0 2rem 0;
			}

			.information {
				display: flex;
				color: #555;
				margin: 0.7rem 0;
				align-items: center;
				font-size: 0.95rem;
			}

			.icon {
				width: 28px;
				margin-right: 0.7rem;
			}

			.social-media {
				padding: 2rem 0 0 0;
			}

			.social-media p {
				color: #333;
			}

			.social-icons {
				display: flex;
				margin-top: 0.5rem;
			}

			.social-icons a {
				width: 35px;
				height: 35px;
				border-radius: 5px;
				background: linear-gradient(45deg, #1abc9c, #149279);
				color: #fff;
				text-align: center;
				line-height: 35px;
				margin-right: 0.5rem;
				transition: 0.3s;
			}

			.social-icons a:hover {
				transform: scale(1.05);
			}

			.contact-info:before {
				content: "";
				position: absolute;
				width: 110px;
				height: 100px;
				border: 22px solid #1abc9c;
				border-radius: 50%;
				bottom: -77px;
				right: 50px;
				opacity: 0.3;
			}

			.big-circle {
				position: absolute;
				width: 500px;
				height: 500px;
				border-radius: 50%;
				background: linear-gradient(to bottom, #1cd4af, #159b80);
				bottom: 50%;
				right: 50%;
				transform: translate(-40%, 38%);
			}

			.big-circle:after {
				content: "";
				position: absolute;
				width: 360px;
				height: 360px;
				background-color: #fafafa;
				border-radius: 50%;
				top: calc(50% - 180px);
				left: calc(50% - 180px);
			}

			.square {
				position: absolute;
				height: 400px;
				top: 50%;
				left: 50%;
				transform: translate(181%, 11%);
				opacity: 0.2;
			}

			@media (max-width: 1000px) {
				.form {
					grid-template-columns: 1fr;
				}

				.contact-info:before {
					bottom: initial;
					top: -75px;
					right: 65px;
					transform: scale(0.95);
				}

				.contact-form:before {
					top: -13px;
					left: initial;
					right: 70px;
				}

				.square {
					transform: translate(140%, 43%);
					height: 350px;
				}

				.big-circle {
					bottom: 75%;
					transform: scale(0.9) translate(-40%, 30%);
					right: 50%;
				}

				.text {
					margin: 1rem 0 1.5rem 0;
				}

				.social-media {
					padding: 1.5rem 0 0 0;
				}
			}

			@media (max-width: 700px) {
				.container {
					padding: 1.5rem;
				}

				.contact-info:before {
					display: none;
				}

				.square,
				.big-circle {
					display: none;
				}

				form,
				.contact-info {
					padding: 1.7rem 1.6rem;
				}

				.text,
				.information,
				.social-media p {
					font-size: 0.8rem;
				}

				.title {
					font-size: 1.15rem;
				}

				.social-icons a {
					width: 30px;
					height: 30px;
					line-height: 30px;
				}

				.icon {
					width: 23px;
				}

				.input {
					padding: 0.45rem 1.2rem;
				}

				.btn {
					padding: 0.45rem 1.2rem;
				}
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
                    <a href="#" class="active">
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
                    <a href="Settings" >
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

            <div style="padding-top: 0;" class="home-content">
                <div class="containers">
                    <span class="big-circle"></span>
                    <img src="img/shape.png" class="square" alt="" />
                    <div class="form">
                        <div class="contact-info">
                            <h3 class="title">Let's get in touch</h3>
                            <p class="text">
                                Team 7 mail contact
                            </p>

                            <div class="info">
                                <div class="information">
                                    <img src="img/location.png" class="icon" alt="" />
                                    <p>km 17+, Đại lộ Thăng Long , KCN cao Hòa Lạc, Thạch Thất, Hà Nội </p>
                                </div>
                                <div class="information">
                                    <img src="img/email.png" class="icon" alt="" />
                                    <p>7group@gmail.com</p>
                                </div>
                                <div class="information">
                                    <img src="img/phone.png" class="icon" alt="" />
                                    <p>0989096666</p>
                                </div>
                            </div>

                            <div class="social-media">
                                <p>Connect with us :</p>
                                <div class="social-icons">
                                    <a href="#">
                                        <i class="fab fa-facebook-f"></i>
                                    </a>
                                    <a href="#">
                                        <i class="fab fa-twitter"></i>
                                    </a>
                                    <a href="#">
                                        <i class="fab fa-instagram"></i>
                                    </a>
                                    <a href="#">
                                        <i class="fab fa-linkedin-in"></i>
                                    </a>
                                </div>
                            </div>
                        </div>

                        <div class="contact-form">
                            <span class="circle one"></span>
                            <span class="circle two"></span>

                            <form action="index.html" autocomplete="off">
                                <h3 class="title">Contact us</h3>
                                <div class="input-container">
                                    <input type="text" name="name" id="subject" class="input" placeholder="Subject" />

                                </div>
                                <div class="input-container">
                                    <input type="email" name="email" id="emailcc" class="input" placeholder="cc" />
                                    <div id="selected"></div>
                                    <div id="matches-mail" class="autocom-box">
                                        <!-- here list are inserted from javascript -->

                                    </div>
                                </div>
                                <div class="input-container textarea">
                                    <textarea name="message" id="message" class="input" placeholder="Message"></textarea>

                                </div>
                                <input type="submit" value="Send" id="send" class="btn" />
                            </form>
                        </div>
                    </div>
                </div>

            </div>

        </section>

        <!--        <script src="/js/contactJS.js"></script>-->
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
			console.log('ok');
			$('#emailcc').keyup(function (e) {
				$('#matches-mail').html('');
				let keyword = e.target.value;
				if (keyword !== '') {
					$.getJSON('js/json/Email.json', function (json) {
						let selected = [];
						$('#selected li').each(function () {
							selected.push($(this).text().toLowerCase());
						});
						console.log(selected);
						let emptyArray = [];
						emptyArray = json.filter((data) => {
							return data.email.toLowerCase().startsWith(keyword.toLowerCase()) && !selected.includes(data.email.toLowerCase());
						});
						emptyArray = emptyArray.map(function (json) {
							return `<li>` + json.email + `</li>`;
						});
						suggestShow(emptyArray);
						$('#matches-mail li').on('click', function () {
							$('#selected').append('<li>' + $(this).html() + '<i class="icon-remove"></i></li>');
							$(this).remove();

						});
						$(document).on('click', '.icon-remove', function () {
							console.log('da vao');
							$(this).parent().remove();
						});
					});
				}
			});

			$(document).on('click', '#send', function (e) {
				e.preventDefault();
				let selected = [];
				$('#selected li').each(function () {
					selected.push($(this).text());
				});
				selected = selected.join(' ');
				let subject = $('#subject').val();
				let message = $('#message').val();
				$.ajax({
					type: 'POST',
					data: {subject: subject, selected: selected, message: message},
					url: 'Contact',
					success: function (result) {
						alert(result);
					}
				});
			});
			function suggestShow(data) {
				let listdata;
				if (!data.length) {
					listdata = `<li>` + $('#emailcc').val() + `</li>`;
				} else {
					listdata = data.join('');
				}
				$('#matches-mail').html(listdata);

			}


        </script>

    </body>
</html>



