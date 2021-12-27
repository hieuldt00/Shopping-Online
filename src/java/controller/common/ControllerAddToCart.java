/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.common;

import dal.DAOCart;
import dal.DAOProduct;
import dal.DAOShop;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Hashtable;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Account;
import model.Cart;
import model.Product;
import model.Shop;

/**
 *
 * @author ToneVn
 */
public class ControllerAddToCart extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Account account = (Account) session.getAttribute("account");
		DAOCart daoCart = new DAOCart();
		DAOProduct daoProduct = new DAOProduct();
		DAOShop daoShop = new DAOShop();
		Product product = new Product();
		Cart c;
		Shop shop;
		double total = 0;
		if (account == null) {
			response.sendRedirect("Login.jsp");
		} else {
			//htb: key shopID; value arrayList Carts cats
			Hashtable<Integer, ArrayList<Cart>> htbShopCart = new Hashtable<Integer, ArrayList<Cart>>();
			ArrayList<Cart> carts = daoCart.getCartsByAccount_id(account.getAccount_id());
			for (Cart cart : carts) {
				ArrayList<Cart> cats = new ArrayList<>();
				c = new Cart();
				product = daoProduct.getProductByProduct_id(cart.getProduct_id());
				shop = daoShop.getShopByShopID(product.getShop_id());
				int shop_id = shop.getShop_id();
				product.setShop(shop);
				c.setProduct(product);
				c.setProduct_id(cart.getProduct_id());
				c.setAccount_id(cart.getAccount_id());
				c.setQuantity(cart.getQuantity());
				if (htbShopCart.containsKey(shop.getShop_id())) {
					cats = htbShopCart.get(shop.getShop_id());
					cats.add(c);
					htbShopCart.put(shop_id, cats);
				} else {
					cats.add(c);
					htbShopCart.put(shop_id, cats);
				}
				total += (cart.getQuantity() * product.getPrice());
			}
			request.setAttribute("htbShopCart", htbShopCart);
			request.setAttribute("carts", carts);
			request.getRequestDispatcher("Cart.jsp").forward(request, response);
		}
//		HttpSession session = request.getSession();
//		Account account = (Account) session.getAttribute("account");
//
//		DAOCart daoCart = new DAOCart();
//		DAOProduct daoProduct = new DAOProduct();
//		//int product_id = Integer.parseInt(request.getParameter("btn_addToCart"));
//		//Product product = daoProduct.getProductByProduct_id(product_id);
//
//		//if guess add item to cart, login required:
//		if (account == null) {
//			response.sendRedirect("Login.jsp");
//		} else {
//			//get list cart from db:
//			ArrayList<Cart> carts = daoCart.getCartsByAccount_id(account.getAccount_id());
//			//if list cart == null -> add new cart into db with quantity = 1 if from HomePage /or = quantity in Product/Cart page
//			for (Cart cart : carts) {
//				cart.setProduct(daoProduct.getProductByProduct_id(cart.getProduct_id()));				
//			}
//			request.setAttribute("carts", carts);
//			request.getRequestDispatcher("Cart.jsp").forward(request, response);
//		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Account account = (Account) session.getAttribute("account");

		DAOCart daoCart = new DAOCart();
		DAOProduct daoProduct = new DAOProduct();
		int product_id = Integer.parseInt(request.getParameter("btn_addToCart"));
		Product product = daoProduct.getProductByProduct_id(product_id);

		//if guess add item to cart, login required:
		if (account == null) {
			response.sendRedirect("Login.jsp");
		}

		//if user already logged in:
		if (account != null) {
			//get list cart from db:
			ArrayList<Cart> carts = daoCart.getCartsByAccount_id(account.getAccount_id());
			//if list cart == null -> add new cart into db with quantity = 1 if from HomePage /or = quantity in Product/Cart page
			String quantity = request.getParameter("quantity");
			if (quantity == null) {
				quantity = "1";
			}
			//if user dont have any product in cart:
			if (carts.isEmpty()) {
				daoCart.AddProductToCart(account.getAccount_id(), product_id, Integer.parseInt(quantity));

			} else {
				boolean flag = false;
				for (Cart cart : carts) {
					//if carts already have product
					if (cart.getProduct_id() == product_id) {
						flag = true;
						cart.setQuantity(cart.getQuantity() + Integer.parseInt(quantity));
						daoCart.UpdateCart(account.getAccount_id(), product_id, cart.getQuantity());
					}
				}
				if (!flag) {
					daoCart.AddProductToCart(account.getAccount_id(), product_id, Integer.parseInt(quantity));
				}
			}
			carts = daoCart.getCartsByAccount_id(account.getAccount_id());
			for (Cart cart : carts) {
				cart.setProduct(daoProduct.getProductByProduct_id(cart.getProduct_id()));
			}
			request.setAttribute("carts", carts);
			doGet(request, response);
//			request.getRequestDispatcher("Cart.jsp").forward(request, response);
		}

//		response.sendRedirect("checkout.jsp");
	}

	@Override
	public String getServletInfo() {
		return "Short description";
	}// </editor-fold>

}
