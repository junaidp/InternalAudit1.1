package com.internalaudit.client.view;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.internalaudit.client.view.AuditEngagement.LabelBold;
import com.internalaudit.client.view.AuditEngagement.LabelHeading;

public class DegreeOfImportanceHeading extends VerticalPanel{
	
	private Image imgAddDegree;
	private HorizontalPanel hpnlRiskAssesmentFields; 
	
	public DegreeOfImportanceHeading() {
		add(layoutHeaderDegreeOfImportance());
	}
	
	private Widget layoutHeaderDegreeOfImportance() {
		HorizontalPanel hpnlHeader = new HorizontalPanel();
		hpnlHeader.setWidth("1000px");
		
		LabelHeading lblDegreeImportance = new LabelHeading("Degree of importance");
		hpnlHeader.add(lblDegreeImportance);
		lblDegreeImportance.setWidth("400px");
		
		hpnlRiskAssesmentFields = new HorizontalPanel(); 
		hpnlRiskAssesmentFields.setVisible(false);
		LabelHeading lblComment = new LabelHeading("Comment");
		LabelHeading lblWeightage = new LabelHeading("Weightage");
		LabelHeading lblRating = new LabelHeading("Rating");
		
		lblComment.setWidth("440px");
		lblWeightage.setWidth("100px");
		
		hpnlRiskAssesmentFields.add(lblComment);
		hpnlRiskAssesmentFields.add(lblWeightage);
		hpnlRiskAssesmentFields.add(lblRating);
		hpnlHeader.add(hpnlRiskAssesmentFields);
		
		imgAddDegree = new Image("images/add.png"); 
		imgAddDegree.addStyleName("w3-right");
		hpnlHeader.add(imgAddDegree);
	
		return hpnlHeader;
	}

	public void visibleHpnlRiskAssesment() {
		hpnlRiskAssesmentFields.setVisible(true);
	}
	
	public Image getImgAddDegree() {
		return imgAddDegree;
	}

	public void setImgAddDegree(Image imgAddDegree) {
		this.imgAddDegree = imgAddDegree;
	}
	
}
