package com.abc.bank.services.impl;

import com.abc.bank.repositories.CustomerRepository;
import com.abc.bank.services.AbcBankApplicationService;
import com.abc.bank.to.LightWeightObjectTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;

import static java.util.stream.Collectors.*;

/**
 * Created by Diluni on 17/12/19.
 */
@Service
public class AbcBankApplicationServiceImpl implements AbcBankApplicationService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Map<String, Map<String, Double>> getTransactionSummery() {

        List<LightWeightObjectTO> lightWeightObjectTOs = customerRepository.findTransactionSummery(Date.from(ZonedDateTime.of(LocalDate.now()
                , LocalTime.MIN, ZoneId.systemDefault()).toInstant()), Date.from(ZonedDateTime.of(LocalDate.now(), LocalTime.MAX, ZoneId.systemDefault()).toInstant()));

        Map<String, Map<String, Double>> transactionSummery = lightWeightObjectTOs.stream()
                .collect(groupingBy(LightWeightObjectTO::getCustomerName, groupingBy(LightWeightObjectTO::getTransactionType, summingDouble(LightWeightObjectTO::getAmount))));

        return transactionSummery;
    }

}
