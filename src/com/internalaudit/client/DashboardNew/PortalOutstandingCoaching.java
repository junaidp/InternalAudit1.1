
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
import com.internalaudit.shared.ToDo;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;

public class PortalOutstandingCoaching extends VerticalLayoutContainer {

	protected static final int MIN_HEIGHT = 1;
	protected static final int MIN_WIDTH = 1280;
	protected static final int PREFERRED_HEIGHT = 1;
	protected static final int PREFERRED_WIDTH = 1;
	private ContentPanel panel;
	private static final OutstandingCoachingProperties properties = GWT.create(OutstandingCoachingProperties.class);

	private List<OutstandingCoaching> outstandingCoachings = new ArrayList<OutstandingCoaching>();

	public PortalOutstandingCoaching(ArrayList<ToDo> arrayList) {

		setData(arrayList);
		add(createGridFieldWork());

	}

	private void setData(ArrayList<ToDo> arrayList) {
		for (int i = 0; i < arrayList.size(); i++) {
			OutstandingCoaching issue = new OutstandingCoaching();
			// issue.setId(exceptions.get(i).getExceptionId());
			issue.setId(arrayList.get(i).getToDoId());
			issue.setCoachingNote(arrayList.get(i).getDescription());
			issue.setRaisedBy(arrayList.get(i).getAssignedFrom().getEmployeeName());
			issue.setRaisedTo(arrayList.get(i).getAssignedTo().getEmployeeName());
			issue.setOverDueDays(arrayList.get(i).getDueDate());

			outstandingCoachings.add(issue);
		}
	}

	public Widget createGridFieldWork() {

		ColumnConfig<OutstandingCoaching, Integer> outstandingId = new ColumnConfig<OutstandingCoaching, Integer>(
				properties.id(), 70, "Id");
		ColumnConfig<OutstandingCoaching, String> outstandingCoachingNote = new ColumnConfig<OutstandingCoaching, String>(
				properties.coachingNote(), 590, "Coaching Note");
		ColumnConfig<OutstandingCoaching, String> outstandingRaisedTo = new ColumnConfig<OutstandingCoaching, String>(
				properties.raisedTo(), 180, "Raised To");
		ColumnConfig<OutstandingCoaching, String> outstandingRaisedBy = new ColumnConfig<OutstandingCoaching, String>(
				properties.raisedBy(), 180, "Raised By");
		ColumnConfig<OutstandingCoaching, Date> outstandingOverDue = new ColumnConfig<OutstandingCoaching, Date>(
				properties.overDueDays(), 150, "OverDue Day");

		Cell cellDueDate = new DateCell(DateTimeFormat.getFormat("MM/dd/yy"));
		outstandingOverDue.setCell(cellDueDate);

		List<ColumnConfig<OutstandingCoaching, ?>> columns = new ArrayList<ColumnConfig<OutstandingCoaching, ?>>();
		columns.add(outstandingId);
		columns.add(outstandingCoachingNote);
		columns.add(outstandingRaisedTo);
		columns.add(outstandingRaisedBy);
		columns.add(outstandingOverDue);

		ColumnModel<OutstandingCoaching> cm = new ColumnModel<OutstandingCoaching>(columns);

		ListStore<OutstandingCoaching> store = new ListStore<OutstandingCoaching>(properties.key());
		store.addAll(outstandingCoachings);

		final Grid<OutstandingCoaching> grid = new Grid<OutstandingCoaching>(store, cm);
		// grid.setWidth(580);
		grid.getView().setAutoExpandColumn(outstandingId);
		grid.getView().setForceFit(true);
		grid.getView().setStripeRows(true);
		grid.getView().setColumnLines(true);
		ScrollPanel p = new ScrollPanel();
		p.setHeight("200px");

		p.add(grid);

		VerticalLayoutContainer con = new VerticalLayoutContainer();

		con.add(p, new VerticalLayoutData(1, 1));

		panel = new ContentPanel();
		// panel.setHeight(220);
		// panel.setWidth(700);
		panel.setSize("1190px", "350px");
		panel.setHeadingText("OutstandingCoaching");
		panel.add(con);
		return panel;
	}

}