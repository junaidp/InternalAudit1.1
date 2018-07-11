package com.internalaudit.client.view.data;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.internalaudit.client.InternalAuditServiceAsync;
import com.internalaudit.client.view.DisplayAlert;
import com.internalaudit.client.view.AuditEngagement.ActivityObjectiveView;
import com.internalaudit.client.view.AuditEngagement.ActivityObjectiveViewNew;
import com.internalaudit.client.view.AuditEngagement.AssignmentObjectiveView;
import com.internalaudit.client.view.AuditEngagement.EngagementPlanningView;
import com.internalaudit.client.view.AuditEngagement.ProcessUnderstandingView;
import com.internalaudit.shared.AuditEngagement;
import com.internalaudit.shared.Employee;
import com.internalaudit.shared.InternalAuditConstants;
import com.internalaudit.shared.JobCreation;
import com.internalaudit.shared.User;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.internalaudit.shared.TimeOutException;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;

public class EngagementPlanningViewData {

	private Logger logger = Logger.getLogger("EngagementPlanningViewData");

	public EngagementPlanningViewData() {

	}

	InternalAuditServiceAsync rpcService;


	public void setData(InternalAuditServiceAsync rpcService) {
		this.rpcService = rpcService;
	}

	public void fetchCreatedJob(final EngagementPlanningView engagementPlanningView,	int selectedJobId, final User loggedInUser) {


		rpcService.fetchAuditEngagement( selectedJobId , new AsyncCallback<AuditEngagement>() {

			@Override
			public void onFailure(Throwable caught) {
				

				logger.log(Level.INFO, "FAIL: fetchAuditEngagement .Inside Audit AuditAreaspresenter");
				if(caught instanceof TimeOutException){
					History.newItem("login");
				}else{
					System.out.println("FAIL: fetchAuditEngagement .Inside AuditAreaspresenter");
					Window.alert("FAIL: fetchAuditEngagement");// After FAIL ... write RPC Name  NOT Method Name..
				}
				

			}

			@Override
			public void onSuccess(AuditEngagement record) {
				if(record!=null ){
					if(record.getInitiatedBy()!=null && record.getInitiatedBy().getEmployeeId()!=0){
					engagementPlanningView.disableFields();
					}else{
						engagementPlanningView.enableInitiationpanel();
					}
					engagementPlanningView.getProcess().setText( record.getProcess()==null?"": record.getProcess() );
					engagementPlanningView.getActivityObjective().setText(  record.getActivityObj()==null?"": record.getActivityObj() );
					engagementPlanningView.getAssignmentObjective().setText( record.getAssignmentObj()==null?"": record.getAssignmentObj() );		
//					if(!record.getProcess().equals("") &&  !record.getActivityObj().equals("") && !record.getAssignmentObj().equals("")){
//						engagementPlanningView.disableFields();
//					}
					if(record.getInitiatedBy() !=null && record.getInitiatedBy().getEmployeeId() == loggedInUser.getEmployeeId().getEmployeeId()
							&& (record.getStatus() == InternalAuditConstants.SAVED || record.getStatus() == InternalAuditConstants.INITIATED || record.getStatus() == InternalAuditConstants.REJECTED)){
						engagementPlanningView.enableInitiationpanel();
						engagementPlanningView.enableFields();
					}
//					else if(record.getStatus() == InternalAuditConstants.SUBMIT && record.getInitiatedBy().getReportingTo() !=null && record.getInitiatedBy().getReportingTo().getEmployeeId() == loggedInUser.getEmployeeId().getEmployeeId()
//							|| loggedInUser.getEmployeeId().getRollId().getRollId() == 1 ){ //2018: with this auditorhead always see approve buttons
					else if(record.getStatus() == InternalAuditConstants.SUBMIT 
							&& (record.getInitiatedBy().getReportingTo() !=null && record.getInitiatedBy().getReportingTo().getEmployeeId() == loggedInUser.getEmployeeId().getEmployeeId()
							|| 
							loggedInUser.getEmployeeId().getRollId().getRollId() == 1)
							 ){
					
					engagementPlanningView.enableApprovalnpanel();
						engagementPlanningView.enableFields();
						
						engagementPlanningView.getSubmittedBy().setVisible(true);
						engagementPlanningView.getSubmittedBy().setText("Submitted by:" + record.getInitiatedBy().getEmployeeName());
					} 
				}
			}

		});

	}

