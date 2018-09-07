package com.internalaudit.client.DashboardNew;

import java.util.HashMap;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.internalaudit.client.InternalAuditService;
import com.internalaudit.client.InternalAuditServiceAsync;
import com.internalaudit.shared.DashBoardNewDTO;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;

public class DashboardAuditWorkStatus extends VerticalLayoutContainer {
	DashboardListBoxes dashboardlistBox = new DashboardListBoxes();
	
	public DashboardAuditWorkStatus() {
		
		loadData();
		
		dashboardlistBox.getBtnSearch().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				loadData();
				
		
			
			}
		});
	}

	private void loadData() {
		HashMap<String,String> hm = new HashMap<String,String>();
		String listBoxProcess=	dashboardlistBox.getListBoxProcess().getSelectedValue();
		String listBoxDomain = dashboardlistBox.getListBoxDomain().getSelectedValue();
		String listBoxAudit = dashboardlistBox.getListBoxAudit().getSelectedValue();
		String listBoxUnit = dashboardlistBox.getListBoxUnit().getSelectedValue();
		String listBoxResource = dashboardlistBox.getListBoxResource().getSelectedValue();
		String listBoxDivision = dashboardlistBox.getListBoxDivision().getSelectedValue();
		String listBoxRisk = dashboardlistBox.getListBoxRiskLevel().getSelectedValue();
		
		hm.put("Process",listBoxProcess);
		hm.put("Domain",listBoxDomain);
		hm.put("Audit",listBoxAudit);
		hm.put("Unit",listBoxUnit);
		hm.put("Resource",listBoxResource);
		hm.put("Division",listBoxDivision);
		hm.put("Risk",listBoxRisk);
		
		
		InternalAuditServiceAsync rpcService = GWT.create(InternalAuditService.class);
		rpcService.fetchDashboard(hm,new AsyncCallback<DashBoardNewDTO>() {

			@Override
			public void onFailure(Throwable arg0) {

			}

			@Override
			public void onSuccess(DashBoardNewDTO dashboard) {
				 	clear();
					
					PortalIssue portalIssues = new PortalIssue(dashboard.getExceptions());
					AuditWorkChart auditWorkChart = new AuditWorkChart(dashboard.getAuditWorkStatus());
					AuditImplementationStatusChart auditImplementation = new AuditImplementationStatusChart(
							dashboard.getJobNamesWithExceptionImplementationStatus());
					ExceptionReportingStatusChart exceptionReporting = new ExceptionReportingStatusChart(
							dashboard.getExceptionReportingStatus());
					CompletedAndInprogressExceptions auditPie = new CompletedAndInprogressExceptions(
							dashboard.getCompletedAndInprogressExceptions());
					
					HorizontalPanel mainPanel = new HorizontalPanel();
					VerticalPanel panelLeft = new VerticalPanel();

					panelLeft.setWidth("700px");
					VerticalPanel panelRight = new VerticalPanel();
					panelRight.setWidth("30%");
					 panelLeft.add(dashboardlistBox);
					panelLeft.add(auditWorkChart);
					panelLeft.add(portalIssues);

					panelRight.add(auditPie);
					panelRight.add(auditImplementation);
					panelRight.add(exceptionReporting);

					mainPanel.add(panelLeft);
					mainPanel.add(panelRight);
					add(mainPanel);
			}
		});
	}
}
