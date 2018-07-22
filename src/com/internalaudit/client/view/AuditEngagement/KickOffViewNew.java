package com.internalaudit.client.view.AuditEngagement;


import com.google.gwt.dom.client.Style.FontWeight;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.internalaudit.client.widgets.AddIcon;
import com.internalaudit.shared.ActivityObjective;
import com.internalaudit.shared.RiskObjective;
import com.sencha.gxt.widget.core.client.ProgressBar;

public class KickOffViewNew extends VerticalPanel {

	
	Label lblJobName = new Label("Job Name");
	Label lblStartDate = new Label("Planned Start Date");
	Label lblEndDate = new Label("Planned End Date");
	Label lblPlaning = new Label("Planing");
	Label lblFieldWork = new Label("FieldWork");
	Label lblReporting = new Label("Reeporting");
	
	private Label lblJobNameData = new Label("Jobnamelable");
	private Label lblStartDateData = new Label("Starting Date");
	private Label lblEndDateData = new Label("EndDateData");
	
	private AddIcon btnAddKeyRisk = new AddIcon();
	private ActivityObjective activityObjective = new ActivityObjective();
	ProgressBar progressBarPlanning = new ProgressBar();
	private int riskId = 0;
	ProgressBar progressBarFieldWork = new ProgressBar();
	ProgressBar progressBarReporting = new ProgressBar();
	
	public  KickOffViewNew() {


		lblJobName.addStyleName("w3-panel w3-light-blue");
		lblStartDate.addStyleName("w3-panel w3-light-blue");

		lblEndDate.addStyleName("w3-panel w3-light-blue");

		lblPlaning.addStyleName("w3-panel w3-light-blue");

		lblFieldWork.addStyleName("w3-panel w3-light-blue");
		
		lblReporting.addStyleName("w3-panel w3-light-blue");

	
	

lblFieldWork.setWidth("150px");
	
		
	
		progressBarPlanning.addStyleName("w3-red w3-round-large");
		progressBarPlanning.setWidth("130px");
		progressBarFieldWork.setWidth("130px");
		progressBarReporting.setWidth("130px");
		

		
		progressBarFieldWork.addStyleName("w3-pruple w3-round" );
		
		lblJobName.getElement().getStyle().setFontWeight(FontWeight.BOLD);
		lblStartDate.getElement().getStyle().setFontWeight(FontWeight.BOLD);
		lblEndDate.getElement().getStyle().setFontWeight(FontWeight.BOLD);
		lblPlaning.getElement().getStyle().setFontWeight(FontWeight.BOLD);
		lblPlaning.setWidth("130px");
		lblFieldWork.getElement().getStyle().setFontWeight(FontWeight.BOLD);
		lblReporting.getElement().getStyle().setFontWeight(FontWeight.BOLD);	
		
		lblStartDate.getElement().getStyle().setFontWeight(FontWeight.BOLD);
		lblStartDateData.setWidth("160px");

		lblJobName.getElement().getStyle().setMarginLeft(20, Unit.PX);
		lblStartDate.getElement().getStyle().setMarginLeft(20, Unit.PX);
		lblEndDate.getElement().getStyle().setMarginLeft(20, Unit.PX);
		lblPlaning.getElement().getStyle().setMarginLeft(20, Unit.PX);
		//lblFieldWork.getElement().getStyle().setMarginLeft(20, Unit.PX);
		lblStartDateData.getElement().getStyle().setMarginLeft(20, Unit.PX);
		lblJobNameData.getElement().getStyle().setMarginLeft(10, Unit.PX);
		lblEndDateData.getElement().getStyle().setMarginLeft(10, Unit.PX);
		progressBarPlanning.getElement().getStyle().setMarginLeft(10, Unit.PX);
		progressBarFieldWork.getElement().getStyle().setMarginLeft(10, Unit.PX);
		progressBarReporting.getElement().getStyle().setMarginLeft(10, Unit.PX);


		FlexTable flex = new FlexTable();
		

		
		VerticalPanel vpLblactivitydata = new VerticalPanel();
		
		flex.setWidget(0,0, lblJobName);
		vpLblactivitydata.add(lblJobNameData);
		vpLblactivitydata.setWidth("240px");
		flex.setWidget(1,0,vpLblactivitydata);

		flex.setWidget(0,1, lblStartDate);
		VerticalPanel vpLblblRef = new VerticalPanel();
		vpLblblRef.add(lblStartDateData);
		vpLblblRef.setWidth("240px");
		flex.setWidget(1,1, vpLblblRef);
		//  flex.setWidget(0,1,actv);
		
		flex.setWidget(0,2, lblEndDate);
		VerticalPanel vpLblrisk = new VerticalPanel();
		vpLblrisk.add(lblEndDateData);
		vpLblrisk.setWidth("240px");
		flex.setWidget(1,2,vpLblrisk);
		flex.setWidget(2,2,btnAddKeyRisk);

		flex.setWidget(0,3, lblPlaning);
		VerticalPanel vpLblriskRating = new VerticalPanel();
		vpLblriskRating.add(progressBarPlanning);
		vpLblriskRating.setWidth("180px");
		flex.setWidget(1,3,vpLblriskRating);

		flex.setWidget(0,4, lblFieldWork);
		VerticalPanel vpLblriskRating1 = new VerticalPanel();
		vpLblriskRating1.add(progressBarFieldWork);
		vpLblriskRating1.setWidth("180px");
		flex.setWidget(1,4,vpLblriskRating1);
		
		flex.setWidget(0,5, lblReporting);
		flex.setWidget(1,5,progressBarReporting);
		add(flex);
		
	}
}