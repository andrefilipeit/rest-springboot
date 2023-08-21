package br.com.rest.springboot.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.rest.springboot.exceptions.ResourceNotFoundException;
import br.com.rest.springboot.model.Person;
import br.com.rest.springboot.repositories.PersonRepository;

@Service
public class PersonService {
	
	private Logger logger = Logger.getLogger(PersonService.class.getName());
	
	@Autowired
	PersonRepository repository;
	
	public Person findById(Long id) {
		logger.info("Finding one Person!");
		return repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
	}
	
	public List<Person> findAll() {
		logger.info("Finding a list of Person!");
		return repository.findAll();
	}
	
	public Person create(Person person) { //getting from bodyRequest
		logger.info("Person created!");
		return repository.save(person);
	}
	
	public Person update(Person person) { //getting from bodyRequest
		
		var entity = repository.findById(person.getId())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		
		entity.setAddress(person.getAddress());
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setGender(person.getGender());
		entity.setAddress(person.getAddress());
		
		logger.info("Person updated!");
		return repository.save(entity);
	}
	
	public void delete(Long id) { //getting from bodyRequest
		
		
		var person = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		
		logger.info("Deleting person with id " + id);
		repository.delete(person);
	}

}
