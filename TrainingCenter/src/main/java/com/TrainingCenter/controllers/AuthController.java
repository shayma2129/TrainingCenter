package com.TrainingCenter.controllers;



import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.validation.Valid;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.TrainingCenter.Dto.LoginRequestDto;
import com.TrainingCenter.Dto.SignupRequestDto;
import com.TrainingCenter.services.impl.AuthServiceImpl;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

	

	
	@Autowired
	AuthServiceImpl authServiceImpl;
	
    private static String UPLOAD_DIR ="C:\\wamp64\\www\\Examen_Final\\photosUsers";


	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequestDto loginRequestDto) {
		return authServiceImpl.authenticateUser(loginRequestDto);

		
	}
	
	
	@PostMapping("/signup")	
	public ResponseEntity<?> registerUser(@RequestParam(name="data",required = false) String data,@RequestParam("file") MultipartFile file)throws Exception,IOException,JsonParseException , JsonMappingException  {
			  SignupRequestDto signUpRequestdto =new ObjectMapper().readValue(data, SignupRequestDto.class);
				String filename= file.getOriginalFilename();
				String modifiedFileName="file_"+signUpRequestdto.getUsername()+"_"+System.currentTimeMillis()+"."+FilenameUtils.getExtension(filename);
				signUpRequestdto.setFile(file.getBytes());
				signUpRequestdto.setPath(modifiedFileName);
			    Path rootLocation = Paths.get(UPLOAD_DIR);
			    Files.copy(file.getInputStream(), rootLocation.resolve(modifiedFileName));
		return authServiceImpl.registerUser(signUpRequestdto);
		
		
	}
	}	