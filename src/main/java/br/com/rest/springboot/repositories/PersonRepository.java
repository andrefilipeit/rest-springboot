package br.com.rest.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.rest.springboot.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long>{}
