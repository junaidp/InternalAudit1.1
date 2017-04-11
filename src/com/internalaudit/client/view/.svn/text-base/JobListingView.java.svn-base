/**
 * 
 */
package com.internalaudit.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.internalaudit.client.presenter.JobListingPresenter.Display;

/**
 * @author Muhammad Yaseen
 *
 */
public class JobListingView extends Composite implements Display {

	private static JobListingUiBinder uiBinder = GWT.create(JobListingUiBinder.class);

	interface JobListingUiBinder extends UiBinder<Widget, JobListingView> {
	}
	
	@UiField
	VerticalPanel jobListContainer;
	private String callingFrom ; 
	
	public JobListingView(String callingFrom) {
		
		initWidget(uiBinder.createAndBindUi(this));
		this.setCallingFrom(callingFrom);
	}

	public VerticalPanel getJobListContainer() {
		return jobListContainer;
	}

	public void setJobListContainer(VerticalPanel jobListContainer) {
		this.jobListContainer = jobListContainer;
	}

	public String getCallingFrom() {
		return callingFrom;
	}

	public void setCallingFrom(String callingFrom) {
		this.callingFrom = callingFrom;
	}


}
