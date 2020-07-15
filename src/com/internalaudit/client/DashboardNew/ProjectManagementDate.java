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
	private Button btnSrearchP = new Button("Search");
	
	public ProjectManagementDate() {
		LabelBold lblStartDate = new LabelBold("Start Date");
		LabelBold lblEndDate = new LabelBold("End Date");
		lblStartDate.setWidth("100px");
		lblEndDate.setWidth("100px");
		DateBox dpStart = new DateBox();
		DateBox dpEnd = new DateBox();
		dpStart.setFormat(new DefaultFormat(DateTimeFormat.getShortDateFormat()));
		dpEnd.setFormat(new DefaultFormat(DateTimeFormat.getShortDateFormat()));
		dpStart.getElement().setPropertyString("placeholder", "yyyy-mm-dd");
		dpEnd.getElement().setPropertyString("placeholder", "yyyy-mm-dd");
		dpStart.getElement().getStyle().setPaddingLeft(5, Unit.PX);
		dpEnd.getElement().getStyle().setPaddingLeft(5, Unit.PX);
		dpEnd.setWidth("100px");
		dpStart.setWidth("100px");
		// dpStart.getElement().getStyle().setMarginLeft(15, Unit.PX);
		// dpEnd.getElement().getStyle().setMarginLeft(15, Unit.PX);
		// dpStart.addStyleName("w3-panel");
		// dpEnd.addStyleName("w3-panel");

		// HorizontalPanel panelStart = new HorizontalPanel();
		// HorizontalPanel panelEnd = new HorizontalPanel();
		// panelStart.add(lblStartDate);
		// panelStart.add(dpStart);
		// panelEnd.add(lblEndDate);
		// panelEnd.add(dpEnd);

		setWidget(0, 0, lblStartDate);
		setWidget(0, 1, lblEndDate);
		setWidget(1, 0, dpStart);
		setWidget(1, 1, dpEnd);
		setWidget(1, 2, btnSrearchP);
		
		// setWidget(1, 2, new HTML(
		// "&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
		// &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;"));
		// add(panelStart);
		// add(panelEnd);

	}
	public Button getBtnSrearchP() {
		return btnSrearchP;
	}
	public void setBtnSrearchP(Button btnSrearchP) {
		this.btnSrearchP = btnSrearchP;
	}
}
