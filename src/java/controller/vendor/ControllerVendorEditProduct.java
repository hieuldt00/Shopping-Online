/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.vendor;

import dal.DAOCategory;
import dal.DAOProduct;
import dal.DAOShop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import model.Account;
import model.Category;
import model.Image_Product;
import model.Product;
import model.Shop;
import model.ShopAccount;

/**
 *
 * @author son
 */
@MultipartConfig(
		fileSizeThreshold = 1024 * 1024 * 10,
		maxFileSize = 1024 * 1024 * 50,
		maxRequestSize = 1024 * 1024 * 100
)
public class ControllerVendorEditProduct extends HttpServlet {

	/**
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {
			/* TODO output your page here. You may use following sample code. */
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Servlet ControllerVendorEditProduct</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<h1>Servlet ControllerVendorEditProduct at " + request.getContextPath() + "</h1>");
			out.println("</body>");
			out.println("</html>");
		}
	}

	// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
	/**
	 * Handles the HTTP <code>GET</code> method.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// processRequest(request, response);
		int product_id = Integer.parseInt(request.getParameter("product_id"));
		DAOProduct dao = new DAOProduct();
		Product product = dao.getProductByProduct_id(product_id);
		request.setAttribute("product", product);
		DAOCategory daoC = new DAOCategory();
		List<Category> listCategory = daoC.getAllCategories();
		request.setAttribute("listC", listCategory);
		request.getRequestDispatcher("EditProduct.jsp").forward(request, response);
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
		// processRequest(request, response);
		HttpSession session = request.getSession();
		Shop shop = (Shop) session.getAttribute("shop");
		String product_id = request.getParameter("product_id");
		DAOProduct daoP = new DAOProduct();
		Product product = daoP.getProductByProduct_id(Integer.parseInt(product_id));
		request.setAttribute("product", product);
		DAOCategory daoC = new DAOCategory();
		List<Category> listCategory = daoC.getAllCategories();
		request.setAttribute("listC", listCategory);
		String ProductName = request.getParameter("ProductName");
		String quantity = request.getParameter("quantity");
		if (quantity.matches("\\d+") == false) {
			request.setAttribute("message", "Wrong input");
			request.getRequestDispatcher("EditProduct.jsp").forward(request, response);
		}
		String price = request.getParameter("price");
		if (price.matches("[+]?\\d+\\.?(\\d+)?") == false) {
			request.setAttribute("message", "Wrong input");
			request.getRequestDispatcher("EditProduct.jsp").forward(request, response);
		}
		String status = request.getParameter("status");
		String description = request.getParameter("description");
		String detail = request.getParameter("detail");
		Part filePart = request.getPart("image");
		String imagethumbnail = (String) getFileName(filePart);
		String category = request.getParameter("category");
		Part filePart1 = request.getPart("image1");
		String image1 = (String) getFileName(filePart1);
		Part filePart2 = request.getPart("image2");
		String image2 = (String) getFileName(filePart2);
		Part filePart3 = request.getPart("image3");
		String image3 = (String) getFileName(filePart3);
		Part filePart4 = request.getPart("image4");
		String image4 = (String) getFileName(filePart4);
		DAOShop dao = new DAOShop();
		List<Image_Product> listImage = dao.ImageByProduct_id(Integer.parseInt(product_id));
		if (imagethumbnail.length() == 0) {
			imagethumbnail = shop.getShop_image();
		}
		if (image1.length() == 0) {
			image1 = listImage.get(0).getUrl();
		}
		if (image2.length() == 0) {
			image2 = listImage.get(1).getUrl();
		}
		if (image3.length() == 0) {
			image3 = listImage.get(2).getUrl();
		}
		if (image4.length() == 0) {
			image4 = listImage.get(3).getUrl();
		}
		if (image1.equalsIgnoreCase(image2) || image1.equalsIgnoreCase(image3) || image1.equalsIgnoreCase(image4) || image2.equalsIgnoreCase(image3) || image2.equalsIgnoreCase(image4) || image3.equalsIgnoreCase(image4)) {
			request.setAttribute("message", "Can not add the same image");
			request.getRequestDispatcher("EditProduct.jsp").forward(request, response);
		} else {

			Account acc = (Account) session.getAttribute("account");
			ShopAccount shopacc = dao.getShopID(acc.getAccount_id());
			dao.EditProduct(ProductName, Integer.parseInt(quantity), Double.parseDouble(price), description, Integer.parseInt(status), imagethumbnail, Integer.parseInt(category), shopacc.getShop_id(), detail, Integer.parseInt(product_id));
			dao.EditImage_Product(Integer.parseInt(product_id), image1, listImage.get(0).getUrl());
			dao.EditImage_Product(Integer.parseInt(product_id), image2, listImage.get(1).getUrl());
			dao.EditImage_Product(Integer.parseInt(product_id), image3, listImage.get(2).getUrl());
			dao.EditImage_Product(Integer.parseInt(product_id), image4, listImage.get(3).getUrl());

			request.setAttribute("fileName", uploadFile(request, "image"));
			request.setAttribute("fileName", uploadFile2(request, "image1"));
			request.setAttribute("fileName", uploadFile2(request, "image2"));
			request.setAttribute("fileName", uploadFile2(request, "image3"));
			request.setAttribute("fileName", uploadFile2(request, "image4"));

			response.sendRedirect("VendorStore");
		}
	}

	private String uploadFile(HttpServletRequest request, String image) throws IOException, ServletException {
		String fileName = "";
		try {
			Part filePart = request.getPart(image);
			fileName = (String) getFileName(filePart);
			InputStream inputStream = null;
			OutputStream outputStream = null;

			try {
				File outputFilePath = new File("C:\\Users\\ToneVn\\Desktop\\SWP_ShoppingOnline\\web\\img\\image_product_thumbnail\\" + fileName);
				inputStream = filePart.getInputStream();
				outputStream = new FileOutputStream(outputFilePath);
				int read = 0;
				final byte[] bytes = new byte[1024];
				while ((read = inputStream.read(bytes)) != -1) {
					outputStream.write(bytes, 0, read);
				}
			} catch (Exception e) {
				e.printStackTrace();
				fileName = "";
			} finally {
				if (inputStream != null) {
					inputStream.close();
				}
				if (outputStream != null) {
					outputStream.close();
				}
			}

		} catch (Exception e) {
			fileName = "";
		}
		return fileName;
	}

	private String uploadFile2(HttpServletRequest request, String image) throws IOException, ServletException {
		String fileName = "";
		try {
			Part filePart = request.getPart(image);
			fileName = (String) getFileName(filePart);
			InputStream inputStream = null;
			OutputStream outputStream = null;

			try {
				File outputFilePath = new File("C:\\Users\\ToneVn\\Desktop\\SWP_ShoppingOnline\\web\\img\\image_product\\" + fileName);
				inputStream = filePart.getInputStream();
				outputStream = new FileOutputStream(outputFilePath);
				int read = 0;
				final byte[] bytes = new byte[1024];
				while ((read = inputStream.read(bytes)) != -1) {
					outputStream.write(bytes, 0, read);
				}
			} catch (Exception e) {
				e.printStackTrace();
				fileName = "";
			} finally {
				if (inputStream != null) {
					inputStream.close();
				}
				if (outputStream != null) {
					outputStream.close();
				}
			}

		} catch (Exception e) {
			fileName = "";
		}
		return fileName;
	}

	private String getFileName(Part part) {
		final String partHeader = part.getHeader("content-disposition");
		System.out.println("*****partHeader :" + partHeader);
		for (String content : part.getHeader("content-disposition").split(";")) {
			if (content.trim().startsWith("filename")) {
				return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
			}
		}
		return null;
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
