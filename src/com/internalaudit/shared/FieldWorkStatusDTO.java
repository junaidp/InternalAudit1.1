package com.internalaudit.shared;

import java.io.Serializable;
import java.util.Date;

public class FieldWorkStatusDTO implements Serializable{
	
	private int audtiStepId=0;
	private String auditStepName;
	private Date approvalDate;
	private boolean haveExceptions;
	private String status;
	
	public FieldWorkStatusDTO(){
		
		
		
	}

	public int getAudtiStepId() {
		return audtiStepId;
	}

	public void setAudtiStepId(int audtiStepId) {
		this.audtiStepId = audtiStepId;
	}

	public String getAuditStepName() {
		return auditStepName;
	}

	public void setAuditStepName(String auditStepName) {
		this.auditStepName = auditStepName;
	}

	public Date getApprovalDate() {
		return approvalDate;
	}

	public void setApprovalDate(Date approvalDate) {
		this.approvalDate = approvalDate;
	}

	public boolean isHaveExceptions() {
		return haveExceptions;
	}

	public void setHaveExceptions(boolean haveExceptions) {
		this.haveExceptions = haveExceptions;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
