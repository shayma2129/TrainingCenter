package com.TrainingCenter.Model.Entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.TrainingCenter.Model.pk.CourseUserKey;


@Entity
public class CourseUser implements Serializable{
	
	private static final long serialVersionUID = 5750306877786924585L;
	
	 @EmbeddedId
	 CourseUserKey id;
	 
	 @ManyToOne(cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
	 @MapsId("idUser")
	 @JoinColumn(name = "idUser")
	 User user;
	    
	   
	 @ManyToOne(cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
	 @MapsId("idCourse")
	 @JoinColumn(name = "idCourse")
	 Course course;
	 
	 
	 @Temporal(TemporalType.TIMESTAMP)
	 @Column(name="Dateaffect",length=60)
	 private Date dateaffect = new Date();
	 
	 @Column(name="autorisation")
	 private Boolean autorisation ;

	public CourseUser(CourseUserKey id, User user, Course course, Date dateaffect, Boolean autorisation) {
		super();
		this.id = id;
		this.user = user;
		this.course = course;
		this.dateaffect = dateaffect;
		this.autorisation = autorisation;
	}

	public CourseUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CourseUserKey getId() {
		return id;
	}

	public void setId(CourseUserKey id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Date getDateaffect() {
		return dateaffect;
	}

	public void setDateaffect(Date dateaffect) {
		this.dateaffect = dateaffect;
	}

	public Boolean getAutorisation() {
		return autorisation;
	}

	public void setAutorisation(Boolean autorisation) {
		this.autorisation = autorisation;
	}
	 
	 
	 

}
