package org.investmentsimspring.liabilities;

import org.investmentsimspring.domain.liabilities.loans.LoansCalculator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoansCalculatorUnitTests {

    @Test
    void calculateMonthlyPayment_ValidParams_PositiveDouble() {
        // arrange
        double amount = 100_000; // 100,000$
        double rate = 0.025; // 2.5%
        int term = 360; // 30 years
        double expected = 395.12; // 395.12$ per month
        double acceptableError = 0.05; // 5cent
        // act
        double result = LoansCalculator.calculateMonthlyPayment(amount, rate, term);
        // assert
        assertEquals(expected, result, acceptableError);
    }

    @Test
    void calculatePrincipal_ValidParams_PositiveDouble() {
        // arrange
        double monthlyPayement = 395.12; // 395.12$
        double rate = 0.025; // 2.5%
        int term = 360; // 30 years
        double expected = 100_000; // 100,000$
        double acceptableError = 1; // 1$
        // act
        double result = LoansCalculator.calculatePrincipal(monthlyPayement, rate, term);
        // assert
        assertEquals(expected, result, acceptableError);
    }

    @Test
    void calculateAmortization_ValidParams_PositiveDouble() {
        // arrange
        double amount = 100_000; // 100,000$
        double rate = 0.025; // 2.5%
        int term = 360; // 30 years
        double expected = 186.79; // 186,79$
        double acceptableError = 0.05; // 5cent
        // act
        double result = LoansCalculator.calculateAmortization(amount, rate, term, 1);
        // assert
        assertEquals(expected, result, acceptableError);
    }

    @Test
    void calculateTotalAmount_ValidParams_PositiveDouble() {
        // arrange
        double amount = 100_000; // 100,000$
        double rate = 0.025; // 2.5%
        int term = 360; // 30 years
        double expected = 142_244; // 142,244$
        double acceptableError = 1; // 1$
        // act
        double result = LoansCalculator.calculateTotalAmount(amount, rate, term);
        // assert
        assertEquals(expected, result, acceptableError);
    }

    @Test
    void calculateInterest_ValidParams_PositiveDouble() {
        // arrange
        double amount = 100_000; // 100,000$
        double rate = 0.025; // 2.5%
        int term = 360; // 30 years
        double expected = 208.33; // 208.33$
        double acceptableError = 0.05; // 5cent
        // act
        double result = LoansCalculator.calculateInterest(amount, rate, term, 1);
        // assert
        assertEquals(expected, result, acceptableError);
    }

    @Test
    void calculateTotalInterest_ValidParams_PositiveDouble() {
        // arrange
        double amount = 100_000; // 100,000$
        double rate = 0.025; // 2.5%
        int term = 360; // 30 years
        double expected = 42_244; // 42,244$
        double acceptableError = 1; // 1$
        // act
        double result = LoansCalculator.calculateTotalInterest(amount, rate, term);
        // assert
        assertEquals(expected, result, acceptableError);
    }

}
