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

public class KeyRiskViewNew implements IsWidget {
	
	@Override
	public Widget asWidget() {
		VerticalPanel vpn = new VerticalPanel();
		// TODO Auto-generated method stub
		Label lblName = new Label("p2p-c1");
		Label lblactivityObjective = new Label("Activity Objective");
		Label lblrisks = new Label("Risk");
		Label lblriskRatings = new Label("Risk Ratings");
		Label lblapplicability = new Label("Applicability");
		lblrisks.addStyleName("w3-panel w3-pink");
		Label lblactivityObjectiveData = new Label("Al controets, memoronduma  understonding ond lett8 of intent lhot finonciolly obhgote the compony ore opprowd and outherized os per the Authority Motrix");
		lblactivityObjectiveData.setHeight("90px");
		lblactivityObjectiveData.setWidth("220px");
		TextArea txtRisk = new TextArea();
		
		txtRisk.setWidth("300px");
		txtRisk.setHeight("90px");
	    txtRisk.setText("hello h listen hey hello hi");
        
        ListBox listBoxRating = new ListBox();
        listBoxRating.addItem("Low");
        listBoxRating.addItem("Medium");
        listBoxRating.addItem("High");

         CheckBox checkBoxApplicability = new CheckBox("checkbox");
        
         lblactivityObjective.getElement().getStyle().setFontWeight(FontWeight.BOLD);
        lblrisks.getElement().getStyle().setFontWeight(FontWeight.BOLD);
        lblriskRatings.getElement().getStyle().setFontWeight(FontWeight.BOLD);
        lblapplicability.getElement().getStyle().setFontWeight(FontWeight.BOLD);
        lblName.getElement().getStyle().setFontWeight(FontWeight.BOLD);
     
        lblactivityObjective.getElement().getStyle().setMarginLeft(20, Unit.PX);
        lblrisks.getElement().getStyle().setMarginLeft(20, Unit.PX);
       lblriskRatings.getElement().getStyle().setMarginLeft(20, Unit.PX);
        lblapplicability.getElement().getStyle().setMarginLeft(20, Unit.PX);
  
        lblactivityObjectiveData.getElement().getStyle().setMarginLeft(20, Unit.PX);
        txtRisk.getElement().getStyle().setMarginLeft(20, Unit.PX);
        listBoxRating.getElement().getStyle().setMarginLeft(20, Unit.PX);
        checkBoxApplicability.getElement().getStyle().setMarginLeft(20, Unit.PX);
        
        
        FlexTable flex = new FlexTable();
        flex.setWidget(1,0, lblName);
      //  flex.setWidget(0,1,actv);
        
        flex.setWidget(0,1, lblactivityObjective);
        flex.setWidget(1,1,lblactivityObjectiveData);
        
        flex.setWidget(0,2, lblrisks);
      flex.setWidget(1,2,txtRisk);
      
        flex.setWidget(0,3, lblriskRatings);
      flex.setWidget(1,3,listBoxRating);
      
        flex.setWidget(0,4, lblapplicability);
      flex.setWidget(1,4,checkBoxApplicability);
      
		return flex;
	}

}
