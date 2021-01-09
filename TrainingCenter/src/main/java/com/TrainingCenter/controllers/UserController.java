package com.TrainingCenter.controllers;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.TrainingCenter.Dto.SignupRequestDto;
import com.TrainingCenter.Dto.UserDto;
import com.TrainingCenter.Model.Entity.User;
import com.TrainingCenter.Response.MessageResponse;
import com.TrainingCenter.services.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/UserMethode")
public class UserController {

	@Autowired
	UserServiceImpl userService;
	@Autowired
	ModelMapper modelMapper;
	
    private static String UPLOAD_DIR ="C:\\wamp64\\www\\Examen_Final\\photosUsers";

	
	@GetMapping(value = "/listUser")
	//@PreAuthorize("hasRole('ROLE_ADMIN')")
	public Object findAll() {
		List <User> listUser = userService.findAll();
		Type listType = new TypeToken<List<UserDto>>() {}.getType();
		List<UserDto> listUserDtos = modelMapper.map(listUser,listType);
		return ResponseEntity.status(HttpStatus.OK).body(listUserDtos);
	}
	//@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/activate/{id}")
	public MessageResponse activate(@PathVariable Long id) {
		return userService.activate(id);
	}
	
	
	@DeleteMapping("/deleteUser/{idU}")
    public Object deleteUser(@PathVariable("idU")Long idUser)
    {
		userService.deleteUser(idUser);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
	@GetMapping("/getUser/{idU}")
	public Object getUserById(@PathVariable("idU") Long idUser)
    {
		User user=userService.findbyid(idUser);
		UserDto userDto=modelMapper.map(user,UserDto.class);
		return ResponseEntity.status(HttpStatus.OK).body(userDto);
    }
	
	
	@PutMapping("/updateUser/{idU}")
	public Object updateUserInfo(@RequestParam(name="data",required = false) String data,@RequestParam("file") MultipartFile file,@PathVariable("idU") Long idU)throws Exception,IOException,JsonParseException , JsonMappingException  {
		  SignupRequestDto signUpRequestdto =new ObjectMapper().readValue(data, SignupRequestDto.class);
		  
		String filename= file.getOriginalFilename();
		String modifiedFileName="file_"+signUpRequestdto.getUsername()+"_"+System.currentTimeMillis()+"."+FilenameUtils.getExtension(filename);
		signUpRequestdto.setFile(file.getBytes());
		signUpRequestdto.setPath(modifiedFileName);
	    Path rootLocation = Paths.get(UPLOAD_DIR);
	    Files.copy(file.getInputStream(), rootLocation.resolve(modifiedFileName));
	    
		return userService.updateUser(signUpRequestdto, idU);
	}
}
