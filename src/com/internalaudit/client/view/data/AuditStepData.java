package com.internalaudit.client.view.data;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent;
import com.google.gwt.user.client.ui.FormPanel.SubmitEvent;
import com.google.gwt.user.client.ui.FormPanel.SubmitHandler;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.internalaudit.client.InternalAuditService;
import com.internalaudit.client.InternalAuditServiceAsync;
import com.internalaudit.client.view.DisplayAlert;
import com.internalaudit.client.view.LoadingPopup;
import com.internalaudit.client.view.AuditEngagement.AuditStepUploads;
import com.internalaudit.client.view.AuditEngagement.AuditStepView;
import com.internalaudit.client.view.AuditEngagement.SamplingAuditStep;
import com.internalaudit.client.widgets.ExceptionRow;
import com.internalaudit.shared.AuditStep;
import com.internalaudit.shared.AuditWork;
import com.internalaudit.shared.Employee;
import com.internalaudit.shared.Exceptions;
import com.internalaudit.shared.InternalAuditConstants;
import com.internalaudit.shared.TimeOutException;

public class AuditStepData {

	private InternalAuditServiceAsync rpcService = GWT.create(InternalAuditService.class);
	private Logger logger = Logger.getLogger("AuditStepData");

	public AuditStepData() {

	}

