package com.internalaudit.client.view.AuditEngagement;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.internalaudit.client.InternalAuditServiceAsync;
import com.internalaudit.client.view.data.EngagementPlanningViewData;
import com.internalaudit.shared.User;

import java.util.logging.Logger;


public class EngagementPlanningView extends Composite {
		
	@UiField
	TextArea assignmentObjective;

	@UiField
	TextArea activityObjective;
	
	@UiField
	TextArea process;
	
	@UiField
	VerticalPanel activeObjUI;

	@UiField
	VerticalPanel assignObjUI;

	@UiField
	VerticalPanel processUnderstandingUI;
	
	@UiField
	Button submit;
	
	@UiField
	Button save;
	
	@UiField
	Button approve;
	
	@UiField
	Button reject;
	
	@UiField
	Label submittedBy;
	
	@UiField
	HorizontalPanel initiationButtonsPanel;
	
	@UiField
	HorizontalPanel approvalButtonsPanel;

	private Logger logger = Logger.getLogger("EngagementPlanningView");

	private InternalAuditServiceAsync rpcService;
	
	//private AssignObjectiveViewData assignmentObjViewData = new AssignObjectiveViewData();
	//private ActivityObjectiveViewData activityData = new ActivityObjectiveViewData();
	
	private  EngagementPlanningViewData  engagementPlanningViewData= new EngagementPlanningViewData(); 

	private int selectedJobId;
	private  int auditEngId;
	
	private static EngagementPlanningViewUiBinder uiBinder = GWT.create(EngagementPlanningViewUiBinder.class);

	interface EngagementPlanningViewUiBinder extends
	UiBinder<Widget, EngagementPlanningView> {
	}
	
	
	public EngagementPlanningView(final InternalAuditServiceAsync rpcService, final int selectedJobId, final int auditEngId, User loggedInUser) {
		
		this.rpcService =  rpcService;
		
		this.selectedJobId = selectedJobId;
		
		initWidget(uiBinder.createAndBindUi(this));
		
		this.auditEngId = auditEngId;

		engagementPlanningViewData.setData(rpcService);//, assignObjUI, selectedJobId);
				
		engagementPlanningViewData.fetchCreatedJob( this, selectedJobId , loggedInUser);
		
		engagementPlanningViewData.save(rpcService, selectedJobId, loggedInUser, this);

	}


//	private void save(final InternalAuditServiceAsync rpcService,
//			final int selectedJobId, final User loggedInUser) {
//		submit.addClickHandler(new ClickHandler() {
//			
//			@Override
//			public void onClick(ClickEvent arg0) {
//				AuditEngagement auditEngagement = new AuditEngagement();
//				auditEngagement.setStatus(InternalAuditConstants.SAVED);
//				updateAuditEngagement(rpcService, selectedJobId, loggedInUser, auditEngagement);
//			}
//		});
//	}
//	
//	private void updateAuditEngagement(
//			final InternalAuditServiceAsync rpcService,
//			final int selectedJobId, final User loggedInUser, AuditEngagement auditEngagement) {
//		disableFields();
//		
//		
//		
//		auditEngagement.setActivityObj( activityObjective.getText() );
//		
//		auditEngagement.setAssignmentObj( assignmentObjective.getText() );
//		
//		auditEngagement.setProcess( process.getText());
//		
//		
//		Employee initiatedBy = new Employee();
//		initiatedBy = loggedInUser.getEmployeeId();
//		auditEngagement.setInitiatedBy(initiatedBy);
//		
//		
//		
//		JobCreation j = new JobCreation();
//		
//		j.setJobCreationId(selectedJobId);
//		
//		auditEngagement.setJobCreation(j);
//					
//		rpcService.updateAuditEngagement(auditEngagement, "objectives", new AsyncCallback<Boolean>() {
//			
//			@Override
//			public void onSuccess(Boolean caught) {
//				new DisplayAlert("Engagement Planning Saved");
//			}
//			
//			@Override
//			public void onFailure(Throwable caught) {
//				System.out.println("updateAuditEngagement RPC failed");
//				
//
//				logger.log(Level.INFO, "FAIL: updateAuditEngagement .Inside Audit AuditAreaspresenter");
//				if(caught instanceof TimeOutException){
//					History.newItem("login");
//				}else{
//					System.out.println("FAIL: updateAuditEngagement .Inside AuditAreaspresenter");
//					Window.alert("FAIL: declineStrategic");// After FAIL ... write RPC Name  NOT Method Name..
//				}
//				
//				
//			}
//		});
//	}


	public void disableFields() {
		activityObjective.setEnabled(false);
		assignmentObjective.setEnabled(false);
		process.setEnabled(false);
	}
	
	public void enableFields() {
		activityObjective.setEnabled(true);
		assignmentObjective.setEnabled(true);
		process.setEnabled(true);
	}
	
	public void enableInitiationpanel(){
		initiationButtonsPanel.setVisible(true);
		
	}
	
	public void disableInitiationpanel(){
		initiationButtonsPanel.setVisible(false);
		
	}
	
	public void enableApprovalnpanel(){
		approvalButtonsPanel.setVisible(true);
		
	}
	
	public void disableApprovalpanel(){
		approvalButtonsPanel.setVisible(false);
		
	}
	
	public TextArea getAssignmentObjective() {
		return assignmentObjective;
	}


	public void setAssignmentObjective(TextArea assignmentObjective) {
		this.assignmentObjective = assignmentObjective;
	}


	public TextArea getActivityObjective() {
		return activityObjective;
	}


	public void setActivityObjective(TextArea activityObjective) {
		this.activityObjective = activityObjective;
	}


	public TextArea getProcess() {
		return process;
	}


	public void setProcess(TextArea process) {
		this.process = process;
	}


	public Button getSubmit() {
		return submit;
	}


	public void setSubmit(Button submit) {
		this.submit = submit;
	}


	public Button getSave() {
		return save;
	}


	public void setSave(Button save) {
		this.save = save;
	}


	public Button getApprove() {
		return approve;
	}


	public void setApprove(Button approve) {
		this.approve = approve;
	}


	public Button getReject() {
		return reject;
	}


	public void setReject(Button reject) {
		this.reject = reject;
	}


	public Label getSubmittedBy() {
		return submittedBy;
	}


	public void setSubmittedBy(Label submittedBy) {
		this.submittedBy = submittedBy;
	}
	

}
