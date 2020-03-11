package com.internalaudit.client.view;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.internalaudit.client.view.AuditEngagement.LabelHeading;
import com.internalaudit.client.view.data.ConsolidationViewData;
import com.sencha.gxt.widget.core.client.ContentPanel;

public class ConsolidationHeadingView extends Composite {
	private VerticalPanel vpnlData;
	private ContentPanel cp;
	private ConsolidationViewData consolidationViewData = new ConsolidationViewData();
	private Button submit;

	public VerticalPanel getVpnlData() {
		return vpnlData;
	}

	public void setVpnlData(VerticalPanel vpnlData) {
		this.vpnlData = vpnlData;
	}

	public ConsolidationHeadingView(ContentPanel cp) {
		vpnlData = new VerticalPanel();
		this.cp = cp;

		VerticalPanel verticalPanel = new VerticalPanel();
		initWidget(verticalPanel);
		verticalPanel.setHeight("35px");
		verticalPanel.setWidth("1100px");

		submit = new Button("Submit");
		submit.setWidth("100px");
		HorizontalPanel hpnlButton = new HorizontalPanel();
		HorizontalPanel hpnlButtonSpace = new HorizontalPanel();
		hpnlButtonSpace.setWidth("900px");
		hpnlButton.add(hpnlButtonSpace);
		// hpnlButton.add(submit);

		verticalPanel.add(hpnlButton);
		consolidationViewData.setData(cp, vpnlData, submit);
		submit.setWidth("100px");

		HorizontalPanel horizontalPanel = new HorizontalPanel();
		verticalPanel.add(horizontalPanel);
		horizontalPanel.setSize("1000px", "100%");
		horizontalPanel.setSpacing(5);

		LabelHeading lblArea = new LabelHeading();
		lblArea.setText("Objective");
		// lblArea.setWordWrap(false);
		// lblArea.addStyleName("labelHeading");
		horizontalPanel.add(lblArea);
		lblArea.setWidth("225px");

		LabelHeading lblRiskUnit = new LabelHeading();
		lblRiskUnit.setText("Risk Rating");
		// lblRiskUnit.addStyleName("labelHeading");
		horizontalPanel.add(lblRiskUnit);
		lblRiskUnit.setWidth("80px");

		LabelHeading lblAuditaleRating = new LabelHeading();
		lblAuditaleRating.setText("Auditable Unit");
		// lblAuditaleRating.addStyleName("labelHeading");
		horizontalPanel.add(lblAuditaleRating);
		lblAuditaleRating.setWidth("200px");

		// horizontalPanel.addStyleName("statusRowConsolidation");

		// heading label of procees
		LabelHeading lblAuditaleProcess = new LabelHeading();
		lblAuditaleProcess.setText("Process");
		// lblAuditaleProcess.addStyleName("labelHeading");
		horizontalPanel.add(lblAuditaleProcess);
		lblAuditaleProcess.setWidth("160px");

		// heading labelof sub process
		LabelHeading lblAuditaleSubProcess = new LabelHeading();
		lblAuditaleSubProcess.setText("SubProcess");
		// lblAuditaleSubProcess.addStyleName("labelHeading");
		horizontalPanel.add(lblAuditaleSubProcess);
		lblAuditaleSubProcess.setWidth("160px");

		// heading labelof jobtype
		LabelHeading lblAuditaleJobType = new LabelHeading();
		lblAuditaleJobType.setText("JobType");
		// lblAuditaleJobType.addStyleName("labelHeading");
		horizontalPanel.add(lblAuditaleJobType);
		lblAuditaleJobType.setWidth("190px");

		Label lblTab = new Label("");
		lblTab.addStyleName("blue");
		horizontalPanel.add(lblTab);
		lblTab.setWidth("50px");

		ScrollPanel scroll = new ScrollPanel();

		// scroll.setSize("98%", "375px");
		scroll.setHeight("375px");
		scroll.setWidth("1200px");
		scroll.setWidget(vpnlData);
		verticalPanel.add(scroll);
		// vpnlData.setSize("800px", "50%");
	}

	public ContentPanel getCp() {
		return cp;
	}

	public void setCp(ContentPanel cp) {
		this.cp = cp;
	}

	public ConsolidationViewData getConsolidationViewData() {
		return consolidationViewData;
	}

	public void setConsolidationViewData(ConsolidationViewData consolidationViewData) {
		this.consolidationViewData = consolidationViewData;
	}

}
