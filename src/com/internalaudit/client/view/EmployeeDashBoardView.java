package com.internalaudit.client.view;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.google.gwt.cell.client.ClickableTextCell;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.ColumnSortEvent.ListHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;
import com.internalaudit.client.view.data.AuditTrailViewData;
import com.internalaudit.client.view.data.EmployeeDashBoardViewData;
import com.internalaudit.shared.DashBoardDTO;
import com.internalaudit.shared.DashBoardDTO;
import com.internalaudit.shared.DashBoardDTO;

public class EmployeeDashBoardView extends Composite {

	private static EmployeeDashBoardViewUiBinder uiBinder = GWT
			.create(EmployeeDashBoardViewUiBinder.class);

	interface EmployeeDashBoardViewUiBinder extends
			UiBinder<Widget, EmployeeDashBoardView> {
	}

	@UiField Image refresh;
	@UiField
	CellTable<DashBoardDTO> table;
	@UiField
	SimplePager pager;
	Column<DashBoardDTO, String> objectiveName;
	Column<DashBoardDTO, String> assignee;
	Column<DashBoardDTO, String> initiatedBy;
	Column<DashBoardDTO, String> approvar;
	Column<DashBoardDTO, String> date;
	Column<DashBoardDTO, String> statusColumn;
	Column<DashBoardDTO, String> phase;
	
	
	public EmployeeDashBoardView() {
		
		table = new CellTable<DashBoardDTO>(10);
		initWidget(uiBinder.createAndBindUi(this));
		bind();
		refresh.setUrl("refresh.png");
		refresh.setStyleName("pointerStyle");
	}
	
	public void bind(){
//		mainPanel.add(table);
		setUserTable();
		EmployeeDashBoardViewData employeeDashBoardViewData = new EmployeeDashBoardViewData();
		
		employeeDashBoardViewData.setData(this);
	}
	
	
	private void setUserTable() {
		
		table.setEmptyTableWidget(new HTML("No Record found"));
		table.setRowCount(0);

		objectiveName = new Column<DashBoardDTO, String>(new ClickableTextCell()) {
			@Override
			public String getValue(DashBoardDTO object) {
				return object.getObjectiveName();
			}
		};
		objectiveName.setSortable(true);
		table.addColumn(objectiveName,"Name");
		
		initiatedBy = new Column<DashBoardDTO, String>(new TextCell()) {
			@Override
			public String getValue(DashBoardDTO object) {
				return object.getInitiatedBy();
			}
		};
		initiatedBy.setSortable(true);
		table.addColumn(initiatedBy,"Initiated by");
		
//		assignee = new Column<DashBoardDTO, String>(new TextCell()) {
//			@Override
//			public String getValue(DashBoardDTO object) {
//				
//				return object.getAssignedTo().getEmployeeName();
//				
//			}
//		};
//		assignee.setSortable(true);
//		table.addColumn(assignee,"Assigned to");

//		assignee = new Column<DashBoardDTO, String>(new TextCell()) {
//			@Override
//			public String getValue(DashBoardDTO object) {
//				return object.getAssignedTo().getEmployeeName();
//			}
//		};
//		assignee.setSortable(true);
//		table.addColumn(assignee,"AssignedTo");
		
//		approvar = new Column<DashBoardDTO, String>(new TextCell()) {
//			@Override
//			public String getValue(DashBoardDTO object) {
//				if(object.getApprovedBy().getEmployeeName().equalsIgnoreCase("no user")){
//					return "";
//				}else{
//				return object.getApprovedBy().getEmployeeName();
//				}
//			}
//		};
//		approvar.setSortable(true);
//		table.addColumn(approvar,"Approved by");
		
		phase = new Column<DashBoardDTO, String>(new TextCell()) {
			@Override
			public String getValue(DashBoardDTO object) {
				if(object.getPhase().equals("1")){
					return "Audit Identification";
				}else if(object.getPhase().equals("2")){
					return "Risk Assesment";
				}else if(object.getPhase().equals("3")){
					return "Consolidation";
				}else if(object.getPhase().equals("4")){
					return "Risk Prioritization";
				}
				else if(object.getPhase().equals("5")){
					return "Final Auditables";
				}
				else
				return object.getPhase();
			}
		};
		phase.setSortable(true);
		table.addColumn(phase,"Phase");
		
		statusColumn = new Column<DashBoardDTO, String>(new TextCell()) {
			@Override
			public String getValue(DashBoardDTO object) {
				return object.getStatus();
			}
		};
		table.addColumn(statusColumn,"Status");
		statusColumn.setSortable(true);
		
//		date = new Column<DashBoardDTO, String>(new TextCell()) {
//			@Override
//			public String getValue(DashBoardDTO object) {
//				return object.getDate().toLocaleString();
//			}
//		};
//		table.addColumn(date,"Date");
//		date.setSortable(true);
//		table.addColumn(dateLastAmmendedColumn,TieConstants.DATEAMMENDED_COLUMNN);
		
		
		
		
	}
	
