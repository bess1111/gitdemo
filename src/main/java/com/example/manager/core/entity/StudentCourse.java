package com.example.manager.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Table(name="T_STUDENT_COURSE")
@Entity
public class StudentCourse {
	@Id
	@GeneratedValue(generator="")
	@GenericGenerator(
			 name = "sequenceGenerator",
	         strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
	         parameters = {@Parameter(name = "sequence_name", value = "SEQ_STUDENT_COURSE")}
			)
	private Long id;
	
	@Column
	private Long studentId;
	
	@Column
	private Long courseId;
	
	@Column
	private Double score;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}
	
	
	
	
}
