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
public class ControllerSearch extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DAOCategory daoCategory = new DAOCategory();
		ArrayList<Category> categories = daoCategory.getAllCategories();
		request.setAttribute("categories", categories);
		request.getRequestDispatcher("search.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String scid = request.getParameter("cate");
		if (scid == null) {
			scid = "-1";
		}
		int cid = Integer.parseInt(scid);

		DAOCategory daoCategory = new DAOCategory();
		DAOProduct daoProduct = new DAOProduct();

		ArrayList<Category> categories = daoCategory.getAllCategories();
		ArrayList<Product> products = null;
		Category category = null;
		String[] categories_selected = null;
		categories_selected = request.getParameterValues("cbCategory");

		if (categories_selected == null) {
			cid = -1;
			request.setAttribute("categories_selected", categories_selected);
		} else {
			int[] arrayCateID = new int[categories_selected.length];
			for (int i = 0; i < categories_selected.length; i++) {
				arrayCateID[i] = Integer.parseInt(categories_selected[i]);
			}
			
			request.setAttribute("arrayCateID", arrayCateID);
			request.setAttribute("categories_selected", categories_selected);
		}

		if (cid == -1) {
			products = daoProduct.getAllProducts();
			request.setAttribute("products", products);
		}
		if (cid > 0) {
			category = daoCategory.getCategoryByCategory_id(cid);
		}
		request.setAttribute("categories", categories);
		request.setAttribute("cid", cid);
		request.getRequestDispatcher("search.jsp").forward(request, response);
	}

	@Override
	public String getServletInfo() {
		return "Short description";
	}// </editor-fold>

}
