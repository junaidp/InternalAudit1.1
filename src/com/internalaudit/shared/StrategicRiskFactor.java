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

@Table(name = "strategicriskfactor")
public class StrategicRiskFactor implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;

	@JoinColumn(name = "strategicID", nullable = true)
	@ManyToOne(fetch = FetchType.EAGER)
	private Strategic strategicID;

	@JoinColumn(name = "riskFactorID", nullable = true)
	@ManyToOne(fetch = FetchType.EAGER)
	private RiskFactor riskFactorID;
	
	@Column(name = "probability")
	private int probability;
	
	@Column(name = "isCheck")
	private int check;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Strategic getStrategicID() {
		return strategicID;
	}

	public void setStrategicID(Strategic strategicID) {
		this.strategicID = strategicID;
	}

	public RiskFactor getRiskFactorID() {
		return riskFactorID;
	}

	public void setRiskFactorID(RiskFactor riskFactorID) {
		this.riskFactorID = riskFactorID;
	}

	public int getProbability() {
		return probability;
	}

	public void setProbability(int probability) {
		this.probability = probability;
	}

	public int getCheck() {
		return check;
	}

	public void setCheck(int check) {
		this.check = check;
	}

}
