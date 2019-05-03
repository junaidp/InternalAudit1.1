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
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;
import com.internalaudit.client.InternalAuditServiceAsync;
import com.internalaudit.client.view.ButtonRound;
import com.internalaudit.client.view.DisplayAlert;
import com.internalaudit.shared.City;
import com.internalaudit.shared.Company;
import com.internalaudit.shared.Country;
import com.internalaudit.shared.Employee;
import com.internalaudit.shared.InternalAuditConstants;
import com.internalaudit.shared.RollsEnum;
import com.internalaudit.shared.Skills;

public class UserInductionFormPresenter implements Presenter

{
	private final InternalAuditServiceAsync rpcService;
	private final HandlerManager eventBus;
	private final Display display;

	private Logger logger = Logger.getLogger("DashBoardPresenter");
	private Employee loggedInUser;

	public interface Display {
		Widget asWidget();

		TextBox getTxtName();

		ListBox getListDivision();

		TextBox getTxtDesignation();

		ListBox getListuserProfile();

		DateBox getDateOfJoining();

		DateBox getDateAvailabilityForm();

		DateBox getDateAvailabalityTo();

		ListBox getListSkills();

		ButtonRound getBtnCancel();

		ButtonRound getBtnSubmit();

		// TextBox getTxtEmail();
		ListBox getListReportingTo();

		TextBox getTxtUserName();

		PasswordTextBox getTxtPassword();

		Label getLblReportingTo();

		Label getLblPasswordError();

		Label getLblUserNameError();

		Label getLblEmailError();

		ListBox getListCompany();
	}

