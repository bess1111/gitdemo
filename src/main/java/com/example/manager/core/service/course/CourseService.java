package com.example.manager.core.service.course;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.manager.core.entity.Course;
import com.example.manager.rest.vo.course.ViewCourse;

public interface CourseService {
	
	List<ViewCourse> getAllCourse();
	
	ViewCourse getCourseById(Long courseId);
	
	ViewCourse deleteCourseById(Long courseId);
	
	ViewCourse addCourse(ViewCourse viewCourse);
	
	ViewCourse updateCourse(ViewCourse viewCourse,Long courseId);
	
	//分页查询所有课程
	Page<ViewCourse> getAllCourseByPage();
	
	//多条件查询
	Page<ViewCourse> getCoursesBySpecification(String name,Integer hour,Double credit,Integer pageNumber,Integer pageSize);
}
