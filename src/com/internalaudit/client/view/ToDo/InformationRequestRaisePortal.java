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
import com.internalaudit.shared.Exceptions;
import com.internalaudit.shared.InformationRequestEntity;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;

public class InformationRequestRaisePortal extends VerticalLayoutContainer {

	protected static final int MIN_HEIGHT = 1;
	protected static final int MIN_WIDTH = 1280;
	protected static final int PREFERRED_HEIGHT = 1;
	protected static final int PREFERRED_WIDTH = 1;
	  private ContentPanel panel;
	private static final InformationRequestRaiseProperties properties = GWT.create(InformationRequestRaiseProperties.class);

	private List<InformationRequestRaiseEntity> informationRequests = new ArrayList<InformationRequestRaiseEntity>();

	public InformationRequestRaisePortal(ArrayList<InformationRequestEntity> arrayList) {
		setData(arrayList);
		//setData(exceptions);
		add(createGridFieldWork());

	}

	private void setData(ArrayList<InformationRequestEntity> arrayList) {
		for (int i = 0; i < arrayList.size(); i++) {
			InformationRequestRaiseEntity issue = new InformationRequestRaiseEntity();
			issue.setId(arrayList.get(i).getInformationRequestId());
			issue.setRequestedItem(arrayList.get(i).getRequestItem());
	//		issue.setRelatedJob(arrayList.get(i).getJob().getJobName());
	
			issue.setRaisedTo(arrayList.get(i).getContactResponsible().getEmployeeName());
			issue.setOverDueDays(arrayList.get(i).getDueDate().toString());
			issue.setStatus(arrayList.get(i).getStatus()+"");
			informationRequests.add(issue);
		}	
//		}
	}

	public Widget createGridFieldWork() {

		ColumnConfig<InformationRequestRaiseEntity, Integer> informationId = new ColumnConfig<InformationRequestRaiseEntity, Integer>(properties.id(), 50, "Ir#");
		ColumnConfig<InformationRequestRaiseEntity, String> requestedItem = new ColumnConfig<InformationRequestRaiseEntity, String>(properties.requestedItem(), 190,
				"RequestedItem");
		ColumnConfig<InformationRequestRaiseEntity, String> informationRaisedTo = new ColumnConfig<InformationRequestRaiseEntity, String>(properties.raisedTo(),
				130, "Requested To");
		ColumnConfig<InformationRequestRaiseEntity, String> relatedJob = new ColumnConfig<InformationRequestRaiseEntity, String>(properties.relatedJob(), 130, "Related Job");
		ColumnConfig<InformationRequestRaiseEntity, String> informationOverDue = new ColumnConfig<InformationRequestRaiseEntity, String>(properties.overDueDays(), 180, "Due Date");
		ColumnConfig<InformationRequestRaiseEntity, String> informationStatus = new ColumnConfig<InformationRequestRaiseEntity, String>(properties.status(), 130, "status");

		List<ColumnConfig<InformationRequestRaiseEntity, ?>> columns = new ArrayList<ColumnConfig<InformationRequestRaiseEntity, ?>>();
		columns.add(informationId);
		columns.add(requestedItem);
		columns.add(informationRaisedTo);
		columns.add(relatedJob);
		columns.add(informationOverDue);
		columns.add(informationStatus);
		

		ColumnModel<InformationRequestRaiseEntity> cm = new ColumnModel<InformationRequestRaiseEntity>(columns);

		ListStore<InformationRequestRaiseEntity> store = new ListStore<InformationRequestRaiseEntity>(properties.key());
		store.addAll(informationRequests);

		final Grid<InformationRequestRaiseEntity> grid = new Grid<InformationRequestRaiseEntity>(store, cm);
		//grid.setWidth(600);
		grid.getView().setAutoExpandColumn(informationId);
		grid.getView().setForceFit(true);
		grid.getView().setStripeRows(true);
		grid.getView().setColumnLines(true);
		 ScrollPanel p = new ScrollPanel();
		 p.setHeight("220px");
		
	   p.add(grid);

	
		 VerticalLayoutContainer con = new VerticalLayoutContainer();
		Anchor addInformationRequest = new Anchor("Raise Information Request");
		addInformationRequest.addStyleName("w3-right");
	   con.add(addInformationRequest);
	   
	   addInformationRequest.addClickHandler(new ClickHandler() {
		
		@Override
		public void onClick(ClickEvent event) {
			final InformationRequestRaiserView informationreq = new InformationRequestRaiserView();
			PopupsView pp = new PopupsView(informationreq, "");
			pp.getLabelheading().setText("Information Request");
			pp.getVpnlMain().setTitle("Information Request");
			pp.getVpnlMain().setWidth("550px");
			pp.getHpnlSPace().setWidth("500px");
			pp.getVpnlMain().setHeight("530px");

			
		}
	});
	   
	      con.add(p, new VerticalLayoutData(1, 1));

	      panel = new ContentPanel();
	      panel.setHeight(230);
	      panel.setWidth(850);
	      panel.setHeadingText("InformationRequestRaise");
	      panel.add(con);
	      return panel;
	      
	      
	}
}