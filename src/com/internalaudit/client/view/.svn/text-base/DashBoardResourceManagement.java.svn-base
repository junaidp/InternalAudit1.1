package com.internalaudit.client.view;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class DashBoardResourceManagement extends Composite {
	private VerticalPanel vpnlResources = new VerticalPanel();
	
	public DashBoardResourceManagement(){
	DecoratorPanel decoratorPanel = new DecoratorPanel();
	VerticalPanel mainPanel = new VerticalPanel();
	mainPanel.setSize("300px", "200px");
	Label lblHeading = new Label("Overall Status");
	lblHeading.setStyleName("popupHeading");
	initWidget(decoratorPanel);
	decoratorPanel.setWidget(mainPanel);
	mainPanel.add(lblHeading);
	mainPanel.setHeight("200px");
	Label lblResources = new Label("Resource free for next 2 weeks");
	lblResources.setWidth("260px");
	mainPanel.add(lblResources);
	lblResources.setStyleName("bold");
	mainPanel.add(vpnlResources);
	
	}

	public VerticalPanel getVpnlResources() {
		return vpnlResources;
	}

	public void setVpnlResources(VerticalPanel vpnlResources) {
		this.vpnlResources = vpnlResources;
	}
}
