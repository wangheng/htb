package org.nextplus.htb2.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.nextplus.commons.HttpSessionAware;
import org.nextplus.commons.ValidatorFactory;
import org.nextplus.htb2.domain.User;
import org.nextplus.htb2.spring.HttpServletRequestAware;
import org.nextplus.htb2.spring.HttpServletResponseAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public abstract class BaseController
		implements HttpSessionAware, HttpServletRequestAware, HttpServletResponseAware {

	protected User getUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null) {
			Object principal = authentication.getPrincipal();
			if (principal instanceof User) {
				User u = (User) principal;
				return u;
			}
		}
		return null;

//		Member member = new Member();
//		member.setId(4L);
//		return member;
	}

	protected Long getUserId() {
		if (getUser() != null) {
			return getUser().getId();
		}
		return null;
	}

	@InitBinder
	public void initBinder(WebDataBinder binder, HttpServletRequest req) {
		binder.setValidator(vf.getValidator());
		// 安全测试
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public String handleException(
			Exception e,
			HttpServletRequest req) {
		e.printStackTrace();
//		req.setAttribute("msg", e.getMessage());
//		req.setAttribute("fullStackTrace", ExceptionUtils.getFullStackTrace(e));
		return e.getMessage();
	}

	protected String redirect(String path, Object... objs) {
		String ref = request.getParameter("ref");
		if (StringUtils.hasText(ref)) {
//			System.out.println(ref);
			return "redirect:" + ref;
		}

		return String.format("redirect:" + getUrlPrefix() + path, objs);
	}

	protected abstract String getUrlPrefix();

	protected String render(String viewName) {
		return getViewNamePrefix() + viewName;
	}

	protected abstract String getViewNamePrefix();

	// Login

	protected void login(User user) {
		login(user.getUsername(), user.getPassword());
	}

	protected void login(String username, String password) {
		Assert.hasText(username);
		Assert.hasText(password);

		UsernamePasswordAuthenticationToken authenticationToken =
				new UsernamePasswordAuthenticationToken(username, password);
		Authentication authentication = authenticationManager.authenticate(authenticationToken);
		SecurityContextHolder.getContext().setAuthentication(authentication);

		session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
				SecurityContextHolder.getContext());
	}

	protected void logout() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null) {
			new SecurityContextLogoutHandler().logout(request, response, authentication);
			session = request.getSession(true);
		}
	}

	// DI

	// HTTP Session
	protected HttpSession session;
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	// RequestUrlBase
	protected String requestUrlBase;
	protected String fileUploadPath;
	// Others
	protected ValidatorFactory vf;
	protected AuthenticationManager authenticationManager;

	@Override
	public void setSession(HttpSession session) {
		this.session = session;
	}

	@Override
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	@Value("${context.requestUrlBase}")
	public void setRequestUrlBase(String requestUrlBase) {
		this.requestUrlBase = requestUrlBase;
	}

	@Value("${context.fileUploadPath}")
	public void setFileUploadPath(String fileUploadPath) {
		this.fileUploadPath = fileUploadPath;
	}

	@Autowired
	public void setVf(ValidatorFactory vf) {
		this.vf = vf;
	}

	@Autowired
	@Qualifier("authenticationManager")
	public void setAuthenticationManager(
			AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

}
