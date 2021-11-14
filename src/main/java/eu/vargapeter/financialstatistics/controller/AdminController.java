package eu.vargapeter.financialstatistics.controller;

import eu.vargapeter.financialstatistics.model.Bank;
import eu.vargapeter.financialstatistics.model.BankAccount;
import eu.vargapeter.financialstatistics.model.BankAccountType;
import eu.vargapeter.financialstatistics.model.TransactionCategory;
import eu.vargapeter.financialstatistics.service.bank.BankAccountService;
import eu.vargapeter.financialstatistics.service.bank.BankService;
import eu.vargapeter.financialstatistics.service.categorization.TransactionCategoryService;
import eu.vargapeter.financialstatistics.service.transaction.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.Locale;

@Slf4j
@Controller
public class AdminController {

    private MessageSource messageSource;

    private BankService bankService;

    private BankAccountService bankAccountService;

    private TransactionCategoryService transactionCategoryService;

    private TransactionService transactionService;

    @Autowired
    public AdminController(MessageSource messageSource,
                           BankService bankService,
                           BankAccountService bankAccountService,
                           TransactionCategoryService transactionCategoryService,
                           TransactionService transactionService) {
        this.messageSource = messageSource;
        this.bankService = bankService;
        this.bankAccountService = bankAccountService;
        this.transactionCategoryService = transactionCategoryService;
        this.transactionService = transactionService;
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String admin(Principal principal, Model model) {

        model.addAttribute("newBank", new Bank());
        model.addAttribute("newBankAccount", new BankAccount());
        model.addAttribute("newTransactionCategory", new TransactionCategory());
        model.addAttribute("bankList", bankService.findAllBannk());
        model.addAttribute("bankAccountList", bankAccountService.findAllBankAccount());
        model.addAttribute("transactionCategoryList", transactionCategoryService.findAllTransactionCategory());
        model.addAttribute("userName", principal.getName());

        log.info(messageSource.getMessage("allOK",null, Locale.getDefault()));

        return "admin";
    }

    @RequestMapping(value = "/admin/saveNewBank", method = RequestMethod.POST)
    public String saveNewBank(@ModelAttribute Bank newBank) {

        log.debug("Admin controller received new bank: " + newBank.toString());
        bankService.saveBank(newBank);

        return "redirect:/admin";
    }

    @RequestMapping(value = "/admin/saveNewBankAccount", method = RequestMethod.POST)
    public String saveNewBankAccount(@ModelAttribute BankAccount newBankAccount,
                                     @ModelAttribute("bankId") String bankId,
                                     @ModelAttribute BankAccountType bankAccountType) {


        Bank bank = bankService.findBankById(Integer.parseInt(bankId));
        newBankAccount.setBank(bank);
        newBankAccount.setBankAccountType(bankAccountType);

        log.debug("Admin controller received new bankaccount: " + newBankAccount.toString());

        bankAccountService.saveBankAccount(newBankAccount);

        return "redirect:/admin";
    }

    @RequestMapping(value = "/admin/saveNewTransactionCategory", method = RequestMethod.POST)
    public String saveNewTransactionCategory(@ModelAttribute TransactionCategory transactionCategory) {

        log.debug("Admin controller received new transaction category: " + transactionCategory.toString());
        transactionCategoryService.saveTransactionCategory(transactionCategory);
        return "redirect:/admin";
    }

    @RequestMapping(value = "/admin/deleteTransactionCategory/{transactionCategoryId}", method = RequestMethod.POST)
    public String deleteTransactionCategory(@PathVariable("transactionCategoryId") Integer transactionCategoryId) {

        log.debug("Admin controller received transactionCategory on delete: " + transactionCategoryId);
        transactionService.updateTransactionCategoryToUncategorized(transactionCategoryId);

        return "redirect:/admin";
    }
}
