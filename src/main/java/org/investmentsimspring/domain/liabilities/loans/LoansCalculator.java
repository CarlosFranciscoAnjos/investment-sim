package org.investmentsimspring.domain.liabilities.loans;

public class LoansCalculator {

    /**
     * @param amount of the loan (PV)
     * @param rate   per year in the scale 0-1 (i)
     * @param term   in months (n)
     * @return the monthly payment
     */
    public static double calculateMonthlyPayment(double amount, double rate, int term) {
        double i = rate / 12;
        double nomerator = amount * i * Math.pow(1 + i, term);
        double denominator = Math.pow(1 + i, term) - 1;
        return nomerator / denominator;
    }

    /**
     * @param monthlyPayment (PMT)
     * @param rate           per year in the scale 0-1 (i)
     * @param term           in months (n)
     * @return the total loan amount
     */
    public static double calculateLoanAmount(double monthlyPayment, double rate, int term) {
        return 0;
    }

}
