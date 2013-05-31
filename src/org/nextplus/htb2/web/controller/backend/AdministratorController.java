package org.nextplus.htb2.web.controller.backend;

import java.util.List;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.nextplus.commons.DuplicateException;
import org.nextplus.commons.Paginator;
import org.nextplus.htb2.domain.Administrator;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Controller("backendAdministratorController")
@RequestMapping(Constants.BACKEND_URL_PREFIX)
@SessionAttributes("administratorForm")
public class AdministratorController extends BaseController {

	private final static Log LOG = LogFactory.getLog(AdministratorController.class);

	@RequestMapping(value = "/administrators/new", method = RequestMethod.GET)
	public String newAdministratorForm(
			ModelMap modelMap) {
		Administrator administrator = new Administrator();
		modelMap.addAttribute("administratorForm", administrator);
		return render("administrator_form");
	}

	@RequestMapping(value = "/administrators", method = RequestMethod.POST)
	public String createAdministrator(
			@Valid @ModelAttribute("administratorForm") Administrator administrator,
			BindingResult result,
			SessionStatus status,
			ModelMap modelMap) {
		if (result.hasErrors()) {
			if (LOG.isDebugEnabled()) {
				LOG.debug(result);
			}
			return render("administrator_form");
		}
		try {
			userService.createUser(administrator);
		} catch (DuplicateException e) {
//			e.printStackTrace();
			result.rejectValue("username", null, e.getMessage());
			return render("administrator_form");
		}
		status.setComplete();
		return redirect("/administrators");
	}

	@RequestMapping(value = "/administrators/{administratorId}/edit", method = RequestMethod.GET)
	public String editAdministratorForm(
			@PathVariable("administratorId") Long administratorId,
			ModelMap modelMap) {
		Administrator administrator = userService.getUser(Administrator.class, administratorId);
		modelMap.addAttribute("administratorForm", administrator);
		return render("administrator_form");
	}

	@RequestMapping(value = "/administrators/{administratorId}", method = {RequestMethod.PUT, RequestMethod.POST})
	public String updateAdministrator(
			@PathVariable("administratorId") Long administratorId,
			@Valid @ModelAttribute("administratorForm") Administrator administrator,
			BindingResult result,
			SessionStatus status,
			ModelMap modelMap) {
		if (result.hasErrors()) {
			if (LOG.isDebugEnabled()) {
				LOG.debug(result);
			}
			return render("administrator_form");
		}
		try {
			userService.updateUser(administrator);
		} catch (DuplicateException e) {
//			e.printStackTrace();
			result.rejectValue("username", null, e.getMessage());
			return render("administrator_form");
		}
		status.setComplete();
		return redirect("/administrators");
	}

	@RequestMapping(value = "/administrators/{administratorId}", method = RequestMethod.DELETE)
	public String deleteAdministrators(
			@PathVariable("administratorId") Long administratorId) {
		userService.deleteUser(administratorId);
		return redirect("/administrators");
	}

	@RequestMapping(value = "/administrators", method = RequestMethod.GET)
	public String getAdministrators(
			@RequestParam(value = "p", defaultValue = "1") Integer p,
			@RequestParam(value = "s", defaultValue = "50") Integer s,
			ModelMap modelMap) {
		Paginator paginator = new Paginator(p, s);
		modelMap.addAttribute("paginator", paginator);
		List<Administrator> administrators = userService.getUsers(Administrator.class, paginator);
		modelMap.addAttribute("administrators", administrators);
		return render("administrator_index");
	}

	// DI

	private UserService userService;

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
