package org.nextplus.htb2.web.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nextplus.htb2.domain.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.util.Assert;

public class DefaultAuthenticationSuccessHandler extends
		SimpleUrlAuthenticationSuccessHandler {

	@SuppressWarnings("unused")
	private final RequestCache requestCache = new HttpSessionRequestCache();
	private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {

//		DefaultSavedRequest savedReq = (DefaultSavedRequest) request.getSession()
//				.getAttribute(WebAttributes.SAVED_REQUEST);
//		if (savedReq != null) {
//			String path = URIUtil.getPath(savedReq.getRedirectUrl());
//			response.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
//			response.setHeader("Location", path);
//		} else {
			User u = (User) SecurityContextHolder.getContext()
					.getAuthentication().getPrincipal();
			Assert.notNull(u);
			if (!u.getRoles().isEmpty()) {
				String prefix = u.getRoles()
						.get(0).getPrefix();
				redirectStrategy.sendRedirect(request, response, prefix);
			}
//		}
	}

}
