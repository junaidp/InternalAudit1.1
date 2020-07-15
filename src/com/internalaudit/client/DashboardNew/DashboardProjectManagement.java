package com.internalaudit.client.DashboardNew;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.internalaudit.client.InternalAuditService;
import com.internalaudit.client.InternalAuditServiceAsync;
import com.internalaudit.client.view.LoadingPopup;
import com.internalaudit.client.view.Scheduling.JobsSchedulingView;
import com.internalaudit.client.view.Scheduling.TimeLineJobsView;
import com.internalaudit.client.widgets.AuditScheduling;
import com.internalaudit.shared.DashBoardNewDTO;
import com.internalaudit.shared.JobCreation;
import com.internalaudit.shared.TimeOutException;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;

public class DashboardProjectManagement extends VerticalLayoutContainer {
	InternalAuditServiceAsync rpcService = GWT.create(InternalAuditService.class);
	private DashboardListBoxes dashboardlistBox = null;
	private ProjectManagementDate pmDate = null;
	private Date startDate = null;
	private Date endDate = null;
	
	public DashboardProjectManagement() {
		
		 DashboardListBoxes dashboardlistBox1 = new DashboardListBoxes();
		 this.dashboardlistBox = dashboardlistBox1;
		 loadData();
		 clickHandler();  
		
	}


	private void loadData() {
		HashMap<String, String> hm = new HashMap<String, String>();
		String listBoxProcess = dashboardlistBox.getListBoxProcess().getSelectedValue();
		String listBoxDomain = dashboardlistBox.getListBoxDomain().getSelectedValue();
		String listBoxAudit = dashboardlistBox.getListBoxAudit().getSelectedValue();
		String listBoxUnit = dashboardlistBox.getListBoxUnit().getSelectedValue();
		String listBoxResource = dashboardlistBox.getListBoxResource().getSelectedValue();
		String listBoxDivision = dashboardlistBox.getListBoxDivision().getSelectedValue();
		String listBoxRisk = dashboardlistBox.getListBoxRiskLevel().getSelectedValue();
		String listBoxDepartment = dashboardlistBox.getListBoxDepartment().getSelectedValue();


		hm.put("Process", listBoxProcess);
		hm.put("Domain", listBoxDomain);
		hm.put("Audit", listBoxAudit);
		hm.put("Unit", listBoxUnit);
		hm.put("Resource", listBoxResource);
		hm.put("Division", listBoxDivision);
		hm.put("Department", listBoxDepartment);
		hm.put("Risk", listBoxRisk);

		InternalAuditServiceAsync rpcService = GWT.create(InternalAuditService.class);
		final LoadingPopup loadingpopup = new LoadingPopup();
		loadingpopup.display();
		rpcService.fetchDashboard(hm, new AsyncCallback<DashBoardNewDTO>() {

			@Override
			public void onFailure(Throwable arg0) {
				loadingpopup.remove();
				Window.alert("fetchDashboard fail in DashnoardProjectManagement");
			}

			@Override
			public void onSuccess(DashBoardNewDTO dashboard) {
				loadingpopup.remove();
				clear();
				final JobsSchedulingView jobSchedulingView = new JobsSchedulingView();
				ScrollPanel paneljobviewscroll = new ScrollPanel();
				paneljobviewscroll.add(jobSchedulingView);
				paneljobviewscroll.setWidth("1200px");
				paneljobviewscroll.setHeight("300px");
				// end
				ProjectManagementActualHours actualHours = new ProjectManagementActualHours();
				pmDate = new ProjectManagementDate();
				startDate =pmDate.getDpStart().getDatePicker().getValue();
				endDate = pmDate.getDpEnd().getDatePicker().getValue();
				fetchJobs(jobSchedulingView,startDate,endDate);
				
				dashboardlistBox.getHpnlDates().clear();
				PortalInformationRequest portalInformation = new PortalInformationRequest(
						dashboard.getInformationRequests());
				PortalOutstandingCoaching portalOutstanding = new PortalOutstandingCoaching(dashboard.getTodo());
				dashboardlistBox.getHpnlDates().add(pmDate);
				add(dashboardlistBox);
				add(paneljobviewscroll);
				add(portalInformation);
				add(portalOutstanding);

			}

		});

	}

