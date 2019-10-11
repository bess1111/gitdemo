package com.example.manager.core.service.student;

import java.util.List;

import com.example.manager.rest.vo.student.ViewStudent;

public interface StudentService {
	
	public ViewStudent getStudent(Long studentId);
	
	public List<ViewStudent> getStudentList();
	
	public ViewStudent addStudent(ViewStudent viewstudent);
	
	public ViewStudent updateStudent(ViewStudent viewstudent);
	
	public ViewStudent deleteStudent(Long studentId);
	
	public List<ViewStudent> getStudentByNameOrAge(String name,Integer age);
	
	public List<ViewStudent> getStudentByNameAndAge(String name,Integer age);
	
}
