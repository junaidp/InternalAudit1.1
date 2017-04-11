package com.internalaudit.client.view.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratedPopupPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.internalaudit.client.InternalAuditService;
import com.internalaudit.client.InternalAuditServiceAsync;
import com.internalaudit.client.view.AmendmentPopup;
import com.internalaudit.client.view.ConsolidationView;
import com.internalaudit.client.view.LoadingPopup;
import com.internalaudit.client.view.PhaseNames;
import com.internalaudit.client.view.RiskAssesmentStrategicView;
import com.internalaudit.client.view.RiskAssesmentView;
import com.internalaudit.client.view.RiskFactorsView;
import com.internalaudit.shared.RiskAssesmentDTO;
import com.internalaudit.shared.RiskFactor;
import com.internalaudit.shared.Strategic;
import com.internalaudit.shared.TimeOutException;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.event.BeforeExpandEvent;
import com.sencha.gxt.widget.core.client.event.BeforeExpandEvent.BeforeExpandHandler;
import com.sencha.gxt.widget.core.client.form.TextField;

public class ConsolidationViewData {

	private InternalAuditServiceAsync rpcService = GWT.create(InternalAuditService.class);
	private ArrayList<ConsolidationView> updatedStrategics = new ArrayList<ConsolidationView>();
	private ArrayList<Strategic> previousStrategicsEntity;
	private int index;
	private String actionPerformed;
	private Logger logger = Logger.getLogger("ConsolidationViewData");
	
	public void setData(ContentPanel cp, VerticalPanel vpnlData, Button submit){
		
		setHandlers(cp, vpnlData, submit);
		
	}
	
	public void saveConsolidation(Strategic strategic, final VerticalPanel vpnlData, ConsolidationView consolidationView, String todo, int tab, final Button buttonClicked){
		buttonClicked.setEnabled(false);
		strategic.setAuditableUnit(consolidationView.getAuditableUnit().getText());
//		strategic.setPhase("Consolidation");
		strategic.setPhase(3);
		strategic.setNextPhase(4);
		
//		strategic.setNextPhase("Prioritization");
		strategic.setComments(consolidationView.getComment());
		actionPerformed = todo;
		HashMap<String,String> hm = new HashMap<String,String>();
		if(todo.equalsIgnoreCase("approve")){
			todo = "submit";
			}
	      hm.put("todo", todo);
	      hm.put("tab", tab+"");
			rpcService.saveStrategic(strategic, hm ,  new AsyncCallback<String>(){

			@Override
			public void onFailure(Throwable caught) {
				
				logger.log(Level.INFO, "FAIL: saveStrategic .Inside Audit AuditAreaspresenter");
				if(caught instanceof TimeOutException){
					History.newItem("login");
				}else{
					System.out.println("FAIL: saveStrategic .Inside AuditAreaspresenter");
					Window.alert("FAIL: saveStrategic");// After FAIL ... write RPC Name  NOT Method Name..
				}
				
				buttonClicked.setEnabled(true);
				Window.alert("save Consolidation  failed");	
			}

			@Override
			public void onSuccess(String arg0) {
				buttonClicked.setEnabled(true);
				vpnlData.clear();
				final DecoratedPopupPanel popup = new DecoratedPopupPanel();
				if(actionPerformed.equalsIgnoreCase("save")){
					popup.setWidget(new Label("Consolidation Saved "));
				}
				else if(actionPerformed.equalsIgnoreCase("approve")){
					popup.setWidget(new Label("Consolidation Approved "));
				}
				else if(actionPerformed.equalsIgnoreCase("amend")){
					popup.setWidget(new Label("Feedback Submitted "));
				}
				else{
					popup.setWidget(new Label("Consolidation Submitted  "));
					
				}
				popup.setPopupPosition(350, 350);
				popup.show();
				Timer time = new Timer() {
					public void run() {
						popup.removeFromParent();
					}

				};//timer for showing the popup of "update"
				time.schedule(1500);
				fetchStrategic(vpnlData);
			}});
	}
	
	private void setHandlers(ContentPanel cp, final VerticalPanel vpnlData, Button submit) {
		
//		submit.addClickHandler(new ClickHandler(){
//
//			@Override
//			public void onClick(ClickEvent arg0) {
//				saveConsolidation(vpnlData);
//			}});
		
		cp.addBeforeExpandHandler(new BeforeExpandHandler(){

			@Override
			public void onBeforeExpand(BeforeExpandEvent event) {
				fetchStrategic(vpnlData);
			}});
	}

