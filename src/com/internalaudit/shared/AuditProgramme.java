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

@Table(name="auditprogramme")
public class AuditProgramme   implements Serializable {

	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)	
	@Column(name="auditProgrammeId")
	private int auditProgrammeId;
	
	@Column(name ="auditProgrammeName")
	private String auditProgrammeName;
	
	@JoinColumn(name = "suggestedControlId", nullable = true)
	@ManyToOne(fetch = FetchType.EAGER)
	private SuggestedControls suggestedControlsId;
	
	@JoinColumn(name = "reviewer", nullable = true)
	@ManyToOne(fetch = FetchType.EAGER)
	private Employee reviewer;

	public int getAuditProgrammeId() {
		return auditProgrammeId;
	}

	public void setAuditProgrammeId(int auditProgrammeId) {
		this.auditProgrammeId = auditProgrammeId;
	}

	public String getAuditProgrammeName() {
		return auditProgrammeName;
	}

	public void setAuditProgrammeName(String auditProgrammeName) {
		this.auditProgrammeName = auditProgrammeName;
	}

	public SuggestedControls getSuggestedControlsId() {
		return suggestedControlsId;
	}

	public void setSuggestedControlsId(SuggestedControls suggestedControlsId) {
		this.suggestedControlsId = suggestedControlsId;
	}

	public Employee getReviewer() {
		return reviewer;
	}

	public void setReviewer(Employee reviewer) {
		this.reviewer = reviewer;
	}
}
