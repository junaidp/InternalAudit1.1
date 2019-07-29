package com.internalaudit.client.view.AuditEngagement;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.internalaudit.client.InternalAuditService;
import com.internalaudit.client.InternalAuditServiceAsync;
import com.internalaudit.client.upload.AuditWorkProgramUpload;

public class SamplingAuditStep extends VerticalPanel {
	InternalAuditServiceAsync rpcService = GWT.create(InternalAuditService.class);
	LabelHeading lblControl = new LabelHeading();
	LabelHeading lblControlList = new LabelHeading();
	LabelHeading lblFrequency = new LabelHeading();
	LabelHeading lblSampleSize = new LabelHeading();
	LabelHeading lblSamplingMethod = new LabelHeading();
	LabelHeading lblPopulationSize = new LabelHeading();
	LabelHeading lblAuditProcedure = new LabelHeading();

	TextArea txtAreaControl = new TextArea();
	ListBox listBoxControlList = new ListBox();
	ListBox listBoxFrequency = new ListBox();
	TextBox lblSampleSizeData = new TextBox();
	ListBox listBoxSamplingMethod = new ListBox();
	TextBox lblPopulationData = new TextBox();
	TextArea txtAreaAuditProcedure = new TextArea();
	HorizontalPanel panelFileDetail = new HorizontalPanel();
	ScrollPanel panelFileDetailScroll = new ScrollPanel();
	AuditWorkProgramUpload fileUpload;

