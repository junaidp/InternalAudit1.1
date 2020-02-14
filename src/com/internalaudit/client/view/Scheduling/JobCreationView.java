/**
 * 
 */
package com.internalaudit.client.view.Scheduling;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.internalaudit.client.presenter.JobCreationPresenter.Display;
import com.internalaudit.shared.StrategicDTO;

/**
 * @author Muhammad Yaseen
 *
 */
public class JobCreationView extends Composite implements Display {

	@UiField
	TextBox domainText;
	@UiField
	ListBox relevantDept;
	@UiField
	TextBox riskRating;
	@UiField
	TextBox estimatedWeeks;
	@UiField
	HorizontalPanel skillResourceContainer;

	@UiField
	TextBox techSkill;
	@UiField
	ListBox softSkill;
	@UiField
	ListBox proposedResources;
	@UiField
	ListBox auditHead;
	private StrategicDTO strategicDTO;

	@UiField
	Label heading;

	@UiField
	Button saveJobCreation;

	@UiField
	Button backButton;

	@UiField
	TextBox jobCreationId;

	private static JobCreationViewUiBinder uiBinder = GWT.create(JobCreationViewUiBinder.class);

	interface JobCreationViewUiBinder extends UiBinder<Widget, JobCreationView> {
	}

	public JobCreationView(StrategicDTO strategicDTO) {
		initWidget(uiBinder.createAndBindUi(this));

		this.strategicDTO = strategicDTO;

	}

	public TextBox getEstimatedWeeks() {
		return estimatedWeeks;
	}

	public void setEstimatedWeeks(TextBox estimatedWeeks) {
		this.estimatedWeeks = estimatedWeeks;
	}

	public ListBox getProposedResources() {
		return proposedResources;
	}

	public void setProposedResources(ListBox proposedResources) {
		this.proposedResources = proposedResources;
	}

	public HorizontalPanel getSkillResourceContainer() {
		return skillResourceContainer;
	}

	public void setSkillResourceContainer(HorizontalPanel skillResourceContainer) {
		this.skillResourceContainer = skillResourceContainer;
	}

	public TextBox getRiskRating() {
		return riskRating;
	}

	public void setRiskRating(TextBox riskRating) {
		this.riskRating = riskRating;
	}

	public StrategicDTO getStrategicDTO() {
		return strategicDTO;
	}

	public void setStrategicDTO(StrategicDTO strategicDTO) {
		this.strategicDTO = strategicDTO;
	}

	public TextBox getDomainText() {
		return domainText;
	}

	public void setDomainText(TextBox domainText) {
		this.domainText = domainText;
	}

	public TextBox getTechSkill() {
		return techSkill;
	}

	public void setTechSkill(TextBox techSkill) {
		this.techSkill = techSkill;
	}

	public Label getHeading() {
		return heading;
	}

	public void setHeading(Label heading) {
		this.heading = heading;
	}

	public Button getSaveJobCreation() {
		return saveJobCreation;
	}

	public void setSaveJobCreation(Button saveJobCreation) {
		this.saveJobCreation = saveJobCreation;
	}

	public Button getBackButton() {
		return backButton;
	}

	public void setBackButton(Button backButton) {
		this.backButton = backButton;
	}

	public ListBox getSoftSkill() {
		return softSkill;
	}

	public void setSoftSkill(ListBox softSkill) {
		this.softSkill = softSkill;
	}

	public ListBox getAuditHead() {
		return auditHead;
	}

	public void setAuditHead(ListBox auditHead) {
		this.auditHead = auditHead;
	}

	public TextBox getJobCreationId() {
		return jobCreationId;
	}

	public void setJobCreationId(TextBox jobCreationId) {
		this.jobCreationId = jobCreationId;
	}

	public ListBox getRelevantDept() {
		return relevantDept;
	}

	public void setRelevantDept(ListBox relevantDept) {
		this.relevantDept = relevantDept;
	}

	public void disableFields() {
		domainText.setEnabled(false);
		relevantDept.setEnabled(false);
		riskRating.setEnabled(false);
		estimatedWeeks.setEnabled(false);
		techSkill.setEnabled(false);
		softSkill.setEnabled(false);
		proposedResources.setEnabled(false);
		auditHead.setEnabled(false);
		saveJobCreation.setVisible(false);

	}

}
