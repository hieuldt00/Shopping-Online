/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.common;

import dal.DAOCart;
import dal.DAOProduct;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Account;
import model.Cart;

/**
 *
 * @author ToneVn
 */
public class ControllerUpdateCart extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String[] quantity = request.getParameterValues("quantity");
		String[] product_id = request.getParameterValues("product_id");
		System.out.println(quantity[0]);
		System.out.println(product_id[0]);

		HttpSession session = request.getSession();
		Account account = (Account) session.getAttribute("account");
		DAOCart daoCart = new DAOCart();
//		DAOProduct daoProduct = new DAOProduct();
//		ArrayList<Cart> carts = daoCart.getCartsByAccount_id(account.getAccount_id());

		for (int i = 0; i < quantity.length; i++) {
			daoCart.UpdateCart(account.getAccount_id(), Integer.parseInt(product_id[i]), Integer.parseInt(quantity[i]));
		}
		
//		
//		for (int i = 0; i < carts.size(); i++) {
//			carts.get(i).setQuantity(Integer.parseInt(quantity[i]));
//		}
//		for (Cart cart : carts) {
//			daoCart.UpdateCart(account.getAccount_id(), cart.getProduct_id(), cart.getQuantity());
//
//		}
//		carts = daoCart.getCartsByAccount_id(account.getAccount_id());
//		for (Cart cart : carts) {
//			cart.setProduct(daoProduct.getProductByProduct_id(cart.getProduct_id()));
//		}
//		request.setAttribute("carts", carts);
		response.sendRedirect("AddToCart");
//		request.getRequestDispatcher("AddToCart");
	}

	@Override
	public String getServletInfo() {
		return "Short description";
	}// </editor-fold>

}
