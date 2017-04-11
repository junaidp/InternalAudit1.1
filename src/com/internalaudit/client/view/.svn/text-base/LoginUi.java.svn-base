package com.internalaudit.client.view;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.widget.client.TextButton;
import com.internalaudit.client.presenter.LoginPresenter.Display;
import com.sencha.gxt.widget.core.client.FramedPanel;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.FieldSet;
import com.sencha.gxt.widget.core.client.form.PasswordField;
import com.sencha.gxt.widget.core.client.form.TextField;

public class LoginUi extends Composite implements IsWidget,Display {
	public LoginUi() {
	}
	
	private VerticalPanel vp;
	private TextField txtUserName = new TextField();
	private PasswordField txtPassword = new PasswordField();
	private TextButton btnSubmit = new TextButton("Submit");
	
	private Label lblError = new Label();

	private void createForm2() {
		
		FramedPanel form2 = new FramedPanel();
		form2.setHeadingText("Sign In");
		form2.setWidth(350);
		form2.setPagePosition(500, 200);
		FieldSet fieldSet = new FieldSet();
		fieldSet.setHeadingText("Enter you credentials");
		fieldSet.setCollapsible(false);
		form2.add(fieldSet);
		
		lblError.setVisible(false);

		VerticalLayoutContainer p = new VerticalLayoutContainer();
		fieldSet.add(p);
		p.add(lblError, new VerticalLayoutData(1, -1));
		
		txtUserName.setAllowBlank(false);
//		lblError.setLabelWidth(300);
//		lblError.setPosition(104, 0);
		lblError.setStyleName("error");
		p.add(new FieldLabel(txtUserName, "UserName"), new VerticalLayoutData(1, -1));

		
		txtPassword.setAllowBlank(false);
		p.add(new FieldLabel(txtPassword, "Password"), new VerticalLayoutData(1, -1));

		form2.addButton(btnSubmit);
		
		vp.add(form2);
	}
	
	
	
	public Widget asWidget() {
		if (vp == null) {
			vp = new VerticalPanel();
			vp.setSpacing(10);
			createForm2();
		}
		return vp;
	}
	public PasswordField getTxtPassword() {
		return txtPassword;
	}
	public void setTxtPassword(PasswordField txtPassword) {
		this.txtPassword = txtPassword;
	}
	public Label getLblError() {
		return lblError;
	}
	public void setLblError(Label lblError) {
		this.lblError = lblError;
	}
	public TextField getTxtUserName() {
		return txtUserName;
	}
	public void setTxtUserName(TextField txtUserName) {
		this.txtUserName = txtUserName;
	}
	public TextButton getBtnSubmit() {
		return btnSubmit;
	}
	public void setBtnSubmit(TextButton btnSubmit) {
		this.btnSubmit = btnSubmit;
	}

}
