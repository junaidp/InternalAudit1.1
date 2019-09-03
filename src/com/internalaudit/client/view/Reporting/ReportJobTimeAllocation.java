package com.internalaudit.client.view.Reporting;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.internalaudit.client.view.ButtonRound;
import com.internalaudit.client.view.PopupsView;
import com.internalaudit.client.view.AuditEngagement.LabelHeading;

public class ReportJobTimeAllocation extends Composite implements IStackWidget {

	ListBox lstDomain;
	ListBox lstDiv;
	ListBox lstRisk;
	ListBox lstResource;
	//
	ButtonRound btnSearch = new ButtonRound("Preview");

	private ButtonRound btnExportToExcel = new ButtonRound("Export to Excel");

	private ButtonRound btnExportToPDF = new ButtonRound("Export to PDF");

	ButtonRound btnPrint = new ButtonRound("Print");
	ButtonRound btnEmail = new ButtonRound("Email");
	// Anchor ancDetailed = new Anchor("Show Detailed View");

	VerticalPanel vpnlMain;

	VerticalPanel vpnlTable;

	HorizontalPanel btnBelowTable = new HorizontalPanel();

	ScrollPanel chartContainer = new ScrollPanel();

	VerticalPanel chartPanel = new VerticalPanel();

	public ReportJobTimeAllocation(String fromInternalAudit) {
		btnSearch.addStyleName("w3-margin");
		btnExportToExcel.addStyleName("w3-margin");
		btnExportToExcel.setWidth("130px");
		btnExportToPDF.addStyleName("w3-margin");
		btnExportToPDF.setWidth("130px");

		btnSearch.setWidth("130px");
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

		HorizontalPanel mainRowContainer = new HorizontalPanel();
		// mainRowContainer.setWidth("100%");

		mainRowContainer.setHeight("100px");

		VerticalPanel btnLine = new VerticalPanel();
		btnLine.addStyleName("w3-right");

		btnLine.getElement().getStyle().setMarginLeft(430, Unit.PX);
		// btnLine.setWidth("600px");
		btnLine.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);

		btnBelowTable.setVisible(false);
		btnBelowTable.setWidth("600px");
		btnBelowTable.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);

		VerticalPanel vpDomain = new VerticalPanel();
		VerticalPanel vpDiv = new VerticalPanel();
		VerticalPanel vpRisk = new VerticalPanel();
		VerticalPanel vpResource = new VerticalPanel();
		vpDomain.setWidth("130px");
		vpDiv.setWidth("130px");
		vpResource.setWidth("130px");
		vpRisk.setWidth("130px");
		vpDiv.getElement().getStyle().setMarginLeft(20, Unit.PX);
		vpRisk.getElement().getStyle().setMarginLeft(20, Unit.PX);
		vpResource.getElement().getStyle().setMarginLeft(20, Unit.PX);
		vpDomain.getElement().getStyle().setMarginLeft(20, Unit.PX);

		LabelHeading lbl1 = new LabelHeading();
		lbl1.setText("Domain");

		// lbl1.addStyleName("labelHeading");

		LabelHeading lbl2 = new LabelHeading();
		lbl2.setText("Division");
		// lbl2.addStyleName("labelHeading");

		LabelHeading lbl3 = new LabelHeading();
		lbl3.setText("Risk Rating");
		// lbl3.addStyleName("labelHeading");
		LabelHeading lbl4 = new LabelHeading();
		lbl4.setText("Resource");

		// lbl4.addStyleName("labelHeading");

		vpDomain.add(lbl1);

		vpDiv.add(lbl2);

		vpRisk.add(lbl3);
		vpResource.add(lbl4);

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
		lstDomain.setWidth("130px");
		lstResource.setWidth("130px");
		lstRisk.setWidth("130px");
		lstDiv.setWidth("130px");

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

		vpDomain.add(lstDomain);
		vpDiv.add(lstDiv);
		vpRisk.add(lstRisk);
		vpResource.add(lstResource);

		mainRowContainer.add(vpDomain);
		mainRowContainer.add(vpDiv);
		mainRowContainer.add(vpRisk);
		mainRowContainer.add(vpResource);
		mainRowContainer.add(btnLine);

		vpnlMain.add(mainRowContainer);

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
		btnLine.add(btnSearch);
		// btnLine.add(ancDetailed);
		btnLine.add(btnExportToExcel);
		btnLine.add(btnExportToPDF);

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

	public ButtonRound getBtnSearch() {
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

	public ButtonRound getBtnExportToExcel() {
		return btnExportToExcel;
	}

	public void setBtnExportToExcel(ButtonRound btnExportToExcel) {
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

	public ButtonRound getBtnExportToPDF() {
		return btnExportToPDF;
	}

	public void setBtnExportToPDF(ButtonRound btnExportToPDF) {
		this.btnExportToPDF = btnExportToPDF;
	}

}
