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

@Table(name = "strategicsubprocess")
public class StrategicSubProcess implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "strategicSubProcess")
	private int strategicSubProcess;

	@Column(name = "strategicID")
	private int strategicID;

	@JoinColumn(name = "subProcessId", nullable = true)
	@ManyToOne(fetch = FetchType.EAGER)
	private SubProcess subProcessId;

	public int getStrategicID() {
		return strategicID;
	}

	public void setStrategicID(int strategicID) {
		this.strategicID = strategicID;
	}

	public int getStrategicSubProcess() {
		return strategicSubProcess;
	}

	public void setStrategicSubProcess(int strategicSubProcess) {
		this.strategicSubProcess = strategicSubProcess;
	}

	public SubProcess getSubProcessId() {
		return subProcessId;
	}

	public void setSubProcessId(SubProcess subProcessId) {
		this.subProcessId = subProcessId;
	}

}
