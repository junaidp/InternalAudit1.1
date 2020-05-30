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
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent;
import com.google.gwt.user.client.ui.Frame;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.internalaudit.client.InternalAuditService;
import com.internalaudit.client.InternalAuditServiceAsync;
import com.internalaudit.client.view.ButtonRound;

public class AuditWorkProgramUpload extends VerticalPanel {
	InternalAuditServiceAsync rpcService = GWT.create(InternalAuditService.class);
	private FormPanel form;
	HorizontalPanel panelFileDetail = new HorizontalPanel();
	Anchor lblfilename;
	String file;
	Button btnUpload;
	FileUpload upload;
	HorizontalPanel panelContainer = new HorizontalPanel();
	private VerticalPanel uploadPanel;
	private Image delete;

	public AuditWorkProgramUpload(final String auditProcedureId, final String mainFolder) {
		// FileUploadField f = new FileUploadField();
		form = new FormPanel();
		form.setAction(GWT.getModuleBaseURL() + "AuditWorkProgramUpload");
		form.setEncoding(FormPanel.ENCODING_MULTIPART);
		form.setMethod(FormPanel.METHOD_POST);
		HorizontalPanel panel = new HorizontalPanel();
		form.setWidget(panel);
		ScrollPanel panelScroll = new ScrollPanel();
		panelScroll.setHeight("65px");
		uploadPanel = new VerticalPanel();
		upload = new FileUpload();
		upload.setName(auditProcedureId + ":" + mainFolder);
		upload.setTitle("AuditProcedureUploads");
		// uploadPanel.add(f);
		// FileUploader fa = new FileUploader();
		uploadPanel.add(upload);
		// uploadPanel.add(fa);

		// Add a 'submit' button.
		btnUpload = new Button("Upload");
		btnUpload.getElement().getStyle().setMarginTop(3, Unit.PX);
		btnUpload.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				form.submit();
				// btnSubmit.setVisible(false);

			}
		});

		fetchProcedureAttachments(auditProcedureId, mainFolder);

		// Add an event handler to the form.
		form.addSubmitCompleteHandler(new FormPanel.SubmitCompleteHandler() {
			public void onSubmitComplete(SubmitCompleteEvent event) {
				if (event.getResults().contains("success")) {
					Window.alert("File uploaded");
					// btnSubmit.setVisible(false);
					fetchProcedureAttachments(auditProcedureId, mainFolder);
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

		add(form);
		uploadPanel.add(btnUpload);
		panel.add(uploadPanel);
		panelScroll.add(panelFileDetail);
		panel.add(panelScroll);
		add(panelContainer);
	}

	public void fetchProcedureAttachments(final String auditProcedureId, final String mainFolder) {
		rpcService.fetchAuditStepsProcerdure(auditProcedureId, mainFolder, new AsyncCallback<ArrayList<String>>() {

			FlexTable records = new FlexTable();

			@Override
			public void onSuccess(ArrayList<String> result) {
				panelFileDetail.clear();
				// commented by Moqeet as per requirenment
				// if (result.size() >= 1) {
				// btnUpload.setVisible(false);
				// } else {
				// btnUpload.setVisible(true);
				// }
				for (int i = 0; i < result.size(); i++) {
					lblfilename = new Anchor(result.get(i));
					delete = new Image("images/deleteIcon.png");
					lblfilename.addStyleName("pointerStyle");
					lblfilename.getElement().getStyle().setTextDecoration(TextDecoration.NONE);
					lblfilename.setHeight("25px");

					// records.setWidth("100%");
					records.setWidget(i, 0, lblfilename);
					records.setWidget(i, 1, delete);

					if (i % 2 != 0) {
						records.getRowFormatter().addStyleName(i, "jobStatusRow");
					}
					// panelFileDetail.setWidth("350px");
					panelFileDetail.add(records);
					lblfilename.setWordWrap(false);
					final String upperCasedJobLink = result.get(i);
					lblfilename.setText(upperCasedJobLink);

					clickHandlers(auditProcedureId, mainFolder, delete, upperCasedJobLink);

				}

			}

			private void clickHandlers(final String auditProcedureId, final String mainFolder, Image delete,
					final String upperCasedJobLink) {
				delete.addClickHandler(new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {

						rpcService.deleteAttachmentFile(auditProcedureId, mainFolder, upperCasedJobLink,
								new AsyncCallback<String>() {

									@Override
									public void onFailure(Throwable caught) {
										Window.alert("fail deletefile");
									}

									@Override
									public void onSuccess(String result) {
										Window.alert("File Deleted Successfully");
										fetchProcedureAttachments(auditProcedureId, mainFolder);
									}
								});

					}
				});
				lblfilename.addClickHandler(new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						HTML htmlPanel = new HTML(
								"<embed src='http://www.google.com'  width='100%' height='500px'></embed>");

						// Frame frame = new
						// Frame("/war/NotificationUploads/202/client%20mang.png");
						Frame frame = new Frame(mainFolder + "/" + auditProcedureId + "/" + upperCasedJobLink);
						// frame.setWidth("1000px");
						// frame.setHeight("500px");
						frame.setPixelSize(800, 300);
						// panelContainer.add(frame);
						// (frame)commented by moqeet as rafery said
						Window.open(mainFolder + "/" + auditProcedureId + "/" + upperCasedJobLink, "name", "");

					}

				});
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

	// public void disableField() {
	// btnUpload.setVisible(false);
	// }

	public FormPanel getForm() {
		return form;
	}

	public void setForm(FormPanel form) {
		this.form = form;
	}

	public Anchor getLblfilename() {
		return lblfilename;
	}

	public void setLblfilename(Anchor lblfilename) {
		this.lblfilename = lblfilename;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public Button getBtnUpload() {
		return btnUpload;
	}

	public void setBtnUpload(ButtonRound btnUpload) {
		this.btnUpload = btnUpload;
	}

	public FileUpload getUpload() {
		return upload;
	}

	public void setUpload(FileUpload upload) {
		this.upload = upload;
	}

	public VerticalPanel getUploadPanel() {
		return uploadPanel;
	}

	public void setUploadPanel(VerticalPanel uploadPanel) {
		this.uploadPanel = uploadPanel;
	}

	public Image getDelete() {
		return delete;
	}

	public void setDelete(Image delete) {
		this.delete = delete;
	}

	public HorizontalPanel getPanelFileDetail() {
		return panelFileDetail;
	}

	public void setPanelFileDetail(HorizontalPanel panelFileDetail) {
		this.panelFileDetail = panelFileDetail;
	}
}
