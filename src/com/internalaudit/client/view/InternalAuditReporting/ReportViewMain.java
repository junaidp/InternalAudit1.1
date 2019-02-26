package com.internalaudit.client.view.InternalAuditReporting;

import java.util.ArrayList;

import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DateBox;
import com.internalaudit.client.view.ButtonRound;
import com.internalaudit.client.view.AuditEngagement.LabelHeading;
import com.internalaudit.client.view.Reporting.ResponsiblePersonRowHeadingView;
import com.internalaudit.client.view.Reporting.ResponsiblePersonRowView;
import com.internalaudit.client.view.ToDo.ToDoRaiserPortal;
import com.internalaudit.shared.ToDo;

public class ReportViewMain extends VerticalPanel {
	VerticalPanel panelSummaryOfAssesment = new VerticalPanel();
	VerticalPanel panelAllFindings = new VerticalPanel();

	public ReportViewMain() {
		setWidth("1200px");
		// setHeight("700px");
		LabelHeading lblMain = new LabelHeading();
		lblMain.setText("Internal Audit Report");
		lblMain.addStyleName("heading");

		HorizontalPanel panelDate = new HorizontalPanel();
		Label lblDate = new Label("Date");
		DateBox dateBox = new DateBox();
		panelDate.add(lblDate);
		panelDate.add(dateBox);
		panelDate.addStyleName("w3-right");

		LabelHeading lblExecutiveSummary = new LabelHeading();
		lblExecutiveSummary.setText("Executive Summary");
		TextArea txtBoxExecutiveSummary = new TextArea();
		txtBoxExecutiveSummary.setWidth("1000px");
		txtBoxExecutiveSummary.setHeight("50px");
		txtBoxExecutiveSummary.addStyleName("w3-pale-red");
		txtBoxExecutiveSummary.setText("Executive summary text");

		LabelHeading lblAuditPurpose = new LabelHeading();
		lblAuditPurpose.setText(" Audit purpose & its linkage with organisational objectives");
		TextArea txtBoxAuditPurpose = new TextArea();
		txtBoxAuditPurpose.setWidth("1000px");
		txtBoxAuditPurpose.setHeight("50px");
		txtBoxAuditPurpose.addStyleName("w3-pale-green");
		txtBoxAuditPurpose.setText("text of audit purpose");

		LabelHeading lblSummaryOfAssesment = new LabelHeading();
		lblSummaryOfAssesment.setText("Summary of overall assessmen");
		ArrayList<ToDo> a = new ArrayList<ToDo>();
		ToDoRaiserPortal todo = new ToDoRaiserPortal(a);

		panelSummaryOfAssesment.setHeight("250px");
		panelSummaryOfAssesment.addStyleName("w3-pale-green");
		panelSummaryOfAssesment.addStyleName("w3-border");
		panelSummaryOfAssesment.add(todo);

		LabelHeading lblKeyFinding = new LabelHeading();
		lblKeyFinding.setText("Key findings");

		LabelHeading lblKeyFinding1 = new LabelHeading();
		lblKeyFinding1.setText("Finding1");
		lblKeyFinding1.setWidth("100px");
		TextArea txtBoxKeFinding1 = new TextArea();
		txtBoxKeFinding1.setText("findings of q");
		txtBoxKeFinding1.setWidth("1000px");
		txtBoxKeFinding1.setHeight("50px");
		txtBoxKeFinding1.addStyleName("w3-pale-red");

		LabelHeading lblAllFinding = new LabelHeading();
		ResponsiblePersonRowHeadingView responsiibleView = new ResponsiblePersonRowHeadingView();
		ResponsiblePersonRowView responbilerow = new ResponsiblePersonRowView();
		lblAllFinding.setText("All Findings");
		panelAllFindings.setHeight("250px");
		panelAllFindings.addStyleName("w3-pale-green");
		panelAllFindings.addStyleName("w3-border");
		panelAllFindings.add(responsiibleView);
		panelAllFindings.add(responbilerow);

		LabelHeading lblOverallControl = new LabelHeading();
		lblOverallControl.setText("Overall Control Assesment");

		FlexTable flexOverallControl = new FlexTable();
		// HorizontalPanel panelFlex = new HorizontalPanel();
		// panelFlex.setWidth("700px");
		flexOverallControl.addStyleName("w3-panel w3-border");
		LabelHeading lblControl = new LabelHeading();
		lblControl.setText("Control");
		LabelHeading lblOperationalEffectiveness = new LabelHeading();
		lblOperationalEffectiveness.setText("Operational Effectiveness");
		LabelHeading lblObservationRef = new LabelHeading();
		lblObservationRef.setText("Observational Ref");
		flexOverallControl.setWidth("700px");
		// flexOverallControl.setHeight("250px");
		flexOverallControl.setWidget(0, 1, lblControl);
		flexOverallControl.setWidget(0, 2, lblOperationalEffectiveness);
		flexOverallControl.setWidget(0, 3, lblObservationRef);
		// panelFlex.add(flexOverallControl);

		LabelHeading lblAnnexure = new LabelHeading();
		lblAnnexure.setText("Annexure");

		ButtonRound btnSave = new ButtonRound("Save");

		ButtonRound btnPrint = new ButtonRound("Print");
		btnPrint.addStyleName("w3-margin");
		btnSave.addStyleName("w3-margin");

		HorizontalPanel panelButton = new HorizontalPanel();
		panelButton.add(btnSave);
		panelButton.add(btnPrint);
		panelButton.addStyleName("w3-right");

		add(lblMain);
		add(panelDate);
		add(lblExecutiveSummary);
		add(txtBoxExecutiveSummary);
		add(lblAuditPurpose);
		add(txtBoxAuditPurpose);
		add(lblSummaryOfAssesment);
		add(panelSummaryOfAssesment);
		add(lblKeyFinding);
		add(lblKeyFinding1);
		add(txtBoxKeFinding1);
		add(lblAllFinding);
		add(panelAllFindings);
		add(lblOverallControl);
		add(flexOverallControl);
		add(lblAnnexure);
		add(panelButton);
	}
}
