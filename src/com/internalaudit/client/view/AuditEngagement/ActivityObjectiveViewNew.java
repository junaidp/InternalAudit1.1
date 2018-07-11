package com.internalaudit.client.view.AuditEngagement;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.BorderStyle;
import com.google.gwt.dom.client.Style.FontWeight;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sun.org.apache.bcel.internal.generic.LADD;

public class ActivityObjectiveViewNew extends Composite {

	// Label LblName = new Label("Detailed Scope and process understanding");
	 
	@UiField
	VerticalPanel panelActivityObjective;
	 
	
	private static ActivityObjectiveViewNewUiBinder uiBinder = GWT.create(ActivityObjectiveViewNewUiBinder.class);

	interface ActivityObjectiveViewNewUiBinder extends UiBinder<Widget, ActivityObjectiveViewNew> {
	}

	public ActivityObjectiveViewNew() {
		initWidget(uiBinder.createAndBindUi(this));
		Label lblHeading = new Label("Detailed Scope and Process Understanding");
		Label lblName = new Label("p2p-c1");
		
		Label lblActivityObjective = new Label("Activity Objective");
		Label lblriskRatings = new Label("Risk Ratings");
		Label lblapplicability = new Label("Applicability");
		
		TextArea txtAreaActivityObj = new TextArea();
		
		txtAreaActivityObj.setWidth("580px");
		txtAreaActivityObj.setHeight("80px");
		txtAreaActivityObj.setText("Al controets, memoronduma  understonding ond lett8 of intent lhot finonciolly obhgote the compony ore opprowd and outherized os per the Authority Motrix");
        
        ListBox listBoxRating = new ListBox();
        listBoxRating.addItem("Low");
        listBoxRating.addItem("Medium");
        listBoxRating.addItem("High");

         CheckBox checkBoxApplicability = new CheckBox("checkbox");
         
         lblHeading.getElement().getStyle().setFontWeight(FontWeight.BOLD);
         lblActivityObjective.getElement().getStyle().setFontWeight(FontWeight.BOLD);
         lblriskRatings.getElement().getStyle().setFontWeight(FontWeight.BOLD);
         lblapplicability.getElement().getStyle().setFontWeight(FontWeight.BOLD);
         lblName.getElement().getStyle().setFontWeight(FontWeight.BOLD);
     
        lblActivityObjective.getElement().getStyle().setMarginLeft(20, Unit.PX);
        lblriskRatings.getElement().getStyle().setMarginLeft(20, Unit.PX);
        lblapplicability.getElement().getStyle().setMarginLeft(20, Unit.PX);
        txtAreaActivityObj.getElement().getStyle().setMarginLeft(20, Unit.PX);
        listBoxRating.getElement().getStyle().setMarginLeft(20, Unit.PX);
        checkBoxApplicability.getElement().getStyle().setMarginLeft(20, Unit.PX);
        
        
        FlexTable flex = new FlexTable();
        flex.setWidget(0, 1, lblHeading);
        flex.setWidget(2,0, lblName);
      //  flex.setWidget(0,1,actv);
        
        flex.setWidget(1,1, lblActivityObjective);
        flex.setWidget(2,1,txtAreaActivityObj);
        
      
      
        flex.setWidget(1,2, lblriskRatings);
      flex.setWidget(2,2,listBoxRating);
      
        flex.setWidget(1,3, lblapplicability);
      flex.setWidget(2,3,checkBoxApplicability);
      
		panelActivityObjective.add(flex);
	}

}
