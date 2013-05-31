package org.nextplus.htb2.spring;

import javax.servlet.http.HttpServletRequest;

public interface HttpServletRequestAware {

	void setRequest(HttpServletRequest request);

}
