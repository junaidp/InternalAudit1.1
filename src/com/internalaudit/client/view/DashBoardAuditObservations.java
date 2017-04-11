package com.internalaudit.client.view;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class DashBoardAuditObservations extends Composite {
	
	private Label noOfAuditObservations = new Label("Loading..");
	private Label implementationInProgress = new Label("Loading..");
	private Label implemented = new Label("");
	private Label overdue = new Label("");
	
	public DashBoardAuditObservations(){
		
		DecoratorPanel decoratorPanel = new DecoratorPanel();
		VerticalPanel mainPanel = new VerticalPanel();
		mainPanel.setSize("300px", "200px");
		Label lblHeading = new Label("Overall Status");
		lblHeading.setStyleName("popupHeading");
		Label lblNoOfAuditObservations = new Label("Total No of Audit Observations");
		Label lblImplementationInProgress = new Label("Implementation In Progress");
		Label lblImplemented = new Label("Implemented");
		Label lblOverdue = new Label("Overdue for Implementation");
		
		HorizontalPanel hpnlNoOfAuditObservations = new HorizontalPanel();
		HorizontalPanel hpnlImplementationInProgress = new HorizontalPanel();
		HorizontalPanel hpnlImplemented = new HorizontalPanel();
		HorizontalPanel hpnlOverdue = new HorizontalPanel();
		
		
		hpnlNoOfAuditObservations.add(lblNoOfAuditObservations);
		hpnlImplementationInProgress.add(lblImplementationInProgress);
		hpnlImplemented.add(lblImplemented);
		hpnlOverdue.add(lblOverdue);
		hpnlNoOfAuditObservations.add(noOfAuditObservations);
		hpnlImplementationInProgress.add(implementationInProgress);
		hpnlImplemented.add(implemented);
		hpnlOverdue.add(overdue);
		lblNoOfAuditObservations.setWidth("250px");
		lblImplementationInProgress.setWidth("250px");
		lblImplemented.setWidth("250px");
		lblOverdue.setWidth("250px");
		
		initWidget(decoratorPanel);
		decoratorPanel.setWidget(mainPanel);
		mainPanel.add(lblHeading);
		mainPanel.add(hpnlNoOfAuditObservations);
		mainPanel.add(hpnlImplementationInProgress);
		mainPanel.add(hpnlImplemented);
		mainPanel.add(hpnlOverdue);
	
//		lblCompleted.setStyleName("boldText");
//		lblPlanned.setStyleName("boldText");
		mainPanel.setHeight("200px");
	}

	public Label getNoOfAuditObservations() {
		return noOfAuditObservations;
	}

	public void setNoOfAuditObservations(Label noOfAuditObservations) {
		this.noOfAuditObservations = noOfAuditObservations;
	}

	public Label getImplementationInProgress() {
		return implementationInProgress;
	}

	public void setImplementationInProgress(Label implementationInProgress) {
		this.implementationInProgress = implementationInProgress;
	}

	public Label getImplemented() {
		return implemented;
	}

	public void setImplemented(Label implemented) {
		this.implemented = implemented;
	}

	public Label getOverdue() {
		return overdue;
	}

	public void setOverdue(Label overdue) {
		this.overdue = overdue;
	}

	
	
	
}
