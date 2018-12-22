package com.internalaudit.client.presenter;

import java.util.ArrayList;
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
import com.internalaudit.client.view.ButtonRound;
import com.internalaudit.client.view.DashBoardAuditJobs;
import com.internalaudit.client.view.DashBoardAuditObservations;
import com.internalaudit.client.view.DashBoardResourceManagement;
import com.internalaudit.client.view.LoadingPopup;
import com.internalaudit.shared.Employee;
import com.internalaudit.shared.Exceptions;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.PasswordField;
import com.sencha.gxt.widget.core.client.form.TextField;


public class DashBoardPresenter implements Presenter 

{
	private final InternalAuditServiceAsync rpcService;
	private final HandlerManager eventBus;
	private final Display display;
	private Logger logger = Logger.getLogger("DashBoardPresenter");
	private Employee loggedInUser = null;
	
	public interface Display 
	{
		Widget asWidget();
		Object getHtmlErrorMessage = null;
		DashBoardAuditJobs getDashBoardAuditJobs();
		DashBoardAuditObservations getDashBoardAuditObservations();
		DashBoardResourceManagement getDashBoardResourceManagement();
		ButtonRound getBtnContinue();
	}  

	public DashBoardPresenter(InternalAuditServiceAsync rpcService, HandlerManager eventBus, Employee user, Display view) 
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
		
		final DecoratedPopupPanel popLoading = new DecoratedPopupPanel();
	    Label lblPopup = new Label("");
	    lblPopup.setWidth("200px");
		VerticalPanel hpn =  new VerticalPanel();
	    hpn.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
	    hpn.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
	    Image loading = new Image("images/spinning.gif");
	    hpn.add(loading);
	    hpn.add(lblPopup);
	    popLoading.setWidget(hpn);
	    popLoading.setGlassEnabled(true);
	    popLoading.center();
	    popLoading.addStyleName("whiteBackground");
	    
