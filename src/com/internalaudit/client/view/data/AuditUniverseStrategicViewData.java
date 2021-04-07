package com.internalaudit.client.view.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.thirdparty.common.css.compiler.gssfunctions.GssFunctions.Div;
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
import com.internalaudit.client.util.DuplicateArrayListStrategic;
import com.internalaudit.client.util.StringAbbrevations;
import com.internalaudit.client.view.AmendmentPopup;
import com.internalaudit.client.view.AuditUniverseStrategicView;
import com.internalaudit.client.view.ButtonRound;
import com.internalaudit.client.view.DisplayAlert;
import com.internalaudit.client.view.LoadingPopup;
import com.internalaudit.client.view.PhaseNames;
import com.internalaudit.client.view.PopupViewGXT;
import com.internalaudit.shared.Department;
import com.internalaudit.shared.Division;
import com.internalaudit.shared.Employee;
import com.internalaudit.shared.RiskFactor;
import com.internalaudit.shared.Strategic;
import com.internalaudit.shared.StrategicDepartments;
import com.internalaudit.shared.StrategicDivisions;
import com.internalaudit.shared.StrategicTabs;
import com.internalaudit.shared.SubProcess;
import com.internalaudit.shared.TimeOutException;
import com.itextpdf.text.pdf.PdfStructTreeController.returnType;
import com.sencha.gxt.core.client.util.DelayedTask;

public class AuditUniverseStrategicViewData {

	private InternalAuditServiceAsync rpcService = GWT.create(InternalAuditService.class);
	// private AuditUniverseStrategicView auditUniverseStrategicView;
	private ArrayList<Employee> objectiveOwners = new ArrayList<Employee>();
	private ArrayList<Department> listDepartments = new ArrayList<Department>();
	private ArrayList<Division> divisions = new ArrayList<Division>();
	private ArrayList<Division> arrayAllDivisions = new ArrayList<Division>();
	private String actionperformed;
	private Logger logger = Logger.getLogger("AuditUniverStrategicViewData");
	private int currentYear = 2020; 
//	private boolean flagFetchStrategicDulicate = false;
	private ArrayList<StrategicTabs> arrayStrategicTabs = new ArrayList<StrategicTabs>();
	
	public void setData() {
		// this.auditUniverseStrategicView = auditUniverseStrategicView;
		fetchObjectiveOwners();
		fetchDivisions();
		// setHandlers();
	}

	public void declineStrategic(int strategicId, final VerticalPanel vpnlStrategic,
			final HorizontalPanel hpnlButtonInitiator, final HorizontalPanel hpnlButtonsApprovar, final Image btnAdd,
			final int tab, final Button button) {

		button.setEnabled(false);
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

				button.setEnabled(true);
				Window.alert("decline strategic failed");
			}

