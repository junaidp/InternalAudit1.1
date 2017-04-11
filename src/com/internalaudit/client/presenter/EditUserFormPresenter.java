package com.internalaudit.client.presenter;

import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Logger;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;
import com.internalaudit.client.InternalAuditServiceAsync;
import com.internalaudit.client.view.DisplayAlert;
import com.internalaudit.shared.City;
import com.internalaudit.shared.Company;
import com.internalaudit.shared.Country;
import com.internalaudit.shared.Employee;
import com.internalaudit.shared.InternalAuditConstants;
import com.internalaudit.shared.Rolls;
import com.internalaudit.shared.Skills;
import com.internalaudit.shared.User;


public class EditUserFormPresenter implements Presenter 

{
	private final InternalAuditServiceAsync rpcService;
	private final HandlerManager eventBus;
	private final Display display;

	private Logger logger = Logger.getLogger("DashBoardPresenter");
	private User loggedInUser;
	private int previousNumberOfHours = 0;
	Employee selectedEmployee;
	public interface Display 
	{
		Widget asWidget();
		TextBox getTxtName();
		ListBox getListDivision();
		TextBox getTxtDesignation();
		ListBox getListuserProfile();
		DateBox getDateOfJoining();
		DateBox getDateAvailabilityForm();
		DateBox getDateAvailabalityTo();
		ListBox getListSkills();
		Button  getBtnCancel();
		Button  getBtnSubmit();
		//		TextBox getTxtEmail();
		ListBox getListReportingTo();
		TextBox getTxtUserName();
		PasswordTextBox getTxtPassword();
		Label getLblReportingTo();
		Label getLblPasswordError();
		Label getLblUserNameError();
		Label getLblEmailError();
		ListBox getListCompany() ;
		ListBox getListEmployees();
	}  

	public EditUserFormPresenter(InternalAuditServiceAsync rpcService, HandlerManager eventBus, User loggedInUser, Display view) 
	{
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.display = view;
		this.loggedInUser = loggedInUser;

		fetchSkilss();
		getStartEndDates();
		fetchEmployees();
		fetchCompanies();
		fetchRolls();
	}

