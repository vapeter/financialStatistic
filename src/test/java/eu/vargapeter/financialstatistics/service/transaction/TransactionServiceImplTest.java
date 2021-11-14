package eu.vargapeter.financialstatistics.service.transaction;

import eu.vargapeter.financialstatistics.model.BankAccount;
import eu.vargapeter.financialstatistics.model.Transaction;
import eu.vargapeter.financialstatistics.repository.TransactionRepository;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TransactionServiceImplTest {

    @InjectMocks
    TransactionServiceImpl transactionService;

    @Mock
    TransactionRepository transactionRepository;

    private static List<Transaction> transactionList = new ArrayList<>();
    private static BankAccount bankAccount = new BankAccount();

    @BeforeClass
    public static void init() {

        bankAccount.setBankAccountNumber("01234567-01234567-01234567");
        bankAccount.setBankAccountId(1);
        bankAccount.setAmount(10000L);

        Transaction oneTrans = new Transaction();
        oneTrans.setTransactionId(1);
        oneTrans.setAmount(-9000L);
        oneTrans.setPartnerName("MOL");
        oneTrans.setBankAccount(bankAccount);

        Transaction secTrans = new Transaction();
        secTrans.setAmount(8000L);
        secTrans.setPartnerName("OMV");

        transactionList.add(oneTrans);
        transactionList.add(secTrans);

    }


    @Test
    public void testFindAllTransactions() {


       when(transactionRepository.findAll()).thenReturn(transactionList);

       List<Transaction> transactions = transactionService.findAllTransaction();
       assertEquals(2, transactions.size());
       verify(transactionRepository, times(1)).findAll();

    }

    @Test
    public void testSaveTransactionWithID() {

        transactionService.saveTransaction(transactionList.get(0));

        verify(transactionRepository, times(1)).save(transactionList.get(0));

    }

    @Test
    public void testDeleteTransactionBytransactionId() {

        transactionService.deleteTransactionBytransactionId(
                transactionList.get(0).getTransactionId().toString());

        verify(transactionRepository, times(1)).deleteTransactionBytransactionId(
                transactionList.get(0).getTransactionId().toString());
    }

}