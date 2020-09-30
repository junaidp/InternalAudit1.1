
package com.internalaudit.shared;

import java.io.Serializable;

import javax.persistence.Entity;

@Entity

// @Table(name = "riskfactor")
public class SamplingExcelSheetEntity implements Serializable {

	private String category;
	private String docNo;
	private String date;
	private String itemCode;
	private String description;
	private String quantity;
	private String ucost;
	private String transCost;
	private String code;
	private String name;
	private int id;
	
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getUcost() {
		return ucost;
	}
	public void setUcost(String ucost) {
		this.ucost = ucost;
	}
	public String getTransCost() {
		return transCost;
	}
	public void setTransCost(String transCost) {
		this.transCost = transCost;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	public String getDocNo() {
		return docNo;
	}
	public void setDocNo(String docNo) {
		this.docNo = docNo;
	}
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
