package com.internalaudit.client.view;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;
import com.internalaudit.client.view.AuditEngagement.LabelHeading;

public class RiskFactorSettingsHeading extends HorizontalPanel{
	
	private HorizontalPanel hpnlHeader ;
	private HorizontalPanel hpnlHeaderChild;
	private Image imgAddRiskFactor;
	
	public RiskFactorSettingsHeading() {
		add(layout());
	}

	private Widget layout() {
		hpnlHeader = new HorizontalPanel();		
		hpnlHeader.setWidth("1000px");
		
		LabelHeading lblDegreeImportance = new LabelHeading("Risk Factors");
		hpnlHeader.add(lblDegreeImportance);
		lblDegreeImportance.setWidth("380px");
		
		LabelHeading lblDegreeDescription = new LabelHeading("Risk Description");
		hpnlHeader.add(lblDegreeDescription);
		lblDegreeDescription.setWidth("400px");
		
		hpnlHeaderChild = new HorizontalPanel();
		hpnlHeader.add(hpnlHeaderChild);
		
		imgAddRiskFactor = new Image("images/add.png"); 
		imgAddRiskFactor.addStyleName("w3-right");
		hpnlHeader.add(imgAddRiskFactor);
	
		return hpnlHeader;
	}

	public Image getImgAddRiskFactor() {
		return imgAddRiskFactor;
	}

	public void setImgAddRiskFactor(Image imgAddRiskFactor) {
		this.imgAddRiskFactor = imgAddRiskFactor;
	}

	public HorizontalPanel getHpnlHeaderChild() {
		return hpnlHeaderChild;
	}

	public void setHpnlHeaderChild(HorizontalPanel hpnlHeaderChild) {
		this.hpnlHeaderChild = hpnlHeaderChild;
	}

	public HorizontalPanel getHpnlHeader() {
		return hpnlHeader;
	}

	public void setHpnlHeader(HorizontalPanel hpnlHeader) {
		this.hpnlHeader = hpnlHeader;
	}
	
}
