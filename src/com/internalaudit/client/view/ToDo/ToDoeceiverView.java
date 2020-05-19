
package com.internalaudit.client.view.ToDo;

import java.util.ArrayList;

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
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.internalaudit.client.InternalAuditService;
import com.internalaudit.client.InternalAuditServiceAsync;
import com.internalaudit.client.upload.AuditWorkProgramUpload;
import com.internalaudit.client.view.DisplayAlert;
import com.internalaudit.shared.Employee;
import com.internalaudit.shared.JobCreation;
import com.internalaudit.shared.ToDo;
import com.internalaudit.shared.ToDoLogsEntity;

public class ToDoeceiverView extends VerticalPanel {
	InternalAuditServiceAsync rpcService = GWT.create(InternalAuditService.class);
	VerticalPanel panelMain = new VerticalPanel();
	HorizontalPanel PanelUpButton = new HorizontalPanel();
	Button btnEmial = new Button("Email");
	Button btnPrint = new Button("Print");
	HorizontalPanel panelLabel = new HorizontalPanel();
	Label lblIr = new Label();
	Label lblIrData = new Label(":123");
	Label lblDate = new Label();
	Label lblDateData = new Label("");
	Label lblSpace = new Label();
	Label lblJob = new Label();
	Label lblAssignedTo = new Label();
	Label lblAssignedToData = new Label("Hamza");
	Label lblJobData = new Label();
	Label lblMesssage = new Label("Message:");
	Label lblEmailData = new Label("Dear XYZ plz provide the detail about");

	Label lblReply = new Label("Reply: ");
	TextArea txtAreaReply = new TextArea();

	Button btnSubmit = new Button("Submit");
	Button btnClose = new Button("Close");

	VerticalPanel panelMailRep = new VerticalPanel();
	VerticalPanel panelMail = new VerticalPanel();
	VerticalPanel panelReply = new VerticalPanel();
	VerticalPanel vpnlReplyMessages = new VerticalPanel();
	private ArrayList<ToDoLogsEntity> listOldToDoLogs;
	private Label lblReplyOld;
	private Label lblReplyOldData;
	private String loggedInUserName;
	private Label lblMesssageData;
	private ArrayList<ToDoLogsEntity> listUpdatedToDoLogs;

	// HorizontalPanel panelFileDetail = new HorizontalPanel();
	// final VerticalPanel panelFileName = new VerticalPanel();
	public ToDoeceiverView(final ToDoReceiverEntity toDo) {

		setHandler(toDo);
		clickHandler(toDo);

	}

