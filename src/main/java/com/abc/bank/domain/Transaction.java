package com.abc.bank.domain;

import com.abc.bank.enumValues.enumValues;
import lombok.Data;

import javax.persistence.*;

/**
 * Created by Diluni on 17/12/19.
 */
@Entity
@Data
public class Transaction extends baseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String transactionType = enumValues.TransactionType.DEPOSIT.toString();

    private Double amount = 0.00;

    private String branch;

    private Boolean isWithinTheBank = Boolean.TRUE;

    @ManyToOne
    @JoinColumn
    private Account account;

}