	public void fetchStrategic(final VerticalPanel vpnlData){
		HashMap<String,String> hm = new HashMap<String,String>();
	      hm.put("phase", "3");
	      hm.put("tab", "0");
	      final LoadingPopup loadingPopup = new LoadingPopup();
			loadingPopup.display();
		rpcService.fetchStrategic(hm, new AsyncCallback<ArrayList<Strategic>>(){

			@Override
			public void onFailure(Throwable caught) {
				loadingPopup.remove();
				Window.alert("Fetch Consolidation universe strategic failed");
				logger.log(Level.INFO, "FAIL: fetchStrategic .Inside Audit AuditAreaspresenter");
				if(caught instanceof TimeOutException){
					History.newItem("login");
				}else{
					System.out.println("FAIL: fetchStrategic .Inside AuditAreaspresenter");
					Window.alert("FAIL: fetchStrategic");// After FAIL ... write RPC Name  NOT Method Name..
				}
			}

			@Override
			public void onSuccess(final ArrayList<Strategic> strategics) {
				vpnlData.clear();
				loadingPopup.remove();
				 previousStrategicsEntity = strategics;
				 
				for(index=0; index<strategics.size(); index++){
					final ConsolidationView consolidationView = new ConsolidationView();
					setButtonsVisibility(strategics, index, consolidationView);
					if(strategics.get(index).getPhase() != 3 || strategics.get(index).getLoggedInUser()!= strategics.get(index).getAssignedTo().getEmployeeId()){
					disablePanel(consolidationView, strategics.get(index));
					}else{
						enablePanel(consolidationView);
					}
					consolidationView.setStrategicId(strategics.get(index).getId());
					consolidationView.setIndex(index);
					
					if(strategics.get(index).getStatus().equals("amend")){
						consolidationView.getComments().setStyleName("point");
						consolidationView.getComments().setVisible(true);
						}else{
							consolidationView.getComments().setVisible(false);
						}
					consolidationView.getComments().setTitle(strategics.get(index).getComments());
					
					
					vpnlData.add(consolidationView);
					consolidationView.getArea().setText(strategics.get(consolidationView.getIndex()).getStrategicObjective());
					consolidationView.getArea().setTitle(strategics.get(consolidationView.getIndex()).getStrategicObjective());
					
					consolidationView.getAuditableUnit().setText(strategics.get(consolidationView.getIndex()).getAuditableUnit());
					consolidationView.getAuditableUnit().setTitle(strategics.get(consolidationView.getIndex()).getAuditableUnit());
					
					if(strategics.get(consolidationView.getIndex()).getRating().equalsIgnoreCase("high")){
						consolidationView.getRiskRating().setUrl("redcircle.png");
						consolidationView.getRiskRating().setTitle("High");
					}
					if(strategics.get(consolidationView.getIndex()).getRating().equalsIgnoreCase("medium")){
						consolidationView.getRiskRating().setUrl("yellowcircle.png");
						consolidationView.getRiskRating().setTitle("Medium");
					}
					if(strategics.get(consolidationView.getIndex()).getRating().equalsIgnoreCase("low")){
						consolidationView.getRiskRating().setUrl("greencircle.png");
						consolidationView.getRiskRating().setTitle("Low");
					}
					
					
					if(strategics.get(consolidationView.getIndex()).getTab()==0){
//						consolidationView.getTab().setText("(Strategic)");
					}
					else if(strategics.get(consolidationView.getIndex()).getTab()==1){
//						consolidationView.getTab().setText("(Operations)");
					}
					if(strategics.get(consolidationView.getIndex()).getTab()==2){
//						consolidationView.getTab().setText("(Reporting)");
					}
					if(strategics.get(consolidationView.getIndex()).getTab()==3){
//						consolidationView.getTab().setText("(Compliance)");
					}
					
					
					consolidationView.getBtnSubmit().addClickHandler(new ClickHandler(){

						@Override
						public void onClick(ClickEvent event) {
							saveConsolidation(strategics.get(consolidationView.getIndex()), vpnlData, consolidationView, "approve", strategics.get(consolidationView.getIndex()).getTab(), consolidationView.getBtnSubmit());
//							saveConsolidation(strategics.get(consolidationView.getIndex()), vpnlData, consolidationView, "submit", strategics.get(consolidationView.getIndex()).getTab(), consolidationView.getBtnSubmit());
							
						}});
					consolidationView.getBtnSave().addClickHandler(new ClickHandler(){

						@Override
						public void onClick(ClickEvent event) {
							saveConsolidation(strategics.get(consolidationView.getIndex()), vpnlData, consolidationView, "save", strategics.get(consolidationView.getIndex()).getTab(), consolidationView.getBtnSave());
						}});
					
					consolidationView.getBtnApprove().addClickHandler(new ClickHandler(){

						@Override
						public void onClick(ClickEvent event) {
							saveConsolidation(strategics.get(consolidationView.getIndex()), vpnlData, consolidationView, "approve", strategics.get(consolidationView.getIndex()).getTab(), consolidationView.getBtnApprove());
						}});
					
					consolidationView.getBtnAmend().addClickHandler(new ClickHandler(){

						@Override
						public void onClick(ClickEvent event) {
							final AmendmentPopup amendmentPopup = new AmendmentPopup();
							amendmentPopup.popupAmendment();
							amendmentPopup.getBtnSubmit().addClickHandler(new ClickHandler(){

								@Override
								public void onClick(ClickEvent event) {
									consolidationView.setComment(amendmentPopup.getComments().getText());
									saveConsolidation(strategics.get(consolidationView.getIndex()), vpnlData, consolidationView, "amend", strategics.get(consolidationView.getIndex()).getTab(), consolidationView.getBtnAmend());
									amendmentPopup.getPopupComments().removeFromParent();
								}});
						}});
					
					consolidationView.getBtnDecline().addClickHandler(new ClickHandler(){

						@Override
						public void onClick(ClickEvent event) {
							boolean confirmed = Window.confirm("Are you sure you want to delete : " + consolidationView.getArea().getText());
							if(confirmed){
								declineStrategic(consolidationView.getStrategicId(), vpnlData, consolidationView.getBtnDecline());
							}
						}

						});

					consolidationView.getBtnDeclineInitiator().addClickHandler(new ClickHandler(){

						@Override
						public void onClick(ClickEvent event) {
							boolean confirmed = Window.confirm("Are you sure you want to delete : " + consolidationView.getArea().getText());
							if(confirmed){
								declineStrategic(consolidationView.getStrategicId(), vpnlData, consolidationView.getBtnDeclineInitiator());
							}
						}

						});
//				updatedStrategics.add(consolidationView);
				}
			}});
	}
	
	
	
