package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.model.Employee;
import com.revature.services.ManagerService;

public class ViewAllEmployeesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ViewAllEmployeesServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		List<Employee> allEmployees = new ArrayList<>();
		String employeeHTML = "";
		ManagerService ms = new ManagerService();
		
		try {
			allEmployees = ms.viewAllEmployees();
			
			for(Employee e : allEmployees) {
				employeeHTML += "<tr>" +"<td>Employee Id: " + e.getEmployeeId() + "</td>";
				employeeHTML += "<td>Employee First Name: " + e.geteFirstName() + "</td>";
				employeeHTML += "<td>Employee Last Name: " + e.geteLastName() + "</td>";
				employeeHTML += "<td>Employee Email: " + e.geteEmail() + "</td>" +"</tr>";
			}
			
			byte[] encHTML = Files.readAllBytes(Paths.get("C:/Users/Trevor/Desktop/Aggregate8Week/ers-app/ers-app/src/main/webapp/html/view_all_employees.html"));
			String html = new String(encHTML, StandardCharsets.UTF_8);
			html = html.replace("placeholder_ViewAllEmployees", employeeHTML);
			out.print("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css\">");
			out.print(html);
		} catch (SQLException e) {
			e.getMessage();
		}
	}
}
