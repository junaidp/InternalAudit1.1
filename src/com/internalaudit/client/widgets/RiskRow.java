package com.internalaudit.client.widgets;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.internalaudit.client.view.AuditEngagement.RiskControlMatrixView;

public class RiskRow extends Composite {

	// private TextBox description;

	// private TextBox control;

	private RiskControlMatrixView riskControlMatrixView;

	private HorizontalPanel rowContainer;

	private Label riskId;

	private Image removeRow;

	public RiskRow() {
		rowContainer = new HorizontalPanel();
		// description = new TextBox();
		// control = new TextBox();
		riskControlMatrixView = new RiskControlMatrixView();
		riskControlMatrixView.getListBoxRisk().setVisible(true);
		riskControlMatrixView.getBtnSelect().setVisible(false);
		riskId = new Label("0");
		removeRow = new Image("images/deleteIcon.png");
		removeRow.addStyleName("pointerStyle");
		removeRow.getElement().getStyle().setMarginTop(18, Unit.PX);
		riskId.addStyleName("hidden");

		initWidget(rowContainer);

		rowContainer.addStyleName("risksRow");
		// description.addStyleName("txtNormal");
		// control.addStyleName("txtNormal");

		// rowContainer.add(description);
		// rowContainer.add(control);
		rowContainer.add(riskControlMatrixView);
		rowContainer.add(removeRow);
	}

	public Label getRiskId() {
		return riskId;
	}

	public void setRiskId(Label riskId) {
		this.riskId = riskId;
	}

	public void disableFields() {
		// description.setEnabled(false);
		// control.setEnabled(false);
		removeRow.setVisible(false);
		riskControlMatrixView.disableFields();

	}

	public Image getRemoveRow() {
		return removeRow;
	}

	public void setRemoveRow(Image removeRow) {
		this.removeRow = removeRow;
	}

	public void removeRow() {
		// description.removeFromParent();
		// control.removeFromParent();
		removeRow.removeFromParent();

	}

	public void enableFields() {
		// description.setEnabled(true);
		// control.setEnabled(true);
		removeRow.setVisible(true);
		riskControlMatrixView.enableFields();
	}

	public RiskControlMatrixView getExistingControlView() {
		return riskControlMatrixView;
	}
}
