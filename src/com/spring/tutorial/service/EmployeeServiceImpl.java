package com.spring.tutorial.service;

import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.tutorial.dao.EmployeeRepository;
import com.spring.tutorial.dto.EmployeeDTO;
import com.spring.tutorial.model.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public void create(EmployeeDTO empDTO) {
		// TODO Auto-generated method stub
		/*
		 * Employee emp = new Employee(); emp.setFirstName(fName);
		 * emp.setLastName(""); emp.setSalary(1000);
		 */
		Employee emp = getEntityFromDTO(empDTO);
		employeeRepository.save(emp);
	}

	@Override
	public Optional<Employee> findById(int id) {
		// TODO Auto-generated method stub
		return employeeRepository.findById(id);
	}

	private Employee getEntityFromDTO(EmployeeDTO empDTO) {
		Employee emp = new Employee();
		emp.setFirstName(empDTO.getFirstName());
		emp.setLastName(empDTO.getLastName());
		emp.setSalary(empDTO.getSalary());
		return emp;
	}

}
