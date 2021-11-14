package eu.vargapeter.financialstatistics.service.categorization;

import eu.vargapeter.financialstatistics.model.Transaction;
import eu.vargapeter.financialstatistics.model.TransactionCategory;
import eu.vargapeter.financialstatistics.service.transaction.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class CategorizationServiceImpl implements CategorizationService{
    
    private HashMap<String, TransactionCategory> categorizedTransactions = new HashMap<>();

    private TransactionService transactionService;

    @Autowired
    public CategorizationServiceImpl(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @Override
    public void initTransactionCategoryByPartnerName() {
        
        List<Transaction> transactions = transactionService.findAllTransaction();

        for (Transaction transaction: transactions) {
            categorizedTransactions.put(transaction.getPartnerName(), transaction.getTransactionCategory());
        }
    }

    @Override
    public TransactionCategory findTransactionCategoryByPartnerName(String uncategorizedPartnerName) {

        initTransactionCategoryByPartnerName();

        return categorizedTransactions.get(uncategorizedPartnerName);

    }
    
    
    
}
