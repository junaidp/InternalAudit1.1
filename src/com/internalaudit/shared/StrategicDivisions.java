package com.internalaudit.shared;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity

@Table(name="strategicdivisions")

public class StrategicDivisions implements Serializable{
	
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="divisionId")
	private int divisionId;
	
	@Column(name ="strategic")
	private int strategic;

	@JoinColumn(name = "division")
	@ManyToOne(fetch = FetchType.EAGER)
	private Division division;
	
	@Transient
	ArrayList<StrategicDepartments> arrayStrategicDepartments;

	public int getDivisionId() {
		return divisionId;
	}

	public void setDivisionId(int divisionId) {
		this.divisionId = divisionId;
	}

	public int getStrategic() {
		return strategic;
	}

	public void setStrategic(int strategic) {
		this.strategic = strategic;
	}

	public Division getDivision() {
		return division;
	}

	public void setDivision(Division division) {
		this.division = division;
	}

	public ArrayList<StrategicDepartments> getArrayStrategicDepartments() {
		return arrayStrategicDepartments;
	}

	public void setArrayStrategicDepartments(ArrayList<StrategicDepartments> arrayStrategicDepartments) {
		this.arrayStrategicDepartments = arrayStrategicDepartments;
	}
	
}
