package com.internalaudit.client.view.data;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.internalaudit.client.InternalAuditService;
import com.internalaudit.client.InternalAuditServiceAsync;
import com.internalaudit.client.view.DisplayAlert;
import com.internalaudit.client.view.FinalAuditablesView;
import com.internalaudit.shared.Employee;
import com.internalaudit.shared.JobCreation;
import com.internalaudit.shared.Strategic;
import com.internalaudit.shared.StrategicDepartments;
import com.internalaudit.shared.TimeOutException;
import com.sencha.gxt.core.client.dom.ScrollSupport.ScrollMode;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.BeforeExpandEvent;
import com.sencha.gxt.widget.core.client.event.BeforeExpandEvent.BeforeExpandHandler;
import com.sencha.gxt.widget.core.client.form.TextField;

public class FinalAuditablesViewData {

	private InternalAuditServiceAsync rpcService = GWT.create(InternalAuditService.class);
	private Logger logger = Logger.getLogger("FinalAuditablesViewData");

	public void setData(ContentPanel cp, final FinalAuditablesView finalAuditablesView,
			VerticalPanel vpnlFinalAuditable, final Employee loggedInUser) {

		cp.addBeforeExpandHandler(new BeforeExpandHandler() {

			@Override
			public void onBeforeExpand(BeforeExpandEvent event) {
				fetchFinalAuditables(finalAuditablesView, loggedInUser);
			}
		});

	}

