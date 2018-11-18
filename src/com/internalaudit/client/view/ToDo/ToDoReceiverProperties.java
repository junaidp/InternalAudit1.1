package com.internalaudit.client.view.ToDo;
import java.util.Date;

import com.google.gwt.editor.client.Editor.Path;
import com.internalaudit.shared.Employee;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public interface ToDoReceiverProperties extends PropertyAccess<ToDoReceiverEntity> {
	@Path("id")
	ModelKeyProvider<ToDoReceiverEntity> key();

	ValueProvider<ToDoReceiverEntity, String> requestedItem();

	ValueProvider<ToDoReceiverEntity, String> raisedBy();
	
	ValueProvider<ToDoReceiverEntity, Integer> raisedById();

	ValueProvider<ToDoReceiverEntity, Integer> relatedjobId();
	
	ValueProvider<ToDoReceiverEntity, String> relatedJob();
	
	ValueProvider<ToDoReceiverEntity, String> status();
	
	ValueProvider<ToDoReceiverEntity, Date> overDueDays();

	ValueProvider<ToDoReceiverEntity, Integer> id();
	
	ValueProvider<ToDoReceiverEntity, String> viewButton();


}
