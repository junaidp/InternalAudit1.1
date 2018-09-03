package com.internalaudit.client.view.Reporting;

import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.internalaudit.client.view.PopupsView;


public class ReportAuditPlanning extends Composite implements IStackWidget{
	
	ListBox lstDomain;
	ListBox lstDiv;
	ListBox lstRisk;
//
	Button btnSearch = new Button("Preview");
	
	private Button btnExportToExcel = new Button("Export to Excel");
	
	
	Button btnPrint = new Button("Print");
	Button btnEmail = new Button("Email");
	Anchor ancDetailed = new Anchor("Show Detailed View");
	
	VerticalPanel vpnlMain;
	
	VerticalPanel vpnlTable;
	
	HorizontalPanel btnBelowTable = new HorizontalPanel();
	
	ScrollPanel chartContainer = new ScrollPanel();
	
	VerticalPanel chartPanel = new VerticalPanel();
	
	public ReportAuditPlanning(String fromInternalAudit){
		
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
	
		Label lbl1 = new Label("Domain");
		
		lbl1.addStyleName("labelHeading");
		
		Label lbl2 = new Label("Division");
		
		lbl2.addStyleName("labelHeading");
		
		Label lbl3 = new Label("Risk Assessment");
		
		lbl3.addStyleName("labelHeading");
		
		vpDomain.add( lbl1 ); 
		
		vpDiv.add( lbl2 ); 
		
		vpRisk.add( lbl3 );
		
		
				 
		lstDomain = new ListBox(); lstDomain.setMultipleSelect(true); 	lstDomain.addStyleName("listboxStyle");
		lstDiv = new ListBox();    lstDiv.setMultipleSelect(true);		lstDiv.addStyleName("listboxStyle");
		lstRisk = new ListBox();   lstRisk.setMultipleSelect(true);		lstRisk.addStyleName("listboxStyle");
		lstRisk.setWidth("110px");
		
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

		mainRowContainer.add( vpDomain ); mainRowContainer.add( vpDiv ); mainRowContainer.add( vpRisk );
		
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
		PopupsView chartPopup = new PopupsView(chartContainer, "");
		
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


}
