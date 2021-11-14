package eu.vargapeter.financialstatistics.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "banks")
@Data
public class Bank implements Serializable {

    @Id
    @Column(name = "bank_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bankId;

    @NotNull
    @Column(name = "bank_name")
    private String bankName;

    @JsonIgnore
    @OneToMany(mappedBy = "bank")
    private List<BankAccount> bankAccounts;
}