	public void saveAuditStepAndException(AuditStep step, ArrayList<Exceptions> exs, final int status,

			final AuditStepView auditStepView) {

		final LoadingPopup loadingPopup = new LoadingPopup();
		loadingPopup.display();
		rpcService.saveAuditStepAndExceptions(step, exs, new AsyncCallback<Void>() {

			@Override
			public void onFailure(Throwable caught) {
				loadingPopup.remove();
				System.out.println("Fail RPC:saveAuditStepAndException  In Audit Step Data");

				logger.log(Level.INFO, "FAIL: saveAuditStepAndExceptions .Inside Audit AuditAreaspresenter");
				if (caught instanceof TimeOutException) {
					History.newItem("login");
				} else {
					System.out.println("FAIL: saveAuditStepAndExceptions .Inside AuditAreaspresenter");
					Window.alert("FAIL: saveAuditStepAndExceptions");// After
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
				loadingPopup.remove();
				auditStepView.getFeedbackPanel().setVisible(false);
				if (status == 3) {
					new DisplayAlert("Audit Step Saved");
					auditStepView.fetchSavedAuditStep();
				} else if (status == 1) {
					new DisplayAlert("Audit Step Approved");
				} else if (status == 2) {
					new DisplayAlert("Feedback submitted");
				} else if (status == 4) {
					new DisplayAlert("Audit Step Submitted");
				}
			}

		});

	}

	private void updateFileNameInDatabase(int auditStepId) {
		InternalAuditServiceAsync rpcService = GWT.create(InternalAuditService.class);
		rpcService.updateUploadedAuditStepFile(auditStepId, new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable arg0) {
				System.out.println("fail: updateFileNameInDatabase");
			}

			@Override
			public void onSuccess(String result) {
				System.out.println(result);
			}
		});

	}

	public void getSavedAuditStep(final AuditStepView auditStepView, final SamplingAuditStep auditSamplingView,
			int selectedJobId, final AuditWork auditWork, final VerticalPanel exceptions,
			final Employee loggedInEmployee) {

		rpcService.getSavedAuditStep(selectedJobId, auditWork.getAuditWorkId(), new AsyncCallback<AuditStep>() {

			@Override
			public void onFailure(Throwable caught) {

				logger.log(Level.INFO, "FAIL: getSavedAuditStep .Inside Audit AuditAreaspresenter");
				if (caught instanceof TimeOutException) {
					History.newItem("login");
				} else {
					System.out.println("FAIL: getSavedAuditStep .Inside AuditAreaspresenter");
					Window.alert("FAIL: getSavedAuditStep");// After FAIL ...
															// write RPC Name
															// NOT Method Name..
				}

			}

			@Override
			public void onSuccess(final AuditStep auditStep) {
				auditSamplingView.getListBoxSamplingMethod().setSelectedIndex(auditStep.getSamplingMethod());
				auditSamplingView.getListBoxControlList().setSelectedIndex(auditStep.getControlList());
				auditSamplingView.getListBoxFrequency().setSelectedIndex(auditStep.getFrequency());
				auditSamplingView.getLblSampleSizeData().setText(auditStep.getSampleSelected());
				auditSamplingView.getLblPopulationData().setText(auditStep.getPopulation());
				auditSamplingView.getTxtAreaAuditProcedure().setText(auditStep.getProceducePerformance());
				if (auditStep.getAuditStepId() != 0) {
					// auditStepView.disableFields(exceptions);
					displayExceptions(exceptions, auditStep.getExceptions());
					// auditStepView.disableFields(exceptions,
					// auditSamplingView);
					//// CHECK WHO IS LOGGEDIN
					/// To show feedback
					if (auditStep.getFeedback() != null && !auditStep.getFeedback().isEmpty()) {
						auditStepView.getFeedbackPanel().setVisible(true);
						auditStepView.getFeedback().setText(auditStep.getFeedback());
					}
					//// end feedbacl

					// if(auditWorks.size()<1){//
					// enableInitiationpanel();
					// enableFields();
					// }else{// Just to show Approved By/ Submitted By ...
					if (auditStep.getApprovedBy() != null && auditStep.getApprovedBy().getEmployeeId() != 0
							&& auditStep.getStatus() == InternalAuditConstants.APPROVED) {
						auditStepView.getApprovedBy().setVisible(true);
						auditStepView.getApprovedBy()
								.setText("Approved by:" + auditStep.getApprovedBy().getEmployeeName());
						if (auditStep.getApprovedBy().getRollId() == 1) {
							auditStepView.getImgApproved().setVisible(true);
						}
						auditStepView.getSubmittedBy().setVisible(true);
						auditStepView.getSubmittedBy()
								.setText("Initiated by:" + auditStep.getInitiatedBy().getEmployeeName() + "::");
					}
					// }

					// if(auditWork.getJobCreationId().getAuditHead() ==
					// loggedInEmployee.getEmployeeId()){
					// auditStepView.supervisorView();
					// if(auditStep.getStatus() ==
					// InternalAuditConstants.APPROVED || auditStep.getStatus()
					// == InternalAuditConstants.REJECTED){
					// auditStepView.getApprove().setEnabled(false);
					// auditStepView.getReject().setEnabled(false);
					// }
					//
					// }else{
					// auditStepView.getApprovalButtonsPanel().setVisible(false);
					// auditStepView.getSave().setVisible(true);
					// if(auditStep.getStatus() ==
					// InternalAuditConstants.APPROVED || auditStep.getStatus()
					// == InternalAuditConstants.INITIATED){
					// auditStepView.disableFields();
					// auditStepView.getSave().setEnabled(false);
					// }
					// }

					if ((auditStep.getInitiatedBy() != null && auditStep.getInitiatedBy().getReportingTo()
							.getEmployeeId() == loggedInEmployee.getEmployeeId() || loggedInEmployee.getRollId() == 1)
							&& (auditStep.getStatus() == InternalAuditConstants.SUBMIT)) {
						auditStepView.supervisorView();

					} else if (!(auditStep.getApprovedBy().getRollId() == 1) && loggedInEmployee.getRollId() == 1) {
						auditStepView.supervisorView();
					}

					else if (auditStep.getInitiatedBy() != null
							&& auditStep.getInitiatedBy().getEmployeeId() == loggedInEmployee.getEmployeeId()
							&& (auditStep.getStatus() == InternalAuditConstants.SAVED
									|| auditStep.getStatus() == InternalAuditConstants.REJECTED)) {
						auditStepView.enableFields();
					}
					if (auditStep.getStatus() == InternalAuditConstants.APPROVED
							&& auditStep.getApprovedBy().getRollId() == 1) {
						auditStepView.disableFields(exceptions, auditSamplingView);
					}
					///
					// System.out.println(loggedInEmployee.getEmployeeId());
					auditStepView.getPerformance().setText(auditStep.getProceducePerformance());
					// SamplingAuditStep s = new
					// SamplingAuditStep(auditStep.getAuditStepId() + "");
					// s.getListBoxFrequency().setItemSelected(Integer.parseInt(auditStep.getPopulation()),
					// true);
					// s.getListBoxControlList().setSelectedIndex(2);
					// s.getListBoxSamplingMethod().setSelectedIndex(2);
					// s.getTxtAreaAuditProcedure().setText("hihihihih");
					auditStepView.getPopulation().setText(auditStep.getPopulation());
					auditStepView.getSample().setText(auditStep.getSampleSelected());
					auditStepView.getSelection().setText(auditStep.getSelectionBasis());
					// this is how I set id, when needed for update
					auditStepView.getAuditStepId().setText(String.valueOf(auditStep.getAuditStepId()));

					///////////////////////////////
					fileUpload(auditStepView, auditStep);
					///

					if ("Satisfactory".equals(auditStep.getConclusion()))
						auditStepView.getConclusion().setSelectedIndex(0);
					else {
						auditStepView.getConclusion().setSelectedIndex(1);
					}
					// displayExceptions(exceptions, auditStep.getExceptions());
					// auditStepView.disableFields(exceptions);
				} else {
					auditStepView.enableFields();
				}
			}

			private void fileUpload(final AuditStepView auditStepView, final AuditStep auditStep) {
				AuditStepUploads auditStepUpload = new AuditStepUploads("0");

				auditStepUpload.getForm().addSubmitHandler(new SubmitHandler() {

					@Override
					public void onSubmit(SubmitEvent arg0) {
						saveSlectedAuditStepIdInSession(auditStep.getAuditStepId());

					}
				});

				auditStepUpload.getForm().addSubmitCompleteHandler(new FormPanel.SubmitCompleteHandler() {
					public void onSubmitComplete(SubmitCompleteEvent event) {
						if (event.getResults().contains("success")) {
							Window.alert("File uploaded");
							updateFileNameInDatabase(auditStep.getAuditStepId());

						} else {
							Window.alert(event.getResults());
						}
					}

				});
				if (auditStep.getStatus() != InternalAuditConstants.APPROVED) {
					// auditStepView.getUploadFileContainer().add(auditStepUpload);
				}

				Anchor anchorUploadedFile = new Anchor(auditStep.getUploadedFile());
				auditStepView.getUploadFileContainer().add(anchorUploadedFile);
				anchorUploadedFile.addClickHandler(new ClickHandler() {

					@Override
					public void onClick(ClickEvent arg0) {
						// Window.open("/InternalAudit/"+auditStep.getAuditStepId()+"/"+auditStep.getUploadedFile(),
						// "_blank", "");
						// Window.open(GWT.getHostPageBaseURL() +
						// "/AuditSteps//"+auditStep.getAuditStepId()+"//"+auditStep.getUploadedFile(),
						// "name", "enabled");
						Window.open("AuditSteps/" + auditStep.getAuditStepId() + "/" + auditStep.getUploadedFile(),
								"_blank", "");

					}
				});
			}
		});

	}

	private void saveSlectedAuditStepIdInSession(final int auditStepId) {
		rpcService.saveSelectedAuditStepIdInSession(auditStepId, new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable arg0) {
				System.out.println("FAIL: saveSlectedAuditStepIdInSession " + auditStepId);
			}

			@Override
			public void onSuccess(String arg0) {
				System.out.println("saveSlectedAuditStepIdInSession " + auditStepId);
			}
		});
	}
	//
	// public void getSavedExceptions(final VerticalPanel exceptions, int
	// selectedJobId) {
	//
	// rpcService.getSavedExceptions( selectedJobId, new
	// AsyncCallback<ArrayList<Exceptions>>() {
	//
	// @Override
	// public void onFailure(Throwable caught) {
	// //error with fail
	// Window.alert("Fail getting saved ex");
	//
	//
	// logger.log(Level.INFO, "FAIL: getSavedExceptions .Inside Audit
	// AuditAreaspresenter");
	// if(caught instanceof TimeOutException){
	// History.newItem("login");
	// }else{
	// System.out.println("FAIL: getSavedExceptions .Inside
	// AuditAreaspresenter");
	// Window.alert("FAIL: getSavedExceptions");// After FAIL ... write RPC Name
	// NOT Method Name..
	// }
	//
	//
	// }
	//
	// @Override
	// public void onSuccess(ArrayList<Exceptions> arg0) {
	//
	// // displayExceptions(exceptions, arg0);
	// }
	//
	//
	//
	// });
	//
	// }

	private void displayExceptions(final VerticalPanel exceptions, ArrayList<Exceptions> arg0) {
		exceptions.clear();

		for (Exceptions e : arg0) {

			final ExceptionRow row = new ExceptionRow(e.getExceptionId());// employee
			row.getExId().setText(String.valueOf(e.getExceptionId()));
			row.getException().setText(String.valueOf(e.getDetail()));
			exceptions.add(row);
			final DataSetter dataSetter = new DataSetter();
			dataSetter.setId(e.getExceptionId());

			// Remove Saved Row...
			row.getRemoveRow().addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					if (Window.confirm("Are you sure you want to remove this Exception ?")) {
						row.removeRow();
						exceptions.remove(row);
						deleteException(dataSetter.getId());

					}
				}

			});
			//

		}
	}

	private void deleteException(int id) {
		rpcService.deleteException(id, new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable caught) {
				System.out.println("Exception in deleteException");
			}

			@Override
			public void onSuccess(String result) {
				new DisplayAlert("Exception removed");
			}
		});
	}

}