	private void setHandler(final ToDoReceiverEntity toDo) {
		// viewMessages(toDo);
		lblIrData.setText(toDo.getId() + "");
		lblJobData.setText(toDo.getRelatedJob());
		lblAssignedToData.setText(toDo.getRaisedBy());
		// SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy");
		// String d = (toDo.getOverDueDays() + "");
		// try {
		// Date date = (Date) s.parse(d);
		// } catch (ParseException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		lblMesssage.setText("Message From: " + toDo.getRaisedBy());
		lblMesssage.getElement().getStyle().setFontWeight(FontWeight.BOLD);
		String formattedDate = DateTimeFormat.getShortDateFormat().format(toDo.getOverDueDays());
		lblDateData.setText(formattedDate);

		lblEmailData.setText(toDo.getRequestedItem());
		// setWidth("600px");
		// setHeight("500px");
		panelMain.addStyleName("w3-border");
		panelLabel.setWidth("100%");
		lblSpace.getElement().getStyle().setPaddingLeft(250, Unit.PX);

		PanelUpButton.addStyleName("w3-right");
		// PanelUpButton.add(btnEmial);
		// PanelUpButton.add(btnPrint);
		lblDateData.addStyleName("labelHeadingToDo");
		lblIrData.addStyleName("labelHeadingToDo");
		lblIr.setText("Task#");
		lblDate.setText("Date: ");

		panelLabel.addStyleName("w3-border");
		lblIr.addStyleName("labelDesign labelHeadingToDo");
		lblDate.addStyleName("labelDesign labelHeadingToDo");
		panelLabel.add(lblIr);
		panelLabel.add(lblIrData);
		panelLabel.add(lblSpace);
		panelLabel.add(lblDate);
		panelLabel.add(lblDateData);

		panelMailRep.addStyleName("w3-border");
		panelReply.addStyleName("w3-border");
		panelMail.addStyleName("w3-border");

		updateLogs(toDo.getTodoLogList());
		loggedInUserName = toDo.getRaisedTo();

		// lblJob.setText("Job");
		HorizontalPanel panelMailReq = new HorizontalPanel();
		lblJob.setText("Job: ");
		lblJob.setWidth("45px");
		lblJob.addStyleName("labelDesign labelHeadingToDo");
		panelMailReq.add(lblJob);
		lblJobData.addStyleName("labelHeadingToDo");
		panelMailReq.add(lblJobData);
		HorizontalPanel panelAssign = new HorizontalPanel();
		lblAssignedTo.setText("Assigned To: ");
		lblAssignedTo.setWidth("110px");
		lblAssignedTo.addStyleName("labelDesign labelHeadingToDo");
		lblAssignedToData.addStyleName("labelHeadingToDo");
		panelAssign.add(lblAssignedTo);
		panelAssign.add(lblAssignedToData);
		panelMailRep.add(panelMailReq);
		panelMailRep.add(panelAssign);
		// panelMail.add(lblMesssage);
		// panelMail.add(lblEmailData);

		panelReply.add(lblReply);
		lblReply.addStyleName("labelDesign labelHeadingToDo");

		panelReply.add(txtAreaReply);

		ScrollPanel panelMessageScroll = new ScrollPanel();
		panelMessageScroll.setHeight("200px");
		panelMessageScroll.add(panelMail);
		panelMail.addStyleName("w3-sand");
		panelMail.add(vpnlReplyMessages);
		panelMailRep.add(panelMessageScroll);
		panelMailRep.add(panelReply);
		btnSubmit.addStyleName("w3-right");
		btnClose.addStyleName("w3-right");
		panelMailRep.add(btnSubmit);
		String mainFolder = "ToDoUploads";
		String toDoId = toDo.getId() + "";
		// FileUploader f = new FileUploader();
		AuditWorkProgramUpload informationRequestUpload = new AuditWorkProgramUpload(toDoId, mainFolder);
		VerticalPanel panelFileUpload = new VerticalPanel();
		panelFileUpload.add(informationRequestUpload);
		txtAreaReply.getElement().setPropertyString("placeholder", "Enter your Reply here");

		// VerticalPanel panelAttached = new VerticalPanel();

		// panelAttached.add(lblFileAttached);
		// panelFileDetail.add(panelFileName);
		// panelFileDetail.add(panelAttached);

		PanelUpButton.setHeight("50px");
		panelLabel.setHeight("50px");
		panelMailRep.setHeight("300px");
		// panelFileUpload.setHeight("50px");
		// panelFileDetail.setHeight("160px");
		panelMail.setHeight("150px");
		txtAreaReply.setHeight("150px");
		panelReply.setWidth("600px");
		panelMail.setWidth("590px");
		// panelFileUpload.setWidth("590px");
		txtAreaReply.setWidth("590px");
		// panelFileDetail.addStyleName("w3-border");
		// panelFileDetail.setWidth("590px");
		// panelFileName.setWidth("50%");
		// panelAttached.setWidth("50%");

		// add(PanelUpButton);
		add(panelLabel);
		add(panelMailRep);
		add(panelFileUpload);
		add(btnClose);
	}

	private void viewMessages(ToDoReceiverEntity toDoReceiverEntity) throws Exception {
		lblMesssageData = new Label();
		lblMesssage = new Label();
		lblMesssage.setText("Message By: " + toDoReceiverEntity.getRaisedBy());
		lblMesssageData.setText(toDoReceiverEntity.getTodoLogList().get(0).getDescription());
		lblMesssage.getElement().getStyle().setFontWeight(FontWeight.BOLD);
		panelMail.add(lblMesssage);
		panelMail.add(lblMesssageData);

		updateLogs(toDoReceiverEntity.getTodoLogList());
	}

