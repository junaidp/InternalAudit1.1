
package com.internalaudit.client.view;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;

public class RiskFactorHeadingView extends Composite {

	public RiskFactorHeadingView() {

		HorizontalPanel hpnlMain = new HorizontalPanel();
		HorizontalPanel hpnlSpacing = new HorizontalPanel();
		HorizontalPanel hpnlHeading = new HorizontalPanel();
		initWidget(hpnlMain);
		hpnlMain.add(hpnlSpacing);
		hpnlMain.add(hpnlHeading);
		hpnlSpacing.setWidth("0px");
		Label riskFactor = new Label("Risk factors");
		Label impact = new Label("Impact");
		Label probabality = new Label("Probability");
		Label rating = new Label("Rating");
		Label riskComments = new Label("Comment");

		hpnlHeading.add(riskFactor);
		riskFactor.getElement().getStyle().setMarginLeft(2, Unit.PX);

		hpnlHeading.add(riskComments);
		hpnlHeading.add(impact);
		hpnlHeading.add(probabality);
		hpnlHeading.add(rating);
		// hpnlHeading.setWidth("700px");
		impact.addStyleName("blue");
		probabality.addStyleName("blue");
		rating.addStyleName("blue");

		impact.setWidth("70px");
		impact.getElement().getStyle().setMarginLeft(10, Unit.PX);
		probabality.setWidth("70px");
		rating.setWidth("70px");
		riskFactor.addStyleName("blue");
		riskComments.addStyleName("blue");
		riskFactor.setWidth("245px");
		riskComments.setWidth("600px");

	}

}
