package com.abc.EmployeeManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;


@OpenAPIDefinition(
		info = @Info(
		title = "Employee Management",
		version = "1.0",
		description = "Employee Management CRUD App",
		contact = @Contact(name ="Trending Technology",email = "bsktrending@gmail.com")
		),
        servers = {
        		@Server(url = "http://localhost:8080/",description = "Local application server"),
        		@Server(url = "http://employeemanamgemt.com",description = "Production server")
        }

		)


@SpringBootApplication
public class EmployeeManagementApplication {
    
	public static void main(String[] args) {
		SpringApplication.run(EmployeeManagementApplication.class, args);
	}

}
