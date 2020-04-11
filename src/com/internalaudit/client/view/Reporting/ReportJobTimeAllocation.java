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

public class ReportJobTimeAllocation extends Composite implements IStackWidget {

	ListBox lstDomain;
	ListBox lstDiv;
	ListBox lstRisk;
	ListBox lstResource;
	//
	Button btnSearch = new Button("Preview");

	private Button btnExportToExcel = new Button("Export to Excel");

	private Button btnExportToPDF = new Button("Export to PDF");

	Button btnPrint = new Button("Print");
	Button btnEmail = new Button("Email");
	// Anchor ancDetailed = new Anchor("Show Detailed View");

	VerticalPanel vpnlMain;

	VerticalPanel vpnlTable;

	HorizontalPanel btnBelowTable = new HorizontalPanel();

	ScrollPanel chartContainer = new ScrollPanel();

	VerticalPanel chartPanel = new VerticalPanel();

	VerticalPanel vpnlPerviewData = new VerticalPanel();

	public ReportJobTimeAllocation(String fromInternalAudit) {
		// btnSearch.addStyleName("w3-margin");
		// btnExportToExcel.addStyleName("w3-margin");
		btnExportToExcel.setWidth("130px");
		// btnExportToPDF.addStyleName("w3-margin");
		btnExportToPDF.setWidth("130px");
		// btnSearch.setWidth("130px");

		vpnlMain = new VerticalPanel();
		vpnlTable = new VerticalPanel();
		vpnlMain.setWidth("100%");
		chartPanel.setWidth("100%");

		// chartPanel.setWidth("800px");
		chartContainer.setSize("100%", "500px");
		chartContainer.setWidget(chartPanel);
		chartContainer.scrollToBottom();

		VerticalPanel vpnlRowsData = new VerticalPanel();
		HorizontalPanel mainRowContainer = new HorizontalPanel();
		HorizontalPanel mainBtnExport = new HorizontalPanel();

		vpnlRowsData.add(mainRowContainer);
		vpnlRowsData.add(vpnlPerviewData);
		vpnlRowsData.add(mainBtnExport);

		btnBelowTable.setVisible(false);
		btnBelowTable.setWidth("600px");
		btnBelowTable.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);

		mainRowContainer.getElement().getStyle().setPaddingTop(5, Unit.PX);

		LabelBold lbl1 = new LabelBold("Domain:");
		lbl1.setWidth("70px");

		LabelBold lbl2 = new LabelBold("Division:");
		lbl2.setWidth("75px");

		LabelBold lbl3 = new LabelBold("Risk Rating:");
		lbl3.setWidth("95px");

		LabelBold lbl4 = new LabelBold("Resource:");
		lbl4.setWidth("80px");

		lstDomain = new ListBox(); // lstDomain.setMultipleSelect(true);
									// lstDomain.addStyleName("listboxStyle");
		lstDiv = new ListBox(); // lstDiv.setMultipleSelect(true);
								// lstDiv.addStyleName("listboxStyle");
		lstRisk = new ListBox(); // lstRisk.setMultipleSelect(true);
									// lstRisk.addStyleName("listboxStyle");
		lstResource = new ListBox(); // lstResource.setMultipleSelect(true);
										// lstResource.addStyleName("listboxStyle");
		lstDomain.addStyleName(" w3-border");
		lstDiv.addStyleName(" w3-border");
		lstRisk.addStyleName(" w3-border");
		lstResource.addStyleName(" w3-border");
		lstDomain.setWidth("150px");
		lstResource.setWidth("150px");
		lstRisk.setWidth("150px");
		lstDiv.setWidth("150px");

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

		initWidget(vpnlMain);

		mainRowContainer.add(lbl1);
		mainRowContainer.add(lstDomain);
		mainRowContainer.add(new HTML("&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;"));

		mainRowContainer.add(lbl2);
		mainRowContainer.add(lstDiv);
		mainRowContainer.add(new HTML("&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;"));

		mainRowContainer.add(lbl3);
		mainRowContainer.add(lstRisk);
		mainRowContainer.add(new HTML("&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;"));

		mainRowContainer.add(lbl4);
		mainRowContainer.add(lstResource);
		mainRowContainer.add(new HTML("&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;"));

		vpnlMain.add(vpnlRowsData);

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
		mainRowContainer.add(btnSearch);
		btnExportToExcel.getElement().getStyle().setMarginLeft(920, Unit.PX);
		mainBtnExport.add(btnExportToExcel);
		mainBtnExport.add(btnExportToPDF);
		btnExportToExcel.setVisible(false);
		btnExportToPDF.setVisible(false);

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