	public void fetchFinalAuditables(final FinalAuditablesView finalAuditablesView, final Employee loggedInUser) {
		rpcService.fetchFinalAuditables(new AsyncCallback<ArrayList<Strategic>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Fetch Final Auditables failed");

				logger.log(Level.INFO, "FAIL: fetchDashBoard .Inside Audit AuditAreaspresenter");
				if (caught instanceof TimeOutException) {
					History.newItem("login");
				} else {
					System.out.println("FAIL: fetchDashBoard .Inside AuditAreaspresenter");
					Window.alert("FAIL: fetchDashBoard in finalauditableviewdata"); //After FAIL... Name..
				}

			}

			@Override
			public void onSuccess(final ArrayList<Strategic> strategic) {
				setViewData(finalAuditablesView, loggedInUser, strategic);
			}
		});
	}
	
	private void setViewData(final FinalAuditablesView finalAuditablesView, final Employee loggedInUser,
			final ArrayList<Strategic> strategic) {
		finalAuditablesView.getAreas().clear();
		ArrayList<String> auditableUnits = new ArrayList<String>();
		ArrayList<HorizontalPanel> hpnlContainer = new ArrayList<HorizontalPanel>();

		int count = 0;
		for (int i = 0; i < strategic.size(); i++) {

			Button btnApprove = new Button("Approve");
			Button btnDecline = new Button("Decline");
			final TextField txtComments = new TextField();
			txtComments.setEmptyText("Comments");
			txtComments.setWidth(160);

			HorizontalPanel hpnlButtonContainer = new HorizontalPanel();
			hpnlButtonContainer.add(btnDecline);
			hpnlButtonContainer.add(btnApprove);

			VerticalPanel vpnlObjectiveContainer = new VerticalPanel();
			Label lblUnit = new Label(strategic.get(i).getAuditableUnit());

			lblUnit.setWidth("505px");
			if (!auditableUnits.contains(strategic.get(i).getAuditableUnit())) {
				count++;
				HorizontalPanel hpnlMain = new HorizontalPanel();

				auditableUnits.add(strategic.get(i).getAuditableUnit());
				JobCreation jb = new JobCreation();

				Label lblCount = new Label("");
				lblCount.setWidth("55px");
				lblCount.addStyleName("blue");
				// lblCount.setText(count + ")" );

				// lblCount.setText(jb.getJobCreationId() + ")" );
				lblCount.setText(strategic.get(i).getJobCreationId() + ".");
				hpnlMain.add(lblCount);
				hpnlMain.add(lblUnit);

				Label lblDivision = new Label(strategic.get(i).getDivision().getDivisionName());
				lblDivision.setWidth("180px");
				hpnlMain.add(lblDivision);
				
				VerticalLayoutContainer vpnlDepartments = new VerticalLayoutContainer();
				vpnlDepartments.setWidth(180);
				vpnlDepartments.setScrollMode(ScrollMode.AUTOX);
				for(StrategicDepartments departments : strategic.get(i).getStrategicDepartments()) {
					Label lblDepartments = new Label(departments.getDepartment().getDepartmentName());
					lblDepartments.setWidth("180px");
					lblDepartments.setWordWrap(false);
					vpnlDepartments.add(lblDepartments);
				}
				hpnlMain.add(vpnlDepartments);				
				hpnlMain.setSpacing(5);
				
				// hpnlMain.add(vpnlObjectiveContainer);
				// if(loggedInUser.getEmployeeId().isAuditHead()) {
				if (loggedInUser.getRollId() == 1 || loggedInUser.getRollId() == 2
						|| loggedInUser.getRollId() == 3) {
					if (strategic.get(i).isApprovedByAuditHead()) {
						Label lblApproved = new Label("Approved");
						lblApproved.getElement().getStyle().setMarginLeft(10, Unit.PX);
						lblApproved.addStyleName("blue");
						lblApproved.setWidth("90px");
						hpnlMain.add(lblApproved);
					} else if (loggedInUser.getRollId() == 1) {
						VerticalPanel vpnlCommentsAndButton = new VerticalPanel();
						vpnlCommentsAndButton.add(txtComments);
						vpnlCommentsAndButton.add(hpnlButtonContainer);
						vpnlCommentsAndButton.getElement().getStyle().setMarginLeft(10, Unit.PX);
						hpnlMain.add(vpnlCommentsAndButton);
					}
				}

				finalAuditablesView.getAreas().add(hpnlMain);
				hpnlMain.addStyleName("form-row");
				hpnlContainer.add(hpnlMain);

				final DataSetter dataSetter = new DataSetter();
				dataSetter.setId(i);

				btnDecline.addClickHandler(new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						strategic.get(dataSetter.getId()).setComments(txtComments.getText());
						declineFinalAuditable(strategic.get(dataSetter.getId()), finalAuditablesView,
								loggedInUser);
					}
				});

				btnApprove.addClickHandler(new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						strategic.get(dataSetter.getId()).setComments(txtComments.getText());
						approveFinalAuditable(strategic.get(dataSetter.getId()), finalAuditablesView,
								loggedInUser);
					}
				});

			}

		}

		for (int i = 0; i < hpnlContainer.size(); i++) {
			for (int j = 0; j < strategic.size(); j++) {
				Label lblObjective = new Label(strategic.get(j).getStrategicObjective() + " ( "
						+ strategic.get(j).getRating() + " ) ");
				lblObjective.setWidth("400px");
				Label lblUnit = (Label) hpnlContainer.get(i).getWidget(0);
				if (lblUnit.getText().equalsIgnoreCase(strategic.get(j).getAuditableUnit())) {
					VerticalPanel vpnlObjectiveContainer = (VerticalPanel) hpnlContainer.get(i).getWidget(1);
					// vpnlObjectiveContainer.add(lblObjective); // ONLY
					// SHOWING AUDITBLE UNIT FROM NOW...
				}

			}
		}
	}

	public void approveFinalAuditable(Strategic strategic, final FinalAuditablesView finalAuditablesView,
			final Employee loggedInUser) {
		strategic.setApprovedByAuditHead(true);
		rpcService.approveFinalAuditable(strategic, new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable caught) {
				new DisplayAlert("error in approving final auditable");
			}

			@Override
			public void onSuccess(String result) {
				new DisplayAlert("Approved as final auditable");
				fetchFinalAuditables(finalAuditablesView, loggedInUser);
			}
		});

	}

	public void declineFinalAuditable(Strategic strategic, final FinalAuditablesView finalAuditablesView,
			final Employee loggedInUser) {

		strategic.setApprovedByAuditHead(false);
		strategic.setAudit(false);
		rpcService.declineFinalAuditable(strategic, new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable caught) {
				new DisplayAlert("error in declining final auditable");

			}

			@Override
			public void onSuccess(String result) {
				new DisplayAlert("Declined as final auditable");
				fetchFinalAuditables(finalAuditablesView, loggedInUser);
			}
		});

	}

}
