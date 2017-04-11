package com.internalaudit.client.view.Reporting;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ResponsiblePersonView extends VerticalPanel {
	
	public ResponsiblePersonView(){
		
		createLayout();
	}
	
	private void createLayout() {
		HorizontalPanel hpnlHeading = new HorizontalPanel();
		Label heading = new Label("Management Comments");
		Label auditJob = new Label("Audit Job");
		Label exception = new Label("Exception");
		Label managementComments = new Label("Management Comments");
		Label implementaionDate = new Label("Implementaion");
		hpnlHeading.add(auditJob);
		hpnlHeading.add(exception);
		hpnlHeading.add(managementComments);
		hpnlHeading.add(implementaionDate);
		add(hpnlHeading);
		for(int i=0; i< hpnlHeading.getWidgetCount()-1; i++){
			hpnlHeading.getWidget(i).setWidth("320px");
		}
		
		auditJob.setStyleName("boldText");
		exception.setStyleName("boldText");
		managementComments.setStyleName("boldText");
		implementaionDate.setStyleName("boldText");
		heading.setStyleName("heading_2");
		auditJob.setVisible(false);
	}

}
