package com.internalaudit.client.view.dashboard;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class JobsExecutionPanel extends Composite  {

	private static JobsExecutionPanelUiBinder uiBinder = GWT
			.create(JobsExecutionPanelUiBinder.class);

	interface JobsExecutionPanelUiBinder extends
			UiBinder<Widget, JobsExecutionPanel> {
	}

	public JobsExecutionPanel() {
		initWidget(uiBinder.createAndBindUi(this));
		
	}
	@UiField
	VerticalPanel container;
	@UiField 
	Label heading;
	
	public void setData(ArrayList<String> names){
		container.setSpacing(4);
		
		for(int i=0; i< names.size(); i++){
			Label lblName = new Label();
			lblName.setStyleName("white");
			container.add(lblName);
			lblName.setText(" . " + names.get(i));
			
		}
		if(names.size()<1){
			Label lblEmpty = new Label("No Jobs found under this criteria");
			lblEmpty.setStyleName("white");
			container.add(lblEmpty);
		}
	}


}
