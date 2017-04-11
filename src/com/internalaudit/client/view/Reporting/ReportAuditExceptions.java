package com.internalaudit.client.view.Reporting;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.internalaudit.client.view.PopupsView;
import com.internalaudit.shared.InternalAuditConstants;


public class ReportAuditExceptions extends Composite implements IStackWidget{

	ListBox lstDomain;
	ListBox lstDiv;
	ListBox lstJobs;
	ListBox lstExceptionStatus;
	ListBox lstResource;
	ListBox lstResponsibleAuditee;
	ListBox lstRisk;

	//
	Button btnSearch = new Button("Preview");

	private Button btnExportToExcel = new Button("Export to Excel");


	Button btnPrint = new Button("Print");
	Button btnEmail = new Button("Email");
	//	Anchor ancDetailed = new Anchor("Show Detailed View");

	VerticalPanel vpnlMain;

	VerticalPanel vpnlTable;

	HorizontalPanel btnBelowTable = new HorizontalPanel();

	ScrollPanel chartContainer = new ScrollPanel();

	VerticalPanel chartPanel = new VerticalPanel();

	public ReportAuditExceptions(String fromInternalAudit){

		vpnlMain = new VerticalPanel();
		vpnlTable = new VerticalPanel();
		vpnlMain.setWidth("100%");
		chartPanel.setWidth("100%");

		//		chartPanel.setWidth("800px");
		chartContainer.setSize("100%","500px");
		chartContainer.setWidget(chartPanel);
		chartContainer.scrollToBottom();
		///

		//vpnlMain.add(createChart());
		///

		HorizontalPanel mainRowContainer = new HorizontalPanel();
		mainRowContainer.setWidth("100%");

		mainRowContainer.setHeight("100px");

		HorizontalPanel btnLine = new HorizontalPanel();
		btnLine.setWidth("600px");
		btnLine.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);

