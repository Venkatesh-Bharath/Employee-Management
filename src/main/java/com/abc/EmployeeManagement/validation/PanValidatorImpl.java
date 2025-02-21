package com.abc.EmployeeManagement.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PanValidatorImpl implements ConstraintValidator<Pan, String> { 
@Override
public boolean isValid(String pan, ConstraintValidatorContext context) {
	if(pan==null||pan.isEmpty())
	return false;
	
	return pan.matches("[A-Z]{5}[0-9]{4}[A-Z]{1}");
}
}
