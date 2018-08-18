package com.internalaudit.client.DashboardNew;

import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;

public class DashboardAuditWorkStatus extends VerticalLayoutContainer {
	
	public DashboardAuditWorkStatus(){
		HorizontalLayoutContainer hl = new HorizontalLayoutContainer();
		VerticalLayoutContainer v = new VerticalLayoutContainer();
		VerticalLayoutContainer v2 = new VerticalLayoutContainer();
	
		PortalIssue portalIssues = new PortalIssue();
		AuditWorkChart auditWorkChart = new  AuditWorkChart();
		AuditImplementationStatusChart auditImplementation = new AuditImplementationStatusChart();
		ExceptionReportingStatusChart exceptionReporting = new ExceptionReportingStatusChart();
		AuditPieChart auditPie = new AuditPieChart();
		//IssueGrid g = new IssueGrid();
		//v.add(auditWorkChart);
		//v2.add(auditImplementation);
	//	add(g);
		add(portalIssues);
		add(auditWorkChart);
		add(auditPie);;
		add(auditImplementation);
		add(exceptionReporting);
		
		// Add Other charts and Grid here ...
		
		
	}
	

}
