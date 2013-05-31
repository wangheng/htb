package org.nextplus.htb2.web.controller.cs;

import org.nextplus.htb2.util.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller("csDefaultController")
@RequestMapping(Constants.CS_URL_PREFIX)
public class DefaultController extends BaseController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(
			ModelMap modelMap) {
		return render("default_index");
	}

}
