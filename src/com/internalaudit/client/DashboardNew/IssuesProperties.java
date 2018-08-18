package com.internalaudit.client.DashboardNew;

import java.util.Date;

import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public interface IssuesProperties extends PropertyAccess<Issues> {
  @Path("id")
  ModelKeyProvider<Issues> key();
  
  @Path("name")
  
  //new
  //fieldwork
  ValueProvider<Issues, Integer> id();
  ValueProvider<Issues, String> issueTitle();
  ValueProvider<Issues, String> managementResponce();
  ValueProvider<Issues, String> status();

  //planning
  
}
