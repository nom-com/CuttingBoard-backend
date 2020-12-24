package com.revature.cuttingboard.dto;

import com.revature.cuttingboard.model.SystemUser;

/**
 * DTO class to hold data returned in responses to http requests dealing with users. 
 * @author nom.com
 * @since 1.0
 *
 */
public class SystemUserDTO {
	
	private int id;
	private String username;
	private boolean admin;
	
	public SystemUserDTO() {
		super();
	}

	public SystemUserDTO(int id, String username, boolean admin) {
		super();
		this.id = id;
		this.username = username;
		this.admin = admin;
	}
	
	public SystemUserDTO(SystemUser user) {
		this.id = user.getId();
		this.username = user.getUsername();
		this.admin = user.isAdmin();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	@Override
	public String toString() {
		return "SystemUserDTO [id=" + id + ", username=" + username + ", admin=" + admin + "]";
	}	
}