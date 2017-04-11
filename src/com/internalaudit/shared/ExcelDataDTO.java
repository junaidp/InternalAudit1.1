package com.internalaudit.shared;

import java.io.Serializable;

public class ExcelDataDTO implements Serializable {
	
	private String objective;
	private String auditableUnit;
	private String domain;
	private String division;
	private String riskAssesment;
	
	public String getObjective() {
		return objective;
	}
	public void setObjective(String objective) {
		this.objective = objective;
	}
	public String getAuditableUnit() {
		return auditableUnit;
	}
	public void setAuditableUnit(String auditableUnit) {
		this.auditableUnit = auditableUnit;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}
	public String getRiskAssesment() {
		return riskAssesment;
	}
	public void setRiskAssesment(String riskAssesment) {
		this.riskAssesment = riskAssesment;
	}	
	

}
