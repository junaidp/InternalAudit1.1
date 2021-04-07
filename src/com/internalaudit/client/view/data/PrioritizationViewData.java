package com.internalaudit.client.view.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratedPopupPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.internalaudit.client.InternalAuditService;
import com.internalaudit.client.InternalAuditServiceAsync;
import com.internalaudit.client.view.AmendmentPopup;
import com.internalaudit.client.view.ButtonRound;
import com.internalaudit.client.view.DisplayAlert;
import com.internalaudit.client.view.LoadingPopup;
import com.internalaudit.client.view.PhaseNames;
import com.internalaudit.client.view.PopupViewGXT;
import com.internalaudit.client.view.PrioritizationView;
import com.internalaudit.shared.Strategic;
import com.internalaudit.shared.TimeOutException;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.event.BeforeExpandEvent;
import com.sencha.gxt.widget.core.client.event.BeforeExpandEvent.BeforeExpandHandler;

public class PrioritizationViewData {

	private InternalAuditServiceAsync rpcService = GWT.create(InternalAuditService.class);
	private ArrayList<PrioritizationView> updatedStrategics = new ArrayList<PrioritizationView>();
	private ArrayList<Strategic> previousStrategicsEntity;
	private int index;
	private String actionPerformed;
	private Logger logger = Logger.getLogger("PrioritizationViewData");

	public void setData(ContentPanel cp, VerticalPanel vpnlData, ButtonRound submit) {

		setHandlers(cp, vpnlData, submit);

	}

	public void savePrioritization(final VerticalPanel vpnlData, PrioritizationView prioritizationView,
			Strategic strategic, String todo, int tab, final Button buttonRound) {
		if(!prioritizationView.getAudit().isChecked()) {
			Window.alert("Please check 'Selected for Audit'");
		}
		else{
			buttonRound.setEnabled(false);
			strategic.setAudit(prioritizationView.getAudit().getValue());
	
			/////////////
	//		strategic.setYear(Integer.parseInt(
	//				prioritizationView.getListYears().getValue(prioritizationView.getListYears().getSelectedIndex())));
			strategic.setYear(Integer.parseInt(prioritizationView.getListYears().getSelectedValue()));
			////////////
			strategic.setNextPhase(5);
			strategic.setPhase(4);
			//
			strategic.setApprovedByAuditHead(false);
			//
	
			strategic.setComments(prioritizationView.getComment());
			actionPerformed = todo;
			HashMap<String, String> hm = new HashMap<String, String>();
			if (todo.equalsIgnoreCase("approve")) {
				todo = "submit";
			}
			hm.put("todo", todo);
			hm.put("tab", tab + "");
			rpcService.saveStrategic(strategic, hm, new AsyncCallback<String>() {
	
				@Override
				public void onFailure(Throwable caught) {
	
					//
					logger.log(Level.INFO, "FAIL: saveStrategic .Inside Audit AuditAreaspresenter");
					if (caught instanceof TimeOutException) {
						History.newItem("login");
					} else {
						System.out.println("FAIL: saveStrategic .Inside AuditAreaspresenter");
						Window.alert("FAIL: saveStrategic");// After FAIL ... write
															// RPC Name NOT Method
															// Name..
					}
	
					buttonRound.setEnabled(true);
					Window.alert("save Prioritization strategic failed");
				}
	
				@Override
				public void onSuccess(String arg0) {
					buttonRound.setEnabled(true);
					final DecoratedPopupPanel popup = new DecoratedPopupPanel();
					if (actionPerformed.equalsIgnoreCase("save")) {
						popup.setWidget(new Label("Prioritization Saved "));
					} else if (actionPerformed.equalsIgnoreCase("approve")) {
						popup.setWidget(new Label("Prioritization Approved "));
					} else if (actionPerformed.equalsIgnoreCase("amend")) {
						popup.setWidget(new Label("Feedback Submitted "));
					}
	
					else {
						popup.setWidget(new Label("Prioritization Submitted "));
	
					}
					popup.setPopupPosition(350, 350);
					popup.show();
					Timer time = new Timer() {
						public void run() {
							popup.removeFromParent();
						}
	
					};// timer for showing the popup of "update"
					time.schedule(1500);
					fetchStrategic(vpnlData);
				}
			});
		}
	}

