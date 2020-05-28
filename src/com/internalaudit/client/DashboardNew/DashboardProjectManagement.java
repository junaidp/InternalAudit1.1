package com.internalaudit.client.DashboardNew;

import java.util.ArrayList;
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
	DashboardListBoxes dashboardlistBox = null;

	public DashboardProjectManagement(DashboardListBoxes dashboardlistBox) {
		this.dashboardlistBox = dashboardlistBox;
		loadData();

		dashboardlistBox.getBtnSearch().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				loadData();

			}
		});
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

		hm.put("Process", listBoxProcess);
		hm.put("Domain", listBoxDomain);
		hm.put("Audit", listBoxAudit);
		hm.put("Unit", listBoxUnit);
		hm.put("Resource", listBoxResource);
		hm.put("Division", listBoxDivision);
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
				// ProjectManagementTimeLine timeLine = new
				// ProjectManagementTimeLine();
				// 2018 mew
				final JobsSchedulingView jobSchedulingView = new JobsSchedulingView();
				fetchJobs(jobSchedulingView);
				ScrollPanel paneljobviewscroll = new ScrollPanel();
				paneljobviewscroll.add(jobSchedulingView);
				paneljobviewscroll.setWidth("1200px");
				paneljobviewscroll.setHeight("300px");
				// end
				ProjectManagementActualHours actualHours = new ProjectManagementActualHours();
				ProjectManagementDate pmDate = new ProjectManagementDate();
				PortalInformationRequest portalInformation = new PortalInformationRequest(
						dashboard.getInformationRequests());
				PortalOutstandingCoaching portalOutstanding = new PortalOutstandingCoaching(dashboard.getTodo());
				Label l = new Label("ada");
				// add(portalOutstanding);
				// add(timeLine);
				// add(actualHours);

				DashboardListBoxes dashboardlistBox = new DashboardListBoxes();
				// HorizontalPanel paneljoblist = new HorizontalPanel();
				// HorizontalPanel mainPanel = new HorizontalPanel();
				// HorizontalPanel upperPanel = new HorizontalPanel();
				// mainPanel.setWidth("1200px");
				// VerticalPanel panelLeft = new VerticalPanel();
				// panelLeft.setWidth("500px");
				// VerticalPanel panelRight = new VerticalPanel();
				// panelRight.setWidth("650%");
				// VerticalPanel panelDate = new VerticalPanel();
				// panelDate.add(pmDate);
				dashboardlistBox.getHpnlDates().add(pmDate);
				// panelDate.getElement().getStyle().setPaddingLeft(30,
				// Unit.PX);
				// panelDate.getElement().getStyle().setPaddingTop(20, Unit.PX);
				//
				add(dashboardlistBox);

				add(paneljobviewscroll);
				// panelLeft.add(paneljobviewscroll);
				// add(actualHours);
				// Actual Hours removed by Moqeet as Rafey Said
				// upperPanel.add(dashboardlistBox);
				// upperPanel.add(panelDate);
				add(portalInformation);
				add(portalOutstanding);
				// mainPanel.add(panelLeft);
				// mainPanel.add(panelRight);
				// add(upperPanel);
				// add(paneljobviewscroll);
				// add(mainPanel);

			}
		});

	}

	private void fetchJobs(final JobsSchedulingView jobSchedulingView) {
		rpcService.fetchJobs(new AsyncCallback<ArrayList<JobCreation>>() {

			@Override
			public void onFailure(Throwable caught) {

				// logger.log(Level.INFO, "FAIL: fetchJobs .Inside Audit
				// AuditAreaspresenter");
				if (caught instanceof TimeOutException) {
					History.newItem("login");
				} else {
					System.out.println("FAIL: fetchJobs .Inside AuditAreaspresenter");
					Window.alert("FAIL: fetchJobs");// After FAIL ... write RPC
													// Name NOT Method Name..
				}

			}

			@Override
			public void onSuccess(ArrayList<JobCreation> result) {
				// new PopupsViewWhite(jobSchedulingView);
				// popup.setWidth("1000px");

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
					// setHandlers(auditScheduling);
					// if(result.get(i).isApproved()){
					// auditScheduling.getStartDate().setEnabled(false);
					// }

					/////////////////////
					if (auditSchedulingTemp == null || auditSchedulingTemp != auditScheduling) {
						auditSchedulingTemp = auditScheduling;

						// auditScheduling.getStartDate().addValueChangeHandler(new
						// ValueChangeHandler<Date>() {
						//
						//
						// @Override
						// public void onValueChange(ValueChangeEvent<Date>
						// event) {
						//
						// // getEndDate(auditScheduling, event);
						// }
						//
						//
						// });
						///////////////////////
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

		// Label startDate = new Label("Start Date");
		// headingPanel.add(startDate);
		// startDate.setWidth("90px");
		//
		// Label endDate = new Label("End Date");
		// headingPanel.add(endDate);
		// endDate.setWidth("90px");

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

}
