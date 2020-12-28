package com.revature.cuttingboard.dto;

public class CredentialsDTO {

		private String username;
		private String password;
		
		public CredentialsDTO() {
			super();
		}
		
		public CredentialsDTO(String username, String password) {
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

		@Override
		public String toString() {
			return "CredentialsDTO [username=" + username + ", password=" + password + "]";
		}
}
