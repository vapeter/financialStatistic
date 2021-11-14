package eu.vargapeter.financialstatistics.repository;

import eu.vargapeter.financialstatistics.model.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankRepository extends JpaRepository<Bank, Integer> {
}
