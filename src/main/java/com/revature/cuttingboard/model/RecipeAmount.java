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
 * Model class to represent the Recipe_amount table of the database
 * @author nom.com
 * @since 1.0
 *
 */
@Entity
@Table(name="recipe_amount")
public class RecipeAmount {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="recipe_amount_id")
	private int id;
	
	@ManyToOne
	@JoinColumn(name="recipe_id", nullable = false)
	private Recipe recipe;
	
	@ManyToOne
	@JoinColumn(name="amount_id", nullable = false)
	private Amount amount;
	
	@ManyToOne
	@JoinColumn(name="created_by", nullable = false)
	private SystemUser createdBy;
	
	@Column(name="creation_date", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date creationDate;
	
	@ManyToOne
	@JoinColumn(name="last_updated_by", nullable = false)
	private SystemUser lastUpdatedBy;
	
	@Column(name = "last_update_date", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date last_update_date;

	public RecipeAmount() {
		super();
	}

	public RecipeAmount(int id, Recipe recipe, Amount amount, SystemUser createdBy, Date creationDate,
			SystemUser lastUpdatedBy, Date last_update_date) {
		super();
		this.id = id;
		this.recipe = recipe;
		this.amount = amount;
		this.createdBy = createdBy;
		this.creationDate = creationDate;
		this.lastUpdatedBy = lastUpdatedBy;
		this.last_update_date = last_update_date;
	}

	public RecipeAmount(Recipe recipe, Amount amount, SystemUser createdBy, Date creationDate, SystemUser lastUpdatedBy,
			Date last_update_date) {
		super();
		this.recipe = recipe;
		this.amount = amount;
		this.createdBy = createdBy;
		this.creationDate = creationDate;
		this.lastUpdatedBy = lastUpdatedBy;
		this.last_update_date = last_update_date;
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

	public Amount getAmount() {
		return amount;
	}

	public void setAmount(Amount amount) {
		this.amount = amount;
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

	public Date getLast_update_date() {
		return last_update_date;
	}

	public void setLast_update_date(Date last_update_date) {
		this.last_update_date = last_update_date;
	}

	@Override
	public String toString() {
		return "RecipeAmount [id=" + id + ", recipe=" + recipe + ", amount=" + amount + ", createdBy=" + createdBy
				+ ", creationDate=" + creationDate + ", lastUpdatedBy=" + lastUpdatedBy + ", last_update_date="
				+ last_update_date + "]";
	}
	
	
	
}
