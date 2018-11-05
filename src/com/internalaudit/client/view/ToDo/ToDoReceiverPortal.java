

package com.internalaudit.client.view.ToDo;
import java.util.ArrayList;
import java.util.List;

import com.google.gwt.cell.client.Cell.Context;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;
import com.internalaudit.client.view.PopupsView;
import com.internalaudit.shared.Employee;
import com.internalaudit.shared.Exceptions;
import com.internalaudit.shared.InformationRequestEntity;
import com.internalaudit.shared.ToDo;
import com.sencha.gxt.cell.core.client.TextButtonCell;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
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
	TextButtonCell button = new TextButtonCell() ;
	ListStore<ToDoReceiverEntity> store ;
	private List<ToDoReceiverEntity> toDoRequests = new ArrayList<ToDoReceiverEntity>();

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
			issue.setRaisedBy(arrayList.get(i).getAssignedFrom());
			issue.setOverDueDays(arrayList.get(i).getDueDate().toString());
			toDoRequests.add(issue);
		}	
//		}
	}

	public Widget createGridFieldWork() {

		ColumnConfig<ToDoReceiverEntity, Integer> informationId = new ColumnConfig<ToDoReceiverEntity, Integer>(properties.id(), 50, "Sr#");
		ColumnConfig<ToDoReceiverEntity, String> requestedItem = new ColumnConfig<ToDoReceiverEntity, String>(properties.requestedItem(), 170,
				"Task");
		ColumnConfig<ToDoReceiverEntity, Employee> informationRaisedBy = new ColumnConfig<ToDoReceiverEntity, Employee>(properties.raisedBy(),
				120, "Asigned By");
		ColumnConfig<ToDoReceiverEntity, String> relatedJob = new ColumnConfig<ToDoReceiverEntity, String>(properties.relatedJob(), 110, " Job");
		
		
		ColumnConfig<ToDoReceiverEntity, String> informationOverDue = new ColumnConfig<ToDoReceiverEntity, String>(properties.overDueDays(), 160, "Due Date");
		ColumnConfig<ToDoReceiverEntity, String> informationStatus = new ColumnConfig<ToDoReceiverEntity, String>(properties.status(), 110, "status");
		ColumnConfig<ToDoReceiverEntity, String> viewButton = new ColumnConfig<ToDoReceiverEntity, String>(properties.viewButton(), 100, "");


	      button.setText("view");
	      
	    
	      
	      button.addSelectHandler(new SelectHandler() {
	          @Override
	          public void onSelect(SelectEvent event) {
	            Context c = event.getContext();
	            int row = c.getIndex();
	            ToDoReceiverEntity toDo = store.get(row);
	            ToDoRaiserView toDoReceiverView = new ToDoRaiserView(toDo);
	            PopupsView pp = new PopupsView(toDoReceiverView, "");
				pp.getLabelheading().setText("ToDo Receiver");
				pp.getVpnlMain().setTitle("Todos");
				pp.getVpnlMain().setWidth("600px");
				pp.getHpnlSPace().setWidth("600px");
				pp.getVpnlMain().setHeight("530px");
	   
	          }
	        });
	
	     
	     
	viewButton.setCell(button);
		
		List<ColumnConfig<ToDoReceiverEntity, ?>> columns = new ArrayList<ColumnConfig<ToDoReceiverEntity, ?>>();
		columns.add(informationId);
		columns.add(requestedItem);
		columns.add(informationRaisedBy);
		columns.add(relatedJob);
		columns.add(informationOverDue);
		columns.add(informationStatus);
		columns.add(viewButton);

		ColumnModel<ToDoReceiverEntity> cm = new ColumnModel<ToDoReceiverEntity>(columns);

		store = new ListStore<ToDoReceiverEntity>(properties.key());
		store.addAll(toDoRequests);

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