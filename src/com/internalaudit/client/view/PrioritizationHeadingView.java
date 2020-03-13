package com.internalaudit.client.view;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.internalaudit.client.view.AuditEngagement.LabelHeading;
import com.internalaudit.client.view.data.PrioritizationViewData;
import com.sencha.gxt.widget.core.client.ContentPanel;

public class PrioritizationHeadingView extends Composite {
	private VerticalPanel vpnlData;
	private ContentPanel cp;
	private PrioritizationViewData prioritizationViewData = new PrioritizationViewData();
	private ButtonRound submit;

	public VerticalPanel getVpnlData() {
		return vpnlData;
	}

	public void setVpnlData(VerticalPanel vpnlData) {
		this.vpnlData = vpnlData;
	}

	public PrioritizationHeadingView(ContentPanel cp) {
		vpnlData = new VerticalPanel();
		this.cp = cp;

		VerticalPanel verticalPanel = new VerticalPanel();
		initWidget(verticalPanel);
		verticalPanel.setHeight("35px");

		submit = new ButtonRound("Submit");
		submit.setWidth("100px");
		HorizontalPanel hpnlButton = new HorizontalPanel();
		HorizontalPanel hpnlButtonSpace = new HorizontalPanel();
		hpnlButtonSpace.setWidth("800px");
		hpnlButton.add(hpnlButtonSpace);
		// hpnlButton.add(submit);
		verticalPanel.add(hpnlButton);
		prioritizationViewData.setData(cp, vpnlData, submit);
		submit.setWidth("71px");

		HorizontalPanel horizontalPanel = new HorizontalPanel();
		verticalPanel.add(horizontalPanel);
		horizontalPanel.setSize("750px", "100%");

		LabelHeading lblArea = new LabelHeading();
		lblArea.setText("Auditable Unit");
		// lblArea.addStyleName("labelHeading");
		horizontalPanel.add(lblArea);
		lblArea.setWidth("260px");

		LabelHeading lblObjective = new LabelHeading();
		lblObjective.setText("Objective");
		// lblObjective.addStyleName("labelHeading");
		horizontalPanel.add(lblObjective);
		lblObjective.setWidth("180px");

		LabelHeading lblRiskUnit = new LabelHeading();
		lblRiskUnit.setText("Risk Rating");
		// lblRiskUnit.addStyleName("labelHeading");
		horizontalPanel.add(lblRiskUnit);
		lblRiskUnit.setWidth("150px");

		LabelHeading lblAuditaleRating = new LabelHeading();
		lblAuditaleRating.setText("Selected for Audit");
		// lblAuditaleRating.addStyleName("labelHeading");
		horizontalPanel.add(lblAuditaleRating);
		lblAuditaleRating.setWidth("190px");

		// horizontalPanel.addStyleName("statusRowConsolidation");
		horizontalPanel.setSpacing(5);
		// new label adding year
		LabelHeading lblAuditablYear = new LabelHeading();
		lblAuditablYear.setText("Year");
		// lblAuditablYear.getElement().getStyle().setMarginLeft(65, Unit.PX);
		// lblAuditablYear.addStyleName("labelHeading");
		horizontalPanel.add(lblAuditablYear);
		lblAuditaleRating.setWidth("160px");

		Label lblTab = new Label("");
		lblTab.addStyleName("blue");
		horizontalPanel.add(lblTab);
		lblTab.setWidth("100px");

		ScrollPanel scroll = new ScrollPanel();
		scroll.setSize("1200px", "375px");
		scroll.setWidget(vpnlData);
		verticalPanel.add(scroll);
		// vpnlData.setSize("100%", "50%");
	}

	public ContentPanel getCp() {
		return cp;
	}

	public void setCp(ContentPanel cp) {
		this.cp = cp;
	}

	public PrioritizationViewData getPrioritizationViewData() {
		return prioritizationViewData;
	}

	public void setPrioritizationViewData(PrioritizationViewData prioritizationViewData) {
		this.prioritizationViewData = prioritizationViewData;
	}

}
