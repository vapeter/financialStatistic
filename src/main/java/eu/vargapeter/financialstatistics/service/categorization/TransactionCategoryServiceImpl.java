package eu.vargapeter.financialstatistics.service.categorization;

import eu.vargapeter.financialstatistics.model.TransactionCategory;
import eu.vargapeter.financialstatistics.repository.TransactionCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
public class TransactionCategoryServiceImpl implements TransactionCategoryService {

    private TransactionCategoryRepository transactionCategoryRepository;

    @Autowired
    public TransactionCategoryServiceImpl(TransactionCategoryRepository transactionCategoryRepository) {
        this.transactionCategoryRepository = transactionCategoryRepository;
    }

    @Override
    public List<TransactionCategory> findAllTransactionCategory() {
        return transactionCategoryRepository.findAll();
    }

    @Override
    @Transactional
    public void saveTransactionCategory(TransactionCategory transactionCategory) {
        transactionCategoryRepository.save(transactionCategory);
    }

    @Override
    public TransactionCategory findTransacionCategoryById(Integer transactionId) {
        return transactionCategoryRepository.getOne(transactionId);
    }

    @Override
    @Transactional
    public void deleteTransactionCategoryById(Integer transactionCategoryId) {
        transactionCategoryRepository.deleteById(transactionCategoryId);
    }
}
