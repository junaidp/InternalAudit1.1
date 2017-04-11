package com.internalaudit.client.view.Reporting;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.internalaudit.client.presenter.ReportingPresenter.Display;

public class ReportingView extends Composite implements Display {
	
	private VerticalPanel vpnlReporting = new VerticalPanel();
	private VerticalPanel vpnlReportingView = new VerticalPanel(); 
	private VerticalPanel vpnlJobs = new VerticalPanel();
	private VerticalPanel vpnlSelectedJob = new VerticalPanel();
	
	public ReportingView(String fromInternalAudit){
		
		VerticalPanel vpnlMain = new VerticalPanel();
//		if(fromInternalAudit.equals("yes")){
			vpnlMain.add(vpnlReporting);
			vpnlReporting.add(vpnlJobs);
			vpnlReporting.add(vpnlSelectedJob);
//		}else{
//			vpnlMain.add(vpnlReportingView);
//			
//		}
		initWidget(vpnlMain);
		bind();
		vpnlMain.setHeight("600px");
	}

	private void bind() {
		
		
	}

	public VerticalPanel getVpnlReporting() {
		return vpnlReporting;
	}

	public void setVpnlReporting(VerticalPanel vpnlReporting) {
		this.vpnlReporting = vpnlReporting;
	}

	public VerticalPanel getVpnlReportingView() {
		return vpnlReportingView;
	}

	public void setVpnlReportingView(VerticalPanel vpnlReportingView) {
		this.vpnlReportingView = vpnlReportingView;
	}

	public VerticalPanel getVpnlJobs() {
		return vpnlJobs;
	}

	public void setVpnlJobs(VerticalPanel vpnlJobs) {
		this.vpnlJobs = vpnlJobs;
	}

	public VerticalPanel getVpnlSelectedJob() {
		return vpnlSelectedJob;
	}

	public void setVpnlSelectedJob(VerticalPanel vpnlSelectedJob) {
		this.vpnlSelectedJob = vpnlSelectedJob;
	}

}
