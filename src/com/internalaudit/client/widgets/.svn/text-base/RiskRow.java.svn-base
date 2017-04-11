package com.internalaudit.client.widgets;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;

public class RiskRow extends Composite {
	
	private TextBox description;
	
	private TextBox control;
	
	private HorizontalPanel rowContainer;
	
	private Label riskId;
	
	public RiskRow()
	{
		rowContainer = new HorizontalPanel();
		description = new TextBox();
		control = new  TextBox();
		riskId = new Label("0");
		riskId.addStyleName("hidden");
		
		initWidget(rowContainer);
		
		rowContainer.addStyleName("risksRow");
		description.setStyleName("txtNormal");
		control.setStyleName("txtNormal");
		
		rowContainer.add(description);
		rowContainer.add(control);
	}

	public TextBox getDescription() {
		return description;
	}

	public void setDescription(TextBox description) {
		this.description = description;
	}

	public TextBox getControl() {
		return control;
	}

	public void setControl(TextBox control) {
		this.control = control;
	}

	public Label getRiskId() {
		return riskId;
	}

	public void setRiskId(Label riskId) {
		this.riskId = riskId;
	}

	
	public void disableFields(){
		description.setEnabled(false);
		control.setEnabled(false);
	
}
}
