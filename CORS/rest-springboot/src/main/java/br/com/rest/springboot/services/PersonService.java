package br.com.rest.springboot.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Service;

import br.com.rest.springboot.controllers.PersonController;
import br.com.rest.springboot.data.vo.v1.PersonVO;
import br.com.rest.springboot.exceptions.RequiredObjectIsNullException;
import br.com.rest.springboot.exceptions.ResourceNotFoundException;
import br.com.rest.springboot.mapper.DozerMapper;
import br.com.rest.springboot.model.Person;
import br.com.rest.springboot.repositories.PersonRepository;

@Service
public class PersonService {
	
	private Logger logger = Logger.getLogger(PersonService.class.getName());
	
	@Autowired
	PersonRepository repository;
	
	public PersonVO findById(Long id) throws Exception {
		logger.info("Finding one PersonVO!");
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		
		PersonVO vo =  DozerMapper.parseObject(entity, PersonVO.class);
		vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
		return vo;
	}
	
	public List<PersonVO> findAll() {
		logger.info("Finding a list of PersonVO!");
		var persons = DozerMapper.parseListObjects(repository.findAll(), PersonVO.class);
		persons.
			stream().
			forEach(p -> {
				try {
					p.add(linkTo(methodOn(PersonController.class).findById(p.getPk())).withSelfRel());
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
		
		return persons;
	}
	
	public PersonVO create(PersonVO personVO) throws Exception { //getting from bodyRequest
		
		if(personVO == null) throw new RequiredObjectIsNullException(); 
		
		logger.info("PersonVO created!");
		
		//Converting from VO to Person
		var person = DozerMapper.parseObject(personVO, Person.class);
		
		//Converting from Person to VO
		var vo = DozerMapper.parseObject(repository.save(person), PersonVO.class);
		vo.add(linkTo(methodOn(PersonController.class).findById(vo.getPk())).withSelfRel());
		return vo;
	}
	
	public PersonVO update(PersonVO personVO) throws Exception { //getting from bodyRequest
		
		if(personVO == null) throw new RequiredObjectIsNullException(); 
		
		var entity = repository.findById(personVO.getPk())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		
		entity.setAddress(personVO.getAddress());
		entity.setFirstName(personVO.getFirstName());
		entity.setLastName(personVO.getLastName());
		entity.setGender(personVO.getGender());
		entity.setAddress(personVO.getAddress());
		
		logger.info("PersonVO updated!");
		
		
		var vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
		vo.add(linkTo(methodOn(PersonController.class).findById(entity.getId())).withSelfRel());
		return vo;
	}
	
	public void delete(Long id) { //getting from bodyRequest
		
		var person = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		
		logger.info("Deleting person with id " + id);
		repository.delete(person);
	}

}
