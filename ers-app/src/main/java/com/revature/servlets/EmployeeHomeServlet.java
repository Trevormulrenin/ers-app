package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.model.Employee;


public class EmployeeHomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EmployeeHomeServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Employee employee = new Employee();
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		employee = (Employee) session.getAttribute("employee");
		
		
		byte[] encHTML = Files.readAllBytes(Paths.get("C:/Users/Trevor/Desktop/Aggregate8Week/ers-app/ers-app/src/main/webapp/html/employee_home.html"));
		String html = new String(encHTML, StandardCharsets.UTF_8);
		html = html.replace("placeholder_eName", employee.geteFirstName());
		out.print("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css\">");
		out.print(html);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request,response);
	}


}
