package eu.vargapeter.financialstatistics.service.imports;

import eu.vargapeter.financialstatistics.model.BankAccount;
import eu.vargapeter.financialstatistics.util.Constans;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Locale;

@Slf4j
@Service
public class ImportFromFileServiceImpl implements ImportFromFileService {

    private ImportTransactionFromFileImpl importTransactionFromFileImpl;

    private ImportFileCheck importFileCheck;

    private MessageSource messageSource;

    @Autowired
    public ImportFromFileServiceImpl(ImportTransactionFromFileImpl importTransactionFromFileImpl,
                                     ImportFileCheck importFileCheck,
                                     MessageSource messageSource) {
        this.importTransactionFromFileImpl = importTransactionFromFileImpl;
        this.importFileCheck = importFileCheck;
        this.messageSource = messageSource;
    }

    @Override
    public void importFromHSSFExcel(String fileName, BankAccount bankAccount, String bank) {

        try (
                FileInputStream excelfile = new FileInputStream(new File(Constans.FILE_URL + fileName));
                Workbook workbook = new HSSFWorkbook(excelfile)
        ) {
            Sheet sheet = workbook.getSheetAt(Constans.EXCEL_FILE_START);
            Iterator<Row> rowIterator = sheet.rowIterator();

            while (rowIterator.hasNext()) {

                Row currentRow = rowIterator.next();

                if (bank.equals("UniCredit")) {
                    if (currentRow.getRowNum() == Constans.EXCEL_FILE_CHECK_ROW) {
                        importFileCheck.uniCreditImportFileCheck(currentRow);
                        log.info(messageSource.getMessage("successFileCheck", null, Locale.getDefault()));
                    } else if (currentRow.getRowNum() > Constans.EXCEL_FILE_DATA_ROWS) {
                        importTransactionFromFileImpl.importUniCreditTransaction(currentRow, bankAccount);
                    }
                }
            }

        } catch (FileNotFoundException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }


    }

    @Override
    public void importFromXSSFExcel(String fileName, BankAccount bankAccount, String bank) {

        try (
                FileInputStream excelfile = new FileInputStream(new File(Constans.FILE_URL + fileName));
                Workbook workbook = new XSSFWorkbook(excelfile)
        ) {
            Sheet sheet = workbook.getSheetAt(Constans.EXCEL_FILE_START);
            Iterator<Row> rowIterator = sheet.rowIterator();

            while (rowIterator.hasNext()) {

                Row currentRow = rowIterator.next();

                if (bank.equals("Erste")) {
                    if (currentRow.getRowNum() == Constans.EXCEL_FILE_CHECK_ROW) {
                        importFileCheck.ersteImportFileCheck(currentRow);
                        log.info(messageSource.getMessage("successFileCheck", null, Locale.getDefault()));
                    } else if (currentRow.getRowNum() > Constans.EXCEL_FILE_DATA_ROWS) {
                        importTransactionFromFileImpl.importErsteTransaction(currentRow, bankAccount);
                    }
                }
            }

        } catch (FileNotFoundException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }
}
