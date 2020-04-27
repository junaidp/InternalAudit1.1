package com.internalaudit.client.view.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.WhiteSpace;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratedPopupPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.internalaudit.client.InternalAuditService;
import com.internalaudit.client.InternalAuditServiceAsync;
import com.internalaudit.client.view.AmendmentPopup;
import com.internalaudit.client.view.LoadingPopup;
import com.internalaudit.client.view.PhaseNames;
import com.internalaudit.client.view.PopupViewGXT;
import com.internalaudit.client.view.RiskAssesmentStrategicView;
import com.internalaudit.client.view.RiskAssesmentView;
import com.internalaudit.client.view.RiskFactorHeadingView;
import com.internalaudit.client.view.RiskFactorsView;
import com.internalaudit.shared.RiskAssesmentDTO;
import com.internalaudit.shared.RiskFactor;
import com.internalaudit.shared.Strategic;
import com.internalaudit.shared.StrategicRisk;
import com.internalaudit.shared.TimeOutException;

public class RiskAssesmentStrategicViewData {

	private InternalAuditServiceAsync rpcService = GWT.create(InternalAuditService.class);
	private VerticalPanel strategicPanel;
	private ArrayList<RiskFactor> riskFactors = new ArrayList<RiskFactor>();
	private ArrayList<RiskAssesmentStrategicView> updatedStrategics = new ArrayList<RiskAssesmentStrategicView>();
	private ArrayList<RiskAssesmentDTO> previousStrategicsEntity;
	private String actionPerformed;
	private int selectedTab = 0;
	private Logger logger = Logger.getLogger("RiskAssesmentStrategicViewData");

	public void setData(VerticalPanel strategicPanel, RiskAssesmentView riskAssesmentView, int tab) {

		this.strategicPanel = strategicPanel;

		fetchRiskFactors(riskAssesmentView, tab);
		setHandlers();
		updateFields();

	}

	public void updateFields() {

	}

