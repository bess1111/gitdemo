package com.example.manager.rest.controller.course;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.manager.core.entity.Course;
import com.example.manager.core.service.course.CourseService;
import com.example.manager.rest.vo.ResEntity;
import com.example.manager.rest.vo.course.ViewCourse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags="课程接口")
public class CourseController {

	@Autowired
	private CourseService courseService;
	
	@ApiOperation(value="根据courseId查询课程")
	@ApiImplicitParam(paramType="path",name="courseId",value="课程Id",required=true,dataType="Long")
	@RequestMapping(value="course/{courseId}",method=RequestMethod.GET)
	public ResEntity getStudentById(@PathVariable Long courseId) {
		if(null==courseId) {
			return new ResEntity(null,false,"参数为空");
		}
		//查询
		ViewCourse resVo = courseService.getCourseById(courseId);
		if(null==resVo) {
			return new ResEntity(null,false,"结果为空");
		}
		return new ResEntity(resVo,true,"查询成功");
	}
	
	@ApiOperation(value="根据courseId删除课程")
	@ApiImplicitParam(paramType="path",name="courseId",value="课程Id",required=true,dataType="Long")
	@RequestMapping(value="course/{courseId}",method=RequestMethod.DELETE)
	public ResEntity deleteCourseById(@PathVariable Long courseId) {
		if(null==courseId) {
			return new ResEntity(null,false,"参数为空");
		}
		ViewCourse resVo = courseService.deleteCourseById(courseId);
		return new ResEntity(resVo,true,"删除成功");
	}
	
	@ApiOperation(value="新增课程")
	@ApiImplicitParam(paramType="body",name="viewCourse",value="课程",required=true,dataType="ViewCourse")
	@RequestMapping(value="course",method=RequestMethod.POST)
	public ResEntity addCourse(@RequestBody ViewCourse viewCourse) {
		if(null==viewCourse) {
			return new ResEntity(null,false,"参数为空");
		}
		ViewCourse resVo = courseService.addCourse(viewCourse);
		if(null==resVo) {
			return new ResEntity(null,false,"添加失败");
		}
		return new ResEntity(resVo,true,"添加成功");
	}
	
	@ApiOperation(value="查询所有课程")
	@RequestMapping(value="courses",method=RequestMethod.GET)
	public ResEntity addCourse() {
		//List<ViewCourse> resVo = courseService.getAllCourse();    没分页
		Page<ViewCourse> resVo = courseService.getAllCourseByPage();
		//List<Course> resVo = page.getContent();
		if(null==resVo||resVo.isEmpty()) {
			return new ResEntity(null,false,"查询结果为空");
		}
		return new ResEntity(resVo,true,"查询成功");
	}
	
	@ApiOperation(value="修改课程")
	@ApiImplicitParams({
		@ApiImplicitParam(paramType="body",name="viewCourse",value="课程",required=true,dataType="ViewCourse"),
		@ApiImplicitParam(paramType="path",name="courseId",value="课程id",required=true,dataType="Long")	
		})
	@RequestMapping(value="course/{courseId}",method=RequestMethod.PUT)
	public ResEntity updateCourse(@RequestBody ViewCourse viewCourse,@PathVariable Long courseId) {
		if(null==viewCourse||null==courseId) {
			return new ResEntity(null,false,"参数为空");
		}
		ViewCourse resVo = courseService.updateCourse(viewCourse,courseId);
		if(null==resVo) {
			return new ResEntity(null,false,"更新失败");
		}
		return new ResEntity(resVo,true,"添加成功");
	}
	
	//多条件查询
	@ApiOperation(value="多条件查询课程")
	@ApiImplicitParams({
		@ApiImplicitParam(paramType="query",name="name",value="课程名",required=false,dataType="String"),
		@ApiImplicitParam(paramType="query",name="hour",value="课时",required=false,dataType="Integer"),	
		@ApiImplicitParam(paramType="query",name="credit",value="学分",required=false,dataType="Double"),
		@ApiImplicitParam(paramType="query",name="pageNumber",value="第几页",required=false,dataType="Integer"),
		@ApiImplicitParam(paramType="query",name="pageSize",value="每页大小",required=false,dataType="Integer")
		})
	@RequestMapping(value="course",method=RequestMethod.GET)
	public ResEntity getAllCourse(@RequestParam(value="name",required=false) String name,
								  @RequestParam(value="hour",required=false) Integer hour,
								  @RequestParam(value="credit",required=false) Double credit,
								  @RequestParam(value="pageNumber",required=false) Integer pageNumber,
								  @RequestParam(value="pageSize",required=false) Integer pageSize
			) {
		if(null==pageNumber) {
			pageNumber=0;
		}
		if(null==pageSize) {
			pageSize=5;
		}
		Page<ViewCourse> resVo = courseService.getCoursesBySpecification(name, hour, credit, pageNumber, pageSize);
		if(null==resVo||null==resVo.getContent()||resVo.getContent().isEmpty()) {
			return new ResEntity(null,false,"无内容");
		}
		return new ResEntity(resVo,true,"查询成功");
	}
	
}
