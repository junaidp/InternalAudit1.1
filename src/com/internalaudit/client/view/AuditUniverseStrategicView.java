package com.internalaudit.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.internalaudit.client.view.data.AuditUniverseStrategicViewData;
import com.sencha.gxt.widget.core.client.button.IconButton;
import com.sencha.gxt.widget.core.client.form.DateField;
import com.sencha.gxt.widget.core.client.form.TextField;

public class AuditUniverseStrategicView extends Composite {

    private static AuditUniverseStrategicViewUiBinder uiBinder = GWT.create(AuditUniverseStrategicViewUiBinder.class);

    interface AuditUniverseStrategicViewUiBinder extends UiBinder<Widget, AuditUniverseStrategicView> {
    }

    @UiField
    VerticalPanel mainPanel;
    IconButton btnAdd = new IconButton("+..");
    private AuditUniverseStrategicViewData auditUniverseStrategicViewData = new AuditUniverseStrategicViewData();
    private ListBox lstObjectiveOwner = new ListBox();
    private ListBox relevantDepartment = new ListBox();
    private DateField objectiveAchievementDate = new DateField();
    private TextField strategicObjective = new TextField();
    private Label comments = new Label(" Feedback ");
    private Image submitted = new Image(" images/tick.png ");

    private int strategicId;
    private ButtonRound btnSave = new ButtonRound("Save");
    private ButtonRound btnSubmit = new ButtonRound("Submit");
    private ButtonRound btnApprove = new ButtonRound("Approve");
    private ButtonRound btnDecline = new ButtonRound("Delete");
    private ButtonRound btnDeclineInitiator = new ButtonRound("Delete");
    private ButtonRound btnAmend = new ButtonRound("Feedback");
    private HorizontalPanel hpnlButtonInitiator = new HorizontalPanel();
    private HorizontalPanel hpnlButtonsApprovar = new HorizontalPanel();
    private String comment;
    private Label lblStrategicId = new Label();
    private ScrollPanel relevantDepartmentPanel = new ScrollPanel();
    private VerticalPanel vpnlRelevantDepartmentPanel = new VerticalPanel();

    public AuditUniverseStrategicView() {
	initWidget(uiBinder.createAndBindUi(this));
	relevantDepartmentPanel.setVisible(false);
	bind();
	submitted.setVisible(false);
	comments.setVisible(false);
    }

    private void bind() {
	mainPanelLayout();
    }

    private void mainPanelLayout() {
	strategicObjective.setEmptyText("Enter Objective");
	// lstObjectiveOwner.setWidth("150px");
	relevantDepartment.setWidth("150px");
	strategicObjective.setWidth("790px");
	VerticalPanel vpnlStrategicId = new VerticalPanel();
	VerticalPanel vpnlStrategicObjective = new VerticalPanel();
	vpnlStrategicObjective.setWidth("920px");
	VerticalPanel vpnlLstObjectiveOwner = new VerticalPanel();
	VerticalPanel vpnlRelevantDepartment = new VerticalPanel();
	VerticalPanel vpnlObjectiveAchievementDate = new VerticalPanel();
	Label lblStrategicObjective = new Label(AuditConstants.STRATEGICOBJECTIVE);
	Label lblOwner = new Label(AuditConstants.OBJECTIVEOWNER);
	Label lblDepartment = new Label(AuditConstants.OBJECTIVEDEPT);
	Label lblDate = new Label(AuditConstants.OBJECTIVEDATE);

	lblStrategicObjective.setWidth("600px");
	lblStrategicObjective.addStyleName("navybluebold");
	lblOwner.addStyleName("blue");
	lblDepartment.addStyleName("blue");
	lblDate.addStyleName("blue");
	submitted.addStyleName("pointer");
	submitted.setTitle("submitted");

	// vpnlStrategicObjective.add(lblStrategicObjective);
	// vpnlLstObjectiveOwner.add(lblOwner);
	// vpnlRelevantDepartment.add(lblDepartment);
	// vpnlObjectiveAchievementDate.add(lblDate);

	vpnlStrategicObjective.add(strategicObjective);
	vpnlLstObjectiveOwner.add(lstObjectiveOwner);
	vpnlStrategicId.add(lblStrategicId);
	relevantDepartment.setMultipleSelect(true);
	vpnlRelevantDepartment.add(relevantDepartment);
	vpnlRelevantDepartment.add(relevantDepartmentPanel);
	relevantDepartmentPanel.add(vpnlRelevantDepartmentPanel);
	relevantDepartmentPanel.setHeight("30px");
	vpnlRelevantDepartment.setSpacing(1);
	vpnlObjectiveAchievementDate.add(objectiveAchievementDate);

	HorizontalPanel hpnlStrategic = new HorizontalPanel();
	// hpnlStrategic.add(strategicObjective);
	// hpnlStrategic.add(lstObjectiveOwner);
	// hpnlStrategic.add(relevantDepartment);
	// hpnlStrategic.add(objectiveAchievementDate);
	lblStrategicId.addStyleName("blue");
	lblStrategicId.setWidth("45px");
	hpnlStrategic.add(vpnlStrategicId);
	hpnlStrategic.add(vpnlStrategicObjective);
	// hpnlStrategic.setSpacing(3);
	// hpnlStrategic.add(vpnlLstObjectiveOwner);
	hpnlStrategic.add(vpnlRelevantDepartment);
	// hpnlStrategic.add(vpnlObjectiveAchievementDate);
	HorizontalPanel hpnlComments = new HorizontalPanel();

	hpnlComments.setWidth("100px");
	hpnlComments.add(comments);
	hpnlComments.add(submitted);
	hpnlStrategic.add(hpnlComments);
	// hpnlStrategic.add(comments);
	// hpnlStrategic.add(submitted);
	// comments.addStyleName("white");
	mainPanel.add(hpnlStrategic);
	mainPanel.add(hpnlButtonInitiator);
	mainPanel.add(hpnlButtonsApprovar);
	HorizontalPanel hpnlSpace = new HorizontalPanel();
	HorizontalPanel hpnlSpaceApprovar = new HorizontalPanel();

	hpnlButtonsApprovar.add(hpnlSpaceApprovar);
	hpnlButtonsApprovar.add(btnDecline);
	hpnlButtonsApprovar.add(btnAmend);
	hpnlButtonsApprovar.add(btnApprove);
	hpnlButtonsApprovar.setSpacing(2);

	hpnlButtonsApprovar.setVisible(false);
	hpnlButtonInitiator.setVisible(false);

//	btnDecline.setWidth("70px");
//	btnAmend.setWidth("70px");
//	btnApprove.setWidth("70px");

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
	hpnlStrategic.setWidth("900px");

	mainPanel.addStyleName("form-row");

    }

