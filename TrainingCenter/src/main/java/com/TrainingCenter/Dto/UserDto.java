package com.TrainingCenter.Dto;

import java.util.HashSet;
import java.util.Set;
import com.TrainingCenter.Model.Entity.Role;
import com.TrainingCenter.Model.Entity.User;

public class UserDto {

	
	private Long id;

	private String username;

	
	private String email;

	
	private String password;


    private boolean enabled;
    
	private String path;
	/**
	 * file
	 */
	
	 private byte[] file;

	private Set<Role> roles = new HashSet<>();

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public boolean isEnabled() {
		return enabled;
	}


	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}





	public Set<Role> getRoles() {
		return roles;
	}


	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}


	public UserDto() {
		super();
		// TODO Auto-generated constructor stub
	}


	public UserDto(Long id, String username, String email, String password, boolean enabled, String path, byte[] file,
			Set<Role> roles) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.enabled = enabled;
		this.path = path;
		this.file = file;
		this.roles = roles;
	}

	public static UserDto from(User user) {

        return new UserDto(
        		user.getId(),
        		user.getUsername(),
        		user.getEmail(),
        		user.getPassword(),
        		user.isEnabled(),
        		user.getPath(),
        		user.getFile(),
        		user.getRoles()		
               );

    }
	


	public String getPath() {
		return path;
	}


	public void setPath(String path) {
		this.path = path;
	}


	public byte[] getFile() {
		return file;
	}


	public void setFile(byte[] file) {
		this.file = file;
	}

	
	
    
    

}
