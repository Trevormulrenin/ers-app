package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.revature.exceptions.EmployeeIdNotFoundException;
import com.revature.model.Reimbursement;
import com.revature.services.ManagerService;

public class ViewReimbursementByEmployeeIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log= Logger.getLogger(ViewReimbursementByEmployeeIdServlet.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ViewReimbursementByEmployeeIdServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		int id = Integer.valueOf(request.getParameter("employeeId"));

		ManagerService ms = new ManagerService();
		List<Reimbursement> reimbursements = new ArrayList<>();
		String byIdTable = "";

		try {
			reimbursements = ms.viewAllReimbursementsByEmployeeId(id);
			log.info("Manager searched for reimbursements for employee with id:" + id);
			for (Reimbursement r : reimbursements) {
				byIdTable += "<tr>" + "<td>Reimbursement Id: " + r.getReimbursementId() + "</td>";
				byIdTable += "<td>Reimbursement Description: " + r.getReimbursementDescription() + "</td>";
				byIdTable += "<td>Reimbursement Amount: " + r.getReimbursementAmount() + "</td>";
				byIdTable += "<td>Pending: " + r.isPendingReimbursement() + "</td>" + "</tr>";

			}

			byte[] encHTML = Files.readAllBytes(Paths.get(
					"C:/Users/Trevor/Desktop/Aggregate8Week/ers-app/ers-app/src/main/webapp/html/by_id_table.html"));
			String html = new String(encHTML, StandardCharsets.UTF_8);
			html = html.replace("placeholder_AllById", byIdTable);
			out.print("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css\">");
			out.print(html);

		} catch (SQLException e) {
			e.getMessage();
		} catch (EmployeeIdNotFoundException e) {
			RequestDispatcher rd = request.getRequestDispatcher("./ViewReimbursementByEmployeeIdServlet");
			rd.forward(request, response);
		}

	}

}
