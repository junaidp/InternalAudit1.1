package com.internalaudit.client.DashboardNew;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.datepicker.client.DateBox;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;

import javafx.scene.control.DatePicker;


public class ProjectManagementDate extends VerticalLayoutContainer {

	
	public ProjectManagementDate(){
		Label lblStartDate = new Label("Start Date");
		Label lblEndDate = new Label("End Date");
		lblStartDate.addStyleName("w3-panel");
		lblEndDate.addStyleName("w3-panel" );
		lblStartDate.setWidth("120px");
		lblEndDate.setWidth("120px");
		DateBox dpStart = new DateBox();
		DateBox dpEnd = new DateBox();
		dpStart.getElement().getStyle().setMarginLeft(15, Unit.PX);;
		dpEnd.getElement().getStyle().setMarginLeft(15, Unit.PX);
		dpStart.addStyleName("w3-panel w3-light-blue");
		dpEnd.addStyleName("w3-panel w3-light-blue");
		
		HorizontalPanel panelStart = new HorizontalPanel();
		HorizontalPanel panelEnd = new HorizontalPanel();
		panelStart.add(lblStartDate);
		panelStart.add(dpStart);
		panelEnd.add(lblEndDate);
		panelEnd.add(dpEnd);
		
		add(panelStart);
		add(panelEnd);
		
		
		
		
	}
}