			@Override
			public void onSuccess(String result) {
				button.setEnabled(true);
				vpnlStrategic.clear();

				fetchStrategic(vpnlStrategic, hpnlButtonInitiator, hpnlButtonsApprovar, btnAdd);

			}
		});
	}

	public void saveStrategic(final AuditUniverseStrategicView strategicView, final VerticalPanel vpnlStrategicData,
			final HorizontalPanel hpnlButtonInitiator, final HorizontalPanel hpnlButtonsApprovar, final Image btnAdd,
			String todo, Button button) {
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
					todo, strategics, button);

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
							btnAdd, todo, strategics, buttonClicked);
				} else {
					Window.alert("Date not valid");
				}
			}
		});

	}

	private void saveStrategicToServer(final AuditUniverseStrategicView strategicView,
			final VerticalPanel vpnlStrategicData, final HorizontalPanel hpnlButtonInitiator,
			final HorizontalPanel hpnlButtonsApprovar, final Image btnAdd, String todo,
			final ArrayList<Strategic> strategics, final Button button) {

		button.setEnabled(false);
		final Strategic strategic = new Strategic();
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
//		strategic.setRiskFactor(risk);
		strategic.setRating("N/A");// changed low to N/A by Moqeet
//		Department department = new Department();
		// department.setDepartmentId(Integer.parseInt(strategicView.getRelevantDepartment().getValue(strategicView.getRelevantDepartment().getSelectedIndex())));
		//////
		setDivisionSDepartments(strategic, strategicView.getListBoxDivision(), strategicView.getListRelevantDepartment());
//		setDepartments(strategicView.getListRelevantDepartment(), strategic);
		//strategic.setRelevantDepartment(department);
		strategic.setStrategicObjective(strategicView.getStrategicObjective().getText());
		//added by Moqeet
		for(int i = 0; i <strategicView.getListBoxDivision().getItemCount(); i++) {
			if (strategicView.getListBoxDivision().isItemSelected(i)) {
				strategic.setDivisionID(Integer.parseInt(strategicView.getListBoxDivision().getValue(i)));
			}
		}
		strategic.setCreateMultipleJobs(strategicView.getCheckBoxMultiple().getValue());
		strategicView.getCheckBoxMultiple().addValueChangeHandler(new ValueChangeHandler<Boolean>() {

			@Override
			public void onValueChange(ValueChangeEvent<Boolean> arg0) {
				strategic.setCreateMultipleJobs(arg0.getValue());
			}
		});
		// strategic.setPhase("Identification");
		strategic.setPhase(1);
		strategic.setNextPhase(2);
		// strategic.setNextPhase("RiskAssesment");
		strategic.setComments(strategicView.getComment());
		strategic.setYear(fetchCurrentYear());
		// strategic.setRatingComments();
		// strategics.add(strategic);

		actionperformed = todo;
		HashMap<String, String> hm = new HashMap<String, String>();
//		if (todo.equalsIgnoreCase("approve")) {
//			todo = "submit";
//		}
		hm.put("todo", todo);
		hm.put("tab", strategicView.getListStrategicTabs().getSelectedValue());
		strategic.setArrayStrategicTabs(setStrategicTabs(strategic.getId(), strategicView.getListStrategicTabs()));
		fetchStrategicDuplicate(vpnlStrategicData, hpnlButtonInitiator, hpnlButtonsApprovar, btnAdd, button, strategic, hm);
//		if(fetchStrategicDuplicate(vpnlStrategicData, hpnlButtonInitiator, hpnlButtonsApprovar, btnAdd, button, strategic, hm) == false)
//			saveStrategicRPC(vpnlStrategicData, hpnlButtonInitiator, hpnlButtonsApprovar, btnAdd, button, strategic, hm);
	}
	
	private void fetchStrategicDuplicate(final VerticalPanel vpnlStrategicData, final HorizontalPanel hpnlButtonInitiator, final HorizontalPanel hpnlButtonsApprovar, final Image btnAdd, final Button button, final Strategic strategic, final HashMap<String, String> hm) {
		if(strategic.getId() == 0)
			saveStrategicRPC(vpnlStrategicData, hpnlButtonInitiator, hpnlButtonsApprovar, btnAdd, button, strategic, hm);
		else {	
			rpcService.fetchStrategicDuplicate(strategic, new AsyncCallback<ArrayList<Strategic>>() {
	
				@Override
				public void onFailure(Throwable arrayStrategic) {
					new DisplayAlert("Unable to fetch Duplicate Strategic");
				}
	
				@Override
				public void onSuccess(ArrayList<Strategic> arrayStrategic) {
						if(arrayStrategic.size()>1) {
		//					flagFetchStrategicDulicate = true;
							for(Strategic strategicDB : arrayStrategic) {
								Strategic strategicToSave = strategic;
								strategicToSave.setId(strategicDB.getId());
								saveStrategicRPC(vpnlStrategicData, hpnlButtonInitiator, hpnlButtonsApprovar, btnAdd, button, strategicToSave, hm);
						}
					}
						else
							saveStrategicRPC(vpnlStrategicData, hpnlButtonInitiator, hpnlButtonsApprovar, btnAdd, button, strategic, hm);
						}
			});
		}
	}

	private void saveStrategicRPC(final VerticalPanel vpnlStrategicData, final HorizontalPanel hpnlButtonInitiator,
			final HorizontalPanel hpnlButtonsApprovar, final Image btnAdd, final Button button,
			final Strategic strategic, HashMap<String, String> hm) {
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

				button.setEnabled(true);
				Window.alert("save strategic failed");
			}

			@Override
			public void onSuccess(String arg0) {
				button.setEnabled(true);
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
				fetchStrategic(vpnlStrategicData, hpnlButtonInitiator, hpnlButtonsApprovar, btnAdd);
				// }

			}
		});
	}

	private void setDivisionSDepartments(Strategic strategic, ListBox listBoxDivision, ListBox listBoxDepartment) {
		ArrayList<StrategicDivisions> arrayStrategicDivisions = new ArrayList<StrategicDivisions>();
		for (int i = 0; i < listBoxDivision.getItemCount(); i++) {
			if (listBoxDivision.isItemSelected(i)) {
				StrategicDivisions strategicDivision = new StrategicDivisions();
				Division division = new Division();
				division.setDivisionID(Integer.parseInt(listBoxDivision.getValue(i)));
				strategicDivision.setDivision(division);
//				strategicDivision.setStrategic(strategic.getId());
				setDepartments(listBoxDepartment, strategicDivision);
				arrayStrategicDivisions.add(strategicDivision);
			}
		}
		strategic.setArrayStrategicDivisions(arrayStrategicDivisions);
	}
	
	private void setDepartments(ListBox relevantDepartment, StrategicDivisions strategicDivision) {
		ArrayList<StrategicDepartments> strategicDepartments = new ArrayList<StrategicDepartments>();
		StringAbbrevations objAbbrevation = new StringAbbrevations();
		String abrevationDivisionName = null;
		for(Division div : divisions) {
			if(div.getDivisionID() == strategicDivision.getDivision().getDivisionID()) {
				abrevationDivisionName = objAbbrevation.findAbbrevation(div.getDivisionName());
				abrevationDivisionName = abrevationDivisionName.substring(2, abrevationDivisionName.length()-1);
				break;
			}
		}
		
		for (int i = 0; i < relevantDepartment.getItemCount(); i++) {
			if (relevantDepartment.isItemSelected(i)){
				String[] abrevationDepartmentName = relevantDepartment.getItemText(i).split("\\[");
				String nameDept = "";
				int size = abrevationDepartmentName.length;
			//	int size = abrevationDivisionName.length();
//				System.out.println(abrevationDepartmentName[(abrevationDivisionName.length()-1)] + " : " +abrevationDepartmentName[(abrevationDivisionName.length()-1)].length());
//				Window.alert(abrevationDepartmentName[size-1]);
				if(abrevationDepartmentName[size-1].length() < 3) {
					nameDept = abrevationDepartmentName[1];
				}
				else {
					nameDept = abrevationDepartmentName[abrevationDivisionName.length()-1];
				}
				nameDept = nameDept.substring(0, nameDept.length()-1);
			if (abrevationDivisionName.equalsIgnoreCase(nameDept)) {
				StrategicDepartments strategicDepartment = new StrategicDepartments();
				Department department = new Department();
				strategicDepartment.setDivision(strategicDivision.getDivision());
				department.setDepartmentId(Integer.parseInt(relevantDepartment.getValue(i)));
				strategicDepartment.setDepartment(department);
				strategicDepartment.setStrategic(strategicDivision.getStrategic());
				strategicDepartments.add(strategicDepartment);
			}
		}
	}
		strategicDivision.setArrayStrategicDepartments(strategicDepartments);
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
//fecth Department commented by Moqeet
		rpcService.fetchDivision(new AsyncCallback<ArrayList<Division>>() {

			@Override
			public void onFailure(Throwable arg0) {
				// TODO Auto-generated method stub
				Window.alert("Unable to fetch Divisions");
			}

			@Override
			public void onSuccess(ArrayList<Division> division) {
				divisions = division;
				arrayAllDivisions = division;
				auditUniverseStrategicView.getListBoxDivision().addItem("--Select Division--", "0");
				if (auditUniverseStrategicView != null) {
					for(Division div:division) {
						StringAbbrevations stringObj = new StringAbbrevations();
						String name = div.getDivisionName() + stringObj.findAbbrevation(div.getDivisionName());
						auditUniverseStrategicView.getListBoxDivision().addItem(name ,div.getDivisionID()+"");
					}
				}
				//if/else added to fetch departments on divisionID basis when !=0
				if(auditUniverseStrategicView.getListBoxDivision().getSelectedValue() == "0")
					auditUniverseStrategicView.getListRelevantDepartment().addItem("--Select Department--");
				else
					fetchDepartmentsDivision(Integer.parseInt(auditUniverseStrategicView.getListBoxDivision()
									.getValue(auditUniverseStrategicView.getListBoxDivision().getSelectedIndex())),auditUniverseStrategicView.getListRelevantDepartment(), null);
			}
		});
		
		auditUniverseStrategicView.getListBoxDivision().addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				LinkedList<Integer> selectedItems = getSelectedItems(auditUniverseStrategicView.getListBoxDivision());
				auditUniverseStrategicView.getListRelevantDepartment().clear();
				for(int i=0; i<selectedItems.size(); i++) {
					fetchDepartmentsDivision(selectedItems.get(i), auditUniverseStrategicView.getListRelevantDepartment(), null);
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

	public void fetchDivisions() {
//Division added by Moqeet
		rpcService.fetchDivision(new AsyncCallback<ArrayList<Division>>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				logger.log(Level.INFO, "FAIL: FetchDivisions .Inside Audit AuditAreaspresenter");
				if (caught instanceof TimeOutException) {
					History.newItem("login");
				} else {
					System.out.println("FAIL: FetchDivisions .Inside AuditAreaspresenter");
					Window.alert("FAIL: FetchDivisions");// After FAIL ...
															// write RPC Name
															// NOT Method Name..
				}
			}

			@Override
			public void onSuccess(ArrayList<Division> division) {
				divisions = division;
			}
		});
		
		//not required, to fetch all departments
//		rpcService.fetchDepartments(new AsyncCallback<ArrayList<Department>>() {
//
//			@Override
//			public void onFailure(Throwable caught) {
//				//
//				logger.log(Level.INFO, "FAIL: fetchDepartments .Inside Audit AuditAreaspresenter");
//				if (caught instanceof TimeOutException) {
//					History.newItem("login");
//				} else {
//					System.out.println("FAIL: fetchDepartments .Inside AuditAreaspresenter");
//					Window.alert("FAIL: fetchDepartments");// After FAIL ...
//															// write RPC Name
//															// NOT Method Name..
//				}
//			}
//
//			@Override
//			public void onSuccess(ArrayList<Department> department) {
//				listDepartments = department;
//				// if(auditUniverseStrategicView!=null){
//				// for(int i=0; i< department.size(); i++){
//				// auditUniverseStrategicView.getRelevantDepartment().addItem(department.get(i).getDepartmentName(),
//				// department.get(i).getDepartmentId()+"");
//				// }
//				// }
//			}
//		});
	}

	public void fetchStrategic(final VerticalPanel vpnlStrategic, final HorizontalPanel hpnlButtonInitiator,
			final HorizontalPanel hpnlButtonsApprovar, final Image btnAdd) {
		final LoadingPopup loadingPopup = new LoadingPopup();
		loadingPopup.display();
		HashMap<String, String> hm = new HashMap<String, String>();
		hm.put("phase", "1");
//		hm.put("tab", tab + "");
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
			public void onSuccess(ArrayList<Strategic> result) {
				DuplicateArrayListStrategic mergeArrayList = new DuplicateArrayListStrategic();
				result = mergeArrayList.mergeRuplicateList(result); 
				btnAdd.setVisible(true);
				// btnAdd.setEnabled(true);
				// vpnlStrategic.add(new AuditUniverseStrategicViewHeading());
				for (int i = 0; i < result.size(); i++) {

					final AuditUniverseStrategicView auditUniverseStrategicView = new AuditUniverseStrategicView();
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

	public void fetchDepartmentsDivision(final int divisionID, final ListBox listBoxDepartments, final ArrayList<StrategicDepartments> listSelectedDepartments) {
		if(divisionID == 0) {
			listBoxDepartments.clear();
			listBoxDepartments.addItem("--Select Department--");
		}
		else {
		rpcService.fetchDivisionDepartments(divisionID, new AsyncCallback<ArrayList<Department>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("error occured in DepartmentsDivisionFetch");

			}

			@Override
			public void onSuccess(ArrayList<Department> departments) {
				listDepartments = departments;
				String divAbrevator = findDivisionAbrrevation(divisionID);
				if (listBoxDepartments != null) {
//					listBoxDepartments.clear();
				for(Department dept : departments) {
					listBoxDepartments.addItem(dept.getDepartmentName() + divAbrevator, dept.getDepartmentId() + "");
				}
				if(!(listSelectedDepartments == null))
					selectedSubProcess(listSelectedDepartments, listBoxDepartments);
				}
			}
		});
		}
	}
	
	private String findDivisionAbrrevation(int divisionID) {
		StringAbbrevations abrevator = new StringAbbrevations();
		String divAbrevator = null;
		for(Division div: divisions) {
			if(div.getDivisionID() == divisionID)
				divAbrevator = div.getDivisionName();
		}
		divAbrevator = abrevator.findAbbrevation(divAbrevator);
		return divAbrevator;
	}
	
	private void selectedSubProcess(ArrayList<StrategicDepartments> listSelectedDepartments, final ListBox listBox) {
		if (listBox != null) {
			for (int i = 0; i < listBox.getItemCount(); i++) {
				for(int j = 0; j < listSelectedDepartments.size(); j++)  {
					//GWT.log("Value : " + listBox.getValue(i)+ " | "+listSelectedDepartments.get(j).getDepartment().getDepartmentId());
					if (listBox.getValue(i).equals(listSelectedDepartments.get(j).getDepartment().getDepartmentId()+"")) {
						listBox.setItemSelected(i, true);
						break;
					}
			}
		}
	}
		listBox.setHeight("100%");
}
	
	public void disablePanel(AuditUniverseStrategicView auditUniverseStrategicView, Strategic strategic) {

		auditUniverseStrategicView.getHpnlButtonsApprovar().setVisible(false);
		auditUniverseStrategicView.getHpnlButtonInitiator().setVisible(false);
		auditUniverseStrategicView.getLstObjectiveOwner().setEnabled(false);
		auditUniverseStrategicView.getListRelevantDepartment().setEnabled(false);
		auditUniverseStrategicView.getListBoxDivision().setEnabled(false);
		auditUniverseStrategicView.getListStrategicTabs().setEnabled(false);
		auditUniverseStrategicView.getObjectiveAchievementDate().setEnabled(false);
		auditUniverseStrategicView.getStrategicObjective().setEnabled(false);
		auditUniverseStrategicView.getSubmitted().setVisible(true);
		auditUniverseStrategicView.visibleMultipleJobOption(false);
		auditUniverseStrategicView.getSubmitted()
				.setTitle(strategic.getStatus() + ": In " + PhaseNames.getPhaseNames(strategic.getPhase()));

	}

	public void enablePanel(AuditUniverseStrategicView auditUniverseStrategicView) {
		auditUniverseStrategicView.getLstObjectiveOwner().setEnabled(true);
		auditUniverseStrategicView.getListRelevantDepartment().setEnabled(true);
		auditUniverseStrategicView.getListBoxDivision().setEnabled(true);
		auditUniverseStrategicView.getListStrategicTabs().setEnabled(true);
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
			final AuditUniverseStrategicView auditUniverseStrategicView) {

		final Strategic data = result.get(i);
		auditUniverseStrategicView.setStrategicId(result.get(i).getId());
		//checkbox added by moqeet
		auditUniverseStrategicView.getCheckBoxMultiple().setValue(data.isCreateMultipleJobs());
//		auditUniverseStrategicView.getCheckBoxMultiple().addValueChangeHandler(new ValueChangeHandler<Boolean>() {
//
//			@Override
//			public void onValueChange(ValueChangeEvent<Boolean> arg0) {
//				auditUniverseStrategicView.getCheckBoxMultiple().setValue(arg0.getValue());
//			}
//		});
		if(data.isCreateMultipleJobs())
			auditUniverseStrategicView.visibleMultipleJobOption(true);
		if (result.get(i).getStatus().equals("amend")) {
			auditUniverseStrategicView.getFeedback().addStyleName("point");
			auditUniverseStrategicView.getFeedback().setVisible(true);
		} else {
			auditUniverseStrategicView.getFeedback().setVisible(false);
		}

		auditUniverseStrategicView.getFeedback().addClickHandler(new ClickHandler() {
			// SH
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				PopupViewGXT feedbackpopup = new PopupViewGXT(new HTML(data.getComments()), "Feedback");
			}
		});

		auditUniverseStrategicView.getFeedback().setTitle(result.get(i).getComments());
		auditUniverseStrategicView.getObjectiveAchievementDate().setValue(result.get(i).getAcheivementDate());
		auditUniverseStrategicView.getStrategicObjective().setText(result.get(i).getStrategicObjective());
		auditUniverseStrategicView.getLblStrategicId().setText(result.get(i).getId() + ".");
		auditUniverseStrategicView.getStrategicObjective().setTitle(result.get(i).getStrategicObjective());

//		if (result.get(i).getStatus().equals("submitted") || result.get(i).getPhase() > 1) {
//			auditUniverseStrategicView.getListRelevantDepartment().setEnabled(false);
//			//list box changed to disable instead of addStyleName("invisibleListBox");
//			for (int k = 0; k < result.get(i).getStrategicDepartments().size(); k++) {
//				//Window.alert(auditUniverseStrategicView.getStrategicObjective().getText());
//				auditUniverseStrategicView.getListRelevantDepartment().addItem(
//						result.get(i).getStrategicDepartments().get(k).getDepartment().getDepartmentName(),
//						result.get(i).getStrategicDepartments().get(k).getDepartment().getDepartmentId() + "");
//			}
//			//added by Moqeet
//			auditUniverseStrategicView.getListBoxDivision().setEnabled(false);
//			auditUniverseStrategicView.getListBoxDivision().addItem(result.get(i).getDivision().getDivisionName(), result.get(i).getDivision().getDivisionID()+"");
//		} else {
			//added by Moqeet
			for(Division div:divisions) {
				auditUniverseStrategicView.getListBoxDivision().addItem(div.getDivisionName(), div.getDivisionID()+"");
//				if(!result.get(i).getStatus().equalsIgnoreCase("saved"))
//				fetchDepartmentsDivision(div.getDivisionID(), auditUniverseStrategicView.getListRelevantDepartment());
			}
			
//			for (int j = 0; j < listDepartments.size(); j++) {
//				auditUniverseStrategicView.getListRelevantDepartment().addItem(listDepartments.get(j).getDepartmentName(),
//						listDepartments.get(j).getDepartmentId() + "");
//			}
//		}
			
		for (int j = 0; j < objectiveOwners.size(); j++) {
			auditUniverseStrategicView.getLstObjectiveOwner().addItem(objectiveOwners.get(j).getEmployeeName(),
					objectiveOwners.get(j).getEmployeeId() + "");
		}
		
		setListBoxStrategicData(auditUniverseStrategicView);
		for(int m = 0; m < auditUniverseStrategicView.getListStrategicTabs().getItemCount(); m++) {
			if(data.getArrayStrategicTabs() == null || data.getArrayStrategicTabs().isEmpty() ) {
				if(data.getTab() == Integer.parseInt(auditUniverseStrategicView.getListStrategicTabs().getValue(m)))
					auditUniverseStrategicView.getListStrategicTabs().setItemSelected(m, true);
			}
			else {
			for(StrategicTabs strategicTab : data.getArrayStrategicTabs())
				if(strategicTab.getStrategicTabId() == Integer.parseInt(auditUniverseStrategicView.getListStrategicTabs().getValue(m)))
					auditUniverseStrategicView.getListStrategicTabs().setItemSelected(m, true);
			}
		}

		for (int j = 0; j < auditUniverseStrategicView.getLstObjectiveOwner().getItemCount(); j++) {
			// if(auditUniverseStrategicView.getLstObjectiveOwner().getValue(j).equals(result.get(i).getObjectiveOwner().getEmployeeId()+"")){
			auditUniverseStrategicView.getLstObjectiveOwner().setSelectedIndex(j);
			// }
		}
		//added by moqeet
		for (int k = 0; k < auditUniverseStrategicView.getListBoxDivision().getItemCount(); k++) {
			if (auditUniverseStrategicView.getListBoxDivision().getValue(k).equals(data.getDivisionID()+""))
			{
				auditUniverseStrategicView.getListBoxDivision().setItemSelected(k, true);	
					break;
			}
		}
		
		
		fetchDepartmentsDivision(data.getDivisionID(), auditUniverseStrategicView.getListRelevantDepartment(), result.get(i).getStrategicDepartments());	
		// LISTBOX OF DEPARTMENTS
		//int loopSize = (result.get(i).getStatus().equals("saved"))? listDepartments.size(): auditUniverseStrategicView.getListRelevantDepartment().getItemCount();
//		if(result.get(i).getStatus().equals("saved")) {
//			auditUniverseStrategicView.getListRelevantDepartment().clear();
//			//fetchDepartmentsDivision(Integer.valueOf(auditUniverseStrategicView.getListBoxDivision().getSelectedValue()), auditUniverseStrategicView.getListRelevantDepartment());	
//			for (StrategicDepartments departments : result.get(i).getStrategicDepartments()) { 
//					auditUniverseStrategicView.getListRelevantDepartment().addItem(departments.getDepartment().getDepartmentName(), departments.getDepartment().getDepartmentId() + "");
//				}
//			auditUniverseStrategicView.getListRelevantDepartment().addStyleName("invisibleListBox");
//		}
//		else
//		{
//			for (int j = 0; j < auditUniverseStrategicView.getListRelevantDepartment().getItemCount(); j++) {
//				for (int k = 0; k < result.get(i).getStrategicDepartments().size(); k++) {
//					if (auditUniverseStrategicView.getListRelevantDepartment().getValue(j).equals(
//							result.get(i).getStrategicDepartments().get(k).getDepartment().getDepartmentId() + "")) {
//						auditUniverseStrategicView.getListRelevantDepartment().setItemSelected(j, true);
//						break;
//					}
//				}
//			}
//		}
//		if (auditUniverseStrategicView.getListRelevantDepartment().getSelectedIndex() == -1) 
//			auditUniverseStrategicView.getListRelevantDepartment().setSelectedIndex(0);
	}

	private void setHandlers(final VerticalPanel vpnlStrategic, final HorizontalPanel hpnlButtonInitiator,
			final HorizontalPanel hpnlButtonsApprovar, final Image btnAdd,
			final AuditUniverseStrategicView auditUniverseStrategicView, final int tab) {
		auditUniverseStrategicView.getBtnSave().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				saveStrategic(auditUniverseStrategicView, vpnlStrategic, hpnlButtonInitiator, hpnlButtonsApprovar,
						btnAdd, "save", auditUniverseStrategicView.getBtnSave());
			}
		});

		auditUniverseStrategicView.getBtnSubmit().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				saveStrategic(auditUniverseStrategicView, vpnlStrategic, hpnlButtonInitiator, hpnlButtonsApprovar,
						btnAdd, "submit", auditUniverseStrategicView.getBtnSubmit());
			}
		});

		auditUniverseStrategicView.getBtnApprove().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				saveStrategic(auditUniverseStrategicView, vpnlStrategic, hpnlButtonInitiator, hpnlButtonsApprovar,
						btnAdd, "approve", auditUniverseStrategicView.getBtnApprove());
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
								hpnlButtonsApprovar, btnAdd, "amend", amendmentPopup.getBtnSubmit());
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
		
