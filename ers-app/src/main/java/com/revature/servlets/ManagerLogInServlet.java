package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.exceptions.ManagerNotFoundException;
import com.revature.model.Manager;
import com.revature.services.ManagerService;

public class ManagerLogInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ManagerService ms = new ManagerService();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Manager manager = new Manager();
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String un = request.getParameter("managerUsername");
		String pw = request.getParameter("managerPassword");
		HttpSession session = request.getSession();

		try {
			manager = ms.managerLogIn(un, pw);
			session.setAttribute("manager", manager);
		} catch (SQLException e) {
			e.getMessage();
		} catch (ManagerNotFoundException e) {
			e.getMessage();
		}
		
		session.setAttribute("managerFirstName", manager.getmFirstName());
		
		if (un.equals(manager.getmUsername()) && pw.equals(manager.getmPassword())) {
			byte[] encHTML = Files.readAllBytes(Paths.get("C:/Users/Trevor/Desktop/Aggregate8Week/ers-app/ers-app/src/main/webapp/html/manager_home.html"));
			String html = new String(encHTML, StandardCharsets.UTF_8);
			html = html.replace("placeholder_mName", manager.getmFirstName());
			out.print("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css\">");
			out.print(html);
		} else {
			out.print("<p style='position:fixed;top:60%;left:50%;transform:translate(-50%, -50%)'>Sorry Username or Password incorrect</p>");
			response.sendRedirect("html/manager_login.html");
		}
	}
}
