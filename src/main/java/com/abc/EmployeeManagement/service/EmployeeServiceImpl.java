package com.abc.EmployeeManagement.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.abc.EmployeeManagement.model.Employee;
import com.abc.EmployeeManagement.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeServiceInf {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public Employee createEmployee(Employee employee) {
	
		return employeeRepository.save(employee);
	}

	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	@Override
	public Employee updateEmployee(int id, Employee employee) {
	    Employee emp=employeeRepository.findById(id)
	    		.orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee id "+id+" not avilable."));
	    	emp.setName(employee.getName());
	    	emp.setAddress(employee.getAddress());
	    	emp.setEmail(employee.getEmail());
	    	emp.setMobile(employee.getMobile());
	    	emp.setPan(employee.getPan());
	    	return employeeRepository.save(emp);
	}

	@Override
	public void deleteEmployee(int id) {
		Employee employee=employeeRepository.findById(id)
				.orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee id "+id+" not avilable."));
		employeeRepository.delete(employee);
	}
	
}