//		auditUniverseStrategicView.getListBoxDivision().setMultipleSelect(true);

		auditUniverseStrategicView.getListBoxDivision().addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {	
				 LinkedList<Integer> selectedItems = getSelectedItems(auditUniverseStrategicView.getListBoxDivision());
//				divisionSelectedItems(auditUniverseStrategicView.getListBoxDivision());
				 auditUniverseStrategicView.getListRelevantDepartment().clear();
				 for(int i=0; i<selectedItems.size(); i++) {
					 fetchDepartmentsDivision(selectedItems.get(i), auditUniverseStrategicView.getListRelevantDepartment(), null);
				}
			}
		});
		auditUniverseStrategicView.getListRelevantDepartment().addChangeHandler(new ChangeHandler() {
			
			@Override
			public void onChange(ChangeEvent arg0) {
				if(auditUniverseStrategicView.getListRelevantDepartment().isMultipleSelect())
					auditUniverseStrategicView.visibleMultipleJobOption(true);
				else 
					auditUniverseStrategicView.visibleMultipleJobOption(false);
			}
		});
	}
	
	public LinkedList<Integer> getSelectedItems(ListBox listBox) {
	    LinkedList<Integer> selectedItems = new LinkedList<Integer>();
	    for (int i = 0; i < listBox.getItemCount(); i++) {
	        if (listBox.isItemSelected(i)) {
	        	selectedItems.add(Integer.parseInt(listBox.getValue(i)));
	        }
	    }
	    return selectedItems;
	}

