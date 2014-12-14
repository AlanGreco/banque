package util;

public class util {
	
	public static boolean isParsableInt (String input){
	    boolean parsable = true;
	    try{
	        Integer.parseInt(input);
	    }catch(NumberFormatException e){
	        parsable = false;
	    }
	    return parsable;
	}
	
	public static boolean isParsableDouble (String input){
	    boolean parsable = true;
	    try{
	        Double.parseDouble(input);
	    }catch(NumberFormatException e){
	        parsable = false;
	    }
	    return parsable;
	}
}
