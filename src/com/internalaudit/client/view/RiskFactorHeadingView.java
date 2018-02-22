package com.internalaudit.client.view;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;

public class RiskFactorHeadingView extends Composite {
	
	public RiskFactorHeadingView(){
		
		HorizontalPanel hpnlMain = new HorizontalPanel();
		HorizontalPanel hpnlSpacing = new HorizontalPanel();
		HorizontalPanel hpnlHeading = new HorizontalPanel();
		initWidget(hpnlMain);
		hpnlMain.add(hpnlSpacing);
		hpnlMain.add(hpnlHeading);
		hpnlSpacing.setWidth("0px");
		Label riskFactor = new Label("Risk factors");
		Label impact = new Label("Impact");
		Label probabality = new Label("Probabality");
		Label risk = new Label("Rating");
		Label riskComments = new Label("Comment");
		
		hpnlHeading.add(riskFactor);
		hpnlHeading.add(riskComments);
		hpnlHeading.add(impact);
		hpnlHeading.add(probabality);
		hpnlHeading.add(risk);
		hpnlHeading.setWidth("700px");
		impact.addStyleName("blue");
		probabality.addStyleName("blue");
		risk.addStyleName("blue");
		impact.setWidth("75px");
		probabality.setWidth("75px");
		riskFactor.addStyleName("blue");
		riskComments.addStyleName("blue");
		riskFactor.setWidth("330px");
		riskComments.setWidth("360px");
		
	}

}
