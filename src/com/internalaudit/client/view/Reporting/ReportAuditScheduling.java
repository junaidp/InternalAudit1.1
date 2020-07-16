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

public class ReportAuditScheduling extends Composite implements IStackWidget {

	ListBox lstDomain;
	ListBox lstDiv;
	ListBox lstDepartment;
	ListBox lstRisk;
	ListBox lstEmp;
	ListBox lstJobStatus;

	public ListBox getLstJobStatus() {
		return lstJobStatus;
	}

	public void setLstJobStatus(ListBox lstJobStatus) {
		this.lstJobStatus = lstJobStatus;
	}

	//
	Button btnSearch = new Button("Preview");

	public Button btnExportToExcel = new Button("Export to Excel");

	public Button btnExportToPDF = new Button("Export to PDF");

	Button btnPrint = new Button("Print");
	Button btnEmail = new Button("Email");
	Anchor ancDetailed = new Anchor("Show Detailed View");

	VerticalPanel vpnlMain;

	VerticalPanel vpnlTable;

	HorizontalPanel btnBelowTable = new HorizontalPanel();

	ScrollPanel chartContainer = new ScrollPanel();

	VerticalPanel chartPanel = new VerticalPanel();

	VerticalPanel vpnlPerviewData = new VerticalPanel();

