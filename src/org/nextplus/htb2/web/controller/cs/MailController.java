package org.nextplus.htb2.web.controller.cs;

import java.util.List;

import org.nextplus.commons.Paginator;
import org.nextplus.htb2.domain.Mail;
import org.nextplus.htb2.domain.Member;
import org.nextplus.htb2.service.MailService;
import org.nextplus.htb2.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller("csMailController")
@RequestMapping(Constants.CS_URL_PREFIX)
public class MailController extends BaseController {
	
	@RequestMapping(value = "/mails", method = RequestMethod.GET)
	public String getMails(
			@RequestParam(value = "p", defaultValue = "1") Integer p,
			@RequestParam(value = "s", defaultValue = Constants.DEF_PAGE_SIZE_STRING) Integer s,
			ModelMap modelMap) {
		
		Member member = (Member) getUser();
		Paginator paginator = new Paginator(p, s);
		
		List<Mail> mails = mailService.getMails(member.getId(), paginator);
		
		modelMap.addAttribute("paginator", paginator);
		modelMap.addAttribute("mails", mails);
		return render("mail_index");
	}
	
	@RequestMapping(value = "mails/{mailId}", method = RequestMethod.DELETE)
	public String deleteMail(
			@PathVariable("mailId")Long mailId) {
		mailService.deleteMail(mailId);
		return redirect("/mails");
		
	}
	
	//DI
	private MailService mailService;
	
	@Autowired
	public void setMailService(MailService mailService) {
		this.mailService = mailService;
	}
}
