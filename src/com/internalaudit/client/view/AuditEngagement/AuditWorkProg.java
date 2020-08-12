package com.internalaudit.client.view.AuditEngagement;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
//import com.google.gwt.rpc.client.RpcService;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.internalaudit.client.InternalAuditServiceAsync;
import com.internalaudit.client.util.MyUtil;
import com.internalaudit.client.view.AmendmentPopup;
import com.internalaudit.client.view.DisplayAlert;
import com.internalaudit.client.view.PopupsView;
import com.internalaudit.client.view.data.DataSetter;
import com.internalaudit.client.widgets.AddImage;
import com.internalaudit.client.widgets.AuditWorkRow;
import com.internalaudit.shared.AuditWork;
import com.internalaudit.shared.Employee;
import com.internalaudit.shared.InternalAuditConstants;
import com.internalaudit.shared.JobCreation;
import com.internalaudit.shared.JobEmployeeRelation;
import com.internalaudit.shared.SuggestedControls;
import com.internalaudit.shared.TimeOutException;
import com.sencha.gxt.core.client.dom.ScrollSupport.ScrollMode;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;

public class AuditWorkProg extends Composite {

	private Logger logger = Logger.getLogger("AuditWorkProg");

	private static AuditWorkProgUiBinder uiBinder = GWT.create(AuditWorkProgUiBinder.class);

	interface AuditWorkProgUiBinder extends UiBinder<Widget, AuditWorkProg> {
	}

	// @UiField
	// Button addMore;

	@UiField
	HorizontalPanel panelAddIcon;

	@UiField
	VerticalPanel rows;
	//
	// @UiField
	// Button save;
	// @UiField
	// Button submit;
	// @UiField
	// Button reject;
	// @UiField
	// Button approve;
	@UiField
	HorizontalPanel approvalButtonsPanel;
	@UiField
	HorizontalPanel initiationButtonsPanel;
	@UiField
	Label submittedBy;

	@UiField
	Label approvedBy;
	@UiField
	Image imgApproved;
	@UiField
	HorizontalPanel heading;
	@UiField
	HorizontalPanel feedbackPanel;
	@UiField
	Label feedback;
	private ArrayList<SuggestedControls> controls = new ArrayList<SuggestedControls>();

	private Employee loggedInEmployee;
	ArrayList<JobEmployeeRelation> listData;
	private ArrayList<AuditWork> savedAuditWorks;
	private int selectedJobId;
	private AddImage addMore = new AddImage();
	private Button save = new Button("Save");
	private Button submit = new Button("Submit");
	private Button approve = new Button("Approve");
	private Button reject = new Button("FeedBack");
	private Button btnLibrary = new Button("Library");
	final VerticalLayoutContainer scrollPopup = new VerticalLayoutContainer();
    private String userPackage;

	public AuditWorkProg(final InternalAuditServiceAsync rpcService, final int selectedJobId, Employee employee,
			String userPackage , ArrayList<SuggestedControls> controls, VerticalPanel auditWorkNewContainer,
			final AsyncCallback<KickoffView> asyncCallback, VerticalPanel vpnlPopup, int sizeAuditProgramList) {
		initWidget(uiBinder.createAndBindUi(this));

		this.controls = controls;
		// fill listbox with appropriate employees
		this.selectedJobId = selectedJobId;
		getEmployeesForJob(rpcService, selectedJobId, auditWorkNewContainer);
		setHandlers(rpcService, selectedJobId, sizeAuditProgramList, vpnlPopup, asyncCallback);
		this.loggedInEmployee = employee;
		this.userPackage = userPackage; 
		addMore.setVisible(false);
		btnLibrary.setVisible(false);
		visibleSaveSubmitBtn(false);
		if (rows.getWidgetCount() < 2) {
			heading.setVisible(false);
			rows.setSpacing(5);
		}
		scrollPopup.add(vpnlPopup);
		scrollPopup.setHeight("530px");
		scrollPopup.setScrollMode(ScrollMode.AUTOY);
	}

