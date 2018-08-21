package com.internalaudit.client.portal;
import java.util.Date;

import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public interface PlanningProperties extends PropertyAccess<Planning> {
  @Path("id")
  ModelKeyProvider<Planning> key();
  
  @Path("name")
  ValueProvider<Planning, String> name();
  ValueProvider<Planning, String> status();
  ValueProvider<Planning, Date> date();
  ValueProvider<Planning, String> url();

}
