package com.internalaudit.client.presenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.internalaudit.shared.TimeOutException;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratedPopupPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.internalaudit.client.InternalAuditServiceAsync;
import com.internalaudit.client.event.MainEvent;
import com.internalaudit.client.view.DashBoardAuditJobs;
import com.internalaudit.client.view.DashBoardAuditObservations;
import com.internalaudit.client.view.DashBoardResourceManagement;
import com.internalaudit.client.view.LoadingPopup;
import com.internalaudit.client.view.dashboard.AuditJobsByFunction;
import com.internalaudit.client.view.dashboard.AuditJobsByObjective;
import com.internalaudit.client.view.dashboard.AuditJobsByRisk;
import com.internalaudit.client.view.dashboard.ExceptionImplementaionOverDue;
import com.internalaudit.client.view.dashboard.ExceptionsImplementationChart;
import com.internalaudit.client.view.dashboard.JobsByPhases;
import com.internalaudit.client.view.dashboard.JobsDueForCompletionWithinWeek;
import com.internalaudit.client.view.dashboard.JobsDueForKickOffWithinWeek;
import com.internalaudit.client.view.dashboard.JobsExecutionPanel;
import com.internalaudit.client.view.dashboard.ManagementCommentsOverDue;
import com.internalaudit.shared.DashBoardNewDTO;
import com.internalaudit.shared.Employee;
import com.internalaudit.shared.Exceptions;
import com.internalaudit.shared.User;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.PasswordField;
import com.sencha.gxt.widget.core.client.form.TextField;


public class DashBoardNewPresenter implements Presenter 

{
	private final InternalAuditServiceAsync rpcService;
	private final HandlerManager eventBus;
	private final Display display;
	private Logger logger = Logger.getLogger("DashBoardPresenter");
	private User loggedInUser = null;
	
	public interface Display 
	{
		Widget asWidget();
		Object getHtmlErrorMessage = null;
		Button getBtnContinue();
		JobsExecutionPanel getJobsInExecutionPanel();
		JobsDueForKickOffWithinWeek getJobsDueForKickOffWithinWeek();
		ManagementCommentsOverDue getManagementCommentsOverDue();
		JobsDueForCompletionWithinWeek getJobsDueForCompletionWithinWeek();
		ExceptionImplementaionOverDue getExceptionImplementaionOverDue();
		AuditJobsByFunction getAuditJobsByFunction();
		AuditJobsByObjective getAuditJobsByObjective();
		AuditJobsByRisk getAuditJobsByRisk();
		JobsByPhases getJobsByPhases();
		ExceptionsImplementationChart getExceptionsImplementationChart();
	}  

	public DashBoardNewPresenter(InternalAuditServiceAsync rpcService, HandlerManager eventBus, User user, Display view) 
	{
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.display = view;
		this.loggedInUser = user;
	}

	public void go(HasWidgets container) 
	{
		container.clear();
		container.add(display.asWidget());
		bind();
	}

	private void bind() {
		
		  display.getBtnContinue().addClickHandler(new ClickHandler(){

				@Override
				public void onClick(ClickEvent arg0) {
					eventBus.fireEvent(new MainEvent(loggedInUser));
				}});
		
		
		rpcService.fetchDashboard(null, new AsyncCallback<DashBoardNewDTO>(){

			@Override
			public void onFailure(Throwable arg0) {
					
			}

			@Override
			public void onSuccess(DashBoardNewDTO dashboard) {
				display.getJobsInExecutionPanel().setData(dashboard.getJobsInExcutions());
				display.getJobsDueForKickOffWithinWeek().setData(dashboard.getJobKDueForCompletionWithinAWeek());
				display.getManagementCommentsOverDue().setData(dashboard.getJobWithmanagemntCommentsOverdue());
				display.getExceptionImplementaionOverDue().setData(dashboard.getJobsWithExcepptionImplementationoverdue());;
				display.getJobsDueForCompletionWithinWeek().setData(dashboard.getJobKDueForCompletionWithinAWeek());
				display.getAuditJobsByFunction().setData(dashboard.getDivs());
				display.getAuditJobsByObjective().setData(dashboard.getReports());
				display.getAuditJobsByRisk().setData(dashboard.getReports());
				display.getJobsByPhases().setData(dashboard);
				display.getExceptionsImplementationChart().setData(dashboard);
			}});
		
//		rpcService.fetchJobsInExecution(new AsyncCallback<ArrayList<String>>(){
//
//			@Override
//			public void onFailure(Throwable arg0) {
//				Window.alert("fail : fetch jobs in execution");
//			}
//
//			@Override
//			public void onSuccess(ArrayList<String> jobNames) {
//				display.getJobsInExecutionPanel().setData(jobNames);
//				
//			}});
//		
//		rpcService.fetchJobsKickOffWithInaWeek(new AsyncCallback<ArrayList<String>>(){
//
//			@Override
//			public void onFailure(Throwable arg0) {
//				
//			}
//
//			@Override
//			public void onSuccess(ArrayList<String> jobs) {
//				display.getJobsDueForKickOffWithinWeek().setData(jobs);
//				
//			}});
//		
//		rpcService.fetchManagementCommentsOverdue(new AsyncCallback<ArrayList<String>>(){
//
//			@Override
//			public void onFailure(Throwable arg0) {
//				
//			}
//
//			@Override
//			public void onSuccess(ArrayList<String> jobs) {
//				display.getManagementCommentsOverDue().setData(jobs);
//				
//			}});
		
	}
}


