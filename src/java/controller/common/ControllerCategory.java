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

		DAOCategory daoCategory = null;
		DAOProduct daoProduct = null;
		ArrayList<Product> products = new ArrayList<>();
		Category category = null;

		String category_id = request.getParameter("category_id");
		if (category_id == null || category_id.trim().length() == 0 || category_id.equals("")) {
			category_id = "ShowAll";
		}

		if (category_id.trim().equalsIgnoreCase("ShowAll")) {
			daoCategory = new DAOCategory();
			ArrayList<Category> categories = daoCategory.getAllCategories();
			request.setAttribute("categories", categories);
			daoProduct = new DAOProduct();
			products = daoProduct.getAllProducts();
			request.setAttribute("products", products);
		} else {
			daoProduct = new DAOProduct();
//			int pageSize = Integer.parseInt(size);
//			int endPage = 0;
//			int count = daoProduct.countProductByCategory(category_selected);
//			if (count % pageSize != 0) {
//				endPage++;
//			}
//			products = daoProduct.PaggingProductByCategory_id(category_selected, Integer.parseInt(index),
//					Integer.parseInt(size), sortby);
			products = daoProduct.getProductsByCategory_id(Integer.parseInt(category_id));
			request.setAttribute("products", products);

			daoCategory = new DAOCategory();
			category = daoCategory.getCategoryByCategory_id(Integer.parseInt(category_id));
			request.setAttribute("category", category);
		}
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
