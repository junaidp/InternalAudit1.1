
package com.internalaudit.client.view.ToDo;
import java.util.Date;

import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public interface ToDoRaiserProperties extends PropertyAccess<ToDoRaiserEntity> {
	@Path("id")
	ModelKeyProvider<ToDoRaiserEntity> key();

	ValueProvider<ToDoRaiserEntity, String> requestedItem();
	
	ValueProvider<ToDoRaiserEntity, String> reply();

	ValueProvider<ToDoRaiserEntity, String> raisedTo();

	ValueProvider<ToDoRaiserEntity, String> relatedJob();
	
	ValueProvider<ToDoRaiserEntity, String> status();
	
	ValueProvider<ToDoRaiserEntity, String> viewButton();
	
	ValueProvider<ToDoRaiserEntity, Date> overDueDays();

	ValueProvider<ToDoRaiserEntity, Integer> id();
	
	ValueProvider<ToDoRaiserEntity, Integer> raisedToId();
	
	ValueProvider<ToDoRaiserEntity, Integer> raisedById();
	
	ValueProvider<ToDoRaiserEntity, Integer> relatedJobId();

}
