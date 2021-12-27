/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.admin;

import dal.DAOAccount;
import dal.DAOAdmin;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Account;

/**
 *
 * @author duong
 */
public class ControllerLoginAdmin extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("LoginAdmin.jsp").forward(request, response);
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
		DAOAccount dao = new DAOAccount();
		String username = request.getParameter("user");
		String password = request.getParameter("pass");

		try {
			Account account = dao.checkAdminLogin(username, password);

			if (account != null) {
				HttpSession session = request.getSession(true);
				session.setAttribute("account", account);
				response.sendRedirect("Analysis");
				return;
			} else {
				request.setAttribute("user", username);
				request.setAttribute("fail", "sai tên đăng nhập hoặc mất khẩu");
				response.sendRedirect("LoginAdmin.jsp");
			}
		} catch (SQLException ex) {
			Logger.getLogger(ControllerLoginAdmin.class.getName()).log(Level.SEVERE, null, ex);
		}
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
