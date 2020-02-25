package com.internalaudit.client.view.InternalAuditReporting;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.FontWeight;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ChangeListener;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;
import com.internalaudit.client.InternalAuditService;
import com.internalaudit.client.InternalAuditServiceAsync;
import com.internalaudit.client.event.ReportingEvent;
import com.internalaudit.client.upload.AuditWorkProgramUpload;
import com.internalaudit.client.view.ButtonRound;
import com.internalaudit.client.view.DisplayAlert;
import com.internalaudit.client.view.AuditEngagement.LabelHeading;
import com.internalaudit.client.view.ToDo.ToDoRaiserPortal;
import com.internalaudit.shared.AssesmentGridDbEntity;
import com.internalaudit.shared.AuditEngagement;
import com.internalaudit.shared.Exceptions;
import com.internalaudit.shared.JobCreation;
import com.internalaudit.shared.JobStatusDTO;
import com.internalaudit.shared.ReportDataEntity;
import com.internalaudit.shared.ToDo;

public class ReportViewMain extends VerticalPanel {
	private ListBox listBoxJobs = new ListBox();
	private InternalAuditServiceAsync rpcService = GWT.create(InternalAuditService.class);
	private VerticalPanel panelSummaryOfAssesment = new VerticalPanel();
	private VerticalPanel panelAllFindings = new VerticalPanel();
	private TextArea txtBoxKeFinding1;
	private LabelHeading lblKeyFinding1;
	private VerticalPanel panelExceptionHigh = new VerticalPanel();
	private VerticalPanel panelControls = new VerticalPanel();
	private Button btnPrint = new Button("print");
	private TextArea txtBoxExecutiveSummary = new TextArea();
	private TextArea txtBoxAuditPurpose = new TextArea();
	private TextArea txtBoxAnnexure = new TextArea();
	private VerticalPanel panelFileUpload = new VerticalPanel();
	private ButtonRound btnSave = new ButtonRound("Save");
	private ReportDataEntity reportData1 = null;
	int selectedJobId = 0;
	private DateBox dateBox = new DateBox();
	private TextBox txtoperational = new TextBox();

	public ReportViewMain(HandlerManager eventBus) {

		fetchJobs();
		getElement().getStyle().setMarginLeft(20, Unit.PX);
		setWidth("850px");
		// setHeight("700px");
		LabelHeading lblMain = new LabelHeading();
		HorizontalPanel panelDate = new HorizontalPanel();
		panelDate.getElement().getStyle().setMarginRight(100, Unit.PX);
		Label lblDate = new Label("Date");

		LabelHeading lblExecutiveSummary = new LabelHeading();

		LabelHeading lblAuditPurpose = new LabelHeading();

		LabelHeading lblSummaryOfAssesment = new LabelHeading();
		LabelHeading lblKeyFinding = new LabelHeading();
		LabelHeading lblKeyFinding1 = new LabelHeading();
		TextArea txtBoxKeFinding1 = new TextArea();
		LabelHeading lblAllFinding = new LabelHeading();
		LabelHeading lblOverallControl = new LabelHeading();

		LabelHeading lblAnnexure = new LabelHeading();

		ButtonRound btnPrint = new ButtonRound("Print");
		HorizontalPanel panelButton = new HorizontalPanel();

		ArrayList<ToDo> a = new ArrayList<ToDo>();
		ToDoRaiserPortal todo = new ToDoRaiserPortal(a);
		JobStatusDTO jobStatus = null;

		valueChangeHandler(eventBus);
		saveHandler();

		styling(eventBus, lblMain, panelDate, lblExecutiveSummary, txtBoxExecutiveSummary, lblAuditPurpose,
				txtBoxAuditPurpose, lblSummaryOfAssesment, lblKeyFinding, lblKeyFinding1, txtBoxKeFinding1,
				lblAllFinding, lblAnnexure, lblOverallControl, lblAnnexure, btnSave, btnPrint, txtBoxAnnexure);
		dateBox.getElement().setPropertyString("placeholder", "dd/mm/yyyy");
		panelButton.add(btnSave);
		panelButton.add(btnPrint);
		panelButton.addStyleName("w3-right");
		panelDate.add(lblDate);
		panelDate.add(dateBox);

		add(lblMain);
		add(listBoxJobs);
		add(panelDate);
		add(lblExecutiveSummary);
		add(txtBoxExecutiveSummary);
		add(lblAuditPurpose);
		add(txtBoxAuditPurpose);
		add(lblSummaryOfAssesment);
		add(panelSummaryOfAssesment);
		add(lblKeyFinding);
		// add(lblKeyFinding1);
		add(panelExceptionHigh);
		add(lblAllFinding);
		add(panelAllFindings);
		add(lblOverallControl);
		add(panelControls);
		add(lblAnnexure);
		add(txtBoxAnnexure);
		// add(txt);

		String jobid = listBoxJobs.getSelectedValue();

		add(panelFileUpload);
		add(panelButton);

	}

