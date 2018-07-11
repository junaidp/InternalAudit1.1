package com.internalaudit.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.internalaudit.client.upload.testUpload;
import com.internalaudit.client.view.HeaderView;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class InternalAudit implements EntryPoint {

	public void onModuleLoad() {
		
		
		
	InternalAuditServiceAsync rpcService = GWT.create(InternalAuditService.class);
    HandlerManager eventBus = new HandlerManager(null);
    AppController appViewer = new AppController(rpcService, eventBus);
    appViewer.go(RootPanel.get());
    
   /* PopupPanel pop = new PopupPanel();
	pop.setSize("600px", "500px");
	pop.setWidget(new testUpload());
	pop.center();
	*/
	}
}
