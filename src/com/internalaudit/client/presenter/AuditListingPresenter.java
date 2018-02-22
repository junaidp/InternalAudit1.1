package com.internalaudit.client.presenter;

import java.util.ArrayList;
import java.util.Date;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratedPopupPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.internalaudit.client.InternalAuditServiceAsync;
import com.internalaudit.client.view.DisplayAlert;
import com.internalaudit.client.view.LoadingPopup;
import com.internalaudit.client.view.PopupsViewWhite;
import com.internalaudit.client.view.Scheduling.JobsSchedulingView;
import com.internalaudit.client.view.Scheduling.ResourceSchedilingView;
import com.internalaudit.client.view.Scheduling.TimeLineJobsView;
import com.internalaudit.client.view.Scheduling.TimeLineResourceView;
import com.internalaudit.client.widgets.AuditScheduling;
import com.internalaudit.shared.EmployeeJobDTO;
import com.internalaudit.shared.JobCreation;
import com.internalaudit.shared.JobsOfEmployee;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.internalaudit.shared.TimeOutException;


public class AuditListingPresenter implements Presenter {


	private final InternalAuditServiceAsync rpcService;
	private final HandlerManager eventBus;
	private final Display display;
	private Logger logger = Logger.getLogger("AuditListingPresenter");
	int count=0;
	DecoratedPopupPanel popup;
	public interface Display 
	{
		Widget asWidget();
		Object getHtmlErrorMessage = null;
		Button getBtnBack();
		Anchor getResourceButton();
		Anchor getJobsButton();
	}  


	public AuditListingPresenter(InternalAuditServiceAsync rpcService, HandlerManager eventBus, Display view) 
	{
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.display = view;
	}


