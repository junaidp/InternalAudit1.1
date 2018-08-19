package com.internalaudit.client.portal;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gwt.cell.client.DateCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.internalaudit.shared.InternalAuditConstants;
import com.internalaudit.shared.JobStatusDTO;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.Portlet;
import com.sencha.gxt.widget.core.client.button.ToolButton;
import com.sencha.gxt.widget.core.client.container.PortalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;

public class PortableLayoutContainerUi extends VerticalPanel {

	protected static final int MIN_HEIGHT = 1;
	protected static final int MIN_WIDTH = 1280;
	protected static final int PREFERRED_HEIGHT = 1;
	protected static final int PREFERRED_WIDTH = 1;

	private static final FieldWorkProperties fieldWorkProperties = GWT.create(FieldWorkProperties.class);
	private static final PlanningProperties planningProperties = GWT.create(PlanningProperties.class);
	private static final ReportingProperties reportingProperties = GWT.create(ReportingProperties.class);

	private PortalLayoutContainer portal;
	private ArrayList<FieldWork> listFieldWork = new ArrayList<FieldWork>();
	private ArrayList<Planning> listPlanning = new ArrayList<Planning>();
	private ArrayList<Reporting> listReporting = new ArrayList<Reporting>();


	public PortableLayoutContainerUi(JobStatusDTO jobStatus) {

		setData(jobStatus);

		Portlet portletFieldWork = new Portlet();
		portletFieldWork.setHeadingHtml("Field Work");
		portletFieldWork.add(createGridFieldWork());
		portletFieldWork.setWidth(500);
		configPanel(portletFieldWork);

		Portlet portletPlanning = new Portlet();
		portletPlanning.setHeadingHtml("Planing");
		portletPlanning.add(createGridPlanning());
		configPanel(portletPlanning);
		//
		Portlet portletReporting = new Portlet();
		portletReporting.setHeadingHtml("Reporting");
		portletReporting.add(createGridReporting());
		configPanel(portletReporting);

		portal = new PortalLayoutContainer(3);
		portal.setSpacing(20);
		portal.setColumnWidth(0, .99);
		portal.setColumnWidth(1, .50);
		portal.setColumnWidth(2, .30);
		portal.add(portletFieldWork, 0);
		portal.add(portletPlanning, 0);
		portal.add(portletReporting, 0);
		add( portal);
	}


	private void setData(JobStatusDTO jobStatus) {

		listFieldWork.clear();
		for(int i=0; i<jobStatus.getListFieldWorkStatus().size(); i++){
			FieldWork fieldWork = new FieldWork();
			fieldWork.setName(jobStatus.getListFieldWorkStatus().get(i).getAuditStepName());
			fieldWork.setDate(jobStatus.getListFieldWorkStatus().get(i).getApprovalDate());
			fieldWork.setStatus(jobStatus.getListFieldWorkStatus().get(i).getStatus());
			fieldWork.setId(jobStatus.getListFieldWorkStatus().get(i).getAudtiStepId());
			listFieldWork.add(fieldWork);
		}

		listPlanning.clear();
		for(int i=0; i<jobStatus.getListPlanningStatus().size(); i++){
			Planning planning = new Planning();
			planning.setName(jobStatus.getListPlanningStatus().get(i).getPlanningName());
			planning.setDate(jobStatus.getListPlanningStatus().get(i).getDate());
			planning.setStatus(jobStatus.getListPlanningStatus().get(i).getStatus());
			planning.setId(jobStatus.getListPlanningStatus().get(i).getId());
			listPlanning.add(planning);
		}

		listReporting.clear();
//REPORTING
		

		Reporting reporting0 = new Reporting();
		reporting0.setName(InternalAuditConstants.EXCEPTIONSTOSENT);
		reporting0.setId(0);

		Reporting reporting1 = new Reporting();
		reporting1.setName(InternalAuditConstants.AWAITINGCOMMENTS);
		reporting1.setId(1);

		Reporting reporting2 = new Reporting();
		reporting2.setName(InternalAuditConstants.COMMENTSRECEIVED);
		reporting2.setId(2);

		Reporting reporting3 = new Reporting();
		reporting3.setName(InternalAuditConstants.REPORTISSUED);
		reporting3.setId(3);

		Reporting reporting4 = new Reporting();
		reporting4.setName(InternalAuditConstants.FINALREPORTISSUED);
		reporting4.setId(4);
		
		listReporting.add(reporting0);
		listReporting.add(reporting1);
		listReporting.add(reporting2);
		listReporting.add(reporting3);
		listReporting.add(reporting4);

		updateReportingStatusForOthers(jobStatus.getReportingStatus());
	/*	if(jobStatus.getReportingStatus().equals(InternalAuditConstants.EXCEPTIONSTOSENT)){
			//Show green circle before EXCEPTIONSTOSENT, and other as white
			reporting0.setStatus(InternalAuditConstants.COMPLETED);
		
		}else if(jobStatus.getReportingStatus().equals(InternalAuditConstants.AWAITINGCOMMENTS)){
			reporting1.setStatus(InternalAuditConstants.COMPLETED);
		}else if(jobStatus.getReportingStatus().equals(InternalAuditConstants.COMMENTSRECEIVED)){
			reporting2.setStatus(InternalAuditConstants.COMPLETED);
		}else if(jobStatus.getReportingStatus().equals(InternalAuditConstants.REPORTISSUED)){
			reporting3.setStatus(InternalAuditConstants.COMPLETED);
		}else if(jobStatus.getReportingStatus().equals(InternalAuditConstants.FINALREPORTISSUED)){
			reporting4.setStatus(InternalAuditConstants.COMPLETED);
		}
		*/
		
		
	

	}
	