	private void saveHandler() {
		btnSave.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// Window.alert("clicked");

				// reportData.setJobId(parseInt);
				if (reportData1 == null || reportData1.getJobId() != Integer.parseInt(listBoxJobs.getSelectedValue())) {
					reportData1 = new ReportDataEntity();
					reportData1.setJobId(selectedJobId);
					// reportData1.setJobId(Integer.parseInt(listBoxJobs.getSelectedValue()));
				}
				reportData1.setAnnexure(txtBoxAnnexure.getText());
				reportData1.setAuditPurpose(txtBoxAuditPurpose.getText());
				reportData1.setOperationalEffectiveness(txtoperational.getText());
				reportData1.setExecutiveSummary(txtBoxExecutiveSummary.getText());
				reportData1.setDate(dateBox.getDatePicker().getValue());
				// reportData1.se

				saveReportData(reportData1);

			}
		});

	}

	private void styling(HandlerManager eventBus, LabelHeading lblMain, HorizontalPanel panelDate,
			LabelHeading lblExecutiveSummary, TextArea txtBoxExecutiveSummary, LabelHeading lblAuditPurpose,
			TextArea txtBoxAuditPurpose, LabelHeading lblSummaryOfAssesment, LabelHeading lblKeyFinding,
			LabelHeading lblKeyFinding1, TextArea txtBoxKeFinding1, LabelHeading lblAllFinding,
			LabelHeading lblOverallControl, LabelHeading lblControl, LabelHeading lblAnnexure, ButtonRound btnSave,
			ButtonRound btnPrint, TextArea txtBoxAnnexure) {
		lblMain.setText("Internal Audit Report");
		lblMain.addStyleName("heading");
		panelDate.addStyleName("w3-right");
		lblExecutiveSummary.setText("Executive Summary");
		lblAuditPurpose.setText("Audit Purpose");
		lblExecutiveSummary.getElement().getStyle().setFontWeight(FontWeight.BOLD);
		lblAuditPurpose.getElement().getStyle().setFontWeight(FontWeight.BOLD);
		lblSummaryOfAssesment.getElement().getStyle().setFontWeight(FontWeight.BOLD);
		lblAllFinding.getElement().getStyle().setFontWeight(FontWeight.BOLD);
		lblKeyFinding.getElement().getStyle().setFontWeight(FontWeight.BOLD);
		lblAnnexure.getElement().getStyle().setFontWeight(FontWeight.BOLD);
		txtBoxExecutiveSummary.setWidth("800px");
		txtBoxExecutiveSummary.setHeight("50px");
		txtBoxExecutiveSummary.getElement().setPropertyString("placeholder", "Executive Summary");
		txtBoxAuditPurpose.getElement().setPropertyString("placeholder", "Audit Purpose");
		txtBoxAnnexure.getElement().setPropertyString("placeholder", "Annexure");
		txtBoxAnnexure.setWidth("800px");
		txtBoxAnnexure.setHeight("50px");
		txtBoxAuditPurpose.setWidth("800px");
		txtBoxAuditPurpose.setHeight("50px");
		// txtBoxAuditPurpose.addStyleName("w3-pale-green");
		// txtBoxAuditPurpose.setText("text of audit purpose");
		lblSummaryOfAssesment.setText("Summary of overall assessment");
		panelSummaryOfAssesment.setHeight("350px");
		panelSummaryOfAssesment.setWidth("1000px");
		panelControls.setWidth("1010px");
		panelExceptionHigh.setWidth("800px");
		panelSummaryOfAssesment.addStyleName("w3-border");
		lblKeyFinding.setText("Key findings");
		lblKeyFinding1.setText("Finding1");
		lblKeyFinding.getElement().getStyle().setMarginTop(40, Unit.PX);
		txtBoxKeFinding1.setText("finding of 1");
		lblKeyFinding1.setWidth("800px");
		txtBoxKeFinding1.setWidth("800px");
		lblAllFinding.setText("All Findings");
		panelAllFindings.setHeight("250px");
		panelAllFindings.addStyleName("w3-border");
		lblOverallControl.setText("Overall Control Assesment");
		dateBox.setFormat(new DateBox.DefaultFormat(DateTimeFormat.getFormat("dd MMMM , yyyy")));

		lblAnnexure.setText("Annexure");
		btnPrint.addStyleName("w3-margin");
		btnSave.addStyleName("w3-margin");

		btnPrint.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				Window.print();

			}
		});
	}

	private void fetchJobs() {

		rpcService.fetchJobs(new AsyncCallback<ArrayList<JobCreation>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("fail fetch jobs");

			}

			@Override
			public void onSuccess(ArrayList<JobCreation> result) {

				listBoxJobs.addItem("Select Job");
				for (int i = 0; i < result.size(); i++) {
					listBoxJobs.addItem(result.get(i).getJobName(), result.get(i).getJobCreationId() + "");
				}
			}
		});

	}

	private void fetchExceptionWithRating(int jobId, int ImplicationRating) {
		// panelExceptionHigh.clear();
		rpcService.fetchJobExceptionWithImplicationRating(jobId, ImplicationRating,
				new AsyncCallback<ArrayList<Exceptions>>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("failed fetchexceptionwithimpicationrating");

					}

					@Override
					public void onSuccess(ArrayList<Exceptions> result) {

						for (int i = 0; i < result.size(); i++) {
							lblKeyFinding1 = new LabelHeading();
							lblKeyFinding1.setText("Finding" + (i + 1));
							txtBoxKeFinding1 = new TextArea();
							txtBoxKeFinding1.setWidth("800px");
							txtBoxKeFinding1.setText(result.get(i).getDetail());
							panelExceptionHigh.add(lblKeyFinding1);
							panelExceptionHigh.add(txtBoxKeFinding1);

						}

					}
				});
	}

	private void fetchControls(int jobId) {

		//

		//
		rpcService.fetchAuditEngagement(jobId, new AsyncCallback<AuditEngagement>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onSuccess(AuditEngagement result) {
				FlexTable flexOverallControl = new FlexTable();
				flexOverallControl.setWidth("1010px");
				LabelHeading lblControl = new LabelHeading();
				lblControl.setWidth("500px");
				LabelHeading lblOperationalEffectiveness = new LabelHeading();
				LabelHeading lblObservationRef = new LabelHeading();
				flexOverallControl.addStyleName("w3-panel w3-border");
				lblControl.setText("Control");
				lblOperationalEffectiveness.setText("Operational Effectiveness");
				lblOperationalEffectiveness.setWidth("200px");
				lblObservationRef.setWidth("200px");
				lblObservationRef.setText("Observational Ref");
				// flexOverallControl.setHeight("250px");
				lblControl.getElement().getStyle().setFontWeight(FontWeight.BOLD);
				lblObservationRef.getElement().getStyle().setFontWeight(FontWeight.BOLD);
				lblOperationalEffectiveness.getElement().getStyle().setFontWeight(FontWeight.BOLD);

				flexOverallControl.setWidget(0, 1, lblControl);
				flexOverallControl.setWidget(0, 2, lblOperationalEffectiveness);
				// flexOverallControl.setWidget(0, 3, lblObservationRef);
				for (int i = 0; i < result.getEngagementDTO().getSelectedControls().size(); i++) {
					Label lblControlData = new Label();

					lblControlData
							.setText(result.getEngagementDTO().getSelectedControls().get(i).getSuggestedControlsName());
					txtoperational.setWidth("200px");
					Label lblReferenceData = new Label();
					lblReferenceData.setWidth("200px");
					lblControlData.setWidth("500px");
					lblReferenceData
							.setText(result.getEngagementDTO().getSelectedControls().get(i).getSuggestedReferenceNo());
					flexOverallControl.setWidget(i + 1, 1, lblControlData);
					flexOverallControl.setWidget(i + 1, 2, txtoperational);
					// flexOverallControl.setWidget(i + 1, 3, lblReferenceData);
				}

				panelControls.add(flexOverallControl);

			}
		});

		// rpcService.fetchControlsForReport(jobId, new
		// AsyncCallback<ArrayList<SuggestedControls>>() {
		//
		// @Override
		// public void onFailure(Throwable caught) {
		// Window.alert("failed fetch suggested controls in report");
		//
		// }
		//
		// @Override
		// public void onSuccess(ArrayList<SuggestedControls> result) {
		// // panelControls.clear();
		// FlexTable flexOverallControl = new FlexTable();
		// flexOverallControl.setWidth("1010px");
		// LabelHeading lblControl = new LabelHeading();
		// lblControl.setWidth("500px");
		// LabelHeading lblOperationalEffectiveness = new LabelHeading();
		// LabelHeading lblObservationRef = new LabelHeading();
		// flexOverallControl.addStyleName("w3-panel w3-border");
		// lblControl.setText("Control");
		// lblOperationalEffectiveness.setText("Operational Effectiveness");
		// lblOperationalEffectiveness.setWidth("200px");
		// lblObservationRef.setWidth("200px");
		// lblObservationRef.setText("Observational Ref");
		// // flexOverallControl.setHeight("250px");
		// lblControl.getElement().getStyle().setFontWeight(FontWeight.BOLD);
		// lblObservationRef.getElement().getStyle().setFontWeight(FontWeight.BOLD);
		// lblOperationalEffectiveness.getElement().getStyle().setFontWeight(FontWeight.BOLD);
		//
		// flexOverallControl.setWidget(0, 1, lblControl);
		// flexOverallControl.setWidget(0, 2, lblOperationalEffectiveness);
		// flexOverallControl.setWidget(0, 3, lblObservationRef);
		// for (int i = 0; i < result.size(); i++) {
		// Label lblControlData = new Label();
		// TextBox txtoperational = new TextBox();
		//
		// lblControlData.setText(result.get(i).getSuggestedControlsName());
		// txtoperational.setWidth("200px");
		// Label lblReferenceData = new Label();
		// lblReferenceData.setWidth("200px");
		// lblControlData.setWidth("500px");
		// lblReferenceData.setText(result.get(i).getSuggestedReferenceNo());
		// flexOverallControl.setWidget(i + 1, 1, lblControlData);
		// flexOverallControl.setWidget(i + 1, 2, txtoperational);
		// flexOverallControl.setWidget(i + 1, 3, lblReferenceData);
		// }
		//
		// panelControls.add(flexOverallControl);
		// }
		// });

	}

	private void valueChangeHandler(final HandlerManager eventBus) {
		listBoxJobs.addChangeListener(new ChangeListener() {

			@Override
			public void onChange(Widget sender) {

				CalearData();

				selectedJobId = Integer.parseInt(listBoxJobs.getSelectedValue());
				final int jobId = Integer.parseInt(listBoxJobs.getSelectedValue());
				fetchAssesmentGrid(jobId);

				int ImplicationRating = 2;
				// fetchControl(jobId, flexOverallControl);
				fetchExceptionWithRating(jobId, ImplicationRating);
				fetchControls(jobId);
				eventBus.fireEvent(new ReportingEvent(panelAllFindings, jobId));
				String mainFolder = "Annexure Uploads";
				AuditWorkProgramUpload annexureUpload = new AuditWorkProgramUpload(jobId + "", mainFolder);
				panelFileUpload.add(annexureUpload);
				fetchReportData(jobId);

			}

		});
	}

	private void fetchAssesmentGrid(final int jobId) {
		rpcService.fetchAssesmentGrid(jobId, new AsyncCallback<ArrayList<AssesmentGridDbEntity>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Failed fetchAssesment");

			}

			@Override
			public void onSuccess(ArrayList<AssesmentGridDbEntity> result) {
				AssesmentGrid assesmentGrid = new AssesmentGrid(result, jobId);
				panelSummaryOfAssesment.add(assesmentGrid);

			}
		});
	}

	private void fetchReportData(int jobId) {
		rpcService.fetchReportDataPopup(jobId, new AsyncCallback<ReportDataEntity>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("fetchReportDataPopup failed");

			}

			@Override
			public void onSuccess(ReportDataEntity result) {
				txtBoxAnnexure.setText(result.getAnnexure());
				txtBoxAuditPurpose.setText(result.getAuditPurpose());
				txtBoxExecutiveSummary.setText(result.getExecutiveSummary());
				txtoperational.setText(result.getOperationalEffectiveness());
				// dateBox.getDatePicker().setValue(result.getDate());
				dateBox.setValue(result.getDate());
				reportData1 = result;
				// result.setReportDataId(result.getReportDataId());
				// result.setAnnexure(txtBoxAnnexure.getText());
				// result.setAuditPurpose(txtBoxAuditPurpose.getText());
				// result.setExecutiveSummary(txtBoxExecutiveSummary.getText());
				// saveReportData(result);

			}
		});

	}

	private void saveReportData(ReportDataEntity reportData) {

		rpcService.saveReportDataPopup(reportData, new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("saving failed");

			}

			@Override
			public void onSuccess(String result) {
				new DisplayAlert("Report Data Saved");

			}

		});
	}

	private void CalearData() {
		txtBoxAnnexure.setText("");
		txtBoxAuditPurpose.setText("");
		txtBoxExecutiveSummary.setText("");
		panelFileUpload.clear();
		panelExceptionHigh.clear();
		panelSummaryOfAssesment.clear();
		panelControls.clear();
		panelAllFindings.clear();
		dateBox.setValue(null);
		txtoperational.setText("");
		// dateBox.getDatePicker().get

	}

}
