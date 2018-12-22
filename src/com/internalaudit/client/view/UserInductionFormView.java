package com.internalaudit.client.view;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.datepicker.client.DateBox;
import com.internalaudit.client.presenter.UserInductionFormPresenter.Display;
import com.internalaudit.shared.Employee;


public class UserInductionFormView extends FlexTable implements Display {
	
	private TextBox txtName = new TextBox();
	private ListBox listDivision = new ListBox();
	private TextBox txtDesignation = new TextBox();
	private ListBox listUserProfile = new ListBox();
	private DateBox dateOfJoingin = new DateBox();
	private DateBox dateAvailabalityFrom = new DateBox();
	private DateBox dateAvailabalityTo = new DateBox();
	private ListBox listSkillSet = new ListBox();
	private ButtonRound btnCancel = new ButtonRound("Back");
	private ButtonRound btnSubmit = new ButtonRound("Save");
//	private TextBox txtEmail = new TextBox();
	private ListBox listReportingTo = new ListBox();
	private Label lblReportingTo = new Label("Reporting to");
	private Label lblCompany = new Label("Company");
	private ListBox listCompany = new ListBox();
	private TextBox txtUserName = new TextBox();
	private PasswordTextBox txtPassword = new PasswordTextBox();
	private Employee loggedInUser;
	private Label lblUserNameError = new Label("Please enter valid Email Address");
	private Label lblPasswordError = new Label("Password cannot be empty");
	private Label lblEmailError = new Label("Email cannot be empty");
	
	public UserInductionFormView(Employee loggedInUser) {
		this.loggedInUser = loggedInUser;
		layout();
	}

	private void layout() {
		Label lblHeading = new Label("User Induction Form");
		lblHeading.addStyleName("blue");
		
		lblUserNameError.addStyleName("error");
		lblEmailError.addStyleName("error");
		lblPasswordError.addStyleName("error");
		
		lblUserNameError.setVisible(false);
		lblEmailError.setVisible(false);
		lblPasswordError.setVisible(false);
		
		setWidget(0, 1, lblHeading);
		setWidget(1, 0, new Label("Name"));
//		setWidget(2, 0, new Label("Email"));
		
		setWidget(3, 0, new Label("Username (Email)"));
		setWidget(3, 2, lblUserNameError);
		setWidget(4, 2, lblPasswordError);
		setWidget(2, 2, lblEmailError);
		setWidget(4, 0, new Label("Password"));
		
//		setWidget(3,  0, new Label("Division"));
		setWidget(5,  0, new Label("Designation"));
		setWidget(6,  0, new Label("User Profile"));
		setWidget(7,  0, lblReportingTo);
		setWidget(8,  0, new Label("Date of Joining"));
		setWidget(9,  0, new Label("Availability during the year"));
		setWidget(10, 0, new Label("Skill Set"));
		setWidget(1, 1, txtName);
//		setWidget(2, 1, txtEmail);
		setWidget(3, 1, txtUserName);
		setWidget(4, 1, txtPassword);
		setWidget(5, 1, txtDesignation);
		setWidget(6, 1, listUserProfile);
		setWidget(7, 1, listReportingTo);
		setWidget(8, 1, dateOfJoingin);
		setWidget(9, 1, dateAvailabalityFrom);
		setWidget(9, 2, dateAvailabalityTo);
		setWidget(10, 1, listSkillSet);
		setWidget(11, 1, lblCompany);
		setWidget(11, 2, listCompany);
		setWidget(12, 1, btnCancel);
		setWidget(12, 2, btnSubmit);
		
		lblReportingTo.setVisible(false);
		listReportingTo.setVisible(false);
		
		if (loggedInUser.getEmployeeName().equalsIgnoreCase("Muhammad Faheem Piracha") && loggedInUser.getEmployeeId() ==1){
			lblCompany.setVisible(true);
			listCompany.setVisible(true);
			}else{
				lblCompany.setVisible(false);
				listCompany.setVisible(false);
			}
			
	}
	
	public TextBox getTxtName(){
		return txtName;
	}
	
	
	public ListBox getListDivision(){
		return listDivision;
	}
	
	public ListBox getListReportingTo(){
		return listReportingTo;
	}
	
	public TextBox getTxtDesignation(){
		return txtDesignation;
	}
	
	public ListBox getListuserProfile(){
		return listUserProfile;
	}
	
	public DateBox getDateOfJoining(){
		return dateOfJoingin;
	}
	
	public DateBox getDateAvailabilityForm(){
		return dateAvailabalityFrom;
	}
	
	public DateBox getDateAvailabalityTo(){
		return dateAvailabalityTo;
	}
	
	public ListBox getListSkills(){
		return listSkillSet;
	}
	
	public ButtonRound getBtnCancel(){
		return btnCancel;
	}
	
	public ButtonRound getBtnSubmit(){
		return btnSubmit;
	}

	public TextBox getTxtUserName() {
		return txtUserName;
	}

	public void setTxtUserName(TextBox txtUserName) {
		this.txtUserName = txtUserName;
	}

	public PasswordTextBox getTxtPassword() {
		return txtPassword;
	}

	public void setTxtPassword(PasswordTextBox txtPassword) {
		this.txtPassword = txtPassword;
	}

	public Label getLblReportingTo() {
		return lblReportingTo;
	}

	public void setLblReportingTo(Label lblReportingTo) {
		this.lblReportingTo = lblReportingTo;
	}

	public void setListReportingTo(ListBox listReportingTo) {
		this.listReportingTo = listReportingTo;
	}

	public ListBox getListCompany() {
		return listCompany;
	}

	public void setListCompany(ListBox listCompany) {
		this.listCompany = listCompany;
	}

	public Label getLblUserNameError() {
		return lblUserNameError;
	}

	public void setLblUserNameError(Label lblUserNameError) {
		this.lblUserNameError = lblUserNameError;
	}

	public Label getLblPasswordError() {
		return lblPasswordError;
	}

	public void setLblPasswordError(Label lblPasswordError) {
		this.lblPasswordError = lblPasswordError;
	}

	public Label getLblEmailError() {
		return lblEmailError;
	}

	public void setLblEmailError(Label lblEmailError) {
		this.lblEmailError = lblEmailError;
	}
	
	

}
