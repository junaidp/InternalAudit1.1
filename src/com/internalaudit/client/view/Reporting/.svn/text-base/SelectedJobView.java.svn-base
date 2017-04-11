package com.internalaudit.client.view.Reporting;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class SelectedJobView extends Composite{
	private VerticalPanel selectedJobContainer = new VerticalPanel();
	private Label lblJob = new Label();
	
	public SelectedJobView(){
		createLayout();
		
	}

	private void createLayout() {
		HorizontalPanel hpnnlHeadings = new HorizontalPanel();
		hpnnlHeadings.setWidth("900px");
		hpnnlHeadings.add(new Label("Exceptions"));
		hpnnlHeadings.add(new Label("Responsible Person"));
		hpnnlHeadings.add(new Label("Division Head"));
		hpnnlHeadings.add(new Label("Due Date"));
		for(int i =0; i< hpnnlHeadings.getWidgetCount(); i++){
			hpnnlHeadings.getWidget(i).setWidth("200px");
		}
		initWidget(selectedJobContainer);
		selectedJobContainer.add(lblJob);
		selectedJobContainer.add(hpnnlHeadings);
		lblJob.setStyleName("heading");
	}
	
	public Label getLblJob() {
		return lblJob;
	}
	public void setLblJob(Label lblJob) {
		this.lblJob = lblJob;
	}

	public VerticalPanel getSelectedJobContainer() {
		return selectedJobContainer;
	}

	public void setSelectedJobContainer(VerticalPanel selectedJobContainer) {
		this.selectedJobContainer = selectedJobContainer;
	}

}
