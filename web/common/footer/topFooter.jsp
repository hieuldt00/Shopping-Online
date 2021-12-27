<%-- Document : topFooter Created on : Oct 13, 2021, 12:44:17 PM Author : ToneVn --%>

<%@page import="java.util.List"%>
<%@page import="model.Category" %>
<%@page import="java.util.ArrayList" %>
<%@page import="dal.DAOCategory" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>top footer</title>
	</head>

	<body>
		<!-- FOOTER -->
		<footer id="footer">
			<!-- top footer -->
			<div class="section">
				<!-- container -->
				<div class="container">
					<!-- row -->
					<div class="row">
						<div class="col-md-3 col-xs-6">
							<div class="footer">
								<h3 class="footer-title">About Us</h3>
								<p>Team 7 members:</p>
								<ul class="footer-links">
									<li><a href="https://www.facebook.com/ta.p711/">Phạm Tùng Anh</a>
									</li>
									<li><a href="https://www.facebook.com/duong.nguyentrung.180">Nguyễn
											Trùng Dương</a></li>
									<li><a
											href="https://www.facebook.com/profile.php?id=100009471294230">Lê
											Đức Trung Hiếu</a></li>
									<li><a
											href="https://www.facebook.com/profile.php?id=100016897332749">Trương
											Quang Sơn</a></li>
								</ul>
							</div>
						</div>

						<div class="col-md-3 col-xs-6">
							<div class="footer">
								<h3 class="footer-title">Categories</h3>
								<ul class="footer-links">
									<% DAOCategory daoCategory = new DAOCategory();
										List<Category> categories = daoCategory.getAllCategories();
//										List<Category> categories = daoCategory.getAllCategories().subList(0, 5);
										for (Category cate : categories) {
									%>
									<li><a href="Category?category_id=<%= cate.getCategory_id()%>">
											<%= cate.getCategory_name()%>
										</a></li>
										<% }%>
								</ul>
							</div>
						</div>

						<div class="clearfix visible-xs"></div>

						<div class="col-md-3 col-xs-6">
							<div class="footer">
								<h3 class="footer-title">Information</h3>
								<ul class="footer-links">
									<li><a href="#">About Us</a></li>
									<li><a href="#">Contact Us</a></li>
									<li><a href="#">Privacy Policy</a></li>
									<li><a href="#">Orders and Returns</a></li>
									<li><a href="#">Terms & Conditions</a></li>
								</ul>
							</div>
						</div>

						<div class="col-md-3 col-xs-6">
							<div class="footer">
								<h3 class="footer-title">Service</h3>
								<ul class="footer-links">
									<li><a href="#">My Account</a></li>
									<li><a href="#">View Cart</a></li>
									<!--<li><a href="#">Wishlist</a></li>-->
									<li><a href="#">My Order</a></li>
									<li><a href="#">Become a vendor</a></li>
								</ul>
							</div>
						</div>
					</div>
					<!-- /row -->
				</div>
				<!-- /container -->
			</div>
			<!-- /top footer -->
	</body>

</html>