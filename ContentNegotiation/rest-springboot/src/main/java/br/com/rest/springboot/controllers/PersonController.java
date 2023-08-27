package br.com.rest.springboot.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.rest.springboot.data.vo.v1.PersonVO;
import br.com.rest.springboot.services.PersonService;
import br.com.rest.springboot.util.MediaType;

@RestController
@RequestMapping("/api/person/v1")
public class PersonController {

	@Autowired //Injection of PerosnService instance
	private PersonService service;
	
	@GetMapping(value = "/{id}", 
			produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML}) //{required-param}
	//PathVariable is used to recover pathParams explicit in URL - id
	public PersonVO findById(@PathVariable(value = "id") Long id) throws Exception {
		
		return service.findById(id);
	}
	
	@GetMapping(produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
	public List<PersonVO> findAll() throws Exception {
		return service.findAll();
	}
	
	@PostMapping(consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
			produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
	public PersonVO create(@RequestBody PersonVO person) throws Exception {
		return service.create(person);
	}
	
	@PutMapping(consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
			produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
	public PersonVO update(@RequestBody PersonVO person) throws Exception {
		return service.update(person);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> update(@PathVariable(value="id") Long id) throws Exception {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}
