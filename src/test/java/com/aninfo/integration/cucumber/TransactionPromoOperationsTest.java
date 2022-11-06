package com.aninfo.integration.cucumber;

import com.aninfo.exceptions.DepositNegativeSumException;
import com.aninfo.exceptions.InsufficientFundsException;
import com.aninfo.model.Account;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;

public class TransactionPromoOperationsTest  extends AccountIntegrationServiceTest {
    private Account account;
    private InsufficientFundsException ife;
    private DepositNegativeSumException dnse;

    @Before
    public void setup() {
        System.out.println("Before any test execution");
    }
}
