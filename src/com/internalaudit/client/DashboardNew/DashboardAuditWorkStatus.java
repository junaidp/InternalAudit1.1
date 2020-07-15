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
import com.internalaudit.client.view.LoadingPopup;
import com.internalaudit.shared.DashBoardNewDTO;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;

public class DashboardAuditWorkStatus extends VerticalLayoutContainer {

	private DashboardListBoxes dashboardlistBox;

	public DashboardAuditWorkStatus() {
		
		DashboardListBoxes dashboardlistBox1 = new DashboardListBoxes();
		this.dashboardlistBox = dashboardlistBox1;
		loadData();

		dashboardlistBox.getBtnSearch().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				Window.alert("inside dashboard auditwork");
				loadData();

			}
		});

		// JAVA 8 HANDLER ..
		// dashboardlistBox.getBtnSearch().addClickHandler(Event -> loadData()):

	}

	private void loadData() {
		HashMap<String, String> hm = new HashMap<String, String>();
		String listBoxProcess = dashboardlistBox.getListBoxProcess().getSelectedValue();
		String listBoxDomain = dashboardlistBox.getListBoxDomain().getSelectedValue();
		String listBoxAudit = dashboardlistBox.getListBoxAudit().getSelectedValue();
		String listBoxUnit = dashboardlistBox.getListBoxUnit().getSelectedValue();
		String listBoxResource = dashboardlistBox.getListBoxResource().getSelectedValue();
		String listBoxDivision = dashboardlistBox.getListBoxDivision().getSelectedValue();
		String listBoxRisk = dashboardlistBox.getListBoxRiskLevel().getSelectedValue();
		String listBoxDepartment = dashboardlistBox.getListBoxDepartment().getSelectedValue();

		
		
		hm.put("Process", listBoxProcess);
		hm.put("Domain", listBoxDomain);
		hm.put("Audit", listBoxAudit);
		hm.put("Unit", listBoxUnit);
		hm.put("Resource", listBoxResource);
		hm.put("Department", listBoxDepartment);
		hm.put("Division", listBoxDivision);
		hm.put("Risk", listBoxRisk);

		InternalAuditServiceAsync rpcService = GWT.create(InternalAuditService.class);
		final LoadingPopup loadingpopup = new LoadingPopup();
		loadingpopup.display();

		rpcService.fetchDashboard(hm, new AsyncCallback<DashBoardNewDTO>() {

			@Override
			public void onFailure(Throwable arg0) {
				loadingpopup.remove();
				Window.alert("fetchDashboard fail in DashboardAuditWorkStatus");
			}

			@Override
			public void onSuccess(DashBoardNewDTO dashboard) {
				loadingpopup.remove();
				clear();

				PortalIssue portalIssues = new PortalIssue(dashboard.getExceptions());
				AuditWorkChart auditWorkChart = new AuditWorkChart(dashboard.getAuditWorkStatus());
				AuditImplementationStatusChart auditImplementation = new AuditImplementationStatusChart(
						dashboard.getJobNamesWithExceptionImplementationStatus());
				ExceptionReportingStatusChart exceptionReporting = new ExceptionReportingStatusChart(
						dashboard.getExceptionReportingStatus());
				CompletedAndInprogressExceptions auditPie = new CompletedAndInprogressExceptions(
						dashboard.getCompletedAndInprogressExceptions());

				VerticalPanel mainPanel = new VerticalPanel();
				HorizontalPanel hpnlChartRow1 = new HorizontalPanel();

				// panelLeft.setWidth("700px");
				HorizontalPanel hpnlChartRow2 = new HorizontalPanel();
				// panelRight.setWidth("490px");
				add(dashboardlistBox);
				hpnlChartRow1.add(auditWorkChart);
				hpnlChartRow1.add(auditPie);

				hpnlChartRow2.add(auditImplementation);
				hpnlChartRow2.add(exceptionReporting);

				mainPanel.add(hpnlChartRow1);
				mainPanel.add(hpnlChartRow2);
				add(mainPanel);
				add(portalIssues);
			}
		});
	}
}
