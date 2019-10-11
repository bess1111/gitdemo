package com.example.manager.rest.controller.student;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.manager.core.service.student.StudentService;

import com.example.manager.rest.vo.ResEntity;
import com.example.manager.rest.vo.student.ViewStudent;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;




@Api(tags="StudentController-学生接口")
@RestController
public class StudentController {

	@Autowired
	private StudentService studentService;
	
	
	//查询所有学生
		@RequestMapping(value="/students",method=RequestMethod.GET)
		@ApiOperation(value="查询所有学生接口",notes="查询所有学生接口")
		public ResEntity getStudentList() {
			List<ViewStudent> resVo = studentService.getStudentList();
			if(null==resVo) {
				return new ResEntity(null,false,"查询结果为空");
			}
			return new ResEntity(resVo,true,"查询成功");
		}
	
	//查询某个学生
	@RequestMapping(value="/student/{studentId}",method=RequestMethod.GET)
	@ApiOperation(value="根据学生id查询学生接口",notes="根据学生id查询学生接口")
	@ApiImplicitParam(paramType="path",name="studentId",value="学生id",required=true,dataType="Long")
	public ResEntity getStudent(@PathVariable Long studentId) {
		if(null==studentId) {
			return new ResEntity(null,false,"传入参数为空");
		}
		ViewStudent resVo = studentService.getStudent(studentId);
		if(null==resVo) {
			return new ResEntity(null,false,"查询结果为空");
		}
		return new ResEntity(resVo,true,"查询成功");
				
	}
	
	
	//新增某个学生
	@RequestMapping(value="/student",method=RequestMethod.POST)
	@ApiOperation(value="新增学生接口",notes="新增学生接口")
	@ApiImplicitParam(paramType="body",name="viewStudent",value="学生对象",required=false,dataType="ViewStudent")
	public ResEntity addStudent(@RequestBody ViewStudent viewStudent) {
		if(null==viewStudent||null==viewStudent.getStudentNumber()) {
			return new ResEntity(null,false,"传入参数为空");
		}
		//添加学生
		 ViewStudent resVo = studentService.addStudent(viewStudent);
		 if(null==resVo) {
			 return new ResEntity(null,false,"新增失败");
		 }
		 return new ResEntity(resVo,true,"新增成功");
	}
	
	//修改某个学生
	@RequestMapping(value="/student",method=RequestMethod.PUT)
	@ApiOperation(value="根据学生id修改学生接口",notes="根据学生id修改学生接口")
	@ApiImplicitParam(paramType="body",name="viewStudent",value="学生对象",required=true,dataType="ViewStudent")
	public ResEntity updateStudent(@RequestBody ViewStudent viewStudent) {
		if(null==viewStudent) {
			return new ResEntity(null,false,"传入参数为空");
		}
		ViewStudent resVo = studentService.updateStudent(viewStudent);
		 if(null==resVo) {
			 return new ResEntity(null,false,"更新失败");
		 }
		 return new ResEntity(resVo,true,"新增成功");
	}
	
	//删除某个学生
	@RequestMapping(value="/student/{studentId}",method=RequestMethod.DELETE)
	@ApiOperation(value="根据学生id删除学生接口",notes="根据学生id删除学生接口")
	@ApiImplicitParam(paramType="path",name="studentId",value="学生id",required=true,dataType="Long")
	public ResEntity deleteStudent(@PathVariable Long studentId) {
		if(null==studentId) {
			return new ResEntity(null,false,"传入参数为空");
		}
		ViewStudent resVo = studentService.deleteStudent(studentId);
		return new ResEntity(resVo,true,"成功");
	}
	
	//根据姓名年龄查询学生
	@RequestMapping(value="/student",method=RequestMethod.GET)
	@ApiOperation(value="查询学生接口",notes="查询学生接口")
	@ApiImplicitParams({
		@ApiImplicitParam(paramType="query",name="name",value="学生姓名",required=false,dataType="String"),
		@ApiImplicitParam(paramType="query",name="age",value="学生年龄",required=false,dataType="Integer")
	})
	public ResEntity getStudentByNameOrAge(@RequestParam(value="name",required=false) String name, @RequestParam(value="age",required=false) Integer age){
		/*if(null==name||"".equals(name)) {
			return new ResEntity(null,false,"传入参数为空");
		}*/
		List<ViewStudent> resVo = studentService.getStudentByNameOrAge(name, age);
		if(null==resVo) {
			return new ResEntity(null,false,"查询结果为空");
		}
		return new ResEntity(resVo,true,"查询成功");
	}
	
	//根据姓名和年龄查询
	@RequestMapping(value="studen",method=RequestMethod.GET)
	@ApiOperation(value="根据姓名和年龄查询学生接口",notes="根据姓名和年龄查询学生接口")
	@ApiImplicitParams({
		@ApiImplicitParam(value="学生姓名",name="name",paramType="query",dataType="String"),
		@ApiImplicitParam(value="学生年龄",name="age",paramType="query",dataType="Integer")
	})
	public ResEntity getStudentsByNameAndAge(
					@RequestParam(value="name",required=true) String name,
					@RequestParam(value="age",required=true) Integer age
			) {
		if(null==name||"".equals(name)||null==age) {
			return new ResEntity(null,false,"参数为空");
		}
		List<ViewStudent> resVo = studentService.getStudentByNameAndAge(name, age);
		if(null==resVo||resVo.isEmpty()) {
			return new ResEntity(null,false,"结果为空");
		}
		return new ResEntity(resVo,true,"成功");
	}
	
	
	
}
