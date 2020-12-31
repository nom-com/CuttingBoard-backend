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
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

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
	@Cascade(CascadeType.ALL)
	@JoinColumn(name="instructions_id")
	@NotNull
	private Instructions instruction;
	
	@ManyToOne
	@JoinColumn(name="recipe_id")
	@NotNull
	private Recipe recipe;
	
	@Column(name="step_order")
	@NotNull
	private int stepOrder;
	
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

	public InstructionsRecipe() {
		super();
	}

	public InstructionsRecipe(int id, Instructions instruction, Recipe recipe, int stepOrder, SystemUser createdBy,
			Date creationDate, SystemUser lastUpdatedBy, Date lastUpdateDate) {
		super();
		this.id = id;
		this.instruction = instruction;
		this.recipe = recipe;
		this.stepOrder = stepOrder;
		this.createdBy = createdBy;
		this.creationDate = creationDate;
		this.lastUpdatedBy = lastUpdatedBy;
		this.lastUpdateDate = lastUpdateDate;
	}

	public InstructionsRecipe(Instructions instruction, Recipe recipe, int stepOrder, SystemUser createdBy,
			Date creationDate, SystemUser lastUpdatedBy, Date lastUpdateDate) {
		super();
		this.instruction = instruction;
		this.recipe = recipe;
		this.stepOrder = stepOrder;
		this.createdBy = createdBy;
		this.creationDate = creationDate;
		this.lastUpdatedBy = lastUpdatedBy;
		this.lastUpdateDate = lastUpdateDate;
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

	public int getStepOrder() {
		return stepOrder;
	}

	public void setStepOrder(int stepOrder) {
		this.stepOrder = stepOrder;
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

	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	@Override
	public String toString() {
		return "InstructionsRecipe [id=" + id + ", instruction=" + instruction + ", recipe=" + recipe + ", stepOrder="
				+ stepOrder + ", createdBy=" + createdBy + ", creationDate=" + creationDate + ", lastUpdatedBy="
				+ lastUpdatedBy + ", lastUpdateDate=" + lastUpdateDate + "]";
	}
	
	
	
}
