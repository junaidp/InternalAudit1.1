package com.internalaudit.client.DashboardNew;

import java.util.HashMap;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.internalaudit.client.InternalAuditService;
import com.internalaudit.client.InternalAuditServiceAsync;
import com.internalaudit.shared.DashBoardNewDTO;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;

public class DashboardProjectManagement extends VerticalLayoutContainer {

	DashboardListBoxes dashboardlistBox = null;

	public DashboardProjectManagement(DashboardListBoxes dashboardlistBox) {
		this.dashboardlistBox = dashboardlistBox;
		loadData();

		dashboardlistBox.getBtnSearch().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				loadData();

			}
		});
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

		hm.put("Process", listBoxProcess);
		hm.put("Domain", listBoxDomain);
		hm.put("Audit", listBoxAudit);
		hm.put("Unit", listBoxUnit);
		hm.put("Resource", listBoxResource);
		hm.put("Division", listBoxDivision);
		hm.put("Risk", listBoxRisk);

		InternalAuditServiceAsync rpcService = GWT.create(InternalAuditService.class);
		rpcService.fetchDashboard(hm, new AsyncCallback<DashBoardNewDTO>() {

			@Override
			public void onFailure(Throwable arg0) {
				Window.alert("fetchDashboard fail");
			}

			@Override
			public void onSuccess(DashBoardNewDTO dashboard) {

				ProjectManagementTimeLine timeLine = new ProjectManagementTimeLine();
				ProjectManagementActualHours actualHours = new ProjectManagementActualHours();
				ProjectManagementDate pmDate = new ProjectManagementDate();
				PortalInformationRequest portalInformation = new PortalInformationRequest(dashboard.getInformationRequests());
				PortalOutstandingCoaching portalOutstanding = new PortalOutstandingCoaching(dashboard.getTodo());
				Label l = new Label("ada");
				// add(portalOutstanding);
				// add(timeLine);
				// add(actualHours);

				DashboardListBoxes dashboardlistBox = new DashboardListBoxes();

				HorizontalPanel mainPanel = new HorizontalPanel();
				HorizontalPanel upperPanel = new HorizontalPanel();
				mainPanel.setWidth("1200px");
				VerticalPanel panelLeft = new VerticalPanel();
				panelLeft.setWidth("500px");
				VerticalPanel panelRight = new VerticalPanel();
				panelRight.setWidth("650%");
				VerticalPanel panelDate = new VerticalPanel();
				panelDate.add(pmDate);
				panelDate.getElement().getStyle().setPaddingLeft(40, Unit.PX);
				panelDate.getElement().getStyle().setPaddingTop(20, Unit.PX);
				//
				panelLeft.add(dashboardlistBox);
				panelLeft.add(timeLine);
				panelLeft.add(actualHours);
				upperPanel.add(dashboardlistBox);
				upperPanel.add(panelDate);
				panelRight.add(portalInformation);
				panelRight.add(portalOutstanding);

				mainPanel.add(panelLeft);
				mainPanel.add(panelRight);
				add(upperPanel);
				add(mainPanel);

			}
		});

	}
}
