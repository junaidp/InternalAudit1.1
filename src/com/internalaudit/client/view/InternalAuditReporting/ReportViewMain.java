package com.internalaudit.client.view.InternalAuditReporting;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ChangeListener;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;
import com.internalaudit.client.InternalAuditService;
import com.internalaudit.client.InternalAuditServiceAsync;
import com.internalaudit.client.event.ReportingEvent;
import com.internalaudit.client.view.ButtonRound;
import com.internalaudit.client.view.AuditEngagement.LabelHeading;
import com.internalaudit.client.view.ToDo.ToDoRaiserPortal;
import com.internalaudit.shared.Exceptions;
import com.internalaudit.shared.JobCreation;
import com.internalaudit.shared.JobStatusDTO;
import com.internalaudit.shared.SuggestedControls;
import com.internalaudit.shared.ToDo;

public class ReportViewMain extends VerticalPanel {
	private ListBox listBoxJobs = new ListBox();
	private InternalAuditServiceAsync rpcService = GWT.create(InternalAuditService.class);
	private VerticalPanel panelSummaryOfAssesment = new VerticalPanel();
	private VerticalPanel panelAllFindings = new VerticalPanel();
	private HandlerManager eventBus;
	private TextArea txt;
	private TextArea txtBoxKeFinding1;
	private LabelHeading lblKeyFinding1;
	private VerticalPanel panelExceptionHigh = new VerticalPanel();
	private VerticalPanel panelControls = new VerticalPanel();
	private Button btnPrint = new Button("print");
	// LabelHeading lblKeyFinding1;
	// TextArea txtBoxKeFinding1;

	public ReportViewMain(final HandlerManager eventBus) {

		fetchJobs();
		setWidth("850px");
		// setHeight("700px");
		LabelHeading lblMain = new LabelHeading();
		HorizontalPanel panelDate = new HorizontalPanel();
		Label lblDate = new Label("Date");
		DateBox dateBox = new DateBox();
		LabelHeading lblExecutiveSummary = new LabelHeading();
		TextArea txtBoxExecutiveSummary = new TextArea();
		LabelHeading lblAuditPurpose = new LabelHeading();
		TextArea txtBoxAuditPurpose = new TextArea();
		LabelHeading lblSummaryOfAssesment = new LabelHeading();
		LabelHeading lblKeyFinding = new LabelHeading();
		LabelHeading lblKeyFinding1 = new LabelHeading();
		TextArea txtBoxKeFinding1 = new TextArea();
		LabelHeading lblAllFinding = new LabelHeading();
		LabelHeading lblOverallControl = new LabelHeading();

		LabelHeading lblAnnexure = new LabelHeading();
		ButtonRound btnSave = new ButtonRound("Save");
		ButtonRound btnPrint = new ButtonRound("Print");
		HorizontalPanel panelButton = new HorizontalPanel();

		ArrayList<ToDo> a = new ArrayList<ToDo>();
		ToDoRaiserPortal todo = new ToDoRaiserPortal(a);
		JobStatusDTO jobStatus = null;
		AssesmentGrid assesmentGrid = new AssesmentGrid(jobStatus);

		valueChangeHandler(eventBus);

		styling(eventBus, lblMain, panelDate, lblExecutiveSummary, txtBoxExecutiveSummary, lblAuditPurpose,
				txtBoxAuditPurpose, lblSummaryOfAssesment, lblKeyFinding, lblKeyFinding1, txtBoxKeFinding1,
				lblAllFinding, lblAnnexure, lblOverallControl, lblAnnexure, btnSave, btnPrint);

		panelButton.add(btnSave);
		panelButton.add(btnPrint);
		panelButton.addStyleName("w3-right");
		panelDate.add(lblDate);
		panelDate.add(dateBox);
		panelSummaryOfAssesment.add(assesmentGrid);

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
		// add(txt);
		add(panelButton);
		add(btnPrint);

	}

