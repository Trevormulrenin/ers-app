package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.model.Employee;
import com.revature.model.Reimbursement;

public class EmployeeDAOImpl implements EmployeeDAO {

	@Override
	public void updateEmployeeInfo(String username, String password, String firstName, String lastName, String email, int eId, Connection con) throws SQLException {

		String updateEmployeeQuery = "UPDATE ers.employees SET employee_username = ?, employee_password = ?, employee_first_name = ?, employee_last_name = ?, employee_email = ? WHERE employee_id = ?";

		PreparedStatement pstmt = con.prepareStatement(updateEmployeeQuery);

		pstmt.setString(1, username);
		pstmt.setString(2, password);
		pstmt.setString(3, firstName);
		pstmt.setString(4, lastName);
		pstmt.setString(5, email);
		pstmt.setInt(6, eId);

		pstmt.executeUpdate();
		
	}

	@Override
	public Employee logInVerification(String eUsername, String ePassword, Connection con) throws SQLException {
		Employee employee = new Employee();

		String pw = "SELECT * FROM ers.employees WHERE employee_username = ? AND employee_password = ?";
		PreparedStatement pstmt = con.prepareStatement(pw);

		pstmt.setString(1, eUsername);
		pstmt.setString(2, ePassword);

		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {
			int employeeId = rs.getInt("employee_id");
			String employeeUsername = rs.getString("employee_username");
			String employeePassword = rs.getString("employee_password");
			String eFirstName = rs.getString("employee_first_name");
			String eLastName = rs.getString("employee_last_name");
			String eEmail = rs.getString("employee_email");

			employee = new Employee(employeeId, employeeUsername, employeePassword, eFirstName, eLastName, eEmail);
		}
		return employee;
	}

	@Override
	public void submitReimbursementRequest(boolean isPendingReimbursement, boolean isApproved,
			String reimbursementDescription, double reimbursementAmount, int reimbursementEmployeeId, Connection con)
			throws SQLException {
		String sql = "INSERT INTO ers.reimbursements(is_pending, is_approved, reimbursement_description, reimbursement_amount, employee) "
				+ "VALUES(?, ?, ?, ?, ?)";
		PreparedStatement pstmt = con.prepareStatement(sql);

		pstmt.setBoolean(1, isPendingReimbursement);
		pstmt.setBoolean(2, isApproved);
		pstmt.setString(3, reimbursementDescription);
		pstmt.setDouble(4, reimbursementAmount);
		pstmt.setInt(5, reimbursementEmployeeId);

		pstmt.executeUpdate();
	}

	@Override
	public List<Reimbursement> viewAllPendingReimbursementsByEmployeeId(boolean isPendingReimbursement,
			int reimbursementEmployeeId, Connection con) throws SQLException {
		List<Reimbursement> pendingReimbursements = new ArrayList<>();

		String sql = "SELECT * FROM ers.reimbursements WHERE is_pending = ? AND employee = ?";
		PreparedStatement pstmt = con.prepareStatement(sql);

		pstmt.setBoolean(1, isPendingReimbursement);
		pstmt.setInt(2, reimbursementEmployeeId);

		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {
			int reimbursementId = rs.getInt("reimbursement_id");
			boolean isApproved = rs.getBoolean("is_approved");
			double reimbursementAmount = rs.getDouble("reimbursement_amount");
			String reimbursementDescription = rs.getString("reimbursement_description");

			pendingReimbursements.add(new Reimbursement(reimbursementId, reimbursementDescription,
					isPendingReimbursement, isApproved, reimbursementAmount, reimbursementEmployeeId));
		}
		return pendingReimbursements;
	}

	@Override
	public List<Reimbursement> viewAllResolvedReimbursementsByEmployeeId(boolean isPendingReimbursement,
			int reimbursementEmployeeId, Connection con) throws SQLException {
		List<Reimbursement> resolvedReimbursements = new ArrayList<>();

		String sql = "SELECT * FROM ers.reimbursements WHERE is_pending = ? AND employee = ?";
		PreparedStatement pstmt = con.prepareStatement(sql);

		pstmt.setBoolean(1, isPendingReimbursement);
		pstmt.setInt(2, reimbursementEmployeeId);

		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {
			int reimbursementId = rs.getInt("reimbursement_id");
			boolean isApproved = rs.getBoolean("is_approved");
			double reimbursementAmount = rs.getDouble("reimbursement_amount");
			String reimbursementDescription = rs.getString("reimbursement_description");

			resolvedReimbursements.add(new Reimbursement(reimbursementId, reimbursementDescription,
					isPendingReimbursement, isApproved, reimbursementAmount, reimbursementEmployeeId));
		}
		return resolvedReimbursements;
	}

	@Override
	public Employee viewEmployeeInfoById(int employeeId, Connection con) throws SQLException {
		Employee employee = new Employee();
		
		String sql = "SELECT * FROM ers.employees WHERE employee_id = ?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		
		pstmt.setInt(1, employeeId);
		
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next()) {
			String employeeUsername = rs.getString("employee_username");
			String employeePassword = rs.getString("employee_password");
			String eFirstName = rs.getString("employee_first_name");
			String eLastName = rs.getString("employee_last_name");
			String eEmail = rs.getString("employee_email");

			employee = new Employee(employeeId, employeeUsername, employeePassword, eFirstName, eLastName, eEmail);
		}
		return employee;
	}
}
