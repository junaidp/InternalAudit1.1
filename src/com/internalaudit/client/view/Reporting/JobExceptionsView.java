package com.internalaudit.client.view.Reporting;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.user.datepicker.client.DateBox.DefaultFormat;
import com.sencha.gxt.widget.core.client.form.TextField;

public class JobExceptionsView extends HorizontalPanel {

	private TextArea txtAreaObservations = new TextArea();
	private TextArea recommendations = new TextArea();
	private TextArea txtAreaImplication = new TextArea();
	private ListBox listBoxImplicationRating = new ListBox();
	private ListBox responsiblePerson = new ListBox();
	private ListBox divisionHead = new ListBox();
	private DateBox dueDate = new DateBox();
	private Button btnSave = new Button("Send");
	private VerticalPanel vpnlButtons = new VerticalPanel();
	private Button btnApprove = new Button("Approve");
	private Button btnReject = new Button("Feedback");
	private Label status = new Label("");
	private TextField txtComments = new TextField();
	// private AddImage addIcon = new AddImage();
	// private Anchor anchorAddRecommendations = new Anchor("Add More Action
	// Steps");
	// private Anchor anchorViewActionSteps = new Anchor("View Action Steps");

	public JobExceptionsView() {
		// setWidth("900px");
		createLayout();
		this.getElement().getStyle().setMarginTop(10, Unit.PX);
	}

	private void createLayout() {

		listBoxImplicationRating.addItem("Low", "0");
		listBoxImplicationRating.addItem("Medium", "1");
		listBoxImplicationRating.addItem("High", "2");
		status.addStyleName("blue");
		status.setVisible(false);
		// vpnlButtons.add(btnApprove);
		// vpnlButtons.add(btnReject);
		HorizontalPanel hpnlApprovalButtons = new HorizontalPanel();
		vpnlButtons.add(txtComments);
		hpnlApprovalButtons.add(btnApprove);
		hpnlApprovalButtons.add(btnReject);
		vpnlButtons.add(hpnlApprovalButtons);
		txtComments.setEmptyText("Enter Comments");
		vpnlButtons.setVisible(false);
		add(txtAreaObservations);
		txtAreaObservations.getElement().setPropertyString("placeholder", "Enter text here");
		txtAreaObservations.setWidth("150px");
		txtAreaObservations.getElement().getStyle().setPaddingLeft(7, Unit.PX);
		add(txtAreaImplication);
		txtAreaImplication.getElement().setPropertyString("placeholder", "Enter text here");
		txtAreaImplication.setWidth("145px");
		txtAreaImplication.getElement().getStyle().setPaddingLeft(7, Unit.PX);
		add(listBoxImplicationRating);
		listBoxImplicationRating.setWidth("146px");

		// add(divisionHead);
		add(recommendations);
		dueDate.setFormat(new DefaultFormat(DateTimeFormat.getShortDateFormat()));
		add(dueDate);
		dueDate.getElement().setPropertyString("placeholder", "yyyy-mm-dd");
		dueDate.setWidth("100px");

		recommendations.getElement().setPropertyString("placeholder", "Enter text here");
		recommendations.setWidth("137px");
		add(responsiblePerson);
		responsiblePerson.setWidth("123px");
		// responsiblePerson.setMultipleSelect(true);
		status.setWidth("60px");

		// HorizontalPanel hpnlAnchor = new HorizontalPanel();
		// hpnlAnchor.setWidth("75px");
		// hpnlAnchor.add(anchorAddRecommendations);
		// hpnlAnchor.add(anchorViewActionSteps);
		// anchorViewActionSteps.setVisible(false);
		// add(hpnlAnchor);
		add(btnSave);
		// if(status.getText().length()>1){
		add(status);
		// }
		add(vpnlButtons);
		// btnSave.setWidth("120px");
		// for (int i = 0; i < getWidgetCount() - 1; i++) {
		// getWidget(i).setWidth("150px");
		// }
		setSpacing(2);
		// vpnlButtons.setWidth("100px");
		status.setWidth("100px");
		dueDate.setWidth("100px");
		txtAreaImplication.setHeight("300px");
		// listBoxImplicationRating.getElement().getStyle().setMarginLeft(10,
		// Unit.PX);
		listBoxImplicationRating.addStyleName("noresize");
		txtAreaObservations.setHeight("300px");
		recommendations.setHeight("300px");
		txtAreaImplication.addStyleName("noresize ");
		txtAreaObservations.addStyleName("noresize");
		recommendations.addStyleName("noresize");

	}

