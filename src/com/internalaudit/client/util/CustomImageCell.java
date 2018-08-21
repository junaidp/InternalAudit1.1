package com.internalaudit.client.util;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeUri;
import com.google.gwt.safehtml.shared.UriUtils;

public class CustomImageCell extends AbstractCell<String> {
  private ImageXTemplate imageTemplate = GWT.create(ImageXTemplate.class);

  @Override
  public void render(Context context, String valueIsUrl, SafeHtmlBuilder sb) {
    if (valueIsUrl != null) {
      SafeUri url = UriUtils.fromString(valueIsUrl);
      sb.append(imageTemplate.createImage(url.asString()));
    }
  }
}
