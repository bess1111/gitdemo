package com.example.manager.core.service.student;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.manager.core.entity.Classes;
import com.example.manager.core.entity.Student;
import com.example.manager.repository.ClassesRepository;
import com.example.manager.repository.StudentRepository;
import com.example.manager.rest.vo.student.ViewStudent;

@Service
public class StudentServiceImpl implements StudentService{
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired 
	private StudentRepository studentRepository;
	@Autowired
	private ClassesRepository classesRepository;
	
	@Override
	public ViewStudent getStudent(Long studentId) {
		//判空
		if(null==studentId) {
			this.logger.warn("the param of getStudent is null");
			return null;
		}
		//根据id查询
		Optional<Student> student = studentRepository.findById(studentId);
		if(false==student.isPresent()) {
			this.logger.warn("student can not be found");
			return null;
		}
		//转成vo
		ViewStudent viewStudent = this.getViewStudentByStudent(student.get());
		return viewStudent;
	}
	
	//学生po转vo
	public ViewStudent getViewStudentByStudent(Student student) {
		ViewStudent viewStudent = new ViewStudent();
		BeanUtils.copyProperties(student, viewStudent);
		return viewStudent;
	}

	//学生列表
	@Override
	public List<ViewStudent> getStudentList() {
		//查询所有
		List<Student> students = studentRepository.findAll();
		if(students==null||students.isEmpty()) {
			this.logger.warn("studentList can not be found");
			return null;
		}
		//转成vo
		List<ViewStudent> viewStudents = this.getViewStudentsByStudents(students);
		return viewStudents;
	}

	public List<ViewStudent> getViewStudentsByStudents(List<Student> students) {
		List<ViewStudent> viewStudents = new ArrayList<>();
		for (Student student : students) {
			ViewStudent viewStudent = new ViewStudent();
			BeanUtils.copyProperties(student, viewStudent);
			viewStudents.add(viewStudent);
		}
		return viewStudents;
	}
	
	//添加学生
	@Override
	public ViewStudent addStudent(ViewStudent viewStudent) {
		//判空
		if(null==viewStudent||null==viewStudent.getStudentNumber()) {
			this.logger.warn("the param of addStudent is null");
			return null;
		}
		//vo转po
		Student student = new Student();
		BeanUtils.copyProperties(viewStudent, student);
		//判断是否已存在该学生
		Student stu = studentRepository.findByStudentNumber(student.getStudentNumber());
		if(null!=stu) {
			this.logger.warn("the studentNumber already exist");
			return null;
		}
		//插入
		 student = studentRepository.save(student);
		 if(student==null) {
				this.logger.warn("添加失败");
				return null;
			}
		 //po转vo
		 viewStudent = this.getViewStudentByStudent(student);
		 return viewStudent;
	}

	//修改
	@Override
	public ViewStudent updateStudent(ViewStudent viewStudent) {
		//判空
		if(null==viewStudent) {
			this.logger.warn("the param of updateStudent is null");
			return null;
		}
		if(null==viewStudent.getId()) {
			this.logger.warn("the id of updateStudent is null");
			return null;
		}
		//vo转po
		Student student = new Student();
		BeanUtils.copyProperties(viewStudent, student);
		//查询是否有该学生
		Optional<Student> stu = studentRepository.findById(student.getId());
		if(false==stu.isPresent()) {
			this.logger.warn("the student does not exist");
			return null;
		}
		//更新
		student = studentRepository.save(student);
		if(student==null) {
			this.logger.warn("更新失败");
			return null;
		}
		//po转vo
		viewStudent = this.getViewStudentByStudent(student);
		return viewStudent;
	}

	@Override
	public ViewStudent deleteStudent(Long studentId) {
		if(studentId==null) {
			this.logger.warn("the param of updateStudent is null");
			return null;
		}
		Optional<Student> student = studentRepository.findById(studentId);
		if(false==student.isPresent()) {
			this.logger.warn("the student does not exsit");
			return null;
		}
		studentRepository.deleteById(studentId);
		return null;
	}

	@Override
	public List<ViewStudent> getStudentByNameOrAge(String name, Integer age) {
		//1.不传参 查所有
		if(null==name&&age==null) {
			List<Student> students = studentRepository.findAll();
			if(null==students||students.isEmpty()) {
				this.logger.warn("the student does not exsit");
				return null;
			}
			//转成vo
			List<ViewStudent> viewStudents = this.getViewStudentsByStudents(students);
			return viewStudents;
		}
		//2.传name，根据name查
		else if(null!=name&&age==null) {
			List<Student> students = studentRepository.findByName(name);
			if(null==students||students.isEmpty()) {
				this.logger.warn("the student does not exsit");
				return null;
			}
			//转成vo
			List<ViewStudent> viewStudents = this.getViewStudentsByStudents(students);
			return viewStudents;
		}
		//3.传age,根据age查
		else if(null==name&&null!=age) {
			List<Student> students = studentRepository.findByAge(age);
			if(null==students||students.isEmpty()) {
				this.logger.warn("the student does not exsit");
				return null;
			}
			//转成vo
			List<ViewStudent> viewStudents = this.getViewStudentsByStudents(students);
			return viewStudents;
		}
		//4.都传，两个一起查
		else{
			List<Student> students = studentRepository.findByNameAndAge(name, age);
			if(null==students||students.isEmpty()) {
				this.logger.warn("the student does not exsit");
				return null;
			}
			//转成vo
			List<ViewStudent> viewStudents = this.getViewStudentsByStudents(students);
			return viewStudents;
		}
		
	}

	@Override
	public List<ViewStudent> getStudentByNameAndAge(String name, Integer age) {
		if(name==null||"".equals(name)||null==age) {
			this.logger.warn("the param of updateStudent is null");
			return null;
		}
		List<Student> students = this.studentRepository.getStudentsByNameAndAge(name, age);
		if(null==students||students.isEmpty()) {
			this.logger.warn("students is null");
			return null;
		}
		List<ViewStudent> viewStudents = new ArrayList<>();
		for (Student student : students) {
			Classes classes = this.classesRepository.findByClassId(student.getClasses().getId());
			ViewStudent viewStudent = new ViewStudent();
			BeanUtils.copyProperties(student, viewStudent);
			viewStudent.setClassName(classes.getName());
			System.out.println(viewStudent.getClassName());
			viewStudents.add(viewStudent);
		}
		
		return viewStudents;
	}

	

}
