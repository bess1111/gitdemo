package com.example.manager.core.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Table(name="T_STUDENT")
@Entity
public class Student {
	
	@Id
	@GeneratedValue(generator = "sequenceGenerator")
	@GenericGenerator(
	         name = "sequenceGenerator",
	         strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
	         parameters = {@Parameter(name = "sequence_name", value = "SEQ_STUDENT")}
	)
	private Long id;
	
	@Column
	private String studentNumber;
	
	@Column
	private String name;
	
	@Column
	private Integer age;
	
	@Column
	private Timestamp crate_time;
	
	@Column
	private Timestamp update_time;
	
	@Column
	private String reserve;
	
	
	@ManyToOne
	@JoinColumn(name="CLASSES_ID",nullable=false)
	private Classes classes;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Timestamp getCrate_time() {
		return crate_time;
	}
	public void setCrate_time(Timestamp crate_time) {
		this.crate_time = crate_time;
	}
	public Timestamp getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Timestamp update_time) {
		this.update_time = update_time;
	}
	public String getReserve() {
		return reserve;
	}
	public void setReserve(String reserve) {
		this.reserve = reserve;
	}
	public String getStudentNumber() {
		return studentNumber;
	}
	public void setStudentNumber(String studentNumber) {
		this.studentNumber = studentNumber;
	}
	
	public Classes getClasses() {
		return classes;
	}
	public void setClasses(Classes classes) {
		this.classes = classes;
	}
	
	
	
}
