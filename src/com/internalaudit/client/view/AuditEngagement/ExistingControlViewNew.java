package com.internalaudit.client.view.AuditEngagement;

import com.google.gwt.dom.client.Style.BorderStyle;
import com.google.gwt.dom.client.Style.FontWeight;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class ExistingControlViewNew implements IsWidget {

	@Override
	public Widget asWidget() {
		VerticalPanel vpn = new VerticalPanel();
		// all the heading labels defined here
		
		Label lblName = new Label("p2p-c1");
		Label lblactivityObjective = new Label("Activity Objective");
		Label lblrisk = new Label("Risks");
		Label lblcontrol = new Label("Controls");
		Label lblriskRatings = new Label("Risk Ratings");
		Label lblapplicability = new Label("Applicability");
		Label lblresidualRisk = new Label("Residual Risks");
		
		// all the data views are defined here data from db
		
		Label lblactivitydata = new Label("Al controets, memoronduma  understonding ond lett8 of intent lhot finonciolly obhgote the compony ore opprowd and outherized os per the Authority Motrix");
		lblactivitydata.setHeight("90px");
		lblactivitydata.setWidth("200px");
		
		Label lblriskdata = new Label("Data from risk textarea will come he hrer");
		lblriskdata.setHeight("90px");
		lblriskdata.setWidth("200px");
		
		TextArea txtAreaControl = new TextArea();
		txtAreaControl.setWidth("200px");
		txtAreaControl.setHeight("90px");
	    txtAreaControl.setText("hello h listen hey hello hi");
        
        ListBox listBoxRating = new ListBox();
        listBoxRating.addItem("Low");
        listBoxRating.addItem("Medium");
        listBoxRating.addItem("High");

         CheckBox checkBoxApplicability = new CheckBox("checkbox");
         
         ListBox listBoxResidualRating = new ListBox();
         listBoxResidualRating.addItem("Low");
         listBoxResidualRating.addItem("Medium");
         listBoxResidualRating.addItem("High");
         
         //all the styling defined here
        
        lblactivityObjective.getElement().getStyle().setFontWeight(FontWeight.BOLD);
        lblrisk.getElement().getStyle().setFontWeight(FontWeight.BOLD);
        lblcontrol.getElement().getStyle().setFontWeight(FontWeight.BOLD);
        lblriskRatings.getElement().getStyle().setFontWeight(FontWeight.BOLD);
        lblapplicability.getElement().getStyle().setFontWeight(FontWeight.BOLD);
        lblresidualRisk.getElement().getStyle().setFontWeight(FontWeight.BOLD); 
        lblName.getElement().getStyle().setFontWeight(FontWeight.BOLD);
     
        lblactivityObjective.getElement().getStyle().setMarginLeft(20, Unit.PX);
        lblrisk.getElement().getStyle().setMarginLeft(20, Unit.PX);
        lblcontrol.getElement().getStyle().setMarginLeft(20, Unit.PX);
       lblriskRatings.getElement().getStyle().setMarginLeft(20, Unit.PX);
        lblapplicability.getElement().getStyle().setMarginLeft(20, Unit.PX);
        lblresidualRisk.getElement().getStyle().setMarginLeft(20, Unit.PX);
      
        lblactivitydata.getElement().getStyle().setMarginLeft(20, Unit.PX);
        
      
         lblriskdata.getElement().getStyle().setMarginLeft(20, Unit.PX);
        txtAreaControl.getElement().getStyle().setMarginLeft(20, Unit.PX);
        listBoxRating.getElement().getStyle().setMarginLeft(20, Unit.PX);
        checkBoxApplicability.getElement().getStyle().setMarginLeft(20, Unit.PX);
        listBoxResidualRating.getElement().getStyle().setMarginLeft(20, Unit.PX);
        
        // making flexwidget and adding labels in flex widget
        
        FlexTable flex = new FlexTable();
        flex.setWidget(1,0, lblName);
      //  flex.setWidget(0,1,actv);
        
        flex.setWidget(0,1, lblactivityObjective);
        flex.setWidget(1,1,lblactivitydata);
        
        flex.setWidget(0,2, lblrisk);
        flex.setWidget(1,2,lblriskdata);
        
        flex.setWidget(0,3, lblcontrol);
      flex.setWidget(1,3,txtAreaControl);
      
        flex.setWidget(0,4, lblriskRatings);
      flex.setWidget(1,4,listBoxRating);
      
        flex.setWidget(0,5, lblapplicability);
      flex.setWidget(1,5,checkBoxApplicability);
      
      flex.setWidget(0,6, lblresidualRisk);
      flex.setWidget(1,6,listBoxResidualRating);
      
		return flex;
	}

}