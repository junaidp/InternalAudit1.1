package com.internalaudit.shared;

public enum RollsEnum {
	HEAD(1,"Head of Internal Audit"),TEAMLEAD(2, "Team Lead"),AUDITOR(3, "Auditor "),ADMIN(4,"Admin"),MANAGEMENT(5,"Management");
	
	private int value; 
	private String name; 

	  private RollsEnum(int value, String name) {
	    this.value = value;
	    this.name = name;
	  }

	  public int getValue() {
	    return value;
	  }
	  
	  public String getName() {
		    return name;
		  }
	
}
