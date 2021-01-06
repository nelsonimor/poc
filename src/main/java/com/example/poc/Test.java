package com.example.poc;

import java.text.Normalizer;

import org.springframework.util.StringUtils;

public class Test {
	
	public static void main(String[] args) {
		//String s = "Athènes";
		//String s = "Besançon";
		String s = "Càret";
		System.out.println(s.toUpperCase());
		System.out.println(stripAccents(s));
	}

	
	public static String stripAccents(String s) 
	{
	    s = Normalizer.normalize(s, Normalizer.Form.NFD);
	    s = s.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
	    return s;
	}
}
