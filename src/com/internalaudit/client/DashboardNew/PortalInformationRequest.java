package com.internalaudit.client.DashboardNew;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.DateCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;
import com.internalaudit.shared.InformationRequestEntity;
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

	public PortalInformationRequest(ArrayList<InformationRequestEntity> arrayList) {
		setData(arrayList);
		// setData(exceptions);
		add(createGridFieldWork());

	}

	private void setData(ArrayList<InformationRequestEntity> arrayList) {
		for (int i = 0; i < arrayList.size(); i++) {
			InformationRequest issue = new InformationRequest();
			// issue.setId(exceptions.get(i).getExceptionId());
			issue.setId(arrayList.get(i).getInformationRequestId());
			issue.setInformationReport(arrayList.get(i).getRequestItem());
			issue.setRaisedBy(arrayList.get(i).getAssignedFrom().getEmployeeName());
			issue.setRaisedTo(arrayList.get(i).getContactResponsible().getEmployeeName());
			issue.setOverDueDays(arrayList.get(i).getDueDate());
			informationRequests.add(issue);
		}
		// }
	}

	public Widget createGridFieldWork() {

		ColumnConfig<InformationRequest, Integer> informationId = new ColumnConfig<InformationRequest, Integer>(
				properties.id(), 70, "Id");
		ColumnConfig<InformationRequest, String> informationReport = new ColumnConfig<InformationRequest, String>(
				properties.informationReport(), 590, "Information Report");
		ColumnConfig<InformationRequest, String> informationRaisedTo = new ColumnConfig<InformationRequest, String>(
				properties.raisedTo(), 180, "Raised To");
		ColumnConfig<InformationRequest, String> informationRaisedBy = new ColumnConfig<InformationRequest, String>(
				properties.raisedBy(), 180, "Raised By");
		ColumnConfig<InformationRequest, Date> informationOverDue = new ColumnConfig<InformationRequest, Date>(
				properties.overDueDays(), 150, "OverDue Day");

		Cell cellDueDate = new DateCell(DateTimeFormat.getFormat("MM/dd/yy"));
		informationOverDue.setCell(cellDueDate);

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
		// grid.setWidth(600);
		grid.getView().setAutoExpandColumn(informationId);

		// grid.getView().setAutoExpandColumn(informationReport);
		// grid.getView().setAutoExpandColumn(informationRaisedTo);
		// grid.getView().setAutoExpandColumn(informationRaisedBy);
		// grid.getView().setAutoExpandColumn(informationOverDue);

		grid.getView().setForceFit(true);
		grid.getView().setStripeRows(true);
		grid.getView().setColumnLines(true);

		ScrollPanel p = new ScrollPanel();
		// p.setHeight("220px");

		p.add(grid);

		VerticalLayoutContainer con = new VerticalLayoutContainer();

		con.add(p, new VerticalLayoutData(1, 1));

		panel = new ContentPanel();
		// panel.setHeight(230);
		// panel.setWidth(700);
		panel.setSize("1190px", "350px");
		panel.setHeadingText("Information Request");
		panel.add(con);
		return panel;
	}

}