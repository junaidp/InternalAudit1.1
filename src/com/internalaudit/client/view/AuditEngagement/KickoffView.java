package com.internalaudit.client.view.AuditEngagement;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.internalaudit.client.InternalAuditServiceAsync;
import com.internalaudit.client.util.DataStorage;
import com.internalaudit.client.util.MyUtil;
import com.internalaudit.client.view.ButtonRound;
import com.internalaudit.client.view.DisplayAlert;
import com.internalaudit.client.widgets.AddIcon;
import com.internalaudit.shared.ActivityObjective;
import com.internalaudit.shared.AuditEngagement;
import com.internalaudit.shared.AuditProgramme;
import com.internalaudit.shared.Employee;
import com.internalaudit.shared.InternalAuditConstants;
import com.internalaudit.shared.JobCreation;
import com.internalaudit.shared.RiskObjective;
import com.internalaudit.shared.SubProcess;
import com.internalaudit.shared.SuggestedControls;
import com.internalaudit.shared.TimeOutException;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.container.AccordionLayoutContainer;
import com.sencha.gxt.widget.core.client.container.AccordionLayoutContainer.AccordionLayoutAppearance;

public class KickoffView extends Composite {

	private Logger logger = Logger.getLogger("KickoffView");

	private static KickoffViewUiBinder uiBinder = GWT.create(KickoffViewUiBinder.class);

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

	private Employee loggedInUser;

	private InternalAuditServiceAsync rpcService;
	int selectedJobId = 0;
	private int auditEngId = 0;
	private AuditEngagement selectedAuditEngagement;
	private SubProcess subProcess;

