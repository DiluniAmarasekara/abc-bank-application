package com.abc.bank.to;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Diluni on 17/12/19.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LightWeightObjectTO {
    private Long id;
    private String customerName;
    private String transactionType;
    private Double amount;

    @Autowired
    public LightWeightObjectTO(Long id, String customerName, String transactionType, Double amount) {
        this.id = id;
        this.customerName = customerName;
        this.transactionType = transactionType;
        this.amount = amount;
    }

}
