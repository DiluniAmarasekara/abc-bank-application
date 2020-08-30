package com.abc.bank.services.bankService;

import com.abc.bank.entities.Account;
import com.abc.bank.entities.Customer;
import com.abc.bank.entities.Transaction;
import org.springframework.http.HttpStatus;

import java.util.Map;

/**
 * Created by Diluni on 25/08/20.
 */
public interface AbcBankApplicationService {

    Map<Long, Map<String, Double>> getTransactionSummery();

    Map<Long, Map<String, Double>> getTransSummeryByCustomerId(Long customerId);

    HttpStatus addCustomer(Customer customer);

    HttpStatus addAccount(Account account);

    HttpStatus addTransaction(Transaction transaction);

}
