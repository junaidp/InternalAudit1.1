package com.internalaudit.client.presenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.internalaudit.client.InternalAuditServiceAsync;
import com.internalaudit.client.event.JobCreationEvent;
import com.internalaudit.client.event.JobTimeEstimationEvent;
import com.internalaudit.shared.StrategicDTO;
import com.internalaudit.shared.TimeOutException;

public class JobListingPresenter implements Presenter {

	private final InternalAuditServiceAsync rpcService;
	private final HandlerManager eventBus;
	private final Display display;	
	private Logger logger = Logger.getLogger("JobListingPresenter");
	
	public interface Display 
	{
		Widget asWidget();
		Object getHtmlErrorMessage = null;
		VerticalPanel getJobListContainer();
		String getCallingFrom();
		Button getBackButton();
	}
	
	public JobListingPresenter(InternalAuditServiceAsync rpcService, HandlerManager eventBus, Display view) 
	{
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.display = view;
		
		fetchTextForJobLinks();
		
		
		display.getBackButton().addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				History.newItem("auditScheduling");
			}});
	}



	private void fetchTextForJobLinks() {
		
		HashMap<String,String> hm = new HashMap<String,String>();
		
		final String caller = display.getCallingFrom();
		
		rpcService.fetchSchedulingStrategic(hm, new AsyncCallback<ArrayList<StrategicDTO>>() {
			
			@Override
			public void onSuccess(final ArrayList<StrategicDTO> result) {
				
				//now we have links. gotta add to view
				Anchor jobLink;
				for( int i = 0; i < result.size(); ++i )
				{
					final StrategicDTO dto = result.get(i);
					
					jobLink = new Anchor();
					jobLink.setHeight("25px");
					jobLink.addStyleName("jobListingText");
//					jobLink.setText(result.get(i).getStrategicObjective());
					jobLink.setText(result.get(i).getAuditableUnit());
					
										
					jobLink.addClickHandler(new ClickHandler() {
						
						@Override
						public void onClick(ClickEvent event) {
							
							if ( caller == "jobTimeEst" )
							{ 
								
								eventBus.fireEvent( new JobTimeEstimationEvent(dto));
							}
							
							if ( caller == "jobCreation" )
							{
								eventBus.fireEvent( new JobCreationEvent(dto));
							}
						}
					});
					HorizontalPanel jobsContainer = new HorizontalPanel();
					jobsContainer.add(jobLink);
//					jobsContainer.addStyleName("statusRowConsolidation");
//					jobLink.addStyleName("labelTitle"); 
					jobLink.setWordWrap(false);
					jobLink.addStyleName("smallBlack");
					jobLink.addStyleName("pointerStyle");
					jobsContainer.setWidth("100%");
					display.getJobListContainer().setWidth("100%");
					display.getJobListContainer().add(jobsContainer);
//					String upperCasedJobLink = jobLink.getText().toUpperCase();
					String upperCasedJobLink = jobLink.getText();
					jobLink.setText(upperCasedJobLink);
					
					
					display.getJobListContainer().setSpacing(5);
					jobsContainer.setSpacing(5);
				}
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				

				logger.log(Level.INFO, "FAIL: declineStrategic .Inside Audit AuditAreaspresenter");
				if(caught instanceof TimeOutException){
					History.newItem("login");
				}else{
					System.out.println("FAIL: declineStrategic .Inside AuditAreaspresenter");
					Window.alert("FAIL: declineStrategic");// After FAIL ... write RPC Name  NOT Method Name..
				}
				
				
			}
		});
		
	}
		



	public void go(HasWidgets container) 
	{
		container.clear();
		container.add(display.asWidget());
		bind();
	}



	private void bind() {
		// TODO Auto-generated method stub
		
	}
}
