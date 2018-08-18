package com.internalaudit.client.DashboardNew;


import java.util.Date;

import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public interface IssueProperties extends PropertyAccess<Issue> {
  @Path("id")
  ModelKeyProvider<Issue> key();
  
  @Path("name")
  LabelProvider<Issue> nameLabel();

  ValueProvider<Issue, String> name();
  
  ValueProvider<Issue, String> symbol();
  
  ValueProvider<Issue, Double> open();
  
  ValueProvider<Issue, Double> last();
  
  ValueProvider<Issue, Double> change();
  
  ValueProvider<Issue, Date> lastTrans();
  
  ValueProvider<Issue, Boolean> split();
  
  ValueProvider<Issue, String> industry();
}