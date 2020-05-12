package com.internalaudit.client.view;

import java.util.ArrayList;

import com.google.gwt.dom.client.Style.TextDecoration;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.internalaudit.shared.ToDo;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;

public class TodosList extends VerticalLayoutContainer {

	public TodosList(ArrayList<ToDo> arrayList) {

		VerticalPanel panelMain = new VerticalPanel();
		FlexTable records = new FlexTable();
		records.setWidth("100%");
		for (int i = 0; i < arrayList.size(); i++) {
			final ToDo todo = arrayList.get(i);
			Image imageTodo = new Image();
			imageTodo.setUrl("redCircleNew.png");
			// imageTodo.setUrl("greenCircleNew.png");
			// imageTodo.setUrl("yellowCircleNew.png");
			// task added and description commented by moqeet
			// final Anchor lblTodo = new Anchor(todo.getDescription());
			final Anchor lblTodo = new Anchor(todo.getTask());
			lblTodo.getElement().getStyle().setTextDecoration(TextDecoration.NONE);
			lblTodo.setWidth("100%");
			lblTodo.setWordWrap(false);
			lblTodo.setHeight("25px");
			lblTodo.addStyleName("pointerStyle");
			records.setWidget(i, 0, lblTodo);
			records.setWidget(i, 1, imageTodo);
			if (i % 2 != 0) {
				records.getRowFormatter().addStyleName(i, "jobStatusRow");
			}

			String upperCasedJobLink = lblTodo.getText();
			lblTodo.setText(upperCasedJobLink);
			add(records);
			lblTodo.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					// final InformationRequestReceiveView emailView = new
					// InformationRequestReceiveView(todo);
					// PopupsView pp = new PopupsView(emailView, "");
					// pp.getLabelheading().setText("Todos View");
					// pp.getVpnlMain().setTitle("Email View");
					// pp.getVpnlMain().setWidth("600px");
					// pp.getHpnlSPace().setWidth("600px");
					// pp.getVpnlMain().setHeight("530px");

				}
			});
		}

		add(panelMain);

	}

}
