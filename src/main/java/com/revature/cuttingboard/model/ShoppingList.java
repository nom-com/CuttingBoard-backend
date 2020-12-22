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
 * Model class to represent the shopping_list table of the database
 * @author nom.com
 * @since 1.0
 *
 */

@Entity
@Table(name="shopping_list")
public class ShoppingList {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="shopping_list_id")
	private int id;
	
	@ManyToOne
	@JoinColumn(name="ingredients_id", nullable = false)
	private Ingredients ingredient;
	
	@ManyToOne
	@JoinColumn(name="system_user_id", nullable = false)
	private SystemUser systemUser;
	
	@Column(name="creation_date", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date creationDate;

	public ShoppingList() {
		super();
	}

	public ShoppingList(int id, Ingredients ingredient, SystemUser systemUser, Date creationDate) {
		super();
		this.id = id;
		this.ingredient = ingredient;
		this.systemUser = systemUser;
		this.creationDate = creationDate;
	}

	public ShoppingList(Ingredients ingredient, SystemUser systemUser, Date creationDate) {
		super();
		this.ingredient = ingredient;
		this.systemUser = systemUser;
		this.creationDate = creationDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Ingredients getIngredient() {
		return ingredient;
	}

	public void setIngredient(Ingredients ingredient) {
		this.ingredient = ingredient;
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
	
	
}
