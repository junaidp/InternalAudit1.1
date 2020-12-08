package com.internalaudit.shared;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity

@Table(name = "strategicrisk")
public class StrategicRisk implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;

	@JoinColumn(name = "strategicId", nullable = true)
	@ManyToOne(fetch = FetchType.EAGER)
	private Strategic strategicId;
	
	@JoinColumn(name = "degreeImportanceID", nullable = true)
	@ManyToOne(fetch = FetchType.EAGER)
	private DegreeImportance degreeImportanceID;
	
	@Column(name = "ischeck")
	private int check;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Strategic getStrategicId() {
		return strategicId;
	}

	public void setStrategicId(Strategic strategicId) {
		this.strategicId = strategicId;
	}

	public DegreeImportance getDegreeImportanceID() {
		return degreeImportanceID;
	}

	public void setDegreeImportanceID(DegreeImportance degreeImportanceID) {
		this.degreeImportanceID = degreeImportanceID;
	}

	public int getCheck() {
		return check;
	}

	public void setCheck(int check) {
		this.check = check;
	}

}
