package com.internalaudit.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.internalaudit.client.InternalAuditService;
import com.internalaudit.client.InternalAuditServiceAsync;
//import com.google.gwt.user.client.ui.TextBox;
import com.sencha.gxt.widget.core.client.form.TextField;

public class PrioritizationView extends Composite{
	private Label area;
	private Image riskRating;
	private Label tab;
	private Label objective;
	private CheckBox audit= new CheckBox()  ;
//	private CheckBox nextYear = new CheckBox()  ;
	private Label comments = new Label(" Feedback ");
	private Image submitted = new Image(" images/tick.png ");
	private ListBox listYears = new ListBox();
	private ButtonRound btnSave = new ButtonRound("Save");
	private ButtonRound btnSubmit = new ButtonRound("Submit");
	private ButtonRound btnApprove = new ButtonRound("Approve");
	private ButtonRound btnDecline = new ButtonRound("Delete");
	private ButtonRound btnDeclineInitiator = new ButtonRound("Delete");
	private ButtonRound btnAmend = new ButtonRound("Feedback");
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

		area = new Label("");
		horizontalPanel.add(area);
		area.setWidth("240px");
//		area.setWordWrap(false);

		objective = new Label();
		horizontalPanel.add(objective);
		objective.setWidth("180px");

		riskRating = new Image();
		horizontalPanel.add(riskRating);
		HorizontalPanel hpnlSpaceRating = new HorizontalPanel();
		hpnlSpaceRating.add(riskRating);
		horizontalPanel.add(hpnlSpaceRating);
		hpnlSpaceRating.setWidth("120px");

		HorizontalPanel hpn1spaceAudit = new HorizontalPanel();
		hpn1spaceAudit.setWidth("90px");
		hpn1spaceAudit.add(audit);
		horizontalPanel.add(hpn1spaceAudit);
		//audit.setWidth("190px");
		
		//listYears.getElement().getStyle().setMarginLeft(50, Unit.PX);
		

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
		hpnlComments.add(comments);
		hpnlComments.add(submitted);
		hpnlComments.setWidth("0px");
		
		horizontalPanel.add(listYears);
		horizontalPanel.add(hpnlComments);

		verticalPanel.addStyleName("form-row");
		
		
		////
//		listYears.addItem("2014");
//		listYears.addItem("2015");
//		listYears.addItem("2016");
//		listYears.addItem("2017");
//		listYears.addItem("2018");
//		listYears.addItem("2019");
	
		tab = new Label();
		horizontalPanel.add(tab);
		tab.setWidth("70px");
		fetchCurrentYear(horizontalPanel);
		
		

	}
	private void fetchCurrentYear(final HorizontalPanel horizontalPanel) {
		InternalAuditServiceAsync rpcService = GWT.create(InternalAuditService.class);
		 
		rpcService.fetchCurrentYear(new AsyncCallback<Integer>(){

			@Override
			public void onFailure(Throwable caught) {
				System.out.println("fail :fetchCurrentYear");
			}

			@Override
			public void onSuccess(Integer currentYear) {
				listYears.addItem(currentYear+"");
				listYears.addItem(currentYear+1+"");
				listYears.addItem(currentYear+2+"");
				listYears.addItem(currentYear+3+"");
				listYears.addItem(currentYear+4+"");
				
			}});
			
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
	public Label getComments() {
		return comments;
	}
	public void setComments(Label comments) {
		this.comments = comments;
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
