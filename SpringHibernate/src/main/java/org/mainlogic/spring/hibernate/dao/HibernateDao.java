package org.mainlogic.spring.hibernate.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class HibernateDao<T> {

	@Autowired
	protected SessionFactory sessionFactory;

	private Class<T> type;

	@SuppressWarnings("unchecked")
	public HibernateDao() {

		this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	public void save(T t) {
		Session session = this.sessionFactory.openSession();
		session.persist(t);
	}

	public void delete(T t) {
		Session session = this.sessionFactory.openSession();
		session.delete(t);
	}

	public T retrieve(Serializable id) {
		Session session = this.sessionFactory.openSession();
		return session.get(type, id);
	}

	public List<T> retrieve() {
		Session session = this.sessionFactory.openSession();
		List<T> list = session.createQuery("from " + type.getSimpleName(), type).list();
		return list;
	}
}
