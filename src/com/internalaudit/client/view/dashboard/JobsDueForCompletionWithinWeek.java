package com.internalaudit.client.view.dashboard;

public class JobsDueForCompletionWithinWeek extends JobsExecutionPanel {
	
	public JobsDueForCompletionWithinWeek(){
		super.heading.setText("Jobs Due For Completion Within a week ");
		super.background.getElement().getStyle().setBackgroundColor("orange");

	}

}
