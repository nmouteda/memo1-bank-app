Feature: Bank account operations
  Checking bank account operations

  Scenario: Successfully withdraw money when balance is enough
    Given Account with a balance of 1000
    When Trying to withdraw 500
    Then Account balance should be 500

  Scenario: Cannot withdraw more money than the account balance
    Given Account with a balance of 1000
    When Trying to withdraw 1001
    Then Operation should be denied due to insufficient funds
    And Account balance should remain 1000

  Scenario: Successfully deposit money when sum is not negative
    Given Account with a balance of 1000
    When Trying to deposit 500
    Then Account balance should be 1500

  Scenario: Cannot deposit money when sum is negative
    Given Account with a balance of 200
    When Trying to deposit -100
    Then Operation should be denied due to negative sum
    And Account balance should remain 200


Feature: Bank account promo, get 10% extra in your $2000+ deposits, up to $500

  Scenario: Successfully promo applied, cap not reached.
    Given Account with a balance of 0
    When Trying to deposit 2000
    Then Account balance should be 2200

  Scenario: Successfully promo applied, cap reached.
    Given Account with a balance of 0
    When Trying to deposit 6000
    Then Account balance should be 6500

  Scenario: Promo not applied
    Given Account with a balance of 0
    When Trying to deposit 1500
    Then Account balance should be 1500
