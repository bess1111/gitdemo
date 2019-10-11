package com.example.manager.core.service.course;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.manager.core.entity.Course;
import com.example.manager.excption.CustomBusinessException;
import com.example.manager.repository.CourseRepository;
import com.example.manager.rest.vo.course.ViewCourse;

@Service
public class CourseServiceImpl implements CourseService{

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CourseRepository courseRepository;
	
	//根据id查询课程
	@Override
	public ViewCourse getCourseById(Long courseId) {
		//传入参数判空
		if(null==courseId) {
			this.logger.warn("the param of getCourseById is null");
			return null;
		}
		//查询
		Optional<Course> course = this.courseRepository.findById(courseId);
		//查询结果判空
		if(false==course.isPresent()) {
			this.logger.warn("course can not be found");
			return null;
		}
		//po转vo
		ViewCourse viewCourse = this.getViewCourseByCourse(course.get());
		return viewCourse;
		
	}
	
	//课程po转vo
	public ViewCourse getViewCourseByCourse(Course course) {
		ViewCourse viewCourse = new ViewCourse();
		BeanUtils.copyProperties(course, viewCourse);
		return viewCourse;
	}

	//删除
	@Override
	public ViewCourse deleteCourseById(Long courseId) {
		if(null==courseId) {
			this.logger.warn("the param of getCourseById is null");
			return null;
		}
		//根据id查询是否存在该课程
		Optional<Course> course = courseRepository.findById(courseId);
		if(false==course.isPresent()) {
			throw new CustomBusinessException("课程数据为空，请先添加数据库数据");
		}
		this.courseRepository.deleteById(courseId);
		return null;
	}

	//新增课程
	@Override
	public ViewCourse addCourse(ViewCourse viewCourse) {
		if(null==viewCourse) {
			this.logger.warn("the param of addCourse is null");
			return null;
		}
		
		if(null==viewCourse.getName()||"".equals(viewCourse.getName())) {
			this.logger.warn("the name of course is null");
			return null;
		}
		//vo转po
		Course course = new Course();
		BeanUtils.copyProperties(viewCourse, course);
		
		//根据课程名查询是否已经存在该课程
		Course c = courseRepository.findByName(course.getName());
		if(null!=c) {
			this.logger.warn("the id already exists");
			return null;
		}
		//插入课程
		Timestamp currentTime = new Timestamp(System.currentTimeMillis());
		course.setCreateTime(currentTime);
		course = courseRepository.save(course);
		if(null==course) {
			this.logger.warn("course can not be found");
			return null;
		}
		//po转vo
		viewCourse = this.getViewCourseByCourse(course);
		return viewCourse;
	}

	//查询所有课程
	@Override
	public List<ViewCourse> getAllCourse() {
		List<Course> courses = courseRepository.findAll();
		if(null==courses||courses.isEmpty()) {
			this.logger.warn("course can not be found");
			return null;
		}
		List<ViewCourse> viewCourses = this.getViewCoursesByCourses(courses);
		return viewCourses;
	}
	
	//分页查询所有课程
		@Override
		public Page<ViewCourse> getAllCourseByPage() {
			List<Course> list = this.courseRepository.getCouseListByPage("select * from t_course");
			System.out.println(list);
			Sort.Direction sort = Sort.Direction.DESC;
			//Pageable pageable = PageRequest.of(1, 2);
			Pageable pageable1 = PageRequest.of(0, 3, sort, "id");
			Page<Course> page = courseRepository.findAll(pageable1);
			if(null==page.getContent()||page.getContent().isEmpty()) {
				this.logger.warn("course can not be found");
				return null;
			}
			//po转vo
			List<Course> courses = page.getContent();
			List<ViewCourse> viewCourses = this.getViewCoursesByCourses(courses);
			Page<ViewCourse> pageViewCourses = new PageImpl<ViewCourse>(viewCourses, pageable1, page.getTotalElements());
			return pageViewCourses;
		}
	
