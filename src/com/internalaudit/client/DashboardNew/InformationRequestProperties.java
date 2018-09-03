package com.internalaudit.client.DashboardNew;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public interface InformationRequestProperties extends PropertyAccess<InformationRequest> {
	@Path("id")
	ModelKeyProvider<InformationRequest> key();

	ValueProvider<InformationRequest, String> informationReport();

	ValueProvider<InformationRequest, String> raisedTo();

	ValueProvider<InformationRequest, String> raisedBy();
	
	ValueProvider<InformationRequest, String> overDueDays();

	ValueProvider<InformationRequest, Integer> id();

}
