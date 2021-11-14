package eu.vargapeter.financialstatistics.service.bank;

import eu.vargapeter.financialstatistics.model.BankAccount;

import java.util.List;

public interface BankAccountService {

    public List<BankAccount> findAllBankAccount();
    public void saveBankAccount(BankAccount bankAccount);
    public BankAccount findBankAccountById(Integer bankAccountId);
}
