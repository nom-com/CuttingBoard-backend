package com.revature.cuttingboard.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;


/**
 * Model class for the system_user table of the database
 * @author nom.com
 * @since 1.0
 *
 */
@Entity
@Table(name="system_user")
public class SystemUser {

	@Id
	@Column(name="system_user_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(length = 45, unique = true)
	@NotNull
	private String username;
	
	@Column(length = 75)
	@NotNull
	private String password;
	
	@Column(length = 45, unique = true)
	@NotNull
	private String email;
	
	@Column(name="first_name", length = 45)
	@NotNull
	private String firstName;
	
	@Column(name="last_name", length = 45)
	@NotNull
	private String lastName;
	
	@Column(name="creation_date")
	@Temporal(TemporalType.DATE)
	@NotNull
	private Date creationDate;
	
	@Column(name="last_update_date")
	@Temporal(TemporalType.DATE)
	@NotNull
	private Date lastUpdateDate;
	
	@Column(name="admin")
	@NotNull
	private boolean admin;
	
	@Column(length=75)
	@NotNull
	private String salt;

	public SystemUser() {
		super();
	}

	public SystemUser(int id, String username, String password, String email, String firstName, String lastName,
			Date creationDate, Date lastUpdateDate, boolean admin, String salt) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.creationDate = creationDate;
		this.lastUpdateDate = lastUpdateDate;
		this.admin = admin;
		this.salt = salt;
	}

	public SystemUser(String username, String password, String email, String firstName, String lastName,
			Date creationDate, Date lastUpdateDate, boolean admin, String salt) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.creationDate = creationDate;
		this.lastUpdateDate = lastUpdateDate;
		this.admin = admin;
		this.salt = salt;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getLast_update_date() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	@Override
	public String toString() {
		return "SystemUser [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", firstName=" + firstName + ", lastName=" + lastName + ", creationDate=" + creationDate
				+ ", lastUpdateDate=" + lastUpdateDate + ", admin=" + admin + ", salt=" + salt + "]";
	}
	
	
	
}
