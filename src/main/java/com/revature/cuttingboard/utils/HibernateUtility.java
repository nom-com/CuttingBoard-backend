package com.revature.cuttingboard.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtility {
	
	private static SessionFactory sessionFactory;
	
	public static Session getSession() {
		System.out.println(System.getenv("DB_PASSWORD"));
		if (sessionFactory == null) {
			sessionFactory = new Configuration().setProperty("hibernate.connection.url", System.getenv("DB_URL"))
					.setProperty("hibernate.connection.username", System.getenv("DB_USERNAME"))
					.setProperty("hibernate.connection.password", System.getenv("DB_PASSWORD"))
					.configure("hibernate.cfg.xml")
					.buildSessionFactory();
			
		}
		
		return sessionFactory.openSession();
	}
}