	private void setHandlers(ContentPanel cp, final VerticalPanel vpnlData, ButtonRound submit) {

		// submit.addClickHandler(new ClickHandler(){
		//
		// @Override
		// public void onClick(ClickEvent arg0) {
		// savePrioritization();
		// }});
		//
		cp.addBeforeExpandHandler(new BeforeExpandHandler() {

			@Override
			public void onBeforeExpand(BeforeExpandEvent event) {
				fetchStrategic(vpnlData);
			}
		});
	}

	public void fetchStrategic(final VerticalPanel vpnlData) {
		final LoadingPopup loadingPopup = new LoadingPopup();
		loadingPopup.display();
		HashMap<String, String> hm = new HashMap<String, String>();

		hm.put("phase", "4");
		hm.put("tab", "0");
		rpcService.fetchStrategic(hm, new AsyncCallback<ArrayList<Strategic>>() {

			@Override
			public void onFailure(Throwable caught) {
				loadingPopup.remove();
				Window.alert("Fetch Prioritization universe strategic failed");
				logger.log(Level.INFO, "FAIL: fetchStrategic .Inside Audit AuditAreaspresenter");
				if (caught instanceof TimeOutException) {
					History.newItem("login");
				} else {
					System.out.println("FAIL: fetchStrategic .Inside AuditAreaspresenter");
					Window.alert("FAIL: fetchStrategic");// After FAIL ... write
															// RPC Name NOT
															// Method Name..
				}
			}

			@Override
			public void onSuccess(final ArrayList<Strategic> strategics) {
				vpnlData.clear();
				previousStrategicsEntity = strategics;
				loadingPopup.remove();
				for (index = 0; index < strategics.size(); index++) {
					final PrioritizationView prioritizationView = new PrioritizationView();
					prioritizationView.fetchCurrentYear(new AsyncCallback<String>() {

						@Override
						public void onFailure(Throwable arg0) {
							GWT.log("FAIL , fetchCurrent Year");
						}
//strategic.(index) change to 0 in listYear
						@Override
						public void onSuccess(String arg0) {
							for (int i = 0; i < prioritizationView.getListYears().getItemCount(); i++) {
								if (Integer.parseInt(prioritizationView.getListYears().getItemText(i)) == strategics.get(0).getYear()) {
									prioritizationView.getListYears().setSelectedIndex(i);
								}
							}
						}
					} );
					/// CHange here
					// updateYear(prioritizationView, strategics.get(index),
					// vpnlData);
					prioritizationView.getAudit().setTitle(strategics.get(index).getComments());
					
					
					// prioritizationView.getListYears().setSelectedIndex(index);
					//
					if(strategics.get(index).getComments() != null && strategics.get(index).getComments().length()>1) 
						showFeeback(prioritizationView.getAnchorFeedback(), strategics.get(index).getComments());

					setButtonsVisibility(strategics, index, prioritizationView);
					if (strategics.get(index).getPhase() != 4 || strategics.get(index).getLoggedInUser() != strategics
							.get(index).getAssignedTo().getEmployeeId()) {
						disablePanel(prioritizationView, strategics.get(index));
					} else {
						enablePanel(prioritizationView);
						setButtonsVisibility(strategics, index, prioritizationView);

					}
					prioritizationView.setStrategicId(strategics.get(index).getId());
					prioritizationView.setIndex(index);

					if (strategics.get(index).getStatus().equals("amend")) {
						prioritizationView.getFeedback().addStyleName("point");
						prioritizationView.getFeedback().removeStyleName("white");
					} else {
						prioritizationView.getFeedback().addStyleName("white");
					}
					// prioritizationView.getComments().setTitle(strategics.get(index).getComments());
					final String feedbackmsg = strategics.get(index).getComments();
					prioritizationView.getFeedback().addClickHandler(new ClickHandler() {
						@Override
						public void onClick(ClickEvent event) {
							PopupViewGXT feedbackpopup = new PopupViewGXT(new HTML(feedbackmsg), "Feedback");
						}
					});

					vpnlData.add(prioritizationView);
					prioritizationView.getArea().setText(strategics.get(index).getAuditableUnit());
					prioritizationView.getAudit().setValue(strategics.get(index).isAudit());
					// prioritizationView.getRiskRating().setText(strategics.get(index).getRating());
					if (strategics.get(index).getRating().equalsIgnoreCase("high")) {
						prioritizationView.getRiskRating().setUrl("redcircle.png");
						prioritizationView.getRiskRating().setTitle("High");
					}
					if (strategics.get(index).getRating().equalsIgnoreCase("medium")) {
						prioritizationView.getRiskRating().setUrl("yellowcircle.png");
						prioritizationView.getRiskRating().setTitle("Medium");
					}
					if (strategics.get(index).getRating().equalsIgnoreCase("low")) {
						prioritizationView.getRiskRating().setUrl("greencircle.png");
						prioritizationView.getRiskRating().setTitle("Low");
					}

					prioritizationView.getObjective().setText(strategics.get(index).getStrategicObjective());
					if (strategics.get(prioritizationView.getIndex()).getTab() == 0) {
						// prioritizationView.getTab().setText("(Strategic)");
					} else if (strategics.get(prioritizationView.getIndex()).getTab() == 1) {
						// prioritizationView.getTab().setText("(Operations)");
					}
					if (strategics.get(prioritizationView.getIndex()).getTab() == 2) {
						// prioritizationView.getTab().setText("(Reporting)");
					}
					if (strategics.get(prioritizationView.getIndex()).getTab() == 3) {
						// prioritizationView.getTab().setText("(Compliance)");
					}
					prioritizationView.getFeedback().setTitle(strategics.get(index).getComments());

					prioritizationView.getBtnSubmit().addClickHandler(new ClickHandler() {

						@Override
						public void onClick(ClickEvent event) {

							savePrioritization(vpnlData, prioritizationView,
									strategics.get(prioritizationView.getIndex()), "submit",
									strategics.get(prioritizationView.getIndex()).getTab(),
									prioritizationView.getBtnSubmit());
						}
					});

					prioritizationView.getBtnSave().addClickHandler(new ClickHandler() {

						@Override
						public void onClick(ClickEvent event) {
							savePrioritization(vpnlData, prioritizationView,
									strategics.get(prioritizationView.getIndex()), "save",
									strategics.get(prioritizationView.getIndex()).getTab(),
									prioritizationView.getBtnSave());
						}
					});

					prioritizationView.getBtnDeclineInitiator().addClickHandler(new ClickHandler() {

						@Override
						public void onClick(ClickEvent event) {
							boolean confirmed = Window.confirm(
									"Are you sure you want to delete : " + prioritizationView.getArea().getText());
							if (confirmed) {
								declineStrategic(prioritizationView.getStrategicId(), vpnlData,
										prioritizationView.getBtnDeclineInitiator());
							}
						}
					});

					////
					prioritizationView.getBtnApprove().addClickHandler(new ClickHandler() {

						@Override
						public void onClick(ClickEvent event) {
							savePrioritization(vpnlData, prioritizationView,
									strategics.get(prioritizationView.getIndex()), "approve",
									strategics.get(prioritizationView.getIndex()).getTab(),
									prioritizationView.getBtnApprove());
						}
					});

					prioritizationView.getBtnFeedback().addClickHandler(new ClickHandler() {

						@Override
						public void onClick(ClickEvent event) {
							final AmendmentPopup amendmentPopup = new AmendmentPopup();
							amendmentPopup.popupAmendment();
							amendmentPopup.getBtnSubmit().addClickHandler(new ClickHandler() {

								@Override
								public void onClick(ClickEvent event) {
									prioritizationView.setComment(amendmentPopup.getComments().getText());
									savePrioritization(vpnlData, prioritizationView,
											strategics.get(prioritizationView.getIndex()), "amend",
											strategics.get(prioritizationView.getIndex()).getTab(),
											prioritizationView.getBtnFeedback());
									amendmentPopup.getPopupComments().removeFromParent();
								}
							});
						}
					});

					prioritizationView.getBtnDecline().addClickHandler(new ClickHandler() {

						@Override
						public void onClick(ClickEvent event) {
							boolean confirmed = Window.confirm(
									"Are you sure you want to delete : " + prioritizationView.getArea().getText());
							if (confirmed) {
								declineStrategic(prioritizationView.getStrategicId(), vpnlData,
										prioritizationView.getBtnDecline());
							}
						}
					});

					// updatedStrategics.add(prioritizationView);
				}
			}


			private void updateYear(final PrioritizationView prioritizationView, final Strategic strategic,
					final VerticalPanel vpnlData) {
				prioritizationView.getListYears().addChangeHandler(new ChangeHandler() {

					@Override
					public void onChange(ChangeEvent event) {
						if (strategic.getPhase() == 5 && !strategic.isAudit()) {
							strategic.setYear(Integer.parseInt(prioritizationView.getListYears()
									.getItemText(prioritizationView.getListYears().getSelectedIndex())));
							rpcService.updateStrategic(strategic, new AsyncCallback<String>() {

								@Override
								public void onFailure(Throwable caught) {
									Window.alert("fail year updating");
								}

								@Override
								public void onSuccess(String result) {
									fetchStrategic(vpnlData);
									new DisplayAlert("Moved to year " + strategic.getYear());

								}
							});
						}
					}
				});
			}
		});
	}

