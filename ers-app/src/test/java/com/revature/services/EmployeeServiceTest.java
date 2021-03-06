package com.revature.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import com.revature.dao.EmployeeDAO;
import com.revature.exceptions.EmployeeIdNotFoundException;
import com.revature.exceptions.EmployeeNotFoundException;
import com.revature.model.Employee;
import com.revature.model.Reimbursement;
import com.revature.util.ConnectionUtil;

public class EmployeeServiceTest {
	EmployeeService employeeService;
	public static Connection mockConnection;
	public static MockedStatic<ConnectionUtil> mockedStatic;
	public static EmployeeDAO employeeDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		employeeDAO = mock(EmployeeDAO.class);
		mockConnection = mock(Connection.class);

		Employee employee = new Employee(2, "joesmith", "password123", "Joe", "Smith", "joesmith@gmail.com");
		when(employeeDAO.logInVerification(eq("joesmith"), eq("password123"), eq(mockConnection))).thenReturn(employee);

		List<Reimbursement> pReimbursements = new ArrayList<>();
		pReimbursements.add(new Reimbursement(10, "Doctor", true, false, 100.23, 1));
		pReimbursements.add(new Reimbursement(11, "Flight", true, false, 500, 1));
		when(employeeDAO.viewAllPendingReimbursementsByEmployeeId(eq(true), eq(1), eq(mockConnection)))
				.thenReturn(pReimbursements);
		
		List<Reimbursement> rReimbursements = new ArrayList<>();
		rReimbursements.add(new Reimbursement(10, "Doctor", false, true, 100.23, 1));
		rReimbursements.add(new Reimbursement(11, "Flight", false, false, 500, 1));
		when(employeeDAO.viewAllResolvedReimbursementsByEmployeeId(eq(false), eq(1), eq(mockConnection)))
				.thenReturn(rReimbursements);
	}

	@Before
	public void setUp() throws Exception {
		employeeService = new EmployeeService(employeeDAO);
	}

	@Test
	public void testValidUsername() throws SQLException, EmployeeNotFoundException {
		try (MockedStatic<ConnectionUtil> mockedStatic = Mockito.mockStatic(ConnectionUtil.class)) {
			mockedStatic.when(ConnectionUtil::getConnection).thenReturn(mockConnection);
			Employee actual = employeeService.employeeLogIn("joesmith", "password123");
			Employee expected = new Employee(2, "joesmith", "password123", "Joe", "Smith", "joesmith@gmail.com");

			assertEquals(expected, actual);
		}
	}

	@Test(expected = EmployeeNotFoundException.class)
	public void testInvalidUsername() throws SQLException, EmployeeNotFoundException {
		try (MockedStatic<ConnectionUtil> mockedStatic = Mockito.mockStatic(ConnectionUtil.class)) {
			mockedStatic.when(ConnectionUtil::getConnection).thenReturn(mockConnection);
			employeeService.employeeLogIn("123", "password");
		}
	}

	@Test
	public void testValidViewPendingById() throws SQLException, EmployeeIdNotFoundException {
		try (MockedStatic<ConnectionUtil> mockedStatic = Mockito.mockStatic(ConnectionUtil.class)) {
			mockedStatic.when(ConnectionUtil::getConnection).thenReturn(mockConnection);
			List<Reimbursement> actual = employeeService.viewAllPendingReimbursementsById(1);

			List<Reimbursement> reimbursements = new ArrayList<>();
			Reimbursement r1 = new Reimbursement(10, "Doctor", true, false, 100.23, 1);
			Reimbursement r2 = new Reimbursement(11, "Flight", true, false, 500, 1);
			reimbursements.add(r1);
			reimbursements.add(r2);
			List<Reimbursement> expected = reimbursements;

			assertEquals(expected, actual);

		}
	}

	@Test(expected = EmployeeIdNotFoundException.class)
	public void testInvalidViewPendingById() throws SQLException, EmployeeIdNotFoundException {
		try (MockedStatic<ConnectionUtil> mockedStatic = Mockito.mockStatic(ConnectionUtil.class)) {
			mockedStatic.when(ConnectionUtil::getConnection).thenReturn(mockConnection);
			
			employeeService.viewAllPendingReimbursementsById(5);

		}
	}
	
	@Test
	public void testValidViewResolvedById() throws SQLException, EmployeeIdNotFoundException {
		try (MockedStatic<ConnectionUtil> mockedStatic = Mockito.mockStatic(ConnectionUtil.class)) {
			mockedStatic.when(ConnectionUtil::getConnection).thenReturn(mockConnection);
			List<Reimbursement> actual = employeeService.viewAllResolvedReimbursementsById(1);

			List<Reimbursement> reimbursements = new ArrayList<>();
			Reimbursement r1 = new Reimbursement(10, "Doctor", false, true, 100.23, 1);
			Reimbursement r2 = new Reimbursement(11, "Flight", false, false, 500, 1);
			reimbursements.add(r1);
			reimbursements.add(r2);
			List<Reimbursement> expected = reimbursements;

			assertEquals(expected, actual);

		}
	}

	@Test(expected = EmployeeIdNotFoundException.class)
	public void testInvalidViewResolvedById() throws SQLException, EmployeeIdNotFoundException {
		try (MockedStatic<ConnectionUtil> mockedStatic = Mockito.mockStatic(ConnectionUtil.class)) {
			mockedStatic.when(ConnectionUtil::getConnection).thenReturn(mockConnection);
			
			employeeService.viewAllPendingReimbursementsById(5);

		}
	}
}
