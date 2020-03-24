package com.internalaudit.client.presenter;

import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.DecoratedPopupPanel;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.internalaudit.client.InternalAuditServiceAsync;
import com.internalaudit.client.view.DisplayAlert;
import com.internalaudit.client.view.JobData;
import com.internalaudit.client.view.Reporting.AllJobsView;
import com.internalaudit.client.view.Reporting.JobExceptionsView;
import com.internalaudit.client.view.Reporting.JobReportView;
import com.internalaudit.client.view.Reporting.ResponsiblePersonRowHeadingView;
import com.internalaudit.client.view.Reporting.ResponsiblePersonRowView;
import com.internalaudit.client.view.Reporting.SelectedJobView;
import com.internalaudit.shared.Employee;
import com.internalaudit.shared.Exceptions;
import com.internalaudit.shared.JobCreation;
import com.internalaudit.shared.TimeOutException;

public class ReportingPresenter implements Presenter

{
	private final InternalAuditServiceAsync rpcService;
	private final HandlerManager eventBus;
	private final Display display;
	private DecoratedPopupPanel popupLoading;
	private ArrayList<Employee> employeesList = new ArrayList<Employee>();
	private Employee loggedInEmployee;
	private Logger logger = Logger.getLogger("ReportingPresenter");
	private String reportingTab;

	public interface Display {
		Widget asWidget();

		VerticalPanel getVpnlReporting();

		VerticalPanel getVpnlJobs();

		VerticalPanel getVpnlSelectedJob();

	}

	public ReportingPresenter(InternalAuditServiceAsync rpcService, HandlerManager eventBus, Employee employee,
			ArrayList<String> tokenParams, String reportingTab, Display view) {
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.display = view;
		this.loggedInEmployee = employee;
		this.reportingTab = reportingTab;
		setInputParams(tokenParams);

		fetchEmployees();
		fetchJobs();
	}

	public ReportingPresenter(InternalAuditServiceAsync rpcService, HandlerManager eventBus, Employee employee,
			ArrayList<String> tokenParams, String reportingTab, Display view, int jobId) {
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.display = view;
		this.loggedInEmployee = employee;
		this.reportingTab = reportingTab;
		setInputParams(tokenParams);

		fetchEmployees();
		fetchJob(jobId);
	}

	private void setInputParams(ArrayList<String> input_Params) {
		for (int i = 0; i < input_Params.size(); i++) {
			String param = (String) input_Params.get(i);
			String paramKey = "";
			String paramValue = "";
			if (param.indexOf("=") != -1) {
				paramKey = param.substring(0, param.indexOf("="));
				paramValue = param.substring(param.indexOf("=") + 1);
			}
			if (paramKey.equalsIgnoreCase("companyId")) {
				try {
					int companyId = Integer.parseInt(paramValue);
				} catch (Exception ex) {
					Window.alert("Sorry , above page not found");
					History.newItem("login");
				}
			}
		}

	}

	public void go(HasWidgets container) {
		container.clear();
		container.add(display.asWidget());
		bind();

	}

