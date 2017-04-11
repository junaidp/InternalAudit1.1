package com.internalaudit.client.widgets;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;

public class RiskRow extends Composite {
	
	private TextBox description;
	
	private TextBox control;
	
	private HorizontalPanel rowContainer;
	
	private Label riskId;
	
	private Image removeRow;
	
	public RiskRow()
	{
		rowContainer = new HorizontalPanel();
		description = new TextBox();
		control = new  TextBox();
		riskId = new Label("0");
		removeRow = new Image("images/deleteIcon.png");
		removeRow.setStyleName("pointerStyle");
		riskId.addStyleName("hidden");
		
		initWidget(rowContainer);
		
		rowContainer.addStyleName("risksRow");
		description.setStyleName("txtNormal");
		control.setStyleName("txtNormal");
		
		rowContainer.add(description);
		rowContainer.add(control);
		rowContainer.add(removeRow);
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
		removeRow.setVisible(false);
	
}

	public Image getRemoveRow() {
		return removeRow;
	}

	public void setRemoveRow(Image removeRow) {
		this.removeRow = removeRow;
	}

	public void removeRow() {
		description.removeFromParent();
		control.removeFromParent();
		removeRow.removeFromParent();
	}

	public void enableFields() {
		description.setEnabled(true);
		control.setEnabled(true);
		removeRow.setVisible(true);
	}
}
