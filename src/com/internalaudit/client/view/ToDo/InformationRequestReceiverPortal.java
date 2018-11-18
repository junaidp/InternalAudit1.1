package com.internalaudit.client.view.ToDo;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;
import com.internalaudit.client.view.PopupsView;
import  com.google.gwt.cell.client.Cell.Context;
import com.internalaudit.shared.Employee;
import com.internalaudit.shared.InformationRequestEntity;
import com.sencha.gxt.cell.core.client.TextButtonCell;
import com.sencha.gxt.chart.client.draw.sprite.TextSprite.TextAnchor;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.info.Info;

public class InformationRequestReceiverPortal extends VerticalLayoutContainer {

	protected static final int MIN_HEIGHT = 1;
	protected static final int MIN_WIDTH = 1280;
	protected static final int PREFERRED_HEIGHT = 1;
	protected static final int PREFERRED_WIDTH = 1;
	  private ContentPanel panel;
	private static final InformationRequestReceiverProperties properties = GWT.create(InformationRequestReceiverProperties.class);
	TextButtonCell button = new TextButtonCell() ;
	ListStore<InformationRequestReceiverEntity> store ;

	private List<InformationRequestReceiverEntity> informationRequests = new ArrayList<InformationRequestReceiverEntity>();

	public InformationRequestReceiverPortal(ArrayList<InformationRequestEntity> arrayList) {
		setData(arrayList);
		//setData(exceptions);
		add(createGridFieldWork());

	}
	
		private void setData(ArrayList<InformationRequestEntity> arrayList) {
			
			for (int i = 0; i < arrayList.size(); i++) {
				final InformationRequestEntity  infoReq= arrayList.get(i);
				InformationRequestReceiverEntity issue = new InformationRequestReceiverEntity();
				//issue.setId(exceptions.get(i).getExceptionId());
				
				issue.setId(arrayList.get(i).getInformationRequestId());
				issue.setRequestedItem(arrayList.get(i).getRequestItem());
				issue.setRelatedJob(arrayList.get(i).getJob().getJobName());
				issue.setRaisedBy(arrayList.get(i).getContactResponsible().getEmployeeName());
				issue.setRaiseById(arrayList.get(i).getAssignedFrom().getEmployeeId());
				issue.setRaisedToId(arrayList.get(i).getContactResponsible().getEmployeeId());
				issue.setContactEmail(arrayList.get(i).getContactEmail());
				issue.setSstatus(arrayList.get(i).getStatus());
				issue.setSendNotification(arrayList.get(i).getSendReminder());
				issue.setSendReminder(arrayList.get(i).getSendReminder());
				issue.setRelatedJobId(arrayList.get(i).getJob().getJobCreationId());
				issue.setOverDueDays(arrayList.get(i).getDueDate());
				informationRequests.add(issue);
				
				}	
//			}
	}

	public Widget createGridFieldWork() {

		ColumnConfig<InformationRequestReceiverEntity, Integer> informationId = new ColumnConfig<InformationRequestReceiverEntity, Integer>(properties.id(), 50, "Ir#");
		ColumnConfig<InformationRequestReceiverEntity, String> requestedItem = new ColumnConfig<InformationRequestReceiverEntity, String>(properties.requestedItem(), 180,
				"RequestedItem");
		ColumnConfig<InformationRequestReceiverEntity, String> informationRaisedBy = new ColumnConfig<InformationRequestReceiverEntity, String>(properties.raisedBy(),
				130, "Requested By");
		ColumnConfig<InformationRequestReceiverEntity, String> relatedJob = new ColumnConfig<InformationRequestReceiverEntity, String>(properties.relatedJob(), 130, "Related Job");
		ColumnConfig<InformationRequestReceiverEntity, Date> informationOverDue = new ColumnConfig<InformationRequestReceiverEntity, Date>(properties.overDueDays(), 160, "Due Date");
		ColumnConfig<InformationRequestReceiverEntity, String> informationStatus = new ColumnConfig<InformationRequestReceiverEntity, String>(properties.status(), 110, "status");
		ColumnConfig<InformationRequestReceiverEntity, String> viewButton = new ColumnConfig<InformationRequestReceiverEntity, String>(properties.viewButton(), 100, "");
	

		
		      button.setText("view");
		      
		    
		      
		      button.addSelectHandler(new SelectHandler() {
		          @Override
		          public void onSelect(SelectEvent event) {
		            Context c = event.getContext();
		            int row = c.getIndex();
		            InformationRequestReceiverEntity informationRequest = store.get(row);
		            InformationRequestReceiveView infoReceiver = new InformationRequestReceiveView(informationRequest);
					PopupsView pp = new PopupsView(infoReceiver, "");
					pp.getLabelheading().setText("InformationRequest Receiver");
					pp.getVpnlMain().setTitle("Todos");
					pp.getVpnlMain().setWidth("600px");
					pp.getHpnlSPace().setWidth("600px");
					pp.getVpnlMain().setHeight("530px");
		            
		           // Info.display("Event", "The " + p.getRequestedItem() + " was clicked.");
		          }
		        });
		
		     
		     
		viewButton.setCell(button);
		List<ColumnConfig<InformationRequestReceiverEntity, ?>> columns = new ArrayList<ColumnConfig<InformationRequestReceiverEntity, ?>>();
		columns.add(informationId);
		columns.add(requestedItem);
		columns.add(informationRaisedBy);
		columns.add(relatedJob);
		columns.add(informationOverDue);
		columns.add(informationStatus);
		columns.add(viewButton);
		
	
		
		
		ColumnModel<InformationRequestReceiverEntity> cm = new ColumnModel<InformationRequestReceiverEntity>(columns);

		 store = new ListStore<InformationRequestReceiverEntity>(properties.key());
		store.addAll(informationRequests);

		final Grid<InformationRequestReceiverEntity> grid = new Grid<InformationRequestReceiverEntity>(store, cm);
		//grid.setWidth(600);
		grid.getView().setAutoExpandColumn(informationId);
		grid.getView().setForceFit(true);
		grid.getView().setStripeRows(true);
		grid.getView().setColumnLines(true);
		 ScrollPanel p = new ScrollPanel();
		 p.setHeight("220px");
		
	   p.add(grid);

	
		 VerticalLayoutContainer con = new VerticalLayoutContainer();
		Anchor anchorView = new Anchor("view");
		con.add(anchorView);
		anchorView.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				final InformationRequestReceiveView emailView = new InformationRequestReceiveView(null);
				PopupsView pp = new PopupsView(emailView, "");
				pp.getLabelheading().setText("Information Request Receiver Views");
				pp.getVpnlMain().setTitle("Info Req Receiver");
				pp.getVpnlMain().setWidth("900px");
				pp.getHpnlSPace().setWidth("900px");
				pp.getVpnlMain().setHeight("530px");

				
			}
		});
		
	   
	      con.add(p, new VerticalLayoutData(1, 1));

	      panel = new ContentPanel();
	      panel.setHeight(230);
	      panel.setWidth(850);
	      panel.setHeadingText("InformationRequestReceiver");
	      panel.add(con);
	      return panel;
	}

}