package com.internalaudit.client.view;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.google.gwt.cell.client.ClickableTextCell;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.ColumnSortEvent.ListHandler;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;
import com.internalaudit.client.view.data.AuditTrailViewData;
import com.internalaudit.shared.StrategicAudit;


public class AuditnTrail extends Composite  {
	
	private static AuditnTrailUiBinder uiBinder = GWT
			.create(AuditnTrailUiBinder.class);

	interface AuditnTrailUiBinder extends UiBinder<Widget, AuditnTrail> {
	}

	

	@UiField Button refresh;
	@UiField HTMLPanel mainPanel;
	@UiField
	CellTable<StrategicAudit> table;
	@UiField
	SimplePager pager;
	Column<StrategicAudit, String> objectiveName;
	Column<StrategicAudit, String> assignee;
	Column<StrategicAudit, String> initiatedBy;
	Column<StrategicAudit, String> approvar;
	Column<StrategicAudit, String> date;
	Column<StrategicAudit, String> statusColumn;
	Column<StrategicAudit, String> phase;
	
	
	public AuditnTrail() {
		
		table = new CellTable<StrategicAudit>(10);
		initWidget(uiBinder.createAndBindUi(this));
		bind();
	}
	
	public void bind(){
//		mainPanel.add(table);
		setUserTable();
		
		refresh.setStyleName("refreshBtn");
		
		AuditTrailViewData auditTrailViewData = new AuditTrailViewData();
		
		auditTrailViewData.setData(this);
	}
	
	
	private void setUserTable() {
		
		table.setEmptyTableWidget(new HTML("No Record found"));
		table.setRowCount(0);

		objectiveName = new Column<StrategicAudit, String>(new ClickableTextCell()) {
			@Override
			public String getValue(StrategicAudit object) {
				return object.getStrategicObjective();
			}
		};
		objectiveName.setSortable(true);
		table.addColumn(objectiveName,"Objective Name");
		
		initiatedBy = new Column<StrategicAudit, String>(new TextCell()) {
			@Override
			public String getValue(StrategicAudit object) {
				return object.getInitiatedBy().getEmployeeName();
			}
		};
		initiatedBy.setSortable(true);
		table.addColumn(initiatedBy,"Initiated By");
		
		assignee = new Column<StrategicAudit, String>(new TextCell()) {
			@Override
			public String getValue(StrategicAudit object) {
				if(object.getInitiatedBy().getEmployeeName().equals(object.getAssignedTo().getEmployeeName())){
					return "";
				}else{
				return object.getAssignedTo().getEmployeeName();
				}
			}
		};
		assignee.setSortable(true);
		table.addColumn(assignee,"AssignedTo");

//		assignee = new Column<StrategicAudit, String>(new TextCell()) {
//			@Override
//			public String getValue(StrategicAudit object) {
//				return object.getAssignedTo().getEmployeeName();
//			}
//		};
//		assignee.setSortable(true);
//		table.addColumn(assignee,"AssignedTo");
		
		approvar = new Column<StrategicAudit, String>(new TextCell()) {
			@Override
			public String getValue(StrategicAudit object) {
				if(object.getApprovedBy().getEmployeeName().equalsIgnoreCase("no user")){
					return "";
				}else{
				return object.getApprovedBy().getEmployeeName();
				}
			}
		};
		approvar.setSortable(true);
		table.addColumn(approvar,"Approved By");
		
		phase = new Column<StrategicAudit, String>(new TextCell()) {
			@Override
			public String getValue(StrategicAudit object) {
				if(object.getPhase().equals("1")){
					return "Audit Identification";
				}else if(object.getPhase().equals("2")){
					return "Risk Assesment";
				}else if(object.getPhase().equals("3")){
					return "Consolidation";
				}else if(object.getPhase().equals("4")){
					return "Risk Prioritization";
				}else
				return "Final Auditables";
			}
		};
		phase.setSortable(true);
		table.addColumn(phase,"Phase");
		
		statusColumn = new Column<StrategicAudit, String>(new TextCell()) {
			@Override
			public String getValue(StrategicAudit object) {
				return object.getStatus();
			}
		};
		table.addColumn(statusColumn,"Status");
		statusColumn.setSortable(true);
		
		date = new Column<StrategicAudit, String>(new TextCell()) {
			@Override
			public String getValue(StrategicAudit object) {
				return object.getDate().toLocaleString();
			}
		};
		table.addColumn(date,"Date");
		date.setSortable(true);
//		table.addColumn(dateLastAmmendedColumn,TieConstants.DATEAMMENDED_COLUMNN);
		
		
		
		
	}
	
