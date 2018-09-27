package com.internalaudit.client.view.Reporting;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.internalaudit.client.view.AuditEngagement.LabelHeading;

public class AllJobsView extends VerticalPanel {
	
	public AllJobsView(){
		HorizontalPanel hpnl = new HorizontalPanel();
		LabelHeading lblAuditJob = new LabelHeading();
		LabelHeading lblReportingStatus = new LabelHeading();
		setStyling(lblAuditJob, lblReportingStatus);
		hpnl.add(lblAuditJob);
		hpnl.add(lblReportingStatus);
		add(hpnl);
	}

	private void setStyling(LabelHeading  lblAuditJob, LabelHeading  lblReportingStatus) {
		lblAuditJob.setText("Audit Job");
//		lblAuditJob.getElement().getStyle().setFontSize(18, Unit.PX);
//		lblReportingStatus.getElement().getStyle().setFontSize(18, Unit.PX);
		lblReportingStatus.setText("Reporting Status");
		lblAuditJob.setWidth("615px");
		lblReportingStatus.setWidth("400px");
	}

}
