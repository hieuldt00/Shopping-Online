/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.common;

import dal.DAOCategory;
import dal.DAOProduct;
import dal.DAOShop;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Category;
import model.Product;
import model.StringUtils;

/**
 *
 * @author duong
 */
public class ControllerSearchFilter extends HttpServlet {

	/**
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		DAOProduct daoProduct = new DAOProduct();
		ArrayList<Product> lp = null;
		int[] minMaxPrice = daoProduct.getMinMaxPrice();
		String name = request.getParameter("name".trim());
		WriteJSONfile(StringUtils.unAccent(daoProduct.getAllProductname()));
		try {
			lp = daoProduct.filter_Product(name, null, 1, "");

			if (lp.isEmpty()) {
				request.setAttribute("fail", "Không tìm thấy sản phẩm nào !");
			}
			int maxPage = (int) Math.ceil(daoProduct.countProductbyname(name) / 6);

			DAOCategory dc = new DAOCategory();
			ArrayList<Category> categories = dc.getAllCategories();
			request.setAttribute("lp", lp);

			request.setAttribute("maxPage", maxPage);
			request.setAttribute("page", 1);
			request.setAttribute("categories", categories);
			request.setAttribute("name", name);
			request.setAttribute("minPrice", minMaxPrice[0]);
			request.setAttribute("maxPrice", minMaxPrice[1]);
			request.getRequestDispatcher("search.jsp").forward(request, response);
		} catch (SQLException ex) {
			Logger.getLogger(ControllerSearch.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
	/**
	 * Handles the HTTP <code>GET</code> method.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		DAOProduct daoProduct = new DAOProduct();
		request.setAttribute("allProduct", daoProduct.getAllProductname());
		request.setCharacterEncoding("UTF-8");
		ArrayList<Product> lp = null;
		int[] minMaxPrice = daoProduct.getMinMaxPrice();
		String name = request.getParameter("name".trim());
		try {
			lp = daoProduct.filter_Product(name, null, 1, "");

			if (lp.isEmpty()) {
				request.setAttribute("fail", "Không tìm thấy sản phẩm nào !");
			}
			int maxPage = (int) Math.ceil(daoProduct.countProductbyname(name) / 6);

			DAOCategory dc = new DAOCategory();
			ArrayList<Category> categories = dc.getAllCategories();
			request.setAttribute("lp", lp);
			request.setAttribute("maxPage", maxPage);
			request.setAttribute("page", 1);

			request.setAttribute("categories", categories);
			request.setAttribute("name", name);
			request.setAttribute("minPrice", minMaxPrice[0]);
			request.setAttribute("maxPrice", minMaxPrice[1]);
			request.getRequestDispatcher("search.jsp").forward(request, response);
		} catch (SQLException ex) {
			Logger.getLogger(ControllerSearchFilter.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	/**
	 * Handles the HTTP <code>POST</code> method.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Returns a short description of the servlet.
	 *
	 * @return a String containing servlet description
	 */
	public void WriteJSONfile(String s) {

		try {
			String filePath = getServletConfig().getServletContext().getRealPath("/js/json") + "/data.json";
			FileWriter writer = new FileWriter(filePath);
			writer.write(s);
			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getServletInfo() {
		return "Short description";
	}// </editor-fold>

}