	private void fetchJobs(final JobsSchedulingView jobSchedulingView, Date startDate, Date endDate) {
		rpcService.fetchJobsAgainstSelectedDates(startDate, endDate,new AsyncCallback<ArrayList<JobCreation>>() {

			@Override
			public void onFailure(Throwable caught) {

				// logger.log(Level.INFO, "FAIL: fetchJobs .Inside Audit
				// AuditAreaspresenter");
				if (caught instanceof TimeOutException) {
					History.newItem("login");
				} else {
					System.out.println("FAIL: fetchJobsAgainstSelectedDates .Inside AuditAreaspresenter");
					Window.alert("FAIL: fetchJobsAgainstSelectedDates");// After FAIL ... write RPC
													// Name NOT Method Name..
				}

			}

			@Override
			public void onSuccess(ArrayList<JobCreation> result) {

				addHeading(jobSchedulingView);
				AuditScheduling auditSchedulingTemp = null;

				for (int i = 0; i < result.size(); i++) {

					final AuditScheduling auditScheduling = new AuditScheduling();
					auditScheduling.getJobName().setWidth("320px");
					auditScheduling.getJobName().setText(result.get(i).getJobName());
					auditScheduling.setEstimatedWeeks(result.get(i).getEstimatedWeeks());

					auditScheduling.setJobId(result.get(i).getJobCreationId());
					auditScheduling.getEndDate().setText(result.get(i).getEndDate());///
					// auditScheduling.getStartDate().getTextBox().setText(result.get(i).getStartDate());
					auditScheduling.getEndDate().setVisible(false);
					auditScheduling.getStartDate().setVisible(false);
					jobSchedulingView.getListContainer().add(auditScheduling);
					auditScheduling.getTimeLineContainer().add(new TimeLineJobsView(result.get(i).getTimeLineDates()));
					if (auditSchedulingTemp == null || auditSchedulingTemp != auditScheduling) {
						auditSchedulingTemp = auditScheduling;

					}
				}
			}

		});
	}

	private void addHeading(JobsSchedulingView jobSchedulingView) {
		HorizontalPanel headingPanel = new HorizontalPanel();
		headingPanel.setWidth("1100px");
		Label empty = new Label("");
		empty.setWidth("100px");
		headingPanel.add(empty);

		Label jobName = new Label("Job Name");
		jobName.setWidth("160px");
		headingPanel.add(jobName);

		String[] names = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };

		FlexTable flexHeading = new FlexTable();
		flexHeading.setCellSpacing(0);
		flexHeading.setCellPadding(0);
		flexHeading.setBorderWidth(0);
		for (int i = 0; i < 12; i++) {
			flexHeading.getCellFormatter().setWidth(0, i, "66px");
			Label month = new Label(names[i]);
			month.setWordWrap(false);
			flexHeading.setWidget(0, i, month);
			flexHeading.addStyleName("list-heading");

			// Months with 5 weeks
			if (month.getText().equals("Mar") || month.getText().equals("May") || month.getText().equals("Jul")
					|| month.getText().equals("Oct")) {
				flexHeading.getCellFormatter().setWidth(0, i, "86px");
			}
			if (month.getText().equals("Dec")) {
				flexHeading.getCellFormatter().setWidth(0, i, "90px");
			}
		}
		headingPanel.addStyleName("list-heading");
		headingPanel.setWidth("428px");
		jobSchedulingView.getHeadingsPanel().add(headingPanel);
		jobSchedulingView.getHeadingsPanel().add(flexHeading);

	}

	private void clickHandler() {
		dashboardlistBox.getBtnSearch().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				//Window.alert(startDate+ "");
				//Window.alert(endDate+ "");
				loadData();

			}
		});
	}
}