	private void fetchAuditHeadExceptions(int selectedJob) {
		rpcService.fetchAuditHeadExceptions(loggedInEmployee.getEmployeeId(), selectedJob,
				new AsyncCallback<ArrayList<Exceptions>>() {

					@Override
					public void onFailure(Throwable caught) {

						logger.log(Level.INFO, "FAIL: fetchAuditHeadExceptions .Inside Audit AuditAreaspresenter");
						if (caught instanceof TimeOutException) {
							History.newItem("login");
						} else {
							System.out.println("FAIL: fetchAuditHeadExceptions .Inside AuditAreaspresenter");
							Window.alert("FAIL: fetchAuditHeadExceptions");// After
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
					public void onSuccess(final ArrayList<Exceptions> result) {

						// display.getVpnlSelectedJob().clear();/// hERE
						if (result.size() > 0) {
							display.getVpnlSelectedJob().clear();/// hERE
							Label lblHeading = new Label(result.get(0).getJobName());
							lblHeading.addStyleName("heading");
							display.getVpnlSelectedJob().add(lblHeading);
							// display.getVpnlReporting().clear();
							ResponsiblePersonRowHeadingView responsiblePersonView = new ResponsiblePersonRowHeadingView();

							// display.getVpnlReporting().add(responsiblePersonView);
							display.getVpnlSelectedJob().add(responsiblePersonView);

							for (int i = 0; i < result.size(); i++) {
								final ResponsiblePersonRowView responsiblePersonRowView = new ResponsiblePersonRowView();
								responsiblePersonRowView.getException().setText(result.get(i).getDetail());
								responsiblePersonRowView.getRecommendations()
										.setText(result.get(i).getRecommendations());
								responsiblePersonRowView.getRecommendations()
										.setTitle(result.get(i).getRecommendations());

								responsiblePersonRowView.getImplication().setText(result.get(i).getImplication());
								responsiblePersonRowView.getImplication().setTitle(result.get(i).getImplication());
								responsiblePersonRowView.getException().setTitle(result.get(i).getDetail());
								responsiblePersonRowView.getAuditJob().setText(result.get(i).getJobName());
								responsiblePersonRowView.getAuditJob().setTitle(result.get(i).getJobName());
								showManagementPanel(result, i, responsiblePersonRowView);
								if (result.get(i).getFinalStatus() != null
										&& result.get(i).getFinalStatus().equalsIgnoreCase("Approved")) {
									responsiblePersonRowView.getStatus().setText("Closed");
									responsiblePersonRowView.getStatus().addStyleName("blue");
									// showManagementPanel(result, i,
									// responsiblePersonRowView);
									responsiblePersonRowView.getVpnlApprovalButton().setVisible(true);
									showImplementationPanel(result, i, responsiblePersonRowView);
									responsiblePersonRowView.getVpnlApprovalButton().setVisible(false);
								}
								if (result.get(i).getFinalStatus() != null
										&& result.get(i).getFinalStatus().equalsIgnoreCase("Rejected")
										|| result.get(i).getStatus() != null
												&& result.get(i).getStatus().equalsIgnoreCase("Rejected")) {
									responsiblePersonRowView.getStatus().setText("feedback given");
									responsiblePersonRowView.getStatus().addStyleName("blue");
									responsiblePersonRowView.getVpnlApprovalButton().setVisible(false);
									showManagementPanel(result, i, responsiblePersonRowView);
									showImplementationPanel(result, i, responsiblePersonRowView);
								}
								responsiblePersonView.add(responsiblePersonRowView);
								final JobData jobData = new JobData();
								jobData.setSelectedId(i);
								if (!(result.get(i).getManagementComments() == null)
										&& !result.get(i).getManagementComments().equals("")
										&& ((result.get(i).getStatus() != null
												&& result.get(i).getStatus().equals("Sent"))
												|| (result.get(i).getFinalStatus() != null
														&& result.get(i).getFinalStatus().equals("Sent")))) {
									showManagementPanel(result, i, responsiblePersonRowView);
									responsiblePersonRowView.disableFields();
									responsiblePersonRowView.getVpnlApprovalButton().setVisible(true);
									// if(result.get(i).getStatus()!=null &&
									// !result.get(i).getStatus().equals("")){
									// responsiblePersonRowView.getImplementaionDate().setEnabled(true);
									//
									// }
									/// FOR ACTUAL IMPLEMENTATION APPROVAL....
									if (result.get(i).getImplementaionComments() != null
											&& !result.get(i).getImplementaionComments().equals("")
											&& result.get(i).getFinalStatus().equals("Sent")) {
										showImplementationPanel(result, i, responsiblePersonRowView);
										responsiblePersonRowView.getVpnlApprovalButton().setVisible(true);

									}
								}

								responsiblePersonRowView.getBtnApprove().addClickHandler(new ClickHandler() {

									@Override
									public void onClick(ClickEvent event) {
										Boolean sendMail = true;
										if (result.get(jobData.getSelectedId()).getImplementaionComments() != null
												&& !result.get(jobData.getSelectedId()).getImplementaionComments()
														.equals("")
												&& result.get(jobData.getSelectedId()).getStatus().equals("Approved")) {
											result.get(jobData.getSelectedId()).setFinalStatus("Approved");
										} else {
											result.get(jobData.getSelectedId()).setStatus("Approved");

										}
										result.get(jobData.getSelectedId())
												.setComments(responsiblePersonRowView.getTxtComments().getText());

										// responsiblePersonRowView.getStatus().setText("Approved");
										// responsiblePersonRowView.getBtnApprove().setEnabled(false);
										// responsiblePersonRowView.getBtnReject().setEnabled(false);
										exceptionApproved(responsiblePersonRowView, "Approved");
										new DisplayAlert("Exception Approved");
										sendException(result.get(jobData.getSelectedId()), sendMail);

									}

								});

								responsiblePersonRowView.getBtnReject().addClickHandler(new ClickHandler() {

									@Override
									public void onClick(ClickEvent event) {
										Boolean sendMail = false;
										if (result.get(jobData.getSelectedId()).getImplementaionComments() != null
												&& !result.get(jobData.getSelectedId()).getImplementaionComments()
														.equals("")) {
											result.get(jobData.getSelectedId()).setFinalStatus("Rejected");
										} else {
											result.get(jobData.getSelectedId()).setStatus("Rejected");
										}
										result.get(jobData.getSelectedId())
												.setComments(responsiblePersonRowView.getTxtComments().getText());

										// responsiblePersonRowView.getStatus().setText("Rejected");
										// responsiblePersonRowView.getBtnApprove().setEnabled(false);
										// responsiblePersonRowView.getBtnReject().setEnabled(false);
										exceptionApproved(responsiblePersonRowView, "Rejected");

										sendException(result.get(jobData.getSelectedId()), sendMail);
										new DisplayAlert("Exception Rejected");

									}
								});
								responsiblePersonRowView.getImplementaionDate().setEnabled(false);
								responsiblePersonRowView.getIsAgreed().setEnabled(false);
								responsiblePersonRowView.getBtnSend().setVisible(false);

							}
						} else {
							// fetchEmployees();
							// fetchJobs();
						}
					}

					private void showManagementPanel(final ArrayList<Exceptions> result, int i,
							final ResponsiblePersonRowView responsiblePersonRowView) {
						// responsiblePersonRowView.getVpnlApprovalButton().setVisible(true);
						responsiblePersonRowView.getManagementComments().setText(result.get(i).getManagementComments());
						responsiblePersonRowView.getManagementComments()
								.setTitle(result.get(i).getManagementComments());

						responsiblePersonRowView.getImplementaionDate().setValue(result.get(i).getImplementaionDate());
						responsiblePersonRowView.getIsAgreed().setSelectedIndex(result.get(i).getIsAgreed());

						responsiblePersonRowView.getTxtComments().setText(result.get(i).getComments());
						responsiblePersonRowView.getTxtComments().setTitle(result.get(i).getComments());
						responsiblePersonRowView.getImplication().setText(result.get(i).getImplication());
						responsiblePersonRowView.getResponsiblePerson()
								.addItem(result.get(i).getResponsiblePerson().getEmployeeName());
						// responsiblePersonRowView.getImplicationRating().addItem(result.get(i).getImplicationRating());
						if (result.get(i).getImplicationRating() == "0") {
							responsiblePersonRowView.getImplicationRating().addItem("Low");
						}

				else if (result.get(i).getImplicationRating() == "1") {
							responsiblePersonRowView.getImplicationRating().addItem("Medium");
						} else if (result.get(i).getImplicationRating() == "2") {
							responsiblePersonRowView.getImplicationRating().addItem("High");
						}

					}

					private void showImplementationPanel(final ArrayList<Exceptions> result, int i,
							final ResponsiblePersonRowView responsiblePersonRowView) {
						responsiblePersonRowView.getHpnl2().setVisible(true);
						responsiblePersonRowView.getImplementaionComments()
								.setText(result.get(i).getImplementaionComments());
						responsiblePersonRowView.getIsImplemented().setSelectedIndex(result.get(i).getIsImplemented());
						responsiblePersonRowView.getIsAgreed().setSelectedIndex(result.get(i).getIsAgreed());

						responsiblePersonRowView.getImplementaionComments().setEnabled(false);
						responsiblePersonRowView.getIsImplemented().setEnabled(false);
						// responsiblePersonRowView.getIsAgreed().setEnabled(false);
					}
				});
	}

	private void exceptionApproved(final ResponsiblePersonRowView responsiblePersonRowView, String status) {
		responsiblePersonRowView.getStatus().setText(status);
		responsiblePersonRowView.getStatus().addStyleName("blue");
		responsiblePersonRowView.getBtnApprove().setVisible(false);
		responsiblePersonRowView.getBtnReject().setVisible(false);
		responsiblePersonRowView.getTxtComments().setVisible(false);
	}

	private void fetchUserExceptions(int jobId) {
		rpcService.fetchEmployeeExceptions(loggedInEmployee.getEmployeeId(), jobId,
				new AsyncCallback<ArrayList<Exceptions>>() {

					@Override
					public void onFailure(Throwable caught) {

						logger.log(Level.INFO, "FAIL: fetchEmployeeExceptions .Inside Audit AuditAreaspresenter");
						if (caught instanceof TimeOutException) {
							History.newItem("login");
						} else {
							System.out.println("FAIL: fetchEmployeeExceptions .Inside AuditAreaspresenter");
							Window.alert("FAIL: fetchEmployeeExceptions");// After
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
					public void onSuccess(final ArrayList<Exceptions> result) {
						display.getVpnlSelectedJob().clear();

						if (result.size() > 0) {
							Label lblHeading = new Label(result.get(0).getJobName());
							lblHeading.addStyleName("heading");
							display.getVpnlSelectedJob().add(lblHeading);
							ResponsiblePersonRowHeadingView responsiblePersonView = new ResponsiblePersonRowHeadingView();

							for (int i = 0; i < result.size(); i++) {
								final ResponsiblePersonRowView responsiblePersonRowView = new ResponsiblePersonRowView();
								// responsiblePersonRowView.getBtnSend().setVisible(true);
								if (loggedInEmployee.getRollId() == 5) {
									responsiblePersonRowView.getManagementComments().setEnabled(true);
								}
								responsiblePersonRowView.getException().setText(result.get(i).getDetail());
								responsiblePersonRowView.getRecommendations()
										.setText(result.get(i).getRecommendations());
								responsiblePersonRowView.getRecommendations()
										.setTitle(result.get(i).getRecommendations());
								responsiblePersonRowView.getImplication().setText(result.get(i).getImplication());
								responsiblePersonRowView.getResponsiblePerson()
										.addItem(result.get(i).getResponsiblePerson().getEmployeeName());
								// responsiblePersonRowView.getImplicationRating().addItem(result.get(i).getImplicationRating());
								if (result.get(i).getImplicationRating() == "0") {
									responsiblePersonRowView.getImplicationRating().addItem("Low");
								}

				else if (result.get(i).getImplicationRating() == "1") {
									responsiblePersonRowView.getImplicationRating().addItem("Medium");
								} else if (result.get(i).getImplicationRating() == "2") {
									responsiblePersonRowView.getImplicationRating().addItem("High");
								}
								responsiblePersonRowView.getAuditJob().setText(result.get(i).getJobName());
								if (result.get(i).getFinalStatus() != null
										&& result.get(i).getFinalStatus().equalsIgnoreCase("Approved")) {
									responsiblePersonRowView.getStatus().setText("Closed");
									responsiblePersonRowView.getStatus().addStyleName("blue");
								}
								responsiblePersonView.add(responsiblePersonRowView);
								final JobData jobData = new JobData();
								jobData.setSelectedId(i);

								if (result.get(i).getStatus() != null
										&& result.get(i).getStatus().equalsIgnoreCase("rejected")) {
									responsiblePersonRowView.getBtnSend().setText("Send Again");
									responsiblePersonRowView.getManagementComments()
											.setText(result.get(i).getManagementComments());
									responsiblePersonRowView.getManagementComments()
											.setTitle(result.get(i).getManagementComments());
									responsiblePersonRowView.getIsAgreed()
											.setSelectedIndex(result.get(i).getIsAgreed());

									responsiblePersonRowView.getImplementaionDate()
											.setValue(result.get(i).getImplementaionDate());
									// responsiblePersonRowView.getStatus().setText(result.get(i).getStatus()+"
									// ( "+ result.get(i).getComments()+ " ) ");
									responsiblePersonRowView.getStatus().setText(result.get(i).getStatus());
									if (result.get(i).getStatus().equalsIgnoreCase("rejected")) {
										responsiblePersonRowView.getStatus().setText("feedback given");
									}

									responsiblePersonRowView.getStatus().setTitle(result.get(i).getComments());

								} else if (result.get(i).getManagementComments() != null
										&& !result.get(i).getManagementComments().equals("")) {
									// responsiblePersonRowView.getBtnSend().setText("Sent.");
									exceptionSent(responsiblePersonRowView, "Sent");
									responsiblePersonRowView.getManagementComments()
											.setText(result.get(i).getManagementComments());
									responsiblePersonRowView.getManagementComments()
											.setTitle(result.get(i).getManagementComments());
									responsiblePersonRowView.getIsAgreed()
											.setSelectedIndex(result.get(i).getIsAgreed());
									responsiblePersonRowView.getResponsiblePerson()
											.addItem(result.get(i).getResponsiblePerson().getEmployeeName());
									// responsiblePersonRowView.getImplicationRating().addItem(result.get(i).getImplicationRating());
									if (result.get(i).getImplicationRating() == "0") {
										responsiblePersonRowView.getImplicationRating().addItem("Low");
									}

				else if (result.get(i).getImplicationRating() == "1") {
										responsiblePersonRowView.getImplicationRating().addItem("Medium");
									} else if (result.get(i).getImplicationRating() == "2") {
										responsiblePersonRowView.getImplicationRating().addItem("High");
									}

									responsiblePersonRowView.getImplementaionDate()
											.setValue(result.get(i).getImplementaionDate());
									responsiblePersonRowView.disableFields();
									responsiblePersonRowView.getBtnSend().setVisible(true);

									if ((result.get(i).getFinalStatus() != null
											&& result.get(i).getFinalStatus().equalsIgnoreCase("Sent"))
											|| (result.get(i).getStatus() != null
													&& result.get(i).getStatus().equalsIgnoreCase("Sent"))) {
										responsiblePersonRowView.getBtnSend().setVisible(false);
									}

									/// Work for Actual Implementaion for
									/// Responsible person..
									if (result.get(i).getStatus() != null
											&& result.get(i).getStatus().equalsIgnoreCase("approved")) {
										responsiblePersonRowView.getHpnl2().setVisible(true);
										responsiblePersonRowView.getBtnSend().setText("Send");
										responsiblePersonRowView.getBtnSend().setEnabled(true);
										///////////////// CHANGE IMPLEMENTATION
										///////////////// DATE AFTER
										///////////////// APPROVAL//////////
										changeImplementationDateAfterApproval(responsiblePersonRowView, result.get(i));

										if (result.get(i).getImplementaionComments() != null
												&& !result.get(i).getImplementaionComments().equals("")) {
											responsiblePersonRowView.getImplementaionComments().setEnabled(false);
											responsiblePersonRowView.getIsImplemented().setEnabled(false);
											responsiblePersonRowView.getImplementaionComments()
													.setText(result.get(i).getImplementaionComments());
											responsiblePersonRowView.getIsImplemented()
													.setSelectedIndex(result.get(i).getIsImplemented());
											responsiblePersonRowView.getIsAgreed()
													.setSelectedIndex(result.get(i).getIsAgreed());

											// responsiblePersonRowView.getStatus().setText(result.get(i).getFinalStatus());
											responsiblePersonRowView.getBtnSend().setText("Send");
											responsiblePersonRowView.getBtnSend().setEnabled(true);
										}
										if (result.get(i).getFinalStatus() != null
												&& result.get(i).getFinalStatus().equalsIgnoreCase("approved")) {
											responsiblePersonRowView.getImplementaionComments().setEnabled(false);
											responsiblePersonRowView.getIsImplemented().setEnabled(false);
											// responsiblePersonRowView.getBtnSend().setText("Sent.");
											// responsiblePersonRowView.getBtnSend().setEnabled(false);
											exceptionSent(responsiblePersonRowView, "Closed");

										} else if (result.get(i).getFinalStatus() != null
												&& result.get(i).getFinalStatus().equalsIgnoreCase("rejected")) {
											responsiblePersonRowView.getImplementaionComments().setEnabled(true);
											responsiblePersonRowView.getIsImplemented().setEnabled(true);
											responsiblePersonRowView.getBtnSend().setText("Send");
											responsiblePersonRowView.getBtnSend().setEnabled(true);
											// responsiblePersonRowView.getStatus().setText(result.get(i).getFinalStatus()+"
											// ( "+ result.get(i).getComments()+
											// " ) ");
											responsiblePersonRowView.getStatus().setText("feedback given");
											responsiblePersonRowView.getStatus().setTitle(result.get(i).getComments());

										}

									}
								}

								if (result.get(i).getFinalStatus() != null
										&& result.get(i).getFinalStatus().equalsIgnoreCase("Approved")) {
									responsiblePersonRowView.getImplementaionDate().setEnabled(false);
									responsiblePersonRowView.getIsAgreed().setEnabled(false);
									responsiblePersonRowView.getManagementComments().setEnabled(false);
								}

								responsiblePersonRowView.getBtnSend().addClickHandler(new ClickHandler() {

									@Override
									public void onClick(ClickEvent event) {

										if (responsiblePersonRowView.getImplementaionDate().getValue() == null) {
											new DisplayAlert("Please Select implementation Date");
										} else {
											boolean statusApproved = result.get(jobData.getSelectedId())
													.getStatus() != null
													&& result.get(jobData.getSelectedId()).getStatus()
															.equals("Approved");
											// 2019 aug
											if (responsiblePersonRowView.getIsAgreed()
													.getValue(responsiblePersonRowView.getIsAgreed().getSelectedIndex())
													.equalsIgnoreCase("1")
													&& responsiblePersonRowView.getImplementaionDate()
															.getValue() == null) {
												// Window.alert("Please select
												// Implementation date");
												new DisplayAlert("Please select Implementation date");

											}

				else if (statusApproved && responsiblePersonRowView.getImplementaionComments().getText().isEmpty()) {
												// Window.alert();
												new DisplayAlert("Please enter Final comments");
											} else if (responsiblePersonRowView.getManagementComments().getText()
													.isEmpty()) {
												Window.alert("Please enter Management comments");
											} else {
												saveException(result, responsiblePersonRowView,

														jobData);
											}

										}
									}
								});

							}

							// display.getVpnlReporting().clear();
							// display.getVpnlReporting().add(responsiblePersonView);
							display.getVpnlSelectedJob().add(responsiblePersonView);
						}
					}

				});

	}

	private void saveException(final ArrayList<Exceptions> result,
			final ResponsiblePersonRowView responsiblePersonRowView, final JobData jobData) {
		result.get(jobData.getSelectedId())
				.setImplementaionDate(responsiblePersonRowView.getImplementaionDate().getValue());
		result.get(jobData.getSelectedId()).setIsAgreed(Integer.parseInt(responsiblePersonRowView.getIsAgreed()
				.getValue(responsiblePersonRowView.getIsAgreed().getSelectedIndex())));

		result.get(jobData.getSelectedId())
				.setManagementComments(responsiblePersonRowView.getManagementComments().getText());

		// For Actual Implementation of responsible person ..Sending actual
		// imeplemetaion stats
		if (responsiblePersonRowView.getImplementaionComments() != null
				&& !responsiblePersonRowView.getImplementaionComments().getText().equals("")) {
			result.get(jobData.getSelectedId())
					.setImplementaionComments(responsiblePersonRowView.getImplementaionComments().getText());
			result.get(jobData.getSelectedId()).setIsImplemented(Integer.parseInt(responsiblePersonRowView
					.getIsImplemented().getValue(responsiblePersonRowView.getIsImplemented().getSelectedIndex())));
			result.get(jobData.getSelectedId()).setIsAgreed(Integer.parseInt(responsiblePersonRowView.getIsAgreed()
					.getValue(responsiblePersonRowView.getIsAgreed().getSelectedIndex())));
			result.get(jobData.getSelectedId()).setImplication(responsiblePersonRowView.getImplication().getText());

			responsiblePersonRowView.getImplementaionComments().setEnabled(false);
			responsiblePersonRowView.getIsImplemented().setEnabled(false);

			result.get(jobData.getSelectedId()).setFinalStatus("Sent");
		} else {
			result.get(jobData.getSelectedId()).setStatus("Sent");
		}
		Boolean sendMail = false;
		sendException(result.get(jobData.getSelectedId()), sendMail);
		responsiblePersonRowView.disableFields();
		// responsiblePersonRowView.getBtnSend().setText("Sent.");
		// responsiblePersonRowView.getStatus().setText("");
		exceptionSent(responsiblePersonRowView, "Sent");
	}

	private void changeImplementationDateAfterApproval(final ResponsiblePersonRowView responsiblePersonRowView,
			final Exceptions exception) {
		// responsiblePersonRowView.getManagementComments().setEnabled(true);
		responsiblePersonRowView.getImplementaionDate().setEnabled(true);
		responsiblePersonRowView.getIsAgreed().setEnabled(false);
		responsiblePersonRowView.getManagementComments().setEnabled(false);
		responsiblePersonRowView.getImplementaionDate().addValueChangeHandler(new ValueChangeHandler<Date>() {

			@Override
			public void onValueChange(ValueChangeEvent<Date> event) {
				Boolean sendMail = false;
				boolean confirmed = Window
						.confirm("New Implementation date will be Sent to Audit Department for Approval");
				if (confirmed) {
					exception.setImplementaionDate(responsiblePersonRowView.getImplementaionDate().getValue());
					exception.setManagementComments(responsiblePersonRowView.getManagementComments().getText());

					exception.setImplementaionComments("");
					// responsiblePersonRowView.getManagementComments().setText("");
					exception.setStatus("Sent");
					responsiblePersonRowView.getImplementaionDate().setEnabled(false);
					responsiblePersonRowView.getIsAgreed().setEnabled(false);
					responsiblePersonRowView.getImplementaionComments().setEnabled(false);
					responsiblePersonRowView.getManagementComments().setEnabled(false);
					responsiblePersonRowView.getBtnSend().setVisible(false);
					rpcService.sendException(exception, sendMail, new AsyncCallback<String>() {

						@Override
						public void onFailure(Throwable caught) {
							Window.alert("Fail. updating exception");
						}

						@Override
						public void onSuccess(String result) {
							new DisplayAlert("New Implementation Date submitted");
						}
					});
				}
			}
		});
	}

	private void exceptionSent(final ResponsiblePersonRowView responsiblePersonRowView, String status) {
		responsiblePersonRowView.getBtnSend().setVisible(false);
		responsiblePersonRowView.getStatus().setText(status);
		responsiblePersonRowView.getStatus().setVisible(true);
		responsiblePersonRowView.getStatus().addStyleName("blue");
	}

	private void fetchEmployees() {
		rpcService.fetchEmployees(new AsyncCallback<ArrayList<Employee>>() {

			@Override
			public void onFailure(Throwable caught) {
				//

				logger.log(Level.INFO, "FAIL: fetchEmployees .Inside Audit AuditAreaspresenter");
				if (caught instanceof TimeOutException) {
					History.newItem("login");
				} else {
					System.out.println("FAIL: fetchEmployees .Inside AuditAreaspresenter");
					Window.alert("FAIL: fetchEmployees");// After FAIL ... write
															// RPC Name NOT
															// Method Name..
				}

			}

			@Override
			public void onSuccess(ArrayList<Employee> employees) {
				for (int i = 0; i < employees.size(); i++) {
					if (employees.get(i).getFromInternalAuditDept().equalsIgnoreCase("no")) {
						employeesList.add(employees.get(i));
					}
				}

			}
		});

	}

	private void fetchJobs() {
		rpcService.fetchEmployeeJobs(loggedInEmployee, reportingTab, new AsyncCallback<ArrayList<JobCreation>>() {

			@Override
			public void onFailure(Throwable caught) {

				logger.log(Level.INFO, "FAIL: fetchEmployeeJobs .Inside Audit AuditAreaspresenter");
				if (caught instanceof TimeOutException) {
					History.newItem("login");
				} else {
					System.out.println("FAIL: fetchEmployeeJobs .Inside AuditAreaspresenter");
					Window.alert("FAIL: fetchEmployeeJobs");// After FAIL ...
															// write RPC Name
															// NOT Method Name..
				}

			}

			@Override
			public void onSuccess(ArrayList<JobCreation> result) {
				displayJobsReportingView(result);
			}

		});

	}

	private void fetchJob(int jobId) {

		rpcService.fetchSelectedJob(jobId, new AsyncCallback<JobCreation>() {

			@Override
			public void onSuccess(JobCreation result) {
				ArrayList<JobCreation> jobs = new ArrayList<JobCreation>();
				jobs.add(result);
				displayJobsReportingView(jobs);
			}

			@Override
			public void onFailure(Throwable caught) {
				logger.log(Level.INFO, "FAIL: fetchEmployeeJobs .Inside Audit AuditAreaspresenter");
				if (caught instanceof TimeOutException) {
					History.newItem("login");
				} else {
					System.out.println("FAIL: fetchEmployeeJobs .Inside AuditAreaspresenter");
					Window.alert("FAIL: fetchEmployeeJobs");// After FAIL ...
				}
			}
		});

	}

	private void displayJobsReportingView(ArrayList<JobCreation> result) {
		display.getVpnlSelectedJob().clear();
		display.getVpnlJobs().clear();

		if (result != null) {
			AllJobsView allJobsView = new AllJobsView();

			for (int i = 0; i < result.size(); i++) {

				final JobReportView jobReportView = new JobReportView();
				if (loggedInEmployee.getFromInternalAuditDept().equals("yes")) {
					if (result.get(i).getReportStatus() != 0) {
						allJobsView.add(jobReportView);
					}
				} else if (loggedInEmployee.getFromInternalAuditDept().equals("no")) {
					if (result.get(i).getReportStatus() != 1 && result.get(i).getReportStatus() != 0) {
						allJobsView.add(jobReportView);
					}
				}
				jobReportView.setReportStatus(result.get(i).getReportStatus());

				jobReportView.getJobAnchor().setText(result.get(i).getJobName());
				// display.getVpnlReporting().add(allJobsView);
				display.getVpnlJobs().add(allJobsView);
				final JobData jobData = new JobData();
				jobData.setSelectedId(result.get(i).getJobCreationId());

				jobReportView.getJobAnchor().addClickHandler(new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						display.getVpnlSelectedJob().clear();
						SelectedJobView selectedJobView = new SelectedJobView();
						selectedJobView.getLblJob().setText(jobReportView.getJobAnchor().getText());
						display.getVpnlSelectedJob().add(selectedJobView);
						fetchExceptionsforSelectedJob(jobData.getSelectedId(), selectedJobView);

						///////// Displaying Audit head View..///////
						if (loggedInEmployee.getFromInternalAuditDept().equals("yes")) {
							fetchAuditHeadExceptions(jobData.getSelectedId());

						} else {
							fetchUserExceptions(jobData.getSelectedId());

						}
					}

				});
			}
		}
	}

	private void fetchExceptionsforSelectedJob(int jobId, final SelectedJobView selectedJobView) {
		rpcService.fetchJobExceptions(jobId, new AsyncCallback<ArrayList<Exceptions>>() {

			@Override
			public void onFailure(Throwable caught) {

				logger.log(Level.INFO, "FAIL: fetchJobExceptions .Inside Audit AuditAreaspresenter");
				if (caught instanceof TimeOutException) {
					History.newItem("login");
				} else {
					System.out.println("FAIL: fetchJobExceptions .Inside AuditAreaspresenter");
					Window.alert("FAIL: fetchJobExceptions");// After FAIL ...
																// write RPC
																// Name NOT
																// Method Name..
				}

			}

			@Override
			public void onSuccess(final ArrayList<Exceptions> exceptions) {
				for (int i = 0; i < exceptions.size(); i++) {

					final JobExceptionsView jobExceptionsView = new JobExceptionsView();
					selectedJobView.getSelectedJobContainer().add(jobExceptionsView);
					jobExceptionsView.getException().setText(exceptions.get(i).getDetail());
					jobExceptionsView.getDueDate().setValue(exceptions.get(i).getDueDate());
					jobExceptionsView.getRecommendations().setText(exceptions.get(i).getRecommendations());
					jobExceptionsView.getTxtAreaImplication().setText(exceptions.get(i).getImplication());

					if (exceptions.get(i).getImplicationRating() == "0") {
						jobExceptionsView.getListBoxImplicationRating().setItemText(0, "Low");
					} else if (exceptions.get(i).getImplicationRating() == "1") {
						jobExceptionsView.getListBoxImplicationRating().setItemText(0, "Medium");
					} else if (exceptions.get(i).getImplicationRating() == "2") {
						jobExceptionsView.getListBoxImplicationRating().setItemText(0, "High");
					}

					// addeed now
					// jobExceptionsView.getResponsiblePerson().addItem(exceptions.get(i).getResponsiblePerson().getEmployeeName());
					////// CHanged above line here
					jobExceptionsView.getResponsiblePerson().addItem(
							exceptions.get(i).getResponsiblePerson().getEmployeeName(),
							exceptions.get(i).getResponsiblePerson().getEmployeeId() + "");

					// jobExceptionsView.getListBoxImplicationRating().setItemText(0,exceptions.get(i).getImplicationRating());
					///// Displaying Approv/Reject BUTTONs////

					// if(exceptions.get(i).getAuditHead() ==
					// loggedInEmployee.getEmployeeId() &&
					// (exceptions.get(i).getInitialStatus()==null ||
					// exceptions.get(i).getInitialStatus().equals(""))){
					if (loggedInEmployee.getRollId() == 1
							&& (exceptions.get(i).getInitialStatus() == null
									|| exceptions.get(i).getInitialStatus().equals(""))
							&& exceptions.get(i).getDueDate() != null) {
						// if(loggedInEmployee.getRollId().getRollId() ==1 &&
						// (exceptions.get(i).getInitialStatus()==null ||
						// exceptions.get(i).getInitialStatus().equals("") )){
						// //TODO CHANGE FOr DUEDATE

						jobExceptionsView.showApprovalButtons();
					} else {
						jobExceptionsView.hideApprovalButtons();

					}

					if (exceptions.get(i).getResponsiblePerson().getEmployeeId() != 0
							&& exceptions.get(i).getInitialStatus() != null
							&& !exceptions.get(i).getInitialStatus().equals("Rejected")) {
						// jobExceptionsView.getBtnSave().setText("Exception
						// Sent");
						String status;
						if (exceptions.get(i).getFinalStatus() == null
								|| exceptions.get(i).getFinalStatus().equals("")) {
							status = exceptions.get(i).getStatus();
						} else if (exceptions.get(i).getStatus() == null || exceptions.get(i).getStatus().equals("")) {
							status = exceptions.get(i).getInitialStatus();
						} else {
							status = exceptions.get(i).getFinalStatus();
						}
						if (status != null && status.equals("Sent")) {
							status = "Received";
						}
						exceptionSent(jobExceptionsView, status);
						if (status != null && status.equalsIgnoreCase("Approved")) {
							jobExceptionsView.disableFields();
						}
					}

					final JobData exceptionData = new JobData();
					exceptionData.setSelectedId(i);
					for (int j = 0; j < employeesList.size(); j++) {
						jobExceptionsView.getResponsiblePerson().addItem(employeesList.get(j).getEmployeeName(),
								employeesList.get(j).getEmployeeId() + "");
						jobExceptionsView.getDivisionHead().addItem(employeesList.get(j).getEmployeeName(),
								employeesList.get(j).getEmployeeId() + "");

					}

					jobExceptionsView.getBtnSave().addClickHandler(new ClickHandler() {

						@Override
						public void onClick(ClickEvent event) {

							// 2019 aug

							if (jobExceptionsView.getDueDate().getValue() == null) {
								new DisplayAlert("Please Select Due Date");
							} else {

								// commenting this lise 2018
								setResponsibleForandDvisionHead(exceptions, // This
																			// line
																			// also
																			// setting
																			// responsible
																			// person
																			// and
																			// other
																			// details.
										jobExceptionsView, exceptionData);
								exceptions.get(exceptionData.getSelectedId()).setInitialStatus("");
								// 2019 aug
								Boolean sendMail = false;
								sendException(exceptions.get(exceptionData.getSelectedId()), sendMail);
								// jobExceptionsView.getBtnSave().setText("Exception
								// Sent.");
								jobExceptionsView.getHpnlButtons().setVisible(false);

								exceptionSent(jobExceptionsView, "Sent");

								// jobExceptionsView.getBtnSave().setEnabled(false);
							}
						}
					});

					jobExceptionsView.getBtnApprove().addClickHandler(new ClickHandler() {

						@Override
						public void onClick(ClickEvent event) {
							Boolean sendMail = true;
							// setResponsibleForandDvisionHead(exceptions,
							// jobExceptionsView, exceptionData);
							exceptions.get(exceptionData.getSelectedId()).setInitialStatus("Approved");
							exceptions.get(exceptionData.getSelectedId())
									.setComments(jobExceptionsView.getTxtComments().getText());

							sendException(exceptions.get(exceptionData.getSelectedId()), sendMail);
							// jobExceptionsView.getBtnSave().setText("Exception
							// Sent.");
							// jobExceptionsView.getBtnSave().setEnabled(false);
							jobExceptionsView.getHpnlButtons().setVisible(false);

							exceptionSent(jobExceptionsView, "Approved");
							jobExceptionsView.getStatus().setText("Approved");
							new DisplayAlert("Exception Approved");
						}

					});

					jobExceptionsView.getBtnReject().addClickHandler(new ClickHandler() {

						@Override
						public void onClick(ClickEvent event) {
							Boolean sendMail = false;
							// setResponsibleForandDvisionHead(exceptions,
							// jobExceptionsView, exceptionData);
							exceptions.get(exceptionData.getSelectedId()).setInitialStatus("Rejected");
							exceptions.get(exceptionData.getSelectedId())
									.setComments(jobExceptionsView.getTxtComments().getText());
							sendException(exceptions.get(exceptionData.getSelectedId()), sendMail);
							// jobExceptionsView.getBtnSave().setText("Exception
							// Sent.");
							// jobExceptionsView.getBtnSave().setEnabled(false);
							jobExceptionsView.getHpnlButtons().setVisible(false);

							exceptionSent(jobExceptionsView, "feedback given");
							new DisplayAlert("Exception Rejected");
						}

					});
					////// ADDED 2018
					String status;
					if (exceptions.get(i).getFinalStatus() == null || exceptions.get(i).getFinalStatus().equals("")) {
						status = exceptions.get(i).getStatus();
					} else if (exceptions.get(i).getStatus() == null || exceptions.get(i).getStatus().equals("")) {
						status = exceptions.get(i).getInitialStatus();
					} else {
						status = exceptions.get(i).getFinalStatus();
					}
					if (status != null && status.equals("Sent")) {
						status = "Received";
					}

					if (loggedInEmployee.getRollId() == 1 && status != null && status.equalsIgnoreCase("Approved")) {
						jobExceptionsView.getBtnSave().setVisible(false);
						jobExceptionsView.disableFields();
					}
					////// END
					// if(loggedInEmployee.getRollId().getRollId() ==1 ){
					// jobExceptionsView.getBtnSave().setVisible(false);
					// jobExceptionsView.disableFields();
					// }
				}

			}

			private void exceptionSent(final JobExceptionsView jobExceptionsView, String status) {
				jobExceptionsView.getBtnSave().setVisible(false);
				jobExceptionsView.getStatus().setText(status);
				jobExceptionsView.getStatus().setVisible(true);
			}
		});

	}

	private void setResponsibleForandDvisionHead(final ArrayList<Exceptions> exceptions,
			final JobExceptionsView jobExceptionsView, final JobData exceptionData) {
		Employee responsiblePerson = new Employee();
		responsiblePerson.setEmployeeId(Integer.parseInt(jobExceptionsView.getResponsiblePerson()
				.getValue(jobExceptionsView.getResponsiblePerson().getSelectedIndex())));
		exceptions.get(exceptionData.getSelectedId()).setResponsiblePerson(responsiblePerson);

		Employee divisionHead = new Employee();
		divisionHead.setEmployeeId(Integer.parseInt(
				jobExceptionsView.getDivisionHead().getValue(jobExceptionsView.getDivisionHead().getSelectedIndex())));
		exceptions.get(exceptionData.getSelectedId()).setDivisionHead(divisionHead);
		exceptions.get(exceptionData.getSelectedId()).setDueDate(jobExceptionsView.getDueDate().getValue());
		exceptions.get(exceptionData.getSelectedId())
				.setRecommendations(jobExceptionsView.getRecommendations().getText());
		exceptions.get(exceptionData.getSelectedId())
				.setImplication(jobExceptionsView.getTxtAreaImplication().getText());
		exceptions.get(exceptionData.getSelectedId())
				.setImplicationRating(jobExceptionsView.getListBoxImplicationRating().getSelectedValue().toString());
	}

	private void sendException(Exceptions exception, Boolean sendMail) {
		rpcService.sendException(exception, sendMail, new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable caught) {

				logger.log(Level.INFO, "FAIL: sendException .Inside Audit AuditAreaspresenter");
				if (caught instanceof TimeOutException) {
					History.newItem("login");
				} else {
					System.out.println("FAIL: sendException .Inside AuditAreaspresenter");
					Window.alert("FAIL: sendException");// After FAIL ... write
														// RPC Name NOT Method
														// Name..
				}

			}

			@Override
			public void onSuccess(String result) {
				fetchJobs();
			}
		});

	}

	private void bind() {

		// RootPanel.get("loadingMessage").setVisible(false);

	}

	private void displayLoadinPopup() {
		popupLoading = new DecoratedPopupPanel();
		popupLoading.setSize("100%", "100%");
		popupLoading.setWidget(new Label("Loading..."));
		popupLoading.setGlassEnabled(true);
		popupLoading.center();
	}

}
