package com.internalaudit.client.view.AuditEngagement;

import java.util.ArrayList;

import com.google.gwt.dom.client.Style.FontWeight;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.safehtml.shared.SafeUri;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.internalaudit.client.widgets.AddIcon;
import com.internalaudit.shared.ActivityObjective;
import com.internalaudit.shared.RiskObjective;

public class KeyRiskViewNew extends VerticalPanel {

	private Label lblActivityObjectiveData = new Label("");
	Label lblactivityObjective = new Label("Activity Objective");
	Label lblrisks = new Label("Risk");
	Label lblriskRatings = new Label("Inherent Risks");
	Label lblapplicability = new Label("Applicability");
	private TextArea txtRisk = new TextArea();
	private Label lblRef = new Label("");
	//private AddIcon btnAddKeyRisk = new AddIcon();
	private ActivityObjective activityObjective = new ActivityObjective();
	ListBox listBoxRating = new ListBox();
	private int riskId = 0;
	CheckBox checkBoxApplicability = new CheckBox("");
	private Button btnSave = new Button("Save");
	Image delete = new Image("images/deleteIcon.png");
	Label lblReferenceNo = new Label("Reference Number");
	private Button btnSelect = new Button("Select");
	private ListBox listObjectives = new ListBox();

	Image imgRating = new Image();


