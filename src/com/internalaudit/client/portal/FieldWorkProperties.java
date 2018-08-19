package com.internalaudit.client.portal;
import java.util.Date;

import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public interface FieldWorkProperties extends PropertyAccess<FieldWork> {
  @Path("id")
  ModelKeyProvider<FieldWork> key();
  
  @Path("name")
  ValueProvider<FieldWork, String> name();
  ValueProvider<FieldWork, String> status();
  ValueProvider<FieldWork, Date> date();
 

}
