package com.TrainingCenter.services;

import org.springframework.data.domain.Page;

import com.TrainingCenter.Model.Entity.Course;
import com.TrainingCenter.Model.Entity.CourseUser;
import com.TrainingCenter.Model.Entity.User;
import com.TrainingCenter.Model.pk.CourseUserKey;

public interface CourseUserService {

	public CourseUser getCourseUserById(CourseUserKey idkey);
	public Page<Course> getAllCourseOfStudent(long userid,int page, int size);
	public CourseUser subscribeCourse(long courseId,long userid);
	public CourseUser manageSubscribe(long courseId,long userid,boolean autorisation);
	public Page<User> getAllRequestOfCourseId(long courseid,int page, int size);
	public Page<User> getAcceptStudentOfCourseId(long courseid,int page, int size);
	
}
