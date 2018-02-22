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
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.internalaudit.client.InternalAuditService;
import com.internalaudit.client.InternalAuditServiceAsync;
import com.internalaudit.client.view.DisplayAlert;
import com.internalaudit.client.view.FinalAuditablesView;
import com.internalaudit.shared.Strategic;
import com.internalaudit.shared.TimeOutException;
import com.internalaudit.shared.User;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.event.BeforeExpandEvent;
import com.sencha.gxt.widget.core.client.event.BeforeExpandEvent.BeforeExpandHandler;
import com.sencha.gxt.widget.core.client.form.TextField;

public class FinalAuditablesViewData {

	private InternalAuditServiceAsync rpcService = GWT.create(InternalAuditService.class);
	private Logger logger = Logger.getLogger("FinalAuditablesViewData");

	public void setData(ContentPanel cp, final FinalAuditablesView finalAuditablesView, VerticalPanel vpnlFinalAuditable, final User loggedInUser){

		cp.addBeforeExpandHandler(new BeforeExpandHandler(){

			@Override
			public void onBeforeExpand(BeforeExpandEvent event) {
				fetchFinalAuditables(finalAuditablesView, loggedInUser);
			}});


	}

	public void fetchFinalAuditables(final FinalAuditablesView finalAuditablesView, final User loggedInUser){
		rpcService.fetchFinalAuditables(new AsyncCallback<ArrayList<Strategic>>(){

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Fetch Final Auditables failed");


				logger.log(Level.INFO, "FAIL: fetchDashBoard .Inside Audit AuditAreaspresenter");
				if(caught instanceof TimeOutException){
					History.newItem("login");
				}else{
					System.out.println("FAIL: fetchDashBoard .Inside AuditAreaspresenter");
					Window.alert("FAIL: fetchDashBoard");// After FAIL ... write RPC Name  NOT Method Name..
				}

			}

			@Override
			public void onSuccess(final ArrayList<Strategic> strategic) {
				finalAuditablesView.getAreas().clear();
				HorizontalPanel hpnlHeading = new HorizontalPanel();
				hpnlHeading.setSpacing(5);
				Label lblUnitHeading = new Label("Auditable Unit");
				Label lblObjHeading = new Label("Objective");
				lblUnitHeading.setWidth("400px");
				lblObjHeading.setWidth("250px");
				hpnlHeading.add(lblUnitHeading);
				
//				hpnlHeading.addStyleName("statusRowConsolidation");

				lblUnitHeading.addStyleName("labelHeading");
				lblObjHeading.addStyleName("labelHeading");
				finalAuditablesView.getAreas().add(hpnlHeading);
				//				for(int i=0; i< result.size(); i++){
				//					Label lblObjective = new Label(result.get(i).getStrategicObjective());
				//					Label lblUnit = new Label(result.get(i).getAuditableUnit());
				//					lblObjective.setWidth("400px");
				//				
				//					lblUnit.setWidth("400px");
				//					HorizontalPanel hpnlMain = new HorizontalPanel();
				//					hpnlMain.add(lblUnit);
				//					hpnlMain.add(lblObjective);
				//					finalAuditablesView.getAreas().add(hpnlMain);
				//					hpnlMain.addStyleName("form-row");
				//				}
				ArrayList<String> auditableUnits = new ArrayList<String>();
				ArrayList<HorizontalPanel>hpnlContainer = new ArrayList<HorizontalPanel>();
				
				int count =0;
				for(int i=0; i< strategic.size(); i++){
					
					Button btnApprove = new Button("Approve");
					Button btnDecline = new Button("Decline");
					final TextField txtComments = new TextField();
					txtComments.setEmptyText("Comments");
					
					HorizontalPanel hpnlButtonContainer = new HorizontalPanel();
					hpnlButtonContainer.add(btnDecline);
					hpnlButtonContainer.add(btnApprove);
					
					VerticalPanel vpnlObjectiveContainer = new VerticalPanel();
					Label lblUnit = new Label(strategic.get(i).getAuditableUnit());

					lblUnit.setWidth("600px");
					if(! auditableUnits.contains(strategic.get(i).getAuditableUnit())){
						count++;
						HorizontalPanel hpnlMain = new HorizontalPanel();

						auditableUnits.add(strategic.get(i).getAuditableUnit());
						Label lblCount = new Label("");
						lblCount.addStyleName("blue");
						lblCount.setText(count + ")" );
						hpnlMain.add(lblCount);
						hpnlMain.add(lblUnit);
						hpnlMain.setSpacing(5);
						
//						hpnlMain.add(vpnlObjectiveContainer);
//						if(loggedInUser.getEmployeeId().isAuditHead()) {
						if(loggedInUser.getEmployeeId().getRollId().getRollId() == 1) {
							if( strategic.get(i).isApprovedByAuditHead()){
								Label lblApproved = new Label("Approved");
								lblApproved.addStyleName("blue");
								hpnlMain.add(lblApproved);
							}else{
								VerticalPanel vpnlCommentsAndButton = new VerticalPanel();
								vpnlCommentsAndButton.add(txtComments);
								vpnlCommentsAndButton.add(hpnlButtonContainer);
								hpnlMain.add(vpnlCommentsAndButton);
							}
						}
						
						finalAuditablesView.getAreas().add(hpnlMain);
						hpnlMain.addStyleName("form-row");
						hpnlContainer.add(hpnlMain);
						
						final DataSetter dataSetter = new DataSetter();
						dataSetter.setId(i);
						
						btnDecline.addClickHandler(new ClickHandler(){

							@Override
							public void onClick(ClickEvent event) {
								strategic.get(dataSetter.getId()).setComments(txtComments.getText());
								declineFinalAuditable(strategic.get(dataSetter.getId()),finalAuditablesView, loggedInUser);
							}});
						
						btnApprove.addClickHandler(new ClickHandler(){

							@Override
							public void onClick(ClickEvent event) {
								strategic.get(dataSetter.getId()).setComments(txtComments.getText());
								approveFinalAuditable(strategic.get(dataSetter.getId()),finalAuditablesView, loggedInUser);
							}});
						
					}

				}


				for(int i=0; i< hpnlContainer.size(); i++){
					for(int j=0; j< strategic.size(); j++){
						Label lblObjective = new Label(strategic.get(j).getStrategicObjective() +" ( " + strategic.get(j).getRating()+ " ) ");
						lblObjective.setWidth("400px");
						Label lblUnit = (Label) hpnlContainer.get(i).getWidget(0);
						if(lblUnit.getText().equalsIgnoreCase(strategic.get(j).getAuditableUnit())){
							VerticalPanel vpnlObjectiveContainer = (VerticalPanel) hpnlContainer.get(i).getWidget(1);
//							vpnlObjectiveContainer.add(lblObjective);	// ONLY SHOWING AUDITBLE UNIT FROM NOW...
						}
						
					}
				}


			}

		});
	}
	
	
	public void approveFinalAuditable(Strategic strategic, final FinalAuditablesView finalAuditablesView, final User loggedInUser){
		strategic.setApprovedByAuditHead(true);
		rpcService.approveFinalAuditable(strategic, new AsyncCallback<String>(){

			@Override
			public void onFailure(Throwable caught) {
				new DisplayAlert("error in approving final auditable");
			}

			@Override
			public void onSuccess(String result) {
				new DisplayAlert("Approved as final auditable");
				fetchFinalAuditables(finalAuditablesView, loggedInUser);
			}});
		
	}
	
	public void declineFinalAuditable(Strategic strategic, final FinalAuditablesView finalAuditablesView, final User loggedInUser){
		
		strategic.setApprovedByAuditHead(false);
		strategic.setAudit(false);
		rpcService.declineFinalAuditable(strategic, new AsyncCallback<String>(){

			@Override
			public void onFailure(Throwable caught) {
				new DisplayAlert("error in declining final auditable");
				
			}

			@Override
			public void onSuccess(String result) {
				new DisplayAlert("Declined as final auditable");
				fetchFinalAuditables(finalAuditablesView, loggedInUser);
			}});
		
	}

}
