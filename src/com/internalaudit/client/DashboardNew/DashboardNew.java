package com.internalaudit.client.DashboardNew;

import com.sencha.gxt.widget.core.client.PlainTabPanel;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;

public class DashboardNew extends VerticalLayoutContainer {

	public DashboardNew() {

		layout();
		// setHeight(Window.getClientHeight() - 30);
		// setScrollMode(ScrollMode.AUTOY);
	}

	private void layout() {
		final PlainTabPanel panel = new PlainTabPanel();
		panel.setWidth("1200px");
		panel.setResizeTabs(true);
		
		DashboardAuditWorkStatus dashboardAuditWorkSpace = new DashboardAuditWorkStatus();
		DashboardProjectManagement dashboardProjectManagement = new DashboardProjectManagement();
		panel.add(dashboardAuditWorkSpace, "Audit Workspace");
		panel.add(dashboardProjectManagement, "Project Management");
		// panel.add(dashboardProjectManagement, "New dashboard name");
		add(panel);
	}

}
