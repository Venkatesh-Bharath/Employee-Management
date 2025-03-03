package com.abc.EmployeeManagement.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abc.EmployeeManagement.model.Employee;
import com.abc.EmployeeManagement.service.EmployeeServiceInf;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/api/v1/employees")
@Tag(name = "Employee Controller",description = "Employee Create, read, update and delete")
public class EmployeeController {
    @Autowired
    private EmployeeServiceInf employeeServiceInf;
    //Create Employee
    
    private static final Logger logger=LoggerFactory.getLogger(EmployeeController.class);
    
    @Operation(summary = "Post employee",description = "create new employee")
    @ApiResponses({
    	@ApiResponse(responseCode = "201",description = "Employee created successfully"),
    	@ApiResponse(responseCode = "400",description = "Badrequest")
    })
    @PostMapping
    public ResponseEntity<Employee> createEmployee(@Valid @RequestBody Employee employee) {
    	logger.info("Create Employee API called ");
    	logger.debug("Employee Creating Data {}",employee);
    	try {
    		Employee savedEmployee=employeeServiceInf.createEmployee(employee);
    		logger.info("Employee created successfully with Id:{} ",savedEmployee.getId());
    		return ResponseEntity.status(HttpStatus.CREATED).body(savedEmployee);
    	}catch (Exception e) {
    		logger.error("Error while creating employee:{}",e.getMessage());
    		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
    }
    
    
    
    //Get All Employee
    @ApiResponses({
    	@ApiResponse(responseCode = "200",description = "List of Employees",
    	content=@Content(array = @ArraySchema(schema = @Schema(implementation =Employee.class ))))
    })
	@GetMapping
	@Operation(summary = "Get All employee",description = "Get All employees eatils in the form list")
	public ResponseEntity<List<Employee>> getAllEmployees() {
    	logger.info("Get All Employees API called ");
		List<Employee> employees=employeeServiceInf.getAllEmployees();
		logger.debug("Featched Employees",employees);
		return ResponseEntity.status(HttpStatus.OK).body(employees);
	}
	
	//Update Employee by Id
	@PutMapping("/{id}")
	@Operation(summary = "Update employee By Id",description = "Update Employee deatils using employee id")
	public  ResponseEntity<Employee>  updateEmployee(
			@Parameter(description = "Int id for update",example = "1") @PathVariable int id,
			@Valid  @RequestBody Employee employee) {
		logger.info("Update Employee API called ");
		try {
			Employee updatedEmployee=employeeServiceInf.updateEmployee(id,employee);
			logger.info("Employee Updated successfully with Id:{} ",id,updatedEmployee);
			return ResponseEntity.status(HttpStatus.OK).body(updatedEmployee);
	    }catch (Exception e) {
	    	logger.error("Error while Updating employee:{}",id,e.getMessage());
	    	return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	//Delete Employee by Id
	@Operation(summary = "delete employee By Id",description = "Delete Employee deatils using employee id")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteEmployee(@PathVariable int id){
		logger.warn("Delete Employee API called ");
		try {
			employeeServiceInf.deleteEmployee(id);
			logger.info("Employee Deleted successfully with Id:{} ",id);
			return ResponseEntity.noContent().build();
		}catch (Exception e) {
			logger.error("Error while Updating employee:{}",id,e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	
}
