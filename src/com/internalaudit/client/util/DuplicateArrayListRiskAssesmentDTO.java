package com.internalaudit.client.util;

import java.util.ArrayList;

import com.internalaudit.shared.RiskAssesmentDTO;

public class DuplicateArrayListRiskAssesmentDTO {
	
	public ArrayList<RiskAssesmentDTO> mergeRuplicateList(ArrayList<RiskAssesmentDTO> riskAssesmentDTOs) {
		int size = riskAssesmentDTOs.size(); 
		for(int i=0; i<size; i++) {
			for(int j=i+1; j<size; j++) {
				if(riskAssesmentDTOs.get(i).getStrategic().getStrategicObjective().equalsIgnoreCase(riskAssesmentDTOs.get(j).getStrategic().getStrategicObjective())) {
					riskAssesmentDTOs.remove(j);
					size--;
					j--;
				}
			}
		}
		return riskAssesmentDTOs;	
	}

}
