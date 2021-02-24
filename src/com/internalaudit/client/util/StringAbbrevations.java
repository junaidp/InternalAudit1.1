package com.internalaudit.client.util;

public class StringAbbrevations {
	
	public String findAbbrevation(String name) {
		String abbrevateName = "" ;
		String[] names = name.split(" ");
		for(int i=0; i<names.length; i++) {
			abbrevateName += names[i].substring(0, 1);
		}
		return " ["+abbrevateName+"]";
	}

}
