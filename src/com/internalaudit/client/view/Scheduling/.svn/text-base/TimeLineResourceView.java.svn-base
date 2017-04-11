package com.internalaudit.client.view.Scheduling;

import java.util.ArrayList;

import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.internalaudit.shared.JobCreation;

public class TimeLineResourceView extends FlexTable {

	private VerticalPanel vpnlMain = new VerticalPanel();
	
	public TimeLineResourceView(ArrayList<JobCreation> jobs) {
		setBorderWidth(0);
			FlexCellFormatter cellFormatter = getFlexCellFormatter();
		for ( int i = 0; i < 52; i++)
		{
			for(int j=0; j<jobs.size(); j++){
				
			if(i >= jobs.get(j).getTimeLineDates().getStartWeek() && i <= jobs.get(j).getTimeLineDates().getEndWeek()){
				Image image = new Image("blueLine.jpg");
				image.setTitle(jobs.get(j).getJobName()+ " : "+ jobs.get(j).getTimeLineDates().getFormattedStartDat() +" - "+jobs.get(j).getTimeLineDates().getFormattedEndDate()  );
				image.setHeight("10px");
				image.setWidth("17px");
				setWidget(1, i ,image );
				image.setStyleName("image");
				cellFormatter.setColSpan(1, 0, 2);
				break;
			}
			else{
				Image image =  new Image("whiteLine.jpg");
				image.setHeight("10px");
				image.setWidth("17px");
				image.setStyleName("image");
				setWidget(1, i , image);
			}

		}
	}

		setCellSpacing(0);
		setCellPadding(0);
	}

	public VerticalPanel getVpnlMain() {
		return vpnlMain;
	}
	public void setVpnlMain(VerticalPanel vpnlMain) {
		this.vpnlMain = vpnlMain;
	}

}
