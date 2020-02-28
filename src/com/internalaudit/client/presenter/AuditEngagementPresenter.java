package com.internalaudit.client.presenter;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.internalaudit.client.InternalAuditServiceAsync;
import com.internalaudit.client.portal.JobStatusPortaLayout;
import com.internalaudit.client.view.DisplayAlert;
import com.internalaudit.client.view.JobData;
import com.internalaudit.client.view.AuditEngagement.KickoffView;
import com.internalaudit.shared.AuditEngagement;
import com.internalaudit.shared.Employee;
import com.internalaudit.shared.FieldWorkStatusDTO;
import com.internalaudit.shared.InternalAuditConstants;
import com.internalaudit.shared.JobStatusDTO;
import com.internalaudit.shared.PlanningStatusDTO;
import com.internalaudit.shared.TimeOutException;
import com.sencha.gxt.widget.core.client.ProgressBar;

public class AuditEngagementPresenter implements Presenter {

	private final InternalAuditServiceAsync rpcService;
	private final HandlerManager eventBus;
	private final Display display;
	private int jobId;
	private int auditEngId;
	private Employee loggedInUser;
	private Logger logger = Logger.getLogger("AuditEngagementPresenter");

	public interface Display {
		Widget asWidget();

		Object getHtmlErrorMessage = null;

		VerticalPanel getPage();

		Image getSyncBtn();
	}

	public AuditEngagementPresenter(InternalAuditServiceAsync rpcService, HandlerManager eventBus, Display view,
			Employee loggedInUser) {
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.display = view;
		this.loggedInUser = loggedInUser;

	}

	@Override
	public void go(HasWidgets container) {

		container.clear();
		container.add(display.asWidget());
		bind();

		showJobsAndStatus();

	}