//	private void divisionSelectedItems(ListBox listBoxDivision) {
//		ArrayList<Integer> listDivisionValues = new ArrayList<Integer>();
//		for(int i=1; i<=listBoxDivision.getItemCount(); i++) {
//			if(i == listBoxDivision.getSelectedIndex())
//				listDivisionValues.add(i);
//		}
//	}

//	public void setNewRecordData(AuditUniverseStrategicView auditUniverseStrategicView) {
//
//		for (int i = 0; i < listDepartments.size(); i++) {
//			auditUniverseStrategicView.getListRelevantDepartment().addItem(listDepartments.get(i).getDepartmentName(),
//					listDepartments.get(i).getDepartmentId() + "");
//		}
//		
//		//added by Moqeet
//		
//		for (Division div:divisions) {
//			auditUniverseStrategicView.getListBoxDivision().addItem(div.getDivisionName(), div.getDivisionID() + "");
//		}
//
//		for (int i = 0; i < objectiveOwners.size(); i++) {
//			auditUniverseStrategicView.getLstObjectiveOwner().addItem(objectiveOwners.get(i).getEmployeeName(),
//					objectiveOwners.get(i).getEmployeeId() + "");
//		}
//	}

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

	public void setListBoxStrategicData(AuditUniverseStrategicView auditUniverseStrategicView) {
		auditUniverseStrategicView.getListStrategicTabs().addItem("Strategic", "0");
		auditUniverseStrategicView.getListStrategicTabs().addItem("Operations", "1");
		auditUniverseStrategicView.getListStrategicTabs().addItem("Reporting", "2");
		auditUniverseStrategicView.getListStrategicTabs().addItem("Compliance", "3");
	}
	
	private ArrayList<StrategicTabs> setStrategicTabs(int strategicID, ListBox listBoxTabs) {
		arrayStrategicTabs.clear();
		for (int i = 0; i < listBoxTabs.getItemCount(); i++) {
			if (listBoxTabs.isItemSelected(i)) {
				StrategicTabs strategicTab = new StrategicTabs();
				strategicTab.setStrategicId(strategicID);
				strategicTab.setStrategicTabId(Integer.parseInt(listBoxTabs.getValue(i)));
				arrayStrategicTabs.add(strategicTab);
			}
		}
		return arrayStrategicTabs;
	}
	
	private int fetchCurrentYear() {
		rpcService.fetchCurrentYear(new AsyncCallback<Integer>() {

			@Override
			public void onFailure(Throwable arg0) {
				// TODO Auto-generated method stub
				Window.alert("Unable to fetch current year for Strategic in Audit Universe Tab");
			}

			@Override
			public void onSuccess(Integer arg0) {
				// TODO Auto-generated method stub
				currentYear = arg0;
			}
		});
		return currentYear;
	}
}
