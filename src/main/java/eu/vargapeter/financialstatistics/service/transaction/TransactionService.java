package eu.vargapeter.financialstatistics.service.transaction;

import eu.vargapeter.financialstatistics.model.Transaction;
import eu.vargapeter.financialstatistics.model.TransactionCategory;

import java.util.List;

public interface TransactionService {

    public List<Transaction> findAllTransaction();
    public void saveTransaction(Transaction transaction);
    public void updateTransactionCategoryByPartnerName(String partnerName, TransactionCategory transactionCategory);
    public void deleteTransactionBytransactionId(String transactionId);
    public void updateTransactionCategoryToUncategorized(Integer transactionCategoryId);
}
