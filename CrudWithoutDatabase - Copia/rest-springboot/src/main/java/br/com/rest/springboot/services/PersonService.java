package br.com.rest.springboot.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import br.com.rest.springboot.model.Person;

@Service
public class PersonService {
	
	private final AtomicLong counter = new AtomicLong();
	private Logger logger = Logger.getLogger(PersonService.class.getName());
	
	public Person findById(String id) {
		Person person = new Person();
		person.setId(counter.incrementAndGet());
		person.setFirstName("ANDRÉ");
		person.setLastName("FILIPE");
		person.setGender("M");
		person.setAddress("RUA ROSENDO GOMES DA ROCHA");
		
		logger.info("Finding one Person!");
		return person;
	}
	
	public List<Person> findAll() {
		List<Person> persons = new ArrayList<Person>();
		
		
		for (int i = 0; i < 8; i++) {
			Person person = mockPerson(i);
			persons.add(person);
		}
		
		logger.info("Finding a list of Person!");
		return persons;
	}
	
	public Person create() {
		Person person = new Person();
		person.setId(counter.incrementAndGet());
		person.setFirstName("ANDRÉ");
		person.setLastName("FILIPE");
		person.setGender("M");
		person.setAddress("RUA ROSENDO GOMES DA ROCHA");
		
		logger.info("Person created!");
		return person;
	}
	
	public Person update(Person person) { //getting from bodyRequest
		logger.info("Person updated!");
		return person;
	}
	
	public void delete(String id) { //getting from bodyRequest
		logger.info("Deleting person with id " + id);
	}
	

	private Person mockPerson(int i) {
		Person p = new Person();
		p.setId(counter.incrementAndGet());
		p.setAddress("RUA "+i);
		p.setFirstName("NAME "+i);
		p.setGender("M");
		p.setLastName("LAST "+i);
		return p;
	}
	

}
