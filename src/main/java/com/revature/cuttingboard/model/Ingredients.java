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
 * Model class to represent the ingredients table in the database 
 * @author nom.com
 * @since 1.0
 *
 */

@Entity
@Table(name = "ingredients")
public class Ingredients {

	@Id
	@Column(name="ingredients_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(length = 45, nullable = false, unique = true)
	private String ingredient;
	
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

	public Ingredients() {
		super();
	}

	public Ingredients(int id, String ingredient, SystemUser createdBy, Date creationDate, SystemUser lastUpdatedBy,
			Date last_update_date) {
		super();
		this.id = id;
		this.ingredient = ingredient;
		this.createdBy = createdBy;
		this.creationDate = creationDate;
		this.lastUpdatedBy = lastUpdatedBy;
		this.last_update_date = last_update_date;
	}

	public Ingredients(String ingredient, SystemUser createdBy, Date creationDate, SystemUser lastUpdatedBy,
			Date last_update_date) {
		super();
		this.ingredient = ingredient;
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

	public String getIngredient() {
		return ingredient;
	}

	public void setIngredient(String ingredient) {
		this.ingredient = ingredient;
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
		return "Ingredients [id=" + id + ", ingredient=" + ingredient + ", createdBy=" + createdBy + ", creationDate="
				+ creationDate + ", lastUpdatedBy=" + lastUpdatedBy + ", last_update_date=" + last_update_date + "]";
	}
	
	
}
