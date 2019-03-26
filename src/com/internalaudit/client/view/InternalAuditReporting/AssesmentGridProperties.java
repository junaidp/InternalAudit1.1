package com.internalaudit.client.view.InternalAuditReporting;

import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public interface AssesmentGridProperties extends PropertyAccess<AssesmentGridEntity> {
	@Path("id")
	ModelKeyProvider<AssesmentGridEntity> key();

	@Path("name")
	ValueProvider<AssesmentGridEntity, String> name();

	ValueProvider<AssesmentGridEntity, String> urlComplete();

	ValueProvider<AssesmentGridEntity, String> urlSatisfy();

	ValueProvider<AssesmentGridEntity, String> urlNonSatisfy();

	ValueProvider<AssesmentGridEntity, Boolean> urlSatisfyboolean();

	ValueProvider<AssesmentGridEntity, Boolean> urlCompleteboolean();

	ValueProvider<AssesmentGridEntity, Boolean> urlNonSatisfyboolean();

}
