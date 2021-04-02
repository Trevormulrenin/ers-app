package com.revature.services;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.dao.ManagerDAO;
import com.revature.dao.ManagerDAOImpl;
import com.revature.exceptions.EmployeeIdNotFoundException;
import com.revature.exceptions.ManagerNotFoundException;
import com.revature.model.Employee;
import com.revature.model.Manager;
import com.revature.model.Reimbursement;
import com.revature.util.ConnectionUtil;

public class ManagerService {
	public ManagerDAO managerDAO;

	public ManagerService(ManagerDAO managerDAO) {
		this.managerDAO = managerDAO;
	}

	public ManagerService() {
		this.managerDAO = new ManagerDAOImpl();
	}

	public Manager managerLogIn(String username, String password) throws SQLException, ManagerNotFoundException {
		Manager manager = new Manager();

		try (Connection con = ConnectionUtil.getConnection()) {

			manager = managerDAO.logInVerification(username, password, con);

			if(manager == null) {
				throw new ManagerNotFoundException("Manager Not Found");
			}
		}catch (SQLException e) {
			System.out.println("SQL Error");
		}
		return manager;
	}
	
	public List<Employee> viewAllEmployees() throws SQLException {
		List<Employee> e = new ArrayList<>();
		try (Connection con = ConnectionUtil.getConnection()) {
			e = managerDAO.viewAllEmployees(con);
		}catch (SQLException e1) {
			System.out.println("SQL Error");
		}
		return e;
	}
	
	public List<Reimbursement> viewAllPendingReimbursements() throws SQLException {
		List<Reimbursement> pending = new ArrayList<>();
		try (Connection con = ConnectionUtil.getConnection()) {
			pending = managerDAO.viewAllPendingReimbursements(true, con);
		}catch (SQLException e2) {
			System.out.println("SQL Error");
		}
		
		return pending;
	}
	
	public List<Reimbursement> viewAllResolvedReimbursements() throws SQLException {
		List<Reimbursement> resolved = new ArrayList<>();
		try (Connection con = ConnectionUtil.getConnection()) {
			resolved = managerDAO.viewAllPendingReimbursements(false, con);
		}catch (SQLException e2) {
			System.out.println("SQL Error");
		}
		
		return resolved;
	}
	
	public List<Reimbursement> viewAllReimbursementsByEmployeeId(int employeeId) throws SQLException, EmployeeIdNotFoundException {
		List<Reimbursement> reimbursements = new ArrayList<>();
		try (Connection con = ConnectionUtil.getConnection()) {
			reimbursements = managerDAO.viewAllReimbursementsByEmployeeId(employeeId, con);
			boolean isEmpty = reimbursements.isEmpty();
			
			if(isEmpty == true) {
				throw new EmployeeIdNotFoundException("Employee ID Not Found");
			}
		}catch (SQLException e2) {
			System.out.println("SQL Error");
		}
		return reimbursements;
	}
	
	public void approveReimbursementRequest(boolean isPending, boolean isApproved, int reimbursementId)
			throws SQLException{
		try (Connection con = ConnectionUtil.getConnection()) {
			managerDAO.approveReimbursementRequest(false, true, reimbursementId, con);
		}catch (SQLException e2) {
			System.out.println("SQL Error");
		}
	}

	public void denyReimbursementRequest(boolean isPending, boolean isApproved, int reimbursementId)
			throws SQLException{
		try (Connection con = ConnectionUtil.getConnection()) {
			managerDAO.denyReimbursementRequest(false, false, reimbursementId, con);
		}catch (SQLException e2) {
			System.out.println("SQL Error");
		}
	}

}
