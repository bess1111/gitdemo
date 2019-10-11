package com.example.manager.core.entity;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Table(name="T_CLASS")
@Entity
public class Classes {

	@Id
	@GeneratedValue(generator = "sequenceGenerator")
	@GenericGenerator(
	         name = "sequenceGenerator",
	         strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
	         parameters = {@Parameter(name = "sequence_name", value = "SEQ_CLASS")}
	)
	private Long id;
	
	@Column
	private String name;
	@Column
	private Integer stuNumber;
	@Column
	private Timestamp createTime;
	@Column
	private Timestamp updateTime;
	
	@OneToMany(mappedBy="classes",fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	private List<Student> student;
	
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
	public Integer getStuNumber() {
		return stuNumber;
	}
	public void setStuNumber(Integer stuNumber) {
		this.stuNumber = stuNumber;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public Timestamp getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
	public List<Student> getStudent() {
		return student;
	}
	public void setStudent(List<Student> student) {
		this.student = student;
	}
	
	
}
