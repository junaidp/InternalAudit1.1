package com.internalaudit.client.view.Scheduling;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.internalaudit.client.InternalAuditServiceAsync;
import com.internalaudit.client.presenter.AuditListingPresenter;
import com.internalaudit.client.presenter.AuditSchedulingPresenter.Display;
import com.internalaudit.client.presenter.JobListingPresenter;
import com.internalaudit.client.presenter.Presenter;
import com.internalaudit.client.view.AuditListingView;
import com.internalaudit.client.view.JobListingView;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.container.AccordionLayoutContainer;
import com.sencha.gxt.widget.core.client.container.AccordionLayoutContainer.AccordionLayoutAppearance;

public class AuditSchedulingTabView extends Composite implements Display {

	private static AuditSchedulingViewUiBinder uiBinder = GWT.create(AuditSchedulingViewUiBinder.class);

	interface AuditSchedulingViewUiBinder extends UiBinder<Widget, AuditSchedulingTabView> {
	}

	@UiField
	VerticalPanel approvalContainer;
	@UiField
	Button approveSchedule;
	@UiField
	VerticalPanel mainPanel;

	HandlerManager eventBus;
	InternalAuditServiceAsync rpcService;

	public AuditSchedulingTabView(InternalAuditServiceAsync rpcService, HandlerManager eventBus) {
		this.eventBus = eventBus;
		this.rpcService = rpcService;
		initWidget(uiBinder.createAndBindUi(this));

		layout();
		setHandlers();
	}

	private void layout() {

		ContentPanel panel = new ContentPanel();
		panel.setWidth("1200px");
		panel.setHeadingText("Scheduling");
		panel.setBodyBorder(false);
		AccordionLayoutContainer con = new AccordionLayoutContainer();
		panel.add(con);
		con.setWidth(1200);

		AccordionLayoutAppearance appearance = GWT.<AccordionLayoutAppearance>create(AccordionLayoutAppearance.class);

		// Job Time Estimation

		ContentPanel cp = new ContentPanel(appearance);
		cp.setAnimCollapse(false);
		cp.setHeadingText("Job Time Estimation");
		cp.setAnimCollapse(true);
		cp.setExpanded(true);

		VerticalPanel vpnlJobTime = new VerticalPanel();
		// vpnlJobTime.setHeight("370px");
		vpnlJobTime.setWidth("1000px");

		JobListingView jobListingView = new JobListingView("jobTimeEst");
		Presenter presenter = new JobListingPresenter(rpcService, eventBus, jobListingView);
		if (presenter != null) {
			vpnlJobTime.add(jobListingView);
			presenter.go(vpnlJobTime);
		}

		cp.add(vpnlJobTime);
		con.add(cp);
		cp.expand();

		// Job Creation
		VerticalPanel vpnlRiskAssesment = new VerticalPanel();
		// vpnlRiskAssesment.setHeight("370px");
		JobListingView jobCreationView = new JobListingView("jobCreation");
		Presenter presenterCreation = new JobListingPresenter(rpcService, eventBus, jobCreationView);
		if (presenterCreation != null) {
			vpnlRiskAssesment.add(jobCreationView);
			presenterCreation.go(vpnlRiskAssesment);
		}

		cp = new ContentPanel(appearance);
		cp.setAnimCollapse(false);
		cp.setBodyStyleName("pad-text");
		cp.setHeadingText("Job Creation / Resource Assignment");

		cp.add(vpnlRiskAssesment);
		con.add(cp);

		// Scheduling
		VerticalPanel vpnlRScheduling = new VerticalPanel();
		// vpnlRScheduling.setHeight("370px");

		AuditListingView auditSchedulingView = new AuditListingView();
		Presenter presenterScheduling = new AuditListingPresenter(rpcService, eventBus, auditSchedulingView);
		if (presenterScheduling != null) {
			vpnlRScheduling.add(auditSchedulingView);
			presenterScheduling.go(vpnlRScheduling);
		}

		cp = new ContentPanel(appearance);
		cp.setAnimCollapse(false);
		cp.setBodyStyleName("pad-text");
		cp.setHeadingText("Scheduling");

		cp.add(vpnlRScheduling);
		con.add(cp);
		mainPanel.add(panel);

	}

	private void setHandlers() {

	}

	public VerticalPanel getApprovalContainer() {
		return approvalContainer;
	}

	public void setApprovalContainer(VerticalPanel approvalContainer) {
		this.approvalContainer = approvalContainer;
	}

	public Button getApproveSchedule() {
		return approveSchedule;
	}

	public void setApproveSchedule(Button approveSchedule) {
		this.approveSchedule = approveSchedule;
	}

}
