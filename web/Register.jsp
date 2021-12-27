<%-- 
    Document   : Register
    Created on : Oct 1, 2021, 7:46:20 AM
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
			<h1>Register</h1>

			<%
				String message = (String) request.getAttribute("message");
				if (message == null) {
					message = "";
				}
			%>
			<form action="Register" method="POST">
				<table>
					<tr>
						<td>Username</td>
						<td><input type="text" name="account_username" required/></td>
					</tr>
					<tr>
						<td>Password</td>
						<td><input type="password" name="account_password" required/></td>
					</tr>
					<tr>
						<td>Name</td>
						<td><input type="text" name="account_name" required=""/></td>
					</tr>
					<tr>
						<td>Email</td>
						<td><input type="text" name="email"/></td>
					</tr>
					<tr>
						<td>Phone number</td>
						<td><input type="text" name="account_phone"/></td>
					</tr>
					<tr>
						<td>Date of birth</td>
						<td><input type="date" name="dob"/></td>
					</tr>
					<tr>
						<td>Gender</td>
						<td>
							<input type="radio" name="gender" value="1" checked=""/>Male	
							<input type="radio" name="gender" value="0"/>Female
						</td>
					</tr>
					<tr>
						<td>Account Address</td>
						<td><input type="text" name="account_address"/></td>
					</tr>
					<tr>
						<td>Image</td>
						<td><input type="file" name="account_image" accept="image/png, image/jpeg"/></td>
					</tr>
					<tr>
						<td><input type="submit" value="Register"/></td>
						<td><input type="reset" value="Reset"/></td>
					</tr>
				</table>
			</form>
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
        <body oncontextmenu='return false' class='snippet-body'>
                                <div class="container-fluid">
    <div class="row no-gutter">
        <div class="col-md-6 d-none d-md-flex bg-image"></div>
        <div class="col-md-6 bg-light">
            <div class="login d-flex align-items-center py-5">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-7 col-xl-6 mx-auto">
                            <h3 class="display-4">Register</h3> <br>
                            <form action="Register" method="POST">
                                <div class="form-group mb-3"> <input id="inputEmail" type="text" name="account_username" placeholder="Account" required="" autofocus="" class="form-control rounded-pill border-0 shadow-sm px-4"> </div>
                                <div class="form-group mb-3"> <input id="inputPassword" type="password"  name="account_password" placeholder="Password" required="" class="form-control rounded-pill border-0 shadow-sm px-4 text-danger"> </div>
                                <div class="form-group mb-3"> <input id="inputPassword" type="text" name="account_name" placeholder="Name" required="" class="form-control rounded-pill border-0 shadow-sm px-4 text-danger"></div>
                                <div class="form-group mb-3"> <input id="inputPassword" type="email" name="email"placeholder="Email" required="" class="form-control rounded-pill border-0 shadow-sm px-4 text-danger"></div>
                                <p><%= message%></p>
                                <button type="submit" class="btn btn-danger btn-block text-uppercase mb-2 rounded-pill shadow-sm" value="Register">Register</button>
								<span>Have an account? &nbsp<a href="Login" style="color: #6c757d;text-decoration: underline;font-style: italic">Login</a></span>
                                
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
