package com.revature.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.services.ManagerService;


public class ApproveOrDenyReimbursementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ApproveOrDenyReimbursementServlet() {
        super();
    }


	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		
		ManagerService ms = new ManagerService();
		ObjectMapper om = new ObjectMapper();
		StringBuilder sb = new StringBuilder();
		BufferedReader reader = request.getReader();
		
		try {
			String responseString;
			while((responseString = reader.readLine()) != null) {
				sb.append(responseString).append("/n");
			}
		} finally {
			reader.close();
		}
		
		JsonNode json = om.readTree(sb.toString());
		
		int id = json.get("reimbursementId").asInt();
		String approveOrDeny = json.get("approveOrDeny").asText();
		
		System.out.println(id);
		System.out.println(approveOrDeny);
		
		if(approveOrDeny.equals("approve")) {
			try {
				ms.approveReimbursementRequest(false, true, id);
				response.sendRedirect("./ViewAllPendingReimbursementsServlet");
			} catch (SQLException e) {
				e.getMessage();
			}
		} else if(approveOrDeny.equals("deny")) {
			try {
				ms.denyReimbursementRequest(false, false, id);
				response.sendRedirect("./ViewAllPendingReimbursementsServlet");
			} catch (SQLException e) {
				e.getMessage();
			}
		}else {
			System.out.println("error");
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("./ViewAllPendingReimbursementsServlet");
		rd.include(request, response);
	}
}
