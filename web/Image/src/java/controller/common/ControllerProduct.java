/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.common;

import dal.DAOCategory;
import dal.DAOImage_Product;
import dal.DAOProduct;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Category;
import model.Image_Product;
import model.Product;

/**
 *
 * @author ToneVn
 */
public class ControllerProduct extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int product_id = Integer.parseInt(request.getParameter("product_id"));
		DAOProduct daoProduct = new DAOProduct();
		Product product = daoProduct.getProductByProduct_id(product_id);
		request.setAttribute("product", product);
		
		DAOImage_Product daoImage_Product = new DAOImage_Product();
		ArrayList<Image_Product> image_products = daoImage_Product.getAllImage_ProductsByProductID(product_id);
		request.setAttribute("image_products", image_products);
		
		DAOCategory daoCategory = new DAOCategory();
		Category category = daoCategory.getCategoryByProduct_id(product_id);
		request.setAttribute("category", category);
		
		request.getRequestDispatcher("Product.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}


	@Override
	public String getServletInfo() {
		return "Short description";
	}// </editor-fold>

}
