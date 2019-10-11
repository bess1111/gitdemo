package com.example.manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.manager.core.entity.Classes;


public interface ClassesRepository extends JpaRepository<Classes, Long>{

	@Query(value="select * from T_CLASS where id=?1",nativeQuery=true)
	Classes findByClassId(Long id);
	
	
}
