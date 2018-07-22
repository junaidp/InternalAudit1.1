package com.internalaudit.client.view.dashboard;

public class JobsDueForCompletionWithinWeek extends JobsExecutionPanel {
	
	public JobsDueForCompletionWithinWeek(){
		super.heading.setText("Jobs Due For Completion Within a week ");
		super.heading.addStyleName("w3-red");
		super.background.addStyleName("w3-red");

	}

}