	private void styling(HandlerManager eventBus, LabelHeading lblMain, HorizontalPanel panelDate,
			LabelHeading lblExecutiveSummary, TextArea txtBoxExecutiveSummary, LabelHeading lblAuditPurpose,
			TextArea txtBoxAuditPurpose, LabelHeading lblSummaryOfAssesment, LabelHeading lblKeyFinding,
			LabelHeading lblKeyFinding1, TextArea txtBoxKeFinding1, LabelHeading lblAllFinding,
			LabelHeading lblOverallControl, LabelHeading lblControl, LabelHeading lblAnnexure, ButtonRound btnSave,
			ButtonRound btnPrint) {
		lblMain.setText("Internal Audit Report");
		lblMain.addStyleName("heading");
		panelDate.addStyleName("w3-right");
		lblExecutiveSummary.setText("Executive Summary");
		txtBoxExecutiveSummary.setWidth("800px");
		txtBoxExecutiveSummary.setHeight("50px");
		// txtBoxExecutiveSummary.addStyleName("w3-pale-red");
		txtBoxExecutiveSummary.setText("Executive summary text");
		lblAuditPurpose.setText(" Audit purpose & its linkage with organisational objectives");
		txtBoxAuditPurpose.setWidth("800px");
		txtBoxAuditPurpose.setHeight("50px");
		// txtBoxAuditPurpose.addStyleName("w3-pale-green");
		txtBoxAuditPurpose.setText("text of audit purpose");
		lblSummaryOfAssesment.setText("Summary of overall assessment");
		panelSummaryOfAssesment.setHeight("200px");
		panelSummaryOfAssesment.setWidth("1000px");
		panelControls.setWidth("1010px");
		// panelSummaryOfAssesment.addStyleName("w3-pale-green");
		panelSummaryOfAssesment.addStyleName("w3-border");
		lblKeyFinding.setText("Key findings");
		lblKeyFinding1.setText("Finding1");
		lblKeyFinding.getElement().getStyle().setMarginTop(40, Unit.PX);
		txtBoxKeFinding1.setText("finding of 1");
		// fetchExceptionWithRating(jobId, ImplicationRating);
		lblKeyFinding1.setWidth("100px");
		txtBoxKeFinding1.setWidth("800px");
		txtBoxKeFinding1.setHeight("50px");
		// txtBoxKeFinding1.addStyleName("w3-pale-red");
		lblAllFinding.setText("All Findings");
		panelAllFindings.setHeight("250px");
		// panelAllFindings.addStyleName("w3-pale-green");
		panelAllFindings.addStyleName("w3-border");
		// eventBus.fireEvent(new ReportingEvent(panelAllFindings,
		// Integer.parseInt(listBoxJobs.getSelectedValue())));
		lblOverallControl.setText("Overall Control Assesment");

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
							lblKeyFinding1.setText(result.get(i).getJobName());
							txtBoxKeFinding1 = new TextArea();
							txtBoxKeFinding1.setText(result.get(i).getDetail());
							panelExceptionHigh.add(lblKeyFinding1);
							panelExceptionHigh.add(txtBoxKeFinding1);

						}

					}
				});
	}

	private void fetchControls(int jobId) {
		rpcService.fetchControlsForReport(jobId, new AsyncCallback<ArrayList<SuggestedControls>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("failed fetch suggested controls in report");

			}

			@Override
			public void onSuccess(ArrayList<SuggestedControls> result) {
				// panelControls.clear();
				FlexTable flexOverallControl = new FlexTable();
				flexOverallControl.setWidth("1010px");
				LabelHeading lblControl = new LabelHeading();
				LabelHeading lblOperationalEffectiveness = new LabelHeading();
				LabelHeading lblObservationRef = new LabelHeading();
				flexOverallControl.addStyleName("w3-panel w3-border");
				lblControl.setText("Control");
				lblOperationalEffectiveness.setText("Operational Effectiveness");
				lblObservationRef.setText("Observational Ref");
				// flexOverallControl.setHeight("250px");
				flexOverallControl.setWidget(0, 1, lblControl);
				flexOverallControl.setWidget(0, 2, lblOperationalEffectiveness);
				flexOverallControl.setWidget(0, 3, lblObservationRef);
				for (int i = 0; i < result.size(); i++) {
					Label lblControlData = new Label();
					lblControlData.setText(result.get(i).getSuggestedControlsName());

					Label lblReferenceData = new Label();
					lblReferenceData.setText(result.get(i).getSuggestedReferenceNo());
					flexOverallControl.setWidget(i + 1, 1, lblControlData);

					flexOverallControl.setWidget(i + 1, 3, lblReferenceData);
				}

				panelControls.add(flexOverallControl);
			}
		});

	}

	private void valueChangeHandler(final HandlerManager eventBus) {
		listBoxJobs.addChangeListener(new ChangeListener() {

			@Override
			public void onChange(Widget sender) {
				panelExceptionHigh.clear();
				panelControls.clear();
				panelAllFindings.clear();
				int jobId = Integer.parseInt(listBoxJobs.getSelectedValue());
				int ImplicationRating = 2;
				// fetchControl(jobId, flexOverallControl);
				fetchExceptionWithRating(jobId, ImplicationRating);
				fetchControls(jobId);
				eventBus.fireEvent(new ReportingEvent(panelAllFindings, jobId));
			}
		});
	}

}
