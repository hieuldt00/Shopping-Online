/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.admin;

import dal.DAOAccount;
import dal.DAOAdmin;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Account;

/**
 *
 * @author duong
 */
public class ControllerViewUserProfile extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int userid = Integer.parseInt(request.getParameter("userid"));
        DAOAdmin daoadmin = new DAOAdmin();
        DAOAccount daoaccount = new DAOAccount();
        Account user = daoaccount.getAccountByAccountId(userid);
        request.setAttribute("user", user);
        request.setAttribute("selectedRole", daoadmin.getRolebyID(userid));
        request.setAttribute("listrole", daoadmin.getAllRole());
        request.getRequestDispatcher("/admin/UserProfile.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int userid = Integer.parseInt(request.getParameter("userid"));
        String[] selectedRole = request.getParameterValues("roles");
        DAOAdmin daoadmin = new DAOAdmin();
        DAOAccount daoaccount = new DAOAccount();
        Account user = daoaccount.getAccountByAccountId(userid);
        request.setAttribute("user", user);
        daoadmin.updateUserRole(userid, selectedRole);
        request.setAttribute("selectedRole", selectedRole);
        request.setAttribute("listrole", daoadmin.getAllRole());
        request.getRequestDispatcher("/admin/UserProfile.jsp").forward(request, response);
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
