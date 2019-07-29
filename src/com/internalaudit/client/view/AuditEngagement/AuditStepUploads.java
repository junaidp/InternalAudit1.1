package com.internalaudit.client.view.AuditEngagement;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.Hidden;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.internalaudit.client.InternalAuditService;
import com.internalaudit.client.InternalAuditServiceAsync;
import com.internalaudit.client.upload.AuditWorkProgramUpload;
import com.internalaudit.client.view.ButtonRound;

public class AuditStepUploads extends VerticalPanel {
	HorizontalPanel panelFileDetail = new HorizontalPanel();
	FormPanel form;
	FileUpload upload = new FileUpload();
	InternalAuditServiceAsync rpcService = GWT.create(InternalAuditService.class);
	ButtonRound btnSubmit = new ButtonRound("Submit");

	public AuditStepUploads(final String id) {

		ScrollPanel panelScroll = new ScrollPanel();
		panelScroll.setHeight("100px");
		form = new FormPanel();
		form.setAction(GWT.getModuleBaseURL() + "upload");
		form.setEncoding(FormPanel.ENCODING_MULTIPART);
		form.setMethod(FormPanel.METHOD_POST);
		final HorizontalPanel panel = new HorizontalPanel();
		form.add(panel);

		// upload.setName("uploadFormElement");
		upload.setName(id);
		VerticalPanel panelUpload = new VerticalPanel();
		// Add a 'submit' button.
		panelUpload.add(upload);

		btnSubmit.getElement().getStyle().setMarginTop(10, Unit.PX);
		btnSubmit.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				Hidden hidden = new Hidden();
				hidden.setID(id);
				panel.add(hidden);
				// form.add(hidden);
				form.submit();
			}
		});
		String mainFolder = "AuditExceptions";
		AuditWorkProgramUpload upload = new AuditWorkProgramUpload(id, mainFolder);
		// fetchExceptionAttachments(id);
		// panelUpload.add(btnSubmit);
		// panel.add(panelUpload);
		// panelScroll.add(panelFileDetail);
		panel.add(upload);
		// Add an event handler to the form.
		// form.addSubmitHandler(new FormPanel.SubmitHandler() {
		// public void onSubmit(SubmitEvent event) {
		//
		// }
		// });
		// form.addSubmitCompleteHandler(new FormPanel.SubmitCompleteHandler() {
		// public void onSubmitComplete(SubmitCompleteEvent event) {
		// if (event.getResults().contains("success")) {
		// Window.alert("File uploaded");
		// // updateFileNameInDatabase();
		// } else {
		// try {
		// int start = event.getResults().indexOf(">");
		// int end = event.getResults().lastIndexOf(".<");
		//
		// Window.alert(event.getResults().substring(start + 1, end));
		// } catch (Exception ex) {
		// // Window.alert(event.getResults().substring(start,
		// // end));
		// }
		// }
		// }
		//
		// });

		HorizontalPanel hpnl = new HorizontalPanel();
		hpnl.add(form);
		// hpnl.add(download())

		hpnl.setSpacing(10);
		add(hpnl);
	}

	// private void fetchExceptionAttachments(final String id) {
	// rpcService.fetchAuditStepExceptions(id, new
	// AsyncCallback<ArrayList<String>>() {
	// FlexTable records = new FlexTable();
	//
	// @Override
	// public void onSuccess(ArrayList<String> result) {
	//
	// for (int i = 0; i < result.size(); i++) {
	// final Anchor lblfilename = new Anchor(result.get(i));
	// // Label lblFileAttached = new Label("Attached");
	// lblfilename.addStyleName("pointerStyle");
	// lblfilename.getElement().getStyle().setTextDecoration(TextDecoration.NONE);
	// lblfilename.setHeight("25px");
	// // lblFileAttached.setHeight("25px");
	// records.setWidth("100%");
	// records.setWidget(i, 0, lblfilename);
	// // records.setWidget(i, 1, lblFileAttached);
	// if (i % 2 != 0) {
	// records.getRowFormatter().addStyleName(i, "jobStatusRow");
	// }
	// panelFileDetail.setWidth("100%");
	// panelFileDetail.add(records);
	// lblfilename.setWordWrap(false);
	// String upperCasedJobLink = lblfilename.getText();
	// lblfilename.setText(upperCasedJobLink);
	// lblfilename.addClickHandler(new ClickHandler() {
	//
	// @Override
	// public void onClick(ClickEvent event) {
	//
	// Window.open("AuditSteps/" + id + "/" + lblfilename.getText(), "name",
	// "");
	// }
	// });
	// }
	//
	// }
	//
	// @Override
	// public void onFailure(Throwable caught) {
	//
	// System.out.println("AuditSteps Failed");
	// }
	//
	// });
	// }

	public Button download() {
		Button btn = new Button("Download");
		add(btn);
		btn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

			}
		});
		return btn;
	}

	public FormPanel getForm() {
		return form;
	}

	public void setForm(FormPanel form) {
		this.form = form;
	}

	public ButtonRound getBtnSubmit() {
		return btnSubmit;
	}

	public void setBtnSubmit(ButtonRound btnSubmit) {
		this.btnSubmit = btnSubmit;
	}

	public FileUpload getUpload() {
		return upload;
	}
}
