/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.vendor;

import dal.DAOAccount;
import dal.DAOShop;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Account;
import model.Order;
import model.Shop;
import model.ShopAccount;

/**
 *
 * @author son
 */
public class ControllerVendorOrderCancelled extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DAOShop dao = new DAOShop();
		DAOAccount daoAccount = new DAOAccount();
		HttpSession session = request.getSession();
		Account acc = (Account) session.getAttribute("account");
		ShopAccount accountShop = daoAccount.CheckAccountShop(acc.getAccount_id());
		Shop shop = dao.getShopByShopID(accountShop.getShop_id());
		List<Order> list = dao.OrderCancell(shop.getShop_id());
		request.setAttribute("listO", list);
		request.getRequestDispatcher("OrderCancelled.jsp").forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//  processRequest(request, response);

	}

	@Override
	public String getServletInfo() {
		return "Short description";
	}// </editor-fold>

}
