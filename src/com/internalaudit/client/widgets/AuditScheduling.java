package com.internalaudit.client.widgets;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DateBox;

public class AuditScheduling extends Composite {
	
	private Label jobName;
	
	private DateBox startDate;
	
	private Label endDate;
	
	private HorizontalPanel containerPanel;
	
	private int estimatedWeeks;
	
	private int jobId;

	private Label timeLine;
	
	private VerticalPanel timeLineContainer;
	
	public Image getSaveButton() {
		return saveButton;
	}


	public void setSaveButton(Image saveButton) {
		this.saveButton = saveButton;
	}


	private Image saveButton;
	
	public AuditScheduling() {
		
		//TODO : Add time line column
		jobName = new Label();
		
		startDate = new DateBox();//19-05-15
//		startDate.setFormat(new DateBox.DefaultFormat(DateTimeFormat.getFormat("MM/dd/yy")));
		startDate.setFormat(new DateBox.DefaultFormat(DateTimeFormat.getFormat("dd-MM-yy")));
		
		endDate = new Label();
				
		saveButton = new Image("image/save.png");
		saveButton.addStyleName("pointerStyle");
		saveButton.setVisible(false);
		containerPanel = new HorizontalPanel();
//		saveButton.addStyleName("timeLineButton");
		
		timeLine = new Label();
		
		timeLineContainer = new VerticalPanel();
		
			
//		timeLineContainer.addStyleName("line");
		
//		timeLineContainer.add(timeLine);
		
		
		initWidget(containerPanel);
		
		containerPanel.addStyleName("auditScheduleRow");
//		containerPanel.add(saveButton);
//		Label lbl = new Label("-------------------");
//		lbl.addStyleName("white");
		HorizontalPanel hpnlSpace = new HorizontalPanel();
		hpnlSpace.setWidth("95px");
		containerPanel.add(hpnlSpace);
		containerPanel.add(jobName);
		containerPanel.add(startDate);
		containerPanel.add(endDate);
		containerPanel.add(timeLineContainer);
		saveButton.setWidth("10px");
		
		containerPanel.setSpacing(1);
		jobName.addStyleName("jobName");
		startDate.addStyleName("startDate");
		endDate.addStyleName("endDate");
		timeLine.addStyleName("timeLine");
		startDate.setWidth("80px");
		endDate.setWidth("80px");
		endDate.setAutoHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		endDate.setText("hello");
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


	public int getJobId() {
		return jobId;
	}


	public void setJobId(int jobId) {
		this.jobId = jobId;
	}
	

	public Label getTimeLine() {
		return timeLine;
	}


	public VerticalPanel getTimeLineContainer() {
		return timeLineContainer;
	}


	public void setTimeLineContainer(VerticalPanel timeLineContainer) {
		this.timeLineContainer = timeLineContainer;
	}

	
}
