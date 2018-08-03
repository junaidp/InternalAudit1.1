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
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.internalaudit.client.InternalAuditServiceAsync;
import com.internalaudit.client.util.DataStorage;
import com.internalaudit.client.view.DisplayAlert;
import com.internalaudit.client.view.PopupsView;
import com.internalaudit.client.widgets.AddIcon;
import com.internalaudit.shared.ActivityObjective;
import com.internalaudit.shared.AuditEngagement;
import com.internalaudit.shared.AuditProgramme;
import com.internalaudit.shared.JobCreation;
import com.internalaudit.shared.ObjectiveJobRelation;
import com.internalaudit.shared.RiskObjective;
import com.internalaudit.shared.SubProcess;
import com.internalaudit.shared.SuggestedControls;
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
		// added button click just to check test the view
		
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
int jobid ;
	private void getJobRelatedInfo(int selectedjobId) {

		rpcService.fetchCreatedJob(selectedjobId, new AsyncCallback<JobCreation>() {

			@Override
			public void onSuccess(JobCreation result) {

				if(result!=null){
				
					jobid =result.getJobCreationId();
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

	private void showOptionsAccordian(final AuditEngagement record)
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
		cp.setHeadingText("Objectives");

		final VerticalPanel vpnlActicityObjective = new VerticalPanel();
		final VerticalPanel vpnlActicityObjectiveContainer = new VerticalPanel();
		final VerticalPanel usersActivityContainer = new VerticalPanel();
		Button btnSaveActicityObjective = new Button("Submit");
		btnSaveActicityObjective.addStyleName("w3-display-bottomright w3-margin");
		btnSaveActicityObjective.getElement().getStyle().setMarginLeft(600, Unit.PX);
		vpnlActicityObjective.setHeight("370px");
		//vpnlRiskAssesment.add(new EngagementPlanningView(rpcService, selectedJobId, auditEngId, loggedInUser, record));
		//ActivityObjectiveViewNew activityObjectiveView = new ActivityObjectiveViewNew();

		AddIcon btnAddAcitivityObjective = new AddIcon();

		/////
		Label lblUserHeading = new Label("User Library");
		//Heading ... /// User's
		
//		for(int j =0 ;j< record.getEngagementDTO().getSelectedActivityObjectives().size(); j++){
//			ActivityObjectiveViewNew activityObjectiveView = new ActivityObjectiveViewNew();
//			activityObjectiveView.setData(record.getEngagementDTO().getSelectedActivityObjectives().get(j));
			usersActivityContainer.add(lblUserHeading);
//			usersActivityContainer.add(activityObjectiveView);
			
			
		//}
		//usersActivityContainer.add(//////put record.getEngademtnDTO.activityobjecti.list.
		///
		Label lblLibHeading = new Label("Developer Library");
		//Heading ... Our's
		vpnlActicityObjectiveContainer.add(lblLibHeading);
		for(int i =0 ;i< record.getEngagementDTO().getActivityObjectiveList().size(); i++){
			final ActivityObjectiveViewNew activityObjectiveView = new ActivityObjectiveViewNew();
			activityObjectiveView.setData(record.getEngagementDTO().getActivityObjectiveList().get(i));
			
			vpnlActicityObjectiveContainer.add(activityObjectiveView);
			
			//new select btton handler
			activityObjectiveView.getBtnSelectActivity().addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					ActivityObjectiveViewNew activityObjectiveSelected = new ActivityObjectiveViewNew();
					activityObjectiveSelected.getBtnSelectActivity().setVisible(false);
					activityObjectiveSelected.getTxtAreaActivityObj().setText(activityObjectiveView.getTxtAreaActivityObj().getText());
					activityObjectiveSelected.getlblReferenceNoData().setText(activityObjectiveView.getlblReferenceNoData().getText());
					usersActivityContainer.add(activityObjectiveSelected);
					
				}
			});
		}
		


		btnAddAcitivityObjective.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				ActivityObjectiveViewNew act = new ActivityObjectiveViewNew();
				vpnlActicityObjectiveContainer.add(act);
			}
		});
		vpnlActicityObjective.add(usersActivityContainer);
		vpnlActicityObjective.add(vpnlActicityObjectiveContainer);
		vpnlActicityObjective.add(btnAddAcitivityObjective);
		vpnlActicityObjective.add(btnSaveActicityObjective);
		ScrollPanel v = new ScrollPanel();
		v.setHeight("400px");
		v.add(vpnlActicityObjective);
		cp.add(v);
		con.add(cp);

		btnSaveActicityObjective.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				ArrayList<ActivityObjective> activityObjectives = new ArrayList<ActivityObjective>();
				for(int i=0; i< usersActivityContainer.getWidgetCount() ; i++){
					ActivityObjectiveViewNew activityObjectiveView =  (ActivityObjectiveViewNew)usersActivityContainer.getWidget(i);
					ActivityObjective activityObjective = new ActivityObjective();
					activityObjectiveView.getData(activityObjective);
					activityObjective.setSubProcessId(subProcess);
					activityObjectives.add(activityObjective);
					//2018  doing new work
//					ObjectiveJobRelation objectiveJobRelation = new ObjectiveJobRelation();
//					objectiveJobRelation.setObjectiveId(activityObjective);
//					objectiveJobRelation.setJobCreationId(jobid);
					
					//ends here
					
					
				}
				saveActivityObjectives(activityObjectives);

			}

		});

		/////////////////////////////////////KEY RISKS ///////////////////////////

		cp = new ContentPanel(appearance);
		cp.setAnimCollapse(false);
		cp.setBodyStyleName("pad-text");
		cp.setHeadingText("Key Risks");
		ScrollPanel v1 = new ScrollPanel();
		v1.setHeight("400px");
		//v.setWidth("600px");
		 VerticalPanel verticalPanelKeyRisks = new VerticalPanel();
		final VerticalPanel verticalPanelKeyRisksContainer = new VerticalPanel();
		Button btnSaveKeyRisk = new Button("Save");
		btnSaveKeyRisk.addStyleName("w3-display-bottomright w3-margin");



		//verticalPanelKeyRisks.add(new KeyRiskViewNew());
		//2018

		ArrayList<Integer> objectiveIds = new ArrayList<Integer>();
		for(int i=0; i< record.getEngagementDTO().getRiskObjectiveList().size(); i++){
			KeyRiskViewNew keyRiskView = new KeyRiskViewNew();
			verticalPanelKeyRisksContainer.add(keyRiskView);
			final RiskObjective riskObjective = record.getEngagementDTO().getRiskObjectiveList().get(i);
			final ActivityObjective activityObjective = riskObjective.getObjectiveId();
			boolean objectiveAdded = objectiveIds.contains(activityObjective.getObjectiveId());
			objectiveIds.add(activityObjective.getObjectiveId());
			keyRiskView.setData(riskObjective, objectiveAdded);
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
					final KeyRiskViewNew keyRiskViewNew = new KeyRiskViewNew();
					keyRiskViewNew.setData(riskObjective, false);
					keyRiskViewNew.getBtnSave().setVisible(true);
					keyRiskViewNew.getBtnAddKeyRisk().setVisible(false);
					keyRiskViewNew.setActivityObjective(activityObjective);
					//keyRiskViewNew.hideElemetns();
					//verticalPanelKeyRisksContainer.insert(keyRiskViewNew, dataStorage.getCount()+2);
					final PopupsView pop = new PopupsView(keyRiskViewNew, "Add Key Risk");
					pop.getHpnlSPace().setWidth("1100px");
					
					keyRiskViewNew.getBtnSave().addClickHandler(new ClickHandler(){

						@Override
						public void onClick(ClickEvent event) {
							ArrayList<RiskObjective> riskObjectives = new ArrayList<RiskObjective>();
								RiskObjective riskObjective = new RiskObjective();
								keyRiskViewNew.getData(riskObjective);
								riskObjectives.add(riskObjective);
							
							saveRiskObjectives(riskObjectives);
							pop.getPopup().removeFromParent();
						}
					});

				}
			});
		}


		//	v.add(new RisksView(auditEngId, rpcService, loggedInUser.getEmployeeId()));
		
		
		verticalPanelKeyRisks.add(verticalPanelKeyRisksContainer);
		verticalPanelKeyRisks.add(btnSaveKeyRisk);

		v1.add(verticalPanelKeyRisks);
		cp.add(v1);
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
		 VerticalPanel vpExistingControl = new VerticalPanel();
		 final VerticalPanel vpExistingControlContainer = new VerticalPanel();
		Button btnSaveControl = new Button("Save");
		btnSaveControl.addStyleName(" w3-margin");
		Button btnApproveControl = new Button("Approce");
		btnApproveControl.addStyleName("w3-margin");

		
		//	v.add(new RisksView(auditEngId, rpcService, loggedInUser.getEmployeeId()));


		//vpExistingControl.add( new ExistingControlViewNew());
		//2018
		/*ArrayList<Integer> riskIds = new ArrayList<Integer>();
		for(int i=0; i< record.getEngagementDTO().getSuggestedControlsList().size(); i++){
			ExistingControlViewNew existingControlViewNew = new ExistingControlViewNew();
			vpExistingControl.add(existingControlViewNew);
			boolean riskAdded = riskIds.contains(record.getEngagementDTO().getSuggestedControlsList().get(i).getRiskId().getRiskId());
			riskIds.add(record.getEngagementDTO().getSuggestedControlsList().get(i).getRiskId().getRiskId());
			existingControlViewNew.setData(record.getEngagementDTO().getSuggestedControlsList().get(i), riskAdded);
		}*/
		ArrayList<Integer> riskIds = new ArrayList<Integer>();
		for(int i=0; i< record.getEngagementDTO().getSuggestedControlsList().size(); i++){
			ExistingControlViewNew existingControlViewNew = new ExistingControlViewNew();
			vpExistingControlContainer.add(existingControlViewNew);
			final RiskObjective riskObjective = record.getEngagementDTO().getSuggestedControlsList().get(i).getRiskId();
			boolean riskAdded = riskIds.contains(riskObjective.getRiskId());
			riskIds.add(riskObjective.getRiskId());
			existingControlViewNew.setData(record.getEngagementDTO().getSuggestedControlsList().get(i), riskAdded);
			existingControlViewNew.setRiskObjective(riskObjective);

			final DataStorage dataStorage = new DataStorage(); // We will use the same datastoage class and set the same count and value field for every other tabs too.
			dataStorage.setCount(i);


			if(riskAdded){ // removing the add icon if more risks are here, so add icon will only display below the last item
				ExistingControlViewNew existingControlPrevious = (ExistingControlViewNew)vpExistingControlContainer.getWidget(i-1);
				existingControlPrevious.getBtnAdd().setVisible(false);
			}
			//add handler
			existingControlViewNew.getBtnAdd().addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					ExistingControlViewNew existingControlViewNew = new ExistingControlViewNew();
					existingControlViewNew.setRiskObjective(riskObjective);
					existingControlViewNew.hideElemetns();
					//vpExistingControlContainer.insert(existingControlViewNew, dataStorage.getCount()+2);
					/*
					final PopupsView pop = new PopupsView(existingControlViewNew, "Add Existing Control");
					
					existingControlViewNew.getBtnSave().addClickHandler(new ClickHandler(){

						@Override
						public void onClick(ClickEvent event) {
							ArrayList<RiskObjective> riskObjectives = new ArrayList<RiskObjective>();
								RiskObjective riskObjective = new RiskObjective();
								keyRiskViewNew.getData(riskObjective);
								riskObjectives.add(riskObjective);
							
							saveRiskObjectives(riskObjectives);
							pop.getPopup().removeFromParent();
						}
					});
	*/
				}
			});
		}

		HorizontalPanel panelButtons = new HorizontalPanel();
		panelButtons.getElement().getStyle().setMarginTop(10, Unit.PX);
		panelButtons.getElement().getStyle().setMarginLeft(1100, Unit.PX);
		//final VerticalPanel addPanelExistingControl = new VerticalPanel();
		//vpExistingControl.add(addPanelExistingControl);
		panelButtons.add(btnSaveControl);
		panelButtons.add(btnApproveControl);
		vpExistingControl.add(vpExistingControlContainer);
		vpExistingControl.add(panelButtons);
		vps.add(vpExistingControl);

		//cp.add(panelAdd);
		cp.add(vps);

		con.add(cp);

		btnSaveControl.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				ArrayList<SuggestedControls> suggestedControls = new ArrayList<SuggestedControls>();
				for(int i=0; i< vpExistingControlContainer.getWidgetCount() ; i++){
					ExistingControlViewNew existingControlView =  (ExistingControlViewNew)vpExistingControlContainer.getWidget(i);
					SuggestedControls suggestedControl = new SuggestedControls();
					existingControlView.getData(suggestedControl);
					suggestedControls.add(suggestedControl);
				}
				saveExistingControls(suggestedControls);

			}

			
		});

		////////////////////////////////////////Audit Work Programme//////////////////////////////


		cp = new ContentPanel(appearance);
		cp.setAnimCollapse(false);
		cp.setBodyStyleName("pad-text");
		cp.setHeadingText("Audit Work Programme");
		VerticalPanel vpnl = new VerticalPanel();
		vpnl.setHeight("600px");
		
		final AuditWorkProg auditWorkProg = new AuditWorkProg(rpcService, selectedJobId, loggedInUser.getEmployeeId(), record.getEngagementDTO().getSuggestedControlsList());
		vpnl.add(auditWorkProg);
		
		final VerticalPanel auditWorkNewContainer = new VerticalPanel();
		//final VerticalPanel addpanelAuditworkprog = new VerticalPanel();

		AddIcon btnAddAuditWork = new AddIcon();
		Button btnSaveAuditWork = new Button("Save");
		Button btnApproveAuditWork  = new Button("Approve");
		btnSaveAuditWork.addStyleName("w3-margin");
		btnApproveAuditWork.addStyleName("w3-margin");

		//verticalPanelAuditWorkProg.add(new AuditWorkProgramNew());
		//verticalPanelAuditWorkProg.add(addpanelAuditworkprog);
		//2018

		
		for(int i=0; i< record.getEngagementDTO().getAuditProgrammeList().size(); i++){
			final AuditWorkProgramNew auditWorkProgramNew = new AuditWorkProgramNew();
			auditWorkNewContainer.add(auditWorkProgramNew);
			vpnl.add(auditWorkNewContainer);
			auditWorkProgramNew.setData(record.getEngagementDTO().getAuditProgrammeList().get(i));
			
			auditWorkProgramNew.getBtnSelect().addClickHandler(new ClickHandler(){

				@Override
				public void onClick(ClickEvent event) {
					auditWorkProg.addRow(auditWorkProgramNew);
					
				}});
		}



		//addclickhandler of button risk
		btnAddAuditWork.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				AuditWorkProgramNew auditWork = new AuditWorkProgramNew();
				auditWork.hideElemetns();
				auditWorkNewContainer.add(auditWork);

			}
		});
		
		btnSaveAuditWork.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				ArrayList<AuditProgramme> auditWorkProgrammes = new ArrayList<AuditProgramme>();
				for(int i=0; i< auditWorkNewContainer.getWidgetCount() ; i++){
					AuditWorkProgramNew auditWorkProgramView =  (AuditWorkProgramNew)auditWorkNewContainer.getWidget(i);
					AuditProgramme auditProgramme = new AuditProgramme();
					auditWorkProgramView.getData(auditProgramme);
					
					auditWorkProgrammes.add(auditProgramme);
				}
				saveAuditWorkProgramme(auditWorkProgrammes);

			}

			

		});
		HorizontalPanel panelAuditWorkBtn = new HorizontalPanel();
		panelAuditWorkBtn.addStyleName("w3-display-bottomright");
		panelAuditWorkBtn.add(btnSaveAuditWork);
		panelAuditWorkBtn.add(btnApproveAuditWork);
	//	verticalPanelAuditWorkProg.add(btnAddAuditWork);
	//	verticalPanelAuditWorkProg.add(panelAuditWorkBtn);
//		
//		vpnl.add(verticalPanelAuditWorkProgContainer);
//		vpnl.add(verticalPanelAuditWorkProg);
		ScrollPanel scroll = new ScrollPanel();
		scroll.setWidget(vpnl);
		scroll.setHeight("400px");
	
		//vpnl.add(new AuditWorkProgramNew());
		cp.add(scroll);
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
		sp.setHeight("450px");
		cp.add(sp);
		con.add(cp);
		statusPanel.add(panel);
		}

	

	private void saveActivityObjectives(ArrayList<ActivityObjective> activityObjectives) {
	
		rpcService.saveActivityObjectives(activityObjectives, jobid , new AsyncCallback<String>() {

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

	private void saveExistingControls(ArrayList<SuggestedControls> suggestedControls) {
		 rpcService.saveExistingControls(suggestedControls, new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(String result) {
			new DisplayAlert(result);
			}
		});
		
	}
	
	private void saveAuditWorkProgramme(ArrayList<AuditProgramme> auditWorkProgrammes) {
		rpcService.saveAuditWorkProgram(auditWorkProgrammes, selectedJobId,  new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("fail saving audit work");
				
			}

			@Override
			public void onSuccess(String result) {
				new DisplayAlert(result);
				
			}
		});
		
	}
}
