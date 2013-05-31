package org.nextplus.htb2.web.controller.backend;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.nextplus.commons.DuplicateException;
import org.nextplus.commons.Paginator;
import org.nextplus.htb2.domain.Depot;
import org.nextplus.htb2.service.DepotService;
import org.nextplus.htb2.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Controller("backendDepotController")
@RequestMapping(Constants.BACKEND_URL_PREFIX)
@SessionAttributes("depotForm")
public class DepotController extends BaseController {

	@RequestMapping(value = "/depots/new", method = RequestMethod.GET)
	public String dispNewDepotForm(
			ModelMap modelMap) {
		Depot depot = new Depot();
		modelMap.addAttribute("depotForm", depot);
		return render("depot_form");
	}

	@RequestMapping(value = "/depots", method = RequestMethod.POST)
	public String createDepot(
			@Valid @ModelAttribute("depotForm") Depot depot,
			BindingResult result,
			SessionStatus status) throws IllegalStateException, IOException {
		System.out.println(depot.getId());
		if (!result.hasErrors()) {
			try {
				depotService.createDepot(depot);
				status.setComplete();
				System.out.println(depot.getId());
				return redirect("/depots");
			} catch (DuplicateException e) {
				result.rejectValue("name", null, e.getMessage());
			}
		}
		return render("depot_form");
	}

	@RequestMapping(value = "/depots/{depotId}/edit", method = RequestMethod.GET)
	public String dispEditDepotForm(
			@PathVariable("depotId") Long depotId,
			ModelMap modelMap) {
		Depot depot = depotService.getDepot(depotId);
		Assert.notNull(depot, String.format(
				"Depot[%d] not found.", depotId));

		modelMap.addAttribute("depotForm", depot);
		return render("depot_form");
	}

	@RequestMapping(value = "/depots/{depotId}", method = RequestMethod.POST)
	public String updateDepot(
			@PathVariable("depotId") Long depotId,
			@Valid @ModelAttribute("depotForm") Depot depot,
			BindingResult result,
			SessionStatus status) throws IllegalStateException, IOException {
		if (!result.hasErrors()) {
			try {
				depotService.updateDepot(depot);
				status.setComplete();
				return redirect("/depots");
			} catch (DuplicateException e) {
				result.rejectValue("name", null, e.getMessage());
			}
		}

		return render("depot_form");
	}

	@RequestMapping(value = "/depots/{depotId}", method = RequestMethod.DELETE)
	public String deleteDepot(
			@PathVariable("depotId") Long depotId) {
		depotService.deleteDepot(depotId);
		return redirect("/depots");
	}

	@RequestMapping(value = "/depots", method = RequestMethod.GET)
	public String dispDepots(
			@RequestParam(value = "p", defaultValue = "1") Integer p,
			@RequestParam(value = "s", defaultValue = Constants.DEF_PAGE_SIZE_STRING) Integer s,
			ModelMap modelMap) {
		Paginator paginator = new Paginator(p, s);
		List<Depot> depots = depotService.getDepots(paginator);

		modelMap.addAttribute("paginator", paginator);
		modelMap.addAttribute("depots", depots);
		return render("depot_index");
	}

	@RequestMapping(value = "/depots/{depotId}", method = RequestMethod.GET)
	public String dispDepot(
			@PathVariable("depotId") Long depotId,
			ModelMap modelMap) {
		Depot depot = depotService.getDepot(depotId);
		Assert.notNull(depot, String.format(
				"Depot[%d] not found.", depotId));

		modelMap.addAttribute("depot", depot);
		return render("depot_detail");
	}

	// DI

	private DepotService depotService;

	@Autowired
	public void setDepotService(DepotService depotService) {
		this.depotService = depotService;
	}

}
