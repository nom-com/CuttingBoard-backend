package com.revature.cuttingboard.controller;

import org.hibernate.Session;

import com.revature.cuttingboard.utils.HibernateUtility;

public class AppController {
	public static void main(String args[]) {
		Session session = HibernateUtility.getSession();
		
		session.close();
	}
}
