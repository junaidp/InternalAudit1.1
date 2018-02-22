package com.internalaudit.client.view;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.internalaudit.client.presenter.RequestUserNameFormPresenter.Display;

public class RequestUserNameFormView extends FlexTable implements Display {
	
	private TextBox txtCompanyName = new TextBox();
	private TextBox txtContactPersonName = new TextBox();
	private TextBox txtContactPeronEmail = new TextBox();
	private Button btnCancel = new Button("Cancel");
	private Button btnSubmit = new Button("Submit");
	
	public RequestUserNameFormView() {
		
		layout();
	}

	private void layout() {
		Label lblHeading = new Label("Request User Name Form");
		lblHeading.addStyleName("blue");
		
		setWidget(0, 1, lblHeading);
		setWidget(1, 0, new Label("Company Name"));
		setWidget(2, 0, new Label("Contact Person Name"));
		setWidget(3, 0, new Label("Contact Person Email"));
		
		setWidget(1, 1, txtCompanyName);
		setWidget(2, 1, txtContactPersonName);
		setWidget(3, 1, txtContactPeronEmail);
		setWidget(4, 0, btnCancel);
		setWidget(4, 1, btnSubmit);
	}

	public TextBox getTxtCompanyName() {
		return txtCompanyName;
	}

	public void setTxtCompanyName(TextBox txtCompanyName) {
		this.txtCompanyName = txtCompanyName;
	}

	public TextBox getTxtContactPersonName() {
		return txtContactPersonName;
	}

	public void setTxtContactPersonName(TextBox txtContactPersonName) {
		this.txtContactPersonName = txtContactPersonName;
	}

	public TextBox getTxtContactPeronEmail() {
		return txtContactPeronEmail;
	}

	public void setTxtContactPeronEmail(TextBox txtContactPeronEmail) {
		this.txtContactPeronEmail = txtContactPeronEmail;
	}

	public Button getBtnCancel() {
		return btnCancel;
	}

	public void setBtnCancel(Button btnCancel) {
		this.btnCancel = btnCancel;
	}

	public Button getBtnSubmit() {
		return btnSubmit;
	}

	public void setBtnSubmit(Button btnSubmit) {
		this.btnSubmit = btnSubmit;
	}

}
