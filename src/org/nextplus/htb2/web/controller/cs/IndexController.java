package org.nextplus.htb2.web.controller.cs;

import java.util.List;

import org.nextplus.commons.Paginator;
import org.nextplus.htb2.domain.Depot;
import org.nextplus.htb2.service.DepotService;
import org.nextplus.htb2.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller("indexController")
public class IndexController extends BaseController {

	@RequestMapping(value="/", method = RequestMethod.GET)
	public String getIndex(
			@RequestParam(value = "p", defaultValue = "1") Integer p,
			@RequestParam(value = "s", defaultValue = Constants.DEF_PAGE_SIZE_STRING) Integer s,
			ModelMap modelMap) {
		Paginator paginator = new Paginator(p, s);
		List<Depot> depots = depotService.getDepots(paginator);
		modelMap.addAttribute("depots", depots);
		modelMap.addAttribute("paginator", paginator);
		return "index";
	}

	@RequestMapping(value="/about", method=RequestMethod.GET)
	public String getAbout() {
		return "about";
	}

	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login() {
		return "cs/login_form";
	}

	// DI

	private DepotService depotService;

	@Autowired
	public void setDepotService(DepotService depotService) {
		this.depotService = depotService;
	}
}
