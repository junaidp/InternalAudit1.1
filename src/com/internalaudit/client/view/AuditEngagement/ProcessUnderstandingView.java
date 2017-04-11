package com.internalaudit.client.view.AuditEngagement;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.Widget;

public class ProcessUnderstandingView extends Composite {

	private static ProcessUnderstandingViewUiBinder uiBinder = GWT
			.create(ProcessUnderstandingViewUiBinder.class);

	interface ProcessUnderstandingViewUiBinder extends
			UiBinder<Widget, ProcessUnderstandingView> {
	}

	public ProcessUnderstandingView() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	@UiField TextArea process;
	@UiField Button submit;
	@UiField Button attachment;


	public Button getSubmit() {
		return submit;
	}

	public void setSubmit(Button submit) {
		this.submit = submit;
	}



}
