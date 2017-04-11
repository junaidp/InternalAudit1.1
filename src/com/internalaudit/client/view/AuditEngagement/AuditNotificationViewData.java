package com.internalaudit.client.view.AuditEngagement;

import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.internalaudit.client.InternalAuditServiceAsync;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.internalaudit.client.view.DisplayAlert;
import com.internalaudit.client.view.LoadingPopup;
import com.internalaudit.shared.AuditEngagement;
import com.internalaudit.shared.TimeOutException;

public class AuditNotificationViewData {

	private AuditNotificationView auditNotificationView;
	private InternalAuditServiceAsync rpcService;
	private AuditEngagement selectedAuditEngagement;
	private Logger logger = Logger.getLogger("DashBoardPresenter");
	
	public AuditNotificationViewData(){

	}

	public void setData(AuditNotificationView auditNotificationView,
			InternalAuditServiceAsync rpcService, AuditEngagement selectedAuditEngagement) {

		this.auditNotificationView = auditNotificationView;
		this.rpcService =  rpcService;
		this.selectedAuditEngagement = selectedAuditEngagement;
		if(selectedAuditEngagement!=null && !selectedAuditEngagement.getTo().equals("")){
		displaySavedNotification();
		}

	}


	private void displaySavedNotification() {
		auditNotificationView.getCc().setText(selectedAuditEngagement.getCc());
		auditNotificationView.getTo().setText(selectedAuditEngagement.getTo());
		auditNotificationView.getMessage().setText(selectedAuditEngagement.getAuditNotification());
		auditNotificationView.disableFields();
		
	}

	public void sendMessage(final Button send) {
		
		final LoadingPopup loadingPopup = new LoadingPopup();
		loadingPopup.display();
		
		rpcService.saveAuditNotification(selectedAuditEngagement.getAuditEngId(), auditNotificationView.getMessage().getText(), auditNotificationView.getTo().getText(), auditNotificationView.getCc().getText(), new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable caught) {
				System.out.println("Audit Notification sending FAILED.In AuditNotificatioViewData");
				send.setEnabled(true);
				loadingPopup.remove();
				logger.log(Level.INFO, "FAIL: saveAuditNotification .Inside Audit AuditAreaspresenter");
				if(caught instanceof TimeOutException){
					History.newItem("login");
				}else{
					System.out.println("FAIL: saveAuditNotification .Inside AuditAreaspresenter");
					Window.alert("FAIL: saveAuditNotification, Please check all the fields");// After FAIL ... write RPC Name  NOT Method Name..
				}
				
				
			}

			@Override
			public void onSuccess(String result) {
				auditNotificationView.disableFields();
				new DisplayAlert("Notification Sent");
				send.setEnabled(true);
				loadingPopup.remove();
			}					
		});

	}

}
