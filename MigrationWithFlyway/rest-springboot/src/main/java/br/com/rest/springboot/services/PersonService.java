package br.com.rest.springboot.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.rest.springboot.data.vo.v1.PersonVO;
import br.com.rest.springboot.exceptions.ResourceNotFoundException;
import br.com.rest.springboot.mapper.DozerMapper;
import br.com.rest.springboot.model.Person;
import br.com.rest.springboot.repositories.PersonRepository;

@Service
public class PersonService {
	
	private Logger logger = Logger.getLogger(PersonService.class.getName());
	
	@Autowired
	PersonRepository repository;
	
	public PersonVO findById(Long id) {
		logger.info("Finding one PersonVO!");
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		
		return DozerMapper.parseObject(entity, PersonVO.class);
	}
	
	public List<PersonVO> findAll() {
		logger.info("Finding a list of PersonVO!");
		return DozerMapper.parseListObjects(repository.findAll(), PersonVO.class);
	}
	
	public PersonVO create(PersonVO personVO) { //getting from bodyRequest
		logger.info("PersonVO created!");
		
		//Converting from VO to Person
		var person = DozerMapper.parseObject(personVO, Person.class);
		
		//Converting from Person to VO
		return DozerMapper.parseObject(repository.save(person), PersonVO.class);
	}
	
	public PersonVO update(PersonVO personVO) { //getting from bodyRequest
		
		var entity = repository.findById(personVO.getId())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		
		entity.setAddress(personVO.getAddress());
		entity.setFirstName(personVO.getFirstName());
		entity.setLastName(personVO.getLastName());
		entity.setGender(personVO.getGender());
		entity.setAddress(personVO.getAddress());
		
		logger.info("PersonVO updated!");
		
		
		var vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
		return vo;
	}
	
	public void delete(Long id) { //getting from bodyRequest
		
		var person = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		
		logger.info("Deleting person with id " + id);
		repository.delete(person);
	}

}
