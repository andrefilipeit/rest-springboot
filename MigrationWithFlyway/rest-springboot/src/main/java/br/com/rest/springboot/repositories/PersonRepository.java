package br.com.rest.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.rest.springboot.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{}
