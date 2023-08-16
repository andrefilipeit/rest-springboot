package br.com.rest.springboot.mathOperation;

public class Operation {
	public Double sum(Double numberOne, Double numberTwo) {
		return numberOne + numberTwo;
	}
	
	public Double subtract(Double numberOne, Double numberTwo) {
		return numberOne - numberTwo;
	}
	
	public Double multiplication(Double numberOne, Double numberTwo) {
		return numberOne * numberTwo;
	}
	
	public Double divide(Double numberOne, Double numberTwo) {
		return numberOne / numberTwo;
	}
	
	public Double average(Double numberOne, Double numberTwo) {
		return (numberOne + numberTwo)/2;
	}
	
	public Double squareRoot(Double number) {
		return Math.sqrt(number);
	}
}
