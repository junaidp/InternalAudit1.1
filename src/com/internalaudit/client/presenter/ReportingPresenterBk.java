package com.internalaudit.client.presenter;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.DecoratedPopupPanel;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.internalaudit.client.InternalAuditServiceAsync;
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

public class ReportingPresenterBk implements Presenter

{
	private final InternalAuditServiceAsync rpcService;
	private final HandlerManager eventBus;
	private final Display display;
	private DecoratedPopupPanel popupLoading;
	private ArrayList<Employee> employeesList = new ArrayList<Employee>();
	private Employee loggedInEmployee;
	private Logger logger = Logger.getLogger("ReportingPresenterBk");

	public interface Display {
		Widget asWidget();

		VerticalPanel getVpnlReporting();

		VerticalPanel getVpnlJobs();

		VerticalPanel getVpnlSelectedJob();

	}

	public ReportingPresenterBk(InternalAuditServiceAsync rpcService, HandlerManager eventBus, Employee employee,
			Display view) {
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.display = view;
		this.loggedInEmployee = employee;
	}

	public void go(HasWidgets container) {
		container.clear();
		container.add(display.asWidget());
		bind();
		if (loggedInEmployee.getFromInternalAuditDept().equals("yes")) {
			fetchAuditHeadExceptions();

		} else {
			fetchUserExceptions();

		}

	}

