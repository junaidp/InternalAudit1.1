package com.internalaudit.client.upload;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.internalaudit.client.InternalAuditService;
import com.internalaudit.client.InternalAuditServiceAsync;
import com.internalaudit.client.view.ButtonRound;
import com.internalaudit.client.view.LoadingPopup;
import com.internalaudit.client.view.PopupsView;
import com.internalaudit.client.view.AuditEngagement.SamplingSheetView;
import com.internalaudit.shared.SamplingExcelSheetEntity;
import com.sencha.gxt.data.shared.loader.Loader;

public class SamplingFileUploader extends VerticalPanel {
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
	private LoadingPopup loadingPopup;

	public SamplingFileUploader(final String subFolder, final String mainFolder, final TextBox lblPopulationData,
			final TextBox lblSampleSizeData, final ListBox listBoxSamplingMethod,final Integer auditStepId,final Anchor lblSavedAuditReport,final Anchor anchorExcelTemplate) {
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
		upload.setName(subFolder + ":" + mainFolder);
		upload.setTitle("SamplingFileUploader");
		// uploadPanel.add(f);
		// FileUploader fa = new FileUploader();
		uploadPanel.add(upload);
		// uploadPanel.add(fa);

		// Add a 'submit' button.
		btnUpload = new Button("Upload Excel");
		btnUpload.getElement().getStyle().setMarginTop(3, Unit.PX);

		btnUpload.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				loadingPopup = new LoadingPopup();
				loadingPopup.display();
				form.submit();
				// btnSubmit.setVisible(false);

			}
		});
		// Add an event handler to the form.

		form.addSubmitCompleteHandler(new FormPanel.SubmitCompleteHandler() {
			public void onSubmitComplete(SubmitCompleteEvent event) {
				
				if (event.getResults().contains("success")) {
					populateSamplingInput(subFolder, mainFolder, lblPopulationData, lblSampleSizeData,
							listBoxSamplingMethod,auditStepId , lblSavedAuditReport, anchorExcelTemplate);
				} else {
					try {
						loadingPopup.remove();
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

	private void populateSamplingInput(final String subFolder, final String mainFolder, final TextBox lblPopulationData,
			final TextBox lblSampleSizeData, final ListBox listBoxSamplingMethod,final Integer auditStepId,final Anchor lblSavedAuditReport,final Anchor anchorExcelTemplate) {
		rpcService.readExcel(subFolder, mainFolder, new AsyncCallback<ArrayList<SamplingExcelSheetEntity>>() {

			@Override
			public void onFailure(Throwable caught) {
				loadingPopup.remove();
				Window.alert("fail");

			}

			@Override
			public void onSuccess(ArrayList<SamplingExcelSheetEntity> result) {
				Window.alert("success");
				loadingPopup.remove();
				SamplingSheetView samplingSheet = new SamplingSheetView(result, lblPopulationData, lblSampleSizeData,
						listBoxSamplingMethod,auditStepId , lblSavedAuditReport, anchorExcelTemplate);
				PopupsView pp = new PopupsView(samplingSheet, "Sampling", "1000px" ,"700px");
				pp.getPopup().setPosition(15, 300);
				pp.getBtnClose().setVisible(false);

			}
		});
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
