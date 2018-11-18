package com.internalaudit.client.portal;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.DateCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.internalaudit.client.util.CustomImageCell;
import com.internalaudit.shared.InternalAuditConstants;
import com.internalaudit.shared.JobStatusDTO;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.Portlet;
import com.sencha.gxt.widget.core.client.button.ToolButton;
import com.sencha.gxt.widget.core.client.container.PortalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;

public class JobStatusPortaLayout extends VerticalLayoutContainer {

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


	public JobStatusPortaLayout(JobStatusDTO jobStatus) {

		setData(jobStatus);

		Portlet portletFieldWork = new Portlet();
		portletFieldWork.setHeadingHtml("Field Work");
		portletFieldWork.setShadow(disableContextMenu);
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
		portal.setSpacing(5);
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
			//fieldWork.setDate(jobStatus.getListFieldWorkStatus().get(i).getApprovalDate());
			fieldWork.setStatus(jobStatus.getListFieldWorkStatus().get(i).getStatus());
			fieldWork.setId(jobStatus.getListFieldWorkStatus().get(i).getAudtiStepId());
		//	fieldWork.setUrl(jobStatus.getListFieldWorkStatus().get(i).isHaveExceptions()? "redCircleNew.png": "greenCircleNew.png");
			fieldWork.setUrl(jobStatus.getListFieldWorkStatus().get(i).getStatus().equals(InternalAuditConstants.COMPLETED)? "greenCircleNew.png": "redCircleNew.png");
			
			listFieldWork.add(fieldWork);
		}

		listPlanning.clear();
		for(int i=0; i<jobStatus.getListPlanningStatus().size(); i++){
			Planning planning = new Planning();
			planning.setName(jobStatus.getListPlanningStatus().get(i).getPlanningName());
			//planning.setDate(jobStatus.getListPlanningStatus().get(i).getDate());
			planning.setStatus(jobStatus.getListPlanningStatus().get(i).getStatus());
			planning.setId(jobStatus.getListPlanningStatus().get(i).getId());
			planning.setUrl(planning.getStatus().equals(InternalAuditConstants.COMPLETED)?"greenCircleNew.png" : "redCircleNew.png");
			listPlanning.add(planning);
		}

		listReporting.clear();
		//REPORTING


		Reporting reporting0 = new Reporting();
		reporting0.setName("Draft Report Approval By Head of Inernal Audit ("+InternalAuditConstants.EXCEPTIONSTOSENT+")");
		reporting0.setId(0);

		Reporting reporting1 = new Reporting();
		reporting1.setName("Drafts sent to Management for Comments ("+InternalAuditConstants.AWAITINGCOMMENTS+")");
		reporting1.setId(1);

		Reporting reporting2 = new Reporting();
		reporting2.setName(InternalAuditConstants.COMMENTSRECEIVED);
		reporting2.setId(2);

