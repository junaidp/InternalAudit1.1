package com.internalaudit.client.view.AuditEngagement;

import java.util.ArrayList;

import java.util.logging.Level;
import java.util.logging.Logger;
import com.internalaudit.shared.TimeOutException;
import com.google.gwt.user.client.History;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.internalaudit.client.InternalAuditServiceAsync;
import com.internalaudit.client.util.DataStorage;
import com.internalaudit.client.view.DisplayAlert;
import com.internalaudit.client.widgets.AddIcon;
import com.internalaudit.shared.ActivityObjective;
import com.internalaudit.shared.AuditEngagement;
import com.internalaudit.shared.JobCreation;
import com.internalaudit.shared.RiskObjective;
import com.internalaudit.shared.SubProcess;
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

	@UiField
	Label lblProcess;

	@UiField
	Label lblAuditableUnit;

	@UiField
	Label lblSubProcess;
	@UiField
	Label lblJobType;

	private User loggedInUser;

	private InternalAuditServiceAsync rpcService;
	int selectedJobId = 0;
	private int auditEngId = 0;
	private  AuditEngagement selectedAuditEngagement;
	private SubProcess subProcess;

	public KickoffView(InternalAuditServiceAsync rpcService, int selectedjobId, int auditEngId, User loggedInUser, AuditEngagement auditEngagement) {
		this.selectedAuditEngagement =  auditEngagement;

		this.rpcService = rpcService;
		this.loggedInUser = loggedInUser;
		this.selectedJobId=  selectedjobId;
		initWidget(uiBinder.createAndBindUi(this));
		this.auditEngId = auditEngId;
		fetchCreatedJob(selectedJobId, loggedInUser);



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
					lblProcess.setText(result.getStrategic().getProcess()== null? "": result.getStrategic().getProcess().getProcessName());
					lblSubProcess.setText(result.getStrategic().getSubProcess()== null? "": result.getStrategic().getSubProcess().getSubProcessName());
					lblJobType.setText(result.getStrategic().getJobType()== null? "": result.getStrategic().getJobType().getJobTypeName());
					lblAuditableUnit.setText(result.getStrategic().getAuditableUnit()==null?"": result.getStrategic().getAuditableUnit());

					subProcess = result.getStrategic().getSubProcess();
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

	public void fetchCreatedJob(int selectedJobId, final User loggedInUser) {


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
				showOptionsAccordian(record);

			}
		});
	}

	private void showOptionsAccordian(AuditEngagement record)
	{
		ContentPanel panel = new ContentPanel();

		panel.setWidth("1200px");

		//panel.setHeadingText("Audit Planning");

		panel.setBodyBorder(false);
		AccordionLayoutContainer con = new AccordionLayoutContainer();
		panel.add(con);

		AccordionLayoutAppearance appearance = GWT.<AccordionLayoutAppearance> create(AccordionLayoutAppearance.class);

		/////////////////////////////////////AUDIT NOTIFICATION ///////////////////////////
		ContentPanel cp = new ContentPanel(appearance);
		cp.setAnimCollapse(false);
		cp.setHeadingText("Audit Notification");
		VerticalPanel vpnlIdentification = new VerticalPanel();
		vpnlIdentification.setHeight("400px");
		AuditNotificationViewNew auditNotificationViewNew = new AuditNotificationViewNew();
		//		AuditNotificationView auditNotificationView = new AuditNotificationView();
		//		vpnlIdentification.add(auditNotificationView);  
		//		auditNotificationView.getAuditNotificationViewData().setData(auditNotificationView, rpcService, selectedAuditEngagement);


		vpnlIdentification.add(auditNotificationViewNew);
		auditNotificationViewNew.getAuditNotificationViewNewData().setData(auditNotificationViewNew, rpcService, selectedAuditEngagement);


		cp.add(vpnlIdentification);
		con.add(cp);

		/////////////////////////////////////Client Kickoff Meeting Objectives ///////////////////////////

		cp = new ContentPanel(appearance);
		cp.setAnimCollapse(false);
		cp.setBodyStyleName("pad-text");
		cp.setHeadingText("Client Kickoff Meeting Objectives");

		final VerticalPanel vpnlActicityObjective = new VerticalPanel();
		final VerticalPanel vpnlActicityObjectiveContainer = new VerticalPanel();
		Button btnSaveActicityObjective = new Button("Save");
		vpnlActicityObjective.setHeight("370px");
		//vpnlRiskAssesment.add(new EngagementPlanningView(rpcService, selectedJobId, auditEngId, loggedInUser, record));
		//ActivityObjectiveViewNew activityObjectiveView = new ActivityObjectiveViewNew();

		AddIcon btnAddAcitivityObjective = new AddIcon();

		for(int i =0 ;i< record.getEngagementDTO().getActivityObjectiveList().size(); i++){
			ActivityObjectiveViewNew activityObjectiveView = new ActivityObjectiveViewNew();
			activityObjectiveView.setData(record.getEngagementDTO().getActivityObjectiveList().get(i));
			vpnlActicityObjectiveContainer.add(activityObjectiveView);
		}

		btnAddAcitivityObjective.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				ActivityObjectiveViewNew act = new ActivityObjectiveViewNew();
				vpnlActicityObjectiveContainer.add(act);
			}
		});

		vpnlActicityObjective.add(vpnlActicityObjectiveContainer);
		vpnlActicityObjective.add(btnAddAcitivityObjective);
		vpnlActicityObjective.add(btnSaveActicityObjective);
		cp.add(vpnlActicityObjective);
		con.add(cp);

		btnSaveActicityObjective.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				ArrayList<ActivityObjective> activityObjectives = new ArrayList<ActivityObjective>();
				for(int i=0; i< vpnlActicityObjectiveContainer.getWidgetCount() ; i++){
					ActivityObjectiveViewNew activityObjectiveView =  (ActivityObjectiveViewNew)vpnlActicityObjectiveContainer.getWidget(i);
					ActivityObjective activityObjective = new ActivityObjective();
					activityObjectiveView.getData(activityObjective);
					activityObjective.setSubProcessId(subProcess);
					activityObjectives.add(activityObjective);
				}
				saveActivityObjectives(activityObjectives);

			}

		});

		/////////////////////////////////////KEY RISKS ///////////////////////////

		cp = new ContentPanel(appearance);
		cp.setAnimCollapse(false);
		cp.setBodyStyleName("pad-text");
		cp.setHeadingText("Key Risks");
		ScrollPanel v = new ScrollPanel();
		v.setHeight("400px");
		//v.setWidth("600px");
		 VerticalPanel verticalPanelKeyRisks = new VerticalPanel();
		final VerticalPanel verticalPanelKeyRisksContainer = new VerticalPanel();
		Button btnSaveKeyRisk = new Button("Save");



		//verticalPanelKeyRisks.add(new KeyRiskViewNew());
		//2018

		ArrayList<Integer> objectiveIds = new ArrayList<Integer>();
		for(int i=0; i< record.getEngagementDTO().getRiskObjectiveList().size(); i++){
			KeyRiskViewNew keyRiskView = new KeyRiskViewNew();
			verticalPanelKeyRisksContainer.add(keyRiskView);
			final ActivityObjective activityObjective = record.getEngagementDTO().getRiskObjectiveList().get(i).getObjectiveId();
			boolean objectiveAdded = objectiveIds.contains(activityObjective.getObjectiveId());
			objectiveIds.add(activityObjective.getObjectiveId());
			keyRiskView.setData(record.getEngagementDTO().getRiskObjectiveList().get(i), objectiveAdded);
			keyRiskView.setActivityObjective(activityObjective);

			final DataStorage dataStorage = new DataStorage(); // We will use the same datastoage class and set the same count and value field for every other tabs too.
			dataStorage.setCount(i);


			if(objectiveAdded){ // removing the add icon if more risks are here, so add icon will only display below the last item
				KeyRiskViewNew keyRiskViewPrevious = (KeyRiskViewNew)verticalPanelKeyRisksContainer.getWidget(i-1);
				keyRiskViewPrevious.getBtnAddKeyRisk().setVisible(false);
			}
			//add handler
			keyRiskView.getBtnAddKeyRisk().addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					KeyRiskViewNew keyRiskViewNew = new KeyRiskViewNew();
					keyRiskViewNew.setActivityObjective(activityObjective);
					keyRiskViewNew.hideElemetns();
					verticalPanelKeyRisksContainer.insert(keyRiskViewNew, dataStorage.getCount()+2);

				}
			});
		}


		//	v.add(new RisksView(auditEngId, rpcService, loggedInUser.getEmployeeId()));
		
		
		verticalPanelKeyRisks.add(verticalPanelKeyRisksContainer);
		verticalPanelKeyRisks.add(btnSaveKeyRisk);

		v.add(verticalPanelKeyRisks);
		cp.add(v);
		con.add(cp);

		btnSaveKeyRisk.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				ArrayList<RiskObjective> riskObjectives = new ArrayList<RiskObjective>();
				for(int i=0; i< verticalPanelKeyRisksContainer.getWidgetCount() ; i++){
					KeyRiskViewNew keyRiskView =  (KeyRiskViewNew)verticalPanelKeyRisksContainer.getWidget(i);
					RiskObjective riskObjective = new RiskObjective();
					keyRiskView.getData(riskObjective);
					riskObjectives.add(riskObjective);
				}
				saveRiskObjectives(riskObjectives);

			}
		});

		/////////////////////////////////////Objective Risk Control Matrix///////////////////////////

		cp = new ContentPanel(appearance);
		cp.setAnimCollapse(false);

		cp.setBodyStyleName("pad-text");
		cp.setHeadingText("Objective Risk Control Matrix");

		ScrollPanel vps = new ScrollPanel();
		vps.setHeight("400px");
		final VerticalPanel vpExistingControl = new VerticalPanel();

		FocusPanel btnAddExistingControl = new FocusPanel();
		Label l1 = new Label("+");
		btnAddExistingControl.getElement().getStyle().setMarginLeft(300, Unit.PX);

		btnAddExistingControl.add(l1);
		btnAddExistingControl.setStyleName("w3-button w3-small w3-circle w3-green w3-margin");
		//	v.add(new RisksView(auditEngId, rpcService, loggedInUser.getEmployeeId()));


		//vpExistingControl.add( new ExistingControlViewNew());
		//2018
		ArrayList<Integer> riskIds = new ArrayList<Integer>();
		for(int i=0; i< record.getEngagementDTO().getSuggestedControlsList().size(); i++){
			ExistingControlViewNew existingControlViewNew = new ExistingControlViewNew();
			vpExistingControl.add(existingControlViewNew);
			boolean riskAdded = riskIds.contains(record.getEngagementDTO().getSuggestedControlsList().get(i).getRiskId().getRiskId());
			riskIds.add(record.getEngagementDTO().getSuggestedControlsList().get(i).getRiskId().getRiskId());
			existingControlViewNew.setData(record.getEngagementDTO().getSuggestedControlsList().get(i), riskAdded);
		}

		//final VerticalPanel addPanelExistingControl = new VerticalPanel();
		//vpExistingControl.add(addPanelExistingControl);
		vpExistingControl.add(btnAddExistingControl);
		vps.add(vpExistingControl);

		//cp.add(panelAdd);
		cp.add(vps);

		con.add(cp);

		//adddhandler btnadd1 existingcontrol
		btnAddExistingControl.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				ExistingControlViewNew existingControl= new ExistingControlViewNew();
				existingControl.hideElemetns();
				vpExistingControl.add(existingControl);

			}
		});

		////////////////////////////////////////Audit Work Programme//////////////////////////////


		cp = new ContentPanel(appearance);
		cp.setAnimCollapse(false);
		cp.setBodyStyleName("pad-text");
		cp.setHeadingText("Audit Work Programme");
		VerticalPanel vpnl = new VerticalPanel();
		vpnl.setHeight("400px");
		//vpnl.add(new AuditWorkProg(rpcService, selectedJobId, loggedInUser.getEmployeeId(), auditEngId));
		final VerticalPanel verticalPanelAuditWorkProg = new VerticalPanel();
		//final VerticalPanel addpanelAuditworkprog = new VerticalPanel();

		AddIcon btnAddAuditWork = new AddIcon();

		//verticalPanelAuditWorkProg.add(new AuditWorkProgramNew());
		//verticalPanelAuditWorkProg.add(addpanelAuditworkprog);
		//2018

		for(int i=0; i< record.getEngagementDTO().getAuditProgrammeList().size(); i++){
			AuditWorkProgramNew auditWorkProgramNew = new AuditWorkProgramNew();
			verticalPanelAuditWorkProg.add(auditWorkProgramNew);
			auditWorkProgramNew.setData(record.getEngagementDTO().getAuditProgrammeList().get(i));
		}
		verticalPanelAuditWorkProg.add(btnAddAuditWork);


		//addclickhandler of button risk
		btnAddAuditWork.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				AuditWorkProgramNew auditWork = new AuditWorkProgramNew();
				auditWork.hideElemetns();
				verticalPanelAuditWorkProg.add(auditWork);

			}
		});

		vpnl.add(verticalPanelAuditWorkProg);
		//vpnl.add(new AuditWorkProgramNew());
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

	private void saveActivityObjectives(ArrayList<ActivityObjective> activityObjectives) {
		rpcService.saveActivityObjectives(activityObjectives, new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Failed saving Acitivity Objectives:"+ caught.getLocalizedMessage());

			}

			@Override
			public void onSuccess(String result) {
				new DisplayAlert(result);

			}
		});

	}
	
	private void saveRiskObjectives(ArrayList<RiskObjective> riskObjectives) {
		rpcService.saveRiskObjectives(riskObjectives, new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Failed saving Acitivity Objectives:"+ caught.getLocalizedMessage());

			}

			@Override
			public void onSuccess(String result) {
				new DisplayAlert(result);

			}
		});
		
	}

}
