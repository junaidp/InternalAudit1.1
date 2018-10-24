package com.internalaudit.client.view.ToDo;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public interface ToDoReceiverProperties extends PropertyAccess<ToDoReceiverEntity> {
	@Path("id")
	ModelKeyProvider<ToDoReceiverEntity> key();

	ValueProvider<ToDoReceiverEntity, String> requestedItem();

	ValueProvider<ToDoReceiverEntity, String> raisedBy();

	ValueProvider<ToDoReceiverEntity, String> relatedJob();
	
	ValueProvider<ToDoReceiverEntity, String> status();
	
	ValueProvider<ToDoReceiverEntity, String> overDueDays();

	ValueProvider<ToDoReceiverEntity, Integer> id();

}
