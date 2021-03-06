package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.exceptions.EmployeeNotFoundException;
import com.revature.model.Employee;
import com.revature.services.EmployeeService;

public class EmployeeLogInServlet extends HttpServlet {

	EmployeeService es = new EmployeeService();
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Employee employee = new Employee();

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String un = request.getParameter("employeeUsername");
		String pw = request.getParameter("employeePassword");

		
		HttpSession session = request.getSession();
		
		try {
			employee = es.employeeLogIn(un, pw);
	
			if (employee != null && un.equals(employee.geteUsername()) && pw.equals(employee.getePassword())) {
				session.setAttribute("employee", employee);
				RequestDispatcher rd = request.getRequestDispatcher("/EmployeeHomeServlet");
				rd.forward(request, response);
			} else {
//				RequestDispatcher rd = request.getRequestDispatcher("html/employee_login.html");
//				out.print("<p style='position:fixed;top:60%;left:20%;transform:translate(-50%, -50%)'>Sorry Username or Password incorrect</p>");
//				rd.include(request, response);
				response.sendRedirect("html/employee_login.html");
			}
		} catch (SQLException e) {
			e.getMessage();
		} catch (EmployeeNotFoundException e) {
			response.sendRedirect("html/employee_login.html");
		}
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}
}
