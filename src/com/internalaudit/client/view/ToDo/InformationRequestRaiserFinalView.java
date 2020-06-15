package com.internalaudit.client.view.ToDo;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.FontWeight;
import com.google.gwt.dom.client.Style.TextDecoration;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.internalaudit.client.InternalAuditService;
import com.internalaudit.client.InternalAuditServiceAsync;
import com.internalaudit.client.upload.AuditWorkProgramUpload;
import com.internalaudit.client.upload.EmailAttachmentUpload;
import com.internalaudit.client.view.DisplayAlert;
import com.internalaudit.shared.Employee;
import com.internalaudit.shared.InformationRequestEntity;
import com.internalaudit.shared.InformationRequestLogEntity;
import com.internalaudit.shared.JobCreation;

public class InformationRequestRaiserFinalView extends VerticalPanel {
	InternalAuditServiceAsync rpcService = GWT.create(InternalAuditService.class);
	VerticalPanel panelMain = new VerticalPanel();
	HorizontalPanel PanelUpButton = new HorizontalPanel();
	Button btnEmial = new Button("Email");
	Button btnPrint = new Button("Print");
	HorizontalPanel panelLabel = new HorizontalPanel();
	Label lblIr = new Label();
	Label lblIrData = new Label(":123");
	Label lblDate = new Label();
	Label lblDateData = new Label("28-sep-2018");
	Label lblSpace = new Label();
	Label lblRequestetBy = new Label();
	Label lblRequestedData = new Label("Hamza");
	Label lblMesssageData;
	Label lblMesssage;
	TextArea lblEmailData;
	Label lblReplyOld;
	TextArea lblReplyOldData;
	Label lblReply = new Label("Reply: ");
	TextArea txtAreaReply = new TextArea();
	// Button btnSubmit = new Button("Submit/Close");
	Button btnCancel = new Button("Close");
	Button btnReply = new Button("Reply");
	VerticalPanel panelMailRep = new VerticalPanel();
	VerticalPanel panelMail = new VerticalPanel();
	VerticalPanel vpnlReplyMessages = new VerticalPanel();
	VerticalPanel vpnlDescriptionMessage = new VerticalPanel();
	VerticalPanel panelReply = new VerticalPanel();
	HorizontalPanel panelFileDetail = new HorizontalPanel();
	ScrollPanel panelMessage = new ScrollPanel();
	private InformationRequestRaiseEntity informationRequest = null;
	private ArrayList<InformationRequestLogEntity> listOldInformationRequestLogEntity;
	private ArrayList<InformationRequestLogEntity> listUpdatedInformationRequestLogEntity;
	private String loggedInUserName;

	public InformationRequestRaiserFinalView(InformationRequestRaiseEntity informationRequest) {

		this.informationRequest = informationRequest;
		setLayout(informationRequest);
		setHandlers();

	}

	private void setHandlers() {
		btnReply.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				final InformationRequestEntity infoReq = new InformationRequestEntity();
				JobCreation jobid = new JobCreation();
				jobid.setJobCreationId(informationRequest.getRelatedJobId());
				infoReq.setJob(jobid);
				infoReq.setInformationRequestId(informationRequest.getId());
				// Updating Task Strng as well
				infoReq.setRequestItem(informationRequest.getRequestedItem());
				infoReq.setRead(false);			
				infoReq.setSendNotication(informationRequest.getSendNotification());
				infoReq.setSendReminder(informationRequest.getSendReminder());
				infoReq.setContactEmail(informationRequest.getContactEmail());
				Employee raisedBy = new Employee();
				raisedBy.setEmployeeId(informationRequest.getRaisedById());
				Employee raisedTo = new Employee();
				raisedTo.setEmployeeId(informationRequest.getRaisedToId());
				infoReq.setContactResponsible(raisedTo);
				infoReq.setAssignedFrom(raisedBy);
				infoReq.setDueDate(informationRequest.getOverDueDays());
				// Saving data for informationRequestlog
				InformationRequestLogEntity infoRequestLog = new InformationRequestLogEntity();
				infoRequestLog.setInformationRequestId(informationRequest.getId());
				infoRequestLog.setAssignedFrom(raisedBy);
				infoRequestLog.setAssignedTo(raisedTo);
				infoRequestLog.setDescription(informationRequest.getRequestedItem());
				infoRequestLog.setRespond(txtAreaReply.getText());
				infoRequestLog.setDate(informationRequest.getOverDueDays());
				//
				saveInformationRequestandLogs(infoReq, infoRequestLog);
			}

