package com.internalaudit.client.view;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;
import com.internalaudit.client.presenter.UserInductionFormPresenter.Display;
import com.internalaudit.client.view.AuditEngagement.LabelBold;
import com.internalaudit.client.view.AuditEngagement.LabelBold;
import com.internalaudit.shared.Employee;

public class UserInductionFormView extends FlexTable implements Display {

	private TextBox txtName = new TextBox();
	private ListBox listDivision = new ListBox();
	private TextBox txtDesignation = new TextBox();
	private ListBox listUserProfile = new ListBox();
	private DateBox dateOfJoining = new DateBox();
	private DateBox dateAvailabalityFrom = new DateBox();
	private DateBox dateAvailabalityTo = new DateBox();
	private ListBox listSkillSet = new ListBox();
	private Button btnCancel = new Button("Back");
	private Button btnEdit = new Button("Edit");
	private Button btnSubmit = new Button("Save");
	// private TextBox txtEmail = new TextBox();
	private ListBox listReportingTo = new ListBox();
	private LabelBold lblReportingTo = new LabelBold("Reporting to");
	private LabelBold lblfiscalYear = new LabelBold("Fiscal Year");
	private DateBox datefiscalYearfrom = new DateBox();
	private DateBox datefiscalYearto = new DateBox();

	private LabelBold lblCompany = new LabelBold("Company");
	private ListBox listCompany = new ListBox();
	private TextBox txtUserName = new TextBox();
	private PasswordTextBox txtPassword = new PasswordTextBox();
	private Employee loggedInUser;
	private Label lblUserNameError = new Label("Please enter valid Email Address");
	private Label lblPasswordError = new Label("Password cannot be empty");
	private Label lblEmailError = new Label("Email cannot be empty");
	private LabelBold lblSkillSet = new LabelBold("Skill Set");
	private TextBox textareaOthers = new TextBox();
//	private VerticalPanel p = new VerticalPanel();

	public UserInductionFormView(Employee loggedInUser) {
		this.loggedInUser = loggedInUser;
		layout();
		this.addStyleName("w3-display-topmiddle w3-border");
		this.getElement().getStyle().setMarginTop(20, Unit.PX);
	}

	HorizontalPanel c = new HorizontalPanel();
	{
		c.add(listSkillSet);
		c.setSpacing(8);
		c.add(textareaOthers);
	}
	
	private Widget layoutButtons() {
		HorizontalPanel hpnlButtons = new HorizontalPanel();
		hpnlButtons.add(btnCancel);
		hpnlButtons.add(btnSubmit);
		hpnlButtons.add(btnEdit);
		hpnlButtons.addStyleName("w3-right");
		return hpnlButtons;
	}

