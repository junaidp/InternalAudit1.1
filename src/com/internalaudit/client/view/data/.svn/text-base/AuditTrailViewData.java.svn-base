package com.internalaudit.client.view.data;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.internalaudit.client.InternalAuditService;
import com.internalaudit.client.InternalAuditServiceAsync;
import com.internalaudit.client.view.AuditnTrail;
import com.internalaudit.shared.StrategicAudit;
import com.internalaudit.shared.TimeOutException;

public class AuditTrailViewData {

	private InternalAuditServiceAsync rpcService = GWT.create(InternalAuditService.class);
	private Logger logger = Logger.getLogger("AuditTrailViewData");
	
	public void setData(final AuditnTrail auditTrailView) {
		
		auditTrailView.getRefresh().addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				fetchStrategicAudit(auditTrailView);
			}});
		
		fetchStrategicAudit(auditTrailView);
	}
	
	
	public void fetchStrategicAudit(final AuditnTrail auditTrailView){
		rpcService.fetchStrategicAudit(new AsyncCallback<ArrayList<StrategicAudit>>(){

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
								
				logger.log(Level.INFO, "FAIL: fetchStrategicAudit .Inside Audit AuditAreaspresenter");
				if(caught instanceof TimeOutException){
					History.newItem("login");
				}else{
					System.out.println("FAIL: fetchStrategicAudit .Inside AuditAreaspresenter");
					Window.alert("FAIL: fetchStrategicAudit");// After FAIL ... write RPC Name  NOT Method Name..
				}
				
				
			}

			@Override
			public void onSuccess(ArrayList<StrategicAudit> strategicAudits) {
				auditTrailView.populateStrategicAudit(strategicAudits);
			}});
	}

}
