/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.account;

import dal.DAOAccount;
import dal.DAOOrderDetail;
import dal.DAOProduct;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Account;
import model.OrderDetail;
import model.Product;

/**
 *
 * @author admin
 */
@WebServlet(name = "ControllerOrderDetail", urlPatterns = {"/OrderDetail"})
public class ControllerOrderDetail extends HttpServlet {

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
            out.println("<title>Servlet ControllerOrderDetail</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControllerOrderDetail at " + request.getContextPath() + "</h1>");
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
        DAOOrderDetail dao = new DAOOrderDetail();
        DAOProduct daoP=new DAOProduct();
        String order_id = request.getParameter("order_id");
        ArrayList<OrderDetail> list =dao.getOderDetailByOrderID(order_id);
        for (OrderDetail o : list) {
           
            Product product = daoP.getProductByProduct_id(o.getProduct_id());
            o.setProduct(product);
           
        }
        double t=0;
        for(OrderDetail i:list)
            t+=i.getTotal();
        request.setAttribute("all", t);
        request.setAttribute("orderdetail", list);
        HttpSession session = request.getSession();
       
        Account a = (Account) session.getAttribute("account");
        int id=a.getAccount_id();
        DAOAccount daoA= new DAOAccount();
        Account b= daoA.getAccountByAccountId(id);
        request.setAttribute("accountprofile", b);
        request.getRequestDispatcher("UserOrderDetail.jsp").forward(request, response);
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
        processRequest(request, response);
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
