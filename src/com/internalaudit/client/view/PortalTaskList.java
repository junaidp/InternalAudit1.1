package com.internalaudit.client.view;
import java.util.ArrayList;
import java.util.List;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;
import com.internalaudit.shared.TaskRiskEntity;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;

public class PortalTaskList extends VerticalLayoutContainer {

	protected static final int MIN_HEIGHT = 1;
	protected static final int MIN_WIDTH = 1280;
	protected static final int PREFERRED_HEIGHT = 1;
	protected static final int PREFERRED_WIDTH = 1;
	  private ContentPanel panel;
	private static final TaskRiskProperties properties = GWT.create(TaskRiskProperties.class);

	private List<TaskRiskEntity> informationRequests = new ArrayList<TaskRiskEntity>();

	public PortalTaskList() {
		//setData(arrayList);
		//setData(exceptions);
		setData();
		add(createGridFieldWork());

	}

//	private void setData(ArrayList<InformationRequestEntity> arrayList) {
//		for (int i = 0; i < arrayList.size(); i++) {
//			InformationRequest issue = new InformationRequest();
//			//issue.setId(exceptions.get(i).getExceptionId());
//			issue.setId(arrayList.get(i).getInformationRequestId());
//			issue.setInformationReport(arrayList.get(i).getRequestItem());
//			issue.setRaisedBy(arrayList.get(i).getAssignedFrom().getEmployeeName());
//			issue.setRaisedTo(arrayList.get(i).getContactResponsible().getEmployeeName());
//			issue.setOverDueDays(arrayList.get(i).getDueDate().toString());
//			informationRequests.add(issue);
//		}	
//	}
//	}


	public Widget createGridFieldWork() {

		ColumnConfig<TaskRiskEntity, Integer> idHeading= new ColumnConfig<TaskRiskEntity, Integer>(properties.id(), 70, "ID");
		ColumnConfig<TaskRiskEntity, String> taskHeading = new ColumnConfig<TaskRiskEntity, String>(properties.task(), 190,
				"Task");
		ColumnConfig<TaskRiskEntity, String> jobHeading = new ColumnConfig<TaskRiskEntity, String>(properties.job(), 130, "Job");
		ColumnConfig<TaskRiskEntity, String> assignedByHeading = new ColumnConfig<TaskRiskEntity, String>(properties.raisedBy(), 130, "Assigned By");
		ColumnConfig<TaskRiskEntity, String> dueDateHeading = new ColumnConfig<TaskRiskEntity, String>(properties.overDueDays(), 180, "Due Day");
		ColumnConfig<TaskRiskEntity, String> statusHeading = new ColumnConfig<TaskRiskEntity, String>(properties.status(),
				130, "Status");
		ColumnConfig<TaskRiskEntity, String> resolutionHeading = new ColumnConfig<TaskRiskEntity, String>(properties.resolution(),
				130, "Resolution");
		//ButtonRound b = new ButtonRound("a");
		//resolutionHeading.setCell((Cell<String>) b);
	//	  dateCell.setPropertyEditor(new DateTimePropertyEditor(DateTimeFormat.getFormat(PredefinedFormat.DATE_SHORT)));

		List<ColumnConfig<TaskRiskEntity, ?>> columns = new ArrayList<ColumnConfig<TaskRiskEntity, ?>>();
		columns.add(idHeading);
		columns.add(taskHeading);
		columns.add(jobHeading);
		columns.add(assignedByHeading);
		columns.add(dueDateHeading);
		columns.add(statusHeading);
		columns.add(resolutionHeading);
		

		ColumnModel<TaskRiskEntity> cm = new ColumnModel<TaskRiskEntity>(columns);

		ListStore<TaskRiskEntity> store = new ListStore<TaskRiskEntity>(properties.key());
		store.addAll(informationRequests);

		final Grid<TaskRiskEntity> grid = new Grid<TaskRiskEntity>(store, cm);
		//grid.setWidth(600);
		grid.getView().setAutoExpandColumn(idHeading);
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
	      panel.setWidth(1100);
	    //  panel.setHeadingText("InformationRequest");
	      panel.add(con);
	      return panel;
	}
	private void setData(){
TaskRiskEntity t = new TaskRiskEntity();
t.setId(1);
		t.setJob("job");
		t.setRaisedBy("raised");
		t.setStatus("satatus");
		t.setResolution("r");

	}
}