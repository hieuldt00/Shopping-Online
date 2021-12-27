/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.vendor;

import dal.DAOShop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import model.Shop;

/**
 *
 * @author son
 */
@MultipartConfig(
		fileSizeThreshold = 1024 * 1024 * 10,
		maxFileSize = 1024 * 1024 * 50,
		maxRequestSize = 1024 * 1024 * 100
)
@WebServlet(name = "ControllerEditProfileShop", urlPatterns = {"/EditProfileShop"})
public class ControllerEditProfileShop extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//  processRequest(request, response);

		String name = request.getParameter("nameshop");
		Part filePart = request.getPart("image");
		HttpSession session = request.getSession();
		DAOShop dao = new DAOShop();
		List<Shop> listShop = dao.listShop();
		Shop shop = (Shop) session.getAttribute("shop");
		for (Shop s : listShop) {
			if (name.equals(s.getShop_name()) && name.equals(shop.getShop_name()) == false) {
				request.setAttribute("message", "The name shop is already exist");
				request.getRequestDispatcher("EditProfileShop.jsp").forward(request, response);
			}
		}
		if (name.equals(shop.getShop_name())) {
			name = shop.getShop_name();
		}

		String imagethumbnail = (String) getFileName(filePart);
		if (imagethumbnail.length() == 0) {
			imagethumbnail = shop.getShop_image();
		}

		dao.editShop(shop.getShop_id(), name, imagethumbnail);
		request.setAttribute("fileName", uploadFile(request));
		response.sendRedirect("VendorStore");

	}

	private String uploadFile(HttpServletRequest request) throws IOException, ServletException {
		String fileName = "";
		try {
			Part filePart = request.getPart("image");
			fileName = (String) getFileName(filePart);
			InputStream inputStream = null;
			OutputStream outputStream = null;

			try {
				File outputFilePath = new File("C:\\Users\\ToneVn\\Desktop\\SWP_ShoppingOnline\\web\\img\\image_shop\\" + fileName);
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
