package eu.vargapeter.financialstatistics.service.categorization;

import eu.vargapeter.financialstatistics.model.TransactionCategory;

import java.util.List;

public interface TransactionCategoryService {

    public List<TransactionCategory> findAllTransactionCategory();
    public void saveTransactionCategory(TransactionCategory transactionCategory);
    public TransactionCategory findTransacionCategoryById(Integer transactionId);
    public void deleteTransactionCategoryById(Integer transactionCategoryId);
}
