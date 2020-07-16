package com.internalaudit.client.DashboardNew;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.internalaudit.client.InternalAuditService;
import com.internalaudit.client.InternalAuditServiceAsync;
import com.internalaudit.client.view.ButtonRound;
import com.internalaudit.client.view.DisplayAlert;
import com.internalaudit.client.view.AuditEngagement.LabelBold;
import com.internalaudit.shared.DashboardListBoxDTO;
import com.internalaudit.shared.Department;
import com.internalaudit.shared.Division;
import com.internalaudit.shared.SubProcess;

public class DashboardListBoxes extends HorizontalPanel {

	private InternalAuditServiceAsync rpcService = GWT.create(InternalAuditService.class);

	private ArrayList<DashboardListBoxDTO> dashBoardListBoxDTO;
	private ArrayList<SubProcess> subProcess;
	HorizontalPanel hpnlDates = new HorizontalPanel();

	private	Button btnSearch = new Button("Search");


	private	ListBox listBoxDomain = new ListBox();
	private ListBox listBoxProcess = new ListBox();
	

	private ListBox listBoxAudit = new ListBox();
	private ListBox listBoxUnit = new ListBox();
	private ListBox listBoxDivision = new ListBox();
	private ListBox listBoxDepartment = new ListBox();
	private ListBox listBoxResource = new ListBox();
	private ListBox listBoxRiskLevel = new ListBox();

	public DashboardListBoxes() {

		setStyling();
		fetchDashBoardDTOs();
		populateDivision(listBoxDivision);
		populateDepartmentAgainstDivision(listBoxDivision,listBoxDepartment);
		
		
		// listBoxDomain.addItem("All", "0");
		listBoxDomain.addItem("All", "All");
		listBoxDomain.setSelectedIndex(0);
		listBoxDomain.addItem("Strategic");
		listBoxDomain.addItem("Operations");
		listBoxDomain.addItem("Reporting");
		listBoxDomain.addItem("Compliance");
		listBoxProcess.addItem("All", "0");
		
		// listBoxProcess.addItem("High", "2");

		
		listBoxAudit.addItem("All", "0");
		listBoxUnit.addItem("All", "0");
	
		// listBoxDivision.addItem("All", "0");
		listBoxDivision.addItem("All", "All");
		listBoxDivision.setSelectedIndex(0);
		
		listBoxDepartment.addItem("All", "All");
		listBoxDepartment.setSelectedIndex(0);
		
		listBoxResource.addItem("All", "0");

		
		// listBoxRiskLevel.addItem("All", "0");
		listBoxRiskLevel.addItem("All", "All");
		listBoxRiskLevel.setSelectedIndex(0);
		listBoxRiskLevel.addItem("High", "High");
		listBoxRiskLevel.addItem("Medium", "Medium");
		listBoxRiskLevel.addItem("Low", "Low");

		

		
	}

