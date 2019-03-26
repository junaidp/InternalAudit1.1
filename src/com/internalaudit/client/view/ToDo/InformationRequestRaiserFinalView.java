package com.internalaudit.client.view.ToDo;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.TextDecoration;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.internalaudit.client.InternalAuditService;
import com.internalaudit.client.InternalAuditServiceAsync;
import com.internalaudit.client.upload.EmailAttachmentUpload;
import com.internalaudit.client.view.ButtonRound;
import com.internalaudit.client.view.DisplayAlert;
import com.internalaudit.client.view.AuditEngagement.LabelHeading;
import com.internalaudit.shared.Employee;
import com.internalaudit.shared.InformationRequestEntity;
import com.internalaudit.shared.InformationRequestLogEntity;
import com.internalaudit.shared.JobCreation;

public class InformationRequestRaiserFinalView extends VerticalPanel {
	InternalAuditServiceAsync rpcService = GWT.create(InternalAuditService.class);
	VerticalPanel panelMain = new VerticalPanel();
	HorizontalPanel PanelUpButton = new HorizontalPanel();
	ButtonRound btnEmial = new ButtonRound("Email");
	ButtonRound btnPrint = new ButtonRound("Print");
	HorizontalPanel panelLabel = new HorizontalPanel();
	LabelHeading lblIr = new LabelHeading();
	Label lblIrData = new Label(":123");
	LabelHeading lblDate = new LabelHeading();
	Label lblDateData = new Label("28-sep-2018");
	Label lblSpace = new Label();
	LabelHeading lblRequestetBy = new LabelHeading();
	Label lblRequestedData = new Label("Hamza");
	Label lblMesssageData;
	Label lblMesssage;
	TextArea lblEmailData;
	Label lblReplyOld;
	TextArea lblReplyOldData;
	Label lblReply = new Label("Reply");
	TextArea txtAreaReply = new TextArea();
	ButtonRound btnSubmit = new ButtonRound("Submit/Close");
	ButtonRound btnCancel = new ButtonRound("Cancel");
	ButtonRound btnReply = new ButtonRound("Reply");
	VerticalPanel panelMailRep = new VerticalPanel();
	VerticalPanel panelMail = new VerticalPanel();
	VerticalPanel panelReply = new VerticalPanel();
	HorizontalPanel panelFileDetail = new HorizontalPanel();
	ScrollPanel panelMessage = new ScrollPanel();
	private InformationRequestRaiseEntity informationRequest = null;

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
				infoReq.setRequestItem(txtAreaReply.getText());
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
					InformationRequestLogEntity infoRequestLog) {
				rpcService.saveInformationRequestLogs(infoRequestLog, new AsyncCallback<String>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Failed SavaInformationREquestLogs");

					}

					@Override
					public void onSuccess(String result) {
						new DisplayAlert(result);
						rpcService.saveinformationRequest(infoReq, new AsyncCallback<String>() {

							@Override
							public void onFailure(Throwable caught) {
								Window.alert("save informationRequest failed");
							}

							@Override
							public void onSuccess(String result) {

							}
						});
					}
				});
			}
		});
	}

	private void setLayout(InformationRequestRaiseEntity informationRequest) {
		lblMesssage = new Label();
		lblMesssageData = new Label();

		for (int i = 0; i < informationRequest.getInformationRequestLogList().size(); i++) {
			if (i == 0) {
				lblMesssage.setText("Message By ::" + informationRequest.getRaisedBy());
				lblMesssageData.setText(informationRequest.getInformationRequestLogList().get(i).getDescription());
				panelMail.add(lblMesssage);
				panelMail.add(lblMesssageData);
			}

			Label lblReplyOldData = new Label();
			lblReplyOld = new Label();
			lblReplyOld.setText("Message By ::"
					+ informationRequest.getInformationRequestLogList().get(i).getAssignedFrom().getEmployeeName());
			lblReplyOldData.setText(informationRequest.getInformationRequestLogList().get(i).getRespond());
			panelMail.add(lblReplyOld);
			panelMail.add(lblReplyOldData);

		}

		lblRequestedData.setText(informationRequest.getRaisedTo());
		lblDateData.setText(informationRequest.getOverDueDays().toString());
		lblIrData.setText(informationRequest.getId() + "");
		setWidth("600px");
		setHeight("600px");
		panelMain.addStyleName("w3-border");
		panelLabel.setWidth("100%");
		lblSpace.getElement().getStyle().setPaddingLeft(300, Unit.PX);
		PanelUpButton.addStyleName(" w3-right");
		PanelUpButton.add(btnEmial);
		PanelUpButton.add(btnPrint);
		lblDateData.addStyleName("w3-panel");
		lblIrData.addStyleName("w3-panel");
		lblIr.setText("Ir#");
		lblDate.setText("Date");
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
		lblRequestetBy.setText("RequestedBy");
		panelRequestedBy.add(lblRequestetBy);
		lblRequestedData.addStyleName("w3-panel");
		panelRequestedBy.add(lblRequestedData);
		HorizontalPanel panelMailReq = new HorizontalPanel();
		panelMail.add(panelMailReq);
		panelMail.addStyleName("w3-gray");
		ScrollPanel panelMessageScroll = new ScrollPanel();
		panelMessageScroll.setHeight("200px");
		panelMessageScroll.add(panelMail);
		panelReply.add(lblReply);
		panelReply.add(txtAreaReply);
		panelMailRep.add(panelRequestedBy);
		panelMailRep.add(panelMessageScroll);
		panelMailRep.add(panelReply);
		HorizontalPanel panelPriority = new HorizontalPanel();
		Label lblPriority = new Label("Priority ::");
		ListBox listBoxPriority = new ListBox();
		listBoxPriority.addItem("High");
		listBoxPriority.addItem("Medium");
		listBoxPriority.addItem("Low");
		panelPriority.add(lblPriority);
		panelPriority.add(listBoxPriority);
		panelMailRep.add(panelPriority);
		panelPriority.setHeight("30px");
		panelPriority.addStyleName("w3-right");
		btnReply.getElement().getStyle().setMarginLeft(300, Unit.PX);
		HorizontalPanel panelb = new HorizontalPanel();
		panelb.add(btnSubmit);
		panelb.add(btnReply);
		panelb.add(btnCancel);
		panelMailRep.add(panelb);

		EmailAttachmentUpload a = new EmailAttachmentUpload();
		VerticalPanel panelFileUpload = new VerticalPanel();
		panelFileUpload.add(a);
		txtAreaReply.getElement().setPropertyString("placeholder", "Enter your Reply here");
		txtAreaReply.setWidth("590px");
		PanelUpButton.setHeight("50px");
		panelLabel.setHeight("50px");
		panelMailRep.setHeight("300px");
		panelFileUpload.setHeight("50px");
		panelMail.setHeight("250px");
		txtAreaReply.setHeight("80px");
		panelReply.setWidth("600px");
		panelMail.setWidth("590px");
		panelFileUpload.setWidth("590px");
		add(PanelUpButton);
		add(panelLabel);
		add(panelMailRep);

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

	public ButtonRound getBtnCancel() {
		return btnCancel;
	}

	public void setBtnCancel(ButtonRound btnCancel) {
		this.btnCancel = btnCancel;
	}

}