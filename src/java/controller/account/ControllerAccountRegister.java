/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.account;

import dal.DAOAccount;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Account;

/**
 *
 * @author ToneVn
 */
public class ControllerAccountRegister extends HttpServlet {


	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("Register.jsp");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String account_username = request.getParameter("account_username");
		String account_password = request.getParameter("account_password");
		String account_name = request.getParameter("account_name");
                String email=request.getParameter("email");
		DAOAccount daoAccount = new DAOAccount();
		Account account = new Account(account_username, account_password, account_name,email);
		int n = 0;
		n = daoAccount.addAccount(account);
		String message = "";
		if (n>0) {
			message = "Dang ky thanh cong!";
			request.setAttribute("message", message);
			request.getRequestDispatcher("Login.jsp").forward(request, response);
		}else{
			message = "Dang ky khong thanh cong! Tai Khoan Da Ton Tai";
			request.setAttribute("message", message);
			request.getRequestDispatcher("Register.jsp").forward(request, response);
		}
	}

	@Override
	public String getServletInfo() {
		return "Short description";
	}// </editor-fold>

}
