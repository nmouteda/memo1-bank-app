package com.aninfo.integration.cucumber;

import com.aninfo.model.Account;
import com.aninfo.service.AccountService;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;

public class BankAccountPromoTest extends AccountIntegrationServiceTest{

    @Autowired
    private Account account;

    @Given("^Account with a balance of (\\d+)$")
    public void account_with_balance_of(int balance){
        account = createAccount(Double.valueOf(balance));
    }

    @When("^Trying to deposit (\\d+)$")
    public void trying_to_deposit(int amount){
        accountService.deposit(account.getCbu(),Double.valueOf(amount));
    }

    @Then("Account balance should be(\\+)$")
    public void account_balance_should_be(int balance){
        assertEquals(Double.valueOf(balance),account.getBalance());
    }








}
