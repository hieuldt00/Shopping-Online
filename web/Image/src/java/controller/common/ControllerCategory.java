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
public class ControllerCategory extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int category_id = Integer.parseInt(request.getParameter("category_id"));

		DAOProduct daoProduct = new DAOProduct();
		ArrayList<Product> products = daoProduct.getProductsByCategory_id(category_id);
		request.setAttribute("products", products);

		DAOCategory daoCategory = new DAOCategory();
		Category category = daoCategory.getCategoryByCategory_id(category_id);
		request.setAttribute("category", category);
		
		request.getRequestDispatcher("Category.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	/**
	 * Returns a short description of the servlet.
	 *
	 * @return a String containing servlet description
	 */
	@Override
	public String getServletInfo() {
		return "Short description";
	}// </editor-fold>

}
