package com.internalaudit.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.internalaudit.client.view.data.RiskAssesmentStrategicViewData;
import com.sencha.gxt.widget.core.client.form.TextField;

public class RiskAssesmentStrategicView extends Composite {

	private static RiskAssesmentStrategicViewUiBinder uiBinder = GWT
			.create(RiskAssesmentStrategicViewUiBinder.class);

	interface RiskAssesmentStrategicViewUiBinder extends
			UiBinder<Widget, RiskAssesmentStrategicView> {
	}
	
	private String strategicObjective;
	@UiField ListBox rating;
	@UiField VerticalPanel riskFactors;
	@UiField VerticalPanel vpnlComments;
//	@UiField Button save;
//	@UiField TextField comments;
//	@UiField Button amend;
	@UiField HorizontalPanel hpnlButtons;
	private Label comments = new Label(" Feedback ");
	private Image submitted = new Image(" images/tick.png ");
	private Button btnSave = new Button("Save");
	private Button btnSubmit = new Button("Submit");
	private Button btnApprove = new Button("Approve");
	private Button btnDecline = new Button("Delete");
	private Button btnDeclineInitiator = new Button("Delete");
	private Button btnAmend = new Button("Feedback");
	private HorizontalPanel hpnlButtonInitiator = new HorizontalPanel();
	private HorizontalPanel hpnlButtonsApprovar = new HorizontalPanel();
	private int index;
	private int strategicId;
	private String comment;
	
	
	public ListBox getRating() {
		return rating;
	}

	public void setRating(ListBox rating) {
		this.rating = rating;
	}

	private RiskAssesmentStrategicViewData riskAssesmentStrategicViewData = new RiskAssesmentStrategicViewData();
	public RiskAssesmentStrategicView() {
		initWidget(uiBinder.createAndBindUi(this));
		hpnlButtons.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
//		comments.setEmptyText("Enter Comments");
//		amend.setHeight("25px");
//		comments.setHeight("100px");
		submitted.addStyleName("pointer");
		submitted.setTitle("submitted");
	    submitted.setVisible(true);
		hpnlButtons.add(hpnlButtonInitiator);
		hpnlButtons.add(hpnlButtonsApprovar);
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
//		    hpnlComments.add(submitted);  change here
		    vpnlComments.add(hpnlComments);
		    hpnlComments.setSpacing(5);
		    
//		    vpnlComments.add(comments);
//		    hpnlStrategic.setWidth("900px");
		
		
	}
	
	public RiskAssesmentStrategicViewData getRiskAssesmentStrategicViewData() {
		return riskAssesmentStrategicViewData;
	}

	public void setRiskAssesmentStrategicViewData(
			RiskAssesmentStrategicViewData riskAssesmentStrategicViewData) {
		this.riskAssesmentStrategicViewData = riskAssesmentStrategicViewData;
	}

	public String getStrategicObjective() {
		return strategicObjective;
	}

	public void setStrategicObjective(String strategicObjective) {
		this.strategicObjective = strategicObjective;
	}

	public VerticalPanel getRiskFactors() {
		return riskFactors;
	}

	public void setRiskFactors(VerticalPanel riskFactors) {
		this.riskFactors = riskFactors;
	}

//	public Button getSave() {
//		return save;
//	}
//
//	public void setSave(Button save) {
//		this.save = save;
//	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
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

	public Label getComments() {
		return comments;
	}

	public void setComments(Label comments) {
		this.comments = comments;
	}

	public Image getSubmitted() {
		return submitted;
	}

	public void setSubmitted(Image submitted) {
		this.submitted = submitted;
	}

	

//	public TextField getComments() {
//		return comments;
//	}
//
//	public void setComments(TextField comments) {
//		this.comments = comments;
//	}
//
//	public Button getAmend() {
//		return amend;
//	}
//
//	public void setAmend(Button amend) {
//		this.amend = amend;
//	}

	
}