	private void setStyling() {
		
		LabelBold lblDomain = new LabelBold();
		LabelBold lblProcess = new LabelBold();
		LabelBold lblAudit = new LabelBold();
		LabelBold lblUnit = new LabelBold();
		LabelBold lblDivision = new LabelBold();
		LabelBold lblDepartment = new LabelBold();
		LabelBold lblResource = new LabelBold();
		LabelBold lblRiskLabel = new LabelBold();
		
		lblDomain.setText("Domain");
		lblProcess.setText("Process");
		lblAudit.setText("Audit");
		lblUnit.setText("Unit");
		lblDivision.setText("Division");
		lblDepartment.setText("Department");
		lblResource.setText("Resource");
		lblRiskLabel.setText("Risk Level");
		
		
		lblRiskLabel.setWidth("100px");
		// btnSearch.setHeight("35px");
		lblProcess.getElement().getStyle().setMarginLeft(5, Unit.PX);
		lblAudit.getElement().getStyle().setMarginLeft(5, Unit.PX);
		lblUnit.getElement().getStyle().setMarginLeft(5, Unit.PX);
		lblDivision.getElement().getStyle().setMarginLeft(5, Unit.PX);
		lblDepartment.getElement().getStyle().setMarginLeft(5, Unit.PX);
		lblResource.getElement().getStyle().setMarginLeft(5, Unit.PX);
		lblRiskLabel.getElement().getStyle().setMarginLeft(5, Unit.PX);

		listBoxDomain.getElement().getStyle().setMarginLeft(5, Unit.PX);
		listBoxProcess.getElement().getStyle().setMarginLeft(5, Unit.PX);
		listBoxAudit.getElement().getStyle().setMarginLeft(5, Unit.PX);
		listBoxDivision.getElement().getStyle().setMarginLeft(5, Unit.PX);
		listBoxDepartment.getElement().getStyle().setMarginLeft(5, Unit.PX);
		listBoxResource.getElement().getStyle().setMarginLeft(5, Unit.PX);
		listBoxRiskLevel.getElement().getStyle().setMarginLeft(5, Unit.PX);
		listBoxUnit.getElement().getStyle().setMarginLeft(5, Unit.PX);

		listBoxProcess.addStyleName(" w3-border");
		listBoxDomain.addStyleName(" w3-border");
		listBoxDepartment.addStyleName(" w3-border");
		listBoxAudit.addStyleName("w3-border");
		listBoxUnit.addStyleName("w3-border");
		listBoxDivision.addStyleName(" w3-border");
		listBoxResource.addStyleName(" w3-border");
		listBoxRiskLevel.addStyleName(" w3-border");
		
		listBoxAudit.setWidth("100px");
		listBoxDivision.setWidth("100px");
		listBoxDepartment.setWidth("100px");
		listBoxDomain.setWidth("100px");
		listBoxProcess.setWidth("100px");
		listBoxResource.setWidth("100px");
		listBoxUnit.setWidth("100px");
		listBoxRiskLevel.setWidth("100px");
		
		FlexTable flex = new FlexTable();

		flex.setWidget(0, 0, lblDomain);
		flex.setWidget(1, 0, listBoxDomain);

		flex.setWidget(0, 1, lblProcess);
		flex.setWidget(1, 1, listBoxProcess);

		flex.setWidget(0, 2, lblAudit);
		flex.setWidget(1, 2, listBoxAudit);

		flex.setWidget(0, 3, lblUnit);
		flex.setWidget(1, 3, listBoxUnit);

		flex.setWidget(0, 4, lblDivision);
		flex.setWidget(1, 4, listBoxDivision);
		
		flex.setWidget(0, 5, lblDepartment);
		flex.setWidget(1, 5, listBoxDepartment);

		flex.setWidget(0, 6, lblResource);
		flex.setWidget(1, 6, listBoxResource);

		flex.setWidget(0, 7, lblRiskLabel);
		flex.setWidget(1, 7, listBoxRiskLevel);

		add(flex);
		add(hpnlDates);
		add(new HTML("&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;"));
		add(btnSearch);
		btnSearch.getElement().getStyle().setMarginTop(30, Unit.PX);
		setHeight("65px");
	}

	private void populateDepartmentAgainstDivision(final ListBox listBoxDivision,final ListBox listBoxDepartment) {
		listBoxDivision.addChangeHandler(new ChangeHandler() {
			
			@Override
			public void onChange(ChangeEvent arg0) {
				
				if(listBoxDivision.getSelectedValue().equalsIgnoreCase("All")) {
					listBoxDepartment.clear();
					listBoxDepartment.addItem("All", "All");
				}
				else {
					int selectedDivision  = Integer.parseInt(listBoxDivision.getSelectedValue());
					fetchDepartmentAgainstDivision(listBoxDepartment, selectedDivision);

					}
				}
		});
		
	}

	private void fetchDepartmentAgainstDivision(final ListBox listBoxDepartment, int selectedDivision) {
		listBoxDepartment.clear();
		rpcService.fetchDivisionDepartments(selectedDivision, new AsyncCallback<ArrayList<Department>>() {

			@Override
			public void onFailure(Throwable arg0) {
				Window.alert("Error in fetch/department against Divison ");
				
			}

			@Override
			public void onSuccess(ArrayList<Department> departments) {
				listBoxDepartment.addItem("All", "All");

				for(Department department :departments ) {
					listBoxDepartment.addItem(department.getDepartmentName(), department.getDepartmentId()+"");
				}
				
			}
		});
	}

