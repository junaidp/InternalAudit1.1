
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
import com.google.gwt.user.client.ui.VerticalPanel;
import com.internalaudit.client.InternalAuditService;
import com.internalaudit.client.InternalAuditServiceAsync;
import com.internalaudit.client.upload.EmailAttachmentUpload;
import com.internalaudit.client.view.ButtonRound;
import com.internalaudit.client.view.DisplayAlert;
import com.internalaudit.client.view.AuditEngagement.LabelHeading;
import com.internalaudit.shared.Employee;
import com.internalaudit.shared.JobCreation;
import com.internalaudit.shared.ToDo;
import com.sencha.gxt.widget.core.client.form.TextArea;

public class ToDoRaiserView extends VerticalPanel {
	InternalAuditServiceAsync rpcService = GWT.create(InternalAuditService.class);
	VerticalPanel panelMain = new VerticalPanel();
	HorizontalPanel PanelUpButton = new HorizontalPanel();
	ButtonRound btnEmial = new ButtonRound("Email");
	ButtonRound btnPrint = new ButtonRound("Print");
	HorizontalPanel panelLabel = new HorizontalPanel();
	LabelHeading lblIr = new LabelHeading();
	Label lblIrData = new Label(":123");
	LabelHeading lblDate = new LabelHeading();
	Label lblDateData = new Label("");
	Label lblSpace = new Label();
	LabelHeading lblJob = new LabelHeading();
	LabelHeading lblAssignedTo = new LabelHeading();
	Label lblAssignedToData = new Label("Hamza");
	Label lblJobData = new Label();
	Label lblMesssage = new Label("Message:");
	Label lblEmailData = new Label("Dear XYZ plz provide the detail about");

	Label lblReply = new  Label("Reply");
	TextArea txtAreaReply = new TextArea();

	
	ButtonRound btnSubmit = new ButtonRound("Submit");

	VerticalPanel panelMailRep = new VerticalPanel();
	VerticalPanel panelMail = new VerticalPanel();
	VerticalPanel panelReply = new VerticalPanel();
	HorizontalPanel panelFileDetail = new HorizontalPanel();
//	final VerticalPanel panelFileName = new VerticalPanel();

	public ToDoRaiserView(final ToDoReceiverEntity toDo){
		rpcService.fetchEmailAttachments(new AsyncCallback<ArrayList<String>>() {
			FlexTable records = new FlexTable();
			@Override
			public void onSuccess(ArrayList<String> result) {
				for(int i=0;i<result.size();i++){
					final Anchor	lblfilename = new Anchor(result.get(i));
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

							Window.open("/EmailAttachmentUpload/"+lblfilename.getText(), "name", "");
						}
					});
				}

			}

			@Override
			public void onFailure(Throwable caught) {

				Window.alert("fetchEmailAttachment Failed");
			}

		});
		lblIrData.setText(toDo.getId()+"");
		lblJobData.setText(toDo.getRelatedJob());
		lblAssignedToData.setText(toDo.getRaisedBy());
		lblDateData.setText(toDo.getOverDueDays().toString());

		lblEmailData.setText(toDo.getRequestedItem());
		setWidth("600px");
		setHeight("700px");
		panelMain.addStyleName("w3-border");
		panelLabel.setWidth("100%");
		lblSpace.getElement().getStyle().setPaddingLeft(300, Unit.PX);


		PanelUpButton.addStyleName(" w3-right");
		PanelUpButton.add(btnEmial);
		PanelUpButton.add(btnPrint);
		lblDateData.addStyleName("w3-panel");
		lblIrData.addStyleName("w3-panel");
		lblIr.setText("Task#");
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


		//lblJob.setText("Job");
		HorizontalPanel panelMailReq = new HorizontalPanel();
		lblJob.setText("Job");
		panelMailReq.add(lblJob);
		lblJobData.addStyleName("w3-panel");
		panelMailReq.add(lblJobData);
		HorizontalPanel panelAssign = new HorizontalPanel();
		lblAssignedTo.setText("Assigned To");
		lblAssignedToData.addStyleName("w3-panel");
		panelAssign.add(lblAssignedTo);
		panelAssign.add(lblAssignedToData);
		panelMail.add(panelMailReq);
		panelMail.add(panelAssign);
		panelMail.add(lblMesssage);
		panelMail.add(lblEmailData);

		panelReply.add(lblReply);

		panelReply.add(txtAreaReply);

		panelMailRep.add(panelMail);
		panelMailRep.add(panelReply);
		panelMailRep.add(btnSubmit);

		//FileUploader f = new FileUploader();
		EmailAttachmentUpload a = new EmailAttachmentUpload();
		VerticalPanel panelFileUpload = new VerticalPanel();
		panelFileUpload.add(a);
		txtAreaReply.setText("Enter your Reply here");




//		VerticalPanel panelAttached = new VerticalPanel();

//		panelAttached.add(lblFileAttached);
//		panelFileDetail.add(panelFileName);
//		panelFileDetail.add(panelAttached);

		PanelUpButton.setHeight("50px");
		panelLabel.setHeight("50px");
		panelMailRep.setHeight("300px");
		panelFileUpload.setHeight("50px");
//		panelFileDetail.setHeight("160px");
		panelMail.setHeight("150px");
		txtAreaReply.setHeight("150px");
		panelReply.setWidth("600px");
		panelMail.setWidth("590px");
		panelFileUpload.setWidth("590px");
		txtAreaReply.setWidth("590px");
//		panelFileDetail.addStyleName("w3-border");
//		panelFileDetail.setWidth("590px");
//		panelFileName.setWidth("50%");
//		panelAttached.setWidth("50%");



		add(PanelUpButton);
		add(panelLabel);
		add(panelMailRep);
		add(panelFileUpload);
		add(panelFileDetail);

		btnSubmit.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				final ToDo todoEntity = new ToDo();
				todoEntity.setToDoId(toDo.getId());
				todoEntity.setDescription(toDo.getRequestedItem());
				todoEntity.setRespond(txtAreaReply.getText());
				Employee assignedFrom = new Employee();
				assignedFrom.setEmployeeId(toDo.getRaisedById());
				todoEntity.setAssignedTo(assignedFrom);
				todoEntity.setDueDate(toDo.getOverDueDays());
				//JobCreation jobcreationId = new JobCreation();
				//jobcreationId.setJobCreationId(toDo.getRelatedJobId());
				//todoEntity.setJob(jobcreationId);
				
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


}