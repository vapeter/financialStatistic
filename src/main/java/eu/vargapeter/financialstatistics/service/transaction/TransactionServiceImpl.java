package eu.vargapeter.financialstatistics.service.transaction;

import eu.vargapeter.financialstatistics.model.BankAccount;
import eu.vargapeter.financialstatistics.model.Transaction;
import eu.vargapeter.financialstatistics.model.TransactionCategory;
import eu.vargapeter.financialstatistics.repository.TransactionRepository;
import eu.vargapeter.financialstatistics.service.bank.BankAccountService;
import eu.vargapeter.financialstatistics.service.categorization.TransactionCategoryService;
import eu.vargapeter.financialstatistics.util.Constans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    private TransactionRepository transactionRepository;

    private BankAccountService bankAccountService;

    private TransactionCategoryService transactionCategoryService;

    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository,
                                  BankAccountService bankAccountService,
                                  TransactionCategoryService transactionCategoryService) {
        this.transactionRepository = transactionRepository;
        this.bankAccountService = bankAccountService;
        this.transactionCategoryService = transactionCategoryService;
    }

    @Override
    public List<Transaction> findAllTransaction() {
        return transactionRepository.findAll();
    }

    @Override
    @Transactional
    public void saveTransaction(Transaction transaction) {

        if (transaction.getTransactionId() == null) {
            BankAccount bankAccount = bankAccountService.findBankAccountById(transaction.getBankAccount().getBankAccountId());
            bankAccount.setAmount(bankAccount.getAmount() + transaction.getAmount());
            bankAccountService.saveBankAccount(bankAccount);
        }

        transactionRepository.save(transaction);
    }

    @Override
    public void updateTransactionCategoryByPartnerName(String partnerName, TransactionCategory transactionCategory) {

        List<Transaction> transactions = transactionRepository.findTransactionByPartnerName(partnerName);

        for (Transaction transaction: transactions) {
            transaction.setTransactionCategory(transactionCategory);
            saveTransaction(transaction);
        }
    }

    @Override
    @Transactional
    public void deleteTransactionBytransactionId(String transactionId) {
        transactionRepository.deleteTransactionBytransactionId(transactionId);
    }

    @Override
    public void updateTransactionCategoryToUncategorized(Integer transactionCategoryId) {

        List<Transaction> transactionList = transactionRepository.findTransactionByTransactionCategoryId(transactionCategoryId);
        TransactionCategory uncategorized = transactionCategoryService.findTransacionCategoryById(Constans.UNCATEGORYZED_ID);

        for (Transaction transactions: transactionList) {
            transactions.setTransactionCategory(uncategorized);
            transactionRepository.save(transactions);
        }
        transactionCategoryService.deleteTransactionCategoryById(transactionCategoryId);
    }


}
