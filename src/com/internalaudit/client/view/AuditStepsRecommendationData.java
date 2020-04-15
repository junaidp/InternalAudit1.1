package com.internalaudit.client.view;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.user.datepicker.client.DateBox.DefaultFormat;

public class AuditStepsRecommendationData extends HorizontalPanel {
	private DateBox dueDate = new DateBox();
	private TextArea recommendations = new TextArea();
	private ListBox listAuditee = new ListBox();
	private Image deleteIcon = new Image("images/deleteIcon.png");

	public AuditStepsRecommendationData() {
		createLayout();
	}

	private void createLayout() {
		dueDate.setFormat(new DefaultFormat(DateTimeFormat.getShortDateFormat()));
		add(dueDate);
		dueDate.getElement().setPropertyString("placeholder", "yyyy-mm-dd");
		dueDate.setWidth("100px");
		add(recommendations);

		recommendations.getElement().setPropertyString("placeholder", "Enter text here");
		recommendations.setWidth("137px");
		recommendations.setHeight("300px");
		recommendations.addStyleName("noresize");

		add(listAuditee);
		listAuditee.setWidth("123px");
		listAuditee.setMultipleSelect(true);
		addStyleName("w3 border");

		add(deleteIcon);
	}

	public void disableFields() {
		dueDate.setEnabled(false);
		recommendations.setEnabled(false);
		listAuditee.setEnabled(false);
		deleteIcon.setVisible(false);
	}

	public DateBox getDueDate() {
		return dueDate;
	}

	public void setDueDate(DateBox dueDate) {
		this.dueDate = dueDate;
	}

	public TextArea getRecommendations() {
		return recommendations;
	}

	public void setRecommendations(TextArea recommendations) {
		this.recommendations = recommendations;
	}

	public ListBox getListAuditee() {
		return listAuditee;
	}

	public void setListAuditee(ListBox listAuditee) {
		this.listAuditee = listAuditee;
	}

	public Image getDeleteIcon() {
		return deleteIcon;
	}

	public void setDeleteIcon(Image deleteIcon) {
		this.deleteIcon = deleteIcon;
	}
}
