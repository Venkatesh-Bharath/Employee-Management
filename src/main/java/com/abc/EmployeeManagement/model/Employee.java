package com.abc.EmployeeManagement.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Employee")
public class Employee {
	@Id
	@GeneratedValue
	private int id;
	private String name;
	private String email;
	private String pan;
	private String address;
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