	public UserInductionFormPresenter(InternalAuditServiceAsync rpcService, HandlerManager eventBus,
			Employee loggedInUser, Display view) {
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.display = view;
		this.loggedInUser = loggedInUser;

		fetchSkilss();
		getStartEndDates();
		fetchEmployees();
		fetchCompanies();

		for (RollsEnum roles : RollsEnum.values()) {
			display.getListuserProfile().addItem(roles.getName(), roles.getValue() + "");
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
				display.getListReportingTo().clear();
				for (int i = 0; i < result.size(); i++) {
					if (result.get(i).getCompanyId() == loggedInUser.getCompanyId()) {
						display.getListReportingTo().addItem(result.get(i).getEmployeeName(),
								result.get(i).getEmployeeId() + "");
					}
				}
			}
		});
	}

	private void fetchCompanies() {
		rpcService.fetchCompanies(new AsyncCallback<ArrayList<Company>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("fail fetch Employees");
			}

			@Override
			public void onSuccess(ArrayList<Company> result) {
				for (int i = 0; i < result.size(); i++) {
					display.getListCompany().addItem(result.get(i).getName(), result.get(i).getCompanyId() + "");
				}
			}
		});
	}

	private void getStartEndDates() {
		rpcService.getStartEndDates(new AsyncCallback<ArrayList<Date>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("fetch start/end date failed");
			}

			@Override
			public void onSuccess(ArrayList<Date> result) {
				display.getDateAvailabilityForm().setValue(result.get(0));
				display.getDateAvailabalityTo().setValue(result.get(1));
				display.getDateOfJoining().setValue(result.get(2));
			}
		});
	}

	private void fetchSkilss() {
		rpcService.fetchSkills(new AsyncCallback<ArrayList<Skills>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("fetch Skills Failed");
			}

			@Override
			public void onSuccess(ArrayList<Skills> skills) {

				for (int i = 0; i < skills.size(); i++) {
					display.getListSkills().addItem(skills.get(i).getSkillName(), skills.get(i).getSkillId() + "");
				}
			}
		});
	}

	public void go(HasWidgets container) {
		container.clear();
		container.add(display.asWidget());
		bind();
	}

	private void bind() {

		// display.getListuserProfile().addItem(InternalAuditConstants.AUDITHEAD
		// );
		// display.getListuserProfile().addItem(InternalAuditConstants.TEAMLEAD);
		// display.getListuserProfile().addItem(InternalAuditConstants.AUDITOR);
		// display.getListuserProfile().addItem(InternalAuditConstants.ADMIN);

		display.getListuserProfile().addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				if (Integer.parseInt(
						display.getListuserProfile().getValue(display.getListuserProfile().getSelectedIndex())) == 4
						|| Integer.parseInt(display.getListuserProfile()
								.getValue(display.getListuserProfile().getSelectedIndex())) == 1
						|| Integer.parseInt(display.getListuserProfile()
								.getValue(display.getListuserProfile().getSelectedIndex())) == 5) {
					display.getLblReportingTo().setVisible(false);
					display.getListReportingTo().setVisible(false);
				} else {
					display.getLblReportingTo().setVisible(true);
					display.getListReportingTo().setVisible(true);
				}

			}
		});

		display.getDateAvailabalityTo().setFormat(new DateBox.DefaultFormat(DateTimeFormat.getShortDateFormat()));
		display.getDateAvailabilityForm().setFormat(new DateBox.DefaultFormat(DateTimeFormat.getShortDateFormat()));
		display.getDateOfJoining().setFormat(new DateBox.DefaultFormat(DateTimeFormat.getShortDateFormat()));

		display.getBtnCancel().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				History.newItem("login");
			}
		});

		display.getBtnSubmit().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				display.getLblUserNameError().setVisible(false);
				display.getLblEmailError().setVisible(false);
				display.getLblPasswordError().setVisible(false);

				if (display.getTxtUserName().getText().length() < 1 || display.getTxtUserName().getText().equals("")) {
					display.getLblUserNameError().setVisible(true);
				}
				// if(display.getTxtEmail().getText().length()<1 ||
				// display.getTxtEmail().getText().equals("")){
				// display.getLblEmailError().setVisible(true);
				// }
				if (display.getTxtPassword().getText().length() < 1 || display.getTxtPassword().getText().equals("")) {
					display.getLblPasswordError().setVisible(true);
				}
				Window.alert(display.getTxtPassword().getText());
				Window.alert(display.getTxtUserName().getText());
				if (display.getTxtUserName().getText().length() > 1
						&& display.getTxtPassword().getText().length() > 1) {
					saveUser();

				}

			}
		});

	}

	private void saveUser() {
		final Employee employee = new Employee();
		if (display.getListuserProfile().getValue(display.getListuserProfile().getSelectedIndex())
				.equals(InternalAuditConstants.AUDITHEAD)) {
			employee.setAuditHead(true);

		}
		City city = new City();
		city.setCityId(1);
		Country country = new Country();
		country.setCountryId(1);
		employee.setCityId(city);
		employee.setCountryId(country);
		employee.setPassword(display.getTxtPassword().getText());
		employee.setEmail(display.getTxtUserName().getText());
		employee.setEmployeeName(display.getTxtName().getText());
		if (Integer.parseInt(
				display.getListuserProfile().getValue(display.getListuserProfile().getSelectedIndex())) == 5) {
			employee.setFromInternalAuditDept("no");
		} else {
			employee.setFromInternalAuditDept("yes");
		}

		Employee reportingTo = new Employee();
		if (display.getListReportingTo().isVisible()) {
			reportingTo.setEmployeeId(Integer
					.parseInt(display.getListReportingTo().getValue(display.getListReportingTo().getSelectedIndex())));
		} else {
			reportingTo.setEmployeeId(0);
		}
		employee.setReportingTo(reportingTo);
		Skills skill = new Skills();
		skill.setSkillId(
				Integer.parseInt(display.getListSkills().getValue(display.getListSkills().getSelectedIndex())));
		employee.setSkillId(skill);
		employee.setDateOfJoining(display.getDateOfJoining().getValue());
		employee.setDesignation(display.getTxtDesignation().getText());

		employee.setRollId(Integer.parseInt(display.getListuserProfile().getSelectedValue()));

		// Company company =new Company();
		if (display.getListCompany().isVisible()) {
			// company.setCompanyId(Integer.parseInt(display.getListCompany().getValue(display.getListCompany().getSelectedIndex())));
			employee.setCompanyId(
					Integer.parseInt(display.getListCompany().getValue(display.getListCompany().getSelectedIndex())));
		} else {
			employee.setCompanyId(loggedInUser.getCompanyId());
		}

		Employee user = new Employee();
		// user.setName(display.getTxtUserName().getText());
		user.setPassword(display.getTxtPassword().getText());
		user.setEmail(display.getTxtUserName().getText());

		// employee.setUserId(user);

		rpcService.fetchNumberOfDaysBetweenTwoDates(display.getDateAvailabilityForm().getValue(),
				display.getDateAvailabalityTo().getValue(), new AsyncCallback<Integer>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("fail fetchNumberOfDaysBetweenTwoDates");
					}

					@Override
					public void onSuccess(Integer days) {
						int numberOfHours = days * 8;
						saveUserToDb(employee, numberOfHours);
					}
				});

	}

	private void saveUserToDb(Employee employee, int numberOfHours) {
		employee.setTotalNumberOfHoursAvailable(numberOfHours);
		rpcService.saveUser(employee, new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("fail save user");
			}

			@Override
			public void onSuccess(String result) {
				if (result.equalsIgnoreCase(InternalAuditConstants.INVALIDEMAIL)) {
					display.getLblUserNameError().setText(InternalAuditConstants.INVALIDEMAIL);
					display.getLblUserNameError().setVisible(true);
				} else if (result.equalsIgnoreCase(InternalAuditConstants.USERNOTAVAILABLE)) {
					display.getLblUserNameError().setText(InternalAuditConstants.USERNOTAVAILABLE);
					display.getLblUserNameError().setVisible(true);
				} else {
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
		// display.getTxtEmail().setText("");
		display.getTxtName().setText("");
		display.getTxtPassword().setText("");
		display.getTxtUserName().setText("");
		display.getListSkills().setSelectedIndex(0);
		display.getListCompany().setSelectedIndex(0);
	}

}
