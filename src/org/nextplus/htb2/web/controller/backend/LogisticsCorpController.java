package org.nextplus.htb2.web.controller.backend;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.nextplus.commons.DuplicateException;
import org.nextplus.commons.Paginator;
import org.nextplus.htb2.domain.LogisticsCorp;
import org.nextplus.htb2.service.LogisticsCorpService;
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

@Controller("backendLogisticsCorpController")
@RequestMapping(Constants.BACKEND_URL_PREFIX)
@SessionAttributes("logisticsCorpForm")
public class LogisticsCorpController extends BaseController {

	@RequestMapping(value = "/logisticsCorps/new", method = RequestMethod.GET)
	public String dispNewLogisticsCorpForm(
			ModelMap modelMap) {
		LogisticsCorp logisticsCorp = new LogisticsCorp();
		modelMap.addAttribute("logisticsCorpForm", logisticsCorp);
		return render("logisticsCorp_form");
	}

	@RequestMapping(value = "/logisticsCorps", method = RequestMethod.POST)
	public String createLogisticsCorp(
			@Valid @ModelAttribute("logisticsCorpForm") LogisticsCorp logisticsCorp,
			BindingResult result,
			SessionStatus status) throws IllegalStateException, IOException {
		if (!result.hasErrors()) {
			try {
				logisticsCorpService.createLogisticsCorp(logisticsCorp);
				status.setComplete();
				return redirect("/logisticsCorps");
			} catch (DuplicateException e) {
				result.rejectValue("name", null, e.getMessage());
			}
		}

		return render("logisticsCorp_form");
	}

	@RequestMapping(value = "/logisticsCorps/{logisticsCorpId}/edit", method = RequestMethod.GET)
	public String dispEditLogisticsCorpForm(
			@PathVariable("logisticsCorpId") Long logisticsCorpId,
			ModelMap modelMap) {
		LogisticsCorp logisticsCorp = logisticsCorpService.getLogisticsCorp(logisticsCorpId);
		Assert.notNull(logisticsCorp, String.format(
				"LogisticsCorp[%d] not found.", logisticsCorpId));

		modelMap.addAttribute("logisticsCorpForm", logisticsCorp);
		return render("logisticsCorp_form");
	}

	@RequestMapping(value = "/logisticsCorps/{logisticsCorpId}", method = RequestMethod.POST)
	public String updateLogisticsCorp(
			@PathVariable("logisticsCorpId") Long logisticsCorpId,
			@Valid @ModelAttribute("logisticsCorpForm") LogisticsCorp logisticsCorp,
			BindingResult result,
			SessionStatus status) throws IllegalStateException, IOException {
		if (!result.hasErrors()) {
			try {
				logisticsCorpService.updateLogisticsCorp(logisticsCorp);
				status.setComplete();
				return redirect("/logisticsCorps");
			} catch (DuplicateException e) {
				result.rejectValue("name", null, e.getMessage());
			}
		}

		return render("logisticsCorp_form");
	}

	@RequestMapping(value = "/logisticsCorps/{logisticsCorpId}", method = RequestMethod.DELETE)
	public String deleteLogisticsCorp(
			@PathVariable("logisticsCorpId") Long logisticsCorpId) {
		logisticsCorpService.deleteLogisticsCorp(logisticsCorpId);
		return redirect("/logisticsCorps");
	}

	@RequestMapping(value = "/logisticsCorps", method = RequestMethod.GET)
	public String dispLogisticsCorps(
			@RequestParam(value = "p", defaultValue = "1") Integer p,
			@RequestParam(value = "s", defaultValue = Constants.DEF_PAGE_SIZE_STRING) Integer s,
			ModelMap modelMap) {
		Paginator paginator = new Paginator(p, s);
		List<LogisticsCorp> logisticsCorps = logisticsCorpService.getLogisticsCorps(paginator);

		modelMap.addAttribute("paginator", paginator);
		modelMap.addAttribute("logisticsCorps", logisticsCorps);
		return render("logisticsCorp_index");
	}

	@RequestMapping(value = "/logisticsCorps/{logisticsCorpId}", method = RequestMethod.GET)
	public String dispLogisticsCorp(
			@PathVariable("logisticsCorpId") Long logisticsCorpId,
			ModelMap modelMap) {
		LogisticsCorp logisticsCorp = logisticsCorpService.getLogisticsCorp(logisticsCorpId);
		Assert.notNull(logisticsCorp, String.format(
				"LogisticsCorp[%d] not found.", logisticsCorpId));

		modelMap.addAttribute("logisticsCorp", logisticsCorp);
		return render("logisticsCorp_detail");
	}

	// DI

	private LogisticsCorpService logisticsCorpService;

	@Autowired
	public void setLogisticsCorpService(LogisticsCorpService logisticsCorpService) {
		this.logisticsCorpService = logisticsCorpService;
	}

}