	private void setHandlers(final InternalAuditServiceAsync rpcService, final int selectedJobId,
			final int sizeAuditProgramList, final VerticalPanel vpnlPopup, final AsyncCallback<KickoffView> asyncCallback) {
		approvalButtonsPanel.getElement().getStyle().setMarginLeft(1020, Unit.PX);
		initiationButtonsPanel.getElement().getStyle().setMarginTop(25, Unit.PX);
		initiationButtonsPanel.getElement().getStyle().setMarginLeft(1020, Unit.PX);
		panelAddIcon.add(btnLibrary);
		panelAddIcon.add(addMore);
		initiationButtonsPanel.add(save);
		initiationButtonsPanel.add(submit);
		approvalButtonsPanel.add(approve);
		approvalButtonsPanel.add(reject);

		btnLibrary.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				if(vpnlPopup.getWidgetCount() < 1)
					new DisplayAlert("No Library added");
				else {
				final PopupsView popUp = new PopupsView(scrollPopup, "Audit Work Program Library");
//				Button btnClose = new Button("Close");
//				popUp.getVpnlMain().add(btnClose);
//				btnClose.getElement().getStyle().setMarginLeft(530, Unit.PX);
//				btnClose.addClickHandler(new ClickHandler() {
//
//					@Override
//					public void onClick(ClickEvent event) {
//						// TODO Auto-generated method stub
//						popUp.getVpnlMain().removeFromParent();
//						popUp.getPopup().removeFromParent();
//					}
//				});
				}
			}
		});
		
		addMore.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent arg0) {
				addRow(null);
			}

		});

		save.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent arg0) {
				if (rows.getWidgetCount() < 1) {
					Window.alert("please add Audit Work");
				} else {
					saveAuditWork(rpcService, selectedJobId, InternalAuditConstants.SAVED, asyncCallback);
				}
			}
		});

		submit.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent arg0) {
				if (rows.getWidgetCount() < 1) {
					Window.alert("please add Audit Work");
				} else {
					boolean confirmed = Window.confirm("Are you done with Audit Work Program ?");
					if (confirmed) {
						initiationButtonsPanel.setVisible(false);
						saveAuditWork(rpcService, selectedJobId, InternalAuditConstants.SUBMIT, asyncCallback);
					}
				}
			}
		});

		approve.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent arg0) {
				if (rows.getWidgetCount() < 1) {
					Window.alert("No Audit Work to approve");
				} else {
					approvalButtonsPanel.setVisible(false);
					approveAuditWork(rpcService, selectedJobId, InternalAuditConstants.APPROVED, "", asyncCallback);
				}
			}
		});

		reject.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent arg0) {
				if (rows.getWidgetCount() < 1) {
					Window.alert("No auit work to reject");
				} else {
					// approvalButtonsPanel.setVisible(false);
					final AmendmentPopup amendmentPopup = new AmendmentPopup();
					amendmentPopup.popupAmendment();

					amendmentPopup.getBtnSubmit().addClickHandler(new ClickHandler() {

						@Override
						public void onClick(ClickEvent event) {
							approveAuditWork(rpcService, selectedJobId, InternalAuditConstants.REJECTED,
									amendmentPopup.getComments().getText(), asyncCallback);
							amendmentPopup.getPopupComments().removeFromParent();

						}
					});
				}
			}
		});
	}

	//////////////

	public void addRow(final AuditWorkProgramNew auditWorkProgramNew) {
		rows.setSpacing(0);
		visibleSaveSubmitBtn(true);
		if (!heading.isVisible()) {
		//2020 jan hamza
			//	heading.setVisible(true);
		}
		
		
		//save.setVisible(true);
		final AuditWorkRow r = new AuditWorkRow();
		for (int i = 0; i < listData.size(); ++i) {
			if (listData.get(i).getEmployee().getRollId() == 1) {
				r.getEmployeeList().insertItem(listData.get(i).getEmployee().getEmployeeName(),
						String.valueOf(listData.get(i).getEmployee().getEmployeeId()), i);
				break;
			}
		}
		r.getListBoxExistingCtrl().clear();
		for (int j = 0; j < controls.size(); j++) {
			r.getTxtBoxExistingControls().setText(controls.get(0).getSuggestedControlsName());
			r.getListBoxExistingCtrl().addItem(controls.get(j).getSuggestedReferenceNo(),
					controls.get(j).getSuggestedControlsId() + "");
//			for(int k=0; k<r.getListBoxExistingCtrl().getItemCount(); k++) {
//				if(controls.get(j).getSuggestedReferenceNo() == controls.get(0).getSuggestedReferenceNo())
//					r.getListBoxExistingCtrl().setSelectedIndex(k);
//			}
		}
		r.getListBoxExistingCtrl().addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				String name = fetchObjectiveNameAgaintObjectiveId(controls,
						Integer.parseInt(r.getListBoxExistingCtrl().getSelectedValue()));
				r.getTxtBoxExistingControls().setText(name);
			}

			private String fetchObjectiveNameAgaintObjectiveId(ArrayList<SuggestedControls> controls, int value) {
				String name = "";
				for (int i = 0; i < controls.size(); i++) {
					if (controls.get(i).getSuggestedControlsId() == value) {
						// activityObjective = objectives.get(i);
						name = controls.get(i).getSuggestedControlsName();
					}

				}
				return name;
			}
		});
		rows.add(r);

		r.getRemoveRow().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				r.removeRow();
				for (int i = 0; i < rows.getWidgetCount(); i++) {
					if (rows.getWidget(i) == r) {
						rows.remove(i);
					}
				}
