package com.internalaudit.client.util;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.sencha.gxt.core.client.XTemplates;

public interface ImageXTemplate extends XTemplates {
  @XTemplate("<img style=\" width: 17px;\" src=\"{url}\">")
  SafeHtml createImage(String url);
}