package com.internalaudit.client.widgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.internalaudit.client.InternalAuditService;
import com.internalaudit.client.InternalAuditServiceAsync;
import com.sencha.gxt.widget.core.client.form.TextArea;

public class AuditWorkRow extends Composite {

    private TextBox step;

    private Label auditWorkId;

    private TextArea description;

    private ListBox lstReviewer;
   
    private ListBox listBoxRisk;
    private ListBox listBoxExistingCtrl;
    
    private Image removeRow;

    private HorizontalPanel rowContainer;
    
    private int auditEngId = 0;
    private InternalAuditServiceAsync rpcService = GWT.create(InternalAuditService.class);

    public AuditWorkRow() {
	rowContainer = new HorizontalPanel();
	description = new TextArea();
	description.setWidth("300px");
	description.setHeight("90px");
	description.setText("");

	step = new TextBox();
	step.setWidth("75px");
	lstReviewer = new ListBox();
	listBoxRisk = new ListBox();
	listBoxExistingCtrl = new ListBox();
	
	removeRow = new Image("images/deleteIcon.png");
	auditWorkId = new Label("0");
	auditWorkId.addStyleName("hidden");
	//auditWorkId.setWidth("100px");
	auditWorkId.setVisible(false);
	initWidget(rowContainer);
	lstReviewer.setWidth("170px");
	listBoxExistingCtrl.setWidth("165px");

	rowContainer.addStyleName("risksRow");
	description.addStyleName("txtExtendedWidth");
	step.addStyleName("txtShort");
	lstReviewer.addStyleName("txtShrikedWidth");
	listBoxRisk.addStyleName("txtShrikedWidth");
	listBoxExistingCtrl.addStyleName("txtShrikedWidth");
	
	rowContainer.add(step);
	rowContainer.add(description);
	rowContainer.add(lstReviewer);
//	rowContainer.add(listBoxRisk);
	rowContainer.add(listBoxExistingCtrl);

	rowContainer.add(removeRow);
	lstReviewer.setEnabled(false);
	
	
    }
    
    public ListBox getEmployeeList() {
	return this.lstReviewer;
    }

    public TextBox getStep() {
	return step;
    }

    public void setStep(TextBox step) {
	this.step = step;
    }

    public TextArea getDescription() {
	return description;
    }

    public void setDescription(TextArea description) {
	this.description = description;
    }

    public ListBox getLstControls() {
	return lstReviewer;
    }

    public void setLstControls(ListBox lstControls) {
	this.lstReviewer = lstControls;
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
	lstReviewer.setEnabled(false);
	listBoxRisk.setEnabled(false);
	listBoxExistingCtrl.setEnabled(false);
	removeRow.setVisible(false);
    }

    public void enableFields() {
	step.setEnabled(true);
	description.setEnabled(true);
	lstReviewer.setEnabled(true);
	removeRow.setVisible(true);
	listBoxRisk.setEnabled(true);
	listBoxExistingCtrl.setEnabled(true);
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
	lstReviewer.removeFromParent();
	removeRow.removeFromParent();
	listBoxRisk.removeFromParent();
	listBoxExistingCtrl.removeFromParent();
	step.removeFromParent();
    }

	public ListBox getLstReviewer() {
		return lstReviewer;
	}

	public void setLstReviewer(ListBox lstReviewer) {
		this.lstReviewer = lstReviewer;
	}

	public ListBox getListBoxRisk() {
		return listBoxRisk;
	}

	public void setListBoxRisk(ListBox listBoxRisk) {
		this.listBoxRisk = listBoxRisk;
	}

	public ListBox getListBoxExistingCtrl() {
		return listBoxExistingCtrl;
	}

	public void setListBoxExistingCtrl(ListBox listBoxExistingCtrl) {
		this.listBoxExistingCtrl = listBoxExistingCtrl;
	}

}
