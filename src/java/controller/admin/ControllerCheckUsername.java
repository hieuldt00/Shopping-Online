/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.admin;

import dal.DAOAdmin;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author duong
 */
public class ControllerCheckUsername extends HttpServlet {

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
            out.println("<title>Servlet ControllerCheckUsername</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControllerCheckUsername at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);
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
        DAOAdmin dao = new DAOAdmin();

        String action = request.getParameter("action");
        switch (action) {
            case "checkun":
                String user = request.getParameter("username");
                response.setContentType("text/plain");
                if (dao.checkUsernameExist(user)) {
                    response.getWriter().write("Username exists !");
                } else {
                    response.getWriter().write("");
                }
                break;
            case "chart2":
                response.setContentType("text/plain");
                String start = request.getParameter("start");
                String end = request.getParameter("end");
                String result = dao.getDataCategories(start, end);
                response.getWriter().write(result);
                break;
            case "chart1":
                response.setContentType("text/plain");
                String year = request.getParameter("year");
                String result1 = dao.getDataRevenue(year);
                response.getWriter().write(result1);
                break;
            case "c":
                response.setContentType("text/plain");
                String status = request.getParameter("st");
                String catname = request.getParameter("cn");
                dao.controlCategory(catname, status);
                response.getWriter().write("Update succesful");
                break;
        }
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


