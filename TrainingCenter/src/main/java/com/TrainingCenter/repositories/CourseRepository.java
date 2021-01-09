package com.TrainingCenter.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.TrainingCenter.Model.Entity.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {
	
	 Page<Course> findByUserId(long userId,Pageable pageable);

}
