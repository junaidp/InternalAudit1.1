
package com.internalaudit.shared;

import java.io.Serializable;

import javax.persistence.Entity;

@Entity

// @Table(name = "riskfactor")
public class SamplingExcelSheetEntity implements Serializable {

//	private String date;
//	private double referenceNo;
//	private String description;
//	private double amount;
//	private String location;
//	private String jobId;
//	private int id;
	private String category;
	private double docNo;

	private String date;
	private double itemCode;
	private String description;
	private double quantity;
	private double ucost;
	private double transCost;
	private double code;
	private String name;
	private int id;
	
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Double getDocNo() {
		return docNo;
	}
	
	
	public double getUcost() {
		return ucost;
	}
	public void setUcost(double ucost) {
		this.ucost = ucost;
	}
	public void setDocNo(Double docNo) {
		this.docNo = docNo;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public double getItemCode() {
		return itemCode;
	}
	public void setItemCode(double itemCode) {
		this.itemCode = itemCode;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getQuantity() {
		return quantity;
	}
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	public double getTransCost() {
		return transCost;
	}
	public void setTransCost(double transCost) {
		this.transCost = transCost;
	}
	public double getCode() {
		return code;
	}
	public void setCode(double code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