	private void updateReportingStatusForOthers( String status){
		int index = 0;
		if(status.equals(InternalAuditConstants.EXCEPTIONSTOSENT)){
			index = 1;
		}else if(status.equals(InternalAuditConstants.AWAITINGCOMMENTS)){
			index = 2;
		}else if(status.equals(InternalAuditConstants.COMMENTSRECEIVED)){
			index = 3;
		}else if(status.equals(InternalAuditConstants.REPORTISSUED)){
			index = 4;
		}else if(status.equals(InternalAuditConstants.FINALREPORTISSUED)){
			index = 5;
		}
		
		
		listReporting.get(index).setStatus(status);
		if(status.isEmpty()){
			listReporting.get(index).setStatus(InternalAuditConstants.NOT_STARTED);
		}
		
		for(int i=0; i< listReporting.size(); i++){
			if(! status.equals(InternalAuditConstants.COMPLETED) && i> index){
				listReporting.get(i).setStatus(InternalAuditConstants.NOT_STARTED);
			}else if(status.equals(InternalAuditConstants.COMPLETED) && i< index){
				listReporting.get(i).setStatus(InternalAuditConstants.COMPLETED);
			}
		}
	}


	public Widget createGridFieldWork() {
		
		ColumnConfig<FieldWork, String> fieldworkCol = new ColumnConfig<FieldWork, String>(fieldWorkProperties.name(), 150, "Name");
		ColumnConfig<FieldWork, Date> lastTransCol = new ColumnConfig<FieldWork, Date>(fieldWorkProperties.date(), 100, "Date");
		ColumnConfig<FieldWork, String> WorkStepsComptCol = new ColumnConfig<FieldWork, String>(fieldWorkProperties.status(), 150, "Status");

		lastTransCol.setCell(new DateCell(DateTimeFormat.getFormat("MM/dd/yyyy")));

		List<ColumnConfig<FieldWork, ?>> columns = new ArrayList<ColumnConfig<FieldWork, ?>>();
		columns.add(fieldworkCol);
		columns.add(lastTransCol);
		columns.add(WorkStepsComptCol);

		ColumnModel<FieldWork> cm = new ColumnModel<FieldWork>(columns);

		ListStore<FieldWork> store = new ListStore<FieldWork>(fieldWorkProperties.key());
		store.addAll(listFieldWork);

		final Grid<FieldWork> grid = new Grid<FieldWork>(store, cm);
		grid.getView().setAutoExpandColumn(fieldworkCol);
		grid.getView().setForceFit(true);
		grid.getView().setStripeRows(true);
		grid.getView().setColumnLines(true);

		return grid;
	}

	public Widget createGridPlanning() {

		ColumnConfig<Planning, String> nameCol = new ColumnConfig<Planning, String>(planningProperties.name(), 150, "Name");
		ColumnConfig<Planning, Date> dateCol = new ColumnConfig<Planning, Date>(planningProperties.date(), 100, "Date");
		ColumnConfig<Planning, String> statusCol = new ColumnConfig<Planning, String>(planningProperties.status(), 150, "Status");

		dateCol.setCell(new DateCell(DateTimeFormat.getFormat("MM/dd/yyyy")));

		List<ColumnConfig<Planning, ?>> columns = new ArrayList<ColumnConfig<Planning, ?>>();
		columns.add(nameCol);
		columns.add(dateCol);
		columns.add(statusCol);

		ColumnModel<Planning> cm = new ColumnModel<Planning>(columns);

		ListStore<Planning> store = new ListStore<Planning>(planningProperties.key());
		store.addAll(listPlanning);

		final Grid<Planning> grid = new Grid<Planning>(store, cm);
		grid.getView().setAutoExpandColumn(nameCol);
		grid.getView().setForceFit(true);
		grid.getView().setStripeRows(true);
		grid.getView().setColumnLines(true);

		return grid;
	}

	public Widget createGridReporting() {

		ColumnConfig<Reporting, String> nameCol = new ColumnConfig<Reporting, String>(reportingProperties.name(), 150, "Name");
		ColumnConfig<Reporting, Date> dateCol = new ColumnConfig<Reporting, Date>(reportingProperties.date(), 100, "Date");
		ColumnConfig<Reporting, String> statusCol = new ColumnConfig<Reporting, String>(reportingProperties.status(), 150, "Status");

		dateCol.setCell(new DateCell(DateTimeFormat.getFormat("MM/dd/yyyy")));

		List<ColumnConfig<Reporting, ?>> columns = new ArrayList<ColumnConfig<Reporting, ?>>();
		columns.add(nameCol);
		columns.add(dateCol);
		columns.add(statusCol);

		ColumnModel<Reporting> cm = new ColumnModel<Reporting>(columns);

		ListStore<Reporting> store = new ListStore<Reporting>(reportingProperties.key());
		store.addAll(listReporting);

		final Grid<Reporting> grid = new Grid<Reporting>(store, cm);
		grid.getView().setAutoExpandColumn(nameCol);
		grid.getView().setForceFit(true);
		grid.getView().setStripeRows(true);
		grid.getView().setColumnLines(true);

		return grid;
	}

	private void configPanel(final Portlet portlet) {
		portlet.setCollapsible(true);
		portlet.setAnimCollapse(false);
		portlet.getHeader().addTool(new ToolButton(ToolButton.GEAR));
		portlet.getHeader().addTool(new ToolButton(ToolButton.CLOSE, new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				portlet.removeFromParent();
			}
		}));
	}
}