	public void disablePanel(PrioritizationView prioritizationView, Strategic strategic) {
		prioritizationView.getHpnlButtonsApprovar().setVisible(false);
		prioritizationView.getHpnlButtonInitiator().setVisible(false);
		prioritizationView.getAudit().setEnabled(false);
		prioritizationView.getListYears().setEnabled(false);
		prioritizationView.getListYears().addStyleName("listboxDisabledcheck");
		prioritizationView.getSubmitted().setVisible(true);
		prioritizationView.getSubmitted()
				.setTitle(strategic.getStatus() + ": In " + PhaseNames.getPhaseNames(strategic.getPhase()));
	}

	public void enablePanel(PrioritizationView prioritizationView) {
		prioritizationView.getAudit().setEnabled(true);
		prioritizationView.getSubmitted().setVisible(false);
		prioritizationView.getListYears().setEnabled(true);
	}

	private void setButtonsVisibility(final ArrayList<Strategic> strategics, int i,
			PrioritizationView prioritizationView) {
		if (strategics.get(i).getStatus().equals("submitted")) {
			prioritizationView.getHpnlButtonsApprovar().setVisible(true);
			prioritizationView.getHpnlButtonInitiator().setVisible(false);

		} else if (strategics.get(i).getStatus().equals("amend")) {
			prioritizationView.getBtnDeclineInitiator().setVisible(false);
			prioritizationView.getHpnlButtonsApprovar().setVisible(false);
			prioritizationView.getHpnlButtonInitiator().setVisible(true);
		} else {
			prioritizationView.getBtnDeclineInitiator().setVisible(true);
			prioritizationView.getHpnlButtonsApprovar().setVisible(false);
			prioritizationView.getHpnlButtonInitiator().setVisible(true);
		}
	}
	
