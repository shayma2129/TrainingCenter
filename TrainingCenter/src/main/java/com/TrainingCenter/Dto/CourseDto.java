package com.TrainingCenter.Dto;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Lob;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import com.TrainingCenter.Model.Entity.Course;

public class CourseDto implements Serializable{
	
	private static final long serialVersionUID = 5750306877786924585L;
	/**
	 * Id Course
	 */
	private long idCourse;
	
	 /**
     * Title Course 
     */
	@NotBlank
	private String title_course;
    /**
     * Description Course 
     */
	 private String description_course;
    /**
     * Photo course
     */
	 @Lob
	 private byte[] photo;
     /**
      * Path photo
      */
     private String path_photo;
     /**
      * Location course 
      */
 	 private String location_course;
     /**
      * Start date course 
      */
     @Temporal(TemporalType.TIMESTAMP)
 	 private Date startdate_course;
     /**
      * End date course 
      */
     @Temporal(TemporalType.TIMESTAMP)
 	 private Date enddate_course;
     /**
      * Status course
      */
 	 private Boolean status_course;
     /**
      * number of subscribers  
      */
 	 private Long nb_subscribers;
	public CourseDto(long idCourse, @NotBlank String title_course, String description_course, byte[] photo,
			String path_photo, String location_course, Date startdate_course, Date enddate_course,
			Boolean status_course, Long nb_subscribers) {
		super();
		this.idCourse = idCourse;
		this.title_course = title_course;
		this.description_course = description_course;
		this.photo = photo;
		this.path_photo = path_photo;
		this.location_course = location_course;
		this.startdate_course = startdate_course;
		this.enddate_course = enddate_course;
		this.status_course = status_course;
		this.nb_subscribers = nb_subscribers;
	}
	public CourseDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public long getIdCourse() {
		return idCourse;
	}
	public void setIdCourse(long idCourse) {
		this.idCourse = idCourse;
	}
	public String getTitle_course() {
		return title_course;
	}
	public void setTitle_course(String title_course) {
		this.title_course = title_course;
	}
	public String getDescription_course() {
		return description_course;
	}
	public void setDescription_course(String description_course) {
		this.description_course = description_course;
	}
	public byte[] getPhoto() {
		return photo;
	}
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
	public String getPath_photo() {
		return path_photo;
	}
	public void setPath_photo(String path_photo) {
		this.path_photo = path_photo;
	}
	public String getLocation_course() {
		return location_course;
	}
	public void setLocation_course(String location_course) {
		this.location_course = location_course;
	}
	public Date getStartdate_course() {
		return startdate_course;
	}
	public void setStartdate_course(Date startdate_course) {
		this.startdate_course = startdate_course;
	}
	public Date getEnddate_course() {
		return enddate_course;
	}
	public void setEnddate_course(Date enddate_course) {
		this.enddate_course = enddate_course;
	}
	public Boolean getStatus_course() {
		return status_course;
	}
	public void setStatus_course(Boolean status_course) {
		this.status_course = status_course;
	}
	public Long getNb_subscribers() {
		return nb_subscribers;
	}
	public void setNb_subscribers(Long nb_subscribers) {
		this.nb_subscribers = nb_subscribers;
	}
	public static CourseDto from(Course course) {

        return new CourseDto(
        		course.getIdCourse(),
        		course.getTitle_course(),
                course.getDescription_course(),
                course.getPhoto(),
                course.getPath_photo(),
                course.getLocation_course(),
                course.getStartdate_course(),
                course.getEnddate_course(),
                course.getStatus_course(),
                course.getNb_subscribers()
               );

    }
     
}

