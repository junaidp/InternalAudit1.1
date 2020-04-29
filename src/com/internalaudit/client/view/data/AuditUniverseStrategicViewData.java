package com.internalaudit.client.view.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratedPopupPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.internalaudit.client.InternalAuditService;
import com.internalaudit.client.InternalAuditServiceAsync;
import com.internalaudit.client.view.AmendmentPopup;
import com.internalaudit.client.view.AuditUniverseStrategicView;
import com.internalaudit.client.view.ButtonRound;
import com.internalaudit.client.view.LoadingPopup;
import com.internalaudit.client.view.PhaseNames;
import com.internalaudit.client.view.PopupViewGXT;
import com.internalaudit.shared.Department;
import com.internalaudit.shared.Employee;
import com.internalaudit.shared.RiskFactor;
import com.internalaudit.shared.Strategic;
import com.internalaudit.shared.StrategicDepartments;
import com.internalaudit.shared.TimeOutException;

public class AuditUniverseStrategicViewData {

	private InternalAuditServiceAsync rpcService = GWT.create(InternalAuditService.class);
	// private AuditUniverseStrategicView auditUniverseStrategicView;
	private ArrayList<Employee> objectiveOwners = new ArrayList<Employee>();
	private ArrayList<Department> departments = new ArrayList<Department>();
	private String actionperformed;
	private Logger logger = Logger.getLogger("AuditUniverStrategicViewData");

	public void setData(AuditUniverseStrategicView auditUniverseStrategicView) {
		// this.auditUniverseStrategicView = auditUniverseStrategicView;
		fetchObjectiveOwners();
		fetchDepartments();
		// setHandlers();
		//
	}

