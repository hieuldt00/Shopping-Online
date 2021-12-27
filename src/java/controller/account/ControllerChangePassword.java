/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.account;

import dal.DAOAccount;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Account;

/**
 *
 * @author son
 */
@WebServlet(name = "ControllerChangePassword", urlPatterns = {"/ChangePassword"})
public class ControllerChangePassword extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       // processRequest(request, response);
       HttpSession session = request.getSession();
       
        Account a = (Account) session.getAttribute("account");
        int id=a.getAccount_id();
        DAOAccount dao= new DAOAccount();
        Account b= dao.getAccountByAccountId(id);
        request.setAttribute("accountprofile", b);
       request.getRequestDispatcher("ChangePassword.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      //  processRequest(request, response);
      DAOAccount dao = new DAOAccount();
        HttpSession session = request.getSession(true);
        Account a = (Account) session.getAttribute("account");
        String CurrentPassword = request.getParameter("CurrentPass");
        if(CurrentPassword.equals(a.getAccount_password())){
            String newPassword = request.getParameter("password");
            if(newPassword.equals(a.getAccount_password())){
                request.setAttribute("fail", "Mật khẩu không được trùng với mật khẩu cũ");
            }
            else{
                if(newPassword.length()<8){
                    request.setAttribute("fail", "Mật khẩu phải dài hơn 7 ký tự");
                }
                else{
                dao.updatePassword(a.getAccount_id(), newPassword);
                a.setAccount_password(newPassword);
                session.setAttribute("account", a);
                request.setAttribute("succesful", "Thay đổi mật khẩu thành công !");
                }}
        }else{
            request.setAttribute("fail", "Mật khẩu cũ không chính xác !");
        }
        
        int id=a.getAccount_id();
        
        Account b= dao.getAccountByAccountId(id);
        request.setAttribute("accountprofile", b);
        request.getRequestDispatcher("ChangePassword.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