	public SamplingAuditStep(String auditStep) {
		// TODO Auto-generated method stub

		panelFileDetail.setHeight("100px");
		panelFileDetail.setWidth("120px");
		panelFileDetailScroll.setHeight("90px");
		panelFileDetailScroll.setWidth("100px");
		panelFileDetail.add(panelFileDetailScroll);
		lblControl.setText("Control");
		lblControlList.setText("Control List");
		lblFrequency.setText("Frequency");
		lblSampleSize.setText("Sample Size");
		lblSamplingMethod.setText("Sampling Method");
		lblPopulationSize.setText("Population Size");
		lblAuditProcedure.setText("Audit Procedure Performed");
		String mainFolder = "SamplingAuditUploads";
		// Styling of all the labels
		fileUpload = new AuditWorkProgramUpload(auditStep, mainFolder);
		// Styling of all the labels data
		txtAreaControl.getElement().getStyle().setMarginLeft(50, Unit.PX);
		txtAreaAuditProcedure.getElement().getStyle().setMarginLeft(50, Unit.PX);
		txtAreaAuditProcedure.addStyleName("w3-border");
		listBoxControlList.getElement().getStyle().setMarginLeft(50, Unit.PX);
		listBoxFrequency.getElement().getStyle().setMarginLeft(50, Unit.PX);
		lblSampleSizeData.getElement().getStyle().setMarginLeft(50, Unit.PX);
		listBoxSamplingMethod.getElement().getStyle().setMarginLeft(50, Unit.PX);
		lblPopulationData.getElement().getStyle().setMarginLeft(50, Unit.PX);

		// lblSampleSizeData.setText("3");
		lblSampleSizeData.setEnabled(false);

		// lblPopulationData.setText("u00");
		lblPopulationData.setEnabled(false);

		txtAreaControl.setWidth("270px");
		txtAreaControl.setHeight("90px");
		txtAreaControl.setText(
				"Purchase Order Cannot be Issued untill and Unless vendor is selected by the authorized as per the company's approved policy within the procurement module.");

		txtAreaAuditProcedure.setWidth("270px");
		txtAreaAuditProcedure.setHeight("100px");

		listBoxControlList.addItem("Low", "0");
		listBoxControlList.addItem("Medium", "1");
		listBoxControlList.addItem("High", "2");

		listBoxFrequency.addItem("Anually", "0");
		listBoxFrequency.addItem("Quarterly", "1");
		listBoxFrequency.addItem("Monthly", "2");
		listBoxFrequency.addItem("Weekly", "3");
		listBoxFrequency.addItem("Daily", "4");
		listBoxFrequency.addItem("Recurring", "5");

		listBoxSamplingMethod.addItem("Random Selection", "0");
		listBoxSamplingMethod.addItem("Systematic Selection", "1");

		listBoxSamplingMethod.addItem("Block Selection", "2");
		listBoxFrequency.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				getSampleSize(listBoxControlList.getSelectedValue(), listBoxFrequency.getSelectedValue());

			}
		});

		listBoxControlList.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				getSampleSize(listBoxControlList.getSelectedValue(), listBoxFrequency.getSelectedValue());

			}
		});

		FlexTable flex = new FlexTable();

		flex.setWidget(0, 0, lblControl);
		flex.setWidget(0, 1, txtAreaControl);

		flex.setWidget(1, 0, lblControlList);
		flex.setWidget(1, 1, listBoxControlList);

		flex.setWidget(2, 0, lblFrequency);
		flex.setWidget(2, 1, listBoxFrequency);

		flex.setWidget(3, 2, lblSampleSize);
		flex.setWidget(3, 3, lblSampleSizeData);

		flex.setWidget(1, 2, lblSamplingMethod);
		flex.setWidget(1, 3, listBoxSamplingMethod);

		flex.setWidget(2, 2, lblPopulationSize);
		flex.setWidget(2, 3, lblPopulationData);

		flex.setWidget(4, 0, lblAuditProcedure);
		flex.setWidget(4, 1, txtAreaAuditProcedure);
		flex.setWidget(4, 2, fileUpload);
		// flex.setWidget(4,3,panelFileDetail);

		add(flex);

		// rpcService.fetchAuditStepsProcerdure(new
		// AsyncCallback<ArrayList<String>>() {
		// FlexTable records = new FlexTable();
		//
		// @Override
		// public void onSuccess(ArrayList<String> result) {
		// for(int i=0;i<result.size();i++){
		// final Anchor lblfilename = new Anchor(result.get(i));
		// Label lblFileAttached = new Label("Attached");
		// lblfilename.addStyleName("pointerStyle");
		// lblfilename.getElement().getStyle().setTextDecoration(TextDecoration.NONE);
		// lblfilename.setHeight("25px");
		// lblFileAttached.setHeight("25px");
		// records.setWidth("100%");
		// records.setWidget(i, 0, lblfilename);
		// records.setWidget(i, 1, lblFileAttached);
		// //if (i % 2 != 0) {
		// records.getRowFormatter().addStyleName(i, "jobStatusRow");
		// //}
		// //panelFileDetail.setWidth("100%");
		// panelFileDetail.add(records);
		// lblfilename.setWordWrap(false);
		// String upperCasedJobLink = lblfilename.getText();
		// lblfilename.setText(upperCasedJobLink);
		// lblfilename.addClickHandler(new ClickHandler() {
		//
		// @Override
		// public void onClick(ClickEvent event) {
		//
		// Window.open("/EmailAttachmentUpload/"+lblfilename.getText(), "name",
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
		// Window.alert("fetchEmailAttachment Failed");
		// }
		//
		// });

	}

	private void getSampleSize(String control, String frequency) {

		if (control.equals("0") && frequency.equals("0")) {

			lblSampleSizeData.setText("1");
		}
		if (control.equals("1") && frequency.equals("0")) {

			lblSampleSizeData.setText("1");
		}
		if (control.equals("2") && frequency.equals("0")) {

			lblSampleSizeData.setText("1");
		}

		if (control.equals("0") && frequency.equals("1")) {

			lblSampleSizeData.setText("2");
		}
		if (control.equals("1") && frequency.equals("1")) {

			lblSampleSizeData.setText("2");
		}
		if (control.equals("2") && frequency.equals("1")) {

			lblSampleSizeData.setText("2");
		}

		if (control.equals("0") && frequency.equals("2")) {

			lblSampleSizeData.setText("2");
		}
		if (control.equals("1") && frequency.equals("2")) {

			lblSampleSizeData.setText("3");
		}
		if (control.equals("2") && frequency.equals("2")) {

			lblSampleSizeData.setText("3");
		}

		if (control.equals("0") && frequency.equals("3")) {

			lblSampleSizeData.setText("5");
		}

		if (control.equals("1") && frequency.equals("3")) {

			lblSampleSizeData.setText("6");
		}

		if (control.equals("2") && frequency.equals("3")) {

			lblSampleSizeData.setText("8");
		}

		if (control.equals("0") && frequency.equals("4")) {

			lblSampleSizeData.setText("15");
		}

		if (control.equals("1") && frequency.equals("4")) {

			lblSampleSizeData.setText("20");
		}

		if (control.equals("2") && frequency.equals("4")) {

			lblSampleSizeData.setText("25");
		}

		if (control.equals("0") && frequency.equals("5")) {

			lblSampleSizeData.setText("25");
		}

		if (control.equals("1") && frequency.equals("5")) {

			lblSampleSizeData.setText("30");
		}

		if (control.equals("2") && frequency.equals("5")) {

			lblSampleSizeData.setText("40");
		}

		// for populated
		if (frequency.equals("0")) {

			lblPopulationData.setText("1");
		}
		if (frequency.equals("1")) {

			lblPopulationData.setText("4");
		}
		if (frequency.equals("2")) {

			lblPopulationData.setText("12");
		}
		if (frequency.equals("3")) {

			lblPopulationData.setText("52");
		}

	}

	public TextArea getTxtAreaControl() {
		return txtAreaControl;
	}

	public void setTxtAreaControl(TextArea txtAreaControl) {
		this.txtAreaControl = txtAreaControl;
	}

	public ListBox getListBoxControlList() {
		return listBoxControlList;
	}

	public void setListBoxControlList(ListBox listBoxControlList) {
		this.listBoxControlList = listBoxControlList;
	}

	public ListBox getListBoxFrequency() {
		return listBoxFrequency;
	}

	public void setListBoxFrequency(ListBox listBoxFrequency) {
		this.listBoxFrequency = listBoxFrequency;
	}

	public TextBox getLblSampleSizeData() {
		return lblSampleSizeData;
	}

	public void setLblSampleSizeData(TextBox lblSampleSizeData) {
		this.lblSampleSizeData = lblSampleSizeData;
	}

	public ListBox getListBoxSamplingMethod() {
		return listBoxSamplingMethod;
	}

	public void setListBoxSamplingMethod(ListBox listBoxSamplingMethod) {
		this.listBoxSamplingMethod = listBoxSamplingMethod;
	}

	public TextBox getLblPopulationData() {
		return lblPopulationData;
	}

	public void setLblPopulationData(TextBox lblPopulationData) {
		this.lblPopulationData = lblPopulationData;
	}

	public TextArea getTxtAreaAuditProcedure() {
		return txtAreaAuditProcedure;
	}

	public void setTxtAreaAuditProcedure(TextArea txtAreaAuditProcedure) {
		this.txtAreaAuditProcedure = txtAreaAuditProcedure;
	}

	public AuditWorkProgramUpload getFileUpload() {
		return fileUpload;
	}

}