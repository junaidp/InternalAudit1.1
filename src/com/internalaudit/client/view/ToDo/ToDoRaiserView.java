
package com.internalaudit.client.view.ToDo;

import com.google.gwt.core.client.GWT;
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
import com.internalaudit.shared.JobCreation;
import com.internalaudit.shared.ToDo;
import com.internalaudit.shared.ToDoLogsEntity;

public class ToDoRaiserView extends VerticalPanel {
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

	VerticalPanel panelMailRep = new VerticalPanel();
	VerticalPanel panelMail = new VerticalPanel();
	VerticalPanel panelReply = new VerticalPanel();
	// HorizontalPanel panelFileDetail = new HorizontalPanel();
	// final VerticalPanel panelFileName = new VerticalPanel();

	public ToDoRaiserView(final ToDoReceiverEntity toDo) {

		setHandler(toDo);
		clickHandler(toDo);

	}

	private void setHandler(final ToDoReceiverEntity toDo) {
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
		lblMesssage.setText("Message From ::" + toDo.getRaisedBy());
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
		PanelUpButton.add(btnPrint);
		lblDateData.addStyleName("w3-panel");
		lblIrData.addStyleName("w3-panel");
		lblIr.setText("Task#");
		lblDate.setText("Date: ");

		panelLabel.addStyleName("w3-border");
		lblIr.addStyleName("labelDesign w3-panel");
		lblDate.addStyleName("labelDesign w3-panel");
		panelLabel.add(lblIr);
		panelLabel.add(lblIrData);
		panelLabel.add(lblSpace);
		panelLabel.add(lblDate);
		panelLabel.add(lblDateData);

		panelMailRep.addStyleName("w3-border");
		panelReply.addStyleName("w3-border");
		panelMail.addStyleName("w3-border");

		// lblJob.setText("Job");
		HorizontalPanel panelMailReq = new HorizontalPanel();
		lblJob.setText("Job: ");
		lblJob.addStyleName("labelDesign w3-panel");
		panelMailReq.add(lblJob);
		lblJobData.addStyleName("w3-panel");
		panelMailReq.add(lblJobData);
		HorizontalPanel panelAssign = new HorizontalPanel();
		lblAssignedTo.setText("Assigned To: ");
		lblAssignedTo.addStyleName("labelDesign w3-panel");
		lblAssignedToData.addStyleName("w3-panel");
		panelAssign.add(lblAssignedTo);
		panelAssign.add(lblAssignedToData);
		panelMail.add(panelMailReq);
		panelMail.add(panelAssign);
		panelMail.add(lblMesssage);
		panelMail.add(lblEmailData);

		panelReply.add(lblReply);
		lblReply.addStyleName("labelDesign w3-panel");

		panelReply.add(txtAreaReply);

		panelMailRep.add(panelMail);
		panelMailRep.add(panelReply);
		btnSubmit.addStyleName(" w3-right");
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

		add(PanelUpButton);
		add(panelLabel);
		add(panelMailRep);
		add(panelFileUpload);

	}

	private void clickHandler(final ToDoReceiverEntity toDo) {
		btnSubmit.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				final ToDo todoEntity = new ToDo();
				todoEntity.setToDoId(toDo.getId());
				todoEntity.setDescription(toDo.getRequestedItem());
				todoEntity.setRespond(txtAreaReply.getText());
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
						saveToDo(todoEntity);
						txtAreaReply.setEnabled(false);
					}

					private void saveToDo(final ToDo todoEntity) {
						rpcService.savetoDo(todoEntity, new AsyncCallback<String>() {

							@Override
							public void onFailure(Throwable caught) {
								Window.alert("save Todo failed");
							}

							@Override
							public void onSuccess(String result) {

								new DisplayAlert(result);

							}
						});
					}
				});
			}
		});
	}

}