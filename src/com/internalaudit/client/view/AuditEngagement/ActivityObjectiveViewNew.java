package com.internalaudit.client.view.AuditEngagement;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.internalaudit.shared.ActivityObjective;

public class ActivityObjectiveViewNew extends Composite {
	Label lblHeading = new Label();
	LabelHeading lblReferenceNo = new LabelHeading();
	LabelHeading lblActivityObjective = new LabelHeading();
	LabelHeading lblapplicability = new LabelHeading();
	TextArea txtAreaActivityObj = new TextArea();
	CheckBox checkBoxApplicability = new CheckBox("");
	private int activityObjectiveId = 0;
	Label lblReferenceNoData = new Label("");
	Image delete = new Image("images/deleteIcon.png");
	private Button btnSelectActivity = new Button("Select");

	@UiField
	VerticalPanel panelActivityObjective;

	private static ActivityObjectiveViewNewUiBinder uiBinder = GWT.create(ActivityObjectiveViewNewUiBinder.class);

	interface ActivityObjectiveViewNewUiBinder extends UiBinder<Widget, ActivityObjectiveViewNew> {
	}

	public ActivityObjectiveViewNew() {
		delete.setVisible(false);
		initWidget(uiBinder.createAndBindUi(this));
		lblHeading.setText("Detailed Scope and Process Understanding");
		// lblHeading.addStyleName("w3-white");
		lblReferenceNoData.setWidth("180px");
		lblReferenceNoData.getElement().getStyle().setMarginLeft(5, Unit.PX);
		lblReferenceNo.setText("Reference Number");

		lblActivityObjective.setText("Activity Objective");
		lblapplicability.setText("Applicability");
		Button buttonAdd = new Button();
		buttonAdd.addStyleName("w3-button w3-circle w3-teal");

		txtAreaActivityObj.setWidth("400px");
		txtAreaActivityObj.setHeight("120px");

		lblActivityObjective.getElement().getStyle().setMarginLeft(20, Unit.PX);
		lblapplicability.getElement().getStyle().setMarginLeft(20, Unit.PX);
		txtAreaActivityObj.getElement().getStyle().setMarginLeft(20, Unit.PX);
		checkBoxApplicability.getElement().getStyle().setMarginLeft(70, Unit.PX);

		FlexTable flex = new FlexTable();
		// flex.setWidget(1, 0, lblReferenceNo);
		// flex.setWidget(2, 0, lblReferenceNoData);
		// flex.setWidget(0,1,actv);

		flex.setWidget(1, 1, lblActivityObjective);
		flex.setWidget(2, 1, txtAreaActivityObj);

		flex.setWidget(2, 2, btnSelectActivity);
		flex.setWidget(2, 3, delete);

		panelActivityObjective.add(flex);
	}

	public void hideElemetns() {
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

	public Label getlblReferenceNoData() {
		return lblReferenceNoData;
	}

	public void setlblReferenceNoData(Label lblReferenceNoData) {
		this.lblReferenceNoData = lblReferenceNoData;
	}

	public void setData(ActivityObjective activityObjective) {
		txtAreaActivityObj.setText(activityObjective.getObjectiveName());
		activityObjectiveId = activityObjective.getObjectiveId();
		lblReferenceNoData.setText(activityObjective.getReferenceNo());
		checkBoxApplicability.setChecked(activityObjective.getChecked());

	}

	public void getData(ActivityObjective activityObjective) {
		activityObjective.setObjectiveId(activityObjectiveId);
		activityObjective.setObjectiveName(txtAreaActivityObj.getText());
		activityObjective.setChecked(checkBoxApplicability.isChecked());
		activityObjective.setReferenceNo(lblReferenceNoData.getText());

	}

	public Button getBtnSelectActivity() {
		return btnSelectActivity;
	}

	public void setBtnSelectActivity(Button btnSelectActivity) {
		this.btnSelectActivity = btnSelectActivity;
	}

	public void getData(ActivityObjectiveViewNew activityObjectiveSelected) {
		activityObjectiveSelected.getTxtAreaActivityObj().setText(txtAreaActivityObj.getText());
		activityObjectiveSelected.getlblReferenceNoData().setText(lblReferenceNoData.getText());
		activityObjectiveSelected.activityObjectiveId = activityObjectiveId;
		activityObjectiveSelected.btnSelectActivity.setVisible(false);
		activityObjectiveSelected.getDelete().setVisible(true);
	}

	public void disable() {
		txtAreaActivityObj.setEnabled(false);
		delete.setVisible(false);
	}

	public Image getDelete() {
		return delete;
	}

	public void setDelete(Image delete) {
		this.delete = delete;
	}

}
