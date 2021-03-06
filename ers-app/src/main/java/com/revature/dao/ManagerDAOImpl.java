package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.model.Employee;
import com.revature.model.Manager;
import com.revature.model.Reimbursement;

public class ManagerDAOImpl implements ManagerDAO {

	@Override
	public Manager logInVerification(String mUsername, String mPassword, Connection con) throws SQLException {
		Manager manager = new Manager();
		
		String sql = "SELECT * FROM ers.manager WHERE manager_username = ? AND manager_password = ?";
		PreparedStatement pstmt = con.prepareStatement(sql);

		pstmt.setString(1, mUsername);
		pstmt.setString(2, mPassword);
		
		ResultSet rs = pstmt.executeQuery();
		
		while (rs.next()) {
			int managerId = rs.getInt("manager_id");
			String managerUsername = rs.getString("manager_username");
			String managerPassword = rs.getString("manager_password");
			String mFirstName = rs.getString("manager_first_name");
			String mLastName = rs.getString("manager_last_name");
			String mEmail = rs.getString("manager_email");

			manager = new Manager(managerId, managerUsername, managerPassword, mFirstName, mLastName, mEmail);
		}
		return manager;
	}

	@Override
	public void approveReimbursementRequest(boolean isPending, boolean isApproved, int reimbursementId, Connection con)
			throws SQLException {
		String sql = "UPDATE ers.reimbursements SET is_pending = ? AND is_approved = ? WHERE reimbursement_id = ?";
		PreparedStatement pstmt = con.prepareStatement(sql);

		pstmt.setBoolean(1, isPending);
		pstmt.setBoolean(2, isApproved);
		pstmt.setInt(3, reimbursementId);

		pstmt.executeUpdate();
	}

	@Override
	public void denyReimbursementRequest(boolean isPending, boolean isApproved, int reimbursementId, Connection con)
			throws SQLException {

		String sql = "UPDATE ers.reimbursements SET is_pending = ? AND is_approved = ? WHERE reimbursement_id = ?";
		PreparedStatement pstmt = con.prepareStatement(sql);

		pstmt.setBoolean(1, isPending);
		pstmt.setBoolean(2, isApproved);
		pstmt.setInt(3, reimbursementId);

		pstmt.executeUpdate();
	}

	@Override
	public List<Employee> viewAllEmployees(Connection con) throws SQLException {
		List<Employee> allEmployees = new ArrayList<>();

		String sql = "SELECT * FROM ers.employees";
		PreparedStatement pstmt = con.prepareStatement(sql);

		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {
			int employeeId = rs.getInt("employee_id");
			String eUsername = rs.getString("employee_username");
			String ePassword = rs.getString("employee_password");
			String eFirstName = rs.getString("employee_first_name");
			String eLastName = rs.getString("employee_last_name");
			String eEmail = rs.getString("employee_email");

			allEmployees.add(new Employee(employeeId, eUsername, ePassword, eFirstName, eLastName, eEmail));
		}
		return allEmployees;
	}

	@Override
	public List<Reimbursement> viewAllPendingReimbursements(boolean isPending, Connection con) throws SQLException {
		List<Reimbursement> pendingReimbursements = new ArrayList<>();

		String sql = "SELECT * FROM ers.reimbursements WHERE is_pending = ?";
		PreparedStatement pstmt = con.prepareStatement(sql);

		pstmt.setBoolean(1, isPending);

		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {
			int reimbursementId = rs.getInt("reimbursement_id");
			boolean isApproved = rs.getBoolean("is_approved");
			double reimbursementAmount = rs.getDouble("reimbursement_amount");
			int reimbursementEmployeeId = rs.getInt("employee");
			String reimbursementDescription = rs.getString("reimbursement_description");

			pendingReimbursements.add(new Reimbursement(reimbursementId, reimbursementDescription, isPending,
					isApproved, reimbursementAmount, reimbursementEmployeeId));
		}
		return pendingReimbursements;
	}
	
	@Override
	public List<Reimbursement> viewAllResolvedReimbursements(boolean isPending, Connection con) throws SQLException {
		List<Reimbursement> resolvedReimbursements = new ArrayList<>();

		String sql = "SELECT * FROM ers.reimbursements WHERE is_pending = ?";
		PreparedStatement pstmt = con.prepareStatement(sql);

		pstmt.setBoolean(1, isPending);

		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {
			int reimbursementId = rs.getInt("reimbursement_id");
			boolean isApproved = rs.getBoolean("is_approved");
			double reimbursementAmount = rs.getDouble("reimbursement_amount");
			int reimbursementEmployeeId = rs.getInt("employee");
			String reimbursementDescription = rs.getString("reimbursement_description");

			resolvedReimbursements.add(new Reimbursement(reimbursementId, reimbursementDescription, isPending,
					isApproved, reimbursementAmount, reimbursementEmployeeId));
		}
		return resolvedReimbursements;
	}

	@Override
	public List<Reimbursement> viewAllReimbursementsByEmployeeId(int employeeId, Connection con) throws SQLException {
		List<Reimbursement> allReimbursements = new ArrayList<>();

		String sql = "SELECT * FROM ers.reimbursements WHERE employee = ?";
		PreparedStatement pstmt = con.prepareStatement(sql);

		pstmt.setInt(1, employeeId);

		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {
			int reimbursementId = rs.getInt("reimbursement_id");
			boolean isApproved = rs.getBoolean("is_approved");
			double reimbursementAmount = rs.getDouble("reimbursement_amount");
			boolean isPending = rs.getBoolean("is_pending");
			String reimbursementDescription = rs.getString("reimbursement_description");

			allReimbursements.add(new Reimbursement(reimbursementId, reimbursementDescription, isPending,
					isApproved, reimbursementAmount, employeeId));
		}
		return allReimbursements;
	}
}
