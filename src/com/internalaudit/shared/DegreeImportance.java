package com.internalaudit.shared;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity

@Table(name = "degreeImportance")

public class DegreeImportance implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "degreeImportanceID")
	private int degreeImportanceID;

	@Column(name = "degreeImportanceName")
	private String degreeImportanceName;
	
	@Column(name = "companyID")
	private int companyID;
	
	@Column(name = "checked")
	private int checked;
	
	@Column(name = "isactive")
	private int isActive;

	public int getDegreeImportanceID() {
		return degreeImportanceID;
	}

	public void setDegreeImportanceID(int degreeImportanceID) {
		this.degreeImportanceID = degreeImportanceID;
	}

	public String getDegreeImportanceName() {
		return degreeImportanceName;
	}

	public void setDegreeImportanceName(String degreeImportanceName) {
		this.degreeImportanceName = degreeImportanceName;
	}

	public int getCompanyID() {
		return companyID;
	}

	public void setCompanyID(int companyID) {
		this.companyID = companyID;
	}

	public int getChecked() {
		return checked;
	}

	public void setChecked(int checked) {
		this.checked = checked;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}
	
}
