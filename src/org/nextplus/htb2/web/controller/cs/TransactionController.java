package org.nextplus.htb2.web.controller.cs;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.nextplus.commons.DuplicateException;
import org.nextplus.commons.Paginator;
import org.nextplus.htb2.domain.Depot;
import org.nextplus.htb2.domain.LogisticsCorp;
import org.nextplus.htb2.domain.Member;
import org.nextplus.htb2.domain.Transaction;
import org.nextplus.htb2.domain.Transaction.State;
import org.nextplus.htb2.service.DepotService;
import org.nextplus.htb2.service.LogisticsCorpService;
import org.nextplus.htb2.service.TransactionService;
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

@Controller("csTransactionController")
@RequestMapping(Constants.CS_URL_PREFIX)
@SessionAttributes("transactionForm")
public class TransactionController extends BaseController {

	@RequestMapping(value = "/transactions/new", method = RequestMethod.GET)
	public String dispNewTransactionForm(
			ModelMap modelMap) {
		Transaction transaction = new Transaction();
		transaction.setMember((Member) getUser());
		modelMap.addAttribute("transactionForm", transaction);
		return render("transaction_form");
	}

	@RequestMapping(value = "/transactions", method = RequestMethod.POST)
	public String createTransaction(
			@Valid @ModelAttribute("transactionForm") Transaction transaction,
			BindingResult result,
			SessionStatus status) throws IllegalStateException, IOException {
		if (!result.hasErrors()) {
			try {
				transactionService.createTransaction(transaction);
				status.setComplete();
				return redirect("/transactions");
			} catch (DuplicateException e) {
				result.rejectValue("name", null, e.getMessage());
			}
		}

		return render("transaction_form");
	}

	@RequestMapping(value = "/transactions/{transactionId}/edit", method = RequestMethod.GET)
	public String dispEditTransactionForm(
			@PathVariable("transactionId") Long transactionId,
			ModelMap modelMap) {
		Transaction transaction = transactionService.getTransaction(transactionId);
		Assert.notNull(transaction, String.format(
				"Transaction[%d] not found.", transactionId));

		modelMap.addAttribute("transactionForm", transaction);
		return render("transaction_form");
	}

	@RequestMapping(value = "/transactions/{transactionId}", method = RequestMethod.POST)
	public String updateTransaction(
			@PathVariable("transactionId") Long transactionId,
			@Valid @ModelAttribute("transactionForm") Transaction transaction,
			BindingResult result,
			SessionStatus status) throws IllegalStateException, IOException {
		if (!result.hasErrors()) {
			try {
				transactionService.updateTransaction(transaction);
				status.setComplete();
				return redirect("/transactions");
			} catch (DuplicateException e) {
				result.rejectValue("name", null, e.getMessage());
			}
		}

		return render("transaction_form");
	}

	@RequestMapping(value = "/transactions/{transactionId}", method = RequestMethod.DELETE)
	public String deleteTransaction(
			@PathVariable("transactionId") Long transactionId) {
		transactionService.deleteTransaction(transactionId);
		return redirect("/transactions");
	}

	@RequestMapping(value = "/transactions", method = RequestMethod.GET)
	public String dispTransactions(
			@RequestParam(value = "from", required = false) Date from,
			@RequestParam(value = "to", required = false) Date to,
			@RequestParam(value = "state", required = false) State state,
			@RequestParam(value = "depotId", required = false) Long depotId,
			@RequestParam(value = "p", defaultValue = "1") Integer p,
			@RequestParam(value = "s", defaultValue = Constants.DEF_PAGE_SIZE_STRING) Integer s,
			ModelMap modelMap) {
		Paginator paginator = new Paginator(p, s);
		List<Transaction> transactions = transactionService.getTransactions(getUserId(), from, to,
				state, depotId, paginator);

		modelMap.addAttribute("paginator", paginator);
		modelMap.addAttribute("transactions", transactions);
		
		populateModelMap(modelMap);
		
		return render("transaction_index");
	}

	@RequestMapping(value = "/transactions/{transactionId}", method = RequestMethod.GET)
	public String dispTransaction(
			@PathVariable("transactionId") Long transactionId,
			ModelMap modelMap) {
		Transaction transaction = transactionService.getTransaction(transactionId);
		Assert.notNull(transaction, String.format(
				"Transaction[%d] not found.", transactionId));

		modelMap.addAttribute("transaction", transaction);
		return render("transaction_detail");
	}
	
	//helper
	private void populateModelMap(ModelMap modelMap) {
		List<Depot> depots = depotService.getDepots(null);
		modelMap.addAttribute("depots", depots);
		List<LogisticsCorp> logisticsCorps = logisticsCorpService.getLogisticsCorps(null);
		modelMap.addAttribute("logisticsCorps", logisticsCorps);
		State[] states = State.values();
		modelMap.addAttribute("states", states);
	}

	// DI

	private TransactionService transactionService;
	private LogisticsCorpService logisticsCorpService;
	private DepotService depotService;

	@Autowired
	public void setTransactionService(TransactionService transactionService) {
		this.transactionService = transactionService;
	}
	
	@Autowired
	public void setLogisticsCorpService(LogisticsCorpService logisticsCorpService) {
		this.logisticsCorpService = logisticsCorpService;
	}

	@Autowired
	public void setDepotService(DepotService depotService) {
		this.depotService = depotService;
	}

}
