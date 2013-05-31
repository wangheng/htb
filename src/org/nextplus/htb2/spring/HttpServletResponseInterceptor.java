package org.nextplus.htb2.spring;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class HttpServletResponseInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		if (handler instanceof HttpServletResponseAware) {
			((HttpServletResponseAware) handler).setResponse(response);
		}
		return super.preHandle(request, response, handler);
	}

}