	private void layout() {
		// datefiscalYearfrom.setFormat(new
		// DateBox.DefaultFormat(DateTimeFormat.getFormat("dd MMMM , yyyy")));
		// datefiscalYearto.setFormat(new
		// DateBox.DefaultFormat(DateTimeFormat.getFormat("dd MMMM , yyyy")));
		datefiscalYearfrom.setFormat(new DateBox.DefaultFormat(DateTimeFormat.getShortDateFormat()));
		datefiscalYearto.setFormat(new DateBox.DefaultFormat(DateTimeFormat.getShortDateFormat()));
		dateAvailabalityFrom.getElement().setPropertyString("placeholder", "yyyy/mm/dd");
		dateAvailabalityTo.getElement().setPropertyString("placeholder", "yyyy/mm/dd");
		datefiscalYearto.getElement().setPropertyString("placeholder", "yyyy/mm/dd");
		datefiscalYearfrom.getElement().setPropertyString("placeholder", "yyyy/mm/dd");
		dateOfJoining.getElement().setPropertyString("placeholder", "yyyy/mm/dd");
		dateAvailabalityFrom.setWidth("80px");
		datefiscalYearto.setWidth("80px");
		datefiscalYearfrom.setWidth("80px");
		dateAvailabalityTo.setWidth("80px");

		Label lblHeading = new Label("User Induction Form");
		lblHeading.addStyleName("blue");

		lblUserNameError.addStyleName("error");
		lblEmailError.addStyleName("error");
		lblPasswordError.addStyleName("error");

		lblUserNameError.setVisible(false);
		lblEmailError.setVisible(false);
		lblPasswordError.setVisible(false);
		textareaOthers.setVisible(false);
		setWidget(0, 1, lblHeading);
		LabelBold lblName = new LabelBold("Name");
		lblName.setWidth("190px");
		setWidget(1, 0, lblName);
		// setWidget(2, 0, new Label("Email"));

		LabelBold lblEmail = new LabelBold("Username (Email)");
		LabelBold lblPassword = new LabelBold("Password");
		
		lblEmail.setWidth("190px");
		lblPassword.setWidth("190px");

		setWidget(3, 0, lblEmail);
		setWidget(3, 2, lblUserNameError);
		setWidget(4, 2, lblPasswordError);
		setWidget(2, 2, lblEmailError);
		setWidget(4, 0, lblPassword);

		// setWidget(3, 0, new Label("Division"));
		LabelBold lblDesignation = new LabelBold("Designation");
		LabelBold lblJoinDate = new LabelBold("Date of Joining");
		LabelBold lblAvailibility = new LabelBold("Availability during the year");
		LabelBold lblUsrProfile = new LabelBold("User Profile");
		
		lblDesignation.setWidth("190px");
		lblUsrProfile.setWidth("190px");
		lblJoinDate.setWidth("190px");
		lblAvailibility.setWidth("190px");
		
		setWidget(5, 0, lblDesignation);
		setWidget(6, 0, lblUsrProfile);
		setWidget(7, 0, lblReportingTo);
		setWidget(8, 0, lblJoinDate);
		setWidget(9, 0, lblAvailibility);
		setWidget(10, 0, lblSkillSet);
		setWidget(1, 1, txtName);
		txtName.setWidth("300px");
		txtPassword.setWidth("300px");
		txtUserName.setWidth("300px");
		txtDesignation.setWidth("300px");
		listCompany.setWidth("300px");
		listDivision.setWidth("300px");
		listReportingTo.setWidth("300px");
		listSkillSet.setWidth("300px");
		listUserProfile.setWidth("300px");
		dateOfJoining.setWidth("300px");
		
		// setWidget(2, 1, txtEmail);
		setWidget(3, 1, txtUserName);
		setWidget(4, 1, txtPassword);
		setWidget(5, 1, txtDesignation);
		setWidget(6, 1, listUserProfile);
		setWidget(7, 1, listReportingTo);
		setWidget(8, 1, dateOfJoining);
		Label lblTo = new Label(" to ");
		lblTo.getElement().getStyle().setPaddingLeft(10, Unit.PX);
		lblTo.setWidth("40px");
		HorizontalPanel hpnlAvailibilty = new HorizontalPanel();
		Label lblFrom = new Label(" from ");
		lblFrom.getElement().getStyle().setPaddingLeft(10, Unit.PX);
		lblFrom.setWidth("60px");
		//hpnlAvailibilty.add(lblFrom);
		hpnlAvailibilty.add(dateAvailabalityFrom);
		hpnlAvailibilty.add(lblTo);
		hpnlAvailibilty.add(dateAvailabalityTo);
		setWidget(9, 1, hpnlAvailibilty);
		setWidget(10, 1, c);
		setWidget(11, 0, lblCompany);
		setWidget(11, 1, listCompany);
		setWidget(12, 0, lblfiscalYear);
		HorizontalPanel hpnlFiscalYear = new HorizontalPanel();
		Label lblTo1 = new Label(" to ");
		lblTo1.getElement().getStyle().setPaddingLeft(10, Unit.PX);
		lblTo1.setWidth("40px");
		Label lblFrom1 = new Label(" from ");
		lblFrom1.getElement().getStyle().setPaddingLeft(10, Unit.PX);
		lblFrom1.setWidth("60px");
		//hpnlFiscalYear.add(lblFrom1);
		hpnlFiscalYear.add(datefiscalYearfrom);
		hpnlFiscalYear.add(lblTo1);
		hpnlFiscalYear.add(datefiscalYearto);
		setWidget(12, 1, hpnlFiscalYear);
		setWidget(13, 1, layoutButtons());
//		setWidget(13, 1, btnCancel);
//		setWidget(13, 1, btnSubmit);
//		setWidget(13, 1, btnEdit);
//		setWidget(14, 2, p);
		
		lblAvailibility.getElement().getStyle().setPaddingTop(5, Unit.PX);
		lblfiscalYear.getElement().getStyle().setPaddingTop(5, Unit.PX);
		lblJoinDate.getElement().getStyle().setPaddingTop(5, Unit.PX);
		lblUsrProfile.getElement().getStyle().setPaddingTop(5, Unit.PX);
		lblDesignation.getElement().getStyle().setPaddingTop(5, Unit.PX);
		lblPassword.getElement().getStyle().setPaddingTop(5, Unit.PX);
		lblEmail.getElement().getStyle().setPaddingTop(5, Unit.PX);
		lblName.getElement().getStyle().setPaddingTop(5, Unit.PX);
		lblCompany.getElement().getStyle().setPaddingTop(5, Unit.PX);
		lblSkillSet.getElement().getStyle().setPaddingTop(5, Unit.PX);

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
		btnEdit.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// EditUserFormView b = new EditUserFormView(loggedInUser);
				History.newItem("editUser");
				// p.add(b);

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
		return dateOfJoining;
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

	public void setLblReportingTo(LabelBold lblReportingTo) {
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

	public Label getSkillSet() {
		return lblSkillSet;
	}

	public void setSkillSet(LabelBold skillSet) {
		this.lblSkillSet = skillSet;
	}

	public Label getLblfiscalYear() {
		return lblfiscalYear;
	}

	public void setLblfiscalYear(LabelBold lblfiscalYear) {
		this.lblfiscalYear = lblfiscalYear;
	}

	public DateBox getDatefiscalYearfrom() {
		return datefiscalYearfrom;
	}

	public void setDatefiscalYearfrom(DateBox datefiscalYearfrom) {
		this.datefiscalYearfrom = datefiscalYearfrom;
	}

	public DateBox getDatefiscalYearto() {
		return datefiscalYearto;
	}

	public void setDatefiscalYearto(DateBox datefiscalYearto) {
		this.datefiscalYearto = datefiscalYearto;
	}

	public TextBox getTextareaOthers() {
		return textareaOthers;
	}

	public void setTextareaOthers(TextBox textareaOthers) {
		this.textareaOthers = textareaOthers;
	}

}
