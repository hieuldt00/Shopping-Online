/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.common;

import dal.DAOCategory;
import dal.DAOProduct;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Category;
import model.Product;

/**
 *
 * @author ToneVn
 */
public class ControllerHome extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DAOCategory daoCategory = new DAOCategory();
		ArrayList<Category> categories = daoCategory.getAllCategories();
		request.setAttribute("categories", categories);
		DAOProduct daoProduct = new DAOProduct();
		ArrayList<Product> products = daoProduct.getAllProducts();
		request.setAttribute("products", products);		
		request.getRequestDispatcher("Home.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DAOCategory daoCategory = new DAOCategory();
		ArrayList<Category> categories = daoCategory.getAllCategories();
		request.setAttribute("categories", categories);
		DAOProduct daoProduct = new DAOProduct();
		ArrayList<Product> products = daoProduct.getAllProducts();
		request.setAttribute("products", products);

		request.getRequestDispatcher("Home.jsp").forward(request, response);
	}

	@Override
	public String getServletInfo() {
		return "Short description";
	}// </editor-fold>

}
