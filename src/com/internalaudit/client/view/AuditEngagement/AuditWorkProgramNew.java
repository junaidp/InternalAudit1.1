package com.internalaudit.client.view.AuditEngagement;

import com.google.gwt.dom.client.Style.FontWeight;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.internalaudit.shared.AuditProgramme;
import com.internalaudit.shared.Employee;
import com.internalaudit.shared.SuggestedControls;

public class AuditWorkProgramNew extends VerticalPanel {
	Label lblSerialNo = new Label("Serial No.");
	LabelHeading lblReference = new LabelHeading();
	LabelHeading lblAuditProg = new LabelHeading();
	// Label lblReviewer = new Label("Reviewer");
	TextArea txtAreaAuditProg = new TextArea();
	// ListBox listBoxReviewerData = new ListBox();
	Label lblSerialNoData = new Label("");
	Image delete = new Image("images/deleteIcon.png");
	Label lblReferenceData = new Label("");
	private int auditWorkProgrammeId = 0;
	private Button btnSelect = new Button("Select");
	private SuggestedControls control = new SuggestedControls();
	VerticalPanel panelReference = new VerticalPanel();
	VerticalPanel panelAuditProg = new VerticalPanel();

	public AuditWorkProgramNew() {
		// TODO Auto-generated method stub
		VerticalPanel vpn = new VerticalPanel();
		// TODO Auto-generated method stub

		lblReference.setText("Controls");
		lblAuditProg.setText("Audit Program");
		lblSerialNo.addStyleName("w3-panel w3-light-blue");

		lblReference.addStyleName("w3-panel");

		lblAuditProg.addStyleName("w3-panel");

		// lblReviewer.addStyleName("w3-panel w3-light-blue");

		txtAreaAuditProg.setWidth("550px");
		// txtAreaAuditProg.setHeight("90px");
		txtAreaAuditProg.setText("");

		lblSerialNo.getElement().getStyle().setFontWeight(FontWeight.BOLD);
		// lblAuditProg.getElement().getStyle().setFontWeight(FontWeight.BOLD);
		// lblReviewer.getElement().getStyle().setFontWeight(FontWeight.BOLD);
		// lblReference.getElement().getStyle().setFontWeight(FontWeight.BOLD);

		lblAuditProg.getElement().getStyle().setMarginLeft(1, Unit.PX);
		lblReference.getElement().getStyle().setMarginLeft(10, Unit.PX);
		// lblReviewer.getElement().getStyle().setMarginLeft(10, Unit.PX);
		lblSerialNoData.setWidth("150x");
		lblSerialNoData.addStyleName("w3-panel");
		lblReferenceData.addStyleName("w3-panel");
		txtAreaAuditProg.addStyleName("w3-panel");
		// listBoxReviewerData.addStyleName("w3-panel");
		// listBoxReviewerData.addItem("korgenthead");

		lblReferenceData.getElement().getStyle().setMarginLeft(10, Unit.PX);
		txtAreaAuditProg.getElement().getStyle().setMarginLeft(10, Unit.PX);
		// listBoxReviewerData.getElement().getStyle().setMarginLeft(20,
		// Unit.PX);

		FlexTable flex = new FlexTable();

		// flex.setWidget(0,1, lblSerialNo);
		// VerticalPanel panelSerial = new VerticalPanel();
		// panelSerial.setWidth("180px");
		// panelSerial.add(lblSerialNoData);
		// flex.setWidget(1,1,panelSerial);

		flex.setWidget(0, 2, lblReference);
		panelReference.setWidth("500px");
		panelReference.add(lblReferenceData);
		flex.setWidget(1, 2, panelReference);

		flex.setWidget(0, 3, lblAuditProg);
		panelAuditProg.setWidth("550px");
		panelAuditProg.add(txtAreaAuditProg);
		flex.setWidget(1, 3, panelAuditProg);

		// flex.setWidget(0,4, lblReviewer);
		// flex.setWidget(1,4,listBoxReviewerData);
		flex.setWidget(1, 5, btnSelect);

		add(flex);
	}

	public void setPopupView() {
		panelAuditProg.setWidth("250px");
		panelReference.setWidth("250px");
		// txtAreaAuditProg.setWidth("250px");
		txtAreaAuditProg.setSize("250px", "90px");
	}

	public void hideElemetns() {
		lblSerialNo.setVisible(false);
		// lblReviewer.setVisible(false);
		lblAuditProg.setVisible(false);
		lblReference.setVisible(false);

	}

	public void setData(AuditProgramme auditProgramme) {
		txtAreaAuditProg.setText(auditProgramme.getAuditProgrammeName());
		// slistBoxReviewerData.(auditProgramme.getReviewer().getEmployeeName());

		// listBoxReviewerData.getValue(auditWorkProgrammeId).equals(auditProgramme.getReviewer().getEmployeeName());
		// listBoxReviewerData.setSelectedIndex(auditProgramme.getReviewer().getEmployeeName());
		lblSerialNoData.setText(auditProgramme.getAuditProgrammeId() + "");
		lblReferenceData.setText(auditProgramme.getSuggestedControlsId().getSuggestedControlsName());
		auditWorkProgrammeId = auditProgramme.getAuditProgrammeId();
		control = auditProgramme.getSuggestedControlsId();
		// TODO populate other , refDate =
		// auditProgramme.getSuggestedControlsId().getRefNo(); (Add refNo colun
		// in suggestcontrols Table , data is in auditeng PDF)
		//

	}

	public void getData(AuditProgramme auditProgramme) {
		auditProgramme.setAuditProgrammeId(auditWorkProgrammeId);
		auditProgramme.setAuditProgrammeName(txtAreaAuditProg.getText());
		Employee employee = new Employee();
		employee.setEmployeeId(58);
		auditProgramme.setReviewer(employee);
		auditProgramme.setSuggestedControlsId(control);

	}

	public TextArea getTxtAreaAuditProg() {
		return txtAreaAuditProg;
	}

	public void setTxtAreaAuditProg(TextArea txtAreaAuditProg) {
		this.txtAreaAuditProg = txtAreaAuditProg;
	}

	public Label getLblSerialNoData() {
		return lblSerialNoData;
	}

	public void setLblSerialNoData(Label lblSerialNoData) {
		this.lblSerialNoData = lblSerialNoData;
	}

	public Label getLblReferenceData() {
		return lblReferenceData;
	}

	public void setLblReferenceData(Label lblReferenceData) {
		this.lblReferenceData = lblReferenceData;
	}

	public int getAuditWorkProgrammeId() {
		return auditWorkProgrammeId;
	}

	public void setAuditWorkProgrammeId(int auditWorkProgrammeId) {
		this.auditWorkProgrammeId = auditWorkProgrammeId;
	}

	public Button getBtnSelect() {
		return btnSelect;
	}

	public void setBtnSelect(Button btnSelect) {
		this.btnSelect = btnSelect;
	}
}