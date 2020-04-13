package com.internalaudit.client.view.dashboard;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.internalaudit.client.presenter.DashBoardNewPresenter.Display;

public class DashBoardDesignerView extends Composite implements Display {

	private static DashBoardDesignerViewUiBinder uiBinder = GWT.create(DashBoardDesignerViewUiBinder.class);

	interface DashBoardDesignerViewUiBinder extends UiBinder<Widget, DashBoardDesignerView> {
	}

	@UiField
	VerticalPanel vpnlLabels;
	@UiField
	Button btnContinue;
	@UiField
	VerticalPanel vpnlBar;
	@UiField
	VerticalPanel vpnlPie1;
	@UiField
	VerticalPanel vpnlPie2;
	@UiField
	VerticalPanel vpnlPie3;
	@UiField
	VerticalPanel vpnlPie4;
	@UiField
	Image imgLogo;

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

	public DashBoardDesignerView(boolean startup) {
		initWidget(uiBinder.createAndBindUi(this));
		// VerticalPanel vpnl1 = new VerticalPanel();
		// VerticalPanel vpnl2 = new VerticalPanel();
		// VerticalPanel vpnl3 = new VerticalPanel();

		// HorizontalPanel hpnlMain = new HorizontalPanel();
		if (!startup) {
			btnContinue.setVisible(false);
			imgLogo.setVisible(false);
		}
		vpnlBar.add(jobsByPhases);
		vpnlPie1.add(auditJobsByObjective);
		vpnlPie2.add(auditJobsByFunction);
		vpnlPie3.add(auditJobsByRisk);
		vpnlPie4.add(exceptionsImplementationChart);

		vpnlLabels.add(jobsInExecutionPanel);
		vpnlLabels.add(jobsDueForKickOffWithinWeek);
		vpnlLabels.add(managementCommentsOverDue);
		vpnlLabels.add(exceptionImplementaionOverDue);
		vpnlLabels.add(jobsDueForCompletionWithinWeek);

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

	public void setJobsDueForKickOffWithinWeek(JobsDueForKickOffWithinWeek jobsDueForKickOffWithinWeek) {
		this.jobsDueForKickOffWithinWeek = jobsDueForKickOffWithinWeek;
	}

	public ManagementCommentsOverDue getManagementCommentsOverDue() {
		return managementCommentsOverDue;
	}

	public void setManagementCommentsOverDue(ManagementCommentsOverDue managementCommentsOverDue) {
		this.managementCommentsOverDue = managementCommentsOverDue;
	}

	public JobsDueForCompletionWithinWeek getJobsDueForCompletionWithinWeek() {
		return jobsDueForCompletionWithinWeek;
	}

	public void setJobsDueForCompletionWithinWeek(JobsDueForCompletionWithinWeek jobsDueForCompletionWithinWeek) {
		this.jobsDueForCompletionWithinWeek = jobsDueForCompletionWithinWeek;
	}

	public ExceptionImplementaionOverDue getExceptionImplementaionOverDue() {
		return exceptionImplementaionOverDue;
	}

	public void setExceptionImplementaionOverDue(ExceptionImplementaionOverDue exceptionImplementaionOverDue) {
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

	public void setExceptionsImplementationChart(ExceptionsImplementationChart exceptionsImplementationChart) {
		this.exceptionsImplementationChart = exceptionsImplementationChart;
	}

	public void setBtnContinue(Button btnContinue) {
		this.btnContinue = btnContinue;
	}

	public Button getBtnContinue() {
		return btnContinue;
	}
}