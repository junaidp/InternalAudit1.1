package com.internalaudit.shared;

import java.io.Serializable;
import java.util.ArrayList;

public class StrategicDTO implements Serializable{
	
	
	 private String strategicObjective;
	 private String auditableUnit;
	 private String departmentName;
	 private String riskRating;
	 private int deptId;
	 private int strategicId; // jobId
	 private String jobName;
	 private ArrayList<StrategicDepartments> departments = new ArrayList<StrategicDepartments>();
	 
	
	private int tab;
	 
	public String getStrategicObjective() {
		return strategicObjective;
	}
	public void setStrategicObjective(String strategicObjective) {
		this.strategicObjective = strategicObjective;
	}
	public String getAuditableUnit() {
		return auditableUnit;
	}
	public void setAuditableUnit(String auditableUnit) {
		this.auditableUnit = auditableUnit;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getRiskRating() {
		return riskRating;
	}
	public void setRiskRating(String riskRating) {
		this.riskRating = riskRating;
	}
	
	public String getDomainBasedOnTab() {
		
		int code = this.getTab();
		//this is hardcoded, perhaps it should come from db instead
		switch ( code )
		{
		
			case 0:
				return "Strategic";
			case 1:
				return "Operations";
			case 2:
				return "Reporting";
			case 3: 
				return "Compliance";
			default: //OOPs .. wrong code!
				return "Error : Code Undefined";
		}
	}
	
	public int getTab() {
		return tab;
	}
	public void setTab(int tab) {
		this.tab = tab;
	}
	public int getDeptId() {
		return deptId;
	}
	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}
	public int getStrategicId() {
		return strategicId;
	}
	public void setStrategicId(int strategicId) {
		this.strategicId = strategicId;
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public ArrayList<StrategicDepartments> getDepartments() {
		return departments;
	}
	public void setDepartments(ArrayList<StrategicDepartments> departments) {
		this.departments = departments;
	}
	
}
