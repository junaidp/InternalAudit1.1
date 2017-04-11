package com.internalaudit.client.widgets;

import java.util.ArrayList;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DateBox;

public class ResourceScheduling extends Composite {
	
	private Label jobName;
	
	private Label resourceName;
	
	//private Label startDate;
	
	//private Label endDate;
	
	private Label timeLine;
	
	private ArrayList<Label> timeLines;
	
	
	private HorizontalPanel timeLineContainer;
	
	private HorizontalPanel containerPanel;
	
	private int estimatedWeeks;
	
	private void setTimeLines()
	{
		String[] names  = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" }; 
		
		timeLines = new ArrayList<Label>();
		for ( int i = 0; i < names.length; i++)
		{
			Label monthTimeLine = new Label( /*names[i]*/ );
					
			monthTimeLine.setStyleName("timeLine");
			
			monthTimeLine.addStyleName("hiddenLine");
		
			timeLines.add( monthTimeLine );
		}
		
		
		for ( int m = 0; m < timeLines.size(); ++m)
			
			timeLineContainer.add(timeLines.get(m));
		
	}
	public ResourceScheduling() {

		jobName = new Label();
		
		//startDate = new Label();
		
		resourceName = new Label();
		
		//endDate = new Label();
		
		timeLine = new Label();
		
		timeLineContainer = new HorizontalPanel();
		
			
		timeLineContainer.setStyleName("line");
		
		//timeLineContainer.add(timeLine);
		
		containerPanel = new HorizontalPanel();
	
		
		initWidget(containerPanel);
		
		containerPanel.setStyleName("auditScheduleRow");
		
		
		//containerPanel.add(jobName);
		containerPanel.add(resourceName);
		//containerPanel.add(startDate);
		//	containerPanel.add(endDate);
		setTimeLines();
		
		containerPanel.add(timeLineContainer);
		
		
		jobName.setStyleName("jobName");
		//startDate.setStyleName("startDate");
		//endDate.setStyleName("endDate");
		timeLine.addStyleName("timeLine");
		resourceName.setStyleName("resource");
		
		
		
	}


	public Label getJobName() {
		return jobName;
	}


	public void setJobName(Label jobName) {
		this.jobName = jobName;
	}

/*
	public Label getStartDate() {
		return startDate;
	}


	public void setStartDate(Label startDate) {
		this.startDate = startDate;
	}


	public Label getEndDate() {
		return endDate;
	}


	public void setEndDate(Label endDate) {
		this.endDate = endDate;
	}

*/
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


	public Label getResourceName() {
		return resourceName;
	}


	public void setResourceName(Label resourceName) {
		this.resourceName = resourceName;
	}


	public Label getTimeLine() {
		return timeLine;
	}


	public void setTimeLine(Label timeLine) {
		this.timeLine = timeLine;
	}
	
	public HorizontalPanel getTimeLineContainer() {
		return this.timeLineContainer;
	}
	public ArrayList<Label> getTimeLines() {
		return timeLines;
	}
	public void setTimeLines(ArrayList<Label> timeLines) {
		this.timeLines = timeLines;
	}
	
}
