package com.internalaudit.shared;

import java.io.Serializable;
import java.util.ArrayList;

public class RiskAssesmentDTO implements Serializable{
	
	Strategic strategic = new Strategic();
	ArrayList<StrategicRisk> strategicRisks = new ArrayList<StrategicRisk>();
	ArrayList<StrategicRiskFactor> arrayStrategicRiskFactor = new ArrayList<StrategicRiskFactor>(); 
	
	public Strategic getStrategic() {
		return strategic;
	}
	public void setStrategic(Strategic strategic) {
		this.strategic = strategic;
	}
	public ArrayList<StrategicRisk> getStrategicRisks() {
		return strategicRisks;
	}
	public void setStrategicRisks(ArrayList<StrategicRisk> strategicRisks) {
		this.strategicRisks = strategicRisks;
	}
	public ArrayList<StrategicRiskFactor> getArrayStrategicRiskFactor() {
		return arrayStrategicRiskFactor;
	}
	public void setArrayStrategicRiskFactor(ArrayList<StrategicRiskFactor> arrayStrategicRiskFactor) {
		this.arrayStrategicRiskFactor = arrayStrategicRiskFactor;
	}
	

}
