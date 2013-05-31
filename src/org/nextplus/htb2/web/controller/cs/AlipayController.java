package org.nextplus.htb2.web.controller.cs;

import org.nextplus.htb2.domain.Transaction;
import org.nextplus.htb2.service.TransactionService;
import org.nextplus.htb2.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller("csAlipayController")
@RequestMapping(Constants.CS_URL_PREFIX)
@SessionAttributes("transactionForm")
public class AlipayController extends BaseController {
	
	@RequestMapping(value = "transactions/{transationId}/pay", method = RequestMethod.GET)
	public String pendingPay(
			@PathVariable("transationId") Long transationId,
			ModelMap modelMap)  {
		Transaction transaction = transactionService.getTransaction(transationId);		
		modelMap.addAttribute("transaction", transaction);
		
		return render("pendingPay");
	}
	
	
	//DI
	
	private TransactionService transactionService;

	@Autowired
	public void setTransactionService(TransactionService transactionService) {
		this.transactionService = transactionService;
	}
	
	
}
