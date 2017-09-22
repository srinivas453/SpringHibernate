package org.mainlogic.spring.hibernate.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class JpaDao<T> {

	@PersistenceContext
	protected EntityManager em;

	private Class<T> type;

	@SuppressWarnings("unchecked")
	public JpaDao() {

		this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	public void save(T t) {
		em.persist(t);
	}

	public void delete(T t) {
		em.remove(t);
	}

	public T retrieve(Serializable id) {
		return em.find(type, id);
	}

	public List<T> retrieve() {
		List<T> list = em.createQuery("from " + type.getSimpleName(), type).getResultList();
		return list;
	}
}
