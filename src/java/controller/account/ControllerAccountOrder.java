/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.account;

import dal.DAOAccount;
import dal.DAOOrder;
import dal.DAOShop;
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
import model.Order;
import model.Shop;

/**
 *
 * @author admin
 */
@WebServlet(name = "ControllerAccountOrder", urlPatterns = {"/Order"})
public class ControllerAccountOrder extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account a = (Account) session.getAttribute("account");
        int account_id = a.getAccount_id();
        DAOOrder daoOrder = new DAOOrder();
        DAOShop  daoShop = new DAOShop();
        ArrayList<Order> orders = daoOrder.getOrderByAccountID(account_id);
        for (Order o : orders) {
           Shop shop = daoShop.getShopByShopID(o.getShop_id());
            o.setShop(shop);
        }
        request.setAttribute("orders", orders);
        int id=a.getAccount_id();
        DAOAccount dao= new DAOAccount();
        Account b= dao.getAccountByAccountId(id);
        request.setAttribute("accountprofile", b);
        request.getRequestDispatcher("UserOrder.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        processRequest(request, response);
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
