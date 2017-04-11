package com.internalaudit.client.view;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratedPopupPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.form.TextArea;

public class AmendmentPopup {
	
	Button btnSubmit;
	DecoratedPopupPanel popupComments;
	TextArea comments;
	
	public void popupAmendment() {
		
		popupComments = new DecoratedPopupPanel();
		VerticalPanel vpnlComments = new VerticalPanel();
		popupComments.setWidget(vpnlComments);
		Label lbl = new Label("Enter Comments");
		comments = new TextArea();
		comments.setSize("400px", "200px");
		HorizontalPanel hpnlButtons = new HorizontalPanel();
		hpnlButtons.setSpacing(2);
		btnSubmit = new Button("Submit");
		Button btnCancel = new Button("Cancel");
		hpnlButtons.add(btnCancel);
		hpnlButtons.add(btnSubmit);
		vpnlComments.add(lbl);
		vpnlComments.add(comments);
		vpnlComments.add(hpnlButtons);

		vpnlComments.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		vpnlComments.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		popupComments.center();
		
		

			btnCancel.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				popupComments.removeFromParent();
			}});
	}


	public Button getBtnSubmit() {
		return btnSubmit;
	}


	public void setBtnSubmit(Button btnSubmit) {
		this.btnSubmit = btnSubmit;
	}


	public DecoratedPopupPanel getPopupComments() {
		return popupComments;
	}


	public void setPopupComments(DecoratedPopupPanel popupComments) {
		this.popupComments = popupComments;
	}


	public TextArea getComments() {
		return comments;
	}


	public void setComments(TextArea comments) {
		this.comments = comments;
	}
}
