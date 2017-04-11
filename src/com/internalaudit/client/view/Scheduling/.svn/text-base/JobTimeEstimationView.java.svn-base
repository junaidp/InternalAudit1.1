package com.internalaudit.client.view.Scheduling;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.internalaudit.client.presenter.JobTimeEstimationPresenter.Display;
import com.internalaudit.shared.StrategicDTO;

public class JobTimeEstimationView extends Composite implements Display {

//	@UiField ListBox skillSetListBox;
	
	@UiField ListBox estimatedWeeksListBox;
	@UiField TextBox fieldWorkManHoursTextBox;
	@UiField TextBox managementHoursTextBox;
	@UiField TextBox totalWorkingManHoursTextBox;
	@UiField VerticalPanel skillResourceContainer;
	
	@UiField Button saveJobTimeEst;
	@UiField TextArea highLevelScopeOfWork;
	@UiField ListBox placeofWorkListBox;
	@UiField ListBox travelingDaysListBox;
	@UiField TextBox hoursInclusiveOfTravel;

	@UiField
	Label heading;
	
	
	@UiField
	Label areaOfExpertise;
	
	@UiField
	Label jobTimeEstId;
	
	@UiField
	Button backButton;
	
	private StrategicDTO strategicDTO;
	
	private boolean listCreated = false;
	
	private int jobEstimationId;
	
	private static JobTimeEstimationViewUiBinder uiBinder = GWT
			.create(JobTimeEstimationViewUiBinder.class);

	interface JobTimeEstimationViewUiBinder extends
			UiBinder<Widget, JobTimeEstimationView> {
	}
	
	public JobTimeEstimationView(StrategicDTO dto) {
		
		initWidget(uiBinder.createAndBindUi(this));
		
		this.setStrategicDTO(dto);
	}

	@Override
	public ListBox getEstWeeksListBox() {
		
		return estimatedWeeksListBox;
	}

	@Override
	public TextBox getFieldWorkManHours() {
		return fieldWorkManHoursTextBox;
	}

	@Override
	public TextBox getMgmtHours() {
		return managementHoursTextBox;
	}

	@Override
	public TextBox getTotalWorkingManHours() {
		return totalWorkingManHoursTextBox;
	}
	
	
	public VerticalPanel getSkillsResourceContainer() {
		return skillResourceContainer;
	}

	public void setSkillsResourceContainer(VerticalPanel skillResourceContainer) {
		this.skillResourceContainer = skillResourceContainer;
	}

	public Button getSaveJobTimeEst() {
		return saveJobTimeEst;
	}

	public void setSaveJobTimeEst(Button saveJobTimeEst) {
		this.saveJobTimeEst = saveJobTimeEst;
	}

	public TextBox getTotalWorkingManHoursTextBox() {
		return totalWorkingManHoursTextBox;
	}

	public VerticalPanel getSkillResourceContainer() {
		return skillResourceContainer;
	}

	public TextArea getHighLevelScopeOfWork() {
		return highLevelScopeOfWork;
	}

	public ListBox getPlaceofWorkListBox() {
		return placeofWorkListBox;
	}

	public ListBox getTravelingDaysListBox() {
		return travelingDaysListBox;
	}

	public TextBox getHoursInclusiveOfTravel() {
		return hoursInclusiveOfTravel;
	}

	public StrategicDTO getStrategicDTO() {
		return strategicDTO;
	}

	public void setStrategicDTO(StrategicDTO strategicDTO) {
		this.strategicDTO = strategicDTO;
	}

	public Label getHeading() {
		return heading;
	}

	public void setHeading(Label heading) {
		this.heading = heading;
	}

	public Button getBackButton() {
		return backButton;
	}

	public void setBackButton(Button backButton) {
		this.backButton = backButton;
	}

	public Label getAreaOfExpertise() {
		return areaOfExpertise;
	}

	public void setAreaOfExpertise(Label areaOfExpertise) {
		this.areaOfExpertise = areaOfExpertise;
	}

	public boolean isListCreated() {
		return listCreated;
	}

	public void setListCreated(boolean listCreated) {
		this.listCreated = listCreated;
	}

	@Override
	public void setJobEstimationId(int jobTimeEstimationId) {
		this.jobEstimationId  = jobTimeEstimationId;
		
	}
	
	public int getJobEstimationId() {
		return this.jobEstimationId; 
		
	}

	public Label getJobTimeEstId() {
		return jobTimeEstId;
	}

	public void setJobTimeEstId(Label jobTimeEstId) {
		this.jobTimeEstId = jobTimeEstId;
	}

}
