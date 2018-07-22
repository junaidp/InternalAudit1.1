package com.internalaudit.client.view.dashboard;

public class JobsDueForKickOffWithinWeek extends JobsExecutionPanel {
	
	public JobsDueForKickOffWithinWeek(){
		super.heading.setText("Jobs Due For Kick Off Within a week ");
		super.heading.addStyleName("w3-red");
		super.background.addStyleName("w3-red");
	}

}
