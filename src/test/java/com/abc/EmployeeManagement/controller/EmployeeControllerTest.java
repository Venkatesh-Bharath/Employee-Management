package com.abc.EmployeeManagement.controller;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.abc.EmployeeManagement.model.Employee;
import com.abc.EmployeeManagement.service.EmployeeServiceInf;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.boot.test.mock.mockito.MockBean;

@WebMvcTest(EmployeeController.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("Employee Controller Test")
@Import(EmployeeControllerTest.TestConfig.class)
public class EmployeeControllerTest {
	@TestConfiguration
	static class TestConfig {
         
		@Bean
		EmployeeServiceInf employeeServiceInf() {
			return mock(EmployeeServiceInf.class);
		}
	}

	@Autowired
	private MockMvc mockMvc;
	
//	@MockBean
	@Autowired
	private EmployeeServiceInf employeeServiceInf;
	
	@Autowired
	private ObjectMapper objectMapper;
	
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
	void createEmployeeTestSuccess() throws JsonProcessingException, Exception {
		when(employeeServiceInf.createEmployee(any(Employee.class))).thenReturn(employee);
		
		mockMvc.perform(post("/api/v1/employee")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(employee)))
		.andExpect(status().isCreated())
		.andExpect(jsonPath("$.name").value("BSK"));
	}
	
	@Test
	@DisplayName("Get All Employees Test")
	void getAllEmployeesTestSuccess() throws Exception {
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
		
		when(employeeServiceInf.getAllEmployees()).thenReturn(List.of(employee,emp1,emp2));
		mockMvc.perform(get("/api/v1/employee"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$[0].name").value("BSK"));		
	}
	
	@Test
	@DisplayName("Update Employee Test")
	void updateEmployeeTestSuccess() throws JsonProcessingException, Exception {
		Employee updatedDate=new Employee.Builder()
				.id(employee.getId())
				.name("MAHI")
				.email("mahi@gmail.com")
				.pan("GGGGG8765M")
				.address("AP")
				.mobile("9876543211")
				.build();
		when(employeeServiceInf.updateEmployee(eq(1), any(Employee.class))).thenReturn(updatedDate);
		
		mockMvc.perform(put("/api/v1/employee/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(updatedDate)))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.name").value("MAHI"));
		
	}
	
	@Test
	@DisplayName("Delete Employee Test")
	void deleteEmployeeTestSuccess() throws Exception {
		doNothing().when(employeeServiceInf).deleteEmployee(1);
		mockMvc.perform(delete("/api/v1/employee/1"))
		.andExpect(status().isNoContent());
	}

}
