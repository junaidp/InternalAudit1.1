package com.internalaudit.client.widgets;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;

public class AuditWorkRow extends Composite {

    private TextBox step;

    private Label auditWorkId;

    private TextBox description;

    private ListBox lstControls;
    private Image removeRow;

    private HorizontalPanel rowContainer;

    public AuditWorkRow() {

	rowContainer = new HorizontalPanel();
	description = new TextBox();
	step = new TextBox();
	lstControls = new ListBox();
	removeRow = new Image("images/deleteIcon.png");
	auditWorkId = new Label("0");
	auditWorkId.addStyleName("hidden");
	auditWorkId.setVisible(false);
	initWidget(rowContainer);
	lstControls.setWidth("250px");

	rowContainer.addStyleName("risksRow");
	description.addStyleName("txtExtendedWidth");
	step.addStyleName("txtShort");
	lstControls.addStyleName("txtShrikedWidth");

	rowContainer.add(step);
	rowContainer.add(description);
	rowContainer.add(lstControls);
	rowContainer.add(removeRow);
	lstControls.setEnabled(false);
    }

    public ListBox getEmployeeList() {
	return this.lstControls;
    }

    public TextBox getStep() {
	return step;
    }

    public void setStep(TextBox step) {
	this.step = step;
    }

    public TextBox getDescription() {
	return description;
    }

    public void setDescription(TextBox description) {
	this.description = description;
    }

    public ListBox getLstControls() {
	return lstControls;
    }

    public void setLstControls(ListBox lstControls) {
	this.lstControls = lstControls;
    }

    public HorizontalPanel getRowContainer() {
	return rowContainer;
    }

    public void setRowContainer(HorizontalPanel rowContainer) {
	this.rowContainer = rowContainer;
    }

    public Label getAuditWorkId() {
	return auditWorkId;
    }

    public void setAuditWorkId(Label auditWorkId) {
	this.auditWorkId = auditWorkId;
    }

    public void disableFields() {
	step.setEnabled(false);
	description.setEnabled(false);
	lstControls.setEnabled(false);
	removeRow.setVisible(false);
    }

    public void enableFields() {
	step.setEnabled(true);
	description.setEnabled(true);
	lstControls.setEnabled(true);
	removeRow.setVisible(true);
    }

    public void showAuditHeadView() {

    }

    public Image getRemoveRow() {
	return removeRow;
    }

    public void setRemoveRow(Image removeRow) {
	this.removeRow = removeRow;
    }

    public void removeRow() {
	description.removeFromParent();
	lstControls.removeFromParent();
	removeRow.removeFromParent();
	step.removeFromParent();
    }

}
