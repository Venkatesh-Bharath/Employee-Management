package com.abc.EmployeeManagement.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="Employee")
public class Employee {
	@Id
	@GeneratedValue
	private int id;
	@NotBlank(message = "Name cannot blank")
	@Size(min = 3,message = "Name should be min 3 character")
	private String name;
	@Email
	private String email;
	@Pattern(regexp = "[A-Z]{5}[0-9]{4}[A-Z]{1}",message = "Invalid PAN format")
	private String pan;//DSGSP8765P
	@Size(max = 100,message = "Address should not exceed 100 character")
	private String address;
	@Pattern(regexp = "[6-9]{1}[0-9]{9}",message = "Invalid mobile format")
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
