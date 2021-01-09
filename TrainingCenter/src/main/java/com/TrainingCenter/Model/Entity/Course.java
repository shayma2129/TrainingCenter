package com.TrainingCenter.Model.Entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name="Course")
@JsonInclude(value=Include.NON_NULL)
public class Course implements Serializable{
	
	private static final long serialVersionUID = 5750306877786924585L;
	/**
	 * Id Course
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="IDCourse")
	private long idCourse;
	
	 /**
     * Title Course 
     */
	@NotBlank
    @Column(name="title_course",length=120)
	private String title_course;
    /**
     * Description Course 
     */
     @Column(name="description_course",columnDefinition="TEXT")
	 private String description_course;
    /**
     * Photo course
     */
     @Column(name="photo")
	 @Lob
	 private byte[] photo;
     /**
      * Path photo
      */
     @Column(name="path_photo",length=255)
     private String path_photo;
     /**
      * Location course 
      */
     @Column(name="location_course",length=120)
 	 private String location_course;
     /**
      * Start date course 
      */
     @Temporal(TemporalType.TIMESTAMP)
     @Column(name="startdate_course",length=60)
 	 private Date startdate_course;
     /**
      * End date course 
      */
     @Temporal(TemporalType.TIMESTAMP)
     @Column(name="enddate_course",length=60)
 	 private Date enddate_course;
     /**
      * Status course
      */
     @Column(name="status_course")
 	 private Boolean status_course;
     /**
      * number of subscribers  
      */
     @Column(name="nb_subscribers",length=120)
 	 private Long nb_subscribers;
     /**
      * 
      */
     @ManyToOne
     @JoinColumn(name = "user_id")
     private User user;
     
	 
	public Course(long id_Course, @NotBlank String title_course, String description_course, byte[] photo,
			String path_photo, String location_course, Date startdate_course, Date enddate_course,
			Boolean status_course, Long nb_subscribers, User user) {
		super();
		this.idCourse = id_Course;
		this.title_course = title_course;
		this.description_course = description_course;
		this.photo = photo;
		this.path_photo = path_photo;
		this.location_course = location_course;
		this.startdate_course = startdate_course;
		this.enddate_course = enddate_course;
		this.status_course = status_course;
		this.nb_subscribers = nb_subscribers;
		this.user = user;
	}
	public Course() {
		super();
		// TODO Auto-generated constructor stub
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
	public long getIdCourse() {
		return idCourse;
	}
	public void setIdCourse(long idCourse) {
		this.idCourse = idCourse;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
     
     
}
