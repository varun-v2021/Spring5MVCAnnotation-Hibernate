package com.spring.tutorial.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.tutorial.dao.StudentRepository;
import com.spring.tutorial.model.Student;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	StudentRepository studentRepository;
	
	@Override
	public void create(Student stu) {
		// TODO Auto-generated method stub
		studentRepository.save(stu);
	}

}
