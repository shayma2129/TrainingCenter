package com.TrainingCenter.Dto;


public class LoginRequestDto {
	
	private String username;

	private String password;

	public LoginRequestDto(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LoginRequestDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
   
   

}
