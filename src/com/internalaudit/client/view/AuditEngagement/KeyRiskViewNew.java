package com.internalaudit.client.view.AuditEngagement;

import com.google.gwt.dom.client.Style.FontWeight;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.internalaudit.client.widgets.AddIcon;
import com.internalaudit.shared.ActivityObjective;
import com.internalaudit.shared.RiskObjective;

public class KeyRiskViewNew extends VerticalPanel {

	private Label lblActivityObjectiveData = new Label("");
	Label lblactivityObjective = new Label("Activity Objective");
	Label lblrisks = new Label("Risk");
	Label lblriskRatings = new Label("Risk Ratings");
	Label lblapplicability = new Label("Applicability");
	private TextArea txtRisk = new TextArea();
	private Label lblName = new Label("");
	private AddIcon btnAddKeyRisk = new AddIcon();
	private ActivityObjective activityObjective = new ActivityObjective();
	private int riskId = 0;
	
	public  KeyRiskViewNew() {


		lblName.addStyleName("w3-panel w3-light-blue");
		lblactivityObjective.addStyleName("w3-panel w3-light-blue");

		lblrisks.addStyleName("w3-panel w3-light-blue");

		lblriskRatings.addStyleName("w3-panel w3-light-blue");

		lblapplicability.addStyleName("w3-panel w3-light-blue");

		lblActivityObjectiveData.setHeight("90px");
		lblActivityObjectiveData.setWidth("220px");


		txtRisk.setWidth("300px");
		txtRisk.setHeight("90px");
		txtRisk.setText("");

		ListBox listBoxRating = new ListBox();
		listBoxRating.addStyleName("w3-panel");
		listBoxRating.addItem("Low");
		listBoxRating.addItem("Medium");
		listBoxRating.addItem("High");

		CheckBox checkBoxApplicability = new CheckBox("");
		checkBoxApplicability.addStyleName("w3-panel");
		lblactivityObjective.getElement().getStyle().setFontWeight(FontWeight.BOLD);
		lblrisks.getElement().getStyle().setFontWeight(FontWeight.BOLD);
		lblriskRatings.getElement().getStyle().setFontWeight(FontWeight.BOLD);
		lblapplicability.getElement().getStyle().setFontWeight(FontWeight.BOLD);
		lblName.getElement().getStyle().setFontWeight(FontWeight.BOLD);

		lblactivityObjective.getElement().getStyle().setMarginLeft(20, Unit.PX);
		lblrisks.getElement().getStyle().setMarginLeft(20, Unit.PX);
		lblriskRatings.getElement().getStyle().setMarginLeft(20, Unit.PX);
		lblapplicability.getElement().getStyle().setMarginLeft(20, Unit.PX);

		lblActivityObjectiveData.getElement().getStyle().setMarginLeft(10, Unit.PX);
		txtRisk.getElement().getStyle().setMarginLeft(10, Unit.PX);
		listBoxRating.getElement().getStyle().setMarginLeft(10, Unit.PX);
		checkBoxApplicability.getElement().getStyle().setMarginLeft(10, Unit.PX);


		FlexTable flex = new FlexTable();
		flex.setWidget(1,0, lblName);
		//  flex.setWidget(0,1,actv);

		flex.setWidget(0,1, lblactivityObjective);
		VerticalPanel vpLblactivitydata = new VerticalPanel();
		vpLblactivitydata.add(lblActivityObjectiveData);
		vpLblactivitydata.setWidth("240px");
		flex.setWidget(1,1,vpLblactivitydata);

		flex.setWidget(0,2, lblrisks);
		flex.setWidget(1,2,txtRisk);
		flex.setWidget(2,2,btnAddKeyRisk);

		flex.setWidget(0,3, lblriskRatings);
		flex.setWidget(1,3,listBoxRating);

		flex.setWidget(0,4, lblapplicability);
		flex.setWidget(1,4,checkBoxApplicability);
		add(flex);
		
	}

	public void hideElemetns(){
		lblActivityObjectiveData.setVisible(false);
		lblactivityObjective.setVisible(false);
		lblrisks.setVisible(false);
		lblriskRatings.setVisible(false);
		lblapplicability.setVisible(false);
		btnAddKeyRisk.setVisible(false);
	}

	public void setData(RiskObjective riskObjective, boolean objectiveNameAdded){
		lblActivityObjectiveData.setText(riskObjective.getObjectiveId().getObjectiveName());
		txtRisk.setText(riskObjective.getRiskname());
		riskId = riskObjective.getRiskId();
		//TODO add field refNo in subprocess Table , set the refNo from excel file in db manually , and then uncomment below line
		//lblName.setText(riskObjective.getObjectiveId().getSubProcessId().getRefNo()+"");
		//TODO set rating and applicabality (also create these fields in db)

		if(objectiveNameAdded){
			lblActivityObjectiveData.setVisible(false);
			lblactivityObjective.setVisible(false);
			lblName.setVisible(false);
		}
	}
	
	public void getData(RiskObjective riskObjective) {
		riskObjective.setObjectiveId(activityObjective);
		riskObjective.setRiskId(riskId);
		riskObjective.setRiskname(txtRisk.getText());
		
	}

	public AddIcon getBtnAddKeyRisk() {
		return btnAddKeyRisk;
	}

	public void setBtnAddKeyRisk(AddIcon btnAddKeyRisk) {
		this.btnAddKeyRisk = btnAddKeyRisk;
	}

	public ActivityObjective getActivityObjective() {
		return activityObjective;
	}

	public void setActivityObjective(ActivityObjective activityObjective) {
		this.activityObjective = activityObjective;
	}

	public int getRiskId() {
		return riskId;
	}

	public void setRiskId(int riskId) {
		this.riskId = riskId;
	}

	
}