	private void showFeeback(Anchor feedback, final String feedbackMsg) {
		feedback.setVisible(true);
		feedback.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent arg0) {
				new PopupViewGXT(new HTML(feedbackMsg), "Feedback");
			}
		});
	}

	public void declineStrategic(int strategicId, final VerticalPanel vpnlData, final Button buttonRound) {
		buttonRound.setEnabled(false);
		rpcService.declineStrategic(strategicId, new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable caught) {

				logger.log(Level.INFO, "FAIL: declineStrategic .Inside Audit AuditAreaspresenter");
				if (caught instanceof TimeOutException) {
					History.newItem("login");
				} else {
					System.out.println("FAIL: declineStrategic .Inside AuditAreaspresenter");
					Window.alert("FAIL: declineStrategic");// After FAIL ...
															// write RPC Name
															// NOT Method Name..
				}

				buttonRound.setEnabled(true);
				Window.alert("decline strategic failed");
			}

			@Override
			public void onSuccess(String result) {
				buttonRound.setEnabled(true);
				// vpnlStrategic.clear();
				final DecoratedPopupPanel popup = new DecoratedPopupPanel();
				popup.setWidget(new Label("strategic deleted ! "));
				popup.setPopupPosition(350, 350);
				popup.show();
				Timer time = new Timer() {
					public void run() {
						popup.removeFromParent();
					}

				};// timer for showing the popup of "update"
				time.schedule(1500);
				fetchStrategic(vpnlData);

			}
		});
	}

}
