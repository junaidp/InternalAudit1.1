package com.internalaudit.client.view.Reporting;

import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.internalaudit.shared.InternalAuditConstants;

public class JobReportView extends HorizontalPanel {
	
	private Anchor jobAnchor;
	private VerticalPanel colorPanel;
	
	public JobReportView(){
		
		jobAnchor = new Anchor("");
		colorPanel = new VerticalPanel();
		add(jobAnchor);
		add(colorPanel);
		setStyling();
	}

	private void setStyling() {
		jobAnchor.addStyleName("linker");
		jobAnchor.setWidth("600px");
		jobAnchor.addStyleName("menuLink");
		colorPanel.setWidth("400px");
		colorPanel.setHeight("25px");
		colorPanel.setStyleName("htmlStyle");
		colorPanel.setSpacing(2);
		setSpacing(2);
		colorPanel.add(new Label(""));
	}
	
	public void setReportStatus(int status){
		
		if(status == 1){
			colorPanel.setTitle(InternalAuditConstants.EXCEPTIONSTOSENT);
			colorPanel.setStyleName("grayBackground");
		}
		else if(status == 2){
			colorPanel.setTitle(InternalAuditConstants.AWAITINGCOMMENTS);
			colorPanel.setStyleName("redBackground");
		}
		else if(status == 3){
			colorPanel.setTitle(InternalAuditConstants.COMMENTSRECEIVED);
			colorPanel.setStyleName("blueBackground");
		}
		else if(status == 4){
			colorPanel.setTitle(InternalAuditConstants.REPORTISSUED);
			colorPanel.setStyleName("silverBackground");
		}
		else if(status == 5){
			colorPanel.setTitle(InternalAuditConstants.FINALREPORTISSUED);
			colorPanel.setStyleName("greenBackground");
		}
		
		
		
	}

	public Anchor getJobAnchor() {
		return jobAnchor;
	
	}
	public void setJobAnchor(Anchor jobAnchor) {
		this.jobAnchor = jobAnchor;
	}

	public VerticalPanel getColorPanel() {
		return colorPanel;
	}

	public void setColorPanel(VerticalPanel colorPanel) {
		this.colorPanel = colorPanel;
	}

}