	public  KeyRiskViewNew() {

		listObjectives.setVisible(false);
		delete.setVisible(false);
		//lblName.addStyleName("w3-panel w3-light-blue");
		lblactivityObjective.addStyleName("w3-panel w3-light-blue");

		lblrisks.addStyleName("w3-panel w3-light-blue");

		lblriskRatings.addStyleName("w3-panel w3-light-blue");

		lblapplicability.addStyleName("w3-panel w3-light-blue");

		lblActivityObjectiveData.setHeight("90px");
		lblActivityObjectiveData.setWidth("220px");


		txtRisk.setWidth("300px");
		txtRisk.setHeight("90px");
		txtRisk.setText("");

		imgRating.addStyleName("w3-panel");
		listBoxRating.addStyleName("w3-panel");
		listBoxRating.addItem("Low", "0");
		listBoxRating.addItem("Medium", "1");
		listBoxRating.addItem("High", "2");


		//checkBoxApplicability.addStyleName("w3-panel");
		lblactivityObjective.getElement().getStyle().setFontWeight(FontWeight.BOLD);
		lblrisks.getElement().getStyle().setFontWeight(FontWeight.BOLD);
		lblriskRatings.getElement().getStyle().setFontWeight(FontWeight.BOLD);
		lblriskRatings.setWidth("130px");
		lblapplicability.getElement().getStyle().setFontWeight(FontWeight.BOLD);
		lblRef.getElement().getStyle().setFontWeight(FontWeight.BOLD);
		lblRef.setWidth("160px");

		lblactivityObjective.getElement().getStyle().setMarginLeft(20, Unit.PX);
		lblrisks.getElement().getStyle().setMarginLeft(20, Unit.PX);
		lblriskRatings.getElement().getStyle().setMarginLeft(20, Unit.PX);
		//lblapplicability.getElement().getStyle().setMarginLeft(20, Unit.PX);
		lblRef.getElement().getStyle().setMarginLeft(20, Unit.PX);
		lblActivityObjectiveData.getElement().getStyle().setMarginLeft(10, Unit.PX);
		txtRisk.getElement().getStyle().setMarginLeft(10, Unit.PX);
		listBoxRating.getElement().getStyle().setMarginLeft(30, Unit.PX);
		checkBoxApplicability.getElement().getStyle().setMarginLeft(50, Unit.PX);

		lblReferenceNo.getElement().getStyle().setFontWeight(FontWeight.BOLD);
		lblReferenceNo.addStyleName("w3-panel w3-light-blue");



		FlexTable flex = new FlexTable();
		VerticalPanel vpLblblRef = new VerticalPanel();
		vpLblblRef.add(lblRef);
		vpLblblRef.setWidth("240px");
		flex.setWidget(0,1, lblReferenceNo);
		flex.setWidget(1,1, vpLblblRef);
		//  flex.setWidget(0,1,actv);


		VerticalPanel containerActivityObjective = new VerticalPanel();

		flex.setWidget(0,0, lblactivityObjective);
		containerActivityObjective.add(listObjectives);
		containerActivityObjective.add(lblActivityObjectiveData);
		containerActivityObjective.setWidth("240px");
		flex.setWidget(1,0,containerActivityObjective);

		flex.setWidget(0,2, lblrisks);
		VerticalPanel vpLblrisk = new VerticalPanel();
		vpLblrisk.add(txtRisk);
		vpLblrisk.setWidth("240px");
		flex.setWidget(1,2,vpLblrisk);
		//flex.setWidget(2,2,btnAddKeyRisk);

		//flex.setWidget(0,3, lblriskRatings);
		HorizontalPanel vpLblriskRating = new HorizontalPanel();

		vpLblriskRating.setWidth("180px");
		vpLblriskRating.add(listBoxRating);
		vpLblriskRating.add(imgRating);

		flex.setWidget(1,3,vpLblriskRating);

		//flex.setWidget(0,4, lblapplicability);
		VerticalPanel panelchckboox = new VerticalPanel();
		panelchckboox.setWidth("150px");
		//panelchckboox.add(checkBoxApplicability);
		flex.setWidget(1,4,btnSelect);
		flex.setWidget(1,5, delete);
		add(flex);
		
		delete.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				
				
			}
		});

	}

	public void hideElemetns(){
		lblActivityObjectiveData.setVisible(false);
		lblactivityObjective.setVisible(false);
		lblrisks.setVisible(false);
		lblriskRatings.setVisible(false);
		lblapplicability.setVisible(false);
		//	btnAddKeyRisk.setVisible(false);
		lblReferenceNo.setVisible(false);
	}

	public void setData(RiskObjective riskObjective){
		lblActivityObjectiveData.setText(riskObjective.getObjectiveId().getObjectiveName());
		txtRisk.setText(riskObjective.getRiskname());
		riskId = riskObjective.getRiskId();
		//TODO add field refNo in subprocess Table , set the refNo from excel file in db manually , and then uncomment below line
		//lblName.setText(riskObjective.getObjectiveId().getSubProcessId().getRefNo()+"");
		lblRef.setText(riskObjective.getRiskReferenceNo());
		//listBoxRating.setse.setItemText(1, riskObjective.getRiskRating());
		for(int i=0; i< listBoxRating.getItemCount(); i++){
			if(Integer.parseInt(listBoxRating.getValue(i)) == riskObjective.getRiskRating()){
				listBoxRating.setSelectedIndex(i);
				//for circle
				imgRating.setUrl(getImgRelatedToRating(listBoxRating.getValue(i)));
			}
		}
		checkBoxApplicability.setChecked(riskObjective.getChecked());
		activityObjective = riskObjective.getObjectiveId();
		//TODO set rating and applicabality (also create these fields in db)

		/*if(objectiveNameAdded){
			lblActivityObjectiveData.setVisible(false);
			lblactivityObjective.setVisible(false);
			lblName.setVisible(false);
			lblReferenceNo.setVisible(false);

			lblrisks.setVisible(false);
			lblriskRatings.setVisible(false);
			lblapplicability.setVisible(false);
		}*/
	}

	private String getImgRelatedToRating(String value) {
		if (value.equals("2")){

			return "redcircle.png";
		}
		else if (value.equals("1")){

			return "yellowcircle.png";
		}
		else if (value.equals("0")){

			return "greencircle.png";

		}
		return "";
	}

	public void getData(RiskObjective riskObjective) {
		riskObjective.setObjectiveId(activityObjective);
		riskObjective.setRiskId(riskId);
		riskObjective.setRiskname(txtRisk.getText());
		riskObjective.setRiskReferenceNo(lblRef.getText());
		riskObjective.setChecked(checkBoxApplicability.isChecked());
		riskObjective.setRiskRating(Integer.parseInt(listBoxRating.getValue(listBoxRating.getSelectedIndex())));

	}

	public void disable() {
		txtRisk.setEnabled(false);
		delete.setVisible(false);

	}

	public ActivityObjective getActivityObjective() {
		return activityObjective;
	}

	public void setActivityObjective(ActivityObjective activityObjective) {
		this.activityObjective = activityObjective;
	}

	public int getRiskId() {
		return riskId;
	}

	public void setRiskId(int riskId) {
		this.riskId = riskId;
	}

	public Button getBtnSave() {
		return btnSave;
	}

	public void setBtnSave(Button btnSave) {
		this.btnSave = btnSave;
	}

	public Button getBtnSelect() {
		return btnSelect;
	}

	public void getData(KeyRiskViewNew keyRiskViewSelected) {
		keyRiskViewSelected.getTxtRisk().setText(txtRisk.getText());
		keyRiskViewSelected.lblRef.setText(lblRef.getText());
		keyRiskViewSelected.riskId = riskId;
		keyRiskViewSelected.lblActivityObjectiveData.setText(lblActivityObjectiveData.getText());
		keyRiskViewSelected.activityObjective = activityObjective;
		keyRiskViewSelected.listBoxRating.setSelectedIndex(listBoxRating.getSelectedIndex());
	}

	public TextArea getTxtRisk() {
		return txtRisk;
	}

	public void setTxtRisk(TextArea txtRisk) {
		this.txtRisk = txtRisk;
	}

	public ListBox getListBoxRating() {
		return listBoxRating;
	}

	public void setListBoxRating(ListBox listBoxRating) {
		this.listBoxRating = listBoxRating;
	}

	protected void populateObjectives(final ArrayList<ActivityObjective> objectives){
		listObjectives.setVisible(true);
		for(int i=0; i< objectives.size(); i++){
			listObjectives.addItem(objectives.get(i).getReferenceNo(), objectives.get(i).getObjectiveId()+"");
			
		}
		
		lblActivityObjectiveData.setText(objectives.get(0).getObjectiveName());
		activityObjective = objectives.get(0);
		listObjectives.addChangeHandler(new ChangeHandler() {
			
			@Override
			public void onChange(ChangeEvent event) {
				String name = fetchObjectiveNameAgaintObjectiveId(objectives, Integer.parseInt(listObjectives.getSelectedValue()));
				lblActivityObjectiveData.setText(name);
			}
		});
	}
	
	private String fetchObjectiveNameAgaintObjectiveId(ArrayList<ActivityObjective> objectives, int value){
		String name = "";
		for(int i=0 ; i< objectives.size(); i++){
			if(objectives.get(i).getObjectiveId() == value){
				activityObjective = objectives.get(i);
				name = objectives.get(i).getObjectiveName();
			}
			
		}
		return name;
		
	}

	public Label getLblRef() {
		return lblRef;
	}

	public void usersView() {
		delete.setVisible(true);
		btnSelect.setVisible(false);
	}

	public void deleteRow(){
		this.removeFromParent();
	}

	public Image getDelete() {
		return delete;
	}

}