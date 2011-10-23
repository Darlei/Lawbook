package br.com.lawbook.DAO.hibernate;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.logging.Logger;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import br.com.lawbook.DAO.GenericDAO;

/**
 * @author Edilson Luiz Ales Junior
 * @version 23OCT2011-03
 * 
 */
@SuppressWarnings("unchecked")
public class HibernateGenericDAO<T> implements GenericDAO<T> {

    private Class<T> persistentClass;
    private Session session;
    private static Logger LOG = Logger.getLogger("HibernateGenericDAO");

	public HibernateGenericDAO(Session session) {
        this.session = session;
        this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Override
    public Session getSession() {
        return this.session;
    }

    @Override
    public T getById(Serializable id) {
        return (T) this.session.get(this.persistentClass, id);
    }

    @Override
    public List<T> getAll() {
        return this.session.createCriteria(this.persistentClass).list();
    }

    @Override
    public T save(T entity) {
		try {
			this.session.save(entity);
			return entity;
		} catch (Exception e) {
			LOG.severe(e.getMessage());
			throw new HibernateException("Problem saving new register: " + e.getMessage());
		}
    }
    
    @Override
    public T update(T entity) {
    	try {
			this.session.update(entity);
			return entity;
		} catch (Exception e) {
			LOG.severe(e.getMessage());
			throw new HibernateException("Problem updating new register: " + e.getMessage());
		}
    }

    @Override
    public void delete(T entity) {
        this.session.delete(entity);
    }

}