/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.admin;

import dal.DAOAccount;
import dal.DAOAdmin;
import dal.DAOShop;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Shop;

/**
 *
 * @author duong
 */
public class ControllerShopManage extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DAOAdmin daoadmin = new DAOAdmin();
        ArrayList<Shop> shops = daoadmin.findshops("");
        shops.remove(0);
        request.setAttribute("listshop", shops);
        request.getRequestDispatcher("/admin/ShopManage.jsp").forward(request, response);
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

        
        String keyword = request.getParameter("keyword");
        if (keyword == null) {
            keyword = "";
        }
        String banparam = request.getParameter("ban");
        if (banparam != null) {
            int shopid = Integer.parseInt(request.getParameter("shopid"));
            boolean ban = Boolean.parseBoolean(banparam);
            DAOShop daoshop = new DAOShop();
            daoshop.updateStatusShop(shopid, ban);
        }
        DAOAdmin daoadmin = new DAOAdmin();
        ArrayList<Shop> shops = daoadmin.findshops(keyword);
        if (!shops.isEmpty()&&shops.get(0).getShop_id() == 0) {
            shops.remove(0);
        }

        request.setAttribute("keyword", keyword);
        request.setAttribute("listshop", shops);
        request.getRequestDispatcher("/admin/ShopManage.jsp").forward(request, response);
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
