package com.internalaudit.client.view;

public enum UserPackages {
    TRIAL(0, "Trial",
            "15 days trial for premium services"),
    BASIC(1, "Basic",
            "Basic Service, No Libraries "),
    GOLD(2, "Gold",
            "Gold Pacakge having limited Services"),
	PLATINUM(3, "Platinum",
            "Platinum Pacakge having full Services");
	
	int id;
    String name;
    String description ;
    
    UserPackages(  int id,String name, String description ){
        this.name = name;
        this.id = id;
        this.description = description;
    }
    
}
