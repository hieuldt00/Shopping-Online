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
import model.Product;
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
public class ControllerVendorAddProduct extends HttpServlet {

 
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
        	throws ServletException, IOException {
    	//  processRequest(request, response);
    	DAOCategory dao = new DAOCategory();
   	 
    	List<Category> listCategory = dao.getAllCategories();
    	request.setAttribute("listC", listCategory);
    	request.getRequestDispatcher("AddProduct.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
        	throws ServletException, IOException {
    	//  processRequest(request, response);
    	String ProductName = request.getParameter("ProductName");
    	DAOCategory daoC = new DAOCategory(); 	 
    	List<Category> listCategory = daoC.getAllCategories();
    	request.setAttribute("listC", listCategory);
    	String quantity = request.getParameter("quantity");
    	if (quantity.matches("\\d+") == false) {
        	request.setAttribute("message", "Wrong input Quantity");
        	request.getRequestDispatcher("AddProduct.jsp").forward(request, response);
    	}
    	String price = request.getParameter("price");
    	if (price.matches("[+]?\\d+\\.?(\\d+)?") == false) {
        	request.setAttribute("message", "Wrong input price");
        	request.getRequestDispatcher("AddProduct.jsp").forward(request, response);
    	}
    	String description = request.getParameter("description");
    	String detail = request.getParameter("detail");
    	String status = request.getParameter("status");
    	Part filePart = request.getPart("image");
    	String imagethumbnail = (String) getFileName(filePart);
    	Part filePart1 = request.getPart("image1");
    	String image1 = (String) getFileName(filePart1);
    	Part filePart2 = request.getPart("image2");
    	String image2 = (String) getFileName(filePart2);
    	Part filePart3 = request.getPart("image3");
    	String image3 = (String) getFileName(filePart3);
    	Part filePart4 = request.getPart("image4");
    	String image4 = (String) getFileName(filePart4);
    	if(image1.equalsIgnoreCase(image2)||image1.equalsIgnoreCase(image3)||image1.equalsIgnoreCase(image4)||image2.equalsIgnoreCase(image3)||image2.equalsIgnoreCase(image4)||image3.equalsIgnoreCase(image4)){
    	request.setAttribute("message", "Can not add the same image");
    	request.getRequestDispatcher("AddProduct.jsp").forward(request, response);
    	}
    	else{
    	String category = request.getParameter("category");
    	List<String> list = new ArrayList<>();
    	list.add(image1);
    	list.add(image2);
    	list.add(image3);
    	list.add(image4);
 	 
    	DAOShop dao = new DAOShop();
	 
    	HttpSession session = request.getSession();
    	Account acc = (Account) session.getAttribute("account");
    	ShopAccount shopacc = dao.getShopID(acc.getAccount_id());
    	dao.AddNewProduct(ProductName, Integer.parseInt(quantity), Double.parseDouble(price), description, detail, 1, Integer.parseInt(category), shopacc.getShop_id(), imagethumbnail);
    	Product product = dao.getLastProduct();
    	for (String s : list) {
        	dao.AddNewImageProduct(s,product.getProduct_id());
    	}

    	request.setAttribute("fileName", uploadFile(request, "image"));
    	request.setAttribute("fileName", uploadFile2(request, "image1"));
    	request.setAttribute("fileName", uploadFile2(request, "image2"));
    	request.setAttribute("fileName", uploadFile2(request, "image3"));
    	request.setAttribute("fileName", uploadFile2(request, "image"));

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


