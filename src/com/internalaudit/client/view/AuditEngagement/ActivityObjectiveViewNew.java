package com.internalaudit.client.view.AuditEngagement;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.FontWeight;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.internalaudit.shared.ActivityObjective;

public class ActivityObjectiveViewNew extends Composite {
	Label lblHeading = new Label("Detailed Scope and Process Understanding");
	Label lblActivityObjective = new Label("Activity Objective");
	Label lblapplicability = new Label("Applicability");
	TextArea txtAreaActivityObj = new TextArea();
	CheckBox checkBoxApplicability = new CheckBox("");
	private int activityObjectiveId = 0;
	Label lblReferenceNo = new Label("");

	// Label LblName = new Label("Detailed Scope and process understanding");

	@UiField
	VerticalPanel panelActivityObjective;


	private static ActivityObjectiveViewNewUiBinder uiBinder = GWT.create(ActivityObjectiveViewNewUiBinder.class);

	interface ActivityObjectiveViewNewUiBinder extends UiBinder<Widget, ActivityObjectiveViewNew> {
	}

	public ActivityObjectiveViewNew() {
		initWidget(uiBinder.createAndBindUi(this));
		lblHeading.addStyleName("w3-panel w3-blue");
		lblReferenceNo.setWidth("180px");
		lblReferenceNo.addStyleName("w3-panel w3-light-blue");
		lblActivityObjective.addStyleName("w3-panel w3-light-blue");

		lblapplicability.addStyleName("w3-panel w3-light-blue");
		Button buttonAdd = new Button();
		buttonAdd.addStyleName("w3-button w3-circle w3-teal");

		txtAreaActivityObj.setWidth("580px");
		txtAreaActivityObj.setHeight("80px");

		lblHeading.getElement().getStyle().setFontWeight(FontWeight.BOLD);
		lblActivityObjective.getElement().getStyle().setFontWeight(FontWeight.BOLD);
		lblapplicability.getElement().getStyle().setFontWeight(FontWeight.BOLD);
		lblReferenceNo.getElement().getStyle().setFontWeight(FontWeight.BOLD);

		lblActivityObjective.getElement().getStyle().setMarginLeft(20, Unit.PX);
		lblapplicability.getElement().getStyle().setMarginLeft(20, Unit.PX);
		txtAreaActivityObj.getElement().getStyle().setMarginLeft(20, Unit.PX);
		checkBoxApplicability.getElement().getStyle().setMarginLeft(20, Unit.PX);


		FlexTable flex = new FlexTable();
		//flex.setWidget(0, 1, lblHeading);
		flex.setWidget(2,0, lblReferenceNo);
		//  flex.setWidget(0,1,actv);

		flex.setWidget(1,1, lblActivityObjective);
		flex.setWidget(2,1,txtAreaActivityObj);
		flex.setWidget(1,2, lblapplicability);
		flex.setWidget(2,2,checkBoxApplicability);

		panelActivityObjective.add(flex);
	}

	public void hideElemetns(){
		lblHeading.setVisible(false);
		lblActivityObjective.setVisible(false);
		lblapplicability.setVisible(false);


	}

	public Label getLblHeading() {
		return lblHeading;
	}

	public void setLblHeading(Label lblHeading) {
		this.lblHeading = lblHeading;
	}

	public TextArea getTxtAreaActivityObj() {
		return txtAreaActivityObj;
	}

	public void setTxtAreaActivityObj(TextArea txtAreaActivityObj) {
		this.txtAreaActivityObj = txtAreaActivityObj;
	}

	public CheckBox getCheckBoxApplicability() {
		return checkBoxApplicability;
	}

	public void setCheckBoxApplicability(CheckBox checkBoxApplicability) {
		this.checkBoxApplicability = checkBoxApplicability;
	}

	public Label getlblReferenceNo() {
		return lblReferenceNo;
	}

	public void setlblReferenceNo(Label lblReferenceNo) {
		this.lblReferenceNo = lblReferenceNo;
	}

	public void setData(ActivityObjective activityObjective) {
		txtAreaActivityObj.setText(activityObjective.getObjectiveName());
		activityObjectiveId = activityObjective.getObjectiveId();
		lblReferenceNo.setText(activityObjective.getReferenceNo());
		checkBoxApplicability.setChecked(activityObjective.getChecked());
		
	
		//TODO populate other 
		
	}

	public void getData(ActivityObjective activityObjective) {
		activityObjective.setObjectiveId(activityObjectiveId);
		activityObjective.setObjectiveName(txtAreaActivityObj.getText());
		activityObjective.setChecked(checkBoxApplicability.isChecked());
		activityObjective.setReferenceNo(lblReferenceNo.getText());
		
		
		
		//TODO set any other..
		
	}
}
