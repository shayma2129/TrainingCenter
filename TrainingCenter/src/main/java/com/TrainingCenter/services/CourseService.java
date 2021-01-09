package com.TrainingCenter.services;

import org.springframework.data.domain.Page;

import com.TrainingCenter.Model.Entity.Course;

public interface CourseService {
	
	public Course createCourse(Course course,long userId);
	public Course updateCourse(Course course,long courseId);
	public Course getCourseById(long courseId);
	public void deleteCourse(long courseId);
	public Page<Course> getAllCourse(int page, int size);
	public Page<Course> getAllCourseByUserId(long userid,int page, int size);
	


}
