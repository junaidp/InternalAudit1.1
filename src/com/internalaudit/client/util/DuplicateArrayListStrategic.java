package com.internalaudit.client.util;

import java.util.ArrayList;

import com.internalaudit.shared.Strategic;

public class DuplicateArrayListStrategic {
	
	public ArrayList<Strategic> mergeRuplicateList(ArrayList<Strategic> result) {
		int size = result.size(); 
		for(int i=0; i<size; i++) {
			for(int j=i+1; j<size; j++) {
				if(result.get(i).getStrategicObjective().equalsIgnoreCase(result.get(j).getStrategicObjective())) {
					result.remove(j);
					size--;
					j--;
				}
			}
		}
		return result;	
	}
}
