package com.internalaudit.client.view.Scheduling;

import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.internalaudit.shared.TimeLineDates;

public class TimeLineJobsView extends FlexTable {
	
	private VerticalPanel vpnlMain = new VerticalPanel();
		
	public TimeLineJobsView(TimeLineDates timeLineDates) {
		setBorderWidth(0);
		if(timeLineDates == null){
			for ( int i = 0; i < 52; i++)
			{
				Image image =  new Image("whiteLine.jpg");
				image.setHeight("10px");
				image.setWidth("17px");
				image.addStyleName("image");
				setWidget(1, i , image);

				
			}
			setCellSpacing(0);
		    setCellPadding(0);
			return;
		}
		int startWeek = timeLineDates.getStartWeek()-1;
		int endWeek = timeLineDates.getEndWeek()-1;
		FlexCellFormatter cellFormatter = getFlexCellFormatter();
		for ( int i = 0; i < 52; i++)
		{
			if(i >= startWeek && i <= endWeek){
				Image image = new Image("blueLine.jpg");
				image.setTitle(timeLineDates.getFormattedStartDat()+" - "+ timeLineDates.getFormattedEndDate());
				image.setHeight("10px");
				image.setWidth("17px");
				setWidget(1, i ,image );
				image.addStyleName("image");
				cellFormatter.setColSpan(1, 0, 2);
		
			}else{
			
			
			Image image =  new Image("whiteLine.jpg");
			image.setHeight("10px");
			image.setWidth("17px");
			image.addStyleName("image");
			setWidget(1, i , image);

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
