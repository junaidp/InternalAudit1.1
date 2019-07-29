package com.internalaudit.client.view.AuditEngagement;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.TextDecoration;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.internalaudit.client.InternalAuditService;
import com.internalaudit.client.InternalAuditServiceAsync;
import com.internalaudit.client.upload.AuditWorkProgramUpload;
import com.internalaudit.shared.AuditEngagement;

public class AuditNotificationViewNew extends Composite {
	AuditWorkProgramUpload uploada;
	private static AuditNotificationViewNewUiBinder uiBinder = GWT.create(AuditNotificationViewNewUiBinder.class);

	interface AuditNotificationViewNewUiBinder extends UiBinder<Widget, AuditNotificationViewNew> {
	}

	InternalAuditServiceAsync rpcService = GWT.create(InternalAuditService.class);
	@UiField
	TextBox txtBoxReference;
	@UiField
	TextBox txtBoxFrom;
	@UiField
	TextBox txtBoxForAction;
	@UiField
	TextBox txtBoxForInfo;
	@UiField
	TextBox txtBoxSubject;
	@UiField
	Button btnSend;
	@UiField
	VerticalPanel panelUpload;
	@UiField
	TextArea txtAreaBody;
	String filepath;
	Anchor lblfilename;
	private AuditNotificationViewNewData auditNotificationViewNewData = new AuditNotificationViewNewData();

	public AuditNotificationViewNew(AuditEngagement record) {
		initWidget(uiBinder.createAndBindUi(this));
		HTML html = new HTML();
		setHandlers();
		txtAreaBody.setText(
				"We hereby would like to inform you thot company Name Internal Audit will be conducting o review of Engagement Name from 14 December, 2017. This is part of the Internal Audit Plan for 2017, discussed with Top Management, Group Internal Audit, and approved by member(s) of BOD/Audit Committee\nThe audit will be performed by Mr. DEF ond M.r GHI , members of Local Internal Audit.\n The scope of the audit includes review of adequacy, effectiveness & efficiency of the controls ond processes around Engagement Name, with specific focus on xxxx. \n Please note thot IA team may take contact with relevant members of your team in the coming doys for a better process understanding relating to this audit and information required. Any resultant scope changes, if required, will be duly communicated. \n The completion ond finalisation will depend on the availability of information and interviews, yet we oim ot delivering a draft report for your review and comments by 8 FebruorY, 2018. \n You are requested to forward the information in this notification to those who will be involved in the oudit. If you hove any questions concerning the audit, please feel free to contact myself or e-mail ot xxxxx. \n We look forward to your continued support. ");

		final String notificationId = record.getAuditEngId() + "";
		final String mainFolder = "NotificationUploads";

		rpcService.fetchAuditStepsProcerdure(notificationId, mainFolder, new AsyncCallback<ArrayList<String>>() {

			@Override
			public void onSuccess(ArrayList<String> result) {

				for (int i = 0; i < result.size(); i++) {
					lblfilename = new Anchor(result.get(i));

					lblfilename.addStyleName("pointerStyle");
					lblfilename.getElement().getStyle().setTextDecoration(TextDecoration.NONE);
					lblfilename.setHeight("25px");

					lblfilename.setWordWrap(false);
					String upperCasedJobLink = lblfilename.getText();
					lblfilename.setText(upperCasedJobLink);

				}
				filepath = mainFolder + "/" + notificationId + "/" + lblfilename.getText();

			}

			@Override
			public void onFailure(Throwable caught) {

				System.out.println("fetchAuditProcedure Failed");
			}

		});

		uploada = new AuditWorkProgramUpload(notificationId, mainFolder);
		// Window.alert(uploada.getFile());
		panelUpload.add(uploada);

	}

	private void setHandlers() {
		btnSend.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				auditNotificationViewNewData.sendMessage(btnSend);
				disableFields();
			}
		});
	}

	public void disableFields() {
		txtAreaBody.setEnabled(false);
		txtBoxForAction.setEnabled(false);
		txtBoxForInfo.setEnabled(false);
		txtBoxFrom.setEnabled(false);
		txtBoxReference.setEnabled(false);
		txtBoxSubject.setEnabled(false);
		btnSend.setVisible(false);
		uploada.getBtnSubmit().setVisible(false);

	}

	public AuditNotificationViewNewData getAuditNotificationViewNewData() {
		return auditNotificationViewNewData;
	}

	public void setAuditNotificationViewNewData(AuditNotificationViewNewData auditNotificationViewNewData) {
		this.auditNotificationViewNewData = auditNotificationViewNewData;
	}

	public static AuditNotificationViewNewUiBinder getUiBinder() {
		return uiBinder;
	}

	public static void setUiBinder(AuditNotificationViewNewUiBinder uiBinder) {
		AuditNotificationViewNew.uiBinder = uiBinder;
	}

	public TextBox getTxtBoxReference() {
		return txtBoxReference;
	}

	public void setTxtBoxReference(TextBox txtBoxReference) {
		this.txtBoxReference = txtBoxReference;
	}

	public TextBox getTxtBoxFrom() {
		return txtBoxFrom;
	}

	public void setTxtBoxFrom(TextBox txtBoxFrom) {
		this.txtBoxFrom = txtBoxFrom;
	}

	public TextBox getTxtBoxForAction() {
		return txtBoxForAction;
	}

	public void setTxtBoxForAction(TextBox txtBoxForAction) {
		this.txtBoxForAction = txtBoxForAction;
	}

	public TextBox getTxtBoxForInfo() {
		return txtBoxForInfo;
	}

	public void setTxtBoxForInfo(TextBox txtBoxForInfo) {
		this.txtBoxForInfo = txtBoxForInfo;
	}

	public TextBox getTxtBoxSubject() {
		return txtBoxSubject;
	}

	public void setTxtBoxSubject(TextBox txtBoxSubject) {
		this.txtBoxSubject = txtBoxSubject;
	}

	public Button getBtnSend() {
		return btnSend;
	}

	public void setBtnSend(Button btnSend) {
		this.btnSend = btnSend;
	}

	public TextArea getTxtAreaBody() {
		return txtAreaBody;
	}

	public void setTxtAreaBody(TextArea txtAreaBody) {
		this.txtAreaBody = txtAreaBody;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
}
