package com.internalaudit.client.widgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.internalaudit.client.InternalAuditService;
import com.internalaudit.client.InternalAuditServiceAsync;
import com.internalaudit.client.upload.AuditWorkProgramUpload;
import com.internalaudit.client.view.AuditEngagement.AuditStepUploads;

public class ExceptionRow extends Composite {
	InternalAuditServiceAsync rpcService = GWT.create(InternalAuditService.class);
	private Label exId;
	HorizontalPanel panelFileDetail = new HorizontalPanel();
	private TextBox exception;
	// private FileUpload fileUploadException;
	private HorizontalPanel hpnl;
	private Image removeRow;
	private int exceptionId;
	AuditStepUploads auditStepUpload = null;
	private AuditWorkProgramUpload upload;

	public ExceptionRow() {
		hpnl = new HorizontalPanel();

		initWidget(hpnl);

		exId = new Label("0");
		layout();

	}

	public ExceptionRow(int exceptionId) {
		hpnl = new HorizontalPanel();
		this.exceptionId = exceptionId;
		initWidget(hpnl);

		exId = new Label(exceptionId + "");
		layout();

	}

	private void layout() {
		exId.addStyleName("hidden");

		exception = new TextBox();
		// fileUploadException = new FileUpload();
		removeRow = new Image("images/deleteIcon.png");
		exception.addStyleName("txtExtendedWidth");

		hpnl.add(exId);
		hpnl.add(exception);

		hpnl.add(removeRow);
		if (exceptionId != 0) {
			String mainFolder = "AuditExceptions";
			upload = new AuditWorkProgramUpload(exceptionId + "", mainFolder);
			// auditStepUpload = new AuditStepUploads(exId.getText());
			hpnl.add(upload);
			// hpnl.add(panelFileDetail);
		}

		exId.setVisible(false);

	}

	public Label getExId() {
		return exId;
	}

	public void setExId(Label exId) {
		this.exId = exId;
	}

	public TextBox getException() {
		return exception;
	}

	public void setException(TextBox exception) {
		this.exception = exception;
	}

	public Image getRemoveRow() {
		return removeRow;
	}

	public void setRemoveRow(Image removeRow) {
		this.removeRow = removeRow;
	}

	public void removeRow() {
		exception.removeFromParent();
		exId.removeFromParent();
		removeRow.removeFromParent();

	}

	public void disableFields() {
		exception.setEnabled(false);
		removeRow.setVisible(false);
		upload.getUploadPanel().setVisible(false);

		// auditStepUpload.getUpload().setVisible(false);
		// auditStepUpload.getBtnSubmit().setVisible(false);
	}

	public AuditStepUploads getAuditStepUpload() {
		return auditStepUpload;
	}

	public void setAuditStepUpload(AuditStepUploads auditStepUpload) {
		this.auditStepUpload = auditStepUpload;
	}

}
