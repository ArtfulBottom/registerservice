package edu.uark.controllers;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.uark.commands.transactionEntry.TransactionEntrySaveCommand;
import edu.uark.models.api.TransactionEntry;

@RestController
@RequestMapping(value = "/transactionEntry")
public class TransactionEntryRestController {
	@RequestMapping(value = "/apiv0/", method = RequestMethod.PUT)
	public TransactionEntry putTransactionEntry(@RequestBody TransactionEntry transactionEntry) {
		return (new TransactionEntrySaveCommand()).
			setApiTransactionEntry(transactionEntry).
			execute();
	}

	@ResponseBody
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test() {
		return "Successful test. (TransactionEntryRestController)";
	}
} 