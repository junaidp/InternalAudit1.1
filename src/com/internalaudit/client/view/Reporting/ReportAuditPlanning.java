package com.internalaudit.client.view.Reporting;

import org.moxieapps.gwt.highcharts.client.ChartTitle.Align;

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


public class ReportAuditPlanning extends Composite implements IStackWidget{
	
	ListBox lstDomain;
	ListBox lstDiv;
	ListBox lstRisk;
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
	
	public ReportAuditPlanning(String fromInternalAudit){
		btnExportToExcel.setWidth("130px");
		btnSearch.setWidth("130px");
		btnExportToExcel.addStyleName("w3-margin");
		btnSearch.addStyleName("w3-margin");
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
		
		
		mainRowContainer.setHeight("100px");
		mainRowContainer.setWidth("800px");
		
		VerticalPanel btnLine = new VerticalPanel();
		btnLine.addStyleName("w3-right");
//		btnLine.add(padd);
		btnLine.add(btnSearch);
//		btnLine.add(ancDetailed);
		btnLine.add(btnExportToExcel);
		//btnLine.setWidth("600px");
		//btnLine.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		btnLine.getElement().getStyle().setPaddingLeft(600, Unit.PX);
		
		btnBelowTable.setVisible(false);
		btnBelowTable.setWidth("600px");
		btnBelowTable.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);

		
		VerticalPanel vpDomain = new VerticalPanel();
		vpDomain.setWidth("130px");
		
		VerticalPanel vpDiv = new VerticalPanel();
		vpDiv.setWidth("130px");
		vpDiv.getElement().getStyle().setPaddingLeft(20, Unit.PX);
		vpDiv.addStyleName("w3-left");
		VerticalPanel vpRisk = new VerticalPanel();
		vpRisk.setWidth("130px");
		vpRisk.getElement().getStyle().setPaddingLeft(20, Unit.PX);
	
		LabelHeading lbl1 = new LabelHeading();
		lbl1.setText("Domain");
		
		//lbl1.addStyleName("labelHeading");
		
		LabelHeading lbl2 = new LabelHeading();
		lbl2.setText("Division");
		
		//lbl2.addStyleName("labelHeading");
		
		LabelHeading lbl3 = new LabelHeading();
		lbl3.setText("Risk Assesment");
		
		//lbl3.addStyleName("labelHeading");
		
		vpDomain.add( lbl1 ); 
		
		vpDiv.add( lbl2 ); 
		
		vpRisk.add( lbl3 );
		
		
				 
		lstDomain = new ListBox(); //lstDomainsetMultipleSelect(true); 	//lstDomain.addStyleName("listboxStyle");
		lstDomain.setWidth("130px");
		lstDomain.addStyleName(" w3-border");
		lstDiv = new ListBox(); //   lstDiv.setMultipleSelect(true);		//lstDiv.addStyleName("listboxStyle");
		lstDiv.setWidth("130px");
		lstDiv.addStyleName(" w3-border");
		lstRisk = new ListBox(); //  lstRisk.setMultipleSelect(true);		//lstRisk.addStyleName("listboxStyle");
		lstRisk.addStyleName(" w3-border");
		lstRisk.setWidth("130px");
		lstRisk.setWidth("130px");
		
		lstRisk.addItem("All", "All"); lstRisk.setSelectedIndex(0);
		lstRisk.addItem("High", "High");
		lstRisk.addItem("Medium", "Medium");
		lstRisk.addItem("Low", "Low");
		
		lstDomain.addItem("All","All"); lstDomain.setSelectedIndex(0);
		lstDomain.addItem("Strategic","0");
		lstDomain.addItem("Operations","1");
		lstDomain.addItem("Reporting","2");
		lstDomain.addItem("Compliance","3");
		
		
		lstDiv.addItem("All","All"); lstDiv.setSelectedIndex(0);
		lstDiv.addItem("IT");
		lstDiv.addItem("Finance");
		lstDiv.addItem("Business");
		

		initWidget(vpnlMain);
		
		vpDomain.add( lstDomain );
		vpDiv.add( lstDiv );
		vpRisk.add( lstRisk );

		mainRowContainer.add( vpDomain ); mainRowContainer.add( vpDiv ); mainRowContainer.add( vpRisk ); mainRowContainer.add(btnLine);
		
		vpnlMain.add( mainRowContainer );
		
		mainRowContainer.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
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

		
//		btnBelowTable.add(padd);
//		btnBelowTable.add(btnPrint);
//		btnBelowTable.add(btnEmail);		
		
		//vpnlMain.add(btnLine);
		
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


}
