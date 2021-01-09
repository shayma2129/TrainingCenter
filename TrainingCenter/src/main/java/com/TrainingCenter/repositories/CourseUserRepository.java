package com.TrainingCenter.repositories;


import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.TrainingCenter.Model.Entity.Course;
import com.TrainingCenter.Model.Entity.CourseUser;
import com.TrainingCenter.Model.Entity.User;
import com.TrainingCenter.Model.pk.CourseUserKey;


@Repository
public interface CourseUserRepository extends JpaRepository <CourseUser,Long> {
	
	 @Query("SELECT  course FROM Course As course, CourseUser As cu\r\n" + 
				"WHERE cu.user.id=:id AND cu.course.idCourse=course.idCourse and cu.autorisation=true ")
	 Page<Course> findByUserId(@Param("id")long userId,Pageable pageable);
	 Optional<CourseUser> findById(CourseUserKey id);
	 @Query("SELECT  user FROM User As user, CourseUser As cu\r\n" + 
				"WHERE cu.course.idCourse=:id AND cu.user.id=user.id and cu.autorisation=false")
	 Page<User> findByCourseIdCourse(@Param("id") long courseId,Pageable pageable);
	 @Query("SELECT  user FROM User As user, CourseUser As cu\r\n" + 
				"WHERE cu.course.idCourse=:id AND cu.user.id=user.id and cu.autorisation=true")
	 Page<User> findAcceptStudent(@Param("id") long courseId,Pageable pageable);
	 

}
