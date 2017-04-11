package com.internalaudit.client.presenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.internalaudit.client.InternalAuditServiceAsync;
import com.internalaudit.client.event.JobListingEvent;
import com.internalaudit.client.view.Scheduling.AuditSchedulingView;


public class AuditSchedulingPresenter implements Presenter 

{
	private final InternalAuditServiceAsync rpcService;
	private final HandlerManager eventBus;
	private final Display display;


	public interface Display 
	{
		Widget asWidget();
		Object getHtmlErrorMessage = null;
		Anchor getAreaOfExpertise();
		Anchor getJobTimeEst();
		Anchor getJobCreation();
		Anchor getScheduling();
	}  

	public AuditSchedulingPresenter(InternalAuditServiceAsync rpcService, HandlerManager eventBus, Display view) 
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
		
		//this represents those 3 links on main page
		//as you can see, for last two, I started a new View.
		//with Anchor
		
		//now you can navigate to pres and view. and if any question, I am here.  so where r u stuck ?
		
		//as you can se
		//view is SAME for both, jobtimeest and jobcreation
		//so I need a way of knowing through which button JobListing view was accessed
		//so that we can display correct headings, as client hsas requested.
		// aslo, IF user click on Anchor in jobtimest, estimation form (view) should open
		//if click on anchor from creation view , then that form should open.
		//currently clicks just redirect / move to JobList view.
		
		//if want something like
		
		/*
		 * if ( user clicked jobcreation to enter joli   got it ...  plz show me those two places /anchors  form where that same page is opening

		 */
		
		
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
	
	
	}


}


