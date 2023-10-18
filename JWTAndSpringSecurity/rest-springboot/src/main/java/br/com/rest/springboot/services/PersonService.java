package br.com.rest.springboot.services;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

import br.com.rest.springboot.controllers.PersonController;
import br.com.rest.springboot.data.vo.v1.PersonVO;
import br.com.rest.springboot.exceptions.RequiredObjectIsNullException;
import br.com.rest.springboot.exceptions.ResourceNotFoundException;
import br.com.rest.springboot.mapper.DozerMapper;
import br.com.rest.springboot.model.Person;
import br.com.rest.springboot.repositories.PersonRepository;
import jakarta.transaction.Transactional;

@Service
public class PersonService {
	
	private Logger logger = Logger.getLogger(PersonService.class.getName());
	
	@Autowired
	PersonRepository repository;
	
	@Autowired
	PagedResourcesAssembler<PersonVO> assembler;

	public PagedModel<EntityModel<PersonVO>> findAll(Pageable pageable) {

		logger.info("Finding all people!");
		
		var personPage = repository.findAll(pageable);
		
		var personVOSPage = personPage.map( p -> DozerMapper.parseObject(p, PersonVO.class));
		
		var personVOPageWithHATEOAS = personVOSPage.map(p -> {
			try {
				return p.add(linkTo(methodOn(PersonController.class)
						.findById(p.getPk())).withSelfRel());
			} catch (Exception e) {
				e.printStackTrace();
			}
			return p;
		});

		Link link = linkTo(methodOn(PersonController.class)
				.findAll(pageable.getPageNumber(), pageable.getPageSize(), "asc")).withSelfRel();
		
		return assembler.toModel(personVOPageWithHATEOAS, link);
	}
	
	public PagedModel<EntityModel<PersonVO>> findPersonByName(String firstName, Pageable pageable) {
		
		logger.info("Finding all people!");
		
		var personPage = repository.findPersonByName(firstName, pageable);
		
		var personVOSPage = personPage.map( p -> DozerMapper.parseObject(p, PersonVO.class));
		
		var personVOPageWithHATEOAS = personVOSPage.map(p -> {
			try {
				return p.add(linkTo(methodOn(PersonController.class)
						.findById(p.getPk())).withSelfRel());
			} catch (Exception e) {
				e.printStackTrace();
			}
			return p;
		});
		
		Link link = linkTo(methodOn(PersonController.class)
				.findAll(pageable.getPageNumber(), pageable.getPageSize(), "asc")).withSelfRel();
		
		return assembler.toModel(personVOPageWithHATEOAS, link);
	}

	public PersonVO findById(Long id) throws Exception {
		
		logger.info("Finding one person!");
		
		var entity = repository.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		var vo = DozerMapper.parseObject(entity, PersonVO.class);
		vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
		return vo;
	}
	
	public PersonVO create(PersonVO person) throws Exception {

		if (person == null) throw new RequiredObjectIsNullException();
		
		logger.info("Creating one person!");
		var entity = DozerMapper.parseObject(person, Person.class);
		var vo =  DozerMapper.parseObject(repository.save(entity), PersonVO.class);
		vo.add(linkTo(methodOn(PersonController.class).findById(vo.getPk())).withSelfRel());
		return vo;
	}
	
	public PersonVO update(PersonVO person) throws Exception {

		if (person == null) throw new RequiredObjectIsNullException();
		
		logger.info("Updating one person!");
		
		var entity = repository.findById(person.getPk())
			.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());
		
		var vo =  DozerMapper.parseObject(repository.save(entity), PersonVO.class);
		vo.add(linkTo(methodOn(PersonController.class).findById(vo.getPk())).withSelfRel());
		return vo;
	}
	
	@Transactional
	public PersonVO disablePerson(Long id) throws Exception {
		
		logger.info("Disabling one person!");
		
		repository.disablePerson(id);
		
		var entity = repository.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		var vo = DozerMapper.parseObject(entity, PersonVO.class);
		vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
		return vo;
	}
	
	public void delete(Long id) {
		
		logger.info("Deleting one person!");
		
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		repository.delete(entity);
	}
}
