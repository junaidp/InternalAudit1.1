package com.internalaudit.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.internalaudit.client.InternalAuditService;
import com.internalaudit.client.InternalAuditServiceAsync;
import com.internalaudit.client.presenter.ResetPasswordPresenter.Display;
import com.internalaudit.shared.Employee;
import com.sencha.gxt.widget.core.client.box.AlertMessageBox;
import com.sencha.gxt.widget.core.client.box.MessageBox; 

public class ForgetPassword extends FlexTable implements Display  {

	private PasswordTextBox txtNewPassword = new PasswordTextBox();
	private PasswordTextBox txtConfirmPassword = new PasswordTextBox();
	private InternalAuditServiceAsync rpcService = GWT.create(InternalAuditService.class);
	private Button submit = new Button("Submit");
	private HTML htmlError;
	
	public ForgetPassword() {
		layout();
		setWidget(3,0,htmlError);
		htmlError.setVisible(false);
		this.addStyleName("w3-display-middle w3-border");
		//this.getElement().getStyle().setMarginTop(20, Unit.PX);
	}
	
	private void layout() {
		
		Label lblNewPassword = new Label("Enter New Password ");
		Label lblConfirmPassword = new Label("Confirm New Password ");
		
		lblNewPassword.addStyleName("labelDesign");
		lblConfirmPassword.addStyleName("labelDesign");
		
		lblConfirmPassword.setWidth("180px");
		lblNewPassword.setWidth("180px");
		
		lblConfirmPassword.getElement().getStyle().setPaddingTop(7, Unit.PX);
		lblNewPassword.getElement().getStyle().setPaddingTop(7, Unit.PX);
		
		setWidget(0, 0, lblNewPassword);
		setWidget(1, 0, lblConfirmPassword);
		
		setWidget(0, 2, txtNewPassword);
		setWidget(1, 2, txtConfirmPassword);
		
		submit.addStyleName("w3-right");
		setWidget(2, 2, submit);
		
		 htmlError = new HTML();
		 htmlError.addStyleName("error");
		 htmlError.setWidth("180px");
		 htmlError.setWordWrap(true);
		 htmlError.setVisible(false);
	}
	
	public PasswordTextBox getTxtNewPassword() {
		return txtNewPassword;
	}

	public void setTxtNewPassword(PasswordTextBox txtNewPassword) {
		this.txtNewPassword = txtNewPassword;
	}

	public Button getSubmit() {
		return submit;
	}

	public void setSubmit(Button submit) {
		this.submit = submit;
	}

	public PasswordTextBox getTxtConfirmPassword() {
		return txtConfirmPassword;
	}

	public void setTxtConfirmPassword(PasswordTextBox txtConfirmPassword) {
		this.txtConfirmPassword = txtConfirmPassword;
	}

	public HTML getHtmlError() {
		return htmlError;
	}

	public void setHtmlError(HTML htmlError) {
		this.htmlError = htmlError;
	}
}
