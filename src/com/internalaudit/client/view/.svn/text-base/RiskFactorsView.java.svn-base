package com.internalaudit.client.view;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;

public class RiskFactorsView extends Composite{
	private Label riskFactor;
	private ListBox rating;
	private int riskFactorId;
	private TextBox comments;
	
	public RiskFactorsView() {
		
		HorizontalPanel hpnlMain = new HorizontalPanel();
		initWidget(hpnlMain);
		hpnlMain.setSize("100%", "100%");
		
		riskFactor = new Label("");
		hpnlMain.add(riskFactor);
		riskFactor.setWidth("300px");
		
		rating = new ListBox();
		hpnlMain.add(rating);
		rating.setVisibleItemCount(1);
		
		rating.addItem("N/A");
		rating.addItem("Low");
		rating.addItem("Medium");
		rating.addItem("High");
		
		comments = new TextBox();
		hpnlMain.add(comments);
		comments.setWidth("190px");
		
	}

	public Label getRiskFactor() {
		return riskFactor;
	}

	public void setRiskFactor(Label riskFactor) {
		this.riskFactor = riskFactor;
	}

	public ListBox getRating() {
		return rating;
	}

	public void setRating(ListBox rating) {
		this.rating = rating;
	}

	public int getRiskFactorId() {
		return riskFactorId;
	}

	public void setRiskFactorId(int riskFactorId) {
		this.riskFactorId = riskFactorId;
	}

	public TextBox getComments() {
		return comments;
	}

	public void setComments(TextBox comments) {
		this.comments = comments;
	}

	

}