	@Override
	public void go(HasWidgets container) {
		container.clear();

		container.add(display.asWidget());
		
		display.getBtnBack().addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				History.newItem("auditScheduling");
			}});
		

		display.getJobsButton().addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				JobsSchedulingView jobSchedulingView = new JobsSchedulingView();
				
				fetchJobs(jobSchedulingView);
			}});
		
		display.getResourceButton().addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				JobsSchedulingView resourceSchedulingView = new JobsSchedulingView();
				
				fetchEmployees(resourceSchedulingView);
			}});
		
		
		

	}
	
	public void fetchEmployees(final JobsSchedulingView resourceSchedulingView){
		
		rpcService.fetchEmployeesWithJobs(new AsyncCallback<ArrayList<JobsOfEmployee>>(){

			@Override
			public void onFailure(Throwable caught) {
				

				logger.log(Level.INFO, "FAIL: fetchEmployeesWithJobs .Inside Audit AuditAreaspresenter");
				if(caught instanceof TimeOutException){
					History.newItem("login");
				}else{
					System.out.println("FAIL: fetchEmployeesWithJobs .Inside AuditAreaspresenter");
					Window.alert("FAIL: fetchEmployeesWithJobs");// After FAIL ... write RPC Name  NOT Method Name..
				}
				
				
			}

			@Override
			public void onSuccess(ArrayList<JobsOfEmployee> result) {
				new PopupsViewWhite(resourceSchedulingView);
				addHeadingResource(resourceSchedulingView);
//				for ( int i = 1; i< result.size(); i++)
				
				for ( int i = 0; i< result.size(); i++)
				{
					final ResourceSchedilingView auditScheduling = new ResourceSchedilingView();
					auditScheduling.getResourceName().setText(result.get(i).getEmployee().getEmployeeName());
					
					auditScheduling.setEmployeeId(result.get(i).getEmployee().getEmployeeId());
					resourceSchedulingView.getListContainer().add(auditScheduling);
					auditScheduling.getTimeLineContainer().add(new TimeLineResourceView(result.get(i).getJobs()));
					
				}
			
			}});
	}

	private void fetchJobs(final JobsSchedulingView jobSchedulingView) {
		rpcService.fetchJobs(new AsyncCallback<ArrayList<JobCreation>>(){

			@Override
			public void onFailure(Throwable caught) {


				logger.log(Level.INFO, "FAIL: fetchJobs .Inside Audit AuditAreaspresenter");
				if(caught instanceof TimeOutException){
					History.newItem("login");
				}else{
					System.out.println("FAIL: fetchJobs .Inside AuditAreaspresenter");
					Window.alert("FAIL: fetchJobs");// After FAIL ... write RPC Name  NOT Method Name..
				}
				
				
			}

			@Override
			public void onSuccess(ArrayList<JobCreation> result) {
				new PopupsViewWhite(jobSchedulingView);
				addHeading(jobSchedulingView);
				AuditScheduling auditSchedulingTemp=null;
				for ( int i =0; i< result.size(); i++)
				{
					 final AuditScheduling auditScheduling = new AuditScheduling();
					auditScheduling.getJobName().setText(result.get(i).getJobName());
					auditScheduling.setEstimatedWeeks(result.get(i).getEstimatedWeeks());	

					auditScheduling.setJobId(result.get(i).getJobCreationId());
					auditScheduling.getEndDate().setText(result.get(i).getEndDate());///
					auditScheduling.getStartDate().getTextBox().setText(result.get(i).getStartDate());
					
					jobSchedulingView.getListContainer().add(auditScheduling);
					auditScheduling.getTimeLineContainer().add(new TimeLineJobsView(result.get(i).getTimeLineDates()));
//					setHandlers(auditScheduling);
					if(result.get(i).isApproved()){
						auditScheduling.getStartDate().setEnabled(false);
					}
					
					/////////////////////
					if(auditSchedulingTemp==null || auditSchedulingTemp!=auditScheduling){
					auditSchedulingTemp = auditScheduling;
					
					auditScheduling.getStartDate().addValueChangeHandler(new ValueChangeHandler<Date>() {


						@Override
						public void onValueChange(ValueChangeEvent<Date> event) {
							
							getEndDate(auditScheduling, event);
						}

						
					});
					///////////////////////
					}
				}
			}

		});
	}

	private void addHeading(JobsSchedulingView jobSchedulingView) {
		HorizontalPanel headingPanel = new HorizontalPanel();

		Label empty = new Label("");
		empty.setWidth("100px");
		headingPanel.add(empty);

		Label jobName = new Label("Job Name");
		jobName.setWidth("160px");
		headingPanel.add(jobName);

		Label startDate = new Label("Start Date");
		headingPanel.add(startDate);
		startDate.setWidth("90px");

		Label endDate = new Label("End Date");
		headingPanel.add(endDate);
		endDate.setWidth("90px");		

		String[] names  = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"}; 

		FlexTable flexHeading = new FlexTable();
		flexHeading.setCellSpacing(0);
		flexHeading.setCellPadding(0);
		flexHeading.setBorderWidth(0);
		for ( int i = 0; i < 12 ; i++)
		{
			flexHeading.getCellFormatter().setWidth(0, i, "66px");
			Label month = new Label(names[i]);
			month.setWordWrap(false);
			flexHeading.setWidget(0, i , month);
			flexHeading.addStyleName("list-heading");

			//Months with 5 weeks
			if(month.getText().equals("Mar") || month.getText().equals("May")|| month.getText().equals("Jul") || month.getText().equals("Oct")){
				flexHeading.getCellFormatter().setWidth(0, i, "86px");
			}
			if(month.getText().equals("Dec")){
				flexHeading.getCellFormatter().setWidth(0, i, "90px");
			}
		}
		headingPanel.addStyleName("list-heading");
		headingPanel.setWidth("428px");
		jobSchedulingView.getHeadingsPanel().add(headingPanel);
		jobSchedulingView.getHeadingsPanel().add(flexHeading); 


	}
	
	
	private void addHeadingResource(JobsSchedulingView jobSchedulingView) {
		HorizontalPanel headingPanel = new HorizontalPanel();

		
		Label employeeName = new Label("Employee Name");
		employeeName.setWidth("160px");
		headingPanel.add(employeeName);

		String[] names  = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"}; 

		FlexTable flexHeading = new FlexTable();
		flexHeading.setCellSpacing(0);
		flexHeading.setCellPadding(0);
		flexHeading.setBorderWidth(0);
		for ( int i = 0; i < 12 ; i++)
		{
			flexHeading.getCellFormatter().setWidth(0, i, "66px");
			Label month = new Label(names[i]);
			month.setWordWrap(false);
			flexHeading.setWidget(0, i , month);
			flexHeading.addStyleName("list-heading");

			//Months with 5 weeks
			if(month.getText().equals("Mar") || month.getText().equals("May")|| month.getText().equals("Jul") || month.getText().equals("Oct")){
				flexHeading.getCellFormatter().setWidth(0, i, "86px");
			}
			if(month.getText().equals("Dec")){
				flexHeading.getCellFormatter().setWidth(0, i, "110px");
			}
		}
		headingPanel.addStyleName("list-heading");
		headingPanel.setWidth("300px");
		jobSchedulingView.getHeadingsPanel().add(headingPanel);
		jobSchedulingView.getHeadingsPanel().add(flexHeading); 


	}

	public void setHandlers(final AuditScheduling auditScheduling){
		
		


		auditScheduling.getSaveButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				System.out.println("in button");

//				saveEndDate(auditScheduling);

			}

			
		});
		
		



		
	}
	
	private void getEndDate(final AuditScheduling auditScheduling,
			ValueChangeEvent<Date> event) {
		System.out.println("in value change");
		
		rpcService.getEndDate(event.getValue(), auditScheduling.getEstimatedWeeks(), new AsyncCallback<String>() {

			@Override
			public void onSuccess(String result) {
				auditScheduling.getEndDate().setText(result);
				saveEndDate(auditScheduling);
				
			}

			@Override
			public void onFailure(Throwable caught) {
				System.out.println("FAIL: rpcService.getEndDate in AuditListing Presenter");
				

				logger.log(Level.INFO, "FAIL: getEndDate .Inside Audit AuditAreaspresenter");
				if(caught instanceof TimeOutException){
					History.newItem("login");
				}else{
					System.out.println("FAIL: getEndDate .Inside AuditAreaspresenter");
					Window.alert("FAIL: getEndDate");// After FAIL ... write RPC Name  NOT Method Name..
				}
				
				
			}
		});
	}
	
	private void saveEndDate(final AuditScheduling auditScheduling) {
		
		final LoadingPopup loadingPopup = new LoadingPopup();
		loadingPopup.display();
		
		rpcService.updateEndDateForJob( 
				auditScheduling.getJobId(), 
				auditScheduling.getStartDate().getTextBox().getText(),
				auditScheduling.getEndDate().getText(), 

				new AsyncCallback<JobCreation>() {

					@Override
					public void onFailure(Throwable caught) {
						
						loadingPopup.remove();
						logger.log(Level.INFO, "FAIL: updateEndDateForJob .Inside Audit AuditAreaspresenter");
						if(caught instanceof TimeOutException){
							History.newItem("login");
						}else{
							System.out.println("FAIL: updateEndDateForJob .Inside AuditAreaspresenter");
							Window.alert("FAIL: updateEndDateForJob");// After FAIL ... write RPC Name  NOT Method Name..
						}
						

					}

					@Override
					public void onSuccess(JobCreation result) {
						//						History.newItem("");
						//						History.newItem("auditListing");
						auditScheduling.getTimeLineContainer().clear();
						auditScheduling.getTimeLineContainer().add(new TimeLineJobsView(result.getTimeLineDates()));
						loadingPopup.remove();
						
						if(result.getEmployeeJobDTO()!=null && result.getEmployeeJobDTO().size()>0){
//						if(count==0){
//							count=count+1;
							displayJobConflicts(result.getEmployeeJobDTO());
//							Window.alert(result.getEmployeeJobDTO().getEmployeeName()+"'s " + "Job "+ "'"+result.getEmployeeJobDTO().getJobName()+"'"+" ( "+result.getEmployeeJobDTO().getSatrtDate()+" - "+result.getEmployeeJobDTO().getEndDate()+" ) Collapse with this schedule ");
//						}
						}
						new DisplayAlert("Schedule saved");
					}

				});
	}
	
	public void displayJobConflicts(ArrayList<EmployeeJobDTO> result){
		if(popup!=null&& popup.isShowing()){
			popup.removeFromParent();
		}
		popup = new DecoratedPopupPanel();
		VerticalPanel vpnl = new VerticalPanel();
		vpnl.setSpacing(5);
		Button btnClose = new Button("Ok");
		for(int i=0; i<result.size(); i++){
			HorizontalPanel hpnl = new HorizontalPanel();
			hpnl.setSpacing(3);
			Label lbl = new Label(result.get(i).getEmployeeName()+"'s " + "Job "+ "'"+result.get(i).getJobName()+"'"+" ( "+result.get(i).getSatrtDate()+" - "+result.get(i).getEndDate()+" ) Collapse with this schedule");
			hpnl.add(lbl);
			vpnl.add(hpnl);
			
		}
		HorizontalPanel hpnlButton = new HorizontalPanel();
		vpnl.add(hpnlButton);
		hpnlButton.add(btnClose);
		hpnlButton.setWidth("300px");
		hpnlButton.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		vpnl.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		popup.setWidget(vpnl);
		popup.center();
		
		btnClose.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				popup.removeFromParent();
			}
		});
		
	}

}