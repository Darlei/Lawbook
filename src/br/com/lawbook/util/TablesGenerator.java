package br.com.lawbook.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Logger;

import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import br.com.lawbook.model.*;

/**
 * @author Edilson Luiz Ales Junior
 * @version 28OCT2011-09
 * 
 */
public class TablesGenerator {

	private final static Logger LOG = Logger.getLogger("TablesGenerator");
	
	public static void main(String[] args) {
		
		LOG.info("Are you sure you want to do that? All database will be purged! ( Y | N )");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String answer = null;
		try {
			answer = reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if (answer == null || !answer.equalsIgnoreCase("y")) return;
		
		try {
			Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
			cfg.addAnnotatedClass(Authority.class);
			cfg.addAnnotatedClass(Comment.class);
			cfg.addAnnotatedClass(Location.class);
			cfg.addAnnotatedClass(Post.class);
			cfg.addAnnotatedClass(Profile.class);
			cfg.addAnnotatedClass(User.class);
			SchemaExport se = new SchemaExport(cfg);
			se.create(true, true);
		} catch (Throwable ex) {
			LOG.severe("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}
}
