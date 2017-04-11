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
import com.internalaudit.shared.Company;


public class CompanyInductionFormPresenter implements Presenter 

{
	private final InternalAuditServiceAsync rpcService;
	private final HandlerManager eventBus;
	private final Display display;
	
	private Logger logger = Logger.getLogger("CompanyInductionFormPresenter");
	
	public interface Display 
	{
		Widget asWidget();
		TextBox getTxtName();
		TextBox getTxtContactPerson();
		TextBox getTxtContactPersonEmail();
		Button getBtnCancel();
		Button getBtnSubmit();
		
	}  

	public CompanyInductionFormPresenter(InternalAuditServiceAsync rpcService, HandlerManager eventBus, Display view) 
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
		
		display.getBtnCancel().addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				History.newItem("login");
			}});
		
		display.getBtnSubmit().addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				addCompany();
			}

			});
	}
	
	private void addCompany() {
		Company company  = new Company();
		company.setContactPerson(display.getTxtContactPerson().getText());
		company.setContactPersonEmail(display.getTxtContactPersonEmail().getText());
		company.setName(display.getTxtName().getText());
		
		rpcService.saveCompany(company, new AsyncCallback<String>(){

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Save company failed");
			}

			@Override
			public void onSuccess(String result) {
				new DisplayAlert("Company added");
				History.newItem("login");
			}});
		
	}
	
}


