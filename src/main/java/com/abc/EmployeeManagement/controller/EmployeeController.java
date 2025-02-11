package com.abc.EmployeeManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abc.EmployeeManagement.model.Employee;
import com.abc.EmployeeManagement.service.EmployeeServiceInf;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {
    @Autowired
    private EmployeeServiceInf employeeServiceInf;
	
    //Create Employee
    
    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        Employee savedEmployee=employeeServiceInf.createEmployee(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEmployee);
    }
    
    
    
    //Get All Employee
	@GetMapping
	public ResponseEntity<List<Employee>> getAllEmployees() {
		List<Employee> employees=employeeServiceInf.getAllEmployees();
		return ResponseEntity.status(HttpStatus.OK).body(employees);
	}
	
	//Update Employee by Id
	@PutMapping("/{id}")
	public  ResponseEntity<Employee>  updateEmployee(@PathVariable int id, @RequestBody Employee employee) {
		Employee updatedEmployee=employeeServiceInf.updateEmployee(id,employee);
		return ResponseEntity.status(HttpStatus.OK).body(updatedEmployee);
	}
	
	//Delete Employee by Id
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteEmployee(@PathVariable int id){
		employeeServiceInf.deleteEmployee(id);
		return ResponseEntity.noContent().build();
	}
	
	
}
