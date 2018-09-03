package com.internalaudit.client.view;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
//import com.google.gwt.user.client.ui.TextBox;
import com.sencha.gxt.widget.core.client.form.TextField;

public class ConsolidationView extends Composite{
	private Label area;
	private Image riskRating;
	private TextField auditableUnit;
	private Label tab;
	private Label comments = new Label(" Feedback ");
	private Image submitted = new Image(" images/tick.png ");
	// new listbox for process
	private ListBox listBoxProcess;
	//new listbox for subprocess
	private ListBox listBoxSubProcess;
	private ListBox listBoxJobType;
	private Button btnSave = new Button("Save");
	private Button btnSubmit = new Button("Submit");
	private Button btnApprove = new Button("Approve");
	private Button btnDecline = new Button("Delete");
	private Button btnDeclineInitiator = new Button("Delete");
	private Button btnAmend = new Button("Feedback");
	private HorizontalPanel hpnlButtonInitiator = new HorizontalPanel();
	private HorizontalPanel hpnlButtonsApprovar = new HorizontalPanel();
	private int strategicId;
	private int index;
	private String comment;
	
	public ConsolidationView() {
		
		submitted.addStyleName("pointer");
		submitted.setTitle("submitted");
	   
		submitted.setVisible(false);
		comments.setVisible(false);
		
		VerticalPanel verticalPanel = new VerticalPanel();
		initWidget(verticalPanel);
		verticalPanel.setSize("100%", "50%");
		
		HorizontalPanel horizontalPanel = new HorizontalPanel();
		verticalPanel.add(horizontalPanel);
		horizontalPanel.setSize("900px", "100%");
		
		area = new Label("");
		horizontalPanel.add(area);
		area.setWidth("240px");
//		area.setWordWrap(false);
		
		riskRating = new Image();
		horizontalPanel.add(riskRating);
		HorizontalPanel hpnlSpaceRating = new HorizontalPanel();
		horizontalPanel.add(hpnlSpaceRating);
		hpnlSpaceRating.setWidth("50px");
		
		auditableUnit = new TextField();
		
		horizontalPanel.add(auditableUnit);
		auditableUnit.setWidth("185px");
		
	
		
		//listboox  process added in view panel
		listBoxProcess = new ListBox();
		horizontalPanel.add(listBoxProcess);
		listBoxProcess.setWidth("160px");
		
		//listbox subricess added in panel
		listBoxSubProcess = new ListBox();
		horizontalPanel.add(listBoxSubProcess);
		listBoxSubProcess.setWidth("180px");
		
		//listbox jobtype added in panel
				listBoxJobType = new ListBox();
				horizontalPanel.add(listBoxJobType);
				listBoxJobType.setWidth("180px");
				
				tab = new Label();
				horizontalPanel.add(tab);
				tab.setWidth("50px");
		
		verticalPanel.add(hpnlButtonInitiator);
		verticalPanel.add(hpnlButtonsApprovar);
		    HorizontalPanel hpnlSpace = new HorizontalPanel();
		    HorizontalPanel hpnlSpaceApprovar = new HorizontalPanel();
		    
			hpnlButtonsApprovar.add(hpnlSpaceApprovar);
			hpnlButtonsApprovar.add(btnDecline);
			hpnlButtonsApprovar.add(btnAmend);
			hpnlButtonsApprovar.add(btnApprove);
			hpnlButtonsApprovar.setSpacing(2);
			
			hpnlButtonsApprovar.setVisible(false);
			hpnlButtonInitiator.setVisible(false);
			
			btnDecline.setWidth("70px");
			btnAmend.setWidth("70px");
			btnApprove.setWidth("70px");
		    
			hpnlSpace.setWidth("600px");
			hpnlSpaceApprovar.setWidth("600px");
			hpnlButtonInitiator.add(hpnlSpace);
			 hpnlButtonInitiator.add(btnDeclineInitiator);
			 btnDeclineInitiator.setVisible(false);
		    hpnlButtonInitiator.add(btnSave);
		    hpnlButtonInitiator.add(btnSubmit);
		    btnSave.setWidth("70px");
		    btnSubmit.setWidth("70px");
		    btnDeclineInitiator.setWidth("70px");
		    hpnlButtonInitiator.setSpacing(2);
		    
		    HorizontalPanel hpnlComments = new HorizontalPanel();
		    hpnlComments.add(comments);
		    hpnlComments.add(submitted);
		    hpnlComments.setWidth("150px");
		    horizontalPanel.add(hpnlComments);
		    
		   verticalPanel.addStyleName("form-row");
		   
	}
	public Label getArea() {
		return area;
	}
	public void setArea(Label area) {
		this.area = area;
	}
	
