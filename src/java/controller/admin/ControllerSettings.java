/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.admin;

import dal.DAOAdmin;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Category;
//import org.apache.commons.fileupload.FileItem;
//import org.apache.commons.fileupload.FileUploadException;
//import org.apache.commons.fileupload.disk.DiskFileItemFactory;
//import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author duong
 */
public class ControllerSettings extends HttpServlet {

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {
			/* TODO output your page here. You may use following sample code. */
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Servlet ControllerSettings</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<h1>Servlet ControllerSettings at " + request.getContextPath() + "</h1>");
			out.println("</body>");
			out.println("</html>");
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DAOAdmin dao = new DAOAdmin();
		request.setAttribute("listcategory", dao.getAllCategories());
		request.getRequestDispatcher("admin/Settings.jsp").forward(request, response);
	}

	private boolean isMultipart;
	private String filePath;
	private int maxFileSize = 50 * 1024 * 100;
	private int maxMemSize = 4 * 1024;
	private File file;

	public void init() {
		filePath = getServletConfig().getServletContext().getRealPath("/img/image_category/");
	}

	@Override
	public void doPost(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, java.io.IOException {
		response.setContentType("text/html;charset=UTF-8");
//		DAOAdmin dao = new DAOAdmin();
//		String categoryname = request.getParameter("categoryname");
//		String fileName = "";
//
//		response.getWriter().write("Success");
//		// Check that we have a file upload request
//		isMultipart = ServletFileUpload.isMultipartContent(request);
//
//		if (!isMultipart) {
//			return;
//		}
//		DiskFileItemFactory factory = new DiskFileItemFactory();
//		// maximum size that will be stored in memory
//		factory.setSizeThreshold(maxMemSize);
//		// Location to save data that is larger than maxMemSize.
//		factory.setRepository(new File("c:\\temp"));      // Create a new file upload handler
//		ServletFileUpload upload = new ServletFileUpload(factory);
//		// maximum file size to be uploaded.
//		upload.setSizeMax(maxFileSize);
//
//		// Parse the request to get file items.
//		List<FileItem> fileItems = null;
//		try {
//			fileItems = upload.parseRequest(request);
//		} catch (FileUploadException ex) {
//		}
//
//		// Process the uploaded file items
//		Iterator i = fileItems.iterator();
//
//		while (i.hasNext()) {
//			FileItem fi = (FileItem) i.next();
//			if (!fi.isFormField()) {
//				// Get the uploaded file parameters
//				String fieldName = fi.getFieldName();
//				fileName = fi.getName();
//				String contentType = fi.getContentType();
//				boolean isInMemory = fi.isInMemory();
//				long sizeInBytes = fi.getSize();
//				// Write the file
//				if (fileName.lastIndexOf("\\") >= 0) {
//					file = new File(filePath
//							+ fileName.substring(fileName.lastIndexOf("\\")));
//				} else {
//					file = new File(filePath + "/"
//							+ fileName.substring(fileName.lastIndexOf("\\") + 1));
//				}
//				try {
//					fi.write(file);
//					dao.insertCategory(categoryname, fileName);
//				} catch (Exception ex) {
//				}
//			}
//		}
//
//		ArrayList<Category> lc = dao.getAllCategories();
//		String result = "";
//		for (Category c : lc) {
//			if (c.getStatus() == 0) {
//				result += "<tr scope=\"row\" class=\"active\">";
//			} else {
//				result += "<tr scope=\"row\" class=\"\">";
//			}
//			result += "<td>"
//					+ c.getCategory_id() + "</td>"
//					+ "<td class=\"pl-0\">"
//					+ "<div class=\"d-flex align-items-center\">\n"
//					+ "<a href=\"#\" class=\"name\">" + c.getCategory_name() + "</a>"
//					+ "</div>"
//					+ "</td>"
//					+ "<td>"
//					+ "<img src=\"./img/image_category/" + c.getCategory_image() + "\" width=\"90\">"
//					+ "</td>"
//					+ "<td>"
//					+ "<label class=\"custom-control ios-switch\">\n";
//			if (c.getStatus() == 1) {
//				result += "<input type=\"checkbox\" class=\"ios-switch-control-input\" checked=\"\">\n"
//						+ "<span class=\"ios-switch-control-indicator\"></span>\n"
//						+ "</label>"
//						+ "</td>"
//						+ "</tr>";
//			} else {
//				result += "<input type=\"checkbox\" class=\"ios-switch-control-input\">"
//						+ " <span class=\"ios-switch-control-indicator\"></span>"
//						+ " </label>"
//						+ "</td>"
//						+ "</tr>";
//			}
//		}
//		response.getWriter().write(result);
	}
}

/**
 * Returns a short description of the servlet.
 *
 * @return a String containing servlet description
 */
