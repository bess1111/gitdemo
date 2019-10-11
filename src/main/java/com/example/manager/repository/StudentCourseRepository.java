package com.example.manager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.manager.core.entity.Student;
import com.example.manager.core.entity.StudentCourse;

public interface StudentCourseRepository extends JpaRepository<StudentCourse, Long>{

	/*@Query(value="select * from T_STUDENT where name=?1 and age=?2",nativeQuery=true)
	List<Student> getStudentsByNameAndAge(String name,Integer age);*/
}
