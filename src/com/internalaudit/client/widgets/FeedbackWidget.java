package com.internalaudit.client.widgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.internalaudit.client.InternalAuditService;
import com.internalaudit.client.InternalAuditServiceAsync;

public class FeedbackWidget extends Composite {

	private static FeedbackUiBinder uiBinder = GWT.create(FeedbackUiBinder.class);

	interface FeedbackUiBinder extends UiBinder<Widget, FeedbackWidget> {
	}
	
	@UiField
	TextBox txtHeading;
	@UiField
	TextArea txtDescription;
	@UiField
	Button btnSubmit;
	

	public FeedbackWidget() {
		initWidget(uiBinder.createAndBindUi(this));
		txtHeading.getElement().setPropertyString("placeholder", "Enter Title");
		txtDescription.getElement().setPropertyString("placeholder", "Enter Description");
		
	}


	public TextBox getTxtHeading() {
		return txtHeading;
	}


	public TextArea getTxtDescription() {
		return txtDescription;
	}


	public Button getBtnSubmit() {
		return btnSubmit;
	}

}
