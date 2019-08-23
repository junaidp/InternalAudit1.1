package com.internalaudit.shared;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.internalaudit.shared.Process;



@Entity

@Table(name="subprocess")
public class SubProcess   implements Serializable {

	
	@Id 
	@Column(name="subprocessId")
	private int subProcessId;
	
	@Column(name="subprocessName")
	private String subProcessName;

	public int getSubProcessId() {
		return subProcessId;
	}
	@JoinColumn(name = "processId")
	@ManyToOne(fetch = FetchType.EAGER)
	private Process processId;
	

	public void setSubProcessId(int subProcessId) {
		this.subProcessId = subProcessId;
	}

	public String getSubProcessName() {
		return subProcessName;
	}

	public void setSubProcessName(String subProcessName) {
		this.subProcessName = subProcessName;
	}

	public Process getProcessId() {
		return processId;
	}

	public void setProcessId(Process processId) {
		this.processId = processId;
	}

//	@Column(name="processId")
//	private int processId;
}
