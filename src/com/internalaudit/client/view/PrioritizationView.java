package com.internalaudit.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.internalaudit.client.InternalAuditService;
import com.internalaudit.client.InternalAuditServiceAsync;

public class PrioritizationView extends Composite {
	private Label area;
	private Image riskRating;
	private Label tab;
	private Label objective;
	private CheckBox audit = new CheckBox();
	// private CheckBox nextYear = new CheckBox() ;
	private Label feedback = new Label(" Feedback ");
	private Image submitted = new Image(" images/tick.png ");
	private ListBox listYears = new ListBox();
	private Button btnSave = new Button("Save");
	private Button btnSubmit = new Button("Submit");
	private Button btnApprove = new Button("Approve");
	private Button btnDecline = new Button("Delete");
	private Button btnDeclineInitiator = new Button("Delete");
	private Button btnFeedback = new Button("Feedback");
	private HorizontalPanel hpnlButtonInitiator = new HorizontalPanel();
	private HorizontalPanel hpnlButtonsApprovar = new HorizontalPanel();
	private int strategicId;
	private int index;
	private String comment;

	public PrioritizationView() {

		submitted.addStyleName("pointer");
		submitted.setTitle("submitted");
		submitted.setVisible(false);

		VerticalPanel verticalPanel = new VerticalPanel();
		initWidget(verticalPanel);
		verticalPanel.setSize("100%", "50%");

		HorizontalPanel horizontalPanel = new HorizontalPanel();
		verticalPanel.add(horizontalPanel);
		horizontalPanel.setSize("1000px", "6px");

		area = new Label("  ");
		HorizontalPanel hpnlSPaceArea = new HorizontalPanel();
		hpnlSPaceArea.setWidth("10px");
		horizontalPanel.add(hpnlSPaceArea);
		horizontalPanel.add(area);
		area.setWidth("210px");

		// area.setWordWrap(false);

		objective = new Label();
		horizontalPanel.add(objective);
		objective.setWidth("175px");

		riskRating = new Image();
		horizontalPanel.add(riskRating);
		HorizontalPanel hpnlSpaceRating = new HorizontalPanel();
		hpnlSpaceRating.add(riskRating);
		horizontalPanel.add(hpnlSpaceRating);
		hpnlSpaceRating.setWidth("150px");

		HorizontalPanel hpn1spaceAudit = new HorizontalPanel();
		hpn1spaceAudit.setWidth("90px");
		hpn1spaceAudit.add(audit);
		horizontalPanel.add(hpn1spaceAudit);
		// audit.setWidth("190px");

		// listYears.getElement().getStyle().setMarginLeft(50, Unit.PX);

		verticalPanel.add(hpnlButtonInitiator);
		verticalPanel.add(hpnlButtonsApprovar);
		HorizontalPanel hpnlSpace = new HorizontalPanel();
		HorizontalPanel hpnlSpaceApprovar = new HorizontalPanel();

		hpnlButtonsApprovar.add(hpnlSpaceApprovar);
		hpnlButtonsApprovar.add(btnDecline);
		hpnlButtonsApprovar.add(btnFeedback);
		hpnlButtonsApprovar.add(btnApprove);
		hpnlButtonsApprovar.setSpacing(2);

		hpnlButtonsApprovar.setVisible(false);
		hpnlButtonInitiator.setVisible(false);

		btnDecline.setWidth("70px");
		btnFeedback.setWidth("70px");
		btnApprove.setWidth("70px");

		hpnlSpace.setWidth("650px");
		hpnlSpaceApprovar.setWidth("650px");
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
		hpnlComments.add(feedback);
		hpnlComments.add(submitted);
		hpnlComments.setWidth("0px");

		horizontalPanel.add(listYears);
		horizontalPanel.add(hpnlComments);

		verticalPanel.addStyleName("form-row");

		////
		// listYears.addItem("2014");
		// listYears.addItem("2015");
		// listYears.addItem("2016");
		// listYears.addItem("2017");
		// listYears.addItem("2018");
		// listYears.addItem("2019");

		tab = new Label();
		horizontalPanel.add(tab);
		tab.setWidth("70px");
		fetchCurrentYear(horizontalPanel);

	}

	private void fetchCurrentYear(final HorizontalPanel horizontalPanel) {
		InternalAuditServiceAsync rpcService = GWT.create(InternalAuditService.class);

		rpcService.fetchCurrentYear(new AsyncCallback<Integer>() {

			@Override
			public void onFailure(Throwable caught) {
				System.out.println("fail :fetchCurrentYear");
			}

			@Override
			public void onSuccess(Integer currentYear) {
				listYears.addItem(currentYear + "");
				listYears.addItem(currentYear + 1 + "");
				listYears.addItem(currentYear + 2 + "");
				listYears.addItem(currentYear + 3 + "");
				listYears.addItem(currentYear + 4 + "");

			}
		});

	}

	public Label getArea() {
		return area;
	}

	public void setArea(Label area) {
		this.area = area;
	}

	public CheckBox getAudit() {
		return audit;
	}

	public void setAudit(CheckBox priority) {
		this.audit = priority;
	}

	public Label getFeedback() {
		return feedback;
	}

	public void setFeedback(Label feedback) {
		this.feedback = feedback;
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

	public Button getBtnFeedback() {
		return btnFeedback;
	}

	public void setBtnFeedback(Button btnFeedback) {
		this.btnFeedback = btnFeedback;
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

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Image getRiskRating() {
		return riskRating;
	}

	public void setRiskRating(Image riskRating) {
		this.riskRating = riskRating;
	}

	public Label getTab() {
		return tab;
	}

	public void setTab(Label tab) {
		this.tab = tab;
	}

	public Label getObjective() {
		return objective;
	}

	public void setObjective(Label objective) {
		this.objective = objective;
	}

	public Image getSubmitted() {
		return submitted;
	}

	public void setSubmitted(Image submitted) {
		this.submitted = submitted;
	}

	public ListBox getListYears() {
		return listYears;
	}

	public void setListYears(ListBox listYears) {
		this.listYears = listYears;
	}

}
