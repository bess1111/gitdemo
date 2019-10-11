package com.example.manager.rest.controller.classes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.manager.core.service.classes.ClassesService;
import com.example.manager.rest.vo.ResEntity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@Api(tags="ClassCommandController-班级管理接口")
@RestController
public class ClassCommandController {
	@Autowired 
	private ClassesService classesService;

	//删除某个班级
		@RequestMapping(value="/classes/{classesId}",method=RequestMethod.DELETE)
		@ApiOperation(value="根据班级id删除班级接口",notes="根据班级id删除班级接口")
		@ApiImplicitParam(paramType="path",name="classesId",value="班级id",required=true,dataType="Long")
		public ResEntity deleteClasses(@PathVariable Long classesId) {
			if(null==classesId) {
				return new ResEntity(null,false,"传入参数为空");
			}
			Boolean resVo = classesService.deleteClasses(classesId);
			if(null!=resVo && resVo) {
				return new ResEntity(resVo,true,"成功");
			}
			return new ResEntity(null,false,"失败");
		}
}
