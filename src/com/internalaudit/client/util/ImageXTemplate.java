package com.internalaudit.client.util;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.sencha.gxt.core.client.XTemplates;

public interface ImageXTemplate extends XTemplates {
  @XTemplate("<img style=\"border: 2px solid red; width: 25px;\" src=\"{url}\">")
  SafeHtml createImage(String url);
}