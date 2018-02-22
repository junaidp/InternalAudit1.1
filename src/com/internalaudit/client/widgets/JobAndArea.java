package com.internalaudit.client.widgets;

import java.util.ArrayList;

import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;

public class JobAndArea extends Composite {
	
	private HorizontalPanel widgetContainer;
	private Label jobLink;
	private ArrayList<CheckBox> skills;
	private Button saveButton;
	private int jobId;
	
	public JobAndArea() {
		
		widgetContainer = new HorizontalPanel();
		initWidget(widgetContainer);
		jobLink = new Label();
		jobLink.addStyleName("schedulingLink");
		widgetContainer.setWidth("950px");
		widgetContainer.addStyleName("spacer-pad");
		widgetContainer.add(jobLink);
		
		saveButton = new Button();
		saveButton.setText("Save");
		skills = new ArrayList<CheckBox>();
		
	}
	
	public void addCheckBoxesAndButton() {
		
		for ( int i = 0; i < skills.size(); i++ )
			widgetContainer.add( skills.get(i) );
		
		widgetContainer.add( saveButton );
	}

	public HorizontalPanel getWidgetContainer() {
		return widgetContainer;
	}

	public void setWidgetContainer(HorizontalPanel widgetContainer) {
		this.widgetContainer = widgetContainer;
	}

	public Label getJobLink() {
		return jobLink;
	}

	public void setJobLink(Label jobLink) {
		this.jobLink = jobLink;
	}

	public ArrayList<CheckBox> getSkills() {
		return skills;
	}

	public void setSkills(ArrayList<CheckBox> skills) {
		this.skills = skills;
	}

	public int getJobId() {
		return jobId;
	}

	public void setJobId(int jobId) {
		this.jobId = jobId;
	}

	public Button getSaveButton() {
		return saveButton;
	}

	public void setSaveButton(Button saveButton) {
		this.saveButton = saveButton;
	}

}
