package com.internalaudit.client.view.ToDo;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public interface InformationRequestRaiseProperties extends PropertyAccess<InformationRequestRaiseEntity> {
	@Path("id")
	ModelKeyProvider<InformationRequestRaiseEntity> key();

	ValueProvider<InformationRequestRaiseEntity, String> requestedItem();

	ValueProvider<InformationRequestRaiseEntity, String> raisedTo();

	ValueProvider<InformationRequestRaiseEntity, String> relatedJob();
	
	ValueProvider<InformationRequestRaiseEntity, String> status();
	
	ValueProvider<InformationRequestRaiseEntity, String> overDueDays();

	ValueProvider<InformationRequestRaiseEntity, Integer> id();

}
