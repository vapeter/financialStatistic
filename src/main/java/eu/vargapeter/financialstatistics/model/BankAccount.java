package eu.vargapeter.financialstatistics.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;
import java.io.Serializable;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "bank_accounts")
@Data
public class BankAccount implements Serializable {

    @Id
    @Column(name = "bank_account_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bankAccountId;

    @NotNull
    @Column(name = "bank_account_number")
    private String bankAccountNumber;

    @NotNull
    @Column(name = "amount")
    private Long amount;

    @NotNull
    @Column(name = "bank_account_type")
    private BankAccountType bankAccountType;

    @ManyToOne
    @JoinColumn(name = "bank_id")
    private Bank bank;

    @JsonIgnore
    @OneToMany(mappedBy = "bankAccount")
    private List<Transaction> transactions;

    @Override
    public String toString() {
        return "BankAccount{" +
                "bankAccountId=" + bankAccountId +
                ", bankAccountNumber='" + bankAccountNumber + '\'' +
                ", amount=" + amount +
                ", bankAccountType=" + bankAccountType.toString() +
                ", bank=" + bank.getBankName() +
                '}';
    }
}
