package eu.vargapeter.financialstatistics.repository;

import eu.vargapeter.financialstatistics.model.TransactionCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionCategoryRepository extends JpaRepository<TransactionCategory, Integer> {
}
