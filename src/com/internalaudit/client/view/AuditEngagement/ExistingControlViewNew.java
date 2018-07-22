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
import com.internalaudit.client.widgets.AddIcon;
import com.internalaudit.shared.ActivityObjective;
import com.internalaudit.shared.RiskObjective;
import com.internalaudit.shared.SuggestedControls;

public class ExistingControlViewNew extends VerticalPanel {
	Label lblactivityObjective = new Label("Activity Objective");
	Label lblrisk = new Label("Risks");
	Label lblriskRatings = new Label("Risk Ratings");
	Label lblcontrol = new Label("Controls");
	Label lblapplicability = new Label("Applicability");
	Label lblresidualRisk = new Label("Residual Risks");
	Label lblName = new Label("p2p-c1");
	// data labels
	Label lblactivitydata = new Label("");
	Label lblriskdata = new Label("");
	ListBox listBoxRating = new ListBox();
	private TextArea txtAreaControl = new TextArea();
	private RiskObjective riskObjective = new RiskObjective();
	CheckBox checkBoxApplicability = new CheckBox("");
	 private AddIcon btnAdd = new AddIcon();
	ListBox listBoxResidualRating = new ListBox();
	private int suggestedControlsId = 0;
	
	
	public ExistingControlViewNew() {
		

	
		lblName.addStyleName("w3-panel w3-light-blue");

		lblactivityObjective.addStyleName("w3-panel w3-light-blue");

		lblrisk.addStyleName("w3-panel w3-light-blue");

		lblcontrol.addStyleName("w3-panel w3-light-blue");

		lblriskRatings.addStyleName("w3-panel w3-light-blue");

		lblapplicability.addStyleName("w3-panel w3-light-blue");

		lblresidualRisk.addStyleName("w3-panel w3-light-blue");


		// all the data views are defined here data from db

		lblactivitydata.setHeight("90px");
		lblactivitydata.setWidth("200px");

		lblriskdata.setHeight("90px");
		lblriskdata.setWidth("200px");

		
		txtAreaControl.setWidth("200px");
		txtAreaControl.setHeight("90px");
		txtAreaControl.setText("");


		listBoxRating.addItem("Low");
		listBoxRating.addItem("Medium");
		listBoxRating.addItem("High");

		listBoxResidualRating.addItem("Low");
		listBoxResidualRating.addItem("Medium");
		listBoxResidualRating.addItem("High");

		//all the styling defined here

	lblactivityObjective.getElement().getStyle().setFontWeight(FontWeight.BOLD);
		lblrisk.getElement().getStyle().setFontWeight(FontWeight.BOLD);
		lblcontrol.getElement().getStyle().setFontWeight(FontWeight.BOLD);
		lblriskRatings.getElement().getStyle().setFontWeight(FontWeight.BOLD);
		lblriskRatings.setWidth("150px");
		lblapplicability.getElement().getStyle().setFontWeight(FontWeight.BOLD);
		lblresidualRisk.getElement().getStyle().setFontWeight(FontWeight.BOLD); 
		lblresidualRisk.setWidth("140px");
		lblName.getElement().getStyle().setFontWeight(FontWeight.BOLD);

		lblactivityObjective.getElement().getStyle().setMarginLeft(20, Unit.PX);
		lblrisk.getElement().getStyle().setMarginLeft(20, Unit.PX);
		lblName.getElement().getStyle().setMarginLeft(20, Unit.PX);
		lblcontrol.getElement().getStyle().setMarginLeft(20, Unit.PX);
		lblriskRatings.getElement().getStyle().setMarginLeft(20, Unit.PX);
		lblapplicability.getElement().getStyle().setMarginLeft(20, Unit.PX);
		lblapplicability.setWidth("120px");
		lblresidualRisk.getElement().getStyle().setMarginLeft(20, Unit.PX);

		lblactivitydata.getElement().getStyle().setMarginLeft(20, Unit.PX);


		lblriskdata.getElement().getStyle().setMarginLeft(20, Unit.PX);
		txtAreaControl.getElement().getStyle().setMarginLeft(20, Unit.PX);
		listBoxRating.getElement().getStyle().setMarginLeft(50, Unit.PX);
		checkBoxApplicability.getElement().getStyle().setMarginLeft(60, Unit.PX);
		listBoxResidualRating.getElement().getStyle().setMarginLeft(60, Unit.PX);

		// making flexwidget and adding labels in flex widget

		FlexTable flex = new FlexTable();
		VerticalPanel vpLblRef = new VerticalPanel();
		vpLblRef.setWidth("150px");
		vpLblRef.add(lblName);
		flex.setWidget(1,3, vpLblRef);
		//  flex.setWidget(0,1,actv);

		flex.setWidget(0,0, lblactivityObjective);
		VerticalPanel vpLblactivitydata = new VerticalPanel();
		vpLblactivitydata.add(lblactivitydata);
		vpLblactivitydata.setWidth("220px");
		flex.setWidget(1,0,vpLblactivitydata);

		flex.setWidget(0,1, lblrisk);
		VerticalPanel vpLblRisk = new VerticalPanel();
		vpLblRisk.setWidth("220px");
		vpLblRisk.add(lblriskdata);
		flex.setWidget(1,1,vpLblRisk);

		flex.setWidget(0,4, lblcontrol);
		VerticalPanel vpLblControl = new VerticalPanel();
		vpLblControl.setWidth("230px");
		vpLblControl.add(txtAreaControl);
		flex.setWidget(1,4,vpLblControl);
		flex.setWidget(2,5,btnAdd);

		flex.setWidget(0,2, lblriskRatings);
		VerticalPanel vpLblRiskRating = new VerticalPanel();
		vpLblRiskRating.setWidth("230px");
		vpLblRiskRating.add(listBoxRating);
		flex.setWidget(1,2,vpLblRiskRating);

		flex.setWidget(0,5, lblapplicability);
		VerticalPanel vpLblApplicability = new VerticalPanel();
		vpLblApplicability.setWidth("100px");
		vpLblApplicability.add(checkBoxApplicability);
		flex.setWidget(1,5,vpLblApplicability);

		flex.setWidget(0,6, lblresidualRisk);
		flex.setWidget(1,6,listBoxResidualRating);

		add(flex);

	}	

