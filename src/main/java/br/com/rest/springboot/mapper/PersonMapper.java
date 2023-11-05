package br.com.rest.springboot.mapper;

import java.util.Date;

import org.springframework.stereotype.Service;

import br.com.rest.springboot.data.vo.v2.PersonVOV2;
import br.com.rest.springboot.model.Person;

@Service
public class PersonMapper {
	
	public PersonVOV2 convertEntityToVoV2(Person person) {
		PersonVOV2 vov2 = new PersonVOV2();
		vov2.setId(person.getId());
		vov2.setAddress(person.getAddress());
		vov2.setBirthday(new Date());
		vov2.setFirstName(person.getFirstName());
		vov2.setLastName(person.getLastName());
		vov2.setGender(person.getGender());
		return vov2;
	}
	
	
	public Person convertVoV2ToEntity(PersonVOV2 person) {
		Person entity = new Person();
		entity.setId(person.getId());
		entity.setAddress(person.getAddress());
		//vo.setBirthDay(new Date()); // It will be set when the column exists in the database
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setGender(person.getGender());
		return entity;
	}

}
