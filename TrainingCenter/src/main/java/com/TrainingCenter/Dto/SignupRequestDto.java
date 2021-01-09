package com.TrainingCenter.Dto;

import java.util.Set;

public class SignupRequestDto {

	
	 private String username;
	    private String email;

	    private boolean enabled;
	    
	    private Set<String> role;
	    
	
	    private String password;

			private String path;
			/**
			 * file
			 */
			 
			 private byte[] file;
	    

	


		public SignupRequestDto(String username, String email, boolean enabled, Set<String> role, String password,
					String path, byte[] file) {
				super();
				this.username = username;
				this.email = email;
				this.enabled = enabled;
				this.role = role;
				this.password = password;
				this.path = path;
				this.file = file;
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


		public SignupRequestDto() {
			super();
			// TODO Auto-generated constructor stub
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


		public boolean isEnabled() {
			return enabled;
		}


		public void setEnabled(boolean enabled) {
			this.enabled = enabled;
		}


		public Set<String> getRole() {
			return role;
		}


		public void setRole(Set<String> role) {
			this.role = role;
		}


		public String getPassword() {
			return password;
		}


		public void setPassword(String password) {
			this.password = password;
		}
	    
	    
	  

}
