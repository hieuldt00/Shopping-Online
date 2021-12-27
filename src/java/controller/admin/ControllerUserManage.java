/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.admin;

import dal.DAOAccount;
import dal.DAOAdmin;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Account;
import model.Role;

/**
 *
 * @author duong
 */
public class ControllerUserManage extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DAOAdmin daoadmin = new DAOAdmin();
		HttpSession session = request.getSession(true);
		Account currentAccount = (Account) session.getAttribute("account");
		ArrayList<Role> roles = daoadmin.getAllRole();
		ArrayList<Account> listUsers = daoadmin.findAccount("", "", "", null, currentAccount.getAccount_id());

		listUsers.remove(currentAccount);
		request.setAttribute("listrole", roles);
		request.setAttribute("listuser", listUsers);

		request.getRequestDispatcher("/admin/UserManage.jsp").forward(request, response);
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
		String gender = request.getParameter("gender");
		String status = request.getParameter("status");
		String keyword = request.getParameter("keyword");
		String[] selectedRole = request.getParameterValues("roles");
		if (keyword == null) {
			keyword = "";
		}

		DAOAdmin daoadmin = new DAOAdmin();
		String banparam = request.getParameter("ban");
		if (banparam != null) {
			int userid = Integer.parseInt(request.getParameter("userid"));
			boolean ban = Boolean.parseBoolean(banparam);
			DAOAccount daoaccount = new DAOAccount();
			daoaccount.updateStatusUser(userid, ban);
		}
		HttpSession session = request.getSession(true);
		Account currentAccount = (Account) session.getAttribute("account");
		ArrayList<Account> listUsers = daoadmin.findAccount(keyword, gender, status, selectedRole, currentAccount.getAccount_id());
		listUsers.remove(currentAccount);
		request.setAttribute("listuser", listUsers);

		request.setAttribute("selectedRole", selectedRole);
		request.setAttribute("listrole", daoadmin.getAllRole());
		request.setAttribute("keyword", keyword);
		request.setAttribute("gender", gender);
		request.setAttribute("status", status);
		request.getRequestDispatcher("/admin/UserManage.jsp").forward(request, response);
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
