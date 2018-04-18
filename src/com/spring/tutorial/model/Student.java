package com.spring.tutorial.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "STUDENT")
public class Student {

	private long studentId;
	private String studentName;
	private Address studentAddress;

	public Student() {
	}

	public Student(String studentName, Address studentAddress) {
		this.studentName = studentName;
		this.studentAddress = studentAddress;
	}

	@Id
	@GeneratedValue
	@Column(name = "STUDENT_ID")
	public long getStudentId() {
		return this.studentId;
	}

	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}

	@Column(name = "STUDENT_NAME", nullable = false, length = 100)
	public String getStudentName() {
		return this.studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	/*
	 * If Cascade property is not set, then we get an error during saving Student object, below is the exception: 
	 * org.hibernate.TransientPropertyValueException: object references an unsaved transient instance - 
	 * save the transient instance before flushing
	 * */
	@OneToOne(cascade = CascadeType.ALL)	//will affect dependent tables (Address in this case) on CRUD operations
	@JoinColumn(name="STUDENT_ADDRESS")    //Unidirectional Relationship
	public Address getStudentAddress() {
		return this.studentAddress;
	}

	public void setStudentAddress(Address studentAddress) {
		this.studentAddress = studentAddress;
	}

}