package org.nextplus.htb2.web.controller.cs;

import org.nextplus.htb2.util.Constants;

public class BaseController extends
		org.nextplus.htb2.web.controller.BaseController {

	@Override
	protected String getUrlPrefix() {
		return Constants.CS_URL_PREFIX;
	}

	@Override
	protected String getViewNamePrefix() {
		return Constants.CS_VIEW_NAME_PREFIX;
	}

	// Helpers.

}
