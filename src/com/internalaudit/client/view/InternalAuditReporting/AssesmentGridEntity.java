package com.internalaudit.client.view.InternalAuditReporting;

import java.io.Serializable;

public class AssesmentGridEntity implements Serializable {

	private int id;
	private String name;
	private String urlSatisfy;
	private String urlNonSatisfy;
	private String urlComplete;
	private boolean urlSatisfyboolean;
	private boolean urlCompleteboolean;
	private boolean urlNonSatisfyboolean;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUrlSatisfy() {
		return urlSatisfy;
	}

	public void setUrlSatisfy(String urlSatisfy) {
		this.urlSatisfy = urlSatisfy;
	}

	public String getUrlNonSatisfy() {
		return urlNonSatisfy;
	}

	public void setUrlNonSatisfy(String urlNonSatisfy) {
		this.urlNonSatisfy = urlNonSatisfy;
	}

	public String getUrlComplete() {
		return urlComplete;
	}

	public void setUrlComplete(String urlComplete) {
		this.urlComplete = urlComplete;
	}

	public boolean isUrlSatisfyboolean() {
		return urlSatisfyboolean;
	}

	public void setUrlSatisfyboolean(boolean urlSatisfyboolean) {
		this.urlSatisfyboolean = urlSatisfyboolean;
	}

	public boolean isUrlCompleteboolean() {
		return urlCompleteboolean;
	}

	public void setUrlCompleteboolean(boolean urlCompleteboolean) {
		this.urlCompleteboolean = urlCompleteboolean;
	}

	public boolean isUrlNonSatisfyboolean() {
		return urlNonSatisfyboolean;
	}

	public void setUrlNonSatisfyboolean(boolean urlNonSatisfyboolean) {
		this.urlNonSatisfyboolean = urlNonSatisfyboolean;
	}
}
