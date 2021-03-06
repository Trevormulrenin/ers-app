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



public class UpdateEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public UpdateEmployeeServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Employee employee = new Employee();
		EmployeeService es = new EmployeeService();

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();

		employee = (Employee) session.getAttribute("employee");

		String un = request.getParameter("employeeUsername");
		String pw = request.getParameter("employeePassword");
		String fn = request.getParameter("firstName");
		String ln = request.getParameter("lastName");
		String email = request.getParameter("email");
		String id = request.getParameter("employeeId");
		int eId = Integer.valueOf(id);

		if (eId == employee.getEmployeeId()) {

			if (un.equals("")) {
				un = employee.geteUsername();
			}

			if (pw.equals("")) {
				pw = employee.getePassword();
			}

			if (fn.equals("")) {
				fn = employee.geteFirstName();
			}
			
			if (ln.equals("")) {
				ln = employee.geteLastName();
			}
			
			if (email.equals("")) {
				email = employee.geteEmail();
			}
			
			try {
				es.updateEmployee(un, pw, fn, ln, email, eId);
				out.print("<h1 style='font-size:15px; position: absolute;text-align:center; top:65%; left:35%;'>Successfully Updated Employee Profile"  + "!</h1>");
				RequestDispatcher rd = request.getRequestDispatcher("/EmployeeHomeServlet");
				rd.include(request, response);
			} catch (SQLException e) {
				e.getMessage();
			}
			
		} else {
			response.sendRedirect("html/employee_info.html");
		}

	}

}
