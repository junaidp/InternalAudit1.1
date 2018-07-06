package com.internalaudit.client.view;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.internalaudit.client.view.data.ConsolidationViewData;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.google.gwt.user.client.ui.Button;

public class ConsolidationHeadingView extends Composite{
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
		
		submit = new Button("Submit");
		submit.setWidth("100px");
		HorizontalPanel hpnlButton = new HorizontalPanel();
		HorizontalPanel hpnlButtonSpace = new HorizontalPanel();
		hpnlButtonSpace.setWidth("900px");
		hpnlButton.add(hpnlButtonSpace);
//		hpnlButton.add(submit);
		
		verticalPanel.add(hpnlButton);
		consolidationViewData.setData(cp, vpnlData, submit);
		submit.setWidth("100px");
		
		HorizontalPanel horizontalPanel = new HorizontalPanel();
		verticalPanel.add(horizontalPanel);
		horizontalPanel.setSize("1000px", "100%");
		horizontalPanel.setSpacing(5);
		
		Label lblArea = new Label("Objective");
//		lblArea.setWordWrap(false);
		lblArea.addStyleName("labelHeading");
		horizontalPanel.add(lblArea);
		lblArea.setWidth("200px");
		
		Label lblRiskUnit = new Label("Risk Rating");
		lblRiskUnit.addStyleName("labelHeading");
		horizontalPanel.add(lblRiskUnit);
		lblRiskUnit.setWidth("100px");
		
		Label lblAuditaleRating = new Label("Auditable Unit");
		lblAuditaleRating.addStyleName("labelHeading");
		horizontalPanel.add(lblAuditaleRating);
		lblAuditaleRating.setWidth("180px");
		
		
//		horizontalPanel.addStyleName("statusRowConsolidation");
		
		// heading label of procees
		Label lblAuditaleProcess = new Label("Process");
		lblAuditaleProcess.addStyleName("labelHeading");
		horizontalPanel.add(lblAuditaleProcess);
		lblAuditaleProcess.setWidth("160px");
		
		//heading labelof sub process
		Label lblAuditaleSubProcess = new Label("SubProcess");
		lblAuditaleSubProcess.addStyleName("labelHeading");
		horizontalPanel.add(lblAuditaleSubProcess);
		lblAuditaleSubProcess.setWidth("160px");
		
		//heading labelof  jobtype
				Label lblAuditaleJobType = new Label("JobType");
				lblAuditaleJobType.addStyleName("labelHeading");
				horizontalPanel.add(lblAuditaleJobType);
				lblAuditaleJobType.setWidth("160px");
		
				Label lblTab = new Label("");
				lblTab.addStyleName("blue");
				horizontalPanel.add(lblTab);
				lblTab.setWidth("50px");
				
		ScrollPanel scroll = new ScrollPanel();
		 
//		 scroll.setSize("98%", "375px");
		 scroll.setHeight("375px");
		 scroll.setWidget(vpnlData);
		verticalPanel.add(scroll);
//		vpnlData.setSize("800px", "50%");
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
