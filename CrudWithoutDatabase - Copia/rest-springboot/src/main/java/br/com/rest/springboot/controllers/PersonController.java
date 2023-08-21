package br.com.rest.springboot.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.rest.springboot.model.Person;
import br.com.rest.springboot.services.PersonService;

@RestController
@RequestMapping("/person")
public class PersonController {

	@Autowired //Injection of PerosnService instance
	private PersonService service;
	
	@RequestMapping(value = "/{id}", 
			method=RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE) //{required-param}
	
	//PathVariable is used to recover pathParams explicit in URL - id
	public Person findById(@PathVariable(value = "id") String id) throws Exception {
		
		return service.findById(id);
	}
	
	@RequestMapping(method=RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Person> findAll() throws Exception {
		return service.findAll();
	}
	
	@RequestMapping(method=RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Person create(@RequestBody Person person) throws Exception {
		return service.create();
	}
	
	@RequestMapping(method=RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Person update(@RequestBody Person person) throws Exception {
		return service.update(person);
	}
	
	@RequestMapping(value = "/{id}",
			method=RequestMethod.DELETE)
	public void update(@PathVariable(value="id") String id) throws Exception {
		service.delete(id);
	}

}
