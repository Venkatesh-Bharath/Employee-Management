package com.abc.EmployeeManagement.configurations;

import java.util.Map;

import org.springframework.boot.actuate.info.Info.Builder;
import org.springframework.stereotype.Component;
import org.springframework.boot.actuate.info.InfoContributor;

@Component
public class CustomInfoContributor implements InfoContributor {

	@Override
	public void contribute(Builder builder) {
		builder.withDetail("app", Map.of(
				"name","Employee Management",
				"version","1.0.0",
				"description","Spring Boot Employee Managment application"
				));
		
	}

}
