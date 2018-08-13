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
  ValueProvider<Stock, String> fieldWorkLabel();
  ValueProvider<Stock, String> exceptionsLabel();
  ValueProvider<Stock, String> workStepCompletedLabel();
  
  //end
  LabelProvider<Stock> nameLabel();

  ValueProvider<Stock, String> name();
  
  ValueProvider<Stock, String> symbol();
  
  ValueProvider<Stock, Double> open();
  
  ValueProvider<Stock, Double> last();
  
  ValueProvider<Stock, Double> change();
  
  ValueProvider<Stock, Date> lastTrans();
  
  ValueProvider<Stock, Boolean> split();
  
  ValueProvider<Stock, String> industry();
}
