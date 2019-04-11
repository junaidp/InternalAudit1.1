package com.internalaudit.client.presenter;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.internalaudit.client.InternalAuditServiceAsync;
import com.internalaudit.client.DashboardNew.DashboardNew;
import com.internalaudit.client.event.AuditEngagementEvent;
import com.internalaudit.client.event.AuditSchedulingEvent;
import com.internalaudit.client.event.CreateUserEvent;
import com.internalaudit.client.event.DashBoardEvent;
import com.internalaudit.client.event.FollowUpEvent;
import com.internalaudit.client.event.MainEvent;
import com.internalaudit.client.event.ReportingEvent;
import com.internalaudit.client.event.ReportsEvent;
import com.internalaudit.client.view.AuditPlanningView;
import com.internalaudit.client.view.DisplayAlert;
import com.internalaudit.client.view.LoadingPopup;
import com.internalaudit.client.view.PopupsView;
import com.internalaudit.client.widgets.FeedbackWidget;
import com.internalaudit.shared.Employee;
import com.internalaudit.shared.Feedback;
import com.internalaudit.shared.TimeOutException;
import com.sencha.gxt.widget.core.client.PlainTabPanel;
import com.sencha.gxt.widget.core.client.TabItemConfig;
import com.sencha.gxt.widget.core.client.TabPanel;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;

public class MainPresenter implements Presenter

{
	private final InternalAuditServiceAsync rpcService;
	private final HandlerManager eventBus;
	private final Display display;
	private Logger logger = Logger.getLogger("DashBoardPresenter");
	private int selectedYear = 0;
	private Employee loggedInUser;
	private DashboardNew dashboardNew = null;
	private AuditPlanningView auditPlanningView = null;

	public interface Display {
		Widget asWidget();

		Employee getLoggedInUser();

		Anchor getLogOut();

		Anchor getWelcome();

		VerticalPanel getVpnlAuditScheduing();

		PlainTabPanel getPanel();

		VerticalPanel getVpnlAuditEngagement();

		VerticalPanel getReportingView();

		VerticalPanel getFollowUpView();

		VerticalPanel getReportsView();

		ListBox getListYears();

		VerticalPanel getVpnlDashBoard();

		Anchor getCreateCompany();

		Anchor getCreateUser();

		Anchor getFeedBack();

		VerticalLayoutContainer getVpnlDashBoardNew();

		VerticalPanel getContainerAuditPlanning();

	}

	public MainPresenter(InternalAuditServiceAsync rpcService, HandlerManager eventBus, int selectedYear,
			Employee loggedInUser, Display view) {
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.display = view;
		this.selectedYear = selectedYear;
		this.loggedInUser = loggedInUser;
	}

	public void go(HasWidgets container) {
		container.clear();
		container.add(display.asWidget());
		bind();
	}

