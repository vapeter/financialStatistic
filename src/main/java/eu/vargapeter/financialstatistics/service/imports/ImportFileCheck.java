package eu.vargapeter.financialstatistics.service.imports;

import org.apache.poi.ss.usermodel.Row;

public interface ImportFileCheck {

    public void uniCreditImportFileCheck(Row row);
    public void ersteImportFileCheck(Row row);
    public void invalidFile();
}
