package com.internalaudit.client.view;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;
import com.internalaudit.client.InternalAuditService;
import com.internalaudit.client.InternalAuditServiceAsync;
import com.internalaudit.shared.Employee;
import com.internalaudit.shared.ToDo;

public class ToDoView extends Composite {
	
	@UiField
	TextBox txtBoxDescription;
	@UiField
	ListBox listBoxAssignedTo;
	@UiField
	DateBox dueDate;
	@UiField
	Button btnSave;
	private InternalAuditServiceAsync rpcService;

	private static ToDoViewUiBinder uiBinder = GWT.create(ToDoViewUiBinder.class);

	interface ToDoViewUiBinder extends UiBinder<Widget, ToDoView> {
		
	}

	public ToDoView() {
		initWidget(uiBinder.createAndBindUi(this));
//		todo.setRes[]
		rpcService = GWT.create(InternalAuditService.class);
		fetchEmployees();
		setHandlers();
		
	}

	private void setHandlers() {
		btnSave.addClickHandler(new ClickHandler() {
			
			
					
			@Override
			public void onClick(ClickEvent event) {
				
				saveToDo();
			}
			
		});
	}
	
	private void saveToDo() {
		final ToDo todo = new ToDo();
		todo.setDescription(txtBoxDescription.getText());
		
		Employee assignedTo = new Employee();
		assignedTo.setEmployeeId(Integer.parseInt(listBoxAssignedTo.getSelectedValue()));
		
		
		todo.setAssignedTo(assignedTo);
	
		
		todo.setDueDate(dueDate.getValue());
		
		rpcService.savetoDo(todo, new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("error in rpc savetodo" + caught.getLocalizedMessage());
				
			}

			@Override
			public void onSuccess(String result) {
				new DisplayAlert(result);
				
			}
		});
	}
	private void fetchEmployees(){
		rpcService.fetchEmployees(new AsyncCallback<ArrayList<Employee>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("fail fetch Employees");
			}

			@Override
			public void onSuccess(ArrayList<Employee> result) {
				
				for(int i=0; i< result.size(); i++){
				
						listBoxAssignedTo.addItem(result.get(i).getEmployeeName(), result.get(i).getEmployeeId()+"");
						//display.getListEmployees().addItem(result.get(i).getEmployeeName(), result.get(i).getEmployeeId()+"");
						
					}
				}
			
		});
	}
	
	public TextBox getTxtBoxDescription() {
		return txtBoxDescription;
	}

	public void setTxtBoxDescription(TextBox txtBoxDescription) {
		this.txtBoxDescription = txtBoxDescription;
	}

	public ListBox getListBoxAssignedTo() {
		return listBoxAssignedTo;
	}

	public void setListBoxAssignedTo(ListBox listBoxAssignedTo) {
		this.listBoxAssignedTo = listBoxAssignedTo;
	}

	public DateBox getDueDate() {
		return dueDate;
	}

	public void setDueDate(DateBox dueDate) {
		this.dueDate = dueDate;
	}

}
