package com.revature.services;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.dao.EmployeeDAO;
import com.revature.dao.EmployeeDAOImpl;
import com.revature.exceptions.EmployeeIdNotFoundException;
import com.revature.exceptions.EmployeeNotFoundException;
import com.revature.model.Employee;
import com.revature.model.Reimbursement;
import com.revature.servlets.ViewReimbursementByEmployeeIdServlet;
import com.revature.util.ConnectionUtil;

public class EmployeeService {
	private static Logger log= Logger.getLogger(EmployeeService.class);
	public EmployeeDAO employeeDAO;

	public EmployeeService(EmployeeDAO employeeDAO) {
		this.employeeDAO = employeeDAO;
	}

	public EmployeeService() {
		this.employeeDAO = new EmployeeDAOImpl();
	}

	public Employee employeeLogIn(String username, String password) throws SQLException, EmployeeNotFoundException {
		Employee employee = new Employee();
		try (Connection con = ConnectionUtil.getConnection()) {

			employee = employeeDAO.logInVerification(username, password, con);
			log.info("Employee with username: " + username + " and password: " + password + " logged in successfully");
			if (employee == null) {
				log.info("Failed employee log-in");
				throw new EmployeeNotFoundException("Employee not found");
			}
		} catch (SQLException e) {
			System.out.println("SQL Error");
		}
		return employee;
	}

	public void sendReimbursementRequest(boolean isPending, boolean isApproved, String reimbursementDescription,
			double reimbursementAmount, int reimbursementEmployeeId) throws SQLException {

		try (Connection con = ConnectionUtil.getConnection()) {
			employeeDAO.submitReimbursementRequest(isPending, isApproved, reimbursementDescription, reimbursementAmount,
					reimbursementEmployeeId, con);
		} catch (SQLException e) {
			System.out.println("SQL error");
		}
	}

	public List<Reimbursement> viewAllPendingReimbursementsById(int employeeId)
			throws SQLException, EmployeeIdNotFoundException {
		List<Reimbursement> pReimbursements = new ArrayList<>();

		try (Connection con = ConnectionUtil.getConnection()) {
			pReimbursements = employeeDAO.viewAllPendingReimbursementsByEmployeeId(true, employeeId, con);
			boolean isEmpty = pReimbursements.isEmpty();
			if (isEmpty == true) {
				throw new EmployeeIdNotFoundException("Employee Id Not Found");
			}
		} catch (SQLException e) {
			System.out.println("SQL error");
		}

		return pReimbursements;
	}

	public List<Reimbursement> viewAllResolvedReimbursementsById(int employeeId)
			throws SQLException, EmployeeIdNotFoundException {
		List<Reimbursement> rReimbursements = new ArrayList<>();

		try (Connection con = ConnectionUtil.getConnection()) {
			rReimbursements = employeeDAO.viewAllResolvedReimbursementsByEmployeeId(false, employeeId, con);
			boolean isEmpty = rReimbursements.isEmpty();
			if (isEmpty == true) {
				throw new EmployeeIdNotFoundException("Employee Id Not Found");
			}
		} catch (SQLException e) {
			System.out.println("SQL error");
		}

		return rReimbursements;
	}

	public void updateEmployee(String username, String password, String firstName, String lastName, String email,
			int eId) throws SQLException {

		try (Connection con = ConnectionUtil.getConnection()) {
			employeeDAO.updateEmployeeInfo(username, password, firstName, lastName, email, eId, con);
		} catch (SQLException e) {
			System.out.println("SQL error");
		}
	}
	
	public Employee viewEmployeeInfo(int employeeId) throws SQLException {
		Employee employee = new Employee();
		try (Connection con = ConnectionUtil.getConnection()) {
			employee = employeeDAO.viewEmployeeInfoById(employeeId, con);
		} catch (SQLException e) {
			System.out.println("SQL error");
		}
		
		return employee;
	}
}
