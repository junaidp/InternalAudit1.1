package com.internalaudit.client.DashboardNew;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;
import com.internalaudit.shared.Exceptions;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;

public class PortalInformationRequest extends VerticalLayoutContainer {

	protected static final int MIN_HEIGHT = 1;
	protected static final int MIN_WIDTH = 1280;
	protected static final int PREFERRED_HEIGHT = 1;
	protected static final int PREFERRED_WIDTH = 1;
	  private ContentPanel panel;
	private static final InformationRequestProperties properties = GWT.create(InformationRequestProperties.class);

	private List<InformationRequest> informationRequests = new ArrayList<InformationRequest>();

	public PortalInformationRequest() {

		//setData(exceptions);
		add(createGridFieldWork());

	}

	private void setData() {
//		for (int i = 0; i < exceptions.size(); i++) {
			InformationRequest informationRequest = new InformationRequest();
			informationRequest.setId(1);
			informationRequest.setInformationReport("some Information");
			informationRequest.setRaisedTo("raised to?");
			informationRequest.setRaisedBy("raisedBy?");
			informationRequest.setOverDueDays("date?");
//			//issue.setId(exceptions.get(i).getExceptionId());
//			informationRequest.setId(exceptions.get(i).getExceptionId());
//			informationRequest.setIssueTitle(exceptions.get(i).getDetail());
//			informationRequest.setManagementResponce(exceptions.get(i).getManagementComments());
//			informationRequest.setStatus(exceptions.get(i).getDisplayStatus());
//
			informationRequests.add(informationRequest);
//		}
	}

	public Widget createGridFieldWork() {

		ColumnConfig<InformationRequest, Integer> informationId = new ColumnConfig<InformationRequest, Integer>(properties.id(), 50, "Id");
		ColumnConfig<InformationRequest, String> informationReport = new ColumnConfig<InformationRequest, String>(properties.informationReport(), 180,
				"Information Report");
		ColumnConfig<InformationRequest, String> informationRaisedTo = new ColumnConfig<InformationRequest, String>(properties.raisedTo(),
				110, "Raised To");
		ColumnConfig<InformationRequest, String> informationRaisedBy = new ColumnConfig<InformationRequest, String>(properties.raisedBy(), 110, "Raised By");
		ColumnConfig<InformationRequest, String> informationOverDue = new ColumnConfig<InformationRequest, String>(properties.raisedBy(), 180, "OverDue Day");

		List<ColumnConfig<InformationRequest, ?>> columns = new ArrayList<ColumnConfig<InformationRequest, ?>>();
		columns.add(informationId);
		columns.add(informationReport);
		columns.add(informationRaisedTo);
		columns.add(informationRaisedBy);
		columns.add(informationOverDue);
		

		ColumnModel<InformationRequest> cm = new ColumnModel<InformationRequest>(columns);

		ListStore<InformationRequest> store = new ListStore<InformationRequest>(properties.key());
		store.addAll(informationRequests);

		final Grid<InformationRequest> grid = new Grid<InformationRequest>(store, cm);
		//grid.setWidth(600);
		grid.getView().setAutoExpandColumn(informationId);
		grid.getView().setForceFit(true);
		grid.getView().setStripeRows(true);
		grid.getView().setColumnLines(true);
		 ScrollPanel p = new ScrollPanel();
		 p.setHeight("350px");
		
	   p.add(grid);

	
		 VerticalLayoutContainer con = new VerticalLayoutContainer();
		
	   
	      con.add(p, new VerticalLayoutData(1, 1));

	      panel = new ContentPanel();
	      panel.setHeadingText("InformationRequest");
	      panel.add(con);
	      return panel;
	}

}