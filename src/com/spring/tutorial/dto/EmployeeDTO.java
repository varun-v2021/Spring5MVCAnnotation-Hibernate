package com.spring.tutorial.dto;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class EmployeeDTO {
	@Id
	@GeneratedValue
	private int id;

	private String firstName;

	private String lastName;

	private int salary;

	public EmployeeDTO() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String first_name) {
		this.firstName = first_name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String last_name) {
		this.lastName = last_name;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}
}