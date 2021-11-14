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
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "transaction_categories")
@Data
public class TransactionCategory implements Serializable {

    @Id
    @Column(name = "transaction_category_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer transactionCategoryId;

    @Column(name = "transaction_category")
    private String transactionCategoryName;

    @JsonIgnore
    @OneToMany(mappedBy = "transactionCategory")
    private List<Transaction> transactions;
}
