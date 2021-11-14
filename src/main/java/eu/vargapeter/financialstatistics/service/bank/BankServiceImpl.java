package eu.vargapeter.financialstatistics.service.bank;

import eu.vargapeter.financialstatistics.model.Bank;
import eu.vargapeter.financialstatistics.repository.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
public class BankServiceImpl implements BankService {

    private BankRepository bankRepository;

    @Autowired
    public BankServiceImpl(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    @Override
    public List<Bank> findAllBannk() {
        return bankRepository.findAll();
    }

    @Override
    @Transactional
    public void saveBank(Bank bank) {
        bankRepository.save(bank);
    }

    @Override
    public Bank findBankById(Integer bankId) {
        return bankRepository.getOne(bankId);
    }
}
