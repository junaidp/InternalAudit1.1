package com.internalaudit.client.view.AuditEngagement;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class AuditNotificationViewNew extends Composite {

	private static AuditNotificationViewNewUiBinder uiBinder = GWT.create(AuditNotificationViewNewUiBinder.class);

	interface AuditNotificationViewNewUiBinder extends UiBinder<Widget, AuditNotificationViewNew> {
	}
	@UiField
	TextBox txtBoxReference;
	@UiField
	TextBox txtBoxFrom;
	@UiField
	TextBox txtBoxForAction;
	@UiField
	TextBox txtBoxForInfo;
	@UiField
	TextBox txtBoxSubject;
	@UiField
	Button btnSend;
	
	@UiField
	TextArea txtAreaBody;
	private AuditNotificationViewNewData auditNotificationViewNewData = new AuditNotificationViewNewData();

	
	public AuditNotificationViewNew() {
		initWidget(uiBinder.createAndBindUi(this));
		HTML html = new HTML();
		setHandlers();
		txtAreaBody.setText("We hereby would like to inform you thot company Nome Internal Audit will be conducting o review of Engagement Nome from 14 December, 2017. This is port of the Internal Audit Pion for 2017, discussed with Top Monogement, Group Internal Audit, ond approved by member(s) of BoD/Audit Committee\nThe oudit will be performed by Mr DEF ond Mr GHI , members of Locol Internal Audit.\n The scope of the oudit includes review of odequocy, effectiveness & efficiency of the controls ond processes around Engagement Nome, with specific focus on xxxx. \n Please note thot IA teom moy toke contact with relevant members of your teom in the coming doys for o better process understanding relating to this oudit ond information required. Any resultant scope changes, if required, will be duly communicated. \n The completion ond finolisotion will depend on the ovoilobility of information ond interviewees, yet we oim ot delivering o droft report for your review ond comments by 8 FebruorY, 2018. \n You ore requested to forward the information in ti-is notification to those who will be involved in the oudit. If you hove ony questions concerning the oudit, please feel free to contact myself or e-moil ot xxxxx. \n We look forward to your continued support. ");
		
	}

	
	private void setHandlers() {
		btnSend.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				//btnSend.setEnabled(false);
				auditNotificationViewNewData.sendMessage(btnSend);
				Window.alert("Yay");
				
			}});
}
	
	public AuditNotificationViewNewData getAuditNotificationViewNewData() {
		return auditNotificationViewNewData;
	}


	public void setAuditNotificationViewNewData(
			AuditNotificationViewNewData auditNotificationViewNewData) {
		this.auditNotificationViewNewData = auditNotificationViewNewData;
	}


	public static AuditNotificationViewNewUiBinder getUiBinder() {
		return uiBinder;
	}


	public static void setUiBinder(AuditNotificationViewNewUiBinder uiBinder) {
		AuditNotificationViewNew.uiBinder = uiBinder;
	}


	public TextBox getTxtBoxReference() {
		return txtBoxReference;
	}


	public void setTxtBoxReference(TextBox txtBoxReference) {
		this.txtBoxReference = txtBoxReference;
	}


	public TextBox getTxtBoxFrom() {
		return txtBoxFrom;
	}


	public void setTxtBoxFrom(TextBox txtBoxFrom) {
		this.txtBoxFrom = txtBoxFrom;
	}


	public TextBox getTxtBoxForAction() {
		return txtBoxForAction;
	}


	public void setTxtBoxForAction(TextBox txtBoxForAction) {
		this.txtBoxForAction = txtBoxForAction;
	}


	public TextBox getTxtBoxForInfo() {
		return txtBoxForInfo;
	}


	public void setTxtBoxForInfo(TextBox txtBoxForInfo) {
		this.txtBoxForInfo = txtBoxForInfo;
	}


	public TextBox getTxtBoxSubject() {
		return txtBoxSubject;
	}


	public void setTxtBoxSubject(TextBox txtBoxSubject) {
		this.txtBoxSubject = txtBoxSubject;
	}


	public Button getBtnSend() {
		return btnSend;
	}


	public void setBtnSend(Button btnSend) {
		this.btnSend = btnSend;
	}


	public TextArea getTxtAreaBody() {
		return txtAreaBody;
	}


	public void setTxtAreaBody(TextArea txtAreaBody) {
		this.txtAreaBody = txtAreaBody;
	}
}
