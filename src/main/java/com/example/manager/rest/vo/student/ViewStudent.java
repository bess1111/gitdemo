package com.example.manager.rest.vo.student;

import java.sql.Timestamp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="学生对象模型")
public class ViewStudent {

	@ApiModelProperty(value="学生id")
	private Long id;
	@ApiModelProperty(value="学号")
	private String studentNumber;
	@ApiModelProperty(value="学生姓名")
	private String name;
	@ApiModelProperty(value="学生年龄")
	private Integer age;
	@ApiModelProperty(value="创建时间")
	private Timestamp create_time;
	@ApiModelProperty(value="修改时间")
	private Timestamp update_time;
	@ApiModelProperty(value="预留字段")
	private String reserve;
	private Long classId;
	private String className;

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
	public Timestamp getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Timestamp create_time) {
		this.create_time = create_time;
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
	public Long getClassId() {
		return classId;
	}
	public void setClassId(Long classId) {
		this.classId = classId;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	
	
	
}
