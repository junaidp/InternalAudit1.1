package com.internalaudit.client.upload;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.TextDecoration;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.internalaudit.client.InternalAuditService;
import com.internalaudit.client.InternalAuditServiceAsync;
import com.internalaudit.client.view.ButtonRound;

public class AuditWorkProgramUpload extends VerticalPanel {
	InternalAuditServiceAsync rpcService = GWT.create(InternalAuditService.class);
	FormPanel form;
	HorizontalPanel panelFileDetail = new HorizontalPanel();

	public AuditWorkProgramUpload(final String auditProcedureId, String mainFolder) {
		form = new FormPanel();
		form.setAction(GWT.getModuleBaseURL() + "AuditWorkProgramUpload");
		form.setEncoding(FormPanel.ENCODING_MULTIPART);
		form.setMethod(FormPanel.METHOD_POST);
		HorizontalPanel panel = new HorizontalPanel();
		form.setWidget(panel);
		ScrollPanel panelScroll = new ScrollPanel();
		panelScroll.setHeight("120px");
		VerticalPanel uploadPanel = new VerticalPanel();
		final FileUpload upload = new FileUpload();
		upload.setName(auditProcedureId + ":" + mainFolder);
		upload.setTitle("AuditProcedureUploads");
		uploadPanel.add(upload);

		// Add a 'submit' button.
		ButtonRound btnSubmit = new ButtonRound("Submit");
		btnSubmit.getElement().getStyle().setMarginTop(10, Unit.PX);
		btnSubmit.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				form.submit();

			}
		});
		uploadPanel.add(btnSubmit);
		panel.add(uploadPanel);
		panelScroll.add(panelFileDetail);
		panel.add(panelScroll);

		fetchProcedureAttachments(auditProcedureId, mainFolder);

		// Add an event handler to the form.
		form.addSubmitCompleteHandler(new FormPanel.SubmitCompleteHandler() {
			public void onSubmitComplete(SubmitCompleteEvent event) {
				if (event.getResults().contains("success")) {
					Window.alert("File uploaded");
					// updateFileNameInDatabase();
				} else {
					try {
						int start = event.getResults().indexOf(">");
						int end = event.getResults().lastIndexOf(".<");
						// Window.alert(event.getResults());
						Window.alert(event.getResults().substring(start + 1, end));
					} catch (Exception ex) {
						// Window.alert(event.getResults().substring(start,
						// end));
					}
				}
			}

		});

		HorizontalPanel hpnl = new HorizontalPanel();
		hpnl.add(form);
		// hpnl.add(download());
		hpnl.setSpacing(10);
		add(hpnl);
	}

	private void fetchProcedureAttachments(final String auditProcedureId, final String mainFolder) {
		rpcService.fetchAuditStepsProcerdure(auditProcedureId, mainFolder, new AsyncCallback<ArrayList<String>>() {

			FlexTable records = new FlexTable();

			@Override
			public void onSuccess(ArrayList<String> result) {
				panelFileDetail.clear();
				for (int i = 0; i < result.size(); i++) {
					final Anchor lblfilename = new Anchor(result.get(i));

					lblfilename.addStyleName("pointerStyle");
					lblfilename.getElement().getStyle().setTextDecoration(TextDecoration.NONE);
					lblfilename.setHeight("25px");

					records.setWidth("100%");
					records.setWidget(i, 0, lblfilename);

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

							Window.open(mainFolder + "/" + auditProcedureId + "/" + lblfilename.getText(), "name", "");
						}
					});
				}

			}

			@Override
			public void onFailure(Throwable caught) {

				System.out.println("fetchAuditProcedure Failed");
			}

		});
	}

	public ButtonRound download() {
		ButtonRound btn = new ButtonRound("Download");
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
}
