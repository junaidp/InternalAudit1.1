package com.internalaudit.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.internalaudit.client.view.data.RiskAssesmentStrategicViewData;

public class RiskAssesmentStrategicView extends Composite {

	private static RiskAssesmentStrategicViewUiBinder uiBinder = GWT.create(RiskAssesmentStrategicViewUiBinder.class);

	interface RiskAssesmentStrategicViewUiBinder extends UiBinder<Widget, RiskAssesmentStrategicView> {

	}

	private String strategicObjective;
	@UiField
	ListBox rating;
	@UiField
	HorizontalPanel userOption;
	@UiField
	ListBox listBoxUserOption;
	@UiField
	VerticalPanel riskFactors;
	@UiField
	VerticalPanel vpnlComments;
	// @UiField Button save;
	// @UiField TextField comments;
	// @UiField Button amend;
	@UiField
	HorizontalPanel hpnlButtons;
	private Label comments = new Label(" Feedback ");
	private Image submitted = new Image(" images/tick.png ");
	private Label lblImg = new Label("  ");

	private ButtonRound btnSave = new ButtonRound("Save");
	private ButtonRound btnSubmit = new ButtonRound("Submit");
	private ButtonRound btnApprove = new ButtonRound("Approve");
	private ButtonRound btnDecline = new ButtonRound("Delete");
	private ButtonRound btnDeclineInitiator = new ButtonRound("Delete");
	private ButtonRound btnAmend = new ButtonRound("Feedback");
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
		// comments.setEmptyText("Enter Comments");
		// amend.setHeight("25px");
		// comments.setHeight("100px");
		submitted.addStyleName("pointer");
		submitted.setTitle("submitted");
		submitted.setVisible(true);

		lblImg.setVisible(true);
		lblImg.setWidth("12px");
		hpnlButtons.add(hpnlButtonInitiator);
		hpnlButtons.add(hpnlButtonsApprovar);
		HorizontalPanel hpnlSpace = new HorizontalPanel();
		HorizontalPanel hpnlSpaceApprovar = new HorizontalPanel();

		hpnlButtonsApprovar.add(hpnlSpaceApprovar);
		hpnlButtonsApprovar.add(btnAmend);
		hpnlButtonsApprovar.add(btnApprove);
		hpnlButtonsApprovar.setSpacing(2);

		hpnlButtonsApprovar.setVisible(false);
		hpnlButtonInitiator.setVisible(false);
		//
		// btnDecline.setWidth("70px");
		// btnAmend.setWidth("70px");
		// btnApprove.setWidth("70px");

		hpnlSpace.setWidth("600px");
		hpnlSpaceApprovar.setWidth("600px");
		hpnlButtonInitiator.add(hpnlSpace);
		hpnlButtonInitiator.add(btnDeclineInitiator);
		btnDeclineInitiator.setVisible(false);
		hpnlButtonInitiator.add(btnSave);
		hpnlButtonInitiator.add(btnSubmit);
		// btnSave.setWidth("70px");
		// btnSubmit.setWidth("70px");
		// btnDeclineInitiator.setWidth("70px");
		hpnlButtonInitiator.setSpacing(2);
		HorizontalPanel hpnlComments = new HorizontalPanel();

		hpnlComments.add(comments);
		// hpnlComments.add(submitted); change here
		vpnlComments.add(hpnlComments);
		hpnlComments.setSpacing(5);

		// vpnlComments.add(comments);
		// hpnlStrategic.setWidth("900px");

	}

	public RiskAssesmentStrategicViewData getRiskAssesmentStrategicViewData() {
		return riskAssesmentStrategicViewData;
	}

	public void setRiskAssesmentStrategicViewData(RiskAssesmentStrategicViewData riskAssesmentStrategicViewData) {
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

	// public Button getSave() {
	// return save;
	// }
	//
	// public void setSave(Button save) {
	// this.save = save;
	// }

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public ButtonRound getBtnSave() {
		return btnSave;
	}

	public void setBtnSave(ButtonRound btnSave) {
		this.btnSave = btnSave;
	}

	public ButtonRound getBtnSubmit() {
		return btnSubmit;
	}

	public void setBtnSubmit(ButtonRound btnSubmit) {
		this.btnSubmit = btnSubmit;
	}

	public ButtonRound getBtnApprove() {
		return btnApprove;
	}

	public void setBtnApprove(ButtonRound btnApprove) {
		this.btnApprove = btnApprove;
	}

	public ButtonRound getBtnDecline() {
		return btnDecline;
	}

	public void setBtnDecline(ButtonRound btnDecline) {
		this.btnDecline = btnDecline;
	}

	public ButtonRound getBtnDeclineInitiator() {
		return btnDeclineInitiator;
	}

	public void setBtnDeclineInitiator(ButtonRound btnDeclineInitiator) {
		this.btnDeclineInitiator = btnDeclineInitiator;
	}

	public ButtonRound getBtnAmend() {
		return btnAmend;
	}

	public void setBtnAmend(ButtonRound btnAmend) {
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

	public ListBox getListBoxUserOption() {
		return listBoxUserOption;
	}

	public void setListBoxUserOption(ListBox listBoxUserOption) {
		this.listBoxUserOption = listBoxUserOption;
	}

	public Label getLblImg() {
		return lblImg;
	}

	public void setLblImg(Label lblImg) {
		this.lblImg = lblImg;
	}

	// public TextField getComments() {
	// return comments;
	// }
	//
	// public void setComments(TextField comments) {
	// this.comments = comments;
	// }
	//
	// public Button getAmend() {
	// return amend;
	// }
	//
	// public void setAmend(Button amend) {
	// this.amend = amend;
	// }

}
