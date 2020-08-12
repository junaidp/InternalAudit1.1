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
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.internalaudit.client.InternalAuditService;
import com.internalaudit.client.InternalAuditServiceAsync;
import com.internalaudit.shared.Employee;
import com.sencha.gxt.widget.core.client.box.AlertMessageBox;
import com.sencha.gxt.widget.core.client.box.MessageBox; 

public class ChangePassword extends FlexTable {

	private TextBox txtOldPassword = new TextBox();
	private TextBox txtNewPassword = new TextBox();
	private TextBox txtConfirmPassword = new TextBox();
	private InternalAuditServiceAsync rpcService = GWT.create(InternalAuditService.class);
	private Button submit = new Button("Submit");
	private Employee loggedInUser;
	private PopupsView popup;
	private Label lblError;
	
	public ChangePassword(Employee loggedInUser) {
		this.loggedInUser = loggedInUser;
		layout();
		handlers();
	}
 
	public void setPopUp(PopupsView popup ) {
		this.popup = popup;  
		popup.getVpnlMain().add(lblError); 
	}
	
	private void layout() {
		
		Label lblOldPassword = new Label("Enter Old Password");
		Label lblNewPassword = new Label("Enter New Password");
		Label lblConfirmPassword = new Label("Confirm New Password  ");
		
		lblOldPassword.addStyleName("labelDesign");
		lblNewPassword.addStyleName("labelDesign");
		lblConfirmPassword.addStyleName("labelDesign");
		
		lblConfirmPassword.getElement().getStyle().setPaddingTop(7, Unit.PX);
		lblNewPassword.getElement().getStyle().setPaddingTop(7, Unit.PX);
		lblOldPassword.getElement().getStyle().setPaddingTop(7, Unit.PX);
		
		setWidget(0, 0, lblOldPassword);
		setWidget(1, 0, lblNewPassword);
		setWidget(2, 0, lblConfirmPassword);
		
		setWidget(0, 2, txtOldPassword);
		setWidget(1, 2, txtNewPassword);
		setWidget(2, 2, txtConfirmPassword);
		
		submit.addStyleName("w3-right");
		setWidget(3, 2, submit);
		
		 lblError = new Label();
		 lblError.addStyleName("error");
		 lblError.setWidth("290px");
		 lblError.setWordWrap(true);
		 lblError.setVisible(false);
	}

	private void handlers() {
		submit.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent arg0) { 
				updatePasswordData();
			}
		});
		txtNewPassword.addValueChangeHandler(new ValueChangeHandler<String>() {
			
			@Override
			public void onValueChange(ValueChangeEvent<String> newPasword) {
				validatePassword(newPasword.getValue());
			}
		});
	}
	
	private void validatePassword(String newPasword) {
		String passwordRegex = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$";
		if(!newPasword.matches(passwordRegex)){
			lblError.setText("Password must contain minimum 8 alphabets + Numbers + Special Characters");
			lblError.setVisible(true);
			}
		else
			lblError.setVisible(false);
	}
	
	private void updatePasswordData() {
		Employee employee = loggedInUser ; 
		if(matchNewConfirmPassword()) {
		    employee.setPassword(txtNewPassword.getText());
			updatePasswordRPC(employee);
		}
		else {
		 lblError.setText("Old Password Matched with New Password or New and Confirm Password Mismatched");
		 lblError.setVisible(true);
		}
	}

	private boolean matchNewConfirmPassword() { 
		boolean flag;
		if(txtNewPassword.getValue().equals(txtConfirmPassword.getValue()))  
		    flag = true; 
		else
			flag = false;
		return flag;
	}
	
	private void updatePasswordRPC(Employee loggedInUser) {
		rpcService.updatePassword(loggedInUser, new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable msg) {
				// TODO Auto-generated method stub
				Window.alert("Unable to update your password "+ msg);
			}

			@Override
			public void onSuccess(String msg) {
				// TODO Auto-generated method stub
				new DisplayAlert(msg);
				popup.closePopUp();
			}
		});
	}
}
