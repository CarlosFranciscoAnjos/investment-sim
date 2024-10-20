package org.investmentsimspring.services.calculators;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author Carlos Anjos
 *         <p>
 *         Utility class for loan calculation
 */
public class LoansCalculator {

    /**
     * @param principal of the loan (PV)
     * @param rate      per year in the scale 0-1 (i)
     * @param term      in months (n)
     * @return the monthly payment (PMT)
     */
    public static double calculateMonthlyPayment(double principal, double rate, int term) {
        double i = rate / 12;
        return principal * (i * Math.pow(1 + i, term)) /
                (Math.pow(1 + i, term) - 1);
    }

    /**
     * @param monthlyPayment (PMT)
     * @param rate           per year in the scale 0-1 (i)
     * @param term           in months (n)
     * @return the loan principal (PV)
     */
    public static int calculatePrincipal(double monthlyPayment, double rate, int term) {
        double i = rate / 12;
        return (int) Math.round((monthlyPayment / i) * (1 - (1 / Math.pow(1 + i, term))));
    }

    /**
     * @param principal of the loan (PV)
     * @param rate      per year in the scale 0-1 (i)
     * @param term      in months (n)
     * @return amortization made in given month
     */
    public static double calculateAmortization(double principal, double rate, int term, int current) {
        double interest = calculateInterest(principal, rate, term, current);
        double monthlyPayment = calculateMonthlyPayment(principal, rate, term);
        return monthlyPayment - interest;
    }

    /**
     * @param principal of the loan (PV)
     * @param rate      per year in the scale 0-1 (i)
     * @param term      in months (n)
     * @return total amount paid
     */
    public static double calculateTotalAmount(double principal, double rate, int term) {
        return principal + calculateTotalInterest(principal, rate, term);
    }

    /**
     * @param principal of the loan (PV)
     * @param rate      per year in the scale 0-1 (i)
     * @param term      in months (n)
     * @param current   month
     * @return interest paid in given month
     */
    public static double calculateInterest(double principal, double rate, int term, int current) {
        // double monthlyPayment = calculateMonthlyPayment(principal, rate, term);
        // double i = rate / 12;
        // double remainingBalance = principal * Math.pow(1 + i, current)
        // - (monthlyPayment * ((Math.pow(1 + i, current) - 1) / i));
        // return remainingBalance * i;

        // Convert the double values to BigDecimal for precise calculations
        BigDecimal principalBD = BigDecimal.valueOf(principal);
        BigDecimal rateBD = BigDecimal.valueOf(rate);

        // Calculate the monthly interest rate with high precision
        BigDecimal monthlyRate = rateBD.divide(BigDecimal.valueOf(12), 10, RoundingMode.HALF_UP);

        // Calculate the monthly payment using BigDecimal
        BigDecimal monthlyPayment = BigDecimal.valueOf(calculateMonthlyPayment(principal, rate, term));

        // Calculate the remaining balance before the current month using BigDecimal
        BigDecimal onePlusRatePowerCurrentMinus1 = monthlyRate.add(BigDecimal.ONE).pow(current - 1);
        BigDecimal balanceBefore = principalBD.multiply(onePlusRatePowerCurrentMinus1)
                .subtract(monthlyPayment.multiply(onePlusRatePowerCurrentMinus1.subtract(BigDecimal.ONE))
                        .divide(monthlyRate, 10, RoundingMode.HALF_UP));

        // Interest for the current month is calculated as the remaining balance before
        // the current month times the monthly interest rate
        BigDecimal interestForCurrentMonth = balanceBefore.multiply(monthlyRate);

        // Convert the BigDecimal result back to double
        return interestForCurrentMonth.doubleValue();
    }

    /**
     * @param principal of the loan (PV)
     * @param rate      per year in the scale 0-1 (i)
     * @param term      in months (n)
     * @return total interest paid
     */
    public static double calculateTotalInterest(double principal, double rate, int term) {
        double i = rate / 12;
        double c = calculateMonthlyPayment(principal, rate, term);
        return (principal * i - c) * ((Math.pow(1 + i, term) - 1) / i) + (c * term);
    }

}