//				if (rows.getWidgetCount() < 2) {
//					heading.setVisible(false);
//					rows.setSpacing(5);
//				}
				if(rows.getWidgetCount() < 1)
					visibleSaveSubmitBtn(false);
				auditWorkProgramNew.getBtnSelect().setVisible(true);
			}
		});

		// POPULTE DATA IF COMING FROM LIBRARY WHEN USER SELECT SOME WORK PROG
		// FROM LIBRARY
		if (auditWorkProgramNew != null) {
			r.getDescription().setText(auditWorkProgramNew.getTxtAreaAuditProg().getText());
			r.getTxtBoxExistingControls().setText(auditWorkProgramNew.getLblReferenceData().getText());
			r.getStep().setText(auditWorkProgramNew.getLblReferenceData().getText());
			r.addStyleName("w3-sand");

			for (int i = 0; i < r.getListBoxExistingCtrl().getItemCount(); i++) {
//				if (r.getListBoxExistingCtrl().getItemText(i).equals(auditWorkProgramNew.getLblReferenceData().getText())) {
				//check updated by Moqeet to set listbox value accordingly, 13-7-20
				if (r.getListBoxExistingCtrl().getItemText(i).equals(auditWorkProgramNew.getControl().getSuggestedReferenceNo())) {
					r.getListBoxExistingCtrl().setSelectedIndex(i);
					break;
				}
			}
		} else {
			r.getStep().setText(MyUtil.getRandom());
		}
	}

	private void approveAuditWork(final InternalAuditServiceAsync rpcService, final int selectedJobId, int status,
			String feedback, final AsyncCallback<KickoffView> asyncCallback) {
		ArrayList<AuditWork> auditWorks = new ArrayList<AuditWork>();

		for (int i = 0; i < rows.getWidgetCount(); ++i) {
			AuditWorkRow row = ((AuditWorkRow) rows.getWidget(i));
			if (Integer.parseInt(row.getAuditWorkId().getText()) == 0) {

				row.disableFields();
				AuditWork auditWork = new AuditWork();

				auditWork.setDescription(row.getDescription().getText());
				auditWork.setResponsibleControl(
						Integer.parseInt(row.getLstControls().getValue(row.getLstControls().getSelectedIndex())));
				auditWork.setStepNo(row.getStep().getText());
				JobCreation jobCreation = new JobCreation();
				jobCreation.setJobCreationId(selectedJobId);
				auditWork.setJobCreationId(jobCreation);
				// auditWork.setAuditWorkId( Integer.parseInt(
				// row.getAuditWorkId().getText() ) );
				auditWork.setStatus(status);
				auditWork.setFeedback(feedback);
				Employee initiatedBy = new Employee();
				initiatedBy = loggedInEmployee;
				auditWork.setInitiatedBy(initiatedBy);

				Employee approvedBy = new Employee();
				approvedBy = loggedInEmployee;
				auditWork.setApprovedBy(approvedBy);

				// Add suggested control 2018//
				SuggestedControls suggestedControls = new SuggestedControls();
				if(row.getListBoxExistingCtrl().getSelectedValue()!= null) {
					suggestedControls.setSuggestedControlsId(Integer
							.parseInt(row.getListBoxExistingCtrl().getValue(row.getListBoxExistingCtrl().getSelectedIndex())));
				}
				
				else {
					suggestedControls.setSuggestedControlsId(10000);
				}
				auditWork.setSuggestedControlsId(suggestedControls);
				// control end

				auditWorks.add(auditWork);
			} else {

				for (int j = 0; j < savedAuditWorks.size(); j++) {
					if (Integer.parseInt(row.getAuditWorkId().getText()) == savedAuditWorks.get(j).getAuditWorkId()) {
						AuditWork auditWork = savedAuditWorks.get(j);
						auditWork.setDescription(row.getDescription().getText());
						auditWork.setResponsibleControl(Integer
								.parseInt(row.getLstControls().getValue(row.getLstControls().getSelectedIndex())));

						// if(auditWork.getStatus() ==
						// InternalAuditConstants.APPROVED){
						if (status == InternalAuditConstants.APPROVED || status == InternalAuditConstants.REJECTED) {
							Employee approvedBy = new Employee();
							approvedBy = loggedInEmployee;
							auditWork.setApprovedBy(approvedBy);
						}
						auditWork.setStatus(status);
						auditWork.setFeedback(feedback);

						// Add suggested control 2018//
						SuggestedControls suggestedControls = new SuggestedControls();
						if(row.getListBoxExistingCtrl().getSelectedValue()!= null) {
							suggestedControls.setSuggestedControlsId(Integer.parseInt(row.getListBoxExistingCtrl()
									.getValue(row.getListBoxExistingCtrl().getSelectedIndex())));
							}
						
						else {
							suggestedControls.setSuggestedControlsId(10000);
						}
						
						auditWork.setSuggestedControlsId(suggestedControls);
						// control end

						auditWorks.add(auditWork);
					}
				}
			}

			if (status != InternalAuditConstants.SAVED) {
				row.disableFields();
				disableApprovalpanel();
				disableInitiationpanel();
				disableFields();
			}

		}
		saveAuditWorkTodb(rpcService, auditWorks, status, asyncCallback);
	}

	/////////////

	private void saveAuditWork(final InternalAuditServiceAsync rpcService, final int selectedJobId, int status,
			final AsyncCallback<KickoffView> asyncCallback) {

		ArrayList<AuditWork> auditWorks = new ArrayList<AuditWork>();
		feedbackPanel.setVisible(false);
		for (int i = 0; i < rows.getWidgetCount(); ++i) {
			AuditWorkRow row = ((AuditWorkRow) rows.getWidget(i));
			AuditWork auditWork = new AuditWork();
			auditWork.setDescription(row.getDescription().getText());
			auditWork.setResponsibleControl(
					Integer.parseInt(row.getLstControls().getValue(row.getLstControls().getSelectedIndex())));
			auditWork.setStepNo(row.getStep().getText());			
			JobCreation jobCreation = new JobCreation();
			jobCreation.setJobCreationId(selectedJobId);
			auditWork.setJobCreationId(jobCreation);
			auditWork.setAuditWorkId(Integer.parseInt(row.getAuditWorkId().getText()));
			auditWork.setStatus(status);
			if (status != InternalAuditConstants.SAVED)
			{
				row.disableFields();
				disableApprovalpanel();
				disableInitiationpanel();
				disableFields();
			}
		
			Employee initiatedBy = new Employee();
			initiatedBy = loggedInEmployee;
			auditWork.setInitiatedBy(initiatedBy);

			Employee approvedBy = new Employee();
			approvedBy.setEmployeeId(0);
			auditWork.setApprovedBy(approvedBy);

			// Add suggested control 2018//
			SuggestedControls suggestedControls = new SuggestedControls();
			
			if(row.getListBoxExistingCtrl().getSelectedValue()!= null) {
				suggestedControls.setSuggestedControlsId(Integer
						.parseInt(row.getListBoxExistingCtrl().getValue(row.getListBoxExistingCtrl().getSelectedIndex())));
			}
			
			else {
				suggestedControls.setSuggestedControlsId(10000);
			}
			auditWork.setSuggestedControlsId(suggestedControls);
			// control end

			auditWorks.add(auditWork);

		}

		saveAuditWorkTodb(rpcService, auditWorks, status, asyncCallback);
	}

	private void saveAuditWorkTodb(final InternalAuditServiceAsync rpcService, ArrayList<AuditWork> records,
			final int status, final AsyncCallback<KickoffView> asyncCallback) {
		rpcService.saveAuditWork(records, new AsyncCallback<Void>() {

			@Override
			public void onFailure(Throwable caught) {

				logger.log(Level.INFO, "FAIL: saveAuditWorkTodb .Inside Audit AuditAreaspresenter");
				if (caught instanceof TimeOutException) {
					History.newItem("login");
				} else {
					System.out.println("FAIL: saveAuditWorkTodb .Inside AuditAreaspresenter");
					Window.alert("FAIL: saveAuditWorkTodb");// After FAIL ...
					// write RPC Name
					// NOT Method Name..
				}

			}

			@Override
			public void onSuccess(Void arg0) {
				if (status == 3) {
					new DisplayAlert("Audit Work Saved");
					fetchSavedAuditWork(rpcService, selectedJobId, null);
				} else if (status == 1) {
					new DisplayAlert("Audit Work Approved");
				} else if (status == 2) {
					new DisplayAlert("Feedback submitted");
				} else if (status == 4) {
					new DisplayAlert("Audit Work Submitted");
				}
				asyncCallback.onSuccess(null);
			}

		});
	}

	private void fetchSavedAuditWork(final InternalAuditServiceAsync rpcService, final int selectedJobId,
			final VerticalPanel auditWorkNewContainer) {
		rpcService.fetchAuditWorkRows(selectedJobId, new AsyncCallback<ArrayList<AuditWork>>() {

			@Override
			public void onFailure(Throwable caught) {
				//

				logger.log(Level.INFO, "FAIL: fetchAuditWorkRows .Inside Audit AuditAreaspresenter");
				if (caught instanceof TimeOutException) {
					History.newItem("login");
				} else {
					System.out.println("FAIL: fetchAuditWorkRows .Inside AuditAreaspresenter");
					Window.alert("FAIL: fetchAuditWorkRows");// After FAIL ...
					// write RPC Name
					// NOT Method
					// Name..
				}

			}

			@Override
			public void onSuccess(ArrayList<AuditWork> auditWorks) {
				rows.clear();
				savedAuditWorks = auditWorks;
				if (auditWorks.size() < 1) {//
					enableInitiationpanel();
					enableFields();
				} else {// Just to show Approved By/ Submitted By ...
				//2020 6 hamza
					//	heading.setVisible(true);
					visibleSaveSubmitBtn(true);
					if (auditWorks.get(0).getApprovedBy() != null
							&& auditWorks.get(0).getApprovedBy().getEmployeeId() != 0
							&& auditWorks.get(0).getStatus() == InternalAuditConstants.APPROVED) {
						approvedBy.setVisible(true);
						approvedBy.setText("Approved by:" + auditWorks.get(0).getApprovedBy().getEmployeeName());
						if (auditWorks.get(0).getApprovedBy().getRollId() == 1
								&& auditWorks.get(0).getStatus() == InternalAuditConstants.APPROVED) {
							imgApproved.setVisible(true);
						}
						submittedBy.setVisible(true);
						submittedBy
								.setText("Initiated by:" + auditWorks.get(0).getInitiatedBy().getEmployeeName() + "::");

						auditWorkNewContainer.clear();

					}
					// Displayig rows..

					for (AuditWork auditWork : auditWorks) {
						initiationButtonsPanel.setVisible(false);
						approvalButtonsPanel.setVisible(false);
						addMore.setVisible(false);
						btnLibrary.setVisible(false);

						final AuditWorkRow row = new AuditWorkRow();
						row.disableFields();

						if ((auditWork.getInitiatedBy() != null && auditWork.getInitiatedBy().getReportingTo()
								.getEmployeeId() == loggedInEmployee.getEmployeeId()
								|| loggedInEmployee.getRollId() == 1)
								&& (auditWork.getStatus() == InternalAuditConstants.SUBMIT)) {
							showApprovalView(row);
						} else if (!(auditWork.getApprovedBy().getRollId() == 1) && loggedInEmployee.getRollId() == 1
								&& (auditWork.getStatus() == InternalAuditConstants.APPROVED
										|| auditWork.getStatus() == InternalAuditConstants.SUBMIT)) {
							showApprovalView(row);
						}

						else if (auditWork.getInitiatedBy() != null
								&& auditWork.getInitiatedBy().getEmployeeId() == loggedInEmployee.getEmployeeId()
								&& (auditWork.getStatus() == InternalAuditConstants.SAVED
										|| auditWork.getStatus() == InternalAuditConstants.REJECTED)) {
							showInitiationView(row);

						}
						/// To show feedback
						if (auditWork.getFeedback() != null && !auditWork.getFeedback().isEmpty()) {
							feedbackPanel.setVisible(true);
							feedback.setText(auditWork.getFeedback());
						}
						//// end feedback
						row.getStep().setText(auditWork.getStepNo());
						row.getDescription().setText(auditWork.getDescription());
						row.getAuditWorkId().setText(String.valueOf(auditWork.getAuditWorkId()));
						// Setting suggested control
						row.getListBoxExistingCtrl().clear();
						if(controls.size() == 0) {
							row.getTxtBoxExistingControls().setText(auditWork.getSuggestedControlsId().getSuggestedControlsName());
							row.getLblControls().setVisible(false);
							row.getContainerExistingControls().setVisible(false);
						}
						
						for (int j = 0; j < controls.size(); j++) {
							row.getListBoxExistingCtrl().addItem(controls.get(j).getSuggestedReferenceNo(),
									controls.get(j).getSuggestedControlsId() + "");
							row.getListBoxExistingCtrl().setTitle(controls.get(j).getSuggestedControlsName());
						}
						
						for (int i = 0; i < row.getListBoxExistingCtrl().getItemCount(); i++) {
							if (Integer.parseInt(row.getListBoxExistingCtrl().getValue(i)) == auditWork
									.getSuggestedControlsId().getSuggestedControlsId()) {
								row.getListBoxExistingCtrl().setSelectedIndex(i);
								//existing control is set by stepNo,sets library selected 
								//and stepNo sets manual add
//								if(!auditWork.getStepNo().matches("^[0-9]*$"))
//								row.getTxtBoxExistingControls().setText(auditWork.getStepNo());
//								else
								row.getTxtBoxExistingControls().setText(auditWork.getSuggestedControlsId().getSuggestedControlsName());
								//july 2020 set stepNo instead of ControlName
								break;
							}
						}
						// set selected emp;
						for (int i = 0; i < listData.size(); ++i) {
							if (listData.get(i).getEmployee().getRollId() == 1) {// change
								row.getEmployeeList().setEnabled(false);// change
								// row.getEmployeeList().insertItem(listData.get(i).getEmployee().getEmployeeName(),
								// String.valueOf(listData.get(i).getEmployee().getEmployeeId()),i);
								row.getEmployeeList().addItem(listData.get(i).getEmployee().getEmployeeName(),
										String.valueOf(listData.get(i).getEmployee().getEmployeeId()));

							}
							if (auditWork.getResponsibleControl() == listData.get(i).getEmployee().getEmployeeId()) {
								row.getEmployeeList().setSelectedIndex(i);
							}
						}
						row.getEmployeeList().setSelectedIndex(0);

						rows.add(row);
						final DataSetter dataSetter = new DataSetter();
						dataSetter.setId(auditWork.getAuditWorkId());

						row.getRemoveRow().addClickHandler(new ClickHandler() {

							@Override
							public void onClick(ClickEvent event) {
								if (Window.confirm("Are you sure you want to remove this Audit Work?")) {
									row.removeRow();
									rows.remove(row);
									deleteAuditWork(dataSetter.getId(), rpcService);
//									if (rows.getWidgetCount() < 2) {
//										heading.setVisible(false);
//										rows.setSpacing(5);
//									}
									if(rows.getWidgetCount() < 1)
										visibleSaveSubmitBtn(false);
								}
							}

						});

					}

				}
			}

			private void showInitiationView(AuditWorkRow row) {
				approvalButtonsPanel.setVisible(false);
				initiationButtonsPanel.setVisible(true);
				addMore.setVisible(true);
				btnLibrary.setVisible(true);
				row.enableFields();
				packageFeature();
			}
//
			private void showApprovalView(AuditWorkRow row) {
				approvalButtonsPanel.setVisible(true);
				addMore.setVisible(false);
				btnLibrary.setVisible(false);
				initiationButtonsPanel.setVisible(false);
				row.enableFields();
			}

		});
	}

	private void deleteAuditWork(int auditWorkId, InternalAuditServiceAsync rpcService) {

		rpcService.deleteAuditWork(auditWorkId, new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Fail:AuditWork Delete");
			}

			@Override
			public void onSuccess(String result) {
				new DisplayAlert("Audit Work removed");

			}
		});
	}

	private void getEmployeesForJob(final InternalAuditServiceAsync rpcService, final int selectedJobId,
			final VerticalPanel auditWorkNewContainer) {
		rpcService.fetchEmployeeJobRelations(selectedJobId, new AsyncCallback<ArrayList<JobEmployeeRelation>>() {

			@Override
			public void onFailure(Throwable caught) {

				logger.log(Level.INFO, "FAIL: fetchEmployeeJobRelations .Inside Audit AuditAreaspresenter");
				if (caught instanceof TimeOutException) {
					History.newItem("login");
				} else {
					System.out.println("FAIL: fetchEmployeeJobRelations .Inside AuditAreaspresenter");
					Window.alert("FAIL: fetchEmployeeJobRelations");// After
					// FAIL ...
					// write RPC
					// Name NOT
					// Method
					// Name..
				}

			}

			@Override
			public void onSuccess(ArrayList<JobEmployeeRelation> arg0) {
				/*
				 * 
				 * Here I am getting correct emp for job need to put this in
				 * list boxes
				 */

				if (arg0.size() > 0) {

					rows.clear();
					listData = arg0;
					// display 3 rows as default
					for (int k = 0; k < 3; ++k) {
						AuditWorkRow r = new AuditWorkRow();
						for (int i = 0; i < arg0.size(); ++i) {
							r.getEmployeeList().insertItem(arg0.get(i).getEmployee().getEmployeeName(),
									String.valueOf(arg0.get(i).getEmployee().getEmployeeId()), i);
						}

						rows.add(r);
					}

				}

				fetchSavedAuditWork(rpcService, selectedJobId, auditWorkNewContainer);

			}

		});
	}

	public void enableInitiationpanel() {
		initiationButtonsPanel.setVisible(true);
		addMore.setVisible(true);
		btnLibrary.setVisible(true);
		packageFeature();
	}

	public void disableInitiationpanel() {
		initiationButtonsPanel.setVisible(false);
		addMore.setVisible(false);
		btnLibrary.setVisible(false);
	}

	public void enableApprovalnpanel() {
		approvalButtonsPanel.setVisible(true);
		addMore.setVisible(true);
		btnLibrary.setVisible(true); 
		packageFeature();
	}

	public void disableApprovalpanel() {
		approvalButtonsPanel.setVisible(false);
		addMore.setVisible(false);
		btnLibrary.setVisible(false);
	}

	public void disableFields() {
		addMore.setVisible(false);
		save.setVisible(false);
		btnLibrary.setVisible(false);
	}

	public void enableFields() {
		addMore.setVisible(true);
		btnLibrary.setVisible(true);
		packageFeature();
	}
	
	private void visibleSaveSubmitBtn(boolean flag) {
		save.setVisible(flag);
		submit.setVisible(flag);
		initiationButtonsPanel.setVisible(flag);
	}
	
	public void packageFeature( ) {
		if(userPackage.equalsIgnoreCase("Basic") || userPackage.equalsIgnoreCase("Gold")) {
			btnLibrary.setVisible(false);
			addMore.getElement().getStyle().setPaddingLeft(1150, Unit.PX);
		}
		else
			addMore.getElement().getStyle().setPaddingLeft(1080, Unit.PX);
	}
}
