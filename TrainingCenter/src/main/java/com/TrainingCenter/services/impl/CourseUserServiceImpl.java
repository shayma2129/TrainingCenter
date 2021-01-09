package com.TrainingCenter.services.impl;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.TrainingCenter.Model.Entity.Course;
import com.TrainingCenter.Model.Entity.CourseUser;
import com.TrainingCenter.Model.Entity.User;
import com.TrainingCenter.Model.pk.CourseUserKey;
import com.TrainingCenter.repositories.CourseUserRepository;
import com.TrainingCenter.services.CourseService;
import com.TrainingCenter.services.CourseUserService;
import com.TrainingCenter.services.UserService;

@Service
public class CourseUserServiceImpl implements CourseUserService {
	
	
	private final CourseUserRepository courseuserRepository;
	private final CourseService courseService;
	private final UserService userService;
	@Autowired
	public CourseUserServiceImpl(CourseUserRepository courseuserRepository,CourseService courseService,UserService userService) {
		this.courseuserRepository = courseuserRepository;
		this.courseService = courseService;
		this.userService = userService;
	}
	
	@Override
	@Transactional
	public Page<Course> getAllCourseOfStudent(long userid, int page, int size) {
		
		return this.courseuserRepository.findByUserId(userid, PageRequest.of(page, size));
	}
	
	@Override
	@Transactional
	public CourseUser subscribeCourse(long courseId, long userid) {
		CourseUserKey key=new CourseUserKey(userid,courseId);
		Course course=this.courseService.getCourseById(courseId);
		User user=this.userService.findbyid(userid);
		CourseUser courseuser=new CourseUser(key,user,course,new Date(),false);
	   return this.courseuserRepository.save(courseuser);
	}

	@Override
	@Transactional
	public CourseUser manageSubscribe(long courseId, long userid,boolean autorisation) {
		CourseUserKey key=new CourseUserKey(userid,courseId);
		CourseUser courseUser=getCourseUserById(key);
		courseUser.setAutorisation(autorisation);
		return courseUser;
	}

	@Override
	public CourseUser getCourseUserById(CourseUserKey idkey) {
		
		return this.courseuserRepository.findById(idkey).get();
	}

	@Override
	@Transactional
	public Page<User> getAllRequestOfCourseId(long courseid, int page, int size) {
		
		return this.courseuserRepository.findByCourseIdCourse(courseid,PageRequest.of(page, size));
	}

	@Override
	@Transactional
	public Page<User> getAcceptStudentOfCourseId(long courseid, int page, int size) {
		return this.courseuserRepository.findAcceptStudent(courseid, PageRequest.of(page, size));
	}

}
