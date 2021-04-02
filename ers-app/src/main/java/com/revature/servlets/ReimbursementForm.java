package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.model.Employee;
import com.revature.services.EmployeeService;


public class ReimbursementForm extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ReimbursementForm() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		Employee employee = new Employee();
		boolean isPending = true;
		boolean isApproved = false;
		String reimbursementDescription = request.getParameter("reimbursementDescription");
		double reimbursementAmount = Double.valueOf(request.getParameter("reimbursementAmount"));
		int reimbursementEmployeeId = Integer.valueOf(request.getParameter("reimbursementEmployeeId"));

		HttpSession session = request.getSession();

		employee = (Employee) session.getAttribute("employee");

		EmployeeService es = new EmployeeService();
		try {
			
			if(reimbursementEmployeeId == employee.getEmployeeId()) {
				es.sendReimbursementRequest(isPending, isApproved, reimbursementDescription, reimbursementAmount,
					reimbursementEmployeeId);
				out.print(
					"<h1 style='font-size:15px; position: absolute;text-align:center; top:65%; left:20%;'>Submission sent. Check your pending submission page in a few days for a response. Thank you and have a wonderful day "
							+ employee.geteFirstName() + "!</h1>");
				RequestDispatcher rd = request.getRequestDispatcher("/EmployeeHomeServlet");;
				rd.include(request, response);
			}else {
//					out.print("<p style='position:fixed;top:60%;left:50%;transform:translate(-50%, -50%)'>Invalid Submission Request, Please try again</p>");
//					RequestDispatcher rd = request.getRequestDispatcher("html/reimbursement_home.html");
//					rd.include(request, response);
				response.sendRedirect("html/reimbursement_home.html");
				}

		} catch (SQLException e) {
			e.getMessage();
		} catch(NumberFormatException e1) {
			response.sendRedirect("html/reimbursement_home.html");
		}
	}

}
