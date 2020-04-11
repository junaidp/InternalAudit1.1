package com.internalaudit.client.view.Reporting;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.StackPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.internalaudit.client.presenter.ReportsPresenter.Display;
import com.sencha.gxt.core.client.dom.ScrollSupport.ScrollMode;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.container.AccordionLayoutContainer;
import com.sencha.gxt.widget.core.client.container.AccordionLayoutContainer.AccordionLayoutAppearance;
import com.sencha.gxt.widget.core.client.container.AccordionLayoutContainer.ExpandMode;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;

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
	AccordionLayoutContainer accordion = new AccordionLayoutContainer();
	ListBox lstDomain;
	ListBox lstDiv;
	ListBox lstRisk;

	public ReportsView(String arg) {
		initWidget(vpnlMain);

		stckReportsContainer.setSize("1200px", "200px");

		report1 = new ReportAuditPlanning(null);
		report2 = new ReportAuditScheduling(null);
		report3 = new ReportAuditEngagement(null);
		report4 = new ReportJobTimeAllocation(null);
		report5 = new ReportAuditExceptions(null);

		AccordionLayoutAppearance appearance = GWT.<AccordionLayoutAppearance>create(AccordionLayoutAppearance.class);

		ContentPanel cp1 = new ContentPanel(appearance);
		cp1.setAnimCollapse(false);
		cp1.setHeadingText("Audit Planning Report");
		VerticalLayoutContainer p1 = new VerticalLayoutContainer();
		p1.setHeight("400px");
		p1.setScrollMode(ScrollMode.AUTOY);
		p1.add(report1);
		cp1.add(p1);

		ContentPanel cp2 = new ContentPanel(appearance);
		cp2.setAnimCollapse(false);
		cp2.setHeadingText("Audit Scheduling Report");
		VerticalLayoutContainer p2 = new VerticalLayoutContainer();
		p2.setHeight("400px");
		p2.setScrollMode(ScrollMode.AUTOY);
		p2.add(report2);
		cp2.add(p2);

		ContentPanel cp3 = new ContentPanel(appearance);
		cp3.setAnimCollapse(false);
		VerticalLayoutContainer p3 = new VerticalLayoutContainer();
		p3.setHeight("400px");
		p3.setScrollMode(ScrollMode.AUTOY);
		p3.add(report4);
		cp3.setHeadingText("Job Time Allocation Report ");
		cp3.add(p3);

		ContentPanel cp4 = new ContentPanel(appearance);
		VerticalLayoutContainer p4 = new VerticalLayoutContainer();
		p4.setHeight("400px");
		p4.setScrollMode(ScrollMode.AUTOY);
		p4.add(report5);
		cp4.setAnimCollapse(false);
		cp4.setHeadingText("Audit Exceptions Report");
		cp4.add(p4);

		accordion.setSize("1200px", "500px");
		// accordion.setWidth("1200px");
		accordion.setExpandMode(ExpandMode.SINGLE_FILL);
		accordion.add(cp1);
		accordion.add(cp2);
		accordion.add(cp3);
		accordion.add(cp4);
		// accordion.setActiveWidget(cp1);

		// stckReportsContainer.add(report1 , "Audit Planning Report");
		// stckReportsContainer.add(report2, "Audit Scheduling Report");
		//// stckReportsContainer.add(report3, "Audit Engagement Report");
		// stckReportsContainer.add(report4, "Job Time Allocation Report");
		// stckReportsContainer.add(report5, "Audit Exceptions Report");

		// ancDetailed.addClickHandler(new ClickHandler() {
		//
		// @Override
		// public void onClick(ClickEvent arg0) {
		//
		// showDetailedCharts();
		//
		// }
		// });

		lstReports.addItem("Report 1", "Report 1");
		lstReports.addItem("Report 2", "Report 2");
		lstReports.addItem("Report 3", "Report 3");
		lstReports.addItem("Report 3", "Report 3");
		lstReports.addItem("Report 4", "Report 4");
		lstReports.addItem("Report 5", "Report 5");

		// reportsContainer.add(stckReportsContainer);
		// vpnlMain.add(reportsContainer);
		vpnlMain.add(accordion);

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

	public AccordionLayoutContainer getAccordion() {
		return accordion;
	}

	public void setAccordion(AccordionLayoutContainer accordion) {
		this.accordion = accordion;
	}

}
