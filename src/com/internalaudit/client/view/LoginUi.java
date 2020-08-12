package com.internalaudit.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.internalaudit.client.presenter.LoginPresenter.Display;

public class LoginUi extends Composite implements Display  {

	private static LoginUiUiBinder uiBinder = GWT.create(LoginUiUiBinder.class);

	interface LoginUiUiBinder extends UiBinder<Widget, LoginUi> {
	}
	
	@UiField TextBox textUserName;
	@UiField PasswordTextBox textPassword;
	@UiField Label lblError;
	@UiField Button btnSubmit;
	@UiField Anchor requestUserName; 
	@UiField Anchor forgetPassword;

	public LoginUi() {
		initWidget(uiBinder.createAndBindUi(this));
		textUserName.getElement().setPropertyString("placeholder", "Enter Username");
		textPassword.getElement().setPropertyString("placeholder", "Enter Password");
	}

	
	public LoginUi(String firstName) {
		initWidget(uiBinder.createAndBindUi(this));
		
	}


	public TextBox getTxtUserName() {
		return textUserName;
	}


	public void setTxtUserName(TextBox textUserName) {
		this.textUserName = textUserName;
	}


	public PasswordTextBox getTxtPassword() {
		return textPassword;
	}


	public void setTextPassword(PasswordTextBox textPassword) {
		this.textPassword = textPassword;
	}


	public Label getLblError() {
		return lblError;
	}


	public void setLblError(Label lblError) {
		this.lblError = lblError;
	}


	public Button getBtnSubmit() {
		return btnSubmit;
	}


	public void setBtnSubmit(Button btnSubmit) {
		this.btnSubmit = btnSubmit;
	}


	public Anchor getRequestUsername() {
		return requestUserName;
	}


	public void setRequestUserName(Anchor requestUserName) {
		this.requestUserName = requestUserName;
	} 
	
	
	public Anchor getForgetPassword() {
		return forgetPassword;
	}


	public void setForgetPassword(Anchor forgetPassword) {
		this.forgetPassword = forgetPassword;
	}
	
}
