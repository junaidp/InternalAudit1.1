package com.internalaudit.client.view.data;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.poi.ss.usermodel.Textbox;
import org.dom4j.tree.BackedList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.FontWeight;
import com.google.gwt.dom.client.Style.WhiteSpace;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratedPopupPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.internalaudit.client.InternalAuditService;
import com.internalaudit.client.InternalAuditServiceAsync;
import com.internalaudit.client.view.AddDegreeOfImportanceSettingsView;
import com.internalaudit.client.view.StrategicDegreeImportanceView;
import com.internalaudit.client.view.AmendmentPopup;
import com.internalaudit.client.view.DegreeOfImportanceHeading;
import com.internalaudit.client.view.DisplayAlert;
import com.internalaudit.client.view.LoadingPopup;
import com.internalaudit.client.view.PhaseNames;
import com.internalaudit.client.view.PopupViewGXT;
import com.internalaudit.client.view.RiskAssesmentStrategicView;
import com.internalaudit.client.view.RiskAssesmentView;
import com.internalaudit.client.view.RiskFactorHeadingView;
import com.internalaudit.client.view.RiskFactorsDataView;
import com.internalaudit.client.view.RiskFactorsView;
import com.internalaudit.client.view.AuditEngagement.LabelBold;
import com.internalaudit.shared.DegreeImportance;
import com.internalaudit.shared.RiskAssesmentDTO;
import com.internalaudit.shared.RiskFactor;
import com.internalaudit.shared.Strategic;
import com.internalaudit.shared.StrategicDegreeImportance;
import com.internalaudit.shared.StrategicRiskFactor;
import com.internalaudit.shared.TimeOutException;
import com.sencha.gxt.chart.client.draw.engine.SVG.TextBBox;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

import javafx.beans.property.ListProperty;
import javafx.collections.ArrayChangeListener;
import javafx.collections.ObservableFloatArray;

public class RiskAssesmentStrategicViewData {

	private InternalAuditServiceAsync rpcService = GWT.create(InternalAuditService.class);
	private VerticalPanel strategicPanel;
//	private ArrayList<DegreeImportance> arrayListDegreeImportance = new ArrayList<DegreeImportance>();
//	private ArrayList<RiskFactor> arrayListRiskFactors = new ArrayList<RiskFactor>();
	private ArrayList<RiskAssesmentStrategicView> arrayListUpdatedStrategics = new ArrayList<RiskAssesmentStrategicView>();
	private ArrayList<RiskAssesmentDTO> arrayListPreviousStrategicsEntity;
	private int selectedTab = 0;
	private Logger logger = Logger.getLogger("RiskAssesmentStrategicViewData");
	private int companyID = 0;
	private float resultImpact;
	private float resultRating;
	private String actionPerformed;
	private int indexer = 0;
//	private ArrayList<StrategicRisk> arrayListAddMoreStrategic;

	public void setData(VerticalPanel strategicPanel, RiskAssesmentView riskAssesmentView, int companyID) {

		this.strategicPanel = strategicPanel;
		this.companyID = companyID;
		fetchDegreeImportanceRiskFactors(riskAssesmentView);
		setHandlers();
	}

