package com.internalaudit.client.view;

import org.apache.tools.ant.util.StringUtils;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.internalaudit.client.InternalAuditService;
import com.internalaudit.client.InternalAuditServiceAsync;
import com.internalaudit.client.upload.AuditWorkProgramUpload;
import com.internalaudit.client.view.AuditEngagement.LabelBold;
import com.internalaudit.server.AuditWorkProgramUploadServlet;
import com.internalaudit.shared.Employee;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;

public class CompanyLogo extends VerticalLayoutContainer {
	
	private InternalAuditServiceAsync rpcService;
	private int companyID;
	
	public CompanyLogo(int companyID) {
		rpcService = GWT.create(InternalAuditService.class);
		this.companyID = companyID;
		add(tabLaout());
	}
	
	private Widget tabLaout() {
		VerticalPanel vpnlMain = new VerticalPanel();
		LabelBold lblCompanyLogo = new LabelBold("Add/Update Company Logo");
		vpnlMain.add(lblCompanyLogo);
		AuditWorkProgramUpload fileLogoUpload = new AuditWorkProgramUpload("", "logo");
		handler(fileLogoUpload);
		vpnlMain.add(fileLogoUpload);
		
		return vpnlMain;
	}

	private void handler(final AuditWorkProgramUpload fileLogoUpload) {
//		fileLogoUpload.getForm().submit();
		fileLogoUpload.getBtnUpload().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent arg0) {
				rpcService.uploadCompanyLogo(extractFileName(fileLogoUpload.getUpload().getFilename()), companyID, new AsyncCallback<String>() {

					@Override
					public void onFailure(Throwable msg) {
						Window.alert("Unable to Upload Company Logo" + msg);
					}

					@Override
					public void onSuccess(String msg) {
						new DisplayAlert(msg);
					}
				});
			}
		});
	}
	
	private String extractFileName(String fileName) {
		fileName = fileName.substring(fileName.indexOf("fakepath"));
		fileName = fileName.substring(9);
		fileName = "images/logo/" + fileName;
		return fileName;
	}
	
}
