package com.internalaudit.client.view;

import java.util.ArrayList;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.sencha.gxt.widget.core.client.form.TextArea;

public class RiskFactorsView extends Composite {
	private Label riskFactor;
	private ListBox rating;
	private ListBox impact;
	private ListBox probabality;
	private int riskFactorId;
	private TextArea description;
	private Label lbl;
	private Image riskRating;
	private ListBox listBoxOverAllRating;
	private ArrayList<RiskFactorsView> riskFactorsUpdated;

	public RiskFactorsView() {

		HorizontalPanel hpnlMain = new HorizontalPanel();
		initWidget(hpnlMain);
		hpnlMain.setSize("700px", "100%");

		riskFactor = new Label("");

		riskFactor.setWidth("245px");

		rating = new ListBox();
		rating.setEnabled(false);
//		rating.addItem("N/A","0");

		rating.setVisibleItemCount(1);

		impact = new ListBox();

		impact.setVisibleItemCount(1);

		probabality = new ListBox();

		probabality.setVisibleItemCount(1);
		lbl = new Label("hello");

		rating.addItem("N/A", "0");
		rating.addItem("Low", "1");
		rating.addItem("Medium", "2");
		rating.addItem("High", "3");

		probabality.addItem("N/A", "0");
		probabality.addItem("Low", "1");
		probabality.addItem("Medium", "2");
		probabality.addItem("High", "3");

		impact.addItem("N/A", "0");
		impact.addItem("Low", "1");
		impact.addItem("Medium", "2");
		impact.addItem("High", "3");

		description = new TextArea();
		description.setSize("600px", "40px");
		// placeholder not working
		// comments.getElement().setPropertyString("placeholder", "Enter your
		// Comment here");

		riskRating = new Image("redcircle.png");
		riskRating.setVisible(false);

		probabality.setWidth("70px");
		impact.setWidth("70px");
		rating.setWidth("70px");

		impact.getElement().getStyle().setMarginLeft(10, Unit.PX);
		// HorizontalPanel hpnlSpaceRating = new HorizontalPanel();

		// hpnlSpaceRating.setWidth("200px");
		hpnlMain.add(riskFactor);
		hpnlMain.add(description);
		hpnlMain.add(impact);
		hpnlMain.add(probabality);
		hpnlMain.add(rating);
		hpnlMain.add(riskRating);
		// hpnlMain.add(hpnlSpaceRating);
		hpnlMain.setSpacing(2);

		setHandlers();

	}

	public void setHandlers() {
		probabality.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent arg0) {
				String level = calculateIMpactAndUpdateRating();
				for (int i = 0; i < rating.getItemCount(); i++) {
					if (rating.getValue(i).equalsIgnoreCase(level)) {
						rating.setSelectedIndex(i);
					}
				}
				try {
					setOverAllRating();
				} catch (Exception ex) {
					GWT.log("Inside RiskFactor:" + ex);
				}
			}
		});

		impact.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent arg0) {
				String level = calculateIMpactAndUpdateRating();
				for (int i = 0; i < rating.getItemCount(); i++) {
					if (rating.getValue(i).equalsIgnoreCase(level)) {
						rating.setSelectedIndex(i);

					}
				}
				try {
					setOverAllRating();
				} catch (Exception ex) {
					GWT.log("Inside RiskFactor:" + ex);
				}
			}
		});

	}

	public void overAllRatingHander(final ArrayList<RiskFactorsView> riskFactorsUpdated,
			final ListBox listBoxOverAllRating) {
		this.listBoxOverAllRating = listBoxOverAllRating;
		this.riskFactorsUpdated = riskFactorsUpdated;
		listBoxOverAllRating.setEnabled(false);
	}

	private void setOverAllRating() {
		String overallRating = null;
		float avg = 0;
		int countHigh = 3;
		int countMedium = 2;
		int countLow = 1;
		float sum = 0;
		int count = 0;
		for (int i = 0; i < riskFactorsUpdated.size(); i++) {
			if (riskFactorsUpdated.get(i).getRating().getSelectedValue().equalsIgnoreCase("High")) {
				sum += countHigh;
				count++;
			} else if (riskFactorsUpdated.get(i).getRating().getSelectedValue().equalsIgnoreCase("Medium")) {
				sum += countMedium;
				count++;
			} else if (riskFactorsUpdated.get(i).getRating().getSelectedValue().equalsIgnoreCase("Low")) {
				sum += countLow;
				count++;
			}
		}
		avg = sum / count;
		if (avg >= 2.5 && avg <= 3) {
			overallRating = "High";
		} else if (avg >= 1.5 && avg < 2.5) {
			overallRating = "Medium";
		} else if (avg > 0 && avg < 1.5) {
			overallRating = "Low";
		} else
			overallRating = "N/A";
		for (int i = 0; i < listBoxOverAllRating.getItemCount(); i++) {
			if (overallRating.equals(listBoxOverAllRating.getValue(i))) {
				listBoxOverAllRating.setSelectedIndex(i);
				break;
			}
		}

	}

	public String calculateIMpactAndUpdateRating() {
		String ratingLevel = "";
		if (!probabality.getValue(probabality.getSelectedIndex()).equalsIgnoreCase("0")
				&& !impact.getValue(impact.getSelectedIndex()).equalsIgnoreCase("0")) {

			int prob = Integer.parseInt(probabality.getValue(probabality.getSelectedIndex()));
			int imp = Integer.parseInt(impact.getValue(impact.getSelectedIndex()));

			int ratingValue = prob * imp;

			if (ratingValue >= 6) {
				ratingLevel = "High";
			} else if (ratingValue >= 3) {
				ratingLevel = "Medium";
			} else if (ratingValue >= 1) { // criteria added by moqeet
				ratingLevel = "Low";
			} else {
				ratingLevel = "N/A"; // else was outside this scope
			}
		}
		// try {
		// setOverAllRating();
		// } catch (Exception ex) {
		// GWT.log("Inside RiskFactor:" + ex);
		// }
		return ratingLevel;
	}

	public Label getRiskFactor() {
		return riskFactor;
	}

	public void setRiskFactor(Label riskFactor) {
		this.riskFactor = riskFactor;
	}

	public ListBox getRating() {
		return rating;
	}

	public void setRating(ListBox rating) {
		this.rating = rating;
	}

	public int getRiskFactorId() {
		return riskFactorId;
	}

	public void setRiskFactorId(int riskFactorId) {
		this.riskFactorId = riskFactorId;
	}

	public TextArea getDescription() {
		return description;
	}

	public void setDescription(TextArea description) {
		this.description = description;
	}

	public ListBox getImpact() {
		return impact;
	}

	public void setImpact(ListBox impact) {
		this.impact = impact;
	}

	public ListBox getProbabality() {
		return probabality;
	}

	public void setProbabality(ListBox probabality) {
		this.probabality = probabality;
	}

	public Image getRiskRating() {
		return riskRating;
	}

	public void setRiskRating(Image riskRating) {
		this.riskRating = riskRating;
	}

}