	public void setHandlers() {

		Button saveRiskAssesment = (Button) strategicPanel.getWidget(0);
		saveRiskAssesment.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent arg0) {
				for (int i = 0; i < arrayListUpdatedStrategics.size(); i++) {
//					arrayListPreviousStrategicsEntity.get(i).getStrategic().setRating(arrayListUpdatedStrategics.get(i).getOverallRating()
//							.getValue(arrayListUpdatedStrategics.get(i).getOverallRating().getSelectedIndex()));
					// previousStrategicsEntity.get(i).setStrategicObjective(updatedStrategics.get(i).getStrategicObjective().getText());

					// new work
//					arrayListPreviousStrategicsEntity.get(i).getStrategic()
//							.setUserDefinedRating(arrayListUpdatedStrategics.get(i).getListBoxUserOption()
//									.getValue(arrayListUpdatedStrategics.get(i).getListBoxUserOption().getSelectedIndex()));
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

	public void fetchDegreeImportanceRiskFactors(final RiskAssesmentView riskAssesmentView) {

		rpcService.fetchRiskFactors(companyID, new AsyncCallback<ArrayList<RiskFactor>>() {

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
//				arrayListRiskFactors.clear();
//				arrayListRiskFactors = risks;
				fetchStrategic(riskAssesmentView, selectedTab);
			}
		});
//		
//		rpcService.fetchDegreeImportance(companyID, new AsyncCallback<ArrayList<DegreeImportance>>() {
//
//			@Override
//			public void onFailure(Throwable arg0) {
//				new DisplayAlert("Unabe to fetch Degree Importance");
//			}
//
//			@Override
//			public void onSuccess(ArrayList<DegreeImportance> result) {
//				arrayListDegreeImportance.clear();
//				arrayListDegreeImportance = result;
//			}
//		});
//		
	}

	public void fetchStrategic(final RiskAssesmentView riskAssesmentView, final int tab) {
		HashMap<String, String> hm = new HashMap<String, String>();
		final LoadingPopup loadingPopup = new LoadingPopup();
		loadingPopup.display();
		hm.put("tab", "All");
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
				arrayListPreviousStrategicsEntity = riskAssesmentDTOs;

				for ( int index = 0; index < riskAssesmentDTOs.size(); index++) {
					
					final DataSetter dataSetter = new DataSetter();
					dataSetter.setId(index);
					dataSetter.setComment(riskAssesmentDTOs.get(index).getStrategic().getComments());
					final ArrayList<StrategicDegreeImportance> arrayListSaveDegreeImportance = new ArrayList<StrategicDegreeImportance>();
					final ArrayList<StrategicRiskFactor> arrayListSaveRiskFactors = new ArrayList<StrategicRiskFactor>();
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
//					for (int k = 0; k < riskAssesmentStrategicView.getOverallRating().getItemCount(); k++) {
//						if (riskAssesmentDTOs.get(index).getStrategic().getRating()
//								.equals(riskAssesmentStrategicView.getOverallRating().getValue(k))) {
//							riskAssesmentStrategicView.getOverallRating().setSelectedIndex(k);
//						}
//						riskAssesmentStrategicView.getRatingComment()
//								.setText(riskAssesmentDTOs.get(index).getStrategic().getRatingComments());
//						// new work
//
//						for (int l = 0; l < riskAssesmentStrategicView.getListBoxUserOption().getItemCount(); l++) {
//							if (riskAssesmentDTOs.get(index).getStrategic().getUserDefinedRating()
//									.equals(riskAssesmentStrategicView.getListBoxUserOption().getValue(l))) {
//								riskAssesmentStrategicView.getListBoxUserOption().setSelectedIndex(l);
//							}
//						}
//					}
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
//					final ArrayList<StrategicRisk> arrayListStrategicRisks = new ArrayList<StrategicRisk>();
//					arrayListAddMoreStrategic = new ArrayList<StrategicRisk>();
					//list that have impact ratings
					final ArrayList<TextBox> arrayWeightage = new ArrayList<TextBox>();
					final ArrayList<ListBox> listBoxesImpact = new ArrayList<ListBox>();
					final ArrayList<StrategicDegreeImportanceView> listDegreeImportanceView = new ArrayList<StrategicDegreeImportanceView>();
					final ArrayList<RiskFactorsDataView> listRiskFactor = new ArrayList<RiskFactorsDataView>();
					//Degree Importance view
					
					DegreeOfImportanceHeading viewDegreeOfImportanceHeading = new DegreeOfImportanceHeading();
					viewDegreeOfImportanceHeading.visibleHpnlRiskAssesment();
					riskAssesmentStrategicView.getRiskFactors().add(viewDegreeOfImportanceHeading);
					
					final VerticalPanel vpnlDegreeImporatnce = new VerticalPanel();
					riskAssesmentStrategicView.getRiskFactors().add(vpnlDegreeImporatnce);
					final VerticalPanel vpnlOverallImpactRatings = new VerticalPanel();
					riskAssesmentStrategicView.getRiskFactors().add(vpnlOverallImpactRatings);
					final VerticalPanel vpnlHeaderRiskFactor = new VerticalPanel();
					riskAssesmentStrategicView.getRiskFactors().add(vpnlHeaderRiskFactor);
					final VerticalPanel vpnlRiskFactor = new VerticalPanel();
					riskAssesmentStrategicView.getRiskFactors().add(vpnlRiskFactor);
									
					for(int k=0; k<riskAssesmentDTOs.get(index).getStrategicRisks().size(); k++) {
						StrategicDegreeImportance strategicDegreeImportance = riskAssesmentDTOs.get(index).getStrategicRisks().get(k);
//						if(degreeImportance.getChecked() == 0 && degreeImportance.getDegreeImportanceID() != 0) {
						if(riskAssesmentDTOs.get(index).getStrategicRisks().get(k).getCheck() == 1) {
							StrategicDegreeImportanceView strategicDegreeImportanceView = new StrategicDegreeImportanceView();
							strategicDegreeImportanceView.getAddDegreeOfImportanceSettingsView().invisibleFiels();
							strategicDegreeImportanceView.getAddDegreeOfImportanceSettingsView().getTxtName().setText(strategicDegreeImportance.getDegreeImportanceID().getDegreeImportanceName());
							strategicDegreeImportanceView.getTxtAreaComment().setText(strategicDegreeImportance.getComments());
							strategicDegreeImportanceView.getTxtWeightage().setText(String.valueOf(strategicDegreeImportance.getWeightage()));
							for(int i=0; i<strategicDegreeImportanceView.getListBoxRatings().getItemCount(); i++) {
								if(strategicDegreeImportance.getRatings() == Integer.parseInt(strategicDegreeImportanceView.getListBoxRatings().getValue(i)))
									strategicDegreeImportanceView.getListBoxRatings().setSelectedIndex(i);
								}
							vpnlDegreeImporatnce.add(strategicDegreeImportanceView);	
							strategicDegreeImportanceView.setDegreeImportance(strategicDegreeImportance);
							arrayListSaveDegreeImportance.add(strategicDegreeImportance);
							arrayWeightage.add(strategicDegreeImportanceView.getTxtWeightage());
							listBoxesImpact.add(strategicDegreeImportanceView.getListBoxRatings());
							listDegreeImportanceView.add(strategicDegreeImportanceView);
							deleteStrategicDegreeImportance(strategicDegreeImportanceView, vpnlDegreeImporatnce, strategicDegreeImportance);
						}
							
//						RiskFactor riskFactor = new RiskFactor(); 
////							RiskFactor riskFactor = riskAssesmentDTOs.get(index).getStrategicRisks().get(k).getRiskFactorId(); 
//							if(riskAssesmentDTOs.get(index).getStrategicRisks().get(k).getCheck() == 0) {
//								RiskFactorsDataView riskFactorsView = new RiskFactorsDataView();
//								riskFactorsView.getRiskFactorsSettingsView().getTxtRiskFactors().setText(riskFactor.getRiskName());
//								riskFactorsView.getRiskFactorsSettingsView().getTxtRiskDescription().setText(riskFactor.getRiskDescription());
////								for(int j=0; j<riskFactorsView.getListBoxProbability().getItemCount(); j++) {
////									if(riskFactor.getProbability() == Integer.parseInt(riskFactorsView.getListBoxProbability().getValue(j)))
////										riskFactorsView.getListBoxProbability().setSelectedIndex(j);
////								}
//								riskFactorsView.updateProbilityImage();
//								vpnlRiskFactor.add(riskFactorsView);
//								riskFactorsView.getRiskFactorsSettingsView().invisibleIcons();
//								riskFactorsView.setRiskFactors(riskFactor, companyID);
////								arrayOverAllRatings.add(riskFactorsView.getListBoxProbability());
////								listImgProbability.add(riskFactorsView.getImgRiskRating());
//								arrayListSaveRiskFactors.add(riskFactor);
//								StrategicRisk strategicRisk = new StrategicRisk();
//								strategicRisk.setId(riskAssesmentDTOs.get(index).getStrategicRisks().get(k).getId());
//								strategicRisk.setStrategicId(riskAssesmentDTOs.get(index).getStrategic());
//								strategicRisk.setDegreeImportanceID(degreeImportance);
////								strategicRisk.setRiskFactorId(riskFactor);
//								arrayListStrategicRisks.add(strategicRisk);
//						}	
					}
					final VerticalPanel vpnlAddDegreeImportanceView = new VerticalPanel();
					vpnlDegreeImporatnce.add(vpnlAddDegreeImportanceView);
					//addIconClickHandler
					if (riskAssesmentDTOs.get(index).getStrategic().getPhase() == 2) 
						viewDegreeOfImportanceHeading.getImgAddDegree().setVisible(true);
					else
						viewDegreeOfImportanceHeading.getImgAddDegree().setVisible(false);
					viewDegreeOfImportanceHeading.getImgAddDegree().addClickHandler(new ClickHandler() {
						
						@Override
						public void onClick(ClickEvent arg0) {
								StrategicDegreeImportanceView strategicDegreeImportanceView = new StrategicDegreeImportanceView();
								strategicDegreeImportanceView.getAddDegreeOfImportanceSettingsView().invisibleFiels();
								deleteStrategicDegreeImportance(strategicDegreeImportanceView, vpnlAddDegreeImportanceView, null);
								vpnlAddDegreeImportanceView.add(strategicDegreeImportanceView);	
								strategicDegreeImportanceView.setListBoxDegreeImportance(riskAssesmentDTOs.get(dataSetter.getId()).getStrategicRisks());
								StrategicDegreeImportance strategicDegreeImportanceNew = new StrategicDegreeImportance();								
								strategicDegreeImportanceView.setDegreeImportance(strategicDegreeImportanceNew);
								arrayWeightage.add(strategicDegreeImportanceView.getTxtWeightage());
								listBoxesImpact.add(strategicDegreeImportanceView.getListBoxRatings());
								strategicDegreeImportanceNew.setCheck(1);
								arrayListSaveDegreeImportance.add(strategicDegreeImportanceNew);
//								StrategicRisk strategicRisk = new StrategicRisk();
//								strategicRisk.setDegreeImportanceID(strategicDegreeImportanceNew);
//								arrayListAddMoreStrategic.add(strategicDegreeImportanceNew);
								listDegreeImportanceView.add(strategicDegreeImportanceView);
						}				
					});	
					
					
					final ArrayList<ListBox> arrayOverAllRatings = new ArrayList<ListBox>();
					final ArrayList<Image> listImgProbability = new ArrayList<Image>();
					vpnlOverallImpactRatings.add(overallRatingLayout(arrayWeightage, listBoxesImpact, arrayOverAllRatings, listImgProbability, riskAssesmentStrategicView.getLblOverallRatings()));
					
					//RiskFactors view
					final ArrayList<RiskFactorsView> riskFactorsUpdated = new ArrayList<RiskFactorsView>();
					RiskFactorHeadingRiskAssement riskfactorsHeading = new RiskFactorHeadingRiskAssement();
					vpnlHeaderRiskFactor.add(riskfactorsHeading);
					for(int i=0; i<riskAssesmentDTOs.get(index).getArrayStrategicRiskFactor().size(); i++) {
						StrategicRiskFactor strategicRiskFactor = riskAssesmentDTOs.get(index).getArrayStrategicRiskFactor().get(i); 
						if(strategicRiskFactor.getCheck() == 1) {
							RiskFactorsDataView riskFactorsView = new RiskFactorsDataView();
							riskFactorsView.getRiskFactorsSettingsView().getTxtRiskFactors().setText(strategicRiskFactor.getRiskFactorID().getRiskName());
							riskFactorsView.getRiskFactorsSettingsView().getTxtRiskDescription().setText(strategicRiskFactor.getRiskFactorID().getRiskDescription());
							riskFactorsView.getRiskFactorsSettingsView().getTxtRiskDescription().setEnabled(false);
							for(int j=0; j<riskFactorsView.getListBoxProbability().getItemCount(); j++) {
								if(strategicRiskFactor.getProbability() == Integer.parseInt(riskFactorsView.getListBoxProbability().getValue(j)))
									riskFactorsView.getListBoxProbability().setSelectedIndex(j);
							}
							riskFactorsView.updateProbilityImage();
							riskAssesmentStrategicView.getRiskFactors().add(riskFactorsView);
							riskFactorsView.getRiskFactorsSettingsView().invisibleIcons();
							riskFactorsView.setRiskFactors(strategicRiskFactor);
							arrayOverAllRatings.add(riskFactorsView.getListBoxProbability());
							listImgProbability.add(riskFactorsView.getImgRiskRating());							
//							StrategicRisk strategicRisk = new StrategicRisk();
//							strategicRisk.setId(riskAssesmentDTOs.get(index).getStrategicRisks().get(i).getId());
//							strategicRisk.setStrategicId(riskAssesmentDTOs.get(index).getStrategic());
//							strategicRisk.setId(riskFactor.getId());
							arrayListSaveRiskFactors.add(strategicRiskFactor);
//							arrayListStrategicRisks.add(strategicRisk);
							vpnlRiskFactor.add(riskFactorsView);
							listRiskFactor.add(riskFactorsView);
							deleteStrategicRiskFactor(riskFactorsView, vpnlRiskFactor, strategicRiskFactor);
						}
					}
					
					final VerticalPanel vpnlAddRiskFactorView = new VerticalPanel();
					vpnlRiskFactor.add(vpnlAddRiskFactorView);
					if (riskAssesmentDTOs.get(index).getStrategic().getPhase() == 2) 
						riskfactorsHeading.getRiskFactorSettingsHeading().getImgAddRiskFactor().setVisible(true);
					else
						riskfactorsHeading.getRiskFactorSettingsHeading().getImgAddRiskFactor().setVisible(false);
					riskfactorsHeading.getRiskFactorSettingsHeading().getImgAddRiskFactor().addClickHandler(new ClickHandler() {
						
						@Override
						public void onClick(ClickEvent arg0) {
							RiskFactorsDataView riskFactorsView = new RiskFactorsDataView();
//							riskFactorsView.getRiskFactorsSettingsView().getTxtRiskDescription().setText(riskFactor.getRiskDescription());
							riskFactorsView.getRiskFactorsSettingsView().invisibleIcons();
							riskFactorsView.setLisBoxRiskFactors(riskAssesmentDTOs.get(dataSetter.getId()).getArrayStrategicRiskFactor() );
							StrategicRiskFactor strategicRiskFactor = new StrategicRiskFactor();
							riskFactorsView.setRiskFactors(strategicRiskFactor);
							strategicRiskFactor.setProbability(Integer.parseInt(riskFactorsView.getListBoxProbability().getSelectedValue()));
							vpnlAddRiskFactorView.add(riskFactorsView);							
							arrayOverAllRatings.add(riskFactorsView.getListBoxProbability());
							listImgProbability.add(riskFactorsView.getImgRiskRating());
							arrayListSaveRiskFactors.add(strategicRiskFactor);
//							StrategicRisk strategicRisk = new StrategicRisk();
//							strategicRisk.setRiskFactorId(riskFactor);
//							arrayListAddMoreStrategic.add(riskFactor);
							listRiskFactor.add(riskFactorsView);
							deleteStrategicRiskFactor(riskFactorsView, vpnlAddRiskFactorView, strategicRiskFactor);
						}					
					});
					calculateOverAllRisksRatings(arrayOverAllRatings, listImgProbability, riskAssesmentStrategicView.getLblOverallRatings());					
//					for (int j = 0; j < arrayistRiskFactors.size(); j++) {
//						RiskFactorsView riskFactorsView = new RiskFactorsView();
						if (riskAssesmentDTOs.get(index).getStrategic().getPhase() != 2
								|| riskAssesmentDTOs.get(index).getStrategic().getLoggedInUser() != riskAssesmentDTOs
										.get(index).getStrategic().getAssignedTo().getEmployeeId()) {
							disablePanel(riskAssesmentStrategicView, listDegreeImportanceView, listRiskFactor,
									riskAssesmentDTOs.get(index).getStrategic());
						} 
						else {
							enablePanel(riskAssesmentStrategicView, listDegreeImportanceView, listRiskFactor, riskAssesmentDTOs.get(index).getStrategic().getStatus());
						}
//
//						riskFactorsView.setRiskFactorId(arrayistRiskFactors.get(j).getRiskId());
//						riskFactorsView.getRiskFactor().setText(arrayistRiskFactors.get(j).getRiskName());
//
//						try {
//							if (riskAssesmentDTOs.get(index).getStrategicRisks().size() > 0) {
////								riskFactorsView.getDescription().setText(riskAssesmentDTOs.get(index).getStrategicRisks().get(j).getComments());
//								riskFactorsView.getDescription().setText(arrayistRiskFactors.get(j).getRiskDescription());
//
//								for (int k = 0; k < riskFactorsView.getRating().getItemCount(); k++) {
//									if (riskAssesmentDTOs.get(index).getStrategicRisks().get(j).getRating()
//											.equals(riskFactorsView.getRating().getValue(k))) {
//										riskFactorsView.getRating().setSelectedIndex(k);
//									}
//								}
//								// CHANGE 2018
//								for (int k = 0; k < riskFactorsView.getImpact().getItemCount(); k++) {
//									if (riskAssesmentDTOs.get(index).getStrategicRisks().get(j).getImpact()
//											.equals(riskFactorsView.getImpact().getValue(k))) {
//										riskFactorsView.getImpact().setSelectedIndex(k);
//									}
//								}
//
//								for (int k = 0; k < riskFactorsView.getRating().getItemCount(); k++) {
//									if (riskAssesmentDTOs.get(index).getStrategicRisks().get(j).getProbabality()
//											.equals(riskFactorsView.getProbabality().getValue(k))) {
//										riskFactorsView.getProbabality().setSelectedIndex(k);
//
//										// for circle
//										if (riskAssesmentDTOs.get(index).getStrategicRisks().get(j).getProbabality()
//												.equals("3")) {
//											riskFactorsView.getRiskRating().setVisible(true);
//											riskFactorsView.getRiskRating().setUrl("redcircle.png");
//										} else if (riskAssesmentDTOs.get(index).getStrategicRisks().get(j)
//												.getProbabality().equals("2")) {
//											riskFactorsView.getRiskRating().setVisible(true);
//											riskFactorsView.getRiskRating().setUrl("yellowcircle.png");
//										} else if (riskAssesmentDTOs.get(index).getStrategicRisks().get(j)
//												.getProbabality().equals("1")) {
//											riskFactorsView.getRiskRating().setVisible(true);
//											riskFactorsView.getRiskRating().setUrl("greencircle.png");
//										}
//									}
//								}
//								// END
//							}
//						} catch (Exception ex) {
//							GWT.log("Error at line 296 RiskAssesmentStrategicViewData : " + ex);
//						}
//
//						riskAssesmentStrategicView.getRiskFactors().add(riskFactorsView);
//						riskFactorsUpdated.add(riskFactorsView);
//						riskFactorsView.overAllRatingHander(riskFactorsUpdated,
//								riskAssesmentStrategicView.getOverallRating());
//
//						//// TEST CODE
//						// riskFactorsUpdated.get(j).getRating().addChangeHandler(new
//						// ChangeHandler() {
//						// @Override
//						// public void onChange(ChangeEvent event) {
//						// // return value = call Method (value , high,
//						// // low, medium) calculateOverallRating
//						// Window.alert(riskFactorsUpdated.size() + "");
//						// String listOverAllRatingValue = setOverAllRating();
//						// for (int i = 0; i <
//						// riskAssesmentStrategicView.getRating().getVisibleItemCount();
//						// i++) {
//						// if (listOverAllRatingValue
//						// .equals(riskAssesmentStrategicView.getRating().getValue(i)))
//						// {
//						// riskAssesmentStrategicView.getRating().setSelectedIndex(i);
//						// // break;
//						// }
//						//
//						// }
//						//
//						// }
//						// });
//					}
					selectedTab = tab;
//					arrayListStrategicRisks.addAll(setArrayListStrategicRisksToSave(arrayListAddMoreStrategic));
					setButtonsHandlers(riskAssesmentView, riskAssesmentDTOs, riskAssesmentStrategicView, riskFactorsUpdated, arrayListSaveDegreeImportance, arrayListSaveRiskFactors);
					arrayListUpdatedStrategics.add(riskAssesmentStrategicView);
				}
				loadingPopup.remove();
			}
		});
	}
	
	private void setButtonsHandlers(final RiskAssesmentView riskAssesmentView,	final ArrayList<RiskAssesmentDTO> riskAssesmentDTOs, final RiskAssesmentStrategicView riskAssesmentStrategicView,
			final ArrayList<RiskFactorsView> riskFactorsUpdated, final ArrayList<StrategicDegreeImportance> arrayListSaveDegreeImportance, final ArrayList<StrategicRiskFactor> arrayListSaveRiskFactors) {

		riskAssesmentStrategicView.getBtnApprove().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				saveAssesment(riskAssesmentView, riskAssesmentDTOs, riskAssesmentStrategicView, riskFactorsUpdated,
						"approve", riskAssesmentStrategicView.getBtnApprove(), arrayListSaveDegreeImportance, arrayListSaveRiskFactors);
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
						saveAssesment( riskAssesmentView, riskAssesmentDTOs, riskAssesmentStrategicView,
								riskFactorsUpdated, "amend", amendmentPopup.getBtnSubmit(), arrayListSaveDegreeImportance, arrayListSaveRiskFactors);
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
						"save", riskAssesmentStrategicView.getBtnSave(), arrayListSaveDegreeImportance, arrayListSaveRiskFactors);
			}

		});

		riskAssesmentStrategicView.getBtnSubmit().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				saveAssesment(riskAssesmentView, riskAssesmentDTOs, riskAssesmentStrategicView, riskFactorsUpdated,
						"submit", riskAssesmentStrategicView.getBtnSubmit(), arrayListSaveDegreeImportance, arrayListSaveRiskFactors);
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
			final ArrayList<RiskFactorsView> riskFactorsUpdated, String todo, Button button, ArrayList<StrategicDegreeImportance> arrayListSaveDegreeImportance, ArrayList<StrategicRiskFactor> arrayListSaveRiskFactors) {
//		ArrayList<StrategicRisk> strategicRisks = new ArrayList<StrategicRisk>();
//		ArrayList<StrategicRisk> strategicRisks = arrayListStrategicRisks;
		// riskAssesmentDTOs.get(riskAssesmentStrategicView.getIndex()).getStrategic().setPhase("RiskAssesment1");
		riskAssesmentDTOs.get(riskAssesmentStrategicView.getIndex()).getStrategic().setPhase(2);
		riskAssesmentDTOs.get(riskAssesmentStrategicView.getIndex()).getStrategic().setNextPhase(3);

		// riskAssesmentDTOs.get(riskAssesmentStrategicView.getIndex()).getStrategic().setNextPhase("Consolidation");
//		riskAssesmentDTOs.get(riskAssesmentStrategicView.getIndex()).getStrategic().setRating(riskAssesmentStrategicView
//				.getOverallRating().getValue(riskAssesmentStrategicView.getOverallRating().getSelectedIndex()));

		// added by moqeet
		riskAssesmentDTOs.get(riskAssesmentStrategicView.getIndex()).getStrategic()
				.setRatingComments(riskAssesmentStrategicView.getRatingComment().getText());
		// new work

//		riskAssesmentDTOs.get(riskAssesmentStrategicView.getIndex()).getStrategic()
//				.setUserDefinedRating(riskAssesmentStrategicView.getListBoxUserOption()
//						.getValue(riskAssesmentStrategicView.getListBoxUserOption().getSelectedIndex()));

		riskAssesmentDTOs.get(riskAssesmentStrategicView.getIndex()).getStrategic()
				.setComments(riskAssesmentStrategicView.getComment());
//		int sizeLoop = 0;
//		if(arrayListSaveDegreeImportance.size() > arrayListSaveRiskFactors.size())
//			sizeLoop = arrayListSaveDegreeImportance.size();
//		else
//			sizeLoop = arrayListSaveRiskFactors.size();
//		for (int i = 0; i < sizeLoop; i++) {
//			StrategicRisk strategicRisk = new StrategicRisk();
//			strategicRisk.setId(riskAssesmentStrategicView.getStrategicId());
//			strategicRisk.setStrategicId(riskAssesmentDTOs.get(riskAssesmentStrategicView.getIndex()).getStrategic());
////			RiskFactor riskFactor = new RiskFactor();
////			riskFactor.setRiskId(riskFactorsUpdated.get(i).getRiskFactorId());
////			riskFactor.setRiskName(riskFactorsUpdated.get(i).getRiskFactor().getText());
//
////			strategicRisk.setRiskFactorId(riskFactor);
////			strategicRisk.setRating(riskFactorsUpdated.get(i).getRating()
////					.getValue(riskFactorsUpdated.get(i).getRating().getSelectedIndex()));
////			strategicRisk.setImpact(riskFactorsUpdated.get(i).getImpact()
////					.getValue(riskFactorsUpdated.get(i).getImpact().getSelectedIndex()));
////			strategicRisk.setProbabality(riskFactorsUpdated.get(i).getProbabality()
////					.getValue(riskFactorsUpdated.get(i).getProbabality().getSelectedIndex()));
////			strategicRisk.setComments(riskFactorsUpdated.get(i).getDescription().getText());
//			strategicRisk.setRiskFactorId(arrayListSaveRiskFactors.get(i));
//			strategicRisk.setDegreeImportanceID(arrayListSaveDegreeImportance.get(i));
//			strategicRisks.add(strategicRisk);
//		}
		if(showErrorMessage(arrayListSaveDegreeImportance) == 100)
			saveRiskAssesment(arrayListSaveDegreeImportance, arrayListSaveRiskFactors, riskAssesmentView, todo, button);
		else
			new DisplayAlert("Sum of all weightage must equal to 100%");
	}

	public void disablePanel(RiskAssesmentStrategicView riskAssesmentStrategicView, ArrayList<StrategicDegreeImportanceView> listDegreeImportanceView, ArrayList<RiskFactorsDataView> listRiskFactor, Strategic strategic) {
		for(StrategicDegreeImportanceView degreeImportanceView : listDegreeImportanceView) {
			degreeImportanceView.enableFields(false, strategic.getStatus());
		}
		for(RiskFactorsDataView riskFactorView : listRiskFactor) {
			riskFactorView.enableFields(false, strategic.getStatus());
		}
		riskAssesmentStrategicView.getHpnlButtonsApprovar().setVisible(false);
		riskAssesmentStrategicView.getHpnlButtonInitiator().setVisible(false);
		riskAssesmentStrategicView.getRatingComment().setEnabled(false);
		riskAssesmentStrategicView.getSubmitted().setVisible(true);
		riskAssesmentStrategicView.getLblImg().setVisible(false);
		riskAssesmentStrategicView.getSubmitted()
				.setTitle(strategic.getStatus() + ": In " + PhaseNames.getPhaseNames(strategic.getPhase()));

	}
	
	private void deleteStrategicDegreeImportance(final StrategicDegreeImportanceView addDegreeOfImportanceView, final VerticalPanel vpnlAddDegreeImportanceView, final StrategicDegreeImportance strategicDegreeImportance) {
		addDegreeOfImportanceView.getAddDegreeOfImportanceSettingsView().getImgDelete().setVisible(true);
		addDegreeOfImportanceView.getAddDegreeOfImportanceSettingsView().getImgDelete().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent arg0) {
				boolean confirmed = Window.confirm("Are you sure you want to delete");
				if(confirmed && strategicDegreeImportance != null) 
					deleteStrategicDegreeImportanceRPC(strategicDegreeImportance, vpnlAddDegreeImportanceView, addDegreeOfImportanceView);					
				if(confirmed && strategicDegreeImportance == null)
					vpnlAddDegreeImportanceView.remove(addDegreeOfImportanceView);
			}
		});
	}
	
	private void deleteStrategicRiskFactor(final RiskFactorsDataView riskFactorsView, final VerticalPanel vpnlAddRiskFactorView, final StrategicRiskFactor strategicRiskFactor) {
		riskFactorsView.getRiskFactorsSettingsView().getImgDelete().setVisible(true);
		riskFactorsView.getRiskFactorsSettingsView().getImgDelete().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent arg0) {
				boolean confirmed = Window.confirm("Are you sure you want to delete" );
				if(confirmed && strategicRiskFactor != null) 
					deleteStrategicRiskFactorRPC(riskFactorsView, vpnlAddRiskFactorView, strategicRiskFactor);
				if(confirmed && strategicRiskFactor == null) 
					vpnlAddRiskFactorView.remove(riskFactorsView);
			}
		});
	}

	private void deleteStrategicDegreeImportanceRPC(StrategicDegreeImportance strategicDegreeImportance, final VerticalPanel vpnlAddDegreeImportanceView, final StrategicDegreeImportanceView addDegreeOfImportanceView) {
		rpcService.deleteStrategicDegreeImportance(strategicDegreeImportance.getId(), new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable arg0) {
				new DisplayAlert("Sorry! Unable to delete " + arg0);
			}

			@Override
			public void onSuccess(String msg) {
				new DisplayAlert(msg);
				vpnlAddDegreeImportanceView.remove(addDegreeOfImportanceView);
			}
		});
	}
	
	private void deleteStrategicRiskFactorRPC(final RiskFactorsDataView riskFactorsView, final VerticalPanel vpnlAddRiskFactorView, StrategicRiskFactor strategicRiskFactor) {
		rpcService.deleteStrategicRiskFactor(strategicRiskFactor.getId(), new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable arg0) {
				new DisplayAlert("Sorry! Unable to delete " + arg0);
			}

			@Override
			public void onSuccess(String msg) {
				new DisplayAlert(msg);
				vpnlAddRiskFactorView.remove(riskFactorsView);
			}
		});
	}
	
	public void enablePanel(RiskAssesmentStrategicView riskAssesmentStrategicView, ArrayList<StrategicDegreeImportanceView> listDegreeImportanceView, ArrayList<RiskFactorsDataView> listRiskFactor, String status) {
		for(StrategicDegreeImportanceView degreeImportanceView : listDegreeImportanceView) {
			degreeImportanceView.enableFields(true, status);
		}
		for(RiskFactorsDataView riskFactorView : listRiskFactor) {
			riskFactorView.enableFields(true, status);
		}
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
//			if (!riskAssesmentStrategicView.getOverallRating().getSelectedValue()
//					.equals(riskAssesmentStrategicView.getListBoxUserOption().getSelectedValue())) {
//				riskAssesmentStrategicView.getPanelRatingComment().setVisible(true);
//			}
		} else {
			riskAssesmentStrategicView.getBtnDeclineInitiator().setVisible(true);
			riskAssesmentStrategicView.getHpnlButtonsApprovar().setVisible(false);
			riskAssesmentStrategicView.getHpnlButtonInitiator().setVisible(true);
//			if (!riskAssesmentStrategicView.getOverallRating().getSelectedValue()
//					.equals(riskAssesmentStrategicView.getListBoxUserOption().getSelectedValue())) {
//				riskAssesmentStrategicView.getPanelRatingComment().setVisible(true);
//			}
		}
	}

	public void saveRiskAssesment(ArrayList<StrategicDegreeImportance> strategicDegreeImportance, ArrayList<StrategicRiskFactor> arrayListSaveRiskFactors, final RiskAssesmentView riskAssesmentView,
			String todo, final Button button) {
//		setArrayListStrategicRisksToSave(arrayListAddMoreStrategic);
//		for(StrategicRisk strategicRisk : arrayListAddMoreStrategic)
//		{
//			strategicRisk.setStrategicId(strategicRisks.get(0).getStrategicId());
//			strategicRisks.add(strategicRisk);
//		}
		button.setEnabled(false);
		actionPerformed = todo;
		HashMap<String, String> hm = new HashMap<String, String>();
		if (todo.equalsIgnoreCase("approve")) {
			todo = "submit";
		}
		hm.put("todo", todo);
		hm.put("tab", selectedTab + "");
				
//		rpcService.saveDegreeImportance(arrayListSaveDegreeImportance, new AsyncCallback<ArrayList<DegreeImportance>>() {
//
//			@Override
//			public void onFailure(Throwable arg0) {
//				new DisplayAlert("Unable to save Degree Importamce");
//			}
//
//			@Override
//			public void onSuccess(ArrayList<DegreeImportance> resultDegreeImportance) {
//				fetchDegreeImportanceRiskFactors(riskAssesmentView);
//			}
//		});
//		
//		rpcService.saveRiskFactor(arrayListSaveRiskFactors, new AsyncCallback<ArrayList<RiskFactor>>() {
//
//			@Override
//			public void onFailure(Throwable arg0) {
//				new DisplayAlert("Unable to save Risk Factor");				
//			}
//
//			@Override
//			public void onSuccess(ArrayList<RiskFactor> resultArrayRiskFactors) {
//				fetchDegreeImportanceRiskFactors(riskAssesmentView);
//			}
//		});
		
		rpcService.saveRiskAssesment(hm, strategicDegreeImportance, arrayListSaveRiskFactors, new AsyncCallback<String>() {

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

	private Widget overallRatingLayout(ArrayList<TextBox> arrayWeightage, ArrayList<ListBox> listBoxesImpact, ArrayList<ListBox> arrayOverAllRatings, ArrayList<Image> listImgProbability, Label lblOverallRating) {
		final HorizontalPanel hpnlOverallRating = new HorizontalPanel();
		LabelBold lblBoldOverallRating = new LabelBold("Overall impact rating");
		lblBoldOverallRating.setWidth("974px");
		hpnlOverallRating.add(lblBoldOverallRating);
		Label lblRatings = new Label();
		lblRatings.getElement().getStyle().setFontWeight(FontWeight.BOLDER);
		hpnlOverallRating.add(lblRatings);	
		calculateOverAllImpactRatings(arrayWeightage, listBoxesImpact, lblRatings, arrayOverAllRatings, listImgProbability, lblOverallRating);
		return hpnlOverallRating;
	}
	
	private void calculateOverAllImpactRatings(final ArrayList<TextBox> arrayWeightage, final ArrayList<ListBox> listBoxesImpact, Label lblRatings, ArrayList<ListBox> arrayOverAllRatings, ArrayList<Image> listImgProbability, Label lblOverallRating) {
		resultImpact  = 0;
//		showErrorMessage(arrayWeightage);
		for(int i=0; i<arrayWeightage.size(); i++) {
			resultImpact +=  ((Float.parseFloat(arrayWeightage.get(i).getValue())/100) * Float.parseFloat(listBoxesImpact.get(i).getSelectedValue()));
		}
//		if(resultImpact != 0)
//			resultImpact = resultImpact / arrayImpactRating.size();
		textBoxAndListBoxWeightageValueChangeHandler(arrayWeightage, listBoxesImpact, lblRatings, arrayOverAllRatings, listImgProbability, lblOverallRating);
		lblRatings.setText(NumberFormat.getFormat(".00").format(resultImpact));
		calculateOverAllRisksRatings(arrayOverAllRatings, listImgProbability, lblOverallRating);		
	}
	
	private void textBoxAndListBoxWeightageValueChangeHandler(final ArrayList<TextBox> arrayWeightage, final ArrayList<ListBox> listBoxesImpact, final Label lblRatings, final ArrayList<ListBox> arrayOverAllRatings, final ArrayList<Image> listImgProbability, final Label lblOverallRating) {
		for(final TextBox txtWeightage : arrayWeightage)
			txtWeightage.addValueChangeHandler(new ValueChangeHandler<String>() {
				
				@Override
				public void onValueChange(ValueChangeEvent<String> arg0) {
					txtWeightage.setText(arg0.getValue());
				calculateOverAllImpactRatings(arrayWeightage, listBoxesImpact, lblRatings, arrayOverAllRatings, listImgProbability, lblOverallRating);
				}
			});
		for(final ListBox listBoxImpact : listBoxesImpact)
			listBoxImpact.addChangeHandler(new ChangeHandler() {
				
				@Override
				public void onChange(ChangeEvent arg0) {
					calculateOverAllImpactRatings(arrayWeightage, listBoxesImpact, lblRatings, arrayOverAllRatings, listImgProbability, lblOverallRating);
				}
			});
		} 
	
	private int showErrorMessage(ArrayList<StrategicDegreeImportance> arrayListSaveDegreeImportance) {
		int sum = 0;
		int i=0;
		for(StrategicDegreeImportance strategicRisk : arrayListSaveDegreeImportance) {
			sum += strategicRisk.getWeightage();
		}
//		if(sum != 100)
//			new DisplayAlert("Sum of all weightage must equal to 100%");
		return sum;
	}
		
	private void calculateOverAllRisksRatings(ArrayList<ListBox> arrayOverAllRatings, ArrayList<Image> listImgProbability, Label lblOverallRatings) {
		resultRating = 0;
		for(int i=0; i<arrayOverAllRatings.size(); i++) {
			resultRating +=  Float.parseFloat(arrayOverAllRatings.get(i).getSelectedItemText());
		}
//		if(resultRating != 0)
//			resultRating = resultRating / arrayOverAllRatings.size();
		listBoxRatingValueChangeHandler(arrayOverAllRatings, listImgProbability, lblOverallRatings);
		String result = NumberFormat.getFormat(".00").format((resultRating * resultImpact / arrayOverAllRatings.size())); 
		lblOverallRatings.setText(String.valueOf(result));
	}
	
	private void listBoxRatingValueChangeHandler(final ArrayList<ListBox> arrayOverAllRatings, final ArrayList<Image> listImgProbability, final Label lblOverallRatings) {
		for(final ListBox listBoxRating : arrayOverAllRatings)
			listBoxRating.addChangeHandler(new ChangeHandler() {
				
				@Override
				public void onChange(ChangeEvent arg0) {
					calculateOverAllRisksRatings(arrayOverAllRatings, listImgProbability, lblOverallRatings);
					updateProbilityImage(arrayOverAllRatings, listImgProbability);
				}
			});		
	} 
	
	private void updateProbilityImage(ArrayList<ListBox> arrayOverAllRatings, ArrayList<Image> listImgProbability) {
		for(int i = 0; i<arrayOverAllRatings.size(); i++)
		if(Integer.parseInt(arrayOverAllRatings.get(i).getSelectedValue()) <= 10 && Integer.parseInt(arrayOverAllRatings.get(i).getSelectedValue()) > 7)
			listImgProbability.get(i).setUrl("redcircle.png");
		else if(Integer.parseInt(arrayOverAllRatings.get(i).getSelectedValue()) <= 7 && Integer.parseInt(arrayOverAllRatings.get(i).getSelectedValue()) > 3)	
			listImgProbability.get(i).setUrl("yellowcircle.png");
		else 
			listImgProbability.get(i).setUrl("greencircle.png");
	}
	
}
