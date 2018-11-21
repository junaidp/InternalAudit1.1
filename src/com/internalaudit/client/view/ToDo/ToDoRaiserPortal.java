
package com.internalaudit.client.view.ToDo;
import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;
import com.internalaudit.client.view.PopupsView;
import com.internalaudit.client.view.ToDoView;
import com.internalaudit.shared.Exceptions;
import com.internalaudit.shared.InformationRequestEntity;
import com.internalaudit.shared.ToDo;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;

public class ToDoRaiserPortal extends VerticalLayoutContainer {

	protected static final int MIN_HEIGHT = 1;
	protected static final int MIN_WIDTH = 1280;
	protected static final int PREFERRED_HEIGHT = 1;
	protected static final int PREFERRED_WIDTH = 1;
	  private ContentPanel panel;
	private static final ToDoRaiserProperties properties = GWT.create(ToDoRaiserProperties.class);

	private List<ToDoRaiserEntity> informationRequests = new ArrayList<ToDoRaiserEntity>();

	public ToDoRaiserPortal(ArrayList<ToDo> arrayList) {
		setData(arrayList);
		//setData(exceptions);
		add(createGridFieldWork());

	}

	private void setData(ArrayList<ToDo> arrayList) {
		for (int i = 0; i < arrayList.size(); i++) {
			ToDoRaiserEntity issue = new ToDoRaiserEntity();
			//issue.setId(exceptions.get(i).getExceptionId());
			issue.setId(arrayList.get(i).getToDoId());
			issue.setRequestedItem(arrayList.get(i).getDescription());
			//issue.setRelatedJob(arrayList.get(i).getJob().getJobName());
			issue.setRaisedTo(arrayList.get(i).getAssignedTo().getEmployeeName());
			issue.setOverDueDays(arrayList.get(i).getDueDate().toString());
			issue.setStatus(arrayList.get(i).getRespond());
			informationRequests.add(issue);
		}	
//		}
	}

	public Widget createGridFieldWork() {

		ColumnConfig<ToDoRaiserEntity, Integer> informationId = new ColumnConfig<ToDoRaiserEntity, Integer>(properties.id(), 50, "Sr#");
		ColumnConfig<ToDoRaiserEntity, String> requestedItem = new ColumnConfig<ToDoRaiserEntity, String>(properties.requestedItem(), 190,
				"Task");
		ColumnConfig<ToDoRaiserEntity, String> informationRaisedBy = new ColumnConfig<ToDoRaiserEntity, String>(properties.raisedTo(),
				130, "Asigned To");
		ColumnConfig<ToDoRaiserEntity, String> relatedJob = new ColumnConfig<ToDoRaiserEntity, String>(properties.relatedJob(), 130, " Job");
		
		
		ColumnConfig<ToDoRaiserEntity, String> informationOverDue = new ColumnConfig<ToDoRaiserEntity, String>(properties.overDueDays(), 180, "Due Date");
		ColumnConfig<ToDoRaiserEntity, String> informationStatus = new ColumnConfig<ToDoRaiserEntity, String>(properties.status(), 130, "status");

		List<ColumnConfig<ToDoRaiserEntity, ?>> columns = new ArrayList<ColumnConfig<ToDoRaiserEntity, ?>>();
		columns.add(informationId);
		columns.add(requestedItem);
		columns.add(informationRaisedBy);
		columns.add(relatedJob);
		columns.add(informationOverDue);
		columns.add(informationStatus);
		

		ColumnModel<ToDoRaiserEntity> cm = new ColumnModel<ToDoRaiserEntity>(columns);

		ListStore<ToDoRaiserEntity> store = new ListStore<ToDoRaiserEntity>(properties.key());
		store.addAll(informationRequests);

		final Grid<ToDoRaiserEntity> grid = new Grid<ToDoRaiserEntity>(store, cm);
		//grid.setWidth(600);
		grid.getView().setAutoExpandColumn(informationId);
		grid.getView().setForceFit(true);
		grid.getView().setStripeRows(true);
		grid.getView().setColumnLines(true);
		 ScrollPanel p = new ScrollPanel();
		 p.setHeight("220px");
		
	   p.add(grid);

	
		 VerticalLayoutContainer con = new VerticalLayoutContainer();
		 Anchor addTask = new Anchor("Add New Task");
			addTask.addStyleName("w3-right");
		   con.add(addTask);
		   
		   addTask.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				final ToDoView todoview = new ToDoView();
				PopupsView pp = new PopupsView(todoview, "");
				pp.getLabelheading().setText("To Do");
				pp.getVpnlMain().setWidth("400px");
				pp.getHpnlSPace().setWidth("400px");
				pp.getVpnlMain().setHeight("320px");

				
			}
		});
		   
	   
	      con.add(p, new VerticalLayoutData(1, 1));

	      panel = new ContentPanel();
	      panel.setHeight(230);
	      panel.setWidth(850);
	      panel.setHeadingText("ToDoRaise");
	      panel.add(con);
	      return panel;
	}

}