package com.internalaudit.client.widgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.internalaudit.client.InternalAuditService;
import com.internalaudit.client.InternalAuditServiceAsync;
import com.internalaudit.client.view.AuditEngagement.LabelHeading;

public class AuditWorkRow extends Composite {

	private TextBox step;

	private Label auditWorkId;

	private TextArea description;

	private Label txtBoxExistingControls;

	private ListBox lstReviewer;

	private ListBox listBoxRisk;
	private ListBox listBoxExistingCtrl;

	private Image removeRow;
	
	VerticalPanel containerExistingControls;

	public VerticalPanel getContainerExistingControls() {
		return containerExistingControls;
	}

	public void setContainerExistingControls(VerticalPanel containerExistingControls) {
		this.containerExistingControls = containerExistingControls;
	}

	private HorizontalPanel rowContainer;
	private LabelHeading lblControls = new LabelHeading("Controls") ;
	private LabelHeading lblAuditProgram = new LabelHeading("Audit Program") ;
	
	public LabelHeading getLblControls() {
		return lblControls;
	}

	public void setLblControls(LabelHeading lblControls) {
		this.lblControls = lblControls;
	}

	public LabelHeading getLblAuditProgram() {
		return lblAuditProgram;
	}

	public void setLblAuditProgram(LabelHeading lblAuditProgram) {
		this.lblAuditProgram = lblAuditProgram;
	}

	private int auditEngId = 0;
	private InternalAuditServiceAsync rpcService = GWT.create(InternalAuditService.class);

	public AuditWorkRow() {
		//for moqeet added new flectable and added headings in that . at first 
		// headins were in auditworkProg tab..i sset visible false in audtwork prog.
		// check line 561 of auditworkprog.
		rowContainer = new HorizontalPanel();
		
		FlexTable flex = new FlexTable();
		
		lblControls.addStyleName("txtExtendedControlWidth");
		lblAuditProgram.addStyleName("txtExtendedAuditProgramWidth");
		
		description = new TextArea();
		description.setWidth("750px");
		description.setHeight("90px");
		description.setText("");
		description.getElement().getStyle().setMarginLeft(20, Unit.PX);
		step = new TextBox();
		step.setWidth("75px");
		lstReviewer = new ListBox();
		listBoxRisk = new ListBox();
		listBoxExistingCtrl = new ListBox();
		// txtBoxExistingControls = new TextArea();
		// txtBoxExistingControls.setWidth("300px");
		// txtBoxExistingControls.setHeight("90px");
		txtBoxExistingControls = new Label();
		txtBoxExistingControls.getElement().getStyle().setMarginLeft(8, Unit.PX);
		txtBoxExistingControls.setWidth("290px");
		// txtBoxExistingControls.setHeight("90px");

		removeRow = new Image("images/deleteIcon.png");
		auditWorkId = new Label("0");
		auditWorkId.addStyleName("hidden");
		// auditWorkId.setWidth("100px");
		auditWorkId.setVisible(false);
		initWidget(flex);
		lstReviewer.setWidth("170px");
		listBoxExistingCtrl.setWidth("165px");
		removeRow.getElement().getStyle().setMarginLeft(30, Unit.PX);
		// listBoxExistingCtrl.getElement().getStyle().setMarginLeft(80,
		// Unit.PX);

		rowContainer.addStyleName("risksRow");
		description.addStyleName("txtExtendedWidth");
		step.addStyleName("txtShort");
		lstReviewer.addStyleName("txtShrikedWidth");
		listBoxRisk.addStyleName("txtShrikedWidth");
		listBoxExistingCtrl.addStyleName("listTextBold");
		listBoxExistingCtrl.getElement().getStyle().setMarginLeft(8, Unit.PX);

		containerExistingControls = new VerticalPanel();

		containerExistingControls.add(listBoxExistingCtrl);
		containerExistingControls.add(txtBoxExistingControls);
		containerExistingControls.setWidth("300px");

		// rowContainer.add(step);
	//	rowContainer.add(containerExistingControls);
	//	rowContainer.add(description);
		// rowContainer.add(lstReviewer);
		// rowContainer.add(listBoxRisk);
		
		flex.setWidget(0, 1, lblControls);
		flex.setWidget(1, 1, containerExistingControls);
		
		flex.setWidget(0, 2, lblAuditProgram);
		flex.setWidget(1, 2, description);
		
		flex.setWidget(1, 3, removeRow);
		
		

		
	//	rowContainer.add(removeRow);
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
		// mar 2020visibilty false of listbox
		listBoxExistingCtrl.setVisible(false);
		lstReviewer.setEnabled(false);
		// txtBoxExistingControls.setEnabled(false);
		listBoxRisk.setEnabled(false);
		listBoxExistingCtrl.setEnabled(false);
		removeRow.setVisible(false);
	}

	public void enableFields() {
		step.setEnabled(true);
		description.setEnabled(true);
		lstReviewer.setEnabled(true);
		// txtBoxExistingControls.setEnabled(true);
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
		lblAuditProgram.removeFromParent();
		lblControls.removeFromParent();
		description.removeFromParent();
		lstReviewer.removeFromParent();
		txtBoxExistingControls.removeFromParent();
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

	public Label getTxtBoxExistingControls() {
		return txtBoxExistingControls;
	}

	public void setTxtBoxExistingControls(Label txtBoxExistingControls) {
		this.txtBoxExistingControls = txtBoxExistingControls;
	}

}
