package com.internalaudit.client.view.dashboard;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.internalaudit.client.presenter.DashBoardNewPresenter.Display;

public class DashboardNewView extends VerticalPanel implements Display {
	
	FlexTable flex = new FlexTable();
	JobsExecutionPanel jobsInExecutionPanel = new JobsExecutionPanel();
	JobsDueForKickOffWithinWeek jobsDueForKickOffWithinWeek = new JobsDueForKickOffWithinWeek();
	ManagementCommentsOverDue managementCommentsOverDue = new ManagementCommentsOverDue();
	JobsDueForCompletionWithinWeek jobsDueForCompletionWithinWeek = new JobsDueForCompletionWithinWeek();
	ExceptionImplementaionOverDue exceptionImplementaionOverDue = new ExceptionImplementaionOverDue();
	AuditJobsByFunction auditJobsByFunction = new AuditJobsByFunction();
	AuditJobsByObjective auditJobsByObjective = new AuditJobsByObjective();
	AuditJobsByRisk auditJobsByRisk = new AuditJobsByRisk();
	JobsByPhases jobsByPhases = new JobsByPhases();
	ExceptionsImplementationChart exceptionsImplementationChart = new ExceptionsImplementationChart();
	Button btnContinue = new Button("Continue");
	
	public DashboardNewView(boolean startup){
		VerticalPanel vpnl1 = new VerticalPanel();
		VerticalPanel vpnl2 = new VerticalPanel();
		VerticalPanel vpnl3 = new VerticalPanel();
		setSpacing(0);
		
		HorizontalPanel hpnlMain = new HorizontalPanel();
		vpnl1.add(layout());
//		setWidth(Window.getClientWidth()-10+"px");
		if( startup){
			add(createheader());
			add(btnContinue);
			}
		add(hpnlMain);
		hpnlMain.add (vpnl1);
		hpnlMain.add (vpnl2);
		hpnlMain.add (vpnl3);
		vpnl2.add(jobsByPhases);
		vpnl2.add(exceptionsImplementationChart);
		vpnl3.add(auditJobsByFunction);
		vpnl3.add(auditJobsByRisk);
		vpnl3.add(auditJobsByObjective);
		vpnl1.setSpacing(4);
		vpnl2.setSpacing(4);
		vpnl3.setSpacing(4);
		vpnl1.addStyleName("dottedborder");
		vpnl2.addStyleName("dottedborder");
		vpnl3.addStyleName("dottedborder");
		vpnl1.setHeight(Window.getClientHeight()-10+"px");
		vpnl2.setHeight(Window.getClientHeight()-10+"px");
		vpnl3.setHeight(Window.getClientHeight()-10+"px");
		
		
	}
	
	public Widget createheader(){
		Image imgHeader = new Image("images/logo.png");
		VerticalPanel hpnlHeader = new VerticalPanel();
		HorizontalPanel hpnlMain = new HorizontalPanel();
		hpnlMain.add(imgHeader);
		hpnlMain.add(hpnlHeader);
//		hpnlHeader.addStyleName("blueBackground");
//		hpnlMain.addStyleName("blueBackground");
//		hpnlMain.setWidth(Window.getClientWidth()-10+"px");
//		hpnlHeader.setWidth(Window.getClientWidth()-imgHeader.getWidth()+"px");
		hpnlHeader.setHeight("91px");
		return hpnlMain;
	} 
	
	public FlexTable layout(){
		flex.setCellPadding(5);
		flex.setWidget(0, 0, jobsInExecutionPanel);
		flex.setWidget(1, 0, jobsDueForKickOffWithinWeek);
		flex.setWidget(2, 0, managementCommentsOverDue);
		flex.setWidget(3, 0, exceptionImplementaionOverDue);
		flex.setWidget(4, 0, jobsDueForCompletionWithinWeek);

		return flex;
	}


	public JobsExecutionPanel getJobsInExecutionPanel() {
		return jobsInExecutionPanel;
	}

	public void setJobsInExecutionPanel(JobsExecutionPanel jobsInExecutionPanel) {
		this.jobsInExecutionPanel = jobsInExecutionPanel;
	}

	public JobsDueForKickOffWithinWeek getJobsDueForKickOffWithinWeek() {
		return jobsDueForKickOffWithinWeek;
	}

	public void setJobsDueForKickOffWithinWeek(
			JobsDueForKickOffWithinWeek jobsDueForKickOffWithinWeek) {
		this.jobsDueForKickOffWithinWeek = jobsDueForKickOffWithinWeek;
	}

	public ManagementCommentsOverDue getManagementCommentsOverDue() {
		return managementCommentsOverDue;
	}

	public void setManagementCommentsOverDue(
			ManagementCommentsOverDue managementCommentsOverDue) {
		this.managementCommentsOverDue = managementCommentsOverDue;
	}

	public JobsDueForCompletionWithinWeek getJobsDueForCompletionWithinWeek() {
		return jobsDueForCompletionWithinWeek;
	}

	public void setJobsDueForCompletionWithinWeek(
			JobsDueForCompletionWithinWeek jobsDueForCompletionWithinWeek) {
		this.jobsDueForCompletionWithinWeek = jobsDueForCompletionWithinWeek;
	}

	public ExceptionImplementaionOverDue getExceptionImplementaionOverDue() {
		return exceptionImplementaionOverDue;
	}

	public void setExceptionImplementaionOverDue(
			ExceptionImplementaionOverDue exceptionImplementaionOverDue) {
		this.exceptionImplementaionOverDue = exceptionImplementaionOverDue;
	}

	public AuditJobsByFunction getAuditJobsByFunction() {
		return auditJobsByFunction;
	}

	public void setAuditJobsByFunction(AuditJobsByFunction auditJobsByFunction) {
		this.auditJobsByFunction = auditJobsByFunction;
	}

	public AuditJobsByObjective getAuditJobsByObjective() {
		return auditJobsByObjective;
	}

	public void setAuditJobsByObjective(AuditJobsByObjective auditJobsByObjective) {
		this.auditJobsByObjective = auditJobsByObjective;
	}

	public AuditJobsByRisk getAuditJobsByRisk() {
		return auditJobsByRisk;
	}

	public void setAuditJobsByRisk(AuditJobsByRisk auditJobsByRisk) {
		this.auditJobsByRisk = auditJobsByRisk;
	}

	public JobsByPhases getJobsByPhases() {
		return jobsByPhases;
	}

	public void setJobsByPhases(JobsByPhases jobsByPhases) {
		this.jobsByPhases = jobsByPhases;
	}

	public ExceptionsImplementationChart getExceptionsImplementationChart() {
		return exceptionsImplementationChart;
	}

	public void setExceptionsImplementationChart(
			ExceptionsImplementationChart exceptionsImplementationChart) {
		this.exceptionsImplementationChart = exceptionsImplementationChart;
	}

	public void setBtnContinue(Button btnContinue) {
		this.btnContinue = btnContinue;
	}

	public Button getBtnContinue() {
		return btnContinue;
	}

}
