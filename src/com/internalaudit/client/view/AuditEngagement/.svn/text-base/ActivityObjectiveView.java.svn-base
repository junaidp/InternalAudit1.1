package com.internalaudit.client.view.AuditEngagement;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.Widget;
import com.internalaudit.shared.AuditEngagement;

public class ActivityObjectiveView extends Composite {

	private static ActivityObjectiveViewUiBinder uiBinder = GWT
			.create(ActivityObjectiveViewUiBinder.class);

	interface ActivityObjectiveViewUiBinder extends
			UiBinder<Widget, ActivityObjectiveView> {
	}

	public ActivityObjectiveView(AuditEngagement record) {
		initWidget(uiBinder.createAndBindUi(this));
		
		if ( record != null)
			activityObjective.setText(record.getActivityObj());
	}
	
	@UiField TextArea activityObjective;
	@UiField Button submit;



	public TextArea getActivityObjective() {
		return activityObjective;
	}


	public Button getSubmit() {
		return submit;
	}

	public void setSubmit(Button submit) {
		this.submit = submit;
	}

	public void disableFields(){
		activityObjective.setEnabled(false);
		submit.setEnabled(false);
	}


}
