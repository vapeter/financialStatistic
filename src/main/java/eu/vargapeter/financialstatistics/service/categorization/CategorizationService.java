package eu.vargapeter.financialstatistics.service.categorization;

import eu.vargapeter.financialstatistics.model.TransactionCategory;

public interface CategorizationService {

    public void initTransactionCategoryByPartnerName();
    public TransactionCategory findTransactionCategoryByPartnerName(String uncategorizedPartnerName);
}
