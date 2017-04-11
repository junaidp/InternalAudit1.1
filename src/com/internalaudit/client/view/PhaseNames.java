package com.internalaudit.client.view;

public class PhaseNames {
	
	public static String getPhaseNames(int phaseNum){
		if(phaseNum == 1){
			return "Audit Universe Identifiction";
		}
		else if(phaseNum == 2){
			return "Risk Assesment";
		}
		else if(phaseNum == 3){
			return "Consilidation";
		}
		else if(phaseNum == 4){
			return "Prioritization";
		}
		else if(phaseNum == 5){
			return "Final Auditables";
		}else{
			return "";
		}
	}

}
