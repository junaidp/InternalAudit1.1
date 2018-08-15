package com.internalaudit.client.portal;
import java.util.Date;

import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public interface StockProperties extends PropertyAccess<Stock> {
  @Path("id")
  ModelKeyProvider<Stock> key();
  
  @Path("name")
  
  //new
  //fieldwork
  ValueProvider<Stock, String> name();
  ValueProvider<Stock, String> fieldWorkNames();
  ValueProvider<Stock, String> fieldWorkStep();
  ValueProvider<Stock, Date> fieldWorkDate();
  //planning
  ValueProvider<Stock, String> planningName();
  ValueProvider<Stock, String> planningWorkStep();
  ValueProvider<Stock, Date> planningWorkDate();
  //reporting
  ValueProvider<Stock, String> reportingName();
  ValueProvider<Stock, String> reportingWorkStep();
  ValueProvider<Stock, Date> reportingDate();
  
  //end
  LabelProvider<Stock> nameLabel();


  
  ValueProvider<Stock, String> symbol();
  
  ValueProvider<Stock, Double> open();
  
  ValueProvider<Stock, Double> last();
  
  ValueProvider<Stock, Double> change();
  
  ValueProvider<Stock, Date> lastTrans();
  
  ValueProvider<Stock, Boolean> split();
  
  ValueProvider<Stock, String> industry();
}
