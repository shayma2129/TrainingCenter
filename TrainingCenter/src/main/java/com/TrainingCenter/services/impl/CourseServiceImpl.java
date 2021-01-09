package com.TrainingCenter.services.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.TrainingCenter.Model.Entity.Course;
import com.TrainingCenter.Model.Entity.User;
import com.TrainingCenter.exceptions.DataNotFoundException;
import com.TrainingCenter.repositories.CourseRepository;
import com.TrainingCenter.services.CourseService;
import com.TrainingCenter.services.UserService;

@Service
public class CourseServiceImpl implements CourseService {
	
	private final CourseRepository courseRepository;
	private final UserService userService;
	@Autowired
	public CourseServiceImpl(CourseRepository courseRepository,UserService userService) {
		this.courseRepository = courseRepository;
		this.userService=userService;
	}

	@Override
	public Course createCourse(Course course,long userId) {
		User user=this.userService.findbyid(userId);
		course.setUser(user);
		return this.courseRepository.save(course);
	}


	@Override
    @Transactional
	public Course updateCourse(Course course, long courseId) {
		Course oldcourse=this.getCourseById(courseId);
		oldcourse.setIdCourse(courseId);
		oldcourse.setTitle_course(course.getTitle_course());
		oldcourse.setDescription_course(course.getDescription_course());
		oldcourse.setStartdate_course(course.getStartdate_course());
		oldcourse.setEnddate_course(course.getEnddate_course());
		oldcourse.setLocation_course(course.getLocation_course());
		oldcourse.setNb_subscribers(course.getNb_subscribers());
		oldcourse.setPath_photo(course.getPath_photo());
		oldcourse.setPhoto(course.getPhoto());
		oldcourse.setStatus_course(course.getStatus_course());
		oldcourse.setUser(course.getUser());
		return oldcourse;
	}

	@Override
	public Course getCourseById(long courseId) {
		return this.courseRepository.findById(courseId)
				.orElseThrow(() -> new DataNotFoundException(null, courseId));
	}

	@Override
	public void deleteCourse(long courseId) {
		this.courseRepository.deleteById(courseId);
	}

	@Override
	public Page<Course> getAllCourse(int page, int size) {
		return this.courseRepository.findAll(PageRequest.of(page, size));
	}
    
	@Override
	@Transactional
	public Page<Course> getAllCourseByUserId(long userid,int page, int size) {
		return this.courseRepository.findByUserId(userid, PageRequest.of(page, size));
	}

	

	

}
