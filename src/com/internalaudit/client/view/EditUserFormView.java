package com.internalaudit.client.view;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.datepicker.client.DateBox;
import com.internalaudit.client.InternalAuditService;
import com.internalaudit.client.InternalAuditServiceAsync;
import com.internalaudit.client.presenter.EditUserFormPresenter.Display;
import com.internalaudit.client.view.AuditEngagement.LabelBold;
import com.internalaudit.shared.Employee;

public class EditUserFormView extends FlexTable implements Display {

	private TextBox txtName = new TextBox();
	private ListBox listDivision = new ListBox();
	private TextBox txtDesignation = new TextBox();
	private ListBox listUserProfile = new ListBox();
	private DateBox dateOfJoingin = new DateBox();
	private DateBox dateAvailabalityFrom = new DateBox();
	private DateBox dateAvailabalityTo = new DateBox();
	private ListBox listSkillSet = new ListBox();
	private Button btnCancel = new Button("Back");
	private Button btnSubmit = new Button("Update");
	// private TextBox txtEmail = new TextBox();
	private ListBox listReportingTo = new ListBox();
	private ListBox listEmployees = new ListBox();
	private Label lblReportingTo = new Label("Reporting to");
	private LabelBold lblCompany = new LabelBold("Company");
	private ListBox listCompany = new ListBox();
	private TextBox txtUserName = new TextBox();
	private PasswordTextBox txtPassword = new PasswordTextBox();
	private Employee loggedInUser;
	private Label lblUserNameError = new Label("Please enter valid Email Address");
	private Label lblPasswordError = new Label("Password cannot be empty");
	private Label lblEmailError = new Label("Email cannot be empty");
	private InternalAuditServiceAsync rpcService = GWT.create(InternalAuditService.class);

	public EditUserFormView(Employee loggedInUser) {
		this.loggedInUser = loggedInUser;
		// fetchEmployees();
		layout();
		this.addStyleName("w3-display-topmiddle w3-border");
		this.getElement().getStyle().setMarginTop(20, Unit.PX);
	}

