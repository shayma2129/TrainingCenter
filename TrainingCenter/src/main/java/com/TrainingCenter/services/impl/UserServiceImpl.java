package com.TrainingCenter.services.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.TrainingCenter.Dto.SignupRequestDto;
import com.TrainingCenter.Model.Entity.ERole;
import com.TrainingCenter.Model.Entity.Role;
import com.TrainingCenter.Model.Entity.User;
import com.TrainingCenter.Response.MessageResponse;
import com.TrainingCenter.repositories.RoleRepository;
import com.TrainingCenter.repositories.UserRepository;
import com.TrainingCenter.security.jwt.JwtUtils;
import com.TrainingCenter.services.UserService;




@Service
public  class UserServiceImpl implements UserService  {
	
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
	



	@Transactional
	@Override
	public MessageResponse activate(Long id) {
		
		User user = userRepository.findById(id).orElse(null);
		if(user==null) {
			return new MessageResponse("Utilisateur n'existe pas");
		}
		
		user.setEnabled(!user.isEnabled());
		userRepository.save(user);
		return new MessageResponse("Opération effectuée avec succès");
	}





	@Transactional
	@Override
	public User findbyid(Long id) {
		// TODO Auto-generated method stub
		return userRepository.findById(id).get();
	}



	@Transactional
	@Override
	public void deleteUser(long id) {
		userRepository.deleteById(id);
		
	}
	
	@Transactional
	@Override
	public List<User> findAll(){
		return userRepository.findAll();
	}




	



	@Transactional
	@Override
	public ResponseEntity<?> updateUser(SignupRequestDto signUpRequest, long idUser_old) {
	User old_User=findbyid(idUser_old);
		
		old_User = new User(idUser_old, signUpRequest.getUsername(),
					
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

old_User.setRoles(roles);
		
		 userRepository.save(old_User);
		return ResponseEntity.ok(new MessageResponse("User updated successfully!"));

	}
	
	
	
	
}
