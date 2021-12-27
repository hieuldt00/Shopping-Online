/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.admin;

import dal.DAOAdmin;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author duong
 */
public class ControllerAnalysis extends HttpServlet {

	/**
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DAOAdmin daoadmin = new DAOAdmin();

		DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		DateTimeFormatter df2 = DateTimeFormatter.ofPattern("yyyy-MM");

		int selectedYear = Integer.parseInt(request.getParameter("year"));

		String startdate = request.getParameter("startdate");
		String enddate = request.getParameter("enddate");

		LocalDate dateTo = LocalDate.now().plusDays(1);
		LocalDate dateFrom = dateTo.minusDays(30);

		int[] year = {2022, 2021, 2020};
		int[] pageInfo = daoadmin.getPageInfo();
		request.setAttribute("revenueData", daoadmin.getDataRevenue(selectedYear + ""));

		request.setAttribute("revenueDataCategories", daoadmin.getDataCategories(startdate, enddate));
		request.setAttribute("startdate", startdate);
		request.setAttribute("enddate", enddate);

		request.setAttribute("year", year);
		request.setAttribute("selectedYear", selectedYear);

		request.setAttribute("totaluser", pageInfo[0]);
		request.setAttribute("totalshop", pageInfo[1]);
		request.setAttribute("revenue", pageInfo[2]);
		request.setAttribute("topshop", daoadmin.gettop10Shops(dateFrom.format(df), dateTo.format(df)));

		request.setAttribute("bestseller", daoadmin.getBestseller());
		request.getRequestDispatcher("/admin/admin.jsp").forward(request, response);

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
		DAOAdmin daoadmin = new DAOAdmin();

		int[] pageInfo = daoadmin.getPageInfo();

		LocalDate dateTo = LocalDate.now().plusDays(1);
		LocalDate dateFrom = dateTo.minusDays(30);
		DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		int[] year = {2022, 2021, 2020};

		request.setAttribute("revenueData", daoadmin.getDataRevenue(LocalDate.now().getYear() + ""));
		request.setAttribute("year", year);
		request.setAttribute("selectedYear", 2021);

		request.setAttribute("revenueDataCategories", daoadmin.getDataCategories("2021-01-01", dateTo.format(df)));
		request.setAttribute("startdate", "2021-01-01");
		request.setAttribute("enddate", dateTo.format(df));

		request.setAttribute("totaluser", pageInfo[0]);
		request.setAttribute("totalshop", pageInfo[1]);
		request.setAttribute("revenue", pageInfo[2]);
		request.setAttribute("topshop", daoadmin.gettop10Shops(dateFrom.format(df), dateTo.format(df)));

		request.setAttribute("bestseller", daoadmin.getBestseller());

		request.getRequestDispatcher("/admin/admin.jsp").forward(request, response);
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
