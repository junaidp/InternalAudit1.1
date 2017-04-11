package com.internalaudit.client.view.Reporting;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.user.datepicker.client.DateBox.DefaultFormat;
import com.google.gwt.i18n.client.DateTimeFormat;

public class JobExceptionsView extends HorizontalPanel {
	
	private TextBox exception = new TextBox();
	private ListBox responsiblePerson = new ListBox();
	private ListBox divisionHead = new ListBox();
	private DateBox dueDate = new DateBox();
	private Button btnSave = new Button("Send");
	private HorizontalPanel hpnlButtons = new HorizontalPanel();
	private Button btnApprove = new Button("Approve");
	private Button btnReject = new Button("Reject");
	public JobExceptionsView(){
	setWidth("900px");
	
	createLayout();
	
	}

	private void createLayout() {
		hpnlButtons.add(btnApprove);
		hpnlButtons.add(btnReject);
		hpnlButtons.setVisible(false);
		add(exception);
		add(responsiblePerson);
		add(divisionHead);
		dueDate.setFormat(new DefaultFormat(DateTimeFormat.getShortDateFormat()));
		add(dueDate);
		add(btnSave);
		add(hpnlButtons);
		btnSave.setWidth("120px");
		for(int i =0; i< getWidgetCount()-1; i++){
			getWidget(i).setWidth("200px");
		}
	}

	public Button getBtnSave() {
		return btnSave;
	}

	public void setBtnSave(Button btnSave) {
		this.btnSave = btnSave;
	}

	public TextBox getException() {
		return exception;
	}

	public void setException(TextBox exception) {
		this.exception = exception;
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
		exception.setEnabled(false);
		responsiblePerson.setEnabled(false);
		divisionHead .setEnabled(false);
		dueDate.setEnabled(false);
		btnSave.setEnabled(false);
		
	}
	
	public void showApprovalButtons(){
		hpnlButtons.setVisible(true);
		btnSave.setVisible(false);
	}
	
	public void hideApprovalButtons(){
		hpnlButtons.setVisible(false);
		btnSave.setVisible(true);
	}

	public HorizontalPanel getHpnlButtons() {
		return hpnlButtons;
	}

	public void setHpnlButtons(HorizontalPanel hpnlButtons) {
		this.hpnlButtons = hpnlButtons;
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
	
	

}