	public void declineStrategic(int strategicId, final VerticalPanel vpnlStrategic,
			final HorizontalPanel hpnlButtonInitiator, final HorizontalPanel hpnlButtonsApprovar, final Image btnAdd,
			final int tab, final ButtonRound buttonRound) {

		buttonRound.setEnabled(false);
		rpcService.declineStrategic(strategicId, new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable caught) {

				logger.log(Level.INFO, "FAIL: declineStrategic .Inside Audit AuditAreaspresenter");
				if (caught instanceof TimeOutException) {
					History.newItem("login");
				} else {
					System.out.println("FAIL: declineStrategic .Inside AuditAreaspresenter");
					Window.alert("FAIL: declineStrategic");// After FAIL ...
															// write RPC Name
															// NOT Method Name..
				}

				buttonRound.setEnabled(true);
				Window.alert("decline strategic failed");
			}

			@Override
			public void onSuccess(String result) {
				buttonRound.setEnabled(true);
				vpnlStrategic.clear();

				fetchStrategic(vpnlStrategic, hpnlButtonInitiator, hpnlButtonsApprovar, btnAdd, tab);

			}
		});
	}

	public void saveStrategic(final AuditUniverseStrategicView strategicView, final VerticalPanel vpnlStrategicData,
			final HorizontalPanel hpnlButtonInitiator, final HorizontalPanel hpnlButtonsApprovar, final Image btnAdd,
			String todo, int tab, ButtonRound buttonRound) {
		final ArrayList<Strategic> strategics = new ArrayList<Strategic>();

		if (strategicView.getStrategicObjective().getText().equals("")
				|| strategicView.getStrategicObjective().getText().equals("Enter Objective")) {
			Window.alert("Objective name required");
		} else {
			// TEST CheckDate removed by faheem
			// checkDate(strategicView, vpnlStrategicData,hpnlButtonInitiator,
			// hpnlButtonsApprovar, btnAdd, todo,
			// strategics, tab, buttonClicked);
			saveStrategicToServer(strategicView, vpnlStrategicData, hpnlButtonInitiator, hpnlButtonsApprovar, btnAdd,
					todo, strategics, tab, buttonRound);

		}
	}

	public void checkDate(final AuditUniverseStrategicView strategicView, final VerticalPanel vpnlStrategicData,
			final HorizontalPanel hpnlButtonInitiator, final HorizontalPanel hpnlButtonsApprovar, final Image btnAdd,
			final String todo, final ArrayList<Strategic> strategics, final int tab, final ButtonRound buttonClicked) {
		rpcService.checkDate(strategicView.getObjectiveAchievementDate().getValue(), new AsyncCallback<Boolean>() {

			@Override
			public void onFailure(Throwable caught) {

				logger.log(Level.INFO, "FAIL: checkDate .Inside Audit AuditAreaspresenter");
				if (caught instanceof TimeOutException) {
					History.newItem("login");
				} else {
					System.out.println("FAIL: checkDate .Inside AuditAreaspresenter");
					Window.alert("FAIL: checkDate");// After FAIL ... write RPC
													// Name NOT Method Name..
				}

			}

			@Override
			public void onSuccess(Boolean dateValidt) {
				if (dateValidt) {
					saveStrategicToServer(strategicView, vpnlStrategicData, hpnlButtonInitiator, hpnlButtonsApprovar,
							btnAdd, todo, strategics, tab, buttonClicked);
				} else {
					Window.alert("Date not valid");
				}
			}
		});

	}

	private void saveStrategicToServer(final AuditUniverseStrategicView strategicView,
			final VerticalPanel vpnlStrategicData, final HorizontalPanel hpnlButtonInitiator,
			final HorizontalPanel hpnlButtonsApprovar, final Image btnAdd, String todo,
			final ArrayList<Strategic> strategics, final int tab, final ButtonRound buttonRound) {

		buttonRound.setEnabled(false);
		Strategic strategic = new Strategic();
		strategic.setAcheivementDate(strategicView.getObjectiveAchievementDate().getCurrentValue());
		Employee employee = new Employee();

		// employee.setEmployeeId(Integer.parseInt(strategicView.getLstObjectiveOwner().getValue(strategicView.getLstObjectiveOwner().getSelectedIndex())));
		// TEST... objective owner is no more reuiqred as per faheem
		// employee.setEmployeeId(1);
		//
		// strategic.setObjectiveOwner(employee);
		RiskFactor risk = new RiskFactor();
		risk.setRiskId(1);
		strategic.setId(strategicView.getStrategicId());
		strategic.setRiskFactor(risk);
		strategic.setRating("N/A");// changed low to N/A by Moqeet
		Department department = new Department();
		// department.setDepartmentId(Integer.parseInt(strategicView.getRelevantDepartment().getValue(strategicView.getRelevantDepartment().getSelectedIndex())));
		//////
		setDepartments(strategicView.getRelevantDepartment(), strategic);
		strategic.setRelevantDepartment(department);
		strategic.setStrategicObjective(strategicView.getStrategicObjective().getText());
		// strategic.setPhase("Identification");
		strategic.setPhase(1);
		strategic.setNextPhase(2);
		// strategic.setNextPhase("RiskAssesment");
		strategic.setComments(strategicView.getComment());
		// strategic.setRatingComments();
		// strategics.add(strategic);

		actionperformed = todo;
		HashMap<String, String> hm = new HashMap<String, String>();
		if (todo.equalsIgnoreCase("approve")) {
			todo = "submit";
		}
		hm.put("todo", todo);
		hm.put("tab", tab + "");
		rpcService.saveStrategic(strategic, hm, new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable caught) {

				//
				logger.log(Level.INFO, "FAIL: saveStrategic .Inside Audit AuditAreaspresenter");
				if (caught instanceof TimeOutException) {
					History.newItem("login");
				} else {
					System.out.println("FAIL: saveStrategic .Inside AuditAreaspresenter");
					Window.alert("FAIL: saveStrategic");// After FAIL ... write
														// RPC Name NOT Method
														// Name..
				}

				buttonRound.setEnabled(true);
				Window.alert("save strategic failed");
			}

			@Override
			public void onSuccess(String arg0) {
				buttonRound.setEnabled(true);
				vpnlStrategicData.clear();
				final DecoratedPopupPanel popup = new DecoratedPopupPanel();
				if (actionperformed.equalsIgnoreCase("save")) {
					popup.setWidget(new Label("Identification Saved"));

				} else if (actionperformed.equalsIgnoreCase("approve")) {
					popup.setWidget(new Label("Identification Approved "));

				} else if (actionperformed.equalsIgnoreCase("amend")) {
					popup.setWidget(new Label("Feedback Submitted "));

				} else {
					popup.setWidget(new Label("Identification Submitted ! "));
				}
				popup.setPopupPosition(350, 350);
				popup.show();
				Timer time = new Timer() {
					public void run() {
						popup.removeFromParent();
					}

				};// timer for showing the popup of "update"
				time.schedule(1500);

				// for(int i=0; i< strategicList.size(); i++){
				fetchStrategic(vpnlStrategicData, hpnlButtonInitiator, hpnlButtonsApprovar, btnAdd, tab);
				// }

			}
		});
	}

	private void setDepartments(ListBox relevantDepartment, Strategic strategic) {
		ArrayList<StrategicDepartments> strategicDepartments = new ArrayList<StrategicDepartments>();
		for (int i = 0; i < relevantDepartment.getItemCount(); i++) {
			if (relevantDepartment.isItemSelected(i)) {
				StrategicDepartments strategicDepartment = new StrategicDepartments();
				Department department = new Department();
				department.setDepartmentId(Integer.parseInt(relevantDepartment.getValue(i)));
				strategicDepartment.setDepartment(department);
				strategicDepartments.add(strategicDepartment);
			}
		}
		strategic.setStrategicDepartments(strategicDepartments);

	}

	public void fetchObjectiveOwners() {
		final LoadingPopup loadingPopup = new LoadingPopup();
		loadingPopup.display();
		rpcService.fetchObjectiveOwners(new AsyncCallback<ArrayList<Employee>>() {

			public void onFailure(Throwable caught) {
				///// These are the lines we need to paste in every method's
				///// onFailure
				/// From here
				logger.log(Level.INFO, "FAIL: fetchObjectiveOwners .Inside Audit UniverseStrategicViewData");
				// please change the class name , method's name accordingly in
				// other methods//
				if (caught instanceof TimeOutException) {
					History.newItem("login");
				} else {
					System.out.println("FAIL: fetchObjectiveOwners .Inside AuditUniverseStrategicViewData");
					Window.alert("FAIL: fetchObjectiveOwners");// After FAIL ...
																// write RPC
																// Name NOT
																// Method Name..
					//// to here..
				}
			}

			public void onSuccess(ArrayList<Employee> employees) {
				objectiveOwners = employees;
				loadingPopup.remove();
			}
		});
	}

	public void fetchDepartmentsForNewRecord(final AuditUniverseStrategicView auditUniverseStrategicView) {

		rpcService.fetchDepartments(new AsyncCallback<ArrayList<Department>>() {

			@Override
			public void onFailure(Throwable arg0) {
				//
			}

			@Override
			public void onSuccess(ArrayList<Department> department) {
				departments = department;
				if (auditUniverseStrategicView != null) {
					for (int i = 0; i < department.size(); i++) {
						auditUniverseStrategicView.getRelevantDepartment().addItem(
								department.get(i).getDepartmentName(), department.get(i).getDepartmentId() + "");
					}
					if (auditUniverseStrategicView.getRelevantDepartment().getSelectedIndex() == -1) {
						auditUniverseStrategicView.getRelevantDepartment().setSelectedIndex(0);
					}
				}
			}
		});
	}

	public void fetchObjectiveOwnersForNewRecord(final AuditUniverseStrategicView auditUniverseStrategicView) {
		final LoadingPopup loadingPopup = new LoadingPopup();
		loadingPopup.display();
		rpcService.fetchObjectiveOwners(new AsyncCallback<ArrayList<Employee>>() {

			@Override
			public void onFailure(Throwable caught) {
				loadingPopup.remove();
				logger.log(Level.INFO, "FAIL: fetchObjectiveOwners .Inside Audit AuditAreaspresenter");
				if (caught instanceof TimeOutException) {
					History.newItem("login");
				} else {
					System.out.println("FAIL: fetchObjectiveOwners .Inside AuditAreaspresenter");
					Window.alert("FAIL: fetchObjectiveOwners");// After FAIL ...
																// write RPC
																// Name NOT
																// Method Name..
				}

			}

			@Override
			public void onSuccess(ArrayList<Employee> employees) {
				objectiveOwners = employees;
				if (auditUniverseStrategicView != null) {
					for (int i = 0; i < employees.size(); i++) {
						if (!employees.get(i).getEmployeeName().equals("no user")) {
							auditUniverseStrategicView.getLstObjectiveOwner()
									.addItem(employees.get(i).getEmployeeName(), employees.get(i).getEmployeeId() + "");
						}
					}
				}
				loadingPopup.remove();
			}
		});
	}

	public void fetchDepartments() {

		rpcService.fetchDepartments(new AsyncCallback<ArrayList<Department>>() {

			@Override
			public void onFailure(Throwable caught) {
				//
				logger.log(Level.INFO, "FAIL: fetchDepartments .Inside Audit AuditAreaspresenter");
				if (caught instanceof TimeOutException) {
					History.newItem("login");
				} else {
					System.out.println("FAIL: fetchDepartments .Inside AuditAreaspresenter");
					Window.alert("FAIL: fetchDepartments");// After FAIL ...
															// write RPC Name
															// NOT Method Name..
				}
			}

			@Override
			public void onSuccess(ArrayList<Department> department) {
				departments = department;
				// if(auditUniverseStrategicView!=null){
				// for(int i=0; i< department.size(); i++){
				// auditUniverseStrategicView.getRelevantDepartment().addItem(department.get(i).getDepartmentName(),
				// department.get(i).getDepartmentId()+"");
				// }
				// }
			}
		});
	}

	public void fetchStrategic(final VerticalPanel vpnlStrategic, final HorizontalPanel hpnlButtonInitiator,
			final HorizontalPanel hpnlButtonsApprovar, final Image btnAdd, int tab) {
		final LoadingPopup loadingPopup = new LoadingPopup();
		loadingPopup.display();
		HashMap<String, String> hm = new HashMap<String, String>();
		hm.put("phase", "1");
		hm.put("tab", tab + "");
		btnAdd.setVisible(false);
		rpcService.fetchStrategic(hm, new AsyncCallback<ArrayList<Strategic>>() {

			@Override
			public void onFailure(Throwable ex) {
				loadingPopup.remove();
				logger.log(Level.INFO, "FAIL: fetchStrategic .Inside Audit AuditAreaspresenter");
				if (ex instanceof TimeOutException) {
					History.newItem("login");
				} else {
					System.out.println("FAIL: fetchStrategic .Inside AuditAreaspresenter");
					Window.alert("FAIL: fetchStrategic");// After FAIL ... write
															// RPC Name NOT
															// Method Name..
				}

				Window.alert("Fetch Audit universe strategic failed");
				Window.alert("" + ex.getMessage());
				Window.alert("" + ex.getLocalizedMessage());
				Window.alert("" + ex.getCause());
				Window.alert("" + ex.getStackTrace());
			}

			@Override
			public void onSuccess(final ArrayList<Strategic> result) {

				btnAdd.setVisible(true);
				// btnAdd.setEnabled(true);
				// vpnlStrategic.add(new AuditUniverseStrategicViewHeading());
				for (int i = 0; i < result.size(); i++) {

					AuditUniverseStrategicView auditUniverseStrategicView = new AuditUniverseStrategicView();
					setButtonsVisibility(result, i, auditUniverseStrategicView);
					if (result.get(i).getPhase() != 1
							|| result.get(i).getLoggedInUser() != result.get(i).getAssignedTo().getEmployeeId()) {
						disablePanel(auditUniverseStrategicView, result.get(i));
					} else {
						enablePanel(auditUniverseStrategicView);
					}

					updateFields(result, i, auditUniverseStrategicView);
					vpnlStrategic.add(auditUniverseStrategicView);

					setHandlers(vpnlStrategic, hpnlButtonInitiator, hpnlButtonsApprovar, btnAdd,
							auditUniverseStrategicView, result.get(i).getTab());

					// strategicList.add(auditUniverseStrategicView);
				}
				if (result.size() > 0) {
					hpnlButtonInitiator.setVisible(true);

				} else {
					hpnlButtonInitiator.setVisible(false);
				}

				loadingPopup.remove();
			}
		});

	}

	public void disablePanel(AuditUniverseStrategicView auditUniverseStrategicView, Strategic strategic) {

		auditUniverseStrategicView.getHpnlButtonsApprovar().setVisible(false);
		auditUniverseStrategicView.getHpnlButtonInitiator().setVisible(false);
		auditUniverseStrategicView.getLstObjectiveOwner().setEnabled(false);
		auditUniverseStrategicView.getRelevantDepartment().setEnabled(false);
		auditUniverseStrategicView.getObjectiveAchievementDate().setEnabled(false);
		auditUniverseStrategicView.getStrategicObjective().setEnabled(false);
		auditUniverseStrategicView.getSubmitted().setVisible(true);
		auditUniverseStrategicView.getSubmitted()
				.setTitle(strategic.getStatus() + ": In " + PhaseNames.getPhaseNames(strategic.getPhase()));

	}

	public void enablePanel(AuditUniverseStrategicView auditUniverseStrategicView) {
		auditUniverseStrategicView.getLstObjectiveOwner().setEnabled(true);
		auditUniverseStrategicView.getRelevantDepartment().setEnabled(true);
		auditUniverseStrategicView.getObjectiveAchievementDate().setEnabled(true);
		auditUniverseStrategicView.getStrategicObjective().setEnabled(true);
		auditUniverseStrategicView.getSubmitted().setVisible(false);
	}

	private void setButtonsVisibility(final ArrayList<Strategic> result, int i,
			AuditUniverseStrategicView auditUniverseStrategicView) {
		if (result.get(i).getStatus().equals("submitted")) {
			auditUniverseStrategicView.getHpnlButtonsApprovar().setVisible(true);
			auditUniverseStrategicView.getHpnlButtonInitiator().setVisible(false);

		} else if (result.get(i).getStatus().equals("amend")) {
			auditUniverseStrategicView.getBtnDeclineInitiator().setVisible(false);
			auditUniverseStrategicView.getHpnlButtonsApprovar().setVisible(false);
			auditUniverseStrategicView.getHpnlButtonInitiator().setVisible(true);

		} else {
			auditUniverseStrategicView.getBtnDeclineInitiator().setVisible(true);
			auditUniverseStrategicView.getHpnlButtonsApprovar().setVisible(false);
			auditUniverseStrategicView.getHpnlButtonInitiator().setVisible(true);
		}
	}

	private void updateFields(final ArrayList<Strategic> result, int i,
			AuditUniverseStrategicView auditUniverseStrategicView) {

		final Strategic data = result.get(i);
		auditUniverseStrategicView.setStrategicId(result.get(i).getId());
		if (result.get(i).getStatus().equals("amend")) {
			auditUniverseStrategicView.getComments().addStyleName("point");
			auditUniverseStrategicView.getComments().setVisible(true);
		} else {
			auditUniverseStrategicView.getComments().setVisible(false);
		}

		auditUniverseStrategicView.getComments().addClickHandler(new ClickHandler() {
			// SH
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				PopupViewGXT feedbackpopup = new PopupViewGXT(new HTML(data.getComments()), "Feedback");
			}
		});

		auditUniverseStrategicView.getComments().setTitle(result.get(i).getComments());
		auditUniverseStrategicView.getObjectiveAchievementDate().setValue(result.get(i).getAcheivementDate());
		auditUniverseStrategicView.getStrategicObjective().setText(result.get(i).getStrategicObjective());
		auditUniverseStrategicView.getLblStrategicId().setText(result.get(i).getId() + ".");
		auditUniverseStrategicView.getStrategicObjective().setTitle(result.get(i).getStrategicObjective());

		if (result.get(i).getStatus().equals("submitted") || result.get(i).getPhase() > 1) {
			auditUniverseStrategicView.getRelevantDepartment().addStyleName("invisibleListBox");
			for (int k = 0; k < result.get(i).getStrategicDepartments().size(); k++) {
				auditUniverseStrategicView.getRelevantDepartment().addItem(
						result.get(i).getStrategicDepartments().get(k).getDepartment().getDepartmentName(),
						result.get(i).getStrategicDepartments().get(k).getDepartment().getDepartmentId() + "");

			}

		} else {
			for (int j = 0; j < departments.size(); j++) {
				auditUniverseStrategicView.getRelevantDepartment().addItem(departments.get(j).getDepartmentName(),
						departments.get(j).getDepartmentId() + "");
			}

		}

		for (int j = 0; j < objectiveOwners.size(); j++) {
			auditUniverseStrategicView.getLstObjectiveOwner().addItem(objectiveOwners.get(j).getEmployeeName(),
					objectiveOwners.get(j).getEmployeeId() + "");
		}

		for (int j = 0; j < auditUniverseStrategicView.getLstObjectiveOwner().getItemCount(); j++) {
			// if(auditUniverseStrategicView.getLstObjectiveOwner().getValue(j).equals(result.get(i).getObjectiveOwner().getEmployeeId()+"")){
			auditUniverseStrategicView.getLstObjectiveOwner().setSelectedIndex(j);
			// }
		}
		// LISTBOX OF DEPARTMENTS
		for (int j = 0; j < auditUniverseStrategicView.getRelevantDepartment().getItemCount(); j++) {
			for (int k = 0; k < result.get(i).getStrategicDepartments().size(); k++) {
				if (auditUniverseStrategicView.getRelevantDepartment().getValue(j).equals(
						result.get(i).getStrategicDepartments().get(k).getDepartment().getDepartmentId() + "")) {
					auditUniverseStrategicView.getRelevantDepartment().setItemSelected(j, true);
					break;
				}
			}
		}
		if (auditUniverseStrategicView.getRelevantDepartment().getSelectedIndex() == -1) {
			auditUniverseStrategicView.getRelevantDepartment().setSelectedIndex(0);
		}

	}

	private void setHandlers(final VerticalPanel vpnlStrategic, final HorizontalPanel hpnlButtonInitiator,
			final HorizontalPanel hpnlButtonsApprovar, final Image btnAdd,
			final AuditUniverseStrategicView auditUniverseStrategicView, final int tab) {
		auditUniverseStrategicView.getBtnSave().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				saveStrategic(auditUniverseStrategicView, vpnlStrategic, hpnlButtonInitiator, hpnlButtonsApprovar,
						btnAdd, "save", tab, auditUniverseStrategicView.getBtnSave());
			}
		});

		auditUniverseStrategicView.getBtnSubmit().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				saveStrategic(auditUniverseStrategicView, vpnlStrategic, hpnlButtonInitiator, hpnlButtonsApprovar,
						btnAdd, "submit", tab, auditUniverseStrategicView.getBtnSubmit());
			}
		});

		auditUniverseStrategicView.getBtnApprove().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				saveStrategic(auditUniverseStrategicView, vpnlStrategic, hpnlButtonInitiator, hpnlButtonsApprovar,
						btnAdd, "approve", tab, auditUniverseStrategicView.getBtnApprove());
			}
		});

		auditUniverseStrategicView.getBtnAmend().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				final AmendmentPopup amendmentPopup = new AmendmentPopup();
				amendmentPopup.popupAmendment();
				amendmentPopup.getBtnSubmit().addClickHandler(new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						auditUniverseStrategicView.setComment(amendmentPopup.getComments().getText());
						saveStrategic(auditUniverseStrategicView, vpnlStrategic, hpnlButtonInitiator,
								hpnlButtonsApprovar, btnAdd, "amend", tab, amendmentPopup.getBtnSubmit());
						amendmentPopup.getPopupComments().removeFromParent();
					}
				});

				// popupAmendment(vpnlStrategic, hpnlButtonInitiator,
				// hpnlButtonsApprovar, btnAdd, auditUniverseStrategicView);
			}

		});

		auditUniverseStrategicView.getBtnDecline().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				boolean confirmed = Window.confirm("Are you sure you want to delete : "
						+ auditUniverseStrategicView.getStrategicObjective().getText());
				if (confirmed) {
					declineStrategic(auditUniverseStrategicView.getStrategicId(), vpnlStrategic, hpnlButtonInitiator,
							hpnlButtonsApprovar, btnAdd, tab, auditUniverseStrategicView.getBtnDecline());
				}
			}
		});

		auditUniverseStrategicView.getBtnDeclineInitiator().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				boolean confirmed = Window.confirm("Are you sure you want to delete : "
						+ auditUniverseStrategicView.getStrategicObjective().getText());
				if (confirmed) {
					declineStrategic(auditUniverseStrategicView.getStrategicId(), vpnlStrategic, hpnlButtonInitiator,
							hpnlButtonsApprovar, btnAdd, tab, auditUniverseStrategicView.getBtnDeclineInitiator());
				}
			}
		});
	}

	public void setNewRecordData(AuditUniverseStrategicView auditUniverseStrategicView) {

		for (int i = 0; i < departments.size(); i++) {
			auditUniverseStrategicView.getRelevantDepartment().addItem(departments.get(i).getDepartmentName(),
					departments.get(i).getDepartmentId() + "");
		}

		for (int i = 0; i < objectiveOwners.size(); i++) {
			auditUniverseStrategicView.getLstObjectiveOwner().addItem(objectiveOwners.get(i).getEmployeeName(),
					objectiveOwners.get(i).getEmployeeId() + "");
		}
	}

	// private void popupAmendment(final VerticalPanel vpnlStrategic,
	// final HorizontalPanel hpnlButtonInitiator,
	// final HorizontalPanel hpnlButtonsApprovar,
	// final TextButton btnAdd,
	// final AuditUniverseStrategicView auditUniverseStrategicView) {
	// final DecoratedPopupPanel popupComments = new DecoratedPopupPanel();
	// VerticalPanel vpnlComments = new VerticalPanel();
	// popupComments.setWidget(vpnlComments);
	// Label lbl = new Label("Enter Comments");
	// final TextArea comments = new TextArea();
	// comments.setSize("400px", "200px");
	// HorizontalPanel hpnlButtons = new HorizontalPanel();
	// hpnlButtons.setSpacing(2);
	// Button btnSubmit = new Button("Submit");
	// Button btnCancel = new Button("Cancel");
	// hpnlButtons.add(btnCancel);
	// hpnlButtons.add(btnSubmit);
	// vpnlComments.add(lbl);
	// vpnlComments.add(comments);
	// vpnlComments.add(hpnlButtons);
	//
	// vpnlComments.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
	// vpnlComments.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
	// popupComments.center();
	//
	// btnSubmit.addClickHandler(new ClickHandler(){
	//
	// @Override
	// public void onClick(ClickEvent event) {
	// auditUniverseStrategicView.setComment(comments.getText());
	// saveStrategic(auditUniverseStrategicView, vpnlStrategic,
	// hpnlButtonInitiator, hpnlButtonsApprovar, btnAdd, "amend");
	// popupComments.removeFromParent();
	// }});
	//
	// btnCancel.addClickHandler(new ClickHandler(){
	//
	// @Override
	// public void onClick(ClickEvent event) {
	// popupComments.removeFromParent();
	// }});
	// }

	public void submitStrategic(ArrayList<AuditUniverseStrategicView> strategicList, VerticalPanel vpnlStrategicData,
			Button btnSave) {
		// TODO Auto-generated method stub

	}

}
