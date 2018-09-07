package com.internalaudit.client.DashboardNew;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;
import com.internalaudit.client.InternalAuditService;
import com.internalaudit.client.InternalAuditServiceAsync;
import com.internalaudit.shared.DashBoardNewDTO;
import com.sencha.gxt.widget.core.client.PlainTabPanel;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;

public class DashboardNew extends VerticalLayoutContainer {

	public DashboardNew() {
		layout();
		
	
		

	}

	private void layout() {
		final PlainTabPanel panel = new PlainTabPanel();
		panel.setWidth("1200px");
		panel.setResizeTabs(true);
		DashboardAuditWorkStatus dashboardAuditWorkSpace = new DashboardAuditWorkStatus();
		// here make instance of your new dashboard class (as u made
		// DashboardAuditWorkSpace, create an other for new dashboard and also
		// new chart and grid classes
		//DashboardProjectManagement dashboardProjectManagement =new DashboardProjectManagement(null);
		panel.add(dashboardAuditWorkSpace, "Audit Workspace");
		//panel.add(dashboardProjectManagement, "Project Management");
		
		//panel.add(new Label("new dashboard here"), "New dashboard name");
		add(panel);
	}

}
