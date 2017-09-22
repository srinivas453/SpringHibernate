package org.mainlogic.spring.hibernate.dao;

import org.mainlogic.spring.hibernate.entity.Person;
import org.springframework.stereotype.Repository;

@Repository
//public class PersonDao extends HibernateDao<Person> {

public class PersonDao extends JpaDao<Person> {

}