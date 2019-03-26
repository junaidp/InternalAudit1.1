package com.internalaudit.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ReportingEvent extends GwtEvent<ReportingEventHandler> {

	VerticalPanel centerPanel;
	private int jobId = 0;

	public ReportingEvent(VerticalPanel centerPanel) {
		this.centerPanel = centerPanel;
	}

	public ReportingEvent(VerticalPanel centerPanel, int jobId) {
		this.centerPanel = centerPanel;
		centerPanel.clear();
		this.jobId = jobId;
	}

	public static Type<ReportingEventHandler> TYPE = new Type<ReportingEventHandler>();

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<ReportingEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(ReportingEventHandler handler) {
		handler.onReporting(this);

	}

	public VerticalPanel getCenterPanel() {
		return centerPanel;
	}

	public void setCenterPanel(VerticalPanel centerPanel) {
		this.centerPanel = centerPanel;
	}

	public int getJobId() {
		return jobId;
	}

	public void setJobId(int jobId) {
		this.jobId = jobId;
	}

}
