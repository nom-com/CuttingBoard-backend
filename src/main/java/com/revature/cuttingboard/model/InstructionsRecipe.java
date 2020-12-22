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
 * Model class to represent the instructions_recipe table of the database
 * @author nom.com
 * @since 1.0
 *
 */
@Entity
@Table(name="instructions_recipe")
public class InstructionsRecipe {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="instructions_recipe_id")
	private int id;
	
	@ManyToOne
	@JoinColumn(name="instructions_id", nullable = false)
	private Instructions instruction;
	
	@ManyToOne
	@JoinColumn(name="recipe_id")
	private Recipe recipe;
	
	@Column(nullable = false)
	private int order;
	
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

	public InstructionsRecipe() {
		super();
	}

	public InstructionsRecipe(int id, Instructions instruction, Recipe recipe, int order, SystemUser createdBy,
			Date creationDate, SystemUser lastUpdatedBy, Date last_update_date) {
		super();
		this.id = id;
		this.instruction = instruction;
		this.recipe = recipe;
		this.order = order;
		this.createdBy = createdBy;
		this.creationDate = creationDate;
		this.lastUpdatedBy = lastUpdatedBy;
		this.last_update_date = last_update_date;
	}

	public InstructionsRecipe(Instructions instruction, Recipe recipe, int order, SystemUser createdBy,
			Date creationDate, SystemUser lastUpdatedBy, Date last_update_date) {
		super();
		this.instruction = instruction;
		this.recipe = recipe;
		this.order = order;
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

	public Instructions getInstruction() {
		return instruction;
	}

	public void setInstruction(Instructions instruction) {
		this.instruction = instruction;
	}

	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
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
	
	
	
}
