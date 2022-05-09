package org.investmentsimspring.liabilities;

import org.investmentsimspring.domain.liabilities.loans.Loan;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

// @SpringBootTest
public class LoansUnitTest {

    @Test
    void calculateMonthlyPayment_ValidParams_PositiveDouble() {
        // arrange
        double amount = 100_000; // 100,000$
        double rate = 0.025; // 2.5%
        int term = 360; // 30 years
        double expected = 395.12; // 395.12$ per month
        double acceptableError = 0.05; // 5cent

        // act
        double result = Loan.calculateMonthlyPayment(amount, rate, term);

        // assert
        assertEquals(expected, result, acceptableError);
    }
}
