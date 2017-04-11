package com.internalaudit.client.view.Reporting;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.user.datepicker.client.DateBox.DefaultFormat;
import com.google.gwt.i18n.client.DateTimeFormat;

public class ResponsiblePersonRowView extends VerticalPanel{
	
	private TextBox auditJob = new TextBox();
	private TextBox  exception = new TextBox();
	private ListBox  isImplemented = new ListBox();
	private TextArea  implementaionComments = new TextArea();
	private TextBox  managementComments = new TextBox();
	private DateBox  implementaionDate = new DateBox();
	private Button btnSend = new Button("Send");
	private Label status = new Label("");
	private Button btnApprove = new Button("Approve");
	private Button btnReject = new Button("Reject");
	private HorizontalPanel hpnlApprovalButton = new HorizontalPanel();
	private HorizontalPanel hpnl2= new HorizontalPanel();
	
	
	public ResponsiblePersonRowView(){
//		status.setVisible(false);
		
		createLayout();
	}



	private void createLayout() {
		implementaionComments.setSize("500px","75px");
		
		HorizontalPanel hpnl1= new HorizontalPanel();
		
		hpnl2.setVisible(false);
		add(hpnl1);
		add(hpnl2);
		setSpacing(2);
		hpnl2.setSpacing(2);
		hpnl1.setSpacing(2);
		hpnlApprovalButton.setVisible(false);
		hpnlApprovalButton.add(btnApprove);
		hpnlApprovalButton.add(btnReject);
		hpnl1.add(auditJob);
		hpnl1.add(exception);
		hpnl1.add(managementComments);
		implementaionDate.setFormat(new DefaultFormat(DateTimeFormat.getShortDateFormat()));
		hpnl1.add(implementaionDate);
		hpnl1.add(btnSend);
		hpnl1.add(hpnlApprovalButton);
		hpnl1.add(status);
		//2nd row
		VerticalPanel vpnlisImplemented = new VerticalPanel();
		VerticalPanel vpnlimplementationComments = new VerticalPanel();
		vpnlisImplemented.add(new Label("Implemented"));
		vpnlisImplemented.add(isImplemented);
		vpnlimplementationComments.add(new Label("Final Comments"));
		vpnlimplementationComments.add(implementaionComments);
		hpnl2.add(vpnlisImplemented);
		hpnl2.add(vpnlimplementationComments);
		isImplemented.addItem("No", "0");
		isImplemented.addItem("Yes", "1");
		status.setStyleName("blue");
		btnApprove.setWidth("60px");
		btnReject.setWidth("60px");
		exception.setWidth("300px");
		managementComments.setWidth("300px");
		status.setWidth("120px");
		
//		for(int i=0; i< getWidgetCount()-3; i++){
//			getWidget(i).setWidth("300px");
//		}
		implementaionDate.setWidth("100px");
		
		auditJob.setEnabled(false);
		exception.setEnabled(false);
		auditJob.setVisible(false);
	}
	
	

	public TextBox getAuditJob() {
		return auditJob;
	}

	public void setAuditJob(TextBox auditJob) {
		this.auditJob = auditJob;
	}

	public TextBox getException() {
		return exception;
	}

	public void setException(TextBox exception) {
		this.exception = exception;
	}

	public TextBox getManagementComments() {
		return managementComments;
	}

	public void setManagementComments(TextBox managementComments) {
		this.managementComments = managementComments;
	}

	public DateBox getImplementaionDate() {
		return implementaionDate;
	}

	public void setImplementaionDate(DateBox implementaionDate) {
		this.implementaionDate = implementaionDate;
	}

	public Button getBtnSend() {
		return btnSend;
	}

	public void setBtnSend(Button btnSend) {
		this.btnSend = btnSend;
	}



	public void disableFields() {
		auditJob.setEnabled(false);
		exception.setEnabled(false);
		managementComments.setEnabled(false);
		implementaionDate.setEnabled(false);
		btnSend.setEnabled(false);
		
	}



	public Label getStatus() {
		return status;
	}



	public void setStatus(Label status) {
		this.status = status;
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



	public HorizontalPanel getHpnlApprovalButton() {
		return hpnlApprovalButton;
	}



	public void setHpnlApprovalButton(HorizontalPanel hpnlApprovalButton) {
		this.hpnlApprovalButton = hpnlApprovalButton;
	}



	public HorizontalPanel getHpnl2() {
		return hpnl2;
	}



	public void setHpnl2(HorizontalPanel hpnl2) {
		this.hpnl2 = hpnl2;
	}



	public ListBox getIsImplemented() {
		return isImplemented;
	}



	public void setIsImplemented(ListBox isImplemented) {
		this.isImplemented = isImplemented;
	}



	public TextArea getImplementaionComments() {
		return implementaionComments;
	}



	public void setImplementaionComments(TextArea implementaionComments) {
		this.implementaionComments = implementaionComments;
	}

}
