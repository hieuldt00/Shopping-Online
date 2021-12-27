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
public class ControllerRemoveCart extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("Action");
		if (action.equals("Remove")) {
			String product_id = request.getParameter("product_id");
			DAOCart daoCart = new DAOCart();
			DAOProduct daoProduct = new DAOProduct();
			HttpSession session = request.getSession();
			Account account = (Account) session.getAttribute("account");
			ArrayList<Cart> carts = daoCart.getCartsByAccount_id(account.getAccount_id());
			if (!carts.isEmpty()) {
				for (Cart cart : carts) {
					if (cart.getProduct_id() == Integer.parseInt(product_id)) {
						daoCart.RemoveProductFromCart(account.getAccount_id(), Integer.parseInt(product_id));
					}
				}
				carts = daoCart.getCartsByAccount_id(account.getAccount_id());
				for (Cart cart : carts) {
					cart.setProduct(daoProduct.getProductByProduct_id(cart.getProduct_id()));
				}
				request.setAttribute("carts", carts);
				request.getRequestDispatcher("AddToCart").forward(request, response);
			} else {
				carts = daoCart.getCartsByAccount_id(account.getAccount_id());
				for (Cart cart : carts) {
					cart.setProduct(daoProduct.getProductByProduct_id(cart.getProduct_id()));
				}
				request.setAttribute("carts", carts);			
				request.getRequestDispatcher("AddToCart").forward(request, response);
			}
		}
		if (action.equals("RemoveAll")) {
			DAOCart daoCart = new DAOCart();
			DAOProduct daoProduct = new DAOProduct();
			HttpSession session = request.getSession();
			Account account = (Account) session.getAttribute("account");
			ArrayList<Cart> carts = daoCart.getCartsByAccount_id(account.getAccount_id());
			if (!carts.isEmpty()) {
				daoCart.RemoveAllProductFromCart(account.getAccount_id());
				carts = daoCart.getCartsByAccount_id(account.getAccount_id());
				for (Cart cart : carts) {
					cart.setProduct(daoProduct.getProductByProduct_id(cart.getProduct_id()));
				}
				request.setAttribute("carts", carts);
				request.getRequestDispatcher("Cart.jsp").forward(request, response);
			} else {
				carts = daoCart.getCartsByAccount_id(account.getAccount_id());
				for (Cart cart : carts) {
					cart.setProduct(daoProduct.getProductByProduct_id(cart.getProduct_id()));
				}
				request.setAttribute("carts", carts);
				request.getRequestDispatcher("AddToCart").forward(request, response);
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	@Override
	public String getServletInfo() {
		return "Short description";
	}// </editor-fold>

}
