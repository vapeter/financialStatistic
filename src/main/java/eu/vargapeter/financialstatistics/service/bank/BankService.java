package eu.vargapeter.financialstatistics.service.bank;

import eu.vargapeter.financialstatistics.model.Bank;

import java.util.List;

public interface BankService {

    public List<Bank> findAllBannk();
    public void saveBank(Bank bank);
    public Bank findBankById(Integer bankId);

}
