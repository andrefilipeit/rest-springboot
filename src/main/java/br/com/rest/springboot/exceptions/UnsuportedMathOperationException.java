package br.com.rest.springboot.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UnsuportedMathOperationException extends RuntimeException{
	
	public UnsuportedMathOperationException(String ex) {
		super(ex);
	}

	private static final long serialVersionUID = 1L;

}
