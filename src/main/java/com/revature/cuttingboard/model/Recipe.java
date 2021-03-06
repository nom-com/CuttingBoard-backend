package com.revature.cuttingboard.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

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
	
	@Column(name = "image_location", length = 45)
	@NotNull
	private String imageLocation;
	
	@Column
	@NotNull
	private String title;
	
	@Column(length = 255)
	private String description;
	
	@Column(name="public")
	@NotNull
	private boolean publicRecipe;
	
	@OneToMany(mappedBy = "recipe")
	private List<RecipeAmount> ingredients;
	
	@OneToMany(mappedBy = "recipe")
	private List<InstructionsRecipe> instructions;
	
	@ManyToOne
	@JoinColumn(name="created_by")
	@NotNull
	private SystemUser createdBy;
	
	
	@Column(name="creation_date")
	@Temporal(TemporalType.DATE)
	@NotNull
	private Date creationDate;
	
	@ManyToOne
	@JoinColumn(name="last_updated_by")
	@NotNull
	private SystemUser lastUpdatedBy;
	
	@Column(name = "last_update_date")
	@Temporal(TemporalType.DATE)
	@NotNull
	private Date lastUpdateDate;
	
	@ManyToOne
	@JoinColumn(name="category_id")
	@NotNull
	private Category category;

	public Recipe() {
		super();
	}

	

	public Recipe(String imageLocation, String title, String description, boolean publicRecipe, SystemUser createdBy,
			Date creationDate, SystemUser lastUpdatedBy, Date lastUpdateDate, Category category) {
		super();
		this.imageLocation = imageLocation;
		this.title = title;
		this.description = description;
		this.publicRecipe = publicRecipe;
		this.createdBy = createdBy;
		this.creationDate = creationDate;
		this.lastUpdatedBy = lastUpdatedBy;
		this.lastUpdateDate = lastUpdateDate;
		this.category = category;
	}



	public Recipe(int id, String imageLocation, String title, String description, boolean publicRecipe,
			SystemUser createdBy, Date creationDate, SystemUser lastUpdatedBy, Date lastUpdateDate,
			Category category) {
		super();
		this.id = id;
		this.imageLocation = imageLocation;
		this.title = title;
		this.description = description;
		this.publicRecipe = publicRecipe;
		this.createdBy = createdBy;
		this.creationDate = creationDate;
		this.lastUpdatedBy = lastUpdatedBy;
		this.lastUpdateDate = lastUpdateDate;
		this.category = category;
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

	public List<RecipeAmount> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<RecipeAmount> ingredients) {
		this.ingredients = ingredients;
	}

	public List<InstructionsRecipe> getInstructions() {
		return instructions;
	}

	public void setInstructions(List<InstructionsRecipe> instructions) {
		this.instructions = instructions;
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
		return category;
	}

	public void setCategoryId(Category category) {
		this.category = category;
	}

	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}



	@Override
	public String toString() {
		return "Recipe [id=" + id + ", imageLocation=" + imageLocation + ", title=" + title + ", description="
				+ description + ", publicRecipe=" + publicRecipe + ", createdBy=" + createdBy + ", creationDate="
				+ creationDate + ", lastUpdatedBy=" + lastUpdatedBy + ", lastUpdateDate=" + lastUpdateDate
				+ ", category=" + category + "]";
	}
	
	
	
}
