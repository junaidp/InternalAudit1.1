package com.internalaudit.client.view.AuditEngagement;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.internalaudit.client.InternalAuditServiceAsync;
import com.internalaudit.shared.AuditWork;
import com.internalaudit.shared.Employee;
import com.internalaudit.shared.TimeOutException;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.container.AccordionLayoutContainer;
import com.sencha.gxt.widget.core.client.container.AccordionLayoutContainer.AccordionLayoutAppearance;

public class AuditStepContainer extends Composite {

	private VerticalPanel vpnlMain = new VerticalPanel();
	private Employee loggedInEmployee;
	private Logger logger = Logger.getLogger("AuditStepContainer");

	public AuditStepContainer(int selectedJobId, InternalAuditServiceAsync rpcService, Employee loggedInEmployee) {
		initWidget(vpnlMain);
		this.loggedInEmployee = loggedInEmployee;
		fetchAuditWorksForSelectedJob(rpcService, selectedJobId);
		// Label lblHeading = new Label("Audit Steps");
		Label lblHeading = new Label("");
		lblHeading.setHeight("35px");
		vpnlMain.add(lblHeading);
		lblHeading.addStyleName("heading");
		lblHeading.setWidth("1180px");

	}

	private void fetchAuditWorksForSelectedJob(InternalAuditServiceAsync rpcService, final int selectedJobId) {

		rpcService.fetchApprovedAuditWorkRows(selectedJobId, new AsyncCallback<ArrayList<AuditWork>>() {

			@Override
			public void onFailure(Throwable caught) {
				System.out.println("Error in fetchAuditWorkRows in AuditStepContainer");

				logger.log(Level.INFO, "FAIL: fetchApprovedAuditWorkRows .Inside Audit AuditAreaspresenter");
				if (caught instanceof TimeOutException) {
					History.newItem("login");
				} else {
					System.out.println("FAIL: fetchApprovedAuditWorkRows .Inside AuditAreaspresenter");
					Window.alert("FAIL: fetchApprovedAuditWorkRows");// After
																		// FAIL
																		// ...
																		// write
																		// RPC
																		// Name
																		// NOT
																		// Method
																		// Name..
				}

			}

			@Override
			public void onSuccess(ArrayList<AuditWork> result) {
				ContentPanel panel = new ContentPanel();
				panel.setWidth("1180px");
				panel.setBodyBorder(false);
				AccordionLayoutContainer con = new AccordionLayoutContainer();
				panel.add(con);
				AccordionLayoutAppearance appearance = GWT
						.<AccordionLayoutAppearance>create(AccordionLayoutAppearance.class);
				for (int i = 0; i < result.size(); i++) {
					createAuditStepPanels(result.get(i), selectedJobId, con, appearance, panel);
				}
				panel.setHeaderVisible(false);
				vpnlMain.add(panel);
			}

		});
	}

	private void createAuditStepPanels(AuditWork auditWork, int selectedJobId, AccordionLayoutContainer con,
			AccordionLayoutAppearance appearance, ContentPanel panel) {

		ContentPanel cp = new ContentPanel(appearance);
		cp.setAnimCollapse(false);
		//stepNo check added to add Reference number of job 17 july 2020
		String stepNo = ((!auditWork.getStepNo().contains("[a-zA-Z]+")) && auditWork.getStepNo().contains("^[0-9]*$"))? auditWork.getStepNo() : auditWork.getSuggestedControlsId().getSuggestedReferenceNo();
		cp.setHeadingText(stepNo + " | " + auditWork.getDescription());
		ScrollPanel sp = new ScrollPanel();
		sp.add(new AuditStepView(auditWork, selectedJobId, loggedInEmployee));
		sp.setSize("1180px", "350px");
		cp.add(sp);
		con.add(cp);

	}

}
