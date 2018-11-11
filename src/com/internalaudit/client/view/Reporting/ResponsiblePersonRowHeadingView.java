package com.internalaudit.client.view.Reporting;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ResponsiblePersonRowHeadingView extends VerticalPanel {
	
	public ResponsiblePersonRowHeadingView(){
		
		createLayout();
	}
	
	private void createLayout() {
		HorizontalPanel hpnlHeading = new HorizontalPanel();
		Label heading = new Label("Management Comments");
		Label auditJob = new Label("Audit Job");
		Label exception = new Label("Exception");
		Label recommendations = new Label("Recommendations");
		Label managementComments = new Label("Management Comments");
		Label implementaionDate = new Label("Implementaion");
		Label isAgree = new Label("Agree");
		Label implication = new Label("implication");
		hpnlHeading.add(auditJob);
		hpnlHeading.add(exception);
		hpnlHeading.add(recommendations);
		hpnlHeading.add(managementComments);
		hpnlHeading.add(implication);
		hpnlHeading.add(implementaionDate);
		hpnlHeading.add(isAgree);
		
		add(hpnlHeading);
		for(int i=0; i< hpnlHeading.getWidgetCount()-1; i++){
			hpnlHeading.getWidget(i).setWidth("270px");
		}
		implication.setWidth("160px");
		isAgree.setWidth("100px");
		implementaionDate.setWidth("120px");
		managementComments.setWidth("260px");
		auditJob.addStyleName("boldText");
		exception.addStyleName("boldText");
		managementComments.addStyleName("boldText");
		implementaionDate.addStyleName("boldText");
		isAgree.addStyleName("boldText");
		recommendations.addStyleName("boldText");
		heading.addStyleName("heading_2");
		auditJob.setVisible(false);
	}

}
