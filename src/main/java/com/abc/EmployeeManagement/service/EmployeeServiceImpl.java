package com.abc.EmployeeManagement.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.abc.EmployeeManagement.exception.ResourseNotFoundException;
import com.abc.EmployeeManagement.model.Employee;
import com.abc.EmployeeManagement.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeServiceInf {
	
	 private static final Logger logger=LoggerFactory.getLogger(EmployeeServiceImpl.class);

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public Employee createEmployee(Employee employee) {
	    logger.info("Called Creating Employee service method");
		Employee savedEmployee=employeeRepository.save(employee);
		logger.debug("Employee Created Succesfully",employee);
		return savedEmployee;
	}

	@Override
	public List<Employee> getAllEmployees() {
		logger.info("Called Featch all employes service method");
		List<Employee> employeeList=employeeRepository.findAll();
		logger.debug("Total employee Retrived{}",employeeList.size());
		return employeeList;
	}

	@Override
	public Employee updateEmployee(int id, Employee employee) {
		 logger.info("Called Update Employee service method");
	    Employee emp=employeeRepository.findById(id)
	    		.orElseThrow(()->{
					logger.error("Employee id {} not found",id);
					return new ResourseNotFoundException(HttpStatus.NOT_FOUND, "Employee id "+id+" not avilable.");
					});emp.setName(employee.getName());
	    	emp.setAddress(employee.getAddress());
	    	emp.setEmail(employee.getEmail());
	    	emp.setMobile(employee.getMobile());
	    	emp.setPan(employee.getPan());
	    Employee updatedEmployee=employeeRepository.save(emp);
	    logger.info("Update Employee successfuly");
	    return updatedEmployee;
	}

	@Override
	public void deleteEmployee(int id) {
		 logger.warn("Called Delete Employee service method");
		Employee employee=employeeRepository.findById(id)
				.orElseThrow(()->{
					logger.error("Employee id {} not found",id);
					return new ResourseNotFoundException(HttpStatus.NOT_FOUND, "Employee id "+id+" not avilable.");
					});
		employeeRepository.delete(employee);
		logger.info("Employee deleted successfully");
	}
	
}
