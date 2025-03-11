package com.abc.EmployeeManagement.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import com.abc.EmployeeManagement.exception.ResourseNotFoundException;
import com.abc.EmployeeManagement.model.Employee;
import com.abc.EmployeeManagement.repository.EmployeeRepository;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("Employee Service Test")
public class EmployeeServiceImplTest {

	@Mock
	private EmployeeRepository employeeRepository;
	
	@InjectMocks
	private EmployeeServiceImpl employeeServiceImpl;
	
	private Employee employee;
	
	@BeforeEach
	void setUp() {
		employee=new Employee.Builder()
				.name("BSK")
				.email("bsk@gmail.com")
				.pan("GGGGG8765P")
				.address("Andhra pradesh")
				.mobile("9876543210")
				.build();
	}
	
	@Test
	@DisplayName("Create Employee Test")
	void createEmployeeTestSuccess() {
		//mock
	   when(employeeRepository.save(any(Employee.class))).thenReturn(employee);
	   
	   Employee createdEmployee=employeeServiceImpl.createEmployee(employee);
	   assertNotNull(createdEmployee);
	   assertEquals(employee.getName(), createdEmployee.getName());
	}
	
	@Test
	@DisplayName("Get All Employees Test")
	void getAllEmployeesTestSuccess() {
		Employee emp1=new Employee.Builder()
				.name("MAHI")
				.email("mahi@gmail.com")
				.pan("GGGGG8765M")
				.address("AP")
				.mobile("9876543211")
				.build();
		Employee emp2=new Employee.Builder()
				.name("VB")
				.email("vb@gmail.com")
				.pan("GGGGG8765B")
				.address("India")
				.mobile("9876543212")
				.build();
		when(employeeRepository.findAll()).thenReturn(List.of(employee,emp1,emp2));
		
		List<Employee> employeeList=employeeServiceImpl.getAllEmployees();
		
		assertFalse(employeeList.isEmpty());
		
		assertEquals(3, employeeList.size());
	}
	
	@Test
	@DisplayName("Update Employee Test")
	void updateEmployeeTestSuccess() {
		Employee updatedDate=new Employee.Builder()
				.id(employee.getId())
				.name("MAHI")
				.email("mahi@gmail.com")
				.pan("GGGGG8765M")
				.address("AP")
				.mobile("9876543211")
				.build();
		
		when(employeeRepository.findById(1)).thenReturn(Optional.of(employee));	
		when(employeeRepository.save(any(Employee.class))).thenReturn(updatedDate);
	
		Employee updatedEmployee=employeeServiceImpl.updateEmployee(1, updatedDate);
		
		assertNotNull(updatedEmployee);
		assertEquals(updatedEmployee.getName(), "MAHI");
		//assertEquals(updatedEmployee.getName(),updatedDate.getName());
		
	}
	
	
	@Test
	@DisplayName("Update Employee Test")
	void deleteEmployeeTestSuccess() {
		when(employeeRepository.findById(1)).thenReturn(Optional.of(employee));
		
		doNothing().when(employeeRepository).delete(employee);
		
		assertDoesNotThrow(()->employeeServiceImpl.deleteEmployee(1));
	}
	
	@ParameterizedTest
	@DisplayName("delete Employee Not Found Test")
	@ValueSource(ints= {1}) //Allows passing dynamic values
	void deleteEmployeeTestNotFound(int id) {
		when(employeeRepository.findById(id)).thenReturn(Optional.empty());
		
		ResourseNotFoundException exception=assertThrows(ResourseNotFoundException.class,
				()->employeeServiceImpl.deleteEmployee(1));
		assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
	}
	
	
	
	
}