	private void populateDivision( final ListBox listBoxDivision) {
		rpcService.fetchDivision(new AsyncCallback<ArrayList<Division>>() {
			
			@Override
			public void onSuccess(ArrayList<Division> divisions) {
				for(Division division :divisions) {
					listBoxDivision.addItem(division.getDivisionName(), division.getDivisionID() +"");
				}
				
			}
			
			@Override
			public void onFailure(Throwable arg0) {
				Window.alert("Failed fetching Divisions " + arg0.getLocalizedMessage());
				
			}
		});
		
	}

	private void fetchDashBoardDTOs() {
		rpcService.fetchDashBoardListBoxDTOs(new AsyncCallback<ArrayList<DashboardListBoxDTO>>() {

			@Override
			public void onFailure(Throwable ex) {
				Window.alert("Fetch Dashboard ListboxDTO faled");
				Window.alert("" + ex.getMessage());
				Window.alert("" + ex.getLocalizedMessage());
				Window.alert("" + ex.getCause());
				Window.alert("" + ex.getStackTrace());
			}

			@Override
			public void onSuccess(ArrayList<DashboardListBoxDTO> result) {
				// dashBoardListBoxDTO = result;
				
				for (int i = 0; i < result.get(0).getProcessList().size(); i++) {
					listBoxProcess.addItem(result.get(0).getProcessList().get(i).getProcessName(),
							result.get(0).getProcessList().get(i).getProcessId() + "");
				}
				for (int i = 0; i < result.get(0).getEmployList().size(); i++) {
					listBoxResource.addItem(result.get(0).getEmployList().get(i).getEmployeeName(),
							result.get(0).getEmployList().get(i).getEmployeeId() + "");
				}
				for (int i = 0; i < result.get(0).getJobList().size(); i++) {
					listBoxUnit.addItem(result.get(0).getJobList().get(i).getJobName(),
							result.get(0).getJobList().get(i).getJobCreationId() + "");
				}

				// fetchSubProcess(result.get(0).getProcessList().get(0).getProcessId(),
				// null, 0);

			}
		});

	}

	public Button getBtnSearch() {
		return btnSearch;
	}

	public void setBtnSearch(ButtonRound btnSearch) {
		this.btnSearch = btnSearch;
	}

	public ListBox getListBoxDomain() {
		return listBoxDomain;
	}

	public void setListBoxDomain(ListBox listBoxDomain) {
		this.listBoxDomain = listBoxDomain;
	}

	public ListBox getListBoxProcess() {
		return listBoxProcess;
	}

	public void setListBoxProcess(ListBox listBoxProcess) {
		this.listBoxProcess = listBoxProcess;
	}

	public ListBox getListBoxAudit() {
		return listBoxAudit;
	}

	public void setListBoxAudit(ListBox listBoxAudit) {
		this.listBoxAudit = listBoxAudit;
	}

	public ListBox getListBoxUnit() {
		return listBoxUnit;
	}

	public void setListBoxUnit(ListBox listBoxUnit) {
		this.listBoxUnit = listBoxUnit;
	}

	public ListBox getListBoxDivision() {
		return listBoxDivision;
	}

	public void setListBoxDivision(ListBox listBoxDivision) {
		this.listBoxDivision = listBoxDivision;
	}

	public ListBox getListBoxResource() {
		return listBoxResource;
	}

	public void setListBoxResource(ListBox listBoxResource) {
		this.listBoxResource = listBoxResource;
	}

	public ListBox getListBoxRiskLevel() {
		return listBoxRiskLevel;
	}

	public void setListBoxRiskLevel(ListBox listBoxRiskLevel) {
		this.listBoxRiskLevel = listBoxRiskLevel;
	}

	public HorizontalPanel getHpnlDates() {
		return hpnlDates;
	}

	public void setHpnlDates(HorizontalPanel hpnlDates) {
		this.hpnlDates = hpnlDates;
	}
	public ListBox getListBoxDepartment() {
		return listBoxDepartment;
	}

	public void setListBoxDepartment(ListBox listBoxDepartment) {
		this.listBoxDepartment = listBoxDepartment;
	}
}