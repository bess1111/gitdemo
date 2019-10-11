package com.example.manager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.manager.core.entity.Student;
import com.example.manager.rest.vo.student.ViewStudent;

public interface StudentRepository extends JpaRepository<Student, Long>{
	
	Student getStudentByNameOrAge(String name,Integer age);
	
	List<Student> findByName(String name);
	
	List<Student> findByAge(Integer age);
	
	List<Student> findByNameAndAge(String name,Integer age);
	
	Student findByStudentNumber(String studentNumber);
	
	@Query(value="select * from t_student where name = ?1 and age=?2",nativeQuery=true)
//	@Query(value="select * from T_STUDENT where name=:name and age=:age",nativeQuery=true)
	List<Student> getStudentsByNameAndAge(String name,Integer age);
}
