package eu.vargapeter.financialstatistics.model;

import eu.vargapeter.financialstatistics.util.Constans;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import java.io.Serializable;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "transactions")
@Data
public class Transaction implements Serializable {

    @Id
    @Column(name = "transaction_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer transactionId;

    @NotNull
    @Column(name = "date")
    @DateTimeFormat(pattern = Constans.DATE_FORMAT)
    private LocalDate transactionDate;

    @NotNull
    @Column(name = "amount")
    private Long amount;

    @Column(name = "currency")
    private String currency;

    @Column(name = "announcement")
    private String announcement;

    @Column(name = "partner_name")
    private String partnerName;

    @ManyToOne
    @JoinColumn(name = "transaction_category_id")
    private TransactionCategory transactionCategory;

    @ManyToOne
    @JoinColumn(name = "bank_account_id")
    private BankAccount bankAccount;

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId=" + transactionId +
                ", transactionDate=" + transactionDate +
                ", amount=" + amount +
                ", currency='" + currency + '\'' +
                ", announcement='" + announcement + '\'' +
                ", partnerName='" + partnerName + '\'' +
                ", transactionCategory=" + transactionCategory.getTransactionCategoryName() +
                ", bankAccount=" + bankAccount.getBankAccountNumber() +
                '}';
    }
}