	public void disablePanel(ConsolidationView consolidationView, Strategic strategic){
		consolidationView.getHpnlButtonsApprovar().setVisible(false);
		consolidationView.getHpnlButtonInitiator().setVisible(false);
		consolidationView.getAuditableUnit().setEnabled(false);
		consolidationView.getSubmitted().setVisible(true);
		consolidationView.getSubmitted().setTitle(strategic.getStatus() + ": In "+ PhaseNames.getPhaseNames(strategic.getPhase()));

	}
	
	public void enablePanel(ConsolidationView consolidationView){
		consolidationView.getAuditableUnit().setEnabled(true);
		consolidationView.getSubmitted().setVisible(false);
	}
	
	private void setButtonsVisibility(
			final ArrayList<Strategic> strategics, int i, ConsolidationView consolidationView) {
		if(strategics.get(i).getStatus().equals("submitted")){
			consolidationView.getHpnlButtonsApprovar().setVisible(true);
			consolidationView.getHpnlButtonInitiator().setVisible(false);

		}else if(strategics.get(i).getStatus().equals("amend")){
			consolidationView.getBtnDeclineInitiator().setVisible(false);
			consolidationView.getHpnlButtonsApprovar().setVisible(false);
			consolidationView.getHpnlButtonInitiator().setVisible(true);

		}else{
			consolidationView.getBtnDeclineInitiator().setVisible(true);
			consolidationView.getHpnlButtonsApprovar().setVisible(false);
			consolidationView.getHpnlButtonInitiator().setVisible(true);
		}
	}
	
	public void declineStrategic(int strategicId,final VerticalPanel vpnlData, final Button buttonClicked){
		buttonClicked.setEnabled(false);
		rpcService.declineStrategic(strategicId, new AsyncCallback<String>(){

			@Override
			public void onFailure(Throwable caught) {
				
				logger.log(Level.INFO, "FAIL: declineStrategic .Inside Audit AuditAreaspresenter");
				if(caught instanceof TimeOutException){
					History.newItem("login");
				}else{
					System.out.println("FAIL: declineStrategic .Inside AuditAreaspresenter");
					Window.alert("FAIL: declineStrategic");// After FAIL ... write RPC Name  NOT Method Name..
				}
				
				
				buttonClicked.setEnabled(true);
				Window.alert("decline strategic failed");
			}

			@Override
			public void onSuccess(String result) {
				buttonClicked.setEnabled(true);
				final DecoratedPopupPanel popup = new DecoratedPopupPanel();
				popup.setWidget(new Label("strategic declined ! "));
				popup.setPopupPosition(350, 350);
				popup.show();
				Timer time = new Timer() {
					public void run() {
						popup.removeFromParent();
					}

				};//timer for showing the popup of "update"
				time.schedule(1500);
				fetchStrategic(vpnlData);

			}});
	}
	
}
