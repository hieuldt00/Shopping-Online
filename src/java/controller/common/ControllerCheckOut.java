/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.common;

import dal.DAOCart;
import dal.DAOOrder;
import dal.DAOOrderDetail;
import dal.DAOProduct;
import dal.DAOShop;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Set;
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
public class ControllerCheckOut extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Account account = (Account) session.getAttribute("account");
		DAOCart daoCart = new DAOCart();
		Product product = new Product();
		DAOShop daoShop = new DAOShop();
		Cart c;
		Shop shop;
		double total = 0;
		DAOProduct daoProduct = new DAOProduct();
		ArrayList<Cart> carts = daoCart.getCartsByAccount_id(account.getAccount_id());
		if (carts.isEmpty()) {
			String message = "Need atleast 1 product in Cart before Checkout!";
			request.setAttribute("message", message);
			request.getRequestDispatcher("AddToCart").forward(request, response);
		}
		ArrayList<Shop> shops = new ArrayList<>();
		//htb: key shopID; value arrayList Carts cats
		Hashtable<Integer, ArrayList<Cart>> htbShopCart = new Hashtable<Integer, ArrayList<Cart>>();
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
		request.setAttribute("total", total);
		request.setAttribute("carts", carts);
		request.getRequestDispatcher("Checkout.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DAOOrder daoOrder = new DAOOrder();
		DAOCart daoCart = new DAOCart();
		DAOProduct daoProduct = new DAOProduct();
		DAOOrderDetail daoOrderDetail = new DAOOrderDetail();
		HttpSession session = request.getSession();
		Account account = (Account) session.getAttribute("account");
		Product product = new Product();
		String order_name = request.getParameter("order_name");
		String order_phone = request.getParameter("order_phone");
		String order_address = request.getParameter("order_address");
		ArrayList<Cart> carts = daoCart.getCartsByAccount_id(account.getAccount_id());
		double total = 0;

		//
		DAOShop daoShop = new DAOShop();
		Cart c;
		Shop shop;
		ArrayList<Shop> shops = new ArrayList<>();
		//htb: key shopID; value arrayList Carts cats
		Hashtable<Integer, ArrayList<Cart>> htbShopCart = new Hashtable<Integer, ArrayList<Cart>>();
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

		Set<Integer> keySet = htbShopCart.keySet();
		for (Integer key : keySet) {
			double t = 0;
			ArrayList<Cart> cc = htbShopCart.get(key);
			for (Cart cart : cc) {
				t += cart.getQuantity() * cart.getProduct().getPrice();
			}
			long order_id = 0;
			int od = 0;
			order_id = daoOrder.createOrder(account.getAccount_id(), order_address, order_name, order_phone, t, true, key);
			if (order_id != 0) {

				for (Cart cart : cc) {
					od += daoOrderDetail.addOrderDetail(cart.getProduct_id(), (int) order_id, cart.getQuantity(), cart.getProduct().getPrice(), cart.getQuantity() * cart.getProduct().getPrice());
					daoCart.RemoveProductFromCart(account.getAccount_id(), cart.getProduct_id());
				}
			}
		}

		//
//		for (Cart cart : carts) {
//			product = daoProduct.getProductByProduct_id(cart.getProduct_id());
//			cart.setProduct(product);
//			total += (cart.getQuantity() * product.getPrice());
//		}
//		long order_id = 0;
//		int od = 0;
//		order_id = daoOrder.createOrder(account.getAccount_id(), order_address, order_name, order_phone, total, true,);
//		if (order_id != 0) {
//			for (Cart cart : carts) {
//				od += daoOrderDetail.addOrderDetail(cart.getProduct_id(), (int) order_id, cart.getQuantity(), cart.getProduct().getPrice(), cart.getQuantity() * cart.getProduct().getPrice());
//			}
//			if (od == carts.size()) {
//				daoCart.RemoveAllProductFromCart(account.getAccount_id());
//			}
//		}
		response.sendRedirect("Order");
//		request.getRequestDispatcher("Order").forward(request, response);
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
