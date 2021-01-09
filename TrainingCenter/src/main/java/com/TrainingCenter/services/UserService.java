package com.TrainingCenter.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.TrainingCenter.Dto.SignupRequestDto;
import com.TrainingCenter.Model.Entity.User;
import com.TrainingCenter.Response.MessageResponse;


public interface UserService {
	
	public List<User> findAll();
	public MessageResponse activate(Long id) ;

	public ResponseEntity<?> updateUser(SignupRequestDto signupRequest,long idUser_old);

	public  User findbyid(Long  id);
	
	public void deleteUser(long id);
	

}
