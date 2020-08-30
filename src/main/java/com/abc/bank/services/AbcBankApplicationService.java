package com.abc.bank.services;

import java.util.Map;

/**
 * Created by Diluni on 17/12/19.
 */
public interface AbcBankApplicationService {

    Map<String, Map<String, Double>> getTransactionSummery();

}
