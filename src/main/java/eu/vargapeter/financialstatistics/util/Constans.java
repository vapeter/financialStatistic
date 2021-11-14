package eu.vargapeter.financialstatistics.util;

import java.time.format.DateTimeFormatter;

public class Constans {

    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final DateTimeFormatter DATE_FORMAT_UNICREDIT_IMPORT = DateTimeFormatter.ofPattern("yyyy.MM.dd");
    public static final DateTimeFormatter DATE_FORMAT_ERSTE_IMPORT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static final String FILE_URL = "/home/";

    public static final String UNICREDIT_AMOUNT = "Összeg";
    public static final String UNICREDIT_DATE = "Érték Dátum";
    public static final String UNICREDIT_PARTNER = "Partner";
    public static final String UNICREDIT_TRANS_DETAILS = "Tranzakció részletek";

    public static final Integer UNICREDIT_IMPORT_AMOUNT_CELL_FIRST_CHAR = 0;
    public static final Integer UNICREDIT_IMPORT_AMOUNT_CELL_LAST_CHAR = 7;
    public static final Integer UNICREDIT_IMPORT_CURRENCY_CELL_LAST_CHAR = -3;

    public static final String ERSTE_DATE = "Könyvelés dátuma";
    public static final String ERSTE_AMOUNT = "Összeg";
    public static final String ERSTE_CURRENCY = "Devizanem";
    public static final String ERSTE_PARTNER_NAME = "Partner név";
    public static final String ERSTE_ANNOUNCEMENT = "Közlemény";

    public static final Integer ERSTE_IMPORT_AMOUNT_CELL_FIRST_CHAR = 0;
    public static final Integer ERSTE_IMPORT_AMOUNT_CELL_LAST_CHAR = -2;

    //excel import columns
    public static final Integer UNICREDIT_AMOUNT_CELL = 1;
    public static final Integer UNICREDIT_CURRENCY_CELL = 1;
    public static final Integer UNICREDIT_DATE_CELL = 4;
    public static final Integer UNICREDIT_PARTNER_NAME_CELL = 6;
    public static final Integer UNICREDIT_ANNOUNCEMENT_CELL = 8;

    public static final Integer ERSTE_AMOUNT_CELL = 1;
    public static final Integer ERSTE_CURRENCY_CELL = 2;
    public static final Integer ERSTE_DATE_CELL = 0;
    public static final Integer ERSTE_PARTNER_NAME_CELL = 3;
    public static final Integer ERSTE_ANNOUNCEMENT_CELL = 7;

    // excel file rows
    public static final Integer EXCEL_FILE_START = 0;
    public static final Integer EXCEL_FILE_CHECK_ROW = 3;
    public static final Integer EXCEL_FILE_DATA_ROWS = 3;

    //uncategorized category
    public static final Integer UNCATEGORYZED_ID = 1;

}
