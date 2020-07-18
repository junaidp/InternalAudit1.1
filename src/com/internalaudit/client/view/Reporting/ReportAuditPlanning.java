package com.internalaudit.client.view.Reporting;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.internalaudit.client.view.PopupsView;
import com.internalaudit.client.view.AuditEngagement.LabelBold;
import com.sencha.gxt.core.client.dom.ScrollSupport.ScrollMode;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;

public class ReportAuditPlanning extends Composite implements IStackWidget {

	ListBox lstDomain;
	ListBox lstDiv;
	ListBox lstRisk;
	ListBox lstDepartment;

	public ListBox getLstDepartment() {
		return lstDepartment;
	}

	public void setLstDepartment(ListBox lstDepartment) {
		this.lstDepartment = lstDepartment;
	}

	Button btnSearch = new Button("Preview");

	public Button btnExportToExcel = new Button("Export to Excel");
	public Button btnExportToPDF = new Button("Export to PDF");

	Button btnPrint = new Button("Print");
	Button btnEmail = new Button("Email");
	Anchor ancDetailed = new Anchor("Show Detailed View");

	VerticalLayoutContainer vpnlMain;

	VerticalPanel vpnlTable;

	HorizontalPanel btnBelowTable = new HorizontalPanel();

	ScrollPanel chartContainer = new ScrollPanel();

	VerticalPanel chartPanel = new VerticalPanel();

	VerticalPanel vpnlPerviewData = new VerticalPanel();

	public ReportAuditPlanning(String fromInternalAudit) {
		btnExportToExcel.setWidth("130px");
		// btnSearch.setWidth("130px");
		// btnExportToExcel.addStyleName("w3-margin");
		btnExportToPDF.setWidth("130px");
		// btnExportToPDF.addStyleName("w3-margin");
		// btnSearch.addStyleName("w3-margin");
		vpnlMain = new VerticalLayoutContainer();
		vpnlTable = new VerticalPanel();
		vpnlMain.setScrollMode(ScrollMode.AUTOX);
		vpnlMain.setWidth("90%");
	
		chartPanel.setWidth("100%");

		// chartPanel.setWidth("800px");
		chartContainer.setSize("100%", "500px");
		chartContainer.setWidget(chartPanel);
		chartContainer.scrollToBottom();
		///

		// vpnlMain.add(createChart());
		///
		VerticalPanel vpnlRows = new VerticalPanel();
		HorizontalPanel mainRowContainer = new HorizontalPanel();
		vpnlRows.add(mainRowContainer);

		vpnlRows.add(vpnlPerviewData);

		HorizontalPanel hpnlBtnExport = new HorizontalPanel();
		vpnlRows.add(hpnlBtnExport);
		btnExportToExcel.getElement().getStyle().setMarginLeft(910, Unit.PX);
		// mainRowContainer.setHeight("100px");
		// mainRowContainer.setWidth("800px");

		// VerticalPanel btnLine = new VerticalPanel();
		// btnLine.addStyleName("w3-right");
		// btnLine.add(padd);
		// btnLine.add(btnSearch);
		// btnLine.add(ancDetailed);
		btnExportToPDF.setVisible(false);
		btnExportToExcel.setVisible(false);
		hpnlBtnExport.add(btnExportToExcel);
		hpnlBtnExport.add(btnExportToPDF);
		// btnLine.setWidth("600px");
		// btnLine.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		// btnLine.getElement().getStyle().setPaddingLeft(600, Unit.PX);

		btnBelowTable.setVisible(false);
		btnBelowTable.setWidth("600px");
		btnBelowTable.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);

		// VerticalPanel vpDomain = new VerticalPanel();
		// vpDomain.setWidth("130px");
		// VerticalPanel vpDiv = new VerticalPanel();
		// vpDiv.setWidth("130px");
		// vpDiv.getElement().getStyle().setPaddingLeft(20, Unit.PX);
		// vpDiv.addStyleName("w3-left");
		// VerticalPanel vpRisk = new VerticalPanel();
		// vpRisk.setWidth("130px");
		// vpRisk.getElement().getStyle().setPaddingLeft(20, Unit.PX);

		LabelBold lbl1 = new LabelBold("Domain:");
		// lbl1.setText("Domain:");

		// lbl1.addStyleName("labelHeading");

		LabelBold lbl2 = new LabelBold("Division:");
		
		LabelBold lbl3 = new LabelBold("Department:");
		// lbl2.setText("Division:");

		// lbl2.addStyleName("labelHeading");

		LabelBold lbl4 = new LabelBold("Risk Assesment:");
		// lbl3.setText("Risk Assesment:");

		// lbl3.addStyleName("labelHeading");

		// vpDomain.add(lbl1);

		// vpDiv.add(lbl2);

		// vpRisk.add(lbl3);

		lstDomain = new ListBox(); // lstDomainsetMultipleSelect(true);
		lstDomain.setWidth("150px");
		lstDomain.addStyleName(" w3-border");
		lstDiv = new ListBox(); // lstDiv.setMultipleSelect(true);
								// //lstDiv.addStyleName("listboxStyle");
		lstDiv.setWidth("150px");
		lstDiv.addStyleName(" w3-border");
		lstRisk = new ListBox(); // lstRisk.setMultipleSelect(true);
									// //lstRisk.addStyleName("listboxStyle");
		lstRisk.addStyleName(" w3-border");
		lstRisk.setWidth("150px");

