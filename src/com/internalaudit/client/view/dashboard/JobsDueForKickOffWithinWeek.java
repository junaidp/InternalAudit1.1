package com.internalaudit.client.view.dashboard;

public class JobsDueForKickOffWithinWeek extends JobsExecutionPanel {
	
	public JobsDueForKickOffWithinWeek(){
		super.heading.setText("Jobs Due For Kick Off Within a week ");
		super.background.getElement().getStyle().setBackgroundColor("orange");
	}

}
