package com.internalaudit.client.DashboardNew;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;

public class DashboardAuditWorkStatus extends VerticalLayoutContainer {

	public DashboardAuditWorkStatus(){
	
	
		PortalIssue portalIssues = new PortalIssue();
		AuditWorkChart auditWorkChart = new  AuditWorkChart();
		AuditImplementationStatusChart auditImplementation = new AuditImplementationStatusChart();
		ExceptionReportingStatusChart exceptionReporting = new ExceptionReportingStatusChart();
		AuditPieChart auditPie = new AuditPieChart();
		

		HorizontalPanel mainPanel=  new HorizontalPanel();
		VerticalPanel panelLeft = new VerticalPanel();
		panelLeft.setWidth("750px");
		VerticalPanel panelRight = new VerticalPanel();
		
		
		panelLeft.add(auditWorkChart);
		panelLeft.add(portalIssues);
		
		panelRight.add(auditPie);
		panelRight.add(auditImplementation);
		panelRight.add(exceptionReporting);
		
		mainPanel.add(panelLeft);
		mainPanel.add(panelRight);
		add(mainPanel);
	}
	

}
