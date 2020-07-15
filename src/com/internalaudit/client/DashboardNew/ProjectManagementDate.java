package com.internalaudit.client.DashboardNew;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.user.datepicker.client.DateBox.DefaultFormat;
import com.internalaudit.client.view.ButtonRound;
import com.internalaudit.client.view.AuditEngagement.LabelBold;

public class ProjectManagementDate extends FlexTable {
	
	private DateBox dpStart = new DateBox();
	private DateBox dpEnd = new DateBox();
	
	public ProjectManagementDate() {
		LabelBold lblStartDate = new LabelBold("Start Date");
		LabelBold lblEndDate = new LabelBold("End Date");
		lblStartDate.setWidth("100px");
		lblEndDate.setWidth("100px");
		dpStart.setFormat(new DefaultFormat(DateTimeFormat.getShortDateFormat()));
		dpEnd.setFormat(new DefaultFormat(DateTimeFormat.getShortDateFormat()));
		dpStart.getElement().setPropertyString("placeholder", "yyyy-mm-dd");
		dpEnd.getElement().setPropertyString("placeholder", "yyyy-mm-dd");
		dpStart.getElement().getStyle().setPaddingLeft(5, Unit.PX);
		dpEnd.getElement().getStyle().setPaddingLeft(5, Unit.PX);
		dpEnd.setWidth("100px");
		dpStart.setWidth("100px");

		setWidget(0, 0, lblStartDate);
		setWidget(0, 1, lblEndDate);
		setWidget(1, 0, dpStart);
		setWidget(1, 1, dpEnd);
		

	}
	
	public DateBox getDpStart() {
		return dpStart;
	}

	public void setDpStart(DateBox dpStart) {
		this.dpStart = dpStart;
	}

	public DateBox getDpEnd() {
		return dpEnd;
	}

	public void setDpEnd(DateBox dpEnd) {
		this.dpEnd = dpEnd;
	}
}
