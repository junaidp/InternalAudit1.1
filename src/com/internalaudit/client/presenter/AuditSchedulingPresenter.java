package com.internalaudit.client.presenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.internalaudit.client.InternalAuditServiceAsync;
import com.internalaudit.client.event.JobListingEvent;
import com.internalaudit.client.view.Scheduling.AuditSchedulingTabView;
import com.internalaudit.shared.Employee;



public class AuditSchedulingPresenter implements Presenter 

{
	private final InternalAuditServiceAsync rpcService;
	private final HandlerManager eventBus;
	private final AuditSchedulingTabView display;
	private Employee loggedInUser;


	public interface Display 
	{
		Widget asWidget();
		Object getHtmlErrorMessage = null;
		//Anchor getAreaOfExpertise();
		//Anchor getJobTimeEst();
		//Anchor getJobCreation();
		//Anchor getScheduling();
		Button getApproveSchedule();
		VerticalPanel getApprovalContainer();
	}  

	public AuditSchedulingPresenter(InternalAuditServiceAsync rpcService, HandlerManager eventBus, AuditSchedulingTabView auditSchedulingView, Employee loggedInUser) 
	{
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.display = auditSchedulingView;
		this.loggedInUser = loggedInUser;
	}

	

	public void go(HasWidgets container) 
	{
		container.clear();
		container.add(display.asWidget());
		bind();
		if(loggedInUser.getRollId()==1){
			fetchApprovalStatus();
			
		}
		
	}

	private void fetchApprovalStatus() {
		rpcService.isScheduleApproved(new AsyncCallback<Boolean>() {
			
			@Override
			public void onSuccess(Boolean approved) {
				display.getApprovalContainer().setVisible(!approved);
			}
			
			@Override
			public void onFailure(Throwable arg0) {
				Window.alert("fail: is ScheduleApproved");
			}
		});
	}



	private void bind() {
		
		display.getApproveSchedule().addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent arg0) {
				boolean confirm = Window.confirm("Are you sure to Approve & Close the whole schedule");
				if(confirm){
				approveSchedule();
				}
			}

			});
		
		/*
		display.getAreaOfExpertise().addClickHandler(new ClickHandler(){
			//need to remove anchors
			@Override
			public void onClick(ClickEvent event) {
				History.newItem("auditAreas");
			}});
		
		display.getJobTimeEst().addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new JobListingEvent("jobTimeEst"));
			}});
		
		display.getJobCreation().addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new JobListingEvent("jobCreation"));
			}});
			
		
		display.getScheduling().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {

				History.newItem("auditListing");
				
			}
		});
	*/
	
	}
	
	private void approveSchedule() {
		rpcService.approveScheduling(new AsyncCallback<String>(){

			@Override
			public void onFailure(Throwable arg0) {
				Window.alert("fail: Approval");
			}

			@Override
			public void onSuccess(String result) {
				Window.alert(result);
				fetchApprovalStatus();
			}});
	}


}


