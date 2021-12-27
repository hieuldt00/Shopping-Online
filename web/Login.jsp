<%-- 
    Document   : Login
    Created on : Sep 30, 2021, 11:21:39 PM
    Author     : ToneVn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>

        <div align="center">
<%
	String message = (String) request.getAttribute("message");
	if (message == null) {
		message = "";
	}
%>

<p><%= message%></p>
<h1>LOGIN</h1>
<form action="Login" method="POST">
	<table>
		<tr>
			<td>Username:</td>
			<td><input type="text" name="account_username" required="true"/></td>
		</tr>
		<tr>
			<td>Password:</td>
			<td><input type="password" name="account_password" required="true"/></td>
		</tr>
		<tr>
			<td><input type="submit" value="Login"/></td>
			<td><input type="reset" value="Reset"/></td>
		</tr>
	</table>
</form>
<a href="Register.jsp">Register</a>
</div>
</body>
</html>-->
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href='https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css' rel='stylesheet'>
        <link href='' rel='stylesheet'>
        <script type='text/javascript' src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>
        <style>.login,
            .image {
				min-height: 100vh
            }

            .bg-image {
				background-image: url('https://c4.wallpaperflare.com/wallpaper/922/223/671/gundam-mobile-suit-gundam-mech-rx-78-gundam-anime-hd-wallpaper-preview.jpg');
				background-size: cover;
				background-position: center center
			}</style>
    </head>
    <body>
	<body oncontextmenu='() => false' class='snippet-body'>

		<div class="container-fluid">
			<div class="row no-gutter">

				<div class="col-md-6 d-none d-md-flex bg-image"></div>
				<div class="col-md-6 bg-light">
					<div class="login d-flex align-items-center py-5">
						<div class="container">
							<div class="row">
								<div class="col-lg-7 col-xl-6 mx-auto">
                                                                    
									<h3 class="display-4">Login</h3> <br>
                                                                        <p><%= message%></p>
									<form action="Login" method="POST">
										<div class="form-group mb-3"> <input id="inputEmail" type="text" placeholder="Email address" required="true" autofocus="" class="form-control rounded-pill border-0 shadow-sm px-4" name="account_username"> </div>
										<div class="form-group mb-3"> <input id="inputPassword" type="password" placeholder="Password" required="true" class="form-control rounded-pill border-0 shadow-sm px-4 text-danger" name="account_password"><br> </div>
										<!--<div class="custom-control custom-checkbox mb-3"> <input id="customCheck1" type="checkbox" checked class="custom-control-input"> <label for="customCheck1" class="custom-control-label">Remember password</label> </div>--> 
										<button type="submit" class="btn btn-danger btn-block text-uppercase mb-2 rounded-pill shadow-sm">Login</button>
										<div class="text-center d-flex justify-content-between mt-4">
											<p> Dont have account? &nbsp<a href="Register" class="font-italic text-muted"> <u>Create Account</u></a></p>
										</div>
									</form>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<script type='text/javascript' src='https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.bundle.min.js'></script>
		<script type='text/javascript'></script>
	</body>

</html>
