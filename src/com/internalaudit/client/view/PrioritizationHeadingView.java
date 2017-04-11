package com.internalaudit.client.view;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.internalaudit.client.view.data.ConsolidationViewData;
import com.internalaudit.client.view.data.PrioritizationViewData;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.google.gwt.user.client.ui.Button;

public class PrioritizationHeadingView extends Composite{
	private VerticalPanel vpnlData;
	private ContentPanel cp;
	private PrioritizationViewData prioritizationViewData = new PrioritizationViewData();
	private Button submit;
	
	
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
		
		submit = new Button("Submit");
		submit.setWidth("100px");
		HorizontalPanel hpnlButton = new HorizontalPanel();
		HorizontalPanel hpnlButtonSpace = new HorizontalPanel();
		hpnlButtonSpace.setWidth("800px");
		hpnlButton.add(hpnlButtonSpace);
//		hpnlButton.add(submit);
		verticalPanel.add(hpnlButton);
		prioritizationViewData.setData(cp, vpnlData, submit);
		submit.setWidth("71px");
		
		HorizontalPanel horizontalPanel = new HorizontalPanel();
		verticalPanel.add(horizontalPanel);
		horizontalPanel.setSize("750px", "100%");
		
		Label lblArea = new Label("Auditable Unit");
		lblArea.setStyleName("labelHeading");
		horizontalPanel.add(lblArea);
		lblArea.setWidth("260px");
		
		Label lblObjective = new Label("Objective");
		lblObjective.setStyleName("labelHeading");
		horizontalPanel.add(lblObjective);
		lblObjective.setWidth("180px");
		
		Label lblRiskUnit = new Label("Risk Rating");
		lblRiskUnit.setStyleName("labelHeading");
		horizontalPanel.add(lblRiskUnit);
		lblRiskUnit.setWidth("150px");
		
		Label lblAuditaleRating = new Label("Selected for Audit");
		lblAuditaleRating.setStyleName("labelHeading");
		horizontalPanel.add(lblAuditaleRating);
		lblAuditaleRating.setWidth("190px");
		
		Label lblTab = new Label("");
		lblTab.setStyleName("blue");
		horizontalPanel.add(lblTab);
		lblTab.setWidth("100px");
//		horizontalPanel.setStyleName("statusRowConsolidation");
		horizontalPanel.setSpacing(5);
	
		 ScrollPanel scroll = new ScrollPanel();
		 scroll.setSize("1100px", "375px");
		 scroll.setWidget(vpnlData);
		verticalPanel.add(scroll);
//		vpnlData.setSize("100%", "50%");
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
	public void setPrioritizationViewData(
			PrioritizationViewData prioritizationViewData) {
		this.prioritizationViewData = prioritizationViewData;
	}

	
}
