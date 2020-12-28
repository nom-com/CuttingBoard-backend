package com.revature.cuttingboard.controller;

import java.util.Date;

import org.hibernate.Session;

import com.revature.cuttingboard.dao.SystemUserDAO;
import com.revature.cuttingboard.model.Category;
import com.revature.cuttingboard.model.Recipe;
import com.revature.cuttingboard.model.SystemUser;
import com.revature.cuttingboard.model.UserFavorites;
import com.revature.cuttingboard.utils.HibernateUtility;

public class AppController {
	public static void main(String args[]) {
		SystemUserDAO user = new SystemUserDAO();
		
		try {
			System.out.println(user.getUserByUsername("test1"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
