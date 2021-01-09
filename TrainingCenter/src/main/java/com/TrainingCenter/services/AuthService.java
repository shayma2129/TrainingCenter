package com.TrainingCenter.services;


import org.springframework.http.ResponseEntity;

import com.TrainingCenter.Dto.LoginRequestDto;
import com.TrainingCenter.Dto.SignupRequestDto;


public interface AuthService {
	public ResponseEntity<?> registerUser(SignupRequestDto signupRequestDto);

	public ResponseEntity<?> authenticateUser( LoginRequestDto loginRequestDto) ;


}
