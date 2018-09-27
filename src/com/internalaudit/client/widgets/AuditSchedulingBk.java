package com.internalaudit.client.widgets;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DateBox;
import com.internalaudit.client.view.ButtonRound;

public class AuditSchedulingBk extends Composite {
	
	private Label jobName;
	
	private DateBox startDate;
	
	private Label endDate;
	
	private HorizontalPanel containerPanel;
	
	private int estimatedWeeks;
	
	private int jobId;

	private Label timeLine;
	
	private VerticalPanel timeLineContainer;
	
	private ButtonRound saveButton;
	
	public AuditSchedulingBk() {
		
		//TODO : Add time line column
		jobName = new Label();
		
		startDate = new DateBox();
		startDate.setFormat(new DateBox.DefaultFormat(DateTimeFormat.getFormat("dd, MM, yyyy")));
		endDate = new Label();
				
		saveButton = new ButtonRound("Save");
		
		containerPanel = new HorizontalPanel();
		
		
		timeLine = new Label();
		
		timeLineContainer = new VerticalPanel();
		
			
		timeLineContainer.addStyleName("line");
		
		timeLineContainer.add(timeLine);
		
		
		initWidget(containerPanel);
		
		containerPanel.addStyleName("auditScheduleRow");
		
		containerPanel.add(jobName);
		containerPanel.add(startDate);
		containerPanel.add(endDate);
		containerPanel.add(timeLineContainer);
		containerPanel.add(saveButton);
		
		jobName.addStyleName("jobName");
		startDate.addStyleName("startDate");
		endDate.addStyleName("endDate");
		timeLine.addStyleName("timeLine");
		
		
	}


	public Label getJobName() {
		return jobName;
	}


	public void setJobName(Label jobName) {
		this.jobName = jobName;
	}


	public DateBox getStartDate() {
		return startDate;
	}


	public void setStartDate(DateBox startDate) {
		this.startDate = startDate;
	}


	public Label getEndDate() {
		return endDate;
	}


	public void setEndDate(Label endDate) {
		this.endDate = endDate;
	}


	public HorizontalPanel getContainerPanel() {
		return containerPanel;
	}


	public void setContainerPanel(HorizontalPanel containerPanel) {
		this.containerPanel = containerPanel;
	}


	public int getEstimatedWeeks() {
		return estimatedWeeks;
	}


	public void setEstimatedWeeks(int estimatedWeeks) {
		this.estimatedWeeks = estimatedWeeks;
	}


	public ButtonRound getSaveButton() {
		return saveButton;
	}


	public void setSaveButton(ButtonRound saveButton) {
		this.saveButton = saveButton;
	}


	public int getJobId() {
		return jobId;
	}


	public void setJobId(int jobId) {
		this.jobId = jobId;
	}
	

	public Label getTimeLine() {
		return timeLine;
	}

	
	
}