	public void setHandlers() {

		Button saveRiskAssesment = (Button) strategicPanel.getWidget(0);
		saveRiskAssesment.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent arg0) {
				for (int i = 0; i < updatedStrategics.size(); i++) {
					previousStrategicsEntity.get(i).getStrategic().setRating(updatedStrategics.get(i).getRating()
							.getValue(updatedStrategics.get(i).getRating().getSelectedIndex()));
					// previousStrategicsEntity.get(i).setStrategicObjective(updatedStrategics.get(i).getStrategicObjective().getText());

					// new work
					previousStrategicsEntity.get(i).getStrategic()
							.setUserDefinedRating(updatedStrategics.get(i).getListBoxUserOption()
									.getValue(updatedStrategics.get(i).getListBoxUserOption().getSelectedIndex()));
					// if
					// (!updatedStrategics.get(i).getRating().getSelectedValue()
					// .equals(updatedStrategics.get(i).getListBoxUserOption().getSelectedValue()))
					// {
					// updatedStrategics.get(i).getPanelRatingComment().setVisible(true);
					// }

				}
				saveStrategics();

			}
		});
	}

	public void saveStrategics() {
		// ArrayList<Strategic> strategics = new ArrayList<Strategic>();
		// for(int i=0; i<previousStrategicsEntity.size(); i++){
		// strategics.add(previousStrategicsEntity.get(i).getStrategic());
		// }
		// rpcService.saveStrategic(strategics,"submit", new
		// AsyncCallback<String>(){
		//
		// @Override
		// public void onFailure(Throwable arg0) {
		// Window.alert("save RiskAssesment strategic failed");
		// }
		//
		// @Override
		// public void onSuccess(String arg0) {
		// final DecoratedPopupPanel popup = new DecoratedPopupPanel();
		// popup.setWidget(new Label("RiskAssesment strategic saved ! "));
		// popup.setPopupPosition(350, 350);
		// popup.show();
		// Timer time = new Timer() {
		// public void run() {
		// popup.removeFromParent();
		// }
		//
		// };//timer for showing the popup of "update"
		// time.schedule(2000);
		// }});
	}

	public void fetchRiskFactors(final RiskAssesmentView riskAssesmentView, final int tab) {

		rpcService.fetchRiskFactors(new AsyncCallback<ArrayList<RiskFactor>>() {

			@Override
			public void onFailure(Throwable caught) {
				//
				logger.log(Level.INFO, "FAIL: fetchRiskFactors .Inside Audit AuditAreaspresenter");
				if (caught instanceof TimeOutException) {
					History.newItem("login");
				} else {
					System.out.println("FAIL: fetchRiskFactors .Inside AuditAreaspresenter");
					Window.alert("FAIL: fetchRiskFactors");// After FAIL ...
					// write RPC Name NOT
					// Method Name..
				}
			}

			@Override
			public void onSuccess(ArrayList<RiskFactor> risks) {
				riskFactors.clear();
				for (int i = 0; i < risks.size(); i++) {
					riskFactors.add(risks.get(i));
				}
				fetchStrategic(riskAssesmentView, tab);
			}
		});
	}

	public void fetchStrategic(final RiskAssesmentView riskAssesmentView, final int tab) {
		HashMap<String, String> hm = new HashMap<String, String>();
		final LoadingPopup loadingPopup = new LoadingPopup();
		loadingPopup.display();
		hm.put("tab", tab + "");
		hm.put("phase", "2");
		rpcService.fetchRiskAssesment(hm, new AsyncCallback<ArrayList<RiskAssesmentDTO>>() {

			@Override
			public void onFailure(Throwable caught) {
				loadingPopup.remove();
				logger.log(Level.INFO, "FAIL: fetchRiskAssesment .Inside Audit AuditAreaspresenter");
				if (caught instanceof TimeOutException) {
					History.newItem("login");
				} else {
					System.out.println("FAIL: fetchRiskAssesment .Inside AuditAreaspresenter");
					Window.alert("FAIL: fetchRiskAssesment");// After FAIL ...
					// write RPC Name
					// NOT Method
					// Name..
				}

				Window.alert("Fetch RiskAssesment  strategic failed");
			}

			@Override
			public void onSuccess(final ArrayList<RiskAssesmentDTO> riskAssesmentDTOs) {
				// loadingPopup.remove();
				previousStrategicsEntity = riskAssesmentDTOs;

				for (int index = 0; index < riskAssesmentDTOs.size(); index++) {
					final DataSetter dataSetter = new DataSetter();
					dataSetter.setComment(riskAssesmentDTOs.get(index).getStrategic().getComments());

					final RiskAssesmentStrategicView riskAssesmentStrategicView = new RiskAssesmentStrategicView();
					setButtonsVisibility(riskAssesmentDTOs, index, riskAssesmentStrategicView);
					// if(riskAssesmentDTOs.get(index).getStrategic().getPhase()!=
					// 2 ||
					// riskAssesmentDTOs.get(index).getStrategic().getLoggedInUser()!=
					// riskAssesmentDTOs.get(index).getStrategic().getAssignedTo().getEmployeeId()){
					// disablePanel(riskAssesmentStrategicView);
					// }
					riskAssesmentStrategicView.setStrategicId(riskAssesmentDTOs.get(index).getStrategic().getId());
					riskAssesmentStrategicView.setIndex(index);
					riskAssesmentStrategicView
							.setStrategicObjective(riskAssesmentDTOs.get(index).getStrategic().getStrategicObjective());

					if (riskAssesmentDTOs.get(index).getStrategic().getStatus().equals("amend")) {
						riskAssesmentStrategicView.getComments().addStyleName("point");
						// riskAssesmentStrategicView.getComments().removeStyleName("white");
						riskAssesmentStrategicView.getComments().setVisible(true);
					} else {
						riskAssesmentStrategicView.getComments().addStyleName("white");
						riskAssesmentStrategicView.getComments().setVisible(false);

					}
					riskAssesmentStrategicView.getComments().addClickHandler(new ClickHandler() {
						@Override
						public void onClick(ClickEvent event) {
							PopupViewGXT feedbackpopup = new PopupViewGXT(new HTML(dataSetter.getComment()),
									"Feedback");
						}
					});

					riskAssesmentStrategicView.getRatingComment()
							.setText(riskAssesmentDTOs.get(index).getStrategic().getRatingComments());
					if (!riskAssesmentStrategicView.getRatingComment().getText().isEmpty()) {
						riskAssesmentStrategicView.getPanelRatingComment().setVisible(true);
					}
					// riskAssesmentStrategicView.getComments()
					// .setTitle(riskAssesmentDTOs.get(index).getStrategic().getComments());

					for (int k = 0; k < riskAssesmentStrategicView.getRating().getItemCount(); k++) {
						// commented by moqeet as rating value is dependent
						if (riskAssesmentDTOs.get(index).getStrategic().getRating()
								.equals(riskAssesmentStrategicView.getRating().getValue(k))) {
							riskAssesmentStrategicView.getRating().setSelectedIndex(k);
						}
						riskAssesmentStrategicView.getRatingComment()
								.setText(riskAssesmentDTOs.get(index).getStrategic().getRatingComments());
						// new work

						for (int l = 0; l < riskAssesmentStrategicView.getListBoxUserOption().getItemCount(); l++) {
							if (riskAssesmentDTOs.get(index).getStrategic().getUserDefinedRating()
									.equals(riskAssesmentStrategicView.getListBoxUserOption().getValue(l))) {
								riskAssesmentStrategicView.getListBoxUserOption().setSelectedIndex(l);
							}
						}
					}

					TreeItem treeItem = new TreeItem();
					treeItem.setHTML(riskAssesmentStrategicView.getStrategicObjective());
					treeItem.getElement().getStyle().setWhiteSpace(WhiteSpace.NORMAL);
					Tree treeMain = new Tree();
					treeMain.addItem(treeItem);
					//// Change
					HorizontalPanel hpnlTree = new HorizontalPanel();
					hpnlTree.setWidth("100%");
					hpnlTree.add(riskAssesmentStrategicView.getSubmitted());
					hpnlTree.add(riskAssesmentStrategicView.getLblImg());
					hpnlTree.add(treeMain);

					////
					strategicPanel.add(hpnlTree);
					// strategicPanel.add(treeMain);
					/////
					treeMain.addStyleName("form-row");
					treeMain.addStyleName("statusRowStrategic");
					/////
					treeItem.addItem(riskAssesmentStrategicView);

					final ArrayList<RiskFactorsView> riskFactorsUpdated = new ArrayList<RiskFactorsView>();
					riskAssesmentStrategicView.getRiskFactors().add(new RiskFactorHeadingView());
					for (int j = 0; j < riskFactors.size(); j++) {
						RiskFactorsView riskFactorsView = new RiskFactorsView();
						if (riskAssesmentDTOs.get(index).getStrategic().getPhase() != 2
								|| riskAssesmentDTOs.get(index).getStrategic().getLoggedInUser() != riskAssesmentDTOs
										.get(index).getStrategic().getAssignedTo().getEmployeeId()) {
							disablePanel(riskAssesmentStrategicView, riskFactorsView,
									riskAssesmentDTOs.get(index).getStrategic());
						} else {
							enablePanel(riskAssesmentStrategicView, riskFactorsView);
						}

						riskFactorsView.setRiskFactorId(riskFactors.get(j).getRiskId());
						riskFactorsView.getRiskFactor().setText(riskFactors.get(j).getRiskName());

						try {
							if (riskAssesmentDTOs.get(index).getStrategicRisks().size() > 0) {
								riskFactorsView.getComments()
										.setText(riskAssesmentDTOs.get(index).getStrategicRisks().get(j).getComments());

								for (int k = 0; k < riskFactorsView.getRating().getItemCount(); k++) {
									if (riskAssesmentDTOs.get(index).getStrategicRisks().get(j).getRating()
											.equals(riskFactorsView.getRating().getValue(k))) {
										riskFactorsView.getRating().setSelectedIndex(k);
									}
								}
								// CHANGE 2018
								for (int k = 0; k < riskFactorsView.getImpact().getItemCount(); k++) {
									if (riskAssesmentDTOs.get(index).getStrategicRisks().get(j).getImpact()
											.equals(riskFactorsView.getImpact().getValue(k))) {
										riskFactorsView.getImpact().setSelectedIndex(k);
									}
								}

								for (int k = 0; k < riskFactorsView.getRating().getItemCount(); k++) {
									if (riskAssesmentDTOs.get(index).getStrategicRisks().get(j).getProbabality()
											.equals(riskFactorsView.getProbabality().getValue(k))) {
										riskFactorsView.getProbabality().setSelectedIndex(k);

										// for circle
										if (riskAssesmentDTOs.get(index).getStrategicRisks().get(j).getProbabality()
												.equals("3")) {
											riskFactorsView.getRiskRating().setVisible(true);
											riskFactorsView.getRiskRating().setUrl("redcircle.png");
										} else if (riskAssesmentDTOs.get(index).getStrategicRisks().get(j)
												.getProbabality().equals("2")) {
											riskFactorsView.getRiskRating().setVisible(true);
											riskFactorsView.getRiskRating().setUrl("yellowcircle.png");
										} else if (riskAssesmentDTOs.get(index).getStrategicRisks().get(j)
												.getProbabality().equals("1")) {
											riskFactorsView.getRiskRating().setVisible(true);
											riskFactorsView.getRiskRating().setUrl("greencircle.png");
										}
									}
								}
								// END
							}
						} catch (Exception ex) {
							GWT.log("Error at line 296 RiskAssesmentStrategicViewData : " + ex);
						}

						riskAssesmentStrategicView.getRiskFactors().add(riskFactorsView);
						riskFactorsUpdated.add(riskFactorsView);
						riskFactorsView.overAllRatingHander(riskFactorsUpdated, riskAssesmentStrategicView.getRating());

						//// TEST CODE
						// riskFactorsUpdated.get(j).getRating().addChangeHandler(new
						// ChangeHandler() {
						// @Override
						// public void onChange(ChangeEvent event) {
						// // return value = call Method (value , high,
						// // low, medium) calculateOverallRating
						// Window.alert(riskFactorsUpdated.size() + "");
						// String listOverAllRatingValue = setOverAllRating();
						// for (int i = 0; i <
						// riskAssesmentStrategicView.getRating().getVisibleItemCount();
						// i++) {
						// if (listOverAllRatingValue
						// .equals(riskAssesmentStrategicView.getRating().getValue(i)))
						// {
						// riskAssesmentStrategicView.getRating().setSelectedIndex(i);
						// // break;
						// }
						//
						// }
						//
						// }
						// });
					}
					selectedTab = tab;
					setHandlers(riskAssesmentView, riskAssesmentDTOs, riskAssesmentStrategicView, riskFactorsUpdated);
					updatedStrategics.add(riskAssesmentStrategicView);
				}
				loadingPopup.remove();
			}
		});
	}

	private void setHandlers(final RiskAssesmentView riskAssesmentView,
			final ArrayList<RiskAssesmentDTO> riskAssesmentDTOs,
			final RiskAssesmentStrategicView riskAssesmentStrategicView,
			final ArrayList<RiskFactorsView> riskFactorsUpdated) {

		riskAssesmentStrategicView.getBtnApprove().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				saveAssesment(riskAssesmentView, riskAssesmentDTOs, riskAssesmentStrategicView, riskFactorsUpdated,
						"approve", riskAssesmentStrategicView.getBtnApprove());
			}
		});

		riskAssesmentStrategicView.getBtnAmend().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				final AmendmentPopup amendmentPopup = new AmendmentPopup();
				amendmentPopup.popupAmendment();
				amendmentPopup.getBtnSubmit().addClickHandler(new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						riskAssesmentStrategicView.setComment(amendmentPopup.getComments().getText());
						saveAssesment(riskAssesmentView, riskAssesmentDTOs, riskAssesmentStrategicView,
								riskFactorsUpdated, "amend", amendmentPopup.getBtnSubmit());
						amendmentPopup.getPopupComments().removeFromParent();
					}
				});

				// popupAmendment(vpnlStrategic, hpnlButtonInitiator,
				// hpnlButtonsApprovar, btnAdd, auditUniverseStrategicView);
			}

		});

		riskAssesmentStrategicView.getBtnDecline().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				boolean confirmed = Window.confirm(
						"Are you sure you want to delete : " + riskAssesmentStrategicView.getStrategicObjective());
				if (confirmed) {
					declineStrategic(riskAssesmentStrategicView.getStrategicId(), riskAssesmentView,
							riskAssesmentStrategicView.getBtnDecline());
				}
			}

		});

		riskAssesmentStrategicView.getBtnDeclineInitiator().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				boolean confirmed = Window.confirm(
						"Are you sure you want to delete : " + riskAssesmentStrategicView.getStrategicObjective());
				if (confirmed) {
					declineStrategic(riskAssesmentStrategicView.getStrategicId(), riskAssesmentView,
							riskAssesmentStrategicView.getBtnDecline());
				}
			}

		});

		riskAssesmentStrategicView.getBtnSave().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				saveAssesment(riskAssesmentView, riskAssesmentDTOs, riskAssesmentStrategicView, riskFactorsUpdated,
						"save", riskAssesmentStrategicView.getBtnSave());
			}

		});

		riskAssesmentStrategicView.getBtnSubmit().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				saveAssesment(riskAssesmentView, riskAssesmentDTOs, riskAssesmentStrategicView, riskFactorsUpdated,
						"submit", riskAssesmentStrategicView.getBtnSubmit());
			}
		});
	}

	public void declineStrategic(int strategicId, final RiskAssesmentView riskAssesmentView, final Button button) {

		button.setEnabled(false);
		rpcService.declineStrategic(strategicId, new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable caught) {

				logger.log(Level.INFO, "FAIL: declineStrategic .Inside Audit AuditAreaspresenter");
				if (caught instanceof TimeOutException) {
					History.newItem("login");
				} else {
					System.out.println("FAIL: declineStrategic .Inside AuditAreaspresenter");
					Window.alert("FAIL: declineStrategic");// After FAIL ...
					// write RPC Name NOT
					// Method Name..
				}

				button.setEnabled(true);
				Window.alert("decline strategic failed");
			}

			@Override
			public void onSuccess(String result) {
				button.setEnabled(true);
				// vpnlStrategic.clear();
				final DecoratedPopupPanel popup = new DecoratedPopupPanel();
				popup.setWidget(new Label("strategic declined ! "));
				popup.setPopupPosition(350, 350);
				popup.show();
				Timer time = new Timer() {
					public void run() {
						popup.removeFromParent();
					}

				};// timer for showing the popup of "update"
				time.schedule(1500);
				riskAssesmentView.auditUniverseIdentificationTabs();

			}
		});
	}

	private void saveAssesment(final RiskAssesmentView riskAssesmentView,
			final ArrayList<RiskAssesmentDTO> riskAssesmentDTOs,
			final RiskAssesmentStrategicView riskAssesmentStrategicView,
			final ArrayList<RiskFactorsView> riskFactorsUpdated, String todo, Button button) {
		ArrayList<StrategicRisk> strategicRisks = new ArrayList<StrategicRisk>();

		// riskAssesmentDTOs.get(riskAssesmentStrategicView.getIndex()).getStrategic().setPhase("RiskAssesment1");
		riskAssesmentDTOs.get(riskAssesmentStrategicView.getIndex()).getStrategic().setPhase(2);
		riskAssesmentDTOs.get(riskAssesmentStrategicView.getIndex()).getStrategic().setNextPhase(3);

		// riskAssesmentDTOs.get(riskAssesmentStrategicView.getIndex()).getStrategic().setNextPhase("Consolidation");
		riskAssesmentDTOs.get(riskAssesmentStrategicView.getIndex()).getStrategic().setRating(riskAssesmentStrategicView
				.getRating().getValue(riskAssesmentStrategicView.getRating().getSelectedIndex()));

		// added by moqeet
		riskAssesmentDTOs.get(riskAssesmentStrategicView.getIndex()).getStrategic()
				.setRatingComments(riskAssesmentStrategicView.getRatingComment().getText());
		// new work

		riskAssesmentDTOs.get(riskAssesmentStrategicView.getIndex()).getStrategic()
				.setUserDefinedRating(riskAssesmentStrategicView.getListBoxUserOption()
						.getValue(riskAssesmentStrategicView.getListBoxUserOption().getSelectedIndex()));

		riskAssesmentDTOs.get(riskAssesmentStrategicView.getIndex()).getStrategic()
				.setComments(riskAssesmentStrategicView.getComment());

		for (int i = 0; i < riskFactorsUpdated.size(); i++) {
			StrategicRisk strategicRisk = new StrategicRisk();
			strategicRisk.setStrategicId(riskAssesmentDTOs.get(riskAssesmentStrategicView.getIndex()).getStrategic());
			RiskFactor riskFactor = new RiskFactor();
			riskFactor.setRiskId(riskFactorsUpdated.get(i).getRiskFactorId());
			riskFactor.setRiskName(riskFactorsUpdated.get(i).getRiskFactor().getText());

			strategicRisk.setRiskFactorId(riskFactor);
			strategicRisk.setRating(riskFactorsUpdated.get(i).getRating()
					.getValue(riskFactorsUpdated.get(i).getRating().getSelectedIndex()));
			strategicRisk.setImpact(riskFactorsUpdated.get(i).getImpact()
					.getValue(riskFactorsUpdated.get(i).getImpact().getSelectedIndex()));
			strategicRisk.setProbabality(riskFactorsUpdated.get(i).getProbabality()
					.getValue(riskFactorsUpdated.get(i).getProbabality().getSelectedIndex()));
			strategicRisk.setComments(riskFactorsUpdated.get(i).getComments().getText());
			strategicRisks.add(strategicRisk);

		}

		saveRiskAssesment(strategicRisks, riskAssesmentView, todo, button);
	}

	public void disablePanel(RiskAssesmentStrategicView riskAssesmentStrategicView, RiskFactorsView riskFactorsView,
			Strategic strategic) {
		riskAssesmentStrategicView.getHpnlButtonsApprovar().setVisible(false);
		riskAssesmentStrategicView.getHpnlButtonInitiator().setVisible(false);
		riskAssesmentStrategicView.getRating().setEnabled(false);
		// riskAssesmentStrategicView.getRating().addStyleName("listboxDisabled");
		// new work
		riskAssesmentStrategicView.getListBoxUserOption().setEnabled(false);
		riskAssesmentStrategicView.getRatingComment().setEnabled(false);

		riskFactorsView.getImpact().setEnabled(false);
		riskFactorsView.getImpact().addStyleName("listboxDisabledRating");

		riskFactorsView.getProbabality().setEnabled(false);
		riskFactorsView.getProbabality().addStyleName("listboxDisabledRating");
		riskFactorsView.getRating().addStyleName("listboxDisabledRating");
		riskFactorsView.getComments().setEnabled(false);
		riskAssesmentStrategicView.getSubmitted().setVisible(true);
		riskAssesmentStrategicView.getLblImg().setVisible(false);

		riskAssesmentStrategicView.getSubmitted()
				.setTitle(strategic.getStatus() + ": In " + PhaseNames.getPhaseNames(strategic.getPhase()));

	}

	public void enablePanel(RiskAssesmentStrategicView riskAssesmentStrategicView, RiskFactorsView riskFactorsView) {
		riskFactorsView.getImpact().setEnabled(true);
		riskFactorsView.getImpact().addStyleName("leftPadding");

		riskFactorsView.getProbabality().setEnabled(true);
		riskFactorsView.getProbabality().addStyleName("leftPadding");
		riskFactorsView.getRating().addStyleName("leftPadding listboxDisabledRating");
		riskFactorsView.getComments().setEnabled(true);
		riskAssesmentStrategicView.getRating().setEnabled(true);
		// new work
		riskAssesmentStrategicView.getListBoxUserOption().setEnabled(true);
		// if
		// (riskAssesmentStrategicView.getListBoxUserOption().getSelectedValue()
		// != riskAssesmentStrategicView
		// .getRating().getSelectedValue()) {
		// riskAssesmentStrategicView.getPanelRatingComment().setVisible(true);
		// }
		riskAssesmentStrategicView.getSubmitted().setVisible(false);
		riskAssesmentStrategicView.getLblImg().setVisible(true);

	}

	private void setButtonsVisibility(final ArrayList<RiskAssesmentDTO> riskAssesmentDTOs, int i,
			RiskAssesmentStrategicView riskAssesmentStrategicView) {
		if (riskAssesmentDTOs.get(i).getStrategic().getStatus().equals("submitted")) {
			riskAssesmentStrategicView.getHpnlButtonsApprovar().setVisible(true);
			riskAssesmentStrategicView.getHpnlButtonInitiator().setVisible(false);
			// if (!riskAssesmentStrategicView.getRating().getSelectedValue()
			// .equals(riskAssesmentStrategicView.getListBoxUserOption().getSelectedValue()))
			// {
			// riskAssesmentStrategicView.getPanelRatingComment().setVisible(true);
			// }

		} else if (riskAssesmentDTOs.get(i).getStrategic().getStatus().equals("amend")) {
			riskAssesmentStrategicView.getBtnDeclineInitiator().setVisible(false);
			riskAssesmentStrategicView.getHpnlButtonsApprovar().setVisible(false);
			riskAssesmentStrategicView.getHpnlButtonInitiator().setVisible(true);
			if (!riskAssesmentStrategicView.getRating().getSelectedValue()
					.equals(riskAssesmentStrategicView.getListBoxUserOption().getSelectedValue())) {
				riskAssesmentStrategicView.getPanelRatingComment().setVisible(true);
			}
		} else {
			riskAssesmentStrategicView.getBtnDeclineInitiator().setVisible(true);
			riskAssesmentStrategicView.getHpnlButtonsApprovar().setVisible(false);
			riskAssesmentStrategicView.getHpnlButtonInitiator().setVisible(true);
			if (!riskAssesmentStrategicView.getRating().getSelectedValue()
					.equals(riskAssesmentStrategicView.getListBoxUserOption().getSelectedValue())) {
				riskAssesmentStrategicView.getPanelRatingComment().setVisible(true);
			}
		}
	}

	public void saveRiskAssesment(ArrayList<StrategicRisk> strategicRisks, final RiskAssesmentView riskAssesmentView,
			String todo, final Button button) {

		button.setEnabled(false);
		actionPerformed = todo;
		HashMap<String, String> hm = new HashMap<String, String>();
		if (todo.equalsIgnoreCase("approve")) {
			todo = "submit";
		}
		hm.put("todo", todo);
		hm.put("tab", selectedTab + "");
		rpcService.saveRiskAssesment(hm, strategicRisks, new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable caught) {

				logger.log(Level.INFO, "FAIL: saveRiskAssesment .Inside Audit AuditAreaspresenter");
				if (caught instanceof TimeOutException) {
					History.newItem("login");
				} else {
					System.out.println("FAIL: saveRiskAssesment .Inside AuditAreaspresenter");
					Window.alert("FAIL: saveRiskAssesment");// After FAIL ...
					// write RPC Name
					// NOT Method Name..
				}

				button.setEnabled(true);
				Window.alert("saving risk assesment failed");
			}

			@Override
			public void onSuccess(String result) {
				button.setEnabled(true);
				final DecoratedPopupPanel popup = new DecoratedPopupPanel();
				if (actionPerformed.equalsIgnoreCase("save")) {
					popup.setWidget(new Label("Risk Assesment Saved  "));
				} else if (actionPerformed.equalsIgnoreCase("amend")) {
					popup.setWidget(new Label("Feedback Sumitted  "));
				} else if (actionPerformed.equalsIgnoreCase("approve")) {
					popup.setWidget(new Label("Risk Assesment Approved  "));
				} else {
					popup.setWidget(new Label("Risk Assesment Submitted "));
				}
				popup.setPopupPosition(350, 350);
				popup.show();
				Timer time = new Timer() {
					public void run() {
						popup.removeFromParent();
					}

				};// timer for showing the popup of "update"
				time.schedule(2000);
				riskAssesmentView.auditUniverseIdentificationTabs();

			}
		});

	}

	public void sendBackStrategic(Strategic strategicToSendBack, final RiskAssesmentView riskAssesmentView) {

		rpcService.sendBackStrategic(strategicToSendBack, new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable caught) {
				logger.log(Level.INFO, "FAIL: sendBackStrategic .Inside Audit AuditAreaspresenter");
				if (caught instanceof TimeOutException) {
					History.newItem("login");
				} else {
					System.out.println("FAIL: sendBackStrategic .Inside AuditAreaspresenter");
					Window.alert("FAIL: sendBackStrategic");// After FAIL ...
					// write RPC Name
					// NOT Method Name..
				}
			}

			@Override
			public void onSuccess(String result) {
				riskAssesmentView.auditUniverseIdentificationTabs();
			}
		});

	}

}
