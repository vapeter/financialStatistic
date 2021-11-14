package eu.vargapeter.financialstatistics.service.imports;

import eu.vargapeter.financialstatistics.model.BankAccount;
import org.apache.poi.ss.usermodel.Row;

public interface ImportTransactionFromFile {

    public void importUniCreditTransaction(Row row, BankAccount bankAccount);
    public void importErsteTransaction(Row row, BankAccount bankAccount);
}