	public void save(final InternalAuditServiceAsync rpcService, final int selectedJobId,
			final User loggedInUser, final EngagementPlanningView engagementPlanningView) {
		
		//adding addclickhandler to btn add
//		engagementPlanningView.getAdd().addClickHandler(new ClickHandler() {
//			
//			@Override
//			public void onClick(ClickEvent event) {
//				ActivityObjectiveViewNew act = new ActivityObjectiveViewNew();
//				
//			}
//		});
//		
		
		engagementPlanningView.getSubmit().addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent arg0) {
					AuditEngagement auditEngagement = new AuditEngagement();
					Employee initiatedBy = new Employee();
					initiatedBy = loggedInUser.getEmployeeId();
					auditEngagement.setInitiatedBy(initiatedBy);
					
					Employee approvedBy = new Employee();
					approvedBy.setEmployeeId(0);
					auditEngagement.setApprovedBy(approvedBy);
					
					
					
					auditEngagement.setStatus(InternalAuditConstants.SUBMIT);
					updateAuditEngagement(rpcService, selectedJobId, loggedInUser, auditEngagement,  engagementPlanningView , "submitted");
				}
			});
		
		engagementPlanningView.getSave().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent arg0) {
				AuditEngagement auditEngagement = new AuditEngagement();
				Employee initiatedBy = new Employee();
				initiatedBy = loggedInUser.getEmployeeId();
				auditEngagement.setInitiatedBy(initiatedBy);
				
				Employee approvedBy = new Employee();
				approvedBy.setEmployeeId(0);
				auditEngagement.setApprovedBy(approvedBy);
				
				
				auditEngagement.setStatus(InternalAuditConstants.SAVED);
				updateAuditEngagement(rpcService, selectedJobId, loggedInUser, auditEngagement,  engagementPlanningView, "saved");
			}
		});
		
		engagementPlanningView.getReject().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent arg0) {
				AuditEngagement auditEngagement = new AuditEngagement();
				Employee approvedBy = new Employee();
				approvedBy = loggedInUser.getEmployeeId();
				auditEngagement.setApprovedBy(approvedBy);
				
				
				
				auditEngagement.setStatus(InternalAuditConstants.REJECTED);
				updateAuditEngagement(rpcService, selectedJobId, loggedInUser, auditEngagement,  engagementPlanningView, "feedback submitted");
			}
		});
		
		engagementPlanningView.getApprove().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent arg0) {
				AuditEngagement auditEngagement = new AuditEngagement();
				Employee approvedBy = new Employee();
				approvedBy = loggedInUser.getEmployeeId();
				auditEngagement.setApprovedBy(approvedBy);
				
				
				auditEngagement.setStatus(InternalAuditConstants.APPROVED);
				updateAuditEngagement(rpcService, selectedJobId, loggedInUser, auditEngagement,  engagementPlanningView, "approved");
			}
		});
		
		}
		
		private void updateAuditEngagement(
				final InternalAuditServiceAsync rpcService,
				final int selectedJobId, final User loggedInUser, AuditEngagement auditEngagement, EngagementPlanningView engagementPlanningView, final String actionPerformed) {
			if(!actionPerformed.equalsIgnoreCase("saved")){
			engagementPlanningView.disableFields();
			engagementPlanningView.disableInitiationpanel();
			engagementPlanningView.disableApprovalpanel();
			
			}
			auditEngagement.setActivityObj(engagementPlanningView.getActivityObjective().getText() );
			
			auditEngagement.setAssignmentObj(engagementPlanningView.getAssignmentObjective().getText() );
			
			auditEngagement.setProcess( engagementPlanningView.getProcess().getText());
			
			JobCreation j = new JobCreation();
			
			j.setJobCreationId(selectedJobId);
			
			auditEngagement.setJobCreation(j);
						
			rpcService.updateAuditEngagement(auditEngagement, "objectives", new AsyncCallback<Boolean>() {
				
				@Override
				public void onSuccess(Boolean caught) {
					new DisplayAlert("Engagement Planning "+ actionPerformed);
					System.out.println(actionPerformed);
				}
				
				@Override
				public void onFailure(Throwable caught) {
					System.out.println("updateAuditEngagement RPC failed");
					

					logger.log(Level.INFO, "FAIL: updateAuditEngagement .Inside Audit AuditAreaspresenter");
					if(caught instanceof TimeOutException){
						History.newItem("login");
					}else{
						System.out.println("FAIL: updateAuditEngagement .Inside AuditAreaspresenter");
						Window.alert("FAIL: declineStrategic");// After FAIL ... write RPC Name  NOT Method Name..
					}
					
					
				}
			});
		}
		
	
}

