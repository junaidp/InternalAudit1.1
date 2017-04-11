package com.internalaudit.client.view;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.widget.client.TextButton;
import com.internalaudit.client.presenter.DashBoardPresenter.Display;
import com.sencha.gxt.widget.core.client.FramedPanel;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.FieldSet;
import com.sencha.gxt.widget.core.client.form.PasswordField;
import com.sencha.gxt.widget.core.client.form.TextField;

public class DashBoardView extends VerticalPanel implements IsWidget,Display {
	
	private DashBoardAuditJobs dashBoardAuditJobs = new DashBoardAuditJobs();
	private DashBoardAuditObservations dashBoardAuditObservations = new DashBoardAuditObservations();
	private DashBoardResourceManagement dashBoardResourceManagement = new DashBoardResourceManagement();
	
	public DashBoardView() {
		HorizontalPanel hpnl = new HorizontalPanel();
		add(hpnl);
		hpnl.setWidth("100%");
		setSize("100%","500px");
		hpnl.add(dashBoardAuditJobs);
		hpnl.add(dashBoardAuditObservations);
		add(dashBoardResourceManagement);
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

	public void setDashBoardAuditObservations(
			DashBoardAuditObservations dashBoardAuditObservations) {
		this.dashBoardAuditObservations = dashBoardAuditObservations;
	}

	public DashBoardResourceManagement getDashBoardResourceManagement() {
		return dashBoardResourceManagement;
	}

	public void setDashBoardResourceManagement(
			DashBoardResourceManagement dashBoardResourceManagement) {
		this.dashBoardResourceManagement = dashBoardResourceManagement;
	}
	
	

}