	public KickoffView(InternalAuditServiceAsync rpcService, int selectedjobId, int auditEngId, Employee loggedInUser,
			AuditEngagement auditEngagement) {
		this.selectedAuditEngagement = auditEngagement;

		this.rpcService = rpcService;
		this.loggedInUser = loggedInUser;
		this.selectedJobId = selectedjobId;
		initWidget(uiBinder.createAndBindUi(this));
		this.auditEngId = auditEngId;
		fetchCreatedJob(selectedJobId, loggedInUser);

		updateKickoffStatus(selectedjobId);
		// getRiskInfo( auditEngId );

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
				if (caught instanceof TimeOutException) {
					History.newItem("login");
				} else {
					System.out.println("FAIL: updateKickoffStatus .Inside AuditAreaspresenter");
					Window.alert("FAIL: updateKickoffStatus");// After FAIL ...
																// write RPC
																// Name NOT
																// Method Name..
				}

			}
		});

	}

	int jobid;

	private void getJobRelatedInfo(int selectedjobId) {

		rpcService.fetchCreatedJob(selectedjobId, new AsyncCallback<JobCreation>() {

			@Override
			public void onSuccess(JobCreation result) {

				if (result != null) {

					jobid = result.getJobCreationId();
					startDate.setText(result.getStartDate() == null ? "" : result.getStartDate());
					endDate.setText(result.getEndDate() == null ? "" : result.getEndDate());
					jobName.setText(result.getJobName() == null ? "" : result.getJobName());
					lblProcess.setText(result.getStrategic().getProcess() == null ? ""
							: result.getStrategic().getProcess().getProcessName());
					lblSubProcess.setText(result.getStrategic().getSubProcess() == null ? ""
							: result.getStrategic().getSubProcess().getSubProcessName());
					lblJobType.setText(result.getStrategic().getJobType() == null ? ""
							: result.getStrategic().getJobType().getJobTypeName());
					lblAuditableUnit.setText(result.getStrategic().getAuditableUnit() == null ? ""
							: result.getStrategic().getAuditableUnit());

					subProcess = result.getStrategic().getSubProcess();
				}

			}

			@Override
			public void onFailure(Throwable caught) {
				System.out.println("Rpc Failed: fetchCreatedJob in Kick off View");

				logger.log(Level.INFO, "FAIL: fetchCreatedJob .Inside Audit AuditAreaspresenter");
				if (caught instanceof TimeOutException) {
					History.newItem("login");
				} else {
					System.out.println("FAIL: fetchCreatedJob .Inside AuditAreaspresenter");
					Window.alert("FAIL: fetchCreatedJob");// After FAIL ...
															// write RPC Name
															// NOT Method Name..
				}

			}
		});

	}

	private void getExceptions() {

		// rpcService.fetchJobExceptions(selectedJobId, new
		// AsyncCallback<ArrayList<Exceptions>>() {
		//
		// @Override
		// public void onSuccess(ArrayList<Exceptions> ex) {
		//
		// for ( int i = 0; i < ex.size(); i++)
		// {
		// System.out.println( ex.get(i).getDetail());
		//
		// exPanel.add(new Label(ex.get(i).getDetail()));
		// exPanel.add(new Label(ex.get(i).getJobName()));
		// }
		//
		// }
		//
		// @Override
		// public void onFailure(Throwable arg0) {
		//
		// }
		// });

	}

	public void fetchCreatedJob(int selectedJobId, final Employee loggedInUser) {

		rpcService.fetchAuditEngagement(selectedJobId, new AsyncCallback<AuditEngagement>() {

			@Override
			public void onFailure(Throwable caught) {

				logger.log(Level.INFO, "FAIL: fetchAuditEngagement .Inside Audit AuditAreaspresenter");
				if (caught instanceof TimeOutException) {
					History.newItem("login");
				} else {
					System.out.println("FAIL: fetchAuditEngagement .Inside AuditAreaspresenter");
					Window.alert("FAIL: fetchAuditEngagement");// After FAIL ...
																// write RPC
																// Name NOT
																// Method Name..
				}

			}

			@Override
			public void onSuccess(AuditEngagement record) {
				showOptionsAccordian(record);

			}
		});
	}

	private void showOptionsAccordian(final AuditEngagement record) {
		ContentPanel panel = new ContentPanel();

		panel.setWidth("1200px");

		// panel.setHeadingText("Audit Planning");

		panel.setBodyBorder(false);
		AccordionLayoutContainer con = new AccordionLayoutContainer();
		panel.add(con);

		AccordionLayoutAppearance appearance = GWT.<AccordionLayoutAppearance>create(AccordionLayoutAppearance.class);

		///////////////////////////////////// AUDIT NOTIFICATION
		///////////////////////////////////// ///////////////////////////
		ContentPanel cp = new ContentPanel(appearance);
		cp.setAnimCollapse(false);
		cp.setHeadingText("Audit Notification");
		VerticalPanel vpnlIdentification = new VerticalPanel();
		vpnlIdentification.setHeight("400px");
		AuditNotificationViewNew auditNotificationViewNew = new AuditNotificationViewNew(record);
		// AuditNotificationView auditNotificationView = new
		// AuditNotificationView();
		// vpnlIdentification.add(auditNotificationView);
		// auditNotificationView.getAuditNotificationViewData().setData(auditNotificationView,
		// rpcService, selectedAuditEngagement);

		vpnlIdentification.add(auditNotificationViewNew);
		// if (!record.getSubject().isEmpty()) {
		auditNotificationViewNew.getAuditNotificationViewNewData().setData(auditNotificationViewNew, rpcService,
				selectedAuditEngagement);
		// }
		cp.add(vpnlIdentification);
		con.add(cp);

		///////////////////////////////////// Client Kickoff Meeting Objectives
		///////////////////////////////////// ///////////////////////////

		objectivesLayout(record, con, appearance);

		///////////////////////////////////// KEY RISKS
		///////////////////////////////////// ///////////////////////////

		keyRisksLayout(record, con, appearance);

		///////////////////////////////////// Objective Risk Control
		///////////////////////////////////// Matrix///////////////////////////

		objectiveRiskControlMatrix(record, con, appearance);

		//////////////////////////////////////// Audit Work
		//////////////////////////////////////// Programme//////////////////////////////

		auditWorkProgram(record, panel, con, appearance);

	}

	private void auditWorkProgram(final AuditEngagement record, ContentPanel panel, AccordionLayoutContainer con,
			AccordionLayoutAppearance appearance) {
		ContentPanel cp;
		cp = new ContentPanel(appearance);
		cp.setAnimCollapse(false);
		cp.setBodyStyleName("pad-text");
		cp.setHeadingText("Audit Work Programme");
		VerticalPanel vpnl = new VerticalPanel();
		vpnl.setHeight("600px");

		final VerticalPanel auditWorkNewContainer = new VerticalPanel();

		final AuditWorkProg auditWorkProg = new AuditWorkProg(rpcService, selectedJobId, loggedInUser,
				record.getEngagementDTO().getSelectedControls(), auditWorkNewContainer);
		vpnl.add(auditWorkProg);

		AddIcon btnAddAuditWork = new AddIcon();
		ButtonRound btnSaveAuditWork = new ButtonRound("Save");
		ButtonRound btnApproveAuditWork = new ButtonRound("Approve");
		btnSaveAuditWork.addStyleName("w3-margin");
		btnApproveAuditWork.addStyleName("w3-margin");

		// verticalPanelAuditWorkProg.add(new AuditWorkProgramNew());
		// verticalPanelAuditWorkProg.add(addpanelAuditworkprog);
		// 2018

		// library
		for (int i = 0; i < record.getEngagementDTO().getAuditProgrammeList().size(); i++) {
			final AuditWorkProgramNew auditWorkProgramNew = new AuditWorkProgramNew();
			auditWorkNewContainer.add(auditWorkProgramNew);
			vpnl.add(auditWorkNewContainer);
			auditWorkProgramNew.setData(record.getEngagementDTO().getAuditProgrammeList().get(i));

			auditWorkProgramNew.getBtnSelect().addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					auditWorkProg.addRow(auditWorkProgramNew);

				}
			});
		}

		// addclickhandler of button risk
		btnAddAuditWork.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				AuditWorkProgramNew auditWork = new AuditWorkProgramNew();
				auditWork.getLblReferenceData().setText(MyUtil.getRandom());
				auditWork.hideElemetns();
				auditWorkNewContainer.add(auditWork);

			}
		});

		btnSaveAuditWork.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				ArrayList<AuditProgramme> auditWorkProgrammes = new ArrayList<AuditProgramme>();
				for (int i = 0; i < auditWorkNewContainer.getWidgetCount(); i++) {
					AuditWorkProgramNew auditWorkProgramView = (AuditWorkProgramNew) auditWorkNewContainer.getWidget(i);
					AuditProgramme auditProgramme = new AuditProgramme();
					auditWorkProgramView.getData(auditProgramme);

					auditWorkProgrammes.add(auditProgramme);
				}
				saveAuditWorkProgramme(auditWorkProgrammes);

			}

		});
		HorizontalPanel panelAuditWorkBtn = new HorizontalPanel();
		panelAuditWorkBtn.addStyleName("w3-display-bottom");
		panelAuditWorkBtn.add(btnSaveAuditWork);
		panelAuditWorkBtn.add(btnApproveAuditWork);
		ScrollPanel scroll = new ScrollPanel();
		if (record.getEngagementDTO().getAuditProgrammeList().isEmpty()
				&& record.getEngagementDTO().getSelectedControls().isEmpty()) {
			// changedvpn1
			auditWorkNewContainer.clear();
		}
		scroll.setWidget(vpnl);
		scroll.setHeight("400px");

		// vpnl.add(new AuditWorkProgramNew());
		cp.add(scroll);
		con.add(cp);
		////////////////////////////////////////////// AUDIT STEP
		cp = new ContentPanel(appearance);
		cp.setAnimCollapse(false);
		cp.setBodyStyleName("pad-text");
		cp.setHeadingText("Audit Step");
		ScrollPanel sp = new ScrollPanel();
		sp.add(new AuditStepContainer(selectedJobId, rpcService, loggedInUser));
		sp.setHeight("450px");
		cp.add(sp);
		con.add(cp);
		statusPanel.add(panel);
	}

	private void objectiveRiskControlMatrix(final AuditEngagement record, AccordionLayoutContainer con,
			AccordionLayoutAppearance appearance) {
		ContentPanel cp;
		cp = new ContentPanel(appearance);
		cp.setAnimCollapse(false);

		cp.setBodyStyleName("pad-text");
		cp.setHeadingText("Objective Risk Control Matrix");

		ScrollPanel vps = new ScrollPanel();
		vps.setHeight("400px");
		VerticalPanel vpExistingControl = new VerticalPanel();
		final VerticalPanel vpExistingControlContainer = new VerticalPanel();
		final VerticalPanel userRiskControlContainer = new VerticalPanel();

		// user's
		final RisksView riskView = new RisksView(auditEngId, rpcService, loggedInUser,
				record.getEngagementDTO().getSelectedObjectiveRisks(), vpExistingControlContainer);
		userRiskControlContainer.add(riskView);

		// library's
		ArrayList<Integer> riskIds = new ArrayList<Integer>();
		for (int i = 0; i < record.getEngagementDTO().getSuggestedControlsList().size(); i++) {
			final RiskControlMatrixView riskControlMatrixView = new RiskControlMatrixView();
			vpExistingControlContainer.add(riskControlMatrixView);
			final RiskObjective riskObjective = record.getEngagementDTO().getSuggestedControlsList().get(i).getRiskId();
			boolean riskAdded = riskIds.contains(riskObjective.getRiskId());
			riskIds.add(riskObjective.getRiskId());
			riskControlMatrixView.setData(record.getEngagementDTO().getSuggestedControlsList().get(i), riskAdded);
			riskControlMatrixView.setRiskObjective(riskObjective);

			final DataStorage dataStorage = new DataStorage(); // We will use
																// the same
																// datastoage
																// class and set
																// the same
																// count and
																// value field
																// for every
																// other tabs
																// too.
			dataStorage.setCount(i);

			riskControlMatrixView.getBtnSelect().addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					riskView.addRow(riskControlMatrixView, riskObjective);

				}
			});
		}

		HorizontalPanel panelButtons = new HorizontalPanel();
		// panelButtons.getElement().getStyle().setMarginTop(10, Unit.PX);
		// a panelButtons.getElement().getStyle().setMarginLeft(1100, Unit.PX);
		panelButtons.addStyleName("w3-display-bottom w3-margin");
		// final VerticalPanel addPanelExistingControl = new VerticalPanel();
		// vpExistingControl.add(addPanelExistingControl);
		vpExistingControl.add(userRiskControlContainer);
		vpExistingControlContainer.add(new Label("---------Library----------"));
		vpExistingControl.add(vpExistingControlContainer);
		vps.add(vpExistingControl);

		if (record.getEngagementDTO().getSuggestedControlsList().isEmpty()
				&& record.getEngagementDTO().getSelectedObjectiveRisks().isEmpty()) {
			vpExistingControlContainer.clear();
		}

		// cp.add(panelAdd);
		cp.add(vps);

		con.add(cp);

		/*
		 * btnSaveControl.addClickHandler(new ClickHandler(){
		 * 
		 * @Override public void onClick(ClickEvent event) {
		 * ArrayList<SuggestedControls> suggestedControls = new
		 * ArrayList<SuggestedControls>(); for(int i=0; i<
		 * userRiskControlContainer.getWidgetCount() ; i++){
		 * RiskControlMatrixView existingControlView =
		 * (RiskControlMatrixView)userRiskControlContainer.getWidget(i);
		 * SuggestedControls suggestedControl = new SuggestedControls();
		 * existingControlView.getData(suggestedControl);
		 * suggestedControls.add(suggestedControl); }
		 * saveExistingControls(suggestedControls);
		 * 
		 * }
		 * 
		 * 
		 * });
		 */
	}

	private void keyRisksLayout(final AuditEngagement record, AccordionLayoutContainer con,
			AccordionLayoutAppearance appearance) {

		ContentPanel cp;
		cp = new ContentPanel(appearance);
		cp.setAnimCollapse(false);
		cp.setBodyStyleName("pad-text");
		cp.setHeadingText("Key Risks");
		ScrollPanel v1 = new ScrollPanel();
		v1.setHeight("400px");
		// v.setWidth("600px");
		VerticalPanel verticalPanelKeyRisks = new VerticalPanel();
		final VerticalPanel verticalPanelKeyRisksContainer = new VerticalPanel();
		final VerticalPanel usersRisksContainer = new VerticalPanel();
		ButtonRound btnSaveKeyRisk = new ButtonRound("Save");
		ButtonRound btnSubmitKeyRisk = new ButtonRound("Submit");
		AddIcon btnAdd = new AddIcon();

		verticalPanelKeyRisks.add(btnAdd);

		// User's LIBRARY
		for (int j = 0; j < record.getEngagementDTO().getSelectedObjectiveRisks().size(); j++) {
			final KeyRiskViewNew keyRiskView = new KeyRiskViewNew();

			keyRiskView.usersView();
			final RiskObjective objectiveRisk = record.getEngagementDTO().getSelectedObjectiveRisks().get(j);
			keyRiskView.setData(objectiveRisk);
			if (record.getEngagementDTO().getSelectedObjectiveRisks().get(j)
					.getStatus() == InternalAuditConstants.SUBMIT) {
				keyRiskView.disable();
				btnSaveKeyRisk.setVisible(false);
				btnSubmitKeyRisk.setVisible(false);
				btnAdd.setVisible(false);
			}
			keyRiskView.getDelete().addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					boolean confirmed = Window.confirm("Are you sure you want to delete this risk?");

					if (confirmed) {
						keyRiskView.removeFromParent();

						deleteObjectiveRisk(objectiveRisk.getRiskId());
					}
				}
			});

			usersRisksContainer.add(keyRiskView);
		}

		// ArrayList<Integer> objectiveIds = new ArrayList<Integer>();

		// library

		if (record.getEngagementDTO().getSelectedObjectiveRisks().size() <= 0 || record.getEngagementDTO()
				.getSelectedObjectiveRisks().get(0).getStatus() == InternalAuditConstants.SAVED) {
			verticalPanelKeyRisksContainer.add(new Label("-------Library------"));

			for (int i = 0; i < record.getEngagementDTO().getRiskObjectiveList().size(); i++) {
				final KeyRiskViewNew keyRiskView = new KeyRiskViewNew();
				verticalPanelKeyRisksContainer.add(keyRiskView);
				final RiskObjective riskObjective = record.getEngagementDTO().getRiskObjectiveList().get(i);
				keyRiskView.setData(riskObjective);

				final DataStorage dataStorage = new DataStorage(); // We will
																	// use the
																	// same
																	// datastoage
																	// class and
																	// set the
																	// same
																	// count and
																	// value
																	// field for
																	// every
																	// other
																	// tabs too.
				dataStorage.setCount(i);

				keyRiskView.getBtnSelect().addClickHandler(new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						final KeyRiskViewNew keyRiskSelectedView = new KeyRiskViewNew();
						keyRiskSelectedView.usersView();
						keyRiskView.getData(keyRiskSelectedView);
						usersRisksContainer.add(keyRiskSelectedView);

						keyRiskSelectedView.getDelete().addClickHandler(new ClickHandler() {

							@Override
							public void onClick(ClickEvent event) {
								keyRiskSelectedView.removeFromParent();
							}
						});

					}
				});
			}
		}

		btnAdd.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				final KeyRiskViewNew keyRiskViewNew = new KeyRiskViewNew();
				keyRiskViewNew.usersView();
				keyRiskViewNew.getLblRef().setText(MyUtil.getRandom());
				keyRiskViewNew.populateObjectives(record.getEngagementDTO().getSelectedActivityObjectives());
				usersRisksContainer.add(keyRiskViewNew);

				keyRiskViewNew.getDelete().addClickHandler(new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						keyRiskViewNew.removeFromParent();
					}
				});

			}
		});

		verticalPanelKeyRisks.add(usersRisksContainer);
		verticalPanelKeyRisks.add(verticalPanelKeyRisksContainer);
		HorizontalPanel hpnlButton = new HorizontalPanel();
		hpnlButton.add(btnSaveKeyRisk);
		hpnlButton.add(btnSubmitKeyRisk);
		verticalPanelKeyRisks.add(hpnlButton);
		// hpnlButton.getElement().getStyle().setMarginTop(30, Unit.PX);
		hpnlButton.addStyleName("w3-display-bottom w3-margin");

		if (record.getEngagementDTO().getRiskObjectiveList().isEmpty()
				&& record.getEngagementDTO().getSelectedObjectiveRisks().isEmpty()) {
			verticalPanelKeyRisksContainer.clear();//////// here
		}

		v1.add(verticalPanelKeyRisks);
		cp.add(v1);
		con.add(cp);

		btnSaveKeyRisk.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				ArrayList<RiskObjective> riskObjectives = new ArrayList<RiskObjective>();
				for (int i = 0; i < usersRisksContainer.getWidgetCount(); i++) {
					KeyRiskViewNew keyRiskView = (KeyRiskViewNew) usersRisksContainer.getWidget(i);
					RiskObjective riskObjective = new RiskObjective();
					keyRiskView.getData(riskObjective);
					riskObjectives.add(riskObjective);
				}
				saveRiskObjectives(riskObjectives, InternalAuditConstants.SAVED);

			}
		});

		btnSubmitKeyRisk.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				ArrayList<RiskObjective> riskObjectives = new ArrayList<RiskObjective>();
				for (int i = 0; i < usersRisksContainer.getWidgetCount(); i++) {
					KeyRiskViewNew keyRiskView = (KeyRiskViewNew) usersRisksContainer.getWidget(i);
					RiskObjective riskObjective = new RiskObjective();
					keyRiskView.getData(riskObjective);
					riskObjectives.add(riskObjective);
				}
				saveRiskObjectives(riskObjectives, InternalAuditConstants.SUBMIT);

			}
		});
	}

	protected void deleteObjectiveRisk(int riskId) {
		rpcService.deleteRiskObjective(riskId, jobid, new AsyncCallback<String>() {

			@Override
			public void onSuccess(String result) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("fail deleting risk " + caught.getLocalizedMessage());
			}
		});

	}

	private void objectivesLayout(final AuditEngagement record, AccordionLayoutContainer con,
			AccordionLayoutAppearance appearance) {
		ContentPanel cp;
		cp = new ContentPanel(appearance);
		cp.setAnimCollapse(false);
		cp.setBodyStyleName("pad-text");
		cp.setHeadingText("Objectives");

		final VerticalPanel vpnlActicityObjective = new VerticalPanel();
		final VerticalPanel vpnlActicityObjectiveContainer = new VerticalPanel();
		final VerticalPanel usersActivityContainer = new VerticalPanel();
		ButtonRound btnSaveActicityObjective = new ButtonRound("Save");
		ButtonRound btnSubmitActicityObjective = new ButtonRound("Submit");
		btnSaveActicityObjective.addStyleName("w3-display-bottom w3-margin");
		btnSaveActicityObjective.getElement().getStyle().setMarginLeft(600, Unit.PX);
		vpnlActicityObjective.setHeight("370px");

		AddIcon btnAddAcitivityObjective = new AddIcon();

		// User's LIBRARY
		for (int j = 0; j < record.getEngagementDTO().getSelectedActivityObjectives().size(); j++) {
			ActivityObjectiveViewNew activityObjectiveView = new ActivityObjectiveViewNew();
			activityObjectiveView.getBtnSelectActivity().setVisible(false);
			activityObjectiveView.setData(record.getEngagementDTO().getSelectedActivityObjectives().get(j));
			if (record.getEngagementDTO().getSelectedActivityObjectives().get(j)
					.getStatus() == InternalAuditConstants.SUBMIT) {
				activityObjectiveView.disable();
				btnSaveActicityObjective.setVisible(false);
				btnAddAcitivityObjective.setVisible(false);

				// 2019 april
				btnSubmitActicityObjective.setVisible(false);
			}
			// activityObjectiveView.getDelete().addClickHandler(new
			// ClickHandler() {
			//
			// @Override
			// public void onClick(ClickEvent event) {
			// Window.alert("cc");
			// boolean confirmed = Window.confirm("Are you sure you want to
			// delete this risk?");
			//
			// if (confirmed) {
			// activityObjectiveView.removeFromParent();
			//
			// }
			// }
			// });

			usersActivityContainer.add(activityObjectiveView);

		}

		// Our's LIBRARY
		Label lblLibHeading = new Label("Library");
		lblLibHeading.setVisible(false);
		if (record.getEngagementDTO().getSelectedActivityObjectives().size() <= 0 || record.getEngagementDTO()
				.getSelectedActivityObjectives().get(0).getStatus() == InternalAuditConstants.SAVED) {
			lblLibHeading.setVisible(true);
			for (int i = 0; i < record.getEngagementDTO().getActivityObjectiveList().size(); i++) {
				final ActivityObjectiveViewNew activityObjectiveView = new ActivityObjectiveViewNew();
				activityObjectiveView.setData(record.getEngagementDTO().getActivityObjectiveList().get(i));
				// activityObjectiveView.disable();
				vpnlActicityObjectiveContainer.add(activityObjectiveView);

				activityObjectiveView.getBtnSelectActivity().addClickHandler(new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						ActivityObjectiveViewNew activityObjectiveSelected = new ActivityObjectiveViewNew();
						activityObjectiveView.getData(activityObjectiveSelected);
						usersActivityContainer.add(activityObjectiveSelected);

						activityObjectiveSelected.getDelete().addClickHandler(new ClickHandler() {

							@Override
							public void onClick(ClickEvent event) {
								usersActivityContainer.clear();
							}
						});

					}
				});

			}
		} else {

		}

		btnAddAcitivityObjective.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				ActivityObjectiveViewNew act = new ActivityObjectiveViewNew();
				act.getlblReferenceNoData().setText(MyUtil.getRandom());
				act.getBtnSelectActivity().setVisible(false);
				usersActivityContainer.add(act);
				act.getDelete().addClickHandler(new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						// TODO Auto-generated method stub

						usersActivityContainer.clear();
					}
				});

			}
		});

		btnSaveActicityObjective.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				saveActivityObjective(usersActivityContainer, InternalAuditConstants.SAVED);

			}
		});

		btnSubmitActicityObjective.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				saveActivityObjective(usersActivityContainer, InternalAuditConstants.SUBMIT);

			}
		});

		vpnlActicityObjective.add(usersActivityContainer);
		vpnlActicityObjective.add(btnAddAcitivityObjective);
		vpnlActicityObjective.add(lblLibHeading);
		vpnlActicityObjective.add(vpnlActicityObjectiveContainer);
		HorizontalPanel hpnlButtons = new HorizontalPanel();
		hpnlButtons.setWidth("400px");
		hpnlButtons.add(btnSaveActicityObjective);
		hpnlButtons.add(btnSubmitActicityObjective);
		hpnlButtons.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		vpnlActicityObjective.add(hpnlButtons);
		ScrollPanel v = new ScrollPanel();
		v.setHeight("400px");
		v.add(vpnlActicityObjective);
		cp.add(v);
		con.add(cp);

	}

	private void saveActivityObjective(final VerticalPanel usersActivityContainer, int status) {
		ArrayList<ActivityObjective> activityObjectives = new ArrayList<ActivityObjective>();
		for (int i = 0; i < usersActivityContainer.getWidgetCount(); i++) {
			ActivityObjectiveViewNew activityObjectiveView = (ActivityObjectiveViewNew) usersActivityContainer
					.getWidget(i);
			ActivityObjective activityObjective = new ActivityObjective();
			activityObjectiveView.getData(activityObjective);
			activityObjective.setSubProcessId(subProcess);
			activityObjectives.add(activityObjective);
		}
		saveActivityObjectives(activityObjectives, status);
	}

	private void saveActivityObjectives(ArrayList<ActivityObjective> activityObjectives, int status) {

		rpcService.saveActivityObjectives(activityObjectives, jobid, status, new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Failed saving Acitivity Objectives:" + caught.getLocalizedMessage());

			}

			@Override
			public void onSuccess(String result) {
				new DisplayAlert(result);

			}
		});

	}

	private void saveRiskObjectives(ArrayList<RiskObjective> riskObjectives, int status) {
		rpcService.saveRiskObjectives(riskObjectives, jobid, status, new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Failed saving Acitivity Objectives:" + caught.getLocalizedMessage());

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
		rpcService.saveAuditWorkProgram(auditWorkProgrammes, selectedJobId, new AsyncCallback<String>() {

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
