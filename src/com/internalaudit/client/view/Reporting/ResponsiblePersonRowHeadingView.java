package com.internalaudit.client.view.Reporting;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.internalaudit.client.view.AuditEngagement.LabelHeading;

public class ResponsiblePersonRowHeadingView extends VerticalPanel {

	public ResponsiblePersonRowHeadingView() {

		createLayout();
	}

	private void createLayout() {

		HorizontalPanel hpnlHeading = new HorizontalPanel();
		LabelHeading heading = new LabelHeading();
		heading.setText("Management Comments");

		LabelHeading auditJob = new LabelHeading();

		auditJob.setText("Audit Job");
		LabelHeading exception = new LabelHeading();
		exception.setText("Observations");

		LabelHeading recommendations = new LabelHeading();
		recommendations.setText("Recommendations");
		LabelHeading managementComments = new LabelHeading();
		managementComments.setText("Management Comments");
		LabelHeading implementaionDate = new LabelHeading();
		implementaionDate.setText("Implementation");

		LabelHeading implicationRating = new LabelHeading();
		implicationRating.setText("Implication Rating");
		LabelHeading responsiblePerson = new LabelHeading();
		responsiblePerson.setText("Responsible Person");
		LabelHeading isAgree = new LabelHeading();
		isAgree.setText("Agree");
		LabelHeading implication = new LabelHeading();
		implication.setText("Implication");

		exception.setWidth("140px");
		implication.setWidth("130px");
		implicationRating.setWidth("140px");
		recommendations.setWidth("140px");
		managementComments.setWidth("140px");
		responsiblePerson.setWidth("110px");
		implementaionDate.setWidth("120px");
		isAgree.setWidth("60px");
		// Label heading = new Label("Management Comments");
		// Label auditJob = new Label("Audit Job");
		// Label exception = new Label("Observations");
		// Label recommendations = new Label("Recommendations");
		// Label managementComments = new Label("Management Comments");
		// Label implementaionDate = new Label("Implementaion");
		// Label implicationRating = new Label("ImplicationRating");
		// Label responsiblePerson = new Label("Responsible Person");
		// Label isAgree = new Label("Agree");
		// Label implication = new Label("implication");
		exception.getElement().getStyle().setMarginLeft(10, Unit.PX);
		implication.getElement().getStyle().setMarginLeft(10, Unit.PX);
		implicationRating.getElement().getStyle().setMarginLeft(10, Unit.PX);
		recommendations.getElement().getStyle().setMarginLeft(10, Unit.PX);
		managementComments.getElement().getStyle().setMarginLeft(10, Unit.PX);
		responsiblePerson.getElement().getStyle().setMarginLeft(10, Unit.PX);
		implementaionDate.getElement().getStyle().setMarginLeft(10, Unit.PX);
		isAgree.getElement().getStyle().setMarginLeft(10, Unit.PX);

		hpnlHeading.add(auditJob);
		hpnlHeading.add(exception);
		hpnlHeading.add(implication);
		hpnlHeading.add(implicationRating);
		hpnlHeading.add(recommendations);
		hpnlHeading.add(managementComments);
		hpnlHeading.add(responsiblePerson);

		hpnlHeading.add(implementaionDate);
		hpnlHeading.add(isAgree);

		add(hpnlHeading);
		// for (int i = 0; i < hpnlHeading.getWidgetCount() - 1; i++) {
		// hpnlHeading.getWidget(i).setWidth("150px");
		// }
		// implication.setWidth("150px");
		// isAgree.setWidth("100px");
		// implementaionDate.setWidth("120px");
		// managementComments.setWidth("150px");
		// auditJob.addStyleName("boldText");
		// exception.addStyleName("boldText");
		// implication.addStyleName("boldText");
		// implicationRating.addStyleName("boldText");
		// responsiblePerson.addStyleName("boldText");
		// managementComments.addStyleName("boldText");
		// implementaionDate.addStyleName("boldText");
		// isAgree.addStyleName("boldText");
		// recommendations.addStyleName("boldText");
		heading.addStyleName("heading_2");
		auditJob.setVisible(false);
	}

}