    public IconButton getBtnAdd() {
	return btnAdd;
    }

    public void setBtnAdd(IconButton btnAdd) {
	this.btnAdd = btnAdd;
    }

    public AuditUniverseStrategicViewData getAuditUniverseStrategicViewData() {
	return auditUniverseStrategicViewData;
    }

    public void setAuditUniverseStrategicViewData(AuditUniverseStrategicViewData auditUniverseStrategicViewData) {
	this.auditUniverseStrategicViewData = auditUniverseStrategicViewData;
    }

    public ListBox getLstObjectiveOwner() {
	return lstObjectiveOwner;
    }

    public void setLstObjectiveOwner(ListBox lstObjectiveOwner) {
	this.lstObjectiveOwner = lstObjectiveOwner;
    }

    public ListBox getRelevantDepartment() {
	return relevantDepartment;
    }

    public void setRelevantDepartment(ListBox relevantDepartment) {
	this.relevantDepartment = relevantDepartment;
    }

    public DateField getObjectiveAchievementDate() {
	return objectiveAchievementDate;
    }

    public void setObjectiveAchievementDate(DateField objectiveAchievementDate) {
	this.objectiveAchievementDate = objectiveAchievementDate;
    }

    public TextField getStrategicObjective() {
	return strategicObjective;
    }

    public void setStrategicObjective(TextField strategicObjective) {
	this.strategicObjective = strategicObjective;
    }

    public int getStrategicId() {
	return strategicId;
    }

    public void setStrategicId(int strategicId) {
	this.strategicId = strategicId;
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

    public HorizontalPanel getHpnlButtonInitiator() {
	return hpnlButtonInitiator;
    }

    public void setHpnlButtonInitiator(HorizontalPanel hpnlButtonInitiator) {
	this.hpnlButtonInitiator = hpnlButtonInitiator;
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

    public ButtonRound getBtnAmend() {
	return btnAmend;
    }

    public void setBtnAmend(ButtonRound btnAmend) {
	this.btnAmend = btnAmend;
    }

    public HorizontalPanel getHpnlButtonsApprovar() {
	return hpnlButtonsApprovar;
    }

    public void setHpnlButtonsApprovar(HorizontalPanel hpnlButtonsApprovar) {
	this.hpnlButtonsApprovar = hpnlButtonsApprovar;
    }

    public ButtonRound getBtnDeclineInitiator() {
	return btnDeclineInitiator;
    }

    public void setBtnDeclineInitiator(ButtonRound btnDeclineInitiator) {
	this.btnDeclineInitiator = btnDeclineInitiator;
    }

    public String getComment() {
	return comment;
    }

    public void setComment(String comment) {
	this.comment = comment;
    }

    public Label getLblStrategicId() {
	return lblStrategicId;
    }

    public void setLblStrategicId(Label lblStrategicId) {
	this.lblStrategicId = lblStrategicId;
    }

    public VerticalPanel getVpnlRelevantDepartmentPanel() {
	return vpnlRelevantDepartmentPanel;
    }

    public void setVpnlRelevantDepartmentPanel(VerticalPanel vpnlRelevantDepartmentPanel) {
	this.vpnlRelevantDepartmentPanel = vpnlRelevantDepartmentPanel;
    }

    public ScrollPanel getRelevantDepartmentPanel() {
	return relevantDepartmentPanel;
    }

    public void setRelevantDepartmentPanel(ScrollPanel relevantDepartmentPanel) {
	this.relevantDepartmentPanel = relevantDepartmentPanel;
    }

    public Image getSubmitted() {
	return submitted;
    }

    public void setSubmitted(Image submitted) {
	this.submitted = submitted;
    }

}