	public void populateDashBoard(ArrayList<DashBoardDTO> dashBoard) {
		table.setRowData(0, dashBoard);
		table.setRowCount(dashBoard.size());
		pager.setDisplay(table);
//		sortTableGroup(dashBoard);
	}

	

	public Column<DashBoardDTO, String> getGroupNameColumn() {
		return objectiveName;
	}

	public void setGroupNameColumn(Column<DashBoardDTO, String> objectiveName) {
		this.objectiveName = objectiveName;
	}


	
//	private void sortTableGroup(List<DashBoardDTO> groupList){
//		ListDataProvider<DashBoardDTO> dataProvider = new ListDataProvider<DashBoardDTO>();
//		dataProvider.addDataDisplay(table);
//
//		List<DashBoardDTO> list = dataProvider.getList();
//
//		for (DashBoardDTO group : groupList) {
//		list.add(group);
//		}
//		final ListHandler<DashBoardDTO> columnSortHandler = new ListHandler<DashBoardDTO>(list);
//
//		columnSortHandler.setComparator(objectiveName,new Comparator<DashBoardDTO>() {
//
//		public int compare(DashBoardDTO o1,DashBoardDTO o2) {
//		if (o1 == o2) {
//		return 0;
//		}
//
//		// Compare the groupname columns.
//		if (o1 != null) {
//
//		return (o2 != null) ? o1.getStrategicObjective().compareTo(o2.getStrategicObjective()) : 1;
//		}
//		return -1;
//		}
//		});
//		
//		columnSortHandler.setComparator(assignee,new Comparator<DashBoardDTO>() {
//
//			public int compare(DashBoardDTO o1,DashBoardDTO o2) {
//			if (o1 == o2) {
//			return 0;
//			}
//
//			// Compare the groupname columns.
//			if (o1 != null) {
//
//			return (o2 != null) ? o1.getAssignedTo().getEmployeeName().compareTo(o2.getAssignedTo().getEmployeeName()) : 1;
//			}
//			return -1;
//			}
//			});
//		
//		columnSortHandler.setComparator(approvar,new Comparator<DashBoardDTO>() {
//
//			public int compare(DashBoardDTO o1,DashBoardDTO o2) {
//			if (o1 == o2) {
//			return 0;
//			}
//
//			// Compare the groupname columns.
//			if (o1 != null) {
//
//			return (o2 != null) ? o1.getApprovedBy().getEmployeeName().compareTo(o2.getApprovedBy().getEmployeeName()) : 1;
//			}
//			return -1;
//			}
//			});
//		
//		columnSortHandler.setComparator(phase,new Comparator<DashBoardDTO>() {
//
//			public int compare(DashBoardDTO o1,DashBoardDTO o2) {
//			if (o1 == o2) {
//			return 0;
//			}
//
//			// Compare the groupname columns.
//			if (o1 != null) {
//
//			return (o2 != null) ? o1.getPhase()+"".compareTo(o2.getPhase()+"") : 1;
//			}
//			return -1;
//			}
//			});
//		
//		//
//		columnSortHandler.setComparator(statusColumn,new Comparator<DashBoardDTO>() {
//
//			public int compare(DashBoardDTO o1,DashBoardDTO o2) {
//			if (o1 == o2) {
//			return 0;
//			}
//
//			// Compare the status columns.
//			if (o1 != null) {
//
//			return (o2 != null) ? o1.getStatus().compareTo(o2.getStatus()) : 1;
//			}
//			return -1;
//			}
//			});
//		//
//		
//		columnSortHandler.setComparator(date,new Comparator<DashBoardDTO>() {
//
//			public int compare(DashBoardDTO o1,DashBoardDTO o2) {
//			if (o1 == o2) {
//			return 0;
//			}
//
//			// Compare the status columns.
//			if (o1 != null) {
//
//			return (o2 != null) ? o1.getDate().compareTo(o2.getDate()) : 1;
//			}
//			return -1;
//			}
//			});
//		
//		
//		
//		table.addColumnSortHandler(columnSortHandler);
//		table.getColumnSortList().push(objectiveName);
//		table.getColumnSortList().push(assignee);
//		table.getColumnSortList().push(approvar);
//		table.getColumnSortList().push(date);
//		table.getColumnSortList().push(initiatedBy);
//		table.getColumnSortList().push(date);
//		table.getColumnSortList().push(statusColumn);
//		table.getColumnSortList().push(phase);
//		
//	}

	public Image getRefresh() {
		return refresh;
	}

	public void setRefresh(Image refresh) {
		this.refresh = refresh;
	}

	

	


}
	

