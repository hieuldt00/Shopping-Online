/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.account;

import dal.DAOAccount;
import dal.DAOShop;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Account;
import model.Shop;
import model.ShopAccount;

/**
 *
 * @author ToneVn
 */
public class ControllerAccountLogin extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Account account = (Account) session.getAttribute("account");
		if (account == null) {
			String message = (String) request.getAttribute("message");
			if (message == null) {
				message = "";
			}
			request.setAttribute("message", message);
			request.getRequestDispatcher("Login.jsp").forward(request, response);
		} else {
			response.sendRedirect("Home");
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String account_username = request.getParameter("account_username");
		String account_password = request.getParameter("account_password");
		DAOAccount daoAccount = new DAOAccount();

		Account account = null;
		try {
			account = daoAccount.checkAccountLogin(account_username, account_password);
			String message = "";
			if (account != null) {
				HttpSession session = request.getSession(true);
				session.setMaxInactiveInterval(60*60);
				session.setAttribute("account", account);
				message = "Hello " + account.getAccount_name() + "!";
				request.setAttribute("message", message);
				int account_id = 0;
				DAOAccount dao = new DAOAccount();
				DAOShop daoShop = new DAOShop();

				Account acc = (Account) session.getAttribute("account");
				account_id = acc.getAccount_id();
				ShopAccount accountShop = dao.CheckAccountShop(account_id);
				if (accountShop != null) {
					Shop shop = daoShop.GetShopById(accountShop.getShop_id());
					session.setAttribute("shop", shop);
				}
				session.setAttribute("accountShop", accountShop);

				//HOW TO CALL servlet to servlet???
//   		 request.getRequestDispatcher("Home").forward(request, response);
				response.sendRedirect("Home");
			} else {
				message = "Wrong username or password";
				request.setAttribute("message", message);
				request.getRequestDispatcher("Login.jsp").forward(request, response);
//   		 request.getRequestDispatcher("LoginController").forward(request, response);
			}
		} catch (SQLException ex) {
			Logger.getLogger(ControllerAccountLogin.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	@Override
	public String getServletInfo() {
		return "Short description";
	}

}
