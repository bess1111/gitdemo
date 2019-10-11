package com.example.manager.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.manager.core.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Long>,JpaSpecificationExecutor<Course>{

	Course findByName(String name);
	
	
	
	@Query(value=" select * from\r\n" + 
			" (\r\n" + 
			" select rownum rn,t_course.* from t_course \r\n" + 
			"where 1=1 and (name = :name or :name is null) and (:hour is null or hour = :hour) and (:credit is null or credit = :credit)\r\n" + 
			") where rn between :rowBegin and :rowEnd",nativeQuery=true)
	List<Course> selectCouseListByPage( String name,
										Integer hour,
										Double credit,
										Integer rowBegin,
										Integer rowEnd);
	
	@Query(value="?1",nativeQuery=true)
	List<Course> getCouseListByPage(String sqlStr);
}
