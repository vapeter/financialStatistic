package eu.vargapeter.financialstatistics.service.imports;

import eu.vargapeter.financialstatistics.exception.ImportCellException;
import eu.vargapeter.financialstatistics.model.BankAccount;
import eu.vargapeter.financialstatistics.model.Transaction;
import eu.vargapeter.financialstatistics.service.categorization.CategorizationService;
import eu.vargapeter.financialstatistics.service.categorization.TransactionCategoryService;
import eu.vargapeter.financialstatistics.service.transaction.TransactionService;
import eu.vargapeter.financialstatistics.util.Constans;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Locale;

@Slf4j
@Service
public class ImportTransactionFromFileImpl implements ImportTransactionFromFile {

    private TransactionService transactionService;

    private TransactionCategoryService transactionCategoryService;

    private CategorizationService categorizationService;

    private MessageSource messageSource;

    @Autowired
    public ImportTransactionFromFileImpl(TransactionService transactionService,
                                         TransactionCategoryService transactionCategoryService,
                                         CategorizationService categorizationService,
                                         MessageSource messageSource) {
        this.transactionService = transactionService;
        this.transactionCategoryService = transactionCategoryService;
        this.categorizationService = categorizationService;
        this.messageSource = messageSource;
    }

    @Override
    public void importUniCreditTransaction(Row row, BankAccount bankAccount) {

        Transaction transaction = new Transaction();

        try {
            String amountCell = row.getCell(Constans.UNICREDIT_AMOUNT_CELL).getStringCellValue().trim();
            String amountString = amountCell.substring(0, (amountCell.length() - 7));
            String amount = amountString.replaceAll("\u00A0", "");
            log.debug("File import UniCredit amount: " + amount);
            transaction.setAmount(Long.parseLong(amount));
        } catch (NumberFormatException e) {
            log.error(messageSource.getMessage("invalidImportAmount", null, Locale.getDefault()));
        }

        try {
            String currencyCell = row.getCell(Constans.UNICREDIT_CURRENCY_CELL).getStringCellValue().trim();
            String currency = currencyCell.substring((currencyCell.length() - 3), currencyCell.length());
            transaction.setCurrency(currency);
        } catch (Exception e) {
            throw new ImportCellException("Currency cell is invalid! " + e.getMessage());
        }


        try {
            log.debug("File import UniCredit date: " + row.getCell(Constans.UNICREDIT_DATE_CELL).getStringCellValue());
            transaction.setTransactionDate(LocalDate.parse(row.getCell(
                    Constans.UNICREDIT_DATE_CELL).getStringCellValue(), Constans.DATE_FORMAT_UNICREDIT_IMPORT));
        } catch (DateTimeParseException e) {
            log.error(messageSource.getMessage("invalidImportDate", null, Locale.getDefault()));
            throw new ImportCellException("Date cell is invalid! " + e.getMessage());
        }

        try {
            transaction.setPartnerName(row.getCell(Constans.UNICREDIT_PARTNER_NAME_CELL).getStringCellValue());
        } catch (Exception e) {
            throw new ImportCellException("Partner name cell is invalid! " + e.getMessage());
        }

        try {
            transaction.setAnnouncement(row.getCell(Constans.UNICREDIT_ANNOUNCEMENT_CELL).getStringCellValue());
        } catch (Exception e) {
            throw new ImportCellException("Announcement cell is invalid! " + e.getMessage());
        }

        if (categorizationService.findTransactionCategoryByPartnerName(transaction.getPartnerName()) == null) {
            transaction.setTransactionCategory(transactionCategoryService.findTransacionCategoryById(Constans.UNCATEGORYZED_ID));
        } else {
            transaction.setTransactionCategory(categorizationService.findTransactionCategoryByPartnerName(transaction.getPartnerName()));
        }

        transaction.setBankAccount(bankAccount);

        transactionService.saveTransaction(transaction);

    }

    @Override
    public void importErsteTransaction(Row row, BankAccount bankAccount) {

        Transaction transaction = new Transaction();

        try {
            Double amountCell = row.getCell(Constans.ERSTE_AMOUNT_CELL).getNumericCellValue();
            log.debug("File import Erste amount: " + amountCell.longValue());
            transaction.setAmount(amountCell.longValue());
        } catch (NumberFormatException e) {
            log.error(messageSource.getMessage("invalidImportAmount", null, Locale.getDefault()));
            throw new ImportCellException("Amount cell is invalid! " + e.getMessage());
        }

        try {
            String currency = row.getCell(Constans.ERSTE_CURRENCY_CELL).getStringCellValue().trim();
            transaction.setCurrency(currency);
        } catch (Exception e) {
            throw new ImportCellException("Currency cell is invalid! " + e.getMessage());
        }

        try {
            log.debug("File import Erste date: " + row.getCell(Constans.ERSTE_DATE_CELL)
                    .getLocalDateTimeCellValue().format(Constans.DATE_FORMAT_ERSTE_IMPORT));
            transaction.setTransactionDate(LocalDate.parse(row.getCell(Constans.ERSTE_DATE_CELL)
                    .getLocalDateTimeCellValue().format(Constans.DATE_FORMAT_ERSTE_IMPORT)));
        } catch (DateTimeParseException e) {
            log.error(messageSource.getMessage("invalidImportDate", null, Locale.getDefault()));
            throw new ImportCellException("Date cell is invalid! " + e.getMessage());
        }

        try {
            transaction.setPartnerName(row.getCell(Constans.ERSTE_PARTNER_NAME_CELL).getStringCellValue());
            log.debug(transaction.getPartnerName());
        } catch (Exception e) {
            throw new ImportCellException("Partner name cell is invalid " + e.getMessage());
        }

        try {
            transaction.setAnnouncement(row.getCell(Constans.ERSTE_ANNOUNCEMENT_CELL).getStringCellValue());
            log.debug(transaction.getAnnouncement());
        } catch (Exception e) {
            throw new ImportCellException("Announcement cell is invalid " + e.getMessage());
        }

        if (categorizationService.findTransactionCategoryByPartnerName(transaction.getPartnerName()) == null) {
            transaction.setTransactionCategory(transactionCategoryService.findTransacionCategoryById(Constans.UNCATEGORYZED_ID));
        } else {
            transaction.setTransactionCategory(categorizationService.findTransactionCategoryByPartnerName(transaction.getPartnerName()));
        }

        log.debug(transaction.getTransactionCategory().getTransactionCategoryName());

        transaction.setBankAccount(bankAccount);
        log.debug(transaction.toString());
        transactionService.saveTransaction(transaction);

    }
}
