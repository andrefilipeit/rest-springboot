package br.com.rest.springboot.mapper.custom;

import java.util.Date;

import org.springframework.stereotype.Service;

import br.com.rest.springboot.data.vo.v2.PersonVOV2;
import br.com.rest.springboot.model.Person;

@Service
public class PersonMapper {

	public PersonVOV2 convertEntityToVo(Person person) {
		PersonVOV2 vov2 = new PersonVOV2();
		vov2.setId(person.getId());
		vov2.setAddress(person.getAddress());
		vov2.setFirstName(person.getFirstName());
		vov2.setLastName(person.getLastName());
		vov2.setGender(person.getGender());
		vov2.setBirthday(new Date());
		
		return vov2;
	}
	
	public Person convertVoV2ToEntity(PersonVOV2 personVOV2) {
		Person entity = new Person();
		entity.setId(personVOV2.getId());
		entity.setAddress(personVOV2.getAddress());
		entity.setFirstName(personVOV2.getFirstName());
		entity.setLastName(personVOV2.getLastName());
		entity.setGender(personVOV2.getGender());
		//person.setBirthday(personVOV2.getBirthday());
		
		return entity;
	}
	
	
}