	//po转成vo
	public List<ViewCourse> getViewCoursesByCourses(List<Course> courses) {
		List<ViewCourse> ViewCourses = new ArrayList<>();
		for (Course course : courses) {
			ViewCourse viewCourse = new ViewCourse();
			BeanUtils.copyProperties(course, viewCourse);
			ViewCourses.add(viewCourse);
		}
		return ViewCourses;
	}

	//修改课程
	@Override
	public ViewCourse updateCourse(ViewCourse viewCourse,Long courseId) {
		//判空
		if(null==viewCourse||null==courseId) {
			this.logger.warn("the param of updateCourse is null");
			return null;
		}
		//vo转po
		Course course = new Course();
		BeanUtils.copyProperties(viewCourse, course);
		//根据id查询是否有该课程
		Optional<Course> c = courseRepository.findById(courseId);
		if(false==c.isPresent()) {
			this.logger.warn("course can not be found");
			return null;
		}
		//更新  
		course.setId(courseId);    //id设置为path上的id
		Timestamp currentTime = new Timestamp(System.currentTimeMillis());
		course.setUpdateTime(currentTime);
		course = courseRepository.save(course);
		if(null==course) {
			this.logger.warn("更新失败");
			return null;
		}
		//po转vo
		viewCourse = this.getViewCourseByCourse(course);
		return viewCourse;
	}

	//多条件分页查询课程
	@Override
	public Page<ViewCourse> getCoursesBySpecification(String name, Integer hour, Double credit, Integer pageNumber,
			Integer pageSize) {
		if(null==pageNumber) {
			pageNumber=0;
		}
		if(null==pageSize) {
			pageSize=5;
		}
		
		Integer rowBegin = (pageNumber-1)*pageSize+1;
		Integer rowEnd = (pageNumber-1)*pageSize+pageSize;
		if(null==hour) {
			hour =0;
		}
		if(null==credit) {
			credit=0.0;
		}
		//查询某页的课程
		List<Course> courses = this.courseRepository.selectCouseListByPage(name,hour,credit,rowBegin, rowEnd);
//		List<Course> courses = this.courseRepository.getCouseListByPage("t_course");
		for (Course course : courses) {
			System.out.println(course.getName());
		}
		//根据参数的查询条件
		Specification<Course> specCourses = this.getCourseSpecificationByParam(name,hour,credit);
		
		
		//分页
		Sort.Direction desc = Sort.Direction.DESC;
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		
		//查询
		Page<Course> pageCourses = this.courseRepository.findAll(specCourses, pageable);
		//Specification<Course> specCourses = this.getCourseSpecificationByParam(name,hour,credit,pageNumber,pageSize);
		//List<Course> findAll = courseRepository.findAll(specCourses);
		
		//判空
		if(null==pageCourses) {
			this.logger.warn("查询结果为空");
			return null;
		}
		if(null==pageCourses.getContent()||pageCourses.getContent().isEmpty()) {
			this.logger.warn("查询结果为空");
			return null;
		}
		//po转vo
		List<ViewCourse> viewCourses = new ArrayList<>();
		for (Course course : pageCourses.getContent()) {
			ViewCourse viewCourse = new ViewCourse();
			BeanUtils.copyProperties(course, viewCourse);
			viewCourses.add(viewCourse);
		}
		
		//加入分页
		Page<ViewCourse> pageViewCourses = new PageImpl<>(viewCourses, pageable, pageCourses.getTotalElements());
		
		return pageViewCourses;
	}
	
	//条件参数
		private Specification<Course> getCourseSpecificationByParam(String name, Integer hour, Double credit){
		return new Specification<Course>() {


			@Override
			public Predicate toPredicate(Root<Course> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				
				List<Predicate> predicates = new ArrayList<>();
				
				if(null!=name|| "".equals(name)) {
					Predicate predicate = cb.or(cb.like(root.get("name").as(String.class),"%"+name+"%"));
					predicates.add(predicate);
				}
				if(null!=hour) {
					Predicate predicate = cb.equal(root.get("hour").as(Integer.class), hour);
					predicates.add(predicate);
				}
				
				if(null!=credit) {
				Predicate predicate = cb.equal(root.get("credit").as(Double.class), credit);
					predicates.add(predicate);
				}
				return query.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
			}
		};
	}

	
	
	
}
