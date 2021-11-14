package eu.vargapeter.financialstatistics.controller;

import eu.vargapeter.financialstatistics.model.Transaction;
import eu.vargapeter.financialstatistics.service.transaction.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class StatisticRestController {

    List<Transaction> transactionList;

    @Autowired
    TransactionService transactionService;

    @GetMapping(value = "/getAmountByCategory")
    public List<Transaction> getAllTransaction() {

        transactionList = transactionService.findAllTransaction();
        return transactionList;
    }

}
