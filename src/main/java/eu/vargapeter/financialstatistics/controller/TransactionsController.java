package eu.vargapeter.financialstatistics.controller;

import eu.vargapeter.financialstatistics.model.Transaction;
import eu.vargapeter.financialstatistics.service.bank.BankAccountService;
import eu.vargapeter.financialstatistics.service.categorization.TransactionCategoryService;
import eu.vargapeter.financialstatistics.service.transaction.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Slf4j
@Controller
public class TransactionsController {

    @Autowired
    TransactionService transactionService;

    @Autowired
    TransactionCategoryService transactionCategoryService;

    @Autowired
    BankAccountService bankAccountService;

    @RequestMapping(value = "/transactions", method = RequestMethod.GET)
    public String transactions(Model model) {

        model.addAttribute("newTransaction", new Transaction());

        model.addAttribute("bankAccountList", bankAccountService.findAllBankAccount());
        model.addAttribute("transactionCategoryList", transactionCategoryService.findAllTransactionCategory());
        model.addAttribute("transactionList", transactionService.findAllTransaction());


        return "transactions";
    }

    @RequestMapping(value = "/saveNewTransaction", method = RequestMethod.POST)
    public String saveNewTransaction(@ModelAttribute Transaction transaction) {

        log.debug("Transacions controller received transaction: " + transaction);

        transactionService.updateTransactionCategoryByPartnerName(transaction.getPartnerName(), transaction.getTransactionCategory());

        transactionService.saveTransaction(transaction);

        return "redirect:/transactions";
    }

    @RequestMapping(value = "/deleteTransaction/{transactionId}", method = RequestMethod.POST)
    public String deleteTransaction(@PathVariable("transactionId") String transactionId) {

        log.debug("Transacions controller received transaction to delete: " + transactionId);

        transactionService.deleteTransactionBytransactionId(transactionId);

        return "redirect:/transactions";
    }
}