			private void saveInformationRequestandLogs(final InformationRequestEntity infoReq,
					final InformationRequestLogEntity infoRequestLog) {
				rpcService.saveInformationRequestLogs(infoRequestLog, new AsyncCallback<String>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Failed SavaInformationREquestLogs");

					}

					@Override
					public void onSuccess(String result) {
						new DisplayAlert(result);
						rpcService.saveinformationRequest(infoReq, "", new AsyncCallback<String>() {

							@Override
							public void onFailure(Throwable caught) {
								Window.alert("save informationRequest failed");
							}

							@Override
							public void onSuccess(String result) {
								// Window.alert("Saved information Request");
								viewUpdatedReply(infoRequestLog);
							}
						});
					}
				});
			}
		});
	}

	private void viewUpdatedReply(final InformationRequestLogEntity infoRequestLog) {
		listOldInformationRequestLogEntity.add(infoRequestLog);
		listUpdatedInformationRequestLogEntity = new ArrayList<InformationRequestLogEntity>(
				listOldInformationRequestLogEntity);
		vpnlReplyMessages.clear();
		informationRequestLogs(listUpdatedInformationRequestLogEntity);
		txtAreaReply.setText("");
		txtAreaReply.getElement().setPropertyString("placeholder", "Enter your Reply here");
	}

	private void setLayout(InformationRequestRaiseEntity informationRequest) {
		ViewMessages(informationRequest);

		lblRequestedData.setText(informationRequest.getRaisedTo());
		String formattedDate = DateTimeFormat.getShortDateFormat().format(informationRequest.getOverDueDays());
		lblDateData.setText(formattedDate);
		// lblDateData.setText(informationRequest.getOverDueDays().toString());
		lblIrData.setText(informationRequest.getId() + "");
		setWidth("600px");
		setHeight("500px");
		panelMain.addStyleName("w3-border");
		panelLabel.setWidth("100%");
		lblSpace.getElement().getStyle().setPaddingLeft(300, Unit.PX);
		PanelUpButton.addStyleName(" w3-right");
		// PanelUpButton.add(btnEmial);
		// PanelUpButton.add(btnPrint);
		lblDateData.addStyleName("labelHeadingToDo");
		lblIrData.addStyleName("labelHeadingToDo");
		lblIr.setText("Ir#");
		lblDate.setText("Date: ");
		lblIr.addStyleName("labelDesign labelHeadingToDo");
		lblDate.addStyleName("labelDesign labelHeadingToDo");
		panelLabel.addStyleName("w3-border");
		panelLabel.add(lblIr);
		panelLabel.add(lblIrData);
		panelLabel.add(lblSpace);
		panelLabel.add(lblDate);
		panelLabel.add(lblDateData);
		panelMailRep.addStyleName("w3-border");
		panelReply.addStyleName("w3-border");
		panelMail.addStyleName("w3-border");
		HorizontalPanel panelRequestedBy = new HorizontalPanel();
		lblRequestetBy.setText("Requested By: ");
		lblRequestetBy.setWidth("110px");
		panelRequestedBy.add(lblRequestetBy);
		lblRequestetBy.addStyleName("labelDesign labelHeadingToDo");
		lblRequestedData.addStyleName("labelHeadingToDo");
		panelRequestedBy.add(lblRequestedData);
		HorizontalPanel panelMailReq = new HorizontalPanel();
		panelMail.add(panelMailReq);
		panelMail.addStyleName("w3-sand");
		ScrollPanel panelMessageScroll = new ScrollPanel();
		panelMessageScroll.setHeight("200px");
		panelMessageScroll.add(panelMail);
		panelMail.add(vpnlDescriptionMessage);
		panelMail.add(vpnlReplyMessages);
		panelReply.add(lblReply);
		lblReply.addStyleName("labelDesign labelHeadingToDo");
		panelReply.add(txtAreaReply);
		panelMailRep.add(panelRequestedBy);
		panelMailRep.add(panelMessageScroll);
		panelMailRep.add(panelReply);
		HorizontalPanel panelPriority = new HorizontalPanel();
		Label lblPriority = new Label("Priority: ");
		lblPriority.addStyleName("labelDesign");
		ListBox listBoxPriority = new ListBox();
		listBoxPriority.addItem("High");
		listBoxPriority.addItem("Medium");
		listBoxPriority.addItem("Low");
		panelPriority.add(lblPriority);
		panelPriority.add(listBoxPriority);
		panelMailRep.add(panelPriority);
		panelPriority.setHeight("30px");
		panelPriority.addStyleName("w3-right");
		btnReply.getElement().getStyle().setMarginLeft(440, Unit.PX);
		HorizontalPanel panelb = new HorizontalPanel();
		// panelb.add(btnSubmit);
		panelb.add(btnReply);
		panelb.add(btnCancel);
		// btnSubmit.setWidth("85px");
		// panelMailRep.add(panelb);
		EmailAttachmentUpload a = new EmailAttachmentUpload();
		// panelFileUpload.add(a);
		// commented as it is not added in view by moqeet
		String mainFolder = "InformationRequestUploads";
		String informationRequestId = informationRequest.getId() + "";
		AuditWorkProgramUpload informationRequestUploadAttachment = new AuditWorkProgramUpload(informationRequestId,
				mainFolder);
		VerticalPanel panelFileUpload = new VerticalPanel();
		panelFileUpload.add(informationRequestUploadAttachment);
		informationRequestUploadAttachment.getPanelFileDetail().setWidth("350px");
		txtAreaReply.getElement().setPropertyString("placeholder", "Enter your Reply here");
		txtAreaReply.setWidth("590px");
		PanelUpButton.setHeight("50px");
		panelLabel.setHeight("50px");
		panelMailRep.setHeight("300px");
		// panelFileUpload.setHeight("50px");
		panelMail.setHeight("250px");
		txtAreaReply.setHeight("80px");
		panelReply.setWidth("600px");
		panelMail.setWidth("590px");
		// panelFileUpload.setWidth("590px");
		// add(PanelUpButton);
		add(panelLabel);
		add(panelMailRep);
		add(panelFileUpload);
		add(panelb);
	}

	private void ViewMessages(InformationRequestRaiseEntity informationRequest) {
		lblMesssage = new Label();
		lblMesssageData = new Label();
		lblMesssage.setText("Message By: " + informationRequest.getRaisedBy());
		lblMesssageData.setText(informationRequest.getInformationRequestLogList().get(0).getDescription());
		lblMesssage.getElement().getStyle().setFontWeight(FontWeight.BOLD);
		vpnlDescriptionMessage.add(lblMesssage);
		vpnlDescriptionMessage.add(lblMesssageData);
		// panelMail.add(vpnlReplyMessages);
		informationRequestLogs(informationRequest.getInformationRequestLogList());
	}

	private void informationRequestLogs(ArrayList<InformationRequestLogEntity> informationRequestLogList) {
		for (int i = 0; i < informationRequestLogList.size(); i++) {
			Label lblReplyOldData = new Label();
			lblReplyOld = new Label();
			String lblMsgHeader = null;
			if (informationRequestLogList.get(i) != null && informationRequestLogList.get(i).getAssignedFrom() != null
					&& informationRequestLogList.get(i).getAssignedFrom().getEmployeeName() != null) {
				loggedInUserName = informationRequestLogList.get(i).getAssignedFrom().getEmployeeName();
				lblMsgHeader = "Message By: " + informationRequestLogList.get(i).getAssignedFrom().getEmployeeName();
			} else {
				lblMsgHeader = "Message By: " + loggedInUserName;
			}
			lblReplyOld.setText(lblMsgHeader);
			lblReplyOldData.setText(informationRequestLogList.get(i).getRespond());
			lblReplyOld.getElement().getStyle().setFontWeight(FontWeight.BOLD);
			vpnlReplyMessages.add(lblReplyOld);
			vpnlReplyMessages.add(lblReplyOldData);
		}
		listOldInformationRequestLogEntity = new ArrayList<InformationRequestLogEntity>(informationRequestLogList);
	}

	private void fetchEmailAttachments() {
		rpcService.fetchEmailAttachments(new AsyncCallback<ArrayList<String>>() {
			FlexTable records = new FlexTable();

			@Override
			public void onSuccess(ArrayList<String> result) {
				for (int i = 0; i < result.size(); i++) {
					final Anchor lblfilename = new Anchor(result.get(i));
					Label lblFileAttached = new Label("Attached");
					lblfilename.addStyleName("pointerStyle");
					lblfilename.getElement().getStyle().setTextDecoration(TextDecoration.NONE);
					lblfilename.setHeight("25px");
					lblFileAttached.setHeight("25px");
					records.setWidth("100%");
					records.setWidget(i, 0, lblfilename);
					records.setWidget(i, 1, lblFileAttached);
					if (i % 2 != 0) {
						records.getRowFormatter().addStyleName(i, "jobStatusRow");
					}
					panelFileDetail.setWidth("100%");
					panelFileDetail.add(records);
					lblfilename.setWordWrap(false);
					String upperCasedJobLink = lblfilename.getText();
					lblfilename.setText(upperCasedJobLink);
					lblfilename.addClickHandler(new ClickHandler() {

						@Override
						public void onClick(ClickEvent event) {

							Window.open("/EmailAttachmentUpload/" + lblfilename.getText(), "name", "");
						}
					});
				}

			}

			@Override
			public void onFailure(Throwable caught) {

				Window.alert("fetchEmailAttachment Failed");
			}

		});
	}

	public Button getBtnCancel() {
		return btnCancel;
	}

	public void setBtnCancel(Button btnCancel) {
		this.btnCancel = btnCancel;
	}

	// public Button getBtnSubmit() {
	// return btnSubmit;
	// }
	//
	// public void setBtnSubmit(Button btnSubmit) {
	// this.btnSubmit = btnSubmit;
	// }

}