package com.example.poc;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestPattern {
	
	public static void main(String[] args) {

		
		Pattern pattern = Pattern.compile("^([a-zA-Z\\u0080-\\u024F]+(?:. |-| |'))*[a-zA-Z\\u0080-\\u024F]*$");
	    System.out.println(pattern.matcher("toto").matches());
	    System.out.println(pattern.matcher("New-York").matches());
	    System.out.println(pattern.matcher("München").matches());
	    System.out.println(pattern.matcher("<%UNDEFINED%>").matches());
	    System.out.println(pattern.matcher("aint Vallier").matches());
	    System.out.println(pattern.matcher("UNDEFINED>").matches());
	}

}