	private void updateLogs(ArrayList<ToDoLogsEntity> todoLogs) {
		for (int i = 0; i < todoLogs.size(); i++) {
			lblReplyOld = new Label();
			lblReplyOldData = new Label();
			String lblMsgHeader = null;
			if (todoLogs.get(i) != null && todoLogs.get(i).getAssignedFrom() != null
					&& todoLogs.get(i).getAssignedFrom().getEmployeeName() != null) {
				loggedInUserName = todoLogs.get(i).getAssignedFrom().getEmployeeName();
				lblMsgHeader = "Message By: " + todoLogs.get(i).getAssignedFrom().getEmployeeName();
			} else {
				lblMsgHeader = "Message By: " + loggedInUserName;
			}
			lblReplyOld.setText(lblMsgHeader);
			lblReplyOldData.setText(todoLogs.get(i).getRespond());
			lblReplyOld.getElement().getStyle().setFontWeight(FontWeight.BOLD);
			vpnlReplyMessages.add(lblReplyOld);
			vpnlReplyMessages.add(lblReplyOldData);
		}
		listOldToDoLogs = new ArrayList<ToDoLogsEntity>(todoLogs);
	}

	private void viewUpdatedReply(final ToDoLogsEntity todoLogsEntity) {
		listOldToDoLogs.add(todoLogsEntity);
		listUpdatedToDoLogs = new ArrayList<ToDoLogsEntity>(listOldToDoLogs);
		vpnlReplyMessages.clear();
		updateLogs(listUpdatedToDoLogs);
		txtAreaReply.setText("");
		txtAreaReply.getElement().setPropertyString("placeholder", "Enter your Reply here");
	}

	private void clickHandler(final ToDoReceiverEntity toDo) {
		btnSubmit.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				final ToDo todoEntity = new ToDo();
				todoEntity.setToDoId(toDo.getId());
				todoEntity.setTask(toDo.getRequestedItem());
				// todoEntity.setDescription(toDo.getRequestedItem());
				// Updating Task String as well, by moqeet
				// todoEntity.setRespond(txtAreaReply.getText());
				Employee assignedTo = new Employee();
				assignedTo.setEmployeeId(toDo.getRaisedToId());
				Employee assignedFrom = new Employee();
				assignedFrom.setEmployeeId(toDo.getRaisedById());
				todoEntity.setAssignedTo(assignedTo);
				todoEntity.setDueDate(toDo.getOverDueDays());
				todoEntity.setAssignedFrom(assignedFrom);
				JobCreation jobcreationId = new JobCreation();
				jobcreationId.setJobCreationId(toDo.getRelatedJobId());
				todoEntity.setJob(jobcreationId);
				todoEntity.setRead(true);

				final ToDoLogsEntity todoLogsEntity = new ToDoLogsEntity();

				todoLogsEntity.setDescription(toDo.getRequestedItem());
				todoLogsEntity.setRespond(txtAreaReply.getText());
				todoLogsEntity.setToDoId(toDo.getId());
				todoLogsEntity.setAssignedFrom(assignedTo);
				todoLogsEntity.setAssignedTo(assignedFrom);
				todoLogsEntity.setDate(toDo.getOverDueDays());

				saveToDoLog(todoEntity, todoLogsEntity);

			}

			private void saveToDoLog(final ToDo todoEntity, final ToDoLogsEntity todoLogsEntity) {
				rpcService.saveToDoLogs(todoLogsEntity, new AsyncCallback<String>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("save ToDoLogsFailed");

					}

					@Override
					public void onSuccess(String result) {
						new DisplayAlert(result);
						saveToDo(todoEntity, todoLogsEntity);
						// txtAreaReply.setEnabled(false);
					}

					private void saveToDo(final ToDo todoEntity, final ToDoLogsEntity todoLogsEntity) {
						rpcService.savetoDo(todoEntity, new AsyncCallback<String>() {

							@Override
							public void onFailure(Throwable caught) {
								Window.alert("save Todo failed");
							}

							@Override
							public void onSuccess(String result) {
								new DisplayAlert(result);
								viewUpdatedReply(todoLogsEntity);
							}
						});
					}
				});
			}
		});
	}

	public Button getBtnClose() {
		return btnClose;
	}

	public void setBtnClose(Button btnClose) {
		this.btnClose = btnClose;
	}

}