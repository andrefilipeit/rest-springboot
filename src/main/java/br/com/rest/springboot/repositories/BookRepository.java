package br.com.rest.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.rest.springboot.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {}