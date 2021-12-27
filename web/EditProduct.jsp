<%--
	Document   : EditProduct
	Created on : Oct 30, 2021, 10:56:36 AM
	Author 	: son
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">

		<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
		<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<link href="css/manager.css" rel="stylesheet" type="text/css"/>
		<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
		<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
		<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
		<style>
			img{
				width: 200px;
				height: 120px;
			}
		</style>
	<body>
		<div class="container">
			<div class="table-wrapper" >
				<div class="table-title" style="background-color: white">
					<div class="row">
						<div class="col-sm-6">
							<h2 style="color: red">Edit <b>Product</b></h2>
						</div>
						<div class="col-sm-6">
						</div>
					</div>
				</div>
			</div>
			<div class="container">
				<div id="editEmployeeModal">
					<div class="modal-dialog">
						<div class="modal-content">
							<form action="VendorEditProduct?product_id=${product.product_id}" method="post" enctype="multipart/form-data">
								<span >${message}</span>
								<div class="modal-body">
									<div class="form-group">
										<label>Product Name</label>
										<input name="ProductName" value="${product.product_name}" type="text" class="form-control" required >
									</div>
									<div class="form-group">
										<label>Quantity</label>
										<input name="quantity" value="${product.quantity}" type="text" class="form-control" required>
									</div>
									<div class="form-group">
										<label>Price</label>
										<input name="price" value="${product.price}" type="text" class="form-control" required>
									</div>
									<div class="form-group">
										<label>Description</label>
										<input name="description" value="${product.description}" type="text" class="form-control" required>
									</div>
									<div class="form-group">
										<label>Detail</label>
										<input name="detail" value="${product.detail}" type="text" class="form-control" required>
									</div>
									<div class="form-group">
										<label>Image Thumbnail</label>
										<input name="image" value="${product.image_thumbnail}" type="file" class="form-control">
									</div>
									<div class="form-group">
										<label>Image 1</label>
										<input name="image1" type="file" class="form-control">
									</div>
									<div class="form-group">
										<label>Image 2</label>
										<input name="image2" type="file" class="form-control">
									</div>
									<div class="form-group">
										<label>Image 3</label>
										<input name="image3"  type="file" class="form-control">
									</div>
									<div class="form-group">
										<label>Image 4</label>
										<input name="image4" type="file" class="form-control">
									</div>
									<div class="form-group">
										<label>Status</label>
										<select class="selectpicker form-control" name="status">

											<option value="1">active</option>
											<option value="0">inactive</option>
										</select>
									</div>

									<div class="form-group">
										<label>Category</label>
										<select class="selectpicker form-control" name="category">
											<c:forEach items="${listC}" var="o">
												<option value="${o.category_id}">${o.category_name}</option>
											</c:forEach>
										</select>
									</div>

								</div>
								<div class="modal-footer">
									<button onclick='window.location.href = "VendorStore"'>Cancel</button>
									<input type="submit" class="btn btn-success" value="Edit">
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>


		</div>


		<script src="js/manager.js" type="text/javascript"></script>
	</body>
</html>
