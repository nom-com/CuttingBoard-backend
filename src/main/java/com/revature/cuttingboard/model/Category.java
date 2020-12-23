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
 * Model class to represent the category database table
 * @author nom.com
 * @since 1.0
 *
 */
@Entity
@Table(name="category")
public class Category {
	
	@Id
	@Column(name="category_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(length = 45)
	@NotNull
	private String category;
	
	@Column(name = "creation_date")
	@Temporal(TemporalType.DATE)
	@NotNull
	private Date creationDate;
	
	@ManyToOne
	@JoinColumn(name="created_by")
	@NotNull
	private SystemUser createdBy;

	public Category() {
		super();
	}

	public Category(int id, String category, Date creationDate, SystemUser createdBy) {
		super();
		this.id = id;
		this.category = category;
		this.creationDate = creationDate;
		this.createdBy = createdBy;
	}

	public Category(String category, Date creationDate, SystemUser createdBy) {
		super();
		this.category = category;
		this.creationDate = creationDate;
		this.createdBy = createdBy;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public SystemUser getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(SystemUser createdBy) {
		this.createdBy = createdBy;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", category=" + category + ", creationDate=" + creationDate + ", createdBy="
				+ createdBy + "]";
	}
	
	
}
