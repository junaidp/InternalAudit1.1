package com.internalaudit.client;

import com.internalaudit.client.event.AuditEngagementEvent;
import com.internalaudit.client.event.AuditEngagementEventHandler;
import com.internalaudit.client.event.AuditSchedulingEvent;
import com.internalaudit.client.event.AuditSchedulingEventHandler;
import com.internalaudit.client.event.CreateUserEvent;
import com.internalaudit.client.event.CreateUserEventHandler;
import com.internalaudit.client.event.DashBoardAtStartupEvent;
import com.internalaudit.client.event.DashBoardAtStartupEventHandler;
import com.internalaudit.client.event.DashBoardEvent;
import com.internalaudit.client.event.DashBoardEventHandler;
import com.internalaudit.client.event.JobCreationEvent;
import com.internalaudit.client.event.JobCreationEventHandler;
import com.internalaudit.client.event.JobListingEvent;
import com.internalaudit.client.event.JobListingEventHandler;
import com.internalaudit.client.event.JobTimeEstimationEvent;
import com.internalaudit.client.event.JobTimeEstimationEventHandler;
import com.internalaudit.client.event.MainEvent;
import com.internalaudit.client.event.MainEventHandler;
import com.internalaudit.client.event.ReportingEvent;
import com.internalaudit.client.event.ReportingEventHandler;
import com.internalaudit.client.event.ReportsEvent;
import com.internalaudit.client.event.ReportsEventHandler;
import com.internalaudit.client.presenter.AuditAreasPresenter;
import com.internalaudit.client.presenter.AuditEngagementPresenter;
import com.internalaudit.client.presenter.AuditListingPresenter;
import com.internalaudit.client.presenter.AuditSchedulingPresenter;
import com.internalaudit.client.presenter.CompanyInductionFormPresenter;
import com.internalaudit.client.presenter.DashBoardNewPresenter;
import com.internalaudit.client.presenter.DashBoardPresenter;
import com.internalaudit.client.presenter.HeaderPresenter;
import com.internalaudit.client.presenter.JobCreationPresenter;
import com.internalaudit.client.presenter.JobListingPresenter;
import com.internalaudit.client.presenter.JobTimeEstimationPresenter;
import com.internalaudit.client.presenter.LoginPresenter;
import com.internalaudit.client.presenter.MainPresenter;
import com.internalaudit.client.presenter.Presenter;
import com.internalaudit.client.presenter.ReportingPresenter;
import com.internalaudit.client.presenter.ReportsPresenter;
import com.internalaudit.client.presenter.RequestUserNameFormPresenter;
import com.internalaudit.client.presenter.UserInductionFormPresenter;
import com.internalaudit.client.view.AuditListingView;
import com.internalaudit.client.view.CompanyInductionFormView;
import com.internalaudit.client.view.DashBoardView;
import com.internalaudit.client.view.HeaderView;
import com.internalaudit.client.view.JobListingView;
import com.internalaudit.client.view.LoginUi;
import com.internalaudit.client.view.MainView;
import com.internalaudit.client.view.RequestUserNameFormView;
import com.internalaudit.client.view.UserInductionFormView;
import com.internalaudit.client.view.AuditEngagement.AuditEngagementView;
import com.internalaudit.client.view.Reporting.ReportingView;
import com.internalaudit.client.view.Reporting.ReportsView;
import com.internalaudit.client.view.Reporting.ReportAuditPlanning;
import com.internalaudit.client.view.Scheduling.AuditAreasView;
import com.internalaudit.client.view.Scheduling.AuditSchedulingView;
import com.internalaudit.client.view.Scheduling.JobCreationView;
import com.internalaudit.client.view.Scheduling.JobTimeEstimationView;
import com.internalaudit.client.view.dashboard.DashBoardDesignerView;
import com.internalaudit.client.view.dashboard.DashboardNewView;
import com.internalaudit.shared.StrategicDTO;
import com.internalaudit.shared.User;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class AppController implements Presenter, ValueChangeHandler<String> {
		private final HandlerManager eventBus;
	
	private final InternalAuditServiceAsync rpcService; 
	private HasWidgets container;
	private HeaderView header;
	private User loggedInUser;
	private VerticalPanel centerPanel;
	private HasWidgets mainContainer;
	Presenter presenter = null;
	private int jobId;
	private String callingFrom;
	private StrategicDTO strategicDTO;
	private int selectedYear;
	
	public AppController(InternalAuditServiceAsync rpcService, HandlerManager eventBus) {
		this.eventBus = eventBus;
		this.rpcService = rpcService;

		bind();
	}


	private void bind() {
		History.addValueChangeHandler(this);
		
		eventBus.addHandler(MainEvent.TYPE,
				new MainEventHandler() {
			public void onMain(MainEvent event) {
				loggedInUser = event.getLoggedInUser();
				selectedYear = event.getSelectedYear();
				
				doMain();
			}
			private void doMain() {
				
				History.newItem("main");

			}
		}); 
		
		eventBus.addHandler(CreateUserEvent.TYPE,
				new CreateUserEventHandler() {
			public void onCreateUser(CreateUserEvent event) {
				loggedInUser = event.getUser();
				
				doCreateUser();
			}
			private void doCreateUser() {
				
				History.newItem("createUser");

			}
		}); 

		eventBus.addHandler(DashBoardEvent.TYPE,
				new DashBoardEventHandler() {
			public void onDashBoard(DashBoardEvent event) {
				centerPanel = event.getCenterPanel();
				doDashBoard();
			}
			private void doDashBoard() {
				
				History.newItem("Dashboard");

			}
		});  
		
		eventBus.addHandler(DashBoardAtStartupEvent.TYPE,
				new DashBoardAtStartupEventHandler() {
			public void onDashBoardAtStartup(DashBoardAtStartupEvent event) {
				loggedInUser = event.getUser();
				History.newItem("DashboardStartup");
			}
			
		});  
		
		
		
		eventBus.addHandler(JobTimeEstimationEvent.TYPE,
				new JobTimeEstimationEventHandler() {
			public void onJobTimeEstimation(JobTimeEstimationEvent event) {
				strategicDTO = event.getStrategicDTO();
				doJobTimeEstimationEvent();
			}
			private void doJobTimeEstimationEvent() {
				
				History.newItem("jobTimeEstimation");

			}
		});  
		
		eventBus.addHandler(JobListingEvent.TYPE,	new JobListingEventHandler() {
			public void onJobListing(JobListingEvent event) {
				callingFrom = event.getFrom();
				doJobListingEvent();
			}
			
			private void doJobListingEvent() {
				
				History.newItem("jobListing");

			}
		});  
		
		eventBus.addHandler(JobCreationEvent.TYPE,
				new JobCreationEventHandler() {
			public void onJobCreation(JobCreationEvent event) {
				strategicDTO = event.getStrategicDTO();
				doJobCreation();
			}
		
			public void doJobCreation() {
				
				History.newItem("jobCreation");
				
			}
		});  
		
		eventBus.addHandler(AuditSchedulingEvent.TYPE,
				new AuditSchedulingEventHandler() {
			public void onAuditScheduling(AuditSchedulingEvent event) {
				centerPanel = event.getCenterPanel();
				doAuditSchedulingEvent();
			}
			private void doAuditSchedulingEvent() {
				
				History.newItem("auditScheduling");

			}
		});  
		
		eventBus.addHandler(AuditEngagementEvent.TYPE,
				new AuditEngagementEventHandler() {
			public void onAuditEngagement(AuditEngagementEvent event) {
				centerPanel = event.getCenterPanel();
				doAuditEngagementEvent();
			}
			private void doAuditEngagementEvent() {
				
				History.newItem("auditEngagement");

			}
		});  
		
		eventBus.addHandler(ReportingEvent.TYPE,
				new ReportingEventHandler() {
			public void onReporting(ReportingEvent event) {
				centerPanel = event.getCenterPanel();
				doReportingEvent();
			}
			private void doReportingEvent() {
				
				History.newItem("Reporting");

			}
		});  
		
		eventBus.addHandler(ReportsEvent.TYPE,
				new ReportsEventHandler() {
			public void onReports(ReportsEvent event) {
				centerPanel = event.getCenterPanel();
				doReportsEvent();
			}
			private void doReportsEvent() {
				
				History.newItem("Reports");

			}
		});  
	}

	public void go(final HasWidgets container) {
		this.container = container;
		this.mainContainer = container;
		
		if (centerPanel == null) {
			centerPanel = new VerticalPanel();
		}
		
		
//		if (header == null) {
//			header = new HeaderView();
//			new HeaderPresenter(rpcService, eventBus, header).go(RootPanel
//					.get("headerContainer"));
//		}
		if ("".equals(History.getToken())) {
			History.newItem("login");
		}
		else {
			History.fireCurrentHistoryState();
		}
	}

	public void onValueChange(ValueChangeEvent<String> event) {
		String token = event.getValue();

		if (token != null) {
			presenter = null;

			if (token.equals("login")) {
				presenter = new LoginPresenter(rpcService, eventBus, new LoginUi());
				if (presenter != null) {
					this.container = mainContainer;
					presenter.go(container);
				}
			}
			if (token.equals("main")) {
				presenter = new MainPresenter(rpcService, eventBus, selectedYear, loggedInUser, new MainView(loggedInUser));
				if (presenter != null) {
					this.container = mainContainer;
					presenter.go(container);
				}
			}
			
			if (token.equals("auditAreas")) {
				AuditAreasView auditAreasView = new AuditAreasView();
				presenter = new AuditAreasPresenter(rpcService, eventBus, auditAreasView);
				setContainer(centerPanel);
				if (presenter != null) {
					presenter.go(container);
				}
			}
			
			if (token.equals("auditScheduling")) {
				AuditSchedulingView auditSchedulingView = new AuditSchedulingView();
				presenter = new AuditSchedulingPresenter(rpcService, eventBus, auditSchedulingView, loggedInUser);
				
				setContainer(centerPanel);
//				this.container = mainContainer;

				if (presenter != null) {
					presenter.go(container);
				}
			}
			
			if (token.equals("jobCreation")) {
				presenter = new JobCreationPresenter(rpcService, eventBus, new JobCreationView(strategicDTO), strategicDTO);
//				this.container = mainContainer;
				setContainer(centerPanel);
				if (presenter != null) {
					presenter.go(container);
				}
			}
			
			if (token.equals("auditListing")) {
				AuditListingView auditSchedulingView = new AuditListingView();
				presenter = new AuditListingPresenter(rpcService, eventBus, auditSchedulingView);
				setContainer(centerPanel);
				if (presenter != null) {
					presenter.go(container);
				}
			}
			
			if (token.equals("jobTimeEstimation")) {
				presenter = new JobTimeEstimationPresenter(rpcService, eventBus, new JobTimeEstimationView(strategicDTO));
//				this.container = mainContainer;
				setContainer(centerPanel);
				if (presenter != null) {
					presenter.go(container);
				}
			}
			
			if (token.equals("jobListing")) {
				presenter = new JobListingPresenter(rpcService, eventBus, new JobListingView(callingFrom));
				if (presenter != null) {
					setContainer(centerPanel);
//					this.container = mainContainer;
					presenter.go(container);
				}
			}

			if (token.equals("auditEngagement")) {
				presenter = new AuditEngagementPresenter(rpcService, eventBus, new AuditEngagementView(), loggedInUser);
				setContainer(centerPanel);
				if (presenter != null) {
					presenter.go(container);
				}
			}	
			
			else if (token.equals("Reporting")) {
				presenter = new ReportingPresenter(rpcService, eventBus, loggedInUser.getEmployeeId(), new ReportingView(loggedInUser.getEmployeeId().getFromInternalAuditDept()));
				setContainer(centerPanel);
				if (presenter != null) {
					presenter.go(container);
				}
			}
			
			else if (token.equals("Reports")) {
				presenter = new ReportsPresenter(rpcService, eventBus, loggedInUser.getEmployeeId(), new ReportsView(loggedInUser.getEmployeeId().getFromInternalAuditDept()));
				setContainer(centerPanel);
				if (presenter != null) {
					presenter.go(container);
				}
			}
//			else if (token.equals("Dashboard")) {
//				presenter = new DashBoardPresenter(rpcService, eventBus, loggedInUser, new DashBoardView(false));
//				setContainer(centerPanel);
//				if (presenter != null) {
//					presenter.go(container);
//				}
//			}	
			
			else if (token.equals("Dashboard")) {
				presenter = new DashBoardNewPresenter(rpcService, eventBus, loggedInUser, new DashBoardDesignerView(false));
				setContainer(centerPanel);
				if (presenter != null) {
					presenter.go(container);
				}
			}	
			
			else if (token.equals("createUser")) {
				presenter = new UserInductionFormPresenter(rpcService, eventBus, loggedInUser, new UserInductionFormView(loggedInUser));
				if (presenter != null) {
					this.container = mainContainer;
					presenter.go(container);
				}
			}	
			
			else if (token.equals("requestUserName")) {
				presenter = new RequestUserNameFormPresenter(rpcService, eventBus, new RequestUserNameFormView());
				if (presenter != null) {
					this.container = mainContainer;
					presenter.go(container);
				}
			}	
			
			else if (token.equals("createCompany")) {
				presenter = new CompanyInductionFormPresenter(rpcService, eventBus, new CompanyInductionFormView());
				if (presenter != null) {
					this.container = mainContainer;
					presenter.go(container);
				}
			}	
			
			if (token.equals("DashboardStartup")) {
				presenter = new DashBoardNewPresenter(rpcService, eventBus, loggedInUser, new DashBoardDesignerView(true));
				if (presenter != null) {
					this.container = mainContainer;
					presenter.go(container);
				}
			}
			
//			else if (token.equals("main")) {
//				presenter = new MainPresenter(rpcService, eventBus, new MainView(loggedInUser, selectedYear));
//				setContainer(centerPanel);
//				if (presenter != null) {
//					presenter.go(container);
//				}
//			}	
			
			
//			if (presenter != null) {
//				this.container = mainContainer;
//				presenter.go(container);
//				
//			}
		}
	} 
	private void setContainer(HasWidgets container) {
		this.container = container;
		this.container.clear();
	}
}
