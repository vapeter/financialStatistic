package eu.vargapeter.financialstatistics.service.bank;

import eu.vargapeter.financialstatistics.model.BankAccount;
import eu.vargapeter.financialstatistics.repository.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
public class BankAccountServiceImpl implements BankAccountService {


    private BankAccountRepository bankAccountRepository;

    @Autowired
    public BankAccountServiceImpl(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    @Override
    public List<BankAccount> findAllBankAccount() {
        return bankAccountRepository.findAll();
    }

    @Override
    @Transactional
    public void saveBankAccount(BankAccount bankAccount) {
        bankAccountRepository.save(bankAccount);
    }

    @Override
    public BankAccount findBankAccountById(Integer bankAccountId) {
        return bankAccountRepository.getOne(bankAccountId);
    }
}
