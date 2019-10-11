package com.example.manager.core.service.classes;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.manager.core.entity.Classes;
import com.example.manager.repository.ClassesRepository;

@Service
public class ClassesServiceImpl implements ClassesService{
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ClassesRepository classesRepository;
	
	@Override
	public Boolean deleteClasses(Long classesId) {
		if(null==classesId) {
			this.logger.info("param is null");
			return null;
		}
		Classes classes = this.getClassesById(classesId);
		if(null==classes) {
			this.logger.info("classes does not exsis");
			return null;
		}
		classesRepository.deleteById(classesId);
		return true;
	}

	public Classes getClassesById(Long classesId) {
		Optional<Classes> optClasses = classesRepository.findById(classesId);
		return optClasses.get();
	}
	

}
