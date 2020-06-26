
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
import javax.persistence.Transient;


@Entity

@Table(name="suggestedcontrols")
public class SuggestedControls   implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)	
	@Column(name="suggestedControlsId")
	private int suggestedControlsId;
	
	@Column(name ="sugestedControlsName")
	private String suggestedControlsName;
	
	@JoinColumn(name = "riskId", nullable = true)
	@ManyToOne(fetch = FetchType.EAGER)
	private RiskObjective riskId;
	
	@Column(name ="checked")
	private boolean checked;
	
	@Column(name ="suggestedReferenceNo")
	private String suggestedReferenceNo;

	@Column(name ="controlRisk")
	private int controlRisk;
	

	public int getSuggestedControlsId() {
		return suggestedControlsId;
	}

	public void setSuggestedControlsId(int suggestedControlsId) {
		this.suggestedControlsId = suggestedControlsId;
	}

	public String getSuggestedControlsName() {
		return suggestedControlsName;
	}

	public void setSuggestedControlsName(String suggestedControlsName) {
		this.suggestedControlsName = suggestedControlsName;
	}

	public RiskObjective getRiskId() {
		return riskId;
	}

	public void setRiskId(RiskObjective riskId) {
		this.riskId = riskId;
	}

	public boolean getChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public String getSuggestedReferenceNo() {
		return suggestedReferenceNo;
	}

	public void setSuggestedReferenceNo(String suggestedReferenceNo) {
		this.suggestedReferenceNo = suggestedReferenceNo;
	}

	public int getControlRisk() {
		return controlRisk;
	}

	public void setControlRisk(int controlRisk) {
		this.controlRisk = controlRisk;
	}

}