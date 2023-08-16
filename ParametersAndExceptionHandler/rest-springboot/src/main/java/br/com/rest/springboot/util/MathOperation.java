package br.com.rest.springboot.util;

public class MathOperation {
	
	public static boolean isNumeric(String strNumber) {
		if(strNumber == null) return false;
		String number = strNumber.replaceAll(",", ".");

		//verify if matches positives numbers 0-9 . and more positive numbers
		return number.matches("[-+]?[0-9]*\\.?[0-9]+");
	}
	
	public static Double convertToDouble(String strNumber) {
		if(strNumber == null) return 0D;
		
		//BR10,25 / US10.25
		String number = strNumber.replaceAll(",", ".");
		
		if (isNumeric(number)) return Double.parseDouble(number);
		
		return 0D;
	}
}
