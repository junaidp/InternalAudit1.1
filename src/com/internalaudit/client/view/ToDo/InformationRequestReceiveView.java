package com.internalaudit.client.view.ToDo;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.FontWeight;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.internalaudit.client.InternalAuditService;
import com.internalaudit.client.InternalAuditServiceAsync;
import com.internalaudit.client.upload.AuditWorkProgramUpload;
import com.internalaudit.client.view.DisplayAlert;
import com.internalaudit.shared.Employee;
import com.internalaudit.shared.InformationRequestEntity;
import com.internalaudit.shared.InformationRequestLogEntity;
import com.internalaudit.shared.JobCreation;

public class InformationRequestReceiveView extends VerticalPanel {
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
	Label lblMesssage = new Label("Message:");
	Label lblEmailData = new Label("Dear XYZ plz provide the detail about");

	Label lblReply = new Label("Reply: ");
	TextArea txtAreaReply = new TextArea();

	Button btnSubmit = new Button("Submit");

	VerticalPanel panelMailRep = new VerticalPanel();
	VerticalPanel panelMail = new VerticalPanel();
	VerticalPanel panelReply = new VerticalPanel();
	HorizontalPanel panelFileDetail = new HorizontalPanel();
	private InformationRequestReceiverEntity informationRequest = null;

	public InformationRequestReceiveView(InformationRequestReceiverEntity informationRequest) {

		this.informationRequest = informationRequest;

		setLayout(informationRequest);

		setHandlers();

	}

	private void setHandlers() {
		btnSubmit.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				final InformationRequestEntity infoReq = new InformationRequestEntity();

				infoReq.setRequestItem(informationRequest.getRequestedItem());
				JobCreation jobid = new JobCreation();
				jobid.setJobCreationId(informationRequest.getRelatedJobId());
				infoReq.setJob(jobid);
				infoReq.setInformationRequestId(informationRequest.getId());
				infoReq.setRespond(txtAreaReply.getText());
				Employee raisedTo = new Employee();
				raisedTo.setEmployeeId(informationRequest.getRaisedToId());
				Employee raisedBy = new Employee();
				raisedBy.setEmployeeId(informationRequest.getRaiseById());
				// raisedBy.setEmployeeName(informationRequest.getRaisedBy());
				infoReq.setContactResponsible(raisedTo);

				// infoReq.setDueDate(informationRequest.getOverDueDays());
				infoReq.setAssignedFrom(raisedBy);
				infoReq.setContactEmail(informationRequest.getContactEmail());
				infoReq.setSendNotication(informationRequest.getSendNotification());
				infoReq.setSendReminder(informationRequest.getSendReminder());
				infoReq.setStatus(informationRequest.getSstatus());
				infoReq.setDueDate(informationRequest.getOverDueDays());
				infoReq.setRead(true);
				// Saving data for informationRequestlog
				InformationRequestLogEntity infoRequestLog = new InformationRequestLogEntity();
				infoRequestLog.setAssignedFrom(raisedTo);
				infoRequestLog.setAssignedTo(raisedBy);
				infoRequestLog.setDescription(informationRequest.getRequestedItem());
				infoRequestLog.setDate(informationRequest.getOverDueDays());
				infoRequestLog.setRespond(txtAreaReply.getText());
				infoRequestLog.setInformationRequestId(informationRequest.getId());

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
								txtAreaReply.setEnabled(false);

							}
						});
					}
				});

			}
		});
	}

	private void setLayout(InformationRequestReceiverEntity informationRequest) {
		lblMesssage.setText("Message From: " + informationRequest.getRaisedBy());
		lblMesssage.getElement().getStyle().setFontWeight(FontWeight.BOLD);
		lblIrData.setText(informationRequest.getId() + "");
		lblRequestedData.setText(informationRequest.getRaisedBy());
		// lblDateData.setText(informationRequest.getOverDueDays().toString());
		String formattedDate = DateTimeFormat.getShortDateFormat().format(informationRequest.getOverDueDays());
		lblDateData.setText(formattedDate);
		lblEmailData.setText(informationRequest.getRequestedItem());
		// setWidth("600px");
		// setHeight("700px");
		panelMain.addStyleName("w3-border");
		panelLabel.setWidth("100%");
		lblSpace.getElement().getStyle().setPaddingLeft(250, Unit.PX);

		PanelUpButton.addStyleName(" w3-right");
		// PanelUpButton.add(btnEmial);
		// PanelUpButton.add(btnPrint);
		lblDateData.addStyleName("labelHeadingToDo");
		lblIrData.addStyleName("labelHeadingToDo");
		lblIr.setText("Ir#");
		lblDate.setText("Date: ");

		lblIr.addStyleName("labelDesign labelHeadingToDo");
		lblDate.addStyleName("labelDesign labelHeadingToDo");
		lblIrData.addStyleName("labelHeadingToDo");
		lblDateData.addStyleName("labelHeadingToDo");

		panelLabel.addStyleName("w3-border");
		panelLabel.add(lblIr);
		panelLabel.add(lblIrData);
		panelLabel.add(lblSpace);
		panelLabel.add(lblDate);
		panelLabel.add(lblDateData);

		panelMailRep.addStyleName("w3-border");
		panelReply.addStyleName("w3-border");
		panelMail.addStyleName("w3-border");

		lblRequestetBy.setText("Requested By: ");
		lblRequestetBy.setWidth("110px");
		lblRequestetBy.addStyleName("labelDesign labelHeadingToDo");
		HorizontalPanel panelMailReq = new HorizontalPanel();
		panelMailReq.add(lblRequestetBy);
		lblRequestedData.addStyleName("labelHeadingToDo");
		panelMailReq.add(lblRequestedData);
		panelMail.add(panelMailReq);
		panelMail.add(lblMesssage);
		panelMail.add(lblEmailData);
		// panelMail.addStyleName("w3-gray");

		panelReply.add(lblReply);
		lblReply.addStyleName("labelDesign labelHeadingToDo");

		panelReply.add(txtAreaReply);

		panelMailRep.add(panelMail);
		panelMailRep.add(panelReply);
		panelMailRep.add(btnSubmit);

		btnSubmit.addStyleName("w3-right");

		String mainFolder = "InformationRequestUploads";
		String informationRequestId = informationRequest.getId() + "";
		AuditWorkProgramUpload informationRequestUploadAttachment = new AuditWorkProgramUpload(informationRequestId,
				mainFolder);
		VerticalPanel panelFileUpload = new VerticalPanel();
		panelFileUpload.add(informationRequestUploadAttachment);
		txtAreaReply.getElement().setPropertyString("placeholder", "Enter your Reply here");

		PanelUpButton.setHeight("50px");
		panelLabel.setHeight("50px");
		panelMailRep.setHeight("300px");
		panelFileUpload.setHeight("50px");
		panelMail.setHeight("150px");
		txtAreaReply.setHeight("150px");
		panelReply.setWidth("600px");
		panelMail.setWidth("590px");
		panelFileUpload.setWidth("590px");
		txtAreaReply.setWidth("590px");

		// add(PanelUpButton);
		add(panelLabel);
		add(panelMailRep);
		add(panelFileUpload);

	}

}