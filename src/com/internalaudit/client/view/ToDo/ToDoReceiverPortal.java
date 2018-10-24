

package com.internalaudit.client.view.ToDo;
import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;
import com.internalaudit.shared.Exceptions;
import com.internalaudit.shared.InformationRequestEntity;
import com.internalaudit.shared.ToDo;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;

public class ToDoReceiverPortal extends VerticalLayoutContainer {

	protected static final int MIN_HEIGHT = 1;
	protected static final int MIN_WIDTH = 1280;
	protected static final int PREFERRED_HEIGHT = 1;
	protected static final int PREFERRED_WIDTH = 1;
	  private ContentPanel panel;
	private static final ToDoReceiverProperties properties = GWT.create(ToDoReceiverProperties.class);

	private List<ToDoReceiverEntity> informationRequests = new ArrayList<ToDoReceiverEntity>();

	public ToDoReceiverPortal(ArrayList<ToDo> arrayList) {
		setData(arrayList);
		//setData(exceptions);
		add(createGridFieldWork());

	}

	private void setData(ArrayList<ToDo> arrayList) {
		for (int i = 0; i < arrayList.size(); i++) {
			ToDoReceiverEntity issue = new ToDoReceiverEntity();
			//issue.setId(exceptions.get(i).getExceptionId());
			issue.setId(arrayList.get(i).getToDoId());
			issue.setRequestedItem(arrayList.get(i).getDescription());
			issue.setRelatedJob(arrayList.get(i).getJob().getJobName());
			issue.setRaisedBy(arrayList.get(i).getAssignedFrom().getEmployeeName());
			issue.setOverDueDays(arrayList.get(i).getDueDate().toString());
			informationRequests.add(issue);
		}	
//		}
	}

	public Widget createGridFieldWork() {

		ColumnConfig<ToDoReceiverEntity, Integer> informationId = new ColumnConfig<ToDoReceiverEntity, Integer>(properties.id(), 50, "Sr#");
		ColumnConfig<ToDoReceiverEntity, String> requestedItem = new ColumnConfig<ToDoReceiverEntity, String>(properties.requestedItem(), 190,
				"Task");
		ColumnConfig<ToDoReceiverEntity, String> informationRaisedBy = new ColumnConfig<ToDoReceiverEntity, String>(properties.raisedBy(),
				130, "Asigned By");
		ColumnConfig<ToDoReceiverEntity, String> relatedJob = new ColumnConfig<ToDoReceiverEntity, String>(properties.relatedJob(), 130, " Job");
		
		
		ColumnConfig<ToDoReceiverEntity, String> informationOverDue = new ColumnConfig<ToDoReceiverEntity, String>(properties.overDueDays(), 180, "Due Date");
		ColumnConfig<ToDoReceiverEntity, String> informationStatus = new ColumnConfig<ToDoReceiverEntity, String>(properties.status(), 130, "status");
		Anchor a = new  Anchor();
		//ColumnConfig<ToDoReceiverEntity, a> view = new ColumnConfig<ToDoReceiverEntity, a>(properties.status(), 130, "status"));
		List<ColumnConfig<ToDoReceiverEntity, ?>> columns = new ArrayList<ColumnConfig<ToDoReceiverEntity, ?>>();
		columns.add(informationId);
		columns.add(requestedItem);
		columns.add(informationRaisedBy);
		columns.add(relatedJob);
		columns.add(informationOverDue);
		columns.add(informationStatus);
		//columns.add(a);

		ColumnModel<ToDoReceiverEntity> cm = new ColumnModel<ToDoReceiverEntity>(columns);

		ListStore<ToDoReceiverEntity> store = new ListStore<ToDoReceiverEntity>(properties.key());
		store.addAll(informationRequests);

		final Grid<ToDoReceiverEntity> grid = new Grid<ToDoReceiverEntity>(store, cm);
		//grid.setWidth(600);
		grid.getView().setAutoExpandColumn(informationId);
		grid.getView().setForceFit(true);
		grid.getView().setStripeRows(true);
		grid.getView().setColumnLines(true);
		 ScrollPanel p = new ScrollPanel();
		 p.setHeight("220px");
		
	   p.add(grid);

	
		 VerticalLayoutContainer con = new VerticalLayoutContainer();
		
	   
	      con.add(p, new VerticalLayoutData(1, 1));

	      panel = new ContentPanel();
	      panel.setHeight(230);
	      panel.setWidth(850);
	      panel.setHeadingText("ToDoReceiver");
	      panel.add(con);
	      return panel;
	}

}