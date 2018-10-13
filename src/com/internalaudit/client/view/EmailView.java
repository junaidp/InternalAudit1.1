package com.internalaudit.client.view;

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
import com.internalaudit.client.view.AuditEngagement.LabelHeading;
import com.internalaudit.shared.ToDo;
import com.sencha.gxt.widget.core.client.form.TextArea;

public class EmailView extends VerticalPanel {
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

	public EmailView(final ToDo toDo){
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
		lblIrData.setText(toDo.getToDoId()+"");
		lblRequestedData.setText(toDo.getAssignedFrom().getEmployeeName());

		lblEmailData.setText(toDo.getDescription());
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


		lblRequestetBy.setText("RequestedBy");
		HorizontalPanel panelMailReq = new HorizontalPanel();
		panelMailReq.add(lblRequestetBy);
		lblRequestedData.addStyleName("w3-panel");
		panelMailReq.add(lblRequestedData);
		panelMail.add(panelMailReq);
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
				//final ToDo todo = new ToDo();
				//toDo.setDescription(toDo.getDescription());
				toDo.setRespond(txtAreaReply.getText());
				toDo.setAssignedTo(toDo.getAssignedFrom());
				rpcService.savetoDo(toDo, new AsyncCallback<String>() {

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