		lstRisk.addItem("All", "All");
		lstRisk.setSelectedIndex(0);
		lstRisk.addItem("High", "High");
		lstRisk.addItem("Medium", "Medium");
		lstRisk.addItem("Low", "Low");

		lstDomain.addItem("All", "All");
		lstDomain.setSelectedIndex(0);
		lstDomain.addItem("Strategic", "0");
		lstDomain.addItem("Operations", "1");
		lstDomain.addItem("Reporting", "2");
		lstDomain.addItem("Compliance", "3");

		lstDiv.addItem("All", "All");
		lstDiv.setSelectedIndex(0);
		
		lstDepartment = new ListBox(); // lstRisk.setMultipleSelect(true);
		// //lstRisk.addStyleName("listboxStyle");
		lstDepartment.addStyleName(" w3-border");
		lstDepartment.setWidth("150px");
		
		lstDepartment.addItem("All", "All");
		lstDepartment.setSelectedIndex(0);

//		lstDiv.addItem("IT", "1");
//		lstDiv.addItem("Finance", "2");
//		lstDiv.addItem("Business", "3");

		initWidget(vpnlMain);

		// vpDomain.add(lstDomain);
		// vpDiv.add(lstDiv);
		// vpRisk.add(lstRisk);

		// mainRowContainer.add(vpDomain);
		// mainRowContainer.add(vpDiv);
		// mainRowContainer.add(vpRisk);
		lbl1.setWidth("70px");
		lbl2.setWidth("70px");
		lbl3.setWidth("90px");
		lbl4.setWidth("120px");
		mainRowContainer.getElement().getStyle().setPaddingTop(5, Unit.PX);

		mainRowContainer.add(lbl1);
		mainRowContainer.add(lstDomain);
		mainRowContainer.add(new HTML("&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;"));

		mainRowContainer.add(lbl2);
		mainRowContainer.add(lstDiv);
		mainRowContainer.add(new HTML("&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;"));

		mainRowContainer.add(lbl3);
		mainRowContainer.add(lstDepartment);
		mainRowContainer.add(new HTML("&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;"));

		mainRowContainer.add(lbl4);
		mainRowContainer.add(lstRisk);
		mainRowContainer.add(new HTML("&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;"));
		
		//btnSearch.getElement().getStyle().setMarginLeft(270, Unit.PX);
		mainRowContainer.add(btnSearch);
		vpnlRows.add(hpnlBtnExport);

		vpnlMain.add(vpnlRows);

		mainRowContainer.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
		// ancDetailed.addClickHandler(new ClickHandler() {
		//
		// @Override
		// public void onClick(ClickEvent arg0) {
		//
		// showDetailedCharts();
		//
		// }
		// });

		// Label padd = new Label("t");
		// padd.setWidth("350px");
		//
		//

		// btnBelowTable.add(padd);
		// btnBelowTable.add(btnPrint);
		// btnBelowTable.add(btnEmail);

		// vpnlMain.add(btnLine);

		vpnlMain.add(vpnlTable);
		vpnlMain.setWidth("100%");
		vpnlTable.setWidth("100%");

		// vpnlMain.add(new Label("design test"));

		// vpnlMain.setHeight("180px");

		bind();

	}

	@SuppressWarnings("unused")
	protected void showDetailedCharts() {

		// create container popup

		// add chart

		// show.
		chartContainer.setWidget(chartPanel);
		PopupsView chartPopup = new PopupsView(chartContainer, "");

	}

	private void bind() {

	}

	//
	public ListBox getDivListbox() {
		return lstDiv;
	}

	public Button getBtnSearch() {
		return btnSearch;
	}

	//
	//
	public ListBox getDomainListbox() {
		return lstDomain;
	}

	//
	public ListBox getRiskListbox() {
		return lstRisk;
	}

	public VerticalLayoutContainer getMainPanel() {
		return vpnlMain;
	}

	public void showButtonBelow() {

		btnBelowTable.setVisible(true);
		vpnlMain.add(btnBelowTable);
	}

	public VerticalPanel getTablePanel() {

		return vpnlTable;
	}

	// public void setBtnSearch(Button btnSearch) {
	// this.btnSearch = btnSearch;
	// }

	public Button getBtnExportToExcel() {
		return btnExportToExcel;
	}

	public void setBtnExportToExcel(Button btnExportToExcel) {
		this.btnExportToExcel = btnExportToExcel;
	}

	public VerticalPanel getDetailedChartsView() {
		// TODO Auto-generated method stub
		return chartPanel;
	}

	@Override
	public String getReportType() {
		return "Report 1";
	}

	public Button getBtnExportToPDF() {
		return btnExportToPDF;
	}

	public void setBtnExportToPDF(Button btnExportToPDF) {
		this.btnExportToPDF = btnExportToPDF;
	}

	public VerticalPanel getVpnlPerviewData() {
		return vpnlPerviewData;
	}

	public void setVpnlPerviewData(VerticalPanel vpnlPerviewData) {
		this.vpnlPerviewData = vpnlPerviewData;
	}

}
