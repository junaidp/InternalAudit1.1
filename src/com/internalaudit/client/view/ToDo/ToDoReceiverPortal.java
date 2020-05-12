package com.internalaudit.client.view.ToDo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.Cell.Context;
import com.google.gwt.cell.client.DateCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.internalaudit.client.view.PopupsView;
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
	TextButtonCell button = new TextButtonCell();
	ListStore<ToDoReceiverEntity> store;
	private List<ToDoReceiverEntity> toDoRequests = new ArrayList<ToDoReceiverEntity>();

	public ToDoReceiverPortal(ArrayList<ToDo> arrayList) {
		setData(arrayList);
		add(createGridFieldWork());

	}

	private void setData(ArrayList<ToDo> arrayList) {
		for (int i = 0; i < arrayList.size(); i++) {
			ToDoReceiverEntity issue = new ToDoReceiverEntity();
			// issue.setId(exceptions.get(i).getExceptionId());
			issue.setId(arrayList.get(i).getToDoId());
			// issue.setRequestedItem(arrayList.get(i).getDescription());//changed
			// by Moqeet
			issue.setRequestedItem(arrayList.get(i).getTask());
			issue.setRelatedJob(arrayList.get(i).getJob().getJobName());
			issue.setRaisedBy(arrayList.get(i).getAssignedFrom().getEmployeeName());
			issue.setOverDueDays(arrayList.get(i).getDueDate());
			issue.setRaisedById(arrayList.get(i).getAssignedFrom().getEmployeeId());
			issue.setRaisedToId(arrayList.get(i).getAssignedTo().getEmployeeId());
			issue.setRaisedTo(arrayList.get(i).getAssignedTo().getEmployeeName());
			issue.setRelatedJobId(arrayList.get(i).getJob().getJobCreationId());
			// issue.setStatus(arrayList.get(i).getJob().getJobName());
			toDoRequests.add(issue);
		}
		// }
	}

	public Widget createGridFieldWork() {

		ColumnConfig<ToDoReceiverEntity, Integer> informationId = new ColumnConfig<ToDoReceiverEntity, Integer>(
				properties.id(), 70, "Sr#");
		ColumnConfig<ToDoReceiverEntity, String> requestedItem = new ColumnConfig<ToDoReceiverEntity, String>(
				properties.requestedItem(), 170, "Task");
		ColumnConfig<ToDoReceiverEntity, String> informationRaisedBy = new ColumnConfig<ToDoReceiverEntity, String>(
				properties.raisedBy(), 140, "Asigned By");
		ColumnConfig<ToDoReceiverEntity, String> relatedJob = new ColumnConfig<ToDoReceiverEntity, String>(
				properties.relatedJob(), 140, " Job");
		ColumnConfig<ToDoReceiverEntity, Date> informationOverDue = new ColumnConfig<ToDoReceiverEntity, Date>(
				properties.overDueDays(), 110, "Due Date");
		Cell cellDueDate = new DateCell(DateTimeFormat.getFormat("MM/dd/yy"));
		informationOverDue.setCell(cellDueDate);
		ColumnConfig<ToDoReceiverEntity, String> informationStatus = new ColumnConfig<ToDoReceiverEntity, String>(
				properties.status(), 110, "Status");
		ColumnConfig<ToDoReceiverEntity, String> viewButton = new ColumnConfig<ToDoReceiverEntity, String>(
				properties.viewButton(), 100, "");

		button.setText("view");

		button.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				Context c = event.getContext();
				int row = c.getIndex();
				ToDoReceiverEntity toDo = store.get(row);
				ToDoRaiserView toDoReceiverView = new ToDoRaiserView(toDo);
				final PopupsView pp = new PopupsView(toDoReceiverView, "To Do Receiver");
				// pp.getLabelheading().setText("ToDo Receiver");
				// pp.getPopup().setHeadingText("ToDo Receiver");
				pp.getVpnlMain().setTitle("Todos");
				pp.getVpnlMain().setWidth("600px");
				pp.getHpnlSPace().setWidth("600px");
				pp.getVpnlMain().setHeight("500px");
				toDoReceiverView.getBtnClose().addClickHandler(new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						pp.getVpnlMain().removeFromParent();
						pp.getPopup().removeFromParent();
					}
				});

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
		// grid.setWidth(600);
		grid.getView().setAutoExpandColumn(informationId);
		grid.getView().setForceFit(true);
		grid.getView().setStripeRows(true);
		grid.getView().setColumnLines(true);
		VerticalPanel p = new VerticalPanel();
		grid.setHeight("220px");
		p.add(grid);

		// VerticalLayoutContainer con = new VerticalLayoutContainer();
		//
		// con.add(p, new VerticalLayoutData(1, 1));

		// panel = new ContentPanel();
		// panel.setHeight(230);
		// panel.setWidth(850);
		// panel.setHeadingText("ToDoReceiver");
		// panel.add(con);
		// return panel;
		return p;
	}

}