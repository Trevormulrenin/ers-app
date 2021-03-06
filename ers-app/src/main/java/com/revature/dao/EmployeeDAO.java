package com.revature.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.revature.model.Employee;
import com.revature.model.Reimbursement;

public interface EmployeeDAO {

	public void updateEmployeeInfo(String username, String password, String firstName, String lastName, String email, int eId, Connection con) throws SQLException;

	public Employee logInVerification(String eUsername, String ePassword, Connection con) throws SQLException;

	public void submitReimbursementRequest(boolean isPendingReimbursement, boolean isApproved,
			String reimbursementDescription, double reimbursementAmount, int reimbursementEmployeeId, Connection con)
			throws SQLException;

	public List<Reimbursement> viewAllPendingReimbursementsByEmployeeId(boolean isPendingReimbursement,
			int reimbursementEmployeeId, Connection con) throws SQLException;

	public List<Reimbursement> viewAllResolvedReimbursementsByEmployeeId(boolean isPendingReimbursement,
			int reimbursementEmployeeId, Connection con) throws SQLException;
	
	public Employee viewEmployeeInfoById(int employeeId, Connection con) throws SQLException;

}
