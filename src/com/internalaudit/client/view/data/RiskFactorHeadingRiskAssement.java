package com.internalaudit.client.view.data;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.internalaudit.client.view.RiskFactorSettingsHeading;
import com.internalaudit.client.view.AuditEngagement.LabelBold;
import com.internalaudit.client.view.AuditEngagement.LabelHeading;

public class RiskFactorHeadingRiskAssement extends HorizontalPanel{
	
	private RiskFactorSettingsHeading riskFactorSettingsHeading;
	
	public RiskFactorHeadingRiskAssement() {
		add(layoutMain());
	}

	private Widget layoutMain() {

		riskFactorSettingsHeading = new RiskFactorSettingsHeading();
		HorizontalPanel hpnlMain = riskFactorSettingsHeading.getHpnlHeader();
//		hpnlMain.setWidth("970px");
		
		LabelHeading lblDegreeDescription = new LabelHeading("Risk Description");
		riskFactorSettingsHeading.getHpnlHeaderChild().add(lblDegreeDescription);
		lblDegreeDescription.setWidth("400px");
		
		LabelHeading lblProbability = new LabelHeading("Probability");
		lblProbability.setWidth("100%");
		riskFactorSettingsHeading.getHpnlHeaderChild().add(lblProbability);
		return hpnlMain;
	}

	public RiskFactorSettingsHeading getRiskFactorSettingsHeading() {
		return riskFactorSettingsHeading;
	}
	
}
