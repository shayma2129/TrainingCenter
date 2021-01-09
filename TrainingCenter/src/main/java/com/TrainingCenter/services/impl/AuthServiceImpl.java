package com.TrainingCenter.services.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.TrainingCenter.Dto.LoginRequestDto;
import com.TrainingCenter.Dto.SignupRequestDto;
import com.TrainingCenter.Model.Entity.ERole;
import com.TrainingCenter.Model.Entity.Role;
import com.TrainingCenter.Model.Entity.User;
import com.TrainingCenter.Request.LoginRequest;
import com.TrainingCenter.Request.SignupRequest;
import com.TrainingCenter.Response.JwtResponse;
import com.TrainingCenter.Response.MessageResponse;
import com.TrainingCenter.repositories.RoleRepository;
import com.TrainingCenter.repositories.UserRepository;
import com.TrainingCenter.security.jwt.JwtUtils;
import com.TrainingCenter.security.services.UserDetailsImpl;
import com.TrainingCenter.services.AuthService;



@Service
public class AuthServiceImpl implements AuthService{
	

	@Autowired
	UserRepository userRepository  ;
	
	@Autowired
	AuthenticationManager authenticationManager;

	

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;
		
	@Autowired
	ModelMapper modelMapper;
	
	
	@Transactional
	@Override
	public ResponseEntity<?> registerUser(SignupRequestDto signupRequestDto) {
		
		SignupRequest signUpRequest=modelMapper.map(signupRequestDto, SignupRequest.class);

		
		
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}

		// Create new user's account
		User user = new User(null, signUpRequest.getUsername(), 
							 signUpRequest.getEmail(),
							 encoder.encode(signUpRequest.getPassword()),						 
							 signUpRequest.isEnabled(),
							 null, 
							 signUpRequest.getPath(),
						     signUpRequest.getFile()
						     );

		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);

					break;
				case "mod":
					Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(modRole);

					break;
				default:
					Role userRole = roleRepository.findByName(ERole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}

		user.setRoles(roles);
		userRepository.save(user);
		signupRequestDto =modelMapper.map(signUpRequest, SignupRequestDto.class);
		
		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}



	@Transactional
	@Override	
	public ResponseEntity<?> authenticateUser(LoginRequestDto loginRequestDto) {
		
		LoginRequest loginRequest=modelMapper.map(loginRequestDto, LoginRequest.class);

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());
		loginRequestDto =modelMapper.map(loginRequest, LoginRequestDto.class);


		return ResponseEntity.ok(new JwtResponse(jwt, 
												 userDetails.getId(), 
												 userDetails.getUsername(), 
												 userDetails.getEmail(), 
												 roles));
	}



}
