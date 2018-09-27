package com.internalaudit.client.view.Reporting;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.internalaudit.client.view.ButtonRound;
import com.internalaudit.client.view.PopupsView;
import com.internalaudit.client.view.AuditEngagement.LabelHeading;


public class ReportAuditScheduling extends Composite implements IStackWidget{
	
	ListBox lstDomain;
	ListBox lstDiv;
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
	ButtonRound btnSearch = new ButtonRound("Preview");
	
	private ButtonRound btnExportToExcel = new ButtonRound("Export to Excel");
	
	
	ButtonRound btnPrint = new ButtonRound("Print");
	ButtonRound btnEmail = new ButtonRound("Email");
	Anchor ancDetailed = new Anchor("Show Detailed View");
	
	VerticalPanel vpnlMain;
	
	VerticalPanel vpnlTable;
	
	HorizontalPanel btnBelowTable = new HorizontalPanel();
	
	ScrollPanel chartContainer = new ScrollPanel();
	
	VerticalPanel chartPanel = new VerticalPanel();
	
	public ReportAuditScheduling(String fromInternalAudit){
		btnExportToExcel.setWidth("130px");
		btnSearch.setWidth("130px");
		vpnlMain = new VerticalPanel();
		vpnlTable = new VerticalPanel();

		chartPanel.setWidth("800px");
		chartContainer.setSize("800px","500px");
		chartContainer.setWidget(chartPanel);
		chartContainer.scrollToBottom();
		///
		
			//vpnlMain.add(createChart());
		///
		
		HorizontalPanel mainRowContainer = new HorizontalPanel();
		mainRowContainer.setWidth("900px");
		
		mainRowContainer.setHeight("100px");
		
		VerticalPanel btnLine = new VerticalPanel();
	//	btnLine.setWidth("600px");
		//btnLine.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		btnLine.addStyleName("w3-right");
		btnLine.getElement().getStyle().setMarginLeft(430, Unit.PX);
		btnSearch.addStyleName("w3-margin");
		btnExportToExcel.addStyleName("w3-margin");
		btnBelowTable.setVisible(false);
		//btnBelowTable.setWidth("600px");
		btnBelowTable.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);

		
		VerticalPanel vpDomain = new VerticalPanel();
		VerticalPanel vpDiv = new VerticalPanel();
		VerticalPanel vpJobStatus = new VerticalPanel();
		VerticalPanel vpResponsiblePerson = new VerticalPanel();
	
		LabelHeading lbl1 = new LabelHeading();
		lbl1.setText("Domain");
		
		//lbl1.addStyleName("labelHeading");
		
		LabelHeading lbl2 = new LabelHeading();
		lbl2.setText("Division");
		
		//lbl2.addStyleName("labelHeading");
		
		LabelHeading lbl3 = new LabelHeading();
		lbl3.setText("Job Status");
		
		//lbl3.addStyleName("labelHeading");
		
		LabelHeading lbl4 = new LabelHeading();
		lbl4.setText("Responsible Person");
		//lbl4.addStyleName("labelHeading");
		vpDiv.setWidth("130px");
		vpDomain.setWidth("130px");
		vpJobStatus.setWidth("130px");
		vpResponsiblePerson.setWidth("150px");
		//vpDiv.getElement().getStyle().setMarginLeft(20, Unit.PX);
		vpJobStatus.getElement().getStyle().setMarginLeft(20, Unit.PX);
		vpResponsiblePerson.getElement().getStyle().setMarginLeft(20, Unit.PX);
		vpDomain.getElement().getStyle().setMarginLeft(20, Unit.PX);
		vpDomain.add( lbl1 ); 
		
		vpDiv.add( lbl2 ); 
		
		vpJobStatus.add( lbl3 );
		
		vpResponsiblePerson.add(lbl4);
		
		
				 
		lstDomain = new ListBox(); //lstDomain.setMultipleSelect(true); 	lstDomain.addStyleName("listboxStyle");
		lstDiv = new ListBox();   // lstDiv.setMultipleSelect(true);		lstDiv.addStyleName("listboxStyle");
		lstRisk = new ListBox();  // lstRisk.setMultipleSelect(true);		lstRisk.addStyleName("listboxStyle");
		lstEmp = new ListBox();  // lstEmp.setMultipleSelect(true);		lstEmp.addStyleName("listboxStyle");
		
		lstJobStatus = new ListBox();  // lstJobStatus.setMultipleSelect(true);		lstJobStatus.addStyleName("listboxStyle");
		lstDomain.setWidth("130px");
		lstRisk.setWidth("130px");
	
		lstEmp.setWidth("150px");
		lstJobStatus.setWidth("130px");
		lstDiv.setWidth("130px");
		lstDomain.addStyleName(" w3-border");
		lstDiv.addStyleName(" w3-border");
		lstRisk.addStyleName(" w3-border");
		lstEmp.addStyleName(" w3-border");
		lstJobStatus.addStyleName(" w3-border");
		
		lstRisk.addItem("All", "All"); lstRisk.setSelectedIndex(0);
		lstRisk.addItem("High", "High");
		lstRisk.addItem("Medium", "Medium");
		lstRisk.addItem("Low", "Low");
		
		lstJobStatus.addItem("All");
		lstJobStatus.addItem("Planned");
		lstJobStatus.addItem("In Progress");
		lstJobStatus.addItem("Completed");
		lstJobStatus.setSelectedIndex(0);
		lstDomain.addItem("All","All"); lstDomain.setSelectedIndex(0);
		lstDomain.addItem("Strategic","0");
		lstDomain.addItem("Operations","1");
		lstDomain.addItem("Reporting","2");
		lstDomain.addItem("Compliance","3");
			
		lstEmp.addItem("All");
		lstEmp.setSelectedIndex(0);
		lstDiv.addItem("All","All"); lstDiv.setSelectedIndex(0);
		lstDiv.addItem("IT");
		lstDiv.addItem("Finance");
		lstDiv.addItem("Business");
		initWidget(vpnlMain);
		
		vpDomain.add( lstDomain );
		vpDiv.add( lstDiv );
		vpJobStatus.add( lstJobStatus );
		vpResponsiblePerson.add(lstEmp);

		mainRowContainer.add( vpDiv  ); mainRowContainer.add( vpDomain ); mainRowContainer.add(vpResponsiblePerson  ); mainRowContainer.add(vpJobStatus);mainRowContainer.add(btnLine);
		
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
		
		//vpnlMain.add(btnLine);
		
		vpnlMain.add(vpnlTable);
		
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
		PopupsView chartPopup = new PopupsView(chartContainer, "");
		
	}

	private void bind() {
		
		
	}

//
	public ListBox getDivListbox() {
		return lstDiv;
	}
	

	public ButtonRound getBtnSearch() { return btnSearch; }
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

//	public void setBtnSearch(Button btnSearch) {
//		this.btnSearch = btnSearch;
//	}

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
		return "Report 2";
	}


}
