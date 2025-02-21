package com.abc.EmployeeManagement.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy =PanValidatorImpl.class )
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Pan {
	String message() default "Invalid PAN Number";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
