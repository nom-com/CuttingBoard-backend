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

/**
 * Model class to represent the amount database table
 * @author nom.com
 * @since 1.0
 *
 */
@Entity
@Table(name="amount")
public class Amount {
	
	@Id
	@Column(name="amount_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column
	@NotNull
	private String amount;
	
	@ManyToOne
	@JoinColumn(name="ingredient_id")
	@NotNull
	private Ingredients ingredient;
	
	@ManyToOne
	@JoinColumn(name="created_by")
	@NotNull
	private SystemUser createdBy;
	
	@Column(name="creation_date")
	@NotNull
	@Temporal(TemporalType.DATE)
	private Date creationDate;
	
	@ManyToOne
	@JoinColumn(name="last_updated_by")
	@NotNull
	private SystemUser lastUpdatedBy;
	
	@Column(name = "last_update_date")
	@Temporal(TemporalType.DATE)
	@NotNull
	private Date lastUpdateDate;

	public Amount() {
		super();
	}

	public Amount(int id, String amount, Ingredients ingredient, SystemUser createdBy, Date creationDate,
			SystemUser lastUpdatedBy, Date lastUpdateDate) {
		super();
		this.id = id;
		this.amount = amount;
		this.ingredient = ingredient;
		this.createdBy = createdBy;
		this.creationDate = creationDate;
		this.lastUpdatedBy = lastUpdatedBy;
		this.lastUpdateDate = lastUpdateDate;
	}

	public Amount(String amount, Ingredients ingredient, SystemUser createdBy, Date creationDate,
			SystemUser lastUpdatedBy, Date lastUpdateDate) {
		super();
		this.amount = amount;
		this.ingredient = ingredient;
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

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public Ingredients getIngredient() {
		return ingredient;
	}

	public void setIngredient(Ingredients ingredient) {
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

	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	@Override
	public String toString() {
		return "Amount [id=" + id + ", amount=" + amount + ", ingredient=" + ingredient + ", createdBy=" + createdBy
				+ ", creationDate=" + creationDate + ", lastUpdatedBy=" + lastUpdatedBy + ", lastUpdateDate="
				+ lastUpdateDate + "]";
	}
	
	
}
