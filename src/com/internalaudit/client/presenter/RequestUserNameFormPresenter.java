package com.internalaudit.client.presenter;

import java.util.logging.Logger;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.internalaudit.client.InternalAuditServiceAsync;
import com.internalaudit.client.view.DisplayAlert;
import com.internalaudit.client.view.LoadingPopup;


public class RequestUserNameFormPresenter implements Presenter 

{
	private final InternalAuditServiceAsync rpcService;
	private final HandlerManager eventBus;
	private final Display display;
	
	private Logger logger = Logger.getLogger("DashBoardPresenter");
	
	public interface Display 
	{
		Widget asWidget();
		TextBox getTxtCompanyName();
		TextBox getTxtContactPersonName();
		TextBox getTxtContactPeronEmail();
		Button getBtnCancel();
		Button getBtnSubmit();
		
		
		
		
	}  

	public RequestUserNameFormPresenter(InternalAuditServiceAsync rpcService, HandlerManager eventBus, Display view) 
	{
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.display = view;
		
		
	}
	
	

	public void go(HasWidgets container) 
	{
		container.clear();
		container.add(display.asWidget());
		bind();
	}

	private void bind() {
		
		display.getBtnCancel().addClickHandler( new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				History.newItem("login");
			}});
		
		display.getBtnSubmit().addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				
//				String to = "mfaheempiracha@gmail.com";
				String to = "junaidp@gmail.com";
				String body = "Name: " + display.getTxtContactPersonName().getText() + " Email:  " + display.getTxtContactPeronEmail().getText() + " Company Name: " + display.getTxtCompanyName().getText();
				final LoadingPopup loadingPopup = new LoadingPopup();
				loadingPopup.display();
				display.getBtnSubmit().setEnabled(false);
				rpcService.sendEmail(body, to, new AsyncCallback<Boolean>(){

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Fail Sending email for username ");
						loadingPopup.remove();
						display.getBtnSubmit().setEnabled(true);
					}

					@Override
					public void onSuccess(Boolean result) {
						Window.alert("Request Submitted, You will soon receive your trial admin username !");
						loadingPopup.remove();
						display.getBtnSubmit().setEnabled(true);
						
						History.newItem("login");
						
					}});
			}});
		
	}

	
}


