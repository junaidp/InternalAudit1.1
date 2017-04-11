package com.internalaudit.client.view.AuditEngagement;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.internalaudit.client.InternalAuditServiceAsync;
import com.internalaudit.shared.AuditEngagement;
import com.internalaudit.shared.JobCreation;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.internalaudit.shared.TimeOutException;


public class ActivityObjectiveViewData {
	
	private Logger logger = Logger.getLogger("ActivityObjectiveViewData");
	
	public void setData(ActivityObjectiveView activityObjectiveView,
			InternalAuditServiceAsync rpcService, int selectedJobId) {
		
		
		activityObjectiveHandlers(activityObjectiveView, rpcService, selectedJobId);
		
	}

	private void activityObjectiveHandlers(
			final ActivityObjectiveView activityObjectiveView,
			final InternalAuditServiceAsync rpcService,
			final int selectedJobId) 
	{
		activityObjectiveView.getSubmit().addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				
				//Window.alert("Saving"+activityObjectiveView.getActivityObjective().getText()+" Data to JobID:"+selectedJobId+" via rpc from here: ");
				
				String toSave = activityObjectiveView.getActivityObjective().getText();
				int jobCreationId = selectedJobId;
				
				
				AuditEngagement e = new AuditEngagement();
				e.setActivityObj(toSave);
				
				JobCreation j = new JobCreation();
				j.setJobCreationId(jobCreationId);
				e.setJobCreation(j);
				
				rpcService.updateAuditEngagement(e, "activityObj", new AsyncCallback<Boolean>() {
					
					@Override
					public void onSuccess(Boolean arg0) {
						Window.alert("success");
					}
					
					@Override
					public void onFailure(Throwable caught) {
						Window.alert("fail");
						

						logger.log(Level.INFO, "FAIL: updateAuditEngagement .Inside Audit AuditAreaspresenter");
						if(caught instanceof TimeOutException){
							History.newItem("login");
						}else{
							System.out.println("FAIL: updateAuditEngagement .Inside AuditAreaspresenter");
							Window.alert("FAIL: updateAuditEngagement");// After FAIL ... write RPC Name  NOT Method Name..
						}
						
						
					}
				});
				
				
				// call rpc from here.. and save this data to this selectedJobId.. in our new table under column  assignmentObjective
			}});

	}
		
}
