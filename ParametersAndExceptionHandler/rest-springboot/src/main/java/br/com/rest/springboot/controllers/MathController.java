package br.com.rest.springboot.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.rest.springboot.exceptions.UnsupportedMathOperationException;
import br.com.rest.springboot.mathOperation.Operation;
import br.com.rest.springboot.util.MathOperation;

@RestController
public class MathController {

	Operation operation = new Operation();
	
	@RequestMapping(value = "/sum/{numberOne}/{numberTwo}", 
			method=RequestMethod.GET) //{required-param}
	public Double sum(
			//PathVariable is used to recover pathParams explicit in URL - numberOne and numberTwo
			@PathVariable(value = "numberOne") String numberOne,
			@PathVariable(value = "numberTwo") String numberTwo
			) throws Exception {
		
		if(!MathOperation.isNumeric(numberOne) || !MathOperation.isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a Numeric Value");
		}
		
		return operation.sum(MathOperation.convertToDouble(numberOne), MathOperation.convertToDouble(numberTwo));
	}
	
	@RequestMapping(value = "/subtract/{numberOne}/{numberTwo}", 
			method=RequestMethod.GET) //{required-param}
	public Double subtract(
			//PathVariable is used to recover pathParams explicit in URL - numberOne and numberTwo
			@PathVariable(value = "numberOne") String numberOne,
			@PathVariable(value = "numberTwo") String numberTwo
			) throws Exception {
		
		if(!MathOperation.isNumeric(numberOne) || !MathOperation.isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a Numeric Value");
		}
		
		return operation.subtract(MathOperation.convertToDouble(numberOne), MathOperation.convertToDouble(numberTwo));
	}
	
	@RequestMapping(value = "/multiplication/{numberOne}/{numberTwo}", 
			method=RequestMethod.GET) //{required-param}
	public Double multiplication(
			//PathVariable is used to recover pathParams explicit in URL - numberOne and numberTwo
			@PathVariable(value = "numberOne") String numberOne,
			@PathVariable(value = "numberTwo") String numberTwo
			) throws Exception {
		
		if(!MathOperation.isNumeric(numberOne) || !MathOperation.isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a Numeric Value");
		}
		
		return operation.multiplication(MathOperation.convertToDouble(numberOne), MathOperation.convertToDouble(numberTwo));
	}
	
	@RequestMapping(value = "/divide/{numberOne}/{numberTwo}", 
			method=RequestMethod.GET) //{required-param}
	public Double divide(
			//PathVariable is used to recover pathParams explicit in URL - numberOne and numberTwo
			@PathVariable(value = "numberOne") String numberOne,
			@PathVariable(value = "numberTwo") String numberTwo
			) throws Exception {
		
		if(!MathOperation.isNumeric(numberOne) || !MathOperation.isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a Numeric Value");
		}
		
		return operation.divide(MathOperation.convertToDouble(numberOne), MathOperation.convertToDouble(numberTwo));
	}
	
	@RequestMapping(value = "/average/{numberOne}/{numberTwo}", 
			method=RequestMethod.GET) //{required-param}
	public Double average(
			//PathVariable is used to recover pathParams explicit in URL - numberOne and numberTwo
			@PathVariable(value = "numberOne") String numberOne,
			@PathVariable(value = "numberTwo") String numberTwo
			) throws Exception {
		
		if(!MathOperation.isNumeric(numberOne) || !MathOperation.isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a Numeric Value");
		}
		
		return operation.average(MathOperation.convertToDouble(numberOne), MathOperation.convertToDouble(numberTwo));
	}
	
	@RequestMapping(value = "/squareRoot/{numberOne}", 
			method=RequestMethod.GET) //{required-param}
	public Double squareRoot(
			//PathVariable is used to recover pathParams explicit in URL - numberOne and numberTwo
			@PathVariable(value = "numberOne") String numberOne
			) throws Exception {
		
		if(!MathOperation.isNumeric(numberOne)) {
			throw new UnsupportedMathOperationException("Please set a Numeric Value");
		}
		
		Double numberOneDouble = MathOperation.convertToDouble(numberOne);
		
		return operation.squareRoot(numberOneDouble);
	}

}
