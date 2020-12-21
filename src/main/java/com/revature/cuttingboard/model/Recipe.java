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
 * Model class to represent the recipe table in the database
 * @author nom.com
 * @since 1.0
 */

@Entity
@Table(name="recipe")
public class Recipe {

	@Id
	@Column(name="recipe_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "image_location", length = 45, nullable = false)
	private String imageLocation;
	
	@Column(nullable = false)
	private String title;
	
	@Column(length = 45)
	private String description;
	
	@Column(name="public", nullable = false)
	private boolean publicRecipe;
	
	@ManyToOne
	@JoinColumn(name="created_by", nullable = false)
	private SystemUser createdBy;
	
	
	@Column(name="creation_date", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date creationDate;
	
	@ManyToOne
	@JoinColumn(name="last_updated_by", nullable = false)
	private SystemUser lastUpdatedBy;
	
	@Column(name = "last_update_date")
	@Temporal(TemporalType.DATE)
	private Date last_update_date;
	
	@ManyToOne
	@JoinColumn(name="category_id", nullable = false)
	private Category categoryId;

	public Recipe() {
		super();
	}

	

	public Recipe(String imageLocation, String title, String description, boolean publicRecipe, SystemUser createdBy,
			Date creationDate, SystemUser lastUpdatedBy, Date last_update_date, Category categoryId) {
		super();
		this.imageLocation = imageLocation;
		this.title = title;
		this.description = description;
		this.publicRecipe = publicRecipe;
		this.createdBy = createdBy;
		this.creationDate = creationDate;
		this.lastUpdatedBy = lastUpdatedBy;
		this.last_update_date = last_update_date;
		this.categoryId = categoryId;
	}



	public Recipe(int id, String imageLocation, String title, String description, boolean publicRecipe,
			SystemUser createdBy, Date creationDate, SystemUser lastUpdatedBy, Date last_update_date,
			Category categoryId) {
		super();
		this.id = id;
		this.imageLocation = imageLocation;
		this.title = title;
		this.description = description;
		this.publicRecipe = publicRecipe;
		this.createdBy = createdBy;
		this.creationDate = creationDate;
		this.lastUpdatedBy = lastUpdatedBy;
		this.last_update_date = last_update_date;
		this.categoryId = categoryId;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImageLocation() {
		return imageLocation;
	}

	public void setImageLocation(String imageLocation) {
		this.imageLocation = imageLocation;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isPublicRecipe() {
		return publicRecipe;
	}

	public void setPublicRecipe(boolean publicRecipe) {
		this.publicRecipe = publicRecipe;
	}

	public SystemUser getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(SystemUser createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public SystemUser getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(SystemUser lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public Category getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Category categoryId) {
		this.categoryId = categoryId;
	}

	public Date getLast_update_date() {
		return last_update_date;
	}

	public void setLast_update_date(Date last_update_date) {
		this.last_update_date = last_update_date;
	}
	
	
	
}
