package com.abc.EmployeeManagement.model;

import com.abc.EmployeeManagement.validation.Mobile;
import com.abc.EmployeeManagement.validation.Pan;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="Employee")
@Schema(description = "Employee entity representation")
public class Employee {
	@Id
	@GeneratedValue
	@Schema(description = "Unique Id for employee",example = "1")
	private int id;
	
	@NotBlank(message = "Name cannot blank")
	@Size(min = 3,message = "Name should be min 3 character")
	@Schema(description = "Employee name",example = "Bsk")
	private String name;
	
	@Email
	@NotBlank
	@Schema(description = "Employee email",example="bsk@gmail.com")
	private String email;
	
//	@Pattern(regexp = "[A-Z]{5}[0-9]{4}[A-Z]{1}",message = "Invalid PAN format")
	@Pan(message = "PAN card not valid")
	@NotBlank
	@Schema(description = "Employee PAN",example = "ASDAA8765A")
	private String pan;//DSGSP8765P
	
	@Schema(description = "Employee Address", example = "Andhra Pradesh")
	@Size(max = 100,message = "Address should not exceed 100 character")
	private String address;
	
//	@Pattern(regexp = "[6-9]{1}[0-9]{9}",message = "Invalid mobile format")
	@Mobile
	@Schema(description = "Employee Mobile", example = "9888888881")
	private String mobile;
	public Employee() {
	}
	
	public Employee(int id, String name, String email, String pan, String address, String mobile) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.pan = pan;
		this.address = address;
		this.mobile = mobile;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPan() {
		return pan;
	}
	public void setPan(String pan) {
		this.pan = pan;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

}
