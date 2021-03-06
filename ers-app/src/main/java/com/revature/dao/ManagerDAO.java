package com.revature.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.revature.model.Employee;
import com.revature.model.Manager;
import com.revature.model.Reimbursement;

public interface ManagerDAO {

	public Manager logInVerification(String mUsername, String mPassword, Connection con) throws SQLException;

	public void approveReimbursementRequest(boolean isPending, boolean isApproved, int reimbursementId, Connection con)
			throws SQLException;

	public void denyReimbursementRequest(boolean isPending, boolean isApproved, int reimbursementId, Connection con)
			throws SQLException;

	public List<Employee> viewAllEmployees(Connection con) throws SQLException;

	public List<Reimbursement> viewAllPendingReimbursements(boolean isPending, Connection con) throws SQLException;

	public List<Reimbursement> viewAllResolvedReimbursements(boolean isPending, Connection con) throws SQLException;

	public List<Reimbursement> viewAllReimbursementsByEmployeeId(int employeeId, Connection con) throws SQLException;

}
