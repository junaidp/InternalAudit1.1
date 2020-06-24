
package com.internalaudit.client.view.AuditEngagement;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.user.client.History;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.internalaudit.client.InternalAuditServiceAsync;
import com.internalaudit.client.view.DisplayAlert;
import com.internalaudit.client.view.LoadingPopup;
import com.internalaudit.shared.AuditEngagement;
import com.internalaudit.shared.TimeOutException;

public class AuditNotificationViewNewData {

	// private AuditNotificationView auditNotificationView;
	private InternalAuditServiceAsync rpcService;
	private AuditEngagement selectedAuditEngagement;
	private Logger logger = Logger.getLogger("DashBoardPresenter");
	private AuditNotificationViewNew auditNotificationViewNew;

	public AuditNotificationViewNewData() {

	}

	public void setData(AuditNotificationViewNew auditNotificationViewNew, InternalAuditServiceAsync rpcService,
			AuditEngagement selectedAuditEngagement) {

		this.auditNotificationViewNew = auditNotificationViewNew;
		this.rpcService = rpcService;
		this.selectedAuditEngagement = selectedAuditEngagement;
		// if (selectedAuditEngagement != null &&
		// !selectedAuditEngagement.getTo().equals("")) {
		if (selectedAuditEngagement.getEmailStatus() == 1) {
			auditNotificationViewNew.disableFields();
			auditNotificationViewNew.getFileUploader().getBtnUpload().setVisible(false);
		}
		if (!selectedAuditEngagement.getSubject().isEmpty()) {
			displaySavedNotification();
		}

		//added by moqeet to hide delete icon after submit
		if(selectedAuditEngagement.getEmailStatus() != 0)
			auditNotificationViewNew.getFileUploader().getDelete().setVisible(false);
	}

	public void displaySavedNotification() {
		auditNotificationViewNew.getTxtBoxForInfo().setText(selectedAuditEngagement.getCc());
		auditNotificationViewNew.getTxtBoxForAction().setText(selectedAuditEngagement.getTo());
		auditNotificationViewNew.getTxtAreaBody().setText(selectedAuditEngagement.getAuditNotification());
		// auditNotificationView.disableFields();
		auditNotificationViewNew.getTxtBoxReference().setText(selectedAuditEngagement.getReferenceNo());
		auditNotificationViewNew.getTxtBoxFrom().setText(selectedAuditEngagement.getFrom());
		auditNotificationViewNew.getTxtBoxSubject().setText(selectedAuditEngagement.getSubject());
		auditNotificationViewNew.getTxtBoxMemo().setText(selectedAuditEngagement.getProcess());
		auditNotificationViewNew.getDate().setValue(selectedAuditEngagement.getNotificationSentDate());
	}

	public void sendMessage(final Button btnSend, int status, final AsyncCallback<KickoffView> asyncCallback,
			final String filepath, final AsyncCallback<String> notificationCallBack) {

		final LoadingPopup loadingPopup = new LoadingPopup();
		loadingPopup.display();

		/*
		 * rpcService.saveAuditNotification(selectedAuditEngagement.
		 * getAuditEngId(), auditNotificationViewNew.getTxtAreaBody().getText(),
		 * auditNotificationViewNew.getTxtBoxForAction().getText(),
		 * auditNotificationViewNew.getTxtBoxForInfo().getText(),
		 * auditNotificationViewNew.getTxtBoxReference().getText(),
		 * auditNotificationViewNew.getTxtBoxFrom().getText(),
		 * auditNotificationViewNew.getTxtBoxSubject().getText(), filepath,
		 * auditNotificationViewNew.getTxtBoxMemo().getText(),
		 * auditNotificationViewNew.getDate().getTextBox().getText(), status,
		 * new AsyncCallback<String>() {
		 */ Date d = new Date();
		rpcService.saveAuditNotification(selectedAuditEngagement.getAuditEngId(),
				auditNotificationViewNew.getTxtAreaBody().getText(),
				auditNotificationViewNew.getTxtBoxForAction().getText(),
				auditNotificationViewNew.getTxtBoxForInfo().getText(), "ReferenceNo", "From",
				auditNotificationViewNew.getTxtBoxSubject().getText(), filepath, "memo", "date", status,
				new AsyncCallback<String>() {

					@Override
					public void onFailure(Throwable caught) {
						System.out.println("Audit Notification sending FAILED.In AuditNotificatioViewData");
						btnSend.setEnabled(true);
						loadingPopup.remove();
						logger.log(Level.INFO, "FAIL: saveAudi+tNotification .Inside Audit AuditAreaspresenter");
						if (caught instanceof TimeOutException) {
							History.newItem("login");
						} else {
							System.out.println("FAIL: saveAuditNotification .Inside AuditAreaspresenter");
							// UNDO 2018 Window.alert("FAIL:
							// saveAuditNotification, Please check all the
							// fields");// After FAIL ... write RPC Name NOT
							// Method Name..
							new DisplayAlert("Notification not Sent.");
						}

					}

					@Override
					public void onSuccess(String result) {
						// auditNotificationView.disableFields();
						new DisplayAlert("Notification Sent");
						// btnSend.setEnabled(true);
						loadingPopup.remove();

						notificationCallBack.onSuccess("notification saved");

					}
				});

	}

}
