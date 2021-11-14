package eu.vargapeter.financialstatistics.service.imports;

import eu.vargapeter.financialstatistics.exception.ImportFileFormatNotValidException;
import eu.vargapeter.financialstatistics.util.Constans;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class ImportFileCheckImpl implements ImportFileCheck {

    private MessageSource messageSource;

    @Autowired
    public ImportFileCheckImpl(MessageSource messageSource) {
        this.messageSource = messageSource;
    }


    @Override
    public void uniCreditImportFileCheck(Row row) {

        if (    !row.getCell(Constans.UNICREDIT_AMOUNT_CELL).getStringCellValue().trim().equals(Constans.UNICREDIT_AMOUNT) ||
                !row.getCell(Constans.UNICREDIT_DATE_CELL).getStringCellValue().trim().equals(Constans.UNICREDIT_DATE) ||
                !row.getCell(Constans.UNICREDIT_PARTNER_NAME_CELL).getStringCellValue().trim().equals(Constans.UNICREDIT_PARTNER) ||
                !row.getCell(Constans.UNICREDIT_ANNOUNCEMENT_CELL).getStringCellValue().trim().equals(Constans.UNICREDIT_TRANS_DETAILS)
        ) {
            invalidFile();
        }


    }

    @Override
    public void ersteImportFileCheck(Row row) {

        if (    !row.getCell(Constans.ERSTE_DATE_CELL).getStringCellValue().trim().equals(Constans.ERSTE_DATE) ||
                !row.getCell(Constans.ERSTE_AMOUNT_CELL).getStringCellValue().trim().equals(Constans.ERSTE_AMOUNT) ||
                !row.getCell(Constans.ERSTE_CURRENCY_CELL).getStringCellValue().trim().equals(Constans.ERSTE_CURRENCY) ||
                !row.getCell(Constans.ERSTE_PARTNER_NAME_CELL).getStringCellValue().trim().equals(Constans.ERSTE_PARTNER_NAME) ||
                !row.getCell(Constans.ERSTE_ANNOUNCEMENT_CELL).getStringCellValue().trim().equals(Constans.ERSTE_ANNOUNCEMENT)
        ) {
            invalidFile();
        }
    }

    public void invalidFile() {
        throw new ImportFileFormatNotValidException(
                messageSource.getMessage("invalidImportFileFormat", null, Locale.getDefault()));
    }
}