	private void bind() {

		fetchCurrentYear();

		final LoadingPopup loading = new LoadingPopup();
		loading.display();
		Timer t = new Timer() {
			@Override
			public void run() {
				if (display.getLoggedInUser().getFromInternalAuditDept().equalsIgnoreCase("no")) {
					eventBus.fireEvent(new ReportingEvent(display.getReportingView()));

				} else {
					eventBus.fireEvent(new DashBoardEvent(display.getVpnlDashBoard()));
				}
				loading.remove();
			}
		};

		// Schedule the timer to run once in 2 seconds.
		t.schedule(2000);

		display.getCreateUser().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new CreateUserEvent(display.getLoggedInUser()));
			}
		});

		display.getCreateCompany().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				History.newItem("createCompany");
			}
		});

		display.getListYears().addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				History.newItem("");
				selectYear();
				eventBus.fireEvent(new MainEvent(display.getLoggedInUser()));
			}
		});

		SelectionHandler<Widget> handler = new SelectionHandler<Widget>() {
			@Override
			public void onSelection(SelectionEvent<Widget> event) {
				TabPanel panel = (TabPanel) event.getSource();
				Widget w = event.getSelectedItem();
				TabItemConfig config = panel.getConfig(w);

				if ("Audit Planning".equalsIgnoreCase(config.getText())) {

					display.getContainerAuditPlanning().clear();
					display.getContainerAuditPlanning().add(getAuditPlanningView());
				}

				if ("Audit Scheduling".equalsIgnoreCase(config.getText())) {
					eventBus.fireEvent(new AuditSchedulingEvent(display.getVpnlAuditScheduing()));
				}

				else if ("Audit Engagement".equalsIgnoreCase(config.getText())) {
					eventBus.fireEvent(new AuditEngagementEvent(display.getVpnlAuditEngagement()));
				}

				else if ("Reporting".equalsIgnoreCase(config.getText())) {
					eventBus.fireEvent(new ReportingEvent(display.getReportingView()));

				}

				else if ("Follow Up".equalsIgnoreCase(config.getText())) {
					eventBus.fireEvent(new FollowUpEvent(display.getFollowUpView()));

				}

				else if ("Reports".equalsIgnoreCase(config.getText())) {
					eventBus.fireEvent(new ReportsEvent(display.getReportsView()));

				}

				else if ("DashBoard".equalsIgnoreCase(config.getText())) {
					eventBus.fireEvent(new DashBoardEvent(display.getVpnlDashBoard()));

				}

				else if ("Analytics".equalsIgnoreCase(config.getText())) {
					display.getVpnlDashBoardNew().add(getNewDashBoard());

				}

				// Info.display("", "" + config.getText() + "");
			}

		};

		display.getPanel().addSelectionHandler(handler);

		display.getLogOut().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				logOut();

			}
		});

		display.getFeedBack().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				final FeedbackWidget feedBackWidget = new FeedbackWidget();
				final PopupsView popup = new PopupsView(feedBackWidget, "Feedback");
				feedBackWidget.getBtnSubmit().addClickHandler(new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						submitFeedback(feedBackWidget, popup);
					}
				});

			}

		});

		display.getWelcome().setText(display.getLoggedInUser().getEmployeeName() + " ");

	}

	private Widget getAuditPlanningView() {
		if (auditPlanningView == null) {
			auditPlanningView = new AuditPlanningView(loggedInUser);
		}
		return auditPlanningView;
	}

	private Widget getNewDashBoard() {
		if (dashboardNew == null) {
			dashboardNew = new DashboardNew();
		}
		return dashboardNew;

	}

	private void submitFeedback(FeedbackWidget feedBackWidget, final PopupsView popup) {
		Feedback feedBack = new Feedback();

		feedBack.setEmployee(loggedInUser);
		feedBack.setHeading(feedBackWidget.getTxtHeading().getText());
		feedBack.setDescription(feedBackWidget.getTxtDescription().getText());

		rpcService.submitFeedBack(feedBack, new AsyncCallback<String>() {

			@Override
			public void onSuccess(String result) {
				new DisplayAlert(result);
				popup.getPopup().removeFromParent();
			}

			@Override
			public void onFailure(Throwable caught) {
				popup.getPopup().removeFromParent();
				Window.alert("Failed submit feedback: " + caught.getLocalizedMessage());
			}
		});
	}

	private void fetchCurrentYear() {
		rpcService.fetchCurrentYear(new AsyncCallback<Integer>() {

			@Override
			public void onFailure(Throwable caught) {
				System.out.println("fail :fetchCurrentYear");
			}

			@Override
			public void onSuccess(Integer currentYear) {
				display.getListYears().addItem(currentYear + "", currentYear + "");
				display.getListYears().addItem(currentYear + 1 + "", currentYear + 1 + "");
				display.getListYears().addItem(currentYear + 2 + "", currentYear + 2 + "");
				display.getListYears().addItem(currentYear + 3 + "", currentYear + 3 + "");
				display.getListYears().addItem(currentYear + 4 + "", currentYear + 4 + "");
				display.getListYears().addItem(currentYear - 1 + "", currentYear - 1 + "");
				display.getListYears().addItem(currentYear - 2 + "", currentYear - 2 + "");
				display.getListYears().addItem(currentYear - 3 + "", currentYear - 3 + "");
				display.getListYears().addItem(currentYear - 4 + "", currentYear - 4 + "");

				if (selectedYear != 0) {
					for (int i = 0; i < display.getListYears().getItemCount(); i++) {
						if (Integer.parseInt(display.getListYears().getValue(i)) == selectedYear) {// CHANGE
																									// THIS
																									// remove
																									// -1
							display.getListYears().setSelectedIndex(i);
						}
					}
				}

			}
		});

	}

	public void logOut() {
		rpcService.logOut(new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable caught) {
				System.out.println("FAIL: logOut");

				logger.log(Level.INFO, "FAIL: logOut .Inside Audit AuditAreaspresenter");
				if (caught instanceof TimeOutException) {
					History.newItem("login");
				} else {
					System.out.println("FAIL: logOut .Inside AuditAreaspresenter");
					Window.alert("FAIL: logOut");// After FAIL ... write RPC
													// Name NOT Method Name..
				}

			}

			@Override
			public void onSuccess(String result) {
				History.newItem("login");

			}
		});
	}

	public void selectYear() {
		final int year = Integer.parseInt(display.getListYears().getSelectedValue());
		final LoadingPopup loading = new LoadingPopup();
		loading.display();
		rpcService.selectYear(year, new AsyncCallback<Void>() {

			@Override
			public void onFailure(Throwable caught) {
				System.out.println("FAIL: select year");

				logger.log(Level.INFO, "FAIL: selectYear .Inside Audit AuditAreaspresenter");
				if (caught instanceof TimeOutException) {
					History.newItem("login");
				} else {
					System.out.println("FAIL: selectYear .Inside AuditAreaspresenter");
					Window.alert("FAIL: selectYear");// After FAIL ... write RPC
														// Name NOT Method
														// Name..
				}

			}

			@Override
			public void onSuccess(Void result) {
				History.newItem("");
				eventBus.fireEvent(new MainEvent(display.getLoggedInUser(), year));
				loading.remove();
			}
		});
	}

}