		btnBelowTable.setVisible(false);
		btnBelowTable.setWidth("600px");
		btnBelowTable.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);


		VerticalPanel vpDomain = new VerticalPanel();
		VerticalPanel vpDiv = new VerticalPanel();
		VerticalPanel vpRisk = new VerticalPanel();
		VerticalPanel vpResource = new VerticalPanel();
		VerticalPanel vpJobs = new VerticalPanel();
		VerticalPanel vpExceptionStatus = new VerticalPanel();
		VerticalPanel vpResponsibleAuditee = new VerticalPanel();

		Label lbl1 = new Label("Domain");

		lbl1.addStyleName("labelHeading");

		Label lbl2 = new Label("Division");

		lbl2.addStyleName("labelHeading");

		Label lbl3 = new Label("Risk Rating");

		lbl3.addStyleName("labelHeading");
		Label lbl4 = new Label("Resources");

		lbl4.addStyleName("labelHeading");

		Label lbl5 = new Label("Jobs");

		lbl5.addStyleName("labelHeading");

		Label lbl6 = new Label("Exception Status");

		lbl6.addStyleName("labelHeading");

		Label lbl7 = new Label("Responsible Auditee");

		lbl7.addStyleName("labelHeading");

		vpDomain.add( lbl1 ); 
		vpDiv.add( lbl2 ); 
		vpRisk.add( lbl3 );
		vpResource.add( lbl4);
		vpJobs.add( lbl5);
		vpExceptionStatus.add( lbl6);
		vpResponsibleAuditee.add( lbl7);
		



		lstDomain = new ListBox(); lstDomain.setMultipleSelect(true); 	lstDomain.addStyleName("listboxStyle");
		lstDiv = new ListBox();    lstDiv.setMultipleSelect(true);		lstDiv.addStyleName("listboxStyle");
		lstRisk = new ListBox();   lstRisk.setMultipleSelect(true);		lstRisk.addStyleName("listboxStyle");
		lstResource = new ListBox();   lstResource.setMultipleSelect(true);		lstResource.addStyleName("listboxStyle");
		lstJobs = new ListBox();   lstJobs.setMultipleSelect(true);		lstJobs.addStyleName("listboxStyle");
		lstExceptionStatus = new ListBox();   lstExceptionStatus.setMultipleSelect(true);		lstExceptionStatus.addStyleName("listboxStyle");
		lstResponsibleAuditee= new ListBox();   lstResponsibleAuditee.setMultipleSelect(true);		lstResponsibleAuditee.addStyleName("listboxStyle");

		lstRisk.addItem("All", "All"); lstRisk.setSelectedIndex(0);
		lstRisk.addItem("High", "High");
		lstRisk.addItem("Medium", "Medium");
		lstRisk.addItem("Low", "Low");
		lstResource.addItem("All");
		lstDomain.addItem("All","All"); lstDomain.setSelectedIndex(0);
		lstDomain.addItem("Strategic");
		lstDomain.addItem("Operations");
		lstDomain.addItem("Reporting");
		lstDomain.addItem("Compliance");

		lstResource.setSelectedIndex(0);
		lstDiv.addItem("All","All"); lstDiv.setSelectedIndex(0);
		lstDiv.addItem("IT");
		lstDiv.addItem("Finance");
		lstDiv.addItem("Business");

		lstJobs.addItem("All", "All"); lstJobs.setSelectedIndex(0);
		lstExceptionStatus.addItem("All", "All"); lstExceptionStatus.setSelectedIndex(0);
		lstExceptionStatus.addItem(InternalAuditConstants.APPROVEDBYIAHEAD);
		lstExceptionStatus.addItem(InternalAuditConstants.COMMUNICATEDTOMGM);
		lstExceptionStatus.addItem(InternalAuditConstants.AGREEDBYMGM);
		lstExceptionStatus.addItem(InternalAuditConstants.NOTAGREED);
		lstExceptionStatus.addItem(InternalAuditConstants.IMPLEMENTATIONINPROGRESS);
		lstExceptionStatus.addItem(InternalAuditConstants.IMPLEMENTATIONDELAYED);
		lstExceptionStatus.addItem(InternalAuditConstants.IMPLEMENTATIONCOMPLETED);
		
		lstResponsibleAuditee.addItem("All", "All"); lstResponsibleAuditee.setSelectedIndex(0);
		
		initWidget(vpnlMain);

		vpDomain.add( lstDomain );
		vpDiv.add( lstDiv );
		vpRisk.add( lstRisk );
		vpResource.add( lstResource );
		vpJobs.add(lstJobs);
		vpExceptionStatus.add(lstExceptionStatus);
		vpResponsibleAuditee.add(lstResponsibleAuditee);

		mainRowContainer.add( vpDomain ); mainRowContainer.add( vpDiv ); mainRowContainer.add( vpRisk );
		mainRowContainer.add(vpResource);
		mainRowContainer.add(vpJobs);
		mainRowContainer.add(vpExceptionStatus);
		mainRowContainer.add(vpResponsibleAuditee);

		vpnlMain.add( mainRowContainer );

		//		ancDetailed.addClickHandler(new ClickHandler() {
		//			
		//			@Override
		//			public void onClick(ClickEvent arg0) {
		//				
		//				showDetailedCharts();
		//				
		//			}
		//		});

		//		Label padd = new Label("t");
		//		padd.setWidth("350px");
		//		
		//		
		//		btnLine.add(padd);
		btnLine.add(btnSearch);
		//		btnLine.add(ancDetailed);
		btnLine.add(btnExportToExcel);

		//		btnBelowTable.add(padd);
		//		btnBelowTable.add(btnPrint);
		//		btnBelowTable.add(btnEmail);		

		vpnlMain.add(btnLine);

		vpnlMain.add(vpnlTable);
		vpnlMain.setWidth("100%");
		vpnlTable.setWidth("100%");

		//		vpnlMain.add(new Label("design test"));

		//vpnlMain.setHeight("180px");

		bind();

	}

	@SuppressWarnings("unused")
	protected void showDetailedCharts() {

		//create container popup

		//add chart

		//show.
		chartContainer.setWidget(chartPanel);
		PopupsView chartPopup = new PopupsView(chartContainer);

	}

	private void bind() {


	}

	//
	public ListBox getDivListbox() {
		return lstDiv;
	}


	public Button getBtnSearch() { return btnSearch; }
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

	//	public void setBtnSearch(Button btnSearch) {
	//		this.btnSearch = btnSearch;
	//	}

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


}
