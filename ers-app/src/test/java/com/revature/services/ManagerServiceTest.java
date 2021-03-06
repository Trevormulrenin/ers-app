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

import com.revature.dao.ManagerDAO;
import com.revature.exceptions.EmployeeIdNotFoundException;
import com.revature.exceptions.ManagerNotFoundException;
import com.revature.model.Employee;
import com.revature.model.Manager;
import com.revature.model.Reimbursement;
import com.revature.util.ConnectionUtil;

public class ManagerServiceTest {
	ManagerService managerService;
	public static Connection mockConnection;
	public static MockedStatic<ConnectionUtil> mockedStatic;
	public static ManagerDAO managerDAO;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		managerDAO = mock(ManagerDAO.class);
		mockConnection = mock(Connection.class);
		
		Manager manager = new Manager(1, "mrmanager", "password", "Mister", "Manager", "mrmanager@gmail.com");
		when(managerDAO.logInVerification(eq("mrmanager"), eq("password"), eq(mockConnection))).thenReturn(manager);
		
		List<Employee> employees = new ArrayList<>();
		employees.add(new Employee(1, "trevor", "password", "Trevor", "Mulrenin", "trevormulrenin@gmail.com"));
		employees.add(new Employee(2, "joe", "password123", "Joe", "Smith", "joesmith@gmail.com"));
		when(managerDAO.viewAllEmployees(eq(mockConnection))).thenReturn(employees);
		
		List<Reimbursement> byId = new ArrayList<>();
		byId.add(new Reimbursement(10, "Doctor", false, true, 100.23, 1));
		byId.add(new Reimbursement(11, "Flight", false, false, 500, 1));
		when(managerDAO.viewAllReimbursementsByEmployeeId(eq(1), eq(mockConnection))).thenReturn(byId);
	}
	
	@Before
	public void setUp() throws Exception {
		managerService = new ManagerService(managerDAO);
	}
	
	@Test
	public void testValidLogIn() throws SQLException, ManagerNotFoundException {
		try (MockedStatic<ConnectionUtil> mockedStatic = Mockito.mockStatic(ConnectionUtil.class)) {
			mockedStatic.when(ConnectionUtil::getConnection).thenReturn(mockConnection);
			Manager actual = managerService.managerLogIn("mrmanager", "password");
			Manager expected = new Manager(1, "mrmanager", "password", "Mister", "Manager", "mrmanager@gmail.com");
			
			assertEquals(actual,expected);
		}
	}
	
	@Test(expected = ManagerNotFoundException.class)
	public void testInvalidLogIn() throws SQLException, ManagerNotFoundException {
		try (MockedStatic<ConnectionUtil> mockedStatic = Mockito.mockStatic(ConnectionUtil.class)) {
			mockedStatic.when(ConnectionUtil::getConnection).thenReturn(mockConnection);
			managerService.managerLogIn("mrmanager1111", "password");
		}
	}
	
	@Test
	public void testValidViewAllEmployees() throws SQLException {
		try (MockedStatic<ConnectionUtil> mockedStatic = Mockito.mockStatic(ConnectionUtil.class)) {
			mockedStatic.when(ConnectionUtil::getConnection).thenReturn(mockConnection);
			
			List<Employee> actual = managerService.viewAllEmployees();
			
			List<Employee> allEmployees = new ArrayList<>();
			Employee e1 = new Employee(1, "trevor", "password", "Trevor", "Mulrenin", "trevormulrenin@gmail.com");
			Employee e2 = new Employee(2, "joe", "password123", "Joe", "Smith", "joesmith@gmail.com");
			allEmployees.add(e1);
			allEmployees.add(e2);
			List<Employee> expected = allEmployees;
			
			assertEquals(actual, expected);
		}
	}
	
	@Test
	public void testValidEmployeeId() throws SQLException, EmployeeIdNotFoundException {
		try (MockedStatic<ConnectionUtil> mockedStatic = Mockito.mockStatic(ConnectionUtil.class)) {
			mockedStatic.when(ConnectionUtil::getConnection).thenReturn(mockConnection);
			
			List<Reimbursement> actual = managerService.viewAllReimbursementsByEmployeeId(1);
			
			List<Reimbursement> allById = new ArrayList<>();
			Reimbursement r1 = new Reimbursement(10, "Doctor", false, true, 100.23, 1);
			Reimbursement r2 = new Reimbursement(11, "Flight", false, false, 500, 1);
			allById.add(r1);
			allById.add(r2);
			List<Reimbursement> expected = allById;
			
			assertEquals(actual, expected);
		}
	}
	@Test (expected = EmployeeIdNotFoundException.class)
	public void testInvalidEmployeeId() throws SQLException, EmployeeIdNotFoundException {
		try (MockedStatic<ConnectionUtil> mockedStatic = Mockito.mockStatic(ConnectionUtil.class)) {
			mockedStatic.when(ConnectionUtil::getConnection).thenReturn(mockConnection);
			
			managerService.viewAllReimbursementsByEmployeeId(5);
			
		}
	}
}