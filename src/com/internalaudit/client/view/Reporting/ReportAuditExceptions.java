package com.internalaudit.client.view.Reporting;

import com.google.gwt.dom.client.Style.Unit;
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
import com.internalaudit.shared.InternalAuditConstants;

public class ReportAuditExceptions extends Composite implements IStackWidget {

	ListBox lstDomain;
	ListBox lstDiv;
	ListBox lstJobs;
	ListBox lstExceptionStatus;
	ListBox lstResource;
	ListBox lstResponsibleAuditee;
	ListBox lstRisk;

	Button btnSearch = new Button("Preview");

	public Button btnExportToExcel = new Button("Export to Excel");
	public Button btnExportToPDF = new Button("Export to PDF");

	Button btnPrint = new Button("Print");
	Button btnEmail = new Button("Email");
	// Anchor ancDetailed = new Anchor("Show Detailed View");

	VerticalPanel vpnlMain;

	VerticalPanel vpnlTable;

	HorizontalPanel btnBelowTable = new HorizontalPanel();

	ScrollPanel chartContainer = new ScrollPanel();

	VerticalPanel chartPanel = new VerticalPanel();

	VerticalPanel vpnlRows = new VerticalPanel();

	VerticalPanel vpnlPerview = new VerticalPanel();

	public ReportAuditExceptions(String fromInternalAudit) {

		btnExportToPDF.setWidth("130px");
		btnExportToExcel.setWidth("130px");
		vpnlMain = new VerticalPanel();
		vpnlTable = new VerticalPanel();
		vpnlMain.setWidth("100%");
		chartPanel.setWidth("100%");

		// chartPanel.setWidth("800px");
		chartContainer.setSize("100%", "500px");
		chartContainer.setWidget(chartPanel);
		chartContainer.scrollToBottom();
		///

		// vpnlMain.add(createChart());
		///

		HorizontalPanel mainRow1 = new HorizontalPanel();
		HorizontalPanel mainRow2 = new HorizontalPanel();

		btnBelowTable.setVisible(false);
		btnBelowTable.setWidth("600px");
		btnBelowTable.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);

		btnExportToExcel.setVisible(false);
		btnExportToPDF.setVisible(false);

		LabelBold lbl1 = new LabelBold("Domain:");
		lbl1.setWidth("65px");

		LabelBold lbl2 = new LabelBold("Division:");
		lbl2.setWidth("70px");

		LabelBold lbl3 = new LabelBold("Risk Rating:");
		lbl3.setWidth("85px");

		LabelBold lbl4 = new LabelBold("Resources:");
		lbl4.setWidth("85px");

		LabelBold lbl5 = new LabelBold("Jobs:");
		lbl5.setWidth("50px");

		LabelBold lbl6 = new LabelBold("Exception Status:");
		lbl6.setWidth("125px");

		LabelBold lbl7 = new LabelBold("Responsible Auditee:");
		lbl7.setWidth("150px");

		lstDomain = new ListBox();// lstDomain.setMultipleSelect(true);
									// lstDomain.addStyleName("listboxStyle");
		lstDiv = new ListBox(); // lstDiv.setMultipleSelect(true);
								// lstDiv.addStyleName("listboxStyle");
		lstRisk = new ListBox(); // lstRisk.setMultipleSelect(true);
									// lstRisk.addStyleName("listboxStyle");
		lstResource = new ListBox(); // lstResource.setMultipleSelect(true);
										// lstResource.addStyleName("listboxStyle");
		lstJobs = new ListBox(); /// lstJobs.setMultipleSelect(true);
									/// lstJobs.addStyleName("listboxStyle");
		lstExceptionStatus = new ListBox(); // lstExceptionStatus.setMultipleSelect(true);
											// lstExceptionStatus.addStyleName("listboxStyle");
		lstResponsibleAuditee = new ListBox(); // lstResponsibleAuditee.setMultipleSelect(true);
												// lstResponsibleAuditee.addStyleName("listboxStyle");
		lstDomain.addStyleName("w3-border");
		lstRisk.addStyleName("w3-border");
		lstResource.addStyleName("w3-border");
		lstJobs.addStyleName("w3-border");
		lstExceptionStatus.addStyleName("w3-border");
		lstResponsibleAuditee.addStyleName("w3-border");
		lstDiv.addStyleName("w3-border");
		lstDomain.setWidth("150px");
		lstDiv.setWidth("150px");
		lstRisk.setWidth("150px");
		lstResource.setWidth("150px");
		lstJobs.setWidth("150px");
		lstExceptionStatus.setWidth("150px");
		lstResponsibleAuditee.setWidth("150px");
		lstRisk.addItem("All", "All");
		lstRisk.setSelectedIndex(0);
		lstRisk.addItem("High", "High");
		lstRisk.addItem("Medium", "Medium");
		lstRisk.addItem("Low", "Low");
		lstResource.addItem("All");
		lstDomain.addItem("All", "All");
		lstDomain.setSelectedIndex(0);
		lstDomain.addItem("Strategic");
		lstDomain.addItem("Operations");
		lstDomain.addItem("Reporting");
		lstDomain.addItem("Compliance");

