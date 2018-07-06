package com.internalaudit.client.view.AuditEngagement;

import java.util.ArrayList;

import java.util.logging.Level;
import java.util.logging.Logger;
import com.internalaudit.shared.TimeOutException;
import com.google.gwt.user.client.History;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.internalaudit.client.InternalAuditServiceAsync;
import com.internalaudit.client.view.AuditUniverseIdentificationView;
import com.internalaudit.client.view.ConsolidationHeadingView;
import com.internalaudit.client.view.FinalAuditablesView;
import com.internalaudit.client.view.PrioritizationHeadingView;
import com.internalaudit.client.view.RiskAssesmentView;
import com.internalaudit.shared.AuditEngagement;
import com.internalaudit.shared.Exceptions;
import com.internalaudit.shared.JobCreation;
import com.internalaudit.shared.JobCreationDTO;
import com.internalaudit.shared.Risk;
import com.internalaudit.shared.User;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.container.AccordionLayoutContainer;
import com.sencha.gxt.widget.core.client.container.AccordionLayoutContainer.AccordionLayoutAppearance;

public class KickoffView extends Composite {

	private Logger logger = Logger.getLogger("KickoffView");
	
	private static KickoffViewUiBinder uiBinder = GWT
			.create(KickoffViewUiBinder.class);

	interface KickoffViewUiBinder extends UiBinder<Widget, KickoffView> {
	}

	@UiField 
	VerticalPanel statusPanel;

	@UiField 
	VerticalPanel exPanel;

	@UiField
	Label jobName;

	@UiField
	Label startDate;

	@UiField
	Label endDate;
	private User loggedInUser;

	private InternalAuditServiceAsync rpcService;
	int selectedJobId = 0;
	private int auditEngId = 0;
	private  AuditEngagement selectedAuditEngagement;

	public KickoffView(InternalAuditServiceAsync rpcService, int selectedjobId, int auditEngId, User loggedInUser, AuditEngagement auditEngagement) {
		this.selectedAuditEngagement =  auditEngagement;
		
		this.rpcService = rpcService;
		this.loggedInUser = loggedInUser;
		this.selectedJobId=  selectedjobId;
		initWidget(uiBinder.createAndBindUi(this));
		this.auditEngId = auditEngId;
		showOptionsAccordian();
		

		updateKickoffStatus( selectedjobId );
		//getRiskInfo( auditEngId );

		getJobRelatedInfo(selectedjobId);

		getExceptions();
	}

	private void updateKickoffStatus(int auditEngId) {

		rpcService.updateKickoffStatus(auditEngId, loggedInUser, new AsyncCallback<Void>() {

			@Override
			public void onSuccess(Void result) {

			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("fail");
				

				logger.log(Level.INFO, "FAIL: updateKickoffStatus .Inside Audit AuditAreaspresenter");
				if(caught instanceof TimeOutException){
					History.newItem("login");
				}else{
					System.out.println("FAIL: updateKickoffStatus .Inside AuditAreaspresenter");
					Window.alert("FAIL: updateKickoffStatus");// After FAIL ... write RPC Name  NOT Method Name..
				}
				
			}
		});

	}

	private void getJobRelatedInfo(int selectedjobId) {

		rpcService.fetchCreatedJob(selectedjobId, new AsyncCallback<JobCreation>() {

			@Override
			public void onSuccess(JobCreation result) {
				
				if(result!=null){
				startDate.setText(result.getStartDate()==null? "": result.getStartDate());
				endDate.setText( result.getEndDate()==null? "": result.getEndDate() );	
				jobName.setText( result.getJobName()==null? "": result.getJobName() );
				}

			}

			@Override
			public void onFailure(Throwable caught) {
				System.out.println("Rpc Failed: fetchCreatedJob in Kick off View");
				

				logger.log(Level.INFO, "FAIL: fetchCreatedJob .Inside Audit AuditAreaspresenter");
				if(caught instanceof TimeOutException){
					History.newItem("login");
				}else{
					System.out.println("FAIL: fetchCreatedJob .Inside AuditAreaspresenter");
					Window.alert("FAIL: fetchCreatedJob");// After FAIL ... write RPC Name  NOT Method Name..
				}
				
				
			}
		});

	}

