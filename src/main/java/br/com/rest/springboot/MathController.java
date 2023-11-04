package br.com.rest.springboot;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.rest.springboot.exceptions.UnsuportedMathOperationException;

@RestController
public class MathController {

    @RequestMapping(value="/sum/{numberOne}/{numberTwo}", method=RequestMethod.GET)
    public Double sum(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) throws Exception {
        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
            throw new UnsuportedMathOperationException("Please set a numeric value!");
        }
        Double result = covertToDouble(numberOne) + covertToDouble(numberTwo);
        return result;        
    }
    
    @RequestMapping(value="/subtraction/{numberOne}/{numberTwo}", method=RequestMethod.GET)
    public Double subtraction(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) throws Exception {
        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
            throw new UnsuportedMathOperationException("Please set a numeric value!");
        }
        Double result = covertToDouble(numberOne) - covertToDouble(numberTwo);
        return result;        
    }
    
    @RequestMapping(value="/multiplication/{numberOne}/{numberTwo}", method=RequestMethod.GET)
    public Double multiplication(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) throws Exception {
        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
            throw new UnsuportedMathOperationException("Please set a numeric value!");
        }
        Double result = covertToDouble(numberOne) * covertToDouble(numberTwo);
        return result;        
    }
    
    @RequestMapping(value="/division/{numberOne}/{numberTwo}", method=RequestMethod.GET)
    public Double division(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) throws Exception {
        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
            throw new UnsuportedMathOperationException("Please set a numeric value!");
        }
        Double result = covertToDouble(numberOne) / covertToDouble(numberTwo);
        return result;        
    }
    
    @RequestMapping(value="/mean/{numberOne}/{numberTwo}", method=RequestMethod.GET)
    public Double mean(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) throws Exception {
        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
            throw new UnsuportedMathOperationException("Please set a numeric value!");
        }
        Double result = (covertToDouble(numberOne) + covertToDouble(numberTwo)) / 2;
        return result;        
    }
    
    @RequestMapping(value="/squareRoot/{number}", method=RequestMethod.GET)
    public Double squareRoot(@PathVariable("number") String number) throws Exception {
        if (!isNumeric(number)) {
            throw new UnsuportedMathOperationException("Please set a numeric value!");
        }
        Double result = Math.sqrt(covertToDouble(number));
        return result;        
    }
    
    public static Double covertToDouble(String strNumber) {
        if (strNumber == null) return 0d; 
        String number = strNumber.replaceAll(",", ".");
        if (isNumeric(number)) return Double.parseDouble(number);
        return 0d;
    }

    public static boolean isNumeric(String strNumber) {
        if (strNumber == null) return false; 
        String number = strNumber.replaceAll(",", ".");
        return number.matches("[-+]?[0-9]*\\.?[0-9]+");
    }
    
}
