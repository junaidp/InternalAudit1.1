package com.internalaudit.shared;

import java.io.Serializable;
import java.util.ArrayList;

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

@Table(name="user")
public class User   implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="userId")
	private int userId;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "username")
	private String name;
	
	@JoinColumn(name = "employeeId")
	@ManyToOne(fetch = FetchType.LAZY)
	private Employee employeeId;
	
	@Column(name="email")
	private String email;
	
	@Transient
	private ArrayList<ToDo> todos;
	
	@Transient
	private ArrayList<ToDo> userRaisedToDos;
	
	@Transient
	private ArrayList<InformationRequestEntity> userRaisedInformationRequests;
	
	@Transient
	private ArrayList<InformationRequestEntity> informationRequests ;
	
	public User(){}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int id) {
		this.userId = id;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Employee getEmployeeId() {
		return employeeId;
	}


	public void setEmployeeId(Employee employeeId) {
		this.employeeId = employeeId;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public ArrayList<ToDo> getTodos() {
		return todos;
	}


	public void setTodos(ArrayList<ToDo> todos) {
		this.todos = todos;
	}


	public ArrayList<InformationRequestEntity> getInformationRequests() {
		return informationRequests;
	}


	public void setInformationRequests(ArrayList<InformationRequestEntity> informationRequests) {
		this.informationRequests = informationRequests;
	}


	public ArrayList<ToDo> getUserRaisedToDos() {
		return userRaisedToDos;
	}


	public void setUserRaisedToDos(ArrayList<ToDo> userRaisedToDos) {
		this.userRaisedToDos = userRaisedToDos;
	}


	public ArrayList<InformationRequestEntity> getUserRaisedInformationRequests() {
		return userRaisedInformationRequests;
	}


	public void setUserRaisedInformationRequests(ArrayList<InformationRequestEntity> userRaisedInformationRequests) {
		this.userRaisedInformationRequests = userRaisedInformationRequests;
	}







	
}
