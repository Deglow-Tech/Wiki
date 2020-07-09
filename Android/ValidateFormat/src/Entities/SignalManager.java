package Entities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignalManager {
	
	public static void validFormatFromBand(String input) {
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
	
	public static void validFormatFromBabyGirdle(String input) {
		Pattern patron = Pattern.compile("\\d{1,3};[0-1]?");
	    Matcher mat = patron.matcher(input);
	    
	    if(mat.matches()) {
	    	System.out.println("Valid format");
	    	String [] parts = input.split(";");
	    	int frecuency, movement;
	    	
	    	frecuency = Integer.parseInt(parts[0]);
	    	movement = Integer.parseInt(parts[1]);
	    	
	    	System.out.println("The heart rate is " + frecuency);
	    	System.out.println("Movement detected: " + (movement == 1 ? "Yes" : "No"));
	    }
	    else
	    	System.out.println("Invalid format");
	}

}
