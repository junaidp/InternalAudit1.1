package com.internalaudit.client.view;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.internalaudit.client.view.AuditEngagement.LabelHeading;
import com.internalaudit.client.widgets.AddImage;

public class AuditStepsRecommendationHeading extends HorizontalPanel {
	private AddImage addMore = new AddImage();

	public AuditStepsRecommendationHeading() {
		createLayout();
	}

	private void createLayout() {
		LabelHeading lblDueDate = new LabelHeading("Due Date");
		add(lblDueDate);
		lblDueDate.setWidth("105px");

		LabelHeading lblRecommendedAction = new LabelHeading("Recommended Action Steps");
		add(lblRecommendedAction);
		lblRecommendedAction.setWidth("137px");

		LabelHeading lbllistAuditee = new LabelHeading("Auditee");
		add(lbllistAuditee);
		lbllistAuditee.setWidth("123px");

		add(addMore);

		getElement().getStyle().setMarginLeft(15, Unit.PX);
	}

	public AddImage getAddMore() {
		return addMore;
	}

	public void setAddMore(AddImage addMore) {
		this.addMore = addMore;
	}
}
