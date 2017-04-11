package com.internalaudit.client.view.Scheduling;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ResourceSchedilingView extends Composite {
	
	private Label resourceName;
	
	private HorizontalPanel containerPanel;
	
	private int estimatedWeeks;
	
	private int employeeId;

	private Label timeLine;
	
	private VerticalPanel timeLineContainer;
	
	public ResourceSchedilingView() {
		
		//TODO : Add time line column
		resourceName = new Label();
		
		
		containerPanel = new HorizontalPanel();
		timeLine = new Label();
		
		timeLineContainer = new VerticalPanel();
		
		initWidget(containerPanel);
		
		containerPanel.setStyleName("auditScheduleRow");
		containerPanel.add(resourceName);
		containerPanel.add(timeLineContainer);
		
		containerPanel.setSpacing(1);
		resourceName.setStyleName("jobName");
		timeLine.setStyleName("timeLine");
		resourceName.setWidth("295px");
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



	public Label getTimeLine() {
		return timeLine;
	}


	public VerticalPanel getTimeLineContainer() {
		return timeLineContainer;
	}


	public void setTimeLineContainer(VerticalPanel timeLineContainer) {
		this.timeLineContainer = timeLineContainer;
	}





	public Label getResourceName() {
		return resourceName;
	}





	public void setResourceName(Label resourceName) {
		this.resourceName = resourceName;
	}





	public int getEmployeeId() {
		return employeeId;
	}





	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

}
