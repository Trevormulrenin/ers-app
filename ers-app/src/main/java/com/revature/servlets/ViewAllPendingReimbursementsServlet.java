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

import com.revature.model.Reimbursement;
import com.revature.services.ManagerService;


public class ViewAllPendingReimbursementsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ViewAllPendingReimbursementsServlet() {
        super();
    }

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Cache-Control",  "no-cache");
		response.setHeader("Cache-Control", "no-store");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
	
		ManagerService ms = new ManagerService();
		List<Reimbursement> pReimbursements = new ArrayList<>();
		String pendingTable = "";
		try {
			pReimbursements = ms.viewAllPendingReimbursements();
			

			for(Reimbursement r : pReimbursements) {
				pendingTable += "<table onclick='changeColor(" + r.getReimbursementId() + ")' id='table_" + r.getReimbursementId() + "' class='table'><tr>";
				pendingTable += "<td>Reimbursement Description: " + r.getReimbursementDescription() + "</td>";
				pendingTable += "<td>Reimbursement ID: " + r.getReimbursementId() + "</td>";
				pendingTable += "<td>Reimbursement Amount: " + r.getReimbursementAmount() + "</td>";
				pendingTable += "<td>Employee Id:" + r.getReimbursementEmployeeId() + "</td>";
				pendingTable += "<td><button onclick='approve(" + r.getReimbursementId() + ")' class='btn btn-dark' id='approve_" + r.getReimbursementId() + "' value='approve' name='button'>Approve</button></td>";
				pendingTable += "<td><button onclick='deny(" + r.getReimbursementId() + ")' class='btn btn-dark' id='deny_" + r.getReimbursementId() + "' value='deny' name='button'>Deny</button></td>";
				pendingTable += "</tr></table>";
						
			}
			byte[] encHTML = Files.readAllBytes(Paths.get("C:/Users/Trevor/Desktop/Aggregate8Week/ers-app/ers-app/src/main/webapp/html/pending_manager.html"));
			String html = new String(encHTML, StandardCharsets.UTF_8);
			html = html.replace("placeholder_AllPending", pendingTable);
			out.print("<style> #approve {position: relative; float:right; }#deny{position: relative; float:right; } </style>");
			out.print("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css\">");
			out.print("<script type='text/javascript' src='js/approve_deny.js'></script>");
			out.print(html);
			
		} catch(SQLException e) {
			e.getMessage();
		}
			
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPut(request, response);
	}
	
}

