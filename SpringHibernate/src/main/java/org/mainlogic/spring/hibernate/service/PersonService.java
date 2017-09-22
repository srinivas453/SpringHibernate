package org.mainlogic.spring.hibernate.service;

import java.util.List;

import org.mainlogic.spring.hibernate.dao.PersonDao;
import org.mainlogic.spring.hibernate.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PersonService {
	
	@Autowired
	private PersonDao dao;

	public void save(Person p) {

		dao.save(p);
	}
	
	public List<Person> list() {
		
		return dao.retrieve();
	}
}
