package com.internalaudit.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.internalaudit.client.presenter.AuditListingPresenter.Display;

public class AuditListingView extends Composite implements Display {

	private static AuditListingUiBinder uiBinder = GWT.create(AuditListingUiBinder.class);

	interface AuditListingUiBinder extends UiBinder<Widget, AuditListingView> {
	}

	
	@UiField
	Anchor resourceButton;
	
	@UiField
	Anchor jobsButton;
	@UiField
	Button btnBack;
	
	
	public AuditListingView() {
		initWidget(uiBinder.createAndBindUi(this));
	}


	public Anchor getResourceButton() {
		return resourceButton;
	}


	public void setResourceButton(Anchor resourceButton) {
		this.resourceButton = resourceButton;
	}


	public Anchor getJobsButton() {
		return jobsButton;
	}


	public void setJobsButton(Anchor jobsButton) {
		this.jobsButton = jobsButton;
	}


	public Button getBtnBack() {
		return btnBack;
	}


	public void setBtnBack(Button btnBack) {
		this.btnBack = btnBack;
	}


	

}
