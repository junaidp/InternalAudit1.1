package com.internalaudit.client.portal;
import java.util.Date;

import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public interface ReportingProperties extends PropertyAccess<Reporting> {
  @Path("id")
  ModelKeyProvider<Reporting> key();
  
  @Path("name")
  ValueProvider<Reporting, String> name();
  ValueProvider<Reporting, String> status();
  ValueProvider<Reporting, Date> date();
 

}
