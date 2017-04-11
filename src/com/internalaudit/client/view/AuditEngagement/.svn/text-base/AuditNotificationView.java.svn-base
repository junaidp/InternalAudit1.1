package com.internalaudit.client.view.AuditEngagement;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.internalaudit.client.InternalAuditServiceAsync;
import com.internalaudit.client.view.DisplayAlert;

public class AuditNotificationView extends Composite  {

	private static MessageViewUiBinder uiBinder = GWT
			.create(MessageViewUiBinder.class);

	interface MessageViewUiBinder extends UiBinder<Widget, AuditNotificationView> {
	}

	@UiField
	Button send;
	@UiField
	TextBox to;
	@UiField
	TextBox cc;
	@UiField
	TextArea message;
	private AuditNotificationViewData auditNotificationViewData = new AuditNotificationViewData();


	public AuditNotificationView() {
		initWidget(uiBinder.createAndBindUi(this));
		setHandlers();

	}

	private void setHandlers() {
		send.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				auditNotificationViewData.sendMessage();
			}});

	}

	public void disableFields(){
		send.setEnabled(false);
		to.setEnabled(false);
		cc.setEnabled(false);
		message.setEnabled(false);

	}

	public AuditNotificationViewData getAuditNotificationViewData() {
		return auditNotificationViewData;
	}


	public void setAuditNotificationViewData(
			AuditNotificationViewData auditNotificationViewData) {
		this.auditNotificationViewData = auditNotificationViewData;
	}

	public TextBox getTo() {
		return to;
	}
	public void setTo(TextBox to) {
		this.to = to;
	}

	public TextBox getCc() {
		return cc;
	}
	public void setCc(TextBox cc) {
		this.cc = cc;
	}

	public TextArea getMessage() {
		return message;
	}
	public void setMessage(TextArea message) {
		this.message = message;
	}




}
