package br.com.lawbook.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @author Edilson Luiz Ales Junior
 * @version 04SEP2011-01 
 * 
 */

public class HibernateUtil {
	
	private static final SessionFactory sessionFactory;
	private static Session session;

    static {
        try {
            sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() {
    	if (session == null || !session.isOpen()) {
    		session = sessionFactory.openSession();
    	}
        return session;
    }
	
}
