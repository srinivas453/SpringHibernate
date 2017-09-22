package org.mainlogic.spring.hibernate;

import java.util.List;

import org.mainlogic.spring.hibernate.config.JpaConfig;
import org.mainlogic.spring.hibernate.entity.Person;
import org.mainlogic.spring.hibernate.service.PersonService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class AppAnnotationMain {

	public static void main(String[] args) {
		
		//AbstractApplicationContext context = new AnnotationConfigApplicationContext(HibernateConfig.class);
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(JpaConfig.class);
		
		Person p = new Person();
		p.setName("Spring");
		p.setCountry("US");
		
		PersonService service = (PersonService)context.getBean("personService");
		service.save(p);
		
		List<Person> persons = service.list();
		
		System.out.println(persons != null ? persons.size() : null);

		context.close();
	}
}
