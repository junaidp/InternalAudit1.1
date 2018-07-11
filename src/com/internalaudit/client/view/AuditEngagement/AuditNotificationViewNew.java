package com.internalaudit.client.view.AuditEngagement;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.Widget;

public class AuditNotificationViewNew extends Composite {

	private static AuditNotificationViewNewUiBinder uiBinder = GWT.create(AuditNotificationViewNewUiBinder.class);

	interface AuditNotificationViewNewUiBinder extends UiBinder<Widget, AuditNotificationViewNew> {
	}
@UiField
TextArea txtAreaBody;
	public AuditNotificationViewNew() {
		initWidget(uiBinder.createAndBindUi(this));
		HTML html = new HTML();
		txtAreaBody.setText("We hereby would like to inform you thot company Nome Internal Audit will be conducting o review of Engagement Nome from 14 December, 2017. This is port of the Internal Audit Pion for 2017, discussed with Top Monogement, Group Internal Audit, ond approved by member(s) of BoD/Audit Committee\nThe oudit will be performed by Mr DEF ond Mr GHI , members of Locol Internal Audit.\n The scope of the oudit includes review of odequocy, effectiveness & efficiency of the controls ond processes around Engagement Nome, with specific focus on xxxx. \n Please note thot IA teom moy toke contact with relevant members of your teom in the coming doys for o better process understanding relating to this oudit ond information required. Any resultant scope changes, if required, will be duly communicated. \n The completion ond finolisotion will depend on the ovoilobility of information ond interviewees, yet we oim ot delivering o droft report for your review ond comments by 8 FebruorY, 2018. \n You ore requested to forward the information in ti-is notification to those who will be involved in the oudit. If you hove ony questions concerning the oudit, please feel free to contact myself or e-moil ot xxxxx. \n We look forward to your continued support. ");
		
	}

}
