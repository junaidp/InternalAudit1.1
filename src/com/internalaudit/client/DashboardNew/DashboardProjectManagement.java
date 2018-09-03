package com.internalaudit.client.DashboardNew;

import javax.sound.sampled.Port;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.internalaudit.shared.DashBoardNewDTO;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;

public class DashboardProjectManagement extends VerticalLayoutContainer {
	public DashboardProjectManagement(DashBoardNewDTO dashboardDTO) {
		ProjectManagementTimeLine timeLine = new ProjectManagementTimeLine();
		ProjectManagementActualHours actualHours = new ProjectManagementActualHours();
		ProjectManagementDate pmDate = new ProjectManagementDate();
		PortalInformationRequest portalInformation = new PortalInformationRequest();
		PortalOutstandingCoaching portalOutstanding = new PortalOutstandingCoaching(dashboardDTO.getExceptions());
		Label l = new Label("ada");
//		add(portalOutstanding);
//		add(timeLine);
//		add(actualHours);
		

		 DashboardListBoxes dashboardlistBox = new DashboardListBoxes();
		
		HorizontalPanel mainPanel = new HorizontalPanel();
		HorizontalPanel upperPanel = new HorizontalPanel();
		mainPanel.setWidth("1200px");
		VerticalPanel panelLeft = new VerticalPanel();
		panelLeft.setWidth("500px");
		VerticalPanel panelRight = new VerticalPanel();
		panelRight.setWidth("650%");
		VerticalPanel panelDate = new VerticalPanel();
		panelDate.add(pmDate);
		panelDate.getElement().getStyle().setPaddingLeft(40, Unit.PX);
		panelDate.getElement().getStyle().setPaddingTop(20, Unit.PX);
		//
		panelLeft.add(timeLine);
		panelLeft.add(actualHours);
		upperPanel.add(dashboardlistBox);
		upperPanel.add(panelDate);
		panelRight.add(portalInformation);
		panelRight.add(portalOutstanding);

		mainPanel.add(panelLeft);
		mainPanel.add(panelRight);
		add(upperPanel);
		add(mainPanel);

	}
}
