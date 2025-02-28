package com.abc.EmployeeManagement.configurations;

import java.util.Map;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

@Component
@Endpoint(id="custom")
public class CustomEndpoint {

	@ReadOperation
	public Map<String,String> customEndpoint(){
		return Map.of("message","this is custom actuator endpoint");
	}
}