	private void bind() {

		/*
		 * This method will sync Job creation and audit engagement tables i.e
		 * whenever a new record is added to job creation table, User is
		 * supposed to 'sync' Audit Engagement table.
		 * 
		 * In this way, we'll have Jobcreation and audit egagement synced
		 * 
		 */
		display.getSyncBtn().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent arg0) {

				rpcService.syncAuditEngagementWithCreatedJobs(loggedInUser.getEmployeeId(), new AsyncCallback<Void>() {

					@Override
					public void onFailure(Throwable caught) {

						System.out.println("Failed to sync tables : " + caught.getMessage());

						logger.log(Level.INFO,
								"FAIL: syncAuditEngagementWithCreatedJobs .Inside Audit AuditAreaspresenter");
						if (caught instanceof TimeOutException) {
							History.newItem("login");
						} else {
							System.out.println("FAIL: syncAuditEngagementWithCreatedJobs .Inside AuditAreaspresenter");
							Window.alert("FAIL: syncAuditEngagementWithCreatedJobs");// After
							// FAIL
							// ...
							// write
							// RPC
							// Name
							// NOT
							// Method
							// Name..
						}

					}

					@Override
					public void onSuccess(Void arg0) {

						new DisplayAlert("Refreshed");
						showJobsAndStatus();
					}

				});

			}
		});

	}

	private void showJobsAndStatus() {
		rpcService.fetchAllAuditEngagement(loggedInUser.getEmployeeId(),
				new AsyncCallback<ArrayList<AuditEngagement>>() {

					@Override
					public void onSuccess(ArrayList<AuditEngagement> allJobsAndStatus) {
						if (allJobsAndStatus != null) {
							displayLayout(allJobsAndStatus);
						}

						// Window.alert("success");
					}

					@Override
					public void onFailure(Throwable caught) {

						logger.log(Level.INFO, "FAIL: fetchAllAuditEngagement .Inside Audit AuditAreaspresenter");
						if (caught instanceof TimeOutException) {
							History.newItem("login");
						} else {
							System.out.println("FAIL: fetchAllAuditEngagement .Inside AuditAreaspresenter");
							Window.alert("FAIL: fetchAllAuditEngagement");// After

						}

						System.out.println("fail fetchAllAuditEngagement");
					}
				});

	}

	private void displayLayout(final ArrayList<AuditEngagement> allJobsAndStatus) {
		if (allJobsAndStatus.size() > 0) {
			FlexTable records = new FlexTable();
			jobId = 0;
			auditEngId = 0;
			records.addStyleName("statusTable");

			// set headings

			String headings[] = { "Sr#", " Job Name", "Planned Start Date", "Planned End Date", "status", "Planning ",
					"Field Work", "Reporting" };

			for (int i = 0; i < headings.length; i++) {

				Label heading = new Label();
				heading.setText(headings[i]);
				heading.addStyleName("labelDesignNormalSize");
				// heading.addStyleName("jobStatusHeading");

				records.setWidget(0, i, heading);
			}

			// set data in cells
			for (int i = 0; i < allJobsAndStatus.size(); i++) {
				if (i % 2 != 0) {
					records.getRowFormatter().addStyleName(i, "jobStatusRow");
				}
				final Label lblJobNo = new Label((allJobsAndStatus.get(i).getJobCreation().getJobCreationId() + "."));
				lblJobNo.setWidth("40px");

				final Label jobName = new Label(allJobsAndStatus.get(i).getJobCreation().getJobName());

				// jobName.addStyleName("statusJobName");
				// jobName.addStyleName("statusCell");
				// jobName.addStyleName("linkStyle");
				HorizontalPanel p = new HorizontalPanel();
				lblJobNo.getElement().getStyle().setPaddingLeft(10, Unit.PX);
				// p.add(lblJobNo);

				p.add(jobName);

				records.setWidget(i + 1, 0, lblJobNo);
				records.setWidget(i + 1, 1, p);
				jobName.setWidth("300px");
				jobId = allJobsAndStatus.get(i).getJobCreation().getJobCreationId(); // this

				auditEngId = allJobsAndStatus.get(i).getAuditEngId();
				final JobData jobData = new JobData();
				jobData.setJobId(jobId);
				jobData.setAuditEngId(auditEngId);
				jobData.setSelectedId(i);
				Label startDate = new Label(allJobsAndStatus.get(i).getJobCreation().getStartDate());
				startDate.addStyleName("statusJobDate");
				startDate.addStyleName("statusCell");
				startDate.setWidth("190px");
				///// ADDED////
				Label endDate = new Label(allJobsAndStatus.get(i).getJobCreation().getEndDate());
				endDate.addStyleName("statusJobDate");
				endDate.addStyleName("statusCell");
				endDate.setWidth("190px");
				////////////

				records.setWidget(i + 1, 2, startDate);

				Label status = new Label(allJobsAndStatus.get(i).getJobStatus());
				startDate.addStyleName("statusJobStatus");
				startDate.addStyleName("statusCell");

				records.setWidget(i + 1, 3, endDate);// Added
				records.setWidget(i + 1, 4, status);

				displayingProgress(records, i, jobData.getJobId(), allJobsAndStatus.get(i).getJobStatusDTO());

				// only 'not started' jobs can be kicked off

				Anchor kick = new Anchor("Kickoff");
				kick.setVisible(false);
				kick.addClickHandler(new ClickHandler() {

					@Override
					public void onClick(ClickEvent arg0) {

						display.getPage().clear();
						System.out.println(jobData.getSelectedId()
								+ " KickOff Clicked in Audit Engagement, opening kick off view");
						display.getPage().add(new KickoffView(rpcService, jobData.getJobId(), jobData.getAuditEngId(),
								loggedInUser, allJobsAndStatus.get(jobData.getSelectedId())));

					}
				});
				// records.setWidget(i + 1, 3, kick); movin to 255

				if ("Not Started".equals(allJobsAndStatus.get(i).getJobStatus())) {
					jobName.removeStyleName("point");
					kick.setVisible(true);
					records.setWidget(i + 1, 4, kick);
					kick.addStyleName("statusCell");
				}

				else {
					// job has started, make job name handler
					// I added this today
					// and the code which missing .. that belong to this class
					// or auditEngagementViewData?
					// data class
					jobName.addStyleName("point");
					jobName.addClickHandler(new ClickHandler() {

						@Override
						public void onClick(ClickEvent arg0) {
							display.getPage().clear();
							System.out.println(
									jobData.getJobId() + " Job Clicked in Audit Engagement, opening kick off view");
							display.getPage()
									.add(new KickoffView(rpcService, jobData.getJobId(), jobData.getAuditEngId(),
											loggedInUser, allJobsAndStatus.get(jobData.getSelectedId())));

						}
					});

				}

			}
			display.getPage().clear();
			display.getPage().add(records);
		}
	}

	// Here we are showing the progress, For now we jut show fix progress for
	// every one like 20%, 40% you can see below
	private void displayingProgress(FlexTable records, int i, final int jobId, JobStatusDTO jobStatusDTO) {

		ProgressBar progressPlanning = new ProgressBar();
		// progressPlanning.setValue((double) 5);

		// progressPlanning.updateProgress(.20, "20%");

		ProgressBar progressFieldWork = new ProgressBar();
		// progressFieldWork.updateProgress(.3, "40%");
		// progressFieldWork.setValue((double) 25);
		ProgressBar progressReporting = new ProgressBar();
		// progressFieldWork.addStyleName("w3-border w3-red");
		// progressReporting.updateProgress(.7, "70%");

		// progressFieldWork.getElement().getStyle().setBackgroundColor("yellow");
		// progressReporting.getElement().getStyle().setText("green");
		// progressReporting.getElement().getStyle().setBackgroundColor("green");
		// progressReporting.getElement().getStyle().setColor("green");
		// progressPlanning.getElement().getStyle().setBackgroundColor("red");
		// //progressReporting.setValue((double) 75);
		Anchor AnchorJobStatus = new Anchor("Job Status");
		AnchorJobStatus.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				fetchJobStatus(jobId);
			}
		});

		updateProgressBarsPercent(i, jobStatusDTO, progressPlanning, progressFieldWork, progressReporting);

		records.setWidget(i + 1, 5, progressPlanning);
		records.setWidget(i + 1, 6, progressFieldWork);
		records.setWidget(i + 1, 7, progressReporting);
		records.setWidget(i + 1, 8, AnchorJobStatus);
	}

	private void updateProgressBarsPercent(int i, JobStatusDTO jobStatusDTO, ProgressBar progressPlanning,
			ProgressBar progressFieldWork, ProgressBar progressReporting) {
		// boolean fieldWorkStatusCompleted = false;

		ArrayList<Boolean> fieldWorkStatusCompleted = new ArrayList<Boolean>();

		for (FieldWorkStatusDTO fieldWorkStatus : jobStatusDTO.getListFieldWorkStatus()) {
			// Window.alert(fieldWorkStatus.getAuditStepName());
			if (fieldWorkStatus.getStatus().equalsIgnoreCase(InternalAuditConstants.COMPLETED)) {
				fieldWorkStatusCompleted.add(true);

			}
		}

		double percentage;
		double percentage1;
		double listFieldWorkStatus = (jobStatusDTO.getListFieldWorkStatus().size());
		if (listFieldWorkStatus == 0) {
			percentage = 0;
			progressFieldWork.updateProgress(0, "0%");
		} else {
			double listCompleted = (fieldWorkStatusCompleted.size());
			percentage1 = (double) ((listCompleted / listFieldWorkStatus) * 100);
			double progress1 = percentage1;
			double progress = Math.round(progress1);
			percentage = Math.round(progress1);
			progressFieldWork.updateProgress(percentage / 100, percentage + "%");

		}

		// PLANNING
		ArrayList<Boolean> progressPlanningCompleted = new ArrayList<Boolean>();
		for (PlanningStatusDTO planningStatus : jobStatusDTO.getListPlanningStatus()) {
			if (planningStatus.getStatus().equalsIgnoreCase(InternalAuditConstants.COMPLETED)) {
				progressPlanningCompleted.add(true);
			}

			double percentage_planning;
			double percentage1_planning;
			double listPlanningStatus = (jobStatusDTO.getListPlanningStatus().size());
			if (listPlanningStatus == 0) {
				percentage_planning = 0;
				progressPlanning.updateProgress(0, "0%");
			} else {
				double listCompleted_planning = (progressPlanningCompleted.size());
				percentage1_planning = (double) ((listCompleted_planning / listPlanningStatus) * 100);
				double progress1_planning = percentage1_planning;
				double progress_planning = Math.round(progress1_planning);
				percentage_planning = Math.round(progress1_planning);
				progressPlanning.updateProgress(percentage_planning / 100, percentage_planning + "%");

			}

			/////// reporting

			String reportStatus = jobStatusDTO.getReportingStatus();

			if (reportStatus.equals(InternalAuditConstants.EXCEPTIONSTOSENT)) {
				progressReporting.updateProgress(.2, "20%");
			} else if (reportStatus.equals(InternalAuditConstants.AWAITINGCOMMENTS)) {
				progressReporting.updateProgress(.4, "40%");
			} else if (reportStatus.equals(InternalAuditConstants.COMMENTSRECEIVED)) {
				progressReporting.updateProgress(.6, "60%");
			} else if (reportStatus.equals(InternalAuditConstants.REPORTISSUED)) {
				progressReporting.updateProgress(.8, "80%");
			} else if (reportStatus.equals(InternalAuditConstants.FINALREPORTISSUED)) {
				progressReporting.updateProgress(1, "100%");
			} else
				progressReporting.updateProgress(0, "0%");

			progressPlanning.setWidth(105);
			progressFieldWork.setWidth(105);
			progressReporting.setWidth(105);
		}
	}

	private void fetchJobStatus(int jobId) {
		rpcService.fetchJobStatus(jobId, new AsyncCallback<JobStatusDTO>() {

			@Override
			public void onSuccess(JobStatusDTO jobStatus) {
				JobStatusPortaLayout jobStatusPortal = new JobStatusPortaLayout(jobStatus);
				com.sencha.gxt.widget.core.client.Window windowPortal = new com.sencha.gxt.widget.core.client.Window();
				windowPortal.setModal(true);
				windowPortal.setHeadingText("Job Status");
				windowPortal.setPixelSize(920, 650);
				ScrollPanel sp = new ScrollPanel();
				sp.setHeight("400px");
				sp.add(jobStatusPortal);
				windowPortal.add(sp);
				windowPortal.show();
				// Trying

				// Window.alert(jobStatus.getListFieldWorkStatus() + "");

				// Trying
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("fetchJobStatus failed :" + caught.getLocalizedMessage());

			}
		});

	}

	public Display getDisplay() {
		return display;
	}

}
