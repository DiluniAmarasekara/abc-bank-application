package com.abc.bank.services.bankService.impl;

import com.abc.bank.entities.Account;
import com.abc.bank.entities.Customer;
import com.abc.bank.entities.Transaction;
import com.abc.bank.repositories.AccountRepository;
import com.abc.bank.repositories.CustomerRepository;
import com.abc.bank.repositories.TransactionRepository;
import com.abc.bank.services.bankService.AbcBankApplicationService;
import com.abc.bank.to.LightWeightObjectTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;

import static java.util.stream.Collectors.*;

/**
 * Created by Diluni on 25/08/20.
 */
@Service
public class AbcBankApplicationServiceImpl implements AbcBankApplicationService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Map<Long, Map<String, Double>> getTransactionSummery() {
        List<LightWeightObjectTO> lightWeightObjectTOs = customerRepository.findTransactionSummery(Date.from(ZonedDateTime.of(LocalDate.now()
                , LocalTime.MIN, ZoneId.systemDefault()).toInstant()), Date.from(ZonedDateTime.of(LocalDate.now(), LocalTime.MAX, ZoneId.systemDefault()).toInstant()));

        Map<Long, Map<String, Double>> transactionSummery = lightWeightObjectTOs.stream()
                .collect(groupingBy(LightWeightObjectTO::getId, groupingBy(LightWeightObjectTO::getTransactionType, summingDouble(LightWeightObjectTO::getAmount))));

        return transactionSummery;
    }

    @Override
    public Map<Long, Map<String, Double>> getTransSummeryByCustomerId(Long customerId) {

        List<LightWeightObjectTO> lightWeightObjectTOs = customerRepository.findTransSummeryByCustomerId(customerId, Date.from(ZonedDateTime.of(LocalDate.now()
                , LocalTime.MIN, ZoneId.systemDefault()).toInstant()), Date.from(ZonedDateTime.of(LocalDate.now(), LocalTime.MAX, ZoneId.systemDefault()).toInstant()));

        Map<Long, Map<String, Double>> transactionSummery = lightWeightObjectTOs.stream()
                .collect(groupingBy(LightWeightObjectTO::getId, groupingBy(LightWeightObjectTO::getTransactionType, summingDouble(LightWeightObjectTO::getAmount))));

        return transactionSummery;
    }

    @Override
    public HttpStatus addCustomer(Customer customer) {
        customerRepository.save(customer);
        return HttpStatus.OK;
    }

    @Override
    public HttpStatus addAccount(Account account) {
        accountRepository.save(account);
        return HttpStatus.OK;
    }

    @Override
    public HttpStatus addTransaction(Transaction transaction) {
        transactionRepository.save(transaction);
        return HttpStatus.OK;
    }

}
