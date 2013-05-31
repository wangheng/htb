package org.nextplus.htb2.spring;

import javax.servlet.http.HttpServletResponse;

public interface HttpServletResponseAware {

	void setResponse(HttpServletResponse response);

}
