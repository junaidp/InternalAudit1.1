package com.internalaudit.client.view;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.VerticalPanel;
//import com.google.gwt.widget.client.TextButton;
import com.internalaudit.client.presenter.DashBoardPresenter.Display;

public class DashBoardView extends VerticalPanel implements IsWidget, Display {

    private DashBoardAuditJobs dashBoardAuditJobs = new DashBoardAuditJobs();
    private DashBoardAuditObservations dashBoardAuditObservations = new DashBoardAuditObservations();
    private DashBoardResourceManagement dashBoardResourceManagement = new DashBoardResourceManagement();
    private Button btnContinue = new Button("Continue");

    public DashBoardView(boolean atStartup) {
	HorizontalPanel hpnl = new HorizontalPanel();
	add(hpnl);
	hpnl.setWidth("100%");
	setSize("100%", "500px");
	hpnl.add(dashBoardAuditJobs);
	hpnl.add(dashBoardAuditObservations);
	add(dashBoardResourceManagement);
	if (atStartup) {
	    add(btnContinue);
	}
    }

    public DashBoardAuditJobs getDashBoardAuditJobs() {
	return dashBoardAuditJobs;
    }

    public void setDashBoardAuditJobs(DashBoardAuditJobs dashBoardAuditJobs) {
	this.dashBoardAuditJobs = dashBoardAuditJobs;
    }

    public DashBoardAuditObservations getDashBoardAuditObservations() {
	return dashBoardAuditObservations;
    }

    public void setDashBoardAuditObservations(DashBoardAuditObservations dashBoardAuditObservations) {
	this.dashBoardAuditObservations = dashBoardAuditObservations;
    }

    public DashBoardResourceManagement getDashBoardResourceManagement() {
	return dashBoardResourceManagement;
    }

    public void setDashBoardResourceManagement(DashBoardResourceManagement dashBoardResourceManagement) {
	this.dashBoardResourceManagement = dashBoardResourceManagement;
    }

    public Button getBtnContinue() {
	return btnContinue;
    }

    public void setBtnContinue(Button btnContinue) {
	this.btnContinue = btnContinue;
    }

}