	private void fetchAuditHeadExceptions() {
		rpcService.fetchAuditHeadExceptions(loggedInEmployee.getEmployeeId(), 0,
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

						if (result.size() > 0) {
							display.getVpnlReporting().clear();
							ResponsiblePersonRowHeadingView responsiblePersonView = new ResponsiblePersonRowHeadingView();
							display.getVpnlReporting().add(responsiblePersonView);

							for (int i = 0; i < result.size(); i++) {
								final ResponsiblePersonRowView responsiblePersonRowView = new ResponsiblePersonRowView();
								responsiblePersonRowView.getException().setText(result.get(i).getDetail());
								responsiblePersonRowView.getAuditJob().setText(result.get(i).getJobName());
								responsiblePersonRowView.getStatus().setText(result.get(i).getStatus());
								responsiblePersonView.add(responsiblePersonRowView);
								final JobData jobData = new JobData();
								jobData.setSelectedId(i);
								if (!result.get(i).getManagementComments().equals("")) {
									responsiblePersonRowView.getBtnSend().setVisible(false);
									responsiblePersonRowView.getVpnlApprovalButton().setVisible(true);
									responsiblePersonRowView.getManagementComments()
											.setText(result.get(i).getManagementComments());
									responsiblePersonRowView.getImplementaionDate()
											.setValue(result.get(i).getImplementaionDate());

									responsiblePersonRowView.disableFields();
									if (result.get(i).getStatus() != null && !result.get(i).getStatus().equals("")) {

										responsiblePersonRowView.getBtnApprove().setEnabled(false);
										responsiblePersonRowView.getBtnReject().setEnabled(false);
									}
									/// FOR ACTUAL IMPLEMENTATION APPROVAL....
									if (result.get(i).getImplementaionComments() != null
											&& !result.get(i).getImplementaionComments().equals("")) {
										responsiblePersonRowView.getHpnl2().setVisible(true);
										responsiblePersonRowView.getImplementaionComments()
												.setText(result.get(i).getImplementaionComments());
										responsiblePersonRowView.getIsImplemented()
												.setSelectedIndex(result.get(i).getIsImplemented());
										responsiblePersonRowView.getImplementaionComments().setEnabled(false);
										responsiblePersonRowView.getIsImplemented().setEnabled(false);
										responsiblePersonRowView.getBtnApprove().setEnabled(true);
										responsiblePersonRowView.getBtnReject().setEnabled(true);
										responsiblePersonRowView.getStatus().setText(result.get(i).getFinalStatus());

										if (result.get(i).getFinalStatus() != null
												&& !result.get(i).getFinalStatus().equals("")) {
											responsiblePersonRowView.getBtnApprove().setEnabled(false);
											responsiblePersonRowView.getBtnReject().setEnabled(false);
										}
									}
								}

								responsiblePersonRowView.getBtnApprove().addClickHandler(new ClickHandler() {

									@Override
									public void onClick(ClickEvent event) {
										if (result.get(jobData.getSelectedId()).getImplementaionComments() != null
												&& !result.get(jobData.getSelectedId()).getImplementaionComments()
														.equals("")) {
											result.get(jobData.getSelectedId()).setFinalStatus("Approved");
										} else {
											result.get(jobData.getSelectedId()).setStatus("Approved");
										}
										responsiblePersonRowView.getStatus().setText("Approved");
										responsiblePersonRowView.getBtnApprove().setEnabled(false);
										responsiblePersonRowView.getBtnReject().setEnabled(false);

										sendException(result.get(jobData.getSelectedId()));

									}
								});

								responsiblePersonRowView.getBtnReject().addClickHandler(new ClickHandler() {

									@Override
									public void onClick(ClickEvent event) {
										if (result.get(jobData.getSelectedId()).getImplementaionComments() != null
												&& !result.get(jobData.getSelectedId()).getImplementaionComments()
														.equals("")) {
											result.get(jobData.getSelectedId()).setFinalStatus("Rejected");
										} else {
											result.get(jobData.getSelectedId()).setStatus("Rejected");
										}
										responsiblePersonRowView.getStatus().setText("Rejected");
										responsiblePersonRowView.getBtnApprove().setEnabled(false);
										responsiblePersonRowView.getBtnReject().setEnabled(false);
										sendException(result.get(jobData.getSelectedId()));

									}
								});
							}
						} else {
							fetchEmployees();
							fetchJobs();
						}
					}
				});
	}

	private void fetchUserExceptions() {
		rpcService.fetchEmployeeExceptions(loggedInEmployee.getEmployeeId(), 0,
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
						if (result.size() > 0) {
							ResponsiblePersonRowHeadingView responsiblePersonView = new ResponsiblePersonRowHeadingView();
							for (int i = 0; i < result.size(); i++) {
								final ResponsiblePersonRowView responsiblePersonRowView = new ResponsiblePersonRowView();
								responsiblePersonRowView.getException().setText(result.get(i).getDetail());
								responsiblePersonRowView.getAuditJob().setText(result.get(i).getJobName());
								responsiblePersonRowView.getStatus().setText(result.get(i).getStatus());
								responsiblePersonView.add(responsiblePersonRowView);
								final JobData jobData = new JobData();
								jobData.setSelectedId(i);

								if (result.get(i).getStatus() != null
										&& result.get(i).getStatus().equalsIgnoreCase("rejected")) {
									responsiblePersonRowView.getBtnSend().setText("Send Again");
									responsiblePersonRowView.getManagementComments()
											.setText(result.get(i).getManagementComments());
									responsiblePersonRowView.getImplementaionDate()
											.setValue(result.get(i).getImplementaionDate());

								} else if (result.get(i).getManagementComments() != null
										&& !result.get(i).getManagementComments().equals("")) {
									responsiblePersonRowView.getBtnSend().setText("Sent.");
									responsiblePersonRowView.getManagementComments()
											.setText(result.get(i).getManagementComments());
									responsiblePersonRowView.getImplementaionDate()
											.setValue(result.get(i).getImplementaionDate());

									responsiblePersonRowView.disableFields();
									/// Work for Actual Implementaion for
									/// Responsible person..
									if (result.get(i).getStatus() != null
											&& result.get(i).getStatus().equalsIgnoreCase("approved")) {
										responsiblePersonRowView.getHpnl2().setVisible(true);
										responsiblePersonRowView.getBtnSend().setText("Send");
										responsiblePersonRowView.getBtnSend().setEnabled(true);

										if (result.get(i).getImplementaionComments() != null
												&& !result.get(i).getImplementaionComments().equals("")) {
											responsiblePersonRowView.getImplementaionComments().setEnabled(false);
											responsiblePersonRowView.getIsImplemented().setEnabled(false);
											responsiblePersonRowView.getImplementaionComments()
													.setText(result.get(i).getImplementaionComments());
											responsiblePersonRowView.getIsImplemented()
													.setSelectedIndex(result.get(i).getIsImplemented());
											responsiblePersonRowView.getStatus()
													.setText(result.get(i).getFinalStatus());
											responsiblePersonRowView.getBtnSend().setText("Send");
											responsiblePersonRowView.getBtnSend().setEnabled(true);
										}
										if (result.get(i).getFinalStatus() != null
												&& result.get(i).getFinalStatus().equalsIgnoreCase("approved")) {
											responsiblePersonRowView.getImplementaionComments().setEnabled(false);
											responsiblePersonRowView.getIsImplemented().setEnabled(false);
											responsiblePersonRowView.getBtnSend().setText("Sent.");
											responsiblePersonRowView.getBtnSend().setEnabled(false);

										} else if (result.get(i).getFinalStatus() != null
												&& result.get(i).getFinalStatus().equalsIgnoreCase("rejected")) {
											responsiblePersonRowView.getImplementaionComments().setEnabled(true);
											responsiblePersonRowView.getIsImplemented().setEnabled(true);
											responsiblePersonRowView.getBtnSend().setText("Send");
											responsiblePersonRowView.getBtnSend().setEnabled(true);
										}

									}
								}

								responsiblePersonRowView.getBtnSend().addClickHandler(new ClickHandler() {

									@Override
									public void onClick(ClickEvent event) {
										result.get(jobData.getSelectedId()).setImplementaionDate(
												responsiblePersonRowView.getImplementaionDate().getValue());
										result.get(jobData.getSelectedId()).setManagementComments(
												responsiblePersonRowView.getManagementComments().getText());
										// For Actual Implementation of
										// responsible person ..Sending actual
										// imeplemetaion stats
										if (responsiblePersonRowView.getImplementaionComments() != null
												&& !responsiblePersonRowView.getImplementaionComments().getText()
														.equals("")) {
											result.get(jobData.getSelectedId()).setImplementaionComments(
													responsiblePersonRowView.getImplementaionComments().getText());
											result.get(jobData.getSelectedId())
													.setIsImplemented(Integer.parseInt(responsiblePersonRowView
															.getIsImplemented().getValue(responsiblePersonRowView
																	.getIsImplemented().getSelectedIndex())));
											responsiblePersonRowView.getImplementaionComments().setEnabled(false);
											responsiblePersonRowView.getIsImplemented().setEnabled(false);
											result.get(jobData.getSelectedId()).setFinalStatus("");
										} else {
											result.get(jobData.getSelectedId()).setStatus("");
										}

										responsiblePersonRowView.disableFields();
										responsiblePersonRowView.getBtnSend().setText("Sent.");
										responsiblePersonRowView.getStatus().setText("");
									}
								});
							}
							// display.getVpnlReporting().clear();
							// display.getVpnlReporting().add(responsiblePersonView);
							display.getVpnlSelectedJob().clear();
							display.getVpnlSelectedJob().add(responsiblePersonView);
						}
					}
				});

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
		rpcService.fetchJobs(new AsyncCallback<ArrayList<JobCreation>>() {

			@Override
			public void onFailure(Throwable caught) {

				logger.log(Level.INFO, "FAIL: fetchJobs .Inside Audit AuditAreaspresenter");
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
				AllJobsView allJobsView = new AllJobsView();

				for (int i = 0; i < result.size(); i++) {

					final JobReportView jobReportView = new JobReportView();
					if (result.get(i).getReportStatus() != 0) {
						allJobsView.add(jobReportView);
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

						}

					});
				}

			}
		});

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

					if (exceptions.get(i).getResponsiblePerson().getEmployeeId() != 0) {
						jobExceptionsView.getBtnSave().setText("Exception Sent.");
						jobExceptionsView.disableFields();
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
							setResponsibleForandDvisionHead(exceptions, jobExceptionsView, exceptionData);

							sendException(exceptions.get(exceptionData.getSelectedId()));
							jobExceptionsView.getBtnSave().setText("Exception Sent.");
							jobExceptionsView.getBtnSave().setEnabled(false);
						}

					});
				}
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
	}

	private void sendException(Exceptions exception) {
		Boolean sendMail = false;
		rpcService.sendException(exception, sendMail, "", new AsyncCallback<String>() {

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
				// Window.alert("Exception sent");
			}
		});

	}

	private void bind() {

		RootPanel.get("loadingMessage").setVisible(false);

	}

	private void displayLoadinPopup() {
		popupLoading = new DecoratedPopupPanel();
		popupLoading.setSize("100%", "100%");
		popupLoading.setWidget(new Label("Loading..."));
		popupLoading.setGlassEnabled(true);
		popupLoading.center();
	}

}
