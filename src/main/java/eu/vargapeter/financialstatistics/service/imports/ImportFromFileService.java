package eu.vargapeter.financialstatistics.service.imports;

import eu.vargapeter.financialstatistics.model.BankAccount;

public interface ImportFromFileService {

    public void importFromHSSFExcel(String fileName, BankAccount bankAccount, String bank);
    public void importFromXSSFExcel(String fileName, BankAccount bankAccount, String bank);
}
