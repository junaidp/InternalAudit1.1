package com.internalaudit.client.view;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.internalaudit.client.presenter.CompanyInductionFormPresenter.Display;

public class CompanyInductionFormView extends FlexTable implements Display {
	
	private TextBox txtName = new TextBox();
	private TextBox txtContactPeron= new TextBox();
	private TextBox txtContactPersonEmail = new TextBox();
	private ButtonRound btnCancel = new ButtonRound("Cancel");
	private ButtonRound btnSubmit = new ButtonRound("Submit");
	
	
	public CompanyInductionFormView() {
		
		layout();
	}

	private void layout() {
		Label lblHeading = new Label("User Induction Form");
		lblHeading.addStyleName("blue");
		
		
		setWidget(0, 1, lblHeading);
		setWidget(1,0, new Label("Company Name"));
		setWidget(2,0, new Label("Contact Person Name"));
		setWidget(3,0, new Label("Contact Person Email"));

		setWidget(1,1, txtName);
		setWidget(2,1, txtContactPeron);
		setWidget(3,1, txtContactPersonEmail);
		setWidget(4,0, btnCancel);
		setWidget(4,1, btnSubmit);
		
	
	}

	@Override
	public TextBox getTxtName() {
		
		return txtName;
	}
	
	public TextBox getTxtContactPerson() {
		
		return txtContactPeron;
	}

	public TextBox getTxtContactPersonEmail() {
	
	return txtContactPersonEmail;
}
	
	public ButtonRound getBtnCancel(){
		return btnCancel;
	}
	
	public ButtonRound getBtnSubmit(){
		return btnSubmit;
	}

	

}
