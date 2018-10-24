
package com.internalaudit.client.view.ToDo;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public interface ToDoRaiserProperties extends PropertyAccess<ToDoRaiserEntity> {
	@Path("id")
	ModelKeyProvider<ToDoRaiserEntity> key();

	ValueProvider<ToDoRaiserEntity, String> requestedItem();

	ValueProvider<ToDoRaiserEntity, String> raisedTo();

	ValueProvider<ToDoRaiserEntity, String> relatedJob();
	
	ValueProvider<ToDoRaiserEntity, String> status();
	
	ValueProvider<ToDoRaiserEntity, String> overDueDays();

	ValueProvider<ToDoRaiserEntity, Integer> id();

}
