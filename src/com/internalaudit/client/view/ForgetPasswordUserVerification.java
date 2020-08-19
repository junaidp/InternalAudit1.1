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
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.internalaudit.client.InternalAuditService;
import com.internalaudit.client.InternalAuditServiceAsync;
import com.internalaudit.shared.Employee;
import com.sencha.gxt.widget.core.client.box.AlertMessageBox;
import com.sencha.gxt.widget.core.client.box.MessageBox; 

public class ForgetPasswordUserVerification extends VerticalPanel {

	private TextBox txtEmailID = new TextBox(); 
	private InternalAuditServiceAsync rpcService = GWT.create(InternalAuditService.class);
	private Button submit = new Button("Submit");  
	private boolean emailVerified = false;
	private Label lblError;
	private PopupsView popUp;
	
	public ForgetPasswordUserVerification() {
		add(layout());
		handlers();
	}
 
	public void setPopUp(PopupsView popup ) {
		popUp = popup;
		popUp.getVpnlMain().add(lblError); 
		lblError.setWidth("295px");
	}
	
	private Widget layout() {
		FlexTable flexTable = new FlexTable(); 
		Label lblEmailID = new Label("Enter Email ID "); 
	    lblEmailID.addStyleName("labelDesign");  
		lblEmailID.getElement().getStyle().setPaddingTop(7, Unit.PX);
		flexTable.setWidget(0, 0, lblEmailID); 
		flexTable.setWidget(0, 2, txtEmailID); 

		lblEmailID.setWidth("100px");
		txtEmailID.setWidth("200px");
		
		submit.addStyleName("w3-right");
		flexTable.setWidget(1, 2, submit);
		
		 lblError = new Label();
		 lblError.addStyleName("error");
		 lblError.setWidth("290px");
		 lblError.setWordWrap(true);
		 lblError.setVisible(false);
		return flexTable;
	}

	private void handlers() {
		submit.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent arg0) {  
				validateRegisteredUserEmail();
			}
		});
		
//		txtEmailID.addValueChangeHandler(new ValueChangeHandler<String>() {
//			
//			@Override
//			public void onValueChange(ValueChangeEvent<String> txtEmailID) {
//				//validateEmail(txtEmailID.getValue()); 
//			}
//		});
	}
	
	private boolean validateEmail(String txtEmailID) {
		boolean flag = true;
		String passwordRegex = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$";
		if(!txtEmailID.matches(passwordRegex)){
			lblError.setText("Please enter a valid email address");
			lblError.setVisible(true); 
			flag = false;
			}
		else
			lblError.setVisible(false);
		return flag;
	}  
	
	private void validateRegisteredUserEmail() {
		rpcService.validateRegisteredUserEmail(txtEmailID.getText(), new AsyncCallback<Integer>() {

			@Override
			public void onFailure(Throwable flag) {
				// TODO Auto-generated method stub
				Window.alert("Error in validateRegisteredUserEmail "+ flag);
			}

			@Override
			public void onSuccess(Integer employeeID) {
			if(employeeID == 0)
				errorEmailNotRegistered();
			else {
				lblError.setVisible(false);
				sendEmailResetPassword(employeeID);
				}
			}
		});
	}

	private void errorEmailNotRegistered() {
		lblError.setText("Email not registered");
		lblError.setVisible(true);
	}
	
	private void sendEmailResetPassword(Integer employeeID) {
		String resetUrl = GWT.getHostPageBaseURL() + "InternalAudit.html#resetPassword?" + employeeID;
		String emailBody = "Please click this link to reset " + resetUrl;
		rpcService.sendPasswordResetEmail(emailBody, txtEmailID.getValue(), new AsyncCallback<Boolean>() {

			@Override
			public void onFailure(Throwable arg0) {
				// TODO Auto-generated method stub
				Window.alert("Unable to send email to reset Password");
			}

			@Override
			public void onSuccess(Boolean arg0) {
				popUp.closePopUp();
			}
		});
		
	}
}
