package com.internalaudit.client.view;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class DashBoardAuditJobs extends Composite {
	
	private Label plannedJobs = new Label("Loading..");
	private Label inProgressJobs = new Label("Loading..");
	private Label completedJobs = new Label("Loading..");
	private VerticalPanel vpnlDueKickOffNextWeek = new VerticalPanel();
	
	public DashBoardAuditJobs(){
		
		DecoratorPanel decoratorPanel = new DecoratorPanel();
		VerticalPanel mainPanel = new VerticalPanel();
		mainPanel.setSize("300px", "200px");
		Label lblHeading = new Label("Audit Jobs");
		lblHeading.setStyleName("popupHeading");
		HorizontalPanel hpnlPlanned = new HorizontalPanel();
		Label lblPlanned = new Label("Planned");
		Label lblInProgress = new Label("InProgress");
		Label lblCompleted = new Label("Completed");
		
		
		HorizontalPanel hpnlInProgress = new HorizontalPanel();
		HorizontalPanel hpnlCompleted = new HorizontalPanel();
		Label lblDueKickOffNextWeek = new Label("Due Kickoff Next Week");
		
		
		hpnlPlanned.add(lblPlanned);
		hpnlInProgress.add(lblInProgress);
		hpnlCompleted.add(lblCompleted);
		hpnlPlanned.add(plannedJobs);
		hpnlInProgress.add(inProgressJobs);
		hpnlCompleted.add(completedJobs);
		lblPlanned.setWidth("250px");
		lblInProgress.setWidth("250px");
		lblCompleted.setWidth("250px");
		
		initWidget(decoratorPanel);
		decoratorPanel.setWidget(mainPanel);
		mainPanel.add(lblHeading);
		mainPanel.add(hpnlCompleted);
		mainPanel.add(hpnlInProgress);
		mainPanel.add(hpnlPlanned);
		mainPanel.add(lblDueKickOffNextWeek);
		mainPanel.add(vpnlDueKickOffNextWeek);
		
		lblDueKickOffNextWeek.setStyleName("boldText");
//		lblCompleted.setStyleName("boldText");
//		lblPlanned.setStyleName("boldText");
		mainPanel.setHeight("200px");
	}

	private void layout() {
		// TODO Auto-generated method stub
		
	}

	public Label getPlannedJobs() {
		return plannedJobs;
	}

	public void setPlannedJobs(Label plannedJobs) {
		this.plannedJobs = plannedJobs;
	}

	public Label getInProgressJobs() {
		return inProgressJobs;
	}

	public void setInProgressJobs(Label inProgressJobs) {
		this.inProgressJobs = inProgressJobs;
	}

	public Label getCompletedJobs() {
		return completedJobs;
	}

	public void setCompletedJobs(Label completedJobs) {
		this.completedJobs = completedJobs;
	}

	public VerticalPanel getVpnlDueKickOffNextWeek() {
		return vpnlDueKickOffNextWeek;
	}

	public void setVpnlDueKickOffNextWeek(VerticalPanel vpnlDueKickOffNextWeek) {
		this.vpnlDueKickOffNextWeek = vpnlDueKickOffNextWeek;
	}
	
}
