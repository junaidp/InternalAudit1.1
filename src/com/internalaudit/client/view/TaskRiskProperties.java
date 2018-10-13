package com.internalaudit.client.view;

import com.google.gwt.editor.client.Editor.Path;
import com.google.gwt.user.client.ui.TextBox;
import com.internalaudit.shared.TaskRiskEntity;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public interface TaskRiskProperties extends PropertyAccess<TaskRiskEntity> {
	@Path("id")
	ModelKeyProvider<TaskRiskEntity> key();

	ValueProvider<TaskRiskEntity, String> task();

	ValueProvider<TaskRiskEntity, String> job();

	ValueProvider<TaskRiskEntity, String> raisedBy();
	
	ValueProvider<TaskRiskEntity, String> overDueDays();
	
	ValueProvider<TaskRiskEntity, String> status();
	
	ValueProvider<TaskRiskEntity, String> resolution();

	ValueProvider<TaskRiskEntity, Integer> id();

	

}
