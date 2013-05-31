package org.nextplus.htb2.web.controller.backend;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.nextplus.commons.Paginator;
import org.nextplus.htb2.domain.Member;
import org.nextplus.htb2.service.UserService;
import org.nextplus.htb2.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller("backendMemberController")
@RequestMapping(Constants.BACKEND_URL_PREFIX)
@SessionAttributes("memberForm")
public class MemberController extends BaseController {

	@SuppressWarnings("unused")
	private final static Log LOG = LogFactory.getLog(MemberController.class);

	@RequestMapping(value = "/members/{memberId}", method = RequestMethod.DELETE)
	public String deleteMembers(
			@PathVariable("memberId") Long memberId) {
		userService.deleteUser(memberId);
		return redirect("/members");
	}

	@RequestMapping(value = "/members", method = RequestMethod.GET)
	public String dispMembers(
			@RequestParam(value = "p", defaultValue = "1") Integer p,
			@RequestParam(value = "s", defaultValue = Constants.DEF_PAGE_SIZE_STRING) Integer s,
			ModelMap modelMap) {
		Paginator paginator = new Paginator(p, s);
		List<Member> members = userService.getUsers(Member.class, paginator);

		modelMap.addAttribute("paginator", paginator);
		modelMap.addAttribute("members", members);
		return render("member_index");
	}

	@RequestMapping(value = "/members/{memberId}", method = RequestMethod.GET)
	public String dispMember(
			@PathVariable("memberId") Long memberId,
			ModelMap modelMap) {
		Member member = userService.getUser(Member.class, memberId);
		Assert.notNull(member, String.format(
				"Member[%d] not found.", memberId));

		modelMap.addAttribute("member", member);
		return render("member_detail");
	}

	// DI

	private UserService userService;

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
