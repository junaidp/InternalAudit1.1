package com.internalaudit.client.widgets;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DateBox;

public class AuditSchedulingBk extends Composite {
	
	private Label jobName;
	
	private DateBox startDate;
	
	private Label endDate;
	
	private HorizontalPanel containerPanel;
	
	private int estimatedWeeks;
	
	private int jobId;

	private Label timeLine;
	
	private VerticalPanel timeLineContainer;
	
	private Button saveButton;
	
	public AuditSchedulingBk() {
		
		//TODO : Add time line column
		jobName = new Label();
		
		startDate = new DateBox();
		startDate.setFormat(new DateBox.DefaultFormat(DateTimeFormat.getFormat("dd, MM, yyyy")));
		endDate = new Label();
				
		saveButton = new Button("Save");
		
		containerPanel = new HorizontalPanel();
		
		
		timeLine = new Label();
		
		timeLineContainer = new VerticalPanel();
		
			
		timeLineContainer.setStyleName("line");
		
		timeLineContainer.add(timeLine);
		
		
		initWidget(containerPanel);
		
		containerPanel.setStyleName("auditScheduleRow");
		
		containerPanel.add(jobName);
		containerPanel.add(startDate);
		containerPanel.add(endDate);
		containerPanel.add(timeLineContainer);
		containerPanel.add(saveButton);
		
		jobName.setStyleName("jobName");
		startDate.setStyleName("startDate");
		endDate.setStyleName("endDate");
		timeLine.setStyleName("timeLine");
		
		
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


	public Button getSaveButton() {
		return saveButton;
	}


	public void setSaveButton(Button saveButton) {
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
