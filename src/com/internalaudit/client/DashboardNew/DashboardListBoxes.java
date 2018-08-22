package com.internalaudit.client.DashboardNew;


import java.util.ArrayList;

import com.google.gwt.dom.client.Style.FontWeight;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;


public class DashboardListBoxes extends VerticalPanel {

	private Label lblDomain= new Label("Domain");
	Label lblProcess = new Label("Process");
	Label lblAudit = new Label("Audit");
	Label lblUnit = new Label("Unit");
	Label lblDivision = new Label("Devision");
	Label lblResource = new Label("Resource");
	Label lblRiskLabel = new Label("Risk Level");
	
	
	ListBox listBoxDomain = new ListBox();
	ListBox listBoxProcess = new ListBox();
	ListBox listBoxAudit = new ListBox();
	ListBox listBoxUnit = new ListBox();
	ListBox listBoxDivision = new ListBox();
	ListBox listBoxResource = new ListBox();
	ListBox listBoxRiskLevel = new ListBox();
	
	
	



	public  DashboardListBoxes() {
		lblDomain.getElement().getStyle().setFontWeight(FontWeight.BOLD);
		lblDomain.addStyleName("w3-panel w3-light-blue");
		
		lblProcess.addStyleName("w3-panel w3-light-blue");
		lblProcess.getElement().getStyle().setFontWeight(FontWeight.BOLD);
		
		
		lblAudit.addStyleName("w3-panel w3-light-blue");
		lblAudit.getElement().getStyle().setFontWeight(FontWeight.BOLD);
		
		lblUnit.addStyleName("w3-panel w3-light-blue");
	    lblUnit.getElement().getStyle().setFontWeight(FontWeight.BOLD);
	    
		lblDivision.addStyleName("w3-panel w3-light-blue");
		lblDivision.getElement().getStyle().setFontWeight(FontWeight.BOLD);
		
		lblResource.addStyleName("w3-panel w3-light-blue");
		lblResource.getElement().getStyle().setFontWeight(FontWeight.BOLD);
		
		lblRiskLabel.addStyleName("w3-panel w3-light-blue");
		lblRiskLabel.getElement().getStyle().setFontWeight(FontWeight.BOLD);
		
		lblProcess.getElement().getStyle().setMarginLeft(10, Unit.PX);
		lblAudit.getElement().getStyle().setMarginLeft(10, Unit.PX);
		lblUnit.getElement().getStyle().setMarginLeft(10, Unit.PX);
		lblDivision.getElement().getStyle().setMarginLeft(10, Unit.PX);
		lblResource.getElement().getStyle().setMarginLeft(10, Unit.PX);
		lblRiskLabel.getElement().getStyle().setMarginLeft(10, Unit.PX);
		
		





		listBoxDomain.addStyleName("w3-panel");
		listBoxDomain.addItem("All", "0");
		listBoxProcess.addItem("All", "0");
		listBoxProcess.addItem("High", "2");
		
		listBoxAudit.addStyleName("w3-panel");
		listBoxAudit.addItem("All", "0");
		listBoxUnit.addStyleName("w3-panel");
		listBoxUnit.addItem("All", "0");
		listBoxDivision.addStyleName("w3-panel");
		listBoxDivision.addItem("All", "0");
		listBoxResource.addStyleName("w3-panel");
		listBoxResource.addItem("All", "0");
		listBoxRiskLevel.addStyleName("w3-panel");
		listBoxRiskLevel.addItem("All", "0");
		
	




		FlexTable flex = new FlexTable();
		
		flex.setWidget(0,0, lblDomain);
		flex.setWidget(1,0,listBoxDomain);
		
		flex.setWidget(0,1, lblProcess);
		flex.setWidget(1,1,listBoxProcess);
		
		flex.setWidget(0,2, lblAudit);
		flex.setWidget(1,2,listBoxAudit);
		
		flex.setWidget(0,3, lblUnit);
		flex.setWidget(1,3,listBoxUnit);
		
		flex.setWidget(0,4, lblDivision);
		flex.setWidget(1,4,listBoxDivision);
		
		flex.setWidget(0,5, lblResource);
		flex.setWidget(1,5,listBoxResource);
		
		flex.setWidget(0,6, lblRiskLabel);
		flex.setWidget(1,6,listBoxRiskLevel);
		
		
		add(flex);
		

	}
	}