	public TextField getAuditableUnit() {
		return auditableUnit;
	}
	public void setAuditableUnit(TextField auditableUnit) {
		this.auditableUnit = auditableUnit;
	}
	public Image getRiskRating() {
		return riskRating;
	}
	public void setRiskRating(Image riskRating) {
		this.riskRating = riskRating;
	}
	public Label getComments() {
		return comments;
	}
	public void setComments(Label comments) {
		this.comments = comments;
	}
	public Button getBtnSave() {
		return btnSave;
	}
	public void setBtnSave(Button btnSave) {
		this.btnSave = btnSave;
	}
	public Button getBtnSubmit() {
		return btnSubmit;
	}
	public void setBtnSubmit(Button btnSubmit) {
		this.btnSubmit = btnSubmit;
	}
	public Button getBtnApprove() {
		return btnApprove;
	}
	public void setBtnApprove(Button btnApprove) {
		this.btnApprove = btnApprove;
	}
	public Button getBtnDecline() {
		return btnDecline;
	}
	public void setBtnDecline(Button btnDecline) {
		this.btnDecline = btnDecline;
	}
	public Button getBtnDeclineInitiator() {
		return btnDeclineInitiator;
	}
	public void setBtnDeclineInitiator(Button btnDeclineInitiator) {
		this.btnDeclineInitiator = btnDeclineInitiator;
	}
	public Button getBtnAmend() {
		return btnAmend;
	}
	public void setBtnAmend(Button btnAmend) {
		this.btnAmend = btnAmend;
	}
	public HorizontalPanel getHpnlButtonInitiator() {
		return hpnlButtonInitiator;
	}
	public void setHpnlButtonInitiator(HorizontalPanel hpnlButtonInitiator) {
		this.hpnlButtonInitiator = hpnlButtonInitiator;
	}
	public HorizontalPanel getHpnlButtonsApprovar() {
		return hpnlButtonsApprovar;
	}
	public void setHpnlButtonsApprovar(HorizontalPanel hpnlButtonsApprovar) {
		this.hpnlButtonsApprovar = hpnlButtonsApprovar;
	}
	public int getStrategicId() {
		return strategicId;
	}
	public void setStrategicId(int strategicId) {
		this.strategicId = strategicId;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	
	public Label getTab() {
		return tab;
	}
	public void setTab(Label tab) {
		this.tab = tab;
	}
	public Image getSubmitted() {
		return submitted;
	}
	public void setSubmitted(Image submitted) {
		this.submitted = submitted;
	}
	public ListBox getListBoxProcess() {
		return listBoxProcess;
	}
	public void setListBoxProcess(ListBox listBoxProcess) {
		this.listBoxProcess = listBoxProcess;
	}
	public ListBox getListBoxSubProcess() {
		return listBoxSubProcess;
	}
	public void setListBoxSubProcess(ListBox listBoxSubProcess) {
		this.listBoxSubProcess = listBoxSubProcess;
	}
	public ListBox getListBoxJobType() {
		return listBoxJobType;
	}
	public void setListBoxJobType(ListBox listBoxJobType) {
		this.listBoxJobType = listBoxJobType;
	}
	

	
}
