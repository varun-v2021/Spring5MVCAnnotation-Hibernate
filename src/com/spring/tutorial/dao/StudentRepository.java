package com.spring.tutorial.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.tutorial.model.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

}