	public ReportAuditScheduling(String fromInternalAudit) {
		btnExportToExcel.setWidth("130px");
		btnExportToPDF.setWidth("130px");
		// btnSearch.setWidth("130px");
		vpnlMain = new VerticalPanel();
		vpnlTable = new VerticalPanel();

		chartPanel.setWidth("800px");
		chartContainer.setSize("800px", "500px");
		chartContainer.setWidget(chartPanel);
		chartContainer.scrollToBottom();
		///

		// vpnlMain.add(createChart());
		///
		VerticalPanel vpnlTop = new VerticalPanel();
		HorizontalPanel hpnlRow1 = new HorizontalPanel();
		HorizontalPanel hpnlRow2BtnExport = new HorizontalPanel();
		vpnlTop.add(hpnlRow1);
		vpnlTop.add(vpnlPerviewData);
		vpnlTop.add(hpnlRow2BtnExport);

		// VerticalPanel btnLine = new VerticalPanel();
		// btnLine.setWidth("600px");
		// btnLine.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		// btnLine.addStyleName("w3-right");
		// btnLine.getElement().getStyle().setMarginLeft(430, Unit.PX);
		// btnSearch.addStyleName("w3-margin");
		// btnExportToExcel.addStyleName("w3-margin");
		// btnExportToPDF.addStyleName("w3-margin");
		btnBelowTable.setVisible(false);
		// btnBelowTable.setWidth("600px");
		btnBelowTable.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		LabelBold lbl1 = new LabelBold("Domain:");
		LabelBold lbl2 = new LabelBold("Division:");
		LabelBold lbl3 = new LabelBold("Department:");
		LabelBold lbl4 = new LabelBold("Job Status:");
		LabelBold lbl5 = new LabelBold("Responsible Person:");
		lbl1.setWidth("65px");
		lbl2.setWidth("70px");
		lbl3.setWidth("90px");
		lbl4.setWidth("85px");
		lbl5.setWidth("145px");

		lstDomain = new ListBox(); // lstDomain.setMultipleSelect(true);
									// lstDomain.addStyleName("listboxStyle");
		lstDiv = new ListBox(); // lstDiv.setMultipleSelect(true);
								// lstDiv.addStyleName("listboxStyle");
		lstDepartment = new ListBox();

		lstRisk = new ListBox(); // lstRisk.setMultipleSelect(true);
									// lstRisk.addStyleName("listboxStyle");
		lstEmp = new ListBox(); // lstEmp.setMultipleSelect(true);
								// lstEmp.addStyleName("listboxStyle");
		lstJobStatus = new ListBox(); // lstJobStatus.setMultipleSelect(true);
										// lstJobStatus.addStyleName("listboxStyle");
		lstDomain.setWidth("100px");
		lstRisk.setWidth("150px");

		lstEmp.setWidth("100px");
		lstJobStatus.setWidth("100px");
		lstDiv.setWidth("130px");
		lstDepartment.setWidth("150px");
		lstDomain.addStyleName(" w3-border");
		lstDiv.addStyleName(" w3-border");
		lstDepartment.addStyleName(" w3-border");
		lstRisk.addStyleName(" w3-border");
		lstEmp.addStyleName(" w3-border");
		lstJobStatus.addStyleName(" w3-border");

		lstRisk.addItem("All", "All");
		lstRisk.setSelectedIndex(0);
		lstRisk.addItem("High", "High");
		lstRisk.addItem("Medium", "Medium");
		lstRisk.addItem("Low", "Low");

		lstJobStatus.addItem("All");
		lstJobStatus.addItem("Planned");
		lstJobStatus.addItem("In Progress");
		lstJobStatus.addItem("Completed");
		lstJobStatus.setSelectedIndex(0);
		lstDomain.addItem("All", "All");
		lstDomain.setSelectedIndex(0);
		lstDomain.addItem("Strategic", "0");
		lstDomain.addItem("Operations", "1");
		lstDomain.addItem("Reporting", "2");
		lstDomain.addItem("Compliance", "3");

		lstEmp.addItem("All");
		lstEmp.setSelectedIndex(0);
		lstDiv.addItem("All", "All");
		lstDiv.setSelectedIndex(0);
		
		lstDepartment.addItem("All", "All");
		lstDepartment.setSelectedIndex(0);
//		lstDiv.addItem("IT", "1");
//		lstDiv.addItem("Finance", "2");
//		lstDiv.addItem("Business", "3");
		initWidget(vpnlMain);

		hpnlRow1.getElement().getStyle().setPaddingTop(5, Unit.PX);
		hpnlRow1.add(lbl1);
		hpnlRow1.add(lstDomain);
		hpnlRow1.add(new HTML("&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;"));

		hpnlRow1.add(lbl2);
		hpnlRow1.add(lstDiv);
		hpnlRow1.add(new HTML("&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;"));

		hpnlRow1.add(lbl3);
		hpnlRow1.add(lstDepartment);
		hpnlRow1.add(new HTML("&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;"));

		hpnlRow1.add(lbl4);
		hpnlRow1.add(lstJobStatus);
		hpnlRow1.add(new HTML("&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;"));
		
		hpnlRow1.add(lbl5);
		hpnlRow1.add(lstEmp);
		hpnlRow1.add(new HTML("&nbsp; &nbsp; &nbsp;"));

		vpnlMain.add(vpnlTop);

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
		// btnLine.add(padd);
		hpnlRow1.add(btnSearch);
		// btnLine.add(ancDetailed);
		btnExportToExcel.getElement().getStyle().setMarginLeft(920, Unit.PX);
		hpnlRow2BtnExport.add(btnExportToExcel);
		hpnlRow2BtnExport.add(btnExportToPDF);

		btnExportToExcel.setVisible(false);
		btnExportToPDF.setVisible(false);

		// btnBelowTable.add(padd);
		// btnBelowTable.add(btnPrint);
		// btnBelowTable.add(btnEmail);

		// vpnlMain.add(btnLine);

		vpnlMain.add(vpnlTable);

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

	public ListBox getRiskListbox() {
		return lstRisk;
	}

	public ListBox getEmpListbox() {
		return lstEmp;
	}

	public VerticalPanel getMainPanel() {
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
		return "Report 2";
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
	public ListBox getLstDepartment() {
		return lstDepartment;
	}

	public void setLstDepartment(ListBox lstDepartment) {
		this.lstDepartment = lstDepartment;
	}

}
