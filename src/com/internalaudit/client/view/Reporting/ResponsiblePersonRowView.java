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
import com.sencha.gxt.widget.core.client.form.TextField;

public class ResponsiblePersonRowView extends VerticalPanel{
	
	private TextBox auditJob = new TextBox();
	private TextArea  exception = new TextArea();
	private TextArea  recommendations = new TextArea();
	private ListBox  isImplemented = new ListBox();
	private ListBox  isAgreed = new ListBox();
	private TextArea  implementaionComments = new TextArea();
	private TextArea  managementComments = new TextArea();
	private DateBox  implementaionDate = new DateBox();
	private Button btnSend = new Button("Send");
	private Label status = new Label("");
	private Button btnApprove = new Button("Approve");
	private Button btnReject = new Button("Feedback");
	private VerticalPanel vpnlApprovalButton = new VerticalPanel();
	private HorizontalPanel hpnl2= new HorizontalPanel();
	private TextField txtComments = new TextField();
//	private TextBox txt

	
	public ResponsiblePersonRowView(){
//		status.setVisible(false);
		
		createLayout();
	}



	private void createLayout() {
		implementaionComments.setSize("900px","50px");
		
		HorizontalPanel hpnl1= new HorizontalPanel();
		
		hpnl2.setVisible(false);
		add(hpnl1);
		add(hpnl2);
		setSpacing(2);
		hpnl2.setSpacing(2);
		hpnl1.setSpacing(2);
		vpnlApprovalButton.setVisible(false);
		HorizontalPanel hpnlApprovalButtons = new HorizontalPanel();
		
		hpnlApprovalButtons.add(btnApprove);
		hpnlApprovalButtons.add(btnReject);
		vpnlApprovalButton.add(txtComments);
		txtComments.setEmptyText("Enter Comments");
		vpnlApprovalButton.add(hpnlApprovalButtons);
		hpnl1.add(auditJob);
		hpnl1.add(exception);
		hpnl1.add(recommendations);
		hpnl1.add(managementComments);
		implementaionDate.setFormat(new DefaultFormat(DateTimeFormat.getShortDateFormat()));
		hpnl1.add(implementaionDate);
		hpnl1.add(isAgreed);
		hpnl1.add(btnSend);
		hpnl1.add(vpnlApprovalButton);
		hpnl1.add(status);
		//2nd row
		VerticalPanel vpnlisAgreed = new VerticalPanel();
		VerticalPanel vpnlisImplemented = new VerticalPanel();
		VerticalPanel vpnlimplementationComments = new VerticalPanel();
		vpnlisImplemented.add(new Label("Implemented - "));
		vpnlisAgreed.add(new Label("Agree"));
//		vpnlisAgreed.add(isAgreed);
		vpnlisImplemented.add(isImplemented);
		vpnlimplementationComments.add(new Label("Final Comments"));
		vpnlimplementationComments.add(implementaionComments);
//		hpnl2.add(vpnlisAgreed);
		hpnl2.add(vpnlisImplemented);
		hpnl2.add(vpnlimplementationComments);
		isImplemented.addItem("No", "0");
		isImplemented.addItem("Yes", "1");
		isAgreed.addItem("No", "0");
		isAgreed.addItem("Yes", "1");
		
		btnApprove.setWidth("60px");
		btnReject.setWidth("60px");
		exception.setWidth("250px");
		recommendations.setWidth("250px");
		managementComments.setWidth("250px");
		status.setWidth("120px");
		isAgreed.setWidth("100px");
//		for(int i=0; i< getWidgetCount()-3; i++){
//			getWidget(i).setWidth("300px");
//		}
		implementaionDate.setWidth("100px");
		
		auditJob.setEnabled(false);
		exception.setEnabled(false);
		auditJob.setVisible(false);
		recommendations.setEnabled(false);
		managementComments.setEnabled(false); 
		
		exception.setHeight("300px");
		recommendations.setHeight("300px");
		managementComments.setHeight("300px");
		
		exception.addStyleName("noresize");
		recommendations.addStyleName("noresize");
		managementComments.addStyleName("noresize");
		implementaionComments.addStyleName("noresize");
		
	}
	
	

	public TextBox getAuditJob() {
		return auditJob;
	}

	public void setAuditJob(TextBox auditJob) {
		this.auditJob = auditJob;
	}

	public TextArea getException() {
		return exception;
	}

	public void setException(TextArea exception) {
		this.exception = exception;
	}

	public TextArea getManagementComments() {
		return managementComments;
	}

	public void setManagementComments(TextArea managementComments) {
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
		isAgreed.setEnabled(false);
		
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



	public VerticalPanel getVpnlApprovalButton() {
		return vpnlApprovalButton;
	}



	public void setHpnlApprovalButton(VerticalPanel hpnlApprovalButton) {
		this.vpnlApprovalButton = hpnlApprovalButton;
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



	public TextField getTxtComments() {
		return txtComments;
	}



	public void setTxtComments(TextField txtComments) {
		this.txtComments = txtComments;
	}



	public ListBox getIsAgreed() {
		return isAgreed;
	}



	public void setIsAgreed(ListBox isAgreed) {
		this.isAgreed = isAgreed;
	}



	public TextArea getRecommendations() {
		return recommendations;
	}



	public void setRecommendations(TextArea recommendations) {
		this.recommendations = recommendations;
	}

}
