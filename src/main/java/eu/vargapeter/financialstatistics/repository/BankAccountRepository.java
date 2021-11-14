package eu.vargapeter.financialstatistics.repository;

import eu.vargapeter.financialstatistics.model.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Integer> {
}
