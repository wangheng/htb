package org.nextplus.htb2.spring;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class HttpServletRequestInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		if (handler instanceof HttpServletRequestAware) {
			((HttpServletRequestAware) handler).setRequest(request);
			request.setAttribute("contextPath", request.getContextPath());
		}
		return super.preHandle(request, response, handler);
	}

}