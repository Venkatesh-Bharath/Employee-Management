package com.abc.EmployeeManagement.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class MobileValidatorImpl implements ConstraintValidator<Mobile, String> {

	@Override
	public boolean isValid(String mobile, ConstraintValidatorContext context) {
		if(mobile==null||mobile.isEmpty())
			return false;
		
		return mobile.matches("[6-9]{1}[0-9]{9}");
	}

}
