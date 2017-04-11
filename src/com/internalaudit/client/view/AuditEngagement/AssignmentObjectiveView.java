package com.internalaudit.client.view.AuditEngagement;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.Widget;
import com.internalaudit.shared.AuditEngagement;

public class AssignmentObjectiveView extends Composite {

	private static AssignmentObjectiveViewUiBinder uiBinder = GWT
			.create(AssignmentObjectiveViewUiBinder.class);

	interface AssignmentObjectiveViewUiBinder extends
			UiBinder<Widget, AssignmentObjectiveView> {
	}
	
	@UiField TextArea assignmentObjective;
	@UiField Button submit;

	public AssignmentObjectiveView(AuditEngagement record) {
		initWidget(uiBinder.createAndBindUi(this));
		
		if ( record != null)
			assignmentObjective.setText(record.getAssignmentObj());
	}

	public TextArea getAssignmentObjective() {
		return assignmentObjective;
	}

	public void setAssignmentObjective(TextArea assignmentObjective) {
		this.assignmentObjective = assignmentObjective;
	}

	public Button getSubmit() {
		return submit;
	}

	public void setSubmit(Button submit) {
		this.submit = submit;
	}
	
	public void disableFields(){
		assignmentObjective.setEnabled(false);
		submit.setEnabled(false);
	}


}
