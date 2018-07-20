package com.internalaudit.client.view.AuditEngagement;

import com.google.gwt.dom.client.Style.BorderStyle;
import com.google.gwt.dom.client.Style.FontWeight;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.internalaudit.shared.SuggestedControls;

public class ExistingControlViewNew implements IsWidget {
	//Label lblactivityObjective = new Label("Activity Objective");
	Label lblrisk = new Label("Risks");
	Label lblriskRatings = new Label("Risk Ratings");
	Label lblcontrol = new Label("Controls");
	Label lblapplicability = new Label("Applicability");
	Label lblresidualRisk = new Label("Residual Risks");
	// data labels
	//Label lblactivitydata = new Label("");
	Label lblriskdata = new Label("");
	ListBox listBoxRating = new ListBox();
	private TextArea txtAreaControl = new TextArea();
	
	@Override
	public Widget asWidget() {
		VerticalPanel vpn = new VerticalPanel();
		// all the heading labels defined here

		Label lblName = new Label("p2p-c1");
		lblName.addStyleName("w3-panel w3-light-blue");

		//lblactivityObjective.addStyleName("w3-panel w3-light-blue");

		lblrisk.addStyleName("w3-panel w3-light-blue");

		lblcontrol.addStyleName("w3-panel w3-light-blue");

		lblriskRatings.addStyleName("w3-panel w3-light-blue");

		lblapplicability.addStyleName("w3-panel w3-light-blue");

		lblresidualRisk.addStyleName("w3-panel w3-light-blue");


		// all the data views are defined here data from db

		//lblactivitydata.setHeight("90px");
		//lblactivitydata.setWidth("200px");

		lblriskdata.setHeight("90px");
		lblriskdata.setWidth("200px");

		
		txtAreaControl.setWidth("200px");
		txtAreaControl.setHeight("90px");
		txtAreaControl.setText("");


		listBoxRating.addItem("Low");
		listBoxRating.addItem("Medium");
		listBoxRating.addItem("High");

		CheckBox checkBoxApplicability = new CheckBox("");

		ListBox listBoxResidualRating = new ListBox();
		listBoxResidualRating.addItem("Low");
		listBoxResidualRating.addItem("Medium");
		listBoxResidualRating.addItem("High");

		//all the styling defined here

	//	lblactivityObjective.getElement().getStyle().setFontWeight(FontWeight.BOLD);
		lblrisk.getElement().getStyle().setFontWeight(FontWeight.BOLD);
		lblcontrol.getElement().getStyle().setFontWeight(FontWeight.BOLD);
		lblriskRatings.getElement().getStyle().setFontWeight(FontWeight.BOLD);
		lblapplicability.getElement().getStyle().setFontWeight(FontWeight.BOLD);
		lblresidualRisk.getElement().getStyle().setFontWeight(FontWeight.BOLD); 
		lblName.getElement().getStyle().setFontWeight(FontWeight.BOLD);

	//	lblactivityObjective.getElement().getStyle().setMarginLeft(20, Unit.PX);
		lblrisk.getElement().getStyle().setMarginLeft(20, Unit.PX);
		lblcontrol.getElement().getStyle().setMarginLeft(20, Unit.PX);
		lblriskRatings.getElement().getStyle().setMarginLeft(20, Unit.PX);
		lblapplicability.getElement().getStyle().setMarginLeft(20, Unit.PX);
		lblresidualRisk.getElement().getStyle().setMarginLeft(20, Unit.PX);

		//lblactivitydata.getElement().getStyle().setMarginLeft(20, Unit.PX);


		lblriskdata.getElement().getStyle().setMarginLeft(20, Unit.PX);
		txtAreaControl.getElement().getStyle().setMarginLeft(20, Unit.PX);
		listBoxRating.getElement().getStyle().setMarginLeft(20, Unit.PX);
		checkBoxApplicability.getElement().getStyle().setMarginLeft(20, Unit.PX);
		listBoxResidualRating.getElement().getStyle().setMarginLeft(20, Unit.PX);

		// making flexwidget and adding labels in flex widget

		FlexTable flex = new FlexTable();
		flex.setWidget(1,0, lblName);
		//  flex.setWidget(0,1,actv);

		//flex.setWidget(0,1, lblactivityObjective);
		VerticalPanel vpLblactivitydata = new VerticalPanel();
		//vpLblactivitydata.add(lblactivitydata);
		//vpLblactivitydata.setWidth("220px");
		flex.setWidget(1,1,vpLblactivitydata);

		flex.setWidget(0,2, lblrisk);
		VerticalPanel vpLblRisk = new VerticalPanel();
		vpLblRisk.add(lblriskdata);
		vpLblRisk.setWidth("220px");
		flex.setWidget(1,2,vpLblRisk);

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

	public void hideElemetns(){

	
		lblapplicability.setVisible(false);
	//	lblactivityObjective.setVisible(false);
		lblrisk.setVisible(false);
		lblriskRatings.setVisible(false);
		lblcontrol.setVisible(false);
		lblriskdata.setVisible(false);
		

		//lblactivitydata.setVisible(false);

		listBoxRating.setVisible(false);
	}

	public void setData(SuggestedControls suggestedControls, boolean riskAdded) {
		txtAreaControl.setText(suggestedControls.getSuggestedControlsName());
		lblriskdata.setText(suggestedControls.getRiskId().getRiskname());
		// TODO Populate all fields, whatever is missing ,add in db
		
		if(riskAdded){
			lblriskdata.setVisible(false);	
			lblriskRatings.setVisible(false);
		}
		
	}





}