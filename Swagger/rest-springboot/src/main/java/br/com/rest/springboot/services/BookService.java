package br.com.rest.springboot.services;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.rest.springboot.controllers.BookController;
import br.com.rest.springboot.controllers.PersonController;
import br.com.rest.springboot.data.vo.v1.BookVO;
import br.com.rest.springboot.exceptions.RequiredObjectIsNullException;
import br.com.rest.springboot.exceptions.ResourceNotFoundException;
import br.com.rest.springboot.mapper.DozerMapper;
import br.com.rest.springboot.model.Book;
import br.com.rest.springboot.repositories.BookRepository;

@Service
public class BookService {
	
	private Logger logger = Logger.getLogger(BookService.class.getName());
	
	@Autowired
	BookRepository repository;
	
	public BookVO findById(Long id) throws Exception {
		logger.info("Finding one BookVO!");
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		
		BookVO vo =  DozerMapper.parseObject(entity, BookVO.class);
		vo.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
		return vo;
	}
	
	public List<BookVO> findAll() {
		logger.info("Finding a list of BookVO!");
		var books = DozerMapper.parseListObjects(repository.findAll(), BookVO.class);
		books.
			stream().
			forEach(p -> {
				try {
					p.add(linkTo(methodOn(BookController.class).findById(p.getPk())).withSelfRel());
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
		
		return books;
	}
	
	public BookVO create(BookVO bookVO) throws Exception { //getting from bodyRequest
		
		if(bookVO == null) throw new RequiredObjectIsNullException(); 
		
		logger.info("BookVO created!");
		
		//Converting from VO to Book
		var book = DozerMapper.parseObject(bookVO, Book.class);
		
		//Converting from Book to VO
		var vo = DozerMapper.parseObject(repository.save(book), BookVO.class);
		vo.add(linkTo(methodOn(BookController.class).findById(vo.getPk())).withSelfRel());
		return vo;
	}
	
	public BookVO update(BookVO bookVO) throws Exception { //getting from bodyRequest
		
		if(bookVO == null) throw new RequiredObjectIsNullException(); 
		
		var entity = repository.findById(bookVO.getPk())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		
		entity.setAuthor(bookVO.getAuthor());
		entity.setLaunchDate(bookVO.getLaunchDate());
		entity.setPrice(bookVO.getPrice());
		entity.setTitle(bookVO.getTitle());
		
		logger.info("BookVO updated!");
		
		
		var vo = DozerMapper.parseObject(repository.save(entity), BookVO.class);
		vo.add(linkTo(methodOn(BookController.class).findById(entity.getId())).withSelfRel());
		return vo;
	}
	
	public void delete(Long id) { //getting from bodyRequest
		
		var book = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		
		logger.info("Deleting book with id " + id);
		repository.delete(book);
	}

}
