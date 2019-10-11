package com.example.manager.core.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Table(name="T_COURSE")
@Entity
public class Course {

	@Id
	@GeneratedValue(generator = "sequenceGenerator")
	@GenericGenerator(
	         name = "sequenceGenerator",
	         strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
	         parameters = {@Parameter(name = "sequence_name", value = "SEQ_COURSE")}
	)
	private Long id;
	
	@Column
	private String name;
	
	@Column
	private Integer hour;
	
	@Column
	private Double credit;
	
	@Column
	private Timestamp createTime;
	
	@Column
	private Timestamp updateTime;

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

	public Integer getHour() {
		return hour;
	}

	public void setHour(Integer hour) {
		this.hour = hour;
	}

	public Double getCredit() {
		return credit;
	}

	public void setCredit(Double credit) {
		this.credit = credit;
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
	
	
	
}
