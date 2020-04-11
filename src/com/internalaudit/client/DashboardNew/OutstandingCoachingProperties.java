
package com.internalaudit.client.DashboardNew;

import java.util.Date;

import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public interface OutstandingCoachingProperties extends PropertyAccess<OutstandingCoaching> {
	@Path("id")
	ModelKeyProvider<OutstandingCoaching> key();

	ValueProvider<OutstandingCoaching, String> coachingNote();

	ValueProvider<OutstandingCoaching, String> raisedTo();

	ValueProvider<OutstandingCoaching, String> raisedBy();

	ValueProvider<OutstandingCoaching, Integer> id();

	ValueProvider<OutstandingCoaching, Date> overDueDays();

}
