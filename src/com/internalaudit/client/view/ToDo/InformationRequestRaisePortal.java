package com.internalaudit.client.view.ToDo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gwt.cell.client.Cell.Context;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;
import com.internalaudit.client.view.PopupsView;
import com.internalaudit.shared.InformationRequestEntity;
import com.sencha.gxt.cell.core.client.TextButtonCell;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;

public class InformationRequestRaisePortal extends VerticalLayoutContainer {

	protected static final int MIN_HEIGHT = 1;
	protected static final int MIN_WIDTH = 1280;
	protected static final int PREFERRED_HEIGHT = 1;
	protected static final int PREFERRED_WIDTH = 1;
	private ContentPanel panel;
	private static final InformationRequestRaiseProperties properties = GWT
			.create(InformationRequestRaiseProperties.class);
	TextButtonCell button = new TextButtonCell();
	ListStore<InformationRequestRaiseEntity> store;
	private List<InformationRequestRaiseEntity> informationRequests = new ArrayList<InformationRequestRaiseEntity>();

	public InformationRequestRaisePortal(ArrayList<InformationRequestEntity> arrayList) {
		setData(arrayList);
		// setData(exceptions);
		add(createGridFieldWork());

	}

	private void setData(ArrayList<InformationRequestEntity> arrayList) {
		for (int i = 0; i < arrayList.size(); i++) {
			InformationRequestRaiseEntity issue = new InformationRequestRaiseEntity();
			issue.setId(arrayList.get(i).getInformationRequestId());
			issue.setRequestedItem(arrayList.get(i).getRequestItem());
			issue.setRelatedJob(arrayList.get(i).getJob().getJobName());
			issue.setRaisedTo(arrayList.get(i).getContactResponsible().getEmployeeName());
			issue.setOverDueDays(arrayList.get(i).getDueDate());
			issue.setRaisedBy(arrayList.get(i).getAssignedFrom().getEmployeeName());
			issue.setStatus(arrayList.get(i).getStatus() + "");
			issue.setReply(arrayList.get(i).getRespond());
			issue.setRaisedById(arrayList.get(i).getAssignedFrom().getEmployeeId());
			issue.setRaisedToId(arrayList.get(i).getContactResponsible().getEmployeeId());
			issue.setRelatedJobId(arrayList.get(i).getJob().getJobCreationId());
			issue.setContactEmail(arrayList.get(i).getContactEmail());
			issue.setSstatus(arrayList.get(i).getStatus());
			issue.setSendNotification(arrayList.get(i).getSendReminder());
			issue.setSendReminder(arrayList.get(i).getSendReminder());
			issue.setInformationRequestLogList(arrayList.get(i).getInformationRequestLogList());
			informationRequests.add(issue);
		}
		// }
	}

	public Widget createGridFieldWork() {

		ColumnConfig<InformationRequestRaiseEntity, Integer> informationId = new ColumnConfig<InformationRequestRaiseEntity, Integer>(
				properties.id(), 70, "IR#");
		ColumnConfig<InformationRequestRaiseEntity, String> requestedItem = new ColumnConfig<InformationRequestRaiseEntity, String>(
				properties.requestedItem(), 190, "Requested Item");
		ColumnConfig<InformationRequestRaiseEntity, String> informationRaisedTo = new ColumnConfig<InformationRequestRaiseEntity, String>(
				properties.raisedTo(), 130, "Requested To");
		ColumnConfig<InformationRequestRaiseEntity, String> relatedJob = new ColumnConfig<InformationRequestRaiseEntity, String>(
				properties.relatedJob(), 100, "Related Job");
		ColumnConfig<InformationRequestRaiseEntity, Date> informationOverDue = new ColumnConfig<InformationRequestRaiseEntity, Date>(
				properties.overDueDays(), 160, "Due Date");
		ColumnConfig<InformationRequestRaiseEntity, String> informationStatus = new ColumnConfig<InformationRequestRaiseEntity, String>(
				properties.status(), 100, "Status");
		ColumnConfig<InformationRequestRaiseEntity, String> viewButton = new ColumnConfig<InformationRequestRaiseEntity, String>(
				properties.viewButton(), 100, "");

		button.setText("view");

		button.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				Context c = event.getContext();
				int row = c.getIndex();
				InformationRequestRaiseEntity informationRequest = store.get(row);
				InformationRequestRaiserFinalView infoReceiver = new InformationRequestRaiserFinalView(
						informationRequest);
				final PopupsView pp = new PopupsView(infoReceiver, "InformationRequest Receiver");
				// pp.getLabelheading().setText("InformationRequest Receiver");
				pp.getVpnlMain().setTitle("Todos");
				pp.getVpnlMain().setWidth("600px");
				pp.getHpnlSPace().setWidth("600px");
				pp.getVpnlMain().setHeight("530px");
				infoReceiver.getBtnCancel().addClickHandler(new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						pp.getVpnlMain().removeFromParent();
						pp.getPopup().removeFromParent();

					}
				});

				// Info.display("Event", "The " + p.getRequestedItem() + " was
				// clicked.");
			}
		});

		viewButton.setCell(button);
		List<ColumnConfig<InformationRequestRaiseEntity, ?>> columns = new ArrayList<ColumnConfig<InformationRequestRaiseEntity, ?>>();
		columns.add(informationId);
		columns.add(requestedItem);
		columns.add(informationRaisedTo);
		columns.add(relatedJob);
		columns.add(informationOverDue);
		columns.add(informationStatus);
		columns.add(viewButton);

		ColumnModel<InformationRequestRaiseEntity> cm = new ColumnModel<InformationRequestRaiseEntity>(columns);

		store = new ListStore<InformationRequestRaiseEntity>(properties.key());
		store.addAll(informationRequests);

		final Grid<InformationRequestRaiseEntity> grid = new Grid<InformationRequestRaiseEntity>(store, cm);
		// grid.setWidth(600);
		// grid.getView().setAutoExpandColumn(informationId);
		// grid.getView().setForceFit(true);
		// grid.getView().setStripeRows(true);
		// grid.getView().setColumnLines(true);
		ScrollPanel p = new ScrollPanel();
		// p.setHeight("220px");
		p.setHeight("400px");
		p.add(grid);

		VerticalLayoutContainer con = new VerticalLayoutContainer();
		Anchor addInformationRequest = new Anchor("Raise Information Request");
		addInformationRequest.addStyleName("w3-right");
		con.add(addInformationRequest);

		addInformationRequest.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				final InformationRequestRaiserView informationrequestRaiser = new InformationRequestRaiserView();
				final PopupsView pp = new PopupsView(informationrequestRaiser, "Information Reques");
				// pp.getLabelheading().setText("Information Request");
				// pp.getPopup().setHeadingText("Information Request");
				pp.getVpnlMain().setTitle("Information Request");
				pp.getVpnlMain().setWidth("650px");
				pp.getHpnlSPace().setWidth("600px");
				pp.getVpnlMain().setHeight("530px");
				pp.getClose().addClickHandler(new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						informationrequestRaiser.deleteUnssavedAttachments();
					}
				});
				informationrequestRaiser.getBtnCancel().addClickHandler(new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						pp.getVpnlMain().removeFromParent();
						pp.getPopup().removeFromParent();

					}
				});

			}
		});

		con.add(p, new VerticalLayoutData(1, 1));
		return con;
		// panel = new ContentPanel();
		// panel.setHeight(230);
		// panel.setWidth(850);
		// panel.setHeadingText("InformationRequestRaise");
		// panel.add(con);
		// return panel;

	}
}