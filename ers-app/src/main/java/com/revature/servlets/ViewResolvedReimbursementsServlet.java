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
import javax.servlet.http.HttpSession;

import com.revature.exceptions.EmployeeIdNotFoundException;
import com.revature.model.Employee;
import com.revature.model.Reimbursement;
import com.revature.services.EmployeeService;


public class ViewResolvedReimbursementsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ViewResolvedReimbursementsServlet() {
        super();
    
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Employee employee = new Employee();
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();

		employee = (Employee) session.getAttribute("employee");
	
		
		EmployeeService es = new EmployeeService();
		List<Reimbursement> rReimbursements = new ArrayList<>();
		String resolvedTable = "";
		try {
			rReimbursements = es.viewAllResolvedReimbursementsById(employee.getEmployeeId());
			

			for(Reimbursement r : rReimbursements) {
				resolvedTable += "<tr>" +"<td>Reimbursement Id: " + r.getReimbursementId() + "</td>";
				resolvedTable += "<td>Reimbursement Description: " + r.getReimbursementDescription() + "</td>";
				resolvedTable += "<td>Reimbursement Amount: " + r.getReimbursementAmount() + "</td>";
				resolvedTable += "<td>Pending: " + r.isPendingReimbursement() + "</td>" +"</tr>";
				
			}
			
			byte[] encHTML = Files.readAllBytes(Paths.get("C:/Users/Trevor/Desktop/Aggregate8Week/ers-app/ers-app/src/main/webapp/html/resolved.html"));
			String html = new String(encHTML, StandardCharsets.UTF_8);
			html = html.replace("placeholder_Resolved", resolvedTable);
			out.print("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css\">");
			out.print(html);
			
		} catch(SQLException e) {
			e.getMessage();
		} catch (EmployeeIdNotFoundException e) {
			e.getMessage();
		}
			
	}
}
