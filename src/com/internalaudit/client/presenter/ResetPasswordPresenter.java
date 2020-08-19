package com.internalaudit.client.presenter;

import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;
import com.internalaudit.client.InternalAuditServiceAsync;
import com.internalaudit.client.view.ButtonRound;
import com.internalaudit.client.view.DisplayAlert;
import com.internalaudit.client.view.ForgetPassword;
import com.internalaudit.shared.City;
import com.internalaudit.shared.Company;
import com.internalaudit.shared.Country;
import com.internalaudit.shared.Employee;
import com.internalaudit.shared.InternalAuditConstants;
import com.internalaudit.shared.RollsEnum;
import com.internalaudit.shared.Skills;

public class ResetPasswordPresenter implements Presenter

{
	private final InternalAuditServiceAsync rpcService;
	private final HandlerManager eventBus;
	private final Display display;

	public interface Display {
		Widget asWidget();
		PasswordTextBox getTxtNewPassword();
		PasswordTextBox getTxtConfirmPassword();
		HTML getHtmlError();
		Button getSubmit();
	}

	public ResetPasswordPresenter(InternalAuditServiceAsync rpcService, HandlerManager eventBus, Display view) {
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.display = view;
	}

	public void go(HasWidgets container) {
		container.clear();
		container.add(display.asWidget());
		bind();

	}

	private void bind() {
		display.getSubmit().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent arg0) { 
				matchNewConfirmPassword();
			}
		});
		
		display.getTxtNewPassword().addValueChangeHandler(new ValueChangeHandler<String>() {
			
			@Override
			public void onValueChange(ValueChangeEvent<String> newPasword) {
				validatePassword(newPasword.getValue());
			}
		});
	}

	private void validatePassword(String newPasword) {
		String passwordRegex = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$";
		if(!newPasword.matches(passwordRegex)){
			display.getHtmlError().setHTML("<b>Password must contain: </b><br/>" + "- Atleast 8 characters <br/>" + "- An Upper-Case alphabet <br/>" + "- Number <br/>" + "- Special character");
			display.getHtmlError().setVisible(true);
			}
		else
			display.getHtmlError().setVisible(false);
	}
	
	
	private void matchNewConfirmPassword() { 
		if(display.getTxtNewPassword().getValue().equals(display.getTxtConfirmPassword().getValue()))  
			updatePasswordRPC(display.getTxtNewPassword().getValue());
		else
		{
			display.getHtmlError().setText("passwords not matched");
			display.getHtmlError().setVisible(true);
		}
	}
	
	private void updatePasswordRPC(String newPassword) {
		rpcService.resetPassword(extractEmployeeID() ,newPassword, new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable msg) {
				// TODO Auto-generated method stub
				Window.alert("Unable to update your password "+ msg);
			}

			@Override
			public void onSuccess(String msg) {
				new DisplayAlert(msg);
				History.newItem("login");
			}
		});
	}
	
	private Integer extractEmployeeID() {
		String clientURL = Window.Location.getHref();
		String empID = clientURL.substring(clientURL.indexOf("?")+1);
		return Integer.parseInt(empID);
	} 
	
}
