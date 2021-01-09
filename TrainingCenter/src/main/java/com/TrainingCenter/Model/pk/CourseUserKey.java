package com.TrainingCenter.Model.pk;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CourseUserKey implements Serializable {
	
	private static final long serialVersionUID = -6704381302698819724L;
	
	@Column(name = "idUser")
	public Long idUser;
	
	@Column(name = "idCourse")
	public Long idCourse;
	
	
	public CourseUserKey(Long idUser, Long idCourse) {
		super();
		this.idUser = idUser;
		this.idCourse = idCourse;
	}
	public CourseUserKey() {
		super();
		// TODO Auto-generated constructor stub
	}

	
}
