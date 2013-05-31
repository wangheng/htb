package org.nextplus.htb2.web.controller.cs;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.nextplus.commons.DuplicateException;
import org.nextplus.htb2.domain.Member;
import org.nextplus.htb2.service.UserService;
import org.nextplus.htb2.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Controller("csMemberController")
@RequestMapping(Constants.CS_URL_PREFIX)
@SessionAttributes("memberForm")
public class MemberController extends BaseController {

	private final static Log LOG = LogFactory.getLog(MemberController.class);

	@RequestMapping(value = "/members/new", method = RequestMethod.GET)
	public String newMemberForm(
			ModelMap modelMap) {
		Member member = new Member();
		modelMap.addAttribute("memberForm", member);
		return render("member_form");
	}

	@RequestMapping(value = "/members", method = RequestMethod.POST)
	public String createMember(
			@Valid @ModelAttribute("memberForm") Member member,
			BindingResult result,
			SessionStatus status,
			ModelMap modelMap) {
		if (result.hasErrors()) {
			if (LOG.isDebugEnabled()) {
				LOG.debug(result);
			}
			return render("member_form");
		}
		try {
			userService.createUser(member);
		} catch (DuplicateException e) {
//			e.printStackTrace();
			result.rejectValue("username", null, e.getMessage());
			return render("member_form");
		}
		status.setComplete();
		return redirect("/");
	}

	@RequestMapping(value = "/members/{memberId}/edit", method = RequestMethod.GET)
	public String editMemberForm(
			@PathVariable("memberId") Long memberId,
			ModelMap modelMap) {
		Member member = userService.getUser(Member.class, memberId);
		modelMap.addAttribute("memberForm", member);
		return render("member_form");
	}

	@RequestMapping(value = "/members/{memberId}", method = {RequestMethod.PUT, RequestMethod.POST})
	public String updateMember(
			@PathVariable("memberId") Long memberId,
			@Valid @ModelAttribute("memberForm") Member member,
			BindingResult result,
			SessionStatus status,
			ModelMap modelMap) {
		if (result.hasErrors()) {
			if (LOG.isDebugEnabled()) {
				LOG.debug(result);
			}
			return render("member_form");
		}
		try {
			userService.updateUser(member);
		} catch (DuplicateException e) {
//			e.printStackTrace();
			result.rejectValue("username", null, e.getMessage());
			return render("member_form");
		}
		status.setComplete();
		return redirect("/");
	}

	// DI

	private UserService userService;

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
