package com.internalaudit.client.view.Scheduling;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.internalaudit.client.presenter.AuditSchedulingPresenter.Display;

public class AuditSchedulingView extends Composite implements Display {

	private static AuditSchedulingViewUiBinder uiBinder = GWT
			.create(AuditSchedulingViewUiBinder.class);

	interface AuditSchedulingViewUiBinder extends
			UiBinder<Widget, AuditSchedulingView> {
	}
	
	@UiField Anchor areaOfExpertise;
	
	@UiField Anchor jobTimeEst;
	
	@UiField Anchor jobCreation;
	
	@UiField
	Anchor scheduling;
	
	HandlerManager eventBus = new HandlerManager(null);
	   

	public AuditSchedulingView() {
		initWidget(uiBinder.createAndBindUi(this));
		
		setHandlers();
	}


	private void setHandlers() {

		
	}


	public Anchor getAreaOfExpertise() {
		return areaOfExpertise;
	}


	public void setAreaOfExpertise(Anchor areaOfExpertise) {
		this.areaOfExpertise = areaOfExpertise;
	}


	public Anchor getJobCreation() {
		return jobCreation;
	}


	public void setJobCreation(Anchor jobCreation) {
		this.jobCreation = jobCreation;
	}

	public Anchor getJobTimeEst() {
		return jobTimeEst;
	}


	public void setJobTimeEst(Anchor jobTimeEst) {
		this.jobTimeEst = jobTimeEst;
	}


	public Anchor getScheduling() {
		return scheduling;
	}


	public void setScheduling(Anchor scheduling) {
		this.scheduling = scheduling;
	}

	
	
}
