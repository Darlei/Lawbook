package br.com.lawbook.dao.impl;

import java.util.logging.Logger;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import br.com.lawbook.dao.AuthorityDAO;
import br.com.lawbook.model.Authority;
import br.com.lawbook.util.HibernateUtil;

/**
 * @author Edilson Luiz Ales Junior
 * @version 29OCT2011-04
 * 
 */
public class AuthorityDAOImpl implements AuthorityDAO {
	
	private final static Logger LOG = Logger.getLogger("AuthorityDAOImpl");
	
	@Override
	public void create(Authority auth) throws HibernateException, IllegalArgumentException {
		Session session = HibernateUtil.getSession();
		
		if (this.checkIfExist(auth, session)) {
			session.close();
			LOG.info("Hibernate Session closed");
			throw new IllegalArgumentException("Authority name " + auth.getName() + " already exist");
		}
		
		Transaction tx = session.beginTransaction();
		
		try {
			session.save(auth);
			tx.commit();
		} catch (Exception e) {
			LOG.severe(e.getMessage());
			tx.rollback();
			throw new HibernateException(e);
		} finally {
			session.close();
			LOG.info("Hibernate Session closed");
		}
	}

	@Override
	public Authority getByName(String authName) throws HibernateException, IllegalArgumentException {
		Authority auth = new Authority(authName);
		Session session = HibernateUtil.getSession();
		boolean flag = this.checkIfExist(auth, session);
		session.close();
		LOG.info("Hibernate Session closed");
		
		if (flag) return auth;
		
		throw new IllegalArgumentException("Authority " + authName + " doesn't exist");
	}
	
	/* In order to optimize the database connection usage
	 * this method reuse sessions, that's why it isn't closed here */
	private boolean checkIfExist(Authority auth, Session session) throws HibernateException {
		try {
			Criteria crit = session.createCriteria(Authority.class);
			crit.add(Restrictions.eq("name", auth.getName()));
			Authority authAux = (Authority) crit.uniqueResult();

			if (authAux == null) return false;
			
			authAux.copyTo(auth);
			return true;
		} catch (Exception e) {
			session.close();
			LOG.info("Hibernate Session closed");
			LOG.severe(e.getMessage());
			throw new HibernateException(e);
		}
	}
}
