package com.internalaudit.client.view.ToDo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.Cell.Context;
import com.google.gwt.cell.client.DateCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.TextDecoration;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.internalaudit.client.InternalAuditService;
import com.internalaudit.client.InternalAuditServiceAsync;
import com.internalaudit.client.view.DisplayAlert;
import com.internalaudit.client.view.PopupsView;
import com.internalaudit.shared.Employee;
import com.internalaudit.shared.InformationRequestEntity;
import com.internalaudit.shared.JobCreation;
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
	private String formattedDate;
	private static final InformationRequestRaiseProperties properties = GWT
			.create(InformationRequestRaiseProperties.class);
	TextButtonCell button = new TextButtonCell();
	ListStore<InformationRequestRaiseEntity> store;
	private List<InformationRequestRaiseEntity> informationRequests = new ArrayList<InformationRequestRaiseEntity>();
	private InternalAuditServiceAsync rpcService;
	private String filepath;
	private VerticalPanel con = new VerticalPanel();
	private PopupsView pp;

	public InformationRequestRaisePortal(ArrayList<InformationRequestEntity> arrayList) {
		setData(arrayList);
		// setData(exceptions);
		add(createGridFieldWork());
		rpcService = GWT.create(InternalAuditService.class);
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
				properties.raisedTo(), 150, "Requested To");
		ColumnConfig<InformationRequestRaiseEntity, String> relatedJob = new ColumnConfig<InformationRequestRaiseEntity, String>(
				properties.relatedJob(), 130, "Related Job");
		ColumnConfig<InformationRequestRaiseEntity, Date> informationOverDue = new ColumnConfig<InformationRequestRaiseEntity, Date>(
				properties.overDueDays(), 110, "Due Date");
		Cell cellDueDate = new DateCell(DateTimeFormat.getFormat("MM/dd/yy"));
		informationOverDue.setCell(cellDueDate);
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
				pp = new PopupsView(infoReceiver, "Information Request Raiser");
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
		// VerticalPanel p = new VerticalPanel();
		grid.setHeight("220px");
		// p.setHeight("400px");

		// VerticalLayoutContainer con = new VerticalLayoutContainer();
		Anchor addInformationRequest = new Anchor("Raise Information Request");
		addInformationRequest.addStyleName("w3-right");
		con.add(addInformationRequest);
		con.add(grid);
		con.setWidth("875px");

		addInformationRequest.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				final InformationRequestRaiserView informationrequestRaiser = new InformationRequestRaiserView();
				final PopupsView pp = new PopupsView(informationrequestRaiser, "Information Request");
				// pp.getLabelheading().setText("Information Request");
				// pp.getPopup().setHeadingText("Information Request");
				pp.getVpnlMain().setTitle("Information Request");
				pp.getVpnlMain().setWidth("380px");
				pp.getHpnlSPace().setWidth("380px");
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
				informationrequestRaiser.getBtnSave().addClickHandler(new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						// TODO Auto-generated method stub
						refreshFile(informationrequestRaiser.getInformationRequestId(),
								informationrequestRaiser.getMainFolder());
						saveInformationRequest(informationrequestRaiser, filepath);
						pp.getVpnlMain().removeFromParent();
						pp.getPopup().removeFromParent();
					}
				});
			}
		});
		// con.add(p, new VerticalLayoutData(1, 1));
		// return con;
		// panel = new ContentPanel();
		// panel.setHeight(230);
		// panel.setWidth(850);
		// panel.setHeadingText("InformationRequestRaise");
		// panel.add(con);
		// return panel;
		return con;
	}

	private void saveInformationRequest(InformationRequestRaiserView informationrequestRaiser, String filepath) {
		InformationRequestEntity informationrequest = new InformationRequestEntity();
		informationrequest.setRequestItem(informationrequestRaiser.getTxtBoxRequestItem().getText());
		Employee responsibleContact = new Employee();
		responsibleContact
				.setEmployeeId(Integer.parseInt(informationrequestRaiser.getListBoxContact().getSelectedValue()));
		JobCreation job = new JobCreation();
		job.setJobCreationId(Integer.parseInt(informationrequestRaiser.getListBoxJobs().getSelectedValue()));
		informationrequest.setContactResponsible(responsibleContact);

		informationrequest.setJob(job);
		informationrequest.setContactEmail(informationrequestRaiser.getTxtBoxEmail().getText());
		informationrequest.setSendNotication(informationrequestRaiser.getCheckBoxNotification().getValue());
		informationrequest.setSendReminder(informationrequestRaiser.getCheckBoxReminder().getValue());
		informationrequest.setDueDate(informationrequestRaiser.getDueDate().getValue());
		informationrequest.setStatus(informationrequestRaiser.getListBoxStatus().getSelectedIndex());
		informationrequest.setRead(false);
		rpcService.saveinformationRequest(informationrequest, filepath, new AsyncCallback<String>() {

			@Override
			public void onSuccess(String result) {
				new DisplayAlert(result);
				Window.alert(result + "success");
				fetchInformationRequestReLoad();

			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("error in rpc saveInformationRequest" + caught.getLocalizedMessage());

			}
		});
	}

	private void refreshFile(final String informationRequestId, final String mainFolder) {
		rpcService.fetchAuditStepsProcerdure(informationRequestId, mainFolder, new AsyncCallback<ArrayList<String>>() {

			@Override
			public void onSuccess(ArrayList<String> result) {
				Anchor lblfilename = null;
				for (int i = 0; i < result.size(); i++) {
					lblfilename = new Anchor(result.get(i));

					lblfilename.addStyleName("pointerStyle");
					lblfilename.getElement().getStyle().setTextDecoration(TextDecoration.NONE);
					lblfilename.setHeight("25px");

					lblfilename.setWordWrap(false);
					String upperCasedJobLink = lblfilename.getText();
					lblfilename.setText(upperCasedJobLink);

				}
				filepath = mainFolder + "/" + informationRequestId + "/" + lblfilename.getText();

			}

			@Override
			public void onFailure(Throwable caught) {

				System.out.println("fetchAuditProcedure Failed");
			}

		});

	}

	public void fetchInformationRequestReLoad() {
		pp.getVpnlMain().removeFromParent();
		pp.getPopup().removeFromParent();
		rpcService.fetchInformationRequestReLoad(new AsyncCallback<ArrayList<InformationRequestEntity>>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				Window.alert("failed to add new Information Request in Updated list");
			}

			@Override
			public void onSuccess(ArrayList<InformationRequestEntity> result) {
				// TODO Auto-generated method stub
				updatedView(result);
				// Window.alert("Successfully added new Information Request in
				// Updated list");
			}
		});
	}

	private void updatedView(ArrayList<InformationRequestEntity> toDosUpdated) {
		store.clear();
		informationRequests.clear();
		con.clear();
		setData(toDosUpdated);
		add(createGridFieldWork());
	}
}