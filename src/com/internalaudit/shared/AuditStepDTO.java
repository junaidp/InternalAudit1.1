package com.internalaudit.shared;

import java.util.ArrayList;

public class AuditStepDTO {
	
	private AuditStep auditStep = new AuditStep();
	ArrayList<Exceptions> exceptions = new ArrayList<Exceptions>();
	
	
	public AuditStep getAuditStep() {
		return auditStep;
	}
	public void setAuditStep(AuditStep auditStep) {
		this.auditStep = auditStep;
	}
	public ArrayList<Exceptions> getExceptions() {
		return exceptions;
	}
	public void setExceptions(ArrayList<Exceptions> exceptions) {
		this.exceptions = exceptions;
	}

}