	public void hideElemetns(){

	
		lblapplicability.setVisible(false);
	//	lblactivityObjective.setVisible(false);
		lblrisk.setVisible(false);
		lblriskRatings.setVisible(false);
		lblcontrol.setVisible(false);
		lblriskdata.setVisible(false);
		lblcontrol.setVisible(false);
		lblresidualRisk.setVisible(false);
		lblapplicability.setVisible(false);
		lblcontrol.setVisible(false);
		

		//lblactivitydata.setVisible(false);

		listBoxRating.setVisible(false);
	}

	public void setData(SuggestedControls suggestedControls, boolean riskAdded) {
		txtAreaControl.setText(suggestedControls.getSuggestedControlsName());
		suggestedControlsId = suggestedControls.getSuggestedControlsId();
		// check its right or not..?
		lblactivitydata.setText(suggestedControls.getRiskId().getObjectiveId().getObjectiveName());
		lblriskdata.setText(suggestedControls.getRiskId().getRiskname());
		// TODO Populate all fields, whatever is missing ,add in db
		lblName.setText(suggestedControls.getSuggestedReferenceNo());
		//listBoxRating.setse.setItemText(1, riskObjective.getRiskRating());
		for(int i=0; i< listBoxRating.getItemCount(); i++){
			if(listBoxRating.getValue(i).equals(suggestedControls.getControlRisk())){
				listBoxRating.setSelectedIndex(i);
				break;
			}
		}
		checkBoxApplicability.setChecked(suggestedControls.getChecked());
		
		if(riskAdded){
			lblriskdata.setVisible(false);	
			lblriskRatings.setVisible(false);
			lblactivityObjective.setVisible(false);
			lblrisk.setVisible(false);
			lblactivitydata.setVisible(false);
			listBoxRating.setVisible(false);
			
		}
		
		
	}

	public void getData(SuggestedControls suggestedControls) {
		suggestedControls.setRiskId(riskObjective);
		suggestedControls.setSuggestedControlsId(suggestedControlsId);
		suggestedControls.setSuggestedControlsName(txtAreaControl.getText());
		suggestedControls.setSuggestedReferenceNo(lblName.getText());
		suggestedControls.setChecked(checkBoxApplicability.isChecked());
		suggestedControls.setControlRisk(listBoxResidualRating.getValue(listBoxResidualRating.getSelectedIndex()));
		// extra ading all
	//	suggestedControls.set(listBoxResidualRating.getValue(listBoxResidualRating.getSelectedIndex()));
	}

	public RiskObjective getRiskObjective() {
		return riskObjective;
	}

	public void setRiskObjective(RiskObjective riskObjective) {
		this.riskObjective = riskObjective;
	}

	public AddIcon getBtnAdd() {
		return btnAdd;
	}

	public void setBtnAdd(AddIcon btnAdd) {
		this.btnAdd = btnAdd;
	}



}