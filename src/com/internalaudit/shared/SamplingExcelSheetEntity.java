
package com.internalaudit.shared;

import java.io.Serializable;

import javax.persistence.Entity;

@Entity

// @Table(name = "riskfactor")
public class SamplingExcelSheetEntity implements Serializable {

	private String date;
	private double referenceNo;
	private String description;
	private double amount;
	private String location;
	private String jobId;
	private int id;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public double getReferenceNo() {
		return referenceNo;
	}

	public void setReferenceNo(double referenceNo) {
		this.referenceNo = referenceNo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
