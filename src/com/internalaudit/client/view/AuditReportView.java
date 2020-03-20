package com.internalaudit.client.view;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.internalaudit.client.event.MainEvent;
import com.internalaudit.client.presenter.AuditReportPresenter.Display;
import com.internalaudit.client.view.InternalAuditReporting.ReportViewMain;
import com.internalaudit.shared.Employee;

public class AuditReportView extends Composite implements Display {

	public AuditReportView(final HandlerManager eventBus, final Employee loggedInUser) {
		VerticalPanel vp = new VerticalPanel();

		Button btn = new Button("Back");
		vp.add(new ReportViewMain(eventBus));
		vp.add(btn);
		btn.addStyleName("w3-right");
		btn.getElement().getStyle().setMarginTop(10, Unit.PX);
		btn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				back(eventBus, loggedInUser);

			}
		});
		initWidget(vp);

	}

	private void back(HandlerManager eventBus, Employee loggedInUser) {
		eventBus.fireEvent(new MainEvent(loggedInUser));
	}

}