		lstResource.setSelectedIndex(0);
		lstDiv.addItem("All", "All");
		lstDiv.setSelectedIndex(0);
		lstDiv.addItem("IT");
		lstDiv.addItem("Finance");
		lstDiv.addItem("Business");

		lstJobs.addItem("All", "All");
		lstJobs.setSelectedIndex(0);
		lstExceptionStatus.addItem("All", "All");
		lstExceptionStatus.setSelectedIndex(0);
		lstExceptionStatus.addItem(InternalAuditConstants.APPROVEDBYIAHEAD);
		lstExceptionStatus.addItem(InternalAuditConstants.COMMUNICATEDTOMGM);
		lstExceptionStatus.addItem(InternalAuditConstants.AGREEDBYMGM);
		lstExceptionStatus.addItem(InternalAuditConstants.NOTAGREED);
		lstExceptionStatus.addItem(InternalAuditConstants.IMPLEMENTATIONINPROGRESS);
		lstExceptionStatus.addItem(InternalAuditConstants.IMPLEMENTATIONDELAYED);
		lstExceptionStatus.addItem(InternalAuditConstants.IMPLEMENTATIONCOMPLETED);

		lstResponsibleAuditee.addItem("All", "All");
		lstResponsibleAuditee.setSelectedIndex(0);

		initWidget(vpnlMain);

		mainRow1.add(lbl1);
		mainRow1.add(lstDomain);
		mainRow1.add(new HTML("&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;"));

		mainRow1.add(lbl2);
		mainRow1.add(lstDiv);
		mainRow1.add(new HTML("&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;"));

		mainRow1.add(lbl3);
		mainRow1.add(lstRisk);
		mainRow1.add(new HTML("&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;"));

		mainRow1.add(lbl4);
		mainRow1.add(lstResource);
		mainRow1.add(new HTML("&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;"));

		mainRow2.add(lbl5);
		mainRow2.add(lstJobs);
		mainRow2.add(new HTML("&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;"));

		mainRow2.add(lbl6);
		mainRow2.add(lstExceptionStatus);
		mainRow2.add(new HTML("&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;"));

		mainRow2.add(lbl7);
		mainRow2.add(lstResponsibleAuditee);
		btnSearch.getElement().getStyle().setMarginLeft(250, Unit.PX);
		mainRow2.add(btnSearch);

		mainRow1.getElement().getStyle().setPaddingTop(5, Unit.PX);
		vpnlRows.add(mainRow1);
		vpnlRows.add(mainRow2);
		vpnlRows.add(vpnlPerview);
		HorizontalPanel hpnlExportBtn = new HorizontalPanel();
		hpnlExportBtn.add(btnExportToExcel);
		hpnlExportBtn.add(btnExportToPDF);
		btnExportToExcel.getElement().getStyle().setMarginLeft(920, Unit.PX);
		vpnlRows.add(hpnlExportBtn);
		vpnlMain.add(vpnlRows);

		hpnlExportBtn.add(btnExportToExcel);
		hpnlExportBtn.add(btnExportToPDF);

		// btnBelowTable.add(padd);
		// btnBelowTable.add(btnPrint);
		// btnBelowTable.add(btnEmail);

		// vpnlMain.add(btnLine);

		vpnlMain.add(vpnlTable);
		vpnlMain.setWidth("100%");
		vpnlTable.setWidth("100%");
		vpnlMain.add(new HTML("&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;"));

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
		return "Report 1";
	}

	public ListBox getLstResource() {
		return lstResource;
	}

	public void setLstResource(ListBox lstResource) {
		this.lstResource = lstResource;
	}

	public ListBox getLstJobs() {
		return lstJobs;
	}

	public void setLstJobs(ListBox lstJobs) {
		this.lstJobs = lstJobs;
	}

	public ListBox getLstResponsibleAuditee() {
		return lstResponsibleAuditee;
	}

	public void setLstResponsibleAuditee(ListBox lstResponsibleAuditee) {
		this.lstResponsibleAuditee = lstResponsibleAuditee;
	}

	public ListBox getLstExceptionStatus() {
		return lstExceptionStatus;
	}

	public void setLstExceptionStatus(ListBox lstExceptionStatus) {
		this.lstExceptionStatus = lstExceptionStatus;
	}

	public Button getBtnExportToPDF() {
		return btnExportToPDF;
	}

	public void setBtnExportToPDF(Button btnExportToPDF) {
		this.btnExportToPDF = btnExportToPDF;
	}

	public VerticalPanel getVpnlPerview() {
		return vpnlPerview;
	}

	public void setVpnlPerview(VerticalPanel vpnlPerview) {
		this.vpnlPerview = vpnlPerview;
	}

}