	public void populateStrategicAudit(ArrayList<StrategicAudit> strategicAudits) {
		table.setRowData(0, strategicAudits);
		table.setRowCount(strategicAudits.size());
		pager.setDisplay(table);
		sortTableGroup(strategicAudits);
	}

	

	public Column<StrategicAudit, String> getGroupNameColumn() {
		return objectiveName;
	}

	public void setGroupNameColumn(Column<StrategicAudit, String> objectiveName) {
		this.objectiveName = objectiveName;
	}


	
	private void sortTableGroup(List<StrategicAudit> groupList){
		ListDataProvider<StrategicAudit> dataProvider = new ListDataProvider<StrategicAudit>();
		dataProvider.addDataDisplay(table);

		List<StrategicAudit> list = dataProvider.getList();

		for (StrategicAudit group : groupList) {
		list.add(group);
		}
		final ListHandler<StrategicAudit> columnSortHandler = new ListHandler<StrategicAudit>(list);

		columnSortHandler.setComparator(objectiveName,new Comparator<StrategicAudit>() {

		public int compare(StrategicAudit o1,StrategicAudit o2) {
		if (o1 == o2) {
		return 0;
		}

		// Compare the groupname columns.
		if (o1 != null) {

		return (o2 != null) ? o1.getStrategicObjective().compareTo(o2.getStrategicObjective()) : 1;
		}
		return -1;
		}
		});
		
		columnSortHandler.setComparator(assignee,new Comparator<StrategicAudit>() {

			public int compare(StrategicAudit o1,StrategicAudit o2) {
			if (o1 == o2) {
			return 0;
			}

			// Compare the groupname columns.
			if (o1 != null) {

			return (o2 != null) ? o1.getAssignedTo().getEmployeeName().compareTo(o2.getAssignedTo().getEmployeeName()) : 1;
			}
			return -1;
			}
			});
		
		columnSortHandler.setComparator(approvar,new Comparator<StrategicAudit>() {

			public int compare(StrategicAudit o1,StrategicAudit o2) {
			if (o1 == o2) {
			return 0;
			}

			// Compare the groupname columns.
			if (o1 != null) {

			return (o2 != null) ? o1.getApprovedBy().getEmployeeName().compareTo(o2.getApprovedBy().getEmployeeName()) : 1;
			}
			return -1;
			}
			});
		
		columnSortHandler.setComparator(phase,new Comparator<StrategicAudit>() {

			public int compare(StrategicAudit o1,StrategicAudit o2) {
			if (o1 == o2) {
			return 0;
			}

			// Compare the groupname columns.
			if (o1 != null) {

			return (o2 != null) ? o1.getPhase().compareTo(o2.getPhase()) : 1;
			}
			return -1;
			}
			});
		
		//
		columnSortHandler.setComparator(statusColumn,new Comparator<StrategicAudit>() {

			public int compare(StrategicAudit o1,StrategicAudit o2) {
			if (o1 == o2) {
			return 0;
			}

			// Compare the status columns.
			if (o1 != null) {

			return (o2 != null) ? o1.getStatus().compareTo(o2.getStatus()) : 1;
			}
			return -1;
			}
			});
		//
		
		columnSortHandler.setComparator(date,new Comparator<StrategicAudit>() {

			public int compare(StrategicAudit o1,StrategicAudit o2) {
			if (o1 == o2) {
			return 0;
			}

			// Compare the status columns.
			if (o1 != null) {

			return (o2 != null) ? o1.getDate().compareTo(o2.getDate()) : 1;
			}
			return -1;
			}
			});
		
		
		
		table.addColumnSortHandler(columnSortHandler);
		table.getColumnSortList().push(objectiveName);
		table.getColumnSortList().push(assignee);
		table.getColumnSortList().push(approvar);
		table.getColumnSortList().push(date);
		table.getColumnSortList().push(initiatedBy);
		table.getColumnSortList().push(date);
		table.getColumnSortList().push(statusColumn);
		table.getColumnSortList().push(phase);
		
	}

	public Button getRefresh() {
		return refresh;
	}

	public void setRefresh(Button refresh) {
		this.refresh = refresh;
	}

	

	


}
