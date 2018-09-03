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

public class PortalIssue extends VerticalLayoutContainer {

	protected static final int MIN_HEIGHT = 1;
	protected static final int MIN_WIDTH = 1280;
	protected static final int PREFERRED_HEIGHT = 1;
	protected static final int PREFERRED_WIDTH = 1;
	  private ContentPanel panel;
	private static final IssuesProperties properties = GWT.create(IssuesProperties.class);

	private List<Issues> issues = new ArrayList<Issues>();

	public PortalIssue(ArrayList<Exceptions> exceptions) {

		setData(exceptions);
		add(createGridFieldWork());

	}

	private void setData(ArrayList<Exceptions> exceptions) {
		for (int i = 0; i < exceptions.size(); i++) {
			Issues issue = new Issues();
			//issue.setId(exceptions.get(i).getExceptionId());
			issue.setId(exceptions.get(i).getExceptionId());
			issue.setIssueTitle(exceptions.get(i).getDetail());
			issue.setManagementResponce(exceptions.get(i).getManagementComments());
			issue.setStatus(exceptions.get(i).getDisplayStatus());

			issues.add(issue);
		}
	}

	public Widget createGridFieldWork() {

		ColumnConfig<Issues, Integer> issueId = new ColumnConfig<Issues, Integer>(properties.id(), 50, "Id");
		ColumnConfig<Issues, String> issueTitle = new ColumnConfig<Issues, String>(properties.issueTitle(), 130,
				"Issue Title");
		ColumnConfig<Issues, String> issueManagement = new ColumnConfig<Issues, String>(properties.managementResponce(),
				200, "Management Response");
		ColumnConfig<Issues, String> issueStatus = new ColumnConfig<Issues, String>(properties.status(), 400, "Status");

		List<ColumnConfig<Issues, ?>> columns = new ArrayList<ColumnConfig<Issues, ?>>();
		columns.add(issueId);
		columns.add(issueTitle);
		columns.add(issueManagement);
		columns.add(issueStatus);

		ColumnModel<Issues> cm = new ColumnModel<Issues>(columns);

		ListStore<Issues> store = new ListStore<Issues>(properties.key());
		store.addAll(issues);

		final Grid<Issues> grid = new Grid<Issues>(store, cm);
		grid.setWidth(650);
		grid.getView().setAutoExpandColumn(issueId);
		grid.getView().setForceFit(true);
		grid.getView().setStripeRows(true);
		grid.getView().setColumnLines(true);
		 ScrollPanel p = new ScrollPanel();
		 p.setHeight("350px");
		
	   p.add(grid);

	
		 VerticalLayoutContainer con = new VerticalLayoutContainer();
		
	   
	      con.add(p, new VerticalLayoutData(1, 1));

	      panel = new ContentPanel();
	      panel.setHeadingText("Issues");
	      panel.add(con);
	      return panel;
	}

}