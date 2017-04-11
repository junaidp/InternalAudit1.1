package com.internalaudit.client.view.Reporting;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.StackPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.internalaudit.client.presenter.ReportsPresenter.Display;

public class ReportsView extends Composite implements Display {
	
	
	ListBox lstReports = new ListBox();
	
	VerticalPanel reportsContainer = new VerticalPanel();
	
	StackPanel stckReportsContainer = new StackPanel();
	
	VerticalPanel vpnlMain = new VerticalPanel();
	
	HorizontalPanel mainRowContainer = new HorizontalPanel();
	
	ReportAuditPlanning report1;
	ReportAuditScheduling report2;
	ReportAuditEngagement report3;
	ReportJobTimeAllocation report4;
	ReportAuditExceptions report5;
	
	ListBox lstDomain;
	ListBox lstDiv;
	ListBox lstRisk;
	
	public ReportsView(String arg)
	{
		initWidget(vpnlMain);
	
		stckReportsContainer.setSize("1200px","200px");
		
		report1 = new ReportAuditPlanning(null);
		
		report2 = new ReportAuditScheduling(null);
		report3 = new ReportAuditEngagement(null);
		report4 = new ReportJobTimeAllocation(null);
		report5 = new ReportAuditExceptions(null);
		
		stckReportsContainer.add(report1 , "Audit Planning Report");
		stckReportsContainer.add(report2, "Audit Scheduling Report");
//		stckReportsContainer.add(report3, "Audit Engagement Report");
		stckReportsContainer.add(report4, "Job Time Allocation Report");
		stckReportsContainer.add(report5, "Audit Exceptions Report");
		
//		ancDetailed.addClickHandler(new ClickHandler() {
//					
//					@Override
//					public void onClick(ClickEvent arg0) {
//						
//						showDetailedCharts();
//						
//					}
//				});

		lstReports.addItem("Report 1","Report 1");
		lstReports.addItem("Report 2","Report 2");
		lstReports.addItem("Report 3","Report 3");
		lstReports.addItem("Report 3","Report 3");
		lstReports.addItem("Report 4","Report 4");
		lstReports.addItem("Report 5","Report 5");
		
		reportsContainer.add(stckReportsContainer);
		vpnlMain.add(reportsContainer);
		
		
		
	}

	@Override
	public ListBox getSelectionBox() {
		// TODO Auto-generated method stub
		return lstReports;
	}

	@Override
	public VerticalPanel getReportsContainer() {
		// TODO Auto-generated method stub
		return reportsContainer;
	}
	
	public HorizontalPanel getListBoxesContainer() {
		return mainRowContainer;
	}

	@Override
	public ListBox getDivListbox() {
		// TODO Auto-generated method stub
		return lstDiv;
	}

	@Override
	public ListBox getDomainListbox() {
		// TODO Auto-generated method stub
		return lstDomain;
	}

	@Override
	public ListBox getRiskListbox() {
		// TODO Auto-generated method stub
		return lstRisk;
	}

	@Override
	public Button getBtnSearch() {
		// TODO Auto-generated method stub
		return null;
	}
	
	protected void showDetailedCharts() {
		
		
		Window.alert("un implemented");
		
	}
	
	@Override
	public StackPanel getStackReports() {
	
		return stckReportsContainer;
	}

	public ReportAuditPlanning getReport1() {
		return report1;
	}

	public void setReport1(ReportAuditPlanning report1) {
		this.report1 = report1;
	}

	public ReportAuditScheduling getReport2() {
		return report2;
	}

	public void setReport2(ReportAuditScheduling report2) {
		this.report2 = report2;
	}

	public ReportAuditEngagement getReport3() {
		return report3;
	}

	public void setReport3(ReportAuditEngagement report3) {
		this.report3 = report3;
	}

	public ReportJobTimeAllocation getReport4() {
		return report4;
	}

	public void setReport4(ReportJobTimeAllocation report4) {
		this.report4 = report4;
	}

	public ReportAuditExceptions getReport5() {
		return report5;
	}

	public void setReport5(ReportAuditExceptions report5) {
		this.report5 = report5;
	}
	
	
}
