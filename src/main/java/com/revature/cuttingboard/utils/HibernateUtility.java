package com.revature.cuttingboard.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

public class HibernateUtility {
	
	private static SessionFactory sessionFactory;
	
	public static Session getSession() {
		if (sessionFactory == null) {
			System.out.println(System.getenv("DB_USERNAMEE"));
			System.out.println(System.getenv("DB_PASSWORD"));
			sessionFactory = new Configuration().setProperty("hibernate.connection.username", System.getenv("DB_USERNAME"))
					.setProperty("hibernate.connection.password", System.getenv("DB_PASSWORD"))
					.configure("hibernate.cfg.xml")
					.buildSessionFactory();
			
		}
		
		return sessionFactory.openSession();
	}
}
