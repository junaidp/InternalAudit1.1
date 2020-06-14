package com.internalaudit.client.widgets;

import com.internalaudit.client.view.ToDo.ToDoReceiverEntity;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.widget.core.client.grid.GridViewConfig;

public class MyGridViewConfig implements GridViewConfig<ToDoReceiverEntity> {

	@Override
	public String getColStyle(ToDoReceiverEntity model, ValueProvider valueProvider, int rowIndex, int colIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRowStyle(ToDoReceiverEntity model, int rowIndex) {

		return "labelRisk";
	}

}
