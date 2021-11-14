package eu.vargapeter.financialstatistics.repository;

import eu.vargapeter.financialstatistics.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    public List<Transaction> findTransactionByPartnerName(String partnerName);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM transactions WHERE transaction_id = :transactionId", nativeQuery = true)
    public void deleteTransactionBytransactionId(String transactionId);

    @Query(value = "SELECT * FROM transactions WHERE transaction_category_id = :transactionCategoryId", nativeQuery = true)
    public List<Transaction> findTransactionByTransactionCategoryId(Integer transactionCategoryId);

}
