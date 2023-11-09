package br.com.rest.springboot.services;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.rest.springboot.controllers.PersonController;
import br.com.rest.springboot.data.vo.v1.PersonVO;
import br.com.rest.springboot.data.vo.v2.PersonVOV2;
import br.com.rest.springboot.exceptions.ResourceNotFoundException;
import br.com.rest.springboot.mapper.DozerMapper;
import br.com.rest.springboot.mapper.PersonMapper;
import br.com.rest.springboot.model.Person;
import br.com.rest.springboot.repositories.PersonRepository;
import jakarta.transaction.Transactional;

@Service
public class PersonServices {
	
	private Logger logger = Logger.getLogger(PersonServices.class.getName());
	
	@Autowired
	PersonRepository repository;
	
	@Autowired
	PersonMapper mapper;

	public List<PersonVO> findAll() {

		logger.info("Finding all people!");

		var persons = DozerMapper.parseListObjects(repository.findAll(), PersonVO.class);
		persons.stream().forEach( p -> p.add(linkTo(methodOn(PersonController.class).findById(p.getId())).withSelfRel()));
		return persons;
	}

	public PersonVO findById(Long id) {
		
		logger.info("Finding one person!");
		
		var entity = repository.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		var vo = DozerMapper.parseObject(entity, PersonVO.class);
		vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
		return vo;
	}
	
	public PersonVO create(PersonVO person) {

		logger.info("Creating one person!");
		var entity = DozerMapper.parseObject(person, Person.class);
		var vo =  DozerMapper.parseObject(repository.save(entity), PersonVO.class);
		vo.add(linkTo(methodOn(PersonController.class).findById(vo.getId())).withSelfRel());
		return vo;
	}
	
	public PersonVO update(PersonVO person) {
		
		logger.info("Updating one person!");
		
		var entity = repository.findById(person.getId())
			.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());
		
		var vo =  DozerMapper.parseObject(repository.save(entity), PersonVO.class);
		vo.add(linkTo(methodOn(PersonController.class).findById(vo.getId())).withSelfRel());
		return vo;
	}
	
	@Transactional
	public PersonVO disablePerson(Long id) {
		
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
	
	//versioning endpoint /v2
	public PersonVOV2 createV2(PersonVOV2 personVOV2) {

		logger.info("Creating one person with V2!");
		var entity = mapper.convertVoV2ToEntity(personVOV2);
		var vov2 =  mapper.convertEntityToVoV2(repository.save(entity));
		return vov2;
	}
}