	private void layout() {
		Label lblHeading = new Label("User Edit Form");
		lblHeading.addStyleName("blue");

		lblUserNameError.addStyleName("error");
		lblEmailError.addStyleName("error");
		lblPasswordError.addStyleName("error");

		lblUserNameError.setVisible(false);
		lblEmailError.setVisible(false);
		lblPasswordError.setVisible(false);

		LabelBold lblName = new LabelBold("Name");
		LabelBold lblEmail = new LabelBold("Username (Email)");
		LabelBold lblPassword = new LabelBold("Password");
		LabelBold lblDesignation = new LabelBold("Designation");
		LabelBold lblJoinDate = new LabelBold("Date of Joining");
		LabelBold lblAvailibility = new LabelBold("Availability during the year");
		LabelBold lblUsrProfile = new LabelBold("User Profile");
		LabelBold lblSelectUser = new LabelBold("Select Employee");
		LabelBold lblSkill = new LabelBold("Skill Set");
		
		lblAvailibility.getElement().getStyle().setPaddingTop(5, Unit.PX);
		lblSelectUser.getElement().getStyle().setPaddingTop(5, Unit.PX);
		lblJoinDate.getElement().getStyle().setPaddingTop(5, Unit.PX);
		lblUsrProfile.getElement().getStyle().setPaddingTop(5, Unit.PX);
		lblDesignation.getElement().getStyle().setPaddingTop(5, Unit.PX);
		lblPassword.getElement().getStyle().setPaddingTop(5, Unit.PX);
		lblEmail.getElement().getStyle().setPaddingTop(5, Unit.PX);
		lblName.getElement().getStyle().setPaddingTop(5, Unit.PX);
		lblCompany.getElement().getStyle().setPaddingTop(5, Unit.PX);
		lblSkill.getElement().getStyle().setPaddingTop(5, Unit.PX);

		setWidget(0, 1, lblHeading);
		setWidget(1, 0, lblName);

		// setWidget(2, 0, new Label("Email"));

		setWidget(3, 0, lblEmail);
		setWidget(3, 2, lblUserNameError);
		setWidget(4, 2, lblPasswordError);
		setWidget(2, 2, lblEmailError);
		setWidget(4, 0, lblPassword);

		// setWidget(3, 0, new Label("Division"));
		setWidget(5, 0, lblDesignation);
		setWidget(6, 0, lblUsrProfile);
		setWidget(7, 0, lblReportingTo);
		setWidget(8, 0, lblJoinDate);
		lblAvailibility.setWidth("190px");
		setWidget(9, 0, lblAvailibility);
		setWidget(10, 0, lblSkill);
		setWidget(1, 1, txtName);
		// setWidget(2, 1, txtEmail);
		
		txtName.setWidth("250px");
		txtUserName.setWidth("250px");
		txtPassword.setWidth("250px");
		txtDesignation.setWidth("250px");
		listUserProfile.setWidth("250px");
		listReportingTo.setWidth("250px");
		dateOfJoingin.setWidth("250px");
		listSkillSet.setWidth("250px");
		listCompany.setWidth("250px");
		dateAvailabalityTo.setWidth("80px");
		dateAvailabalityFrom.setWidth("80px");
		
		setWidget(3, 1, txtUserName);
		setWidget(4, 1, txtPassword);
		setWidget(5, 1, txtDesignation);
		setWidget(6, 1, listUserProfile);
		setWidget(7, 1, listReportingTo);
		setWidget(8, 1, dateOfJoingin);
		Label lblTo = new Label(" to ");
		lblTo.getElement().getStyle().setPaddingLeft(10, Unit.PX);
		lblTo.setWidth("40px");
		HorizontalPanel hpnlAvailibilty = new HorizontalPanel();
		hpnlAvailibilty.add(dateAvailabalityFrom);
		hpnlAvailibilty.add(lblTo);
		hpnlAvailibilty.add(dateAvailabalityTo);
		setWidget(9, 1, hpnlAvailibilty);
		setWidget(10, 1, listSkillSet);
		setWidget(11, 0, lblCompany);
		setWidget(11, 1, listCompany);
		setWidget(12, 0, lblSelectUser);
		setWidget(12, 1, listEmployees);
		HorizontalPanel hpnlButtons = new HorizontalPanel();
		hpnlButtons.addStyleName("w3-right");
		hpnlButtons.add(btnCancel);
		hpnlButtons.add(btnSubmit);
		setWidget(13, 1, hpnlButtons);

		lblReportingTo.setVisible(false);
		listReportingTo.setVisible(false);

		if (loggedInUser.getEmployeeName().equalsIgnoreCase("Muhammad Faheem Piracha")
				&& loggedInUser.getEmployeeId() == 1) {
			lblCompany.setVisible(true);
			listCompany.setVisible(true);
		} else {
			lblCompany.setVisible(false);
			listCompany.setVisible(false);
		}

	}

	private void fetchEmployees() {
		rpcService.fetchEmployees(new AsyncCallback<ArrayList<Employee>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("fail fetch Employees");
			}

			@Override
			public void onSuccess(ArrayList<Employee> result) {
				// display.getListReportingTo().clear();
				for (int i = 0; i < result.size(); i++) {
					if (result.get(i).getCompanyId() == loggedInUser.getCompanyId()) {
						// display.getListReportingTo().addItem(result.get(i).getEmployeeName(),
						// result.get(i).getEmployeeId() + "");
						listEmployees.addItem(result.get(i).getEmployeeName(), result.get(i).getEmployeeId() + "");

					}
				}
			}
		});
	}

	public TextBox getTxtName() {
		return txtName;
	}

	public ListBox getListDivision() {
		return listDivision;
	}

	public ListBox getListReportingTo() {
		return listReportingTo;
	}

	public TextBox getTxtDesignation() {
		return txtDesignation;
	}

	public ListBox getListuserProfile() {
		return listUserProfile;
	}

	public DateBox getDateOfJoining() {
		return dateOfJoingin;
	}

	public DateBox getDateAvailabilityForm() {
		return dateAvailabalityFrom;
	}

	public DateBox getDateAvailabalityTo() {
		return dateAvailabalityTo;
	}

	public ListBox getListSkills() {
		return listSkillSet;
	}

	public Button getBtnCancel() {
		return btnCancel;
	}

	public Button getBtnSubmit() {
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

	public ListBox getListEmployees() {
		return listEmployees;
	}

	public void setListEmployees(ListBox listEmployees) {
		this.listEmployees = listEmployees;
	}

}