	    display.getBtnContinue().addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent arg0) {
				eventBus.fireEvent(new MainEvent(loggedInUser));
			}});
	    
	    
		rpcService.fetchNumberofPlannedJobs(new AsyncCallback<Integer>(){

			@Override
			public void onFailure(Throwable caught) {
				popLoading.removeFromParent();
				

				logger.log(Level.INFO, "FAIL: fetchNumberofPlannedJobs .Inside Audit AuditAreaspresenter");
				if(caught instanceof TimeOutException){
					History.newItem("login");
				}else{
					System.out.println("FAIL: fetchNumberofPlannedJobs .Inside AuditAreaspresenter");
					Window.alert("FAIL: fetchNumberofPlannedJobs");// After FAIL ... write RPC Name  NOT Method Name..
				}
				
				
			}

			@Override
			public void onSuccess(Integer numberOfPlannedJobs) {
				display.getDashBoardAuditJobs().getPlannedJobs().setText(numberOfPlannedJobs+"");
			}});
		
		rpcService.fetchNumberofInProgressJobs(new AsyncCallback<Integer>(){

			@Override
			public void onFailure(Throwable caught) {
				popLoading.removeFromParent();
				

				logger.log(Level.INFO, "FAIL: fetchNumberofInProgressJobs .Inside Audit AuditAreaspresenter");
				if(caught instanceof TimeOutException){
					History.newItem("login");
				}else{
					System.out.println("FAIL: fetchNumberofInProgressJobs .Inside AuditAreaspresenter");
					Window.alert("FAIL: fetchNumberofInProgressJobs");// After FAIL ... write RPC Name  NOT Method Name..
				}
				
				
			}

			@Override
			public void onSuccess(Integer numberOfInProgressJobs) {
				display.getDashBoardAuditJobs().getInProgressJobs().setText(numberOfInProgressJobs+"");
			}});
		
		rpcService.fetchNumberofCompletedJobs(new AsyncCallback<Integer>(){

			@Override
			public void onFailure(Throwable caught) {
				popLoading.removeFromParent();
				

				logger.log(Level.INFO, "FAIL: fetchNumberofCompletedJobs .Inside Audit AuditAreaspresenter");
				if(caught instanceof TimeOutException){
					History.newItem("login");
				}else{
					System.out.println("FAIL: fetchNumberofCompletedJobs .Inside AuditAreaspresenter");
					Window.alert("FAIL: fetchNumberofCompletedJobs");// After FAIL ... write RPC Name  NOT Method Name..
				}
				
				
			}

			@Override
			public void onSuccess(Integer numberOfCompletedJobs) {
				display.getDashBoardAuditJobs().getCompletedJobs().setText(numberOfCompletedJobs+"");
			}});
		
		rpcService.fetchJobsKickOffWithInaWeek(new AsyncCallback<ArrayList<String>>(){

			@Override
			public void onFailure(Throwable caught) {
				popLoading.removeFromParent();
				

				logger.log(Level.INFO, "FAIL: fetchJobsKickOffNextWeek .Inside Audit AuditAreaspresenter");
				if(caught instanceof TimeOutException){
					History.newItem("login");
				}else{
					System.out.println("FAIL: fetchJobsKickOffNextWeek .Inside AuditAreaspresenter");
					Window.alert("FAIL: fetchJobsKickOffNextWeek");// After FAIL ... write RPC Name  NOT Method Name..
				}
				
			}

			@Override
			public void onSuccess(ArrayList<String> kickOffNextWeek) {
				for(int i=0; i< kickOffNextWeek.size(); i++){
					Label job = new Label("("+kickOffNextWeek.get(i)+")");
					job.addStyleName("navybluebold");
					display.getDashBoardAuditJobs().getVpnlDueKickOffNextWeek().add(job);
					
				}
			}});
		
		rpcService.fetchNumberOfAuditObservations(new AsyncCallback<Integer>(){

			@Override
			public void onFailure(Throwable caught) {
				popLoading.removeFromParent();
				

				logger.log(Level.INFO, "FAIL: fetchNumberOfAuditObservations .Inside Audit AuditAreaspresenter");
				if(caught instanceof TimeOutException){
					History.newItem("login");
				}else{
					System.out.println("FAIL: fetchNumberOfAuditObservations .Inside AuditAreaspresenter");
					Window.alert("FAIL: fetchNumberOfAuditObservations");// After FAIL ... write RPC Name  NOT Method Name..
				}
				
				
			}

			@Override
			public void onSuccess(Integer noOfAuditObservations) {
				display.getDashBoardAuditObservations().getNoOfAuditObservations().setText(noOfAuditObservations+"");
			}});
		
		rpcService.fetchNumberOfExceptionsInProgress(new AsyncCallback<Integer>(){

			@Override
			public void onFailure(Throwable caught) {
				popLoading.removeFromParent();
				

				logger.log(Level.INFO, "FAIL: fetchNumberOfExceptionsInProgress .Inside Audit AuditAreaspresenter");
				if(caught instanceof TimeOutException){
					History.newItem("login");
				}else{
					System.out.println("FAIL: fetchNumberOfExceptionsInProgress .Inside AuditAreaspresenter");
					Window.alert("FAIL: fetchNumberOfExceptionsInProgress");// After FAIL ... write RPC Name  NOT Method Name..
				}
				
				
			}

			@Override
			public void onSuccess(Integer noOfExceptionsInProgress) {
				display.getDashBoardAuditObservations().getImplementationInProgress().setText(noOfExceptionsInProgress+"");
			}});
		
		rpcService.fetchNumberOfExceptionsImplemented(new AsyncCallback<Integer>(){

			@Override
			public void onFailure(Throwable caught) {
				popLoading.removeFromParent();
				

				logger.log(Level.INFO, "FAIL: fetchNumberOfExceptionsImplemented .Inside Audit AuditAreaspresenter");
				if(caught instanceof TimeOutException){
					History.newItem("login");
				}else{
					System.out.println("FAIL: fetchNumberOfExceptionsImplemented .Inside AuditAreaspresenter");
					Window.alert("FAIL: fetchNumberOfExceptionsImplemented");// After FAIL ... write RPC Name  NOT Method Name..
				}
				
				
			}

			@Override
			public void onSuccess(Integer noOfExceptionsImplemented) {
				display.getDashBoardAuditObservations().getImplemented().setText(noOfExceptionsImplemented+"");
			}});
		
		rpcService.fetchNumberOfExceptionsOverdue(new AsyncCallback<Integer>(){

			@Override
			public void onFailure(Throwable caught) {
				popLoading.removeFromParent();
				

				logger.log(Level.INFO, "FAIL: fetchNumberOfExceptionsOverdue .Inside Audit AuditAreaspresenter");
				if(caught instanceof TimeOutException){
					History.newItem("login");
				}else{
					System.out.println("FAIL: fetchNumberOfExceptionsOverdue .Inside AuditAreaspresenter");
					Window.alert("FAIL: fetchNumberOfExceptionsOverdue");// After FAIL ... write RPC Name  NOT Method Name..
				}
				
				
			}

			@Override
			public void onSuccess(Integer noOfExceptionsOverdue) {
				display.getDashBoardAuditObservations().getOverdue().setText(noOfExceptionsOverdue+"");
			}});
		
		
		rpcService.fetchEmployeesAvilbleForNext2Weeks(new AsyncCallback<ArrayList<String>>(){

			@Override
			public void onFailure(Throwable caught) {
				popLoading.removeFromParent();
				

				logger.log(Level.INFO, "FAIL: fetchEmployeesAvilbleForNext2Weeks .Inside Audit AuditAreaspresenter");
				if(caught instanceof TimeOutException){
					History.newItem("login");
				}else{
					System.out.println("FAIL: fetchEmployeesAvilbleForNext2Weeks .Inside AuditAreaspresenter");
					Window.alert("FAIL: fetchEmployeesAvilbleForNext2Weeks");// After FAIL ... write RPC Name  NOT Method Name..
				}
				
				
			}

			@Override
			public void onSuccess(ArrayList<String> employeesAvilbleForNext2Weeks) {
				for(int i=0; i< employeesAvilbleForNext2Weeks.size(); i++){
					Label employee = new Label("("+employeesAvilbleForNext2Weeks.get(i)+")");
					employee.addStyleName("navybluebold");
					display.getDashBoardResourceManagement().getVpnlResources().add(employee);
					
				}
				popLoading.removeFromParent();
			}});
		
		
	}

	


}


