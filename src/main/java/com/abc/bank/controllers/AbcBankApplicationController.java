package com.abc.bank.controllers;

import com.abc.bank.services.AbcBankApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by Diluni on 17/12/19.
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AbcBankApplicationController {

    @Autowired
    private AbcBankApplicationService abcBankApplicationService;

    /**
     * get today's transaction summery
     *
     * @return
     */
    @RequestMapping(value = "/getTransactionSummery", method = RequestMethod.GET)
    public Map<String, Map<String, Double>> getTransactionSummery() {
        return abcBankApplicationService.getTransactionSummery();
    }

}
