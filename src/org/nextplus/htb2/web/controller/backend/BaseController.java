package org.nextplus.htb2.web.controller.backend;

import org.nextplus.htb2.util.Constants;

public class BaseController extends
		org.nextplus.htb2.web.controller.BaseController {

	@Override
	protected String getUrlPrefix() {
		return Constants.BACKEND_URL_PREFIX;
	}

	@Override
	protected String getViewNamePrefix() {
		return Constants.BACKEND_VIEW_NAME_PREFIX;
	}

	// Helpers.

}
