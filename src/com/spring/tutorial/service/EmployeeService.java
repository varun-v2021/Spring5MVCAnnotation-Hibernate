package com.spring.tutorial.service;

import java.util.List;
import java.util.Optional;

import com.spring.tutorial.dto.EmployeeDTO;
import com.spring.tutorial.model.Employee;

public interface EmployeeService {
	public void create(EmployeeDTO emp);
	public Optional<Employee> findById(int id);
}