	public Button getBtnSave() {
		return btnSave;
	}

	public void setBtnSave(Button btnSave) {
		this.btnSave = btnSave;
	}

	public TextArea getException() {
		return txtAreaObservations;
	}

	public void setException(TextArea exception) {
		this.txtAreaObservations = exception;
	}

	public ListBox getResponsiblePerson() {
		return responsiblePerson;
	}

	public void setResponsiblePerson(ListBox responsiblePerson) {
		this.responsiblePerson = responsiblePerson;
	}

	public DateBox getDueDate() {
		return dueDate;
	}

	public void setDueDate(DateBox dueDate) {
		this.dueDate = dueDate;
	}

	public ListBox getDivisionHead() {
		return divisionHead;
	}

	public void setDivisionHead(ListBox divisionHead) {
		this.divisionHead = divisionHead;
	}

	public void disableFields() {
		txtAreaObservations.setEnabled(false);
		responsiblePerson.setEnabled(false);
		divisionHead.setEnabled(false);
		dueDate.setEnabled(false);
		btnSave.setEnabled(false);
		recommendations.setEnabled(false);
		txtAreaImplication.setEnabled(false);
		listBoxImplicationRating.setEnabled(false);
	}

	public void showApprovalButtons() {
		vpnlButtons.setVisible(true);
		btnSave.setVisible(false);
	}

	public void hideApprovalButtons() {
		vpnlButtons.setVisible(false);
		btnSave.setVisible(true);
	}

	public VerticalPanel getHpnlButtons() {
		return vpnlButtons;
	}

	public void setHpnlButtons(VerticalPanel hpnlButtons) {
		this.vpnlButtons = hpnlButtons;
	}

	public Button getBtnApprove() {
		return btnApprove;
	}

	public void setBtnApprove(Button btnApprove) {
		this.btnApprove = btnApprove;
	}

	public Button getBtnReject() {
		return btnReject;
	}

	public void setBtnReject(Button btnReject) {
		this.btnReject = btnReject;
	}

	public Label getStatus() {
		return status;
	}

	public void setStatus(Label status) {
		this.status = status;
	}

	public TextField getTxtComments() {
		return txtComments;
	}

	public void setTxtComments(TextField txtComments) {
		this.txtComments = txtComments;
	}

	public TextArea getRecommendations() {
		return recommendations;
	}

	public void setRecommendations(TextArea recommendations) {
		this.recommendations = recommendations;
	}

	public TextArea getTxtAreaImplication() {
		return txtAreaImplication;
	}

	public void setTxtAreaImplication(TextArea txtAreaImplication) {
		this.txtAreaImplication = txtAreaImplication;
	}

	public ListBox getListBoxImplicationRating() {
		return listBoxImplicationRating;
	}

	public void setListBoxImplicationRating(ListBox listBoxImplicationRating) {
		this.listBoxImplicationRating = listBoxImplicationRating;
	}

	// public Anchor getAnchorAddRecommendations() {
	// return anchorAddRecommendations;
	// }
	//
	// public void setAnchorAddRecommendations(Anchor anchorAddRecommendations)
	// {
	// this.anchorAddRecommendations = anchorAddRecommendations;
	// }
	//
	// public Anchor getAnchorViewActionSteps() {
	// return anchorViewActionSteps;
	// }
	//
	// public void setAnchorViewActionSteps(Anchor anchorViewActionSteps) {
	// this.anchorViewActionSteps = anchorViewActionSteps;
	// }

}