		Reporting reporting3 = new Reporting();
		reporting3.setName("Issuance of Final Report ("+InternalAuditConstants.REPORTISSUED+")");
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
	}

	private void updateReportingStatusForOthers( String status){
		int index = 0;
		if(status.equals(InternalAuditConstants.EXCEPTIONSTOSENT)){
			index = 0;
		}else if(status.equals(InternalAuditConstants.AWAITINGCOMMENTS)){
			index = 1;
		}else if(status.equals(InternalAuditConstants.COMMENTSRECEIVED)){
			index = 2;
		}else if(status.equals(InternalAuditConstants.REPORTISSUED)){
			index = 3;
		}else if(status.equals(InternalAuditConstants.FINALREPORTISSUED)){
			index = 4;
		}


		listReporting.get(index).setStatus(status);
		
		//listReporting.get(index).setUrl(listReporting.get(index).getStatus().equals(InternalAuditConstants.COMPLETED)? "greenCircleNew.png" : "redCircleNew.png");

		listReporting.get(index).setUrl( "greenCircleNew.png");

		/*for(int i=0; i< listReporting.size(); i++){
			if(! status.equals(InternalAuditConstants.COMPLETED) && i> index){
				listReporting.get(i).setStatus(InternalAuditConstants.NOT_STARTED);
				listReporting.get(i).setUrl(listReporting.get(i).getStatus().equals(InternalAuditConstants.COMPLETED)? "greenCircleNew.png" : "redCircleNew.png");

			}else if(status.equals(InternalAuditConstants.COMPLETED) && i< index){
				listReporting.get(i).setStatus(InternalAuditConstants.COMPLETED);
				listReporting.get(i).setUrl(listReporting.get(i).getStatus().equals(InternalAuditConstants.COMPLETED)? "greenCircleNew.png" : "redCircleNew.png");

			}
		}*/
		for(int i=0; i< listReporting.size(); i++){
			
			if(i <= index){

				listReporting.get(i).setUrl( "greenCircleNew.png");
				listReporting.get(i).setStatus(InternalAuditConstants.COMPLETED);

			}else{
				listReporting.get(i).setUrl( "redCircleNew.png");
				listReporting.get(i).setStatus(InternalAuditConstants.NOT_STARTED);;
			}
		}
		
		if(status.isEmpty()){
			listReporting.get(index).setStatus(InternalAuditConstants.NOT_STARTED);
			listReporting.get(index).setUrl( "redCircleNew.png");
		}
	}


	public Widget createGridFieldWork() {

		ColumnConfig<FieldWork, String> imageColumn = new ColumnConfig<FieldWork, String>(fieldWorkProperties.url(), 20, "");
		imageColumn.setCell(new CustomImageCell());

		ColumnConfig<FieldWork, String> fieldworkCol = new ColumnConfig<FieldWork, String>(fieldWorkProperties.name(), 150, "Name");
		//ColumnConfig<FieldWork, Date> lastTransCol = new ColumnConfig<FieldWork, Date>(fieldWorkProperties.date(), 100, "Date");
		ColumnConfig<FieldWork, String> statusCol = new ColumnConfig<FieldWork, String>(fieldWorkProperties.status(), 150, "Status");

		//lastTransCol.setCell(new DateCell(DateTimeFormat.getFormat("MM/dd/yyyy")));

		statusCol.setCell(new AbstractCell<String>() {
			@Override
			public void render(Context context, String value, SafeHtmlBuilder sb) {
				String style = "style='color: " + (value.equals(InternalAuditConstants.COMPLETED) ? "green" : "red") + "'";
				sb.appendHtmlConstant("<span " + style + ">" + value + "</span>");
			}
		});

		List<ColumnConfig<FieldWork, ?>> columns = new ArrayList<ColumnConfig<FieldWork, ?>>();
		columns.add(imageColumn);
		columns.add(fieldworkCol);
		//columns.add(lastTransCol);
		columns.add(statusCol);

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

		ColumnConfig<Planning, String> imageColumn = new ColumnConfig<Planning, String>(planningProperties.url(), 20, "");
		imageColumn.setCell(new CustomImageCell());

		ColumnConfig<Planning, String> nameCol = new ColumnConfig<Planning, String>(planningProperties.name(), 150, "Name");
		//ColumnConfig<Planning, Date> dateCol = new ColumnConfig<Planning, Date>(planningProperties.date(), 100, "Date");
		ColumnConfig<Planning, String> statusCol = new ColumnConfig<Planning, String>(planningProperties.status(), 150, "Status");

		//dateCol.setCell(new DateCell(DateTimeFormat.getFormat("MM/dd/yyyy")));

		List<ColumnConfig<Planning, ?>> columns = new ArrayList<ColumnConfig<Planning, ?>>();
		columns.add(imageColumn);
		columns.add(nameCol);
		//columns.add(dateCol);
		columns.add(statusCol);

		statusCol.setCell(new AbstractCell<String>() {
			@Override
			public void render(Context context, String value, SafeHtmlBuilder sb) {
				String style = "style='color: " + (value.equals(InternalAuditConstants.COMPLETED) ? "green" : "red") + "'";
				sb.appendHtmlConstant("<span " + style + ">" + value + "</span>");
			}
		});

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

		ColumnConfig<Reporting, String> imageColumn = new ColumnConfig<Reporting, String>(reportingProperties.url(), 20, "");
		imageColumn.setCell(new CustomImageCell());

		ColumnConfig<Reporting, String> nameCol = new ColumnConfig<Reporting, String>(reportingProperties.name(), 150, "Name");
		//ColumnConfig<Reporting, Date> dateCol = new ColumnConfig<Reporting, Date>(reportingProperties.date(), 100, "Date");
		ColumnConfig<Reporting, String> statusCol = new ColumnConfig<Reporting, String>(reportingProperties.status(), 150, "Status");

		//dateCol.setCell(new DateCell(DateTimeFormat.getFormat("MM/dd/yyyy")));

		List<ColumnConfig<Reporting, ?>> columns = new ArrayList<ColumnConfig<Reporting, ?>>();
		columns.add(imageColumn);
		columns.add(nameCol);
		//columns.add(dateCol);
		columns.add(statusCol);

		nameCol.setCell(new AbstractCell<String>() {
			@Override
			public void render(Context context, String value, SafeHtmlBuilder sb) {
				if (value == null || value.isEmpty()) {
					sb.append((SafeHtml) new HTML("("));
				} else {
					sb.append(wrapString(value));
				}
			}
		});

		statusCol.setCell(new AbstractCell<String>() {
			@Override
			public void render(Context context, String value, SafeHtmlBuilder sb) {
				String style = "style='color: " + (value.equals(InternalAuditConstants.COMPLETED) ? "green" : "red") + "'";
				sb.appendHtmlConstant("<span " + style + ">" + value + "</span>");
			}
		});

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

	private SafeHtml wrapString(String untrustedString) {
		SafeHtmlBuilder sb = new SafeHtmlBuilder();
		sb.appendHtmlConstant("<span style='white-space: normal;'>");
		sb.appendEscaped(untrustedString);
		sb.appendHtmlConstant("</span>");
		return sb.toSafeHtml();
	}

	private void configPanel(final Portlet portlet) {
		portlet.setCollapsible(true);
		portlet.setAnimCollapse(false);
		//portlet.setShadow(disabled);
		portlet.getHeader().addTool(new ToolButton(ToolButton.GEAR));
		portlet.getHeader().addTool(new ToolButton(ToolButton.CLOSE, new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				portlet.removeFromParent();
			}
		}));
	}
}
