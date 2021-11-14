package eu.vargapeter.financialstatistics.controller;

import eu.vargapeter.financialstatistics.service.bank.BankAccountService;
import eu.vargapeter.financialstatistics.service.bank.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class StatisticController {

    @Autowired
    BankAccountService bankAccountService;

    @Autowired
    BankService bankService;

    @GetMapping(value = "/statistic")
    public String statistic(Model model) {

        model.addAttribute("bankList", bankService.findAllBannk());
        model.addAttribute("bankAccountList", bankAccountService.findAllBankAccount());

        return "statistic";
    }


}
