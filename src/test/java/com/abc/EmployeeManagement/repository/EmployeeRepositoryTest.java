package com.abc.EmployeeManagement.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.abc.EmployeeManagement.model.Employee;

@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("Employee Repository Test")
public class EmployeeRepositoryTest {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	private Employee empolyee;
	
	@BeforeEach
	void setUp() {
		employeeRepository.deleteAll();
		empolyee=new Employee.Builder()
				.name("BSK")
				.email("bsk@gmail.com")
				.pan("GGGGG8765P")
				.address("Andhra pradesh")
				.mobile("9876543210")
				.build();
	}
	
	@Test
	@DisplayName("Save Employee Repository Test")
	void saveEmployeeTest() {
		Employee savedEmployee=employeeRepository.save(empolyee);
		
		assertThat(savedEmployee).isNotNull();
		assertThat(savedEmployee.getName()).isEqualTo(empolyee.getName());
		assertThat(savedEmployee.getEmail()).isEqualTo(empolyee.getEmail());	
	}
	
	@Test
	@DisplayName("Get all Employees")
	void getAllEmployees() {
		employeeRepository.save(empolyee);
		List<Employee> employees=employeeRepository.findAll();
		
		assertThat(employees).isNotEmpty();
		assertThat(employees.size()).isEqualTo(1);
	}
	
	@Test
	@DisplayName("Update Employee Repository Test")
	void updateEmployeeTest() {
		Employee savedEmployee=employeeRepository.save(empolyee);
		
		savedEmployee=new Employee.Builder()
				.id(savedEmployee.getId())
				.name("MAHI")
				.email("mahi@gmail.com")
				.pan("MMMMM8765P")
				.address("AP")
				.mobile("9876543211")
				.build();
		
		employeeRepository.save(savedEmployee);
		
		Optional<Employee> updatedEmployee=employeeRepository.findById(savedEmployee.getId());
		assertThat(updatedEmployee).isPresent();
		
		assertThat(updatedEmployee.get().getName()).isEqualTo(savedEmployee.getName());
		assertThat(updatedEmployee.get().getEmail()).isEqualTo(savedEmployee.getEmail());	
	}
	
	
	@Test
	@DisplayName("Delete Employee Repository Test")
	void deleteEmployeeTest() {
		Employee savedEmployee=employeeRepository.save(empolyee);
		employeeRepository.deleteById(savedEmployee.getId());
		
		Optional<Employee> deleteEmployee=employeeRepository.findById(savedEmployee.getId());
		assertThat(deleteEmployee).isEmpty();
		
	}

}
