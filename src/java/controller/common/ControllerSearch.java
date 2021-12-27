///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package controller.common;
//
//import com.google.gson.Gson;
//import dal.DAOAdmin;
//import dal.DAOCategory;
//import dal.DAOProduct;
//import dal.DAOShop;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import model.Category;
//import model.Product;
//import model.Shop;
//import model.StringUtils;
//
///**
// *
// * @author ToneVn
// */
//public class ControllerSearch extends HttpServlet {
//
//	@Override
//	protected void doGet(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//
//		DAOAdmin dao = new DAOAdmin();
//		DAOProduct daoProduct = new DAOProduct();
//		DAOCategory daoCategory = new DAOCategory();
//		WriteJSONfile(StringUtils.unAccent(daoProduct.getAllProductname()));
//		ArrayList<Category> categories = daoCategory.getAllCategories();
//		request.setAttribute("categories", categories);
//		request.setAttribute("page", 1);
//		request.setAttribute("maxPage", (int) Math.ceil(daoProduct.countAllProduct() / 6));
//		request.setAttribute("lp", daoProduct.getAllProductsPaging(1, ""));
//		int[] minMaxPrice = daoProduct.getMinMaxPrice();
//		request.setAttribute("minPrice", minMaxPrice[0]);
//		request.setAttribute("maxPrice", minMaxPrice[1]);
//		request.getRequestDispatcher("search.jsp").forward(request, response);
//	}
//
//	@Override
//	protected void doPost(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		response.setContentType("text/html;charset=UTF-8");
//		request.setCharacterEncoding("UTF-8");
//		DAOProduct daoProduct = new DAOProduct();
//		ArrayList<Product> lp = null;
//		DAOShop daoShop = new DAOShop();
//
//		String categories_selectedString = request.getParameter("cbCategory");
//		String[] categories_selectedParam = categories_selectedString.split(" ");
//		if (categories_selectedString.isEmpty()) {
//			categories_selectedParam = null;
//		}
//		int[] categories_selected = null;
//		if (categories_selectedParam != null) {
//			categories_selected = new int[categories_selectedParam.length];
//			for (int i = 0; i < categories_selectedParam.length; i++) {
//				categories_selected[i] = Integer.parseInt(categories_selectedParam[i]);
//			}
//		}
//
//		int page = Integer.parseInt(request.getParameter("page"));
//		String action = request.getParameter("action");
//		String fromParam = request.getParameter("from");
//		String toParam = request.getParameter("to");
//		String sort = request.getParameter("sort");
//		int[] minMaxPrice = daoProduct.getMinMaxPrice();
//		int from = minMaxPrice[0];
//		Integer to = null;
//
//		if (!fromParam.equals("")) {
//			from = (int) Double.parseDouble(fromParam);
//			request.setAttribute("from", from);
//			to = minMaxPrice[1];
//		}
//		if (!toParam.equals("")) {
//			to = (int) Double.parseDouble(toParam);
//			request.setAttribute("to", to);
//		}
//		String name = request.getParameter("name".trim());
//
////
////        ArrayList<Shop> listShop = daoShop.getAllShops(name);
////
////        if (!listShop.isEmpty()) {
////            request.setAttribute("shop", listShop.get(0));
////        }
//		try {
//
//			if (to == null) {
//				if (name.equals("") && categories_selected != null) {
//					lp = daoProduct.getProductsByCategory_id(categories_selected, page, sort);
//				} else {
//					lp = daoProduct.filter_Product(name, categories_selected, page, sort);
//				}
//			} else {
//				lp = daoProduct.filter_ProductbyPrice(name, categories_selected, from, to, page, sort);
//			}
//
//			if (lp.isEmpty()) {
//				request.setAttribute("fail", "Không tìm thấy sản phẩm !");
//			}
//
//		} catch (SQLException ex) {
//			Logger.getLogger(ControllerSearch.class.getName()).log(Level.SEVERE, null, ex);
//		}
//		if (action.equals("1")) {
//			Gson g = new Gson();
//			int maxPage = (int) Math.ceil(daoProduct.countProductByCategory(name, categories_selected, from, to) / 6);
//			String[] rs = new String[2];
//			rs[0] = generateProduct(lp);
//			rs[1] = generatePage(page, maxPage);
//			response.getWriter().write(g.toJson(rs));
//		} else {
//			DAOCategory dc = new DAOCategory();
//			ArrayList<Category> categories = dc.getAllCategories();
//			request.setAttribute("lp", lp);
//			request.setAttribute("arrayCateID", categories_selected);
//			request.setAttribute("categories", categories);
//			request.setAttribute("name", name);
//			request.setAttribute("minPrice", minMaxPrice[0]);
//			request.setAttribute("maxPrice", minMaxPrice[1]);
//			request.getRequestDispatcher("search.jsp").forward(request, response);
//		}
//
//	}
//
//	public void GsonWritefile(ArrayList<String> ls) {
//		Gson g = new Gson();
//		String json = g.toJson(ls);
//		try {
//			String filePath = getServletConfig().getServletContext().getRealPath("/js/json") + "/data.json";
//			FileWriter writer = new FileWriter(filePath);
//			writer.write(json);
//			writer.close();
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//
//	public void WriteJSONfile(String s) {
//
//		try {
//			String filePath = getServletConfig().getServletContext().getRealPath("/js/json") + "/data.json";
//			FileWriter writer = new FileWriter(filePath);
//			writer.write(s);
//			writer.close();
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//
//	public String generateProduct(ArrayList<Product> lp) {
//		String rs = "";
//		for (Product p : lp) {
//			rs += "<div class=\"col-sm-4\">\n"
//					+ "                                    <div class=\"product\">\n"
//					+ "                                        <div class=\"product-img\">\n"
//					+ "                                            <img src=\"./img/product01.png\" alt=\"\">\n"
//					+ "                                            <div class=\"product-label\">\n"
//					+ "                                                <span class=\"sale\">-30%</span>\n"
//					+ "                                                <span class=\"new\">NEW</span>\n"
//					+ "                                            </div>\n"
//					+ "                                        </div>\n"
//					+ "                                        <div class=\"product-body\">\n"
//					+ "                                            <p class=\"product-category\">" + p.getCategory_id() + "</p>\n"
//					+ "                                            <h3 class=\"product-name\"><a href=\"#\">" + p.getProduct_name() + "</a></h3>\n"
//					+ "                                            <h4 class=\"product-price\">" + p.getPrice() + "$</h4>\n"
//					+ "                                            <div class=\"product-rating\">\n"
//					+ "                                                <i class=\"fa fa-star\"></i>\n"
//					+ "                                                <i class=\"fa fa-star\"></i>\n"
//					+ "                                                <i class=\"fa fa-star\"></i>\n"
//					+ "                                                <i class=\"fa fa-star\"></i>\n"
//					+ "                                                <i class=\"fa fa-star\"></i>\n"
//					+ "                                            </div>\n"
//					+ "                                            <div class=\"product-btns\">\n"
//					+ "                                                <button class=\"add-to-wishlist\"><i class=\"fa fa-heart-o\"></i><span class=\"tooltipp\">add to wishlist</span></button>\n"
//					+ "                                                <button class=\"add-to-compare\"><i class=\"fa fa-exchange\"></i><span class=\"tooltipp\">add to compare</span></button>\n"
//					+ "                                                <button class=\"quick-view\"><i class=\"fa fa-eye\"></i><span class=\"tooltipp\">quick view</span></button>\n"
//					+ "                                            </div>\n"
//					+ "                                        </div>\n"
//					+ "                                        <div class=\"add-to-cart\">\n"
//					+ "                                            <button class=\"add-to-cart-btn\"><i class=\"fa fa-shopping-cart\"></i> add to cart</button>\n"
//					+ "                                        </div>\n"
//					+ "                                    </div>\n"
//					+ "                                </div>";
//		}
//		return rs;
//	}
//
//	public String generatePage(int page, int maxPage) {
//		String rs = "";
//		rs = "<span class=\"store-qty store-pagination\">You are in <b>" + page + "</b> of " + maxPage + " total page</span>\n"
//				+ "                        <ul class=\"store-pagination storepage\">\n";
//		if (page - 1 > 0) {
//			rs += "<li class=\"page-item \"  ><a class=\"page-link\" href=\"\" >Pre</a></li>\n";
//
//		} else {
//			rs += "<li class=\"page-item disabled \"  ><a class=\"page-link\" href=\"\" >Pre</a></li>\n";
//
//		}
//		if (page - 2 > 0) {
//			rs += "<li class=\"page-item \"><a class=\"page-link\" href=\"\">" + (page - 2) + "</a></li>\n";
//		}
//		if (page - 1 > 0) {
//			rs += "<li class=\"page-item \"><a class=\"page-link\" href=\"\">" + (page - 1) + "</a></li>\n";
//		}
//
//		rs += "<li class=\"page-item active\"><a class=\"page-link\" href=\"\">" + page + "</a></li>\n";
//		if (page + 1 <= maxPage) {
//			rs += " <li class=\"page-item\"><a class=\"page-link\" href=\"\">" + (page + 1) + "</a></li>\n";
//		}
//		if (page + 2 <= maxPage) {
//			rs += " <li class=\"page-item\"><a class=\"page-link\" href=\"\">" + (page + 2) + "</a></li>\n";
//		}
//
//		if (page >= maxPage) {
//			rs += "<li class=\"page-item disabled \"  ><a class=\"page-link\" href=\"#\" >Next</a></li>\n";
//		} else {
//			rs += "<li class=\"page-item \"  ><a class=\"page-link\" href=\"#\" >Next</a></li>\n";
//
//		}
//		return rs;
//	}
//
//	@Override
//	public String getServletInfo() {
//		return "Short description";
//	}// </editor-fold>
//}
