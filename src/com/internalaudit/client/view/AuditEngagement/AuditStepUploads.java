package com.internalaudit.client.view.AuditEngagement;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent;
import com.google.gwt.user.client.ui.FormPanel.SubmitEvent;
import com.internalaudit.client.InternalAuditService;
import com.internalaudit.client.InternalAuditServiceAsync;

public class AuditStepUploads extends VerticalPanel {
	
	FormPanel form;

	public AuditStepUploads(){

		form = new FormPanel();
		form.setAction(GWT.getModuleBaseURL()+"upload");

		// Because we're going to add a FileUpload widget, we'll need to set the
		// form to use the POST method, and multipart MIME encoding.
		form.setEncoding(FormPanel.ENCODING_MULTIPART);
		form.setMethod(FormPanel.METHOD_POST);

		// Create a panel to hold all of the form widgets.
		VerticalPanel panel = new VerticalPanel();
		form.setWidget(panel);

		// Create a TextBox, giving it a name so that it will be submitted.
		
		// Create a FileUpload widget.
		FileUpload upload = new FileUpload();
		upload.setName("uploadFormElement");
		panel.add(upload);

		// Add a 'submit' button.
		panel.add(new Button("Submit", new ClickHandler() {
			public void onClick(ClickEvent event) {
				form.submit();
			}
		}));

		// Add an event handler to the form.
//		form.addSubmitHandler(new FormPanel.SubmitHandler() {
//			public void onSubmit(SubmitEvent event) {
//				
//			}
//		});
//		form.addSubmitCompleteHandler(new FormPanel.SubmitCompleteHandler() {
//			public void onSubmitComplete(SubmitCompleteEvent event) {
//				if(event.getResults().contains("success")){
//					Window.alert("File uploaded");
////					updateFileNameInDatabase();
//				}else{
//				Window.alert(event.getResults());
//				}
//			}
//
//		
//		});

		HorizontalPanel hpnl = new HorizontalPanel();
		hpnl.add(form);
//		hpnl.add(download());
		hpnl.setSpacing(10);
		add(hpnl);
	}
	
	
	
	public Button download(){
		Button btn = new Button("Download");
		add(btn);
		btn.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
					
			}});
		return btn;
	}

	public FormPanel getForm() {
		return form;
	}

	public void setForm(FormPanel form) {
		this.form = form;
	}
}
