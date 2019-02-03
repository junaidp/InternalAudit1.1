package com.internalaudit.client.view.Reporting;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class SelectedJobView extends Composite {
	private VerticalPanel selectedJobContainer = new VerticalPanel();
	private Label lblJob = new Label();

	public SelectedJobView() {
		createLayout();

	}

	private void createLayout() {
		HorizontalPanel hpnnlHeadings = new HorizontalPanel();
		hpnnlHeadings.setWidth("800px");
		hpnnlHeadings.add(new Label("Observations"));
		hpnnlHeadings.add(new Label("Implication"));
		hpnnlHeadings.add(new Label("Implication Rating"));

		// hpnnlHeadings.add(new Label("Division Head"));
		Label lblDUeDate = new Label("Due Date");
		hpnnlHeadings.add(lblDUeDate);
		hpnnlHeadings.add(new Label("Recommended Action Steps"));
		hpnnlHeadings.add(new Label("Auditee"));

		for (int i = 0; i < hpnnlHeadings.getWidgetCount(); i++) {
			hpnnlHeadings.getWidget(i).setWidth("150px");
		}
		lblDUeDate.setWidth("90px");
		initWidget(selectedJobContainer);
		selectedJobContainer.add(lblJob);
		selectedJobContainer.add(hpnnlHeadings);
		lblJob.addStyleName("heading");
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
