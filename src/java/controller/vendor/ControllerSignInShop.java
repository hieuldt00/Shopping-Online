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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Account;
import model.Shop;

/**
 *
 * @author son
 */
public class ControllerSignInShop extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
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
            out.println("<title>Servlet ControllerSignInShop</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControllerSignInShop at " + request.getContextPath() + "</h1>");
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
        //   processRequest(request, response);
        HttpSession session = request.getSession();
        DAOAccount dao = new DAOAccount();
        Account acc = (Account) session.getAttribute("account");
        String Email = acc.getEmail();
        String phone = acc.getAccount_phone();
        if (Email.equals("")) {
            Email = null;
        }
        if (phone.equals("")) {
            phone = null;
        }
        request.setAttribute("Email", Email);
        request.setAttribute("Sdt", phone);
        request.getRequestDispatcher("SignInToBecomeVendor.jsp").forward(request, response);
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
        //  processRequest(request, response);
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        String nameShop = request.getParameter("nameShop");
        String Email = request.getParameter("Email");
        String sdt = request.getParameter("Sdt");
        if (Email == null) {
            Email = account.getEmail();
            request.setAttribute("Email", Email);
        }
        if (sdt == null) {
            sdt = account.getAccount_phone();
             request.setAttribute("Sdt", sdt);
        }
        DAOAccount dao = new DAOAccount();
        DAOShop daoShop = new DAOShop();
        List<Shop> listShop = daoShop.listShop();       
        for (Shop shop : listShop) {
            if (nameShop.equalsIgnoreCase(shop.getShop_name())) {
                request.setAttribute("message2", "Tên shop đã tồn tại");                            
                request.getRequestDispatcher("SignInToBecomeVendor.jsp").forward(request, response);
            }
        }
        if (sdt.matches("\\d+") == false || sdt.length() < 10) {
            request.setAttribute("message", "Số điện thoại phải đủ 10 chữ số và không chứa chữ hoặc kí tự");                        
            request.getRequestDispatcher("SignInToBecomeVendor.jsp").forward(request, response);
        }
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(Email);
        if(matcher.matches()==false){
             request.setAttribute("message", "Email của bạn chưa đúng ");                        
            request.getRequestDispatcher("SignInToBecomeVendor.jsp").forward(request, response);
        }
       
        dao.InfoShop(account.getAccount_id(), nameShop, sdt, Email);
       request.getRequestDispatcher("ManageShop.jsp").forward(request, response);
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