	private void getExceptions()
	{

//		rpcService.fetchJobExceptions(selectedJobId, new AsyncCallback<ArrayList<Exceptions>>() {
//
//			@Override
//			public void onSuccess(ArrayList<Exceptions> ex) {
//
//				for ( int i = 0; i < ex.size(); i++)
//				{
//					System.out.println( ex.get(i).getDetail());
//
//					exPanel.add(new Label(ex.get(i).getDetail()));
//					exPanel.add(new Label(ex.get(i).getJobName()));
//				}
//
//			}
//
//			@Override
//			public void onFailure(Throwable arg0) {
//
//			}
//		});

	}

	private void showOptionsAccordian()
	{
		ContentPanel panel = new ContentPanel();

		panel.setWidth("1200px");

		//panel.setHeadingText("Audit Planning");

		panel.setBodyBorder(false);
		AccordionLayoutContainer con = new AccordionLayoutContainer();
		panel.add(con);

		AccordionLayoutAppearance appearance = GWT.<AccordionLayoutAppearance> create(AccordionLayoutAppearance.class);

		ContentPanel cp = new ContentPanel(appearance);
		cp.setAnimCollapse(false);
		cp.setHeadingText("Audit Notification");
		VerticalPanel vpnlIdentification = new VerticalPanel();
		vpnlIdentification.setHeight("400px");
		AuditNotificationView auditNotificationView = new AuditNotificationView();
		vpnlIdentification.add(auditNotificationView);  
		auditNotificationView.getAuditNotificationViewData().setData(auditNotificationView, rpcService, selectedAuditEngagement);
		
		cp.add(vpnlIdentification);
		con.add(cp);

		cp = new ContentPanel(appearance);
		cp.setAnimCollapse(false);
		cp.setBodyStyleName("pad-text");
		cp.setHeadingText("Engagement Planning");

		VerticalPanel vpnlRiskAssesment = new VerticalPanel();
		vpnlRiskAssesment.setHeight("370px");
		vpnlRiskAssesment.add(new EngagementPlanningView(rpcService, selectedJobId, auditEngId, loggedInUser));
		cp.add(vpnlRiskAssesment);
		con.add(cp);

		cp = new ContentPanel(appearance);
		cp.setAnimCollapse(false);
		cp.setBodyStyleName("pad-text");
		cp.setHeadingText("Key Risks And Existing Controls");
		ScrollPanel v = new ScrollPanel();
		v.setHeight("400px");
		v.add(new RisksView(auditEngId, rpcService, loggedInUser.getEmployeeId()));
		cp.add(v);
		con.add(cp);

		cp = new ContentPanel(appearance);
		cp.setAnimCollapse(false);
		cp.setBodyStyleName("pad-text");
		cp.setHeadingText("Audit Work Programme");
		VerticalPanel vpnl = new VerticalPanel();
		vpnl.setHeight("400px");
		vpnl.add(new AuditWorkProg(rpcService, selectedJobId, loggedInUser.getEmployeeId(), auditEngId));
		cp.add(vpnl);
		con.add(cp);

		/*
	      cp = new ContentPanel(appearance);
	      cp.setAnimCollapse(false);
	      cp.setBodyStyleName("pad-text");
	      cp.setHeadingText("Information Request");
	      VerticalPanel vpnlPriority = new VerticalPanel();
	      vpnlPriority.setHeight("400px");
	      cp.add(vpnlPriority);
	      con.add(cp);*/



//		cp = new ContentPanel(appearance);
//		cp.setAnimCollapse(false);
//		cp.setBodyStyleName("pad-text");
//		cp.setHeadingText("Audit Step");
//		//VerticalPanel vp = new VerticalPanel();
//		//vp.setHeight("400px");
//		ScrollPanel sp = new ScrollPanel();
//		sp.add(new AuditStepView(selectedJobId));
//		sp.setHeight("360px");
//		cp.add(sp);
//		con.add(cp);
		cp = new ContentPanel(appearance);
		cp.setAnimCollapse(false);
		cp.setBodyStyleName("pad-text");
		cp.setHeadingText("Audit Step");
		ScrollPanel sp = new ScrollPanel();
		sp.add(new AuditStepContainer(selectedJobId, rpcService, loggedInUser.getEmployeeId()));
		sp.setHeight("360px");
		cp.add(sp);
		con.add(cp);
		statusPanel.add(panel);



	}

}