	private void fetchEmployees(){
		rpcService.fetchEmployees(new AsyncCallback<ArrayList<Employee>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("fail fetch Employees");
			}

			@Override
			public void onSuccess(ArrayList<Employee> result) {
				display.getListReportingTo().clear();
				for(int i=0; i< result.size(); i++){
					if(result.get(i).getCompanyId() == loggedInUser.getEmployeeId().getCompanyId()){
						display.getListReportingTo().addItem(result.get(i).getEmployeeName(), result.get(i).getEmployeeId()+"");
						display.getListEmployees().addItem(result.get(i).getEmployeeName(), result.get(i).getEmployeeId()+"");
						
					}
				}
			}
		});
	}

	private void fetchCompanies(){
		rpcService.fetchCompanies(new AsyncCallback<ArrayList<Company>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("fail fetch Employees");
			}

			@Override
			public void onSuccess(ArrayList<Company> result) {
				for(int i=0; i< result.size(); i++){
					display.getListCompany().addItem(result.get(i).getName(), result.get(i).getCompanyId()+"");
				}
			}
		});
	}

	private void getStartEndDates(){
		rpcService.getStartEndDates(new AsyncCallback<ArrayList<Date>>(){

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("fetch start/end date failed");
			}

			@Override
			public void onSuccess(ArrayList<Date> result) {
				display.getDateAvailabilityForm().setValue(result.get(0));
				display.getDateAvailabalityTo().setValue(result.get(1));
				display.getDateOfJoining().setValue(result.get(2));
			}});
	}

	private void fetchSkilss() {
		rpcService.fetchSkills(new AsyncCallback<ArrayList<Skills>>(){

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("fetch Skills Failed");	
			}

			@Override
			public void onSuccess(ArrayList<Skills> skills) {

				for(int i=0; i<skills.size(); i++){
					display.getListSkills().addItem(skills.get(i).getSkillName(), skills.get(i).getSkillId()+"");
				}
			}});
	}

	private void fetchRolls() {
		rpcService.fetchRolls(new AsyncCallback<ArrayList<Rolls>>(){

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("fetch Skills Failed");	
			}

			@Override
			public void onSuccess(ArrayList<Rolls> rolls) {

				for(int i=0; i<rolls.size(); i++){
					display.getListuserProfile().addItem(rolls.get(i).getRollName(), rolls.get(i).getRollId()+"");
				}
			}});
	}

	public void go(HasWidgets container) 
	{
		container.clear();
		container.add(display.asWidget());
		bind();
	}

	private void bind() {

		//		display.getListuserProfile().addItem(InternalAuditConstants.AUDITHEAD );
		//		display.getListuserProfile().addItem(InternalAuditConstants.TEAMLEAD);
		//		display.getListuserProfile().addItem(InternalAuditConstants.AUDITOR);
		//		display.getListuserProfile().addItem(InternalAuditConstants.ADMIN);
		
		display.getListEmployees().addChangeHandler(new ChangeHandler(){

			@Override
			public void onChange(ChangeEvent arg0) {
			
				fetchSelectedEmployee(Integer.parseInt(display.getListEmployees().getValue(display.getListEmployees().getSelectedIndex())));
			}}

			);


		display.getListuserProfile().addChangeHandler(new ChangeHandler(){

			@Override
			public void onChange(ChangeEvent event) {
				if( Integer.parseInt(display.getListuserProfile().getValue(display.getListuserProfile().getSelectedIndex())) == 4 || 
						Integer.parseInt(display.getListuserProfile().getValue(display.getListuserProfile().getSelectedIndex()))== 1
						|| Integer.parseInt(display.getListuserProfile().getValue(display.getListuserProfile().getSelectedIndex()))== 5){
					display.getLblReportingTo().setVisible(false);
					display.getListReportingTo().setVisible(false);
				}else{
					display.getLblReportingTo().setVisible(true);
					display.getListReportingTo().setVisible(true);
				}

			}});

		display.getDateAvailabalityTo().setFormat(new DateBox.DefaultFormat(
				DateTimeFormat.getShortDateFormat()));
		display.getDateAvailabilityForm().setFormat(new DateBox.DefaultFormat(
				DateTimeFormat.getShortDateFormat()));
		display.getDateOfJoining().setFormat(new DateBox.DefaultFormat(
				DateTimeFormat.getShortDateFormat()));

		display.getBtnCancel().addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				History.newItem("login");
			}});

		display.getBtnSubmit().addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {

				display.getLblUserNameError().setVisible(false);
				display.getLblEmailError().setVisible(false);
				display.getLblPasswordError().setVisible(false);

				if(display.getTxtUserName().getText().length()<1 || display.getTxtUserName().getText().equals("")){
					display.getLblUserNameError().setVisible(true);
				}
				//				if(display.getTxtEmail().getText().length()<1 || display.getTxtEmail().getText().equals("")){
				//					display.getLblEmailError().setVisible(true);
				//				}
				if(display.getTxtPassword().getText().length()<1 || display.getTxtPassword().getText().equals("")){
					display.getLblPasswordError().setVisible(true);
				}

				if(display.getTxtUserName().getText().length()>1 && display.getTxtPassword().getText().length()>1){
				updateUserToDb(previousNumberOfHours);

				}

			}});

	}

	private void fetchSelectedEmployee(int selectedUserId) {
		
		rpcService.fetchSelectedEmployee(selectedUserId, new AsyncCallback<Employee>(){

			@Override
			public void onFailure(Throwable arg0) {
				Window.alert("fail:fetchSelectedEmployee");
			}

			@Override
			public void onSuccess(Employee employee) {
				displayUser(employee);
			}

			});
	}

	private void displayUser(final Employee employee) {
		

		rpcService.fetchNumberOfDaysBetweenTwoDates(display.getDateAvailabilityForm().getValue(), display.getDateAvailabalityTo().getValue(), new AsyncCallback<Integer>(){

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("fail fetchNumberOfDaysBetweenTwoDates");
			}

			@Override
			public void onSuccess(Integer days) {
				 previousNumberOfHours = days * 8;
				
			}

			});
		
	}
	
	private void displayUser(){
		
		display.getDateOfJoining().setValue(selectedEmployee.getDateOfJoining());
//		display.getListCompany().
		
		
		rpcService.fetchNumberOfDaysBetweenTwoDates(display.getDateAvailabilityForm().getValue(), display.getDateAvailabalityTo().getValue(), new AsyncCallback<Integer>(){

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("fail fetchNumberOfDaysBetweenTwoDates");
			}

			@Override
			public void onSuccess(Integer days) {
				int numberOfHours = days * 8;
				updateUserToDb(numberOfHours);
			}});



	}

	private void updateUserToDb(int numberOfHours) {
		
		if(display.getListuserProfile().getValue(display.getListuserProfile().getSelectedIndex()).equals(InternalAuditConstants.AUDITHEAD)){
				selectedEmployee.setAuditHead(true);

			}
			City city = new City();
			city.setCityId(1);
			Country country = new Country();
			country.setCountryId(1);
			selectedEmployee.setCityId(city);
			selectedEmployee.setCountryId(country);
			selectedEmployee.setEmail(display.getTxtUserName().getText());
			selectedEmployee.setEmployeeName(display.getTxtName().getText());
			if(Integer.parseInt(display.getListuserProfile().getValue(display.getListuserProfile().getSelectedIndex()))== 5){
				selectedEmployee.setFromInternalAuditDept("no");
			}else{
				selectedEmployee.setFromInternalAuditDept("yes");
			}

			Employee reportingTo = new Employee();
			if(display.getListReportingTo().isVisible()){
				reportingTo.setEmployeeId(Integer.parseInt(display.getListReportingTo().getValue(display.getListReportingTo().getSelectedIndex())));
			}else{
				reportingTo.setEmployeeId(0);
			}
			selectedEmployee.setReportingTo(reportingTo);
			Skills skill = new Skills();
			skill.setSkillId(Integer.parseInt(display.getListSkills().getValue(display.getListSkills().getSelectedIndex())));
			selectedEmployee.setSkillId(skill);
			selectedEmployee.setDateOfJoining(display.getDateOfJoining().getValue());
			selectedEmployee.setDesignation(display.getTxtDesignation().getText());
			Rolls role = new Rolls();
			role.setRollId(Integer.parseInt(display.getListuserProfile().getValue(display.getListuserProfile().getSelectedIndex())));
			selectedEmployee.setRollId(role);
			//		Company company =new Company();
			if(display.getListCompany().isVisible()){
				//		company.setCompanyId(Integer.parseInt(display.getListCompany().getValue(display.getListCompany().getSelectedIndex())));
				selectedEmployee.setCompanyId(Integer.parseInt(display.getListCompany().getValue(display.getListCompany().getSelectedIndex())));
			}else{
				selectedEmployee.setCompanyId(loggedInUser.getEmployeeId().getCompanyId());
			}

			selectedEmployee.getUserId().setName(display.getTxtUserName().getText());
			selectedEmployee.getUserId().setPassword(display.getTxtPassword().getText());
			selectedEmployee.getUserId().setEmail(display.getTxtUserName().getText());
		
		
		
		rpcService.updateUser(previousNumberOfHours, selectedEmployee, new AsyncCallback<String>(){

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("fail update user");
			}

			@Override
			public void onSuccess(String result) {
				if(result.equalsIgnoreCase(InternalAuditConstants.INVALIDEMAIL)){
					display.getLblUserNameError().setText(InternalAuditConstants.INVALIDEMAIL);
					display.getLblUserNameError().setVisible(true);
				}else if(result.equalsIgnoreCase(InternalAuditConstants.USERNOTAVAILABLE)){
					display.getLblUserNameError().setText(InternalAuditConstants.USERNOTAVAILABLE);
					display.getLblUserNameError().setVisible(true);
				}else {
					display.getLblUserNameError().setVisible(false);
					new DisplayAlert(result);
					clearFields();
					fetchEmployees();
				}
			}

		});
	}

	private void clearFields() {
		display.getTxtDesignation().setText("");
		//		display.getTxtEmail().setText("");
		display.getTxtName().setText("");
		display.getTxtPassword().setText("");
		display.getTxtUserName().setText("");
		display.getListSkills().setSelectedIndex(0);
		display.getListCompany().setSelectedIndex(0);
	}


}


