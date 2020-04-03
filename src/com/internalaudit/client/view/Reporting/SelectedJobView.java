package com.internalaudit.client.view.Reporting;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.internalaudit.client.view.AuditEngagement.LabelHeading;

public class SelectedJobView extends Composite {
	private VerticalPanel selectedJobContainer = new VerticalPanel();
	private Label lblJob = new Label();

	public SelectedJobView() {
		createLayout();

	}

	private void createLayout() {
		HorizontalPanel hpnnlHeadings = new HorizontalPanel();
		hpnnlHeadings.setWidth("800px");
		// hpnnlHeadings.add(new Label("Observations"));
		LabelHeading lblObservation = new LabelHeading();
		lblObservation.setText("Observations");
		LabelHeading lblImplication = new LabelHeading();
		lblImplication.setText("Implication");
		lblImplication.getElement().getStyle().setMarginLeft(10, Unit.PX);
		lblObservation.setWidth("140px");
		lblImplication.setWidth("130px");
		LabelHeading lblImplicationRating = new LabelHeading();
		lblImplicationRating.setText("Implication Rating");
		lblImplicationRating.getElement().getStyle().setMarginLeft(10, Unit.PX);
		lblImplicationRating.setWidth("140px");
		LabelHeading lblDueDate = new LabelHeading();
		lblDueDate.setText("Due Date");
		lblDueDate.getElement().getStyle().setMarginLeft(10, Unit.PX);
		lblDueDate.setWidth("130px");
		LabelHeading lblRecommendedAction = new LabelHeading();
		lblRecommendedAction.setText("Recommended Action Steps");
		lblRecommendedAction.getElement().getStyle().setMarginLeft(10, Unit.PX);
		lblRecommendedAction.setWidth("130px");
		LabelHeading lblAuditee = new LabelHeading();
		lblAuditee.setText("Auditee");
		lblAuditee.setWidth("130px");
		lblAuditee.getElement().getStyle().setMarginLeft(10, Unit.PX);
		hpnnlHeadings.add(lblObservation);
		hpnnlHeadings.add(lblImplication);
		hpnnlHeadings.add(lblImplicationRating);
		hpnnlHeadings.add(lblDueDate);
		hpnnlHeadings.add(lblRecommendedAction);
		hpnnlHeadings.add(lblAuditee);
		// hpnnlHeadings.add(new Label("Implication"));
		// hpnnlHeadings.add(new Label("Implication Rating"));

		// hpnnlHeadings.add(new Label("Division Head"));
		// Label lblDUeDate = new Label("Due Date");

		// hpnnlHeadings.add(new Label("Recommended Action Steps"));
		// hpnnlHeadings.add(new Label("Auditee"));

		// for (int i = 0; i < hpnnlHeadings.getWidgetCount(); i++) {
		// hpnnlHeadings.getWidget(i).setWidth("150px");
		// }
		lblDueDate.setWidth("90px");
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
