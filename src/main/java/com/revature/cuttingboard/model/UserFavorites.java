package com.revature.cuttingboard.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Model class to represent the user_favorites table of the database. 
 * @author nom.com
 * @since 1.0
 *
 */
@Entity
@Table(name="user_favorites")
public class UserFavorites {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="user_favorites_id")
	private int id;
	
	@ManyToOne
	@JoinColumn(name="recipe_id", nullable = false)
	private Recipe recipe;
	
	@ManyToOne
	@JoinColumn(name="system_user_id", nullable = false)
	private SystemUser systemUser;
	
	@Column(name="creation_date", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date creationDate;

	public UserFavorites() {
		super();
	}

	public UserFavorites(int id, Recipe recipe, SystemUser systemUser, Date creationDate) {
		super();
		this.id = id;
		this.recipe = recipe;
		this.systemUser = systemUser;
		this.creationDate = creationDate;
	}

	public UserFavorites(Recipe recipe, SystemUser systemUser, Date creationDate) {
		super();
		this.recipe = recipe;
		this.systemUser = systemUser;
		this.creationDate = creationDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}

	public SystemUser getSystemUser() {
		return systemUser;
	}

	public void setSystemUser(SystemUser systemUser) {
		this.systemUser = systemUser;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	@Override
	public String toString() {
		return "UserFavorites [id=" + id + ", recipe=" + recipe + ", systemUser=" + systemUser + ", creationDate="
				+ creationDate + "]";
	}
	
	
}
