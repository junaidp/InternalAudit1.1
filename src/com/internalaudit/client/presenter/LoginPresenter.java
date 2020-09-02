package com.internalaudit.client.presenter;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.tools.ant.taskdefs.FixCRLF.AddAsisRemove;

import com.internalaudit.shared.TimeOutException;
import com.google.gwt.user.client.History;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.internalaudit.client.InternalAuditServiceAsync;
import com.internalaudit.client.event.CreateUserEvent;
import com.internalaudit.client.event.MainEvent;
import com.internalaudit.client.view.ForgetPasswordUserVerification;
import com.internalaudit.client.view.LoadingPopup;
import com.internalaudit.client.view.PopupsView;
import com.internalaudit.shared.Employee;
import com.internalaudit.shared.Encryption;
import com.internalaudit.shared.Exceptions;


public class LoginPresenter implements Presenter 

{
	private final InternalAuditServiceAsync rpcService;
	private final HandlerManager eventBus;
	private final Display display;
	private LoadingPopup loadingPopup = null;
	private Logger logger = Logger.getLogger("DashBoardPresenter");
	
	public interface Display 
	{
		Widget asWidget();
		Object getHtmlErrorMessage = null;
		PasswordTextBox getTxtPassword();
		TextBox getTxtUserName();
		Button getBtnSubmit();
//		ListBox getListYears();
		Label getLblError();
		Anchor getRequestUsername(); 
		Anchor getForgetPassword();
	}  

	public LoginPresenter(InternalAuditServiceAsync rpcService, HandlerManager eventBus, Display view) 
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
		
		display.getRequestUsername().addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				History.newItem("requestUserName");
			}});
		
		display.getForgetPassword().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent arg0) {
				ForgetPasswordUserVerification emailVerification = new ForgetPasswordUserVerification();
			    PopupsView popUp = new PopupsView(emailVerification, "Reset Password");
			    popUp.hideCloseBtn();
			    emailVerification.setPopUp(popUp);
			}
		});
		
//		RootPanel.get("loadingMessage").setVisible(false);
		
		display.getBtnSubmit().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				
				
				if(display.getTxtUserName().getText().equals("")|| display.getTxtPassword().getText().equals("")){
					display.getLblError().setVisible(true);
					display.getLblError().setText("username / password cannot be empty");
				}else{
					loadingPopup = new LoadingPopup();
					loadingPopup.display();
					signIn(display.getTxtUserName().getText(),display.getTxtPassword().getText());
					
				}
			}

			
		});

	}

	private void sendEmailNotifications() {
		rpcService.fetchJobExceptions(0, new AsyncCallback<ArrayList<Exceptions>>(){

			@Override
			public void onFailure(Throwable caught) {
				

				logger.log(Level.INFO, "FAIL: fetchJobExceptions .Inside Audit AuditAreaspresenter");
				if(caught instanceof TimeOutException){
					History.newItem("login");
				}else{
					System.out.println("FAIL: fetchJobExceptions .Inside AuditAreaspresenter");
					Window.alert("FAIL: fetchJobExceptions");// After FAIL ... write RPC Name  NOT Method Name..
				}
				
				
			}

			@Override
			public void onSuccess(ArrayList<Exceptions> result) {
				
			}});
		
	}

	public void signIn(String userName,String password)
	{
		rpcService.signIn(userName,password, new AsyncCallback<Employee>()
				

				{
			public void onFailure(Throwable ex) {
				Window.alert(ex.getStackTrace().toString());
				if(loadingPopup!=null){
					loadingPopup.remove();
				}
	//		Window.alert(""+ex.getMessage());
	///			Window.alert(""+ex.getLocalizedMessage());
	//			Window.alert(""+ex.getCause());
	//			Window.alert(""+ex.getStackTrace());
			}

			public void onSuccess(Employee user) {
				if(loadingPopup!=null){
					loadingPopup.remove();
				}
				if(user != null) 
				{
					if(user.getRollId() == 4){
						eventBus.fireEvent(new CreateUserEvent(user));
					}
					else if(user.getRollId() == 5){
						eventBus.fireEvent(new MainEvent(user));
					}
					else{
					display.getLblError().setVisible(false);
					//eventBus.fireEvent(new DashBoardAtStartupEvent(user));
					eventBus.fireEvent(new MainEvent(user));
//					eventBus.fireEvent(new MainEvent(user));
					}
				}
				else 
				{
					display.getLblError().setVisible(true);
					display.getLblError().setText("username / password invalid");
				}
				
				
			}
				});

	}
	
	


}


