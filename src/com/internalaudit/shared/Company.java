package com.internalaudit.shared;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity

@Table(name="company")
public class Company   implements Serializable {

	
	@Id 
	@Column(name="companyId")
	private int companyId;
	
	@Column(name="name")
	private String name;
	
	@Column(name="contactPerson")
	private String contactPerson;
	
	@Column(name="contactPersonEmail")
	private String contactPersonEmail;
	
	@Column(name="package")
	private String userPackage;
	
	@Column(name="noOfUsersAllowed")
	private int noOfUsersAllowed;
	
	@Column(name="noOfMngmntUsersAllowed")
	private int noOfMngmntUsersAllowed;
	
	@Column(name="companyLogo")
	private String companyLogo;

	public int getCompanyId() {
		return companyId;
	}

	public int getNoOfUsersAllowed() {
		return noOfUsersAllowed;
	}

	public void setNoOfUsersAllowed(int noOfUsersAllowed) {
		this.noOfUsersAllowed = noOfUsersAllowed;
	}

	public String getCompanyLogo() {
		return companyLogo;
	}

	public void setCompanyLogo(String companyLogo) {
		this.companyLogo = companyLogo;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getContactPersonEmail() {
		return contactPersonEmail;
	}

	public void setContactPersonEmail(String contactPersonEmail) {
		this.contactPersonEmail = contactPersonEmail;
	}

	public String getUserPackage() {
		return userPackage;
	}

	public void setUserPackage(String userPackage) {
		this.userPackage = userPackage;
	}

	public int getNoOfMngmntUsersAllowed() {
		return noOfMngmntUsersAllowed;
	}

	public void setNoOfMngmntUsersAllowed(int noOfMngmntUsersAllowed) {
		this.noOfMngmntUsersAllowed = noOfMngmntUsersAllowed;
	}

}
