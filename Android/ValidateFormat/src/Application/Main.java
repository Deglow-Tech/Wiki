package Application;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

	public static void main(String[] args) {
		String input = "86;95;22.5";
		Pattern patron = Pattern.compile("\\d{1,3};\\d{1,3};\\d{1,3}.?\\d?");
	    Matcher mat = patron.matcher(input);
	    
	    if(mat.matches()) {
	    	System.out.println("Valid format");
	    	String [] parts = input.split(";");
	    	int frecuency, oxygen;
	    	double temperature;
	    	
	    	frecuency = Integer.parseInt(parts[0]);
	    	oxygen = Integer.parseInt(parts[1]);
	    	temperature = Double.parseDouble(parts[2]);
	    	
	    	System.out.println("The heart rate is " + frecuency);
	    	System.out.println("The oxygen in blood is " + oxygen);
	    	System.out.println("The temperature is " + temperature);
	    }
	    else
	    	System.out.println("Invalid format");
	}

}
